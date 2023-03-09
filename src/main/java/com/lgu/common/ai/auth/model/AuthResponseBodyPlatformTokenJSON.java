package com.lgu.common.ai.auth.model;

public class AuthResponseBodyPlatformTokenJSON {
	private String deviceType;
	private String expireTime;
	private String createTime;
	private String deviceSN;
	private String token;
	
	public String getDeviceType() {
		return deviceType;
	}
	public void setDeviceType(String deviceType) {
		this.deviceType = deviceType;
	}
	public String getExpireTime() {
		return expireTime;
	}
	public void setExpireTime(String expireTime) {
		this.expireTime = expireTime;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	public String getDeviceSN() {
		return deviceSN;
	}
	public void setDeviceSN(String deviceSN) {
		this.deviceSN = deviceSN;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	
	@Override
	public String toString() {
		return "AuthResponseBodyPlatformTokenJSON [deviceType=" + deviceType + ", expireTime=" + expireTime
				+ ", createTime=" + createTime + ", deviceSN=" + deviceSN + ", token=" + token + "]";
	}
}
