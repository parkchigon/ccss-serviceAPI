package com.lgu.common.esb.vo;

public class BillTypeResultVO {

	/** 결과코드 */
	String msgCode;
	/** 결과메시지 */
	String msgText;

	public String getMsgCode() {
		return msgCode;
	}

	public void setMsgCode(String msgCode) {
		this.msgCode = msgCode;
	}

	public String getMsgText() {
		return msgText;
	}

	public void setMsgText(String msgText) {
		this.msgText = msgText;
	}

	@Override
	public String toString() {
		StringBuffer stringBuffer = new StringBuffer();
		stringBuffer.append(String.format("[%32s] %s\n", "msgCode", msgCode));
		stringBuffer.append(String.format("[%32s] %s\n", "msgText", msgText));

		return stringBuffer.toString();
	}
}
