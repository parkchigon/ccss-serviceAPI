package com.lgu.common.cekAi.auth.model.addDevice;

import com.lgu.common.cekAi.auth.model.addDevice.CekAddDeviceInfoVO;

public class CekAddDeivceAuthRequestBodyJSON {
	
	private String customId;
	private String nid;
	private String idTypeCd;
	private String authInfo;
	private String serviceType;
	
	private CekAddDeviceInfoVO device;
	
	public String getIdTypeCd() {
		return idTypeCd;
	}
	public void setIdTypeCd(String idTypeCd) {
		this.idTypeCd = idTypeCd;
	}
	
	public String getCustomId() {
		return customId;
	}
	public void setCustomId(String customId) {
		this.customId = customId;
	}
	
	public String getNid() {
		return nid;
	}
	public void setNid(String nid) {
		this.nid = nid;
	}
	public String getServiceType() {
		return serviceType;
	}
	public void setServiceType(String serviceType) {
		this.serviceType = serviceType;
	}
	
	public CekAddDeviceInfoVO getDevice() {
		return device;
	}
	public void setDevice(CekAddDeviceInfoVO device) {
		this.device = device;
	}
	public String getAuthInfo() {
		return authInfo;
	}
	public void setAuthInfo(String authInfo) {
		this.authInfo = authInfo;
	}
	
	
}
