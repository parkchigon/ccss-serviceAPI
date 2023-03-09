package com.lgu.ccss.api.conf.model;

public class ResultConfEncryptJSON {
	private String securityKey;

	public String getSecurityKey() {
		return securityKey;
	}

	public void setSecurityKey(String securityKey) {
		this.securityKey = securityKey;
	}

	@Override
	public String toString() {
		return "ResultConfEncryptJSON [securityKey=" + securityKey + "]";
	}
}
