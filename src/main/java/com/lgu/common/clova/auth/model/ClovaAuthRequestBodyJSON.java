package com.lgu.common.clova.auth.model;

import java.util.HashMap;
import java.util.Map;

public class ClovaAuthRequestBodyJSON {
	
	private Map<String, String> authInfo =new HashMap<String, String>();
	
	public Map<String, String> getAuthInfo() {
		return authInfo;
	}
	public void setAuthInfo(Map<String, String> authInfo) {
		this.authInfo = authInfo;
	}
	
	@Override
	public String toString() {
		return "ClovaAuthRequestBodyJSON [authInfo=" + authInfo + "]";
	}
	
	
}
