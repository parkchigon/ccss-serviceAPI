package com.lgu.ccss.api.send2car.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lgu.ccss.api.send2car.service.targetGet.TargetGetService;
import com.lgu.ccss.api.send2car.service.targetList.TargetListService;
import com.lgu.ccss.api.send2car.service.targetSet.TargetSetService;
import com.lgu.ccss.common.model.RequestJSON;
import com.lgu.ccss.common.model.ResponseJSON;

@Controller
@RequestMapping(value = {"/api/send2car", "/bmapi/send2car"})
public class Send2CarController {
	

	@Resource(name = "targetListService") 
	private TargetListService targetListService;
	
	@Resource(name = "targetSetService") 
	private TargetSetService targetSetService;
	
	@Resource(name = "targetGetService") 
	private TargetGetService targetGetService;
	
	@RequestMapping(value="/target/list", method = RequestMethod.POST)
	@ResponseBody
	public ResponseJSON requestTargetList(HttpSession session, @RequestHeader HttpHeaders headers,
			@RequestBody RequestJSON reqJson) throws Exception {
		ResponseJSON response = null;
		
		response = targetListService.doService(headers, reqJson);

		return response;
	}
	
	@RequestMapping(value="/target/set", method = RequestMethod.POST)
	@ResponseBody
	public ResponseJSON requestTargetSet(HttpSession session, @RequestHeader HttpHeaders headers,
			@RequestBody RequestJSON reqJson) throws Exception {
		ResponseJSON response = null;
		
		response = targetSetService.doService(headers, reqJson);

		return response;
	}
	
	@RequestMapping(value="/target/get", method = RequestMethod.POST)
	@ResponseBody
	public ResponseJSON requestTargetGet(HttpSession session, @RequestHeader HttpHeaders headers,
			@RequestBody RequestJSON reqJson) throws Exception {
		ResponseJSON response = null;
		
		response = targetGetService.doService(headers, reqJson);

		return response;
	}
}
