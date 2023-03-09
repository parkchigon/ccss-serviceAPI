package com.lgu.ccss.mgr.send2car.service.history.delete;

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
import com.lgu.ccss.common.dao.Send2CarDao;
import com.lgu.ccss.common.model.RequestJSON;
import com.lgu.ccss.common.model.ResponseJSON;
import com.lgu.ccss.common.model.Send2CarVO;
import com.lgu.ccss.common.tlo.TloData;
import com.lgu.ccss.common.tlo.TloUtil;
import com.lgu.ccss.common.util.CCSSUtil;
import com.lgu.ccss.common.util.ResultCodeUtil;
import com.lgu.ccss.common.validation.CheckResultData;
import com.lgu.ccss.common.validation.ValidationChecker;
import com.lgu.common.model.ResultCode;

@Service("targetHistoryDelService")
public class TargetHistoryDelServiceImpl implements TargetHistoryDelService{

	private static final Logger logger = LoggerFactory.getLogger(TargetHistoryDelServiceImpl.class);
	private static final List<String> mandatoryList = new ArrayList<String>(
			Arrays.asList(RequestJSON.PARAM_MEMB_ID ,RequestJSON.PARAM_SEND_TO_CAR_ID));

	@Autowired
	private Send2CarDao send2CarDao;
	
	@Override
	public ResponseJSON doService(HttpHeaders headers, RequestJSON reqJson) {
		ResultCode resultCode=ResultCodeUtil.RC_2S000000;
		String api = reqJson.getCommon().getApiId();
		String mgrappLoginId = CCSSUtil.getMgrUserLoginId();
		String sessionId = CCSSUtil.getMgrSessionId();
		String membId = (String) reqJson.getParam().get(RequestJSON.PARAM_MEMB_ID);
		String mgrappId = CCSSUtil.getMgrappId(); 
		
		ResponseJSON response = null;
		

		CheckResultData result = ValidationChecker.checkValidation(headers,reqJson,CCSSConst.SENDTOCAR_HISTORY_DEL,mandatoryList);
		
		if (result.isStatus() == false) {
			resultCode = ResultCodeUtil.RC_3C004000;
			response = ResultCodeUtil.createResultMsg(resultCode,api,result.getReason());
			return response;
		}else{
			
			try {
			
				
				Send2CarVO send2CarVO = new Send2CarVO();
				
				//String strSendToCarSeq = (String) reqJson.getParam().get(RequestJSON.PARAM_SEND_TO_CAR_SEQ);
				//BigInteger bigIntegerStr=new BigInteger(strSendToCarSeq);
				//send2CarVO.setSendToCarSeq(bigIntegerStr);
				
				send2CarVO.setSendToCarId((String) reqJson.getParam().get(RequestJSON.PARAM_SEND_TO_CAR_ID));
				
				send2CarVO.setUseYn(CCSSConst.DEF_NO);
				send2CarVO.setMembId(membId);
				send2CarVO.setMgrappId(mgrappId);
				send2CarVO.setSendStatus(Send2CarVO.SEND_STATUS_DELETE);
				
				int count = send2CarDao.deleteTargetHis(send2CarVO);
				setTloData(membId);
				
				if(count > 0){
					logger.info("Success Delete Target History sessionId({}) membId({}) mgrappId({})  mgrappLoginId({}) ",sessionId,membId,mgrappId,mgrappLoginId);
				}else{
					resultCode = ResultCodeUtil.RC_9C000001;
					logger.error("Not Exist Target History sessionId({}) membId({}) mgrappId({})  mgrappLoginId({}) ",sessionId,membId,mgrappId,mgrappLoginId);
				}
				
			} catch (Exception e) {
				resultCode = ResultCodeUtil.RC_4C005000;
				logger.error("sessionId({}) membId({}) mgrappId({})  mgrappLoginId({}) Exception({})", sessionId,membId,mgrappId,mgrappLoginId,e);
			}
		}
		
		response = ResultCodeUtil.createResultMsg(resultCode,api);
		return response;
	}
	
	private void setTloData(String membId) {
		if (membId == null) {
			return;
		}
		Map<String, String> tlo = new HashMap<String, String>();
		tlo.put(TloData.MEMB_ID, membId);
		//tlo.put(TloData.MEMB_NO, memb.getMembNo());
		TloUtil.setTloData(tlo);
	}
}
