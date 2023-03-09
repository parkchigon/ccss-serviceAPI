package com.lgu.common.aisv.auth;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.apache.commons.httpclient.HttpStatus;
import org.apache.http.protocol.HTTP;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.lgu.ccss.common.model.RequestJSON;
import com.lgu.ccss.common.util.CCSSUtil;
import com.lgu.common.aisv.auth.model.AisvAuthRequestCommonJSON;
import com.lgu.common.aisv.auth.model.AisvAuthRequestJSON;
import com.lgu.common.aisv.auth.model.AisvAuthResponseJSON;
import com.lgu.common.rest.RestAgent;
import com.lgu.common.rest.RestRequestData;
import com.lgu.common.rest.RestResultData;
import com.lgu.common.util.ExceptionUtil;

@Component
public class AisvAuthAgent {
	private static final Logger logger = LoggerFactory.getLogger(AisvAuthAgent.class);
	private static final Gson gson = new Gson();

	@Value("#{config['cek.aisv.auth.url']}")
	private String aisvAuthUrl;

	@Value("#{config['cek.aisv.auth.connection.timeout']}")
	private String cekAisvAuthConnTimeout;

	@Value("#{config['cek.aisv.auth.timeout']}")
	private String cekAisvAuthTimeout;

	@Autowired
	private RestAgent restAgent;

	public AisvAuthResponseJSON getNidInfo(RequestJSON req, String appType, String carOem) {
		// set request url
		String url = aisvAuthUrl + AisvAuthConst.URL_GET_NID_INFO;
		RestRequestData reqData = new RestRequestData(url);

		reqData.setHeader(HTTP.CONTENT_TYPE, AisvAuthConst.HD_VALUE_CONTENTTYPE_JSON_UTF8);

		AisvAuthRequestCommonJSON common = req.getCommon().getAisvAuthRequestCommonJSON();

		// TRX 조합
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		String time = sdf.format(cal.getTime());
		String trxId = "TR_" + time + "000_" + AisvAuthConst.COMMON_SERVICE_TYPE + "_" + CCSSUtil.getUuid();

		// 디바이스 타입 AM/BM 다름
		String deviceType;
		if (appType.equals(AisvAuthConst.DEVICE_AM_APP_TYPE)) {
			deviceType = AisvAuthConst.COMMON_AM_DEV_TYPE;
		} else {
			deviceType = AisvAuthConst.COMMON_BM_DEV_TYPE;
		}

		// 접속 모듈 정보
		String deviceInfo;
		if (carOem.equals(AisvAuthConst.DEVICE_BM_APP_INFO)) {
			deviceInfo = AisvAuthConst.COMMON_BM_DEV_INFO;
		} else {
			deviceInfo = AisvAuthConst.COMMON_AM_DEV_INFO;
		}

		AisvAuthRequestJSON reqJson = new AisvAuthRequestJSON();
		reqJson.getCommon().setTrxId(trxId);
		reqJson.getCommon().setServiceType(AisvAuthConst.COMMON_SERVICE_TYPE);
		reqJson.getCommon().setDevType(deviceType);
		reqJson.getCommon().setDevInfo(deviceInfo);
		reqJson.getCommon().setOsInfo(common.getOsInfo());
		reqJson.getCommon().setNwInfo(common.getNwInfo());
		reqJson.getCommon().setDeviceModel(common.getDeviceModel());
		reqJson.getCommon().setCarrierType(common.getCarrierType());
		reqJson.getCommon().setClientIp(common.getClientIp());

		String type = (String) req.getParam().get(AisvAuthConst.BODY_NAME_TYPE);
		String nid = (String) req.getParam().get(AisvAuthConst.BODY_NAME_NID);

		reqJson.getData().setType(type);
		reqJson.getData().setNaverUserId(nid);

		reqData.setJson(gson.toJson(reqJson));

		// set trace info
		setTraceInfo(reqData, common.getCtn());

		// set params
		reqData.setConnectionTimeout(Integer.parseInt(cekAisvAuthConnTimeout));
		reqData.setTimeout(Integer.parseInt(cekAisvAuthTimeout));

		return request(reqData, common.getCtn());
	}

	public AisvAuthResponseJSON getNidInfo2(RequestJSON req, String appType, String carOem) {
		// set request url
		String url = aisvAuthUrl + AisvAuthConst.URL_GET_NID_INFO;
		RestRequestData reqData = new RestRequestData(url);

		reqData.setHeader(HTTP.CONTENT_TYPE, AisvAuthConst.HD_VALUE_CONTENTTYPE_JSON_UTF8);

		AisvAuthRequestCommonJSON common = req.getCommon().getAisvAuthRequestCommonJSON();

		// TRX 조합
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		String time = sdf.format(cal.getTime());
		String trxId = "TR_" + time + "000_" + AisvAuthConst.COMMON_SERVICE_TYPE + "_" + CCSSUtil.getUuid();

		// 디바이스 타입 AM/BM 다름
		String deviceType;
		if (appType.equals(AisvAuthConst.DEVICE_AM_APP_TYPE)) {
			deviceType = AisvAuthConst.COMMON_AM_DEV_TYPE;
		} else {
			deviceType = AisvAuthConst.COMMON_BM_DEV_TYPE;
		}

		// 접속 모듈 정보
		String deviceInfo;
		if (carOem.equals(AisvAuthConst.DEVICE_BM_APP_INFO)) {
			deviceInfo = AisvAuthConst.COMMON_BM_DEV_INFO;
		} else {
			deviceInfo = AisvAuthConst.COMMON_AM_DEV_INFO;
		}

		AisvAuthRequestJSON reqJson = new AisvAuthRequestJSON();
		reqJson.getCommon().setTrxId(trxId);
		reqJson.getCommon().setServiceType(AisvAuthConst.COMMON_SERVICE_TYPE);
		reqJson.getCommon().setDevType(deviceType);
		reqJson.getCommon().setDevInfo(deviceInfo);
		reqJson.getCommon().setOsInfo(common.getOsInfo());
		reqJson.getCommon().setNwInfo(common.getNwInfo());
		reqJson.getCommon().setDeviceModel(common.getDeviceModel());
		reqJson.getCommon().setCarrierType(common.getCarrierType());
		reqJson.getCommon().setClientIp(common.getClientIp());

		String nid = (String) req.getParam().get(AisvAuthConst.BODY_NAME_NID);

		reqJson.getData().setType("ID");
		reqJson.getData().setNaverUserId(nid);

		reqData.setJson(gson.toJson(reqJson));

		// set trace info
		setTraceInfo(reqData, common.getCtn());

		// set params
		reqData.setConnectionTimeout(Integer.parseInt(cekAisvAuthConnTimeout));
		reqData.setTimeout(Integer.parseInt(cekAisvAuthTimeout));

		return request(reqData, common.getCtn());
	}

	public AisvAuthResponseJSON tempIdRegist(RequestJSON req, String appType, String carOem) {
		// set request url
		String url = aisvAuthUrl + AisvAuthConst.URL_REGIST_TEMP_ID;
		RestRequestData reqData = new RestRequestData(url);

		reqData.setHeader(HTTP.CONTENT_TYPE, AisvAuthConst.HD_VALUE_CONTENTTYPE_JSON_UTF8);

		AisvAuthRequestCommonJSON common = req.getCommon().getAisvAuthRequestCommonJSON();

		// TRX 조합
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		String time = sdf.format(cal.getTime());
		String trxId = "TR_" + time + "000_" + AisvAuthConst.COMMON_SERVICE_TYPE + "_" + CCSSUtil.getUuid();

		// 디바이스 타입 AM/BM 다름
		String deviceType;
		if (appType.equals(AisvAuthConst.DEVICE_AM_APP_TYPE)) {
			deviceType = AisvAuthConst.COMMON_AM_DEV_TYPE;
		} else {
			deviceType = AisvAuthConst.COMMON_BM_DEV_TYPE;
		}

		// 접속 모듈 정보
		String deviceInfo;
		if (carOem.equals(AisvAuthConst.DEVICE_BM_APP_INFO)) {
			deviceInfo = AisvAuthConst.COMMON_BM_DEV_INFO;
		} else {
			deviceInfo = AisvAuthConst.COMMON_AM_DEV_INFO;
		}

		AisvAuthRequestJSON reqJson = new AisvAuthRequestJSON();
		reqJson.getCommon().setTrxId(trxId);
		reqJson.getCommon().setServiceType(AisvAuthConst.COMMON_SERVICE_TYPE);
		reqJson.getCommon().setDevType(deviceType);
		reqJson.getCommon().setDevInfo(deviceInfo);
		reqJson.getCommon().setOsInfo(common.getOsInfo());
		reqJson.getCommon().setNwInfo(common.getNwInfo());
		reqJson.getCommon().setDeviceModel(common.getDeviceModel());
		reqJson.getCommon().setCarrierType(common.getCarrierType());
		reqJson.getCommon().setClientIp(common.getClientIp());

		String svcCode = (String) req.getParam().get(AisvAuthConst.BODY_SVC_CODE);
		String nid = (String) req.getParam().get(AisvAuthConst.BODY_NAME_NID);
		String userID = (String) req.getParam().get(AisvAuthConst.BODY_USER_ID);
		String oneIdKey = (String) req.getParam().get(AisvAuthConst.BODY_ONE_ID_KEY);
		String ssoKey = (String) req.getParam().get(AisvAuthConst.BODY_SSO_KEY);
		String tempIdYn = (String) req.getParam().get(AisvAuthConst.BODY_TEMP_ID_YN);
		String ctn = (String) req.getParam().get(AisvAuthConst.BODY_CTN);

		reqJson.getData().setSvcCode(svcCode);
		reqJson.getData().setAiTempIdYn("Y");
		reqJson.getData().setUserId(userID);
		reqJson.getData().setNaverUserId(nid);
		reqJson.getData().setOneIdKey(oneIdKey);
		reqJson.getData().setSsoKey(ssoKey);
		reqJson.getData().setTempIdYn(tempIdYn);
		reqJson.getData().setCtn(ctn);

		reqData.setJson(gson.toJson(reqJson));

		// set trace info
		setTraceInfo(reqData, common.getCtn());

		// set params
		reqData.setConnectionTimeout(Integer.parseInt(cekAisvAuthConnTimeout));
		reqData.setTimeout(Integer.parseInt(cekAisvAuthTimeout));

		return request(reqData, common.getCtn());
	}

	public AisvAuthResponseJSON tempIdRegist2(RequestJSON req, String appType, String carOem) {
		// set request url
		String url = aisvAuthUrl + AisvAuthConst.URL_REGIST_TEMP_ID;
		RestRequestData reqData = new RestRequestData(url);

		reqData.setHeader(HTTP.CONTENT_TYPE, AisvAuthConst.HD_VALUE_CONTENTTYPE_JSON_UTF8);

		AisvAuthRequestCommonJSON common = req.getCommon().getAisvAuthRequestCommonJSON();

		// TRX 조합
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		String time = sdf.format(cal.getTime());
		String trxId = "TR_" + time + "000_" + AisvAuthConst.COMMON_SERVICE_TYPE + "_" + CCSSUtil.getUuid();

		// 디바이스 타입 AM/BM 다름
		String deviceType;
		if (appType.equals(AisvAuthConst.DEVICE_AM_APP_TYPE)) {
			deviceType = AisvAuthConst.COMMON_AM_DEV_TYPE;
		} else {
			deviceType = AisvAuthConst.COMMON_BM_DEV_TYPE;
		}

		// 접속 모듈 정보
		String deviceInfo;
		if (carOem.equals(AisvAuthConst.DEVICE_BM_APP_INFO)) {
			deviceInfo = AisvAuthConst.COMMON_BM_DEV_INFO;
		} else {
			deviceInfo = AisvAuthConst.COMMON_AM_DEV_INFO;
		}

		AisvAuthRequestJSON reqJson = new AisvAuthRequestJSON();
		reqJson.getCommon().setTrxId(trxId);
		reqJson.getCommon().setServiceType(AisvAuthConst.COMMON_SERVICE_TYPE);
		reqJson.getCommon().setDevType(deviceType);
		reqJson.getCommon().setDevInfo(deviceInfo);
		reqJson.getCommon().setOsInfo(common.getOsInfo());
		reqJson.getCommon().setNwInfo(common.getNwInfo());
		reqJson.getCommon().setDeviceModel(common.getDeviceModel());
		reqJson.getCommon().setCarrierType(common.getCarrierType());
		reqJson.getCommon().setClientIp(common.getClientIp());

		String svcCode = (String) req.getParam().get("authParameter6");
		String nid = (String) req.getParam().get("nid");
		String userID = (String) req.getParam().get("authParameter1");
		String oneIdKey = (String) req.getParam().get("authParameter2");
		String ssoKey = (String) req.getParam().get("authParameter3");
		String tempIdYn = (String) req.getParam().get("Y");
		String ctn = (String) req.getParam().get("authParameter8");

		reqJson.getData().setSvcCode(svcCode);
		reqJson.getData().setAiTempIdYn("Y");
		reqJson.getData().setUserId(userID);
		reqJson.getData().setNaverUserId(nid);
		reqJson.getData().setOneIdKey(oneIdKey);
		reqJson.getData().setSsoKey(ssoKey);
		reqJson.getData().setTempIdYn(tempIdYn);
		reqJson.getData().setCtn(ctn);

		reqData.setJson(gson.toJson(reqJson));

		// set trace info
		setTraceInfo(reqData, common.getCtn());

		// set params
		reqData.setConnectionTimeout(Integer.parseInt(cekAisvAuthConnTimeout));
		reqData.setTimeout(Integer.parseInt(cekAisvAuthTimeout));

		return request(reqData, common.getCtn());
	}

	private AisvAuthResponseJSON request(RestRequestData reqData, String mgrAppCtn) {
		RestResultData resultData = restAgent.request(reqData);
		if (resultData == null) {
			logger.error("failed to request AISV Auth Server. mgrAppCtn({})", mgrAppCtn);
			return null;
		}

		if (resultData.getStatusCode() != HttpStatus.SC_OK) {
			logger.error("failed to request AISV Auth Server. mgrAppCtn({}) statusCode({})", mgrAppCtn,
					resultData.getStatusCode());
			return null;
		}

		AisvAuthResponseJSON aisvPlatformResp = new AisvAuthResponseJSON();

		try {
			aisvPlatformResp = gson.fromJson(resultData.getJson(), AisvAuthResponseJSON.class);
		} catch (JsonSyntaxException e) {
			logger.error("JSON({}) Exception({})", resultData.getJson(), ExceptionUtil.getPrintStackTrace(e));
			return null;
		}

		return aisvPlatformResp;
	}

	private void setTraceInfo(RestRequestData reqData, String ctn) {
		reqData.setTraceId(ctn);
		reqData.setSource(AisvAuthConst.TRACE_SOURCE);
		reqData.setTarget(AisvAuthConst.TRACE_TARGET);
	}

}
