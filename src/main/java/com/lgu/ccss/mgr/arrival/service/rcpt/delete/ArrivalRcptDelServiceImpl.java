package com.lgu.ccss.mgr.arrival.service.rcpt.delete;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;

import com.lgu.ccss.common.constant.CCSSConst;
import com.lgu.ccss.common.dao.ArrivalNotiDao;
import com.lgu.ccss.common.model.ArrivalAlarmRcptVO;
import com.lgu.ccss.common.model.RequestJSON;
import com.lgu.ccss.common.model.ResponseJSON;
import com.lgu.ccss.common.tlo.TloData;
import com.lgu.ccss.common.tlo.TloUtil;
import com.lgu.ccss.common.util.CCSSUtil;
import com.lgu.ccss.common.util.ResultCodeUtil;
import com.lgu.ccss.common.validation.CheckResultData;
import com.lgu.ccss.common.validation.ValidationChecker;
import com.lgu.common.model.ResultCode;

@Service("arrivalRcptDelService")
public class ArrivalRcptDelServiceImpl implements ArrivalRcptDelService{

	private static final Logger logger = LoggerFactory.getLogger(ArrivalRcptDelServiceImpl.class);
	private static final List<String> mandatoryList = new ArrayList<String>(
			Arrays.asList(RequestJSON.PARAM_MEMB_ID,RequestJSON.PARAM_ARRIVAL_ALARM_RCPT_ID));

	
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
		
		CheckResultData result = ValidationChecker.checkValidation(headers,reqJson,CCSSConst.ARRIVAL_NOTI_RCPT_DEL,mandatoryList);
		
		if (result.isStatus() == false) {
			resultCode = ResultCodeUtil.RC_3C004000;
			response = ResultCodeUtil.createResultMsg(resultCode,api,result.getReason());
			return response;
		}else{
			
			try {
				
				ArrivalAlarmRcptVO arrivalAlarmRcptVO = new ArrivalAlarmRcptVO();
				arrivalAlarmRcptVO.setArrivalAlarmRcptId((String) reqJson.getParam().get(RequestJSON.PARAM_ARRIVAL_ALARM_RCPT_ID));
				
				boolean regFlag = arrivalNotiDao.deleteArrivalAlarmRcpt(arrivalAlarmRcptVO);

				
				if(regFlag){
					logger.info("Arrival Alarm Rcpt Info Delete Success. sessionId({}) membId({}) mgrappId({})  mgrappLoginId({})",sessionId,membId,mgrappId,mgrappLoginId);
					
				}else{
					resultCode=ResultCodeUtil.RC_4C005001;
					logger.error("Arrival Alarm Rcpt Info Delete  Fail. sessionId({}) membId({}) mgrappId({})  mgrappLoginId({}) arrivalAlarmRcptVO({})",sessionId,membId,mgrappId,mgrappLoginId,arrivalAlarmRcptVO.toString());
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
