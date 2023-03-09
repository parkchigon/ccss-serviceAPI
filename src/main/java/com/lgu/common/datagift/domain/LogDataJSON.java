package com.lgu.common.datagift.domain;

public class LogDataJSON {
	private String seq;
	private String logTime;
	private RequestDeviceJSON device;
	private String ctn;
	private String userCtn;
	private String serial;
	private String useType;
	private String requestTime;
	private String responseTime;
	private String result;
	private String resultCode;
	private String category0;
	private String item;
	private String type;
	private String value;
	private String nwType;
	private String posX;
	private String posY;
	private RequestDetailInfoJSON svcDetailInfo;
	
	public String getSeq() {
		return seq;
	}
	public void setSeq(String seq) {
		this.seq = seq;
	}
	public String getLogTime() {
		return logTime;
	}
	public void setLogTime(String logTime) {
		this.logTime = logTime;
	}
	public RequestDeviceJSON getDevice() {
		return device;
	}
	public void setDevice(RequestDeviceJSON device) {
		this.device = device;
	}
	public String getCtn() {
		return ctn;
	}
	public void setCtn(String ctn) {
		this.ctn = ctn;
	}
	public String getUserCtn() {
		return userCtn;
	}
	public void setUserCtn(String userCtn) {
		this.userCtn = userCtn;
	}
	public String getSerial() {
		return serial;
	}
	public void setSerial(String serial) {
		this.serial = serial;
	}
	public String getUseType() {
		return useType;
	}
	public void setUseType(String useType) {
		this.useType = useType;
	}
	public String getRequestTime() {
		return requestTime;
	}
	public void setRequestTime(String requestTime) {
		this.requestTime = requestTime;
	}
	public String getResponseTime() {
		return responseTime;
	}
	public void setResponseTime(String responseTime) {
		this.responseTime = responseTime;
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
	public String getCategory0() {
		return category0;
	}
	public void setCategory0(String category0) {
		this.category0 = category0;
	}
	public String getItem() {
		return item;
	}
	public void setItem(String item) {
		this.item = item;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public String getNwType() {
		return nwType;
	}
	public void setNwType(String nwType) {
		this.nwType = nwType;
	}
	public String getPosX() {
		return posX;
	}
	public void setPosX(String posX) {
		this.posX = posX;
	}
	public String getPosY() {
		return posY;
	}
	public void setPosY(String posY) {
		this.posY = posY;
	}
	public RequestDetailInfoJSON getSvcDetailInfo() {
		return svcDetailInfo;
	}
	public void setSvcDetailInfo(RequestDetailInfoJSON svcDetailInfo) {
		this.svcDetailInfo = svcDetailInfo;
	}
	@Override
	public String toString() {
		return "LogDataJSON [seq=" + seq + ", logTime=" + logTime + ", device=" + device + ", ctn=" + ctn + ", userCtn="
				+ userCtn + ", serial=" + serial + ", useType=" + useType + ", requestTime=" + requestTime
				+ ", responseTime=" + responseTime + ", result=" + result + ", resultCode=" + resultCode
				+ ", category0=" + category0 + ", item=" + item + ", type=" + type + ", value=" + value + ", nwType="
				+ nwType + ", posX=" + posX + ", posY=" + posY + ", svcDetailInfo=" + svcDetailInfo + "]";
	}
	
	
}
