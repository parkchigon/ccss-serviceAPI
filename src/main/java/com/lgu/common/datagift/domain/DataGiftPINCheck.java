package com.lgu.common.datagift.domain;

public class DataGiftPINCheck extends DataGiftResult {
	// "Result": "success",
	// "ResultCode": "0000",
	// "ResultMessage": "등록 가능한 상품권번호입니다.",
	// "GiftNo": "NmpjTWNoZWdXWjF2emRpZ29pdVI0dz09",
	// "GiftName": "500MB",
	// "ExpirationDay": "365",
	// "GiftAmount": "10000"

	String GiftNo = "";
	String GiftName = "";
	String ExpirationDay = "";
	String GiftAmount = "";

	public String getGiftNo() {
		return GiftNo;
	}

	public void setGiftNo(String giftNo) {
		GiftNo = giftNo;
	}

	public String getGiftName() {
		return GiftName;
	}

	public void setGiftName(String giftName) {
		GiftName = giftName;
	}

	public String getExpirationDay() {
		return ExpirationDay;
	}

	public void setExpirationDay(String expirationDay) {
		ExpirationDay = expirationDay;
	}

	public String getGiftAmount() {
		return GiftAmount;
	}

	public void setGiftAmount(String giftAmount) {
		GiftAmount = giftAmount;
	}

}
