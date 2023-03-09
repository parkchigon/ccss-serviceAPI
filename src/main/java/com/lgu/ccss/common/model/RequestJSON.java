package com.lgu.ccss.common.model;

import java.util.LinkedHashMap;
import java.util.Map;

public class RequestJSON {
	public static final String USE_Y = "Y";
	public static final String USE_N = "N";
	
	public static final String PARAM_SERIAL = "serial";
	public static final String PARAM_CTN = "ctn";
	public static final String PARAM_AI_TOKEN = "aiToken";
	public static final String PARAM_CCSS_TOKEN = "ccssToken";
	public static final String PARAM_IS_AGREE = "isAgree";
	public static final String PARAM_VERSION = "version";
	public static final String PARAM_BODY = "body";
	public static final String PARAM_ESN = "esn";
	public static final String PARAM_USIM_MODEL = "usimModel";
	public static final String PARAM_USIM_SN = "usimSn";
	public static final String PARAM_FIRMWARE_INFO = "firmwareInfo";
	public static final String PARAM_TERMS_NO = "termsNo";
	public static final String PARAM_SERVICE_CODE = "serviceCode";
	public static final String PARAM_LOGIN_TYPE = "loginType";
	public static final String PARAM_AUTH_PARAMETER1 = "authParameter1";
	public static final String PARAM_AUTH_PARAMETER2 = "authParameter2";
	public static final String PARAM_AUTH_PARAMETER3 = "authParameter3";
	public static final String PARAM_AUTH_PARAMETER4 = "authParameter4";
	public static final String PARAM_AUTH_PARAMETER5 = "authParameter5";
	public static final String PARAM_AUTH_PARAMETER6 = "authParameter6";
	public static final String PARAM_AUTH_PARAMETER7 = "authParameter7";
	public static final String PARAM_AUTH_PARAMETER8 = "authParameter8";
	public static final String PARAM_AUTH_PARAMETER9 = "authParameter9";
	public static final String PARAM_AUTH_PARAMETER10 = "authParameter10";
	public static final String PARAM_AUTH_HOME_CODE_LIST = "homeCodeList";
	public static final String PARAM_ONE_ID_KYE = "oneIdKey";
	public static final String PARAM_CUSTOM_ID = "customId"; 
	public static final String PARAM_CAR_NUM = "carNum";
	/* 2018.08.29 추가 장영진 */
	public static final String PARAM_TEMP_ONE_ID = "tempOneId";
	/**
	 * Second Dev Add
	 */
	public static final String PARAM_APP_TYPE = "appType";
	public static final String PARAM_MNGR_USER_CTN = "userCtn";
	public static final String PARAM_MNGR_LOGIN_ID = "mgrappLoginId";
	public static final String PARAM_MNGR_LOGIN_TYPE = "loginType";
	public static final String PARAM_MNGR_APP_ID = "mgrAppId";
	public static final String PARAM_UUID = "uuid";
	public static final String PARAM_DEVICE_NM = "deviceNm";
	public static final String PARAM_USER_NM = "userNm";
	public static final String PARAM_RANDOM_KEY = "randomKey";
	public static final String PARAM_PUSH_ID = "pushId";
	public static final String PARAM_MGR_APP_VER = "mgrappVer";
	public static final String PARAM_MGR_JOIN_SET_INFO = "jsonSetInfo";
	public static final String PARAM_MGR_MAIN_USE_YN = "mainUseYn";
	public static final String PARAM_MGR_VER_TYPE= "verType";
	public static final String PARAM_CERT_NO= "certNo";
	public static final String PARAM_MEMB_ID= "membId";
	public static final String PARAM_NAVER_ACCESS_TOKEN ="naverAccessToken";
	public static final String PARAM_NAVER_LOGIN_ID ="nid";
	public static final String PARAM_AUTHORIZATION_CODE ="authorizationCode";
	public static final String PARAM_NAVER_TYPE= "type";
	public static final String PARAM_NAVER_SVC_CODE= "svcCode";
	public static final String PARAM_NAVER_AI_TEMP_ID_YN= "aiTempIdYn";
	
	public static final String PARAM_START_POSITION= "startPosition"; 
	public static final String PARAM_REQ_COUNT= "reqCount"; 
	public static final String PARAM_SEARCH_WORD= "searchWord"; 
	public static final String PARAM_X_CORRD= "xCoord"; 
	public static final String PARAM_Y_CORRD= "yCoord"; 
	public static final String PARAM_SORTOPT= "sortopt"; 
	public static final String PARAM_SEARCH_OPTION= "searchOption"; 
	
	public static final String PARAM_START_ADDRESS ="startAddress"; 
	public static final String PARAM_START_JIBUN ="startJibun"; 
	public static final String PARAM_START_LONX="startLonx"; 
	public static final String PARAM_START_LATY="startLaty"; 
	public static final String PARAM_START_REAL_LONX="startRealLonx"; 
	public static final String PARAM_START_REAL_LATY="startRealLaty";
	public static final String PARAM_START_NM="startNm"; 
	
	public static final String PARAM_START_ROAD_NAME="startRoadName"; 
	public static final String PARAM_START_ROAD_JIBUN="startRoadJibun"; 
	
	public static final String PARAM_END_ADDRESS ="targetAddress"; 
	public static final String PARAM_END_LONX="targetLonx"; 
	public static final String PARAM_END_LATY="targetLaty"; 
	public static final String PARAM_END_REAL_LONX="targetRealLonx"; 
	public static final String PARAM_END_REAL_LATY="targetRealLaty";
	public static final String PARAM_END_POI_ID="targetPoiId";
	public static final String PARAM_END_ROAD_NAME="targetRoadName"; 
	public static final String PARAM_END_ROAD_JIBUN="targetRoadJibun"; 
	public static final String PARAM_END_NM="targetNm"; 
	
	
	
	public static final String PARAM_VIA_LOC_LONX="viaLonx"; 
	public static final String PARAM_VIA_LOC_LATY="viaLaty"; 
	public static final String PARAM_VIA_LOC_NM="viaNm"; 
	
	public static final String PARAM_ARRIV_HOPE_TIME="arrivHopeTime"; 
	public static final String PARAM_REPEAT_ALARM_DAY="repeatAlarmDay"; 
	
	public static final String PARAM_LONX ="lonx";
	public static final String PARAM_LATY ="laty";
	
	public static final String PARAM_POSX ="posx";
	public static final String PARAM_POSY ="posy";
	
	public static final String PARAM_SEND_TO_CAR_ID ="sendToCarId"; 
	
	public static final String PARAM_SCHEULE_ID ="scheduleId"; 
	
	public static final String PARAM_ARRIVAL_ALARM_ID  = "arrivalAlarmId";
	
	public static final String PARAM_ARRIVAL_ALARM_RCPT_ID  = "arrivalAlarmRcptId";
	
	public static final String PARAM_USE_YN ="useYn"; 
	public static final String PARAM_ADDRESS ="address"; 
	public static final String PARAM_TARGET ="target"; 
	public static final String PARAM_TARGET_LIST ="targetList"; 
	public static final String PARAM_RECEIVER ="receiver"; 
	public static final String PARAM_SEND2CAR_ID = "send2CarId";
	
	public static final String PARAM_LOCATION = "location";
	
	public static final String PARAM_SERVICE_CATEGORY = "serviceCategory";
	public static final String PARAM_SERVICE_EXPOSURE = "serviceExposure";
	
	public static final String PARAM_FW_VER = "fwVer";
	
	public static final String GCALENDAR_DATE_FORMAT = "yyyy-MM-dd HH:mm";
	
	public static final String PARAM_APP_PUSH_TYPE = "appPushType";
	public static final String PARAM_DEVICE_TOKEN = "deviceToken";
	public static final String PARAM_FORCED_YN	="forcedYn";
	
	public static final String PARAM_ISAGREESTEP	="isAgreeStep";
	
	public static final String PARAM_LOGINOUT = "loginout";
	
	// 데이타상품권 param 추가, 2018-09-14, by neo073
	public static final String PARAM_REG_TYPE = "regType";
	public static final String PARAM_START_DATE = "startDate";
	public static final String PARAM_END_DATE = "endDate";
	
	public static final String PARAM_TARGET_NAME = "name";
	
	//E:MapInfra Header Const
	private RequestCommonJSON common;
	private Map<String, Object> param;
	// 서비스로그
	private RequestCommonJSON log;
	
	
	// EV param 추가, 2019-01-18, by 김범주
	public static final String PARAM_BATTERY_CAPA = "battery_capa";
	public static final String PARAM_DRIVE_ABLE_DIST1 = "drive_able_dist1";
	public static final String PARAM_DRIVE_ABLE_DIST2 = "drive_able_dist2";
	public static final String PARAM_EXPECT_CHRG_TM1 = "expect_chrg_tm1";
	public static final String PARAM_EXPECT_CHRG_TM2 = "expect_chrg_tm2";
	public static final String PARAM_EXPECT_CHRG_TM3 = "expect_chrg_tm3";
	public static final String PARAM_CAR_USE_DT = "car_use_dt";
	public static final String PARAM_TOTAL_DRIVE_DIST = "total_drive_dist";
	public static final String PARAM_CHRG_STATUS = "chrg_status";
	public static final String PARAM_FULL_CHRG_ALARM = "full_chrg_alarm";
	public static final String PARAM_CHRG_MSG = "chrg_msg";
	
	
	public RequestJSON(){
		this.common = new RequestCommonJSON();
		this.param = new LinkedHashMap<String, Object>();
	}
	
	public RequestCommonJSON getCommon() {
		return common;
	}
	
	public void setCommon(RequestCommonJSON common) {
		this.common = common;
	}
	
	public Map<String, Object> getParam() {
		return param;
	}
	
	public void setParam(Map<String, Object> param) {
		this.param = param;
	}
	
	@Override
	public String toString() {
		return "RequestJSON [common=" + common + ", param=" + param + ", log=" + log +"]";
	}

	public RequestCommonJSON getlog() {
		return log;
	}

}
