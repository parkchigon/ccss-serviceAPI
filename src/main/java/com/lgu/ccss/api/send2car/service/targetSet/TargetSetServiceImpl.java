package com.lgu.ccss.api.send2car.service.targetSet;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;

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
import com.lgu.ccss.common.util.CCSSUtil;
import com.lgu.ccss.common.util.ResultCodeUtil;
import com.lgu.ccss.common.validation.CheckResultData;
import com.lgu.ccss.common.validation.ValidationChecker;

@Service("targetSetService")
public class TargetSetServiceImpl implements TargetSetService {

	private static final Logger logger = LoggerFactory.getLogger(TargetSetServiceImpl.class);
	private static final List<String> mandatoryList = new ArrayList<String>(
			Arrays.asList(RequestJSON.PARAM_SEND2CAR_ID));	
	@Autowired
	Send2CarDao send2CarDao;
	
	@Override
	public ResponseJSON doService(HttpHeaders headers, RequestJSON reqJson) {
		String api = reqJson.getCommon().getApiId();
		String deviceCtn = CCSSUtil.getCtn();

		ResponseJSON response = null;
		
		CheckResultData result = ValidationChecker.checkValidation(null, reqJson, CCSSConst.API_ID_SEND2CAR_TARGET_SET, mandatoryList);
		if (result.isStatus() == false) {
			response = ResultCodeUtil.createResultMsg(ResultCodeUtil.RC_3C004000, api, result.getReason());
			return response;
		}

		try {
			response = setTarget(reqJson);

		} catch (Exception e) {
			logger.error("deviceCtn({}) Exception({})", deviceCtn, e);
			response = ResultCodeUtil.createResultMsg(ResultCodeUtil.RC_4C005000, api);
		}

		return response;
	}
	
	@SuppressWarnings("unchecked")
	ResponseJSON setTarget(RequestJSON reqJson) {
		String api = reqJson.getCommon().getApiId();
		String deviceCtn = CCSSUtil.getCtn();
		String membId = CCSSUtil.getMembId();
				
		List<LinkedHashMap<String, String>> targetList = null;
		try {
			targetList = (List<LinkedHashMap<String, String>>) reqJson.getParam().get(RequestJSON.PARAM_TARGET_LIST);
				
		} catch (Exception e) {
			logger.error("deviceCtn({}) Exception({})", deviceCtn, e);
			return ResultCodeUtil.createResultMsg(ResultCodeUtil.RC_3C004000, api, "param.targetList");
		}
		
		for (LinkedHashMap<String, String> data : targetList) {
			Send2CarVO send2CarVO = new Send2CarVO();
			
			send2CarVO.setMembId(membId);
			send2CarVO.setSendToCarId((String) data.get("send2CarId"));
			send2CarVO.setSendStatus(Send2CarVO.SEND_STATUS_FINAL);
			
			send2CarDao.updateTargetStatus(send2CarVO);
		}
		
		return ResultCodeUtil.createResultMsg(ResultCodeUtil.RC_2S000000, api);
	}	
}
