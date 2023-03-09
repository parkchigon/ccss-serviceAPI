package com.lgu.common.datagift.domain;

import java.util.ArrayList;
import java.util.List;

public class DataGiftOwnListInfo {

	String DataGiftListCnt = "";

	List<DataGiftInfo> DataGiftListInfo = new ArrayList<DataGiftInfo>();

	public String getDataGiftListCnt() {
		return DataGiftListCnt;
	}

	public void setDataGiftListCnt(String dataGiftListCnt) {
		DataGiftListCnt = dataGiftListCnt;
	}

	public List<DataGiftInfo> getDataGiftListInfo() {
		return DataGiftListInfo;
	}

	public void setDataGiftListInfo(List<DataGiftInfo> dataGiftListInfo) {
		DataGiftListInfo = dataGiftListInfo;
	}
	
	
	
}
