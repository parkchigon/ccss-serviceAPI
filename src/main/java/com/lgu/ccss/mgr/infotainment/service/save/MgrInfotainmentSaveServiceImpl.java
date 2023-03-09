package com.lgu.ccss.mgr.infotainment.service.save;

import java.io.IOException;
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

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.lgu.ccss.common.constant.CCSSConst;
import com.lgu.ccss.common.dao.AuthInfoDao;
import com.lgu.ccss.common.dao.DeviceDao;
import com.lgu.ccss.common.dao.InfotainAuthInfoDao;
import com.lgu.ccss.common.dao.MemberDao;
import com.lgu.ccss.common.dao.mqtt.MqttMessageDao;
import com.lgu.ccss.common.model.AIAuthInfoVO;
import com.lgu.ccss.common.model.ConnDeviceVO;
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

@Service("mgrInfotainmentSaveService")
public class MgrInfotainmentSaveServiceImpl implements MgrInfotainmentSaveService{

	private static final Logger logger = LoggerFactory.getLogger(MgrInfotainmentSaveServiceImpl.class);
	private static final List<String> mandatoryList = new ArrayList<String>(
			Arrays.asList(RequestJSON.PARAM_MEMB_ID,RequestJSON.PARAM_SERVICE_CODE));
	
	@SuppressWarnings("serial")
	private static final HashMap<String,String> mqttLoginCodeMap= new HashMap<String,String>(){{
		put(AuthConst.SERVICE_CODE_GENIE, MqttMessageVO.MUSIC_LOGIN_LOGOUT_PUSH_CODE);
		put(AuthConst.SERVICE_CODE_PODCAST, MqttMessageVO.PODCAST_LOGIN_LOGOUT_PUSH_CODE);
		put(AuthConst.SERVICE_CODE_IOT, MqttMessageVO.IOT_LOGIN_LOGOUT_PUSH_CODE);
		put(AuthConst.SERVICE_CODE_GOOGLE, MqttMessageVO.GOOGLE_LOGIN_LOGOUT_PUSH_CODE);
	}};
	
	@Value("#{config['mqtt.infortain.login.content.title']}")
	private String infotainLoginTitle;
	
	@Value("#{config['mqtt.infortain.login.content.msg']}")
	private String infotainLoginMsg;
	
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
	private MemberDao memberDao;
	
	@Override
	@Transactional
	public ResponseJSON doService(HttpHeaders headers, RequestJSON reqJson) {
		ResultCode resultCode=ResultCodeUtil.RC_2S000000;
		String api = reqJson.getCommon().getApiId();
		String mgrappLoginId = CCSSUtil.getMgrUserLoginId();
		String sessionId = CCSSUtil.getMgrSessionId();
		String mgrappId = CCSSUtil.getMgrappId();
		String membId = (String) reqJson.getParam().get(RequestJSON.PARAM_MEMB_ID);
		
		ResponseJSON response = null;
		
		CheckResultData result = ValidationChecker.checkValidation(headers,reqJson,CCSSConst.MANAGER_INFOTAINMENT_SAVE,mandatoryList);
		
		if (result.isStatus() == false) {
			
			resultCode = ResultCodeUtil.RC_3C004000;
			response = ResultCodeUtil.createResultMsg(resultCode,api,result.getReason());
			return response;
		
		}else{
			
			setTloData(membId);
			
			try {
				
				response = saveInfotainment(headers, reqJson);
				
				
				//Mqtt Push Message Insert
				
				if(response.getResultCode().equals(ResultCodeUtil.RC_2S000000.getCode())){
					
					String serviceCode = (String) reqJson.getParam().get(RequestJSON.PARAM_SERVICE_CODE);
					
					String code;
					String msgTitle=serviceCode + infotainLoginTitle;
					String contentMsg=serviceCode + infotainLoginMsg;
					
					if(serviceCode.equals(AuthConst.SERVICE_CODE_GENIE)){
						
						code = mqttLoginCodeMap.get(AuthConst.SERVICE_CODE_GENIE);
						
					}else if(serviceCode.equals(AuthConst.SERVICE_CODE_PODCAST)){
						
						code = mqttLoginCodeMap.get(AuthConst.SERVICE_CODE_PODCAST);
						
					}else if(serviceCode.equals(AuthConst.SERVICE_CODE_IOT)){
						
						code = mqttLoginCodeMap.get(AuthConst.SERVICE_CODE_IOT);
						
					}else if(serviceCode.equals(AuthConst.SERVICE_CODE_GOOGLE)){
						
						code = mqttLoginCodeMap.get(AuthConst.SERVICE_CODE_GOOGLE);
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
								code,msgTitle,contentMsg,null,null ,mqttMessageContentVO);
						
						boolean sendMqttResulFlag = mqttMessageDao.insertTbCarPushQueue(mqttMessageVO);
						
						if(sendMqttResulFlag){
							
							logger.info("Send Car Push Message Insert Success. sessionId({}) membId({}) mgrappId({})  mgrappLoginId({})",sessionId,membId,mgrappId,mgrappLoginId);
					
						}else{
							resultCode=ResultCodeUtil.RC_4C005001;
							logger.error("Send Car Push Message Insert. Fail. sessionId({}) membId({}) mgrappId({})  mgrappLoginId({}) mqttMessageVO({})",sessionId,membId,mgrappId,mgrappLoginId,mqttMessageVO.toString());
						}
					}else{
						logger.error("Not Invalid AI ServiceCode  sessionId({}) membId({}) mgrappId({})  mgrappLoginId({}) , serviceCode({})",sessionId,membId,mgrappId,mgrappLoginId,serviceCode);
					}

				}
			} catch (Exception e) {
				resultCode = ResultCodeUtil.RC_4C005000;
				logger.error("mgrappId({}) sessionId({}) membId({}) mgrappLoginId({}) Exception({})", mgrappId,sessionId,membId,mgrappLoginId,e);
				response = ResultCodeUtil.createResultMsg(resultCode, api);
			}
			
		}
		
		return response;
	}
	
	
	private ResponseJSON saveInfotainment(HttpHeaders headers, RequestJSON reqJson) throws JsonGenerationException, JsonMappingException, IOException {
		ResultCode resultCode=ResultCodeUtil.RC_2S000000;
		
		String api = reqJson.getCommon().getApiId();
		String mgrappLoginId = CCSSUtil.getMgrUserLoginId();
		String sessionId = CCSSUtil.getMgrSessionId();
		String membId = (String) reqJson.getParam().get(RequestJSON.PARAM_MEMB_ID);
		String mgrappId = CCSSUtil.getMgrappId();
		
		AIAuthInfoVO aiAuthInfo = authInfoDao.selectAIAuthInfo(membId);
		if (aiAuthInfo == null) {
			
			resultCode = ResultCodeUtil.RC_4C005001;
			logger.error("failed to select AI AuthInfo data. mgrappId({}) sessionId({}) mgrappLoginId({}) membId({})", mgrappId,sessionId,mgrappLoginId,membId);
		
		}else{
			
			Map<String, String> authParameter = new LinkedHashMap<String, String>();
			
			String serviceCode = (String) reqJson.getParam().get(RequestJSON.PARAM_SERVICE_CODE);
			
			for (String key : reqJson.getParam().keySet()) {
				
				
				//String value = (String) reqJson.getParam().get(key);
				if (key.startsWith("authParameter")) {
					String value = (String) reqJson.getParam().get(key);
					if(value != null){
						authParameter.put(key, value);
					}
				}/*else if(key.startsWith(RequestJSON.PARAM_ONE_ID_KYE) && serviceCode.equals(AuthConst.SERVICE_CODE_IOT)){
					
					String oneIdKey = (String) reqJson.getParam().get(RequestJSON.PARAM_ONE_ID_KYE);
					authParameter.put(RequestJSON.PARAM_ONE_ID_KYE, oneIdKey);
				}*/else{
					logger.error("Ignore Key name is Invalied . mgrappId({}) sessionId({}) mgrappLoginId({}) key({}) ", mgrappId,sessionId,mgrappLoginId,key);
				}
				
			}

			
			//List<HomeCodeInfoJSON> homeCodeInfoList = ()
			Map<String, String> authInfo = new LinkedHashMap<String, String>();
			authInfo.put(AuthRequestBodyJSON.AUTH_INFO_CONT_SVC_CD, serviceCode);
			authInfo.putAll(authParameter);
			
			//Device Info 조회
			ConnDeviceVO connDeviceVO = new ConnDeviceVO();
			connDeviceVO.setMembId(membId);
			connDeviceVO = deviceDao.getDeviceInfo(connDeviceVO);
			
			if(connDeviceVO == null) {
				
				resultCode = ResultCodeUtil.RC_6C000003;
				logger.error("failed to Get DeviceInfo. mgrappId({}) sessionId({}) mgrappLoginId({}) ", mgrappId,sessionId,mgrappLoginId);
				
			}else{
				
				String deviceCtn= connDeviceVO.getDeviceCtn();
				AuthResponseJSON response = authAgent.saveSvcAuthInfo(deviceCtn, aiAuthInfo.getDeviceToken(),
						reqJson.getCommon().getAuthRequestCommon(), authInfo);
				
				if (response == null) {
					
					resultCode = ResultCodeUtil.RC_5A000002;
					logger.error("failed to save Infotainment data. mgrappId({}) sessionId({}) mgrappLoginId({}) deviceCtn({})",mgrappId,sessionId,mgrappLoginId, deviceCtn);
				
				}else{
					
					for (String key : authParameter.keySet()) {
						
						if (insertInfotainAuthInfo(membId, mgrappId,serviceCode, key, authParameter.get(key)) == false) {
							logger.error("failed to insert InfotainAuthInfo data. mgrappId({}) sessionId({}) mgrappLoginId({}) deviceCtn({}) parameter({}:{})",mgrappId,sessionId,mgrappLoginId, deviceCtn, key,
									authParameter.get(key));
							return ResultCodeUtil.createResultMsg(ResultCodeUtil.RC_4C005001, api);
						}
					}
				}
			}			
		}
		return ResultCodeUtil.createResultMsg(resultCode, api);
	}

	private boolean insertInfotainAuthInfo(String membId, String mgrappId, String serviceId, String tokenName, String tokenValue) {
		if (tokenValue == null || tokenValue.length() == 0) {
			return true;
		}
		if (infotainAuthInfoDao.insertInfotainAuthInfo(membId,mgrappId,serviceId, tokenName, tokenValue) == 0) {
			return false;
		}

		return true;
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
