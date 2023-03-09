package com.lgu.common.cekAi.auth.model.addDevice;

import com.lgu.common.cekAi.auth.model.addDevice.CekAddDeviceInfoVO;

public class CekAddDeviceAuthRequestCommonJSON {
	private String clientIp;
	private String devInfo;
	private String osInfo;
	private String nwInfo;
	private String devModel;
	private String carrierType;
	
	
	private String customId;
	private String oneId;
	private String ctn;
	private String nid;

	private String serviceType;	
	private String idTypeCd;
	private String authInfo;
	
	private CekAddDeviceInfoVO device;
	
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
	public String getCustomId() {
		return customId;
	}
	public void setCustomId(String customId) {
		this.customId = customId;
	}
	public String getOneId() {
		return oneId;
	}
	public void setOneId(String oneId) {
		this.oneId = oneId;
	}
	public String getCtn() {
		return ctn;
	}
	public void setCtn(String ctn) {
		this.ctn = ctn;
	}
	public String getNid() {
		return nid;
	}
	public void setNid(String nid) {
		this.nid = nid;
	}
	public String getServiceType() {
		return serviceType;
	}
	public void setServiceType(String serviceType) {
		this.serviceType = serviceType;
	}
	public String getIdTypeCd() {
		return idTypeCd;
	}
	public void setIdTypeCd(String idTypeCd) {
		this.idTypeCd = idTypeCd;
	}
	
	public String getAuthInfo() {
		return authInfo;
	}
	public void setAuthInfo(String authInfo) {
		this.authInfo = authInfo;
	}
	
	public CekAddDeviceInfoVO getDevice() {
		return device;
	}
	public void setDevice(CekAddDeviceInfoVO device) {
		this.device = device;
	}
	@Override
	public String toString() {
		return "CekAuthRequestCommonJSON [clientIp=" + clientIp + ", devInfo="
				+ devInfo + ", osInfo=" + osInfo + ", nwInfo=" + nwInfo
				+ ", devModel=" + devModel + ", carrierType=" + carrierType
				+ ", customId=" + customId + ", oneId=" + oneId + ", ctn="
				+ ctn + ", nid=" + nid + ", serviceType=" + serviceType
				+ ", idTypeCd=" + idTypeCd + ", authInfo=" + authInfo
				+ ", device=" + device + "]";
	}
	
	
}
