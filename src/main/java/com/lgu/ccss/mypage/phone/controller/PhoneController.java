package com.lgu.ccss.mypage.phone.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lgu.ccss.mypage.phone.service.PhoneService;

@Controller
@RequestMapping(value = "/mypage/phone")
public class PhoneController {

	private static final Logger logger = LoggerFactory.getLogger(PhoneController.class);

	@Resource(name = "phoneService")
	private PhoneService phoneService;

	@RequestMapping(value = "/sample.do", method = RequestMethod.GET)
	public String processSample(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
			Model model) {
		return "phone/sample";
	}

	/**
	 * 요금제에 따라 페이지 분기
	 * 
	 * @param httpServletRequest
	 * @param model
	 * @return
	 * 
	 */
	@RequestMapping(value = "/main.do", method = RequestMethod.GET)
	public String processMain(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
			Model model) {
		return phoneService.getMain(httpServletRequest, httpServletResponse, model);
	}

	// ######################################################################
	// 인증
	// ######################################################################

	/**
	 * 인증선택
	 * 
	 * @param httpServletRequest
	 * @param model
	 * @return
	 * 
	 */
	@RequestMapping(value = "/authChoice.do", method = RequestMethod.GET)
	public String processAuthChoice(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
			Model model) {
		return phoneService.getAuthChoice(httpServletRequest, httpServletResponse, model);
	}
	
	/**
	 * 인증선택 unlimitBillType
	 * 
	 * @param httpServletRequest
	 * @param model
	 * @return
	 * 
	 */
	@RequestMapping(value = "/authChoiceKmc.do", method = RequestMethod.GET)
	public String processAuthChoiceKmc(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
			Model model) {
		return phoneService.getAuthChoiceKmc(httpServletRequest, httpServletResponse, model);
	}

	/**
	 * 본인인증 > 나이스아이핀 > 결과
	 * 
	 * @param httpServletRequest
	 * @param model
	 * @return
	 * 
	 */
	@RequestMapping(value = "/authIpinMain.do", method = RequestMethod.GET)
	public String processAuthIpinMain(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
			Model model) {
		return phoneService.getAuthIpinMain(httpServletRequest, httpServletResponse, model);
	}

	/**
	 * 본인인증 > 나이스아이핀
	 * 
	 * @param httpServletRequest
	 * @param model
	 * @return
	 * 
	 */
	@RequestMapping(value = "/authIpinResult.do", method = { RequestMethod.GET, RequestMethod.POST })
	public String processAuthIpinResult(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
			Model model) {
		return phoneService.getAuthIpinResult(httpServletRequest, httpServletResponse, model);
	}

	/**
	 * 본인인증 > 휴대폰본인인증
	 * 
	 * @param httpServletRequest
	 * @param model
	 * @return
	 * 
	 */
	@RequestMapping(value = "/authKmcMain.do", method = RequestMethod.GET)
	public String processAuthKmcMain(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
			Model model) {
		return phoneService.getAuthKmcMain(httpServletRequest, httpServletResponse, model);
	}

	/**
	 * 본인인증 > 휴대폰본인인증 > 결과
	 * 
	 * @param httpServletRequest
	 * @param model
	 * @return
	 * 
	 */
	@RequestMapping(value = "/authKmcResult.do", method = { RequestMethod.GET, RequestMethod.POST })
	public String processAuthKmcResult(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
			Model model) {
		return phoneService.getAuthKmcResult(httpServletRequest, httpServletResponse, model);
	}

	// ######################################################################
	// 월정액요금제1
	// ######################################################################

	/**
	 * 월정액요금제1 > 메뉴
	 * 
	 * @param httpServletRequest
	 * @param model
	 * @return
	 * 
	 */
	@RequestMapping(value = "/limitMenu.do", method = RequestMethod.GET)
	public String processLimitMenu(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
			Model model) {
		return phoneService.getLimitMenu(httpServletRequest, httpServletResponse, model);
	}

	/**
	 * 월정액요금제1 > 가입정보
	 * 
	 * @param httpServletRequest
	 * @param model
	 * @return
	 * 
	 */
	@RequestMapping(value = "/limitSubsInfo.do", method = RequestMethod.GET)
	public String processLimitSubsInfo(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
			Model model) {
		return phoneService.getLimitSubsInfo(httpServletRequest, httpServletResponse, model);
	}

	/**
	 * 월정액요금제1 > 요금제신청
	 * 
	 * @param httpServletRequest
	 * @param model
	 * @return
	 * 
	 */
	@RequestMapping(value = "/limitApplyPlan.do", method = RequestMethod.GET)
	public String processLimitApplyCharge(HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse, Model model) {
		return phoneService.getLimitApplyPlan(httpServletRequest, httpServletResponse, model);
	}

	/**
	 * 월정액요금제1 > 요금제신청 > 요금 납부 정보
	 * 
	 * @param httpServletRequest
	 * @param model
	 * @return
	 * 
	 */
	@RequestMapping(value = "/limitEnterBillInfo.do", method = RequestMethod.GET)
	public String processLimitEnterBillInfo(HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse, Model model) {
		return phoneService.getLimitEnterBillInfo(httpServletRequest, httpServletResponse, model);
	}

	/**
	 * 월정액요금제1 > 이용약관
	 * 
	 * @param httpServletRequest
	 * @param model
	 * @return
	 * 
	 */
	@RequestMapping(value = "/limitTerms.do", method = RequestMethod.GET)
	public String processLimitTerms(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
			Model model) {
		return phoneService.getLimitTerms(httpServletRequest, httpServletResponse, model);
	}

	// ######################################################################
	// 월정액요금제2
	// ######################################################################

	/**
	 * 월정액요금제2 > 메뉴
	 * 
	 * @param httpServletRequest
	 * @param model
	 * @return
	 * 
	 */
	@RequestMapping(value = "/unlimitMenu.do", method = RequestMethod.GET)
	public String processUnlimitedMenu(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
			Model model) {
		return "phone/unlimitMenu";
	}

	/**
	 * 월정액요금제2 > 가입정보
	 * 
	 * @param httpServletRequest
	 * @param model
	 * @return
	 * 
	 */
	@RequestMapping(value = "/unlimitSubsInfo.do", method = RequestMethod.GET)
	public String processUnlimitedSubsInfo(HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse, Model model) {
		return phoneService.getUnlimitSubsInfo(httpServletRequest, httpServletResponse, model);
	}

	/**
	 * 월정액요금제2 > 청구서조회 > 요금납부정보
	 * 
	 * @param httpServletRequest
	 * @param model
	 * @return
	 * 
	 */
	@RequestMapping(value = "/unlimitPayInfo.do", method = RequestMethod.GET)
	public String processUnlimitPayInfo(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
			Model model) {
		return phoneService.getUnlimitPayInfo(httpServletRequest, httpServletResponse, model);
	}
	
	
	/**
	 * 월정액요금제2 > 청구서조회 > 요금납부정보 > 납부방법변경
	 * 
	 * @param httpServletRequest
	 * @param model
	 * @return
	 * 
	 */
	@RequestMapping(value = "/unlimitPayMethod.do", method = RequestMethod.GET)
	public String processUnlimitPayMethod(HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse, Model model) {
		return phoneService.getUnlimitPayMethod(httpServletRequest, httpServletResponse, model);
	}

	/**
	 * 월정액요금제2 > 청구서조회 > 요금납부정보 > 청구유형변경
	 * 
	 * @param httpServletRequest
	 * @param model
	 * @return
	 * 
	 */
	@RequestMapping(value = "/unlimitBillType.do", method = RequestMethod.GET)
	public String processUnlimitBillType(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
			Model model) {
		return phoneService.getUnlimitBillType(httpServletRequest, httpServletResponse, model);
	}
	
	
	/**
	 * 월정액요금제2 > 청구서조회 > 상세내역조회 (청구서확인)
	 * 
	 * @param httpServletRequest
	 * @param model
	 * @return
	 * 
	 */
	@RequestMapping(value = "/unlimitPayDetail.do", method = RequestMethod.GET)
	public String processUnlimitPayDetail(HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse, Model model) {
		return phoneService.getUnlimitPayDetail(httpServletRequest, httpServletResponse, model);
	}

	/**
	 * 월정액요금제2 > 서비스변경내역 > 서비스일시정지내역
	 * 
	 * @param httpServletRequest
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/unlimitPauseHistory.do", method = RequestMethod.GET)
	public String processUnlimitPauseHistory(HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse, Model model) {
		return phoneService.getUnlimitPauseHistory(httpServletRequest, httpServletResponse, model);
	}

	/**
	 * 월정액요금제2 > 이용약관
	 * 
	 * @param httpServletRequest
	 * @param model
	 * @return
	 * 
	 */
	@RequestMapping(value = "/unlimitTerms.do", method = RequestMethod.GET)
	public String processUnlimitTerms(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
			Model model) {
		return phoneService.getUnlimitTerms(httpServletRequest, httpServletResponse, model);
	}

	/**
	 * 월정액요금제2 > 서비스변경내역 > 서비스일시정지신청
	 * 
	 * @param httpServletRequest
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/unlimitRequestPause.do", method = RequestMethod.GET)
	public String processUnlimitRequestPause(HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse, Model model) {
		return phoneService.getUnlimitRequestPause(httpServletRequest, httpServletResponse, model);
	}

	////////////////////////////////////////////////////////////////////////
	// 납부방법변경
	////////////////////////////////////////////////////////////////////////

	@RequestMapping(value = "/changeMethod", method = RequestMethod.POST, produces = {
			"application/json; charset=UTF-8" })
	@ResponseBody
	public String processChangeMethod(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
			@RequestBody String body) {
		return phoneService.getChangeMethod(httpServletRequest, httpServletResponse, body);
	}

	////////////////////////////////////////////////////////////////////////
	// 청구유형변경
	////////////////////////////////////////////////////////////////////////

	@RequestMapping(value = "/changeType", method = RequestMethod.POST, produces = {
			"application/json; charset=UTF-8" })
	@ResponseBody
	public String processChangeType(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
			@RequestBody String body) {
		return phoneService.getChangeType(httpServletRequest, httpServletResponse, body);
	}

	////////////////////////////////////////////////////////////////////////
	// 일시정지
	////////////////////////////////////////////////////////////////////////

	@RequestMapping(value = "/stopService", method = RequestMethod.POST, produces = {
			"application/json; charset=UTF-8" })
	@ResponseBody
	public String processStopService(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
			@RequestBody String body) {
		return phoneService.getStopService(httpServletRequest, httpServletResponse, body);
	}

	////////////////////////////////////////////////////////////////////////
	// 일시정지 해제
	////////////////////////////////////////////////////////////////////////

	@RequestMapping(value = "/unpauseService", method = RequestMethod.POST, produces = {
			"application/json; charset=UTF-8" })
	@ResponseBody
	public String processUnpauseService(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
			@RequestBody String body) {
		return phoneService.getUnpauseService(httpServletRequest, httpServletResponse, body);
	}

	////////////////////////////////////////////////////////////////////////
	// 요금제신청
	////////////////////////////////////////////////////////////////////////
	//produces = 영어가아닌 한글을 반환할때 produces 로 컨텐트타입을 지정할수 있다.
	@RequestMapping(value = "/applyPlan", method = RequestMethod.POST, produces = { "application/json; charset=UTF-8" })
	@ResponseBody
	public String processApplyPlan(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
			@RequestBody String body) {
		return phoneService.getApplyPlan(httpServletRequest, httpServletResponse, body);
	}

	////////////////////////////////////////////////////////////////////////
	// 납부방법검증(사용안함)
	////////////////////////////////////////////////////////////////////////

	@RequestMapping(value = "/verifyPayment", method = RequestMethod.POST, produces = {
			"application/json; charset=UTF-8" })
	@ResponseBody
	public String processVerifyPayment(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
			@RequestBody String body) {
		return phoneService.getVerifyPayment(httpServletRequest, httpServletResponse, body);
	}

	/**
	 * 서비스 이용약관 
	 *  2018-11-27 추가
	 * @param httpServletRequest
	 * @param 
	 * @return
	 * 
	 */
	@RequestMapping(value = "/agreement.do", method = RequestMethod.GET)
	public String processAgreement(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse
			,Model model) {
		return phoneService.getAgreement(httpServletRequest,httpServletResponse,model);
	}
	
	/**
	 * 서비스 이용약관자세히보기
	 *  2018-12-01 추가
	 * @param httpServletRequest
	 * @param 
	 * @return
	 * 
	 */
	@RequestMapping(value = "/agreementCustom/{flag}", method=RequestMethod.GET)
	public String processAgreementCustom(HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse,Model model,@PathVariable String flag) {
		logger.debug("processAgreementCustom = " + flag);
		return phoneService.getAgreementCustom(httpServletRequest,httpServletResponse,model,flag);
	}
	
}
