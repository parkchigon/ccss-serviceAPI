package com.lgu.ccss.common.model.mgr;

public class MgrAppSessVO {
	private String mgrappId;
	private String mgrappSessionId;
	private String deviceType;
	private String mgrappLoginDt;
	private String sessionExpDt;
	private String connIp;
	private String randomKey;
	private String membId;
	
	private String mgrappLoginId;
	private String osType;
	private String uuid;
	
	
	public String getMgrappId() {
		return mgrappId;
	}
	public void setMgrappId(String mgrappId) {
		this.mgrappId = mgrappId;
	}
	public String getMgrappSessionId() {
		return mgrappSessionId;
	}
	public void setMgrappSessionId(String mgrappSessionId) {
		this.mgrappSessionId = mgrappSessionId;
	}
	public String getDeviceType() {
		return deviceType;
	}
	public void setDeviceType(String deviceType) {
		this.deviceType = deviceType;
	}
	
	public String getMgrappLoginDt() {
		return mgrappLoginDt;
	}
	public void setMgrappLoginDt(String mgrappLoginDt) {
		this.mgrappLoginDt = mgrappLoginDt;
	}
	public String getSessionExpDt() {
		return sessionExpDt;
	}
	public void setSessionExpDt(String sessionExpDt) {
		this.sessionExpDt = sessionExpDt;
	}
	public String getConnIp() {
		return connIp;
	}
	public void setConnIp(String connIp) {
		this.connIp = connIp;
	}
	public String getRandomKey() {
		return randomKey;
	}
	public void setRandomKey(String randomKey) {
		this.randomKey = randomKey;
	}
	public String getMembId() {
		return membId;
	}
	public void setMembId(String membId) {
		this.membId = membId;
	}

	public String getOsType() {
		return osType;
	}
	public String getMgrappLoginId() {
		return mgrappLoginId;
	}
	public void setMgrappLoginId(String mgrappLoginId) {
		this.mgrappLoginId = mgrappLoginId;
	}
	public void setOsType(String osType) {
		this.osType = osType;
	}
	public String getUuid() {
		return uuid;
	}
	public void setUuid(String uuid) {
		this.uuid = uuid;
	}
	@Override
	public String toString() {
		return "MgrAppSessVO [mgrappId=" + mgrappId + ", mgrappSessionId="
				+ mgrappSessionId + ", deviceType=" + deviceType
				+ ", mgrappLoginDt=" + mgrappLoginDt + ", sessionExpDt="
				+ sessionExpDt + ", connIp=" + connIp + ", randomKey="
				+ randomKey + ", membId=" + membId + ", mgrappLoginId="
				+ mgrappLoginId + ", osType=" + osType + ", uuid=" + uuid + "]";
	}
	
	
}
