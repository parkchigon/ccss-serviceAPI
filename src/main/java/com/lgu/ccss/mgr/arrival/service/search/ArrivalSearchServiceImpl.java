package com.lgu.ccss.mgr.arrival.service.search;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;

import com.lgu.ccss.common.constant.CCSSConst;
import com.lgu.ccss.common.dao.ArrivalNotiDao;
import com.lgu.ccss.common.dao.mgr.MgrAppManagementDao;
import com.lgu.ccss.common.model.ArrivalAlarmSetVO;
import com.lgu.ccss.common.model.MembVO;
import com.lgu.ccss.common.model.RequestJSON;
import com.lgu.ccss.common.model.RequestParamTargetJSON;
import com.lgu.ccss.common.model.ResponseJSON;
import com.lgu.ccss.common.tlo.TloData;
import com.lgu.ccss.common.tlo.TloUtil;
import com.lgu.ccss.common.util.CCSSUtil;
import com.lgu.ccss.common.util.ResultCodeUtil;
import com.lgu.ccss.common.validation.CheckResultData;
import com.lgu.ccss.common.validation.ValidationChecker;
import com.lgu.ccss.mgr.arrival.model.rcpt.search.DestListJSON;
import com.lgu.ccss.mgr.arrival.model.rcpt.search.ReceiverJSON;
import com.lgu.ccss.mgr.arrival.model.rcpt.search.ResultArrivalSearchJSON;
import com.lgu.ccss.mgr.arrival.model.rcpt.search.ResultArrivalSearchVO;
import com.lgu.common.model.ResultCode;
import com.lgu.common.util.AES256Util;

@Service("arrivalSearchService")
public class ArrivalSearchServiceImpl implements ArrivalSearchService{

	private static final Logger logger = LoggerFactory.getLogger(ArrivalSearchServiceImpl.class);
	private static final List<String> mandatoryList = new ArrayList<String>(
			Arrays.asList(RequestJSON.PARAM_MEMB_ID,RequestJSON.PARAM_START_POSITION,RequestJSON.PARAM_REQ_COUNT));

	@Autowired
	private MgrAppManagementDao mgrAppManagementDao;
	
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
		ResultArrivalSearchJSON resultArrivalSearchJSON =null;
		
		CheckResultData result = ValidationChecker.checkValidation(headers,reqJson,CCSSConst.ARRIVAL_NOTI_LIST,mandatoryList);
		
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
					ArrivalAlarmSetVO arrivalAlarmSetVO  = new ArrivalAlarmSetVO();
					arrivalAlarmSetVO.setMembId(membId);
					arrivalAlarmSetVO.setStartPosition((String) reqJson.getParam().get(RequestJSON.PARAM_START_POSITION));
					arrivalAlarmSetVO.setReqCount((String) reqJson.getParam().get(RequestJSON.PARAM_REQ_COUNT));
					
					//도착 알림 리스트 조회
					List<ResultArrivalSearchVO>  arrivalAlarmInfoList = arrivalNotiDao.selectArrivalAlarmInfo(arrivalAlarmSetVO);
					
					if(arrivalAlarmInfoList !=null  && arrivalAlarmInfoList.size() > 0 ){
						resultArrivalSearchJSON = new ResultArrivalSearchJSON();
						
						List<DestListJSON> destList = new LinkedList<DestListJSON>();
						List<ReceiverJSON> rctpList = null;
						
						for(ResultArrivalSearchVO tempVO : arrivalAlarmInfoList){
							DestListJSON dest = new DestListJSON();
							dest.setIndex(tempVO.getIndex());
							dest.setArrivalAlarmId(tempVO.getArrivalAlarmId());
							dest.setUseYn(tempVO.getUseYn());
							RequestParamTargetJSON target  =new RequestParamTargetJSON();
							target.setAddress(tempVO.getAddress());
							//암복호화
							String desLonx = AES256Util.AESDecode(CCSSConst.LOCATION_ENC_KEY ,tempVO.getLonx());
							String desLaty = AES256Util.AESDecode(CCSSConst.LOCATION_ENC_KEY , tempVO.getLaty());	
							target.setLonx(AES256Util.AESEncode(membVO.getTransToken()  ,desLonx));
							target.setLaty(AES256Util.AESEncode(membVO.getTransToken()  ,desLaty));
							target.setName(tempVO.getName());
							target.setRoadjibun(tempVO.getRoadjibun());
							dest.setTarget(target);
							
							//수신자 조회
							arrivalAlarmSetVO.setArrivalAlarmId(tempVO.getArrivalAlarmId());
							List<ResultArrivalSearchVO>  arrivalAlarmRcptList = arrivalNotiDao.selectArrivalRcptInfo(arrivalAlarmSetVO);
							
							if(arrivalAlarmRcptList !=null ){
								rctpList  =  new LinkedList<ReceiverJSON>();
								
								for(ResultArrivalSearchVO rctpSearchVO : arrivalAlarmRcptList){
									
									ReceiverJSON receiver =  new ReceiverJSON();
									receiver.setArrivalAlarmRcptId(rctpSearchVO.getArrivalAlarmRcptId());
									receiver.setRcptNm(rctpSearchVO.getRcptNm());
									receiver.setRcptCtn(rctpSearchVO.getRcptCtn());
									rctpList.add(receiver);
								}
								dest.setReceiver(rctpList);
							}
							
							destList.add(dest);
						}
						
						resultArrivalSearchJSON.setArrivalAlramList(destList);
						resultArrivalSearchJSON.setTotalCount(arrivalAlarmInfoList.get(0).getTotalCount());
					}else{
						resultCode=ResultCodeUtil.RC_9C200000;
						logger.debug("Not Exist Arrival Alarm Search List sessionId({}) membId({}) mgrappId({})  mgrappLoginId({})",sessionId,membId,mgrappId,mgrappLoginId);
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
		
		response = ResultCodeUtil.createResultMsg(resultCode,resultArrivalSearchJSON,api);
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
