package com.lgu.ccss.api.service.service.logout;

import org.springframework.http.HttpHeaders;

import com.lgu.ccss.common.model.RequestJSON;
import com.lgu.ccss.common.model.ResponseJSON;

public interface LogoutCekInfotainmentService {
	public ResponseJSON doService(HttpHeaders headers, RequestJSON reqJson);
}
