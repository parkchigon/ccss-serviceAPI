package com.lgu.common.esb.vo;

import java.util.List;

public class BillTypeVO {

	/** 청구계정번호 */
	String billAcntNo;
	/** 청구서유형코드 */
	String bltxtKdCd;
	/** 청구서유형명 */
	String bltxtKdNm;
	/** 청구서이메일주소 */
	String billEmailAddr;
	/** 청구서유형유효시작일자 */
	String bltxtKdValdStrtDt;
	/** 보안메일수신여부 */
	String scurMailRcpYn;
	/** 청구서반송내역 */
	List<BillReturnInfoVO> list;

	public String getBillAcntNo() {
		return billAcntNo;
	}

	public void setBillAcntNo(String billAcntNo) {
		this.billAcntNo = billAcntNo;
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

	public String getBillEmailAddr() {
		return billEmailAddr;
	}

	public void setBillEmailAddr(String billEmailAddr) {
		this.billEmailAddr = billEmailAddr;
	}

	public String getBltxtKdValdStrtDt() {
		return bltxtKdValdStrtDt;
	}

	public void setBltxtKdValdStrtDt(String bltxtKdValdStrtDt) {
		this.bltxtKdValdStrtDt = bltxtKdValdStrtDt;
	}

	public String getScurMailRcpYn() {
		return scurMailRcpYn;
	}

	public void setScurMailRcpYn(String scurMailRcpYn) {
		this.scurMailRcpYn = scurMailRcpYn;
	}

	public List<BillReturnInfoVO> getList() {
		return list;
	}

	public void setList(List<BillReturnInfoVO> list) {
		this.list = list;
	}

	@Override
	public String toString() {
		StringBuffer stringBuffer = new StringBuffer();
		stringBuffer.append(String.format("[%32s] %s\n", "billAcntNo", billAcntNo));
		stringBuffer.append(String.format("[%32s] %s\n", "bltxtKdCd", bltxtKdCd));
		stringBuffer.append(String.format("[%32s] %s\n", "bltxtKdNm", bltxtKdNm));
		stringBuffer.append(String.format("[%32s] %s\n", "billEmailAddr", billEmailAddr));
		stringBuffer.append(String.format("[%32s] %s\n", "bltxtKdValdStrtDt", bltxtKdValdStrtDt));
		stringBuffer.append(String.format("[%32s] %s\n", "scurMailRcpYn", scurMailRcpYn));

		if (list != null) {
			for (int i = 0; i < list.size(); i++) {
				BillReturnInfoVO billRetnInfoVO = list.get(i);

				stringBuffer.append(billRetnInfoVO.toString());
			}
		}

		return stringBuffer.toString();
	}
}
