package com.lgu.common.esb.vo;

public class DepositAccountVO {

	/** 은행명 */
	String bankNm;
	/** 입금전용계좌 */
	String vtAcntNo;
	/** 은행코드 */
	String bankCd;

	public String getBankNm() {
		return bankNm;
	}

	public void setBankNm(String bankNm) {
		this.bankNm = bankNm;
	}

	public String getVtAcntNo() {
		return vtAcntNo;
	}

	public void setVtAcntNo(String vtAcntNo) {
		this.vtAcntNo = vtAcntNo;
	}

	public String getBankCd() {
		return bankCd;
	}

	public void setBankCd(String bankCd) {
		this.bankCd = bankCd;
	}

	@Override
	public String toString() {
		StringBuffer stringBuffer = new StringBuffer();
		stringBuffer.append(String.format("[%32s] %s\n", "bankNm", bankNm));
		stringBuffer.append(String.format("[%32s] %s\n", "vtAcntNo", vtAcntNo));
		stringBuffer.append(String.format("[%32s] %s\n", "bankCd", bankCd));

		return stringBuffer.toString();
	}
}
