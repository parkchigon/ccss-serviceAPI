package com.lgu.ccss.common.model;

public class RequestCommonLogDataJSON{
	public static final String OS_INFO_ANDROID  = "android";
	public static final String OS_INFO_IOS  	= "ios";
	public static final String OS_INFO_OTHER  	= "other";
	
	private String clientIp;
	private String devInfo;
	private String osInfo;
	private String nwInfo;
	private String svcName;
	private String devModel;
	private String carrierType;
	private String svcType;
	private String devType;
	
	
	public String getClientIp() {
		return clientIp;
	}
	public void setClientIp(String clientIp) {
		this.clientIp = clientIp;
	}
	public String getDevInfo() {
		return devInfo;
	}
	public void setDevInfo(String devInfo) {
		this.devInfo = devInfo;
	}
	public String getOsInfo() {
		return osInfo;
	}
	public void setOsInfo(String osInfo) {
		this.osInfo = osInfo;
	}
	public String getNwInfo() {
		return nwInfo;
	}
	public void setNwInfo(String nwInfo) {
		this.nwInfo = nwInfo;
	}
	public String getSvcName() {
		return svcName;
	}
	public void setSvcName(String svcName) {
		this.svcName = svcName;
	}
	public String getDevModel() {
		return devModel;
	}
	public void setDevModel(String devModel) {
		this.devModel = devModel;
	}
	public String getCarrierType() {
		return carrierType;
	}
	public void setCarrierType(String carrierType) {
		this.carrierType = carrierType;
	}
	public String getSvcType() {
		return svcType;
	}
	public void setSvcType(String svcType) {
		this.svcType = svcType;
	}
	public String getDevType() {
		return devType;
	}
	public void setDevType(String devType) {
		this.devType = devType;
	}
	
	@Override
	public String toString() {
		return "RequestCommonLogDataJSON [clientIp=" + clientIp + ", devInfo=" + devInfo + ", osInfo=" + osInfo
				+ ", nwInfo=" + nwInfo + ", svcName=" + svcName + ", devModel=" + devModel + ", carrierType="
				+ carrierType + ", svcType=" + svcType + ", devType=" + devType + "]";
	}
}