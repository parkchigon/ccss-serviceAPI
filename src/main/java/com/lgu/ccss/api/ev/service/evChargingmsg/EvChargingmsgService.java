package com.lgu.ccss.api.ev.service.evChargingmsg;

import org.springframework.http.HttpHeaders;

import com.lgu.ccss.common.model.RequestJSON;
import com.lgu.ccss.common.model.ResponseJSON;

public interface EvChargingmsgService {
	public ResponseJSON doService(HttpHeaders headers, RequestJSON reqJson);
}
