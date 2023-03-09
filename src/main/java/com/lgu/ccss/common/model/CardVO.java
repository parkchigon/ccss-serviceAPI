package com.lgu.ccss.common.model;

public class CardVO {
	private String cardId;
	private String cardNm;
	private Integer cardSortNum;
	private String exposureYn;
	private String fixYn;
	private String serviceCategory;
	private String regId;
	private String regDt;
	private String updId;
	private String updDt;
	private String appId;
	
	public String getCardId() {
		return cardId;
	}
	public void setCardId(String cardId) {
		this.cardId = cardId;
	}
	public String getCardNm() {
		return cardNm;
	}
	public void setCardNm(String cardNm) {
		this.cardNm = cardNm;
	}
	public Integer getCardSortNum() {
		return cardSortNum;
	}
	public void setCardSortNum(Integer cardSortNum) {
		this.cardSortNum = cardSortNum;
	}
	public String getExposureYn() {
		return exposureYn;
	}
	public void setExposureYn(String exposureYn) {
		this.exposureYn = exposureYn;
	}
	public String getFixYn() {
		return fixYn;
	}
	public void setFixYn(String fixYn) {
		this.fixYn = fixYn;
	}
	public String getServiceCategory() {
		return serviceCategory;
	}
	public void setServiceCategory(String serviceCategory) {
		this.serviceCategory = serviceCategory;
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
	public String getAppId() {
		return appId;
	}
	public void setAppId(String appId) {
		this.appId = appId;
	}
	@Override
	public String toString() {
		return "CardVO [cardId=" + cardId + ", cardNm=" + cardNm + ", cardSortNum=" + cardSortNum + ", exposureYn="
				+ exposureYn + ", fixYn=" + fixYn + ", serviceCategory=" + serviceCategory + ", regId=" + regId
				+ ", regDt=" + regDt + ", updId=" + updId + ", updDt=" + updDt + "]";
	}
}
