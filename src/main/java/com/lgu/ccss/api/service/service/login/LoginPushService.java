package com.lgu.ccss.api.service.service.login;

import org.springframework.http.HttpHeaders;

import com.lgu.ccss.common.model.RequestJSON;
import com.lgu.ccss.common.model.ResponseJSON;

public interface LoginPushService {
	public ResponseJSON doService(HttpHeaders headers, RequestJSON reqJson);
}
