package com.lgu.ccss.mgr.clova.model.login;


public class LoginResultData {

	private String clovaAccessToken;
	private int expiresIn;
	private String refreshToken;
	private String nid;
	
	public String getClovaAccessToken() {
		return clovaAccessToken;
	}
	public void setClovaAccessToken(String clovaAccessToken) {
		this.clovaAccessToken = clovaAccessToken;
	}
	public int getExpiresIn() {
		return expiresIn;
	}
	public void setExpiresIn(int expiresIn) {
		this.expiresIn = expiresIn;
	}
	public String getRefreshToken() {
		return refreshToken;
	}
	public void setRefreshToken(String refreshToken) {
		this.refreshToken = refreshToken;
	}
	
	public String getNid() {
		return nid;
	}
	public void setNid(String nid) {
		this.nid = nid;
	}
	@Override
	public String toString() {
		return "LoginResultData [clovaAccessToken=" + clovaAccessToken
				+ ", expiresIn=" + expiresIn + ", refreshToken=" + refreshToken
				+ ", nid=" + nid + "]";
	}
	
}
