package com.lgu.ccss.common.model.mgr;

public class MgrAppGCalendarUserVO {
	private String mgrappId;
	private String mgrappLoginId;
	private String mgrConStatus;
	private String userNm;
	
	private String membId;
	private String serviceId;
	private String tokenNm;
	
	
	public String getMgrappId() {
		return mgrappId;
	}
	public void setMgrappId(String mgrappId) {
		this.mgrappId = mgrappId;
	}
	public String getMgrConStatus() {
		return mgrConStatus;
	}
	public String getMgrappLoginId() {
		return mgrappLoginId;
	}
	public void setMgrappLoginId(String mgrappLoginId) {
		this.mgrappLoginId = mgrappLoginId;
	}
	public void setMgrConStatus(String mgrConStatus) {
		this.mgrConStatus = mgrConStatus;
	}
	
	public String getUserNm() {
		return userNm;
	}
	public void setUserNm(String userNm) {
		this.userNm = userNm;
	}
	public String getMembId() {
		return membId;
	}
	public void setMembId(String membId) {
		this.membId = membId;
	}
	public String getServiceId() {
		return serviceId;
	}
	public void setServiceId(String serviceId) {
		this.serviceId = serviceId;
	}
	public String getTokenNm() {
		return tokenNm;
	}
	public void setTokenNm(String tokenNm) {
		this.tokenNm = tokenNm;
	}
	
	
	
	
}
