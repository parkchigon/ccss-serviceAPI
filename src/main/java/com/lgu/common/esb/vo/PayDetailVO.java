package com.lgu.common.esb.vo;

import java.util.List;

public class PayDetailVO {

	/** 청구계정번호 */
	String billAcntNo;
	/** 이번달요금(1) */
	String billAmt;
	/** 미납요금(2) */
	String upaidChrg;
	/** 이번달납부금액(1+2) */
	String totPymScdlAmt;
	/** 공급가액 */
	String spramt;
	/** 세액 */
	String txamt;
	/** 할인내역 */
	List<DiscountInfoVO> list;

	public String getBillAcntNo() {
		return billAcntNo;
	}

	public void setBillAcntNo(String billAcntNo) {
		this.billAcntNo = billAcntNo;
	}

	public String getBillAmt() {
		return billAmt;
	}

	public void setBillAmt(String billAmt) {
		this.billAmt = billAmt;
	}

	public String getUpaidChrg() {
		return upaidChrg;
	}

	public void setUpaidChrg(String upaidChrg) {
		this.upaidChrg = upaidChrg;
	}

	public String getTotPymScdlAmt() {
		return totPymScdlAmt;
	}

	public void setTotPymScdlAmt(String totPymScdlAmt) {
		this.totPymScdlAmt = totPymScdlAmt;
	}

	public String getSpramt() {
		return spramt;
	}

	public void setSpramt(String spramt) {
		this.spramt = spramt;
	}

	public String getTxamt() {
		return txamt;
	}

	public void setTxamt(String txamt) {
		this.txamt = txamt;
	}

	public List<DiscountInfoVO> getList() {
		return list;
	}

	public void setList(List<DiscountInfoVO> list) {
		this.list = list;
	}

	@Override
	public String toString() {
		StringBuffer stringBuffer = new StringBuffer();
		stringBuffer.append(String.format("[%32s] %s\n", "billAcntNo", billAcntNo));
		stringBuffer.append(String.format("[%32s] %s\n", "billAmt", billAmt));
		stringBuffer.append(String.format("[%32s] %s\n", "upaidChrg", upaidChrg));
		stringBuffer.append(String.format("[%32s] %s\n", "totPymScdlAmt", totPymScdlAmt));
		stringBuffer.append(String.format("[%32s] %s\n", "spramt", spramt));
		stringBuffer.append(String.format("[%32s] %s\n", "txamt", txamt));

		if (list != null) {
			for (int i = 0; i < list.size(); i++) {
				DiscountInfoVO dscntDtlVO = list.get(i);

				stringBuffer.append(dscntDtlVO.toString());
			}
		}

		return stringBuffer.toString();
	}
}
