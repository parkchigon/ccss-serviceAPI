package com.lgu.ccss.api.service.model;

public class ResultLoginAIJSON {
	private String aiDeviceToken;
	private String aiPlatformToken;
	private String aiUrl;
	
	public String getAiDeviceToken() {
		return aiDeviceToken;
	}
	public void setAiDeviceToken(String aiDeviceToken) {
		this.aiDeviceToken = aiDeviceToken;
	}
	public String getAiPlatformToken() {
		return aiPlatformToken;
	}
	public void setAiPlatformToken(String aiPlatformToken) {
		this.aiPlatformToken = aiPlatformToken;
	}
	public String getAiUrl() {
		return aiUrl;
	}
	public void setAiUrl(String aiUrl) {
		this.aiUrl = aiUrl;
	}
	
	@Override
	public String toString() {
		return "ResultLoginAIJSON [aiDeviceToken=" + aiDeviceToken + ", aiPlatformToken=" + aiPlatformToken + ", aiUrl="
				+ aiUrl + "]";
	}
}
