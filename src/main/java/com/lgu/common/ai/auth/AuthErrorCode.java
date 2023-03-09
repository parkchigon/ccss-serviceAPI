package com.lgu.common.ai.auth;

import com.lgu.common.model.ResultCode;

public class AuthErrorCode {
	public final static ResultCode RC_20000000 = new ResultCode("AUTH20000000","성공");
	
	public final static ResultCode RC_60000000 = new ResultCode("AUTH60000000","AI_AUTH 연동 오류");
}
