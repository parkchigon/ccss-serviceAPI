package com.lgu.ccss.common.dao;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.lgu.ccss.common.model.CekAIAuthInfoVO;

import devonframe.dataaccess.CommonDao;

@Component
public class CekAuthInfoDao {
	@Value("#{config['common.systemId']}")
	private String systemId;
	
	@Resource(name = "commonDao_oracle")
	private CommonDao commonDao_oracle;
	
	
	public CekAIAuthInfoVO selectCekAIAuthInfo (String membId){
		if(membId == null) {
			throw new IllegalArgumentException();
		}
		CekAIAuthInfoVO cekAIAuthInfoVO = new CekAIAuthInfoVO();
		cekAIAuthInfoVO.setMembId(membId);

		return commonDao_oracle.select("CekAuthInfo.selectCekAIAuthInfo", cekAIAuthInfoVO);
		
	}
	
	public int insertCekAIAuthInfo(String deviceToken,String customId,String membId,String createTime,String nid) {
		if(deviceToken == null || customId == null || membId == null || nid == null) {
			throw new IllegalArgumentException();
		}
		
		CekAIAuthInfoVO cekAIAuthInfoVO = new CekAIAuthInfoVO();
		cekAIAuthInfoVO.setMembId(membId);
		cekAIAuthInfoVO.setCustomId(customId);
		cekAIAuthInfoVO.setDeviceToken(deviceToken);
		cekAIAuthInfoVO.setCreateTime(createTime);
		cekAIAuthInfoVO.setNid(nid);
		cekAIAuthInfoVO.setUseYn(CekAIAuthInfoVO.USE_Y);
		cekAIAuthInfoVO.setRegId(systemId);
		cekAIAuthInfoVO.setUpdId(systemId);

		return commonDao_oracle.insert("CekAuthInfo.insertCekAIAuthInfo", cekAIAuthInfoVO);
	}
	
	
	public int updateCekAIAuthInfo(String membId, String deviceToken, String customId, String createTime, String nid) {
		if(membId == null || deviceToken == null || customId == null || createTime == null) {
			throw new IllegalArgumentException();
		}
		
		CekAIAuthInfoVO cekAIAuthInfoVO = new CekAIAuthInfoVO();
		cekAIAuthInfoVO.setMembId(membId);
		cekAIAuthInfoVO.setCustomId(customId);
		cekAIAuthInfoVO.setDeviceToken(deviceToken);
		cekAIAuthInfoVO.setCreateTime(createTime);
		cekAIAuthInfoVO.setNid(nid);
		cekAIAuthInfoVO.setUseYn(CekAIAuthInfoVO.USE_Y);
		cekAIAuthInfoVO.setRegId(systemId);
		cekAIAuthInfoVO.setUpdId(systemId);
		
		return commonDao_oracle.update("CekAuthInfo.updateCekAIAuthInfo", cekAIAuthInfoVO);
	}
	
	public int deleteCekAIAuthInfo(String membId) {
		if(membId == null) {
			throw new IllegalArgumentException();
		}

		CekAIAuthInfoVO cekAIAuthInfoVO = new CekAIAuthInfoVO();
		cekAIAuthInfoVO.setMembId(membId);
		
		return commonDao_oracle.delete("CekAuthInfo.deleteCekAIAuthInfo", cekAIAuthInfoVO);
	}

}
