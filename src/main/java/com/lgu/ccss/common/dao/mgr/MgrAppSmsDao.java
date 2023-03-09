package com.lgu.ccss.common.dao.mgr;


import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.lgu.ccss.common.model.mgr.MgrAppSmsCertInfoVO;
import com.lgu.ccss.common.model.mgr.MgrAppSmsVO;

import devonframe.dataaccess.CommonDao;

@Component
public class MgrAppSmsDao {
	
	@Resource(name = "commonDao_oracle")
	private CommonDao commonDao_oracle;
	
	@Resource(name = "commonDao_altibase")
	private CommonDao commonDao_altibase;

	
	public MgrAppSmsCertInfoVO getCertNo(MgrAppSmsCertInfoVO mgrAppSmsCertInfoVO) {
		return commonDao_oracle.select("SmsCert.getCertNo",mgrAppSmsCertInfoVO);
	}
	
	public int setCertInfo(MgrAppSmsCertInfoVO mgrAppSmsCertInfoVO) {
		return commonDao_oracle.insert("SmsCert.setCertInfo",mgrAppSmsCertInfoVO);
	}
	
	public int setSmsInfoReg(MgrAppSmsVO mgrAppSmsVO) {
		return commonDao_altibase.insert("SmsSend.setSmsInfoReg",mgrAppSmsVO);
	}
	
	public MgrAppSmsCertInfoVO getCertInfoConfirm(MgrAppSmsCertInfoVO mgrAppSmsCertInfoVO) {
		return commonDao_oracle.select("SmsCert.getCertInfoConfirm",mgrAppSmsCertInfoVO);
	}
	
	public int setCertNoDel(MgrAppSmsCertInfoVO mgrAppSmsCertInfoVO) {
		return commonDao_oracle.delete("SmsCert.setCertNoDel",mgrAppSmsCertInfoVO);
	}
	
	
}
