package com.lgu.ccss.api.location.service;

import org.springframework.http.HttpHeaders;

import com.lgu.ccss.common.model.RequestJSON;
import com.lgu.ccss.common.model.ResponseJSON;

public interface LocationService {
	public ResponseJSON doService(HttpHeaders headers, RequestJSON reqJson);
}
