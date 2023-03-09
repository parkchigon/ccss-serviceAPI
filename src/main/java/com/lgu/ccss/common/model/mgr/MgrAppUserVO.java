package com.lgu.ccss.common.model.mgr;

public class MgrAppUserVO {
	private String mgrappId;
	private String mgrappLoginId;
	private String mgrappLoginDt;
	private String deviceType;
	private String pushId;
	private String lastCertDt;
	private String mgrConStatus;
	private String mgrappStatus;
	private String regDt;
	private String updDt;
	private String membId;
	private String mgrappVer;
	private String verType;
	private String regId;
	private String updId;
	private String jsonSetInfo;
	//private JsonSetInfo jsonSetInfo;
	private String deviceNm;
	//private String mainUseYn;
	private int loginFailCnt;
	private String lastLoginFailDt;
	private String uuid;
	private String randomKey;
	private String userNm;
	
	private String[] membIdArr;
	private String osType;
	private String pushKeyRegYn;
	//private String appVer;
	
	private String loginType;
	
	private String[] arrMgrappId;
	private String[] arrMgrappLoginId;
	
	public String getMgrappId() {
		return mgrappId;
	}
	public void setMgrappId(String mgrappId) {
		this.mgrappId = mgrappId;
	}
	public String getMgrappLoginDt() {
		return mgrappLoginDt;
	}
	public String getMgrappLoginId() {
		return mgrappLoginId;
	}
	public void setMgrappLoginId(String mgrappLoginId) {
		this.mgrappLoginId = mgrappLoginId;
	}
	public void setMgrappLoginDt(String mgrappLoginDt) {
		this.mgrappLoginDt = mgrappLoginDt;
	}
	public String getDeviceType() {
		return deviceType;
	}
	public void setDeviceType(String deviceType) {
		this.deviceType = deviceType;
	}
	public String getPushId() {
		return pushId;
	}
	public void setPushId(String pushId) {
		this.pushId = pushId;
	}
	public String getLastCertDt() {
		return lastCertDt;
	}
	public void setLastCertDt(String lastCertDt) {
		this.lastCertDt = lastCertDt;
	}
	public String getMgrConStatus() {
		return mgrConStatus;
	}
	public void setMgrConStatus(String mgrConStatus) {
		this.mgrConStatus = mgrConStatus;
	}
	public String getMgrappStatus() {
		return mgrappStatus;
	}
	public void setMgrappStatus(String mgrappStatus) {
		this.mgrappStatus = mgrappStatus;
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
	public String getMembId() {
		return membId;
	}
	public void setMembId(String membId) {
		this.membId = membId;
	}
	public String getMgrappVer() {
		return mgrappVer;
	}
	public void setMgrappVer(String mgrappVer) {
		this.mgrappVer = mgrappVer;
	}
	public String getVerType() {
		return verType;
	}
	public void setVerType(String verType) {
		this.verType = verType;
	}
	public String getRegId() {
		return regId;
	}
	public void setRegId(String regId) {
		this.regId = regId;
	}
	public String getUpdId() {
		return updId;
	}
	public void setUpdId(String updId) {
		this.updId = updId;
	}
	
	public String getDeviceNm() {
		return deviceNm;
	}
	public String getJsonSetInfo() {
		return jsonSetInfo;
	}
	public void setJsonSetInfo(String jsonSetInfo) {
		this.jsonSetInfo = jsonSetInfo;
	}
	public void setDeviceNm(String deviceNm) {
		this.deviceNm = deviceNm;
	}
	/*public String getMainUseYn() {
		return mainUseYn;
	}
	public void setMainUseYn(String mainUseYn) {
		this.mainUseYn = mainUseYn;
	}*/
	
	public int getLoginFailCnt() {
		return loginFailCnt;
	}
	public void setLoginFailCnt(int loginFailCnt) {
		this.loginFailCnt = loginFailCnt;
	}
	public String getLastLoginFailDt() {
		return lastLoginFailDt;
	}
	public void setLastLoginFailDt(String lastLoginFailDt) {
		this.lastLoginFailDt = lastLoginFailDt;
	}
	public String getUuid() {
		return uuid;
	}
	public void setUuid(String uuid) {
		this.uuid = uuid;
	}
	public String getRandomKey() {
		return randomKey;
	}
	public void setRandomKey(String randomKey) {
		this.randomKey = randomKey;
	}
	public String getUserNm() {
		return userNm;
	}
	public void setUserNm(String userNm) {
		this.userNm = userNm;
	}
	public String[] getMembIdArr() {
		return membIdArr;
	}
	public void setMembIdArr(String[] membIdArr) {
		this.membIdArr = membIdArr;
	}
	public String getOsType() {
		return osType;
	}
	public void setOsType(String osType) {
		this.osType = osType;
	}
	public String getPushKeyRegYn() {
		return pushKeyRegYn;
	}
	public void setPushKeyRegYn(String pushKeyRegYn) {
		this.pushKeyRegYn = pushKeyRegYn;
	}
	//public String getAppVer() {
	//	return appVer;
	//}
	//public void setAppVer(String appVer) {
	//	this.appVer = appVer;
	//}
	public String getLoginType() {
		return loginType;
	}
	public void setLoginType(String loginType) {
		this.loginType = loginType;
	}
	public String[] getArrMgrappId() {
		return arrMgrappId;
	}
	public void setArrMgrappId(String[] arrMgrappId) {
		this.arrMgrappId = arrMgrappId;
	}
	public String[] getArrMgrappLoginId() {
		return arrMgrappLoginId;
	}
	public void setArrMgrappLoginId(String[] arrMgrappLoginId) {
		this.arrMgrappLoginId = arrMgrappLoginId;
	}	
	
}
