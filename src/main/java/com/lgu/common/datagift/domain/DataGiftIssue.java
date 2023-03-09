package com.lgu.common.datagift.domain;

public class DataGiftIssue extends DataGiftResult {

	// "Result": "success",
	// "ResultCode": "0000",
	// "ResultMessage": "성공",
	// "GiftNo": "reDGWdG354gfewre234WFAWGRAEGWAFESGFERG"

	String GiftNo = "";

	public String getGiftNo() {
		return GiftNo;
	}

	public void setGiftNo(String giftNo) {
		GiftNo = giftNo;
	}
}
