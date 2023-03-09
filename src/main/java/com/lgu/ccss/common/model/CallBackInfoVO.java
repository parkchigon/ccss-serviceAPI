package com.lgu.ccss.common.model;

public class CallBackInfoVO {
	private String mgrappId;
	private String serviceCode;
	private String returnData;
	private String regDt;
	private String updDt;
	
	
	public String getMgrappId() {
		return mgrappId;
	}
	public void setMgrappId(String mgrappId) {
		this.mgrappId = mgrappId;
	}
	public String getServiceCode() {
		return serviceCode;
	}
	public void setServiceCode(String serviceCode) {
		this.serviceCode = serviceCode;
	}
	
	public String getReturnData() {
		return returnData;
	}
	public void setReturnData(String returnData) {
		this.returnData = returnData;
	}
	public String getRegDt() {
		return regDt;
	}
	public void setRegDt(String regDt) {
		this.regDt = regDt;
	}
	public String getUpdDt() {
		return updDt;
	}
	public void setUpdDt(String updDt) {
		this.updDt = updDt;
	}
	@Override
	public String toString() {
		return "CallBackInfoVO [mgrappId=" + mgrappId + ", serviceCode="
				+ serviceCode + ", returnData=" + returnData + ", regDt="
				+ regDt + ", updDt=" + updDt + "]";
	}
	
	
	
	
}
