package com.lgu.ccss.mgr.arrival.service.delete;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;

import com.lgu.ccss.common.constant.CCSSConst;
import com.lgu.ccss.common.dao.ArrivalNotiDao;
import com.lgu.ccss.common.dao.mgr.MgrAppUserDao;
import com.lgu.ccss.common.dao.pushGw.PushGwMessageDao;
import com.lgu.ccss.common.model.ArrivalAlarmSetVO;
import com.lgu.ccss.common.model.RequestJSON;
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

@Service("arrivalDelService")
public class ArrivalDelServiceImpl implements ArrivalDelService{

	private static final Logger logger = LoggerFactory.getLogger(ArrivalDelServiceImpl.class);
	private static final List<String> mandatoryList = new ArrayList<String>(
			Arrays.asList(RequestJSON.PARAM_MEMB_ID,RequestJSON.PARAM_ARRIVAL_ALARM_ID));
	
	@Value("#{config['push.arrivalNotiTitle']}")
	private String arrivalNotiTitle;
	
	@Value("#{config['push.arrivalNotiContent']}")
	private String arrivalNotiContent;
	
	@Autowired
	private PushGwMessageDao pushGwMessageDao;
	
	@Autowired
	private MgrAppUserDao mgrAppUserDao;
	
	
	@Autowired
	private ArrivalNotiDao arrivalNotiDao;
	
	
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
		
		CheckResultData result = ValidationChecker.checkValidation(headers,reqJson,CCSSConst.ARRIVAL_NOTI_DEL,mandatoryList);
		
		if (result.isStatus() == false) {
			resultCode = ResultCodeUtil.RC_3C004000;
			response = ResultCodeUtil.createResultMsg(resultCode,api,result.getReason());
			return response;
		}else{
			
			try {
				
				ArrivalAlarmSetVO arrivalAlarmSetVO = new ArrivalAlarmSetVO();
				arrivalAlarmSetVO.setMembId(membId);
				arrivalAlarmSetVO.setArrivalAlarmId((String) reqJson.getParam().get(RequestJSON.PARAM_ARRIVAL_ALARM_ID));
				
				boolean regFlag = arrivalNotiDao.deleteArrivalAlarmInfo(arrivalAlarmSetVO);

				
				if(regFlag){
					logger.info("Arrival Alarm Info Delete Success. sessionId({}) membId({}) mgrappId({})  mgrappLoginId({})",sessionId,membId,mgrappId,mgrappLoginId);
					
					String carOem = (String) reqJson.getCommon().getDevice().getCarOem();
					String pushCarOem;
					if (carOem.equals("")) {
						pushCarOem = PushGwMessageVO.THINKWARE;
					} else if (carOem.equals("NS")) {
						pushCarOem = PushGwMessageVO.NS;
					} else {
						pushCarOem = PushGwMessageVO.SY;
					}
					
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
					logger.error("Arrival Alarm Info Delete  Fail.  sessionId({}) membId({}) mgrappId({})  mgrappLoginId({}) arrivalAlarmSetVO({})",sessionId,membId,mgrappId,mgrappLoginId,arrivalAlarmSetVO.toString());
				}

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
