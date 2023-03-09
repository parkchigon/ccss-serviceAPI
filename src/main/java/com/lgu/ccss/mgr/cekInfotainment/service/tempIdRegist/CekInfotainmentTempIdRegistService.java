package com.lgu.ccss.mgr.cekInfotainment.service.tempIdRegist;

import org.springframework.http.HttpHeaders;

import com.lgu.ccss.common.model.RequestJSON;
import com.lgu.ccss.common.model.ResponseJSON;

public interface CekInfotainmentTempIdRegistService {
	public ResponseJSON doService(HttpHeaders headers, RequestJSON reqJson);
}
