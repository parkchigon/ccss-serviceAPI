package com.lgu.ccss.api.mgrapp.service.userDel;

import org.springframework.http.HttpHeaders;

import com.lgu.ccss.common.model.RequestJSON;
import com.lgu.ccss.common.model.ResponseJSON;

public interface UserDelService {
	public ResponseJSON doService(HttpHeaders headers, RequestJSON reqJson);
}
