package com.lgu.common.ai.auth.model;

public class AuthResponseBodyDeviceTokenJSON {
	private String createTime;
	private String token;
	private String serviceType;
	private String deviceType;
	private String deviceSN;
	
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public String getServiceType() {
		return serviceType;
	}
	public void setServiceType(String serviceType) {
		this.serviceType = serviceType;
	}
	public String getDeviceType() {
		return deviceType;
	}
	public void setDeviceType(String deviceType) {
		this.deviceType = deviceType;
	}
	public String getDeviceSN() {
		return deviceSN;
	}
	public void setDeviceSN(String deviceSN) {
		this.deviceSN = deviceSN;
	}
	
	@Override
	public String toString() {
		return "AuthResponseBodyDeviceTokenJSON [createTime=" + createTime + ", token=" + token + ", serviceType="
				+ serviceType + ", deviceType=" + deviceType + ", deviceSN=" + deviceSN + "]";
	}
}
