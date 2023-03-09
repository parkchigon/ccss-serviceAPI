package com.lgu.ccss.common.model.datagift;

import java.util.Date;

public class DataGiftVO {
	
	public static String DATA_GIFT_STATE_PAYMENT = "P";   		// 결제완료 상태
	public static String DATA_GIFT_STATE_REGIST = "R";   		// 결재완료  > 발행등록완료 상태
	public static String DATA_GIFT_STATE_ROLLBACK_OK = "B";   	// 결제완료 > 발행등록실패 > 결재rollback완료 상태
	public static String DATA_GIFT_STATE_ROLLBACK_ERROR = "E";  // 결제완료 > 발행등록실패 > 결재rollback에러 상태
	public static String DATA_GIFT_STATE_CANCEL_OK = "C";   	// 결제완료 > 발행등록실패 > 결재rollback에러 > 결재취소완료 상태
	public static String DATA_GIFT_STATE_CANCEL_ERROR = "M";   	// 결제완료 > 발행등록실패 > 결재rollback에러 > 결재취소에러 상태
	
	
	public DataGiftVO()
	{
		
	}
	
	
	public DataGiftVO(String lgdOid, String lgdMid, String lgdTid, String lgdPayDate, String issueNo, String giftName, 
			String dataSize, String giftKub, String issueRegKup, String sellAmount, String issueName, 
			String campStartDt, String campEndDt, String ctn, String giftNo, String giftRegDt, String mgrAppId, String mgrAppCtn, String memberId, String carNum, 
			String useYn, Date regDt, String regId, Date updDt, String updId)
	{
		this.lgdOid = lgdOid;
		this.lgdMid = lgdMid;
		this.lgdTid = lgdTid;
		this.lgdPayDate = lgdPayDate;
		this.issueNo = issueNo;
		this.giftName = giftName;
		this.dataSize = dataSize;
		this.giftKub = giftKub;
		this.issueRegKup = issueRegKup;
		this.sellAmount = sellAmount;
		this.issueName = issueName;
		this.campStartDt = campStartDt;
		this.campEndDt = campEndDt;
		this.ctn = ctn;
		this.giftNo = giftNo;
		this.giftRegDt = giftRegDt;
		this.mgrAppId = mgrAppId;
		this.mgrAppCtn = mgrAppCtn;
		this.memberId = memberId;
		this.carNum = carNum;
		this.useYn = useYn;
		this.regDt = regDt;
		this.regId = regId;
		this.updDt = updDt;
		this.updId = updId;
		
	}
	
	private String lgdOid;
	private String lgdMid;
	private String lgdTid;
	private String lgdPayDate;			// ex) 20181007132938
	private String issueNo;
	private String giftName;
	private String dataSize;
	private String giftKub;
	private String issueRegKup;
	private String sellAmount;
	private String issueName;
	private String campStartDt;
	private String campEndDt;
	private String ctn;
	private String giftNo;
	private String giftRegDt;
	private String mgrAppId;
	private String mgrAppCtn;
	private String memberId;
	private String carNum;
	private String useYn;
	private Date regDt;
	private String regId;
	private Date updDt;
	private String updId;



	public String getLgdOid() {
		return lgdOid;
	}

	public void setLgdOid(String lgdOid) {
		this.lgdOid = lgdOid;
	}

	public String getLgdMid() {
		return lgdMid;
	}

	public void setLgdMid(String lgdMid) {
		this.lgdMid = lgdMid;
	}

	public String getLgdTid() {
		return lgdTid;
	}

	public void setLgdTid(String lgdTid) {
		this.lgdTid = lgdTid;
	}

	public String getLgdPayDate() {
		return lgdPayDate;
	}

	public void setLgdPayDate(String lgdPayDate) {
		this.lgdPayDate = lgdPayDate;
	}

	public String getIssueNo() {
		return issueNo;
	}

	public void setIssueNo(String issueNo) {
		this.issueNo = issueNo;
	}

	public String getGiftName() {
		return giftName;
	}

	public void setGiftName(String giftName) {
		this.giftName = giftName;
	}

	public String getDataSize() {
		return dataSize;
	}

	public void setDataSize(String dataSize) {
		this.dataSize = dataSize;
	}

	public String getGiftKub() {
		return giftKub;
	}

	public void setGiftKub(String giftKub) {
		this.giftKub = giftKub;
	}

	public String getIssueRegKup() {
		return issueRegKup;
	}

	public void setIssueRegKup(String issueRegKup) {
		this.issueRegKup = issueRegKup;
	}

	public String getSellAmount() {
		return sellAmount;
	}

	public void setSellAmount(String sellAmount) {
		this.sellAmount = sellAmount;
	}

	public String getIssueName() {
		return issueName;
	}

	public void setIssueName(String issueName) {
		this.issueName = issueName;
	}

	public String getCampStartDt() {
		return campStartDt;
	}

	public void setCampStartDt(String campStartDt) {
		this.campStartDt = campStartDt;
	}

	public String getCampEndDt() {
		return campEndDt;
	}

	public void setCampEndDt(String campEndDt) {
		this.campEndDt = campEndDt;
	}

	public String getCtn() {
		return ctn;
	}

	public void setCtn(String ctn) {
		this.ctn = ctn;
	}

	public String getGiftNo() {
		return giftNo;
	}

	public void setGiftNo(String giftNo) {
		this.giftNo = giftNo;
	}

	public String getGiftRegDt() {
		return giftRegDt;
	}

	public void setGiftRegDt(String giftRegDt) {
		this.giftRegDt = giftRegDt;
	}

	public String getMgrAppId() {
		return mgrAppId;
	}

	public void setMgrAppId(String mgrAppId) {
		this.mgrAppId = mgrAppId;
	}
	
	

	public String getMgrAppCtn() {
		return mgrAppCtn;
	}


	public void setMgrAppCtn(String mgrAppCtn) {
		this.mgrAppCtn = mgrAppCtn;
	}


	public String getMemberId() {
		return memberId;
	}


	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}


	public String getCarNum() {
		return carNum;
	}


	public void setCarNum(String carNum) {
		this.carNum = carNum;
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
				"DataGiftVO=[lgdOid=%s, lgdMid=%s, lgdTid=%s, lgdPayDate=%s, issueNo=%s, giftName=%s, dataSize=%s, giftKub=%s, \nissueRegKup=%s, sellAmount=%s, issueName=%s, campStartDt=%s, campEndDt=%s, ctn=%s, giftNo=%s, giftRegDt=%s, \nmgrAppId=%s, mgrAppCtn=%s, memberId=%s, carNum=%s, useYn=%s, regDt=%s, regId=%s, updDt=%s, updId=%s]",
				lgdOid, lgdMid, lgdTid, lgdPayDate, issueNo, giftName, dataSize, giftKub, issueRegKup, sellAmount,
				issueName, campStartDt, campEndDt, ctn, giftNo, giftRegDt, mgrAppId, mgrAppCtn, memberId, carNum, useYn, regDt, regId, updDt, updId);
	}

}
