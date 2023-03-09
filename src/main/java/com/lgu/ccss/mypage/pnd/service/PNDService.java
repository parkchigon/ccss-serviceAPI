package com.lgu.ccss.mypage.pnd.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.ui.Model;

public interface PNDService {

	// 본인인증 > 선택
	public String getAuthChoice(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
			Model model);
	
	// 본인인증 > kmc선택
		public String getAuthChoiceKmc(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
				Model model);

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

	// 월정액요금제1 > 가입안내
	public String getLimitIntroduction(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
			Model model);

	// 월정액요금제1 > 서비스약관동의(DB)
	public String getLimitAcceptTerms(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
			Model model);
	
	// 월정액요금제1 > 가입완료
	public String getLimitCompleted(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
			Model model);

	// ######################################################################

	// 월정액요금제1 > 종료안내
	public String getLimitExitGuide(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
			Model model);

	// 월정액요금제1 > 종료임박안내
	public String getLimitExitImminent(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
			Model model);

	// 월정액요금제1 > 가입정보
	public String getLimitSubsInfo(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
			Model model);

	// 월정액요금제1 > 요금제신청
	public String getLimitApplyPlan(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
			Model model);

	// 월정액요금제1 > 요금제신청 > 청구정보입력
	public String getLimitEnterBillInfo(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
			Model model);

	// 월정액요금제1 > 이용약관(DB)
	public String getLimitTerms(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
			Model model);

	// ######################################################################

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

	// 월정액요금제2 > 청구서조회 > 상세내역조회
	public String getUnlimitPayDetail(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
			Model model);

	// 월정액요금제2 > 서비스변경내역 > 서비스일시정지내역
	public String getUnlimitPauseHistory(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
			Model model);

	// 월정액요금제2 > 이용약관(DB)
	public String getUnlimitTerms(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
			Model model);

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

	// 일시정지 해제
	public String getUnpauseRsvService(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
			String body);
	
	// 요금제신청
	public String getApplyPlan(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
			String body);

	// 납부방법검증
	public String getVerifyPayment(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
			String body);

	public String getUnpauseResult(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
			Model model);
	
	//일시정지해제신청
	public String getUnpauseApply(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
			Model model);
	
	//일시정지예약해제신청
	public String getUnpauseRsvApply(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
			Model model);
}
