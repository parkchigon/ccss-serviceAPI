package com.lgu.ccss.mgr.send2car.controller;

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
import com.lgu.ccss.mgr.send2car.service.geocodingSerch.RevGeocodingSearchService;
import com.lgu.ccss.mgr.send2car.service.history.delete.TargetHistoryDelService;
import com.lgu.ccss.mgr.send2car.service.history.search.TargetHistorySearchService;
import com.lgu.ccss.mgr.send2car.service.routeSearch.RouteSearchService;
import com.lgu.ccss.mgr.send2car.service.send.TargetSendService;
import com.lgu.ccss.mgr.send2car.service.targetSearch.TargetSearchService;
import com.lgu.ccss.mgr.send2car.service.totalSearch.TotalSearchService;

@Controller
@RequestMapping(value = "/mgrapi/send2car")
public class MgrSend2CarController {
	@Resource(name = "targetSearchService")
	private TargetSearchService targetSearchService;
	
	@Resource(name = "routeSearchService")
	private RouteSearchService routeSearchService;
	
	@Resource(name = "totalSearchService")
	private TotalSearchService totalSearchService;
	
	
	@Resource(name = "revGeocodingSearchService")
	private RevGeocodingSearchService revGeocodingSearchService;
	
	@Resource(name = "targetHistorySearchService")
	private TargetHistorySearchService targetHistorySearchService;
	
	@Resource(name = "targetHistoryDelService")
	private TargetHistoryDelService targetHistoryDelService;
	
	@Resource(name = "targetSendService")
	private TargetSendService targetSendService;
	
	@RequestMapping(value = "/target/send", method = RequestMethod.POST)
	@ResponseBody
	public ResponseJSON reqTargetSend(HttpSession session, @RequestHeader HttpHeaders headers,
			@RequestBody RequestJSON reqJson) throws Exception {
		
		return targetSendService.doService(headers, reqJson);
	}
	
	
	@RequestMapping(value = "/target/search", method = RequestMethod.POST)
	@ResponseBody
	public ResponseJSON reqTargetSearchAM(HttpSession session, @RequestHeader HttpHeaders headers,
			@RequestBody RequestJSON reqJson ) throws Exception {
		
		return targetSearchService.doService(headers, reqJson);
		//return totalSearchService.doService(headers, reqJson);
	}
	
	@RequestMapping(value = "/target/routeSearch", method = RequestMethod.POST)
	@ResponseBody
	public ResponseJSON reqRouteSearchAM(HttpSession session, @RequestHeader HttpHeaders headers,
			@RequestBody RequestJSON reqJson) throws Exception {
		
		
		return routeSearchService.doService(headers, reqJson);
	}
	
	//사용하지 않음
	@RequestMapping(value = "/target/totalSearch", method = RequestMethod.POST)
	@ResponseBody
	public ResponseJSON reqTotalSearchAM(HttpSession session, @RequestHeader HttpHeaders headers,
			@RequestBody RequestJSON reqJson) throws Exception {
		
		return totalSearchService.doService(headers, reqJson);
		
	
	}
	
	//#################################################### TEST 좌표로 주소 찾기 ########################################################
	//#############################################주차시 해당 좌표로 주소 검색 시 사용 로직 테스트 ################################################
	@RequestMapping(value = "/target/revgeocodingSearch", method = RequestMethod.POST)
	@ResponseBody
	public ResponseJSON revgeocodingSearch(HttpSession session, @RequestHeader HttpHeaders headers,
			@RequestBody RequestJSON reqJson) throws Exception {
	
			return revGeocodingSearchService.doService(headers, reqJson);

	}
	//#############################################################################################################################
	
	
	@RequestMapping(value = "/target/history/list", method = RequestMethod.POST)
	@ResponseBody
	public ResponseJSON reqTargetHistorySearch(HttpSession session, @RequestHeader HttpHeaders headers,
			@RequestBody RequestJSON reqJson) throws Exception {
		return targetHistorySearchService.doService(headers, reqJson);
	}
	
	@RequestMapping(value = "/target/history/del", method = RequestMethod.POST)
	@ResponseBody
	public ResponseJSON reqTargetHistoryDel(HttpSession session, @RequestHeader HttpHeaders headers,
			@RequestBody RequestJSON reqJson) throws Exception {
		return targetHistoryDelService.doService(headers, reqJson);
	}
	
}
