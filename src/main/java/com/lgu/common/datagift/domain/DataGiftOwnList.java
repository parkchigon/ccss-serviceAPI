package com.lgu.common.datagift.domain;

import java.util.ArrayList;
import java.util.List;

public class DataGiftOwnList extends DataGiftResult {
	// {
	// "Result": "success",
	// "ResultCode": "0000",
	// "ResultMessage": "상품권 조회 성공",
	// "DataGiftListCnt": "1",
	// "DataGiftList": [
	// {
	// "Ctn": "01022334460",
	// "GiftName": "100MB",
	// "GiftNo": "Z6LYVC5KZ9M7W98ZPSB7",
	// "DataSize": "100",
	// "UsedDataSize": "0",
	// "StartDate": "2016-07-06",
	// "EndDate": "2017-07-06",
	// "PayTypeName": "상품권번호",
	// "CodeDesc": "이용중"
	// }
	// ]
	// }

	String DataGiftListCnt = "";

	List<DataGift> DataGiftList = new ArrayList<DataGift>();

	public String getDataGiftListCnt() {
		return DataGiftListCnt;
	}

	public void setDataGiftListCnt(String dataGiftListCnt) {
		DataGiftListCnt = dataGiftListCnt;
	}

	public List<DataGift> getDataGiftList() {
		return DataGiftList;
	}

	public void setDataGiftList(List<DataGift> dataGiftList) {
		DataGiftList = dataGiftList;
	}

}
