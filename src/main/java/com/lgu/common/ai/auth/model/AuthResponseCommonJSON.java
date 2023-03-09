package com.lgu.common.ai.auth.model;

public class AuthResponseCommonJSON {
	private String resultCode;

	public String getResultCode() {
		return resultCode;
	}

	public void setResultCode(String resultCode) {
		this.resultCode = resultCode;
	}

	@Override
	public String toString() {
		return "AuthResponseCommonJSON [resultCode=" + resultCode + "]";
	}
}
