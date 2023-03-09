package com.lgu.ccss.mypage.phone.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;

public interface PhoneService {

	// 요금제에 따라 페이지 분기
	public String getMain(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Model model);

	// ######################################################################
	// 인증
	// ######################################################################

	// 인증선택
	public String getAuthChoice(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
			Model model);
	
	// 인증선택 unlimitBillType
	public String getAuthChoiceKmc(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
			Model mode);

	// 본인인증 > 나이스아이핀
	public String getAuthIpinMain(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
			Model model);

	// 본인인증 > 나이스아이핀 > 결과
	public String getAuthIpinResult(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
			Model model);

	// 본인인증 > 휴대폰본인인증
	public String getAuthKmcMain(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
			Model model);

	// 본인인증 > 휴대폰본인인증 > 결과
	public String getAuthKmcResult(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
			Model model);

	// ######################################################################
	// 월정액요금제1
	// ######################################################################

	// 월정액요금제1 > 메뉴
	public String getLimitMenu(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
			Model model);

	// 월정액요금제1 > 가입정보
	public String getLimitSubsInfo(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
			Model model);

	// 월정액요금제1 > 요금제신청
	public String getLimitApplyPlan(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
			Model model);

	// 월정액요금제2 > 요금제신청 > 청구정보입력
	public String getLimitEnterBillInfo(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
			Model model);

	// 월정액요금제1 > 이용약관
	public String getLimitTerms(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
			Model model);

	// ######################################################################
	// 월정액요금제2
	// ######################################################################

	// 월정액요금제2 > 메뉴
	public String getUnlimitedMenu(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
			Model model);

	// 월정액요금제2 > 가입정보
	public String getUnlimitSubsInfo(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
			Model model);

	// 월정액요금제2 > 청구서조회 > 요금납부정보
	public String getUnlimitPayInfo(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
			Model model);

	// 월정액요금제2 > 청구서조회 > 요금납부정보 > 납부방법변경
	public String getUnlimitPayMethod(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
			Model model);

	// 월정액요금제2 > 청구서조회 > 요금납부정보 > 청구유형변경
	public String getUnlimitBillType(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
			Model model);

	// 월정액요금제2 > 청구서조회 > 상세내역조회 (청구서확인)
	public String getUnlimitPayDetail(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
			Model model);

	// 월정액요금제2 > 서비스변경내역 > 서비스일시정지내역
	public String getUnlimitPauseHistory(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
			Model model);

	// 월정액요금제2 > 이용약관
	public String getUnlimitTerms(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
			Model model);

	// 월정액요금제2 > 서비스변경내역 > 서비스일시정지신청
	public String getUnlimitRequestPause(HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse, Model model);

	// ######################################################################

	// 납부방법변경
	public String getChangeMethod(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
			String body);

	// 청구유형변경
	public String getChangeType(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
			String body);

	// 일시정지
	public String getStopService(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
			String body);

	// 일시정지 해제
	public String getUnpauseService(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
			String body);

	// 요금제신청
	public String getApplyPlan(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
			String body);

	// 납부방법검증
	public String getVerifyPayment(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
			String body);
	
	
	//	#####################################################################
	//서비스 이용약관 동의 2018-11-27
	public String getAgreement(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
			Model model);
	
	//서비스 이용약관 리스트 2018-12-3
	public String getAgreementCustom(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
			Model model,@PathVariable String flag);
	
	
}
