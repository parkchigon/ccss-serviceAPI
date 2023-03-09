package com.lgu.common.datagift.domain;

public class Campaign {
	// "GiftName": "데이터상품권500Mb",
	// "DataSize": "500",
	// "GiftKub": "N",
	// "IssueRegKub": "I",
	// "SellAmount": "5000",
	// "IssueName": "OOOO 이벤트",
	// "IssueNo": "11223344556677889900",
	// "CampStartDate": "2014-01-01",
	// "CampEndDate": "2014-01-15",
	// "IssueCnt": "5",
	// "RemainCnt": "95"

	// @JsonProperty("GiftName")
	String GiftName = "";
	String DataSize = "";
	String GiftKub = "";
	String IssueRegKub = "";
	String SellAmount = "";
	String IssueName = "";
	String IssueNo = "";
	String CampStartDate = "";
	String CampEndDate = "";
	String IssueCnt = "";
	String RemainCnt = "";

	public String getGiftName() {
		return GiftName;
	}

	public void setGiftName(String giftName) {
		GiftName = giftName;
	}

	public String getDataSize() {
		return DataSize;
	}

	public void setDataSize(String dataSize) {
		DataSize = dataSize;
	}

	public String getGiftKub() {
		return GiftKub;
	}

	public void setGiftKub(String giftKub) {
		GiftKub = giftKub;
	}

	public String getIssueRegKub() {
		return IssueRegKub;
	}

	public void setIssueRegKub(String issueRegKub) {
		IssueRegKub = issueRegKub;
	}

	public String getSellAmount() {
		return SellAmount;
	}

	public void setSellAmount(String sellAmount) {
		SellAmount = sellAmount;
	}

	public String getIssueName() {
		return IssueName;
	}

	public void setIssueName(String issueName) {
		IssueName = issueName;
	}

	public String getIssueNo() {
		return IssueNo;
	}

	public void setIssueNo(String issueNo) {
		IssueNo = issueNo;
	}

	public String getCampStartDate() {
		return CampStartDate;
	}

	public void setCampStartDate(String campStartDate) {
		CampStartDate = campStartDate;
	}

	public String getCampEndDate() {
		return CampEndDate;
	}

	public void setCampEndDate(String campEndDate) {
		CampEndDate = campEndDate;
	}

	public String getIssueCnt() {
		return IssueCnt;
	}

	public void setIssueCnt(String issueCnt) {
		IssueCnt = issueCnt;
	}

	public String getRemainCnt() {
		return RemainCnt;
	}

	public void setRemainCnt(String remainCnt) {
		RemainCnt = remainCnt;
	}
}
