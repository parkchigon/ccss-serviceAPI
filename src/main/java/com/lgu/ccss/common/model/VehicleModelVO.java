package com.lgu.ccss.common.model;

import java.util.Date;

public class VehicleModelVO {
	private String vehicleModelId;
	private String oemId;
	private String vehicleModelNm;
	private String vehicleModelDesc;
	
	private String regId;
	private Date regDt;
	private String updId;
	private Date updDt;
	
	public String getVehicleModelId() {
		return vehicleModelId;
	}
	public void setVehicleModelId(String vehicleModelId) {
		this.vehicleModelId = vehicleModelId;
	}
	public String getOemId() {
		return oemId;
	}
	public void setOemId(String oemId) {
		this.oemId = oemId;
	}
	public String getVehicleModelNm() {
		return vehicleModelNm;
	}
	public void setVehicleModelNm(String vehicleModelNm) {
		this.vehicleModelNm = vehicleModelNm;
	}
	public String getVehicleModelDesc() {
		return vehicleModelDesc;
	}
	public void setVehicleModelDesc(String vehicleModelDesc) {
		this.vehicleModelDesc = vehicleModelDesc;
	}
	public String getRegId() {
		return regId;
	}
	public void setRegId(String regId) {
		this.regId = regId;
	}
	public Date getRegDt() {
		return regDt;
	}
	public void setRegDt(Date regDt) {
		this.regDt = regDt;
	}
	public String getUpdId() {
		return updId;
	}
	public void setUpdId(String updId) {
		this.updId = updId;
	}
	public Date getUpdDt() {
		return updDt;
	}
	public void setUpdDt(Date updDt) {
		this.updDt = updDt;
	}
	
	@Override
	public String toString() {
		return "VehicleModelVO [vehicleModelId=" + vehicleModelId + ", oemId=" + oemId + ", vehicleModelNm="
				+ vehicleModelNm + ", vehicleModelDesc=" + vehicleModelDesc + ", regId=" + regId + ", regDt=" + regDt
				+ ", updId=" + updId + ", updDt=" + updDt + "]";
	}
}
