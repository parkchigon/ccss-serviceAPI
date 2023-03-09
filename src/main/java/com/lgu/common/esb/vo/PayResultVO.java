package com.lgu.common.esb.vo;

public class PayResultVO {

	/** 카드번호 */
	String cardNo;
	/** 금액 */
	String ccrdAprvRqstAmt;
	/** 카드사코드 */
	String ccrdCompCd;
	/** 개인/법인 */
	String ccrdIndvCoDvCd;
	/** 고객주민번호 */
	String ccrdOwnrPersNo;
	/** 카드승인결과코드 */
	String ccrdStlmAprvRsltCd;
	/** 카드할부개월수 */
	String ccrdStlmInsttMms;
	/** 카드유효종료일자 */
	String ccrdValdEndDt;
	/** 처리자 */
	String operatingDealer;
	/** 상품번호 */
	String prodNo;
	/** 결과코드 */
	String resultCode;

	public String getCardNo() {
		return cardNo;
	}

	public void setCardNo(String cardNo) {
		this.cardNo = cardNo;
	}

	public String getCcrdAprvRqstAmt() {
		return ccrdAprvRqstAmt;
	}

	public void setCcrdAprvRqstAmt(String ccrdAprvRqstAmt) {
		this.ccrdAprvRqstAmt = ccrdAprvRqstAmt;
	}

	public String getCcrdCompCd() {
		return ccrdCompCd;
	}

	public void setCcrdCompCd(String ccrdCompCd) {
		this.ccrdCompCd = ccrdCompCd;
	}

	public String getCcrdIndvCoDvCd() {
		return ccrdIndvCoDvCd;
	}

	public void setCcrdIndvCoDvCd(String ccrdIndvCoDvCd) {
		this.ccrdIndvCoDvCd = ccrdIndvCoDvCd;
	}

	public String getCcrdOwnrPersNo() {
		return ccrdOwnrPersNo;
	}

	public void setCcrdOwnrPersNo(String ccrdOwnrPersNo) {
		this.ccrdOwnrPersNo = ccrdOwnrPersNo;
	}

	public String getCcrdStlmAprvRsltCd() {
		return ccrdStlmAprvRsltCd;
	}

	public void setCcrdStlmAprvRsltCd(String ccrdStlmAprvRsltCd) {
		this.ccrdStlmAprvRsltCd = ccrdStlmAprvRsltCd;
	}

	public String getCcrdStlmInsttMms() {
		return ccrdStlmInsttMms;
	}

	public void setCcrdStlmInsttMms(String ccrdStlmInsttMms) {
		this.ccrdStlmInsttMms = ccrdStlmInsttMms;
	}

	public String getCcrdValdEndDt() {
		return ccrdValdEndDt;
	}

	public void setCcrdValdEndDt(String ccrdValdEndDt) {
		this.ccrdValdEndDt = ccrdValdEndDt;
	}

	public String getOperatingDealer() {
		return operatingDealer;
	}

	public void setOperatingDealer(String operatingDealer) {
		this.operatingDealer = operatingDealer;
	}

	public String getProdNo() {
		return prodNo;
	}

	public void setProdNo(String prodNo) {
		this.prodNo = prodNo;
	}

	public String getResultCode() {
		return resultCode;
	}

	public void setResultCode(String resultCode) {
		this.resultCode = resultCode;
	}

	@Override
	public String toString() {
		StringBuffer stringBuffer = new StringBuffer();
		stringBuffer.append(String.format("[%32s] %s\n", "cardNo", cardNo));
		stringBuffer.append(String.format("[%32s] %s\n", "ccrdAprvRqstAmt", ccrdAprvRqstAmt));
		stringBuffer.append(String.format("[%32s] %s\n", "ccrdCompCd", ccrdCompCd));
		stringBuffer.append(String.format("[%32s] %s\n", "ccrdIndvCoDvCd", ccrdIndvCoDvCd));
		stringBuffer.append(String.format("[%32s] %s\n", "ccrdOwnrPersNo", ccrdOwnrPersNo));
		stringBuffer.append(String.format("[%32s] %s\n", "ccrdStlmAprvRsltCd", ccrdStlmAprvRsltCd));
		stringBuffer.append(String.format("[%32s] %s\n", "ccrdStlmInsttMms", ccrdStlmInsttMms));
		stringBuffer.append(String.format("[%32s] %s\n", "ccrdValdEndDt", ccrdValdEndDt));
		stringBuffer.append(String.format("[%32s] %s\n", "operatingDealer", operatingDealer));
		stringBuffer.append(String.format("[%32s] %s\n", "prodNo", prodNo));
		stringBuffer.append(String.format("[%32s] %s\n", "resultCode", resultCode));

		return stringBuffer.toString();
	}
}
