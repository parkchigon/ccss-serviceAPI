package com.lgu.ccss.mgr.parking.controller;

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
import com.lgu.ccss.mgr.parking.service.location.ParkingLocationService;


@Controller
@RequestMapping(value = "/mgrapi/parking")
public class ParkingController {
	@Resource(name = "parkingLocationService")
	private ParkingLocationService parkingLocationService;
	
	@RequestMapping(value = "/location", method = RequestMethod.POST)
	@ResponseBody
	public ResponseJSON reqParkingLocation(HttpSession session, @RequestHeader HttpHeaders headers,
			@RequestBody RequestJSON reqJson) throws Exception {
		return parkingLocationService.doService(headers, reqJson);
	}
	
	
}
