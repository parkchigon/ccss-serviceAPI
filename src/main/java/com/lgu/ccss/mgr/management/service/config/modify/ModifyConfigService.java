package com.lgu.ccss.mgr.management.service.config.modify;



import org.springframework.http.HttpHeaders;

import com.lgu.ccss.common.model.RequestJSON;
import com.lgu.ccss.common.model.ResponseJSON;

public interface ModifyConfigService {
	
	public ResponseJSON doService(HttpHeaders headers, RequestJSON reqJson);

}
