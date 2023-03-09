package com.lgu.common.datagift.domain;

import java.util.List;

public class DataGiftList extends DataGiftResult {
	// "Result": "success",
	// "ResultCode": "0000",
	// "ResultMessage": "성공",
	// "CampaignCnt": "2",
	// "CampaignList": [
	// {
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
	// },
	// {
	// "GiftName": "데이터상품권100Mb",
	// "DataSize": "100",
	// "GiftKub": "Y",
	// "IssueRegKub": "I",
	// "SellAmount": "1000",
	// "IssueName": "OOOO 이벤트",
	// "IssueNo": "11223344556677113322",
	// "CampStartDate": "2014-01-16",
	// "CampEndDate": "2014-01-30",
	// "IssueCnt": "5",
	// "RemainCnt": "95"
	// }
	// ]

	String CampaignCnt;

	List<Campaign> CampaignList;

	public String getCampaignCnt() {
		return CampaignCnt;
	}

	public void setCampaignCnt(String campaignCnt) {
		CampaignCnt = campaignCnt;
	}

	public List<Campaign> getCampaignList() {
		return CampaignList;
	}

	public void setCampaignList(List<Campaign> campaignList) {
		CampaignList = campaignList;
	}

}
