package com.lgu.common.esb.oAuth.model;

public class ResponseOAuth2AccessTokenDto {
	private String token_type;
	private String access_token;
	private int expires_in;
	private String scope;
	private String refresh_token;
	private int code;
	
	public String getToken_type() {
		return token_type;
	}
	public void setToken_type(String token_type) {
		this.token_type = token_type;
	}
	public String getAccess_token() {
		return access_token;
	}
	public void setAccess_token(String access_token) {
		this.access_token = access_token;
	}
	public int getExpires_in() {
		return expires_in;
	}
	public void setExpires_in(int expires_in) {
		this.expires_in = expires_in;
	}
	public String getScope() {
		return scope;
	}
	public void setScope(String scope) {
		this.scope = scope;
	}
	public String getRefresh_token() {
		return refresh_token;
	}
	public void setRefresh_token(String refresh_token) {
		this.refresh_token = refresh_token;
	}
	
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	@Override
	public String toString() {
		return "ResponseOAuth2AccessTokenDto [token_type=" + token_type + ", access_token=" + access_token
				+ ", expires_in=" + expires_in + ", scope=" + scope + ", refresh_token=" + refresh_token + ", code="
				+ code + "]";
	}
	
}
