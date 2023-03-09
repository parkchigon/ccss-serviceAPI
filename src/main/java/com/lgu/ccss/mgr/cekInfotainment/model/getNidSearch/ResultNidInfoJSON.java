package com.lgu.ccss.mgr.cekInfotainment.model.getNidSearch;

public class ResultNidInfoJSON {
	private String oneId;
	private String oneIdKey;
	private String ssoKey;
	private String tempIdYn;
	private String ctn;
	private String aiTempIdYn;
	private String aisvToken;
	private CekInfoJSON cekInfo;
	
	public String getOneId() {
		return oneId;
	}
	public void setOneId(String oneId) {
		this.oneId = oneId;
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
	public String getAiTempIdYn() {
		return aiTempIdYn;
	}
	public void setAiTempIdYn(String aiTempIdYn) {
		this.aiTempIdYn = aiTempIdYn;
	}
	public String getAisvToken() {
		return aisvToken;
	}
	public void setAisvToken(String aisvToken) {
		this.aisvToken = aisvToken;
	}
	
	public CekInfoJSON getCekInfo() {
		return cekInfo;
	}
	public void setCekInfo(CekInfoJSON cekInfo) {
		this.cekInfo = cekInfo;
	}
	@Override
	public String toString() {
		return "ResultNidInfoJSON [oneId=" + oneId + ", oneIdKey=" + oneIdKey + ", ssoKey=" + ssoKey + ", tempIdYn="
				+ tempIdYn + ", ctn=" + ctn + ", aiTempIdYn=" + aiTempIdYn + ", aisvToken=" + aisvToken + ", cekInfo="
				+ cekInfo.toString() + "]";
	}
	
	
	
	
}
