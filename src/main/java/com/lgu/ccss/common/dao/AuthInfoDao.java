package com.lgu.ccss.common.dao;

import java.util.Date;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.lgu.ccss.common.model.AIAuthInfoVO;
import com.lgu.common.util.DateUtils;

import devonframe.dataaccess.CommonDao;

@Component
public class AuthInfoDao {
	
	@Value("#{config['common.systemId']}")
	private String systemId;
	
	@Resource(name = "commonDao_oracle")
	private CommonDao commonDao_oracle;
	
	public int insertAIAuthInfo(String deviceToken,String platformToken,String membId) {
		if(deviceToken == null || platformToken == null || membId == null) {
			throw new IllegalArgumentException();
		}
		
		AIAuthInfoVO aiAuthInfo = new AIAuthInfoVO();
		aiAuthInfo.setMembId(membId);
		aiAuthInfo.setDeviceToken(deviceToken);
		aiAuthInfo.setPlatformToken(platformToken);
		aiAuthInfo.setUseYn(AIAuthInfoVO.USE_Y);
		aiAuthInfo.setAiAuthExpDt(DateUtils.getBasicCurrentTime());
		
		aiAuthInfo.setRegId(systemId);
		aiAuthInfo.setUpdId(systemId);

		return commonDao_oracle.insert("AuthInfo.insertAIAuthInfo", aiAuthInfo);
	}
	
	public AIAuthInfoVO selectAIAuthInfo(String membId) {
		if(membId == null) {
			throw new IllegalArgumentException();
		}

		AIAuthInfoVO aiAuthInfo = new AIAuthInfoVO();
		aiAuthInfo.setMembId(membId);
		
		return commonDao_oracle.select("AuthInfo.selectAIAuthInfo", aiAuthInfo);
	}
	
	public int updateAIAuthInfo(String membId, String deviceToken, String platformToken) {
		if(membId == null || deviceToken == null || platformToken == null) {
			throw new IllegalArgumentException();
		}
		
		Date now = new Date();

		AIAuthInfoVO aiAuthInfo = new AIAuthInfoVO();
		aiAuthInfo.setMembId(membId);
		aiAuthInfo.setDeviceToken(deviceToken);
		aiAuthInfo.setPlatformToken(platformToken);
		
		aiAuthInfo.setUpdId(systemId);
		aiAuthInfo.setUpdDt(now);
		
		return commonDao_oracle.update("AuthInfo.updateAIAuthInfo", aiAuthInfo);
	}
	
	public int deleteAIAuthInfo(String membId) {
		if(membId == null) {
			throw new IllegalArgumentException();
		}

		AIAuthInfoVO aiAuthInfo = new AIAuthInfoVO();
		aiAuthInfo.setMembId(membId);
		
		return commonDao_oracle.delete("AuthInfo.deleteAIAuthInfo", aiAuthInfo);
	}
}
