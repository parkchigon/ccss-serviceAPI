package com.lgu.ccss.common.dao.mgr;


import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.lgu.ccss.common.model.OneIdAuthInfoVO;
import devonframe.dataaccess.CommonDao;

@Component
public class MgrAppOneIdAuthInfo {

	@Resource(name = "commonDao_oracle")
	private CommonDao commonDao_oracle;
	
	@Value("#{config['common.systemId']}")
	private String systemId;
	
	private final String DEF_Y="Y";
	private final String DEF_N="N";
			
	
	public OneIdAuthInfoVO selectCekOneIdAuthInfo(OneIdAuthInfoVO oneIdAuthInfoVO) {
		
		if (oneIdAuthInfoVO.getMembId() == null ) {
			throw new IllegalArgumentException();
		}
		return commonDao_oracle.select("OneIdAuthInfo.selectCekOneIdAuthInfo",oneIdAuthInfoVO);
	}
	
	
	public int insertCekOneIdAuthInfo(String membId,String oneId,String ssoKey,String oneIdMembNo, String iotSessionKey,String homeCode,String ctn,String nid, String logoutYn) {
		
		if (membId == null || membId.length() == 0 || oneId == null || oneId.length() == 0 
				|| ssoKey == null || ssoKey.length() == 0 	|| oneIdMembNo == null || oneIdMembNo.length() == 0 
				||  iotSessionKey == null || iotSessionKey.length() == 0 
				) {	
			throw new IllegalArgumentException();
		}
		
		OneIdAuthInfoVO oneIdAuthInfoVO = new OneIdAuthInfoVO();
		oneIdAuthInfoVO.setMembId(membId);
		oneIdAuthInfoVO.setOneId(oneId);
		oneIdAuthInfoVO.setSsoKey(ssoKey);
		if(logoutYn == null){
			oneIdAuthInfoVO.setLoginStatus(DEF_Y);
		}else{
			oneIdAuthInfoVO.setLoginStatus(logoutYn);
		}
		oneIdAuthInfoVO.setOneIdMembNo(oneIdMembNo);
		oneIdAuthInfoVO.setIotSessionKey(iotSessionKey);
		oneIdAuthInfoVO.setHomeCode(homeCode);
		
		if(ctn !=null && ctn.length() > 0){
			oneIdAuthInfoVO.setCtn(ctn);	
		}
		oneIdAuthInfoVO.setNid(nid);
		oneIdAuthInfoVO.setRegId(systemId);
		oneIdAuthInfoVO.setUpdId(systemId);
		
		return commonDao_oracle.update("OneIdAuthInfo.insertCekOneIdAuthInfo",oneIdAuthInfoVO);
	}
	
	public int updateCekOneIdAuthInfo(String membId) {
		if (membId == null || membId.length() == 0 ) {	
			throw new IllegalArgumentException();
		}
		OneIdAuthInfoVO oneIdAuthInfoVO = new OneIdAuthInfoVO();
		oneIdAuthInfoVO.setMembId(membId);
		oneIdAuthInfoVO.setLoginStatus(DEF_N);
		oneIdAuthInfoVO.setUpdId(systemId);
		
		return commonDao_oracle.update("OneIdAuthInfo.updateCekOneIdAuthInfo",oneIdAuthInfoVO);
	}
	
}
