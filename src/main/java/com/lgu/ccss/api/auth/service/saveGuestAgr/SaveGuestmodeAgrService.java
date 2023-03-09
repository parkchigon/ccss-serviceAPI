package com.lgu.ccss.api.auth.service.saveGuestAgr;

import org.springframework.http.HttpHeaders;

import com.lgu.ccss.common.model.RequestJSON;
import com.lgu.ccss.common.model.ResponseJSON;

public interface SaveGuestmodeAgrService {

	ResponseJSON doService(HttpHeaders headers, RequestJSON reqJson);

}
