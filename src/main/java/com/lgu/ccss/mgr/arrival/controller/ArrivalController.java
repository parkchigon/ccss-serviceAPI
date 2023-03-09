package com.lgu.ccss.mgr.arrival.controller;

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
import com.lgu.ccss.mgr.arrival.service.delete.ArrivalDelService;
import com.lgu.ccss.mgr.arrival.service.modify.ArrivalModiService;
import com.lgu.ccss.mgr.arrival.service.rcpt.delete.ArrivalRcptDelService;
import com.lgu.ccss.mgr.arrival.service.rcpt.register.ArrivalRcptRegService;
import com.lgu.ccss.mgr.arrival.service.register.ArrivalRegService;
import com.lgu.ccss.mgr.arrival.service.search.ArrivalSearchService;


@Controller
@RequestMapping(value = "/mgrapi/arrivalnotice")
public class ArrivalController {

	@Resource(name = "arrivalRegService")
	private  ArrivalRegService arrivalRegService;
	
	@Resource(name = "arrivalModiService")
	private  ArrivalModiService arrivalModiService;
	
	@Resource(name = "arrivalDelService")
	private  ArrivalDelService arrivalDelService;
	
	@Resource(name = "arrivalRcptRegService")
	private  ArrivalRcptRegService arrivalRcptRegService;
	
	@Resource(name = "arrivalRcptDelService")
	private  ArrivalRcptDelService arrivalRcptDelService;
	
	@Resource(name = "arrivalSearchService")
	private  ArrivalSearchService arrivalSearchService;
	
	
	@RequestMapping(value = "/list", method = RequestMethod.POST)
	@ResponseBody
	public ResponseJSON reqArrivalAlarmList(HttpSession session, @RequestHeader HttpHeaders headers,
			@RequestBody RequestJSON reqJson) throws Exception {
		return arrivalSearchService.doService(headers, reqJson);
	}
	
	
	@RequestMapping(value = "/register", method = RequestMethod.POST)
	@ResponseBody
	public ResponseJSON reqArrivalAlarmReg(HttpSession session, @RequestHeader HttpHeaders headers,
			@RequestBody RequestJSON reqJson) throws Exception {
		return arrivalRegService.doService(headers, reqJson);
	}
	
	
	@RequestMapping(value = "/modify", method = RequestMethod.POST)
	@ResponseBody
	public ResponseJSON reqArrivalAlarmModi(HttpSession session, @RequestHeader HttpHeaders headers,
			@RequestBody RequestJSON reqJson) throws Exception {
		return arrivalModiService.doService(headers, reqJson);
	}
	

	@RequestMapping(value = "/del", method = RequestMethod.POST)
	@ResponseBody
	public ResponseJSON reqArrivalAlarmDelete(HttpSession session, @RequestHeader HttpHeaders headers,
			@RequestBody RequestJSON reqJson) throws Exception {
		return arrivalDelService.doService(headers, reqJson);
	}
	
	
	
	@RequestMapping(value = "/receipient/reg", method = RequestMethod.POST)
	@ResponseBody
	public ResponseJSON reqArrivalAlarmRctpReg(HttpSession session, @RequestHeader HttpHeaders headers,
			@RequestBody RequestJSON reqJson) throws Exception {
		return arrivalRcptRegService.doService(headers, reqJson);
	}
	
	@RequestMapping(value = "/receipient/del", method = RequestMethod.POST)
	@ResponseBody
	public ResponseJSON reqArrivalAlarmRctpDel(HttpSession session, @RequestHeader HttpHeaders headers,
			@RequestBody RequestJSON reqJson) throws Exception {
		return arrivalRcptDelService.doService(headers, reqJson);
	}

}
