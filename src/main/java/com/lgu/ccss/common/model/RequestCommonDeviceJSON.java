package com.lgu.ccss.common.model;

public class RequestCommonDeviceJSON {
	private String deviceType;
	private String appType;
	private String carOem;
	private String detailInfo;
	
	public String getDeviceType() {
		return deviceType;
	}
	public void setDeviceType(String deviceType) {
		this.deviceType = deviceType;
	}
	public String getAppType() {
		return appType;
	}
	public void setAppType(String appType) {
		this.appType = appType;
	}
	public String getCarOem() {
		return carOem;
	}
	public void setCarOem(String carOem) {
		this.carOem = carOem;
	}
	
	@Override
	public String toString() {
		return "RequestCommonDeviceJSON [deviceType=" + deviceType + ", appType=" + appType + ", carOem=" + carOem
				+ "]";
	}
	public String getDetailInfo() {
		// TODO Auto-generated method stub
		return detailInfo;
	}
}
