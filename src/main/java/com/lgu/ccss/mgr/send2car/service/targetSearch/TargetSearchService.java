package com.lgu.ccss.mgr.send2car.service.targetSearch;



import org.springframework.http.HttpHeaders;

import com.lgu.ccss.common.model.RequestJSON;
import com.lgu.ccss.common.model.ResponseJSON;

public interface TargetSearchService {
	
	public ResponseJSON doService(HttpHeaders headers, RequestJSON reqJson);

}
