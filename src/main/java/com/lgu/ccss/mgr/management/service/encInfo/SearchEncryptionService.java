package com.lgu.ccss.mgr.management.service.encInfo;



import org.springframework.http.HttpHeaders;

import com.lgu.ccss.common.model.RequestJSON;
import com.lgu.ccss.common.model.ResponseJSON;

public interface SearchEncryptionService {
	
	public ResponseJSON doService(HttpHeaders headers, RequestJSON reqJson);

}
