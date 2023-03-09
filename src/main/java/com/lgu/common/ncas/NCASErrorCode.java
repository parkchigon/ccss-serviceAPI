package com.lgu.common.ncas;

public class NCASErrorCode {
	
	public static final String ERRORCODE_SUCCESS 			= "00"; //	정상	인증요청 정상 처리                                        
	public static final String ERRORCODE_INVALID_CTN_LEN 	= "09"; //	CTN length 오류	CTN length 오류                                 
	public static final String ERRORCODE_NO_MEMBERID 		= "10"; //	MEMBERID 부재	회원아이디가 없는 경우(요청 시)                   
	public static final String ERRORCODE_NO_MEMBERNO 		= "11"; //	MEMBERNO 부재	회원번호가 없는 경우(요청 시)                     
	public static final String ERRORCODE_NO_CTN 			= "12"; //	CTN 부재	CTN이 없는 경우(요청 시)                              
	public static final String ERRORCODE_NO_SUB_PERS_ID 	= "13"; //	SUB_PERS_ID 부재	명의자주민번호 부재 및 불일치                 
	public static final String ERRORCODE_NO_REAL_PERS_ID 	= "14"; //	REAL_PERS_ID 부재	실사용자주민번호 부재 및 불일치               
	public static final String ERRORCODE_NO_REAL_RESID_LAST = "15"; //	REAL_RESID_LAST 부재	실사용자주민번호뒷자리 부재 및 불일치     
	public static final String ERRORCODE_NO_SUB_RESID_LAST	= "16"; //	SUB_RESID_LAST 부재	명의자주민번호뒷자리 부재 및 불일치         
	public static final String ERRORCODE_NO_PCSNO 			= "17"; //	PCSNO 부재	PCSNO 부재 및 불일치                                
	public static final String ERRORCODE_NO_AGE_YN 			= "18"; //	AGE_YN 부재	나이구분 값이 없는 경우(요청 시)                    
	public static final String ERRORCODE_NO_REAL_CRYPT_PERS_ID = "19"; //	REAL_CRYPT_PERS_ID 부재 및 불일치	암호화된 실사용자주민번호 부재
	public static final String ERRORCODE_NO_SUB_CRYPT_PERS_ID = "20"; //	SUB_CRYPT_PERS_ID 부재 및 불일치	암호화된 명의자주민번호 부재 ?
	public static final String ERRORCODE_INVALID_AGE_YN	 	= "21"; //	AGE_YN 입력값 오류 (숫자만 가능)	AGE_YN 입력값 오류 (숫자만 가?
	public static final String ERRORCODE_NO_AGENT 			= "22"; //	법정대리인 정보 없음	법정대리인 정보 없음                      
	public static final String ERRORCODE_NO_LAW_CUST_NO 	= "23"; //	LAW_CUST_NO OR LAW_CUST_NAME 부재 및 불일치	LAW_CUST_NO OR LAW_C
	public static final String ERRORCODE_NO_LAW_CUST_NO_LAST = "24"; //	LAW_CUST_NO_LAST OR LAW_CUST_NAME 부재 및 불일치	LAW_CUST_NO_LA
	public static final String ERRORCODE_NO_CUST_DATA 		= "25"; //	고객데이터 오류	고객데이터 오류                                 
	public static final String ERRORCODE_INVAID_CUST_NO 	= "26"; //	주민번호 및 법인번호 length 오류	주민번호 및 법인번호 length 오
	public static final String ERRORCODE_ERROR_INTERNAL_CP	= "27"; //	내부 CP 인증 오류	내부 CP 인증 오류                             
	public static final String ERRORCODE_ERROR_EXTERNAL_CP 	= "28"; //	외부 CP 인증 오류	외부 CP 인증 오류                             
	public static final String ERRORCODE_NO_LAW_BIRTH_PERS_ID = "30"; //	LAW_BIRTH_PERS_ID OR LAW_CUST_NAME 부재 및 불일치	LAW_BIRTH_PERS
	public static final String ERRORCODE_NO_COMP_ID		 	= "31"; //	COMP_ID 부재 및 불일치	COMP_ID 부재 및 불일치                  
	public static final String ERRORCODE_NO_CPID 			= "61"; //	CPID 부재	CPID 부재                                             
	public static final String ERRORCODE_NO_CPPWD 			= "62"; //	CPPWD 부재	CPPWD 부재                                          
	public static final String ERRORCODE_NO_CASECODE 		= "63"; //	CASECODE 부재	CASECODE 부재                                     
	public static final String ERRORCODE_NO_CPTYPE		 	= "64"; //	CPTYPE부재	CPTYPE부재                                          
	public static final String ERRORCODE_NO_LGTCASHEADER 	= "65"; //	LGTCASHEADER 부재 및 불일치	LGTCASHEADER 부재 및 불일치         
	public static final String ERRORCODE_NO_PROTOCOL_NO 	= "66"; //	전문번호 부재 및 불일치	전문번호 부재 및 불일치                 
	public static final String ERRORCODE_INVALID_PROTOCOL_LEN = "67"; //	전체의 전문 길이 오류	전체의 전문 길이 오류                     
	public static final String ERRORCODE_NO_LGT 			= "70"; //	LGT 고객정보 없음	LGT 고객정보 없음                             
	public static final String ERRORCODE_PORTEDOUT_SKT 		= "71"; //	SKT로 번호이동	SKT로 번호이동 된 고객                          
	public static final String ERRORCODE_PORTEDOUT_KTF 		= "76"; //	KTF로 번호이동	KTF로 번호이동 된 고객                          
	public static final String ERRORCODE_INVALID_CPID		= "80"; //	CPID 불일치	CPID 불일치                                         
	public static final String ERRORCODE_INVALID_CPPWD		= "81"; //	CPPWD 불일치	CPPWD 불일치                                      
	public static final String ERRORCODE_INVALID_CASECODE 	= "82"; //	CASECODE 불일치	CASECODE 불일치                                 
	public static final String ERRORCODE_INVALID_SVC_IP 	= "83"; //	SVC_IP 불일치	IP인증 오류                                       
	public static final String ERRORCODE_INVALID_INPUT 		= "84"; //	필수 파라미터 불일치	INPUT값 불일치                            
	public static final String ERRORCODE_INVALID_CPTYPE 	= "85"; //	CPTYPE 불일치	CPTYPE 불일치                                     
	public static final String ERRORCODE_INVALID_NPWD 		= "89"; //	NPWD 불일치	NPWD 불일치                                         
	public static final String ERRORCODE_NPDB_SYSTEM_ERROR 	= "93"; //	NPDB SYSTEM ERROR	NPDB SYSTEM ERROR                             
	public static final String ERRORCODE_NO_DBCONNECTION 	= "95"; //	DB 유휴 CONNECTION 없음	DB 유휴 CONNECTION 없음                 
	public static final String ERRORCODE_INVALID_DBCONNECTION = "96"; //	DB CONNECTION 실패	DB CONNECTION 실패                          
	public static final String ERRORCODE_INVALD_ENCGW_CONNECTiON = "97"; //	암호화 G/W CONNECTION 실패	암호화 G/W CONNECTION 실패          
	public static final String ERRORCODE_ETC_SYSTEM 		= "98"; //	기타시스템 에러	기타시스템 에러(코드상 오류 발생시)             
	public static final String ERRORCODE_ETC 				= "99"; //	기타에러	기타에러(서버오류 발생시)                             

	public static final String ERRORCODE_NCAS_CONN = "600";
}
