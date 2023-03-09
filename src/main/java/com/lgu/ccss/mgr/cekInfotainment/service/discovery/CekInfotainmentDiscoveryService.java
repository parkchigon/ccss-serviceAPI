package com.lgu.ccss.mgr.cekInfotainment.service.discovery;

import org.springframework.http.HttpHeaders;

import com.lgu.ccss.common.model.RequestJSON;
import com.lgu.ccss.common.model.ResponseJSON;

public interface CekInfotainmentDiscoveryService {
	public ResponseJSON doService(HttpHeaders headers, RequestJSON reqJson);
}
