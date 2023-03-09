package com.lgu.ccss.mgr.send2car.model.history;

import java.util.List;

public class ResultHistorySearchJSON {	
	
	private List<DestListJSON> destList;
	private int totalCount;
	
	
	public List<DestListJSON> getDestList() {
		return destList;
	}

	public void setDestList(List<DestListJSON> destList) {
		this.destList = destList;
	}

	public int getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}

	
}
