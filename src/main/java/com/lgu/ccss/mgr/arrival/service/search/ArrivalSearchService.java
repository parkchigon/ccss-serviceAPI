package com.lgu.ccss.mgr.arrival.service.search;

import org.springframework.http.HttpHeaders;

import com.lgu.ccss.common.model.RequestJSON;
import com.lgu.ccss.common.model.ResponseJSON;

public interface ArrivalSearchService {
	
	public ResponseJSON doService(HttpHeaders headers, RequestJSON reqJson);

}
