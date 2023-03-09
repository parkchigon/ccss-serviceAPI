package com.lgu.ccss.mgr.parking.model;

public class ResultParkingLocationJSON {	
	
	private String lonx;
	private String laty;
	private String address;
	private String parkingDateTime;
	private String distance;
	
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
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getParkingDateTime() {
		return parkingDateTime;
	}
	public void setParkingDateTime(String parkingDateTime) {
		this.parkingDateTime = parkingDateTime;
	}
	public String getDistance() {
		return distance;
	}
	public void setDistance(String distance) {
		this.distance = distance;
	}
	
	@Override
	public String toString() {
		return "ResultParkingLocationJSON [lonx=" + lonx + ", laty=" + laty + ", address=" + address
				+ ", parkingDateTime=" + parkingDateTime + ", distance=" + distance + "]";
	}
}
