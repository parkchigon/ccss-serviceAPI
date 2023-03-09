package com.lgu.ccss.mgr.device.service.choice;




import org.springframework.http.HttpHeaders;

import com.lgu.ccss.common.model.RequestJSON;
import com.lgu.ccss.common.model.ResponseJSON;

public interface MgrDeviceChoiceService {
	
	public ResponseJSON doService(HttpHeaders headers, RequestJSON reqJson);

}
