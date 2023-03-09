package com.lgu.ccss.api.notice.service;

import org.springframework.http.HttpHeaders;

import com.lgu.ccss.common.model.RequestJSON;
import com.lgu.ccss.common.model.ResponseJSON;

public interface NoticeService {
	public ResponseJSON doService(HttpHeaders headers, RequestJSON reqJson);
}
