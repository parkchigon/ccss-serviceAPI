package com.lgu.ccss.api.carddeck.model;

public class CarddeckListData {
	private String index;
	private String name;
	private String fixYn;
	private String appId;
	
	public String getIndex() {
		return index;
	}
	public void setIndex(String index) {
		this.index = index;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public String getFixYn() {
		return fixYn;
	}
	public void setFixYn(String fixYn) {
		this.fixYn = fixYn;
	}
	public String getAppId() {
		return appId;
	}
	public void setAppId(String appId) {
		this.appId = appId;
	}
	@Override
	public String toString() {
		return "CarddeckListData [index=" + index + ", name=" + name + ", fixYn=" + fixYn + ", appId=" + appId + "]";
	}
}
