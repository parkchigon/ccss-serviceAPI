package com.lgu.common.esb;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.Base64;
import java.util.Base64.Encoder;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import org.apache.commons.httpclient.HttpStatus;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.CoreConnectionPNames;
import org.apache.http.params.HttpParams;
import org.apache.http.protocol.HTTP;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.lgu.common.esb.model.PayDlstResponseJSON;
import com.lgu.common.esb.oAuth.oAuthConst;
import com.lgu.common.esb.vo.PayMethodVO;
import com.lgu.common.rest.RestAgent;
import com.lgu.common.rest.RestRequestData;
import com.lgu.common.rest.RestResultData;
import com.lgu.common.util.ExceptionUtil;

public class TestESBAgent {
	private static final Logger logger = LoggerFactory.getLogger(RestAgent.class);
	private static final int DEFAULT_CONNECTION_TIMEOUT = 5000;
	private static final int DEFAULT_TIMEOUT = 5000;
	private static final Gson gson = new Gson();
	public static void main(String[] args) throws IOException {
		PayMethodVO payMethodVO = new PayMethodVO();
		PayDlstResponseJSON paymDlstResBody = requestESB();

		if (paymDlstResBody != null) {
			/*
			RetrieveBlPaymentInfoEsbServiceStub.DsPaymentInfoOutVO[] dsPaymentInfoOut = resBody_
					.getDsPaymentInfoOutVO();
					*/
			
			if (paymDlstResBody.getDsPaymentInfo() != null) {
				for(int i = 0; i < paymDlstResBody.getDsPaymentInfo().length; i++) {
					payMethodVO.setDuedDt(paymDlstResBody.getDsPaymentInfo()[i].getDuedDt());
					payMethodVO.setAddWdrwTrgtCntnt(paymDlstResBody.getDsPaymentInfo()[i].getAddWdrwTrgtCntnt());
					payMethodVO.setCardDepoNm(paymDlstResBody.getDsPaymentInfo()[i].getCardDepoNm());
					
				}

			}
		}
		System.out.println(payMethodVO.toString());
		
	}
	
public static PayDlstResponseJSON requestESB() throws UnsupportedEncodingException {
		// set request url
		String url = "https://tst-openapi.lguplus.co.kr/uplus/intuser"+"/pv/bl/bl/bt/billDlst/v1/paymDlst?billTrgtYymm=202107";
		RestRequestData reqData = new RestRequestData(url);
		String text = "502105534812"; 
		byte[] targetBytes = text.getBytes();
		Encoder encoder = Base64.getEncoder(); 
		byte[] encodedBytes = encoder.encode(targetBytes);
		String test= new String(encodedBytes);
		String encodedString = Base64.getEncoder().encodeToString(text.getBytes());
		logger.info("1111 ({})",test);
		logger.info("1111 ({})",encodedString);

		reqData.setHeader(HTTP.CONTENT_TYPE, oAuthConst.HD_VALUE_CONTENTTYPE_JSON_UTF8);
		reqData.setHeader(oAuthConst.AUTHORIZATION, "Bearer AAIkMGVhYzQ3ZjUtMzNjZi00OTM0LWFlNzctYTIzYmY4NjZiODJhq6aEUUHcIG1Fov-mOsekkw0yB7jaxOzrxlelSRAeSWInmL8u3OtAmArcGcnNdXn2242KtwaM5gWPszQd9ZiIVHW0tEY3iRnj-xuFgsX4XnEOUS1_5G_ypEEWcpx8uTpM");
		reqData.setHeader(oAuthConst.BILLACNTID, test);
		reqData.setHeader("X-IBM-Client-Id", "0eac47f5-33cf-4934-ae77-a23bf866b82a");
		reqData.setHeader("X-IBM-Client-Secret", "mB0mQ2bP1fA5lV2xG6mB1vV3jS8cO4lB6eT3nK4kQ8vV5dC3qL");
		reqData.setHeader("x-forwarded-for", "Nissan_teelmetics");
		reqData.setHeader("x-forwarded-appname", "Nissan_teelmetics");

		// set params
		reqData.setConnectionTimeout(5000);
		reqData.setTimeout(5000);

		return request1(reqData);
	}
	

	private static PayDlstResponseJSON request1(RestRequestData reqData) {
		
		RestResultData resultData = request2(reqData);
		
		PayDlstResponseJSON oAuthResp = new PayDlstResponseJSON();
		if (resultData == null) {
			logger.error("failed to request oAuth2 Server)");
			return null;
		}

		if (resultData.getStatusCode() != HttpStatus.SC_OK) {
			logger.error("failed to request oAuth2 Server.statusCode({})",
					resultData.getStatusCode());
			oAuthResp.setHttpCode(resultData.getStatusCode());
			return oAuthResp;
		}

		try {
			
			oAuthResp = gson.fromJson(resultData.getJson(), PayDlstResponseJSON.class);

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
		
		HttpGet getHttp = new HttpGet(requestData.getUrl());
		
		HttpParams params = new BasicHttpParams();
		setBasicParams(requestData, params);
		getHttp.setParams(params);
		
		for (String name : requestData.getHeaders()) {
			getHttp.setHeader(name, requestData.getHeader(name));
		}
		
		
		HttpClient client = null;
		HttpResponse response = null;
		try {
			
			client = getHttpClient(requestData.getUrl());
			
			response = client.execute(getHttp);
			
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
