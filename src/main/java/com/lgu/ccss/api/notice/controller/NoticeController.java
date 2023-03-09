package com.lgu.ccss.api.notice.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lgu.ccss.api.notice.service.NoticeService;
import com.lgu.ccss.common.model.RequestJSON;
import com.lgu.ccss.common.model.ResponseJSON;

@Controller
@RequestMapping(value = {"/api/notice", "/bmapi/notice"})
public class NoticeController {
	
	@Resource(name = "noticeService") 
	private NoticeService noticeService;
	
	@RequestMapping(value="/list", method = RequestMethod.POST)
	@ResponseBody
	public ResponseJSON requestNoticeList(HttpSession session, @RequestHeader HttpHeaders headers,
			@RequestBody RequestJSON reqJson) throws Exception {
		ResponseJSON response = null;
		
		response = noticeService.doService(headers, reqJson);

		return response;
	}
}
