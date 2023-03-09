package com.lgu.ccss.component;

import static org.junit.Assert.*;

import org.junit.Ignore;
import org.junit.Test;
import org.springframework.http.HttpHeaders;

import com.lgu.ccss.api.weather.model.RequestWeatherJSON;
import com.lgu.ccss.common.model.AIAuthInfoVO;
import com.lgu.ccss.common.model.RequestCommonLogDataJSON;
import com.lgu.common.ai.coif.COIFAgent;
import com.lgu.common.ai.coif.model.COIFRequestBodyJSON;
import com.lgu.common.ai.coif.model.COIFRequestCommonLogDataJSON;
import com.lgu.common.ai.coif.model.COIFResponseJSON;
import com.lgu.common.ai.coif.model.WeatherRequestBodySlotJSON;

public class AIWeatherAgentTest {

	@SuppressWarnings("unused")
	@Ignore
	@Test
	public void testWeatherSearchIF() {
		COIFAgent weatherIf = new COIFAgent();
		RequestWeatherJSON weatherSearchVo = new RequestWeatherJSON();
		COIFResponseJSON weatherIfVo = new COIFResponseJSON();
		AIAuthInfoVO aiAuthInfoVo = new AIAuthInfoVO();
		String weatherServer = "http://112.172.129.170";
		String weatherPort = "9998";
		String svrKey = "f8d53805934d470b91531cd08cde3bc8";

		weatherSearchVo.getCommon().getLogData().setClientIp("123.123.123.123");
		weatherSearchVo.getCommon().getLogData().setDevInfo("PAD");
		weatherSearchVo.getCommon().getLogData().setOsInfo("android_5.5.5");
		weatherSearchVo.getCommon().getLogData().setNwInfo("4G");
		weatherSearchVo.getCommon().getLogData().setSvcName("");
		weatherSearchVo.getCommon().getLogData().setDevModel("LE-E250");
		weatherSearchVo.getCommon().getLogData().setCarrierType("L");
		weatherSearchVo.getCommon().getLogData().setSvcType("SVC_003");
		weatherSearchVo.getCommon().getLogData().setDevType("");
		weatherSearchVo.getCommon().setApiId("SERVICE_LOGIN_AI");
		weatherSearchVo.getCommon().setCcssToken("Temp_ccss_Token");
		weatherSearchVo.getCommon().getDevice().setDeviceType("PND");
		weatherSearchVo.getCommon().getDevice().setAppType("SERVICE_AGENT");
		weatherSearchVo.getCommon().getDevice().setCarOem("");

		// weatherSearchVo.getParam().getBody().setServiceType("SVC_003");
		// weatherSearchVo.getParam().getBody().getCommon().setDeviceSn("NAVI00001");
		// weatherSearchVo.getParam().getBody().getCommon().setDeviceModel("CONNCAR_SS");
		// weatherSearchVo.getParam().getBody().getCommon().setDeviceFwVersion("00.01.112");
		// weatherSearchVo.getParam().getBody().getCommon().setDeviceLatitude("36");
		// weatherSearchVo.getParam().getBody().getCommon().setDeviceLongtitude("127");
		// weatherSearchVo.getParam().getBody().getCommon().setDeviceLanguage("ko_KR");
		//// weatherSearchVo.getParam().getBody().getCommon().setDeviceStatusSummary("");
		// weatherSearchVo.getParam().getBody().getCommon().setCurrentSkill("");
		// weatherSearchVo.getParam().getBody().getCommon().setCurrentIntent("");
		// weatherSearchVo.getParam().getBody().getCommon().setCurrentSlot("");
		// weatherSearchVo.getParam().getBody().getCommon().getDeviceTime().setSeconds("1495160754");
		// weatherSearchVo.getParam().getBody().getCommon().getDeviceTime().setNanos("");
		// weatherSearchVo.getParam().getBody().getCommon().setUtcTimeZone("");
		// weatherSearchVo.getParam().getBody().getCommon().setTriggerType("");
		// weatherSearchVo.getParam().getBody().getCommon().setSchema("Result");
		// weatherSearchVo.getParam().getBody().getCommon().setMethod("nlu");
		weatherSearchVo.getParam().setBody(new COIFRequestBodyJSON());
		weatherSearchVo.getParam().getBody().setSkill("weather");
		weatherSearchVo.getParam().getBody().setIntent("searchWeather");
		weatherSearchVo.getParam().getBody().setSlot(new WeatherRequestBodySlotJSON());
		weatherSearchVo.getParam().getBody().getSlot().setLatitude("36.6372611");
		weatherSearchVo.getParam().getBody().getSlot().setLongtitude("127.483288888");
		weatherSearchVo.getParam().getBody().getSlot().setlAreaNm("");
		weatherSearchVo.getParam().getBody().getSlot().setmAreaNm("");
		weatherSearchVo.getParam().getBody().getSlot().setsAreaNm("");

		aiAuthInfoVo.setDeviceToken("DEVICETOKEN");

		HttpHeaders headers = new HttpHeaders();
		headers.set("transactionId", "transactionId");

		weatherIfVo = weatherIf.requestWeather("DEVICETOKEN", "01022330811",
				makeLogData(weatherSearchVo.getCommon().getLogData()), weatherSearchVo.getParam().getBody());

		assertNotNull("WeatherIf Response is null!", weatherIfVo);
		assertNotNull("Res Body is null", weatherIfVo.getBody());
	}

	private COIFRequestCommonLogDataJSON makeLogData(RequestCommonLogDataJSON logData) {
		COIFRequestCommonLogDataJSON coifLogData = new COIFRequestCommonLogDataJSON();

		coifLogData.setClientIp(logData.getClientIp());
		coifLogData.setDevInfo(logData.getDevInfo());
		coifLogData.setOsInfo(logData.getOsInfo());
		coifLogData.setNwInfo(logData.getNwInfo());
		coifLogData.setSvcName(logData.getSvcName());
		coifLogData.setDevModel(logData.getDevModel());
		coifLogData.setCarrierType(logData.getCarrierType());
		coifLogData.setSvcType(logData.getSvcType());
		coifLogData.setDevType(logData.getDevType());

		return coifLogData;
	}

}
