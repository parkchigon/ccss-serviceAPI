package com.lgu.ccss.common.model;

public class Send2CarVO {
	
	public final static String SEND_STATUS_READY = "READY"; //최초저장상태
	public final static String SEND_STATUS_SCHEDULE = "SCHEDULE"; //일정관리상태
	public final static String SEND_STATUS_SEND = "SEND"; //Push 전송요청 상태
	public final static String SEND_STATUS_SENDING = "SENDING"; //Push 전송중
	public final static String SEND_STATUS_RECV = "RECV"; //Push 전송 완료상태
	public final static String SEND_STATUS_FINAL = "FINAL"; //네비 차량 목적지 설정
	public final static String SEND_STATUS_SLEEP = "SLEEP"; //PowerOff 등 Push 전송 불가
	public final static String SEND_STATUS_DELETE = "DELETE"; // 매니저앱 삭제
	
	private String rnum;
	private int totalCount;
	//private BigInteger sendToCarSeq;
	private String sendToCarId;
	private String membId;
	private String mgrappId;
	
	private String targetNm;
	private String targetAddress;
	private String targetLonx;
	private String targetLaty;
	
	private String targetRealLonx;
	private String targetRealLaty;
	
	private int poiId;
	private String roadName;
	private String roadJibun;
	private String searchOption;
	
	private String arrivHopeTime;
	private String estTime;
	private String sendStatus;
	private String useYn;
	private String excetYn;
	private String excepDesc;
	private String regId;
	private String regDt;
	private String updId;
	private String updDt;
	
	private String serviceType;
	private String rsvType;
	private String svrId;
	
	private String startPosition;
	private String reqCount;
	
	private String scheduleId;
	
	public String getRnum() {
		return rnum;
	}
	public void setRnum(String rnum) {
		this.rnum = rnum;
	}
	public int getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}

	public String getSendToCarId() {
		return sendToCarId;
	}
	public void setSendToCarId(String sendToCarId) {
		this.sendToCarId = sendToCarId;
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
	public String getEstTime() {
		return estTime;
	}
	public void setEstTime(String estTime) {
		this.estTime = estTime;
	}
	public String getSendStatus() {
		return sendStatus;
	}
	public void setSendStatus(String sendStatus) {
		this.sendStatus = sendStatus;
	}
	public String getUseYn() {
		return useYn;
	}
	public void setUseYn(String useYn) {
		this.useYn = useYn;
	}
	public String getExcetYn() {
		return excetYn;
	}
	public void setExcetYn(String excetYn) {
		this.excetYn = excetYn;
	}
	public String getExcepDesc() {
		return excepDesc;
	}
	public void setExcepDesc(String excepDesc) {
		this.excepDesc = excepDesc;
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
	
	public String getServiceType() {
		return serviceType;
	}
	public void setServiceType(String serviceType) {
		this.serviceType = serviceType;
	}
	public String getRsvType() {
		return rsvType;
	}
	public void setRsvType(String rsvType) {
		this.rsvType = rsvType;
	}
	public String getSvrId() {
		return svrId;
	}
	public void setSvrId(String svrId) {
		this.svrId = svrId;
	}
	
	
	public String getScheduleId() {
		return scheduleId;
	}
	public void setScheduleId(String scheduleId) {
		this.scheduleId = scheduleId;
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
	@Override
	public String toString() {
		return "Send2CarVO [rnum=" + rnum + ", totalCount=" + totalCount
				+ ", sendToCarId=" + sendToCarId + ", membId=" + membId
				+ ", mgrappId=" + mgrappId + ", targetNm=" + targetNm
				+ ", targetAddress=" + targetAddress + ", targetLonx="
				+ targetLonx + ", targetLaty=" + targetLaty
				+ ", targetRealLonx=" + targetRealLonx + ", targetRealLaty="
				+ targetRealLaty + ", poiId=" + poiId + ", roadName="
				+ roadName + ", roadJibun=" + roadJibun + ", arrivHopeTime="
				+ arrivHopeTime + ", estTime=" + estTime + ", sendStatus="
				+ sendStatus + ", useYn=" + useYn + ", excetYn=" + excetYn
				+ ", excepDesc=" + excepDesc + ", regId=" + regId + ", regDt="
				+ regDt + ", updId=" + updId + ", updDt=" + updDt
				+ ", serviceType=" + serviceType + ", rsvType=" + rsvType
				+ ", svrId=" + svrId + ", startPosition=" + startPosition
				+ ", reqCount=" + reqCount + ", scheduleId=" + scheduleId + "]";
	}

	
}
