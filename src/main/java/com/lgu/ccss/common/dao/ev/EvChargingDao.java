package com.lgu.ccss.common.dao.ev;


import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.lgu.ccss.common.model.ev.EvCharginginfoVO;

import devonframe.dataaccess.CommonDao;

@Component
public class EvChargingDao {

	@Resource(name = "commonDao_oracle")
	private CommonDao commonDao_oracle;
	
	public int updateEvCharginginfo(EvCharginginfoVO evCharginginfoVO){
		return commonDao_oracle.update("EvCharging.updateEvCharginginfo",evCharginginfoVO);
	}
}
