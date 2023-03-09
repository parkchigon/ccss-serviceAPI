package com.lgu.common.cekAi.auth.model.addDevice;

public class CekAddDeviceInfoVO {
	private String deviceSN;  //UICCID 
	private String deviceTypeCd; // 아직 미정의 SVC_003
	public String getDeviceSN() {
		return deviceSN;
	}
	public void setDeviceSN(String deviceSN) {
		this.deviceSN = deviceSN;
	}
	public String getDeviceTypeCd() {
		return deviceTypeCd;
	}
	public void setDeviceTypeCd(String deviceTypeCd) {
		this.deviceTypeCd = deviceTypeCd;
	}
	@Override
	public String toString() {
		return "CekAddDeviceInfoVO [deviceSN=" + deviceSN + ", deviceTypeCd="
				+ deviceTypeCd + "]";
	}
	
}
