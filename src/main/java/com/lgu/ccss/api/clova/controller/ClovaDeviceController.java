package com.lgu.ccss.api.clova.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lgu.ccss.api.clova.service.deviceLogin.ClovaDeviceLoginService;
import com.lgu.ccss.common.model.RequestJSON;
import com.lgu.ccss.common.model.ResponseJSON;

@Controller
public class ClovaDeviceController {

	@Resource(name = "clovaDeviceLoginService")
	private ClovaDeviceLoginService clovaDeviceLoginService;
	
	//@Resource(name = "clovaDeviceLoginServiceBM")
	//private ClovaDeviceLoginService clovaDeviceLoginServiceBM;

	@RequestMapping(value = "/api/clova/login", method = RequestMethod.POST)
	@ResponseBody
	public ResponseJSON reqClovaLogin(HttpSession session, @RequestHeader HttpHeaders headers,
			@RequestBody RequestJSON reqJson) throws Exception {
		return clovaDeviceLoginService.doService(headers, reqJson);
	}

	
	@RequestMapping(value = "/bmapi/clova/login", method = RequestMethod.POST)
	@ResponseBody
	public ResponseJSON reqClovaLoginBM(HttpSession session, @RequestHeader HttpHeaders headers,
			@RequestBody RequestJSON reqJson) throws Exception {
		return clovaDeviceLoginService.doService(headers, reqJson);
	}
	
}
