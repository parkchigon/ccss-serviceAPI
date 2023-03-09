package com.lgu.ccss.mgr.schedule.model;

import java.util.List;

public class ResultScheduleSearchJSON {	
	
	private int totalCount =0;
	
	private List<ResultScheduleSetVO> scheduleList;

	public int getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}

	public List<ResultScheduleSetVO> getScheduleList() {
		return scheduleList;
	}

	public void setScheduleList(List<ResultScheduleSetVO> scheduleList) {
		this.scheduleList = scheduleList;
	}
	
	
	
}
