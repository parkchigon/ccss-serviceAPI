package com.lgu.ccss.api.datagift.model;

public class UsedGiftInfo {
	// "Ctn": "01022334460",
	// "GiftName": "100MB",
	// "GiftNo": "Z6LYVC5KZ9M7W98ZPSB7",
	// "DataSize": "100",
	// "UsedDataSize": "0",
	// "StartDate": "2016-07-06",
	// "EndDate": "2017-07-06",
	// "PayTypeName": "상품권번호",
	// "CodeDesc": "이용중"

	String ctn = "";
	String giftName = "";
	String giftNo = "";
	String dataSize = "";
	String usedDataSize = "";
	String startDate = "";
	String endDate = "";
	String payTypeName = "";
	String codeDesc = "";

	public String getCtn() {
		return this.ctn;
	}

	public void setCtn(String ctn) {
		this.ctn = ctn;
	}

	public String getGiftName() {
		return this.giftName;
	}

	public void setGiftName(String giftName) {
		this.giftName = giftName;
	}

	public String getGiftNo() {
		return this.giftNo;
	}

	public void setGiftNo(String giftNo) {
		this.giftNo = giftNo;
	}

	public String getDataSize() {
		return this.dataSize;
	}

	public void setDataSize(String dataSize) {
		this.dataSize = dataSize;
	}

	public String getUsedDataSize() {
		return this.usedDataSize;
	}

	public void setUsedDataSize(String usedDataSize) {
		this.usedDataSize = usedDataSize;
	}

	public String getStartDate() {
		return this.startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return this.endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public String getPayTypeName() {
		return this.payTypeName;
	}

	public void setPayTypeName(String payTypeName) {
		this.payTypeName = payTypeName;
	}

	public String getCodeDesc() {
		return this.codeDesc;
	}

	public void setCodeDesc(String codeDesc) {
		this.codeDesc = codeDesc;
	}

}
