package com.lgu.ccss.mypage.ev.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.lgu.ccss.mypage.ev.service.EVService;

@Controller
@RequestMapping(value = "/mypage/ev")
public class EVController {

	@Resource(name = "eVService")
	private EVService eVService;


	/**
	 * 전기차 충전정보
	 * 
	 * @param httpServletRequest
	 * @param model
	 * @return
	 * 
	 */
	@RequestMapping(value = "/evChrgInfo.do", method = RequestMethod.GET)
	public String selectEVChrgInfo(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
			Model model) {
		return eVService.selectEVChrgInfo(httpServletRequest, httpServletResponse, model);
	}

}
