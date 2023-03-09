package com.lgu.common.clova.auth.model;

public class DeviceInfoList {
	String deviceId;
	String location;
	
	public String getDeviceId() {
		return deviceId;
	}
	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	@Override
	public String toString() {
		return "DeviceInfoList [deviceId=" + deviceId + ", location=" + location + "]";
	}
	
	
}
