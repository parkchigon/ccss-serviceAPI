package com.lgu.ccss.mgr.mypage.service.datausage;




import org.springframework.http.HttpHeaders;

import com.lgu.ccss.common.model.RequestJSON;
import com.lgu.ccss.common.model.ResponseJSON;

public interface DataUsageService {
	
	public ResponseJSON doService(HttpHeaders headers, RequestJSON reqJson);

}
