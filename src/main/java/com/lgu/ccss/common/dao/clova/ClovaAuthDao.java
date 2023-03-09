package com.lgu.ccss.common.dao.clova;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.lgu.ccss.common.model.clova.ClovaAuthInfoVO;

import devonframe.dataaccess.CommonDao;

@Component
public class ClovaAuthDao{
	@Value("#{config['common.systemId']}")
	private String systemId;

	@Resource(name = "commonDao_oracle")
	private CommonDao commonDao_oracle;
	
	public ClovaAuthInfoVO selectClovaAuthInfo (String membId){
		if (membId == null || membId.length() == 0) {
			throw new IllegalArgumentException();
		}
		
		ClovaAuthInfoVO clovaAuthInfoVO = new ClovaAuthInfoVO();
		clovaAuthInfoVO.setMembId(membId);

		return commonDao_oracle.select("ClovaAuthInfo.selectClovaAuthInfo", clovaAuthInfoVO);
	}
	
	
	public int insertClovaAuthInfo (ClovaAuthInfoVO clovaAuthInfoVO){
		if (clovaAuthInfoVO == null ) {
			throw new IllegalArgumentException();
		}
		
		return commonDao_oracle.insert("ClovaAuthInfo.insertClovaAuthInfo", clovaAuthInfoVO);
	}
	
	public int deleteClovaAuthInfo (ClovaAuthInfoVO clovaAuthInfoVO){
		if (clovaAuthInfoVO == null ) {
			throw new IllegalArgumentException();
		}
		
		return commonDao_oracle.delete("ClovaAuthInfo.deleteClovaAuthInfo", clovaAuthInfoVO);
	}
	
	
}
