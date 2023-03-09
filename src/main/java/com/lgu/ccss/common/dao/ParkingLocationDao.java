package com.lgu.ccss.common.dao;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.lgu.ccss.common.model.DeviceParkLoctVO;

import devonframe.dataaccess.CommonDao;

@Component
public class ParkingLocationDao {

	@Resource(name = "commonDao_altibase")
	private CommonDao commonDao_altibase;

	public int insertParkingLocation(DeviceParkLoctVO deviceParkLoctVO) {
		
		if (deviceParkLoctVO.getMembId() == null ) {
			throw new IllegalArgumentException();
		}
		return commonDao_altibase.insert("Parking.insertParkingLocation", deviceParkLoctVO);
	}
}
