package com.lgu.common.ai.auth.model;

import java.util.Map;

public class AuthRequestBodyJSON {
	public static final String AUTH_INFO_CONT_SVC_CD = "contSvcCd";
	
	public static final String AUTH_PARAMETER1 = "authParameter1";
	public static final String AUTH_PARAMETER2 = "authParameter2";
	public static final String AUTH_PARAMETER3 = "authParameter3";
	public static final String AUTH_PARAMETER4 = "authParameter4";
	public static final String AUTH_PARAMETER5 = "authParameter5";
	public static final String AUTH_PARAMETER6 = "authParameter6";
	public static final String AUTH_PARAMETER7 = "authParameter7";
	public static final String AUTH_PARAMETER8 = "authParameter8";
	public static final String AUTH_PARAMETER9 = "authParameter9";
	public static final String AUTH_PARAMETER10 = "authParameter10";
	
	private String idTypeCd;
	private String deviceSN;
	private Map<String, String> authInfo;
	
	public String getIdTypeCd() {
		return idTypeCd;
	}
	public void setIdTypeCd(String idTypeCd) {
		this.idTypeCd = idTypeCd;
	}
	public String getDeviceSN() {
		return deviceSN;
	}
	public void setDeviceSN(String deviceSN) {
		this.deviceSN = deviceSN;
	}
	public Map<String, String> getAuthInfo() {
		return authInfo;
	}
	public void setAuthInfo(Map<String, String> authInfo) {
		this.authInfo = authInfo;
	}
	
	@Override
	public String toString() {
		return "AuthRequestBodyJSON [idTypeCd=" + idTypeCd + ", deviceSN=" + deviceSN + ", authInfo=" + authInfo + "]";
	}
}
