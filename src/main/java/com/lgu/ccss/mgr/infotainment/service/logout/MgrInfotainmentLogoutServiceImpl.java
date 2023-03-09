package com.lgu.ccss.mgr.infotainment.service.logout;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lgu.ccss.common.constant.CCSSConst;
import com.lgu.ccss.common.dao.AuthInfoDao;
import com.lgu.ccss.common.dao.DeviceDao;
import com.lgu.ccss.common.dao.InfotainAuthInfoDao;
import com.lgu.ccss.common.dao.MemberDao;
import com.lgu.ccss.common.dao.mqtt.MqttMessageDao;
import com.lgu.ccss.common.gcalendar.GoogleCalendarService;
import com.lgu.ccss.common.model.AIAuthInfoVO;
import com.lgu.ccss.common.model.ConnDeviceVO;
import com.lgu.ccss.common.model.InfotainAuthInfoVO;
import com.lgu.ccss.common.model.MembVO;
import com.lgu.ccss.common.model.RequestJSON;
import com.lgu.ccss.common.model.ResponseJSON;
import com.lgu.ccss.common.model.mqtt.MqttMessageContentVO;
import com.lgu.ccss.common.model.mqtt.MqttMessageVO;
import com.lgu.ccss.common.tlo.TloData;
import com.lgu.ccss.common.tlo.TloUtil;
import com.lgu.ccss.common.util.CCSSUtil;
import com.lgu.ccss.common.util.ResultCodeUtil;
import com.lgu.ccss.common.validation.CheckResultData;
import com.lgu.ccss.common.validation.ValidationChecker;
import com.lgu.common.ai.auth.AuthAgent;
import com.lgu.common.ai.auth.AuthConst;
import com.lgu.common.ai.auth.model.AuthRequestBodyJSON;
import com.lgu.common.ai.auth.model.AuthResponseJSON;
import com.lgu.common.model.ResultCode;
import com.lgu.common.util.ExceptionUtil;

@Service("mgrInfotainmentLogoutService")
public class MgrInfotainmentLogoutServiceImpl implements MgrInfotainmentLogoutService{

	private static final Logger logger = LoggerFactory.getLogger(MgrInfotainmentLogoutServiceImpl.class);
	private static final List<String> mandatoryList = new ArrayList<String>(
			Arrays.asList(RequestJSON.PARAM_MEMB_ID,RequestJSON.PARAM_SERVICE_CODE));
	
	@SuppressWarnings("serial")
	private static final HashMap<String,String> mqttLogoutCodeMap= new HashMap<String,String>(){{
		put(AuthConst.SERVICE_CODE_GENIE, MqttMessageVO.MUSIC_LOGIN_LOGOUT_PUSH_CODE);
		put(AuthConst.SERVICE_CODE_PODCAST, MqttMessageVO.PODCAST_LOGIN_LOGOUT_PUSH_CODE);
		put(AuthConst.SERVICE_CODE_IOT, MqttMessageVO.IOT_LOGIN_LOGOUT_PUSH_CODE);
		put(AuthConst.SERVICE_CODE_GOOGLE, MqttMessageVO.GOOGLE_LOGIN_LOGOUT_PUSH_CODE);
	}};
	
	@Value("#{config['mqtt.infortain.logout.content.title']}")
	private String infotainLogoutTitle;
	
	@Value("#{config['mqtt.infortain.logout.content.msg']}")
	private String infotainLogoutMsg;
	
	@Autowired
	private InfotainAuthInfoDao infotainAuthInfoDao;
	
	@Autowired
	private AuthAgent authAgent;
	
	@Autowired
	private AuthInfoDao authInfoDao;
	
	@Autowired
	private DeviceDao deviceDao;
	
	@Autowired
	private MqttMessageDao mqttMessageDao;
	
	@Autowired
	private GoogleCalendarService googleCalendarService;
	
	@Autowired
	private MemberDao memberDao;
	
	@Override
	@Transactional
	public ResponseJSON doService(HttpHeaders headers, RequestJSON reqJson) {
		ResultCode resultCode=ResultCodeUtil.RC_2S000000;
		String api = reqJson.getCommon().getApiId();
		String mgrappLoginId = CCSSUtil.getMgrUserLoginId();
		String sessionId = CCSSUtil.getMgrSessionId();
		String mgrappId = CCSSUtil.getMgrappId();
		
		ResponseJSON response = null;
		
		CheckResultData result = ValidationChecker.checkValidation(headers,reqJson,CCSSConst.MANAGER_INFOTAINMENT_LOGOUT,mandatoryList);
		
		if (result.isStatus() == false) {
			
			resultCode = ResultCodeUtil.RC_3C004000;
			response = ResultCodeUtil.createResultMsg(resultCode,api,result.getReason());
			return response;
		
		}else{
			
			String membId = (String) reqJson.getParam().get(RequestJSON.PARAM_MEMB_ID);
			setTloData(membId);
			
			try {
			
				String serviceCode = (String) reqJson.getParam().get(RequestJSON.PARAM_SERVICE_CODE);
				
				if( serviceCode.equals(AuthConst.SERVICE_CODE_GOOGLE) )
				{
					response = logoutMgrappInfotainment(headers, reqJson);
				} else
					response = logoutInfotainment(headers, reqJson);
				
				//Mqtt Push Message Insert
				if(response.getResultCode().equals(ResultCodeUtil.RC_2S000000.getCode())){
					
					
					
					String code;
					String msgTitle=serviceCode + infotainLogoutTitle;
					String contentMsg=serviceCode + infotainLogoutMsg;
					
					if(serviceCode.equals(AuthConst.SERVICE_CODE_GENIE)){
						
						code = mqttLogoutCodeMap.get(AuthConst.SERVICE_CODE_GENIE);
						
					}else if(serviceCode.equals(AuthConst.SERVICE_CODE_PODCAST)){
						
						code = mqttLogoutCodeMap.get(AuthConst.SERVICE_CODE_PODCAST);
						
					}else if(serviceCode.equals(AuthConst.SERVICE_CODE_IOT)){
						
						code = mqttLogoutCodeMap.get(AuthConst.SERVICE_CODE_IOT);
						
					}else if(serviceCode.equals(AuthConst.SERVICE_CODE_GOOGLE)){
						
						code = mqttLogoutCodeMap.get(AuthConst.SERVICE_CODE_GOOGLE);
					}else{
						code="";
					
					}
					
					if(code !=null && code.length() > 0){
						ConnDeviceVO connDeviceVO = new ConnDeviceVO();
						connDeviceVO.setMembId(membId);
						connDeviceVO = deviceDao.getDeviceInfo(connDeviceVO);
						
						MembVO membVO  = memberDao.selectMemberByID(membId);
						
						MqttMessageContentVO mqttMessageContentVO = new MqttMessageContentVO();
						mqttMessageContentVO.getContent().put("code", code);
						
						HashMap<String,Object> infoapp = new HashMap<String,Object>();
						infoapp.put("id", serviceCode);
						mqttMessageContentVO.getContent().put("infoapp", infoapp);
						
						
						MqttMessageVO mqttMessageVO = MqttMessageVO.makeMqttMessage(membVO.getMarketType(),connDeviceVO.getDeviceCtn(),MqttMessageVO.SEND_TYPE_EMERGENCY,MqttMessageVO.MASSAGE_TYPE_SINGLE,
								code,msgTitle,contentMsg,null,null,mqttMessageContentVO);
						
						boolean sendMqttResulFlag = mqttMessageDao.insertTbCarPushQueue(mqttMessageVO);
						
						if(sendMqttResulFlag){
							
							logger.info("Send Car Push Message Insert Success. sessionId({}) membId({}) mgrappId({})  mgrappLoginId({})",sessionId,membId,mgrappId,mgrappLoginId);
					
						}else{
							resultCode=ResultCodeUtil.RC_4C005001;
							logger.error("Send Car Push Message Insert. Fail. sessionId({}) membId({}) mgrappId({})  mgrappLoginId({}) mqttMessageVO({})",sessionId,membId,mgrappId,mgrappLoginId,mqttMessageVO.toString());
						}
					}else{
						logger.error("Not Invalie AI ServiceCode  sessionId({}) membId({}) mgrappId({})  mgrappLoginId({}) , serviceCode({})",sessionId,membId,mgrappId,mgrappLoginId,serviceCode);
					}

				}
			} catch (Exception e) {
				resultCode = ResultCodeUtil.RC_4C005000;
				logger.error("mgrappId({}) sessionId({}) membId({}) mgrappLoginId({}) Exception({})", mgrappId,sessionId,membId,mgrappLoginId,ExceptionUtil.getPrintStackTrace(e));
				response = ResultCodeUtil.createResultMsg(resultCode, api);
			}
			
		}
		return response;
	}
	

	private ResponseJSON logoutInfotainment(HttpHeaders headers, RequestJSON reqJson) {
		ResultCode resultCode=ResultCodeUtil.RC_2S000000;
		
		String api = reqJson.getCommon().getApiId();
		String mgrappLoginId = CCSSUtil.getMgrUserLoginId();
		String sessionId = CCSSUtil.getMgrSessionId();
		String membId = (String) reqJson.getParam().get(RequestJSON.PARAM_MEMB_ID);
		String mgrappId = CCSSUtil.getMgrappId();
		
		AIAuthInfoVO aiAuthInfo = authInfoDao.selectAIAuthInfo(membId);
		
		if (aiAuthInfo == null) {
			
			resultCode = ResultCodeUtil.RC_3C003009;
			logger.error("failed to select AI AuthInfo data. mgrappId({}) sessionId({}) mgrappLoginId({}) membId({})", mgrappId,sessionId,mgrappLoginId,membId);
		
		}else{
			
			if (logger.isDebugEnabled()) {
				logger.debug("AI_AUTHINFO({})", aiAuthInfo);
			}

			String serviceCode = (String) reqJson.getParam().get(RequestJSON.PARAM_SERVICE_CODE);

			Map<String, String> authInfo = new LinkedHashMap<String, String>();
			authInfo.put(AuthRequestBodyJSON.AUTH_INFO_CONT_SVC_CD, serviceCode);
			
			
			//Device Info 조회
			ConnDeviceVO connDeviceVO = new ConnDeviceVO();
			connDeviceVO.setMembId(membId);
			connDeviceVO = deviceDao.getDeviceInfo(connDeviceVO);
			
			if(connDeviceVO == null) {
				
				resultCode = ResultCodeUtil.RC_6C000003;
				logger.error("failed to Get DeviceInfo. mgrappId({}) sessionId({}) mgrappLoginId({}) ", mgrappId,sessionId,mgrappLoginId);
			
			}else{
				String deviceCtn= connDeviceVO.getDeviceCtn();
				AuthResponseJSON response = authAgent.logoutSvc(deviceCtn, aiAuthInfo.getDeviceToken(),
						reqJson.getCommon().getAuthRequestCommon(), authInfo);
				
				if (response == null) {
					
					resultCode = ResultCodeUtil.RC_5A000004;
					logger.error("failed to logout Infotainment. mgrappId({}) sessionId({}) mgrappLoginId({})  deviceCtn({})",mgrappId,sessionId,mgrappLoginId, deviceCtn);
				
				}else{
					
					if (infotainAuthInfoDao.deleteInfotainAuthInfo(membId, serviceCode) == 0) {
						//resultCode = ResultCodeUtil.RC_4C005001;
						logger.error("failed to delete InfotainAuthInfo data. mgrappId({}) sessionId({}) mgrappLoginId({})  deviceCtn({}) deviceCtn({}) serviceCode({})", mgrappId,sessionId,mgrappLoginId,deviceCtn,
								serviceCode);
					}
					
				}
			}
		}
		return ResultCodeUtil.createResultMsg(resultCode, api);
	}
	
	private ResponseJSON logoutMgrappInfotainment(HttpHeaders headers, RequestJSON reqJson) {
		ResultCode resultCode=ResultCodeUtil.RC_2S000000;
		
		String api = reqJson.getCommon().getApiId();
		String mgrappLoginId = CCSSUtil.getMgrUserLoginId();
		String sessionId = CCSSUtil.getMgrSessionId();
		String membId = (String) reqJson.getParam().get(RequestJSON.PARAM_MEMB_ID);
		String mgrappId = CCSSUtil.getMgrappId();
		
		String serviceCode = (String) reqJson.getParam().get(RequestJSON.PARAM_SERVICE_CODE);
		InfotainAuthInfoVO infoVo = infotainAuthInfoDao.selectInfotainAuthInfo(membId, serviceCode, GoogleCalendarService.TOKEN_NAME_ACCESSTOKEN);
		
		if( infoVo != null)
		{
			boolean resultFlag = googleCalendarService.requestRevokeAccessToken(infoVo.getTokenValue(), mgrappLoginId);
			logger.info("Google Token revoke result:" + resultFlag);
			
		} else
			logger.info("Google Token is null:" + mgrappId);
		

		if (infotainAuthInfoDao.deleteInfotainAuthInfo(membId, serviceCode) == 0) {
			resultCode = ResultCodeUtil.RC_4C005001;
			logger.error(
					"failed to delete InfotainAuthInfo data. mgrappId({}) sessionId({}) mgrappLoginId({})  deviceCtn({}) deviceCtn({}) serviceCode({})",
					mgrappId, sessionId, mgrappLoginId, mgrappLoginId, serviceCode);
		}
		
		return ResultCodeUtil.createResultMsg(resultCode, api);
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
