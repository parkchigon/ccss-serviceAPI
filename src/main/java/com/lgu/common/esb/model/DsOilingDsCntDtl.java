package com.lgu.common.esb.model;

public class DsOilingDsCntDtl {
	String prodNo;
	String bnftMnth;
	String oilingLmt;
	String litUnitDscntAmt;
	String useLiter;
	String totDscntAmt;
	String xferLiter;
	public String getProdNo() {
		return prodNo;
	}
	public void setProdNo(String prodNo) {
		this.prodNo = prodNo;
	}
	public String getBnftMnth() {
		return bnftMnth;
	}
	public void setBnftMnth(String bnftMnth) {
		this.bnftMnth = bnftMnth;
	}
	public String getOilingLmt() {
		return oilingLmt;
	}
	public void setOilingLmt(String oilingLmt) {
		this.oilingLmt = oilingLmt;
	}
	public String getLitUnitDscntAmt() {
		return litUnitDscntAmt;
	}
	public void setLitUnitDscntAmt(String litUnitDscntAmt) {
		this.litUnitDscntAmt = litUnitDscntAmt;
	}
	public String getUseLiter() {
		return useLiter;
	}
	public void setUseLiter(String useLiter) {
		this.useLiter = useLiter;
	}
	public String getTotDscntAmt() {
		return totDscntAmt;
	}
	public void setTotDscntAmt(String totDscntAmt) {
		this.totDscntAmt = totDscntAmt;
	}
	public String getXferLiter() {
		return xferLiter;
	}
	public void setXferLiter(String xferLiter) {
		this.xferLiter = xferLiter;
	}
	@Override
	public String toString() {
		return "DsOilingDsCntDtl [prodNo=" + prodNo + ", bnftMnth=" + bnftMnth + ", oilingLmt=" + oilingLmt
				+ ", litUnitDscntAmt=" + litUnitDscntAmt + ", useLiter=" + useLiter + ", totDscntAmt=" + totDscntAmt
				+ ", xferLiter=" + xferLiter + "]";
	}
	
	
}
