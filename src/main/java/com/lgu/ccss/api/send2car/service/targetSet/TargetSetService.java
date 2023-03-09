package com.lgu.ccss.api.send2car.service.targetSet;

import org.springframework.http.HttpHeaders;

import com.lgu.ccss.common.model.RequestJSON;
import com.lgu.ccss.common.model.ResponseJSON;

public interface TargetSetService {
	public ResponseJSON doService(HttpHeaders headers, RequestJSON reqJson);
}
