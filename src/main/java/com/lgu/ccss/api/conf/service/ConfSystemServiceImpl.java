package com.lgu.ccss.api.conf.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;

import com.lgu.ccss.api.conf.model.ResultConfSystemJSON;
import com.lgu.ccss.common.constant.CCSSConst;
import com.lgu.ccss.common.model.RequestJSON;
import com.lgu.ccss.common.model.ResponseJSON;
import com.lgu.ccss.common.util.CCSSUtil;
import com.lgu.ccss.common.util.ResultCodeUtil;
import com.lgu.ccss.common.validation.CheckResultData;
import com.lgu.ccss.common.validation.ValidationChecker;
import com.lgu.common.util.ExceptionUtil;

@Service("confSystemService")
public class ConfSystemServiceImpl implements ConfSystemService{
	private static final Logger logger = LoggerFactory.getLogger(ConfSystemServiceImpl.class);
	
	@Value("#{config['ai.auth.url']}")
	private String aiAuthUrl;
	
	@Value("#{config['log.server.url']}")
	private String logServerUrl;
	
	@Value("#{config['log.maxSize']}")
	private String logMaxSize;
	
	@Override
	public ResponseJSON doService(HttpHeaders headers, RequestJSON reqJson) {
		String api = reqJson.getCommon().getApiId();
		
		ResponseJSON response = null;
		
		CheckResultData result = ValidationChecker.checkValidation(null,reqJson,CCSSConst.API_ID_CONF_SYSTEM,null);
		if (result.isStatus() == false) {
			response = ResultCodeUtil.createResultMsg(ResultCodeUtil.RC_3C004000, api, result.getReason());
			return response;
		}
		
		try {
			response = getSystemConfiguration(headers, reqJson);
			
		} catch (Exception e) {
			logger.error("deviceCtn({}) Exception({})", CCSSUtil.getCtn(), ExceptionUtil.getPrintStackTrace(e));
			response = ResultCodeUtil.createResultMsg(ResultCodeUtil.RC_4C005000, api);
		}
		
		return response;
	}
	
	private ResponseJSON getSystemConfiguration(HttpHeaders headers, RequestJSON reqJson) {
		String api = reqJson.getCommon().getApiId();

		ResultConfSystemJSON resultData = new ResultConfSystemJSON();
		resultData.getAi().setServer(aiAuthUrl);
		resultData.getLog().setServer(logServerUrl);
		resultData.getLog().setPushLogSize(logMaxSize);

		return ResultCodeUtil.createResultMsg(ResultCodeUtil.RC_2S000000, resultData, api);
	}
}
