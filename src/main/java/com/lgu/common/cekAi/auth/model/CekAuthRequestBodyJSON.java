package com.lgu.common.cekAi.auth.model;

import java.util.List;
import java.util.Map;

import com.lgu.common.cekAi.auth.model.addDevice.CekAddDeviceInfoVO;

public class CekAuthRequestBodyJSON {
	public static final String CEK_AUTH_INFO_CONT_SVC_CD = "contSvcCd";
	public static final String CEK_AUTH_INFO_NID = "nid";
	
	public static final String AUTH_PARAMETER1 = "authParameter1";
	public static final String AUTH_PARAMETER2 = "authParameter2";
	public static final String AUTH_PARAMETER3 = "authParameter3";
	public static final String AUTH_PARAMETER4 = "authParameter4";
	public static final String AUTH_PARAMETER5 = "authParameter5";
	public static final String AUTH_PARAMETER6 = "authParameter6";
	public static final String AUTH_PARAMETER7 = "authParameter7";
	public static final String AUTH_PARAMETER8 = "authParameter8";
	public static final String AUTH_PARAMETER9 = "authParameter9";
	public static final String AUTH_PARAMETER10 = "authParameter10";
	

	private String contSvcCd;
	private String customId;
	private String oneId;
	private String ctn;
	private String nid;
	private String serviceType;
	private String idTypeCd;
	
	private CekAddDeviceInfoVO device;
	
	private List<Map<String, String>> authInfos;
	
	private Map<String, String> authInfo;
	
	public String getContSvcCd() {
		return contSvcCd;
	}
	public void setContSvcCd(String contSvcCd) {
		this.contSvcCd = contSvcCd;
	}
	public String getIdTypeCd() {
		return idTypeCd;
	}
	public void setIdTypeCd(String idTypeCd) {
		this.idTypeCd = idTypeCd;
	}
	
	public List<Map<String, String>> getAuthInfos() {
		return authInfos;
	}
	public void setAuthInfos(List<Map<String, String>> authInfos) {
		this.authInfos = authInfos;
	}
	public Map<String, String> getAuthInfo() {
		return authInfo;
	}
	public void setAuthInfo(Map<String, String> authInfo) {
		this.authInfo = authInfo;
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
	
	public CekAddDeviceInfoVO getDevice() {
		return device;
	}
	public void setDevice(CekAddDeviceInfoVO device) {
		this.device = device;
	}	
}
