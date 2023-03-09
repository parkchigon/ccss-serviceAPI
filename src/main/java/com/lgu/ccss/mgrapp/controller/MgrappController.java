package com.lgu.ccss.mgrapp.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.lgu.ccss.mgrapp.service.MgrappService;

@Controller
@RequestMapping(value = "/mgrapp/infortainment")
public class MgrappController {
	private static final Logger logger = LoggerFactory.getLogger(MgrappController.class);

	@Resource(name = "mgrappService")
	private MgrappService mgrappService;

	/**
	 * CEK Home IOT 
	 * 
	 * @param httpServletRequest
	 * @param model
	 * @return
	 * 
	 */
	@RequestMapping(value = "/cekAuthProcess.do", method = RequestMethod.GET)
	public String processCekAuthProcess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
			Model model,@RequestBody MultiValueMap<String, String> requstBody) {
		logger.info(" RequestBody : " + requstBody.toString());
		String 	redirectUrl = "redirect:" + mgrappService.getAuthCekOneIdResult(httpServletRequest, httpServletResponse, model ,requstBody);	
		logger.info(" redirectUrl : " + redirectUrl);
		return redirectUrl;
	}
	
	/**
	 * Home IOT 로그인 > 로그인 결과 (Faid Out)
	 * 
	 * @param httpServletRequest
	 * @param model
	 * @return
	 * 
	 */
	/*@RequestMapping(value = "/loginHomeIOTCallBack.do", method ={RequestMethod.POST ,RequestMethod.GET})
	public void processAuthHomeIOTCallBack(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
		Model model ,@RequestBody MultiValueMap<String, String> requstBody) {
		logger.info(" RequestBody : " + requstBody.toString());
		
		mgrappService.saveLoginHomeIOTCallback(httpServletRequest, httpServletResponse, model ,requstBody);
	}*/
	

	/**
	 * Home IOT 로그인 > 로그인 결과 
	 * 
	 * @param httpServletRequest
	 * @param model
	 * @return
	 * 
	 */
	@RequestMapping(value = "/loginHomeIOTCallBack.do", method ={RequestMethod.POST,RequestMethod.GET})
	public String processAuthHomeIOTResult(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
		Model model ,@RequestBody MultiValueMap<String, String> requstBody/*,HomeIOTVO homeIOTVO*/) {
		logger.info(" RequestBody : " + requstBody.toString());
		String 	redirectUrl = "redirect:" + mgrappService.getAuthHomeIOTResult(httpServletRequest, httpServletResponse, model ,requstBody);
		logger.info(" redirectUrl : " + redirectUrl);
		return redirectUrl;
	}
	
	/**
	 * 지니 앱 인증 요청
	 * 
	 * @param httpServletRequest
	 * @param model
	 * @return
	 * 
	 */  
	@RequestMapping(value = "/loginGenieCallBack.do", method ={RequestMethod.GET})
	public String getProcessGenieAuthResult(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
		Model model , @RequestParam("code") String code) {
		
		logger.info(" RequestBody(code) : " + code);
		String returnURL = "ccss://genie?";
		returnURL+="code=" + code;
		
		String 	redirectUrl = "redirect:" + returnURL;
		logger.info(" redirectUrl : " + redirectUrl);
		return redirectUrl;
	}
	
	@RequestMapping(value = "/loginGenieCallBack.do", method ={RequestMethod.POST})
	public String postProcessGenieAppAuthResult(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
		Model model,@RequestBody MultiValueMap<String, String> requstBody) {
		
		logger.info(" RequestBody : " + requstBody.toString());
		String 	redirectUrl = "redirect:" + mgrappService.getAuthGenieResult(httpServletRequest, httpServletResponse, model ,requstBody);
		logger.info(" redirectUrl : " + redirectUrl);
		return redirectUrl;
	}
	
	
	
}
