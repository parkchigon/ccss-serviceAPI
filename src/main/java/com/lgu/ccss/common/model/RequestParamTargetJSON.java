package com.lgu.ccss.common.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class RequestParamTargetJSON{
	
	private String address;
	private String lonx;
	private String laty;
	private String name;
	private String roadjibun;
	
	@JsonIgnore
	private int poiid;
	
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getLonx() {
		return lonx;
	}
	public void setLonx(String lonx) {
		this.lonx = lonx;
	}
	public String getLaty() {
		return laty;
	}
	public void setLaty(String laty) {
		this.laty = laty;
	}
	public int getPoiid() {
		return poiid;
	}
	public void setPoiid(int poiid) {
		this.poiid = poiid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getRoadjibun() {
		return roadjibun;
	}
	public void setRoadjibun(String roadjibun) {
		this.roadjibun = roadjibun;
	}
	
}