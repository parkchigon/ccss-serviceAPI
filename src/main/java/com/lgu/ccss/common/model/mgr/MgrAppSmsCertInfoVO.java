package com.lgu.ccss.common.model.mgr;


public class MgrAppSmsCertInfoVO {
	
	private int smsAuthSeq;
	private String mgrappCtn;
	private String certNo;
	private String execScnCd;
	private String smsAuthExpDt;
	private String expiredStatus;
	private String regId="SYSTEM";
	private String regDt;
	private String updId="SYSTEM";
	private String updDt;
	

	public int getSmsAuthSeq() {
		return smsAuthSeq;
	}
	public void setSmsAuthSeq(int smsAuthSeq) {
		this.smsAuthSeq = smsAuthSeq;
	}
	public String getMgrappCtn() {
		return mgrappCtn;
	}
	public void setMgrappCtn(String mgrappCtn) {
		this.mgrappCtn = mgrappCtn;
	}
	public String getCertNo() {
		return certNo;
	}
	public void setCertNo(String certNo) {
		this.certNo = certNo;
	}
	public String getExecScnCd() {
		return execScnCd;
	}
	public void setExecScnCd(String execScnCd) {
		this.execScnCd = execScnCd;
	}
	public String getSmsAuthExpDt() {
		return smsAuthExpDt;
	}
	public void setSmsAuthExpDt(String smsAuthExpDt) {
		this.smsAuthExpDt = smsAuthExpDt;
	}
	public String getExpiredStatus() {
		return expiredStatus;
	}
	public void setExpiredStatus(String expiredStatus) {
		this.expiredStatus = expiredStatus;
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
	@Override
	public String toString() {
		return "MgrAppSmsCertInfoVO [smsAuthSeq=" + smsAuthSeq + ", mgrappCtn="
				+ mgrappCtn + ", certNo=" + certNo + ", execScnCd=" + execScnCd
				+ ", smsAuthExpDt=" + smsAuthExpDt + ", expiredStatus="
				+ expiredStatus + ", regId=" + regId + ", regDt=" + regDt
				+ ", updId=" + updId + ", updDt=" + updDt + "]";
	}
	
	
}
