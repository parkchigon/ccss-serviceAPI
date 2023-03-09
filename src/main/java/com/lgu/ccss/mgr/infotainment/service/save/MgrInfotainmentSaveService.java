package com.lgu.ccss.mgr.infotainment.service.save;




import org.springframework.http.HttpHeaders;

import com.lgu.ccss.common.model.RequestJSON;
import com.lgu.ccss.common.model.ResponseJSON;

public interface MgrInfotainmentSaveService {
	
	public ResponseJSON doService(HttpHeaders headers, RequestJSON reqJson);

}
