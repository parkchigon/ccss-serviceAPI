package com.lgu.ccss.common.model;

public class EventCardVO {
	private String cardImgFileName;
	private String eventCardId;
	private String eventCardUrl;
	private String exposureStartDt;
	private String exposureEndDt;
	private String serviceCategory;
	private String regId;
	private String regDt;
	private String updId;
	private String updDt;
	private String appId;
	private String cardType;
	
	public String getCardImgFileName() {
		return cardImgFileName;
	}
	public void setCardImgFileName(String cardImgFileName) {
		this.cardImgFileName = cardImgFileName;
	}
	public String getEventCardId() {
		return eventCardId;
	}
	public void setEventCardId(String eventCardId) {
		this.eventCardId = eventCardId;
	}
	public String getEventCardUrl() {
		return eventCardUrl;
	}
	public void setEventCardUrl(String eventCardUrl) {
		this.eventCardUrl = eventCardUrl;
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
	
	public String getCardType() {
		return cardType;
	}
	public void setCardType(String cardType) {
		this.cardType = cardType;
	}
	@Override
	public String toString() {
		return "EventCardVO [cardImgFileName=" + cardImgFileName + ", eventCardId=" + eventCardId + ", eventCardUrl="
				+ eventCardUrl + ", exposureStartDt=" + exposureStartDt + ", exposureEndDt=" + exposureEndDt
				+ ", serviceCategory=" + serviceCategory + ", regId=" + regId + ", regDt=" + regDt + ", updId=" + updId
				+ ", updDt=" + updDt + "]";
	}
}
