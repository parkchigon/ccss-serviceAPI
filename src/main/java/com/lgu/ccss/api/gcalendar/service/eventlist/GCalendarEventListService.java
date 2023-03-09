package com.lgu.ccss.api.gcalendar.service.eventlist;

import org.springframework.http.HttpHeaders;

import com.lgu.ccss.common.model.RequestJSON;
import com.lgu.ccss.common.model.ResponseJSON;


public interface GCalendarEventListService {
	public ResponseJSON doService(HttpHeaders headers, RequestJSON reqJson);
}
