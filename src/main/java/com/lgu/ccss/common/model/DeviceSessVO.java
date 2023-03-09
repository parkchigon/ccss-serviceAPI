package com.lgu.ccss.common.model;

public class DeviceSessVO {
	private String deviceSessId;
	private String connDeviceId;
	private String deviceCtn;
	private String usimSn;
	private String deviceConnIp;
	private String deviceLoginDt;
	private String deviceSessExpDt;
	private String membId;
	private String deviceSerial;
	private String membNo;
	
	public String getDeviceSessId() {
		return deviceSessId;
	}
	public void setDeviceSessId(String deviceSessId) {
		this.deviceSessId = deviceSessId;
	}
	public String getConnDeviceId() {
		return connDeviceId;
	}
	public void setConnDeviceId(String connDeviceId) {
		this.connDeviceId = connDeviceId;
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
	public String getDeviceConnIp() {
		return deviceConnIp;
	}
	public void setDeviceConnIp(String deviceConnIp) {
		this.deviceConnIp = deviceConnIp;
	}
	public String getDeviceLoginDt() {
		return deviceLoginDt;
	}
	public void setDeviceLoginDt(String deviceLoginDt) {
		this.deviceLoginDt = deviceLoginDt;
	}
	public String getDeviceSessExpDt() {
		return deviceSessExpDt;
	}
	public void setDeviceSessExpDt(String deviceSessExpDt) {
		this.deviceSessExpDt = deviceSessExpDt;
	}
	public String getMembId() {
		return membId;
	}
	public void setMembId(String membId) {
		this.membId = membId;
	}
	public String getDeviceSerial() {
		return deviceSerial;
	}
	public void setDeviceSerial(String deviceSerial) {
		this.deviceSerial = deviceSerial;
	}
	public String getMembNo() {
		return membNo;
	}
	public void setMembNo(String membNo) {
		this.membNo = membNo;
	}
	
	@Override
	public String toString() {
		return "DeviceSessVO [deviceSessId=" + deviceSessId + ", connDeviceId=" + connDeviceId + ", deviceCtn="
				+ deviceCtn + ", usimSn=" + usimSn + ", deviceConnIp=" + deviceConnIp + ", deviceLoginDt="
				+ deviceLoginDt + ", deviceSessExpDt=" + deviceSessExpDt + ", membId=" + membId + ", deviceSerial="
				+ deviceSerial + ", membNo=" + membNo + "]";
	}
}
