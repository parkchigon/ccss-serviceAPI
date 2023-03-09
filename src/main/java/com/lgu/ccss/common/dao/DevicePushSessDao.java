package com.lgu.ccss.common.dao;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.lgu.ccss.common.model.DevicePushSessVO;

import devonframe.dataaccess.CommonDao;

@Component
public class DevicePushSessDao {
	
	@Resource(name = "commonDao_altibase")
	private CommonDao commonDao_altibase;
	
	public int insertDevicePushSess(DevicePushSessVO devicePushSess) {

		if (devicePushSess == null || devicePushSess.getDevicePushSessionId() == null) {
			throw new IllegalArgumentException();
		}
		
		return commonDao_altibase.insert("DevicePushSession.insertDevicePushSession", devicePushSess);
	}
	
	public DevicePushSessVO selectDevicePushSess(String sessionId) {

		if (sessionId == null) {
			throw new IllegalArgumentException();
		}
		
		DevicePushSessVO devicePushSess = new DevicePushSessVO();
		devicePushSess.setDevicePushSessionId(sessionId);

		return commonDao_altibase.select("DevicePushSession.selectDevicePushSession", devicePushSess);
	}

	public void deleteDevicePushSessByID(String sessionId) {

		if (sessionId == null) {
			throw new IllegalArgumentException();
		}
		
		DevicePushSessVO devicePushSess = new DevicePushSessVO();
		devicePushSess.setDevicePushSessionId(sessionId);
		
		commonDao_altibase.delete("DevicePushSession.deleteDevicePushSessByID", devicePushSess);
	}
	
	public void deleteDevicePushSessByCTN(String deviceCtn) {

		if (deviceCtn == null) {
			throw new IllegalArgumentException();
		}
		
		DevicePushSessVO devicePushSess = new DevicePushSessVO();
		devicePushSess.setDeviceCtn(deviceCtn);
		
		commonDao_altibase.delete("DevicePushSession.deleteDevicePushSessByCTN", devicePushSess);
	}
}
