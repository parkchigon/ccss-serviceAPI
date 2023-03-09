package com.lgu.common.esb.model;

import java.util.Arrays;

public class MmlyRmndUsagOverResponseJSON {
	private int httpCode;
	private String httpMessage;
	private String moreInformation;
	
	DsGetEntrMonthAlowsmry[] dsGetEntrMonthAlowsmry;
	DsGetEntrSvcSmry[] dsGetEntrSvcSmry;
	DsGetEntrSvcOver[] dsGetEntrSvcOver;
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
	public DsGetEntrMonthAlowsmry[] getDsGetEntrMonthAlowsmry() {
		return dsGetEntrMonthAlowsmry;
	}
	public void setDsGetEntrMonthAlowsmry(DsGetEntrMonthAlowsmry[] dsGetEntrMonthAlowsmry) {
		this.dsGetEntrMonthAlowsmry = dsGetEntrMonthAlowsmry;
	}
	public DsGetEntrSvcSmry[] getDsGetEntrSvcSmry() {
		return dsGetEntrSvcSmry;
	}
	public void setDsGetEntrSvcSmry(DsGetEntrSvcSmry[] dsGetEntrSvcSmry) {
		this.dsGetEntrSvcSmry = dsGetEntrSvcSmry;
	}
	public DsGetEntrSvcOver[] getDsGetEntrSvcOver() {
		return dsGetEntrSvcOver;
	}
	public void setDsGetEntrSvcOver(DsGetEntrSvcOver[] dsGetEntrSvcOver) {
		this.dsGetEntrSvcOver = dsGetEntrSvcOver;
	}
	public DsRsltInfo getDsRsltInfo() {
		return dsRsltInfo;
	}
	public void setDsRsltInfo(DsRsltInfo dsRsltInfo) {
		this.dsRsltInfo = dsRsltInfo;
	}
	@Override
	public String toString() {
		return "MmlyRmndUsagOverResponseJSON [httpCode=" + httpCode + ", httpMessage=" + httpMessage
				+ ", moreInformation=" + moreInformation + ", dsGetEntrMonthAlowsmry="
				+ Arrays.toString(dsGetEntrMonthAlowsmry) + ", dsGetEntrSvcSmry=" + Arrays.toString(dsGetEntrSvcSmry)
				+ ", dsGetEntrSvcOver=" + Arrays.toString(dsGetEntrSvcOver) + ", dsRsltInfo=" + dsRsltInfo + "]";
	}
	
	
}
