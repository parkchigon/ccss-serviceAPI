package com.lgu.ccss.api.clova.model;

public class ResultClovaLoginJSON {

	private String clovaAccessToken;
	private String expiredIn;
	private String refreshToken;
	private String tokenType;
	
	public String getClovaAccessToken() {
		return clovaAccessToken;
	}
	public void setClovaAccessToken(String clovaAccessToken) {
		this.clovaAccessToken = clovaAccessToken;
	}
	public String getExpiredIn() {
		return expiredIn;
	}
	public void setExpiredIn(String expiredIn) {
		this.expiredIn = expiredIn;
	}
	public String getRefreshToken() {
		return refreshToken;
	}
	public void setRefreshToken(String refreshToken) {
		this.refreshToken = refreshToken;
	}
	
	public String getTokenType() {
		return tokenType;
	}
	public void setTokenType(String tokenType) {
		this.tokenType = tokenType;
	}
	@Override
	public String toString() {
		return "ResultClovaLoginJSON [clovaAccessToken=" + clovaAccessToken
				+ ", expiredIn=" + expiredIn + ", refreshToken=" + refreshToken
				+ ", tokenType=" + tokenType + "]";
	}

}
