package com.lgu.common.aisv.auth.model;

public class AisvAuthRequestCommonJSON {
	private String trxId;
	private String serviceType;
	private String devType;
	private String devInfo;
	private String osInfo;
	private String nwInfo;
	private String deviceModel;
	private String carrierType;
	private String clientIp;
	
	private String ctn;
	
	public String getTrxId() {
		return trxId;
	}
	public void setTrxId(String trxId) {
		this.trxId = trxId;
	}
	public String getServiceType() {
		return serviceType;
	}
	public void setServiceType(String serviceType) {
		this.serviceType = serviceType;
	}
	public String getDevType() {
		return devType;
	}
	public void setDevType(String devType) {
		this.devType = devType;
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
	public String getDeviceModel() {
		return deviceModel;
	}
	public void setDeviceModel(String deviceModel) {
		this.deviceModel = deviceModel;
	}
	public String getCarrierType() {
		return carrierType;
	}
	public void setCarrierType(String carrierType) {
		this.carrierType = carrierType;
	}
	public String getClientIp() {
		return clientIp;
	}
	public void setClientIp(String clientIp) {
		this.clientIp = clientIp;
	}
	
	
	public String getCtn() {
		return ctn;
	}
	public void setCtn(String ctn) {
		this.ctn = ctn;
	}
	@Override
	public String toString() {
		return "AisvAuthRequestCommonJSON [trxId=" + trxId + ", serviceType=" + serviceType + ", devType=" + devType
				+ ", devInfo=" + devInfo + ", osInfo=" + osInfo + ", nwInfo=" + nwInfo + ", deviceModel=" + deviceModel
				+ ", carrierType=" + carrierType + ", clientIp=" + clientIp + ", ctn=" + ctn + "]";
	}
	
	
}
