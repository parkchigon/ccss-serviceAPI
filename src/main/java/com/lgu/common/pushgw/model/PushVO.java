package com.lgu.common.pushgw.model;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class PushVO {
	
	public PushVO(){
	}
	
	public PushVO(String applicationId, String serviceId, String servicePass) {
		this.applicationId = applicationId;
		this.serviceId = serviceId;
		this.servicePass = servicePass;
	}
	
	
	/** s:PUSH G/W 연동용 컬럼 */
	private String requestPart;
	private String requestTime;
	private String applicationId;					//어플리케이션 ID
	private String serviceKey;						//Push 를 전송할 서비스 키(CTN인듯)
	private String servicePass;
	private String serviceKeyCount;
	private String sendTime;
	private Map<String, Object> payload;
	private List<PushServiceKeyVO> serviceKeys;
	private String gcmMultiCount;

	
	private String pushId;							//Transaction ID  YYYYMMDD+Sequence Number(4byte)
	private String serviceId;						//서비스 등록시 부여받은 Unique ID
	private String deviceToken;						//GCM device_token
	private String deviceId;						//단말을 구분할 수 있는 unique 값 UUID or UDID
	private String appPushType;						//PUSH TYPE(POS/APNS/GCM)
	
	private String bookingNumber;
	private String sleepStart;
	private String sleepEnd;
	private String vaildDate;
	private String subServiceId;
	/** e:PUSH G/W 연동용 컬럼 */
	

	private String pMobileNum;
	private String withdrawYn;
	private String deviceRegYn;
	private String deviceRegDate;
	
	public String getRequestPart() {
		return requestPart;
	}
	public void setRequestPart(String requestPart) {
		this.requestPart = requestPart;
	}
	public String getRequestTime() {
		return requestTime;
	}
	public void setRequestTime(String requestTime) {
		this.requestTime = requestTime;
	}
	public String getApplicationId() {
		return applicationId;
	}
	public void setApplicationId(String applicationId) {
		this.applicationId = applicationId;
	}
	public String getServiceKey() {
		return serviceKey;
	}
	public void setServiceKey(String serviceKey) {
		this.serviceKey = serviceKey;
	}
	public String getServicePass() {
		return servicePass;
	}
	public void setServicePass(String servicePass) {
		this.servicePass = servicePass;
	}
	public String getServiceKeyCount() {
		return serviceKeyCount;
	}
	public void setServiceKeyCount(String serviceKeyCount) {
		this.serviceKeyCount = serviceKeyCount;
	}
	public String getSendTime() {
		return sendTime;
	}
	public void setSendTime(String sendTime) {
		this.sendTime = sendTime;
	}
	public Map<String, Object> getPayload() {
		return payload;
	}
	public void setPayload(Map<String, Object> payload) {
		this.payload = payload;
	}
	public List<PushServiceKeyVO> getServiceKeys() {
		return serviceKeys;
	}
	public void setServiceKeys(List<PushServiceKeyVO> serviceKeys) {
		this.serviceKeys = serviceKeys;
	}
	public String getGcmMultiCount() {
		return gcmMultiCount;
	}
	public void setGcmMultiCount(String gcmMultiCount) {
		this.gcmMultiCount = gcmMultiCount;
	}
	public String getPushId() {
		return pushId;
	}
	public void setPushId(String pushId) {
		this.pushId = pushId;
	}
	public String getServiceId() {
		return serviceId;
	}
	public void setServiceId(String serviceId) {
		this.serviceId = serviceId;
	}
	public String getDeviceToken() {
		return deviceToken;
	}
	public void setDeviceToken(String deviceToken) {
		this.deviceToken = deviceToken;
	}
	public String getDeviceId() {
		return deviceId;
	}
	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}
	public String getAppPushType() {
		return appPushType;
	}
	public void setAppPushType(String appPushType) {
		this.appPushType = appPushType;
	}
	public String getBookingNumber() {
		return bookingNumber;
	}
	public void setBookingNumber(String bookingNumber) {
		this.bookingNumber = bookingNumber;
	}
	public String getSleepStart() {
		return sleepStart;
	}
	public void setSleepStart(String sleepStart) {
		this.sleepStart = sleepStart;
	}
	public String getSleepEnd() {
		return sleepEnd;
	}
	public void setSleepEnd(String sleepEnd) {
		this.sleepEnd = sleepEnd;
	}
	public String getVaildDate() {
		return vaildDate;
	}
	public void setVaildDate(String vaildDate) {
		this.vaildDate = vaildDate;
	}
	public String getSubServiceId() {
		return subServiceId;
	}
	public void setSubServiceId(String subServiceId) {
		this.subServiceId = subServiceId;
	}
	
	
	@SuppressWarnings("unchecked")
	public Map<String, Object> getPayloadEncoded() throws UnsupportedEncodingException {
		Map<String, Object> value = new HashMap<String, Object>();
		for(String key : this.getPayload().keySet()) {
			if(StringUtils.equals(key, "aps")) {
				Map<String, Object> value2 = new HashMap<String, Object>();
				Map<String, Object> map = (Map<String, Object>) this.getPayload().get(key);
				for(String key2 : map.keySet()) {
					value2.put(key2, URLEncoder.encode((String) map.get(key2), "UTF-8"));
				}
				value.put(key, value2);
			} else {
				value.put(key, URLEncoder.encode((String)this.getPayload().get(key), "UTF-8"));
			}
			
		}
		return value;
	}
	public String getpMobileNum() {
		return pMobileNum;
	}
	public void setpMobileNum(String pMobileNum) {
		this.pMobileNum = pMobileNum;
	}
	public String getWithdrawYn() {
		return withdrawYn;
	}
	public void setWithdrawYn(String withdrawYn) {
		this.withdrawYn = withdrawYn;
	}
	public String getDeviceRegYn() {
		return deviceRegYn;
	}
	public void setDeviceRegYn(String deviceRegYn) {
		this.deviceRegYn = deviceRegYn;
	}
	public String getDeviceRegDate() {
		return deviceRegDate;
	}
	public void setDeviceRegDate(String deviceRegDate) {
		this.deviceRegDate = deviceRegDate;
	}
	
}
