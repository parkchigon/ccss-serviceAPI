package com.lgu.ccss.mgr.schedule.controller;

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
import com.lgu.ccss.mgr.schedule.service.delete.ScheduleDelService;
import com.lgu.ccss.mgr.schedule.service.modify.ScheduleModiService;
import com.lgu.ccss.mgr.schedule.service.register.ScheduleRegService;
import com.lgu.ccss.mgr.schedule.service.search.ScheduleSearchService;

@Controller
@RequestMapping(value = "/mgrapi/schedule")
public class ScheduleController {

	@Resource(name = "scheduleRegService")
	private ScheduleRegService scheduleRegService;
	
	@Resource(name = "scheduleModiService")
	private ScheduleModiService scheduleModiService;
	
	@Resource(name = "scheduleDelService")
	private ScheduleDelService scheduleDelService;
	
	@Resource(name = "scheduleSearchService")
	private ScheduleSearchService scheduleSearchService;
	
	
	@RequestMapping(value = "/searchList", method = RequestMethod.POST)
	@ResponseBody
	public ResponseJSON reqScheduleSearch(HttpSession session, @RequestHeader HttpHeaders headers,
			@RequestBody RequestJSON reqJson) throws Exception {
		return scheduleSearchService.doService(headers, reqJson);
	}
	
	@RequestMapping(value = "/reg", method = RequestMethod.POST)
	@ResponseBody
	public ResponseJSON reqScheduleReg(HttpSession session, @RequestHeader HttpHeaders headers,
			@RequestBody RequestJSON reqJson) throws Exception {
		return scheduleRegService.doService(headers, reqJson);
	}
	
	@RequestMapping(value = "/modify", method = RequestMethod.POST)
	@ResponseBody
	public ResponseJSON reqScheduleModi(HttpSession session, @RequestHeader HttpHeaders headers,
			@RequestBody RequestJSON reqJson) throws Exception {
		return scheduleModiService.doService(headers, reqJson);
	}
	
	
	@RequestMapping(value = "/del", method = RequestMethod.POST)
	@ResponseBody
	public ResponseJSON reqScheduleDel(HttpSession session, @RequestHeader HttpHeaders headers,
			@RequestBody RequestJSON reqJson) throws Exception {
		return scheduleDelService.doService(headers, reqJson);
	}
	
}
