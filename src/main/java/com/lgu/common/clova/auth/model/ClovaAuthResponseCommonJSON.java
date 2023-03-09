package com.lgu.common.clova.auth.model;


public class ClovaAuthResponseCommonJSON {
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
		return "CekAuthResponseCommonJSON [resultCode=" + resultCode
				+ ", resultMsg=" + resultMsg + "]";
	}

	
}
