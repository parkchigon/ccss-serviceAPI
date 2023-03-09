package com.lgu.common.ai.coif.model;

public class COIFRequestCommonJSON {
	private String serviceType;
	private String deviceSn;
	private String deviceModel;
	private String deviceFwVersion;
	private String deviceLatitude;
	private String deviceLongtitude;
	private String deviceLanguage;
	private Object deviceStatusSummary;
	private String currentSkill;
	private String currentIntent;
	private String currentSlot;
	private Object deviceTime;
	private String utcTimeZone;
	private String triggerType;
	private String schema;
	private String method;
	private String resultCode;
	private String resultMessage;
	
	private COIFRequestCommonLogDataJSON logData;

	public String getServiceType() {
		return serviceType;
	}

	public void setServiceType(String serviceType) {
		this.serviceType = serviceType;
	}

	public String getDeviceSn() {
		return deviceSn;
	}

	public void setDeviceSn(String deviceSn) {
		this.deviceSn = deviceSn;
	}

	public String getDeviceModel() {
		return deviceModel;
	}

	public void setDeviceModel(String deviceModel) {
		this.deviceModel = deviceModel;
	}

	public String getDeviceFwVersion() {
		return deviceFwVersion;
	}

	public void setDeviceFwVersion(String deviceFwVersion) {
		this.deviceFwVersion = deviceFwVersion;
	}

	public String getDeviceLatitude() {
		return deviceLatitude;
	}

	public void setDeviceLatitude(String deviceLatitude) {
		this.deviceLatitude = deviceLatitude;
	}

	public String getDeviceLongtitude() {
		return deviceLongtitude;
	}

	public void setDeviceLongtitude(String deviceLongtitude) {
		this.deviceLongtitude = deviceLongtitude;
	}

	public String getDeviceLanguage() {
		return deviceLanguage;
	}

	public void setDeviceLanguage(String deviceLanguage) {
		this.deviceLanguage = deviceLanguage;
	}

	public Object getDeviceStatusSummary() {
		return deviceStatusSummary;
	}

	public void setDeviceStatusSummary(Object deviceStatusSummary) {
		this.deviceStatusSummary = deviceStatusSummary;
	}

	public String getCurrentSkill() {
		return currentSkill;
	}

	public void setCurrentSkill(String currentSkill) {
		this.currentSkill = currentSkill;
	}

	public String getCurrentIntent() {
		return currentIntent;
	}

	public void setCurrentIntent(String currentIntent) {
		this.currentIntent = currentIntent;
	}

	public String getCurrentSlot() {
		return currentSlot;
	}

	public void setCurrentSlot(String currentSlot) {
		this.currentSlot = currentSlot;
	}

	public Object getDeviceTime() {
		return deviceTime;
	}

	public void setDeviceTime(Object deviceTime) {
		this.deviceTime = deviceTime;
	}

	public String getUtcTimeZone() {
		return utcTimeZone;
	}

	public void setUtcTimeZone(String utcTimeZone) {
		this.utcTimeZone = utcTimeZone;
	}

	public String getTriggerType() {
		return triggerType;
	}

	public void setTriggerType(String triggerType) {
		this.triggerType = triggerType;
	}

	public String getSchema() {
		return schema;
	}

	public void setSchema(String schema) {
		this.schema = schema;
	}

	public String getMethod() {
		return method;
	}

	public void setMethod(String method) {
		this.method = method;
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

	public COIFRequestCommonLogDataJSON getLogData() {
		return logData;
	}

	public void setLogData(COIFRequestCommonLogDataJSON logData) {
		this.logData = logData;
	}

	@Override
	public String toString() {
		return "COIFRequestCommonJSON [serviceType=" + serviceType + ", deviceSn=" + deviceSn + ", deviceModel="
				+ deviceModel + ", deviceFwVersion=" + deviceFwVersion + ", deviceLatitude=" + deviceLatitude
				+ ", deviceLongtitude=" + deviceLongtitude + ", deviceLanguage=" + deviceLanguage
				+ ", deviceStatusSummary=" + deviceStatusSummary + ", currentSkill=" + currentSkill + ", currentIntent="
				+ currentIntent + ", currentSlot=" + currentSlot + ", deviceTime=" + deviceTime + ", utcTimeZone="
				+ utcTimeZone + ", triggerType=" + triggerType + ", schema=" + schema + ", method=" + method
				+ ", resultCode=" + resultCode + ", resultMessage=" + resultMessage + ", logData=" + logData + "]";
	}
}
