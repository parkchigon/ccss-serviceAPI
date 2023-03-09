package com.lgu.ccss.common.model;

public class DevicePushSessVO {
	private String devicePushSessionId;
	private String deviceCtn;
	private String usimSn;
	private String devicePushConnDt;
	private String devicePushConnIp;
	private String connDeviceId;
	private String membId;
	
	public String getDevicePushSessionId() {
		return devicePushSessionId;
	}
	public void setDevicePushSessionId(String devicePushSessionId) {
		this.devicePushSessionId = devicePushSessionId;
	}
	public String getDeviceCtn() {
		return deviceCtn;
	}
	public void setDeviceCtn(String deviceCtn) {
		this.deviceCtn = deviceCtn;
	}
	public String getUsimSn() {
		return usimSn;
	}
	public void setUsimSn(String usimSn) {
		this.usimSn = usimSn;
	}
	public String getDevicePushConnDt() {
		return devicePushConnDt;
	}
	public void setDevicePushConnDt(String devicePushConnDt) {
		this.devicePushConnDt = devicePushConnDt;
	}
	public String getDevicePushConnIp() {
		return devicePushConnIp;
	}
	public void setDevicePushConnIp(String devicePushConnIp) {
		this.devicePushConnIp = devicePushConnIp;
	}
	public String getConnDeviceId() {
		return connDeviceId;
	}
	public void setConnDeviceId(String connDeviceId) {
		this.connDeviceId = connDeviceId;
	}
	public String getMembId() {
		return membId;
	}
	public void setMembId(String membId) {
		this.membId = membId;
	}
	
	@Override
	public String toString() {
		return "DevicePushSessVO [devicePushSessionId=" + devicePushSessionId + ", deviceCtn=" + deviceCtn + ", usimSn="
				+ usimSn + ", devicePushConnDt=" + devicePushConnDt + ", devicePushConnIp=" + devicePushConnIp
				+ ", connDeviceId=" + connDeviceId + ", membId=" + membId + "]";
	}
}
