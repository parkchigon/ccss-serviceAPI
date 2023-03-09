package com.lgu.ccss.datagift.service;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.ui.Model;

public interface DataGiftService {

	// 데이터 상품권 구매 > 메인 (상품권 구매, 구매내역)
	public String getMain(HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse, Model model);

	// 데이터 상품권 구매 > 메인 > 구매내역
	public String getPurchaseHistory(HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse, Model model);

	// 데이터 상품권 구매 > 메인 > 상품권 구매
	public String getDataGiftList(HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse, Model model);

	// 데이터 상품권 구매 > 메인 > 상품권 구매 > 결제
	public String getPayment(HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse, Model model);

	// Callback - 리다이렉트
	public String getCallback(HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse, Model model) throws ServletException, IOException;
	
	// 최종결제 요청 처리
	public String processLastPayment(HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse, Model model) throws IOException;	
	
	// 최종결제 요청 처리
	public String processCancelPayment(HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse, Model model);	

	// Result - 결과표시
	public String getResult(HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse, Model model);
}
