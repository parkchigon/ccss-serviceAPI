package com.lgu.common.clova.auth.model;

public class ClovaAuthResponseJSON {
	private ClovaAuthResponseCommonJSON common;
	private ClovaAuthResponseBodyJSON body;
	
	
	public ClovaAuthResponseCommonJSON getCommon() {
		return common;
	}
	public void setCommon(ClovaAuthResponseCommonJSON common) {
		this.common = common;
	}
	public ClovaAuthResponseBodyJSON getBody() {
		return body;
	}
	public void setBody(ClovaAuthResponseBodyJSON body) {
		this.body = body;
	}
	@Override
	public String toString() {
		return "ClovaAuthResponseJSON [common=" + common + ", body=" + body
				+ "]";
	}
	
	
	
}
