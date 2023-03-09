package com.lgu.ccss.api.carddeck.model;

public class CarddeckEventData {
	public static final String CONTENT_TYPE_URL = "URL";
	public static final String CONTENT_TYPE_TEXT ="TEXT";
	public static final String CONTENT_TYPE_HTML ="HTML";
	
	private String index;
	private String contentType;
	private String content;
	private String startDt;
	private String endDt;
	private String appId;
	private String cardType;
	
	public String getIndex() {
		return index;
	}
	public void setIndex(String index) {
		this.index = index;
	}
	public String getContentType() {
		return contentType;
	}
	public void setContentType(String contentType) {
		this.contentType = contentType;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getStartDt() {
		return startDt;
	}
	public void setStartDt(String startDt) {
		this.startDt = startDt;
	}
	public String getEndDt() {
		return endDt;
	}
	public void setEndDt(String endDt) {
		this.endDt = endDt;
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
		return "CarddeckEventData [index=" + index + ", contentType=" + contentType + ", content=" + content
				+ ", startDt=" + startDt + ", endDt=" + endDt + ", appId=" + appId+ ", cardType=" + cardType + "]";
	}
}
