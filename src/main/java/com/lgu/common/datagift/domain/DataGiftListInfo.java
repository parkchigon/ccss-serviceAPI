package com.lgu.common.datagift.domain;

import java.util.List;

public class DataGiftListInfo {

	String result = "";
	String resultCode = "";
	String resultMessage = "";
	
	
	String campaignCnt;

	List<CampaignInfo> campaignList;

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

	public String getCampaignCnt() {
		return campaignCnt;
	}

	public void setCampaignCnt(String campaignCnt) {
		this.campaignCnt = campaignCnt;
	}

	public List<CampaignInfo> getCampaignList() {
		return campaignList;
	}

	public void setCampaignList(List<CampaignInfo> campaignList) {
		this.campaignList = campaignList;
	}
	
	
	
}
