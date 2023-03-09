package com.lgu.ccss.mgr.gcalendar.service.login;


import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lgu.ccss.common.gcalendar.GoogleCalendarService;
import com.lgu.ccss.common.model.ResponseJSON;
import com.lgu.ccss.common.tlo.TloData;
import com.lgu.ccss.common.tlo.TloUtil;
import com.lgu.ccss.common.util.CCSSUtil;
import com.lgu.ccss.common.util.ResultCodeUtil;
import com.lgu.ccss.common.validation.CheckResultData;
import com.lgu.common.model.ResultCode;

@Service("googleCalendarLoginService")
public class GoogleCalendarLoginServiceImpl implements GoogleCalendarLoginService{

	private static final Logger logger = LoggerFactory.getLogger(GoogleCalendarLoginServiceImpl.class);
	@Value("#{config['gcalendar.login.callbackurl']}")
	private String callbackurl;
	
	@Autowired
	private GoogleCalendarService googleCalendarService;
	
	@Override
	@Transactional
	public ResponseJSON doService(HttpServletRequest request, HttpHeaders headers) {
		ResultCode resultCode=ResultCodeUtil.RC_2S000000;
		String sessionId = CCSSUtil.getMgrSessionId();
		String membId = request.getParameter(GoogleCalendarService.PARAM_MEMBID);
		String mgrappId = CCSSUtil.getMgrappId();
		String mgrappLoginId = CCSSUtil.getMgrUserLoginId();
		
		TloUtil.setTloData(TloData.MEMB_ID, membId);
		
		//LoginCallBackJSON loginCallBackJSON = null;
		
		//CheckResultData result = ValidationChecker.checkValidation(headers,reqJson,CCSSConst.MANAGER_INFOTAINMENT_LOGIN_CALL_BACK,mandatoryList);
		CheckResultData result = new CheckResultData();
		result.setStatus(true);
		
		ResponseJSON response = null;
				
		Object redirectURL = null;
		if (result.isStatus() == false) {
			
			resultCode = ResultCodeUtil.RC_3C004000;
			response = ResultCodeUtil.createResultMsg(resultCode);
			return response;
		}else{
			
			try {
				
				if( membId == null )
				{
					logger.error("invalid parameter membId({}) mgrappId({}) sessionId({}) mgrappLoginId({}) Exception({})", membId, mgrappId,sessionId,mgrappLoginId);
					resultCode = ResultCodeUtil.RC_3C004000;
					response = ResultCodeUtil.createResultMsg(resultCode);
					return response;
				}
				
				HashMap<String, String> returnParamMap = new HashMap<String, String>();
				returnParamMap.put(GoogleCalendarService.PARAM_MSGAPPID, mgrappId);
				returnParamMap.put(GoogleCalendarService.PARAM_MEMBID, membId);
				returnParamMap.put(GoogleCalendarService.PARAM_SESSIONID, sessionId);
				
				String authorizationUrlString = googleCalendarService.getCalendarServiceForLogin(returnParamMap, callbackurl);
				logger.debug("login authorizationUrlString:" + authorizationUrlString);
				redirectURL = authorizationUrlString;
			} catch (Exception e) {
				resultCode = ResultCodeUtil.RC_4C005000;
				logger.error("mgrappId({}) sessionId({}) mgrappLoginId({}) Exception({})", mgrappId,sessionId,mgrappLoginId,e);
				
			}
		}
		response = ResultCodeUtil.createResultMsg(resultCode,redirectURL, null);
		return response;
	}
}
