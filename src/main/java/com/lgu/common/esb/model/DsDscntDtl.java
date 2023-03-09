package com.lgu.common.esb.model;

public class DsDscntDtl {
	String bifCmmDetlItemCntn1;
	String dscntDtl;
	String dscntAmt;
	String totCnt;
	public String getBifCmmDetlItemCntn1() {
		return bifCmmDetlItemCntn1;
	}
	public void setBifCmmDetlItemCntn1(String bifCmmDetlItemCntn1) {
		this.bifCmmDetlItemCntn1 = bifCmmDetlItemCntn1;
	}
	public String getDscntDtl() {
		return dscntDtl;
	}
	public void setDscntDtl(String dscntDtl) {
		this.dscntDtl = dscntDtl;
	}
	public String getDscntAmt() {
		return dscntAmt;
	}
	public void setDscntAmt(String dscntAmt) {
		this.dscntAmt = dscntAmt;
	}
	public String getTotCnt() {
		return totCnt;
	}
	public void setTotCnt(String totCnt) {
		this.totCnt = totCnt;
	}
	@Override
	public String toString() {
		return "DsDscntDtl [bifCmmDetlItemCntn1=" + bifCmmDetlItemCntn1 + ", dscntDtl=" + dscntDtl + ", dscntAmt="
				+ dscntAmt + ", totCnt=" + totCnt + "]";
	}
	
}
