package com.lgu.ccss.mgr.arrival.model.rcpt.search;

import java.util.List;

public class ResultArrivalSearchJSON {	
	
	private int totalCount;
	private List<DestListJSON> arrivalAlramList;
	
	public int getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}

	public List<DestListJSON> getArrivalAlramList() {
		return arrivalAlramList;
	}

	public void setArrivalAlramList(List<DestListJSON> arrivalAlramList) {
		this.arrivalAlramList = arrivalAlramList;
	}

	

}
