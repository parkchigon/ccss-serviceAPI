package com.lgu.common.map.model.findStatRoute;

import java.util.List;

public class ReqFindStatRouthSearchAmJSON {
	private String searchType;
	private String mrVersion;
	private int endLocCount;
	private String timeStamp;
	
	private LocDataAmJSON statisticStartLoc;
	private List<LocDataAmJSON> statisticEndLocList;
	public String getSearchType() {
		return searchType;
	}
	public void setSearchType(String searchType) {
		this.searchType = searchType;
	}
	public String getMrVersion() {
		return mrVersion;
	}
	public void setMrVersion(String mrVersion) {
		this.mrVersion = mrVersion;
	}
	public int getEndLocCount() {
		return endLocCount;
	}
	public void setEndLocCount(int endLocCount) {
		this.endLocCount = endLocCount;
	}
	public String getTimeStamp() {
		return timeStamp;
	}
	public void setTimeStamp(String timeStamp) {
		this.timeStamp = timeStamp;
	}
	public LocDataAmJSON getStatisticStartLoc() {
		return statisticStartLoc;
	}
	public void setStatisticStartLoc(LocDataAmJSON statisticStartLoc) {
		this.statisticStartLoc = statisticStartLoc;
	}
	public List<LocDataAmJSON> getStatisticEndLocList() {
		return statisticEndLocList;
	}
	public void setStatisticEndLocList(List<LocDataAmJSON> statisticEndLocList) {
		this.statisticEndLocList = statisticEndLocList;
	}
	@Override
	public String toString() {
		return "ReqFindStatRouthSearchAmJSON [searchType=" + searchType + ", mrVersion=" + mrVersion + ", endLocCount="
				+ endLocCount + ", timeStamp=" + timeStamp + ", statisticStartLoc=" + statisticStartLoc
				+ ", statisticEndLocList=" + statisticEndLocList + "]";
	}

}
