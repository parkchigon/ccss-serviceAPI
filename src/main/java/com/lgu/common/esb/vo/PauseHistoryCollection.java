package com.lgu.common.esb.vo;

import java.util.List;

public class PauseHistoryCollection {
	/** 일시정지 내역 */
	List<PauseHistory> list;

	public List<PauseHistory> getList() {
		return list;
	}

	public void setList(List<PauseHistory> list) {
		this.list = list;
	}
	
	@Override
	public String toString() {
		StringBuffer stringBuffer = new StringBuffer();

		if (list != null) {
			for (int i = 0; i < list.size(); i++) {
				PauseHistory pauseHistory = list.get(i);

				stringBuffer.append(pauseHistory.toString());
			}
		}

		return stringBuffer.toString();
	}
}
