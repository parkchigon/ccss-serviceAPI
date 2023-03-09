package com.lgu.ccss.common.dao.mgr;


import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.lgu.ccss.common.model.mgr.MgrAppSessVO;
import devonframe.dataaccess.CommonDao;

@Component
public class MgrAppSessionDao {
	@Resource(name = "commonDao_altibase")
	private CommonDao commonDao_altibase;

	public int insertMgrSess(MgrAppSessVO mgrAppSessVO) {

		if (mgrAppSessVO == null || mgrAppSessVO.getMgrappSessionId() == null) {
			throw new IllegalArgumentException();
		}
		
		return commonDao_altibase.insert("MgrAppSession.insertMgrSession", mgrAppSessVO);
	}
	
	public MgrAppSessVO selectMgrSess(String sessionId) {

		if (sessionId == null) {
			throw new IllegalArgumentException();
		}
		
		MgrAppSessVO mgrAppSessVO = new MgrAppSessVO();
		mgrAppSessVO.setMgrappSessionId(sessionId);

		return commonDao_altibase.select("MgrAppSession.selectMgrSession", mgrAppSessVO);
	}

	public void updateMgrSessTime(String sessionId, String expDate, String connIp,String randomKey) {

		if (sessionId == null) {
			throw new IllegalArgumentException();
		}
		
		MgrAppSessVO mgrAppSessVO = new MgrAppSessVO();
		mgrAppSessVO.setMgrappSessionId(sessionId);
		mgrAppSessVO.setConnIp(connIp);
		mgrAppSessVO.setSessionExpDt(expDate);
		mgrAppSessVO.setRandomKey(randomKey);
		commonDao_altibase.update("MgrAppSession.updateMgrSessTime", mgrAppSessVO);
	}

	public void deleteMgrSessByID(String sessionId) {

		if (sessionId == null) {
			throw new IllegalArgumentException();
		}
		
		MgrAppSessVO mgrAppSessVO = new MgrAppSessVO();
		mgrAppSessVO.setMgrappSessionId(sessionId);
		
		commonDao_altibase.delete("MgrAppSession.deleteMgrSessByID", mgrAppSessVO);
	}
	
	public void deleteMgrSessByMgrappId(String mgrappId) {

		if (mgrappId == null ) {
			throw new IllegalArgumentException();
		}
		
		MgrAppSessVO mgrAppSessVO = new MgrAppSessVO();
		mgrAppSessVO.setMgrappId(mgrappId);
		
		commonDao_altibase.delete("MgrAppSession.deleteMgrSessByMgrappId", mgrAppSessVO);
	}
}
