package com.lgu.ccss.common.dao.mgr;


import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.lgu.ccss.common.model.MembVO;
import com.lgu.ccss.common.model.mgr.MgrAppUserVO;

import devonframe.dataaccess.CommonDao;

@Component
public class MgrAppManagementDao {

	@Resource(name = "commonDao_oracle")
	private CommonDao commonDao_oracle;

	public MembVO selectMembTransToken(MembVO membVO) {
		
		if (membVO.getMembId() == null ) {
			throw new IllegalArgumentException();
		}
		return commonDao_oracle.select("Management.selectMembTransToken",membVO);
	}
	
	public MgrAppUserVO selectMgrappConfig(MgrAppUserVO mgrAppUserVO) {
		
		if (mgrAppUserVO.getMgrappId() == null ) {
			throw new IllegalArgumentException();
		}
		return commonDao_oracle.select("Management.selectMgrappConfig",mgrAppUserVO);
	}
	
	
	public boolean updateMgrappConfig(MgrAppUserVO mgrAppUserVO) {
		
		if (mgrAppUserVO.getMgrappId() == null ) {
			throw new IllegalArgumentException();
		}
		int count =  commonDao_oracle.update("Management.updateMgrappConfig",mgrAppUserVO);
		
		return (count > 0 )? true:false;
	}
}
