package com.lgu.ccss.api.datagift.service;

import org.springframework.http.HttpHeaders;

import com.lgu.ccss.common.model.RequestJSON;
import com.lgu.ccss.common.model.ResponseJSON;

public interface DataGiftDeviceService {

	// 데이터 상품권 구매내역
	public ResponseJSON getDatagiftOwnList(HttpHeaders headers,
			RequestJSON reqJson);

}
