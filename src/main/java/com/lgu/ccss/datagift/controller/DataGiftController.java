package com.lgu.ccss.datagift.controller;

import java.io.IOException;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.lgu.ccss.datagift.service.DataGiftService;

@Controller
@RequestMapping(value = "/datagift")
public class DataGiftController {

	@Resource(name = "dataGiftService")
	private DataGiftService dataGiftService;

	/**
	 * 데이터 상품권 구매 > 메인 (상품권 구매, 구매내역)
	 * 
	 * @param httpServletRequest
	 * @param httpServletResponse
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/main.do", method = {RequestMethod.GET, RequestMethod.POST})
	public String getMain(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Model model) {
		
		httpServletResponse.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // HTTP 1.1.
		httpServletResponse.setHeader("Pragma", "no-cache"); // HTTP 1.0.
		httpServletResponse.setDateHeader("Expires", 0); // Proxies.
		
		return dataGiftService.getMain(httpServletRequest, httpServletResponse, model);
	}
	
	
	/**
	 * 데이터 상품권 구매 > 메인 (상품권 구매, 구매내역)
	 * 
	 * @param httpServletRequest
	 * @param httpServletResponse
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/postMain.do", method = {RequestMethod.GET, RequestMethod.POST})
	public String getPostMain(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Model model) {
		return dataGiftService.getMain(httpServletRequest, httpServletResponse, model);
	}

	/**
	 * 데이터 상품권 구매 > 메인 > 구매내역
	 * 
	 * @param httpServletRequest
	 * @param httpServletResponse
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/history.do", method = {RequestMethod.GET, RequestMethod.POST})
	public String processPurchaseHistory(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Model model) {
		return dataGiftService.getPurchaseHistory(httpServletRequest, httpServletResponse, model);
	}

	/**
	 * 데이터 상품권 구매 > 메인 > 상품권 구매
	 * 
	 * @param httpServletRequest
	 * @param httpServletResponse
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/list.do", method = {RequestMethod.GET, RequestMethod.POST})
	public String processDataGiftList(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Model model) {
		return dataGiftService.getDataGiftList(httpServletRequest, httpServletResponse, model);
	}

	/**
	 * 데이터 상품권 구매 > 메인 > 상품권 구매 > 결제
	 * 
	 * @param httpServletRequest
	 * @param httpServletResponse
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/payment.do", method = {RequestMethod.GET, RequestMethod.POST})
	public String processPayment(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Model model) {
		return dataGiftService.getPayment(httpServletRequest, httpServletResponse, model);
	}

	
	/**
	 * 결제 인증 요청에 대한 응답 처리
	 * 
	 * @param httpServletRequest
	 * @param httpServletResponse
	 * @param model
	 * @return
	 * @throws IOException 
	 * @throws ServletException 
	 */
	@RequestMapping(value = "/callback.do", method = { RequestMethod.GET, RequestMethod.POST})
	public String processCertity(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Model model) throws ServletException, IOException {
		return dataGiftService.getCallback(httpServletRequest, httpServletResponse, model);
	}
	
	
	/**
	 * 최종 결제 요청을 처리 한다.
	 * 서비스로그 추가 부분
	 * 
	 * @param httpServletRequest
	 * @param httpServletResponse
	 * @param model
	 * @return
	 * @throws IOException 
	 * @throws ServletException
	 * pgs 
	 */
	@RequestMapping(value = "/payres.do", method = {RequestMethod.GET,RequestMethod.POST})
	public String processPayResp(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Model model) throws ServletException, IOException {
		return dataGiftService.processLastPayment(httpServletRequest, httpServletResponse, model);
	}
	

	
	/**
	 * 결제취소 요청을 처리한다.
	 * 
	 * @param httpServletRequest
	 * @param httpServletResponse
	 * @param model
	 * @return
	 * @throws IOException 
	 * @throws ServletException 
	 */
	@RequestMapping(value = "/cancel.do", method = {RequestMethod.GET, RequestMethod.POST})
	public String processCancelPay(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Model model) throws ServletException, IOException {
		return dataGiftService.processCancelPayment(httpServletRequest, httpServletResponse, model);
	}
	
//	/**
//	 * 데이터 상품권 구매 > 메인 > 상품권 구매 > 결제 > 인증요청
//	 * 
//	 * @param httpServletRequest
//	 * @param httpServletResponse
//	 * @param model
//	 * @return
//	 */
//	@RequestMapping(value = "/certity.do", method = RequestMethod.GET)
//	public String processCertity(HttpServletRequest httpServletRequest,
//			HttpServletResponse httpServletResponse, Model model) {
//
//		return null;  // not implemented!
//	}	
//	
//	
//	/**
//	 * 데이터 상품권 구매 > 메인 > 상품권 발행등록
//	 * 
//	 * @param httpServletRequest
//	 * @param httpServletResponse
//	 * @param model
//	 * @return
//	 */
//	@RequestMapping(value = "/register.do", method = RequestMethod.GET)
//	public String processReg(HttpServletRequest httpServletRequest,
//			HttpServletResponse httpServletResponse, Model model) {
//
//		return null;  // not implemented!
//	}		
	
}
