package com.lgu.common.esb.vo;

import java.util.List;

public class PauseLiftHistoryCollection {
	List<PauseLiftHistory> list;

	public List<PauseLiftHistory> getList() {
		return list;
	}

	public void setList(List<PauseLiftHistory> list) {
		this.list = list;
	}
	
	@Override
	public String toString() {
		StringBuffer stringBuffer = new StringBuffer();

		if (list != null) {
			for (int i = 0; i < list.size(); i++) {
				PauseLiftHistory pauseLiftHistory = list.get(i);

				stringBuffer.append(pauseLiftHistory.toString());
			}
		}

		return stringBuffer.toString();
	}
}
