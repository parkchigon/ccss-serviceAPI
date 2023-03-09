package com.lgu.ccss.mgr.auth.model;

public class ResultMgrAuthRegJSON {
	private String sessionId;
	private String mgrappId;

	public String getSessionId() {
		return sessionId;
	}

	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}

	public String getMgrappId() {
		return mgrappId;
	}

	public void setMgrappId(String mgrappId) {
		this.mgrappId = mgrappId;
	}

	@Override
	public String toString() {
		return "ResultMgrAuthRegJSON [sessionId=" + sessionId + ", mgrappId=" + mgrappId + "]";
	}
	
	
}
