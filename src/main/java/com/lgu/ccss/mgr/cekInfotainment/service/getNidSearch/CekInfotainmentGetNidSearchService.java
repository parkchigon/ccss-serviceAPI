package com.lgu.ccss.mgr.cekInfotainment.service.getNidSearch;

import org.springframework.http.HttpHeaders;

import com.lgu.ccss.common.model.RequestJSON;
import com.lgu.ccss.common.model.ResponseJSON;

public interface CekInfotainmentGetNidSearchService {
	public ResponseJSON doService(HttpHeaders headers, RequestJSON reqJson);
}
