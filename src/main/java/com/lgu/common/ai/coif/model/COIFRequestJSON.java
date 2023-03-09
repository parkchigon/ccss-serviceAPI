package com.lgu.common.ai.coif.model;

public class COIFRequestJSON {
	private COIFRequestCommonJSON common = new COIFRequestCommonJSON();
	private COIFRequestBodyJSON body = new COIFRequestBodyJSON();
	
	public COIFRequestCommonJSON getCommon() {
		return common;
	}
	public void setCommon(COIFRequestCommonJSON common) {
		this.common = common;
	}
	public COIFRequestBodyJSON getBody() {
		return body;
	}
	public void setBody(COIFRequestBodyJSON body) {
		this.body = body;
	}
	
	@Override
	public String toString() {
		return "COIFRequestJSON [common=" + common + ", body=" + body + "]";
	}
}
