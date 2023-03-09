package com.lgu.ccss.mgr.auth.service.register;

import org.springframework.http.HttpHeaders;

import com.lgu.ccss.common.model.RequestJSON;
import com.lgu.ccss.common.model.ResponseJSON;

public interface MgrAuthRegService {
	
	public ResponseJSON doService(HttpHeaders headers, RequestJSON reqJson);

}
