package com.lgu.common.esb.vo;

import java.util.List;

public class PauseHistoryCollectionVO {

	/** 일시정지 내역 */
	List<PauseHistoryVO> list;

	public List<PauseHistoryVO> getList() {
		return list;
	}

	public void setList(List<PauseHistoryVO> list) {
		this.list = list;
	}

	@Override
	public String toString() {
		StringBuffer stringBuffer = new StringBuffer();

		if (list != null) {
			for (int i = 0; i < list.size(); i++) {
				PauseHistoryVO pauseHistoryVO = list.get(i);

				stringBuffer.append(pauseHistoryVO.toString());
			}
		}

		return stringBuffer.toString();
	}
}
