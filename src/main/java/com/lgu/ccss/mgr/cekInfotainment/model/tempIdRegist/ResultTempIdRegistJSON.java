package com.lgu.ccss.mgr.cekInfotainment.model.tempIdRegist;

public class ResultTempIdRegistJSON {
	private String svcCode;
	private String aiTempIdYn;
	private String userId;
	private String nid;
	private String oneIdKey;
	private String ssoKey;
	private String tempIdYn;
	private String ctn;
	public String getSvcCode() {
		return svcCode;
	}
	public void setSvcCode(String svcCode) {
		this.svcCode = svcCode;
	}
	public String getAiTempIdYn() {
		return aiTempIdYn;
	}
	public void setAiTempIdYn(String aiTempIdYn) {
		this.aiTempIdYn = aiTempIdYn;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getNid() {
		return nid;
	}
	public void setNid(String nid) {
		this.nid = nid;
	}
	public String getOneIdKey() {
		return oneIdKey;
	}
	public void setOneIdKey(String oneIdKey) {
		this.oneIdKey = oneIdKey;
	}
	public String getSsoKey() {
		return ssoKey;
	}
	public void setSsoKey(String ssoKey) {
		this.ssoKey = ssoKey;
	}
	public String getTempIdYn() {
		return tempIdYn;
	}
	public void setTempIdYn(String tempIdYn) {
		this.tempIdYn = tempIdYn;
	}
	public String getCtn() {
		return ctn;
	}
	public void setCtn(String ctn) {
		this.ctn = ctn;
	}
	@Override
	public String toString() {
		return "ResultTempIdRegistJSON [svcCode=" + svcCode + ", aiTempIdYn=" + aiTempIdYn + ", userId=" + userId
				+ ", nid=" + nid + ", oneIdKey=" + oneIdKey + ", ssoKey=" + ssoKey + ", tempIdYn=" + tempIdYn + ", ctn="
				+ ctn + "]";
	}
	
	
}
