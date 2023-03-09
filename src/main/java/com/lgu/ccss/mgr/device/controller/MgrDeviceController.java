package com.lgu.ccss.mgr.device.controller;

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
import com.lgu.ccss.mgr.device.service.choice.MgrDeviceChoiceService;
import com.lgu.ccss.mgr.device.service.delete.MgrDeviceDeleteService;
import com.lgu.ccss.mgr.device.service.nickname.MgrDeviceNickNameService;
import com.lgu.ccss.mgr.device.service.search.MgrDeviceListService;


@Controller
@RequestMapping(value = "/mgrapi/device")
public class MgrDeviceController {

	@Resource(name = "mgrDeviceListService")
	private MgrDeviceListService mgrDeviceListService;
	
	@Resource(name = "mgrDeviceChoiceService")
	private MgrDeviceChoiceService mgrDeviceChoiceService;
	
	@Resource(name = "mgrDeviceNickNameService")
	private MgrDeviceNickNameService mgrDeviceNickNameService;
	
	@Resource(name = "mgrDeviceDeleteService")
	private MgrDeviceDeleteService mgrDeviceDeleteService;
	
	@RequestMapping(value = "/list", method = RequestMethod.POST)
	@ResponseBody
	public ResponseJSON reqDeviceList(HttpSession session, @RequestHeader HttpHeaders headers,
			@RequestBody RequestJSON reqJson) throws Exception {
		return mgrDeviceListService.doService(headers, reqJson);
	}
	
	@RequestMapping(value = "/choice", method = RequestMethod.POST)
	@ResponseBody
	public ResponseJSON reqMainUseReg(HttpSession session, @RequestHeader HttpHeaders headers,
			@RequestBody RequestJSON reqJson) throws Exception {
		return mgrDeviceChoiceService.doService(headers, reqJson);
	}
	
	@RequestMapping(value = "/nicknameModi", method = RequestMethod.POST)
	@ResponseBody
	public ResponseJSON reqNickNameModi(HttpSession session, @RequestHeader HttpHeaders headers,
			@RequestBody RequestJSON reqJson) throws Exception {
		return mgrDeviceNickNameService.doService(headers, reqJson);
	}
	
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	@ResponseBody
	public ResponseJSON reqDelete(HttpSession session, @RequestHeader HttpHeaders headers,
			@RequestBody RequestJSON reqJson) throws Exception {
		return mgrDeviceDeleteService.doService(headers, reqJson);
	}
	
}
