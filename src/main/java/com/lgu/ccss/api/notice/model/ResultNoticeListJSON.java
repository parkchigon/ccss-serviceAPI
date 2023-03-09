package com.lgu.ccss.api.notice.model;

import java.util.ArrayList;
import java.util.List;

public class ResultNoticeListJSON {
	private List<NoticeListData> notice = new ArrayList<NoticeListData>();

	public List<NoticeListData> getNotice() {
		return notice;
	}

	public void setNotice(List<NoticeListData> notice) {
		this.notice = notice;
	}

	@Override
	public String toString() {
		return "ResultNoticeListJSON [notice=" + notice + "]";
	}
}
