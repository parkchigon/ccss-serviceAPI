package com.lgu.ccss.mgr.cekInfotainment.service.getAuthorizationCode;

import java.io.IOException;
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
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.lgu.ccss.common.constant.CCSSConst;
import com.lgu.ccss.common.model.RequestJSON;
import com.lgu.ccss.common.model.ResponseJSON;
import com.lgu.ccss.common.tlo.TloData;
import com.lgu.ccss.common.tlo.TloUtil;
import com.lgu.ccss.common.util.CCSSUtil;
import com.lgu.ccss.common.util.ResultCodeUtil;
import com.lgu.ccss.common.validation.CheckResultData;
import com.lgu.ccss.common.validation.ValidationChecker;
import com.lgu.ccss.mgr.cekInfotainment.model.getAuthorizationCode.ResultGetAuthorizationCodeJSON;
import com.lgu.common.cekAi.auth.CekAuthAgent;
import com.lgu.common.cekAi.auth.model.CekAuthResponseJSON;
import com.lgu.common.model.ResultCode;
import com.lgu.common.util.ExceptionUtil;

@Service("cekInfotainmentGetAuthorizationCodeService")
public class CekInfotainmentGetAuthorizationCodeServiceImpl implements CekInfotainmentGetAuthorizationCodeService{

	private static final Logger logger = LoggerFactory.getLogger(CekInfotainmentGetAuthorizationCodeServiceImpl.class);
	private static final List<String> mandatoryList = new ArrayList<String>(
			Arrays.asList(RequestJSON.PARAM_MEMB_ID,RequestJSON.PARAM_SERVICE_CODE,
					RequestJSON.PARAM_NAVER_LOGIN_ID,  RequestJSON.PARAM_CUSTOM_ID));

	@Autowired
	private CekAuthAgent cekAuthAgent;
	
	
	@Value("#{config['cek.infotainment.search.yn']}")
	private String cekInfotainmentSearchYn;
	
	@Override
	@Transactional
	public ResponseJSON doService(HttpHeaders headers, RequestJSON reqJson) {
		ResultCode resultCode=ResultCodeUtil.RC_2S000000;
		String api = reqJson.getCommon().getApiId();
		String mgrappLoginId = CCSSUtil.getMgrUserLoginId();
		String sessionId = CCSSUtil.getMgrSessionId();
		String mgrappId = CCSSUtil.getMgrappId();
		
		ResponseJSON response = null;
		
		CheckResultData result = ValidationChecker.checkValidation(headers,reqJson,CCSSConst.MANAGER_CEK_INFOTAINMENT_GET_AUTHORIZATION_CODE,mandatoryList);
		
		if (result.isStatus() == false) {
			resultCode = ResultCodeUtil.RC_3C004000;
			response = ResultCodeUtil.createResultMsg(resultCode,api,result.getReason());
			return response;
		}else{
			
			String membId = (String) reqJson.getParam().get(RequestJSON.PARAM_MEMB_ID);
			setTloData(membId);
			
			try {
				
				response = getAuthorizationCode(headers, reqJson);
				
			} catch (Exception e) {
				resultCode = ResultCodeUtil.RC_4C005000;
				logger.error("mgrappId({}) sessionId({}) membId({}) mgrappLoginId({}) Exception({})", mgrappId,sessionId,membId,mgrappLoginId,ExceptionUtil.getPrintStackTrace(e));
				response = ResultCodeUtil.createResultMsg(resultCode, api);
			}
			
		}
		return response;
	}
	
	private ResponseJSON getAuthorizationCode(HttpHeaders headers, RequestJSON reqJson) throws JsonParseException, JsonMappingException, IOException {
		ResultCode resultCode=ResultCodeUtil.RC_2S000000;
		String api = reqJson.getCommon().getApiId();
		String mgrappLoginId = CCSSUtil.getMgrUserLoginId();
		String sessionId = CCSSUtil.getMgrSessionId();
		String mgrappId = CCSSUtil.getMgrappId();
		String membId = (String) reqJson.getParam().get(RequestJSON.PARAM_MEMB_ID);
		String serviceCode = (String) reqJson.getParam().get(RequestJSON.PARAM_SERVICE_CODE);
		String customId =  (String) reqJson.getParam().get(RequestJSON.PARAM_CUSTOM_ID);
		String nid =  (String) reqJson.getParam().get(RequestJSON.PARAM_NAVER_LOGIN_ID);
		
		//GetAuthorization Code Cek 요청
		CekAuthResponseJSON cekAuthResponseJSON =  cekAuthAgent.getAuthorizationCode(reqJson, mgrappLoginId);
		
		ResultGetAuthorizationCodeJSON resultGetAuthorizationCodeJSON = new ResultGetAuthorizationCodeJSON();
		if(cekAuthResponseJSON == null){
			resultCode = ResultCodeUtil.RC_5C200005;
			logger.info("Fail to Get AuthorizationCode. mgrappId({}) sessionId({}) mgrappLoginId({}) membId({}) serviceCode({}) customId({}) nid({})"
					, mgrappId,sessionId,mgrappLoginId,membId,serviceCode,customId,nid);
			return ResultCodeUtil.createResultMsg(resultCode, api);
		}else{
			resultGetAuthorizationCodeJSON.setAuthorizationCd(cekAuthResponseJSON.getBody().getCek().getAuthorizationCd());
			
		}
		return ResultCodeUtil.createResultMsg(ResultCodeUtil.RC_2S000000, resultGetAuthorizationCodeJSON, api);
	}
	
	private void setTloData(String membId) {
		if (membId == null || membId.length() <=0) {
			return;
		}
		Map<String, String> tlo = new HashMap<String, String>();
		tlo.put(TloData.MEMB_ID, membId);
		TloUtil.setTloData(tlo);
	}
}
