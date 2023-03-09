package com.lgu.ccss.api.mgrapp.service.userList;

import org.springframework.http.HttpHeaders;

import com.lgu.ccss.common.model.RequestJSON;
import com.lgu.ccss.common.model.ResponseJSON;

public interface UserListService {
	public ResponseJSON doService(HttpHeaders headers, RequestJSON reqJson);
}
