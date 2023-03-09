package com.lgu.ccss.common.enumtype.msg;

import com.lgu.ccss.common.enumtype.CcssEnum;

public enum MsgTitle implements CcssEnum{
	//메세지 전송타입
	SEND_CERT_NO("SEND_CERT_NO","인증번호발송");
	
	private String value;
	private String description;
	
	private MsgTitle(String value, String description) {
		this.value = value;
		this.description = description;
	}	
	@Override
	public String getValue() {
		return value;
	}
	@Override
	public String getDescription() {
		return description;
	}
}
