package com.lgu.ccss.mgr.auth.service.logout;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpHeaders;

import com.lgu.ccss.common.model.RequestJSON;
import com.lgu.ccss.common.model.ResponseJSON;

public interface MgrAuthLogoutService {
	
	public ResponseJSON doService(HttpHeaders headers, RequestJSON reqJson ,HttpServletRequest request);

}
