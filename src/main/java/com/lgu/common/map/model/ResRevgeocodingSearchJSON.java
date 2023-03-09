package com.lgu.common.map.model;

import com.lgu.common.map.model.poi.AdmDataJSON;


public class ResRevgeocodingSearchJSON extends ResErrorJSON{	
	
	private AdmDataJSON adm;
	
	private String deviceType;

	public AdmDataJSON getAdm() {
		return adm;
	}

	public void setAdm(AdmDataJSON adm) {
		this.adm = adm;
	}

	public String getDeviceType() {
		return deviceType;
	}

	public void setDeviceType(String deviceType) {
		this.deviceType = deviceType;
	}
	
	
}
