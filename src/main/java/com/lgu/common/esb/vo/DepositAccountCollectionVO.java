package com.lgu.common.esb.vo;

import java.util.List;

public class DepositAccountCollectionVO {

	/** 청구계정번호 */
	String billAcntNo;
	/** 고객전용입금계좌 */
	List<DepositAccountVO> list;

	public String getBillAcntNo() {
		return billAcntNo;
	}

	public void setBillAcntNo(String billAcntNo) {
		this.billAcntNo = billAcntNo;
	}

	public List<DepositAccountVO> getList() {
		return list;
	}

	public void setList(List<DepositAccountVO> list) {
		this.list = list;
	}

	@Override
	public String toString() {

		StringBuffer stringBuffer = new StringBuffer();
		stringBuffer.append(String.format("[%32s] %s\n", "billAcntNo", billAcntNo));
		if (list != null) {
			for (int i = 0; i < list.size(); i++) {
				DepositAccountVO depositAccountVO = list.get(i);

				stringBuffer.append(depositAccountVO.toString());
			}
		}

		return stringBuffer.toString();
	}
}
