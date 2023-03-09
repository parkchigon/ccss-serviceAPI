package com.lgu.ccss.mgr.mypage.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lgu.ccss.common.model.RequestJSON;
import com.lgu.ccss.common.model.ResponseJSON;
import com.lgu.ccss.mgr.mypage.service.datausage.DataUsageService;


@Controller
@RequestMapping(value = "/mgrapi/mypage")
public class MypageController {
	
	@Autowired
	private DataUsageService dataUsageService;
	
	@RequestMapping(value = "/data/usage", method = RequestMethod.POST)
	@ResponseBody
	public ResponseJSON reqSearchEncryption(HttpSession session, @RequestHeader HttpHeaders headers,
			@RequestBody RequestJSON reqJson) throws Exception {
		return dataUsageService.doService(headers, reqJson);
	}
	
}
