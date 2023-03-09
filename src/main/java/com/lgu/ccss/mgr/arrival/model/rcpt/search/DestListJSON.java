package com.lgu.ccss.mgr.arrival.model.rcpt.search;

import java.util.List;

import com.lgu.ccss.common.model.RequestParamTargetJSON;

public class DestListJSON {	
	private String index;
	
	private String arrivalAlarmId;
	
	private String useYn;
	
	private RequestParamTargetJSON target;
	
	private List<ReceiverJSON> receiver;

	
	public String getIndex() {
		return index;
	}

	public void setIndex(String index) {
		this.index = index;
	}

	public String getArrivalAlarmId() {
		return arrivalAlarmId;
	}

	public void setArrivalAlarmId(String arrivalAlarmId) {
		this.arrivalAlarmId = arrivalAlarmId;
	}

	public String getUseYn() {
		return useYn;
	}

	public void setUseYn(String useYn) {
		this.useYn = useYn;
	}

	public RequestParamTargetJSON getTarget() {
		return target;
	}

	public void setTarget(RequestParamTargetJSON target) {
		this.target = target;
	}

	public List<ReceiverJSON> getReceiver() {
		return receiver;
	}

	public void setReceiver(List<ReceiverJSON> receiver) {
		this.receiver = receiver;
	}

	
}
