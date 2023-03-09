package com.lgu.common.clova.auth.model;

public class ClovaAuthResponseBodyDeleteTokenJSON extends ClovaAuthResponseErrorBodyJSON{
	
	private String access_token;
	private String client_id;
	private String expires_in;
	
	public String getAccess_token() {
		return access_token;
	}
	public void setAccess_token(String access_token) {
		this.access_token = access_token;
	}
	public String getClient_id() {
		return client_id;
	}
	public void setClient_id(String client_id) {
		this.client_id = client_id;
	}
	public String getExpires_in() {
		return expires_in;
	}
	public void setExpires_in(String expires_in) {
		this.expires_in = expires_in;
	}

	


}
