package com.lgu.ccss.common.dao.mqtt;


import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.lgu.ccss.common.model.mqtt.MqttMessageVO;

import devonframe.dataaccess.CommonDao;

@Component
public class MqttMessageDao {
	private static final Logger logger = LoggerFactory.getLogger(MqttMessageDao.class);

	@Resource(name = "commonDao_altibase")
	private CommonDao commonDao_altibase;

	public boolean insertTbCarPushQueue(MqttMessageVO mqttMessageVO) {
		
		if (mqttMessageVO == null ) {
			throw new IllegalArgumentException();
		}
		int count = 0;
		try
		{
			count = commonDao_altibase.insert("MqttMessage.insertTbCarPushQueue",mqttMessageVO);
		} catch (Exception ex )
		{
			logger.error("Send Car Push Message Insert. Fail. mqttMessageVO({})", mqttMessageVO.toString(), ex);
		}
		
		return (count > 0 )? true:false;
	}
}
