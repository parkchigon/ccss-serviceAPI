package com.lgu.ccss.mgr.music.service.loginout;

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

import com.lgu.ccss.common.constant.CCSSConst;
import com.lgu.ccss.common.dao.DeviceDao;
import com.lgu.ccss.common.dao.MemberDao;
import com.lgu.ccss.common.dao.mqtt.MqttMessageDao;
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
import com.lgu.ccss.mgr.infotainment.service.logout.MgrInfotainmentLogoutServiceImpl;
import com.lgu.common.ai.auth.AuthConst;
import com.lgu.common.model.ResultCode;
import com.lgu.common.util.ExceptionUtil;

@Service("loginout")
public class LoginoutServiceImpl implements LoginoutService{
	private static final Logger logger = LoggerFactory.getLogger(MgrInfotainmentLogoutServiceImpl.class);
	private static final List<String> mandatoryList = new ArrayList<String>(
			Arrays.asList(RequestJSON.PARAM_MEMB_ID,RequestJSON.PARAM_SERVICE_CODE,RequestJSON.PARAM_LOGINOUT));
	
	@SuppressWarnings("serial")
	private static final HashMap<String,String> mqttLogoutCodeMap= new HashMap<String,String>(){{
		put(AuthConst.SERVICE_CODE_GENIE, MqttMessageVO.MUSIC_LOGIN_LOGOUT_PUSH_CODE);
		put(AuthConst.SERVICE_CODE_PODCAST, MqttMessageVO.PODCAST_LOGIN_LOGOUT_PUSH_CODE);
		put(AuthConst.SERVICE_CODE_IOT, MqttMessageVO.IOT_LOGIN_LOGOUT_PUSH_CODE);
		put(AuthConst.SERVICE_CODE_GOOGLE, MqttMessageVO.GOOGLE_LOGIN_LOGOUT_PUSH_CODE);
		put(AuthConst.SERVICE_CODE_NAVER, MqttMessageVO.NAVERMUSIC_LOGIN_LOGOUT_PUSH_CODE);
	}};
	
	@Value("#{config['mqtt.infortain.login.content.title']}")
	private String infotainLoginTitle;
	
	@Value("#{config['mqtt.infortain.login.content.msg']}")
	private String infotainLoginMsg;
	
	@Value("#{config['mqtt.infortain.logout.content.title']}")
	private String infotainLogoutTitle;
	
	@Value("#{config['mqtt.infortain.logout.content.msg']}")
	private String infotainLogoutMsg;
	
	@Autowired
	private DeviceDao deviceDao;
	
	@Autowired
	private MqttMessageDao mqttMessageDao;
	
	@Autowired
	private MemberDao memberDao;
	
	@Override
	public ResponseJSON doService(HttpHeaders headers, RequestJSON reqJson) {
		ResultCode resultCode=ResultCodeUtil.RC_2S000000;
		String api = reqJson.getCommon().getApiId();
		String mgrappLoginId = CCSSUtil.getMgrUserLoginId();
		String sessionId = CCSSUtil.getMgrSessionId();
		String mgrappId = CCSSUtil.getMgrappId();
		
		ResponseJSON response = null;
		
		CheckResultData result = ValidationChecker.checkValidation(headers,reqJson,CCSSConst.MANAGER_MUSIC_LOGINOUT,mandatoryList);
		
		if (result.isStatus() == false) {
			
			resultCode = ResultCodeUtil.RC_3C004000;
			response = ResultCodeUtil.createResultMsg(resultCode,api,result.getReason());
			return response;
		
		}else{
			
			String membId = (String) reqJson.getParam().get(RequestJSON.PARAM_MEMB_ID);
			setTloData(membId);
			
			try {
			
				String serviceCode = (String) reqJson.getParam().get(RequestJSON.PARAM_SERVICE_CODE);
				String loginout = (String) reqJson.getParam().get(RequestJSON.PARAM_LOGINOUT);
				
				String code;
				String msgTitle; //serviceCode + infotainLogoutTitle;
				String contentMsg; //serviceCode + infotainLogoutMsg;
				
				if(serviceCode.equals(AuthConst.SERVICE_CODE_GENIE)){
					
					code = mqttLogoutCodeMap.get(AuthConst.SERVICE_CODE_GENIE);
					
				}else if(serviceCode.equals(AuthConst.SERVICE_CODE_PODCAST)){
					
					code = mqttLogoutCodeMap.get(AuthConst.SERVICE_CODE_PODCAST);
					
				}else if(serviceCode.equals(AuthConst.SERVICE_CODE_IOT)){
					
					code = mqttLogoutCodeMap.get(AuthConst.SERVICE_CODE_IOT);
					
				}else if(serviceCode.equals(AuthConst.SERVICE_CODE_GOOGLE)){
					
					code = mqttLogoutCodeMap.get(AuthConst.SERVICE_CODE_GOOGLE);
				}else if(serviceCode.equals(AuthConst.SERVICE_CODE_NAVER)) {
					code = mqttLogoutCodeMap.get(AuthConst.SERVICE_CODE_NAVER);
				}else{
					code="";
				
				}
				
				if(loginout.equals("login")) {
					msgTitle = serviceCode + infotainLoginTitle;
					contentMsg = serviceCode + infotainLoginMsg;
				}else {
					msgTitle =serviceCode + infotainLogoutTitle;
					contentMsg = serviceCode + infotainLogoutMsg;
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
				response = ResultCodeUtil.createResultMsg(resultCode, api);
				
			} catch (Exception e) {
				resultCode = ResultCodeUtil.RC_4C005000;
				logger.error("mgrappId({}) sessionId({}) membId({}) mgrappLoginId({}) Exception({})", mgrappId,sessionId,membId,mgrappLoginId,ExceptionUtil.getPrintStackTrace(e));
				response = ResultCodeUtil.createResultMsg(resultCode, api);
			}
			
		}
		
		return response;
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
