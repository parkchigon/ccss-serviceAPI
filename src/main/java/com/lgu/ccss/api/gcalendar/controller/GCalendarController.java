package com.lgu.ccss.api.gcalendar.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lgu.ccss.api.gcalendar.service.eventlist.GCalendarEventListService;
import com.lgu.ccss.common.model.RequestJSON;
import com.lgu.ccss.common.model.ResponseJSON;

@Controller
@RequestMapping(value = {"/api/gcalendar"})
public class GCalendarController {


	@Resource(name = "gCalendarEventListService")
	private GCalendarEventListService gCalendarEventListService;
	
	/*@Resource(name = "gCalendarMgrappListService")
	private GCalendarMgrappListService gCalendarMgrappListService;
*/
	@RequestMapping(value = { "/event/list" }, method = RequestMethod.POST)
	@ResponseBody
	public ResponseJSON searchGoogleCalendarEventList(HttpSession session, @RequestHeader HttpHeaders headers,
			@RequestBody RequestJSON reqJson) throws Exception {
		return gCalendarEventListService.doService(headers, reqJson);
	}
	
	/*
	 * Chanage Request, 2018-02-12 JYLEE
	 */
	/*@RequestMapping(value = { "/mgrapp/list" }, method = RequestMethod.POST)
	@ResponseBody
	public ResponseJSON searchGoogleCalendarMgrappList(HttpSession session, @RequestHeader HttpHeaders headers,
			@RequestBody RequestJSON reqJson) throws Exception {
		return gCalendarMgrappListService.doService(headers, reqJson);
	}*/
}
