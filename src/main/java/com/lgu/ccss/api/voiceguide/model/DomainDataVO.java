/*** 해당 클래스 사용안함 
 * com.lgu.ccss.common.model.VoiceDomainVO
 ***/
package com.lgu.ccss.api.voiceguide.model;

public class DomainDataVO {
	private String name;
	private String exposureRatio;
	private String marketType;
	
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
		return "DomainData [name=" + name + ", exposureRatio=" + exposureRatio + "]";
	}
}
