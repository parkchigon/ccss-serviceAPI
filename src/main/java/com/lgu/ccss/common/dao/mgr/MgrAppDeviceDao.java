package com.lgu.ccss.common.dao.mgr;


import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.lgu.ccss.common.model.mgr.MgrAppDeviceVO;
import com.lgu.ccss.common.model.mgr.MgrAppUserVO;

import devonframe.dataaccess.CommonDao;

@Component
public class MgrAppDeviceDao {

	@Resource(name = "commonDao_oracle")
	private CommonDao commonDao_oracle;
	
	@Value("#{config['common.systemId']}")
	private String systemId;
	
	public int insertTbMgrAppDevice(MgrAppDeviceVO mgrAppDeviceVO) {
		return commonDao_oracle.insert("MgrAppDevice.insertTbMgrAppDevice",mgrAppDeviceVO);
	}
	
	public MgrAppDeviceVO selectMgrDeviceInfo(MgrAppDeviceVO mgrAppDeviceVO){
		return commonDao_oracle.select("MgrAppDevice.selectMgrDeviceInfo",mgrAppDeviceVO);
	}
	
	public MgrAppDeviceVO selectMgrMainDeviceInfo(MgrAppDeviceVO mgrAppDeviceVO){
		return commonDao_oracle.select("MgrAppDevice.selectMgrMainDeviceInfo",mgrAppDeviceVO);
	}
	
	public List<MgrAppDeviceVO> selectMgrDeviceInfoList(MgrAppDeviceVO mgrAppDeviceVO){
		return commonDao_oracle.selectList("MgrAppDevice.selectMgrDeviceInfo",mgrAppDeviceVO);
	}

	public int updateDeviceMainUseYn(MgrAppDeviceVO mgrAppDeviceVO){
		return commonDao_oracle.update("MgrAppDevice.updateDeviceMainUseYn",mgrAppDeviceVO);
	}
	
	public int updateDeviceNm(MgrAppDeviceVO mgrAppDeviceVO){
		return commonDao_oracle.update("MgrAppDevice.updateDeviceNm",mgrAppDeviceVO);
	}
	
	public int deleteDevice(MgrAppDeviceVO mgrAppDeviceVO){
		return commonDao_oracle.update("MgrAppDevice.deleteDevice",mgrAppDeviceVO);
	}
	
	public int removeTbMgrAppDevice(MgrAppUserVO mgrAppUserVO){
		return commonDao_oracle.update("MgrAppDevice.removeTbMgrAppDevice",mgrAppUserVO);
	}
	
	public int updateMainUseYn(String membId,String mgrappId, String mainUseYn) {

		MgrAppDeviceVO mgrAppDeviceVO = new MgrAppDeviceVO();
		
		mgrAppDeviceVO.setMembId(membId);
		mgrAppDeviceVO.setMgrappId(mgrappId);
		mgrAppDeviceVO.setMainUseYn(mainUseYn);
		mgrAppDeviceVO.setUpdId(systemId);

		return commonDao_oracle.update("MgrAppDevice.updateMainUseYn", mgrAppDeviceVO);
	}	
	
}
