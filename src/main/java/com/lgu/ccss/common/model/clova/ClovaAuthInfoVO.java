package com.lgu.ccss.common.model.clova;

public class ClovaAuthInfoVO {
	private String membId;
	private String clovaToken;
	private String refreshToken;
	private String expiredTime;
	private String tokenType;
	private String LoginStatus;
	private String mgrappId;
	private String loginType;
	
	private String nid;
	private String nAccessToken;
	
	private String regId="SYSTEM";
	private String regDt;
	private String updId="SYSTEM";
	private String updDt;
	//20191127 reg_dt 업데이트를 위해 삽입 
	private String clovaUpdate;
	public String getMembId() {
		return membId;
	}
	public void setMembId(String membId) {
		this.membId = membId;
	}
	public String getClovaToken() {
		return clovaToken;
	}
	public void setClovaToken(String clovaToken) {
		this.clovaToken = clovaToken;
	}
	public String getRefreshToken() {
		return refreshToken;
	}
	public void setRefreshToken(String refreshToken) {
		this.refreshToken = refreshToken;
	}
	public String getExpiredTime() {
		return expiredTime;
	}
	public void setExpiredTime(String expiredTime) {
		this.expiredTime = expiredTime;
	}
	public String getTokenType() {
		return tokenType;
	}
	public void setTokenType(String tokenType) {
		this.tokenType = tokenType;
	}
	public String getLoginStatus() {
		return LoginStatus;
	}
	public void setLoginStatus(String loginStatus) {
		LoginStatus = loginStatus;
	}
	public String getMgrappId() {
		return mgrappId;
	}
	public void setMgrappId(String mgrappId) {
		this.mgrappId = mgrappId;
	}
	public String getLoginType() {
		return loginType;
	}
	public void setLoginType(String loginType) {
		this.loginType = loginType;
	}
	public String getNid() {
		return nid;
	}
	public void setNid(String nid) {
		this.nid = nid;
	}
	public String getnAccessToken() {
		return nAccessToken;
	}
	public void setnAccessToken(String nAccessToken) {
		this.nAccessToken = nAccessToken;
	}
	public String getRegId() {
		return regId;
	}
	public void setRegId(String regId) {
		this.regId = regId;
	}
	public String getRegDt() {
		return regDt;
	}
	public void setRegDt(String regDt) {
		this.regDt = regDt;
	}
	public String getUpdId() {
		return updId;
	}
	public void setUpdId(String updId) {
		this.updId = updId;
	}
	public String getUpdDt() {
		return updDt;
	}
	public void setUpdDt(String updDt) {
		this.updDt = updDt;
	}
	
	
	public String getClovaUpdate() {
		return clovaUpdate;
	}
	public void setClovaUpdate(String clovaUpdate) {
		this.clovaUpdate = clovaUpdate;
	}
	@Override
	public String toString() {
		return "ClovaAuthInfoVO [membId=" + membId + ", clovaToken="
				+ clovaToken + ", refreshToken=" + refreshToken
				+ ", expiredTime=" + expiredTime + ", tokenType=" + tokenType
				+ ", LoginStatus=" + LoginStatus + ", mgrappId=" + mgrappId
				+ ", loginType=" + loginType + ", nid=" + nid
				+ ", nAccessToken=" + nAccessToken + ", regId=" + regId
				+ ", regDt=" + regDt + ", updId=" + updId + ", updDt=" + updDt + ",clovaUpdate =" + clovaUpdate
				+ "]";
	}
	

	
	
	
}
