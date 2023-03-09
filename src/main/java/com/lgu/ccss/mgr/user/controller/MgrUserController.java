package com.lgu.ccss.mgr.user.controller;

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
import com.lgu.ccss.mgr.user.service.delete.MgrUserDeleteService;
import com.lgu.ccss.mgr.user.service.search.MgrUserListService;


@Controller
@RequestMapping(value = "/mgrapi/user")
public class MgrUserController {
	@Resource(name = "mgrUserListService")
	private MgrUserListService mgrUserListService;
	
	@Resource(name = "mgrUserDeleteService")
	private MgrUserDeleteService mgrUserDeleteService;
	
	@RequestMapping(value = "/list", method = RequestMethod.POST)
	@ResponseBody
	public ResponseJSON reqMgrappUserList(HttpSession session, @RequestHeader HttpHeaders headers,
			@RequestBody RequestJSON reqJson) throws Exception {
		return mgrUserListService.doService(headers, reqJson);
	}
	
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	@ResponseBody
	public ResponseJSON reqMgrappUserDelte(HttpSession session, @RequestHeader HttpHeaders headers,
			@RequestBody RequestJSON reqJson) throws Exception {
		return mgrUserDeleteService.doService(headers, reqJson);
	}
}
