package com.lgu.ccss.mgr.music.service.loginout;

import org.springframework.http.HttpHeaders;

import com.lgu.ccss.common.model.RequestJSON;
import com.lgu.ccss.common.model.ResponseJSON;

public interface LoginoutService {
	public ResponseJSON doService(HttpHeaders headers, RequestJSON reqJson);
}
