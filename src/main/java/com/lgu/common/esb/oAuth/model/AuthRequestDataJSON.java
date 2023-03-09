package com.lgu.common.esb.oAuth.model;

public class AuthRequestDataJSON {
	private String grantType;
	private String clientId;
	private String clientSecret;
	private String scope;
	public String getGrantType() {
		return grantType;
	}
	public void setGrantType(String grantType) {
		this.grantType = grantType;
	}
	public String getClientId() {
		return clientId;
	}
	public void setClientId(String clientId) {
		this.clientId = clientId;
	}
	public String getClientSecret() {
		return clientSecret;
	}
	public void setClientSecret(String clientSecret) {
		this.clientSecret = clientSecret;
	}
	public String getScope() {
		return scope;
	}
	public void setScope(String scope) {
		this.scope = scope;
	}
	@Override
	public String toString() {
		return "AuthRequestDataJSON [grantType=" + grantType + ", clientId=" + clientId + ", clientSecret="
				+ clientSecret + ", scope=" + scope + "]";
	}
	
	
}
