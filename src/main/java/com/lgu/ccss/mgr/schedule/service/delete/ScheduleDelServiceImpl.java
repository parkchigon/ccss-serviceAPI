package com.lgu.ccss.mgr.schedule.service.delete;

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
import com.lgu.ccss.common.dao.ScheduleDao;
import com.lgu.ccss.common.dao.Send2CarDao;
import com.lgu.ccss.common.model.RequestJSON;
import com.lgu.ccss.common.model.ResponseJSON;
import com.lgu.ccss.common.model.ScheduleSetVO;
import com.lgu.ccss.common.model.Send2CarVO;
import com.lgu.ccss.common.tlo.TloData;
import com.lgu.ccss.common.tlo.TloUtil;
import com.lgu.ccss.common.util.CCSSUtil;
import com.lgu.ccss.common.util.ResultCodeUtil;
import com.lgu.ccss.common.validation.CheckResultData;
import com.lgu.ccss.common.validation.ValidationChecker;
import com.lgu.common.model.ResultCode;

@Service("scheduleDelService")
public class ScheduleDelServiceImpl implements ScheduleDelService{

	private static final Logger logger = LoggerFactory.getLogger(ScheduleDelServiceImpl.class);
	private static final List<String> mandatoryList = new ArrayList<String>(
			Arrays.asList(RequestJSON.PARAM_MEMB_ID,RequestJSON.PARAM_SCHEULE_ID));

	
	@Autowired
	private ScheduleDao scheduleDao;
	
	@Autowired
	private Send2CarDao send2CarDao;
	
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
		
		CheckResultData result = ValidationChecker.checkValidation(headers,reqJson,CCSSConst.SCHEDULE_DEL,mandatoryList);
		
		if (result.isStatus() == false) {
			resultCode = ResultCodeUtil.RC_3C004000;
			response = ResultCodeUtil.createResultMsg(resultCode,api,result.getReason());
			return response;
		}else{
			
			try {
				
				ScheduleSetVO scheduleSetVO = new ScheduleSetVO();
				scheduleSetVO.setMembId(membId);
				scheduleSetVO.setMgrappId(mgrappId);
				//String strScheduleSeq = (String) reqJson.getParam().get(RequestJSON.PARAM_SCHEULE_SEQ);
				//BigInteger bigIntegerStr=new BigInteger(strScheduleSeq);
				//scheduleSetVO.setScheduleSeq(bigIntegerStr);
				
				String scheduleId = (String) reqJson.getParam().get(RequestJSON.PARAM_SCHEULE_ID);
				scheduleSetVO.setScheduleId(scheduleId);
				
				boolean regFlag = scheduleDao.deleteSecheuleInfo(scheduleSetVO);
				
				if(regFlag){
					logger.info("Schedul Info Update Success. sessionId({}) membId({}) mgrappId({})  mgrappLoginId({})",sessionId,membId,mgrappId,mgrappLoginId);
					//mgrapp_id / schedule_id mgrapp_id / schedule_id 조건 TB_SEND2CAR 테이블 삭제  SEND_STATUS = READY
					Send2CarVO send2CarVO = new Send2CarVO();
					send2CarVO.setMgrappId(mgrappId);
					send2CarVO.setMembId(membId);
					send2CarVO.setScheduleId(scheduleId);
					send2CarVO.setSendStatus(Send2CarVO.SEND_STATUS_SCHEDULE);
					
					send2CarDao.deleteScheduleTarget(send2CarVO);
				}else{
					resultCode=ResultCodeUtil.RC_4C005001;
					logger.error("Schedul Info Delete  Fail. arrivHopeTime({}) repeatAlarmDay({}) sessionId({}) membId({}) mgrappId({})  mgrappLoginId({}) scheduleSetVO({})",sessionId,membId,mgrappId,mgrappLoginId,scheduleSetVO.toString());
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
