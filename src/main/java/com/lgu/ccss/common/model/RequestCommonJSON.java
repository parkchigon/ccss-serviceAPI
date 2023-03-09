package com.lgu.ccss.common.model;

import org.springmodules.validation.bean.conf.loader.annotation.handler.NotEmpty;

import com.lgu.common.ai.auth.model.AuthRequestCommonJSON;
import com.lgu.common.aisv.auth.model.AisvAuthRequestCommonJSON;
import com.lgu.common.cekAi.auth.model.CekAuthRequestCommonJSON;
import com.lgu.common.cekAi.auth.model.addDevice.CekAddDeviceAuthRequestCommonJSON;

public class RequestCommonJSON{
	@NotEmpty
	private String apiId;
	private String ccssToken;
	private String sessionId;
	private RequestCommonDeviceJSON device;
	private RequestCommonLogDataJSON logData;
	
	public RequestCommonJSON() {
		this.logData = new RequestCommonLogDataJSON();
		this.device = new RequestCommonDeviceJSON();
	}

	public String getApiId() {
		return apiId;
	}

	public void setApiId(String apiId) {
		this.apiId = apiId;
	}

	public String getCcssToken() {
		return ccssToken;
	}

	public void setCcssToken(String ccssToken) {
		this.ccssToken = ccssToken;
	}
	

	public String getSessionId() {
		return sessionId;
	}

	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}

	public RequestCommonDeviceJSON getDevice() {
		return device;
	}

	public void setDevice(RequestCommonDeviceJSON device) {
		this.device = device;
	}

	public RequestCommonLogDataJSON getLogData() {
		return logData;
	}

	public void setLogData(RequestCommonLogDataJSON logData) {
		this.logData = logData;
	}

	@Override
	public String toString() {
		return "RequestCommonJSON [apiId=" + apiId + ", ccssToken=" + ccssToken + ", sessionId=" + sessionId + ", device=" + device + ", logData="
				+ logData + "]";
	}
	
	public AuthRequestCommonJSON getAuthRequestCommon() {
		AuthRequestCommonJSON common = new AuthRequestCommonJSON();

		common.setClientIp(logData.getClientIp());
		common.setDevInfo(logData.getDevInfo());
		common.setOsInfo(logData.getOsInfo());
		common.setNwInfo(logData.getNwInfo());
		common.setDevModel(logData.getDevModel());
		common.setCarrierType(logData.getCarrierType());
		common.setServiceType(logData.getSvcType());
		common.setDeviceType(logData.getDevType());
		
		return common;
	}
	
	public CekAuthRequestCommonJSON getCekAuthRequestCommon() {
		CekAuthRequestCommonJSON common = new CekAuthRequestCommonJSON();

		common.setClientIp(logData.getClientIp());
		common.setDevInfo(logData.getDevInfo());
		common.setOsInfo(logData.getOsInfo());
		common.setNwInfo(logData.getNwInfo());
		common.setDevModel(logData.getDevModel());
		common.setCarrierType(logData.getCarrierType());
		
		return common;
	}
	
	public CekAddDeviceAuthRequestCommonJSON getCekAddDeviceAuthRequestCommon() {
		CekAddDeviceAuthRequestCommonJSON common = new CekAddDeviceAuthRequestCommonJSON();

		common.setClientIp(logData.getClientIp());
		common.setDevInfo(logData.getDevInfo());
		common.setOsInfo(logData.getOsInfo());
		common.setNwInfo(logData.getNwInfo());
		common.setDevModel(logData.getDevModel());
		common.setCarrierType(logData.getCarrierType());
		common.setAuthInfo("");
	
		return common;
	}
	
	public AisvAuthRequestCommonJSON getAisvAuthRequestCommonJSON() {
		AisvAuthRequestCommonJSON common = new AisvAuthRequestCommonJSON();

		common.setClientIp(logData.getClientIp());
		common.setDevInfo(logData.getDevInfo());
		common.setOsInfo(logData.getOsInfo());
		common.setNwInfo(logData.getNwInfo());
		common.setDeviceModel(logData.getDevModel());
		common.setCarrierType(logData.getCarrierType());
		
		return common;
	}
}