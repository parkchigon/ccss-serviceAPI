package com.lgu.ccss.api.auth.service.login;

import org.springframework.http.HttpHeaders;

import com.lgu.ccss.common.model.RequestJSON;
import com.lgu.ccss.common.model.ResponseJSON;

public interface AuthLoginService {
	public ResponseJSON doService(HttpHeaders headers, RequestJSON reqJson);
}
