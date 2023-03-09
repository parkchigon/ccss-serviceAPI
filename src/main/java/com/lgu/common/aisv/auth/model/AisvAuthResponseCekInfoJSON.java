package com.lgu.common.aisv.auth.model;

public class AisvAuthResponseCekInfoJSON {
	private String cekUserId;
	private String cekOneIdKey;
	private String cekSsoKey;
	private String cekTempIdYn;
	private String cekCtn;
	public String getCekUserId() {
		return cekUserId;
	}
	public void setCekUserId(String cekUserId) {
		this.cekUserId = cekUserId;
	}
	public String getCekOneIdKey() {
		return cekOneIdKey;
	}
	public void setCekOneIdKey(String cekOneIdKey) {
		this.cekOneIdKey = cekOneIdKey;
	}
	public String getCekSsoKey() {
		return cekSsoKey;
	}
	public void setCekSsoKey(String cekSsoKey) {
		this.cekSsoKey = cekSsoKey;
	}
	public String getCekTempIdYn() {
		return cekTempIdYn;
	}
	public void setCekTempIdYn(String cekTempIdYn) {
		this.cekTempIdYn = cekTempIdYn;
	}
	public String getCekCtn() {
		return cekCtn;
	}
	public void setCekCtn(String cekCtn) {
		this.cekCtn = cekCtn;
	}
	@Override
	public String toString() {
		return "AisvAuthResponseCekInfoJSON [cekUserId=" + cekUserId + ", cekOneIdKey=" + cekOneIdKey + ", cekSsoKey="
				+ cekSsoKey + ", cekTempIdYn=" + cekTempIdYn + ", cekCtn=" + cekCtn + "]";
	}
	
	
	
}
