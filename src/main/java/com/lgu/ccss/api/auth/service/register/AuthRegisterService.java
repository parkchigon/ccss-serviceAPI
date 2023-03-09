package com.lgu.ccss.api.auth.service.register;

import org.springframework.http.HttpHeaders;

import com.lgu.ccss.common.model.RequestJSON;
import com.lgu.ccss.common.model.ResponseJSON;

public interface AuthRegisterService {
	public ResponseJSON doService(HttpHeaders headers, RequestJSON reqJson);
}
