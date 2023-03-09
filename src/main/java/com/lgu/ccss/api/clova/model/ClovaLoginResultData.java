package com.lgu.ccss.api.clova.model;

import com.lgu.common.model.ResultCode;

public class ClovaLoginResultData {
	private ResultCode resultCode;
	private Object resultData;

	public ResultCode getResultCode() {
		return resultCode;
	}

	public void setResultCode(ResultCode resultCode) {
		this.resultCode = resultCode;
	}

	public Object getResultData() {
		return resultData;
	}

	public void setResultData(Object resultData) {
		this.resultData = resultData;
	}

	@Override
	public String toString() {
		return "ClovaLoginResultData [resultCode=" + resultCode
				+ ", resultData=" + resultData + "]";
	}

	
}
