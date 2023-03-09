package com.lgu.common.cekAi.auth;

public class CekAuthConst {
	
	
	//#
	public final static String URL_ADD_USER 	= "/auth/addUser";
	public final static String URL_ADD_DEVICE 		="/auth/addDevice";
	public final static String URL_SAVE_SVC_AUTH_INFO = "/auth/saveSvcAuthInfo";
	public final static String URL_LOGOUT_SVC = "/auth/logoutSvc";
	public final static String URL_RESET_USER_INFO = "/auth/resetUserInfo";
	public final static String URL_GET_SAVE_AUTH_INFO = "/auth/getSvcAuthInfo";
	
	public final static String URL_GET_AUTHORIZATION_CODE = "/auth/getAuthorizationCode";
	public final static String URL_GET_SVC_AUTH_INFO ="/auth/getSvcAuthInfo";
	
	
	public final static String HD_NAME_SVRKEY = "svrKey";
	public final static String HD_NAME_CUSTOMID = "customId";
	public final static String HD_TRANSACTION_ID = "transactionId";
	public final static String HD_TRANSACTION_ID_PREFIX = "TR_";
	public final static String HD_MESSAGE_ID = "messageId";
	public final static String HD_MESSAGE_ID_PREFIX = "MSG_";
	
	public final static String HD_NAME_DEVICETOKEN = "deviceToken";
	
	public final static String HD_VALUE_CONTENTTYPE_JSON_UTF8 = "application/json;charset=UTF-8";
	
	public final static String JSON_VALUE_IDTYPECD = "CTN";
	public final static String JSON_VALUE_IDTYPECD_ONE_ID = "ONEID";
	public final static String JSON_VALUE_DEVICE_TYPE_CD = "DEV_003";
	
	public final static String BODY_NAME_CONTENT_SVC_CODE = "contentSvcCode";
	public final static String BODY_NAME_NID = "nid";
	
	/*public final static String PARAM_NAME_CUSTOM_ID_KEY_VALUE = "authParameter7";
	public final static String PARAM_NAME_ONE_ID_KEY_VALUE = "authParameter1";
	public final static String PARAM_NAME_CTN_KEY_VALUE = "authParameter8";
	public final static String PARAM_NAME_SSO_KEY_VALUE = "authParameter2";
	public final static String PARAM_NAME_CUSTOMID = "customId";*/
	public final static String PARAM_NAME_CUSTOM_ID_KEY_VALUE = "authParameter2";
	public final static String PARAM_NAME_ONE_ID_KEY_VALUE = "authParameter1";
	public final static String PARAM_NAME_CTN_KEY_VALUE = "authParameter8";
	public final static String PARAM_NAME_SSO_KEY_VALUE = "authParameter3";
	public final static String PARAM_NAME_CUSTOMID = "customId";
	public final static String PARAM_NAME_IOT_SESSION_KEY ="authParameter4";
	public final static String PARAM_NAME_HOME_CODE ="authParameter5";
	public final static String PARAM_NAME_CTN ="authParameter8";
	
	public final static String TRACE_SOURCE = "WAS";
	public final static String TRACE_TARGET = "CEK_AI_AUTH";
	
	public final static String SERVICE_CODE_IOT = "iot";
	public final static String SERVICE_CODE_BIOT = "biot";
	public final static String SERVICE_CODE_CUSTIOT = "custiot";
	public final static String SERVICE_CODE_GENIE = "genie";
	//public final static String SERVICE_CODE_PODCAST = "podcast";
	//public final static String SERVICE_CODE_GOOGLE = "gcalendar";
	
}
