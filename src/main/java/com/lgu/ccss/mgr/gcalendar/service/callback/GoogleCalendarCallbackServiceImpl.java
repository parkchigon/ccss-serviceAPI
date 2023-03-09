package com.lgu.ccss.mgr.gcalendar.service.callback;


import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.extensions.java6.auth.oauth2.AuthorizationCodeInstalledApp;
import com.google.api.services.calendar.Calendar;
import com.google.api.services.calendar.model.CalendarList;
import com.google.api.services.calendar.model.CalendarListEntry;
import com.lgu.ccss.common.constant.CCSSConst;
import com.lgu.ccss.common.dao.InfotainAuthInfoDao;
import com.lgu.ccss.common.dao.MemberDao;
import com.lgu.ccss.common.dao.mgr.MgrAppSessionDao;
import com.lgu.ccss.common.dao.mqtt.MqttMessageDao;
import com.lgu.ccss.common.gcalendar.GoogleCalendarService;
import com.lgu.ccss.common.gcalendar.GoogleCalendarUtil;
import com.lgu.ccss.common.model.MembVO;
import com.lgu.ccss.common.model.ResponseJSON;
import com.lgu.ccss.common.model.mgr.MgrAppSessVO;
import com.lgu.ccss.common.model.mqtt.MqttMessageContentVO;
import com.lgu.ccss.common.model.mqtt.MqttMessageVO;
import com.lgu.ccss.common.tlo.TloData;
import com.lgu.ccss.common.tlo.TloUtil;
import com.lgu.ccss.common.util.ResultCodeUtil;
import com.lgu.ccss.common.validation.CheckResultData;
import com.lgu.common.ai.auth.AuthConst;
import com.lgu.common.model.ResultCode;

@Service("googleCalendarCallbackService")
public class GoogleCalendarCallbackServiceImpl implements GoogleCalendarCallbackService{

	private static final Logger logger = LoggerFactory.getLogger(GoogleCalendarCallbackServiceImpl.class);
	@Autowired
	private InfotainAuthInfoDao infotainAuthInfoDao;
	
	@Value("#{config['gcalendar.login.callbackurl']}")
	private String callbackurl;
	
	@Autowired
	private GoogleCalendarService googleCalendarService;
	
	@Value("#{config['mqtt.infortain.login.content.title']}")
	private String infotainLoginTitle;
	
	@Value("#{config['mqtt.infortain.login.content.msg']}")
	private String infotainLoginMsg;
	
	@Autowired
	private MqttMessageDao mqttMessageDao;
	
	@Autowired
	private MemberDao memberDao;
	
	@Autowired
	private MgrAppSessionDao mgrAppSessionDao;
	
	@Override
	@Transactional
	public ResponseJSON doService(HttpServletRequest request, HttpHeaders headers) {
		ResultCode resultCode=ResultCodeUtil.RC_2S000000;
		String api = CCSSConst.MANAGER_GCALENDAR_LOGIN_CALLBACK;
		String mgrappId = null;
		String membId = null;
		String sessionId = null;
		//setTloData(membId);
		
		ResponseJSON response = null;
		
		//CheckResultData result = ValidationChecker.checkValidation(headers,reqJson,CCSSConst.MANAGER_INFOTAINMENT_LOGIN_CALL_BACK,mandatoryList);
		CheckResultData result = new CheckResultData();
		result.setStatus(true);
		
		if (result.isStatus() == false) {
			
			resultCode = ResultCodeUtil.RC_3C004000;
			response = ResultCodeUtil.createResultMsg(resultCode,api,result.getReason());
			return response;
		
		}else{
			
			try {
				
				String code = request.getParameter(GoogleCalendarService.CALLBACK_PARAM_CODE);
				String state = request.getParameter(GoogleCalendarService.CALLBACK_PARAM_STATE);
				String error = request.getParameter(GoogleCalendarService.CALLBACK_PARAM_ERROR);
				
				if( error != null )
				{
					logger.error("Callback error:" + error);
					resultCode = ResultCodeUtil.RC_5G000002;
					response = ResultCodeUtil.createResultMsg(resultCode,api,result.getReason());
					return response;
				}
				
				if( state == null )
				{
					logger.error("Callback Param state is Null");
					resultCode = ResultCodeUtil.RC_5G000001;
					response = ResultCodeUtil.createResultMsg(resultCode,api,result.getReason());
					return response;
				}
				
				state = java.net.URLDecoder.decode(state, "UTF-8");
				
				HashMap<String, String> returnParamMap = AuthorizationCodeInstalledApp.stringToHashmap(state);
				mgrappId = (String)returnParamMap.get(GoogleCalendarService.PARAM_MSGAPPID);
				membId = (String)returnParamMap.get(GoogleCalendarService.PARAM_MEMBID);
				sessionId = (String)returnParamMap.get(GoogleCalendarService.PARAM_SESSIONID);
				
				if( membId == null )
				{
					logger.error("google auth fail. membId NULL state invalid:" + state + " membId({}) mgrappId({}) sessionId({})", membId, mgrappId, sessionId);
					resultCode = ResultCodeUtil.RC_5G000002;
					response = ResultCodeUtil.createResultMsg(resultCode,api,result.getReason());
					return response;
				}
				
				TloUtil.setTloData(TloData.MEMB_ID, membId);
				
				if( sessionId != null )
				{
					TloUtil.setTloData(TloData.SESSION_ID, sessionId);
					
					MgrAppSessVO mgrAppSessVo = mgrAppSessionDao.selectMgrSess(sessionId);
					if( mgrAppSessVo == null )
					{
						logger.error("google auth fail. Invalid sessionId stat:" + state + " membId({}) mgrappId({}) sessionId({})", membId, mgrappId, sessionId);
						resultCode = ResultCodeUtil.RC_3C003007;
						response = ResultCodeUtil.createResultMsg(resultCode,api,result.getReason());
						return response;
					}
					
					TloUtil.setTloData(TloData.SID, mgrAppSessVo.getMgrappLoginId());
				} else
				{
					logger.error("google auth fail. sessionId NULL state:" + state + " membId({}) mgrappId({}) sessionId({})", membId, mgrappId, sessionId);
					resultCode = ResultCodeUtil.RC_3C003007;
					response = ResultCodeUtil.createResultMsg(resultCode,api,result.getReason());
					return response;
				}
								
				Credential credential = googleCalendarService.requestForToken(membId, code, callbackurl);
				if( credential == null || credential.getAccessToken() == null )
				{
					logger.error("credential is null, " + request.getRequestURI());
					resultCode = ResultCodeUtil.RC_5G000003;
					response = ResultCodeUtil.createResultMsg(resultCode,api,result.getReason());
					return response;
				}
				
				Calendar service = googleCalendarService.getCalendarService(credential);	
				if( service == null )
				{
					logger.error("Google Calendar interworking fail!");
					resultCode = ResultCodeUtil.RC_5G000001;
					response = ResultCodeUtil.createResultMsg(resultCode,api,result.getReason());
					return response;
				}
				
				CalendarList calendarList = service.calendarList().list().setPageToken(null).execute();
				
				logger.debug("calendarList:\r\n" + calendarList);
				
				//get primary id
				String primaryId = null;
				List<CalendarListEntry> items = calendarList.getItems();
				
				Iterator<CalendarListEntry> iter = items.iterator();
				while( iter.hasNext() )
				{
					CalendarListEntry entry = (CalendarListEntry)iter.next();
					
					if( entry.isPrimary() )
					{
						primaryId = entry.getId();
						logger.info("primaryId:" + primaryId);
						break;
					}
				}
				// primary id 없을 때 처리
				
				MembVO membVo  = memberDao.selectMemberByID(membId);
				
				if( membVo == null )
				{
					logger.error("Send2Car MEMB NULL. membId({}) mgrappId({})", membId, mgrappId);
					resultCode = ResultCodeUtil.RC_3C002004;
					response = ResultCodeUtil.createResultMsg(resultCode,api,result.getReason());
					return response;
				}
				
				//insert token info
				boolean insertFlag = false;
				insertFlag= insertInfotainAuthInfo(membId, mgrappId, AuthConst.SERVICE_CODE_GOOGLE, 
						CCSSConst.INFOTAINMENT_PARAM_FIELD_PREFIX + 1, primaryId);
				logger.debug("primaryId(authParameter1) " + primaryId + " insert result:" + insertFlag);
				insertFlag= insertInfotainAuthInfo(membId, mgrappId, AuthConst.SERVICE_CODE_GOOGLE, 
						GoogleCalendarService.TOKEN_NAME_ACCESSTOKEN, credential.getAccessToken());
				logger.debug("ACCESSTOKEN " + credential.getAccessToken() + " insert result:" + insertFlag);
				insertFlag= insertInfotainAuthInfo(membId, mgrappId, AuthConst.SERVICE_CODE_GOOGLE, 
						GoogleCalendarService.TOKEN_NAME_REFRESHTOKEN, credential.getRefreshToken());
				logger.debug("REFRESHTOKEN " + credential.getRefreshToken() + " insert result:" + insertFlag);
				insertFlag= insertInfotainAuthInfo(membId, mgrappId, AuthConst.SERVICE_CODE_GOOGLE, 
						GoogleCalendarService.TOKEN_NAME_EXPDATE, 
						GoogleCalendarUtil.convertDateToString(GoogleCalendarUtil.EXP_DATE_FORMAT, credential.getExpirationTimeMilliseconds()));
				logger.debug("EXPDATE " + GoogleCalendarUtil.convertDateToString(GoogleCalendarUtil.EXP_DATE_FORMAT, credential.getExpirationTimeMilliseconds()) + " insert result:" + insertFlag);
			
				String pushCode =  MqttMessageVO.GOOGLE_LOGIN_LOGOUT_PUSH_CODE;
				String msgTitle = AuthConst.SERVICE_CODE_GOOGLE + infotainLoginTitle;
				String contentMsg = AuthConst.SERVICE_CODE_GOOGLE + infotainLoginMsg;
				
				MqttMessageContentVO mqttMessageContentVO = new MqttMessageContentVO();
				mqttMessageContentVO.getContent().put("code", pushCode);
				
				HashMap<String,Object> infoapp = new HashMap<String,Object>();
				infoapp.put("id", AuthConst.SERVICE_CODE_GOOGLE);
				mqttMessageContentVO.getContent().put("infoapp", infoapp);
				
				MqttMessageVO mqttMessageVO = MqttMessageVO.makeMqttMessage( membVo.getMarketType(), membVo.getMembCtn(), MqttMessageVO.SEND_TYPE_EMERGENCY, MqttMessageVO.MASSAGE_TYPE_SINGLE,
						pushCode, msgTitle,contentMsg,null,null ,mqttMessageContentVO);
				
				boolean sendMqttResulFlag = mqttMessageDao.insertTbCarPushQueue(mqttMessageVO);
				
				if(sendMqttResulFlag){
					logger.info("Send Car Push Message Insert Success. membId({}) mgrappId({}) ", membId, mgrappId);
				}else{
					resultCode=ResultCodeUtil.RC_4C005001;
					logger.error("Send Car Push Message Insert. Fail. membId({}) mgrappId({}) mqttMessageVO({})", membId,mgrappId,mqttMessageVO.toString());
				}
				
			} catch (Exception e) {
				resultCode = ResultCodeUtil.RC_4C005000;
				logger.error("mgrappId({}) Exception({})", mgrappId, e);
				
			}
		}
		response = ResultCodeUtil.createResultMsg(resultCode,api);
		return response;
	}
	
	
	private boolean insertInfotainAuthInfo(String membId, String mgrappId, String serviceId, String tokenName, String tokenValue) {
		if (tokenValue == null || tokenValue.length() == 0) {
			return false;
		}
		if (infotainAuthInfoDao.insertInfotainAuthInfo(membId, mgrappId, serviceId, tokenName, tokenValue) == 0) {
			return false;
		}

		return true;
	}
	
}
