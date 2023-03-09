package com.lgu.common.pushgw.service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;


import org.apache.commons.httpclient.HttpStatus;
import org.apache.http.HttpHeaders;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

import com.lgu.common.pushgw.PushConst;
import com.lgu.common.pushgw.model.PushVO;
import com.lgu.common.pushgw.model.ResPushJSON;
import com.lgu.common.pushgw.properties.PushProperties;
import com.lgu.common.rest.RestAgent;
import com.lgu.common.rest.RestRequestData;
import com.lgu.common.rest.RestResultData;
import com.lgu.common.util.ExceptionUtil;

@Component
public class PushServiceAgent {
	
	private static final Gson gson = new Gson();
	
	@Autowired
	private PushProperties pushProperties;
	
	@Autowired
	private RestAgent restAgent;
	
	@Value("#{config['push.proxy.http.proxyHost']}")
	private String pushHttpProxyHost;
	
	@Value("#{config['push.proxy.http.proxySubHost']}")
	private String pushHttpProxySubHost;
	
	@Value("#{config['push.proxy.http.proxyPort']}")
	private int pushHttpProxyPort;
	
	private static final Logger logger = LoggerFactory.getLogger(PushServiceAgent.class);
	

	/*public Object subscriberInfo(PushVO pushVO,String mgrappLoginId) throws Exception {
		
		// set headers 
		String url = PushConst.HTTP_PROTOCOL + pushProperties.getPushDomain()
					+PushConst.COLON + pushProperties.getPushPort1() + pushProperties.getPushSubscriberInfoUrl();
		
		RestRequestData reqData = makeHeaderData(url,pushProperties.getPushSubscriberInfoId());
		
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("REQUEST_PART", pushProperties.getPushResquestPartSp());
		params.put("REQUEST_TIME", new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()));
		params.put("APPLICATION_ID", pushVO.getApplicationId());
		params.put("SERVICEKEY_COUNT", pushVO.getServiceKeyCount());
		
		List<Map<String, String>> keys = new ArrayList<Map<String, String>>();
		for(PushServiceKeyVO serviceKey : pushVO.getServiceKeys()) {
			Map<String, String> tmp = new HashMap<String, String>();
			tmp.put("SERVICE_KEY", serviceKey.getServiceKey());
			keys.add(tmp);
		}
		params.put("SERVICE_KEYS", keys);
		
		
		reqData.setJson(gson.toJson(params));
		reqData.setConnectionTimeout(Integer.parseInt(pushProperties.getConnectionTimeout()));
		reqData.setTimeout(Integer.parseInt(pushProperties.getReadTimeout()));
		
		
		RestResultData resultData = restAgent.request(reqData);
		
		
		if (resultData == null) {
			logger.error("failed to request PushGw Infra Server. mgrappLoginId({})", mgrappLoginId);
			return null;
		}

		if (resultData.getStatusCode() != HttpStatus.SC_OK) {
			logger.error("failed to request PushGw Infra Server. mgrappLoginId({}) statusCode({})", mgrappLoginId, resultData.getStatusCode());
			return null;
		}

		ResPushSubInfoJSON resPushSubInfoJSON = new ResPushSubInfoJSON();
		
		try {
			resPushSubInfoJSON = gson.fromJson(resultData.getJson(), ResPushSubInfoJSON.class);

		} catch (JsonSyntaxException e) {
			logger.error("JSON({}) Exception({})", resultData.getJson(), ExceptionUtil.getPrintStackTrace(e));
			return null;
		}
		
		return resPushSubInfoJSON;
	}*/
	

	public Object subscriberRegisterGcm(PushVO pushVO,String mgrappLoginId, String OemId, String pushServiceId) throws Exception {
		
		//20190211 源�踰붿＜ NS push 遺꾧린異붽�
		String pushSubscriberRegisterGcmAuthKey = ""; 
		
		if (OemId.equals("NS_android")) {
			pushSubscriberRegisterGcmAuthKey = pushProperties.getPushEl1SubscriberRegisterGcmAuthKey();
		} else if(OemId.equals("AM_android")){
			pushSubscriberRegisterGcmAuthKey = pushProperties.getPushSubscriberRegisterGcmAuthKey();
		} else if(OemId.equals("SY_android")) {
			pushSubscriberRegisterGcmAuthKey = pushProperties.getPushSubscriberRegisterGcmBMAuthKey();
		}
		
		// set headers 
		String url = PushConst.HTTP_PROTOCOL  + pushProperties.getPushDomain()
				+	PushConst.COLON +	pushProperties.getPushPort1() + pushProperties.getPushSubscriberRegisterGcmUrl();
		
		RestRequestData reqData = makeHeaderData(url, pushSubscriberRegisterGcmAuthKey, OemId);
				
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("REQUEST_PART", pushProperties.getPushResquestPartApp());
		params.put("REQUEST_TIME", new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()));
		params.put("PUSH_ID", pushVO.getPushId());
		//20181008 諛뺤튂怨� AM/BM GCM 蹂�寃�
		if(OemId.equals("AM_android")){
			params.put("APPLICATION_ID", pushProperties.getPushGcmApplicationId());
			params.put("SERVICE_ID", pushServiceId);			
		}else if(OemId.equals("SY_android")){
			params.put("APPLICATION_ID", pushProperties.getBmPushGcmApplicationId());
			params.put("SERVICE_ID", pushServiceId);
		} else if(OemId.equals("NS_android")) {
			params.put("APPLICATION_ID", pushProperties.getEl1PushGcmApplicationId());
			params.put("SERVICE_ID", pushServiceId);
		}
		params.put("DEVICE_TOKEN", pushVO.getDeviceToken());
		params.put("SERVICE_KEY", pushVO.getServiceKey());
		params.put("DEVICE_ID", pushVO.getDeviceId());
		
		reqData.setJson(gson.toJson(params));
		reqData.setConnectionTimeout(Integer.parseInt(pushProperties.getConnectionTimeout()));
		reqData.setTimeout(Integer.parseInt(pushProperties.getReadTimeout()));
		
		
		RestResultData resultData = restAgent.requestProxy(reqData, pushHttpProxyHost, pushHttpProxySubHost, pushHttpProxyPort);
		
		
		if (resultData == null) {
			logger.error("failed to request PushGw Infra Server. mgrappLoginId({})", mgrappLoginId);
			return null;
		}

		if (resultData.getStatusCode() != HttpStatus.SC_OK) {
			logger.error("failed to request PushGw Infra Server. mgrappLoginId({}) statusCode({})", mgrappLoginId, resultData.getStatusCode());
			return null;
		}

		ResPushJSON resPushJSON = new ResPushJSON();
		
		try {
			resPushJSON = gson.fromJson(resultData.getJson(), ResPushJSON.class);

		} catch (JsonSyntaxException e) {
			logger.error("JSON({}) Exception({})", resultData.getJson(), ExceptionUtil.getPrintStackTrace(e));
			return null;
		}
		
		return resPushJSON;
		
	}

	public Object subscriberRegisterApns(PushVO pushVO,String mgrappLoginId, String OemId, String pushServiceId ) throws Exception {
		
		//20190211 源�踰붿＜ NS push 遺꾧린異붽�
		String pushSubscriberRegisterApnsAuthKey = ""; 
		
		if (OemId.equals("NS_ios")) {
			pushSubscriberRegisterApnsAuthKey = pushProperties.getPushEl1SubscriberRegisterApnsAuthKey();
		} else if(OemId.equals("AM_ios")){
			pushSubscriberRegisterApnsAuthKey = pushProperties.getPushSubscriberRegisterApnsAuthKey();
		} else if(OemId.equals("SY_ios")) {
			pushSubscriberRegisterApnsAuthKey = pushProperties.getPushSubscriberRegisterApnsBMAuthKey();
		}
		
		// set headers 
		String url = PushConst.HTTP_PROTOCOL  + pushProperties.getPushDomain()
				+	PushConst.COLON	+	pushProperties.getPushPort1() + pushProperties.getPushSubscriberRegisterApnsUrl();
		
		RestRequestData reqData = makeHeaderData(url, pushSubscriberRegisterApnsAuthKey, OemId);

		Map<String, Object> params = new HashMap<String, Object>();
		params.put("REQUEST_PART", pushProperties.getPushResquestPartApp());
		params.put("REQUEST_TIME", new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()));
		params.put("PUSH_ID", pushVO.getPushId());
		//20181008 諛뺤튂怨� AM/BM GCM 蹂�寃�
		if(OemId.equals("AM_ios")){
			params.put("APPLICATION_ID", pushProperties.getPushApnsApplicationId());
			params.put("SERVICE_ID", pushServiceId);			
		}else if(OemId.equals("SY_ios")){
			params.put("APPLICATION_ID", pushProperties.getBmPushApnsApplicationId());
			params.put("SERVICE_ID", pushServiceId);
		} else if(OemId.equals("NS_ios")) {
			params.put("APPLICATION_ID", pushProperties.getEl1PushApnsApplicationId());
			params.put("SERVICE_ID", pushServiceId);
		}
		params.put("DEVICE_TOKEN", pushVO.getDeviceToken());
		params.put("SERVICE_KEY", pushVO.getServiceKey());
		params.put("DEVICE_ID", pushVO.getDeviceId());
		

		reqData.setJson(gson.toJson(params));
		reqData.setConnectionTimeout(Integer.parseInt(pushProperties.getConnectionTimeout()));
		reqData.setTimeout(Integer.parseInt(pushProperties.getReadTimeout()));
		
		
		RestResultData resultData = restAgent.requestProxy(reqData, pushHttpProxyHost, pushHttpProxySubHost, pushHttpProxyPort);
		
		
		if (resultData == null) {
			logger.error("failed to request PushGw Infra Server. mgrappLoginId({})", mgrappLoginId);
			return null;
		}

		if (resultData.getStatusCode() != HttpStatus.SC_OK) {
			logger.error("failed to request PushGw Infra Server. mgrappLoginId({}) statusCode({})", mgrappLoginId, resultData.getStatusCode());
			return null;
		}

		ResPushJSON resPushJSON = new ResPushJSON();
		
		try {
			resPushJSON = gson.fromJson(resultData.getJson(), ResPushJSON.class);

		} catch (JsonSyntaxException e) {
			logger.error("JSON({}) Exception({})", resultData.getJson(), ExceptionUtil.getPrintStackTrace(e));
			return null;
		}
		
		return resPushJSON;
	}

	public Object subscriberDeregister(PushVO pushVO, String mgrappLoginId, String OemId, String pushServiceId) throws Exception {
		
		//20190211 源�踰붿＜ NS push 遺꾧린異붽�
		String pushSubscriberDeregisterAuthKey = ""; 
		
		if (OemId.equals("NS_android") || OemId.equals("NS_ios")) {
			pushSubscriberDeregisterAuthKey = pushProperties.getPushEl1SubscriberDeregisterAuthKey();
		} else if(OemId.equals("AM_android") || OemId.equals("AM_ios")){
			pushSubscriberDeregisterAuthKey = pushProperties.getPushSubscriberDeregisterAuthKey();
		} else if(OemId.equals("SY_android") || OemId.equals("SY_ios")) {
			pushSubscriberDeregisterAuthKey = pushProperties.getPushSubscriberDeregisterBMAuthKey();
		}
		
		// set headers 
		String url = PushConst.HTTP_PROTOCOL + pushProperties.getPushDomain()
				+	PushConst.COLON+pushProperties.getPushPort1() + pushProperties.getPushSubscriberDeregisterUrl();
		
		RestRequestData reqData = makeHeaderData(url, pushSubscriberDeregisterAuthKey, OemId);
		

		Map<String, Object> params = new HashMap<String, Object>();
		params.put("REQUEST_PART", pushProperties.getPushResquestPartSp());
		params.put("REQUEST_TIME", new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()));
		params.put("PUSH_ID", pushVO.getPushId());
		
		if(PushConst.GCM.equals(pushVO.getAppPushType().toUpperCase())) {
			if(OemId.equals("AM_android")){
				params.put("APPLICATION_ID", pushProperties.getPushGcmApplicationId());
				params.put("SERVICE_ID", pushServiceId);
			} else if(OemId.equals("SY_android")){
				params.put("APPLICATION_ID", pushProperties.getBmPushGcmApplicationId());
				params.put("SERVICE_ID", pushServiceId);				
			} else if(OemId.equals("NS_android")){
				params.put("APPLICATION_ID", pushProperties.getEl1PushGcmApplicationId());
				params.put("SERVICE_ID", pushServiceId);				
			}
			
		} else {
			if(OemId.equals("AM_ios")){
				params.put("APPLICATION_ID", pushProperties.getPushApnsApplicationId());
				params.put("SERVICE_ID", pushServiceId);
			}else if(OemId.equals("SY_ios")){
				params.put("APPLICATION_ID", pushProperties.getBmPushApnsApplicationId());
				params.put("SERVICE_ID", pushServiceId);
			} else if(OemId.equals("NS_ios")){
				params.put("APPLICATION_ID", pushProperties.getEl1PushApnsApplicationId());
				params.put("SERVICE_ID", pushServiceId);				
			}
			
		}
		
		params.put("APP_PUSH_TYPE", pushVO.getAppPushType());
		params.put("DEVICE_ID", pushVO.getDeviceId());
		params.put("SERVICE_KEY", pushVO.getServiceKey());
		params.put("DEVICE_TOKEN", pushVO.getDeviceToken());
		
		reqData.setJson(gson.toJson(params));
		reqData.setConnectionTimeout(Integer.parseInt(pushProperties.getConnectionTimeout()));
		reqData.setTimeout(Integer.parseInt(pushProperties.getReadTimeout()));
		
		RestResultData resultData = restAgent.requestDeleteProxy(reqData, pushHttpProxyHost, pushHttpProxySubHost, pushHttpProxyPort);
		
		if (resultData == null) {
			logger.error("failed to request PushGw Infra Server. mgrappLoginId({})", mgrappLoginId);
			return null;
		}

		if (resultData.getStatusCode() != HttpStatus.SC_OK) {
			logger.error("failed to request PushGw Infra Server. mgrappLoginId({}) statusCode({})", mgrappLoginId, resultData.getStatusCode());
			return null;
		}

		ResPushJSON resPushJSON = new ResPushJSON();
		
		try {
			resPushJSON = gson.fromJson(resultData.getJson(), ResPushJSON.class);

		} catch (JsonSyntaxException e) {
			logger.error("JSON({}) Exception({})", resultData.getJson(), ExceptionUtil.getPrintStackTrace(e));
			return null;
		}
		
		return resPushJSON;
		
	}

	public static String createPushId() throws Exception {
		String toDay = new SimpleDateFormat("yyyyMMdd").format(new Date());
		Random rd = new Random();
		int max, min;
		max = 9999; min = 1000;
		String ramdomNumber = String.valueOf(rd.nextInt(max - min +1) + min);
		
		StringBuilder sb = new StringBuilder(toDay);
		return sb.append(ramdomNumber).toString();
	}

	public RestRequestData makeHeaderData(String url,String authKey, String OemId){
		//20190211 源�踰붿＜ OemId�뿉 NS�룷�븿�릺硫� pushAuth媛� 遺꾧린泥섎━
		String pushAuth = "";
		if (OemId.contains("NS")) {
			pushAuth = pushProperties.getEl1PushAuth();
		} else if (OemId.contains("AM")){
			pushAuth = pushProperties.getPushAuth();
		} else if(OemId.contains("SY")) {
			pushAuth = pushProperties.getPushBMAuth();
		}
		
		// set headers 		
		RestRequestData reqData = new RestRequestData(url);
		reqData.setHeader(HttpHeaders.CONTENT_TYPE, PushConst.HD_VALUE_CONTENTTYPE_JSON_UTF8); //M
		reqData.setHeader(HttpHeaders.ACCEPT, PushConst.HD_VALUE_ACCEPT_TYPE); //M
		
		reqData.setHeader(HttpHeaders.AUTHORIZATION, PushConst.HD_VALUE_AUTHORIZATION_AUTH_PREFIX 
				+ pushAuth
				+ PushConst.SEMI_COLON 
				+ authKey); 
			
		return reqData;
	}
	
}
