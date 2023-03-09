package com.lgu.common.esb.vo;

public class PayMethodVO {

	/** 청구계정번호 */
	String billAcntNo;
	/** 상품(요금제)명 */
	String svcNm;
	/** 고객명 */
	String custNm;
	/** 요금청구구분 */
	String bltxtKdCd;
	/** 요금청구구분명 */
	String bltxtKdNm;
	/** 납부방법구분 */
	String pymMthdCd;
	/** 납부방법구분명 */
	String pymMthdNm;
	/** 은행명 */
	String bankNm;
	/** 카드회사명 */
	String cardNm;
	/** 납기일 */
	String duedDt;
	/** 출금일 */
	String addWdrwTrgtCntnt;
	/** 예금(카드)주명 */
	String cardDepoNm;

	public String getBillAcntNo() {
		return billAcntNo;
	}

	public void setBillAcntNo(String billAcntNo) {
		this.billAcntNo = billAcntNo;
	}

	public String getSvcNm() {
		return svcNm;
	}

	public void setSvcNm(String svcNm) {
		this.svcNm = svcNm;
	}

	public String getCustNm() {
		return custNm;
	}

	public void setCustNm(String custNm) {
		this.custNm = custNm;
	}

	public String getBltxtKdCd() {
		return bltxtKdCd;
	}

	public void setBltxtKdCd(String bltxtKdCd) {
		this.bltxtKdCd = bltxtKdCd;
	}

	public String getBltxtKdNm() {
		return bltxtKdNm;
	}

	public void setBltxtKdNm(String bltxtKdNm) {
		this.bltxtKdNm = bltxtKdNm;
	}

	public String getPymMthdCd() {
		return pymMthdCd;
	}

	public void setPymMthdCd(String pymMthdCd) {
		this.pymMthdCd = pymMthdCd;
	}

	public String getPymMthdNm() {
		return pymMthdNm;
	}

	public void setPymMthdNm(String pymMthdNm) {
		this.pymMthdNm = pymMthdNm;
	}

	public String getBankNm() {
		return bankNm;
	}

	public void setBankNm(String bankNm) {
		this.bankNm = bankNm;
	}

	public String getCardNm() {
		return cardNm;
	}

	public void setCardNm(String cardNm) {
		this.cardNm = cardNm;
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

	public String getCardDepoNm() {
		return cardDepoNm;
	}

	public void setCardDepoNm(String cardDepoNm) {
		this.cardDepoNm = cardDepoNm;
	}

	@Override
	public String toString() {
		StringBuffer stringBuffer = new StringBuffer();
		stringBuffer.append(String.format("[%32s] %s\n", "billAcntNo", billAcntNo));
		stringBuffer.append(String.format("[%32s] %s\n", "svcNm", svcNm));
		stringBuffer.append(String.format("[%32s] %s\n", "custNm", custNm));
		stringBuffer.append(String.format("[%32s] %s\n", "bltxtKdCd", bltxtKdCd));
		stringBuffer.append(String.format("[%32s] %s\n", "bltxtKdNm", bltxtKdNm));
		stringBuffer.append(String.format("[%32s] %s\n", "pymMthdCd", pymMthdCd));
		stringBuffer.append(String.format("[%32s] %s\n", "pymMthdNm", pymMthdNm));
		stringBuffer.append(String.format("[%32s] %s\n", "bankNm", bankNm));
		stringBuffer.append(String.format("[%32s] %s\n", "cardNm", cardNm));
		stringBuffer.append(String.format("[%32s] %s\n", "duedDt", duedDt));
		stringBuffer.append(String.format("[%32s] %s\n", "addWdrwTrgtCntnt", addWdrwTrgtCntnt));
		stringBuffer.append(String.format("[%32s] %s\n", "cardDepoNm", cardDepoNm));

		return stringBuffer.toString();
	}
}
