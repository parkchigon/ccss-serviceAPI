package com.lgu.ccss.common.model;

public class NoticeVO {
	
	public static final String SVC_EXPO_DEVICE = "D001";
	
	private String notiId;
	private String notiTitle;
	private String notiImportance;
	private String exposureYn;
	private String pushYn;
	private String serviceCategory;
	private String notiStartDt;
	private String notiEndDt;
	private String notiCont;

	private String notiType;
	private String serviceExposure;
	
	private String firmVer;
	private String contType;
	
	private String regId;
	private String regDt;
	private String updId;
	private String updDt;

	private String startPosition;
	private String reqCount;
	private String index;
	private int totalCount;
	
	public String getNotiId() {
		return notiId;
	}
	public void setNotiId(String notiId) {
		this.notiId = notiId;
	}
	public String getNotiTitle() {
		return notiTitle;
	}
	public void setNotiTitle(String notiTitle) {
		this.notiTitle = notiTitle;
	}
	public String getNotiImportance() {
		return notiImportance;
	}
	public void setNotiImportance(String notiImportance) {
		this.notiImportance = notiImportance;
	}
	public String getExposureYn() {
		return exposureYn;
	}
	public void setExposureYn(String exposureYn) {
		this.exposureYn = exposureYn;
	}
	public String getPushYn() {
		return pushYn;
	}
	public void setPushYn(String pushYn) {
		this.pushYn = pushYn;
	}
	public String getServiceCategory() {
		return serviceCategory;
	}
	public void setServiceCategory(String serviceCategory) {
		this.serviceCategory = serviceCategory;
	}
	public String getNotiStartDt() {
		return notiStartDt;
	}
	public void setNotiStartDt(String notiStartDt) {
		this.notiStartDt = notiStartDt;
	}
	public String getNotiEndDt() {
		return notiEndDt;
	}
	public void setNotiEndDt(String notiEndDt) {
		this.notiEndDt = notiEndDt;
	}
	public String getNotiCont() {
		return notiCont;
	}
	public void setNotiCont(String notiCont) {
		this.notiCont = notiCont;
	}
	public String getNotiType() {
		return notiType;
	}
	public void setNotiType(String notiType) {
		this.notiType = notiType;
	}
	public String getServiceExposure() {
		return serviceExposure;
	}
	public void setServiceExposure(String serviceExposure) {
		this.serviceExposure = serviceExposure;
	}
	public String getFirmVer() {
		return firmVer;
	}
	public void setFirmVer(String firmVer) {
		this.firmVer = firmVer;
	}
	public String getContType() {
		return contType;
	}
	public void setContType(String contType) {
		this.contType = contType;
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
	public String getIndex() {
		return index;
	}
	public void setIndex(String index) {
		this.index = index;
	}
	public int getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}
	
	@Override
	public String toString() {
		return "NoticeVO [notiId=" + notiId + ", notiTitle=" + notiTitle + ", notiImportance=" + notiImportance
				+ ", exposureYn=" + exposureYn + ", pushYn=" + pushYn + ", serviceCategory=" + serviceCategory
				+ ", notiStartDt=" + notiStartDt + ", notiEndDt=" + notiEndDt + ", notiCont=" + notiCont + ", notiType="
				+ notiType + ", serviceExposure=" + serviceExposure + ", firmVer=" + firmVer + ", contType=" + contType
				+ ", regId=" + regId + ", regDt=" + regDt + ", updId=" + updId + ", updDt=" + updDt + ", startPosition="
				+ startPosition + ", reqCount=" + reqCount + ", index=" + index + ", totalCount=" + totalCount + "]";
	}
}
