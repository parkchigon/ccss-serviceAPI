package com.lgu.common.clova.auth;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.http.protocol.HTTP;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.lgu.common.cekAi.auth.model.CekDiscoveryRequestJSON;
import com.lgu.common.clova.auth.model.ClovaAuthResponseBodyAccessTokenJSON;
import com.lgu.common.clova.auth.model.ClovaAuthResponseBodyAuthCodeJSON;
import com.lgu.common.clova.auth.model.ClovaAuthResponseBodyDeleteTokenJSON;
import com.lgu.common.clova.auth.model.ClovaAuthResponseDiscoveryJSON;
import com.lgu.common.clova.auth.model.ClovaAuthResponseExtensionJSON;
import com.lgu.common.rest.RestAgent;
import com.lgu.common.rest.RestRequestData;
import com.lgu.common.rest.RestResultData;
import com.lgu.common.util.ExceptionUtil;

@Component
public class ClovaAuthAgent {
	private static final Logger logger = LoggerFactory.getLogger(ClovaAuthAgent.class);
	private static final Gson gson = new Gson();

	@Value("#{config['clova.auth.url']}")
	private String clovaAuthUrl;

	@Value("#{config['clova.link.url']}")
	private String clovaLinkUrl;
	
	//@Value("#{config['clova.client.id']}")
	//private String clovaClientId;

	//@Value("#{config['clova.client.secret']}")
	//private String clovaClientSecret;

	@Value("#{config['clova.auth.connection.timeout']}")
	private String clovaAuthConnTimeout;

	@Value("#{config['clova.auth.timeout']}")
	private String clovaAuthTimeout;
	
	@Value("#{config['clova.proxy.http.proxyHost']}")
	private String clovaHttpProxyHost;
	
	@Value("#{config['clova.proxy.http.proxySubHost']}")
	private String clovaHttpProxySubHost;
	
	@Value("#{config['clova.proxy.http.proxyPort']}")
	private int clovaHttpProxyPort;
	

	@Autowired
	private RestAgent restAgent;

	

	public ClovaAuthResponseBodyAuthCodeJSON createAuthorizationCode(String clovaClientId, String deviceModel,String ccssToken,String deviceCtn, String deviceSerial, String requestVu ,String naverAccessToken) throws UnsupportedEncodingException {

		// set request url
		String url = clovaAuthUrl + ClovaAuthConst.URL_CREATE_AUTHORIZATION_CODE;
		RestRequestData reqData = new RestRequestData(url);
		
		// set headers
		if(requestVu.equals(ClovaAuthConst.DEF_N) && naverAccessToken != null){
			reqData.setHeader(ClovaAuthConst.AUTHORIZATION, ClovaAuthConst.BEARER+naverAccessToken );			
		}
		reqData.setHeader(HTTP.CONTENT_TYPE, ClovaAuthConst.HD_VALUE_CONTENTTYPE_FORM_URL_ENCODED);
		
		//Param Sample: "request_vu=Y&client_id=36b0087a7029752f7e416a35a0fdb220&device_id=auth_test_ose&model_id=test_odel&response_type=code&state=TIwODMxNzYyNDQ1ff6e4";
		String urlParam = "";
		try {
			urlParam = makeClovaAuthorizationCodeParam(requestVu, clovaClientId, deviceSerial, deviceModel, ClovaAuthConst.DEF_RESPONSE_TYPE_CODE, ccssToken);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		reqData.setJson(urlParam);
		// set trace info
		setTraceInfo(reqData, deviceCtn);

		// set params
		reqData.setConnectionTimeout(Integer.parseInt(clovaAuthConnTimeout));
		reqData.setTimeout(Integer.parseInt(clovaAuthTimeout));

		return requestAuthorizationCode(reqData, deviceCtn);
	}
	
	
	public ClovaAuthResponseBodyAccessTokenJSON createClovaAccessToken(String clovaClientId, String clovaClientSecret,String deviceModel,String ccssToken,String deviceCtn, String deviceSerial,String authorizationCode) throws UnsupportedEncodingException, NumberFormatException {

		// set request url
		String url = clovaAuthUrl + ClovaAuthConst.URL_CREATE_CLOVA_ACCESS_TOKEN;
		RestRequestData reqData = new RestRequestData(url);
		
		// set Header
		reqData.setHeader(HTTP.CONTENT_TYPE, ClovaAuthConst.HD_VALUE_CONTENTTYPE_FORM_URL_ENCODED);
		
		// set body
		String urlParam = makeClovaAccessTokenParam(clovaClientId,clovaClientSecret,authorizationCode,deviceSerial,deviceModel);
		
		reqData.setJson(urlParam);

		// set trace info
		setTraceInfo(reqData, deviceCtn);

		// set params
		reqData.setConnectionTimeout(Integer.parseInt(clovaAuthConnTimeout));
		reqData.setTimeout(Integer.parseInt(clovaAuthTimeout));

		return requestClovaAccessToken(reqData, deviceCtn);
	}
	
	public ClovaAuthResponseBodyDeleteTokenJSON deleteClovaAccessToken(String clovaClientId,String clovaClientSecret, String deviceModel,String ccssToken,String deviceCtn, String deviceSerial,String clovaAccessToken) throws UnsupportedEncodingException, NumberFormatException {

		// set request url
		String url = clovaAuthUrl + ClovaAuthConst.URL_DELETE_CLOVA_ACCESS_TOKEN;
		RestRequestData reqData = new RestRequestData(url);
		
		// set Header
		reqData.setHeader(HTTP.CONTENT_TYPE, ClovaAuthConst.HD_VALUE_CONTENTTYPE_FORM_URL_ENCODED);
		
		// set body
		String urlParam = makeDeleteClovaAccessTokenParam(clovaClientId,clovaClientSecret,clovaAccessToken,deviceSerial,deviceModel);
		
		reqData.setJson(urlParam);

		// set trace info
		setTraceInfo(reqData, deviceCtn);

		// set params
		reqData.setConnectionTimeout(Integer.parseInt(clovaAuthConnTimeout));
		reqData.setTimeout(Integer.parseInt(clovaAuthTimeout));

		return requestDeleteClovaAccessToken(reqData, deviceCtn);
	}
	
	public ClovaAuthResponseBodyAccessTokenJSON refreshClovaAccessToken(String clovaClientId,String clovaClientSecret,String deviceModel,String ccssToken,String deviceCtn, String deviceSerial,String refreshToken) throws UnsupportedEncodingException, NumberFormatException {

		// set request url
		String url = clovaAuthUrl + ClovaAuthConst.URL_REFRESH_CLOVA_ACCESS_TOKEN;
		
		RestRequestData reqData = new RestRequestData(url);
		
		// set Header
		reqData.setHeader(HTTP.CONTENT_TYPE, ClovaAuthConst.HD_VALUE_CONTENTTYPE_FORM_URL_ENCODED);
		
		// set body
		String urlParam = makeRefreshClovaAccessTokenParam(clovaClientId,clovaClientSecret,refreshToken,deviceSerial,deviceModel);
		
		reqData.setJson(urlParam);

		// set trace info
		setTraceInfo(reqData, deviceCtn);

		// set params
		reqData.setConnectionTimeout(Integer.parseInt(clovaAuthConnTimeout));
		reqData.setTimeout(Integer.parseInt(clovaAuthTimeout));

		return requestRefreshClovaAccessToken(reqData, deviceCtn);
	}
	
	public ClovaAuthResponseExtensionJSON extensionClova(String clovaToken, String deviceCtn) throws UnsupportedEncodingException, NumberFormatException {
		// set request url
		String url = clovaLinkUrl + ClovaAuthConst.URL_IOT_EXTENSION;
		
		RestRequestData reqData = new RestRequestData(url);
		
		// set Header
		reqData.setHeader(HTTP.CONTENT_TYPE, ClovaAuthConst.HD_VALUE_CONTENTTYPE_JSON_UTF8);
		reqData.setHeader(ClovaAuthConst.AUTHORIZATION, ClovaAuthConst.BEARER + clovaToken);

		// set trace info
		setTraceInfo(reqData, deviceCtn);

		// set params
		reqData.setConnectionTimeout(Integer.parseInt(clovaAuthConnTimeout));
		reqData.setTimeout(Integer.parseInt(clovaAuthTimeout));

		return requestExtension(reqData, deviceCtn);
	}
	
	
	private ClovaAuthResponseExtensionJSON requestExtension(RestRequestData reqData, String deviceCtn) {
		
		RestResultData resultData = restAgent.requestGET(reqData, clovaHttpProxyHost, clovaHttpProxyPort);
		
		if (resultData == null) {
			logger.error("failed to request Clova Auth Server. deviceCtn({})", deviceCtn);
			return null;
		}

		if (resultData.getStatusCode() != HttpStatus.SC_OK) {
			logger.error("failed to request Clova Auth Server. deviceCtn({}) statusCode({})", deviceCtn,
					resultData.getStatusCode());
			return null;
		}
		
		ClovaAuthResponseExtensionJSON clovaAuthResponseExtensonJSON = new ClovaAuthResponseExtensionJSON();
		
		try {
			
			clovaAuthResponseExtensonJSON = gson.fromJson(resultData.getJson(), ClovaAuthResponseExtensionJSON.class);

		} catch (JsonSyntaxException e) {
			logger.error("JSON({}) Exception({})", resultData.getJson(), ExceptionUtil.getPrintStackTrace(e));
			return null;
		}

		if (clovaAuthResponseExtensonJSON == null ) {
			logger.error("failed to request Clova AuthInfo data. ClovaAuthResponseJSON({})", clovaAuthResponseExtensonJSON);
			return null;
		}
		
		return clovaAuthResponseExtensonJSON;
	}
	
	public ClovaAuthResponseDiscoveryJSON discoveryClova(String clovaToken) throws UnsupportedEncodingException, NumberFormatException {
		// set request url
		String url = clovaLinkUrl + ClovaAuthConst.URL_IOT_DISCOVERY;
		
		RestRequestData reqData = new RestRequestData(url);
		
		// set Header
		reqData.setHeader(HTTP.CONTENT_TYPE, ClovaAuthConst.HD_VALUE_CONTENTTYPE_JSON_UTF8);
		reqData.setHeader(ClovaAuthConst.AUTHORIZATION, ClovaAuthConst.BEARER + clovaToken);

		// set params
		reqData.setConnectionTimeout(Integer.parseInt(clovaAuthConnTimeout));
		reqData.setTimeout(Integer.parseInt(clovaAuthTimeout));

		return requestDiscovery(reqData, clovaToken);
	}
	
	private ClovaAuthResponseDiscoveryJSON requestDiscovery(RestRequestData reqData, String clovaToken) {
		
		RestResultData resultData = restAgent.requestGET(reqData,clovaToken, clovaHttpProxyHost, clovaHttpProxySubHost, clovaHttpProxyPort);
		
		if (resultData == null) {
			logger.error("failed to request Clova Auth Server.");
			return null;
		}

		if (resultData.getStatusCode() != HttpStatus.SC_OK) {
			logger.error("failed to request Clova Auth Server. deviceCtn({}) statusCode({})",resultData.getStatusCode());
			return null;
		}
		
		ClovaAuthResponseDiscoveryJSON clovaAuthResponseDiscoveryJSON = new ClovaAuthResponseDiscoveryJSON();
		
		try {
			
			clovaAuthResponseDiscoveryJSON = gson.fromJson(resultData.getJson(), ClovaAuthResponseDiscoveryJSON.class);

		} catch (JsonSyntaxException e) {
			logger.error("JSON({}) Exception({})", resultData.getJson(), ExceptionUtil.getPrintStackTrace(e));
			return null;
		}

		if (clovaAuthResponseDiscoveryJSON == null ) {
			logger.error("failed to request Clova AuthInfo data. ClovaAuthResponseJSON({})", clovaAuthResponseDiscoveryJSON);
			return null;
		}
		
		return clovaAuthResponseDiscoveryJSON;
	}
	
	public ClovaAuthResponseDiscoveryJSON connectClova(String deviceId, String clovaToken) throws UnsupportedEncodingException, NumberFormatException {
		// set request url
		String url = clovaLinkUrl + ClovaAuthConst.URL_IOT_CONNECT + deviceId;
		
		RestRequestData reqData = new RestRequestData(url);
		
		reqData.setHeader(ClovaAuthConst.AUTHORIZATION, ClovaAuthConst.BEARER + clovaToken);
		
		CekDiscoveryRequestJSON cekDiscovery = new CekDiscoveryRequestJSON();
		
		cekDiscovery.setLink(true);
		//20190508 김진형선임 메일로 location 없앰
		//cekDiscovery.setLocation("");
		
		//String connect = "\"link\":\"true\", \"location\":\"\""; //"{"+ "link :" + "true" +"location :"+ ""+ "}";
		reqData.setJson(gson.toJson(cekDiscovery));
		
		// set params
		reqData.setConnectionTimeout(Integer.parseInt(clovaAuthConnTimeout));
		reqData.setTimeout(Integer.parseInt(clovaAuthTimeout));

		return requestConnect(reqData);
	}
	
	


	private ClovaAuthResponseDiscoveryJSON requestConnect(RestRequestData reqData) {
		
		RestResultData resultData = restAgent.requestPUT(reqData, clovaHttpProxyHost, clovaHttpProxySubHost, clovaHttpProxyPort);
		
		if (resultData == null) {
			logger.error("failed to request Clova Auth Server.");
			return null;
		}
		
		
		if (resultData.getStatusCode() != HttpStatus.SC_OK) {
			logger.error("failed to request Clova Auth Server. deviceCtn({}) statusCode({})",resultData.getStatusCode());
			return null;
		}
		
		ClovaAuthResponseDiscoveryJSON clovaAuthResponseDiscoveryJSON = new ClovaAuthResponseDiscoveryJSON();
		
		try {
			
			clovaAuthResponseDiscoveryJSON = gson.fromJson(resultData.getJson(), ClovaAuthResponseDiscoveryJSON.class);

		} catch (JsonSyntaxException e) {
			logger.error("JSON({}) Exception({})", resultData.getJson(), ExceptionUtil.getPrintStackTrace(e));
			return null;
		}

		if (clovaAuthResponseDiscoveryJSON == null ) {
			logger.error("failed to request Clova AuthInfo data. ClovaAuthResponseJSON({})", clovaAuthResponseDiscoveryJSON);
			return null;
		}
		
		return clovaAuthResponseDiscoveryJSON;
	}
	
	public ClovaAuthResponseDiscoveryJSON disConnectClova(String deviceId, String clovaToken) throws UnsupportedEncodingException, NumberFormatException {
		// set request url
		String url = clovaLinkUrl + ClovaAuthConst.URL_IOT_CONNECT + deviceId;
		
		RestRequestData reqData = new RestRequestData(url);
		
		reqData.setHeader(ClovaAuthConst.AUTHORIZATION, ClovaAuthConst.BEARER + clovaToken);
		
		CekDiscoveryRequestJSON cekDiscovery = new CekDiscoveryRequestJSON();
		
		cekDiscovery.setLink(false);
		//20190508 김진형선임 메일로 location 없앰
		//cekDiscovery.setLocation("");
		
		//String connect = "\"link\":\"true\", \"location\":\"\""; //"{"+ "link :" + "true" +"location :"+ ""+ "}";
		reqData.setJson(gson.toJson(cekDiscovery));
		
		// set params
		reqData.setConnectionTimeout(Integer.parseInt(clovaAuthConnTimeout));
		reqData.setTimeout(Integer.parseInt(clovaAuthTimeout));

		return requestDisConnect(reqData);
	}
	
	


	private ClovaAuthResponseDiscoveryJSON requestDisConnect(RestRequestData reqData) {
		
		RestResultData resultData = restAgent.requestPUT(reqData, clovaHttpProxyHost, clovaHttpProxySubHost, clovaHttpProxyPort);
		
		if (resultData == null) {
			logger.error("failed to request Clova Auth Server.");
			return null;
		}
		
		
		if (resultData.getStatusCode() != HttpStatus.SC_OK) {
			logger.error("failed to request Clova Auth Server. deviceCtn({}) statusCode({})",resultData.getStatusCode());
			return null;
		}
		
		ClovaAuthResponseDiscoveryJSON clovaAuthResponseDiscoveryJSON = new ClovaAuthResponseDiscoveryJSON();
		
		try {
			
			clovaAuthResponseDiscoveryJSON = gson.fromJson(resultData.getJson(), ClovaAuthResponseDiscoveryJSON.class);

		} catch (JsonSyntaxException e) {
			logger.error("JSON({}) Exception({})", resultData.getJson(), ExceptionUtil.getPrintStackTrace(e));
			return null;
		}

		if (clovaAuthResponseDiscoveryJSON == null ) {
			logger.error("failed to request Clova AuthInfo data. ClovaAuthResponseJSON({})", clovaAuthResponseDiscoveryJSON);
			return null;
		}
		
		return clovaAuthResponseDiscoveryJSON;
	}
	

	private ClovaAuthResponseBodyAuthCodeJSON requestAuthorizationCode(RestRequestData reqData, String deviceCtn) {
			
		RestResultData resultData = restAgent.requestProxy(reqData, clovaHttpProxyHost, clovaHttpProxySubHost, clovaHttpProxyPort);
		
		if (resultData == null) {
			logger.error("failed to request Clova Auth Server. deviceCtn({})", deviceCtn);
			return null;
		}

		if (resultData.getStatusCode() != HttpStatus.SC_OK) {
			logger.error("failed to request Clova Auth Server. deviceCtn({}) statusCode({})", deviceCtn,
					resultData.getStatusCode());
			return null;
		}
		
		
		ClovaAuthResponseBodyAuthCodeJSON clovaAuthResponseJSON = new ClovaAuthResponseBodyAuthCodeJSON();
		try {
			
			clovaAuthResponseJSON = gson.fromJson(resultData.getJson(), ClovaAuthResponseBodyAuthCodeJSON.class);

		} catch (JsonSyntaxException e) {
			logger.error("JSON({}) Exception({})", resultData.getJson(), ExceptionUtil.getPrintStackTrace(e));
			return null;
		}

		if (clovaAuthResponseJSON == null ) {
			logger.error("failed to request Clova AuthInfo data. ClovaAuthResponseJSON({})", clovaAuthResponseJSON);
			return null;
		}
		
		return clovaAuthResponseJSON;
	}
	
	private ClovaAuthResponseBodyAccessTokenJSON requestClovaAccessToken(RestRequestData reqData, String deviceCtn) {
		
		RestResultData resultData = restAgent.requestProxy(reqData, clovaHttpProxyHost, clovaHttpProxySubHost, clovaHttpProxyPort);
		
		if (resultData == null) {
			logger.error("failed to request Clova Auth Server. deviceCtn({})", deviceCtn);
			return null;
		}

		if (resultData.getStatusCode() != HttpStatus.SC_OK) {
			logger.error("failed to request Clova Auth Server. deviceCtn({}) statusCode({})", deviceCtn,
					resultData.getStatusCode());
			return null;
		}
		
		logger.debug("#### resultData.getJson()", resultData.getJson());

		ClovaAuthResponseBodyAccessTokenJSON clovaAuthResponseBodyAccessTokenJSON = new ClovaAuthResponseBodyAccessTokenJSON();
		try {
			
			clovaAuthResponseBodyAccessTokenJSON = gson.fromJson(resultData.getJson(), ClovaAuthResponseBodyAccessTokenJSON.class);

		} catch (JsonSyntaxException e) {
			logger.error("JSON({}) Exception({})", resultData.getJson(), ExceptionUtil.getPrintStackTrace(e));
			return null;
		}

		if (clovaAuthResponseBodyAccessTokenJSON == null ) {
			logger.error("failed to request Clova AuthInfo data. ClovaAuthResponseBodyAccessTokenJSON({})", clovaAuthResponseBodyAccessTokenJSON);
			return null;
		}
		
		return clovaAuthResponseBodyAccessTokenJSON;
	}
	
	
	private ClovaAuthResponseBodyDeleteTokenJSON requestDeleteClovaAccessToken(RestRequestData reqData, String deviceCtn) {
		
		RestResultData resultData = restAgent.requestProxy(reqData, clovaHttpProxyHost, clovaHttpProxySubHost, clovaHttpProxyPort);
		
		if (resultData == null) {
			logger.error("failed to request Clova Auth Server. deviceCtn({})", deviceCtn);
			return null;
		}

		if (resultData.getStatusCode() != HttpStatus.SC_OK) {
			logger.error("failed to request Clova Auth Server. deviceCtn({}) statusCode({})", deviceCtn,
					resultData.getStatusCode());
			return null;
		}
		
		
		ClovaAuthResponseBodyDeleteTokenJSON clovaAuthResponseBodyDeleteTokenJSON = new ClovaAuthResponseBodyDeleteTokenJSON();
		try {
			
			clovaAuthResponseBodyDeleteTokenJSON = gson.fromJson(resultData.getJson(), ClovaAuthResponseBodyDeleteTokenJSON.class);

		} catch (JsonSyntaxException e) {
			logger.error("JSON({}) Exception({})", resultData.getJson(), ExceptionUtil.getPrintStackTrace(e));
			return null;
		}

		if (clovaAuthResponseBodyDeleteTokenJSON == null ) {
			logger.error("failed to request Delete Clova AuthInfo data. clovaAuthResponseBodyDeleteTokenJSON({})", clovaAuthResponseBodyDeleteTokenJSON);
			return null;
		}
		
		return clovaAuthResponseBodyDeleteTokenJSON;
	}
	
	private ClovaAuthResponseBodyAccessTokenJSON requestRefreshClovaAccessToken(RestRequestData reqData, String deviceCtn) {
		
		RestResultData resultData = restAgent.requestProxy(reqData, clovaHttpProxyHost, clovaHttpProxySubHost, clovaHttpProxyPort);
		
		if (resultData == null) {
			logger.error("failed to request Clova Auth Server. deviceCtn({})", deviceCtn);
			return null;
		}

		if (resultData.getStatusCode() != HttpStatus.SC_OK) {
			logger.error("failed to request Clova Auth Server. deviceCtn({}) statusCode({})", deviceCtn,
					resultData.getStatusCode());
			return null;
		}
		
		
		ClovaAuthResponseBodyAccessTokenJSON clovaAuthResponseBodyAccessTokenJSON = new ClovaAuthResponseBodyAccessTokenJSON();
		try {
			
			clovaAuthResponseBodyAccessTokenJSON = gson.fromJson(resultData.getJson(), ClovaAuthResponseBodyAccessTokenJSON.class);

		} catch (JsonSyntaxException e) {
			logger.error("JSON({}) Exception({})", resultData.getJson(), ExceptionUtil.getPrintStackTrace(e));
			return null;
		}

		if (clovaAuthResponseBodyAccessTokenJSON == null ) {
			logger.error("failed to request Refresh Clova AuthInfo data. ClovaAuthResponseBodyAccessTokenJSON({})", clovaAuthResponseBodyAccessTokenJSON);
			return null;
		}
		
		return clovaAuthResponseBodyAccessTokenJSON;
	}

	public String makeClovaAuthorizationCodeParam(String request_vu,String client_id, String device_id,String model_id,String response_type,String state) throws UnsupportedEncodingException{
		String returnVal="";
		returnVal+=ClovaAuthConst.BODY_REQUEST_VU+ClovaAuthConst.DEF_EQUAL+ URLEncoder.encode(request_vu, ClovaAuthConst.DEF_UTF_8 )+ClovaAuthConst.DEF_AMPERSAND
				+ClovaAuthConst.BODY_AUTH_CLIENT_ID+ClovaAuthConst.DEF_EQUAL+URLEncoder.encode(client_id, ClovaAuthConst.DEF_UTF_8 )+ClovaAuthConst.DEF_AMPERSAND
				+ClovaAuthConst.BODY_AUTH_DEVICE_ID+ClovaAuthConst.DEF_EQUAL+URLEncoder.encode(device_id, ClovaAuthConst.DEF_UTF_8 )+ClovaAuthConst.DEF_AMPERSAND
				+ClovaAuthConst.BODY_AUTH_MODEL_ID+ClovaAuthConst.DEF_EQUAL+URLEncoder.encode(model_id, ClovaAuthConst.DEF_UTF_8 )+ClovaAuthConst.DEF_AMPERSAND
				+ClovaAuthConst.BODY_AUTH_RESPONSE_TYPE+ClovaAuthConst.DEF_EQUAL+URLEncoder.encode(response_type, ClovaAuthConst.DEF_UTF_8 )+ClovaAuthConst.DEF_AMPERSAND
				+ClovaAuthConst.BODY_AUTH_STATE+ClovaAuthConst.DEF_EQUAL+URLEncoder.encode(state, ClovaAuthConst.DEF_UTF_8 );
		return returnVal;
	}
	
	public String makeClovaAccessTokenParam(String client_id,String client_secret,String AuthorizationCode, String device_id,String model_id) throws UnsupportedEncodingException{
		String returnVal="";
		returnVal+=ClovaAuthConst.BODY_AUTH_CLIENT_ID+ClovaAuthConst.DEF_EQUAL+URLEncoder.encode(client_id, ClovaAuthConst.DEF_UTF_8 )+ClovaAuthConst.DEF_AMPERSAND
				+ClovaAuthConst.BODY_AUTH_CLIENT_SECRET+ClovaAuthConst.DEF_EQUAL+URLEncoder.encode(client_secret, ClovaAuthConst.DEF_UTF_8 )+ClovaAuthConst.DEF_AMPERSAND
				+ClovaAuthConst.BODY_AUTH_CODE+ClovaAuthConst.DEF_EQUAL+URLEncoder.encode(AuthorizationCode, ClovaAuthConst.DEF_UTF_8 )+ClovaAuthConst.DEF_AMPERSAND
				+ClovaAuthConst.BODY_AUTH_DEVICE_ID+ClovaAuthConst.DEF_EQUAL+URLEncoder.encode(device_id, ClovaAuthConst.DEF_UTF_8 )+ClovaAuthConst.DEF_AMPERSAND
				+ClovaAuthConst.BODY_AUTH_MODEL_ID+ClovaAuthConst.DEF_EQUAL+URLEncoder.encode(model_id, ClovaAuthConst.DEF_UTF_8 );
		return returnVal;
	}
	
	public String makeDeleteClovaAccessTokenParam(String client_id,String client_secret,String clovaAccessToken, String device_id,String model_id) throws UnsupportedEncodingException{
		String returnVal="";
		returnVal+=ClovaAuthConst.BODY_AUTH_CLIENT_ID+ClovaAuthConst.DEF_EQUAL+URLEncoder.encode(client_id, ClovaAuthConst.DEF_UTF_8 )+ClovaAuthConst.DEF_AMPERSAND
				+ClovaAuthConst.BODY_AUTH_CLIENT_SECRET+ClovaAuthConst.DEF_EQUAL+URLEncoder.encode(client_secret, ClovaAuthConst.DEF_UTF_8 )+ClovaAuthConst.DEF_AMPERSAND
				+ClovaAuthConst.BODY_ACCESS_TOKEN+ClovaAuthConst.DEF_EQUAL+URLEncoder.encode(clovaAccessToken, ClovaAuthConst.DEF_UTF_8 )+ClovaAuthConst.DEF_AMPERSAND
				+ClovaAuthConst.BODY_AUTH_DEVICE_ID+ClovaAuthConst.DEF_EQUAL+URLEncoder.encode(device_id, ClovaAuthConst.DEF_UTF_8 )+ClovaAuthConst.DEF_AMPERSAND
				+ClovaAuthConst.BODY_AUTH_MODEL_ID+ClovaAuthConst.DEF_EQUAL+URLEncoder.encode(model_id, ClovaAuthConst.DEF_UTF_8 );
		return returnVal;
	}
	
	public String makeRefreshClovaAccessTokenParam(String client_id,String client_secret,String refreshToken, String device_id,String model_id) throws UnsupportedEncodingException{
		String returnVal="";
		returnVal+=ClovaAuthConst.BODY_AUTH_CLIENT_ID+ClovaAuthConst.DEF_EQUAL+URLEncoder.encode(client_id, ClovaAuthConst.DEF_UTF_8 )+ClovaAuthConst.DEF_AMPERSAND
				+ClovaAuthConst.BODY_AUTH_CLIENT_SECRET+ClovaAuthConst.DEF_EQUAL+URLEncoder.encode(client_secret, ClovaAuthConst.DEF_UTF_8 )+ClovaAuthConst.DEF_AMPERSAND
				+ClovaAuthConst.BODY_REFRESH_TOKEN+ClovaAuthConst.DEF_EQUAL+URLEncoder.encode(refreshToken, ClovaAuthConst.DEF_UTF_8 )+ClovaAuthConst.DEF_AMPERSAND
				+ClovaAuthConst.BODY_AUTH_DEVICE_ID+ClovaAuthConst.DEF_EQUAL+URLEncoder.encode(device_id, ClovaAuthConst.DEF_UTF_8 )+ClovaAuthConst.DEF_AMPERSAND
				+ClovaAuthConst.BODY_AUTH_MODEL_ID+ClovaAuthConst.DEF_EQUAL+URLEncoder.encode(model_id, ClovaAuthConst.DEF_UTF_8 );
		return returnVal;
	}
	
	public String makeConnectParam() {
		String returnVal="";
		returnVal+= ClovaAuthConst.BODY_AUTH_LINK+ ":" + "true" +"," + ClovaAuthConst.BODY_AUTH_LOCATION + ":" + "";
		return returnVal;
	}
	
	private void setTraceInfo(RestRequestData reqData, String deviceCtn) {
		reqData.setTraceId(deviceCtn);
		reqData.setSource(ClovaAuthConst.TRACE_SOURCE);
		reqData.setTarget(ClovaAuthConst.TRACE_TARGET);
	}

	public static void main(String[] args) {
		
		
	}

	
}
