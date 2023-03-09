package com.lgu.ccss.common.enumtype.msg;

import com.lgu.ccss.common.enumtype.CcssEnum;

public enum MsgType implements CcssEnum{
	//메세지 전송타입
	SMS("SMS","SMS"),
	MGR_PUSH("MPUSH","매니저앱 PUSH"),
	CAR_PUSH("CPUSH","카 PUSH");
	
	private String value;
	private String description;
	
	private MsgType(String value, String description) {
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
