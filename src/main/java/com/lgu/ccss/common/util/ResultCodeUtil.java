package com.lgu.ccss.common.util;

import com.google.gson.Gson;
import com.lgu.ccss.common.model.ResponseJSON;
import com.lgu.common.model.ResultCode;


public final class ResultCodeUtil {
	
	public final static ResultCode RC_2S000000 = new ResultCode("2S000000", "성공");
	public final static ResultCode RC_2I000000 = new ResultCode("2I000000", "인포테인먼트 로그인 정보 없음");
	public final static ResultCode RC_2A000000 = new ResultCode("2A000000", "최신 App 버전 정보 없음");
	
	public final static ResultCode RC_3C002000 = new ResultCode("3C002000", "존재하지 않는 약관");
	public final static ResultCode RC_3C002001 = new ResultCode("3C002001", "서비스약관 동의 필요");
	public final static ResultCode RC_3C002002 = new ResultCode("3C002002", "데이터약관 동의 필요");
	public final static ResultCode RC_3C002003 = new ResultCode("3C002003", "이미 존재하는 가입자");
	public final static ResultCode RC_3C002004 = new ResultCode("3C002004", "가입자 정보 없음");
	public final static ResultCode RC_3C002005 = new ResultCode("3C002005", "가입자 일시 정지");
	public final static ResultCode RC_3C002006 = new ResultCode("3C002006", "등록 되지 않은 디바이스 정보");
	public final static ResultCode RC_3C002007 = new ResultCode("3C002007", "APP & Device Type 정보 불일치");
	public final static ResultCode RC_3C002008 = new ResultCode("3C002008", "ESB 연동실패");
	public final static ResultCode RC_3C002009 = new ResultCode("3C002009", "서비스 해지");
	public final static ResultCode RC_3C002010 = new ResultCode("3C002010", "주기기 없음");
	public final static ResultCode RC_3C002011 = new ResultCode("3C002011", "주기기 서비스 해지");
	
	public final static ResultCode RC_3C003000 = new ResultCode("3C003000", "CCSS 세션 만료");
	public final static ResultCode RC_3C003001 = new ResultCode("3C003001", "AI 플랫폼 세션 만료");
	public final static ResultCode RC_3C003002 = new ResultCode("3C003002", "UICC 정보 인증 실패");
	public final static ResultCode RC_3C003003 = new ResultCode("3C003003", "AI 플랫폼 인증 실패");
	public final static ResultCode RC_3C003004 = new ResultCode("3C003004", "유효하지 않은 CCSSToken");
	public final static ResultCode RC_3C003005 = new ResultCode("3C003005", "새로운 음성가이드 없음");
	public final static ResultCode RC_3C003006 = new ResultCode("3C003006", "로그인 횟수 제한");
	
	public final static ResultCode RC_3C003007 = new ResultCode("3C003007", "유효하지 않은 매니저앱 Session Id");
	public final static ResultCode RC_3C003008 = new ResultCode("3C003008", "매니저앱 세션 만료");
	public final static ResultCode RC_3C003009 = new ResultCode("3C003009", "AI 인증 정보 없음-DB 조회 실패");
	
	
	public final static ResultCode RC_3C004000 = new ResultCode("3C004000", "파라미터 오류");
	public final static ResultCode RC_3C004001 = new ResultCode("3C004001", "지원하지 않는 차량제조사");
	public final static ResultCode RC_3C004002 = new ResultCode("3C004002", "지원하지 않는 디바이스");
	public final static ResultCode RC_3C004003 = new ResultCode("3C004003", "지원하지 않는 APP");
	public final static ResultCode RC_3C004004 = new ResultCode("3C004004", "지원하지 않는 클라이언트");
	public final static ResultCode RC_3C004005 = new ResultCode("3C004005", "위치정보 복호화 실패");
	public final static ResultCode RC_3C004006 = new ResultCode("3C004006", "암호화 정보 복호화 실패");
	
	public final static ResultCode RC_3C005005 = new ResultCode("3C005001", "Google Calendar 로그아웃 ");
	
	public final static ResultCode RC_4C005000 = new ResultCode("4S005000", "서버 내부 오류");
	public final static ResultCode RC_4C005001 = new ResultCode("4S005001", "데이터베이스 오류");
	public final static ResultCode RC_4C005002 = new ResultCode("4S005002", "SMS MSG ID 생성 실패");
	public final static ResultCode RC_4C005003 = new ResultCode("4S005003", "SMS 사용자 인증 시간 만료");
	public final static ResultCode RC_4C005004 = new ResultCode("4S005004", "SMS 사용자 인증 실패-사용자 정보 없음");
	public final static ResultCode RC_4C005005 = new ResultCode("4S005005", "SMS 사용자 인증 실패-인증번호 불일치");
	public final static ResultCode RC_4C005006 = new ResultCode("4S005006", "SMS 사용자 인증 이력 없음.");
	public final static ResultCode RC_4C005007 = new ResultCode("4S005007", "MQTT 인증 실패");

	
	public final static ResultCode RC_5N000000 = new ResultCode("5N000000", "NCAS 인증 실패");
	public final static ResultCode RC_5N000001 = new ResultCode("5N000001", "NCAS 통신 오류");

	public final static ResultCode RC_5A000000 = new ResultCode("5A000000", "AI_AUTH 인증 실패");
	public final static ResultCode RC_5A000001 = new ResultCode("5A000001", "AI_AUTH 서버 연동 실패");
	public final static ResultCode RC_5A000002 = new ResultCode("5A000002", "인포테인먼트 로그인 정보 저장 실패");
	//public final static ResultCode RC_5A000003 = new ResultCode("5A000003", "인포테인먼트 로그인 정보 없음");
	public final static ResultCode RC_5A000004 = new ResultCode("5A000004", "인포테인먼트 로그아웃 실패");
	
	public final static ResultCode RC_5A000005 = new ResultCode("5A000005", "인포테인먼트 로그인 Call Back 정보 결과 없음");
	
	public final static ResultCode RC_5C100000 = new ResultCode("5C100000", "CLOVA Authorization Code 조회 실패");
	public final static ResultCode RC_5C100001 = new ResultCode("5C100001", "CLOVA Access Token 조회 실패");
	public final static ResultCode RC_5C100002 = new ResultCode("5C100002", "CLOVA Access Token 갱신 실패");
	
	public final static ResultCode RC_5C200001 = new ResultCode("5C200001", "CEK OneId 인증 정보 없음");
	public final static ResultCode RC_5C200002 = new ResultCode("5C200002", "CEK Add User 등록 실패");
	public final static ResultCode RC_5C200003 = new ResultCode("5C200003", "CEK Add Device 등록 실패");
	public final static ResultCode RC_5C200004 = new ResultCode("5C200004", "CEK ONE-ID 정보 등록 실패");
	public final static ResultCode RC_5C200005 = new ResultCode("5C200005", "CEK GetAuthorizationCode 정보 조회 실패");
	public final static ResultCode RC_5C200006 = new ResultCode("5C200006", "AI비서 서버 연동 실패");
	public final static ResultCode RC_5C200007 = new ResultCode("5C200007", "NID 미등록");
	public final static ResultCode RC_5C200008 = new ResultCode("5C200008", "임시 아이디 등록 확인 필요");
	public final static ResultCode RC_5C200009 = new ResultCode("5C200009", "인증 정보 없음");
	public final static ResultCode RC_5C200010 = new ResultCode("5C200010", "CEK ONE-ID 정보 없음");
	public final static ResultCode RC_5C200011 = new ResultCode("5C200011", "Extension Data 없음");
	public final static ResultCode RC_5C200012 = new ResultCode("5C200012", "Extension 인증절차 진행");
	
	public final static ResultCode RC_5C000000 = new ResultCode("5C000000", "AI_WEATHER 조회 실패");	
	public final static ResultCode RC_5C000001 = new ResultCode("5C000001", "AI_WEATHER 서버 연동 실패");
	
	public final static ResultCode RC_5G000000 = new ResultCode("5G000000", "구글 연동  실패");	
	public final static ResultCode RC_5G000001 = new ResultCode("5G000001", "구글 캘런더 서버 연동 실패");
	public final static ResultCode RC_5G000002 = new ResultCode("5G000002", "구글 서버 로그인 인증 실패");
	public final static ResultCode RC_5G000003 = new ResultCode("5G000003", "구글 서버 access token 획득 실패");
	public final static ResultCode RC_5G000004 = new ResultCode("5G000004", "구글 갤런더 응답 오류");
	
	public final static ResultCode RC_5P000000 = new ResultCode("5P000000", "PUSH_GW 서버 연동 실패");
	public final static ResultCode RC_5P000001 = new ResultCode("5P000001", "PUSH_GW 단말 등록 실패");
	public final static ResultCode RC_5P000002 = new ResultCode("5P000002", "PUSH_GW 단말 등록 삭제 실패");
	public final static ResultCode RC_5P000003 = new ResultCode("5P000003", "PUSH_GW 단말 등록 정보 DB 저장 실패");
	public final static ResultCode RC_5P000004 = new ResultCode("5P000003", "PUSH_GW 단말 미등록 상태");
	
	public final static ResultCode RC_5R000001 = new ResultCode("5R000001", "데이타상품권 CTN인증 실패");	// 2018-09-14, by neo073.
	public final static ResultCode RC_5R000002 = new ResultCode("5R000002", "데이타상품권 리스트 조회(이력) 실패");	// 2018-09-14, by neo073.
	
	public final static ResultCode RC_6C000000 = new ResultCode("6C000000", "UUID 불일치");
	public final static ResultCode RC_6C000001 = new ResultCode("6C000001", "이미 등록 되어 있는 사용자");
	public final static ResultCode RC_6C000002 = new ResultCode("6C000002", "매니저앱 사용자 정보 없음");
	public final static ResultCode RC_6C000003 = new ResultCode("6C000003", "사용자 디바이스 정보 없음");
	public final static ResultCode RC_6C000004 = new ResultCode("6C000004", "로그인 실패 - 사용자 상태 이상");
	public final static ResultCode RC_6C000005 = new ResultCode("6C000005", "로그인 실패 - UUID 불일치");
	public final static ResultCode RC_6C000006 = new ResultCode("6C000006", "로그인 실패 - RANDOM KEY 불일치");
	public final static ResultCode RC_6C000007 = new ResultCode("6C000007", "매니저앱 이용자수 초과");
	public final static ResultCode RC_6C000008 = new ResultCode("6C000008", "매니저앱 약관미동의");

	
	public final static ResultCode RC_7C000000 = new ResultCode("7C000000", "지도 인프라 조회 실패");
	public final static ResultCode RC_7C000001 = new ResultCode("7C000001", "지도 인프라 서버 연동 실패");
	public final static ResultCode RC_7C000002 = new ResultCode("7C000002", "지도 인프라 조회결과 없음");
	
	
	public final static ResultCode RC_8C000000 = new ResultCode("8C000000", "주차 위치 조회 정보 없음");
	public final static ResultCode RC_8C000001 = new ResultCode("8C000001", "주차 위치 거리(1km 초과)");
	
	
	
	//9대역Send2Car 활용
	public final static ResultCode RC_9C000000 = new ResultCode("9C000000", "목적지 히스토리 조회 결과 없음.");
	public final static ResultCode RC_9C000001 = new ResultCode("9C000001", "삭제 대상 목적지 정보 없음.");
	public final static ResultCode RC_9C000002 = new ResultCode("9C000002", "목적지 히스토리 중복.");
	public final static ResultCode RC_9C000003 = new ResultCode("9C000003", "Send2Car 실패 - (예상 소요 시간 > 도착 희망 시간) ");
	public final static ResultCode RC_9C000004 = new ResultCode("9C000004", "타임머신 조회 결과 없음.");
	

	public final static ResultCode RC_9C100000 = new ResultCode("9C100000", "일정 서비스 조회 결과 없음.");
	
	public final static ResultCode RC_9C200000 = new ResultCode("9C200000", "도착 알림 서비스 조회 결과 없음.");
	//public final static ResultCode RC_9C100001 = new ResultCode("9C000001", "삭제 대상 목적지 정보 없음.");
	
	public final static ResultCode RC_9C300000 = new ResultCode("9C300000", "공지 사항 서비스 조회 결과 없음.");
	
	public final static ResultCode RC_TEST = new ResultCode("TEST", "TEST Code.");
	
	
	public static String createResultMsgToJsonString(ResultCode resultCode, String api) {
		ResponseJSON resp = new ResponseJSON();
		resp.getCommon().setApiId(api);
		resp.setResultCode(resultCode.getCode());
		resp.setResultMsg(resultCode.getMsg());
		Gson gson = new Gson();
		String resultMsgJson = gson.toJson(resp);
		
		return resultMsgJson;
	}
	
	public static ResponseJSON createResultMsg(ResultCode resultCode) {
		ResponseJSON resp = new ResponseJSON();
		resp.setResultCode(resultCode.getCode());
		resp.setResultMsg(resultCode.getMsg());
		
		return resp;
	}
	
	public static ResponseJSON createResultMsg(ResultCode resultCode, String api) {
		ResponseJSON resp = new ResponseJSON();
		resp.getCommon().setApiId(api);
		resp.setResultCode(resultCode.getCode());
		resp.setResultMsg(resultCode.getMsg());
		
		return resp;
	}
	
	public static ResponseJSON createResultMsg(ResultCode resultCode, String api, String subReason) {
		ResponseJSON resp = new ResponseJSON();
		resp.getCommon().setApiId(api);
		resp.setResultCode(resultCode.getCode());
		resp.setResultMsg(resultCode.getMsg() + " | " + subReason);
		
		return resp;
	}
	
	public static ResponseJSON createResultMsg(ResultCode resultCode, Object data, String api) {
		ResponseJSON resp = new ResponseJSON();
		resp.getCommon().setApiId(api);
		resp.setResultCode(resultCode.getCode());
		resp.setResultMsg(resultCode.getMsg());
		resp.setResultData(data);
		
		return resp;
	}
	
}
