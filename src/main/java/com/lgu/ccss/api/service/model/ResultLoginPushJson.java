package com.lgu.ccss.api.service.model;

public class ResultLoginPushJson {
	private String password;
	private String securityKey;
	private String pushServer;
	private String protocol;
	private String locInterval;
	private PushTopicData topic;
	
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getSecurityKey() {
		return securityKey;
	}
	public void setSecurityKey(String securityKey) {
		this.securityKey = securityKey;
	}
	public String getPushServer() {
		return pushServer;
	}
	public void setPushServer(String pushServer) {
		this.pushServer = pushServer;
	}
	public String getProtocol() {
		return protocol;
	}
	public void setProtocol(String protocol) {
		this.protocol = protocol;
	}
	public String getLocInterval() {
		return locInterval;
	}
	public void setLocInterval(String locInterval) {
		this.locInterval = locInterval;
	}
	public PushTopicData getTopic() {
		return topic;
	}
	public void setTopic(PushTopicData topic) {
		this.topic = topic;
	}
	
	@Override
	public String toString() {
		return "ResultLoginPushJson [password=" + password + ", securityKey=" + securityKey + ", pushServer="
				+ pushServer + ", protocol=" + protocol + ", locInterval=" + locInterval + ", topic=" + topic + "]";
	}
}
