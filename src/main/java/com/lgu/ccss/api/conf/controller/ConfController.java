package com.lgu.ccss.api.conf.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lgu.ccss.api.conf.service.ConfEncryptService;
import com.lgu.ccss.api.conf.service.ConfServiceService;
import com.lgu.ccss.api.conf.service.ConfSystemService;
import com.lgu.ccss.common.model.RequestJSON;
import com.lgu.ccss.common.model.ResponseJSON;

@Controller
@RequestMapping(value = {"/api/configuration", "/bmapi/configuration"})
public class ConfController {
	
	@Resource(name = "confEncryptService")
	private ConfEncryptService confEncryptService;

	@Resource(name = "confSystemService")
	private ConfSystemService confSystemService;
	
	@Resource(name = "confServiceService")
	private ConfServiceService confServiceService;
	
	@RequestMapping(value = "/system", method = RequestMethod.POST)
	@ResponseBody
	public ResponseJSON requestSystemConf(HttpSession session, @RequestHeader HttpHeaders headers,
			@RequestBody RequestJSON reqJson) throws Exception {
		ResponseJSON response = null;

		response = confSystemService.doService(headers, reqJson);

		return response;
	}
	
	@RequestMapping(value = "/service", method = RequestMethod.POST)
	@ResponseBody
	public ResponseJSON requestServiceConf(HttpSession session, @RequestHeader HttpHeaders headers,
			@RequestBody RequestJSON reqJson) throws Exception {
		ResponseJSON response = null;

		response = confServiceService.doService(headers, reqJson);

		return response;
	}
	
	@RequestMapping(value = "/encryption", method = RequestMethod.POST)
	@ResponseBody
	public ResponseJSON requestEncryptionConf(HttpSession session, @RequestHeader HttpHeaders headers,
			@RequestBody RequestJSON reqJson) throws Exception {
		ResponseJSON response = null;

		response = confEncryptService.doService(headers, reqJson);

		return response;
	}
}
