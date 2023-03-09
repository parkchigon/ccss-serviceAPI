package com.lgu.ccss.mgr.management.service.config.search;



import org.springframework.http.HttpHeaders;

import com.lgu.ccss.common.model.RequestJSON;
import com.lgu.ccss.common.model.ResponseJSON;

public interface SearchConfigService {
	
	public ResponseJSON doService(HttpHeaders headers, RequestJSON reqJson);

}
