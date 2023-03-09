package com.lgu.ccss.api.auth.model;

import com.lgu.common.model.ResultCode;

public class LoginResultData {
	private ResultCode resultCode;
	private String membId;
	private Object resultData;

	public ResultCode getResultCode() {
		return resultCode;
	}

	public void setResultCode(ResultCode resultCode) {
		this.resultCode = resultCode;
	}

	public String getMembId() {
		return membId;
	}

	public void setMembId(String membId) {
		this.membId = membId;
	}

	public Object getResultData() {
		return resultData;
	}

	public void setResultData(Object resultData) {
		this.resultData = resultData;
	}

	@Override
	public String toString() {
		return "LoginResultData [resultCode=" + resultCode + ", membId=" + membId + ", resultData=" + resultData + "]";
	}
}
