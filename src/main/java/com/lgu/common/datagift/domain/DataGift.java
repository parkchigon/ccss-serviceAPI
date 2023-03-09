package com.lgu.common.datagift.domain;

public class DataGift {
	// "Ctn": "01022334460",
	// "GiftName": "100MB",
	// "GiftNo": "Z6LYVC5KZ9M7W98ZPSB7",
	// "DataSize": "100",
	// "UsedDataSize": "0",
	// "StartDate": "2016-07-06",
	// "EndDate": "2017-07-06",
	// "PayTypeName": "상품권번호",
	// "CodeDesc": "이용중"

	String Ctn = "";
	String GiftName = "";
	String GiftNo = "";
	String DataSize = "";
	String UsedDataSize = "";
	String StartDate = "";
	String EndDate = "";
	String PayTypeName = "";
	String CodeDesc = "";

	public String getCtn() {
		return Ctn;
	}

	public void setCtn(String ctn) {
		Ctn = ctn;
	}

	public String getGiftName() {
		return GiftName;
	}

	public void setGiftName(String giftName) {
		GiftName = giftName;
	}

	public String getGiftNo() {
		return GiftNo;
	}

	public void setGiftNo(String giftNo) {
		GiftNo = giftNo;
	}

	public String getDataSize() {
		return DataSize;
	}

	public void setDataSize(String dataSize) {
		DataSize = dataSize;
	}

	public String getUsedDataSize() {
		return UsedDataSize;
	}

	public void setUsedDataSize(String usedDataSize) {
		UsedDataSize = usedDataSize;
	}

	public String getStartDate() {
		return StartDate;
	}

	public void setStartDate(String startDate) {
		StartDate = startDate;
	}

	public String getEndDate() {
		return EndDate;
	}

	public void setEndDate(String endDate) {
		EndDate = endDate;
	}

	public String getPayTypeName() {
		return PayTypeName;
	}

	public void setPayTypeName(String payTypeName) {
		PayTypeName = payTypeName;
	}

	public String getCodeDesc() {
		return CodeDesc;
	}

	public void setCodeDesc(String codeDesc) {
		CodeDesc = codeDesc;
	}

}
