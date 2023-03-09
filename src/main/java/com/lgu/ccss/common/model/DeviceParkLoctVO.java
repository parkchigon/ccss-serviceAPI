package com.lgu.ccss.common.model;

import java.math.BigInteger;
public class DeviceParkLoctVO {
	
	private BigInteger	deviceParkLoctSeq;
	private String connDeviceId;
	private String membId;
	private String address;
	private String lonx;
	private String laty;
	private String regDt;
	private String updDt;
	
	
	public BigInteger getDeviceParkLoctSeq() {
		return deviceParkLoctSeq;
	}
	public void setDeviceParkLoctSeq(BigInteger deviceParkLoctSeq) {
		this.deviceParkLoctSeq = deviceParkLoctSeq;
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
	public String getAddress() {
		return address;
	}
	public String getLonx() {
		return lonx;
	}
	public void setLonx(String lonx) {
		this.lonx = lonx;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	
	public String getLaty() {
		return laty;
	}
	public void setLaty(String laty) {
		this.laty = laty;
	}
	public String getRegDt() {
		return regDt;
	}
	public void setRegDt(String regDt) {
		this.regDt = regDt;
	}
	public String getUpdDt() {
		return updDt;
	}
	public void setUpdDt(String updDt) {
		this.updDt = updDt;
	}
	
	
}
