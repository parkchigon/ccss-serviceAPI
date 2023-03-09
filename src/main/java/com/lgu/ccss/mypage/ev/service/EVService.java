package com.lgu.ccss.mypage.ev.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.ui.Model;

public interface EVService {

	public String selectEVChrgInfo(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
			Model model);
}
