package com.lgu.ccss.api.conf.service;

import org.springframework.http.HttpHeaders;

import com.lgu.ccss.common.model.RequestJSON;
import com.lgu.ccss.common.model.ResponseJSON;

public interface ConfSystemService {
	public ResponseJSON doService(HttpHeaders headers, RequestJSON reqJson);
}
