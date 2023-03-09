package com.lgu.ccss.common.enumtype.msg;

import com.lgu.ccss.common.enumtype.CcssEnum;

public enum MsgStatus implements CcssEnum{
	//메세지 전송타입
	WAIT("0000","전송대기"),
	PROCESSING("0001","전송처리중"),
	SUCCESS("0002","전송처리중"),
	FAIL("0003","전송처리중");
	
	private String value;
	
	private String description;
	
	private MsgStatus(String value, String description) {
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
