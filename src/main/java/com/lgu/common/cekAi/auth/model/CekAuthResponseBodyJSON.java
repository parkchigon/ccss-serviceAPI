package com.lgu.common.cekAi.auth.model;

import java.util.List;
import java.util.Map;

import com.lgu.common.cekAi.auth.model.addDevice.CekAddDeviceInfoResponseVO;
import com.lgu.common.cekAi.auth.model.getAutorizationCode.CekGetAutorizationCodeVO;

public class CekAuthResponseBodyJSON {
	private String customId;
	private CekAddDeviceInfoResponseVO device;
	private CekGetAutorizationCodeVO cek;
	private List<Map<String,Object>> authInfos;
	
	public String getCustomId() {
		return customId;
	}
	public void setCustomId(String customId) {
		this.customId = customId;
	}
	public CekAddDeviceInfoResponseVO getDevice() {
		return device;
	}
	public void setDevice(CekAddDeviceInfoResponseVO device) {
		this.device = device;
	}
	public CekGetAutorizationCodeVO getCek() {
		return cek;
	}
	public void setCek(CekGetAutorizationCodeVO cek) {
		this.cek = cek;
	}
	public List<Map<String, Object>> getAuthInfos() {
		return authInfos;
	}
	public void setAuthInfos(List<Map<String, Object>> authInfos) {
		this.authInfos = authInfos;
	}
	

	
}
