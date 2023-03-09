package com.lgu.common.esb;

public class ESBConstant {

	//
	public static final String SUCCESS = "N0000";

	// ???
	public static final String ERROR_BIZ00 = "BIZ00";

	// 서비스 연동 오류:Transport Run Time Error (BEA-380000 to BEA-380099)
	public static final String ERROR_E0001 = "E0001";

	// 메시지 처리 오류:Message Flow runtime (382000...382499)
	public static final String ERROR_E0002 = "E0002";

	// Action error codes (382500...382999)
	public static final String ERROR_E0003 = "E0003";

	// Security error codes (386000...386999)
	public static final String ERROR_E0004 = "E0004";

	// ESBHeader Validation Failed.
	public static final String ERROR_E0005 = "E0005";

	// Invalid service id
	public static final String ERROR_E0006 = "E0006";

	// Invalid/blocked system id
	public static final String ERROR_E0007 = "E0007";

	// Unavailable service:서비스 초기화 안
	public static final String ERROR_E0008 = "E0008";

	// Unavailable service:서비스 일시 중지
	public static final String ERROR_E0009 = "E0009";

	// Unavailable service:서비스 사용 중지
	public static final String ERROR_E0010 = "E0010";

	// Tuxedo Service Return Message Error:턱시도 서비스 호출 후 결과 메시지가 없음.또는 &#0 (쓰레기문자열)
	// 파싱 못하는 에러
	public static final String ERROR_E0011 = "E0011";

	// 결과없음(통합결제응답)
	public static final String ERROR_E0012 = "E0012";

	// Provider 서비스 타임아웃
	public static final String ERROR_E0013 = "E0013";
}
