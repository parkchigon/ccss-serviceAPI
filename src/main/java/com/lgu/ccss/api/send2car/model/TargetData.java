package com.lgu.ccss.api.send2car.model;

import com.fasterxml.jackson.annotation.JsonInclude;

public class TargetData {
	private String seq;
	private String send2CarId;
	private String name;
	private String address;
	
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private String latitude;
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private String longtitude;
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private String rlatitude;
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private String rlongtitude;
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private int poiid;
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private String roadname;
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private String roadjibun;
	
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private String date;
	
	public String getSeq() {
		return seq;
	}
	public void setSeq(String seq) {
		this.seq = seq;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSend2CarId() {
		return send2CarId;
	}
	public void setSend2CarId(String send2CarId) {
		this.send2CarId = send2CarId;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getLatitude() {
		return latitude;
	}
	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}
	public String getLongtitude() {
		return longtitude;
	}
	public void setLongtitude(String longtitude) {
		this.longtitude = longtitude;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	
	public String getRlatitude() {
		return rlatitude;
	}
	public void setRlatitude(String rlatitude) {
		this.rlatitude = rlatitude;
	}
	public String getRlongtitude() {
		return rlongtitude;
	}
	public void setRlongtitude(String rlongtitude) {
		this.rlongtitude = rlongtitude;
	}
	
	public int getPoiid() {
		return poiid;
	}
	public void setPoiid(int poiid) {
		this.poiid = poiid;
	}
	public String getRoadname() {
		return roadname;
	}
	public void setRoadname(String roadname) {
		this.roadname = roadname;
	}
	public String getRoadjibun() {
		return roadjibun;
	}
	public void setRoadjibun(String roadjibun) {
		this.roadjibun = roadjibun;
	}
	@Override
	public String toString() {
		return "TargetData [seq=" + seq + ", send2CarId=" + send2CarId
				+ ", name=" + name + ", address=" + address + ", latitude="
				+ latitude + ", longtitude=" + longtitude + ", rlatitude="
				+ rlatitude + ", rlongtitude=" + rlongtitude + ", poiid="
				+ poiid + ", roadname=" + roadname + ", roadjibun=" + roadjibun
				+ ", date=" + date + "]";
	}
	
}
