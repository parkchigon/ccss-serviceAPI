package com.lgu.common.datagift.domain;

public class CampaignInfo implements Comparable<CampaignInfo> {

	String giftName = "";
	String dataSize = "";
	String giftKub = "";
	String issueRegKub = "";
	String sellAmount = "";
	String issueName = "";
	String issueNo = "";
	String campStartDate = "";
	String campEndDate = "";
	String issueCnt = "";
	String remainCnt = "";
	
	
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
	public String getIssueRegKub() {
		return issueRegKub;
	}
	public void setIssueRegKub(String issueRegKub) {
		this.issueRegKub = issueRegKub;
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
	public String getIssueNo() {
		return issueNo;
	}
	public void setIssueNo(String issueNo) {
		this.issueNo = issueNo;
	}
	public String getCampStartDate() {
		return campStartDate;
	}
	public void setCampStartDate(String campStartDate) {
		this.campStartDate = campStartDate;
	}
	public String getCampEndDate() {
		return campEndDate;
	}
	public void setCampEndDate(String campEndDate) {
		this.campEndDate = campEndDate;
	}
	public String getIssueCnt() {
		return issueCnt;
	}
	public void setIssueCnt(String issueCnt) {
		this.issueCnt = issueCnt;
	}
	public String getRemainCnt() {
		return remainCnt;
	}
	public void setRemainCnt(String remainCnt) {
		this.remainCnt = remainCnt;
	}
	
	@Override
	public int compareTo(CampaignInfo o) {
		int ret = 0;
		try{
			long nSellAmount = Long.parseLong(this.sellAmount);
			long nTargetSellAmount = Long.parseLong(o.sellAmount);
			if(nSellAmount > nTargetSellAmount)
			{
				ret = 1;
			}else if(nSellAmount == nTargetSellAmount){
				ret = 0;
			}else if(nSellAmount < nTargetSellAmount){
				ret = -1;
			}
		}catch(Exception e){
			return this.sellAmount.compareTo(o.sellAmount);
		}
		
		return ret;
	}
	
}
