package com.lgu.ccss.api.send2car.service.targetList;

import org.springframework.http.HttpHeaders;

import com.lgu.ccss.common.model.RequestJSON;
import com.lgu.ccss.common.model.ResponseJSON;

public interface TargetListService {
	public ResponseJSON doService(HttpHeaders headers, RequestJSON reqJson);
}
