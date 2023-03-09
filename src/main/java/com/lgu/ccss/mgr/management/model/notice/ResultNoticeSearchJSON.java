package com.lgu.ccss.mgr.management.model.notice;

import java.util.List;

public class ResultNoticeSearchJSON {	
	
	private int totalCount =0;
	
	private List<ResultNoticeSearchVO> noticeList;

	public int getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}

	public List<ResultNoticeSearchVO> getNoticeList() {
		return noticeList;
	}

	public void setNoticeList(List<ResultNoticeSearchVO> noticeList) {
		this.noticeList = noticeList;
	}

	
}
