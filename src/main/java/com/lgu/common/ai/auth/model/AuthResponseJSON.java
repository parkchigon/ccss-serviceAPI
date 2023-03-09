package com.lgu.common.ai.auth.model;

public class AuthResponseJSON {
	private AuthResponseCommonJSON common;
	private AuthResponseBodyJSON body;
	
	public AuthResponseCommonJSON getCommon() {
		return common;
	}
	public void setCommon(AuthResponseCommonJSON common) {
		this.common = common;
	}
	public AuthResponseBodyJSON getBody() {
		return body;
	}
	public void setBody(AuthResponseBodyJSON body) {
		this.body = body;
	}
	
	@Override
	public String toString() {
		return "AuthResponseJSON [common=" + common + ", body=" + body + "]";
	}
}
