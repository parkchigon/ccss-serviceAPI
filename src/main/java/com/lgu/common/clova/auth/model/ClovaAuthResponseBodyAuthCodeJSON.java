package com.lgu.common.clova.auth.model;

public class ClovaAuthResponseBodyAuthCodeJSON  extends ClovaAuthResponseErrorBodyJSON{
	private String code;
	private String state;
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	
	@Override
	public String toString() {
		return "ClovaAuthResponseBodyAuthCodeJSON [code=" + code + ", state="
				+ state + "]";
	}

	
	
}
