package com.lgu.common.cekAi.auth.model;


public class CekAuthResponseJSON {
	private CekAuthResponseCommonJSON common;
	private CekAuthResponseBodyJSON body;
	
	public CekAuthResponseCommonJSON getCommon() {
		return common;
	}
	public void setCommon(CekAuthResponseCommonJSON common) {
		this.common = common;
	}
	public CekAuthResponseBodyJSON getBody() {
		return body;
	}
	public void setBody(CekAuthResponseBodyJSON body) {
		this.body = body;
	}
	@Override
	public String toString() {
		return "CekAuthResponseJSON [common=" + common + ", body=" + body + "]";
	}


	
}
