package com.lgu.ccss.common.dao;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.lgu.ccss.common.model.InfotainAuthInfoVO;

import devonframe.dataaccess.CommonDao;

@Component
public class InfotainAuthInfoDao {
	@Value("#{config['common.systemId']}")
	private String systemId;
	
	@Resource(name = "commonDao_oracle")
	private CommonDao commonDao_oracle;
	
	public int insertInfotainAuthInfo(String membId,String mgrappId, String serviceId, String tokenName, String tokenValue) {
		if(membId == null  ||  mgrappId == null || serviceId == null || tokenName == null || tokenValue == null) {
			throw new IllegalArgumentException();
		}
	
		InfotainAuthInfoVO infotainAuthInfo = new InfotainAuthInfoVO();
		infotainAuthInfo.setMembId(membId);
		infotainAuthInfo.setServiceId(serviceId);
		infotainAuthInfo.setTokenNm(tokenName);
		infotainAuthInfo.setTokenValue(tokenValue);
		infotainAuthInfo.setUseYn(InfotainAuthInfoVO.USE_Y);

		infotainAuthInfo.setRegId(systemId);
		infotainAuthInfo.setUpdId(systemId);
		
		infotainAuthInfo.setMgrappId(mgrappId);
		
		return commonDao_oracle.insert("InfotainAuthInfo.insertInfotatinAuthInfo", infotainAuthInfo);
	}
	
	public int insertInfotainAuthInfo(String membId, String serviceId, String tokenName, String tokenValue) {
		if(membId == null || serviceId == null || tokenName == null || tokenValue == null) {
			throw new IllegalArgumentException();
		}
	
		InfotainAuthInfoVO infotainAuthInfo = new InfotainAuthInfoVO();
		infotainAuthInfo.setMembId(membId);
		infotainAuthInfo.setServiceId(serviceId);
		infotainAuthInfo.setTokenNm(tokenName);
		infotainAuthInfo.setTokenValue(tokenValue);
		infotainAuthInfo.setUseYn(InfotainAuthInfoVO.USE_Y);

		infotainAuthInfo.setRegId(systemId);
		infotainAuthInfo.setUpdId(systemId);
		
		return commonDao_oracle.insert("InfotainAuthInfo.insertInfotatinAuthInfo", infotainAuthInfo);
	}
	
	public List<InfotainAuthInfoVO> selectInfotainAuthInfo(String membId) {
		if(membId == null) {
			throw new IllegalArgumentException();
		}

		InfotainAuthInfoVO infotainAuthInfo = new InfotainAuthInfoVO();
		infotainAuthInfo.setMembId(membId);
		
		return commonDao_oracle.selectList("InfotainAuthInfo.selectInfotainAuthInfoByMembId", infotainAuthInfo);
	}
	
	public List<InfotainAuthInfoVO> selectInfotainAuthInfo(String membId, String serviceId) {
		if(membId == null || serviceId == null) {
			throw new IllegalArgumentException();
		}

		InfotainAuthInfoVO infotainAuthInfo = new InfotainAuthInfoVO();
		infotainAuthInfo.setMembId(membId);
		infotainAuthInfo.setServiceId(serviceId);
		
		return commonDao_oracle.selectList("InfotainAuthInfo.selectInfotainAuthInfoByServiceId", infotainAuthInfo);
	}
	
	public InfotainAuthInfoVO selectInfotainAuthInfo(String membId, String serviceId, String tokenName) {
		if(membId == null || serviceId == null || tokenName == null) {
			throw new IllegalArgumentException();
		}

		InfotainAuthInfoVO infotainAuthInfo = new InfotainAuthInfoVO();
		infotainAuthInfo.setMembId(membId);
		infotainAuthInfo.setServiceId(serviceId);
		infotainAuthInfo.setTokenNm(tokenName);
		
		return commonDao_oracle.select("InfotainAuthInfo.selectInfotainAuthInfoByTokenName", infotainAuthInfo);
	}
	
	public int updateInfotainAuthInfo(String membId, String serviceId, String tokenName) {
		if(membId == null || serviceId == null || tokenName == null) {
			throw new IllegalArgumentException();
		}
		Date now = new Date();

		InfotainAuthInfoVO infotainAuthInfo = new InfotainAuthInfoVO();
		infotainAuthInfo.setMembId(membId);
		infotainAuthInfo.setServiceId(serviceId);
		infotainAuthInfo.setTokenNm(tokenName);
		
		infotainAuthInfo.setUpdId(systemId);
		infotainAuthInfo.setUpdDt(now);

		return commonDao_oracle.update("InfotainAuthInfo.updateInfotainAuthInfoByTokenName", infotainAuthInfo);
	}
	
	public int deleteInfotainAuthInfo(String membId) {
		if(membId == null) {
			throw new IllegalArgumentException();
		}

		InfotainAuthInfoVO infotainAuthInfo = new InfotainAuthInfoVO();
		infotainAuthInfo.setMembId(membId);
		
		return commonDao_oracle.delete("InfotainAuthInfo.deleteInfotainAuthInfoByMembId", infotainAuthInfo);
	}
	
	public int deleteInfotainAuthInfo(String membId, String serviceId) {
		if(membId == null || serviceId == null) {
			throw new IllegalArgumentException();
		}

		InfotainAuthInfoVO infotainAuthInfo = new InfotainAuthInfoVO();
		infotainAuthInfo.setMembId(membId);
		infotainAuthInfo.setServiceId(serviceId);
		
		return commonDao_oracle.delete("InfotainAuthInfo.deleteInfotainAuthInfoByServiceId", infotainAuthInfo);
	}
	
	public int deleteInfotainAuthInfo(String membId, String serviceId, String tokenName) {
		if(membId == null || serviceId == null || tokenName == null) {
			throw new IllegalArgumentException();
		}

		InfotainAuthInfoVO infotainAuthInfo = new InfotainAuthInfoVO();
		infotainAuthInfo.setMembId(membId);
		infotainAuthInfo.setServiceId(serviceId);
		infotainAuthInfo.setTokenNm(tokenName);
		
		return commonDao_oracle.delete("InfotainAuthInfo.deleteInfotainAuthInfoByTokenName", infotainAuthInfo);
	}
}
