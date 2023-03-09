package com.lgu.common.esb.oAuth;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import org.apache.commons.httpclient.HttpStatus;
import org.apache.http.protocol.HTTP;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.lgu.common.esb.oAuth.model.ResponseOAuth2AccessTokenDto;
import com.lgu.common.rest.RestAgent;
import com.lgu.common.rest.RestRequestData;
import com.lgu.common.rest.RestResultData;
import com.lgu.common.util.ExceptionUtil;

@Component
public class oAuthAgent {
	private static final Logger logger = LoggerFactory.getLogger(oAuthAgent.class);
	private static final Gson gson = new Gson();

	/*@Value("#{config['esb.oauth.baseurl']}")
	private String esbOAuthUrl;*/
	
	@Value("#{config['esb.oauth.prefix']}")
	private String esbOAuthPrefix;

	//@Value("#{config['clova.link.url']}")
	//private String clovaLinkUrl;
	
	@Value("#{config['auth.client.id']}")
	private String authClientId;

	@Value("#{config['auth.client.secret']}")
	private String authClientSecret;
	
	@Value("#{config['auth.client.scope']}")
	private String authClientScope;
	
	
	@Value("#{config['auth.http.proxyHost']}")
	private String authHttpProxyHost;
	
	@Value("#{config['auth.http.proxySubHost']}")
	private String authHttpProxySubHost;
	
	@Value("#{config['auth.http.proxyPort']}")
	private int authHttpProxyPort;
	
	
	@Autowired
	private RestAgent restAgent;
	
	public ResponseOAuth2AccessTokenDto requestAccessToken(String urls) throws UnsupportedEncodingException {
		
		// set request url
		String url = urls+esbOAuthPrefix;
		RestRequestData reqData = new RestRequestData(url);
		ResponseOAuth2AccessTokenDto body = null;
		reqData.setHeader(HTTP.CONTENT_TYPE, oAuthConst.HD_VALUE_CONTENTTYPE_FORM_URL_ENCODED);
		reqData.setHeader(oAuthConst.ACCEPT, oAuthConst.HD_VALUE_ACCEPT_TYPE);
		
		// set body
		String urlParam = makeoAauthTokenParam("client_credentials",authClientId,authClientSecret,authClientScope);
		
		reqData.setJson(urlParam);

		// set params
		reqData.setConnectionTimeout(5000);
		reqData.setTimeout(5000);

		return request(reqData);
	}
	
	private String makeoAauthTokenParam(String grantType, String authClientId, String authClientSecret,String scope) throws UnsupportedEncodingException {
		String returnVal="";
		returnVal+=oAuthConst.BODY_AUTH_GRANT_TYPE+oAuthConst.DEF_EQUAL+URLEncoder.encode(grantType, oAuthConst.DEF_UTF_8 )+oAuthConst.DEF_AMPERSAND
				+oAuthConst.BODY_AUTH_CLIENT_ID+oAuthConst.DEF_EQUAL+URLEncoder.encode(authClientId, oAuthConst.DEF_UTF_8 )+oAuthConst.DEF_AMPERSAND
				+oAuthConst.BODY_AUTH_CLIENT_SECRET+oAuthConst.DEF_EQUAL+URLEncoder.encode(authClientSecret, oAuthConst.DEF_UTF_8 )+oAuthConst.DEF_AMPERSAND
				+oAuthConst.BODY_AUTH_SCOPE+oAuthConst.DEF_EQUAL+URLEncoder.encode(scope, oAuthConst.DEF_UTF_8 );
		return returnVal;
	}

	private ResponseOAuth2AccessTokenDto request(RestRequestData reqData) {
		
		//RestResultData resultData = restAgent.requestProxy(reqData, authHttpProxyHost, authHttpProxySubHost,authHttpProxyPort);
		RestResultData resultData = restAgent.request(reqData);
		
		if (resultData == null) {
			logger.error("failed to request oAuth2 Server)");
			return null;
		}

		ResponseOAuth2AccessTokenDto oAuthResp = new ResponseOAuth2AccessTokenDto();
		if (resultData.getStatusCode() != HttpStatus.SC_OK) {
			logger.error("failed to request oAuth2 Server.statusCode({})",
					resultData.getStatusCode());
			oAuthResp.setCode(resultData.getStatusCode());
			return oAuthResp;
		}

		try {
			
			oAuthResp = gson.fromJson(resultData.getJson(), ResponseOAuth2AccessTokenDto.class);

		} catch (JsonSyntaxException e) {
			logger.error("JSON({}) Exception({})", resultData.getJson(), ExceptionUtil.getPrintStackTrace(e));
			return null;
		}

		if (oAuthResp == null ) {
			logger.error("failed to request Clova AuthInfo data. oAuthResp({})", oAuthResp);
			return null;
		}
		
		return oAuthResp;
	}
/*
	@Autowired
	private RestTemplate getRestTemplate() {
		HttpComponentsClientHttpRequestFactory factory = new HttpComponentsClientHttpRequestFactory();
		factory.setReadTimeout(5000);
		factory.setConnectTimeout(5000);
		RestTemplate restTemplate = new RestTemplate(factory);
		return restTemplate;
	}
	
	public ResponseOAuth2AccessTokenDto requestAccessToken() {
		ResponseOAuth2AccessTokenDto body = null;
		try {
			String url = esbOAuthUrl;
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
			headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
			
			AuthRequestDataJSON reqJson = new AuthRequestDataJSON();
			reqJson.setGrantType("client_credentials");
			reqJson.setClientId(authClientId);
			reqJson.setClientSecret(authClientSecret);
			reqJson.setScope(authClientScope);

			HttpEntity<String> httpEntity = new HttpEntity<String>(gson.toJson(reqJson), headers);
			ResponseEntity<ResponseOAuth2AccessTokenDto> responseEntity;

			RestTemplate restTemplate = getRestTemplate();
			responseEntity = restTemplate.exchange(url, HttpMethod.POST, httpEntity, ResponseOAuth2AccessTokenDto.class);
			body = responseEntity.getBody();

			logger.info("requestAccessToken|Res=>",body.toString());
		} catch (Exception e) {
			logger.error("requestAccessToken|error:", e.toString());
			return null;
		}
		
		return body;
	}
	*/
}