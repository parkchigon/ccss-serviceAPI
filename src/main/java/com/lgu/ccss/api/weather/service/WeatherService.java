package com.lgu.ccss.api.weather.service;

import org.springframework.http.HttpHeaders;

import com.lgu.ccss.api.weather.model.RequestWeatherJSON;
import com.lgu.ccss.common.model.ResponseJSON;


public interface WeatherService {
	public ResponseJSON doService(HttpHeaders headers, RequestWeatherJSON reqJson);
}
