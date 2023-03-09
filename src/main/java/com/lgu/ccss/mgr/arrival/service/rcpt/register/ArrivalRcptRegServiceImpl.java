package com.lgu.ccss.mgr.arrival.service.rcpt.register;

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
import com.lgu.ccss.common.model.RequestParamReceiverJSON;
import com.lgu.ccss.common.model.ResponseJSON;
import com.lgu.ccss.common.tlo.TloData;
import com.lgu.ccss.common.tlo.TloUtil;
import com.lgu.ccss.common.util.CCSSUtil;
import com.lgu.ccss.common.util.ResultCodeUtil;
import com.lgu.ccss.common.validation.CheckResultData;
import com.lgu.ccss.common.validation.ValidationChecker;
import com.lgu.common.model.ResultCode;
import com.lgu.common.util.JsonUtil;
import com.lgu.common.util.keygenerator.KeyGenerator;

@Service("arrivalRcptRegService")
public class ArrivalRcptRegServiceImpl implements ArrivalRcptRegService {

	private static final Logger logger = LoggerFactory.getLogger(ArrivalRcptRegServiceImpl.class);
	private static final List<String> mandatoryList = new ArrayList<String>(
			Arrays.asList(RequestJSON.PARAM_MEMB_ID,RequestJSON.PARAM_ARRIVAL_ALARM_ID));

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
		
		CheckResultData result = ValidationChecker.checkValidation(headers,reqJson,CCSSConst.ARRIVAL_NOTI_RCPT_REG,mandatoryList);
		
		if (result.isStatus() == false) {
			resultCode = ResultCodeUtil.RC_3C004000;
			response = ResultCodeUtil.createResultMsg(resultCode,api,result.getReason());
			return response;
		}else{
			
			try {
				
				// DB 데이터 생성
				ArrivalAlarmRcptVO arrivalAlarmRcptVO  = new ArrivalAlarmRcptVO();
			
				arrivalAlarmRcptVO.setArrivalAlarmId((String) reqJson.getParam().get(RequestJSON.PARAM_ARRIVAL_ALARM_ID));
				arrivalAlarmRcptVO.setMembId(membId);
				
				@SuppressWarnings("unchecked")
				List<RequestParamReceiverJSON>  requestParamReceiverJSONList = (List<RequestParamReceiverJSON>) reqJson.getParam().get(RequestJSON.PARAM_RECEIVER);
				
			
				
				for(int idx=0; idx < requestParamReceiverJSONList.size(); idx++){
					
					String reqParamJsonStr = JsonUtil.marshallingJson(requestParamReceiverJSONList.get(idx));
					
					RequestParamReceiverJSON tempVO = JsonUtil.unmarshallingJson(reqParamJsonStr, RequestParamReceiverJSON.class);
					arrivalAlarmRcptVO.setRcptNm(tempVO.getRcptNm());
					arrivalAlarmRcptVO.setRcptCtn(tempVO.getRcptCtn());
					arrivalAlarmRcptVO.setArrivalAlarmRcptId(KeyGenerator.createCommonShardKey());
					try {
						//DB 등록
						boolean regFlag = arrivalNotiDao.insertArrivalAlarmRcpt(arrivalAlarmRcptVO);
						
						if(regFlag){
							logger.info("Arrival Alarm Info Reg Success. sessionId({}) membId({}) mgrappId({})  mgrappLoginId({}) arrivalAlarmSetVO({})",sessionId,membId,mgrappId,mgrappLoginId ,arrivalAlarmRcptVO);
						}else{
							resultCode=ResultCodeUtil.RC_4C005001;
							logger.error("Arrival Alarm Info Reg Fail.   sessionId({}) membId({}) mgrappId({})  mgrappLoginId({}) arrivalAlarmSetVO({})",sessionId,membId,mgrappId,mgrappLoginId,arrivalAlarmRcptVO.toString());
						}
						
					}catch(Exception e){
						logger.error("Arrival Alarm Info Reg Fail.   sessionId({}) membId({}) mgrappId({})  mgrappLoginId({}) arrivalAlarmSetVO({}) exception({})",sessionId,membId,mgrappId,mgrappLoginId,arrivalAlarmRcptVO.toString(),e);
					}
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
