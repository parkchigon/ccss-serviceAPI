package com.lgu.ccss.api.weather.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lgu.ccss.api.weather.model.RequestWeatherJSON;
import com.lgu.ccss.api.weather.service.WeatherService;
import com.lgu.ccss.common.model.ResponseJSON;

@Controller
@RequestMapping(value = {"/api/weather", "/bmapi/weather"})
public class WeatherController {

	@Resource(name = "weatherService")
	private WeatherService weatherService;

	@RequestMapping(value = { "/search", "/searchWeather" }, method = RequestMethod.POST)
	@ResponseBody
	public ResponseJSON searchWeather(HttpSession session, @RequestHeader HttpHeaders headers,
			@RequestBody RequestWeatherJSON reqJson) throws Exception {
		return weatherService.doService(headers, reqJson);
	}

	@RequestMapping(value = { "/lifeindex", "/searchLifeIndex" }, method = RequestMethod.POST)
	@ResponseBody
	public ResponseJSON searchLifeIndex(HttpSession session, @RequestHeader HttpHeaders headers, 
			@RequestBody RequestWeatherJSON reqJson) throws Exception {
		return weatherService.doService(headers, reqJson);
	}
	
	@RequestMapping(value = { "/poi", "/searchPOI" }, method = RequestMethod.POST)
	@ResponseBody
	public ResponseJSON searchPOI(HttpSession session, @RequestHeader HttpHeaders headers, 
			@RequestBody RequestWeatherJSON reqJson) throws Exception {
		return weatherService.doService(headers, reqJson);
	}
}
