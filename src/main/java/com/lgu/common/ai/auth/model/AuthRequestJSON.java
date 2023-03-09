package com.lgu.common.ai.auth.model;

public class AuthRequestJSON {
	private AuthRequestCommonJSON common = new AuthRequestCommonJSON();
	private AuthRequestBodyJSON body = new AuthRequestBodyJSON();
	
	public AuthRequestCommonJSON getCommon() {
		return common;
	}
	public void setCommon(AuthRequestCommonJSON common) {
		this.common = common;
	}
	public AuthRequestBodyJSON getBody() {
		return body;
	}
	public void setBody(AuthRequestBodyJSON body) {
		this.body = body;
	}
	
	@Override
	public String toString() {
		return "AuthRequestJSON [common=" + common + ", body=" + body + "]";
	}
}
