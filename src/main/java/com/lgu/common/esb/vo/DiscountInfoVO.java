package com.lgu.common.esb.vo;

public class DiscountInfoVO {

	/** 할인내역 */
	String dscntDtl;
	/** 할인금액 */
	String dscntAmt;

	public String getDscntDtl() {
		return dscntDtl;
	}

	public void setDscntDtl(String dscntDtl) {
		this.dscntDtl = dscntDtl;
	}

	public String getDscntAmt() {
		return dscntAmt;
	}

	public void setDscntAmt(String dscntAmt) {
		this.dscntAmt = dscntAmt;
	}

	@Override
	public String toString() {
		StringBuffer stringBuffer = new StringBuffer();
		stringBuffer.append(String.format("[%32s] %s\n", "dscntDtl", dscntDtl));
		stringBuffer.append(String.format("[%32s] %s\n", "dscntAmt", dscntAmt));

		return stringBuffer.toString();
	}
}
