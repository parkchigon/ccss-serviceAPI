package com.lgu.ccss.mgr.arrival.service.register;



import org.springframework.http.HttpHeaders;

import com.lgu.ccss.common.model.RequestJSON;
import com.lgu.ccss.common.model.ResponseJSON;

public interface ArrivalRegService {
	
	public ResponseJSON doService(HttpHeaders headers, RequestJSON reqJson);

}
