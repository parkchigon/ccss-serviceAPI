package com.lgu.ccss.common.model.datagift;

import java.util.Date;

public class PaymentVO {

	
	public static String PAY_TYPE_REQUEST = "P";
	public static String PAY_TYPE_ROLLBACK = "R";
	public static String PAY_TYPE_CANCEL = "C";
	
	public static String TX_NAME_REQUEST = "PaymentByKey";
	public static String TX_NAME_CANCEL = "Cancel";
	
	/*

	 */
	private String lgdOid;
	private String issueNo;
	private String payType;
	private String memberId;
	private String cstPlatform;
	private String lgdMid;
	private String lgdAmount;
	private String lgdBuyer;
	private String lgdPrdtInfo;
	private String lgdTimestamp;
	private String lgdReturnurl;
	private String buyerAddress;
	private String buyerPhone;
	private String buyerMail;
	private String lgdCustomFirstpay;
	private String lgdCustomProcessType;
	private String lgdLanguage;
	private String lgdKvpmIspAutoAppYn;
	private String lgdKvpmIspWapUrl;
	private String lgdKvpmIspCancelUrl;
	private String lgdMpiLotteAppCardWapUrl;
	private String lgdPayKey;
	private String lgdTxName;
	private String lgdTid;
	private String lgdPayType;
	private String lgdPayDate;
	private String lgdFinanceCode;
	private String lgdFinanceName;
	private String lgdCardNum;
	private String lgdCardInstallMonth;
	private String lgdCardNoIntYn;
	private String lgdFinanceAuthNum;
	private String lgdRespCode;
	private String lgdRespMsg;
	private String useYn;
	private Date regDt;
	private String regId;
	private Date updDt;
	private String updId;

	public PaymentVO()
	{
		
	}
	
	public PaymentVO(String lgdOid, String issueNo, String payType, String memberId, String cstPlatform, String lgdMid, String lgdAmount, String lgdBuyer
			, String lgdPrdtInfo, String lgdTimestamp
			, String lgdReturnurl, String buyerAddress, String buyerPhone, String buyerMail, String lgdCustomFirstpay
			, String lgdCustomProcessType, String lgdLanguage, String lgdKvpmIspAutoAppYn, String lgdKvpmIspWapUrl
			, String lgdKvpmIspCancelUrl
			, String lgdMpiLotteAppCardWapUrl, String lgdPayKey, String lgdTxName, String lgdTid, String lgdPayType
			, String lgdPayDate, String lgdFinanceCode, String lgdFinanceName, String lgdCardNum
			, String lgdCardInstallMonth, String lgdCardNoIntYn, String lgdFinanceAuthNum, String lgdRespCode, String lgdRespMsg
			, String useYn, Date regDt, String regId, Date updDt, String updId)
	{
		this.lgdOid = lgdOid;
		this.issueNo = issueNo;
		this.payType = payType;
		this.memberId = memberId;
		this.cstPlatform = cstPlatform;
		this.lgdMid = lgdMid;
		this.lgdAmount = lgdAmount;
		this.lgdBuyer = lgdBuyer;
		this.lgdPrdtInfo = lgdPrdtInfo;
		this.lgdTimestamp = lgdTimestamp;
		this.lgdReturnurl = lgdReturnurl;
		this.buyerAddress = buyerAddress;
		this.buyerPhone = buyerPhone;
		this.buyerMail = buyerMail;
		this.lgdCustomFirstpay = lgdCustomFirstpay;
		this.lgdCustomProcessType = lgdCustomProcessType;
		this.lgdLanguage = lgdLanguage;
		this.lgdKvpmIspAutoAppYn = lgdKvpmIspAutoAppYn;
		this.lgdKvpmIspWapUrl = lgdKvpmIspWapUrl;
		this.lgdKvpmIspCancelUrl = lgdKvpmIspCancelUrl;
		this.lgdMpiLotteAppCardWapUrl = lgdMpiLotteAppCardWapUrl;
		this.lgdPayKey = lgdPayKey;
		this.lgdTxName = lgdTxName;
		this.lgdTid = lgdTid;
		this.lgdPayType = lgdPayType;
		this.lgdPayDate = lgdPayDate;
		this.lgdFinanceCode = lgdFinanceCode;
		this.lgdFinanceName = lgdFinanceName;
		this.lgdCardNum = lgdCardNum;
		this.lgdCardInstallMonth = lgdCardInstallMonth;
		this.lgdCardNoIntYn = lgdCardNoIntYn;
		this.lgdFinanceAuthNum = lgdFinanceAuthNum;
		this.lgdRespCode = lgdRespCode;
		this.lgdRespMsg = lgdRespMsg;
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

	public String getIssueNo() {
		return issueNo;
	}

	public void setIssueNo(String issueNo) {
		this.issueNo = issueNo;
	}

	public String getPayType() {
		return payType;
	}

	public void setPayType(String payType) {
		this.payType = payType;
	}

	public String getMemberId() {
		return memberId;
	}

	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}

	public String getCstPlatform() {
		return cstPlatform;
	}

	public void setCstPlatform(String cstPlatform) {
		this.cstPlatform = cstPlatform;
	}

	public String getLgdMid() {
		return lgdMid;
	}

	public void setLgdMid(String lgdMid) {
		this.lgdMid = lgdMid;
	}

	public String getLgdAmount() {
		return lgdAmount;
	}

	public void setLgdAmount(String lgdAmount) {
		this.lgdAmount = lgdAmount;
	}

	public String getLgdBuyer() {
		return lgdBuyer;
	}

	public void setLgdBuyer(String lgdBuyer) {
		this.lgdBuyer = lgdBuyer;
	}

	public String getLgdPrdtInfo() {
		return lgdPrdtInfo;
	}

	public void setLgdPrdtInfo(String lgdPrdtInfo) {
		this.lgdPrdtInfo = lgdPrdtInfo;
	}

	public String getLgdTimestamp() {
		return lgdTimestamp;
	}

	public void setLgdTimestamp(String lgdTimestamp) {
		this.lgdTimestamp = lgdTimestamp;
	}

	public String getLgdReturnurl() {
		return lgdReturnurl;
	}

	public void setLgdReturnurl(String lgdReturnurl) {
		this.lgdReturnurl = lgdReturnurl;
	}

	public String getBuyerAddress() {
		return buyerAddress;
	}

	public void setBuyerAddress(String buyerAddress) {
		this.buyerAddress = buyerAddress;
	}

	public String getBuyerPhone() {
		return buyerPhone;
	}

	public void setBuyerPhone(String buyerPhone) {
		this.buyerPhone = buyerPhone;
	}

	public String getBuyerMail() {
		return buyerMail;
	}

	public void setBuyerMail(String buyerMail) {
		this.buyerMail = buyerMail;
	}

	public String getLgdCustomFirstpay() {
		return lgdCustomFirstpay;
	}

	public void setLgdCustomFirstpay(String lgdCustomFirstpay) {
		this.lgdCustomFirstpay = lgdCustomFirstpay;
	}

	public String getLgdCustomProcessType() {
		return lgdCustomProcessType;
	}

	public void setLgdCustomProcessType(String lgdCustomProcessType) {
		this.lgdCustomProcessType = lgdCustomProcessType;
	}

	public String getLgdLanguage() {
		return lgdLanguage;
	}

	public void setLgdLanguage(String lgdLanguage) {
		this.lgdLanguage = lgdLanguage;
	}

	public String getLgdKvpmIspAutoAppYn() {
		return lgdKvpmIspAutoAppYn;
	}

	public void setLgdKvpmIspAutoAppYn(String lgdKvpmIspAutoAppYn) {
		this.lgdKvpmIspAutoAppYn = lgdKvpmIspAutoAppYn;
	}

	public String getLgdKvpmIspWapUrl() {
		return lgdKvpmIspWapUrl;
	}

	public void setLgdKvpmIspWapUrl(String lgdKvpmIspWapUrl) {
		this.lgdKvpmIspWapUrl = lgdKvpmIspWapUrl;
	}

	public String getLgdKvpmIspCancelUrl() {
		return lgdKvpmIspCancelUrl;
	}

	public void setLgdKvpmIspCancelUrl(String lgdKvpmIspCancelUrl) {
		this.lgdKvpmIspCancelUrl = lgdKvpmIspCancelUrl;
	}

	public String getLgdMpiLotteAppCardWapUrl() {
		return lgdMpiLotteAppCardWapUrl;
	}

	public void setLgdMpiLotteAppCardWapUrl(String lgdMpiLotteAppCardWapUrl) {
		this.lgdMpiLotteAppCardWapUrl = lgdMpiLotteAppCardWapUrl;
	}

	public String getLgdPayKey() {
		return lgdPayKey;
	}

	public void setLgdPayKey(String lgdPayKey) {
		this.lgdPayKey = lgdPayKey;
	}

	public String getLgdTxName() {
		return lgdTxName;
	}

	public void setLgdTxName(String lgdTxName) {
		this.lgdTxName = lgdTxName;
	}

	public String getLgdTid() {
		return lgdTid;
	}

	public void setLgdTid(String lgdTid) {
		this.lgdTid = lgdTid;
	}

	public String getLgdPayType() {
		return lgdPayType;
	}

	public void setLgdPayType(String lgdPayType) {
		this.lgdPayType = lgdPayType;
	}

	public String getLgdPayDate() {
		return lgdPayDate;
	}

	public void setLgdPayDate(String lgdPayDate) {
		this.lgdPayDate = lgdPayDate;
	}

	public String getLgdFinanceCode() {
		return lgdFinanceCode;
	}

	public void setLgdFinanceCode(String lgdFinanceCode) {
		this.lgdFinanceCode = lgdFinanceCode;
	}

	public String getLgdFinanceName() {
		return lgdFinanceName;
	}

	public void setLgdFinanceName(String lgdFinanceName) {
		this.lgdFinanceName = lgdFinanceName;
	}

	public String getLgdCardNum() {
		return lgdCardNum;
	}

	public void setLgdCardNum(String lgdCardNum) {
		this.lgdCardNum = lgdCardNum;
	}

	public String getLgdCardInstallMonth() {
		return lgdCardInstallMonth;
	}

	public void setLgdCardInstallMonth(String lgdCardInstallMonth) {
		this.lgdCardInstallMonth = lgdCardInstallMonth;
	}

	public String getLgdCardNoIntYn() {
		return lgdCardNoIntYn;
	}

	public void setLgdCardNoIntYn(String lgdCardNoIntYn) {
		this.lgdCardNoIntYn = lgdCardNoIntYn;
	}

	public String getLgdFinanceAuthNum() {
		return lgdFinanceAuthNum;
	}

	public void setLgdFinanceAuthNum(String lgdFinanceAuthNum) {
		this.lgdFinanceAuthNum = lgdFinanceAuthNum;
	}

	public String getLgdRespCode() {
		return lgdRespCode;
	}

	public void setLgdRespCode(String lgdRespCode) {
		this.lgdRespCode = lgdRespCode;
	}

	public String getLgdRespMsg() {
		return lgdRespMsg;
	}

	public void setLgdRespMsg(String lgdRespMsg) {
		this.lgdRespMsg = lgdRespMsg;
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
				"PaymentVO[lgdOid=%s, issueNo=%s, payType=%s, memberId=%s, cstPlatform=%s, lgdMid=%s, lgdAmount=%s, lgdBuyer=%s, \nlgdPrdtInfo=%s, lgdTimestamp=%s, lgdReturnurl=%s, buyerAddress=%s, buyerPhone=%s, buyerMail=%s, lgdCustomFirstpay=%s, \nlgdCustomProcessType=%s, lgdLanguage=%s, lgdKvpmIspAutoAppYn=%s, lgdKvpmIspWapUrl=%s, lgdKvpmIspCancelUrl=%s, lgdPayKey=%s, \nlgdTxName=%s, lgdTid=%s, lgdPayType=%s, lgdPayDate=%s, lgdFinanceCode=%s, lgdFinanceName=%s, lgdCardNum=%s, \nlgdCardInstallMonth=%s, lgdCardNoIntYn=%s, lgdFinanceAuthNum=%s, lgdRespCode=%s, lgdRespMsg=%s, useYn=%s, \nregDt=%s, regId=%s, updDt=%s, updId=%s]",
				lgdOid, issueNo, payType, memberId, cstPlatform, lgdMid, lgdAmount, lgdBuyer, lgdPrdtInfo, lgdTimestamp,
				lgdReturnurl, buyerAddress, buyerPhone, buyerMail, lgdCustomFirstpay, lgdCustomProcessType, lgdLanguage,
				lgdKvpmIspAutoAppYn, lgdKvpmIspWapUrl, lgdKvpmIspCancelUrl, lgdPayKey, lgdTxName, lgdTid, lgdPayType,
				lgdPayDate, lgdFinanceCode, lgdFinanceName, lgdCardNum, lgdCardInstallMonth, lgdCardNoIntYn,
				lgdFinanceAuthNum, lgdRespCode, lgdRespMsg, useYn, regDt, regId, updDt, updId);
	}
}
