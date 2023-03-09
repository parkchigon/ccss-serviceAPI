package com.lgu.ccss.mgr.arrival.service.rcpt.delete;

import org.springframework.http.HttpHeaders;

import com.lgu.ccss.common.model.RequestJSON;
import com.lgu.ccss.common.model.ResponseJSON;

public interface ArrivalRcptDelService {
	
	public ResponseJSON doService(HttpHeaders headers, RequestJSON reqJson);

}
