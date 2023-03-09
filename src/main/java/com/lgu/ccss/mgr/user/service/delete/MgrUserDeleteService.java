package com.lgu.ccss.mgr.user.service.delete;




import org.springframework.http.HttpHeaders;

import com.lgu.ccss.common.model.RequestJSON;
import com.lgu.ccss.common.model.ResponseJSON;

public interface MgrUserDeleteService {
	
	public ResponseJSON doService(HttpHeaders headers, RequestJSON reqJson);

}
