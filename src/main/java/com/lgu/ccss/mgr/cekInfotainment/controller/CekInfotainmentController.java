package com.lgu.ccss.mgr.cekInfotainment.controller;

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
import com.lgu.ccss.mgr.cekInfotainment.service.discovery.CekInfotainmentDiscoveryService;
import com.lgu.ccss.mgr.cekInfotainment.service.getAuthorizationCode.CekInfotainmentGetAuthorizationCodeService;
import com.lgu.ccss.mgr.cekInfotainment.service.getNidSearch.CekInfotainmentGetNidSearchService;
import com.lgu.ccss.mgr.cekInfotainment.service.logout.CekInfotainmentLogoutService;
import com.lgu.ccss.mgr.cekInfotainment.service.save.CekInfotainmentSaveService;
import com.lgu.ccss.mgr.cekInfotainment.service.search.CekInfotainmentSearchService;
import com.lgu.ccss.mgr.cekInfotainment.service.searchOneId.CekSearchOndIdService;
import com.lgu.ccss.mgr.cekInfotainment.service.tempIdRegist.CekInfotainmentTempIdRegistService;
import com.lgu.ccss.mgrapp.service.MgrappService;


@Controller
@RequestMapping(value = "/mgrapi/cek/infotainment")
public class CekInfotainmentController {

	@Resource(name = "cekSearchOndIdService")
	private  CekSearchOndIdService cekSearchOndIdService;
	
	@Resource(name = "cekInfotainmentSaveService")
	private  CekInfotainmentSaveService cekInfotainmentSaveService;
	
	@Resource(name = "cekInfotainmentSearchService")
	private  CekInfotainmentSearchService cekInfotainmentSearchService;
	
	@Resource(name = "cekInfotainmentGetAuthorizationCodeService")
	private  CekInfotainmentGetAuthorizationCodeService cekInfotainmentGetAuthorizationCodeService;
	
	@Resource(name = "cekInfotainmentGetNidSearchService")
	private  CekInfotainmentGetNidSearchService cekInfotainmentGetNidSearchService;
	
	@Resource(name = "cekInfotainmentTempIdRegistService")
	private  CekInfotainmentTempIdRegistService cekInfotainmentTempIdRegistService;
	
	@Resource(name = "cekInfotainmentLogoutService")
	private  CekInfotainmentLogoutService cekInfotainmentLogoutService;
	
	@Resource(name = "cekInfotainmentDiscoveryService")
	private  CekInfotainmentDiscoveryService cekInfotainmentDiscoveryService;
	
	@Resource(name = "mgrappService")
	private MgrappService mgrappService;
	
	@RequestMapping(value = "/searchOneId", method = RequestMethod.POST)
	@ResponseBody
	public ResponseJSON searchOneId(HttpSession session, @RequestHeader HttpHeaders headers,
			@RequestBody RequestJSON reqJson) throws Exception {
		return cekSearchOndIdService.doService(headers, reqJson);
	}
	
	// CEK 인포테인먼트 인증 정보 저장.
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	@ResponseBody
	public ResponseJSON save(HttpSession session, @RequestHeader HttpHeaders headers,
			@RequestBody RequestJSON reqJson) throws Exception {
		return cekInfotainmentSaveService.doService(headers, reqJson);
	}
	
	// CEK 인포테인먼트 연동 결과 저장
	@RequestMapping(value = "/result/save", method = RequestMethod.POST)
	@ResponseBody
	public ResponseJSON resultSave(HttpSession session, @RequestHeader HttpHeaders headers,
			@RequestBody RequestJSON reqJson) throws Exception {
		return cekInfotainmentSaveService.doService(headers, reqJson);
	}
	

	// CEK 인포테인먼트 연동 결과 저장
	@RequestMapping(value = "/search", method = RequestMethod.POST)
	@ResponseBody
	public ResponseJSON search(HttpSession session, @RequestHeader HttpHeaders headers,
			@RequestBody RequestJSON reqJson) throws Exception {
		return cekInfotainmentSearchService.doService(headers, reqJson);
	}
	
	// CEK Auth Code 요청 
	@RequestMapping(value = "/getAuthorizationCode", method = RequestMethod.POST)
	@ResponseBody
	public ResponseJSON getAuthorizationCode(HttpSession session, @RequestHeader HttpHeaders headers,
			@RequestBody RequestJSON reqJson) throws Exception {
		return cekInfotainmentGetAuthorizationCodeService.doService(headers, reqJson);
	}
	
	// NID를 이용한 회원정보 조회 
	@RequestMapping(value = "/getNidInfo", method = RequestMethod.POST)
	@ResponseBody
	public ResponseJSON getNidSearch(HttpSession session, @RequestHeader HttpHeaders headers,
			@RequestBody RequestJSON reqJson) throws Exception {
		return cekInfotainmentGetNidSearchService.doService(headers, reqJson);
	}
	
	// 임시계정등록(CEK인증정보) 
	@RequestMapping(value = "/tempIdRegist", method = RequestMethod.POST)
	@ResponseBody
	public ResponseJSON tempIdRegist(HttpSession session, @RequestHeader HttpHeaders headers,
			@RequestBody RequestJSON reqJson) throws Exception {
		return cekInfotainmentTempIdRegistService.doService(headers, reqJson);
	}
	
	// CEK LogOut 
	@RequestMapping(value = "/logout", method = RequestMethod.POST)
	@ResponseBody
	public ResponseJSON logout(HttpSession session, @RequestHeader HttpHeaders headers,
			@RequestBody RequestJSON reqJson) throws Exception {
		return cekInfotainmentLogoutService.doService(headers, reqJson);
	}
	
	// DISCOVERY
	@RequestMapping(value = "/discovery", method = RequestMethod.POST)
	@ResponseBody
	public ResponseJSON discovery(HttpSession session, @RequestHeader HttpHeaders headers,
			@RequestBody RequestJSON reqJson) throws Exception {
		return cekInfotainmentDiscoveryService.doService(headers, reqJson);
	}
}
