package com.lgu.common.clova.auth.model;

import com.fasterxml.jackson.annotation.JsonInclude;

public class ClovaAuthResponseBodyJSON {
	private ClovaAuthResponseBodyAuthCodeJSON authCode;
	
	private ClovaAuthResponseBodyAccessTokenJSON accessToken;
	
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private String error;
	
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private String error_description;
	
	
	public String getError() {
		return error;
	}
	public void setError(String error) {
		this.error = error;
	}
	public String getError_description() {
		return error_description;
	}
	public void setError_description(String error_description) {
		this.error_description = error_description;
	}
	public ClovaAuthResponseBodyAuthCodeJSON getAuthCode() {
		return authCode;
	}
	public void setAuthCode(ClovaAuthResponseBodyAuthCodeJSON authCode) {
		this.authCode = authCode;
	}
	public ClovaAuthResponseBodyAccessTokenJSON getAccessToken() {
		return accessToken;
	}
	public void setAccessToken(ClovaAuthResponseBodyAccessTokenJSON accessToken) {
		this.accessToken = accessToken;
	}
	@Override
	public String toString() {
		return "ClovaAuthResponseBodyJSON [authCode=" + authCode
				+ ", accessToken=" + accessToken + ", error=" + error
				+ ", error_description=" + error_description + "]";
	}
	
	

	
}
