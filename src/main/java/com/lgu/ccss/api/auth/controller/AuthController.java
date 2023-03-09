package com.lgu.ccss.api.auth.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lgu.ccss.api.auth.service.login.AuthLoginService;
import com.lgu.ccss.api.auth.service.nLogin.AuthNLoginService;
import com.lgu.ccss.api.auth.service.register.AuthRegisterService;
import com.lgu.ccss.api.auth.service.saveGuestAgr.SaveGuestmodeAgrService;
import com.lgu.ccss.api.auth.service.status.AuthStatusService;
import com.lgu.ccss.common.model.RequestJSON;
import com.lgu.ccss.common.model.ResponseJSON;

@Controller
public class AuthController {

	@Resource(name = "authLoginService")
	private AuthLoginService authLoginService;

	@Resource(name = "authRegisterService")
	private AuthRegisterService authRegisterService;

	@Resource(name = "authStatusService")
	private AuthStatusService authStatusService;
	
	@Resource(name = "bmAuthLoginService")
	private AuthLoginService bmAuthLoginService;
	
	@Resource(name = "authNLoginService")
	private AuthNLoginService authNLoginService;
	
	@Resource(name = "bmAuthNLoginService")
	private AuthNLoginService bmAuthNLoginService;

	@Resource(name = "saveGuestmodeAgrService")
	private SaveGuestmodeAgrService saveGuestmodeAgrService;
	
	@RequestMapping(value = "/api/authentication/login", method = RequestMethod.POST)
	@ResponseBody
	public ResponseJSON reqLogin(HttpSession session, @RequestHeader HttpHeaders headers,
			@RequestBody RequestJSON reqJson) throws Exception {
		return authLoginService.doService(headers, reqJson);
	}

	@RequestMapping(value = "/api/authentication/register", method = RequestMethod.POST)
	@ResponseBody
	public ResponseJSON requestRegister(HttpSession session, @RequestHeader HttpHeaders headers,
			@RequestBody RequestJSON reqJson) throws Exception {
		return authRegisterService.doService(headers, reqJson);
	}

	@RequestMapping(value = "/api/authentication/status", method = RequestMethod.POST)
	@ResponseBody
	public ResponseJSON requestServiceStatus(HttpSession session, @RequestHeader HttpHeaders headers,
			@RequestBody RequestJSON reqJson) throws Exception {
		return authStatusService.doService(headers, reqJson);
	}
	
	@RequestMapping(value = "/bmapi/authentication/login", method = RequestMethod.POST)
	@ResponseBody
	public ResponseJSON requestLoginBM(HttpSession session, @RequestHeader HttpHeaders headers,
			@RequestBody RequestJSON reqJson) throws Exception {
		return bmAuthLoginService.doService(headers, reqJson);
	}
	
	/**
	 * N corp. clova Login
	 * */
	@RequestMapping(value = "/api/authentication/nlogin", method = RequestMethod.POST)
	@ResponseBody
	public ResponseJSON reqNguestLogin(HttpSession session, @RequestHeader HttpHeaders headers,
			@RequestBody RequestJSON reqJson) throws Exception {
		return authNLoginService.doService(headers, reqJson);
	}
	
	@RequestMapping(value = "/bmapi/authentication/nlogin", method = RequestMethod.POST)
	@ResponseBody
	public ResponseJSON reqNguestLoginBM(HttpSession session, @RequestHeader HttpHeaders headers,
			@RequestBody RequestJSON reqJson) throws Exception {
		return bmAuthNLoginService.doService(headers, reqJson);
	}
	
	@RequestMapping(value = {"/api/authentication/save/guestmode/agr","/bmapi/authentication/save/guestmode/agr"}, method = RequestMethod.POST)
	@ResponseBody
	public ResponseJSON reqSaveGuestmodeAgr(HttpSession session, @RequestHeader HttpHeaders headers,
			@RequestBody RequestJSON reqJson) throws Exception {
		return saveGuestmodeAgrService.doService(headers, reqJson);
	}
}
