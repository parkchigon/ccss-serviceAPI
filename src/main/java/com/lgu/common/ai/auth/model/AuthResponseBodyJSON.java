package com.lgu.common.ai.auth.model;

public class AuthResponseBodyJSON {
	private AuthResponseBodyDeviceTokenJSON deviceToken;
	private AuthResponseBodyPlatformTokenJSON platformToken;
	
	public AuthResponseBodyDeviceTokenJSON getDeviceToken() {
		return deviceToken;
	}
	public void setDeviceToken(AuthResponseBodyDeviceTokenJSON deviceToken) {
		this.deviceToken = deviceToken;
	}
	public AuthResponseBodyPlatformTokenJSON getPlatformToken() {
		return platformToken;
	}
	public void setPlatformToken(AuthResponseBodyPlatformTokenJSON platformToken) {
		this.platformToken = platformToken;
	}
	
	@Override
	public String toString() {
		return "AuthResponseBodyJSON [deviceToken=" + deviceToken + ", platformToken=" + platformToken + "]";
	}
}
