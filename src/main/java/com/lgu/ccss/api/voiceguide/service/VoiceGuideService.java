package com.lgu.ccss.api.voiceguide.service;

import org.springframework.http.HttpHeaders;

import com.lgu.ccss.common.model.RequestJSON;
import com.lgu.ccss.common.model.ResponseJSON;

public interface VoiceGuideService {
	public ResponseJSON doService(HttpHeaders headers, RequestJSON reqJson);
}
