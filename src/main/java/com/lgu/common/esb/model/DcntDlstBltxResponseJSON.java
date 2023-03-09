package com.lgu.common.esb.model;

import java.util.Arrays;
import java.util.List;

public class DcntDlstBltxResponseJSON {
	private int httpCode;
	private String httpMessage;
	private String moreInformation;
	
	DsBillMnth[] dsBillMnth;
	DsBills[] dsBills;
	DsDscntDtl[] dsDscntDtl;
	DsDscntPnltDtl[] dsDscntPnltDtl;
	DsOilingDsCntDtl[] dsOilingDsCntDtl;
	DsPymRsht[] dsPymRsht;
	private DsRsltInfo dsRsltInfo;
	public int getHttpCode() {
		return httpCode;
	}
	public void setHttpCode(int httpCode) {
		this.httpCode = httpCode;
	}
	public String getHttpMessage() {
		return httpMessage;
	}
	public void setHttpMessage(String httpMessage) {
		this.httpMessage = httpMessage;
	}
	public String getMoreInformation() {
		return moreInformation;
	}
	public void setMoreInformation(String moreInformation) {
		this.moreInformation = moreInformation;
	}
	public DsBillMnth[] getDsBillMnth() {
		return dsBillMnth;
	}
	public void setDsBillMnth(DsBillMnth[] dsBillMnth) {
		this.dsBillMnth = dsBillMnth;
	}
	public DsBills[] getDsBills() {
		return dsBills;
	}
	public void setDsBills(DsBills[] dsBills) {
		this.dsBills = dsBills;
	}
	public DsDscntDtl[] getDsDscntDtl() {
		return dsDscntDtl;
	}
	public void setDsDscntDtl(DsDscntDtl[] dsDscntDtl) {
		this.dsDscntDtl = dsDscntDtl;
	}
	public DsDscntPnltDtl[] getDsDscntPnltDtl() {
		return dsDscntPnltDtl;
	}
	public void setDsDscntPnltDtl(DsDscntPnltDtl[] dsDscntPnltDtl) {
		this.dsDscntPnltDtl = dsDscntPnltDtl;
	}
	public DsOilingDsCntDtl[] getDsOilingDsCntDtl() {
		return dsOilingDsCntDtl;
	}
	public void setDsOilingDsCntDtl(DsOilingDsCntDtl[] dsOilingDsCntDtl) {
		this.dsOilingDsCntDtl = dsOilingDsCntDtl;
	}
	public DsPymRsht[] getDsPymRsht() {
		return dsPymRsht;
	}
	public void setDsPymRsht(DsPymRsht[] dsPymRsht) {
		this.dsPymRsht = dsPymRsht;
	}
	public DsRsltInfo getDsRsltInfo() {
		return dsRsltInfo;
	}
	public void setDsRsltInfo(DsRsltInfo dsRsltInfo) {
		this.dsRsltInfo = dsRsltInfo;
	}
	@Override
	public String toString() {
		return "DcntDlsBltxResponseJSON [httpCode=" + httpCode + ", httpMessage=" + httpMessage + ", moreInformation="
				+ moreInformation + ", dsBillMnth=" + Arrays.toString(dsBillMnth) + ", dsBills="
				+ Arrays.toString(dsBills) + ", dsDscntDtl=" + Arrays.toString(dsDscntDtl) + ", dsDscntPnltDtl="
				+ Arrays.toString(dsDscntPnltDtl) + ", dsOilingDsCntDtl=" + Arrays.toString(dsOilingDsCntDtl)
				+ ", dsPymRsht=" + Arrays.toString(dsPymRsht) + ", dsRsltInfo=" + dsRsltInfo + "]";
	}
	
	
}
