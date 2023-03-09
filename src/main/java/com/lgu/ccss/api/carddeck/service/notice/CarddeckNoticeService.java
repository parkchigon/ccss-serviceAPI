package com.lgu.ccss.api.carddeck.service.notice;

import org.springframework.http.HttpHeaders;

import com.lgu.ccss.common.model.RequestJSON;
import com.lgu.ccss.common.model.ResponseJSON;

public interface CarddeckNoticeService {
	public ResponseJSON doService(HttpHeaders headers, RequestJSON reqJson);
}
