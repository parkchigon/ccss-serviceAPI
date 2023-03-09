package com.lgu.common.ncas;

public class NCASConst {
	
	//일시정지 상태
	public static final String CTN_STUS_CODE_ACTIVE = "A";
	public static final String CTN_STUS_CODE_SUSPEND = "S";
	
	//성별
	public static final String BIRTH_PRES_ID_MALE 	= "M";
	public static final String BIRTH_PRES_ID_FEMALE = "F";

	public static String NCAS_FIELD_MEMBERNO = "MEMBERNO";
	public static String NCAS_FIELD_CTN = "CTN";
	public static String NCAS_FIELD_FEE_TYPE = "FEE_TYPE";
	public static String NCAS_FIELD_BAN_UNPID_YN_CODE = "BAN_UNPID_YN_CODE";
	public static String NCAS_FIELD_UNIT_LOSS_YN_CODE = "UNIT_LOSS_YN_CODE";
	public static String NCAS_FIELD_CTN_STUS_CODE = "CTN_STUS_CODE";
	public static String NCAS_FIELD_UNIT_MDL = "UNIT_MDL";
	public static String NCAS_FIELD_REGDATE = "REGDATE";
	public static String NCAS_FIELD_IMSI = "IMSI";
	public static String NCAS_FIELD_SUB_NO = "SUB_NO";
	public static String NCAS_FIELD_ACENO = "ACENO";
	public static String NCAS_FIELD_PROD_CD = "PROD_CD";
	
	public static String NCAS_FIELD_REAL_BIRTH_PERS_ID = "REAL_BIRTH_PERS_ID";
	public static String NCAS_FIELD_SUB_BIRTH_PERS_ID = "SUB_BIRTH_PERS_ID";
	public static String NCAS_FIELD_REAL_SEX_PERS_ID = "REAL_SEX_PERS_ID";
	public static String NCAS_FIELD_SUB_SEX_PERS_ID = "SUB_SEX_PERS_ID";
	
	public static String NCAS_FIELD_FRST_ENTR_DTTM = "FRST_ENTR_DTTM";
	public static String NCAS_FIELD_USIM_ICCID_NO = "USIM_ICCID_NO";
	public static String NCAS_FIELD_IMEI = "IMEI";
	public static String NCAS_FIELD_REAL_PERS_ID_OUT = "REAL_PERS_ID_OUT";
	public static String NCAS_FIELD_SUB_PERS_ID_OUT = "SUB_PERS_ID_OUT";
	
	public static String NCAS_FIELD_SVC_AUTH = "SVC_AUTH";
	public static String NCAS_FIELD_SVC_AUTH_DT = "SVC_AUTH_DT";

	public static String ncas_field_respcode = "RESPCODE";
	
	//요금제에 따른 개통처리 안전제일개통용
	public static final String FEE_TYPE_OPEN= "LPZ0000248";
	
	public final static String TRACE_SOURCE = "WAS";
	public final static String TRACE_TARGET = "NCAS";
}
