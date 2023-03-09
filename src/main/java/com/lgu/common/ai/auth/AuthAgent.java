package com.lgu.common.ai.auth;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.http.protocol.HTTP;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.lgu.common.ai.auth.model.AuthRequestBodyJSON;
import com.lgu.common.ai.auth.model.AuthRequestCommonJSON;
import com.lgu.common.ai.auth.model.AuthRequestJSON;
import com.lgu.common.ai.auth.model.AuthResponseJSON;
import com.lgu.common.rest.RestAgent;
import com.lgu.common.rest.RestRequestData;
import com.lgu.common.rest.RestResultData;
import com.lgu.common.util.ExceptionUtil;

@Component
public class AuthAgent {
	private static final Logger logger = LoggerFactory.getLogger(AuthAgent.class);
	private static final Gson gson = new Gson();

	@Value("#{config['ai.auth.url']}")
	private String aiAuthUrl;

	@Value("#{config['ai.svrKey']}")
	private String svrKey;

	@Value("#{config['ai.auth.connection.timeout']}")
	private String aiAuthConnTimeout;

	@Value("#{config['ai.auth.timeout']}")
	private String aiAuthTimeout;

	@Autowired
	private RestAgent restAgent;

	public AuthResponseJSON createDeviceToken(String deviceCtn, String deviceSerial, AuthRequestCommonJSON common) {

		// set request url
		String url = aiAuthUrl + AuthConst.URL_CREATE_DEVICE_TOKEN;
		RestRequestData reqData = new RestRequestData(url);

		// set headers
		reqData.setHeader(AuthConst.HD_NAME_SVRKEY, svrKey);
		reqData.setHeader(AuthConst.HD_NAME_CUSTOMID, deviceCtn);
		reqData.setHeader(HTTP.CONTENT_TYPE, AuthConst.HD_VALUE_CONTENTTYPE_JSON_UTF8);

		// set body
		AuthRequestJSON reqJson = new AuthRequestJSON();
		reqJson.getCommon().setClientIp(common.getClientIp());
		reqJson.getCommon().setDevInfo(common.getDevInfo());
		reqJson.getCommon().setOsInfo(common.getOsInfo());
		reqJson.getCommon().setNwInfo(common.getNwInfo());
		reqJson.getCommon().setDevModel(common.getDevModel());
		reqJson.getCommon().setCarrierType(common.getCarrierType());
		reqJson.getCommon().setServiceType(common.getServiceType());
		
		reqJson.getBody().setIdTypeCd(AuthConst.JSON_VALUE_IDTYPECD);
		reqJson.getBody().setDeviceSN(deviceSerial);

		reqData.setJson(gson.toJson(reqJson));

		// set trace info
		setTraceInfo(reqData, deviceCtn);

		// set params
		reqData.setConnectionTimeout(Integer.parseInt(aiAuthConnTimeout));
		reqData.setTimeout(Integer.parseInt(aiAuthTimeout));

		return request(reqData, deviceCtn);
	}

	public AuthResponseJSON createPlatformToken(String deviceCtn, String deviceSerial, String deviceToken,
			AuthRequestCommonJSON common) {

		// set request url
		String url = aiAuthUrl + AuthConst.URL_CREATE_PLATFORM_TOKEN;
		RestRequestData reqData = new RestRequestData(url);

		// set headers
		reqData.setHeader(AuthConst.HD_NAME_SVRKEY, svrKey);
		reqData.setHeader(AuthConst.HD_NAME_CUSTOMID, deviceCtn);
		reqData.setHeader(AuthConst.HD_NAME_DEVICETOKEN, deviceToken);
		reqData.setHeader(HTTP.CONTENT_TYPE, AuthConst.HD_VALUE_CONTENTTYPE_JSON_UTF8);

		// set body
		AuthRequestJSON reqJson = new AuthRequestJSON();
		reqJson.getCommon().setClientIp(common.getClientIp());
		reqJson.getCommon().setDevInfo(common.getDevInfo());
		reqJson.getCommon().setOsInfo(common.getOsInfo());
		reqJson.getCommon().setNwInfo(common.getNwInfo());
		reqJson.getCommon().setDevModel(common.getDevModel());
		reqJson.getCommon().setCarrierType(common.getCarrierType());
		reqJson.getCommon().setServiceType(common.getServiceType());
		reqJson.getCommon().setDeviceType(common.getDeviceType());
		reqJson.getCommon().setIdTypeCd(AuthConst.JSON_VALUE_IDTYPECD);
		reqJson.getCommon().setDeviceSN(deviceSerial);
		
		reqData.setJson(gson.toJson(reqJson));

		// set trace info
		setTraceInfo(reqData, deviceCtn);

		// set params
		reqData.setConnectionTimeout(Integer.parseInt(aiAuthConnTimeout));
		reqData.setTimeout(Integer.parseInt(aiAuthTimeout));

		return request(reqData, deviceCtn);
	}

	public AuthResponseJSON saveSvcAuthInfo(String deviceCtn, String deviceToken, AuthRequestCommonJSON common,
			Map<String, String> authInfo) {
		// set request url
		String url = aiAuthUrl + AuthConst.URL_SAVE_SVC_AUTH_INFO;
		RestRequestData reqData = new RestRequestData(url);

		// set headers
		reqData.setHeader(AuthConst.HD_NAME_SVRKEY, svrKey);
		reqData.setHeader(AuthConst.HD_NAME_DEVICETOKEN, deviceToken);
		reqData.setHeader(HTTP.CONTENT_TYPE, AuthConst.HD_VALUE_CONTENTTYPE_JSON_UTF8);

		// set body
		AuthRequestJSON reqJson = new AuthRequestJSON();
		reqJson.getCommon().setClientIp(common.getClientIp());
		reqJson.getCommon().setDevInfo(common.getDevInfo());
		reqJson.getCommon().setOsInfo(common.getOsInfo());
		reqJson.getCommon().setNwInfo(common.getNwInfo());
		reqJson.getCommon().setDevModel(common.getDevModel());
		reqJson.getCommon().setCarrierType(common.getCarrierType());

		reqJson.getCommon().setCustomId(deviceCtn);
		reqJson.getCommon().setContentSvcCd(authInfo.get(AuthRequestBodyJSON.AUTH_INFO_CONT_SVC_CD));

		reqJson.getBody().setAuthInfo(authInfo);

		reqData.setJson(gson.toJson(reqJson));

		// set trace info
		setTraceInfo(reqData, deviceCtn);

		// set params
		reqData.setConnectionTimeout(Integer.parseInt(aiAuthConnTimeout));
		reqData.setTimeout(Integer.parseInt(aiAuthTimeout));

		return request(reqData, deviceCtn);
	}

	public AuthResponseJSON logoutSvc(String deviceCtn, String deviceToken, AuthRequestCommonJSON common,
			Map<String, String> authInfo) {
		// set request url
		String url = aiAuthUrl + AuthConst.URL_LOGOUT_SVC;
		RestRequestData reqData = new RestRequestData(url);

		// set headers
		reqData.setHeader(AuthConst.HD_NAME_SVRKEY, svrKey);
		reqData.setHeader(AuthConst.HD_NAME_DEVICETOKEN, deviceToken);
		reqData.setHeader(HTTP.CONTENT_TYPE, AuthConst.HD_VALUE_CONTENTTYPE_JSON_UTF8);

		// set body
		AuthRequestJSON reqJson = new AuthRequestJSON();
		reqJson.getCommon().setClientIp(common.getClientIp());
		reqJson.getCommon().setDevInfo(common.getDevInfo());
		reqJson.getCommon().setOsInfo(common.getOsInfo());
		reqJson.getCommon().setNwInfo(common.getNwInfo());
		reqJson.getCommon().setDevModel(common.getDevModel());
		reqJson.getCommon().setCarrierType(common.getCarrierType());

		reqJson.getCommon().setCustomId(deviceCtn);
		reqJson.getCommon().setContentSvcCode(authInfo.get(AuthRequestBodyJSON.AUTH_INFO_CONT_SVC_CD));

		reqJson.getBody().setAuthInfo(authInfo);

		reqData.setJson(gson.toJson(reqJson));

		// set trace info
		setTraceInfo(reqData, deviceCtn);

		// set params
		reqData.setConnectionTimeout(Integer.parseInt(aiAuthConnTimeout));
		reqData.setTimeout(Integer.parseInt(aiAuthTimeout));

		return request(reqData, deviceCtn);
	}

	private void setTraceInfo(RestRequestData reqData, String deviceCtn) {
		reqData.setTraceId(deviceCtn);
		reqData.setSource(AuthConst.TRACE_SOURCE);
		reqData.setTarget(AuthConst.TRACE_TARGET);
	}

	private AuthResponseJSON request(RestRequestData reqData, String deviceCtn) {
			
		RestResultData resultData = restAgent.request(reqData);
		if (resultData == null) {
			logger.error("failed to request Auth Server. deviceCtn({})", deviceCtn);
			return null;
		}

		if (resultData.getStatusCode() != HttpStatus.SC_OK) {
			logger.error("failed to request Auth Server. deviceCtn({}) statusCode({})", deviceCtn,
					resultData.getStatusCode());
			return null;
		}

		AuthResponseJSON aiPlatformResp = new AuthResponseJSON();
		try {
			aiPlatformResp = gson.fromJson(resultData.getJson(), AuthResponseJSON.class);

		} catch (JsonSyntaxException e) {
			logger.error("JSON({}) Exception({})", resultData.getJson(), ExceptionUtil.getPrintStackTrace(e));
			return null;
		}

		if (aiPlatformResp == null
				|| !aiPlatformResp.getCommon().getResultCode().equals(AuthErrorCode.RC_20000000.getCode())) {
			logger.error("failed to request AIAuthInfo data. AuthResponseJSON({})", aiPlatformResp);
			return null;
		}
		
		return aiPlatformResp;
	}
}
