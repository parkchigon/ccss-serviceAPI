package com.lgu.common.aisv.auth.model;

public class AisvAuthResponseCommonJSON {
	private String resultCode;
	private String resultMsg;
	public String getResultCode() {
		return resultCode;
	}
	public void setResultCode(String resultCode) {
		this.resultCode = resultCode;
	}
	public String getResultMsg() {
		return resultMsg;
	}
	public void setResultMsg(String resultMsg) {
		this.resultMsg = resultMsg;
	}
	@Override
	public String toString() {
		return "AisvAuthResponseCommonJSON [resultCode=" + resultCode + ", resultMsg=" + resultMsg + "]";
	}
	
}
