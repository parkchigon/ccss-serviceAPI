package com.lgu.ccss.mgr.schedule.service.delete;

import org.springframework.http.HttpHeaders;

import com.lgu.ccss.common.model.RequestJSON;
import com.lgu.ccss.common.model.ResponseJSON;

public interface ScheduleDelService {
	
	public ResponseJSON doService(HttpHeaders headers, RequestJSON reqJson);

}
