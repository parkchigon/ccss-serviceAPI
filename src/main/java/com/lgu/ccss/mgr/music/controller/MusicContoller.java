package com.lgu.ccss.mgr.music.controller;

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
import com.lgu.ccss.mgr.music.service.loginout.LoginoutService;

@Controller
@RequestMapping(value = "/mgrapi/music")
public class MusicContoller {
	@Resource(name = "loginout")
	private LoginoutService loginout;
	
	
	@RequestMapping(value = "/loginout", method = RequestMethod.POST)
	@ResponseBody
	public ResponseJSON reqLoginout(HttpSession session, @RequestHeader HttpHeaders headers,
			@RequestBody RequestJSON reqJson) throws Exception {
		return loginout.doService(headers, reqJson);
	}
}
