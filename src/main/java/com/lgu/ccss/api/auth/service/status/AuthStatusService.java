package com.lgu.ccss.api.auth.service.status;

import org.springframework.http.HttpHeaders;

import com.lgu.ccss.common.model.RequestJSON;
import com.lgu.ccss.common.model.ResponseJSON;

public interface AuthStatusService {
	public ResponseJSON doService(HttpHeaders headers, RequestJSON reqJson);
}
