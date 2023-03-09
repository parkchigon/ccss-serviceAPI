package com.lgu.ccss.common.enumtype.msg;

import com.lgu.ccss.common.enumtype.CcssEnum;

public enum SendType implements CcssEnum{
	//메세지 전송타입
	SEND_INSTANTLY("01","즉시전송"),
	SEND_RESERVATION("02","예약전송");
	
	private String value;
	private String description;
	
	private SendType(String value, String description) {
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
