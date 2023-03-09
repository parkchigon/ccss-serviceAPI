package com.lgu.ccss.common.model;

import java.util.Date;

public class TermsVO {
	private String termsNo;
	private String termsVer;
	private String termsTitle;
	private String termsCont;
	private String termsDiv;
	private String exposureYn;
	private String exposureStartDt;
	private String exposureEndDt;
	
	private String regId;
	private Date regDt;
	private String updId;
	private Date updDt;
	
	public String getTermsNo() {
		return termsNo;
	}
	public void setTermsNo(String termsNo) {
		this.termsNo = termsNo;
	}
	public String getTermsVer() {
		return termsVer;
	}
	public void setTermsVer(String termsVer) {
		this.termsVer = termsVer;
	}
	public String getTermsTitle() {
		return termsTitle;
	}
	public void setTermsTitle(String termsTitle) {
		this.termsTitle = termsTitle;
	}
	public String getTermsCont() {
		return termsCont;
	}
	public void setTermsCont(String termsCont) {
		this.termsCont = termsCont;
	}
	public String getTermsDiv() {
		return termsDiv;
	}
	public void setTermsDiv(String termsDiv) {
		this.termsDiv = termsDiv;
	}
	public String getExposureYn() {
		return exposureYn;
	}
	public void setExposureYn(String exposureYn) {
		this.exposureYn = exposureYn;
	}
	public String getExposureStartDt() {
		return exposureStartDt;
	}
	public void setExposureStartDt(String exposureStartDt) {
		this.exposureStartDt = exposureStartDt;
	}
	public String getExposureEndDt() {
		return exposureEndDt;
	}
	public void setExposureEndDt(String exposureEndDt) {
		this.exposureEndDt = exposureEndDt;
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
		return "TermsVO [termsNo=" + termsNo + ", termsVer=" + termsVer + ", termsTitle=" + termsTitle + ", termsCont="
				+ termsCont + ", termsDiv=" + termsDiv + ", exposureYn=" + exposureYn + ", exposureStartDt="
				+ exposureStartDt + ", exposureEndDt=" + exposureEndDt + ", regId=" + regId + ", regDt=" + regDt
				+ ", updId=" + updId + ", updDt=" + updDt + "]";
	}
}
