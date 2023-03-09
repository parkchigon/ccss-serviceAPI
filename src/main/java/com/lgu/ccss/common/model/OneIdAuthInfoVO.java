package com.lgu.ccss.common.model;



public class OneIdAuthInfoVO {
	private String membId;
	private String oneId;
	private String ssoKey;
	private String loginStatus;
	private String oneIdMembNo; // One ID MEMB NO : oneIdKey
	private String iotSessionKey;
	private String homeCode;
	private String ctn;
	private String nid;
	
	private String regId;
	private String regDt;
	private String updId;
	private String updDt;
	public String getMembId() {
		return membId;
	}
	public void setMembId(String membId) {
		this.membId = membId;
	}
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
	public String getLoginStatus() {
		return loginStatus;
	}
	public void setLoginStatus(String loginStatus) {
		this.loginStatus = loginStatus;
	}
	public String getOneIdMembNo() {
		return oneIdMembNo;
	}
	public void setOneIdMembNo(String oneIdMembNo) {
		this.oneIdMembNo = oneIdMembNo;
	}
	public String getRegId() {
		return regId;
	}
	public void setRegId(String regId) {
		this.regId = regId;
	}
	public String getRegDt() {
		return regDt;
	}
	public void setRegDt(String regDt) {
		this.regDt = regDt;
	}
	public String getUpdId() {
		return updId;
	}
	public void setUpdId(String updId) {
		this.updId = updId;
	}
	public String getUpdDt() {
		return updDt;
	}
	public void setUpdDt(String updDt) {
		this.updDt = updDt;
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
	
	
	public String getNid() {
		return nid;
	}
	public void setNid(String nid) {
		this.nid = nid;
	}
	@Override
	public String toString() {
		return "OneIdAuthInfoVO [membId=" + membId + ", oneId=" + oneId
				+ ", ssoKey=" + ssoKey + ", loginStatus=" + loginStatus
				+ ", oneIdMembNo=" + oneIdMembNo + ", iotSessionKey="
				+ iotSessionKey + ", homeCode=" + homeCode + ", ctn=" + ctn
				+ ", regId=" + regId + ", regDt=" + regDt + ", updId=" + updId
				+ ", updDt=" + updDt + ", nid=" + nid + "]";
	}
	
	
	
}
