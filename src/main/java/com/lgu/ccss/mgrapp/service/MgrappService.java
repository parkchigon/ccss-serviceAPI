package com.lgu.ccss.mgrapp.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.ui.Model;
import org.springframework.util.MultiValueMap;

public interface MgrappService {
	
	// Home IOT 로그인 > 로그인 결과 
	public void saveLoginHomeIOTCallback(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
				Model model,MultiValueMap<String, String> requstBody);
	
		
	// Home IOT 로그인 > 로그인 결과 
	public String getAuthHomeIOTResult(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
			Model model,MultiValueMap<String, String> requstBody);
	
	// 지니 로그인 > 로그인 결과 
	public String getAuthGenieResult(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
			Model model,MultiValueMap<String, String> requstBody);
	
	// CEK 인증 > One ID 조회
		public String getAuthCekOneIdResult(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
				Model model,MultiValueMap<String, String> requstBody);
	
}
