package com.lgu.ccss.api.carddeck.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lgu.ccss.api.carddeck.service.event.CarddeckEventService;
import com.lgu.ccss.api.carddeck.service.list.CarddeckListService;
import com.lgu.ccss.api.carddeck.service.notice.CarddeckNoticeService;
import com.lgu.ccss.common.model.RequestJSON;
import com.lgu.ccss.common.model.ResponseJSON;

@Controller
@RequestMapping(value = {"/api/carddeck", "/bmapi/carddeck"})
public class CarddeckController {

	@Resource(name = "carddeckNoticeService") 
	private CarddeckNoticeService carddeckNoticeService;
	
	@Resource(name = "carddeckListService") 
	private CarddeckListService carddeckListService;
	
	@Resource(name = "carddeckEventService") 
	private CarddeckEventService carddeckEventService;
	
	@RequestMapping(value="/notice", method = RequestMethod.POST)
	@ResponseBody
	public ResponseJSON requestCarddeckNotice(HttpSession session, @RequestHeader HttpHeaders headers,
			@RequestBody RequestJSON reqJson) throws Exception {
		ResponseJSON response = null;
		
		response = carddeckNoticeService.doService(headers, reqJson);

		return response;
	}
	
	@RequestMapping(value="/list", method = RequestMethod.POST)
	@ResponseBody
	public ResponseJSON requestCarddeckList(HttpSession session, @RequestHeader HttpHeaders headers,
			@RequestBody RequestJSON reqJson) throws Exception {
		ResponseJSON response = null;
		
		response = carddeckListService.doService(headers, reqJson);

		return response;
	}
	
	@RequestMapping(value="/event", method = RequestMethod.POST)
	@ResponseBody
	public ResponseJSON requestCarddeckEvent(HttpSession session, @RequestHeader HttpHeaders headers,
			@RequestBody RequestJSON reqJson) throws Exception {
		ResponseJSON response = null;
		
		response = carddeckEventService.doService(headers, reqJson);

		return response;
	}
}
