package com.lgu.ccss.api.mgrapp.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lgu.ccss.api.mgrapp.service.userDel.UserDelService;
import com.lgu.ccss.api.mgrapp.service.userList.UserListService;
import com.lgu.ccss.common.model.RequestJSON;
import com.lgu.ccss.common.model.ResponseJSON;


@Controller
@RequestMapping(value = "/api/mgrapp")
public class MgrAppController {
	
	@Resource(name = "userListService") 
	private UserListService userListService;
	
	@Resource(name = "userDelService") 
	private UserDelService userDelService;
	
	@RequestMapping(value="/user/list", method = RequestMethod.POST)
	@ResponseBody
	public ResponseJSON requestUserList(HttpSession session, @RequestHeader HttpHeaders headers,
			@RequestBody RequestJSON reqJson) throws Exception {
		ResponseJSON response = null;
		
		response = userListService.doService(headers, reqJson);

		return response;
	}
	
	@RequestMapping(value="/user/delete", method = RequestMethod.POST)
	@ResponseBody
	public ResponseJSON requestUserDel(HttpSession session, @RequestHeader HttpHeaders headers,
			@RequestBody RequestJSON reqJson) throws Exception {
		ResponseJSON response = null;
		
		response = userDelService.doService(headers, reqJson);

		return response;
	}
}
