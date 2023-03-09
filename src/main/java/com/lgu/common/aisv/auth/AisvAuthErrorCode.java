package com.lgu.common.aisv.auth;

import com.lgu.common.model.ResultCode;

public class AisvAuthErrorCode {

	public final static ResultCode RC_20000000 = new ResultCode("AUTH20000000","성공");
	
	public final static ResultCode RC_60000000 = new ResultCode("AUTH60000000","AISV_AUTH 연동 오류");
}
