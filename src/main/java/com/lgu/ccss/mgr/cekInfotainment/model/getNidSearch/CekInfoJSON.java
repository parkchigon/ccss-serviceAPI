package com.lgu.ccss.mgr.cekInfotainment.model.getNidSearch;

public class CekInfoJSON {
	private String cekUserId;
	private String oneIdKey;
	private String cekSsoKey;
	private String cekTempIdYn;
	private String cekCtn;
	public String getCekUserId() {
		return cekUserId;
	}
	public void setCekUserId(String cekUserId) {
		this.cekUserId = cekUserId;
	}
	public String getOneIdKey() {
		return oneIdKey;
	}
	public void setOneIdKey(String oneIdKey) {
		this.oneIdKey = oneIdKey;
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
		return "CekInfoJSON [cekUserId=" + cekUserId + ", oneIdKey=" + oneIdKey + ", cekSsoKey=" + cekSsoKey
				+ ", cekTempIdYn=" + cekTempIdYn + ", cekCtn=" + cekCtn + "]";
	}
	
	
}
