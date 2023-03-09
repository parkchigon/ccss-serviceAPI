package com.lgu.ccss.mgr.send2car.model.history;

public class DestListJSON {
	
	private String targetIndex;
	private String sendToCarId;
	private String arrivHopeTime;
	private Send2CarLocationJSON location;

	public String getTargetIndex() {
		return targetIndex;
	}
	public void setTargetIndex(String targetIndex) {
		this.targetIndex = targetIndex;
	}
	
	public String getSendToCarId() {
		return sendToCarId;
	}
	public void setSendToCarId(String sendToCarId) {
		this.sendToCarId = sendToCarId;
	}
	public String getArrivHopeTime() {
		return arrivHopeTime;
	}
	public void setArrivHopeTime(String arrivHopeTime) {
		this.arrivHopeTime = arrivHopeTime;
	}
	public Send2CarLocationJSON getLocation() {
		return location;
	}
	public void setLocation(Send2CarLocationJSON location) {
		this.location = location;
	}
	
	
	
}
