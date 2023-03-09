package com.lgu.ccss.mgr.auth.model;

public class ResultMgrAuthVersionJSON {	
	private String curVersion;
	private String forcedUpdYn;
	private String marketUrl;
	private String description;
	public String getCurVersion() {
		return curVersion;
	}
	public void setCurVersion(String curVersion) {
		this.curVersion = curVersion;
	}
	public String getForcedUpdYn() {
		return forcedUpdYn;
	}
	public void setForcedUpdYn(String forcedUpdYn) {
		this.forcedUpdYn = forcedUpdYn;
	}
	public String getMarketUrl() {
		return marketUrl;
	}
	public void setMarketUrl(String marketUrl) {
		this.marketUrl = marketUrl;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	@Override
	public String toString() {
		return "ResultAuthVersionJSON [curVersion=" + curVersion
				+ ", forcedUpdYn=" + forcedUpdYn + ", marketUrl=" + marketUrl
				+ ", description=" + description + "]";
	}
	
	
	
	
}
