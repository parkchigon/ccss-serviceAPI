package com.lgu.common.esb.vo;

public class PauseLiftHistory {
	String entrSttsValdStrtDt;
	String entrSttsValdEndDt;
	String prodNo;
	String prodNm;
	String entrSttsChngRsnDtlNm;
	String entrSttsNm;
	String entrSttsChngSeqno;
	public String getEntrSttsValdStrtDt() {
		return entrSttsValdStrtDt;
	}
	public void setEntrSttsValdStrtDt(String entrSttsValdStrtDt) {
		this.entrSttsValdStrtDt = entrSttsValdStrtDt;
	}
	public String getEntrSttsValdEndDt() {
		return entrSttsValdEndDt;
	}
	public void setEntrSttsValdEndDt(String entrSttsValdEndDt) {
		this.entrSttsValdEndDt = entrSttsValdEndDt;
	}
	public String getProdNo() {
		return prodNo;
	}
	public void setProdNo(String prodNo) {
		this.prodNo = prodNo;
	}
	public String getProdNm() {
		return prodNm;
	}
	public void setProdNm(String prodNm) {
		this.prodNm = prodNm;
	}
	public String getEntrSttsChngRsnDtlNm() {
		return entrSttsChngRsnDtlNm;
	}
	public void setEntrSttsChngRsnDtlNm(String entrSttsChngRsnDtlNm) {
		this.entrSttsChngRsnDtlNm = entrSttsChngRsnDtlNm;
	}
	public String getEntrSttsNm() {
		return entrSttsNm;
	}
	public void setEntrSttsNm(String entrSttsNm) {
		this.entrSttsNm = entrSttsNm;
	}
	
	public String getEntrSttsChngSeqno() {
		return entrSttsChngSeqno;
	}
	public void setEntrSttsChngSeqno(String entrSttsChngSeqno) {
		this.entrSttsChngSeqno = entrSttsChngSeqno;
	}
	@Override
	public String toString() {
		return "PauseLiftHistory [entrSttsValdStrtDt=" + entrSttsValdStrtDt + ", entrSttsValdEndDt=" + entrSttsValdEndDt
				+ ", prodNo=" + prodNo + ", prodNm=" + prodNm + ", entrSttsChngRsnDtlNm=" + entrSttsChngRsnDtlNm
				+ ", entrSttsNm=" + entrSttsNm + "]";
	}
	
	
}
