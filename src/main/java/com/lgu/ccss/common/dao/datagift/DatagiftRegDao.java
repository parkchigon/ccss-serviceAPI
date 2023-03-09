package com.lgu.ccss.common.dao.datagift;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.lgu.ccss.common.model.datagift.RegisterVO;

import devonframe.dataaccess.CommonDao;

@Component
public class DatagiftRegDao {

	@Resource(name = "commonDao_oracle")
	private CommonDao commonDao_oracle;
	
	public int insertRegister(RegisterVO regist)
	{
		return commonDao_oracle.insert("GiftRegist.insertRegister", regist);
	}	
}
