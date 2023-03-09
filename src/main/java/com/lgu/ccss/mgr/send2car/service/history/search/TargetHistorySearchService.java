package com.lgu.ccss.mgr.send2car.service.history.search;

import org.springframework.http.HttpHeaders;

import com.lgu.ccss.common.model.RequestJSON;
import com.lgu.ccss.common.model.ResponseJSON;

public interface TargetHistorySearchService {
	
	public ResponseJSON doService(HttpHeaders headers, RequestJSON reqJson);

}
