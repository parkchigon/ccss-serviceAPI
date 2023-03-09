package com.lgu.ccss.mgr.infotainment.service.callback;




import org.springframework.http.HttpHeaders;

import com.lgu.ccss.common.model.RequestJSON;
import com.lgu.ccss.common.model.ResponseJSON;

public interface MgrInfotainmentLoginCallBackService {
	
	public ResponseJSON doService(HttpHeaders headers, RequestJSON reqJson);

}
