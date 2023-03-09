package com.lgu.common.datagift.domain;

public class LogResponseJSON {
	private String resultCode;
	private String resultMsg;
	private String resultData;
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
	public String getResultData() {
		return resultData;
	}
	public void setResultData(String resultData) {
		this.resultData = resultData;
	}
	@Override
	public String toString() {
		return "LogResponseJSON [resultCode=" + resultCode + ", resultMsg=" + resultMsg + ", resultData=" + resultData
				+ "]";
	}
	
	
}
