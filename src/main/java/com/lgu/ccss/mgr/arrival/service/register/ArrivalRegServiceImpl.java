package com.lgu.ccss.mgr.arrival.service.register;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;

import com.lgu.ccss.common.constant.CCSSConst;
import com.lgu.ccss.common.dao.ArrivalNotiDao;
import com.lgu.ccss.common.dao.mgr.MgrAppManagementDao;
import com.lgu.ccss.common.dao.mgr.MgrAppUserDao;
import com.lgu.ccss.common.dao.pushGw.PushGwMessageDao;
import com.lgu.ccss.common.model.ArrivalAlarmSetVO;
import com.lgu.ccss.common.model.MembVO;
import com.lgu.ccss.common.model.RequestJSON;
import com.lgu.ccss.common.model.RequestParamTargetJSON;
import com.lgu.ccss.common.model.ResponseJSON;
import com.lgu.ccss.common.model.mgr.MgrAppUserVO;
import com.lgu.ccss.common.model.pushgw.PushGwMessageVO;
import com.lgu.ccss.common.tlo.TloData;
import com.lgu.ccss.common.tlo.TloUtil;
import com.lgu.ccss.common.util.CCSSUtil;
import com.lgu.ccss.common.util.ResultCodeUtil;
import com.lgu.ccss.common.validation.CheckResultData;
import com.lgu.ccss.common.validation.ValidationChecker;
import com.lgu.common.model.ResultCode;
import com.lgu.common.util.AES256Util;
import com.lgu.common.util.JsonUtil;
import com.lgu.common.util.keygenerator.KeyGenerator;

@Service("arrivalRegService")
public class ArrivalRegServiceImpl implements ArrivalRegService{

	private static final Logger logger = LoggerFactory.getLogger(ArrivalRegServiceImpl.class);
	private static final List<String> mandatoryList = new ArrayList<String>(
			Arrays.asList(RequestJSON.PARAM_MEMB_ID,RequestJSON.PARAM_USE_YN));
	
	@Value("#{config['push.arrivalNotiTitle']}")
	private String arrivalNotiTitle;
	
	@Value("#{config['push.arrivalNotiContent']}")
	private String arrivalNotiContent;
	
	@Autowired
	private MgrAppManagementDao mgrAppManagementDao;
	
	@Autowired
	private MgrAppUserDao mgrAppUserDao;
	
	
	@Autowired
	private ArrivalNotiDao arrivalNotiDao;
	
	@Autowired
	private PushGwMessageDao pushGwMessageDao;
	
	//TO-DO
	@Override
	public ResponseJSON doService(HttpHeaders headers, RequestJSON reqJson) {
		ResultCode resultCode=ResultCodeUtil.RC_2S000000;
		String api = reqJson.getCommon().getApiId();
		String mgrappLoginId = CCSSUtil.getMgrUserLoginId();
		String sessionId = CCSSUtil.getMgrSessionId();
		String mgrappId = CCSSUtil.getMgrappId();
		String membId = (String) reqJson.getParam().get(RequestJSON.PARAM_MEMB_ID);
		
		setTloData(membId);
		ResponseJSON response = null;
		
		CheckResultData result = ValidationChecker.checkValidation(headers,reqJson,CCSSConst.ARRIVAL_NOTI_REG,mandatoryList);
		
		if (result.isStatus() == false) {
			resultCode = ResultCodeUtil.RC_3C004000;
			response = ResultCodeUtil.createResultMsg(resultCode,api,result.getReason());
			return response;
		}else{
			
			try {
				
				//1.현재 MembId 로 transTocken 조회 
				MembVO membVO = new MembVO();
				membVO.setMembId(membId);
				membVO.setMgrappId(mgrappId);
				membVO = mgrAppManagementDao.selectMembTransToken(membVO);
				
				if(membVO !=null){
					
					//2. transTocekn으로 App 에서 올라온 logx,laty 복호화
					String reqParamJsonStr = JsonUtil.marshallingJson(reqJson.getParam().get(RequestJSON.PARAM_TARGET));
					RequestParamTargetJSON requestParamTargetJSON = JsonUtil.unmarshallingJson(reqParamJsonStr, RequestParamTargetJSON.class);
				
					String descTargetLonx = AES256Util.AESDecode(membVO.getTransToken(), requestParamTargetJSON.getLonx());
					String descTargetLaty = AES256Util.AESDecode(membVO.getTransToken(), requestParamTargetJSON.getLaty());
					
					//3. DB 암호화
					String encTargetLonx = AES256Util.AESEncode(CCSSConst.LOCATION_ENC_KEY ,descTargetLonx);
					String encTargetLaty = AES256Util.AESEncode(CCSSConst.LOCATION_ENC_KEY , descTargetLaty);		
					
					// DB 데이터 생성
					ArrivalAlarmSetVO arrivalAlarmSetVO  = new ArrivalAlarmSetVO();
					arrivalAlarmSetVO.setArrivalAlarmId(KeyGenerator.createCommonShardKey());
					arrivalAlarmSetVO.setMembId(membId);
					arrivalAlarmSetVO.setUseYn((String) reqJson.getParam().get(RequestJSON.PARAM_USE_YN));
					arrivalAlarmSetVO.setTargetAddr(requestParamTargetJSON.getAddress());
					arrivalAlarmSetVO.setTargetLonx(encTargetLonx);
					arrivalAlarmSetVO.setTargetLaty(encTargetLaty);
					arrivalAlarmSetVO.setName(requestParamTargetJSON.getName());
					arrivalAlarmSetVO.setRoadjibun(requestParamTargetJSON.getRoadjibun());
					
					//DB 등록
					boolean regFlag = arrivalNotiDao.insertArrivalAlarmInfo(arrivalAlarmSetVO);
					
					
					if(regFlag){
						String carOem = (String) reqJson.getCommon().getDevice().getCarOem();
						String pushCarOem;
						if (carOem.equals("")) {
							pushCarOem = PushGwMessageVO.THINKWARE;
						} else if (carOem.equals("NS")) {
							pushCarOem = PushGwMessageVO.NS;
						} else {
							pushCarOem = PushGwMessageVO.SY;
						}
						logger.info("Arrival Alarm Info Reg Success. sessionId({}) membId({}) mgrappId({})  mgrappLoginId({})",sessionId,membId,mgrappId,mgrappLoginId);
						//App Push 전송
						MgrAppUserVO mgrAppUserVO = new MgrAppUserVO();
						mgrAppUserVO.setMembId(membId);
						mgrAppUserVO.setMgrappLoginId(mgrappLoginId);
						List<MgrAppUserVO> userList =mgrAppUserDao.selectMgrAppPusthTargetUserList(mgrAppUserVO);
						
						for(MgrAppUserVO tmpVO : userList){
							try{
								
								PushGwMessageVO pushGwMessageVO = new PushGwMessageVO();
									pushGwMessageVO = PushGwMessageVO.makePushMessage(tmpVO, null, PushGwMessageVO.MASSAGE_TYPE_SINGLE, 
											PushGwMessageVO.ARRIVAL_NOTI_PUSH_CODE,arrivalNotiTitle,arrivalNotiContent, null, null, pushCarOem);
								
								boolean sendMqttResulFlag = pushGwMessageDao.insertTbAppPushQueue(pushGwMessageVO);
							
								if(sendMqttResulFlag){
									
									logger.info("Send App Push Message Insert Success. sessionId({}) membId({}) mgrappId({})  mgrappLoginId({})",sessionId,membId,mgrappId,mgrappLoginId);
							
								}else{
									resultCode=ResultCodeUtil.RC_4C005001;
									logger.error("Send App Push Message Insert. Fail. sessionId({}) membId({}) mgrappId({})  mgrappLoginId({}) pushGwMessageVO({})",sessionId,membId,mgrappId,mgrappLoginId,pushGwMessageVO.toString());
								}
								
							}catch(Exception e){
								logger.error("Push Message Insert fail. sessionId({}) membId({}) mgrappId({})  mgrappLoginId({}) targetCtn({}) exception({})",sessionId,membId,mgrappId,tmpVO.getMgrappLoginId() ,e);
								continue;
							}
						}
					}else{
						resultCode=ResultCodeUtil.RC_4C005001;
						logger.error("Arrival Alarm Info Reg Fail.   sessionId({}) membId({}) mgrappId({})  mgrappLoginId({}) arrivalAlarmSetVO({})",sessionId,membId,mgrappId,mgrappLoginId,arrivalAlarmSetVO.toString());
					}
				}else{
					resultCode=ResultCodeUtil.RC_3C002004;
					logger.error("Not Exist Memb Information sessionId({}) membId({}) mgrappId({})  mgrappLoginId({})  ",sessionId,membId,mgrappId,mgrappLoginId);
				}
				
			} catch(BadPaddingException be){
				resultCode = ResultCodeUtil.RC_3C004005;
				logger.error("sessionId({}) mgrappLoginId({}) Exception({})", sessionId,mgrappLoginId,be);
			}catch(IllegalBlockSizeException ibse){
				resultCode = ResultCodeUtil.RC_3C004005;
				logger.error("sessionId({}) mgrappLoginId({}) Exception({})", sessionId,mgrappLoginId,ibse);
			} catch (Exception e) {
				resultCode = ResultCodeUtil.RC_4C005000;
				logger.error("sessionId({}) mgrappLoginId({}) Exception({})", sessionId,mgrappLoginId,e);
			}
		}
		
		response = ResultCodeUtil.createResultMsg(resultCode,api);
		return response;
	}
	
	private void setTloData(String memId ) {
		if (memId == null) {
			return;
		}
		Map<String, String> tlo = new HashMap<String, String>();
		tlo.put(TloData.MEMB_ID, memId);
		//tlo.put(TloData.MEMB_NO, memb.getMembNo());
		TloUtil.setTloData(tlo);
	}
}
