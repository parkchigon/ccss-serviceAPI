package com.lgu.ccss.api.service.service.search;

import org.springframework.http.HttpHeaders;

import com.lgu.ccss.common.model.RequestJSON;
import com.lgu.ccss.common.model.ResponseJSON;

public interface SearchInfotainmentService {
	public ResponseJSON doService(HttpHeaders headers, RequestJSON reqJson);
}
