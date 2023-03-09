package com.lgu.common.ai.auth;

public class AuthConst {
	
	public final static String URL_CREATE_DEVICE_TOKEN 	= "/auth/createDeviceToken";
	public final static String URL_CREATE_PLATFORM_TOKEN 	= "/auth/createPlatformToken";
	public final static String URL_SAVE_SVC_AUTH_INFO = "/auth/saveSvcAuthInfo";
	public final static String URL_LOGOUT_SVC = "/auth/logoutSvc";
	
	public final static String HD_NAME_SVRKEY = "svrKey";
	public final static String HD_NAME_CUSTOMID = "customId";
	public final static String HD_NAME_DEVICETOKEN = "deviceToken";
	
	public final static String HD_VALUE_CONTENTTYPE_JSON_UTF8 = "application/json;charset=UTF-8";
	
	public final static String JSON_VALUE_IDTYPECD = "CTN";
	
	public final static String TRACE_SOURCE = "WAS";
	public final static String TRACE_TARGET = "AI_AUTH";
	
	public final static String SERVICE_CODE_IOT = "iot";
	public final static String SERVICE_CODE_GENIE = "genie";
	public final static String SERVICE_CODE_PODCAST = "podcast";
	public final static String SERVICE_CODE_GOOGLE = "gcalendar";
	public final static String SERVICE_CODE_NAVER = "naver";
	
}
