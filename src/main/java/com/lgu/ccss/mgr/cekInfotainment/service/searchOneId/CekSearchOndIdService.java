package com.lgu.ccss.mgr.cekInfotainment.service.searchOneId;

import org.springframework.http.HttpHeaders;

import com.lgu.ccss.common.model.RequestJSON;
import com.lgu.ccss.common.model.ResponseJSON;

public interface CekSearchOndIdService {
	
	public ResponseJSON doService(HttpHeaders headers, RequestJSON reqJson);

}
