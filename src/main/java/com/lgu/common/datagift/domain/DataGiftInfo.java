package com.lgu.common.datagift.domain;

public class DataGiftInfo implements Comparable<DataGiftInfo>{

	String ctn = "";
	String giftName = "";
	String giftNo = "";
	String dataSize = "";
	String usedDataSize = "";
	String startDate = "";
	String endDate = "";
	String payTypeName = "";
	String codeDesc = "";
	String sellAmount = "";
	
	String payDate = "";
	String carNumber = "";
	String state = "";
	String issueRegKup = "";
	String tid = "";
	String issueNo = "";
	String lgdOid = "";
	String memberId = "";
	String payDateTime = "";
	
	
	public String getCtn() {
		return ctn;
	}
	public void setCtn(String ctn) {
		this.ctn = ctn;
	}
	public String getGiftName() {
		return giftName;
	}
	public void setGiftName(String giftName) {
		this.giftName = giftName;
	}
	public String getGiftNo() {
		return giftNo;
	}
	public void setGiftNo(String giftNo) {
		this.giftNo = giftNo;
	}
	public String getDataSize() {
		return dataSize;
	}
	public void setDataSize(String dataSize) {
		this.dataSize = dataSize;
	}
	public String getUsedDataSize() {
		return usedDataSize;
	}
	public void setUsedDataSize(String usedDataSize) {
		this.usedDataSize = usedDataSize;
	}
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	public String getPayTypeName() {
		return payTypeName;
	}
	public void setPayTypeName(String payTypeName) {
		this.payTypeName = payTypeName;
	}
	public String getCodeDesc() {
		return codeDesc;
	}
	public void setCodeDesc(String codeDesc) {
		this.codeDesc = codeDesc;
	}
	public String getSellAmount() {
		return sellAmount;
	}
	public void setSellAmount(String sellAmount) {
		this.sellAmount = sellAmount;
	}
	public String getPayDate() {
		return payDate;
	}
	public void setPayDate(String payDate) {
		this.payDate = payDate;
	}
	public String getCarNumber() {
		return carNumber;
	}
	public void setCarNumber(String carNumber) {
		this.carNumber = carNumber;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getIssueRegKup() {
		return issueRegKup;
	}
	public void setIssueRegKup(String issueRegKup) {
		this.issueRegKup = issueRegKup;
	}
	public String getTid() {
		return tid;
	}
	public void setTid(String tid) {
		this.tid = tid;
	}
	public String getIssueNo() {
		return issueNo;
	}
	public void setIssueNo(String issueNo) {
		this.issueNo = issueNo;
	}
	public String getLgdOid() {
		return lgdOid;
	}
	public void setLgdOid(String lgdOid) {
		this.lgdOid = lgdOid;
	}
	public String getMemberId() {
		return memberId;
	}
	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}
	
	
	public String getPayDateTime() {
		return payDateTime;
	}
	public void setPayDateTime(String payDateTime) {
		this.payDateTime = payDateTime;
	}
	@Override
	public int compareTo(DataGiftInfo o) {
		
		return this.payDateTime.compareTo(o.payDateTime);
	}
}
