package com.lgu.common.esb.model;

public class DsPaymentInfo {
	String billAcntId;
	String blngThmn;
	String bltxIsueBaseCd;
	String maskingPrssBfrRepProdNo;
	String useStrDt;
	String duedDt;
	String addWdrwTrgtCntnt;
	String cdcmpBankNm;
	String cardDepoNm;
	String cardNoAcctNo;
	String pymMthdNm;
	public String getBillAcntId() {
		return billAcntId;
	}
	public void setBillAcntId(String billAcntId) {
		this.billAcntId = billAcntId;
	}
	public String getBlngThmn() {
		return blngThmn;
	}
	public void setBlngThmn(String blngThmn) {
		this.blngThmn = blngThmn;
	}
	public String getBltxIsueBaseCd() {
		return bltxIsueBaseCd;
	}
	public void setBltxIsueBaseCd(String bltxIsueBaseCd) {
		this.bltxIsueBaseCd = bltxIsueBaseCd;
	}
	public String getMaskingPrssBfrRepProdNo() {
		return maskingPrssBfrRepProdNo;
	}
	public void setMaskingPrssBfrRepProdNo(String maskingPrssBfrRepProdNo) {
		this.maskingPrssBfrRepProdNo = maskingPrssBfrRepProdNo;
	}
	public String getUseStrDt() {
		return useStrDt;
	}
	public void setUseStrDt(String useStrDt) {
		this.useStrDt = useStrDt;
	}
	public String getDuedDt() {
		return duedDt;
	}
	public void setDuedDt(String duedDt) {
		this.duedDt = duedDt;
	}
	public String getAddWdrwTrgtCntnt() {
		return addWdrwTrgtCntnt;
	}
	public void setAddWdrwTrgtCntnt(String addWdrwTrgtCntnt) {
		this.addWdrwTrgtCntnt = addWdrwTrgtCntnt;
	}
	public String getCdcmpBankNm() {
		return cdcmpBankNm;
	}
	public void setCdcmpBankNm(String cdcmpBankNm) {
		this.cdcmpBankNm = cdcmpBankNm;
	}
	public String getCardDepoNm() {
		return cardDepoNm;
	}
	public void setCardDepoNm(String cardDepoNm) {
		this.cardDepoNm = cardDepoNm;
	}
	public String getCardNoAcctNo() {
		return cardNoAcctNo;
	}
	public void setCardNoAcctNo(String cardNoAcctNo) {
		this.cardNoAcctNo = cardNoAcctNo;
	}
	public String getPymMthdNm() {
		return pymMthdNm;
	}
	public void setPymMthdNm(String pymMthdNm) {
		this.pymMthdNm = pymMthdNm;
	}
	@Override
	public String toString() {
		return "DsPaymentInfo [billAcntId=" + billAcntId + ", blngThmn=" + blngThmn + ", bltxIsueBaseCd="
				+ bltxIsueBaseCd + ", maskingPrssBfrRepProdNo=" + maskingPrssBfrRepProdNo + ", useStrDt=" + useStrDt
				+ ", duedDt=" + duedDt + ", addWdrwTrgtCntnt=" + addWdrwTrgtCntnt + ", cdcmpBankNm=" + cdcmpBankNm
				+ ", cardDepoNm=" + cardDepoNm + ", cardNoAcctNo=" + cardNoAcctNo + ", pymMthdNm=" + pymMthdNm + "]";
	}
	
	
}
