package com.lgu.ccss.api.ev.service.evCharginginfo;

import org.springframework.http.HttpHeaders;

import com.lgu.ccss.common.model.RequestJSON;
import com.lgu.ccss.common.model.ResponseJSON;

public interface EvCharginginfoService {
	public ResponseJSON doService(HttpHeaders headers, RequestJSON reqJson);
}
