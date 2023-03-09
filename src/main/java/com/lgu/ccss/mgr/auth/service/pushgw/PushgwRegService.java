package com.lgu.ccss.mgr.auth.service.pushgw;

import org.springframework.http.HttpHeaders;

import com.lgu.ccss.common.model.RequestJSON;
import com.lgu.ccss.common.model.ResponseJSON;

public interface PushgwRegService {
	
	public ResponseJSON doService(HttpHeaders headers, RequestJSON reqJson);
	
	
		
}
