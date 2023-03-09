package com.lgu.common.esb;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import org.apache.commons.httpclient.HttpStatus;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.CoreConnectionPNames;
import org.apache.http.params.HttpParams;
import org.apache.http.protocol.HTTP;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.lgu.common.esb.oAuth.oAuthConst;
import com.lgu.common.esb.oAuth.model.ResponseOAuth2AccessTokenDto;
import com.lgu.common.rest.RestAgent;
import com.lgu.common.rest.RestRequestData;
import com.lgu.common.rest.RestResultData;
import com.lgu.common.util.ExceptionUtil;

public class TestAgent {
	private static final Logger logger = LoggerFactory.getLogger(RestAgent.class);
	private static final int DEFAULT_CONNECTION_TIMEOUT = 5000;
	private static final int DEFAULT_TIMEOUT = 5000;
	private static final Gson gson = new Gson();
	public static void main(String[] args) throws IOException {
			
		/*
		File f = new File("D:/dev/event_card/acesstoken.txt");
		String str = null;
		if(f.exists()) {
			BufferedReader reader = new BufferedReader(new FileReader("D:/dev/event_card/acesstoken.txt"));
			if ((str = reader.readLine()) != null) {
				logger.info("file contents{{}}", str);
			}
			reader.close();
			logger.info("file contents{{}}", str);
		}else {
			logger.debug("file not exist");
		}
		logger.info("file contents{{}}", str);
		*/
		
		
			ResponseOAuth2AccessTokenDto cekAuthResponseJSON = requestAccessToken();
			System.out.println("cekAuthResponseJSON::"+cekAuthResponseJSON);
			String acessToken;
			if (cekAuthResponseJSON == null) {
				acessToken = null;
			}else {
				acessToken = cekAuthResponseJSON.getToken_type()+" "+cekAuthResponseJSON.getAccess_token();
				BufferedOutputStream bs = null;
				try {
					bs = new BufferedOutputStream(new FileOutputStream("D:/dev/event_card/acesstoken.txt"));
					bs.write(acessToken.getBytes()); //Byte형으로만 넣을 수 있음
				} catch (Exception e) {
					logger.debug("{}", e.getMessage());

					e.printStackTrace();
				} finally {
					bs.close();
				}
			}
			
			System.out.println("acessToken::::"+acessToken);
		
	}
	
public static ResponseOAuth2AccessTokenDto requestAccessToken() throws UnsupportedEncodingException {
		
		// set request url
		String url = "https://tst-openapi.lguplus.co.kr/uplus/intuser"+"/oauth2/token";
		//String url = "https://172.23.19.161/uplus/intuser"+"/oauth2/token";
		RestRequestData reqData = new RestRequestData(url);
		ResponseOAuth2AccessTokenDto body = null;
		reqData.setHeader(HTTP.CONTENT_TYPE, oAuthConst.HD_VALUE_CONTENTTYPE_FORM_URL_ENCODED);
		reqData.setHeader(oAuthConst.ACCEPT, oAuthConst.HD_VALUE_ACCEPT_TYPE);
		
		// set body
		String urlParam = makeoAauthTokenParam("client_credentials","0eac47f5-33cf-4934-ae77-a23bf866b82a","mB0mQ2bP1fA5lV2xG6mB1vV3jS8cO4lB6eT3nK4kQ8vV5dC3qL","EA BL");
		
		reqData.setJson(urlParam);

		// set params
		reqData.setConnectionTimeout(5000);
		reqData.setTimeout(5000);

		return request1(reqData);
	}
	
	private static String makeoAauthTokenParam(String grantType, String authClientId, String authClientSecret,String scope) throws UnsupportedEncodingException {
		String returnVal="";
		returnVal+=oAuthConst.BODY_AUTH_GRANT_TYPE+oAuthConst.DEF_EQUAL+URLEncoder.encode(grantType, oAuthConst.DEF_UTF_8 )+oAuthConst.DEF_AMPERSAND
				+oAuthConst.BODY_AUTH_CLIENT_ID+oAuthConst.DEF_EQUAL+URLEncoder.encode(authClientId, oAuthConst.DEF_UTF_8 )+oAuthConst.DEF_AMPERSAND
				+oAuthConst.BODY_AUTH_CLIENT_SECRET+oAuthConst.DEF_EQUAL+URLEncoder.encode(authClientSecret, oAuthConst.DEF_UTF_8 )+oAuthConst.DEF_AMPERSAND
				+oAuthConst.BODY_AUTH_SCOPE+oAuthConst.DEF_EQUAL+URLEncoder.encode(scope, oAuthConst.DEF_UTF_8 );
		return returnVal;
	}

	private static ResponseOAuth2AccessTokenDto request1(RestRequestData reqData) {
		
		RestResultData resultData = request2(reqData);
		
		//RestResultData resultData = restAgent.request(reqData);
		
		ResponseOAuth2AccessTokenDto oAuthResp = new ResponseOAuth2AccessTokenDto();
		if (resultData == null) {
			logger.error("failed to request oAuth2 Server)");
			return null;
		}

		if (resultData.getStatusCode() != HttpStatus.SC_OK) {
			logger.error("failed to request oAuth2 Server.statusCode({})",
					resultData.getStatusCode());
			return null;
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
	
	public static RestResultData request2(RestRequestData requestData) {
		
		if (logger.isDebugEnabled()) {
			logger.debug("REQUEST({})", requestData);
		}
		
		HttpPost post = new HttpPost(requestData.getUrl());
		
		HttpParams params = new BasicHttpParams();
		setBasicParams(requestData, params);
		post.setParams(params);
		
		for (String name : requestData.getHeaders()) {
			post.setHeader(name, requestData.getHeader(name));
		}
		try {
			if(requestData.getJson() !=null)
			post.setEntity(new StringEntity(requestData.getJson(), StandardCharsets.UTF_8.toString()));
		} catch (UnsupportedEncodingException e) {
			logger.error("Exception({})", ExceptionUtil.getPrintStackTrace(e));
			return null;
		}
		
		HttpClient client = null;
		HttpResponse response = null;
		try {
			client = getHttpClient(requestData.getUrl());
			response = client.execute(post);
			
		} catch (ClientProtocolException e) {
			logger.error("Exception({})", e);
			
		} catch (IOException e) {
			logger.error("Exception({})", e);
			
		} 
		
		if (response == null) {
			return null;
		}
		
		String body = null;
		try {
			body = getBody(response);
			
		} catch (IllegalStateException e) {
			logger.error("Exception({})", e);
			
		} catch (IOException e) {
			logger.error("Exception({})", e);
		}
		
		RestResultData resultData = new RestResultData(response.getStatusLine().getStatusCode());
		if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
			resultData.setJson(body);
		}
		
		if (logger.isDebugEnabled()) {
			logger.debug("RESPONSE({}\n{})", response, body);
		}
		
		
		if (client != null) {
			client.getConnectionManager().shutdown();
		}
		
		return resultData;
	}
	private static void setBasicParams(RestRequestData requestData, HttpParams params) {
		int connectionTimeout = requestData.getConnectionTimeout();
		if (connectionTimeout <= 1000) connectionTimeout = DEFAULT_CONNECTION_TIMEOUT;
		params.setParameter(CoreConnectionPNames.CONNECTION_TIMEOUT, connectionTimeout);
		
		int timeout = requestData.getTimeout();
		if (timeout <= 1000) timeout = DEFAULT_TIMEOUT;
		params.setParameter(CoreConnectionPNames.SO_TIMEOUT, timeout);
	}
	private static String getBody(HttpResponse response) throws IllegalStateException, IOException {
		BufferedReader rd = new BufferedReader(
		        new InputStreamReader(response.getEntity().getContent()));

		StringBuffer result = new StringBuffer();
		String line = "";
		while ((line = rd.readLine()) != null) {
			result.append(line);
		}
		
		return result.toString();
	}
	
	private static HttpClient getHttpClient(String requestUrl) throws MalformedURLException {
		HttpClient client = null;
		
		URL url = new URL(requestUrl);
		
		if (url.getProtocol().equals("https")) {
	        TrustManager easyTrustManager = new X509TrustManager() {
				@Override
				public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {
				}

				@Override
				public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {	
				}

				@Override
				public X509Certificate[] getAcceptedIssuers() {
					return null;
				}

	        };
	        
	        client = new DefaultHttpClient();
	        
	        try {
	            SSLContext sslcontext = SSLContext.getInstance("TLS");
	            sslcontext.init(null, new TrustManager[] { easyTrustManager }, null);

	           // SSLSocketFactory socketFactory = new SSLSocketFactory(sslcontext,
	            //        SSLSocketFactory.STRICT_HOSTNAME_VERIFIER);

	            SSLSocketFactory socketFactory = new SSLSocketFactory(sslcontext,
	                    SSLSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);
	            
	            int port = url.getPort();
	            if (port < 0) port = 443;
	            Scheme sch = new Scheme("https", port, socketFactory);
	            client.getConnectionManager().getSchemeRegistry().register(sch);
	            
	        } catch (Exception e) {
	        	logger.error("URL({}) Exception({})", url, e);
	        	
	        }
		}
		else {
			client = new DefaultHttpClient();
		}
		
		return client;
	}
}
