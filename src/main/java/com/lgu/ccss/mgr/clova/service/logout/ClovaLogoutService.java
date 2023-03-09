package com.lgu.ccss.mgr.clova.service.logout;

import org.springframework.http.HttpHeaders;

import com.lgu.ccss.common.model.RequestJSON;
import com.lgu.ccss.common.model.ResponseJSON;

public interface ClovaLogoutService {

	ResponseJSON doService(HttpHeaders headers, RequestJSON reqJson);

}
