package com.lgu.ccss.mgr.auth.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lgu.ccss.common.model.RequestJSON;
import com.lgu.ccss.common.model.ResponseJSON;
import com.lgu.ccss.mgr.auth.service.login.MgrAuthLoginService;
import com.lgu.ccss.mgr.auth.service.logout.MgrAuthLogoutService;
import com.lgu.ccss.mgr.auth.service.pushgw.PushgwDeleteService;
import com.lgu.ccss.mgr.auth.service.pushgw.PushgwRegService;
import com.lgu.ccss.mgr.auth.service.register.MgrAuthRegService;
import com.lgu.ccss.mgr.auth.service.sms.MgrAuthSmsCertService;
import com.lgu.ccss.mgr.auth.service.sms.MgrAuthSmsSendService;
import com.lgu.ccss.mgr.auth.service.version.MgrAuthVersionService;


@Controller
@RequestMapping(value = "/mgrapi/authentication")
public class MgrAuthController {

	@Resource(name = "mgrAuthVersionService")
	private MgrAuthVersionService mgrAuthVersionService;
	
	@Resource(name = "mgrAuthRegService")
	private MgrAuthRegService mgrAuthRegService;
	
	@Resource(name = "mgrAuthLoginService")
	private MgrAuthLoginService mgrAuthLoginService;
	
	@Resource(name = "mgrAuthLogoutService")
	private MgrAuthLogoutService mgrAuthLogoutService;
	
	@Resource(name = "mgrAuthSmsSendService")
	private MgrAuthSmsSendService mgrAuthSmsSendService;
	
	@Resource(name = "mgrAuthSmsCertService")
	private MgrAuthSmsCertService mgrAuthSmsCertService;
	
	@Resource(name = "pushgwDeleteService")
	private PushgwDeleteService pushgwDeleteService;
	
	@Resource(name = "pushgwRegService")
	private PushgwRegService pushgwRegService;


	@RequestMapping(value = "/version", method = RequestMethod.POST)
	@ResponseBody
	public ResponseJSON reqVersion(HttpSession session, @RequestHeader HttpHeaders headers,
			@RequestBody RequestJSON reqJson) throws Exception {
		return mgrAuthVersionService.doService(headers, reqJson);
	}
	
	
	@RequestMapping(value = "/register", method = RequestMethod.POST)
	@ResponseBody
	public ResponseJSON reqReg(HttpSession session, @RequestHeader HttpHeaders headers,
			@RequestBody RequestJSON reqJson) throws Exception {
		return mgrAuthRegService.doService(headers, reqJson);
	}
	
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	@ResponseBody
	public ResponseJSON reqLogin(HttpSession session, @RequestHeader HttpHeaders headers,
			@RequestBody RequestJSON reqJson,HttpServletRequest request) throws Exception {
		return mgrAuthLoginService.doService(headers, reqJson,request);
	}
	
	@RequestMapping(value = "/logout", method = RequestMethod.POST)
	@ResponseBody
	public ResponseJSON reqLogout(HttpSession session, @RequestHeader HttpHeaders headers,
			@RequestBody RequestJSON reqJson,HttpServletRequest request) throws Exception {
		return mgrAuthLogoutService.doService(headers, reqJson,request);
	}
	
	
	@RequestMapping(value = "/sms/send", method = RequestMethod.POST)
	@ResponseBody
	public ResponseJSON smsSend(HttpSession session, @RequestHeader HttpHeaders headers,
			@RequestBody RequestJSON reqJson) throws Exception {
		return mgrAuthSmsSendService.doService(headers, reqJson);
	}
	
	@RequestMapping(value = "/sms/cert", method = RequestMethod.POST)
	@ResponseBody
	public ResponseJSON smsCert(HttpSession session, @RequestHeader HttpHeaders headers,
			@RequestBody RequestJSON reqJson) throws Exception {
		return mgrAuthSmsCertService.doService(headers, reqJson);
	}
	
	
	/**
	 * 01. 단말 등록
	 * @param pushVO
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/subscriber/register", method = RequestMethod.POST)
	@ResponseBody
	public ResponseJSON subscriberRegisterApns(HttpSession session, @RequestHeader HttpHeaders headers,
			@RequestBody RequestJSON reqJson) throws Exception {
		return pushgwRegService.doService(headers, reqJson);
	}
	
	
	/**
	 * 02. 단말 등록 삭제
	 * @param pushVO
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	
	@RequestMapping(value = "/subscriber/deregister", method = RequestMethod.POST)
	@ResponseBody
	public ResponseJSON subscriberDeregister(HttpSession session, @RequestHeader HttpHeaders headers,
			@RequestBody RequestJSON reqJson) throws Exception {
		return pushgwDeleteService.doService(headers, reqJson);
	}

}
