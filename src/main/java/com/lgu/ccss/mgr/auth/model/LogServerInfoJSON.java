package com.lgu.ccss.mgr.auth.model;


public class LogServerInfoJSON {	
	
	private String server;
	private String pushLogSize;
	
	
	public String getServer() {
		return server;
	}
	public void setServer(String server) {
		this.server = server;
	}
	public String getPushLogSize() {
		return pushLogSize;
	}
	public void setPushLogSize(String pushLogSize) {
		this.pushLogSize = pushLogSize;
	}
	@Override
	public String toString() {
		return "LogServerInfoJSON [server=" + server + ", pushLogSize="
				+ pushLogSize + "]";
	}
	
	
	
	
	
}
