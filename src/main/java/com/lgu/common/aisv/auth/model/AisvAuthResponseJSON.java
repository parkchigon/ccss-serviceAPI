package com.lgu.common.aisv.auth.model;

public class AisvAuthResponseJSON {
	private AisvAuthResponseCommonJSON common;
	private AisvAuthResponseDataJSON data;
	public AisvAuthResponseCommonJSON getCommon() {
		return common;
	}
	public void setCommon(AisvAuthResponseCommonJSON common) {
		this.common = common;
	}
	public AisvAuthResponseDataJSON getData() {
		return data;
	}
	public void setData(AisvAuthResponseDataJSON data) {
		this.data = data;
	}
	@Override
	public String toString() {
		return "AisvAuthResponseJSON [common=" + common + ", data=" + data + "]";
	}

	
	
}
