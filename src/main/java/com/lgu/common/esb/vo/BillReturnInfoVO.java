package com.lgu.common.esb.vo;

public class BillReturnInfoVO {

	/** 청구계정번호 */
	String billAcntNo;
	/** 청구년월 */
	String billTrgtYymm;
	/** 청구서종류 */
	String retnDvNm;
	/** 반송일 */
	String dlvDt;
	/** 반송사유 */
	String retnRsnNm;

	public String getBillAcntNo() {
		return billAcntNo;
	}

	public void setBillAcntNo(String billAcntNo) {
		this.billAcntNo = billAcntNo;
	}

	public String getBillTrgtYymm() {
		return billTrgtYymm;
	}

	public void setBillTrgtYymm(String billTrgtYymm) {
		this.billTrgtYymm = billTrgtYymm;
	}

	public String getRetnDvNm() {
		return retnDvNm;
	}

	public void setRetnDvNm(String retnDvNm) {
		this.retnDvNm = retnDvNm;
	}

	public String getDlvDt() {
		return dlvDt;
	}

	public void setDlvDt(String dlvDt) {
		this.dlvDt = dlvDt;
	}

	public String getRetnRsnNm() {
		return retnRsnNm;
	}

	public void setRetnRsnNm(String retnRsnNm) {
		this.retnRsnNm = retnRsnNm;
	}

	@Override
	public String toString() {
		StringBuffer stringBuffer = new StringBuffer();
		stringBuffer.append(String.format("[%32s] %s\n", "billAcntNo", billAcntNo));
		stringBuffer.append(String.format("[%32s] %s\n", "billTrgtYymm", billTrgtYymm));
		stringBuffer.append(String.format("[%32s] %s\n", "retnDvNm", retnDvNm));
		stringBuffer.append(String.format("[%32s] %s\n", "dlvDt", dlvDt));
		stringBuffer.append(String.format("[%32s] %s\n", "retnRsnNm", retnRsnNm));

		return stringBuffer.toString();
	}
}
