package com.lgu.ccss.common.model.datagift;

import java.util.Date;

public class RegisterVO {

	/*

	 */

	private String lgdOid;	
	private String lgdTid;
	private String issueNo;
	private String ctn;
	private String result;
	private String resultCode;
	private String resultMessage;
	private String giftNo;
	private String useYn;
	private Date regDt;
	private String regId;
	private Date updDt;
	private String updId;
	
	
	public RegisterVO()
	{
		
	}
	
	public RegisterVO(String lgdOid, 	String lgdTid, String issueNo, String ctn, String result, 
			String resultCode, String resultMessage, String giftNo, String useYn, Date regDt, 
			String regId, Date updDt, String updId )
	{
		this.lgdOid = lgdOid;	
		this.lgdTid = lgdTid;
		this.issueNo = issueNo;
		this.ctn = ctn;
		this.result = result;
		this.resultCode = resultCode;
		this.resultMessage = resultMessage;
		this.giftNo = giftNo;
		this.useYn = useYn;
		this.regDt = regDt;
		this.regId = regId;
		this.updDt = updDt;
		this.updId = updId;
	}

	
	
	public String getLgdOid() {
		return lgdOid;
	}

	public void setLgdOid(String lgdOid) {
		this.lgdOid = lgdOid;
	}

	public String getLgdTid() {
		return lgdTid;
	}

	public void setLgdTid(String lgdTid) {
		this.lgdTid = lgdTid;
	}

	public String getIssueNo() {
		return issueNo;
	}

	public void setIssueNo(String issueNo) {
		this.issueNo = issueNo;
	}

	public String getCtn() {
		return ctn;
	}

	public void setCtn(String ctn) {
		this.ctn = ctn;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public String getResultCode() {
		return resultCode;
	}

	public void setResultCode(String resultCode) {
		this.resultCode = resultCode;
	}

	public String getResultMessage() {
		return resultMessage;
	}

	public void setResultMessage(String resultMessage) {
		this.resultMessage = resultMessage;
	}

	public String getGiftNo() {
		return giftNo;
	}

	public void setGiftNo(String giftNo) {
		this.giftNo = giftNo;
	}

	public String getUseYn() {
		return useYn;
	}

	public void setUseYn(String useYn) {
		this.useYn = useYn;
	}

	public Date getRegDt() {
		return regDt;
	}

	public void setRegDt(Date regDt) {
		this.regDt = regDt;
	}

	public String getRegId() {
		return regId;
	}

	public void setRegId(String regId) {
		this.regId = regId;
	}

	public Date getUpdDt() {
		return updDt;
	}

	public void setUpdDt(Date updDt) {
		this.updDt = updDt;
	}

	public String getUpdId() {
		return updId;
	}

	public void setUpdId(String updId) {
		this.updId = updId;
	}

	@Override
	public String toString() {
		return String.format(
				"RegisterVO=[issueNo=%s, ctn=%s, result=%s, resultCode=%s, resultMessage=%s, giftNo=%s, useYn=%s, regDt=%s, regId=%s, updDt=%s, updId=%s]",
				issueNo, ctn, result, resultCode, resultMessage, giftNo, useYn, regDt, regId, updDt, updId);
	}
}
