package com.lgu.ccss.api.location.model;

public class LocationData {
	
	public final static String FIELD_LATITUDE = "latitude";
	public final static String FIELD_LONGTITUDE = "longtitude";
	public final static String FIELD_DATE = "date";
	
	private String latitude;
	private String longtitude;
	private String date;
	
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
	
	@Override
	public String toString() {
		return "LocationData [latitude=" + latitude + ", longtitude=" + longtitude + ", date=" + date + "]";
	}
}
