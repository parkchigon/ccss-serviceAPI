package com.lgu.ccss.api.clova.service.deviceLogin;

import org.springframework.http.HttpHeaders;

import com.lgu.ccss.common.model.RequestJSON;
import com.lgu.ccss.common.model.ResponseJSON;

public interface ClovaDeviceLoginService {
	public ResponseJSON doService(HttpHeaders headers, RequestJSON reqJson);
}
