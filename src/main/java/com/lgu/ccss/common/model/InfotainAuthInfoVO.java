package com.lgu.ccss.common.model;

import java.util.Date;

public class InfotainAuthInfoVO {
	public static final String USE_Y = "Y";
	public static final String USE_N = "N";
	
	public static final String LOGIN_STATUS ="loginStatus";
	public static final String SELF_LOGIN_STATUS ="00";
	public static final String OTHERS_LOGIN_STATUS ="01";
	
	private int authInfoSeq;
	private String membId;
	private String serviceId;
	private int tokenSeq;
	private String tokenNm;
	private String tokenValue;
	private String useYn;
	private String regId;
	private Date regDt;
	private String updId;
	private Date updDt;
	private String mgrappId;
	
	public int getAuthInfoSeq() {
		return authInfoSeq;
	}
	public void setAuthInfoSeq(int authInfoSeq) {
		this.authInfoSeq = authInfoSeq;
	}
	public String getMembId() {
		return membId;
	}
	public void setMembId(String membId) {
		this.membId = membId;
	}
	public String getServiceId() {
		return serviceId;
	}
	public void setServiceId(String serviceId) {
		this.serviceId = serviceId;
	}
	public int getTokenSeq() {
		return tokenSeq;
	}
	public void setTokenSeq(int tokenSeq) {
		this.tokenSeq = tokenSeq;
	}
	public String getTokenNm() {
		return tokenNm;
	}
	public void setTokenNm(String tokenNm) {
		this.tokenNm = tokenNm;
	}
	public String getTokenValue() {
		return tokenValue;
	}
	public void setTokenValue(String tokenValue) {
		this.tokenValue = tokenValue;
	}
	public String getUseYn() {
		return useYn;
	}
	public void setUseYn(String useYn) {
		this.useYn = useYn;
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
	
	public String getMgrappId() {
		return mgrappId;
	}
	public void setMgrappId(String mgrappId) {
		this.mgrappId = mgrappId;
	}
	@Override
	public String toString() {
		return "InfotainAuthInfoVO [authInfoSeq=" + authInfoSeq + ", membId="
				+ membId + ", serviceId=" + serviceId + ", tokenSeq="
				+ tokenSeq + ", tokenNm=" + tokenNm + ", tokenValue="
				+ tokenValue + ", useYn=" + useYn + ", regId=" + regId
				+ ", regDt=" + regDt + ", updId=" + updId + ", updDt=" + updDt
				+ ", mgrappId=" + mgrappId + "]";
	}
	
}
