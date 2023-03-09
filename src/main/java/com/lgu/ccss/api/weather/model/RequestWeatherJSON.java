package com.lgu.ccss.api.weather.model;

import com.lgu.ccss.common.model.RequestCommonJSON;

public class RequestWeatherJSON {
	private RequestCommonJSON common;
	private RequestWeatherParamJSON param;
	
	public RequestCommonJSON getCommon() {
		return common;
	}
	public void setCommon(RequestCommonJSON common) {
		this.common = common;
	}
	public RequestWeatherParamJSON getParam() {
		return param;
	}
	public void setParam(RequestWeatherParamJSON param) {
		this.param = param;
	}
	
	@Override
	public String toString() {
		return "RequestWeatherJSON [common=" + common + ", param=" + param + "]";
	}
}
