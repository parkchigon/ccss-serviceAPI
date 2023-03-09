package com.lgu.ccss.mgr.auth.model;

public class ResultMgrAuthLoginJSON {	
	
	private String sessionId;
	private String sessionIdExpiration;
	private String mgrappId;
	private String pushKeyRegYn;
	private LogServerInfoJSON logSvrInfo;
	
	public String getSessionId() {
		return sessionId;
	}
	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}
	public String getSessionIdExpiration() {
		return sessionIdExpiration;
	}
	public void setSessionIdExpiration(String sessionIdExpiration) {
		this.sessionIdExpiration = sessionIdExpiration;
	}
	
	public String getPushKeyRegYn() {
		return pushKeyRegYn;
	}
	public void setPushKeyRegYn(String pushKeyRegYn) {
		this.pushKeyRegYn = pushKeyRegYn;
	}
	
	public String getMgrappId() {
		return mgrappId;
	}
	public void setMgrappId(String mgrappId) {
		this.mgrappId = mgrappId;
	}
	
	
	
	public LogServerInfoJSON getLogSvrInfo() {
		return logSvrInfo;
	}
	public void setLogSvrInfo(LogServerInfoJSON logSvrInfo) {
		this.logSvrInfo = logSvrInfo;
	}
	@Override
	public String toString() {
		return "ResultMgrAuthLoginJSON [sessionId=" + sessionId
				+ ", sessionIdExpiration=" + sessionIdExpiration
				+ ", mgrappId=" + mgrappId + ", pushKeyRegYn=" + pushKeyRegYn
				+ ", logSvrInfo=" + logSvrInfo.toString() + "]";
	}
	
	

	
	
}
