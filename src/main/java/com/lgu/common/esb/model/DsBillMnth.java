package com.lgu.common.esb.model;

public class DsBillMnth {
	String billYymm;
	String amt;
	String pymMthdNm;
	String totAmt;
	public String getBillYymm() {
		return billYymm;
	}
	public void setBillYymm(String billYymm) {
		this.billYymm = billYymm;
	}
	public String getAmt() {
		return amt;
	}
	public void setAmt(String amt) {
		this.amt = amt;
	}
	public String getPymMthdNm() {
		return pymMthdNm;
	}
	public void setPymMthdNm(String pymMthdNm) {
		this.pymMthdNm = pymMthdNm;
	}
	public String getTotAmt() {
		return totAmt;
	}
	public void setTotAmt(String totAmt) {
		this.totAmt = totAmt;
	}
	@Override
	public String toString() {
		return "DsBillMnth [billYymm=" + billYymm + ", amt=" + amt + ", pymMthdNm=" + pymMthdNm + ", totAmt=" + totAmt
				+ "]";
	}
	
	
}
