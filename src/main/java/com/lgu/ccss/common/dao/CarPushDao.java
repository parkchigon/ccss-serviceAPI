package com.lgu.ccss.common.dao;


import javax.annotation.Resource;

import org.springframework.stereotype.Component;



import com.lgu.ccss.common.model.CarPushVO;

import devonframe.dataaccess.CommonDao;

@Component
public class CarPushDao {
	
	@Resource(name = "commonDao_altibase")
	private CommonDao commonDao_altibase;

	public int insertCarPushMsg(CarPushVO carPushVO) {

		if (carPushVO == null ) {
			throw new IllegalArgumentException();
		}
		
		return commonDao_altibase.insert("CarPush.insertCarPushMsg", carPushVO);
	}
	
	
}
