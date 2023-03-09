package com.lgu.ccss.mgr.schedule.service.modify;

import org.springframework.http.HttpHeaders;

import com.lgu.ccss.common.model.RequestJSON;
import com.lgu.ccss.common.model.ResponseJSON;

public interface ScheduleModiService {
	
	public ResponseJSON doService(HttpHeaders headers, RequestJSON reqJson);

}
