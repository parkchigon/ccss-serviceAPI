package com.lgu.ccss.api.pushConfirm;

import org.springframework.http.HttpHeaders;
import org.springframework.util.MultiValueMap;


public interface ConfirmPushService {
	public int doService(HttpHeaders headers, MultiValueMap<String, String> requestBody, String type);
}
