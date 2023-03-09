package com.lgu.common.cekAi.auth;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.http.protocol.HTTP;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.lgu.ccss.common.model.ConnDeviceVO;
import com.lgu.ccss.common.model.RequestJSON;
import com.lgu.common.cekAi.auth.model.CekAuthRequestCommonJSON;
import com.lgu.common.cekAi.auth.model.CekAuthRequestJSON;
import com.lgu.common.cekAi.auth.model.CekAuthResponseJSON;
import com.lgu.common.cekAi.auth.model.addDevice.CekAddDeviceAuthRequestCommonJSON;
import com.lgu.common.cekAi.auth.model.addDevice.CekAddDeviceAuthRequestJSON;
import com.lgu.common.cekAi.auth.model.addDevice.CekAddDeviceInfoVO;
import com.lgu.common.rest.RestAgent;
import com.lgu.common.rest.RestRequestData;
import com.lgu.common.rest.RestResultData;
import com.lgu.common.util.ExceptionUtil;

@Component
public class CekAuthAgent {
	private static final Logger logger = LoggerFactory.getLogger(CekAuthAgent.class);
	private static final Gson gson = new Gson();

	@Value("#{config['cek.ai.auth.url']}")
	private String cekAiAuthUrl;

	@Value("#{config['cek.ai.svrKey']}")
	private String cekSvrKey;

	@Value("#{config['cek.ai.svcType']}")
	private String svcType;

	@Value("#{config['cek.ai.auth.connection.timeout']}")
	private String cekAiAuthConnTimeout;

	@Value("#{config['cek.ai.auth.timeout']}")
	private String cekAiAuthTimeout;
	
	@Value("#{config['cek.proxy.http.proxyHost']}")
	private String cekHttpProxyHost;
	
	@Value("#{config['cek.proxy.http.proxyPort']}")
	private int cekHttpProxyPort;
	
	@Autowired
	private RestAgent restAgent;

	
	//public CekAuthResponseJSON addUser(CekAuthRequestCommonJSON common,RequestJSON req) {
	public CekAuthResponseJSON addUser(RequestJSON req) {
		
		// set request url
		String url = cekAiAuthUrl + CekAuthConst.URL_ADD_USER;
		RestRequestData reqData = new RestRequestData(url);

		// set headers
		reqData.setHeader(CekAuthConst.HD_NAME_SVRKEY, cekSvrKey);
		//reqData.setHeader(CekAuthConst.HD_NAME_CUSTOMID, deviceCtn);
		

		reqData.setHeader(CekAuthConst.HD_TRANSACTION_ID, CekAuthConst.HD_TRANSACTION_ID_PREFIX+UUID.randomUUID().toString().replace("-", ""));
		reqData.setHeader(CekAuthConst.HD_MESSAGE_ID, CekAuthConst.HD_MESSAGE_ID_PREFIX+UUID.randomUUID().toString().replace("-", ""));
		reqData.setHeader(HTTP.CONTENT_TYPE, CekAuthConst.HD_VALUE_CONTENTTYPE_JSON_UTF8);
		
		CekAuthRequestCommonJSON common = req.getCommon().getCekAuthRequestCommon();
		// set body
		CekAuthRequestJSON reqJson = new CekAuthRequestJSON();
		reqJson.getCommon().setClientIp(common.getClientIp());
		reqJson.getCommon().setDevInfo(common.getDevInfo());
		reqJson.getCommon().setOsInfo(common.getOsInfo());
		reqJson.getCommon().setNwInfo(common.getNwInfo());
		reqJson.getCommon().setDevModel(common.getDevModel());
		reqJson.getCommon().setCarrierType(common.getCarrierType());

		String customId = (String) req.getParam().get(CekAuthConst.PARAM_NAME_CUSTOM_ID_KEY_VALUE);
		String oneId = (String) req.getParam().get(CekAuthConst.PARAM_NAME_ONE_ID_KEY_VALUE);
		String ctn = (String) req.getParam().get(CekAuthConst.PARAM_NAME_CTN_KEY_VALUE);
		String nid = (String) req.getParam().get(CekAuthConst.BODY_NAME_NID);
		
		reqJson.getBody().setCustomId(customId);
		reqJson.getBody().setOneId(oneId);
		reqJson.getBody().setCtn(ctn);
		reqJson.getBody().setNid(nid);
		reqJson.getBody().setServiceType(svcType);
		reqJson.getBody().setIdTypeCd(CekAuthConst.JSON_VALUE_IDTYPECD_ONE_ID);
		
		reqData.setJson(gson.toJson(reqJson));

		// set trace info
		setTraceInfo(reqData, common.getCtn());

		// set params
		reqData.setConnectionTimeout(Integer.parseInt(cekAiAuthConnTimeout));
		reqData.setTimeout(Integer.parseInt(cekAiAuthTimeout));

		return request(reqData, common.getCtn());
	}
	
	//public CekAuthResponseJSON addDevice(CekAddDeviceAuthRequestCommonJSON common) {
	public CekAuthResponseJSON addDevice(RequestJSON req,ConnDeviceVO connDeviceVO) {

		// set request url
		String url = cekAiAuthUrl + CekAuthConst.URL_ADD_DEVICE;
		RestRequestData reqData = new RestRequestData(url);

		// set headers
		reqData.setHeader(CekAuthConst.HD_NAME_SVRKEY, cekSvrKey);
		reqData.setHeader(CekAuthConst.HD_TRANSACTION_ID, CekAuthConst.HD_TRANSACTION_ID_PREFIX+UUID.randomUUID().toString().replace("-", ""));
		reqData.setHeader(CekAuthConst.HD_MESSAGE_ID, CekAuthConst.HD_MESSAGE_ID_PREFIX+UUID.randomUUID().toString().replace("-", ""));
		reqData.setHeader(HTTP.CONTENT_TYPE, CekAuthConst.HD_VALUE_CONTENTTYPE_JSON_UTF8);
		
		CekAddDeviceAuthRequestCommonJSON common = req.getCommon().getCekAddDeviceAuthRequestCommon();
		
		// set body
		CekAddDeviceAuthRequestJSON reqJson = new CekAddDeviceAuthRequestJSON();
		reqJson.getCommon().setClientIp(common.getClientIp());
		reqJson.getCommon().setDevInfo(common.getDevInfo());
		reqJson.getCommon().setOsInfo(common.getOsInfo());
		reqJson.getCommon().setNwInfo(common.getNwInfo());
		reqJson.getCommon().setDevModel(common.getDevModel());
		reqJson.getCommon().setCarrierType(common.getCarrierType());
		reqJson.getCommon().setServiceType(common.getServiceType());
	
		
		String customId = (String) req.getParam().get(CekAuthConst.PARAM_NAME_CUSTOM_ID_KEY_VALUE);
		String nid = (String) req.getParam().get(CekAuthConst.BODY_NAME_NID);
		common.setCustomId(customId);
		common.setNid(nid);
		common.setIdTypeCd(CekAuthConst.JSON_VALUE_IDTYPECD_ONE_ID);
		
		
		reqJson.getBody().setCustomId(common.getCustomId());
		reqJson.getBody().setNid(common.getNid());
		reqJson.getBody().setIdTypeCd(CekAuthConst.JSON_VALUE_IDTYPECD_ONE_ID);
		reqJson.getBody().setAuthInfo(common.getAuthInfo());
		reqJson.getBody().setServiceType(svcType);
		
		
		//Device Info
		CekAddDeviceInfoVO cekAddDeviceInfoVO = new CekAddDeviceInfoVO();
		cekAddDeviceInfoVO.setDeviceSN(connDeviceVO.getUiccId());
		cekAddDeviceInfoVO.setDeviceTypeCd(CekAuthConst.JSON_VALUE_DEVICE_TYPE_CD);
		common.setDevice(cekAddDeviceInfoVO);
		
		reqJson.getBody().setDevice(common.getDevice());
		
		reqData.setJson(gson.toJson(reqJson));

		// set trace info
		setTraceInfo(reqData, common.getCtn());

		// set params
		reqData.setConnectionTimeout(Integer.parseInt(cekAiAuthConnTimeout));
		reqData.setTimeout(Integer.parseInt(cekAiAuthTimeout));

		return request(reqData, common.getCtn());
	}
	
	public CekAuthResponseJSON logOutSvc(  RequestJSON req ,String mgrappCtn) {

		// set request url
		String url = cekAiAuthUrl + CekAuthConst.URL_LOGOUT_SVC;
		RestRequestData reqData = new RestRequestData(url);

		CekAuthRequestCommonJSON common = req.getCommon().getCekAuthRequestCommon();
		
		// set headers
		reqData.setHeader(CekAuthConst.HD_NAME_SVRKEY, cekSvrKey);
		
		String customId = (String) req.getParam().get(CekAuthConst.PARAM_NAME_CUSTOM_ID_KEY_VALUE);
		reqData.setHeader(CekAuthConst.HD_NAME_CUSTOMID, customId);
		
		reqData.setHeader(CekAuthConst.HD_TRANSACTION_ID, CekAuthConst.HD_TRANSACTION_ID_PREFIX+UUID.randomUUID().toString().replace("-", ""));
		reqData.setHeader(CekAuthConst.HD_MESSAGE_ID, CekAuthConst.HD_MESSAGE_ID_PREFIX+UUID.randomUUID().toString().replace("-", ""));
		reqData.setHeader(HTTP.CONTENT_TYPE, CekAuthConst.HD_VALUE_CONTENTTYPE_JSON_UTF8);
		
		
		// set body
		CekAuthRequestJSON reqJson = new CekAuthRequestJSON();
		reqJson.getCommon().setClientIp(common.getClientIp());
		reqJson.getCommon().setDevInfo(common.getDevInfo());
		reqJson.getCommon().setOsInfo(common.getOsInfo());
		reqJson.getCommon().setNwInfo(common.getNwInfo());
		reqJson.getCommon().setDevModel(common.getDevModel());
		reqJson.getCommon().setCarrierType(common.getCarrierType());
		
		reqJson.getBody().setNid((String) req.getParam().get(CekAuthConst.BODY_NAME_NID));
		reqJson.getBody().setServiceType(svcType);
		
		HashMap<String,String> authInfo =new HashMap<String,String>();  //"contentSvcCode":"iot"
		authInfo.put(CekAuthConst.BODY_NAME_CONTENT_SVC_CODE, CekAuthConst.SERVICE_CODE_IOT);
		
		reqJson.getBody().setAuthInfo(authInfo);
		
		reqData.setJson(gson.toJson(reqJson));

		// set trace info
		setTraceInfo(reqData, mgrappCtn);

		// set params
		reqData.setConnectionTimeout(Integer.parseInt(cekAiAuthConnTimeout));
		reqData.setTimeout(Integer.parseInt(cekAiAuthTimeout));

		return request(reqData, common.getCtn());
	}
	
	
	public CekAuthResponseJSON resetUserInfo(RequestJSON req,String mgrappCtn) {
		
		// set request url
		String url = cekAiAuthUrl + CekAuthConst.URL_RESET_USER_INFO;
		RestRequestData reqData = new RestRequestData(url);
		
		CekAuthRequestCommonJSON common = req.getCommon().getCekAuthRequestCommon();
		// set headers
		reqData.setHeader(CekAuthConst.HD_NAME_SVRKEY, cekSvrKey);
		reqData.setHeader(CekAuthConst.HD_TRANSACTION_ID, CekAuthConst.HD_TRANSACTION_ID_PREFIX+UUID.randomUUID().toString().replace("-", ""));
		reqData.setHeader(CekAuthConst.HD_MESSAGE_ID, CekAuthConst.HD_MESSAGE_ID_PREFIX+UUID.randomUUID().toString().replace("-", ""));
		reqData.setHeader(HTTP.CONTENT_TYPE, CekAuthConst.HD_VALUE_CONTENTTYPE_JSON_UTF8);

		// set body
		CekAuthRequestJSON reqJson = new CekAuthRequestJSON();
		reqJson.getCommon().setClientIp(common.getClientIp());
		reqJson.getCommon().setDevInfo(common.getDevInfo());
		reqJson.getCommon().setOsInfo(common.getOsInfo());
		reqJson.getCommon().setNwInfo(common.getNwInfo());
		reqJson.getCommon().setDevModel(common.getDevModel());
		reqJson.getCommon().setCarrierType(common.getCarrierType());
	
		String customId = (String) req.getParam().get(CekAuthConst.PARAM_NAME_CUSTOM_ID_KEY_VALUE);
		reqJson.getBody().setCustomId(customId);
		reqJson.getBody().setNid((String) req.getParam().get(CekAuthConst.BODY_NAME_NID));
		
		reqData.setJson(gson.toJson(reqJson));

		// set trace info
		setTraceInfo(reqData, mgrappCtn);

		// set params
		reqData.setConnectionTimeout(Integer.parseInt(cekAiAuthConnTimeout));
		reqData.setTimeout(Integer.parseInt(cekAiAuthTimeout));

		return request(reqData, common.getCtn());
	}
	
	
	public CekAuthResponseJSON saveSvcAuthInfo(RequestJSON req, String nid, Map<String, String> authInfo,String mgrappCtn) {

		// set request url
		String url = cekAiAuthUrl + CekAuthConst.URL_SAVE_SVC_AUTH_INFO;
		RestRequestData reqData = new RestRequestData(url);
		
		CekAuthRequestCommonJSON common = req.getCommon().getCekAuthRequestCommon();
		
		String customId = (String) req.getParam().get(CekAuthConst.PARAM_NAME_CUSTOM_ID_KEY_VALUE);
		
		// set headers
		reqData.setHeader(CekAuthConst.HD_NAME_SVRKEY, cekSvrKey);		
		reqData.setHeader(CekAuthConst.HD_NAME_CUSTOMID, customId);
		reqData.setHeader(CekAuthConst.HD_TRANSACTION_ID, CekAuthConst.HD_TRANSACTION_ID_PREFIX+UUID.randomUUID().toString().replace("-", ""));
		reqData.setHeader(CekAuthConst.HD_MESSAGE_ID, CekAuthConst.HD_MESSAGE_ID_PREFIX+UUID.randomUUID().toString().replace("-", ""));
		reqData.setHeader(HTTP.CONTENT_TYPE, CekAuthConst.HD_VALUE_CONTENTTYPE_JSON_UTF8);

		// set common
		CekAuthRequestJSON reqJson = new CekAuthRequestJSON();
		reqJson.getCommon().setClientIp(common.getClientIp());
		reqJson.getCommon().setDevInfo(common.getDevInfo());
		reqJson.getCommon().setOsInfo(common.getOsInfo());
		reqJson.getCommon().setNwInfo(common.getNwInfo());
		reqJson.getCommon().setDevModel(common.getDevModel());
		reqJson.getCommon().setCarrierType(common.getCarrierType());
		reqJson.getCommon().setServiceType(common.getServiceType());
		
		// set body
		reqJson.getBody().setServiceType(svcType);
		reqJson.getBody().setNid((String) req.getParam().get(CekAuthConst.BODY_NAME_NID));
		//reqJson.getBody().setAuthInfo(authInfo);
		
		/* 가상 oneId가 있는 경우 교체 */
		Map<String, String> cekAuthInfo = new LinkedHashMap<String, String>();

		for (String key : authInfo.keySet()) {
			cekAuthInfo.put(key, authInfo.get(key));
		}
		cekAuthInfo.put("authParameter1", (String) req.getParam().get("authParameter1"));
		cekAuthInfo.put("authParameter2", (String) req.getParam().get("authParameter2"));

		reqJson.getBody().setAuthInfo(cekAuthInfo);
		
		
		reqData.setJson(gson.toJson(reqJson));

		// set trace info
		setTraceInfo(reqData, mgrappCtn);

		// set params
		reqData.setConnectionTimeout(Integer.parseInt(cekAiAuthConnTimeout));
		reqData.setTimeout(Integer.parseInt(cekAiAuthTimeout));

		return request(reqData, common.getCtn());
	}
	
	
	public CekAuthResponseJSON getAuthorizationCode(RequestJSON req,String mgrappLoginId) {

		// set request url
		String url = cekAiAuthUrl + CekAuthConst.URL_GET_AUTHORIZATION_CODE;
		RestRequestData reqData = new RestRequestData(url);
		
		CekAuthRequestCommonJSON common =  req.getCommon().getCekAuthRequestCommon();
		 
		// set headers
		String customId = (String) req.getParam().get(CekAuthConst.PARAM_NAME_CUSTOMID);
		reqData.setHeader(CekAuthConst.HD_NAME_CUSTOMID, customId);
		reqData.setHeader(CekAuthConst.HD_NAME_SVRKEY, cekSvrKey);
		reqData.setHeader(CekAuthConst.HD_TRANSACTION_ID, CekAuthConst.HD_TRANSACTION_ID_PREFIX+UUID.randomUUID().toString().replace("-", ""));
		reqData.setHeader(CekAuthConst.HD_MESSAGE_ID, CekAuthConst.HD_MESSAGE_ID_PREFIX+UUID.randomUUID().toString().replace("-", ""));
		reqData.setHeader(HTTP.CONTENT_TYPE, CekAuthConst.HD_VALUE_CONTENTTYPE_JSON_UTF8);

		// set Common 
		CekAuthRequestJSON reqJson = new CekAuthRequestJSON();
		reqJson.getCommon().setClientIp(common.getClientIp());
		reqJson.getCommon().setDevInfo(common.getDevInfo());
		reqJson.getCommon().setOsInfo(common.getOsInfo());
		reqJson.getCommon().setNwInfo(common.getNwInfo());
		reqJson.getCommon().setDevModel(common.getDevModel());
		reqJson.getCommon().setCarrierType(common.getCarrierType());
		//set body
		reqJson.getBody().setServiceType(svcType);
		reqJson.getBody().setNid((String) req.getParam().get(CekAuthConst.BODY_NAME_NID));
		
		String contSvcCd = (String) req.getParam().get(RequestJSON.PARAM_SERVICE_CODE);
		reqJson.getBody().setContSvcCd(contSvcCd);
		reqData.setJson(gson.toJson(reqJson));

		// set trace info
		setTraceInfo(reqData, mgrappLoginId);

		// set params
		reqData.setConnectionTimeout(Integer.parseInt(cekAiAuthConnTimeout));
		reqData.setTimeout(Integer.parseInt(cekAiAuthTimeout));

		return request(reqData, common.getCtn());
	}
	
	
	public CekAuthResponseJSON getSvcAuthInfo(RequestJSON req,String mgrappLoginId) {

		// set request url
		String url = cekAiAuthUrl + CekAuthConst.URL_GET_SAVE_AUTH_INFO;
		RestRequestData reqData = new RestRequestData(url);
		
		CekAuthRequestCommonJSON common =  req.getCommon().getCekAuthRequestCommon();
		 
		// set headers
		String customId = (String) req.getParam().get(CekAuthConst.PARAM_NAME_CUSTOMID);

		reqData.setHeader(CekAuthConst.HD_NAME_SVRKEY, cekSvrKey);
		reqData.setHeader(CekAuthConst.HD_TRANSACTION_ID, CekAuthConst.HD_TRANSACTION_ID_PREFIX+UUID.randomUUID().toString().replace("-", ""));
		reqData.setHeader(CekAuthConst.HD_MESSAGE_ID, CekAuthConst.HD_MESSAGE_ID_PREFIX+UUID.randomUUID().toString().replace("-", ""));
		reqData.setHeader(HTTP.CONTENT_TYPE, CekAuthConst.HD_VALUE_CONTENTTYPE_JSON_UTF8);

		// set Common 
		CekAuthRequestJSON reqJson = new CekAuthRequestJSON();
		reqJson.getCommon().setClientIp(common.getClientIp());
		reqJson.getCommon().setDevInfo(common.getDevInfo());
		reqJson.getCommon().setOsInfo(common.getOsInfo());
		reqJson.getCommon().setNwInfo(common.getNwInfo());
		reqJson.getCommon().setDevModel(common.getDevModel());
		reqJson.getCommon().setCarrierType(common.getCarrierType());
		//set body
	
		reqJson.getBody().setCustomId(customId);
		reqJson.getBody().setServiceType(svcType);
		reqJson.getBody().setNid((String) req.getParam().get(CekAuthConst.BODY_NAME_NID));
		
		String contSvcCd = (String) req.getParam().get(RequestJSON.PARAM_SERVICE_CODE);
		reqJson.getBody().setContSvcCd(contSvcCd);
		//serviceCode
		reqData.setJson(gson.toJson(reqJson));

		// set trace info
		setTraceInfo(reqData, mgrappLoginId);

		// set params
		reqData.setConnectionTimeout(Integer.parseInt(cekAiAuthConnTimeout));
		reqData.setTimeout(Integer.parseInt(cekAiAuthTimeout));

		return request(reqData, common.getCtn());
	}
	
	public CekAuthResponseJSON getSvcAuthInfo2(RequestJSON req,String mgrappLoginId, String nid, String cutomId) {

		// set request url
		String url = cekAiAuthUrl + CekAuthConst.URL_GET_SAVE_AUTH_INFO;
		RestRequestData reqData = new RestRequestData(url);
		
		CekAuthRequestCommonJSON common =  req.getCommon().getCekAuthRequestCommon();
		 
		// set headers
		String customId = cutomId;

		reqData.setHeader(CekAuthConst.HD_NAME_SVRKEY, cekSvrKey);
		reqData.setHeader(CekAuthConst.HD_TRANSACTION_ID, CekAuthConst.HD_TRANSACTION_ID_PREFIX+UUID.randomUUID().toString().replace("-", ""));
		reqData.setHeader(CekAuthConst.HD_MESSAGE_ID, CekAuthConst.HD_MESSAGE_ID_PREFIX+UUID.randomUUID().toString().replace("-", ""));
		reqData.setHeader(HTTP.CONTENT_TYPE, CekAuthConst.HD_VALUE_CONTENTTYPE_JSON_UTF8);

		// set Common 
		CekAuthRequestJSON reqJson = new CekAuthRequestJSON();
		reqJson.getCommon().setClientIp(common.getClientIp());
		reqJson.getCommon().setDevInfo(common.getDevInfo());
		reqJson.getCommon().setOsInfo(common.getOsInfo());
		reqJson.getCommon().setNwInfo(common.getNwInfo());
		reqJson.getCommon().setDevModel(common.getDevModel());
		reqJson.getCommon().setCarrierType(common.getCarrierType());
		//set body
	
		reqJson.getBody().setCustomId(customId);
		reqJson.getBody().setServiceType(svcType);
		reqJson.getBody().setNid(nid);
		
		String contSvcCd = (String) req.getParam().get(RequestJSON.PARAM_SERVICE_CODE);
		if(contSvcCd != null && contSvcCd.equals("iot")) {
			contSvcCd = "biot";
		}
		reqJson.getBody().setContSvcCd(contSvcCd);
		//serviceCode
		reqData.setJson(gson.toJson(reqJson));

		// set trace info
		setTraceInfo(reqData, mgrappLoginId);

		// set params
		reqData.setConnectionTimeout(Integer.parseInt(cekAiAuthConnTimeout));
		reqData.setTimeout(Integer.parseInt(cekAiAuthTimeout));

		return request(reqData, common.getCtn());
	}

	private CekAuthResponseJSON request(RestRequestData reqData, String mgrAppCtn) {
			
		//RestResultData resultData = restAgent.requestProxy(reqData, cekHttpProxyHost, cekHttpProxyPort);
		
		RestResultData resultData = restAgent.request(reqData);
		
		if (resultData == null) {
			logger.error("failed to request Cek AI Auth Server. mgrAppCtn({})", mgrAppCtn);
			return null;
		}

		if (resultData.getStatusCode() != HttpStatus.SC_OK) {
			logger.error("failed to request Cek  AI Auth Server. mgrAppCtn({}) statusCode({})", mgrAppCtn,
					resultData.getStatusCode());
			return null;
		}

		CekAuthResponseJSON cekAiPlatformResp = new CekAuthResponseJSON();
		try {
			
			cekAiPlatformResp = gson.fromJson(resultData.getJson(), CekAuthResponseJSON.class);

		} catch (JsonSyntaxException e) {
			logger.error("JSON({}) Exception({})", resultData.getJson(), ExceptionUtil.getPrintStackTrace(e));
			return null;
		}

		if (cekAiPlatformResp == null
				|| !cekAiPlatformResp.getCommon().getResultCode().equals(CekAuthErrorCode.RC_20000000.getCode())) {
			logger.error("failed to request CekAIAuthInfo data. CekAuthResponseJSON({})", cekAiPlatformResp);
			return null;
		}
		
		return cekAiPlatformResp;
	}
	
	private void setTraceInfo(RestRequestData reqData, String ctn) {
		reqData.setTraceId(ctn);
		reqData.setSource(CekAuthConst.TRACE_SOURCE);
		reqData.setTarget(CekAuthConst.TRACE_TARGET);
	}
}
