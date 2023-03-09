package com.lgu.ccss.common.model.mqtt;


import java.util.HashMap;


public class MqttMessageContentVO {

	private HashMap<String,Object> content;
	
	public MqttMessageContentVO(){
		content = new HashMap<String,Object>();
		
	}

	public HashMap<String, Object> getContent() {
		return content;
	}

	public void setContent(HashMap<String, Object> content) {
		this.content = content;
	}
}
