package com.lgu.ccss.api.carddeck.model;

public class CarddeckNoticeData {
	private String fwVer;
	private String title;
	private String contentType;
	private String content;
	
	public String getFwVer() {
		return fwVer;
	}
	public void setFwVer(String fwVer) {
		this.fwVer = fwVer;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
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
	
	@Override
	public String toString() {
		return "CarddeckNoticeData [fwVer=" + fwVer + ", title=" + title + ", contentType=" + contentType + ", content="
				+ content + "]";
	}
}
