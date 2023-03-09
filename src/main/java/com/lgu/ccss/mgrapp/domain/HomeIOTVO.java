package com.lgu.ccss.mgrapp.domain;


public class HomeIOTVO {
	private String returnType;
	private String rt;
	private String rtMsg;
	private String ssoKey;
	private String loginTpe;
	private String oneidEmailAddr;
	private String serviceKey;
	private String name;
	private String lgtType;
	private String pwUpdateDt;
	private String tosServiceCd;
	private String idType;
	public String getReturnType() {
		return returnType;
	}
	public void setReturnType(String returnType) {
		this.returnType = returnType;
	}
	public String getRt() {
		return rt;
	}
	public void setRt(String rt) {
		this.rt = rt;
	}
	public String getRtMsg() {
		return rtMsg;
	}
	public void setRtMsg(String rtMsg) {
		this.rtMsg = rtMsg;
	}
	public String getSsoKey() {
		return ssoKey;
	}
	public void setSsoKey(String ssoKey) {
		this.ssoKey = ssoKey;
	}
	public String getLoginTpe() {
		return loginTpe;
	}
	public void setLoginTpe(String loginTpe) {
		this.loginTpe = loginTpe;
	}
	public String getOneidEmailAddr() {
		return oneidEmailAddr;
	}
	public void setOneidEmailAddr(String oneidEmailAddr) {
		this.oneidEmailAddr = oneidEmailAddr;
	}
	public String getServiceKey() {
		return serviceKey;
	}
	public void setServiceKey(String serviceKey) {
		this.serviceKey = serviceKey;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getLgtType() {
		return lgtType;
	}
	public void setLgtType(String lgtType) {
		this.lgtType = lgtType;
	}
	public String getPwUpdateDt() {
		return pwUpdateDt;
	}
	public void setPwUpdateDt(String pwUpdateDt) {
		this.pwUpdateDt = pwUpdateDt;
	}
	public String getTosServiceCd() {
		return tosServiceCd;
	}
	public void setTosServiceCd(String tosServiceCd) {
		this.tosServiceCd = tosServiceCd;
	}
	public String getIdType() {
		return idType;
	}
	public void setIdType(String idType) {
		this.idType = idType;
	}
	@Override
	public String toString() {
		return "HomeIOTVO [returnType=" + returnType + ", rt=" + rt
				+ ", rtMsg=" + rtMsg + ", ssoKey=" + ssoKey + ", loginTpe="
				+ loginTpe + ", oneidEmailAddr=" + oneidEmailAddr
				+ ", serviceKey=" + serviceKey + ", name=" + name
				+ ", lgtType=" + lgtType + ", pwUpdateDt=" + pwUpdateDt
				+ ", tosServiceCd=" + tosServiceCd + ", idType=" + idType + "]";
	}
	
	
}
