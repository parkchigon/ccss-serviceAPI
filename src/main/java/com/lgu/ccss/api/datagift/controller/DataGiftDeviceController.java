package com.lgu.ccss.api.datagift.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lgu.ccss.api.datagift.service.DataGiftDeviceService;
import com.lgu.ccss.common.model.RequestJSON;
import com.lgu.ccss.common.model.ResponseJSON;


@Controller
public class DataGiftDeviceController {
	



	@Resource(name = "dataGiftDeviceService")
	private DataGiftDeviceService dataGiftDeviceService;

	
	/**
	 * 데이터 상품권 구매내역 조회.
	 * 
	 * @param session
	 * @param headers
	 * @param reqJson
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/bmapi/datagiftown/list", method = RequestMethod.POST)
	@ResponseBody
	public ResponseJSON getDatagiftOwnList(HttpSession session, @RequestHeader HttpHeaders headers,
			@RequestBody RequestJSON reqJson) throws Exception {

			return this.dataGiftDeviceService.getDatagiftOwnList(headers, reqJson);
	}
	
}
