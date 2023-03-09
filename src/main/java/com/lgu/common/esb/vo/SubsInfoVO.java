package com.lgu.common.esb.vo;

public class SubsInfoVO {

	/** 상품(요금제)명 */
	String svcNm;
	/** 약정기간 */
	String fxedFctr1;
	/** 개통일자 */
	String svcFrstStrtDttm;
	/** 해지일자 */
	String svcEndDttm;
	/** 납부방법구분 */
	String pymMthdCd;
	
	String svcCd;

	/** 전화번호 */
	String minNo;
	
	String entrSttsCd;
	
	public String getSvcNm() {
		return svcNm;
	}

	public void setSvcNm(String svcNm) {
		this.svcNm = svcNm;
	}

	public String getFxedFctr1() {
		return fxedFctr1;
	}

	public void setFxedFctr1(String fxedFctr1) {
		this.fxedFctr1 = fxedFctr1;
	}

	public String getSvcFrstStrtDttm() {
		return svcFrstStrtDttm;
	}

	public void setSvcFrstStrtDttm(String svcFrstStrtDttm) {
		this.svcFrstStrtDttm = svcFrstStrtDttm;
	}

	public String getSvcEndDttm() {
		return svcEndDttm;
	}

	public void setSvcEndDttm(String svcEndDttm) {
		this.svcEndDttm = svcEndDttm;
	}

	public String getPymMthdCd() {
		return pymMthdCd;
	}

	public void setPymMthdCd(String pymMthdCd) {
		this.pymMthdCd = pymMthdCd;
	}
	
	public String getSvcCd() {
		return svcCd;
	}

	public void setSvcCd(String svcCd) {
		this.svcCd = svcCd;
	}

	
	public String getMinNo() {
		return minNo;
	}

	public void setMinNo(String minNo) {
		this.minNo = minNo;
	}

	
	public String getEntrSttsCd() {
		return entrSttsCd;
	}

	public void setEntrSttsCd(String entrSttsCd) {
		this.entrSttsCd = entrSttsCd;
	}

	@Override
	public String toString() {
		StringBuffer stringBuffer = new StringBuffer();
		stringBuffer.append(String.format("[%32s] %s\n", "svcNm", svcNm));
		stringBuffer.append(String.format("[%32s] %s\n", "fxedFctr1", fxedFctr1));
		stringBuffer.append(String.format("[%32s] %s\n", "svcFrstStrtDttm", svcFrstStrtDttm));
		stringBuffer.append(String.format("[%32s] %s\n", "svcEndDttm", svcEndDttm));
		stringBuffer.append(String.format("[%32s] %s\n", "pymMthdCd", pymMthdCd));
		stringBuffer.append(String.format("[%32s] %s\n", "svcCd", svcCd));
		stringBuffer.append(String.format("[%32s] %s\n", "minNo", minNo));
		stringBuffer.append(String.format("[%32s] %s\n", "entrSttsCd", entrSttsCd));

		return stringBuffer.toString();
	}

}
