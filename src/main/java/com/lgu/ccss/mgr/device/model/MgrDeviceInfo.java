package com.lgu.ccss.mgr.device.model;

public class MgrDeviceInfo {	
	
	private int deviceIndex;
	private String mainUseYn;
	private String membId;
	private String connDeviceId;
	private String deviceCtn;
	private String deviceNm;
	private String deviceModel;
	private String transToken;
	private String serial;
	private String joinDt;
	private String useStatus;
	private String clovaAccessToken;
	private String nid;
	
	private String clovaClientId;
	private String clovaClientSecret;
	
	public int getDeviceIndex() {
		return deviceIndex;
	}
	public void setDeviceIndex(int deviceIndex) {
		this.deviceIndex = deviceIndex;
	}
	
	public String getMainUseYn() {
		return mainUseYn;
	}
	public void setMainUseYn(String mainUseYn) {
		this.mainUseYn = mainUseYn;
	}
	public String getConnDeviceId() {
		return connDeviceId;
	}
	public String getMembId() {
		return membId;
	}
	public void setMembId(String membId) {
		this.membId = membId;
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
	public String getDeviceNm() {
		return deviceNm;
	}
	public void setDeviceNm(String deviceNm) {
		this.deviceNm = deviceNm;
	}
	public String getDeviceModel() {
		return deviceModel;
	}
	public void setDeviceModel(String deviceModel) {
		this.deviceModel = deviceModel;
	}
	public String getTransToken() {
		return transToken;
	}
	public void setTransToken(String transToken) {
		this.transToken = transToken;
	}
	public String getSerial() {
		return serial;
	}
	public void setSerial(String serial) {
		this.serial = serial;
	}
	public String getJoinDt() {
		return joinDt;
	}
	public void setJoinDt(String joinDt) {
		this.joinDt = joinDt;
	}
	public String getUseStatus() {
		return useStatus;
	}
	public void setUseStatus(String useStatus) {
		this.useStatus = useStatus;
	}
	
	public String getClovaAccessToken() {
		return clovaAccessToken;
	}
	public void setClovaAccessToken(String clovaAccessToken) {
		this.clovaAccessToken = clovaAccessToken;
	}
	public String getNid() {
		return nid;
	}
	public void setNid(String nid) {
		this.nid = nid;
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
		return "MgrDeviceInfo [deviceIndex=" + deviceIndex + ", mainUseYn=" + mainUseYn + ", membId=" + membId
				+ ", connDeviceId=" + connDeviceId + ", deviceCtn=" + deviceCtn + ", deviceNm=" + deviceNm
				+ ", deviceModel=" + deviceModel + ", transToken=" + transToken + ", serial=" + serial + ", joinDt="
				+ joinDt + ", useStatus=" + useStatus + ", clovaAccessToken=" + clovaAccessToken + ", nid=" + nid
				+ ", clovaClientId=" + clovaClientId + ", clovaClientSecret=" + clovaClientSecret + "]";
	}
	
	
}
