package com.lgu.common.esb.vo;

public class DiscountInfoResultVO {

	/** 작업여부 */
	String prssYn;
	/** 불가코드 */
	String errCode;
	/** 불가메세지 */
	String errMsg;

	public String getPrssYn() {
		return prssYn;
	}

	public void setPrssYn(String prssYn) {
		this.prssYn = prssYn;
	}

	public String getErrCode() {
		return errCode;
	}

	public void setErrCode(String errCode) {
		this.errCode = errCode;
	}

	public String getErrMsg() {
		return errMsg;
	}

	public void setErrMsg(String errMsg) {
		this.errMsg = errMsg;
	}

	@Override
	public String toString() {
		StringBuffer stringBuffer = new StringBuffer();
		stringBuffer.append(String.format("[%32s] %s\n", "prssYn", prssYn));
		stringBuffer.append(String.format("[%32s] %s\n", "errCode", errCode));
		stringBuffer.append(String.format("[%32s] %s\n", "errMsg", errMsg));

		return stringBuffer.toString();
	}
}
