package com.lgu.common.ncas;

public class NCASResultData {
	private SubsInfo subsInfo;
	private String respCode;
	
	public NCASResultData(SubsInfo subsInfo, String respCode) {
		this.subsInfo = subsInfo;
		this.respCode = respCode;
	}
	
	public SubsInfo getSubsInfo() {
		return subsInfo;
	}
	public void setSubsInfo(SubsInfo subsInfo) {
		this.subsInfo = subsInfo;
	}
	public String getRespCode() {
		return respCode;
	}
	public void setRespCode(String respCode) {
		this.respCode = respCode;
	}
	
	@Override
	public String toString() {
		return "NCASResultData [subsInfo=" + subsInfo + ", respCode=" + respCode + "]";
	}
}
