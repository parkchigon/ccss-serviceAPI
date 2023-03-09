package com.lgu.common.clova.auth;

public class ClovaAuthConst {
	
	public final static String DEF_Y 			= "Y";
	public final static String DEF_N 			= "N";
	public final static String DEF_EQUAL 		= "=";
	public final static String DEF_AMPERSAND	= "&";
	public final static String DEF_RESPONSE_TYPE_CODE = "code";
	public final static String DEF_UTF_8 ="UTF-8";
	public final static String SERVICE_CODE_CLOVA = "clova";
	public final static String DEF_GUEST	= "G";
	public final static String DEF_NAVER	= "N";
	
	public final static String AUTHORIZATION  = "Authorization";
	public final static String BEARER  = "Bearer ";
	
	public final static String URL_CREATE_AUTHORIZATION_CODE 	= "/authorize";
	public final static String URL_CREATE_CLOVA_ACCESS_TOKEN 	= "/token?grant_type=authorization_code";
	public final static String URL_DELETE_CLOVA_ACCESS_TOKEN 	= "/token?grant_type=delete";
	public final static String URL_REFRESH_CLOVA_ACCESS_TOKEN 	= "/token?grant_type=refresh_token";
	
	public final static String URL_IOT_EXTENSION 	            = "/api/v1/iot/extensions/com.uplus.ucfc.iot.homeiot";
	public final static String URL_IOT_DISCOVERY 	            = "/api/v1/iot/extensions/com.uplus.ucfc.iot.homeiot/discovery";
	public final static String URL_IOT_CONNECT 	                = "/api/v1/iot/devices/";
	
	public final static String HD_VALUE_CONTENTTYPE_JSON_UTF8 = "application/json;charset=UTF-8";
	public final static String HD_VALUE_CONTENTTYPE_FORM_URL_ENCODED = "application/x-www-form-urlencoded";
	public static final String BODY_AUTH_CLIENT_ID = "client_id";
	public static final String BODY_AUTH_CLIENT_SECRET = "client_secret";
	public static final String BODY_AUTH_DEVICE_ID = "device_id";
	public static final String BODY_AUTH_MODEL_ID = "model_id";
	public static final String BODY_AUTH_RESPONSE_TYPE = "response_type";
	public static final String BODY_AUTH_STATE = "state";
	public static final String BODY_AUTH_CODE = "code";
	public static final String BODY_REQUEST_VU = "request_vu";
	public static final String BODY_ACCESS_TOKEN = "access_token";
	public static final String BODY_REFRESH_TOKEN = "refresh_token";
	
	public static final String BODY_AUTH_LINK = "link";
	public static final String BODY_AUTH_LOCATION = "location";
	
	public final static String TRACE_SOURCE = "WAS";
	public final static String TRACE_TARGET = "CLOVA_AUTH";
	
	
	/*public final static String URL_CREATE_PLATFORM_TOKEN 	= "/auth/createPlatformToken";
	public final static String URL_SAVE_SVC_AUTH_INFO = "/auth/saveSvcAuthInfo";
	public final static String URL_LOGOUT_SVC = "/auth/logoutSvc";
	
	public final static String HD_NAME_SVRKEY = "svrKey";
	public final static String HD_NAME_CUSTOMID = "customId";
	public final static String HD_NAME_DEVICETOKEN = "deviceToken";
	
	
	public final static String JSON_VALUE_IDTYPECD = "CTN";
	
	
	
	public final static String SERVICE_CODE_IOT = "iot";
	public final static String SERVICE_CODE_GENIE = "genie";
	public final static String SERVICE_CODE_PODCAST = "podcast";
	public final static String SERVICE_CODE_GOOGLE = "gcalendar";*/
	
}
