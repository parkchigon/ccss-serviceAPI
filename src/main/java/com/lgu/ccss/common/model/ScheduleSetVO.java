package com.lgu.ccss.common.model;

import com.fasterxml.jackson.annotation.JsonIgnore;


public class ScheduleSetVO {
	
	private String index;
	
	private String scheduleId;
	private String membId;
	private String mgrappId;
	private String startNm;
	private String startAddress;
	private String startLonx;
	private String startLaty;
	private String targetNm;
	private String targetAddress;
	private String targetLonx;
	private String targetLaty;
	private String arrivHopeTime;

	private String repeatAlarmDay;
	private String useYn;
	private String regId;
	private String regDt;
	private String updId;
	private String updDt;
	private String sendDt;
	
	private String changeYn;
	
	
	private String targetRealLonx;
	private String targetRealLaty;
	
	private int poiId;
	private String roadName;
	private String roadJibun;
	private String searchOption;
	
	@JsonIgnore
	private String startPosition;
	@JsonIgnore
	private String reqCount;
	@JsonIgnore
	private int totalCount;
	
	private String svrId;
	
	private String startJibun;
	
	public String getIndex() {
		return index;
	}
	public void setIndex(String index) {
		this.index = index;
	}
	
	public String getScheduleId() {
		return scheduleId;
	}
	public void setScheduleId(String scheduleId) {
		this.scheduleId = scheduleId;
	}
	public String getMembId() {
		return membId;
	}
	public void setMembId(String membId) {
		this.membId = membId;
	}
	public String getMgrappId() {
		return mgrappId;
	}
	public void setMgrappId(String mgrappId) {
		this.mgrappId = mgrappId;
	}
	public String getStartNm() {
		return startNm;
	}
	public void setStartNm(String startNm) {
		this.startNm = startNm;
	}
	public String getStartAddress() {
		return startAddress;
	}
	public void setStartAddress(String startAddress) {
		this.startAddress = startAddress;
	}
	public String getStartLonx() {
		return startLonx;
	}
	public void setStartLonx(String startLonx) {
		this.startLonx = startLonx;
	}
	public String getStartLaty() {
		return startLaty;
	}
	public void setStartLaty(String startLaty) {
		this.startLaty = startLaty;
	}
	public String getTargetNm() {
		return targetNm;
	}
	public void setTargetNm(String targetNm) {
		this.targetNm = targetNm;
	}
	public String getTargetAddress() {
		return targetAddress;
	}
	public void setTargetAddress(String targetAddress) {
		this.targetAddress = targetAddress;
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
	
	public String getArrivHopeTime() {
		return arrivHopeTime;
	}
	public void setArrivHopeTime(String arrivHopeTime) {
		this.arrivHopeTime = arrivHopeTime;
	}
	public String getRepeatAlarmDay() {
		return repeatAlarmDay;
	}
	public void setRepeatAlarmDay(String repeatAlarmDay) {
		this.repeatAlarmDay = repeatAlarmDay;
	}
	public String getUseYn() {
		return useYn;
	}
	public void setUseYn(String useYn) {
		this.useYn = useYn;
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
	public String getSendDt() {
		return sendDt;
	}
	public void setSendDt(String sendDt) {
		this.sendDt = sendDt;
	}
	public String getSvrId() {
		return svrId;
	}
	public void setSvrId(String svrId) {
		this.svrId = svrId;
	}
	public String getChangeYn() {
		return changeYn;
	}
	public void setChangeYn(String changeYn) {
		this.changeYn = changeYn;
	}
	public String getTargetRealLonx() {
		return targetRealLonx;
	}
	public void setTargetRealLonx(String targetRealLonx) {
		this.targetRealLonx = targetRealLonx;
	}
	public String getTargetRealLaty() {
		return targetRealLaty;
	}
	public void setTargetRealLaty(String targetRealLaty) {
		this.targetRealLaty = targetRealLaty;
	}
	public int getPoiId() {
		return poiId;
	}
	public void setPoiId(int poiId) {
		this.poiId = poiId;
	}
	public String getRoadName() {
		return roadName;
	}
	public void setRoadName(String roadName) {
		this.roadName = roadName;
	}
	public String getRoadJibun() {
		return roadJibun;
	}
	public void setRoadJibun(String roadJibun) {
		this.roadJibun = roadJibun;
	}
	public String getSearchOption() {
		return searchOption;
	}
	public void setSearchOption(String searchOption) {
		this.searchOption = searchOption;
	}
	public String getStartJibun() {
		return startJibun;
	}
	public void setStartJibun(String startJibun) {
		this.startJibun = startJibun;
	}
	
	
	
}
