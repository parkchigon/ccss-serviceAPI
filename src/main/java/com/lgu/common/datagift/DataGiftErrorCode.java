package com.lgu.common.datagift;

import com.lgu.common.model.ResultCode;

public class DataGiftErrorCode {
	public final static ResultCode RC_20000000 = new ResultCode("DG20000000","성공");
	
	public final static ResultCode RC_30000000 = new ResultCode("DG30000000","DataGift Bad Request");
	
	public final static ResultCode RC_60000000 = new ResultCode("DG60000000","DataGift 연동 오류");
}
