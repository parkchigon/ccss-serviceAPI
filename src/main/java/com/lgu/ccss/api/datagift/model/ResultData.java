package com.lgu.ccss.api.datagift.model;

import java.util.ArrayList;
import java.util.List;

import com.lgu.common.datagift.domain.DataGift;
import com.lgu.common.datagift.domain.DataGiftOwnList;

public class ResultData {
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

	String dataGiftListCnt = "";

	List<UsedGiftInfo> dataGiftList = new ArrayList<UsedGiftInfo>();
	
	
	public ResultData(DataGiftOwnList dataGiftOwnList)
	{
		this.dataGiftListCnt = dataGiftOwnList.getDataGiftListCnt();
		
		List<DataGift> _dataGiftList = dataGiftOwnList.getDataGiftList();
		
		for(DataGift _dataGift : _dataGiftList)
		{
			UsedGiftInfo usedGiftInfo = new UsedGiftInfo();
			
			usedGiftInfo.setCtn(_dataGift.getCtn());
			usedGiftInfo.setGiftName(_dataGift.getGiftName());
			usedGiftInfo.setGiftNo(_dataGift.getGiftNo());
			usedGiftInfo.setDataSize(_dataGift.getDataSize());
			usedGiftInfo.setUsedDataSize(_dataGift.getUsedDataSize());
			usedGiftInfo.setStartDate(_dataGift.getStartDate());
			usedGiftInfo.setEndDate(_dataGift.getEndDate());
			usedGiftInfo.setPayTypeName(_dataGift.getPayTypeName());
			usedGiftInfo.setCodeDesc(_dataGift.getCodeDesc());
			
			this.dataGiftList.add(usedGiftInfo);
		}
	}

	public String getDataGiftListCnt() {
		return this.dataGiftListCnt;
	}

	public void setDataGiftListCnt(String dataGiftListCnt) {
		this.dataGiftListCnt = dataGiftListCnt;
	}

	public List<UsedGiftInfo> getDataGiftList() {
		return this.dataGiftList;
	}

	public void setDataGiftList(List<UsedGiftInfo> dataGiftList) {
		this.dataGiftList = dataGiftList;
	}

}
