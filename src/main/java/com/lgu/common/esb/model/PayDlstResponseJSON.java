package com.lgu.common.esb.model;

import java.util.Arrays;

public class PayDlstResponseJSON {
	private int httpCode;
	private String httpMessage;
	private String moreInformation;
	
	DsGiroVtAcct[] dsGiroVtAcct;
	DsLastPaymentRcpt[] dsLastPaymentRcpt;
	DsPaymentInfo[] dsPaymentInfo;
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
	public DsGiroVtAcct[] getDsGiroVtAcct() {
		return dsGiroVtAcct;
	}
	public void setDsGiroVtAcct(DsGiroVtAcct[] dsGiroVtAcct) {
		this.dsGiroVtAcct = dsGiroVtAcct;
	}
	public DsLastPaymentRcpt[] getDsLastPaymentRcpt() {
		return dsLastPaymentRcpt;
	}
	public void setDsLastPaymentRcpt(DsLastPaymentRcpt[] dsLastPaymentRcpt) {
		this.dsLastPaymentRcpt = dsLastPaymentRcpt;
	}
	public DsPaymentInfo[] getDsPaymentInfo() {
		return dsPaymentInfo;
	}
	public void setDsPaymentInfo(DsPaymentInfo[] dsPaymentInfo) {
		this.dsPaymentInfo = dsPaymentInfo;
	}
	public DsRsltInfo getDsRsltInfo() {
		return dsRsltInfo;
	}
	public void setDsRsltInfo(DsRsltInfo dsRsltInfo) {
		this.dsRsltInfo = dsRsltInfo;
	}
	@Override
	public String toString() {
		return "PayDlstResponseJSON [httpCode=" + httpCode + ", httpMessage=" + httpMessage + ", moreInformation="
				+ moreInformation + ", dsGiroVtAcct=" + Arrays.toString(dsGiroVtAcct) + ", dsLastPaymentRcpt="
				+ Arrays.toString(dsLastPaymentRcpt) + ", dsPaymentInfo=" + Arrays.toString(dsPaymentInfo)
				+ ", dsRsltInfo=" + dsRsltInfo + "]";
	}
	
	
}
