package com.lgu.ccss.mgr.device.service.nickname;




import org.springframework.http.HttpHeaders;

import com.lgu.ccss.common.model.RequestJSON;
import com.lgu.ccss.common.model.ResponseJSON;

public interface MgrDeviceNickNameService {
	
	public ResponseJSON doService(HttpHeaders headers, RequestJSON reqJson);

}
