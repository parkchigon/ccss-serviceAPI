package com.lgu.ccss.common.dao.mgr;


import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.lgu.ccss.common.model.mgr.MgrAppVO;

import devonframe.dataaccess.CommonDao;

@Component
public class MgrAppDao {

	@Resource(name = "commonDao_oracle")
	private CommonDao commonDao_oracle;

	public MgrAppVO getCurrentMagrAppVersion(MgrAppVO mgrAppVO) {
		
		if (mgrAppVO.getAppType() == null || mgrAppVO.getAppVer() == null) {
			throw new IllegalArgumentException();
		}
		return commonDao_oracle.select("Version.selectCurrentMagrAppVersion",mgrAppVO);
	}
	
	public MgrAppVO getNewestMagrAppVersion(MgrAppVO mgrAppVO) {
		
		if (mgrAppVO.getAppType() == null || mgrAppVO.getAppVer() == null) {
			throw new IllegalArgumentException();
		}
		return commonDao_oracle.select("Version.selectNewestMagrAppVersion",mgrAppVO);
	}
}
