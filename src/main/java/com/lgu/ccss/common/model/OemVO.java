package com.lgu.ccss.common.model;

import java.util.Date;

public class OemVO {
	private String oemId;
	private String oemNm;
	private String clovaClientId;
	private String clovaClientSecret;
	private String regId;
	private Date regDt;
	private String updId;
	private Date updDt;
	private String pushId;
	
	public String getOemId() {
		return oemId;
	}
	public void setOemId(String oemId) {
		this.oemId = oemId;
	}
	public String getOemNm() {
		return oemNm;
	}
	public void setOemNm(String oemNm) {
		this.oemNm = oemNm;
	}
	
	public String getClovaClientId() {
		return clovaClientId;
	}
	public void setClovaClientId(String clovaClientId) {
		this.clovaClientId = clovaClientId;
	}
	public String getClovaClientSecret() {
		return clovaClientSecret;
	}
	public void setClovaClientSecret(String clovaClientSecret) {
		this.clovaClientSecret = clovaClientSecret;
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
	public String getPushId() {
		return pushId;
	}
	public void setPushId(String pushId) {
		this.pushId = pushId;
	}
	@Override
	public String toString() {
		return "OemVO [oemId=" + oemId + ", oemNm=" + oemNm + ", clovaClientId=" + clovaClientId
				+ ", clovaClientSecret=" + clovaClientSecret + ", regId=" + regId + ", regDt=" + regDt + ", updId="
				+ updId + ", updDt=" + updDt + ", pushId=" + pushId + "]";
	}
	
}
