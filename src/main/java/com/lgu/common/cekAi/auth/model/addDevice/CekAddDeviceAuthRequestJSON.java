package com.lgu.common.cekAi.auth.model.addDevice;

import com.lgu.common.cekAi.auth.model.CekAuthRequestCommonJSON;

public class CekAddDeviceAuthRequestJSON {
	private CekAuthRequestCommonJSON common = new CekAuthRequestCommonJSON();
	private CekAddDeivceAuthRequestBodyJSON body = new CekAddDeivceAuthRequestBodyJSON();
	
	public CekAuthRequestCommonJSON getCommon() {
		return common;
	}
	public void setCommon(CekAuthRequestCommonJSON common) {
		this.common = common;
	}
	
	public CekAddDeivceAuthRequestBodyJSON getBody() {
		return body;
	}
	public void setBody(CekAddDeivceAuthRequestBodyJSON body) {
		this.body = body;
	}
	
	@Override
	public String toString() {
		return "CekAuthRequestJSON [common=" + common + ", body=" + body + "]";
	}
}
