package com.lgu.ccss.mgr.infotainment.service.logout;




import org.springframework.http.HttpHeaders;

import com.lgu.ccss.common.model.RequestJSON;
import com.lgu.ccss.common.model.ResponseJSON;

public interface MgrInfotainmentLogoutService {
	
	public ResponseJSON doService(HttpHeaders headers, RequestJSON reqJson);

}
