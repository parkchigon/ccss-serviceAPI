package com.lgu.common.ai.coif;

import org.apache.commons.httpclient.HttpStatus;
import org.apache.http.protocol.HTTP;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.lgu.common.ai.coif.model.COIFRequestBodyJSON;
import com.lgu.common.ai.coif.model.COIFRequestCommonLogDataJSON;
import com.lgu.common.ai.coif.model.COIFRequestJSON;
import com.lgu.common.ai.coif.model.COIFResponseJSON;
import com.lgu.common.rest.RestAgent;
import com.lgu.common.rest.RestRequestData;
import com.lgu.common.rest.RestResultData;
import com.lgu.common.util.ExceptionUtil;
import com.lgu.common.util.RandomKeyMakerUtil;

@Component
public class COIFAgent {

	private static final Logger logger = LoggerFactory.getLogger(COIFAgent.class);
	private static final Gson gson = new Gson();

	@Value("#{config['ai.svrKey']}")
	private String svrKey;

	@Value("#{config['ai.weather.url']}")
	private String aiWeatherUrl;

	@Value("#{config['ai.weather.connection.timeout']}")
	private String aiWeatherConnTimeout;

	@Value("#{config['ai.weather.timeout']}")
	private String aiWeatherTimeout;

	@Autowired
	private RestAgent restAgent;

	public COIFResponseJSON requestWeather(String deviceToken, String deviceCtn, COIFRequestCommonLogDataJSON logData,
			COIFRequestBodyJSON body) {

		// set request uri
		String url = aiWeatherUrl + COIFConst.URL_COIF_WEATHER;
		RestRequestData reqData = new RestRequestData(url);

		// set headers
		reqData.setHeader(COIFConst.HD_NAME_SVRKEY, svrKey);
		reqData.setHeader(COIFConst.HD_NAME_CUSTOMERID, deviceCtn);
		reqData.setHeader(COIFConst.HD_NAME_DEVICETOKEN, deviceToken);
		String randomStr = RandomKeyMakerUtil.generate(deviceCtn);
		reqData.setHeader(COIFConst.HD_NAME_TRANSACTIONID, randomStr);
		reqData.setHeader(COIFConst.HD_NAME_MESSAGEID, randomStr);
		reqData.setHeader(HTTP.CONTENT_TYPE, COIFConst.HD_VALUE_CONTENTTYPE_JSON_UTF8);

		// set body
		COIFRequestJSON reqJson = new COIFRequestJSON();
		reqJson.getCommon().setServiceType(logData.getSvcType());
		reqJson.getCommon().setDeviceLanguage("ko_KR");
		reqJson.getCommon().setTriggerType("");
		reqJson.getCommon().setLogData(logData);

		reqJson.setBody(body);

		reqData.setJson(gson.toJson(reqJson));

		// set trace info
		reqData.setTraceId(deviceCtn);
		reqData.setSource(COIFConst.TRACE_SOURCE);
		reqData.setTarget(COIFConst.TRACE_TARGET);

		// set params
		reqData.setConnectionTimeout(Integer.parseInt(aiWeatherConnTimeout));
		reqData.setTimeout(Integer.parseInt(aiWeatherTimeout));
		
		RestResultData resultData = restAgent.request(reqData);
		if (resultData == null) {
			logger.error("failed to request COIF Server. deviceCtn({})", deviceCtn);
			return null;
		}

		if (resultData.getStatusCode() != HttpStatus.SC_OK) {
			logger.error("failed to request COIF Server. deviceCtn({}) statusCode({})", deviceCtn,
					resultData.getStatusCode());
			return null;
		}

		COIFResponseJSON coifResJson = null;
		try {
			coifResJson = gson.fromJson(resultData.getJson(), COIFResponseJSON.class);

		} catch (JsonSyntaxException e) {
			logger.error("deviceCtn({}) JSON({}) Exception({})", deviceCtn, resultData.getJson(),
					ExceptionUtil.getPrintStackTrace(e));
		}

		return coifResJson;
	}
}
