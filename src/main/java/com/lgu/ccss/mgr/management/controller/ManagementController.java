package com.lgu.ccss.mgr.management.controller;

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
import com.lgu.ccss.mgr.management.service.config.modify.ModifyConfigService;
import com.lgu.ccss.mgr.management.service.config.search.SearchConfigService;
import com.lgu.ccss.mgr.management.service.encInfo.SearchEncryptionService;
import com.lgu.ccss.mgr.management.service.notice.NoticeSearchService;


@Controller
@RequestMapping(value = "/mgrapi/management")
public class ManagementController {

	@Resource(name = "searchEncryptionService")
	private SearchEncryptionService searchEncryptionService;
	
	@Resource(name = "searchConfigService")
	private SearchConfigService searchConfigService;
	
	
	@Resource(name = "modifyConfigService")
	private ModifyConfigService modifyConfigService;
	
	@Resource(name = "noticeSearchService")
	private NoticeSearchService noticeSearchService;
	
	@RequestMapping(value = "/search/encryption", method = RequestMethod.POST)
	@ResponseBody
	public ResponseJSON reqSearchEncryption(HttpSession session, @RequestHeader HttpHeaders headers,
			@RequestBody RequestJSON reqJson) throws Exception {
		return searchEncryptionService.doService(headers, reqJson);
	}
	
	@RequestMapping(value = "/search/config", method = RequestMethod.POST)
	@ResponseBody
	public ResponseJSON reqSearchConfig(HttpSession session, @RequestHeader HttpHeaders headers,
			@RequestBody RequestJSON reqJson) throws Exception {
		return searchConfigService.doService(headers, reqJson);
	}
	
	@RequestMapping(value = "/modify/config", method = RequestMethod.POST)
	@ResponseBody
	public ResponseJSON reqModifyConfig(HttpSession session, @RequestHeader HttpHeaders headers,
			@RequestBody RequestJSON reqJson) throws Exception {
		return modifyConfigService.doService(headers, reqJson);
	}
	
	@RequestMapping(value = "/notice/list", method = RequestMethod.POST)
	@ResponseBody
	public ResponseJSON reqScheduleSearch(HttpSession session, @RequestHeader HttpHeaders headers,
			@RequestBody RequestJSON reqJson) throws Exception {
		return noticeSearchService.doService(headers, reqJson);
	}
}
