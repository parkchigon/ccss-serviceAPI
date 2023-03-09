package com.lgu.ccss.mgr.cekInfotainment.model.searchOneId;

public class ResultSearchOneIdJSON {	
	
	private String oneId;
	private String ssoKey;
	private String oneIdMembNo;
	private String loginStatus;
	private String iotSessionKey;
	private String homeCode;
	private String ctn;
	
	public String getOneId() {
		return oneId;
	}
	public void setOneId(String oneId) {
		this.oneId = oneId;
	}
	public String getSsoKey() {
		return ssoKey;
	}
	public void setSsoKey(String ssoKey) {
		this.ssoKey = ssoKey;
	}
	public String getOneIdMembNo() {
		return oneIdMembNo;
	}
	public void setOneIdMembNo(String oneIdMembNo) {
		this.oneIdMembNo = oneIdMembNo;
	}
	
	public String getLoginStatus() {
		return loginStatus;
	}
	public void setLoginStatus(String loginStatus) {
		this.loginStatus = loginStatus;
	}
	public String getIotSessionKey() {
		return iotSessionKey;
	}
	public void setIotSessionKey(String iotSessionKey) {
		this.iotSessionKey = iotSessionKey;
	}
	public String getHomeCode() {
		return homeCode;
	}
	public void setHomeCode(String homeCode) {
		this.homeCode = homeCode;
	}
	public String getCtn() {
		return ctn;
	}
	public void setCtn(String ctn) {
		this.ctn = ctn;
	}
	@Override
	public String toString() {
		return "ResultSearchOneIdJSON [oneId=" + oneId + ", ssoKey=" + ssoKey
				+ ", oneIdMembNo=" + oneIdMembNo + ", loginStatus="
				+ loginStatus + ", iotSessionKey=" + iotSessionKey
				+ ", homeCode=" + homeCode + ", ctn=" + ctn + "]";
	}
	
	
	
	
}
