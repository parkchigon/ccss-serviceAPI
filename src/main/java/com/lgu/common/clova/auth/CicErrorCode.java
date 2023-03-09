package com.lgu.common.clova.auth;

import com.lgu.common.model.ResultCode;

public class CicErrorCode {
	public final static ResultCode RC_20000000 = new ResultCode("Cic20000000","성공");
	
	public final static ResultCode RC_30000000 = new ResultCode("Cic30000000","CIC Bad Request");
	
	public final static ResultCode RC_60000000 = new ResultCode("Cic60000000","CIC 연동 오류");
}
