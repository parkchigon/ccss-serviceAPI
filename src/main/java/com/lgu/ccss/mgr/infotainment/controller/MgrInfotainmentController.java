package com.lgu.ccss.mgr.infotainment.controller;

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
import com.lgu.ccss.mgr.infotainment.service.callback.MgrInfotainmentLoginCallBackServiceImpl;
import com.lgu.ccss.mgr.infotainment.service.logout.MgrInfotainmentLogoutService;
import com.lgu.ccss.mgr.infotainment.service.save.MgrInfotainmentSaveService;
import com.lgu.ccss.mgr.infotainment.service.search.MgrInfotainmentSearchService;


@Controller
@RequestMapping(value = "/mgrapi/infotainment")
public class MgrInfotainmentController {
	@Resource(name = "mgrInfotainmentSearchService")
	private MgrInfotainmentSearchService mgrInfotainmentSearchService;
	
	@Resource(name = "mgrInfotainmentSaveService")
	private MgrInfotainmentSaveService mgrInfotainmentSaveService;
	
	@Resource(name = "mgrInfotainmentLogoutService")
	private MgrInfotainmentLogoutService mgrInfotainmentLogoutService;
	
	@Resource(name = "mgrInfotainmentLoginCallBackService")
	private MgrInfotainmentLoginCallBackServiceImpl mgrInfotainmentLoginCallBackService;
	
	
	@RequestMapping(value = "/search", method = RequestMethod.POST)
	@ResponseBody
	public ResponseJSON reqInfotainmentSearch(HttpSession session, @RequestHeader HttpHeaders headers,
			@RequestBody RequestJSON reqJson) throws Exception {
		return mgrInfotainmentSearchService.doService(headers, reqJson);
	}
	
	
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	@ResponseBody
	public ResponseJSON reqInfotainmentSave(HttpSession session, @RequestHeader HttpHeaders headers,
			@RequestBody RequestJSON reqJson) throws Exception {
		return mgrInfotainmentSaveService.doService(headers, reqJson);
	}
	
	
	@RequestMapping(value = "/logout", method = RequestMethod.POST)
	@ResponseBody
	public ResponseJSON reqInfotainmentLogout(HttpSession session, @RequestHeader HttpHeaders headers,
			@RequestBody RequestJSON reqJson) throws Exception {
		return mgrInfotainmentLogoutService.doService(headers, reqJson);
	}
	
	@RequestMapping(value = "/loginCallBack", method = RequestMethod.POST)
	@ResponseBody
	public ResponseJSON reqLoginCallBack(HttpSession session, @RequestHeader HttpHeaders headers,
			@RequestBody RequestJSON reqJson) throws Exception {
		return mgrInfotainmentLoginCallBackService.doService(headers, reqJson);
	}
	
}
