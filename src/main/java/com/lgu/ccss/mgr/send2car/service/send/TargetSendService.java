package com.lgu.ccss.mgr.send2car.service.send;

import org.springframework.http.HttpHeaders;

import com.lgu.ccss.common.model.RequestJSON;
import com.lgu.ccss.common.model.ResponseJSON;

public interface TargetSendService {
	
	public ResponseJSON doService(HttpHeaders headers, RequestJSON reqJson);

}
