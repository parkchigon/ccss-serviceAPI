package com.lgu.common.ai.coif.model;

public class WeatherRequestBodySlotJSON {
	private String latitude;
	private String longtitude;
	private String lAreaNm;
	private String mAreaNm;
	private String sAreaNm;
	private String reqType;
	private String poiName;
	
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
	public String getlAreaNm() {
		return lAreaNm;
	}
	public void setlAreaNm(String lAreaNm) {
		this.lAreaNm = lAreaNm;
	}
	public String getmAreaNm() {
		return mAreaNm;
	}
	public void setmAreaNm(String mAreaNm) {
		this.mAreaNm = mAreaNm;
	}
	public String getsAreaNm() {
		return sAreaNm;
	}
	public void setsAreaNm(String sAreaNm) {
		this.sAreaNm = sAreaNm;
	}
	public String getReqType() {
		return reqType;
	}
	public void setReqType(String reqType) {
		this.reqType = reqType;
	}
	public String getPoiName() {
		return poiName;
	}
	public void setPoiName(String poiName) {
		this.poiName = poiName;
	}
	
	@Override
	public String toString() {
		return "WeatherRequestBodySlotJSON [latitude=" + latitude + ", longtitude=" + longtitude + ", lAreaNm="
				+ lAreaNm + ", mAreaNm=" + mAreaNm + ", sAreaNm=" + sAreaNm + ", reqType=" + reqType + ", poiName="
				+ poiName + "]";
	}
}
