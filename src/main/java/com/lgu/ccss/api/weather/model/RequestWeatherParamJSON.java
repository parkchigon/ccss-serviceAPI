package com.lgu.ccss.api.weather.model;

import com.lgu.common.ai.coif.model.COIFRequestBodyJSON;

public class RequestWeatherParamJSON {
	private COIFRequestBodyJSON body;
	private Object currentState;
	
	public COIFRequestBodyJSON getBody() {
		return body;
	}
	public void setBody(COIFRequestBodyJSON body) {
		this.body = body;
	}
	public Object getCurrentState() {
		return currentState;
	}
	public void setCurrentState(Object currentState) {
		this.currentState = currentState;
	}
	
	@Override
	public String toString() {
		return "RequestWeatherParamJSON [body=" + body + ", currentState=" + currentState + "]";
	}
}
