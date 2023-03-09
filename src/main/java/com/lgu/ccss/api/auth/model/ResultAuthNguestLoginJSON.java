package com.lgu.ccss.api.auth.model;

public class ResultAuthNguestLoginJSON {
	private String ccssToken;
	private String ccssTokenExpiration;
	private String aiDeviceToken;
	private String aiPlatformToken;
	private String aiPlatformTokenExpiration;
	private String aiUrl;
	
	
	private String securityKey;
	
	private String joinStatus;
	private String firstJoinStatus;
	private String joinDate;
	private String ratePayment;
	private String ratePaymentStatus;
	private String ratePaymentChangeStatus;
	private String ratePaymentResvStatus;
	private String dataStatus;
	
	//Clova result Value
	private String clovaBasicUrl;
	private String clovaAccessToken;
	private String expiredIn;
	private int expiresIn;
	private String refreshToken;
	private String tokenType;
	
	private String guestmodeAgrYn;
	
	public String getCcssToken() {
		return ccssToken;
	}
	public void setCcssToken(String ccssToken) {
		this.ccssToken = ccssToken;
	}
	public String getCcssTokenExpiration() {
		return ccssTokenExpiration;
	}
	public void setCcssTokenExpiration(String ccssTokenExpiration) {
		this.ccssTokenExpiration = ccssTokenExpiration;
	}
	public String getAiDeviceToken() {
		return aiDeviceToken;
	}
	public void setAiDeviceToken(String aiDeviceToken) {
		this.aiDeviceToken = aiDeviceToken;
	}
	public String getAiPlatformToken() {
		return aiPlatformToken;
	}
	public void setAiPlatformToken(String aiPlatformToken) {
		this.aiPlatformToken = aiPlatformToken;
	}
	public String getAiPlatformTokenExpiration() {
		return aiPlatformTokenExpiration;
	}
	public void setAiPlatformTokenExpiration(String aiPlatformTokenExpiration) {
		this.aiPlatformTokenExpiration = aiPlatformTokenExpiration;
	}
	public String getAiUrl() {
		return aiUrl;
	}
	public void setAiUrl(String aiUrl) {
		this.aiUrl = aiUrl;
	}
	
	public String getClovaBasicUrl() {
		return clovaBasicUrl;
	}
	public void setClovaBasicUrl(String clovaBasicUrl) {
		this.clovaBasicUrl = clovaBasicUrl;
	}
	public String getSecurityKey() {
		return securityKey;
	}
	public void setSecurityKey(String securityKey) {
		this.securityKey = securityKey;
	}
	public String getJoinStatus() {
		return joinStatus;
	}
	public void setJoinStatus(String joinStatus) {
		this.joinStatus = joinStatus;
	}
	public String getFirstJoinStatus() {
		return firstJoinStatus;
	}
	public void setFirstJoinStatus(String firstJoinStatus) {
		this.firstJoinStatus = firstJoinStatus;
	}
	public String getJoinDate() {
		return joinDate;
	}
	public void setJoinDate(String joinDate) {
		this.joinDate = joinDate;
	}
	public String getRatePayment() {
		return ratePayment;
	}
	public void setRatePayment(String ratePayment) {
		this.ratePayment = ratePayment;
	}
	public String getRatePaymentStatus() {
		return ratePaymentStatus;
	}
	public void setRatePaymentStatus(String ratePaymentStatus) {
		this.ratePaymentStatus = ratePaymentStatus;
	}
	public String getRatePaymentChangeStatus() {
		return ratePaymentChangeStatus;
	}
	public void setRatePaymentChangeStatus(String ratePaymentChangeStatus) {
		this.ratePaymentChangeStatus = ratePaymentChangeStatus;
	}
	public String getRatePaymentResvStatus() {
		return ratePaymentResvStatus;
	}
	public void setRatePaymentResvStatus(String ratePaymentResvStatus) {
		this.ratePaymentResvStatus = ratePaymentResvStatus;
	}
	public String getDataStatus() {
		return dataStatus;
	}
	public void setDataStatus(String dataStatus) {
		this.dataStatus = dataStatus;
	}
	public String getClovaAccessToken() {
		return clovaAccessToken;
	}
	public void setClovaAccessToken(String clovaAccessToken) {
		this.clovaAccessToken = clovaAccessToken;
	}
	public String getExpiredIn() {
		return expiredIn;
	}
	public void setExpiredIn(String expiredIn) {
		this.expiredIn = expiredIn;
	}
	public int getExpiresIn() {
		return expiresIn;
	}
	public void setExpiresIn(int expiresIn) {
		this.expiresIn = expiresIn;
	}
	public String getRefreshToken() {
		return refreshToken;
	}
	public void setRefreshToken(String refreshToken) {
		this.refreshToken = refreshToken;
	}
	
	public String getTokenType() {
		return tokenType;
	}
	public void setTokenType(String tokenType) {
		this.tokenType = tokenType;
	}
	
	public String getGuestmodeAgrYn() {
		return guestmodeAgrYn;
	}
	public void setGuestmodeAgrYn(String guestmodeAgrYn) {
		this.guestmodeAgrYn = guestmodeAgrYn;
	}
	@Override
	public String toString() {
		return "ResultAuthNguestLoginJSON [ccssToken=" + ccssToken
				+ ", ccssTokenExpiration=" + ccssTokenExpiration
				+ ", aiDeviceToken=" + aiDeviceToken + ", aiPlatformToken="
				+ aiPlatformToken + ", aiPlatformTokenExpiration="
				+ aiPlatformTokenExpiration + ", aiUrl=" + aiUrl
				+ ", securityKey=" + securityKey + ", joinStatus=" + joinStatus
				+ ", firstJoinStatus=" + firstJoinStatus + ", joinDate="
				+ joinDate + ", ratePayment=" + ratePayment
				+ ", ratePaymentStatus=" + ratePaymentStatus
				+ ", ratePaymentChangeStatus=" + ratePaymentChangeStatus
				+ ", ratePaymentResvStatus=" + ratePaymentResvStatus
				+ ", dataStatus=" + dataStatus + ", clovaAccessToken="
				+ clovaAccessToken + ", expiredIn=" + expiredIn
				+ ", refreshToken=" + refreshToken + ", tokenType=" + tokenType
				+ ", expiresIn=" + expiresIn + ", guestmodeAgrYn=" + guestmodeAgrYn +"]";
	}
	
	
	

}
