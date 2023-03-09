package com.lgu.common.cekAi.auth.model;

public class CekAuthRequestJSON {
	private CekAuthRequestCommonJSON common = new CekAuthRequestCommonJSON();
	private CekAuthRequestBodyJSON body = new CekAuthRequestBodyJSON();
	
	public CekAuthRequestCommonJSON getCommon() {
		return common;
	}
	public void setCommon(CekAuthRequestCommonJSON common) {
		this.common = common;
	}
	public CekAuthRequestBodyJSON getBody() {
		return body;
	}
	public void setBody(CekAuthRequestBodyJSON body) {
		this.body = body;
	}
	
	@Override
	public String toString() {
		return "CekAuthRequestJSON [common=" + common + ", body=" + body + "]";
	}
}
