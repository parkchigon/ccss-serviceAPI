package com.lgu.ccss.common.model;

public class ResponseJSON {
	private ResponseCommonJSON common;
	private String resultCode;
	private String resultMsg;
	private Object resultData;
	
	public ResponseJSON() {
		this.common = new ResponseCommonJSON();
	}
	public ResponseCommonJSON getCommon() {
		return common;
	}
	public void setCommon(ResponseCommonJSON common) {
		this.common = common;
	}
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
	public Object getResultData() {
		return resultData;
	}
	public void setResultData(Object resultData) {
		this.resultData = resultData;
	}
	
	@Override
	public String toString() {
		return "ResponseJSON [common=" + common + ", resultCode=" + resultCode + ", resultMsg=" + resultMsg
				+ ", resultData=" + resultData + "]";
	}
}
