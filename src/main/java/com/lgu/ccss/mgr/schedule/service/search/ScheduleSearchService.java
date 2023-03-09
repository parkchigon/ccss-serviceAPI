package com.lgu.ccss.mgr.schedule.service.search;

import org.springframework.http.HttpHeaders;

import com.lgu.ccss.common.model.RequestJSON;
import com.lgu.ccss.common.model.ResponseJSON;

public interface ScheduleSearchService {
	
	public ResponseJSON doService(HttpHeaders headers, RequestJSON reqJson);

}
