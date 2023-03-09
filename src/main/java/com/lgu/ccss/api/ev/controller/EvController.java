package com.lgu.ccss.api.ev.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lgu.ccss.api.ev.service.evCharginginfo.EvCharginginfoService;
import com.lgu.ccss.api.ev.service.evChargingmsg.EvChargingmsgService;
import com.lgu.ccss.common.model.RequestJSON;
import com.lgu.ccss.common.model.ResponseJSON;

@Controller
@RequestMapping(value = "/api/ev")
public class EvController {
	
	@Resource(name = "evCharginginfoService")
	private EvCharginginfoService evCharginginfoService;
	
	@Resource(name = "evChargingmsgService")
	private EvChargingmsgService evChargingmsgService;
	
	@RequestMapping(value = "/charginginfo", method = RequestMethod.POST)
	@ResponseBody
	public ResponseJSON charginginfo(HttpSession session, @RequestHeader HttpHeaders headers,
			@RequestBody RequestJSON reqJson) throws Exception {

		return evCharginginfoService.doService(headers, reqJson);
	}
	
	@RequestMapping(value = "/chargingmsg", method = RequestMethod.POST)
	@ResponseBody
	public ResponseJSON chargingmsg(HttpSession session, @RequestHeader HttpHeaders headers,
			@RequestBody RequestJSON reqJson) throws Exception {

		return evChargingmsgService.doService(headers, reqJson);
	}
}
