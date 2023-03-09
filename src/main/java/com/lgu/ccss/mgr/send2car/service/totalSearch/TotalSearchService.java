package com.lgu.ccss.mgr.send2car.service.totalSearch;

import org.springframework.http.HttpHeaders;

import com.lgu.ccss.common.model.RequestJSON;
import com.lgu.ccss.common.model.ResponseJSON;

public interface TotalSearchService {
	
	public ResponseJSON doService(HttpHeaders headers, RequestJSON reqJson);

}
