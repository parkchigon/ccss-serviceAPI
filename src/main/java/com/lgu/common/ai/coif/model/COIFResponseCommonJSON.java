package com.lgu.common.ai.coif.model;

public class COIFResponseCommonJSON {
	private String schema;
	private String method;
	private String resultCode;
	private String resultMessage;
	
	public String getSchema() {
		return schema;
	}
	public void setSchema(String schema) {
		this.schema = schema;
	}
	public String getMethod() {
		return method;
	}
	public void setMethod(String method) {
		this.method = method;
	}
	public String getResultCode() {
		return resultCode;
	}
	public void setResultCode(String resultCode) {
		this.resultCode = resultCode;
	}
	public String getResultMessage() {
		return resultMessage;
	}
	public void setResultMessage(String resultMessage) {
		this.resultMessage = resultMessage;
	}
	
	@Override
	public String toString() {
		return "COIFResponseCommonJSON [schema=" + schema + ", method=" + method + ", resultCode=" + resultCode
				+ ", resultMessage=" + resultMessage + "]";
	}
}
