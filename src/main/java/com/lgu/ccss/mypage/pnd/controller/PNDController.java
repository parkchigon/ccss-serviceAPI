package com.lgu.ccss.mypage.pnd.controller;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.http.HttpHeaders;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lgu.ccss.mypage.pnd.service.PNDService;

@Controller
@RequestMapping(value = "/mypage/pnd")
public class PNDController {
	@Resource(name = "pNDService")
	private PNDService pNDService;
	
	private Pattern VALID_USER_AGENT_REGEX = Pattern.compile("\\[(.*)\\,(.*)\\]", Pattern.CASE_INSENSITIVE);

	@RequestMapping(value = "/sample.do", method = RequestMethod.GET)
	public String processSample(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
			Model model) {
		return "sample";
	}

	@RequestMapping("**/favicon.ico")
	public String favicon() {
		return "forward:/resources/images/favicon.ico";
	}

	/**
	 * 
	 * @param httpServletRequest
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/errorPage.do", method = RequestMethod.GET)
	public String processErrorPage(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
			Model model) {
		
		//접속 디바이스 모델 찾기 20190128 kangjin
		model.addAttribute("modelNmGbn", getConnDeviceModel(httpServletRequest));		
		
		return "errorPage";
	}

	/**
	 * 
	 * @param httpServletRequest
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/errorFullPage.do", method = RequestMethod.GET)
	public String processErrorFullPage(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
			Model model) {
		
		//접속 디바이스 모델 찾기 20190128 kangjin
		model.addAttribute("modelNmGbn", getConnDeviceModel(httpServletRequest));		
		
		return "errorFullPage";
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

		//접속 디바이스 모델 찾기 20190128 kangjin
		model.addAttribute("modelNmGbn", httpServletRequest.getParameter("modelNmGbn"));				
		
		return pNDService.getAuthChoice(httpServletRequest, httpServletResponse, model);
	}
	
	/**
	 * 인증선택
	 * 
	 * @param httpServletRequest
	 * @param model
	 * @return
	 * 
	 */
	@RequestMapping(value = "/authChoiceKmc.do", method = RequestMethod.GET)
	public String processAuthChoiceKmc(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
			Model model) {
		
		//접속 디바이스 모델 찾기 20190128 kangjin
		model.addAttribute("modelNmGbn", getConnDeviceModel(httpServletRequest));		
		
		return pNDService.getAuthChoice(httpServletRequest, httpServletResponse, model);
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
		
		//접속 디바이스 모델 찾기 20190128 kangjin
		model.addAttribute("modelNmGbn", getConnDeviceModel(httpServletRequest));		
		
		return pNDService.getAuthIpinMain(httpServletRequest, httpServletResponse, model);
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
		
		//접속 디바이스 모델 찾기 20190128 kangjin
		model.addAttribute("modelNmGbn", getConnDeviceModel(httpServletRequest));		
		
		return pNDService.getAuthIpinResult(httpServletRequest, httpServletResponse, model);
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
		
		//접속 디바이스 모델 찾기 20190128 kangjin
		model.addAttribute("modelNmGbn", getConnDeviceModel(httpServletRequest));		
		
		return pNDService.getAuthKmcMain(httpServletRequest, httpServletResponse, model);
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
		
		//접속 디바이스 모델 찾기 20190128 kangjin
		model.addAttribute("modelNmGbn", getConnDeviceModel(httpServletRequest));		
		
		return pNDService.getAuthKmcResult(httpServletRequest, httpServletResponse, model);
	}

	// ######################################################################
	// 월정액요금제1
	// ######################################################################

	/**
	 * 월정액요금제1 > 가입안내
	 * 
	 * @param httpServletRequest
	 * @param model
	 * @return
	 * 
	 */
	@RequestMapping(value = "/limitIntroduction.do", method = RequestMethod.GET)
	public String processLimitIntroduction(HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse, Model model) {
		
		//접속 디바이스 모델 찾기 20190128 kangjin
		model.addAttribute("modelNmGbn", getConnDeviceModel(httpServletRequest));		
		
		return pNDService.getLimitIntroduction(httpServletRequest, httpServletResponse, model);
	}

	/**
	 * 월정액요금제1 > 서비스약관동의
	 * 
	 * @param httpServletRequest
	 * @param model
	 * @return
	 * 
	 */
	@RequestMapping(value = "/limitAcceptTerms.do", method = RequestMethod.GET)
	public String processLimitAcceptTerms(HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse, Model model) {
		
		//접속 디바이스 모델 찾기 20190128 kangjin
		model.addAttribute("modelNmGbn", getConnDeviceModel(httpServletRequest));		
		
		return pNDService.getLimitAcceptTerms(httpServletRequest, httpServletResponse, model);
	}

	/**
	 * 월정액요금제1 > 가입완료
	 * 
	 * @param httpServletRequest
	 * @param model
	 * @return
	 * 
	 */
	@RequestMapping(value = "/limitCompleted.do", method = RequestMethod.GET)
	public String processLimitCompleted(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
			Model model) {
		
		//접속 디바이스 모델 찾기 20190128 kangjin
		model.addAttribute("modelNmGbn", getConnDeviceModel(httpServletRequest));		
		
		return pNDService.getLimitCompleted(httpServletRequest, httpServletResponse, model);
	}

	// ######################################################################
	// 월정액요금제1
	// ######################################################################

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
		
		//접속 디바이스 모델 찾기 20190128 kangjin
		model.addAttribute("modelNmGbn", getConnDeviceModel(httpServletRequest));		
		
		return pNDService.getLimitSubsInfo(httpServletRequest, httpServletResponse, model);
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
		
		//접속 디바이스 모델 찾기 20190128 kangjin
		model.addAttribute("modelNmGbn", getConnDeviceModel(httpServletRequest));		
		
		return pNDService.getLimitApplyPlan(httpServletRequest, httpServletResponse, model);
	}

	/**
	 * 월정액요금제1 > 요금제신청 > 청구정보입력
	 * 
	 * @param httpServletRequest
	 * @param model
	 * @return
	 * 
	 */
	@RequestMapping(value = "/limitEnterBillInfo.do", method = RequestMethod.GET)
	public String processLimitEnterBillInfo(HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse, Model model) {
		
		//접속 디바이스 모델 찾기 20190128 kangjin
		model.addAttribute("modelNmGbn", getConnDeviceModel(httpServletRequest));		
		
		return pNDService.getLimitEnterBillInfo(httpServletRequest, httpServletResponse, model);
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
		
		//접속 디바이스 모델 찾기 20190128 kangjin
		model.addAttribute("modelNmGbn", getConnDeviceModel(httpServletRequest));		
		
		return pNDService.getLimitTerms(httpServletRequest, httpServletResponse, model);
	}

	/**
	 * 월정액요금제1 > 종료임박안내
	 * 
	 * @param httpServletRequest
	 * @param model
	 * @return
	 * 
	 */
	@RequestMapping(value = "/limitExitImminent.do", method = RequestMethod.GET)
	public String processLimitExitImminent(HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse, Model model) {
		
		//접속 디바이스 모델 찾기 20190128 kangjin
		model.addAttribute("modelNmGbn", getConnDeviceModel(httpServletRequest));		
		
		return pNDService.getLimitExitImminent(httpServletRequest, httpServletResponse, model);
	}

	/**
	 * 월정액요금제1 > 종료안내
	 * 
	 * @param httpServletRequest
	 * @param model
	 * @return
	 * 
	 */
	@RequestMapping(value = "/limitExitGuide.do", method = RequestMethod.GET)
	public String processLimitExitGuide(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
			Model model) {
		
		//접속 디바이스 모델 찾기 20190128 kangjin
		model.addAttribute("modelNmGbn", getConnDeviceModel(httpServletRequest));		
		
		return pNDService.getLimitExitGuide(httpServletRequest, httpServletResponse, model);
	}

	// ######################################################################
	// 월정액요금제2
	// ######################################################################

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
		
		//접속 디바이스 모델 찾기 20190128 kangjin
		model.addAttribute("modelNmGbn", getConnDeviceModel(httpServletRequest));		
		
		return pNDService.getUnlimitSubsInfo(httpServletRequest, httpServletResponse, model);
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
		
		//접속 디바이스 모델 찾기 20190128 kangjin
		model.addAttribute("modelNmGbn", getConnDeviceModel(httpServletRequest));		
		
		return pNDService.getUnlimitPayInfo(httpServletRequest, httpServletResponse, model);
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
		
		//접속 디바이스 모델 찾기 20190128 kangjin
		model.addAttribute("modelNmGbn", getConnDeviceModel(httpServletRequest));		
		
		return pNDService.getUnlimitPayMethod(httpServletRequest, httpServletResponse, model);
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
		
		//접속 디바이스 모델 찾기 20190128 kangjin
		model.addAttribute("modelNmGbn", getConnDeviceModel(httpServletRequest));		
		
		return pNDService.getUnlimitBillType(httpServletRequest, httpServletResponse, model);
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
		
		//접속 디바이스 모델 찾기 20190128 kangjin
		model.addAttribute("modelNmGbn", getConnDeviceModel(httpServletRequest));		
		
		return pNDService.getUnlimitPayDetail(httpServletRequest, httpServletResponse, model);
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
		
		//접속 디바이스 모델 찾기 20190128 kangjin
		model.addAttribute("modelNmGbn", getConnDeviceModel(httpServletRequest));		
		
		return pNDService.getUnlimitPauseHistory(httpServletRequest, httpServletResponse, model);
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
		
		//접속 디바이스 모델 찾기 20190128 kangjin
		model.addAttribute("modelNmGbn", getConnDeviceModel(httpServletRequest));		
		
		return pNDService.getUnlimitTerms(httpServletRequest, httpServletResponse, model);
	}

	////////////////////////////////////////////////////////////////////////
	// 납부방법변경
	////////////////////////////////////////////////////////////////////////

	@RequestMapping(value = "/changeMethod", method = RequestMethod.POST, produces = {
			"application/json; charset=UTF-8" })
	@ResponseBody
	public String processChangeMethod(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
			@RequestBody String body) {	
		
		return pNDService.getChangeMethod(httpServletRequest, httpServletResponse, body);
	}

	////////////////////////////////////////////////////////////////////////
	// 청구유형변경
	////////////////////////////////////////////////////////////////////////

	@RequestMapping(value = "/changeType", method = RequestMethod.POST, produces = {
			"application/json; charset=UTF-8" })
	@ResponseBody
	public String processChangeType(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
			@RequestBody String body) {	
		
		return pNDService.getChangeType(httpServletRequest, httpServletResponse, body);
	}

	////////////////////////////////////////////////////////////////////////
	// 일시정지
	////////////////////////////////////////////////////////////////////////

	@RequestMapping(value = "/stopService", method = RequestMethod.POST, produces = {
			"application/json; charset=UTF-8" })
	@ResponseBody
	public String processStopService(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
			@RequestBody String body) {		
		
		return pNDService.getStopService(httpServletRequest, httpServletResponse, body);
	}
	
	////////////////////////////////////////////////////////////////////////
	// 일시정지 해제
	////////////////////////////////////////////////////////////////////////

	@RequestMapping(value = "/unpauseService", method = RequestMethod.POST, produces = {
			"application/json; charset=UTF-8" })
	@ResponseBody
	public String processUnpauseService(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
			@RequestBody String body) {		
		
		return pNDService.getUnpauseService(httpServletRequest, httpServletResponse, body);
	}

	////////////////////////////////////////////////////////////////////////
	// 일시정지 예약해제
	////////////////////////////////////////////////////////////////////////
	
	@RequestMapping(value = "/unpauseRsvService", method = RequestMethod.POST, produces = {
	"application/json; charset=UTF-8" })
	@ResponseBody
	public String processUnpauseRsvService(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
	@RequestBody String body) {
	return pNDService.getUnpauseRsvService(httpServletRequest, httpServletResponse, body);
	}
	
	////////////////////////////////////////////////////////////////////////
	// 요금제신청
	////////////////////////////////////////////////////////////////////////

	@RequestMapping(value = "/applyPlan", method = RequestMethod.POST, produces = { "application/json; charset=UTF-8" })
	@ResponseBody
	public String processApplyPlan(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
			@RequestBody String body) {
		return pNDService.getApplyPlan(httpServletRequest, httpServletResponse, body);
	}

	////////////////////////////////////////////////////////////////////////
	// 납부방법검증(사용안함)
	////////////////////////////////////////////////////////////////////////

	@RequestMapping(value = "/verifyPayment", method = RequestMethod.POST, produces = {
			"application/json; charset=UTF-8" })
	@ResponseBody
	public String processVerifyPayment(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
			@RequestBody String body) {
		return pNDService.getVerifyPayment(httpServletRequest, httpServletResponse, body);
	}
	
	/**
	 * 일시정지 해제 완료
	 * 
	 * @param httpServletRequest
	 * @param model
	 * @return
	 * 
	 */
	@RequestMapping(value = "/unpauseResult.do", method = RequestMethod.GET)
	public String processUnpauseResult(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
			Model model) {
		
		//접속 디바이스 모델 찾기 20190128 kangjin
		model.addAttribute("modelNmGbn", getConnDeviceModel(httpServletRequest));		
		
		return pNDService.getUnpauseResult(httpServletRequest, httpServletResponse, model);
	}
	
	/**
	 * 일시정지 해제 신청
	 * 
	 * @param httpServletRequest
	 * @param model
	 * @return
	 * 
	 */
	@RequestMapping(value = "/unpauseApply.do", method = RequestMethod.GET)
	public String processUnpauseApply(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
			Model model) {
		
		//접속 디바이스 모델 찾기 20190128 kangjin
		model.addAttribute("modelNmGbn", getConnDeviceModel(httpServletRequest));		
		
		return pNDService.getUnpauseApply(httpServletRequest, httpServletResponse, model);
	}
	
	/**
	 * 일시정지 예약해제 신청
	 * 
	 * @param httpServletRequest
	 * @param model
	 * @return
	 * 
	 */
	@RequestMapping(value = "/unpauseRsvApply.do", method = RequestMethod.GET)
	public String processUnpauseRsvApply(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
			Model model) {
		
		//접속 디바이스 모델 찾기 20190128 kangjin
		model.addAttribute("modelNmGbn", getConnDeviceModel(httpServletRequest));		
		
		return pNDService.getUnpauseRsvApply(httpServletRequest, httpServletResponse, model);
	}
	
	/**
	 * 접속모델명 조회 20190128 kangjin
	 */	
	private String getConnDeviceModel(HttpServletRequest httpServletRequest) {
		
		String userAgent = httpServletRequest.getHeader(HttpHeaders.USER_AGENT);

		String deviceCtn = "";
		String ccssToken = "";		
		String modelname = "";

		Matcher matcher = VALID_USER_AGENT_REGEX.matcher(userAgent);
		if (matcher.find()) {
			deviceCtn = matcher.group(1);
			
			String[] arr = StringUtils.splitByWholeSeparator(deviceCtn, ",");
			
			if(arr.length == 2) {
				deviceCtn = arr[0];
				ccssToken = arr[1];
				modelname = matcher.group(2);
			}else {
				deviceCtn = matcher.group(1);
				ccssToken = matcher.group(2);
			}

		}
		
		return modelname;
	}	
	
}
