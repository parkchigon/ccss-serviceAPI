package com.lgu.ccss.common.dao.mgr;


import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.lgu.ccss.common.model.DeviceParkLoctVO;

import devonframe.dataaccess.CommonDao;

@Component
public class MgrAppParkLocDao {

	@Resource(name = "commonDao_altibase")
	private CommonDao commonDao_altibase;

	public DeviceParkLoctVO selectLatestParkLocation(DeviceParkLoctVO deviceParkLoctVO) {
		
		if (deviceParkLoctVO.getMembId() == null ) {
			throw new IllegalArgumentException();
		}
		return commonDao_altibase.select("Parking.selectLatestParkLocation",deviceParkLoctVO);
	}
}
