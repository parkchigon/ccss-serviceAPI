package com.lgu.ccss.common.model;

import com.fasterxml.jackson.annotation.JsonIgnore;



public class ArrivalAlarmSetVO {
	
	private String arrivalAlarmId;
	private String membId;
	private String useYn;
	private String targetAddr;
	private String targetLonx;
	private String targetLaty;
	private String regId;
	private String regDt;
	private String updId;
	private String updDt;
	private String name;
	private String roadjibun;
	
	
	@JsonIgnore
	private String startPosition;
	@JsonIgnore
	private String reqCount;
	@JsonIgnore
	private int totalCount;
	
	public String getArrivalAlarmId() {
		return arrivalAlarmId;
	}
	public void setArrivalAlarmId(String arrivalAlarmId) {
		this.arrivalAlarmId = arrivalAlarmId;
	}
	public String getMembId() {
		return membId;
	}
	public void setMembId(String membId) {
		this.membId = membId;
	}
	public String getUseYn() {
		return useYn;
	}
	public void setUseYn(String useYn) {
		this.useYn = useYn;
	}
	public String getTargetAddr() {
		return targetAddr;
	}
	public void setTargetAddr(String targetAddr) {
		this.targetAddr = targetAddr;
	}
	public String getTargetLonx() {
		return targetLonx;
	}
	public void setTargetLonx(String targetLonx) {
		this.targetLonx = targetLonx;
	}
	public String getTargetLaty() {
		return targetLaty;
	}
	public void setTargetLaty(String targetLaty) {
		this.targetLaty = targetLaty;
	}
	public String getRegId() {
		return regId;
	}
	public void setRegId(String regId) {
		this.regId = regId;
	}
	public String getRegDt() {
		return regDt;
	}
	public void setRegDt(String regDt) {
		this.regDt = regDt;
	}
	public String getUpdId() {
		return updId;
	}
	
	public void setUpdId(String updId) {
		this.updId = updId;
	}
	public String getUpdDt() {
		return updDt;
	}
	public void setUpdDt(String updDt) {
		this.updDt = updDt;
	}
	public String getStartPosition() {
		return startPosition;
	}
	public void setStartPosition(String startPosition) {
		this.startPosition = startPosition;
	}
	public String getReqCount() {
		return reqCount;
	}
	public void setReqCount(String reqCount) {
		this.reqCount = reqCount;
	}
	public int getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getRoadjibun() {
		return roadjibun;
	}
	public void setRoadjibun(String roadjibun) {
		this.roadjibun = roadjibun;
	}
	@Override
	public String toString() {
		return "ArrivalAlarmSetVO [arrivalAlarmId=" + arrivalAlarmId
				+ ", membId=" + membId + ", useYn=" + useYn + ", targetAddr="
				+ targetAddr + ", targetLonx=" + targetLonx + ", targetLaty="
				+ targetLaty + ", regId=" + regId + ", regDt=" + regDt
				+ ", updId=" + updId + ", updDt=" + updDt + ", startPosition="
				+ startPosition + ", reqCount=" + reqCount + ", totalCount="
				+ totalCount + "]";
	}
	
	
}
