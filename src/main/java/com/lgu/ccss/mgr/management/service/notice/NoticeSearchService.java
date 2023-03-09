package com.lgu.ccss.mgr.management.service.notice;

import org.springframework.http.HttpHeaders;

import com.lgu.ccss.common.model.RequestJSON;
import com.lgu.ccss.common.model.ResponseJSON;

public interface NoticeSearchService {
	
	public ResponseJSON doService(HttpHeaders headers, RequestJSON reqJson);

}
