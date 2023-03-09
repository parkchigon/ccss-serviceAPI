package com.lgu.ccss.mgr.device.service.delete;




import org.springframework.http.HttpHeaders;

import com.lgu.ccss.common.model.RequestJSON;
import com.lgu.ccss.common.model.ResponseJSON;

public interface MgrDeviceDeleteService {
	
	public ResponseJSON doService(HttpHeaders headers, RequestJSON reqJson);

}
