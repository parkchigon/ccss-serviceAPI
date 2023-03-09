package com.lgu.common.ai.coif.model;

public class COIFResponseJSON {
	private COIFResponseCommonJSON common;
	private COIFResponseBodyJSON body;
	
	public COIFResponseCommonJSON getCommon() {
		return common;
	}
	public void setCommon(COIFResponseCommonJSON common) {
		this.common = common;
	}
	public COIFResponseBodyJSON getBody() {
		return body;
	}
	public void setBody(COIFResponseBodyJSON body) {
		this.body = body;
	}
	
	@Override
	public String toString() {
		return "COIFResponseJSON [common=" + common + ", body=" + body + "]";
	}
}
