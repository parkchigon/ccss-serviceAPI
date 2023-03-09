package com.lgu.common.esb.vo;

public class PauseHistoryVO {

	/** 일시정지일자 */
	String susDate;
	/** 정지해제일자 혹은 정지해제예약일자 */
	String rspDate;
	/** 요청구분명 */
	String rsnNm;
	/** 서비스명 */
	String prodNm;
	/** 일시정지상세사유명 */
	String susRsnNm;
	/** 일시정지일수 */
	String susDays;
	/** 기간 */
	String term;

	public String getSusDate() {
		return susDate;
	}

	public void setSusDate(String susDate) {
		this.susDate = susDate;
	}

	public String getRspDate() {
		return rspDate;
	}

	public void setRspDate(String rspDate) {
		this.rspDate = rspDate;
	}

	public String getRsnNm() {
		return rsnNm;
	}

	public void setRsnNm(String rsnNm) {
		this.rsnNm = rsnNm;
	}

	public String getProdNm() {
		return prodNm;
	}

	public void setProdNm(String prodNm) {
		this.prodNm = prodNm;
	}

	public String getSusRsnNm() {
		return susRsnNm;
	}

	public void setSusRsnNm(String susRsnNm) {
		this.susRsnNm = susRsnNm;
	}

	public String getSusDays() {
		return susDays;
	}

	public void setSusDays(String susDays) {
		this.susDays = susDays;
	}

	public String getTerm() {
		return term;
	}

	public void setTerm(String term) {
		this.term = term;
	}

	@Override
	public String toString() {
		StringBuffer stringBuffer = new StringBuffer();
		stringBuffer.append(String.format("[%32s] %s\n", "susDate", susDate));
		stringBuffer.append(String.format("[%32s] %s\n", "rspDate", rspDate));
		stringBuffer.append(String.format("[%32s] %s\n", "rsnNm", rsnNm));
		stringBuffer.append(String.format("[%32s] %s\n", "prodNm", prodNm));
		stringBuffer.append(String.format("[%32s] %s\n", "susRsnNm", susRsnNm));
		stringBuffer.append(String.format("[%32s] %s\n", "susDays", susDays));
		stringBuffer.append(String.format("[%32s] %s\n", "term", term));

		return stringBuffer.toString();
	}
}
