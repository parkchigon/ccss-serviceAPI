package com.lgu.ccss.mgr.arrival.model.rcpt.search;

public class ResultArrivalSearchVO {	
	private String index;
	private String arrivalAlarmId;
	private String useYn;
	private String address;
	private String lonx;
	private String laty;
	private String arrivalAlarmRcptId;
	private String rcptNm;
	private String rcptCtn;
	private String membId;
	private String name;
	private String roadjibun;
	
	private int totalCount;
	
	
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

	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getLonx() {
		return lonx;
	}
	public void setLonx(String lonx) {
		this.lonx = lonx;
	}
	public String getLaty() {
		return laty;
	}
	public void setLaty(String laty) {
		this.laty = laty;
	}
	public String getArrivalAlarmRcptId() {
		return arrivalAlarmRcptId;
	}
	public void setArrivalAlarmRcptId(String arrivalAlarmRcptId) {
		this.arrivalAlarmRcptId = arrivalAlarmRcptId;
	}
	
	public String getRcptNm() {
		return rcptNm;
	}
	public void setRcptNm(String rcptNm) {
		this.rcptNm = rcptNm;
	}
	public String getRcptCtn() {
		return rcptCtn;
	}
	public void setRcptCtn(String rcptCtn) {
		this.rcptCtn = rcptCtn;
	}
	public String getMembId() {
		return membId;
	}
	public void setMembId(String membId) {
		this.membId = membId;
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
	
}
