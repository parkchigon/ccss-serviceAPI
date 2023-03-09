package com.lgu.ccss.mgr.gcalendar.service.callback;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpHeaders;

import com.lgu.ccss.common.model.ResponseJSON;

public interface GoogleCalendarCallbackService {
	
	public ResponseJSON doService(HttpServletRequest request, HttpHeaders headers);

}
