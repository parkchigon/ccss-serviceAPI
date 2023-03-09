package com.lgu.ccss.mgr.parking.service.location;



import org.springframework.http.HttpHeaders;

import com.lgu.ccss.common.model.RequestJSON;
import com.lgu.ccss.common.model.ResponseJSON;

public interface ParkingLocationService {
	
	public ResponseJSON doService(HttpHeaders headers, RequestJSON reqJson);

}
