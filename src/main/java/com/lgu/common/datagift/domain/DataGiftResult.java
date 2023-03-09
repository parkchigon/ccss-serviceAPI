package com.lgu.common.datagift.domain;

public class DataGiftResult {
	// "Result": "success",
	// "ResultCode": "0000",
	// "ResultMessage": "선물 취소 성공."

	String Result = "";
	String ResultCode = "";
	String ResultMessage = "";

	public String getResult() {
		return Result;
	}

	public void setResult(String result) {
		Result = result;
	}

	public String getResultCode() {
		return ResultCode;
	}

	public void setResultCode(String resultCode) {
		ResultCode = resultCode;
	}

	public String getResultMessage() {
		return ResultMessage;
	}

	public void setResultMessage(String resultMessage) {
		ResultMessage = resultMessage;
	}

}
