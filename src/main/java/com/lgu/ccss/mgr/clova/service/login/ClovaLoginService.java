package com.lgu.ccss.mgr.clova.service.login;

import org.springframework.http.HttpHeaders;

import com.lgu.ccss.common.model.RequestJSON;
import com.lgu.ccss.common.model.ResponseJSON;

public interface ClovaLoginService {
	
	public ResponseJSON doService(HttpHeaders headers, RequestJSON reqJson);

}
