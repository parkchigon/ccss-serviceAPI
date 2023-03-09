package com.lgu.common.esb.model;

public class DsRsltInfo {
	String rsltCd;
	String rsltMsg;
	public String getRsltCd() {
		return rsltCd;
	}
	public void setRsltCd(String rsltCd) {
		this.rsltCd = rsltCd;
	}
	public String getRsltMsg() {
		return rsltMsg;
	}
	public void setRsltMsg(String rsltMsg) {
		this.rsltMsg = rsltMsg;
	}
	@Override
	public String toString() {
		return "DsRsltInfo [rsltCd=" + rsltCd + ", rsltMsg=" + rsltMsg + "]";
	}
	
	
}
