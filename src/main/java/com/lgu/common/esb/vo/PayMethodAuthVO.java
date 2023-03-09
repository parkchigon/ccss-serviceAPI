package com.lgu.common.esb.vo;

public class PayMethodAuthVO {

	/** 결과코드 */
	String msgCode;
	/** 결과메시지 */
	String msgText;
	/** cms결과코드 */
	String cmsResultCode;

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

	public String getCmsResultCode() {
		return cmsResultCode;
	}

	public void setCmsResultCode(String cmsResultCode) {
		this.cmsResultCode = cmsResultCode;
	}

	@Override
	public String toString() {
		StringBuffer stringBuffer = new StringBuffer();
		stringBuffer.append(String.format("[%32s] %s\n", "msgCode", msgCode));
		stringBuffer.append(String.format("[%32s] %s\n", "msgText", msgText));
		stringBuffer.append(String.format("[%32s] %s\n", "cmsResultCode", cmsResultCode));

		return stringBuffer.toString();
	}
}
