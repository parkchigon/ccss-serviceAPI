package com.lgu.common.cekAi.auth;

import com.lgu.common.model.ResultCode;

public class CekAuthErrorCode {
	public final static ResultCode RC_20000000 = new ResultCode("AUTH20000000","성공");
	
	public final static ResultCode RC_60000000 = new ResultCode("AUTH60000000","CEK_AI_AUTH 연동 오류");
}
