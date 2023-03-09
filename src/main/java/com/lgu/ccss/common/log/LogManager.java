package com.lgu.ccss.common.log;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.lgu.ccss.common.constant.CCSSConst;

@Component
public class LogManager {
	
	@Autowired
	ApiLogServiceImpl apiLogService;
	
	@Autowired
	MgrApiLogServiceImpl mgrApiLogService;
	
	@Autowired
	PndManagerLogServiceImpl pndManagerLogService;
	
	@Autowired
	PhoneManagerLogServiceImpl phoneManagerLogService;
	
	@Autowired
	MqttLogServiceImpl mqttLogService;
	
	@Autowired
	DatagiftLogServiceImpl datagiftLogService;
	
	private Map<String, Object> map = new HashMap<String, Object>();
	
	public LogService getLogService(String key) {
		if (map.size() == 0) {
			initLogService();
		}
				
		return (LogService) map.get(key);
	}
	
	private void initLogService() {
		map.put(CCSSConst.PREFIX_API, apiLogService);
		map.put(CCSSConst.PREFIX_BMAPI, apiLogService);
		map.put(CCSSConst.PREFIX_MGRAPI, mgrApiLogService);
		map.put(CCSSConst.PREFIX_MYPAGE_PND, pndManagerLogService);
		map.put(CCSSConst.PREFIX_MYPAGE_EV, pndManagerLogService);
		map.put(CCSSConst.PREFIX_MYPAGE_PHONE, phoneManagerLogService);
		map.put(CCSSConst.PREFIX_PUSH, mqttLogService);
		map.put(CCSSConst.PREFIX_DATAGIFT, datagiftLogService);
	}
}
