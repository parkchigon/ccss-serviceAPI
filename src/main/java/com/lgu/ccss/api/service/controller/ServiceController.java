package com.lgu.ccss.api.service.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lgu.ccss.api.service.service.login.LoginAIService;
import com.lgu.ccss.api.service.service.login.LoginPushService;
import com.lgu.ccss.api.service.service.logout.LogoutInfotainmentService;
import com.lgu.ccss.api.service.service.logout.LogoutCekInfotainmentService;
import com.lgu.ccss.api.service.service.save.SaveCekInfotainmentService;
import com.lgu.ccss.api.service.service.save.SaveInfotainmentService;
import com.lgu.ccss.api.service.service.search.SearchCekInfotainmentService;
import com.lgu.ccss.api.service.service.search.SearchInfotainmentService;
import com.lgu.ccss.common.model.RequestJSON;
import com.lgu.ccss.common.model.ResponseJSON;

@Controller
@RequestMapping(value = {"/api/service", "/bmapi/service"})
public class ServiceController {


	@Resource(name = "loginAIService")
	private LoginAIService loginAIService;
	
	@Resource(name = "loginPushService")
	private LoginPushService loginPushService;
	
	@Resource(name = "saveInfotainmentService")
	private SaveInfotainmentService saveInfotainmentService;
	
	@Resource(name = "searchInfotainmentService")
	private SearchInfotainmentService searchInfotainmentService;
	
	@Resource(name = "logoutInfotainmentService")
	private LogoutInfotainmentService logoutInfotainmentService;
	
	@Resource(name = "saveCekInfotainmentService")
	private SaveCekInfotainmentService saveCekInfotainmentService;
	
	@Resource(name = "searchCekInfotainmentService")
	private SearchCekInfotainmentService searchCekInfotainmentService;

	@Resource(name = "logoutCekInfotainmentService")
	private LogoutCekInfotainmentService logoutCekInfotainmentService;
	
	@RequestMapping(value = "/login/ai", method = RequestMethod.POST)
	@ResponseBody
	public ResponseJSON requestAiLogin(HttpSession session, @RequestHeader HttpHeaders headers,
			@RequestBody RequestJSON reqJson) throws Exception {
		ResponseJSON response = null;

		response = loginAIService.doService(headers, reqJson);

		return response;
	}
	
	@RequestMapping(value = "/login/push", method = RequestMethod.POST)
	@ResponseBody
	public ResponseJSON requestPushLogin(HttpSession session, @RequestHeader HttpHeaders headers,
			@RequestBody RequestJSON reqJson) throws Exception {
		ResponseJSON response = null;

		response = loginPushService.doService(headers, reqJson);

		return response;
	}
	
	@RequestMapping(value = "/save/infotainment", method = RequestMethod.POST)
	@ResponseBody
	public ResponseJSON requestSaveInfotainment(HttpSession session, @RequestHeader HttpHeaders headers,
			@RequestBody RequestJSON reqJson) throws Exception {
		ResponseJSON response = null;

		response = saveInfotainmentService.doService(headers, reqJson);

		return response;
	}
	
	@RequestMapping(value = "/search/infotainment", method = RequestMethod.POST)
	@ResponseBody
	public ResponseJSON requestSearchInfotainment(HttpSession session, @RequestHeader HttpHeaders headers,
			@RequestBody RequestJSON reqJson) throws Exception {
		ResponseJSON response = null;

		response = searchInfotainmentService.doService(headers, reqJson);

		return response;
	}
	
	@RequestMapping(value = "/logout/infotainment", method = RequestMethod.POST)
	@ResponseBody
	public ResponseJSON requestLogoutInfotainment(HttpSession session, @RequestHeader HttpHeaders headers,
			@RequestBody RequestJSON reqJson) throws Exception {
		ResponseJSON response = null;

		response = logoutInfotainmentService.doService(headers, reqJson);

		return response;
	}
	
	@RequestMapping(value = "/cek/save/infotainment", method = RequestMethod.POST)
	@ResponseBody
	public ResponseJSON requestCekSaveInfotainment(HttpSession session, @RequestHeader HttpHeaders headers,
			@RequestBody RequestJSON reqJson) throws Exception {
		ResponseJSON response = null;

		response = saveCekInfotainmentService.doService(headers, reqJson);

		return response;
	}
	
	@RequestMapping(value = "/cek/search/infotainment", method = RequestMethod.POST)
	@ResponseBody
	public ResponseJSON requestCekSearchInfotainment(HttpSession session, @RequestHeader HttpHeaders headers,
			@RequestBody RequestJSON reqJson) throws Exception {
		ResponseJSON response = null;

		response = searchCekInfotainmentService.doService(headers, reqJson);

		return response;
	}
	
	@RequestMapping(value = "/cek/logout/infotainment", method = RequestMethod.POST)
	@ResponseBody
	public ResponseJSON requestCekLogoutInfotainment(HttpSession session, @RequestHeader HttpHeaders headers,
			@RequestBody RequestJSON reqJson) throws Exception {
		ResponseJSON response = null;

		response = logoutCekInfotainmentService.doService(headers, reqJson);

		return response;
	}
}
