package com.lgu.ccss.mgr.user.model;

public class ResultMgrUserJSON {	
	
	private int userIndex;
	private String mgrappLoginId;
	private String userNm;
	private String mgrConStatus;
	
	public int getUserIndex() {
		return userIndex;
	}
	public void setUserIndex(int userIndex) {
		this.userIndex = userIndex;
	}
	
	public String getMgrappLoginId() {
		return mgrappLoginId;
	}
	public void setMgrappLoginId(String mgrappLoginId) {
		this.mgrappLoginId = mgrappLoginId;
	}
	public String getUserNm() {
		return userNm;
	}
	public void setUserNm(String userNm) {
		this.userNm = userNm;
	}
	public String getMgrConStatus() {
		return mgrConStatus;
	}
	public void setMgrConStatus(String mgrConStatus) {
		this.mgrConStatus = mgrConStatus;
	}
	@Override
	public String toString() {
		return "ResultMgrUserJSON [userIndex=" + userIndex + ", mgrappLoginId="
				+ mgrappLoginId + ", userNm=" + userNm + ", mgrConStatus="
				+ mgrConStatus + "]";
	}
	
	
}
