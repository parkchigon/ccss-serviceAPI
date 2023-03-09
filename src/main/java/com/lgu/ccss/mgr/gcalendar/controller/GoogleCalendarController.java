package com.lgu.ccss.mgr.gcalendar.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lgu.ccss.common.model.ResponseJSON;
import com.lgu.ccss.common.util.ResultCodeUtil;
import com.lgu.ccss.mgr.gcalendar.service.callback.GoogleCalendarCallbackService;
import com.lgu.ccss.mgr.gcalendar.service.login.GoogleCalendarLoginService;
import com.lgu.common.util.JsonUtil;


@Controller
@RequestMapping(value = "/mgrapi/gcalendar")
public class GoogleCalendarController {
	private static final Logger logger = LoggerFactory.getLogger(GoogleCalendarController.class);

	@Resource(name = "googleCalendarLoginService")
	private GoogleCalendarLoginService googleCalendarLoginService;
	
	@Resource(name = "googleCalendarCallbackService")
	private GoogleCalendarCallbackService googleCalendarCallbackService;
	
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login(HttpServletRequest request, HttpSession session, @RequestHeader HttpHeaders headers) throws Exception {
		
		ResponseJSON responseJSON = googleCalendarLoginService.doService(request, headers);
		if( responseJSON.getResultCode() != null  && responseJSON.getResultCode().equals(ResultCodeUtil.RC_2S000000.getCode()))
		{
			logger.debug("redirect url:" + responseJSON.getResultData());
			return "redirect:" + responseJSON.getResultData();
		} else
		{
			logger.error("Error : " + responseJSON);
			return JsonUtil.marshallingJson(responseJSON);
		}
	}
	
	
	@RequestMapping(value = "/login/callback", method = RequestMethod.GET)
	@ResponseBody
	public ResponseJSON callback(HttpServletRequest request, HttpSession session, @RequestHeader HttpHeaders headers) throws Exception {
		return googleCalendarCallbackService.doService(request, headers);
	}
	
}
