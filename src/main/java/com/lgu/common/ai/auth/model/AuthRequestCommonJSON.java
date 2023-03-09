package com.lgu.common.ai.auth.model;

public class AuthRequestCommonJSON {
	private String clientIp;
	private String devInfo;
	private String osInfo;
	private String nwInfo;
	private String devModel;
	private String carrierType;
	
	private String serviceType;	
	private String deviceSN;
	private String deviceType;
	private String idTypeCd;
	
	private String customId;
	private String contentSvcCd;
	private String contentSvcCode;
	
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
	public String getServiceType() {
		return serviceType;
	}
	public void setServiceType(String serviceType) {
		this.serviceType = serviceType;
	}
	public String getDeviceSN() {
		return deviceSN;
	}
	public void setDeviceSN(String deviceSN) {
		this.deviceSN = deviceSN;
	}
	public String getDeviceType() {
		return deviceType;
	}
	public void setDeviceType(String deviceType) {
		this.deviceType = deviceType;
	}
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
	public String getContentSvcCd() {
		return contentSvcCd;
	}
	public void setContentSvcCd(String contentSvcCd) {
		this.contentSvcCd = contentSvcCd;
	}
	public String getContentSvcCode() {
		return contentSvcCode;
	}
	public void setContentSvcCode(String contentSvcCode) {
		this.contentSvcCode = contentSvcCode;
	}
	
	@Override
	public String toString() {
		return "AuthRequestCommonJSON [clientIp=" + clientIp + ", devInfo=" + devInfo + ", osInfo=" + osInfo
				+ ", nwInfo=" + nwInfo + ", devModel=" + devModel + ", carrierType=" + carrierType + ", serviceType="
				+ serviceType + ", deviceSN=" + deviceSN + ", deviceType=" + deviceType + ", idTypeCd=" + idTypeCd
				+ ", customId=" + customId + ", contentSvcCd=" + contentSvcCd + ", contentSvcCode=" + contentSvcCode
				+ "]";
	}
}
