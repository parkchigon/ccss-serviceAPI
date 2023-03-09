package com.lgu.common.esb.model;

public class DsLastPaymentRcpt {
	String billAcntId;
	String BlngThmn;
	String bltxtIsueBaseCd;
	String custNm;
	String repProdNo;
	String pmthCdcmpBankNm;
	String pmthCardDepoNm;
	String pmthCardNoAcctNo;
	String pymDt1;
	String pymAmt1;
	String pymMthdNm1;
	public String getBillAcntId() {
		return billAcntId;
	}
	public void setBillAcntId(String billAcntId) {
		this.billAcntId = billAcntId;
	}
	public String getBlngThmn() {
		return BlngThmn;
	}
	public void setBlngThmn(String blngThmn) {
		BlngThmn = blngThmn;
	}
	public String getBltxtIsueBaseCd() {
		return bltxtIsueBaseCd;
	}
	public void setBltxtIsueBaseCd(String bltxtIsueBaseCd) {
		this.bltxtIsueBaseCd = bltxtIsueBaseCd;
	}
	public String getCustNm() {
		return custNm;
	}
	public void setCustNm(String custNm) {
		this.custNm = custNm;
	}
	public String getRepProdNo() {
		return repProdNo;
	}
	public void setRepProdNo(String repProdNo) {
		this.repProdNo = repProdNo;
	}
	public String getPmthCdcmpBankNm() {
		return pmthCdcmpBankNm;
	}
	public void setPmthCdcmpBankNm(String pmthCdcmpBankNm) {
		this.pmthCdcmpBankNm = pmthCdcmpBankNm;
	}
	public String getPmthCardDepoNm() {
		return pmthCardDepoNm;
	}
	public void setPmthCardDepoNm(String pmthCardDepoNm) {
		this.pmthCardDepoNm = pmthCardDepoNm;
	}
	public String getPmthCardNoAcctNo() {
		return pmthCardNoAcctNo;
	}
	public void setPmthCardNoAcctNo(String pmthCardNoAcctNo) {
		this.pmthCardNoAcctNo = pmthCardNoAcctNo;
	}
	public String getPymDt1() {
		return pymDt1;
	}
	public void setPymDt1(String pymDt1) {
		this.pymDt1 = pymDt1;
	}
	public String getPymAmt1() {
		return pymAmt1;
	}
	public void setPymAmt1(String pymAmt1) {
		this.pymAmt1 = pymAmt1;
	}
	public String getPymMthdNm1() {
		return pymMthdNm1;
	}
	public void setPymMthdNm1(String pymMthdNm1) {
		this.pymMthdNm1 = pymMthdNm1;
	}
	@Override
	public String toString() {
		return "DsLastPaymentRcpt [billAcntId=" + billAcntId + ", BlngThmn=" + BlngThmn + ", bltxtIsueBaseCd="
				+ bltxtIsueBaseCd + ", custNm=" + custNm + ", repProdNo=" + repProdNo + ", pmthCdcmpBankNm="
				+ pmthCdcmpBankNm + ", pmthCardDepoNm=" + pmthCardDepoNm + ", pmthCardNoAcctNo=" + pmthCardNoAcctNo
				+ ", pymDt1=" + pymDt1 + ", pymAmt1=" + pymAmt1 + ", pymMthdNm1=" + pymMthdNm1 + "]";
	}
	
	
}
