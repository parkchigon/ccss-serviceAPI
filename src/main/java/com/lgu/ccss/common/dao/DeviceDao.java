package com.lgu.ccss.common.dao;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.lgu.ccss.common.model.ConnDeviceVO;

import devonframe.dataaccess.CommonDao;

@Component
public class DeviceDao {

	@Value("#{config['common.systemId']}")
	private String systemId;

	@Resource(name = "commonDao_oracle")
	private CommonDao commonDao_oracle;

	public ConnDeviceVO checkUICCID(String membId, String deviceCtn, String uiccId) {
		if (deviceCtn == null || uiccId == null) {
			throw new IllegalArgumentException();
		}

		ConnDeviceVO connDevice = new ConnDeviceVO();
		connDevice.setMembId(membId);
		connDevice.setDeviceCtn(deviceCtn);
		connDevice.setUiccId(uiccId);

		return commonDao_oracle.select("Device.selectUICCID", connDevice);
	}

	public int insertConnDevice(ConnDeviceVO connDevice) {

		Date now = new Date();
		
		connDevice.setRegId(systemId);
		connDevice.setRegDt(now);
		connDevice.setUpdId(systemId);
		connDevice.setUpdDt(now);

		return commonDao_oracle.insert("Device.insertConnDevice", connDevice);
	}
	
	public ConnDeviceVO getDeviceInfo(ConnDeviceVO connDevice) {
		return commonDao_oracle.select("Device.getDeviceInfo", connDevice);
	}
	
	public List<ConnDeviceVO> getDeviceInfoList(ConnDeviceVO connDevice){
		return commonDao_oracle.selectList("Device.getDeviceInfo", connDevice);
	}
	
/*	public List<ConnDeviceVO> getDeviceInfoListByUserCtn(String mgrappLoginId){
		return commonDao_oracle.selectList("Device.getDeviceInfoListByUserCtn",mgrappLoginId);
	}*/
	
	public List<ConnDeviceVO> getDeviceInfoListByMgrappId(String mgrappId){
		return commonDao_oracle.selectList("Device.getDeviceInfoListByMgrappId",mgrappId);
	}
	
	public int updateUseYn(String membId, String useYn) {

		ConnDeviceVO connDevice = new ConnDeviceVO();
		Date now = new Date();
		
		connDevice.setMembId(membId);
		connDevice.setUseYn(useYn);
		connDevice.setUpdId(systemId);
		connDevice.setUpdDt(now);

		return commonDao_oracle.update("Device.updateUseYn", connDevice);
	}	
}
