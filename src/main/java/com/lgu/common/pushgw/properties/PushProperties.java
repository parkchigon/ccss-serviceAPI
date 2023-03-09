package com.lgu.common.pushgw.properties;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class PushProperties {

	@Value("${push.auth}")
	private String pushAuth;
	
	@Value("${push.bm.auth}")
	private String pushBMAuth;
	
	@Value("${el1.push.auth}")
	private String el1PushAuth;
	
	@Value("${push.domain}")
	private String pushDomain;
	
	@Value("${push.port1}")
	private String pushPort1;
	
	@Value("${push.port2}")
	private String pushPort2;
	
	@Value("${push.app.id}")
	private String pushAppId;
	
	@Value("${push.gcm.service.id}")
	private String pushGcmServiceId;
	@Value("${push.gcm.service.passwd}")
	private String pushGcmServicePasswd;
	@Value("${push.gcm.api.key}")
	private String pushGcmApiKey;
	@Value("${push.gcm.application.id}")
	private String pushGcmApplicationId;
	
	@Value("${bm.push.gcm.application.id}")
	private String bmPushGcmApplicationId;
	
	@Value("${el1.push.gcm.application.id}")
	private String el1PushGcmApplicationId;
	
	@Value("${push.apns.service.id}")
	private String pushApnsServiceId;
	@Value("${push.apns.service.passwd}")
	private String pushApnsServicePasswd;
	@Value("${push.apns.application.id}")
	private String pushApnsApplicationId;
	
	@Value("${bm.push.apns.application.id}")
	private String bmPushApnsApplicationId;
	
	@Value("${el1.push.apns.application.id}")
	private String el1PushApnsApplicationId;
	
	@Value("${push.resquest.part.app}")
	private String pushResquestPartApp;
	@Value("${push.resquest.part.sp}")
	private String pushResquestPartSp;
	
	/*@Value("${http.method.post}")
	private String httpMethodPost;
	@Value("${http.method.get}")
	private String httpMethodGet;
	@Value("${http.method.put}")
	private String httpMethodPut;
	@Value("${http.method.del}")
	private String httpMethodDelete;*/
	
	@Value("${http.client.connection.timeout}")
	private String connectionTimeout;
	@Value("${http.client.read.timeout}")
	private String readTimeout;

	@Value("${subscriber.register.gcm.id}")
	private String pushSubscriberRegisterGcmId;
	@Value("${subscriber.register.apns.id}")
	private String pushSubscriberRegisterApnsId;
	@Value("${subscriber.deregister.id}")
	private String pushSubscriberDeregisterId;
	
	@Value("${subscriber.register.gcm.url}")
	private String pushSubscriberRegisterGcmUrl;
	@Value("${subscriber.register.apns.url}")
	private String pushSubscriberRegisterApnsUrl;
	@Value("${subscriber.deregister.url}")
	private String pushSubscriberDeregisterUrl;

	@Value("${subscriber.register.gcm.auth.key}")
	private String pushSubscriberRegisterGcmAuthKey;
	@Value("${subscriber.register.gcm.bm.auth.key}")
	private String pushSubscriberRegisterGcmBMAuthKey; 
	@Value("${subscriber.register.apns.auth.key}")
	private String pushSubscriberRegisterApnsAuthKey;
	@Value("${subscriber.register.apns.bm.auth.key}")
	private String pushSubscriberRegisterApnsBMAuthKey;
	@Value("${subscriber.deregister.auth.key}")
	private String pushSubscriberDeregisterAuthKey;
	@Value("${subscriber.deregister.bm.auth.key}")
	private String pushSubscriberDeregisterBMAuthKey;
	
	@Value("${el1.subscriber.register.gcm.auth.key}")
	private String pushEl1SubscriberRegisterGcmAuthKey; 
	@Value("${el1.subscriber.register.apns.auth.key}")
	private String pushEl1SubscriberRegisterApnsAuthKey; 
	@Value("${el1.subscriber.deregister.auth.key}")
	private String pushEl1SubscriberDeregisterAuthKey; 

	
	public String getPushAuth() {
		return pushAuth;
	}
	public void setPushAuth(String pushAuth) {
		this.pushAuth = pushAuth;
	}
	public String getEl1PushAuth() {
		return el1PushAuth;
	}
	public void setEl1PushAuth(String el1PushAuth) {
		this.el1PushAuth = el1PushAuth;
	}	
	public String getPushDomain() {
		return pushDomain;
	}
	public String getPushPort1() {
		return pushPort1;
	}
	public String getPushPort2() {
		return pushPort2;
	}
	public String getPushAppId() {
		return pushAppId;
	}
	public String getPushGcmServiceId() {
		return pushGcmServiceId;
	}
	public String getPushGcmServicePasswd() {
		return pushGcmServicePasswd;
	}
	public String getPushGcmApiKey() {
		return pushGcmApiKey;
	}
	public String getPushApnsServiceId() {
		return pushApnsServiceId;
	}
	public String getPushApnsServicePasswd() {
		return pushApnsServicePasswd;
	}
	public String getPushResquestPartApp() {
		return pushResquestPartApp;
	}
	public String getPushResquestPartSp() {
		return pushResquestPartSp;
	}
	/*public String getHttpMethodPost() {
		return httpMethodPost;
	}
	public String getHttpMethodGet() {
		return httpMethodGet;
	}
	public String getHttpMethodPut() {
		return httpMethodPut;
	}
	public String getHttpMethodDelete() {
		return httpMethodDelete;
	}*/
	public String getConnectionTimeout() {
		return connectionTimeout;
	}
	public String getReadTimeout() {
		return readTimeout;
	}
	public String getPushSubscriberRegisterGcmId() {
		return pushSubscriberRegisterGcmId;
	}
	public String getPushSubscriberRegisterApnsId() {
		return pushSubscriberRegisterApnsId;
	}
	public String getPushSubscriberDeregisterId() {
		return pushSubscriberDeregisterId;
	}
	public String getPushSubscriberRegisterGcmUrl() {
		return pushSubscriberRegisterGcmUrl;
	}
	public String getPushSubscriberRegisterApnsUrl() {
		return pushSubscriberRegisterApnsUrl;
	}
	public String getPushSubscriberDeregisterUrl() {
		return pushSubscriberDeregisterUrl;
	}
	
	public String getPushSubscriberRegisterGcmAuthKey() {
		return pushSubscriberRegisterGcmAuthKey;
	}
	public String getPushSubscriberRegisterApnsAuthKey() {
		return pushSubscriberRegisterApnsAuthKey;
	}
	public String getPushSubscriberDeregisterAuthKey() {
		return pushSubscriberDeregisterAuthKey;
	}
	public String getPushEl1SubscriberRegisterGcmAuthKey() {
		return pushEl1SubscriberRegisterGcmAuthKey;
	}
	public String getPushEl1SubscriberRegisterApnsAuthKey() {
		return pushEl1SubscriberRegisterApnsAuthKey;
	}
	public String getPushEl1SubscriberDeregisterAuthKey() {
		return pushEl1SubscriberDeregisterAuthKey;
	}	
	public String getPushGcmApplicationId() {
		return pushGcmApplicationId;
	}
	public String getPushApnsApplicationId() {
		return pushApnsApplicationId;
	}
	public String getBmPushGcmApplicationId() {
		return bmPushGcmApplicationId;
	}
	public void setBmPushGcmApplicationId(String bmPushGcmApplicationId) {
		this.bmPushGcmApplicationId = bmPushGcmApplicationId;
	}
	public String getBmPushApnsApplicationId() {
		return bmPushApnsApplicationId;
	}
	public void setBmPushApnsApplicationId(String bmPushApnsApplicationId) {
		this.bmPushApnsApplicationId = bmPushApnsApplicationId;
	}
	public String getEl1PushApnsApplicationId() {
		return el1PushApnsApplicationId;
	}
	public void setEl1PushApnsApplicationId(String el1PushApnsApplicationId) {
		this.el1PushApnsApplicationId = el1PushApnsApplicationId;
	}
	public String getEl1PushGcmApplicationId() {
		return el1PushGcmApplicationId;
	}
	public void setEl1PushGcmApplicationId(String el1PushGcmApplicationId) {
		this.el1PushGcmApplicationId = el1PushGcmApplicationId;
	}
	public String getPushBMAuth() {
		return pushBMAuth;
	}
	public void setPushBMAuth(String pushBMAuth) {
		this.pushBMAuth = pushBMAuth;
	}
	public String getPushSubscriberRegisterGcmBMAuthKey() {
		return pushSubscriberRegisterGcmBMAuthKey;
	}
	public void setPushSubscriberRegisterGcmBMAuthKey(String pushSubscriberRegisterGcmBMAuthKey) {
		this.pushSubscriberRegisterGcmBMAuthKey = pushSubscriberRegisterGcmBMAuthKey;
	}
	public String getPushSubscriberRegisterApnsBMAuthKey() {
		return pushSubscriberRegisterApnsBMAuthKey;
	}
	public void setPushSubscriberRegisterApnsBMAuthKey(String pushSubscriberRegisterApnsBMAuthKey) {
		this.pushSubscriberRegisterApnsBMAuthKey = pushSubscriberRegisterApnsBMAuthKey;
	}
	public String getPushSubscriberDeregisterBMAuthKey() {
		return pushSubscriberDeregisterBMAuthKey;
	}
	public void setPushSubscriberDeregisterBMAuthKey(String pushSubscriberDeregisterBMAuthKey) {
		this.pushSubscriberDeregisterBMAuthKey = pushSubscriberDeregisterBMAuthKey;
	}
	
	
	
}
