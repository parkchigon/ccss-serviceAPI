package com.lgu.ccss.mgr.clova.controller;



import javax.annotation.Resource;
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
import com.lgu.ccss.mgr.clova.service.login.ClovaLoginService;
import com.lgu.ccss.mgr.clova.service.logout.ClovaLogoutService;

@Controller
@RequestMapping(value = "/mgrapi/clova")
public class ClovaController {

	@Resource(name = "clovaLoginService")
	private  ClovaLoginService clovaLoginService;
	
	@Resource(name = "clovaLogoutService")
	private  ClovaLogoutService clovaLogoutService;

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	@ResponseBody
	public ResponseJSON clovaLogin(HttpSession session, @RequestHeader HttpHeaders headers,
			@RequestBody RequestJSON reqJson) throws Exception {
		return clovaLoginService.doService(headers, reqJson);
	}
	
	@RequestMapping(value = "/logout", method = RequestMethod.POST)
	@ResponseBody
	public ResponseJSON clovaLogout(HttpSession session, @RequestHeader HttpHeaders headers,
			@RequestBody RequestJSON reqJson) throws Exception {
		return clovaLogoutService.doService(headers, reqJson);
	}
	

}
