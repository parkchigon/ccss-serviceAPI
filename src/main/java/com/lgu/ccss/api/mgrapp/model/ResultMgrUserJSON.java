package com.lgu.ccss.api.mgrapp.model;

public class ResultMgrUserJSON {
	private String seq;
	private String ctn;
	private String name;
	private String mgrAppId;
	private String conStatus;
	private String mgrappLoginId;
	public String getSeq() {
		return seq;
	}
	public void setSeq(String seq) {
		this.seq = seq;
	}
	public String getCtn() {
		return ctn;
	}
	public void setCtn(String ctn) {
		this.ctn = ctn;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}	
	public String getConStatus() {
		return conStatus;
	}
	public void setConStatus(String conStatus) {
		this.conStatus = conStatus;
	}	
	public String getMgrAppId() {
		return mgrAppId;
	}
	public void setMgrAppId(String mgrAppId) {
		this.mgrAppId = mgrAppId;
	}
	
	public String getMgrappLoginId() {
		return mgrappLoginId;
	}
	public void setMgrappLoginId(String mgrappLoginId) {
		this.mgrappLoginId = mgrappLoginId;
	}
	@Override
	public String toString() {
		return "ResultMgrUserJSON [seq=" + seq + ", ctn=" + ctn + ", name="
				+ name + ", mgrAppId=" + mgrAppId + ", conStatus=" + conStatus
				+ ", mgrappLoginId=" + mgrappLoginId + "]";
	}
	
}
