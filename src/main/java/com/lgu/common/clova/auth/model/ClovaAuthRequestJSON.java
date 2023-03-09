package com.lgu.common.clova.auth.model;

public class ClovaAuthRequestJSON {
	
	private ClovaAuthRequestBodyJSON body = new ClovaAuthRequestBodyJSON();
	
	public ClovaAuthRequestBodyJSON getBody() {
		return body;
	}
	public void setBody(ClovaAuthRequestBodyJSON body) {
		this.body = body;
	}
	@Override
	public String toString() {
		return "ClovaAuthRequestJSON [body=" + body + "]";
	}
	
	
}
