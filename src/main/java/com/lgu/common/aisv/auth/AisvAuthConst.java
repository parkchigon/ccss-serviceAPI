package com.lgu.common.aisv.auth;

public class AisvAuthConst {

	public final static String URL_GET_NID_INFO 	= "/aisv/api/app/getNidInfo";
	public final static String URL_REGIST_TEMP_ID 	= "/aisv/api/app/registTempId";
	
	public final static String HD_VALUE_CONTENTTYPE_JSON_UTF8 = "application/json;charset=UTF-8";
	
	public final static String DEVICE_AM_APP_TYPE = "AM_MGR_APP";
	public final static String DEVICE_BM_APP_TYPE = "BM_MGR_APP";
	public final static String DEVICE_BM_APP_INFO = "SY";
	
	public final static String COMMON_SERVICE_TYPE = "SVC_001";
	public final static String COMMON_AM_DEV_TYPE  = "DEV_006";
	public final static String COMMON_BM_DEV_TYPE  = "DEV_007";
	public final static String COMMON_AM_DEV_INFO  = "ATAPP";
	public final static String COMMON_BM_DEV_INFO  = "BSAPP";
	
	public final static String BODY_NAME_NID = "nid";
	public final static String BODY_NAME_TYPE = "type";
	public final static String BODY_SVC_CODE = "svcCode";
	public final static String BODY_USER_ID = "userId";
	public final static String BODY_ONE_ID_KEY = "oneIdKey";
	public final static String BODY_SSO_KEY = "ssoKey";
	public final static String BODY_TEMP_ID_YN = "tempIdYn";
	public final static String BODY_CTN = "ctn";
	
	public final static String TRACE_SOURCE = "WAS";
	public final static String TRACE_TARGET = "AISV_AUTH";
}
