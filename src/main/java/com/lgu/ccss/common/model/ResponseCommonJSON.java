package com.lgu.ccss.common.model;

public class ResponseCommonJSON {
	private String apiId;

	public String getApiId() {
		return apiId;
	}
	public void setApiId(String apiId) {
		this.apiId = apiId;
	}
	
	@Override
	public String toString() {
		return "ResponseCommonJSON [apiId=" + apiId + "]";
	}
}
