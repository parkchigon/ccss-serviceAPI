package com.lgu.ccss.common.model;

public class VoiceDomainVO {
	private String name;
	private String exposureRatio;
	private String marketType;
	private String voiceVerNo;
	
	public String getVoiceVerNo() {
		return voiceVerNo;
	}
	public void setVoiceVerNo(String voiceVerNo) {
		this.voiceVerNo = voiceVerNo;
	}
	public String getMarketType() {
		return marketType;
	}
	public void setMarketType(String marketType) {
		this.marketType = marketType;
	}
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
		return "VoiceDomainVO [name=" + name + ", exposureRatio=" + exposureRatio + ", marketType=" + marketType + ", voiceVerNo=" + voiceVerNo + "]";
	}
}
