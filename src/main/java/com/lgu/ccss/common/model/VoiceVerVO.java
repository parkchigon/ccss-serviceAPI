package com.lgu.ccss.common.model;

import java.util.Date;

public class VoiceVerVO {
	private String voiceVerNo;
	private String version;
	private String useYn;
	private String regId;
	private Date regDt;
	private String updId;
	private Date updDt;
	private String marketType;
	
	public String getMarketType() {
		return marketType;
	}
	public void setMarketType(String marketType) {
		this.marketType = marketType;
	}
	public String getVoiceVerNo() {
		return voiceVerNo;
	}
	public void setVoiceVerNo(String voiceVerNo) {
		this.voiceVerNo = voiceVerNo;
	}
	public String getVersion() {
		return version;
	}
	public void setVersion(String version) {
		this.version = version;
	}
	public String getUseYn() {
		return useYn;
	}
	public void setUseYn(String useYn) {
		this.useYn = useYn;
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
		return "VoiceVerVO [voiceVerNo=" + voiceVerNo + ", version=" + version + ", useYn=" + useYn + ", regId=" + regId
				+ ", regDt=" + regDt + ", updId=" + updId + ", updDt=" + updDt + ", marketType=" + marketType + "]";
	}	
}