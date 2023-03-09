package com.lgu.ccss.common.dao;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.lgu.ccss.common.model.DeviceSessVO;

import devonframe.dataaccess.CommonDao;

@Component
public class DeviceSessDao {

	@Resource(name = "commonDao_altibase")
	private CommonDao commonDao_altibase;

	public int insertDeviceSess(DeviceSessVO deviceSess) {

		if (deviceSess == null || deviceSess.getDeviceSessId() == null) {
			throw new IllegalArgumentException();
		}
		
		return commonDao_altibase.insert("DeviceSession.insertDeviceSession", deviceSess);
	}
	
	public DeviceSessVO selectDeviceSess(String sessionId) {

		if (sessionId == null) {
			throw new IllegalArgumentException();
		}
		
		DeviceSessVO deviceSess = new DeviceSessVO();
		deviceSess.setDeviceSessId(sessionId);

		return commonDao_altibase.select("DeviceSession.selectDeviceSession", deviceSess);
	}

	public void updateDeviceSessTime(String sessionId, String expDate, String connIp) {

		if (sessionId == null) {
			throw new IllegalArgumentException();
		}
		
		DeviceSessVO deviceSess = new DeviceSessVO();
		deviceSess.setDeviceSessId(sessionId);
		deviceSess.setDeviceConnIp(connIp);
		deviceSess.setDeviceSessExpDt(expDate);
		
		commonDao_altibase.update("DeviceSession.updateDeviceSessTime", deviceSess);
	}

	public void deleteDeviceSessByID(String sessionId) {

		if (sessionId == null) {
			throw new IllegalArgumentException();
		}
		
		DeviceSessVO deviceSess = new DeviceSessVO();
		deviceSess.setDeviceSessId(sessionId);
		
		commonDao_altibase.delete("DeviceSession.deleteDeviceSessByID", deviceSess);
	}
	
	public void deleteDeviceSessByCTN(String deviceCtn, String deviceSerial) {

		if (deviceCtn == null || deviceSerial == null) {
			throw new IllegalArgumentException();
		}
		
		DeviceSessVO deviceSess = new DeviceSessVO();
		deviceSess.setDeviceCtn(deviceCtn);
		deviceSess.setDeviceSerial(deviceSerial);
		
		commonDao_altibase.delete("DeviceSession.deleteDeviceSessByCTN", deviceSess);
	}
}
