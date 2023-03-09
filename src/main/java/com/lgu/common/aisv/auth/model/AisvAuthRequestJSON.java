package com.lgu.common.aisv.auth.model;

public class AisvAuthRequestJSON {
	private AisvAuthRequestCommonJSON common = new AisvAuthRequestCommonJSON();
	private AisvAuthRequestDataJSON data = new AisvAuthRequestDataJSON();
	public AisvAuthRequestCommonJSON getCommon() {
		return common;
	}
	public void setCommon(AisvAuthRequestCommonJSON common) {
		this.common = common;
	}
	public AisvAuthRequestDataJSON getData() {
		return data;
	}
	public void setData(AisvAuthRequestDataJSON data) {
		this.data = data;
	}
	@Override
	public String toString() {
		return "AisvAuthRequestJSON [common=" + common + ", data=" + data + "]";
	}
	
	
	
}
