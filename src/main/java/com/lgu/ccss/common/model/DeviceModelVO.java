package com.lgu.ccss.common.model;

import java.util.Date;

public class DeviceModelVO {
	private String deviceModelId;
	private String oemId;
	private String deviceModelNm;
	private String deviceType;
	private String firmwareNewVer;

	private String regId;
	private Date regDt;
	private String updId;
	private Date updDt;
	
	private String clovaClientId;
	private String clovaClientSecret;
	
	public String getDeviceModelId() {
		return deviceModelId;
	}
	public void setDeviceModelId(String deviceModelId) {
		this.deviceModelId = deviceModelId;
	}
	public String getOemId() {
		return oemId;
	}
	public void setOemId(String oemId) {
		this.oemId = oemId;
	}
	public String getDeviceModelNm() {
		return deviceModelNm;
	}
	public void setDeviceModelNm(String deviceModelNm) {
		this.deviceModelNm = deviceModelNm;
	}
	public String getDeviceType() {
		return deviceType;
	}
	public void setDeviceType(String deviceType) {
		this.deviceType = deviceType;
	}
	public String getFirmwareNewVer() {
		return firmwareNewVer;
	}
	public void setFirmwareNewVer(String firmwareNewVer) {
		this.firmwareNewVer = firmwareNewVer;
	}
	public String getRegId() {
		return regId;
	}
	public void setRegId(String regId) {
		this.regId = regId;
	}
	public Date getRegDt() {
		return regDt;
	}
	public void setRegDt(Date regDt) {
		this.regDt = regDt;
	}
	public String getUpdId() {
		return updId;
	}
	public void setUpdId(String updId) {
		this.updId = updId;
	}
	public Date getUpdDt() {
		return updDt;
	}
	public void setUpdDt(Date updDt) {
		this.updDt = updDt;
	}
	
	public String getClovaClientId() {
		return clovaClientId;
	}
	public void setClovaClientId(String clovaClientId) {
		this.clovaClientId = clovaClientId;
	}
	public String getClovaClientSecret() {
		return clovaClientSecret;
	}
	public void setClovaClientSecret(String clovaClientSecret) {
		this.clovaClientSecret = clovaClientSecret;
	}
	@Override
	public String toString() {
		return "DeviceModelVO [deviceModelId=" + deviceModelId + ", oemId=" + oemId + ", deviceModelNm=" + deviceModelNm
				+ ", deviceType=" + deviceType + ", firmwareNewVer=" + firmwareNewVer + ", regId=" + regId + ", regDt="
				+ regDt + ", updId=" + updId + ", updDt=" + updDt + ", clovaClientId=" + clovaClientId
				+ ", clovaClientSecret=" + clovaClientSecret + "]";
	}
	
}
