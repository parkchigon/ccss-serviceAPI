package com.lgu.ccss.common.model;

import java.util.Date;

public class MembAgrVO {
	public static final String AGR_Y = "Y";
	public static final String AGR_N = "N";
	
	public static final String RE_AGR_Y = "Y";
	public static final String RE_AGR_N = "N";
	
	public static final String COMM_AGR_Y = "Y";
	public static final String COMM_AGR_N = "N";
	
	private String membId;
	private String termsNo;
	private String termsAuthNo;
	private String agrYn;
	private String reAgrYn;
	private String commAgrYn;
	private String validStartDt;
	private String validEndDt;
	private String regId;
	private Date regDt;
	private String updId;
	private Date updDt;
	
	public String getMembId() {
		return membId;
	}
	public void setMembId(String membId) {
		this.membId = membId;
	}
	public String getTermsNo() {
		return termsNo;
	}
	public void setTermsNo(String termsNo) {
		this.termsNo = termsNo;
	}
	public String getTermsAuthNo() {
		return termsAuthNo;
	}
	public void setTermsAuthNo(String termsAuthNo) {
		this.termsAuthNo = termsAuthNo;
	}
	public String getAgrYn() {
		return agrYn;
	}
	public void setAgrYn(String agrYn) {
		this.agrYn = agrYn;
	}
	public String getReAgrYn() {
		return reAgrYn;
	}
	public void setReAgrYn(String reAgrYn) {
		this.reAgrYn = reAgrYn;
	}
	public String getCommAgrYn() {
		return commAgrYn;
	}
	public void setCommAgrYn(String commAgrYn) {
		this.commAgrYn = commAgrYn;
	}
	public String getValidStartDt() {
		return validStartDt;
	}
	public void setValidStartDt(String validStartDt) {
		this.validStartDt = validStartDt;
	}
	public String getValidEndDt() {
		return validEndDt;
	}
	public void setValidEndDt(String validEndDt) {
		this.validEndDt = validEndDt;
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
	
	@Override
	public String toString() {
		return "MembAgrVO [membId=" + membId + ", termsNo=" + termsNo + ", termsAuthNo="
				+ termsAuthNo + ", agrYn=" + agrYn + ", reAgrYn=" + reAgrYn + ", commAgrYn=" + commAgrYn
				+ ", validStartDt=" + validStartDt + ", validEndDt=" + validEndDt + ", regId=" + regId + ", regDt="
				+ regDt + ", updId=" + updId + ", updDt=" + updDt + "]";
	}
}
