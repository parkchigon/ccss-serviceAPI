package com.lgu.ccss.mgr.arrival.service.rcpt.register;



import org.springframework.http.HttpHeaders;

import com.lgu.ccss.common.model.RequestJSON;
import com.lgu.ccss.common.model.ResponseJSON;

public interface ArrivalRcptRegService {
	
	public ResponseJSON doService(HttpHeaders headers, RequestJSON reqJson);

}
