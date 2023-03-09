package com.lgu.ccss.api.voiceguide.model;

public class VoiceDomainListData {
	private String name;
	private String exposureRatio;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getExposureRatio() {
		return exposureRatio;
	}
	public void setExposureRatio(String exposureRatio) {
		this.exposureRatio = exposureRatio;
	}
	
	@Override
	public String toString() {
		return "VoiceDomainListData [name=" + name + ", exposureRatio=" + exposureRatio + "]";
	}
}
