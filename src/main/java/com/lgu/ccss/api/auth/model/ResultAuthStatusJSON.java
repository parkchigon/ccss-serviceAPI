package com.lgu.ccss.api.auth.model;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResultAuthStatusJSON {	
	private String joinStatus;
	private String firstJoinStatus;
	private String joinDate;
	private String ratePayment;
	private String ratePaymentStatus;
	private String ratePaymentChangeStatus;
	private String ratePaymentResvStatus;
	private String dataStatus;
	
	public String getJoinStatus() {
		return joinStatus;
	}
	public void setJoinStatus(String joinStatus) {
		this.joinStatus = joinStatus;
	}
	public String getFirstJoinStatus() {
		return firstJoinStatus;
	}
	public void setFirstJoinStatus(String firstJoinStatus) {
		this.firstJoinStatus = firstJoinStatus;
	}
	public String getJoinDate() {
		return joinDate;
	}
	public void setJoinDate(String joinDate) {
		this.joinDate = joinDate;
	}
	public String getRatePayment() {
		return ratePayment;
	}
	public void setRatePayment(String ratePayment) {
		this.ratePayment = ratePayment;
	}
	public String getRatePaymentStatus() {
		return ratePaymentStatus;
	}
	public void setRatePaymentStatus(String ratePaymentStatus) {
		this.ratePaymentStatus = ratePaymentStatus;
	}
	public String getRatePaymentChangeStatus() {
		return ratePaymentChangeStatus;
	}
	public void setRatePaymentChangeStatus(String ratePaymentChangeStatus) {
		this.ratePaymentChangeStatus = ratePaymentChangeStatus;
	}
	public String getRatePaymentResvStatus() {
		return ratePaymentResvStatus;
	}
	public void setRatePaymentResvStatus(String ratePaymentResvStatus) {
		this.ratePaymentResvStatus = ratePaymentResvStatus;
	}
	public String getDataStatus() {
		return dataStatus;
	}
	public void setDataStatus(String dataStatus) {
		this.dataStatus = dataStatus;
	}
	
	@Override
	public String toString() {
		return "ResultAuthStatusJSON [joinStatus=" + joinStatus + ", firstJoinStatus=" + firstJoinStatus + ", joinDate="
				+ joinDate + ", ratePayment=" + ratePayment + ", ratePaymentStatus=" + ratePaymentStatus
				+ ", ratePaymentChangeStatus=" + ratePaymentChangeStatus + ", ratePaymentResvStatus="
				+ ratePaymentResvStatus + ", dataStatus=" + dataStatus + "]";
	}
}
