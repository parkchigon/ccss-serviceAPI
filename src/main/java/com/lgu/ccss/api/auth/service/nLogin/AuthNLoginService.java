package com.lgu.ccss.api.auth.service.nLogin;

import org.springframework.http.HttpHeaders;

import com.lgu.ccss.common.model.RequestJSON;
import com.lgu.ccss.common.model.ResponseJSON;

public interface AuthNLoginService {
	public ResponseJSON doService(HttpHeaders headers, RequestJSON reqJson);
}
