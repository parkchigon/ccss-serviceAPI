package com.lgu.ccss.common.model;

import java.util.Date;

public class MembCommAgrVO {
	public static final String AGR_Y = "Y";
	public static final String AGR_N = "N";
	
	private Integer membCommAgrNo;
	private String deviceCtn;
	private String uiccId;
	private String termsNo;
	private String termsAuthNo;
	private String agrYn;
	private Date validStartDt;
	private Date validEndDt;
	private String itemSn;
	private String usimModelNm;
	private String naviSn;
	private String regId;
	private Date regDt;
	private String updId;
	private Date updDt;
	
	public Integer getMembCommAgrNo() {
		return membCommAgrNo;
	}
	public void setMembCommAgrNo(Integer membCommAgrNo) {
		this.membCommAgrNo = membCommAgrNo;
	}
	public String getDeviceCtn() {
		return deviceCtn;
	}
	public void setDeviceCtn(String deviceCtn) {
		this.deviceCtn = deviceCtn;
	}
	public String getUiccId() {
		return uiccId;
	}
	public void setUiccId(String uiccId) {
		this.uiccId = uiccId;
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
	public Date getValidStartDt() {
		return validStartDt;
	}
	public void setValidStartDt(Date validStartDt) {
		this.validStartDt = validStartDt;
	}
	public Date getValidEndDt() {
		return validEndDt;
	}
	public void setValidEndDt(Date validEndDt) {
		this.validEndDt = validEndDt;
	}
	public String getItemSn() {
		return itemSn;
	}
	public void setItemSn(String itemSn) {
		this.itemSn = itemSn;
	}
	public String getUsimModelNm() {
		return usimModelNm;
	}
	public void setUsimModelNm(String usimModelNm) {
		this.usimModelNm = usimModelNm;
	}
	public String getNaviSn() {
		return naviSn;
	}
	public void setNaviSn(String naviSn) {
		this.naviSn = naviSn;
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
		return "MembCommAgrVO [membCommAgrNo=" + membCommAgrNo + ", deviceCtn=" + deviceCtn + ", uiccId=" + uiccId
				+ ", termsNo=" + termsNo + ", termsAuthNo=" + termsAuthNo + ", agrYn=" + agrYn + ", validStartDt="
				+ validStartDt + ", validEndDt=" + validEndDt + ", itemSn=" + itemSn + ", usimModelNm=" + usimModelNm
				+ ", naviSn=" + naviSn + ", regId=" + regId + ", regDt=" + regDt + ", updId=" + updId + ", updDt="
				+ updDt + "]";
	}
}
