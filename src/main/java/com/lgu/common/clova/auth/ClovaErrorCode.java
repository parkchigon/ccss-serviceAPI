package com.lgu.common.clova.auth;

import com.lgu.common.model.ResultCode;

public class ClovaErrorCode {
	public final static ResultCode RC_20000000 = new ResultCode("CLOVA20000000","성공");
	
	public final static ResultCode RC_60000000 = new ResultCode("CLOVA60000000","CLOVA_AUTH 연동 오류");
}
