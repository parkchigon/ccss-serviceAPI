package com.lgu.ccss.mgr.gcalendar.service.login;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpHeaders;

import com.lgu.ccss.common.model.ResponseJSON;

public interface GoogleCalendarLoginService {
	
	public ResponseJSON doService(HttpServletRequest request, HttpHeaders headers);

}
