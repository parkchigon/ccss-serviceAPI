package com.lgu.ccss.api.carddeck.service.list;

import org.springframework.http.HttpHeaders;

import com.lgu.ccss.common.model.RequestJSON;
import com.lgu.ccss.common.model.ResponseJSON;

public interface CarddeckListService {
	public ResponseJSON doService(HttpHeaders headers, RequestJSON reqJson);
}
