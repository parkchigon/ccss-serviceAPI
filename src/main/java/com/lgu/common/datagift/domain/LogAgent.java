package com.lgu.common.datagift.domain;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.google.gson.Gson;
import com.lgu.common.rest.RestAgent;
import com.lgu.common.rest.RestRequestData;
import com.lgu.common.rest.RestResultData;
import com.lgu.common.trace.TraceWriter;
import com.lgu.common.util.ExceptionUtil;
import com.lgu.common.util.JsonUtil;

@Component
public class LogAgent {
	private static final Logger logger = LoggerFactory.getLogger(LogAgent.class);
	private static final Gson gson = new Gson();
	
	private static final int DEFAULT_CONNECTION_TIMEOUT = 5000;
	private static final int DEFAULT_TIMEOUT = 5000;

	@Autowired
	private TraceWriter traceWriter;

	@Value("#{config['data.gift.service.log.url']}")
	private String logUrl;

	@Value("#{config['data.gift.connection.timeout']}")
	private String logConnTimeout;

	@Value("#{config['data.gift.timeout']}")
	private String logTimeout;

	@Value("#{config['proxy.http.proxyHost']}")
	private String httpProxyHost;
	
	@Value("#{config['proxy.http.proxySubHost']}")
	private String httpProxySubHost;
	
	@Value("#{config['proxy.http.proxyPort']}")
	private int httpProxyPort;
	
	@Autowired
	private RestAgent restAgent;

	public LogResponseJSON sendLog(String ctn, String ccssToken, String devType, String uuid, String appVersion, String carOem, String devModel, String userCtn, 
			String result, String resultCode, String dataSize, String nwType, String posX, String posY, String logTime) {
		
		String item = null;
		String svcItem= null;
		String responseLogTime = (new SimpleDateFormat("yyyyMMddHHmmss")).format(new Date());
		if("100".equals(dataSize)) {
			item = "0";
			svcItem="데이터 상품권 100MB 구매";
		}else if ("250".equals(dataSize)) {
			item = "1";
			svcItem="데이터 상품권 250MB 구매";
		}else if ("500".equals(dataSize)) {
			item = "2";
			svcItem="데이터 상품권 500MB 구매";
		}else if ("1024".equals(dataSize)) {
			item = "3";
			svcItem="데이터 상품권 1GB 구매";
		}else if ("2048".equals(dataSize)) {
			item = "4";
			svcItem="데이터 상품권 2GB 구매";
		}else if ("5120".equals(dataSize)) {
			item = "5";
			svcItem="데이터 상품권 5GB 구매";
		}
		
		String url = logUrl + LogConst.URL_GET_SEND_LOG;
		RestRequestData reqData = new RestRequestData(url);
		
		reqData.setHeader(HTTP.CONTENT_TYPE, LogConst.HD_VALUE_CONTENTTYPE_JSON_UTF8);
		reqData.setHeader(LogConst.HD_VALUE_LGU_CCSS_CTN, ctn);
		reqData.setHeader(LogConst.HD_VALUE_LGU_CCSS_TOKEN, ccssToken);
		reqData.setHeader(LogConst.HD_VALUE_USER_AGENT, carOem+devType+uuid);
		
		
		LogRequestJSON logRequestJSON = new LogRequestJSON();
		List<LogDataJSON> logRequestJSONList = new ArrayList<LogDataJSON>();
		
		LogDataJSON logDataJSON = new LogDataJSON();
		RequestDeviceJSON requestDeviceJSON = new RequestDeviceJSON();
		RequestDetailInfoJSON requestDetailInfoJSON = new RequestDetailInfoJSON();

		logDataJSON.setSeq("1");
		logDataJSON.setLogTime(logTime);
		
		requestDeviceJSON.setDeviceType("AVN");
		requestDeviceJSON.setAppType("MANAGER_APP");
		requestDeviceJSON.setAppVer(appVersion);
		requestDeviceJSON.setSaVer("N/A");
		requestDeviceJSON.setCarOem(carOem);
		requestDeviceJSON.setDevModel(devModel);
		
		logDataJSON.setDevice(requestDeviceJSON);
		
		logDataJSON.setCtn(ctn);
		logDataJSON.setUserCtn(userCtn);
		logDataJSON.setSerial(uuid);
		logDataJSON.setUseType("touch");
		logDataJSON.setRequestTime(logTime);
		logDataJSON.setResponseTime(responseLogTime);
		logDataJSON.setResult(result);
		logDataJSON.setResultCode(resultCode);
		logDataJSON.setCategory0("5");
		logDataJSON.setItem(item);
		logDataJSON.setType("NUMBER");
		logDataJSON.setValue("1");
		logDataJSON.setNwType(nwType);
		logDataJSON.setPosX(posX);
		logDataJSON.setPosY(posY);
		
		requestDetailInfoJSON.setSvcCategory("5");
		requestDetailInfoJSON.setSvcItem(svcItem);
		logDataJSON.setSvcDetailInfo(requestDetailInfoJSON);
		
		logRequestJSONList.add(logDataJSON);
		logRequestJSON.setLog(logRequestJSONList);
		
		reqData.setJson(gson.toJson(logRequestJSON));
		
		// set params
		reqData.setConnectionTimeout(Integer.parseInt(logConnTimeout));
		reqData.setTimeout(Integer.parseInt(logTimeout));
		
		//return requestProxy(reqData);
		return requestRest(reqData);
	}
	
	private RestResultData request(RestRequestData requestData) {
		
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
		
		traceWriter.trace(requestData.getTraceId(), requestData.getSource(), requestData.getTarget(), post);
		
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
		
		traceWriter.trace(requestData.getTraceId(), requestData.getTarget(), requestData.getSource(), response, body);
		
		if (client != null) {
			client.getConnectionManager().shutdown();
		}
		
		return resultData;
	}
	
	private LogResponseJSON requestRest(RestRequestData reqData) {
		RestResultData resultData = this.request(reqData);
		if (resultData == null) {
			logger.error("failed to request Log Server.");
			return null;
		}

		if (resultData.getStatusCode() != HttpStatus.SC_OK) {
			logger.error("failed to request Log Server. statusCode({})", resultData.getStatusCode());
			return null;
		}
		
		LogResponseJSON logResponseJSON = new LogResponseJSON();
		
		try {
			logResponseJSON = gson.fromJson(resultData.getJson(), LogResponseJSON.class);
		} catch (Exception e) {
			logger.error("JSON({}) Exception({})", resultData.getJson(), ExceptionUtil.getPrintStackTrace(e));
			return null;
		}
		
		return logResponseJSON;
	}
	
	private LogResponseJSON requestProxy(RestRequestData reqData) {
		RestResultData resultData = restAgent.requestProxy(reqData, httpProxyHost, httpProxySubHost, httpProxyPort);
		if (resultData == null) {
			logger.error("failed to request Log Server.");
			return null;
		}

		if (resultData.getStatusCode() != HttpStatus.SC_OK) {
			logger.error("failed to request Log Server. statusCode({})", resultData.getStatusCode());
			return null;
		}
		
		LogResponseJSON logResponseJSON = new LogResponseJSON();
		
		try {
			logResponseJSON = gson.fromJson(resultData.getJson(), LogResponseJSON.class);
		} catch (Exception e) {
			logger.error("JSON({}) Exception({})", resultData.getJson(), ExceptionUtil.getPrintStackTrace(e));
			return null;
		}
		
		return logResponseJSON;
	}
	
	private void setBasicParams(RestRequestData requestData, HttpParams params) {
		int connectionTimeout = requestData.getConnectionTimeout();
		if (connectionTimeout <= 1000) connectionTimeout = DEFAULT_CONNECTION_TIMEOUT;
		params.setParameter(CoreConnectionPNames.CONNECTION_TIMEOUT, connectionTimeout);
		
		int timeout = requestData.getTimeout();
		if (timeout <= 1000) timeout = DEFAULT_TIMEOUT;
		params.setParameter(CoreConnectionPNames.SO_TIMEOUT, timeout);
	}
	
	private String getBody(HttpResponse response) throws IllegalStateException, IOException {
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

	public static void main(String[] args) {
		LogRequestJSON logRequestJSON = new LogRequestJSON();
		List<LogDataJSON> logRequestJSONList = new ArrayList<LogDataJSON>();
		
		LogDataJSON logDataJSON = new LogDataJSON();
		RequestDeviceJSON requestDeviceJSON = new RequestDeviceJSON();
		RequestDetailInfoJSON requestDetailInfoJSON = new RequestDetailInfoJSON();

		logDataJSON.setSeq("1");
		logDataJSON.setLogTime("20190305050055");
		
		requestDeviceJSON.setDeviceType("AVN");
		requestDeviceJSON.setAppType("BM");
		requestDeviceJSON.setAppVer("1.0");
		requestDeviceJSON.setSaVer("1.1");
		requestDeviceJSON.setCarOem("SY");
		requestDeviceJSON.setDevModel("SYMC");
		
		logDataJSON.setDevice(requestDeviceJSON);
		
		logDataJSON.setCtn("01222222222");
		logDataJSON.setUserCtn("01012341234");
		logDataJSON.setSerial("898989989898F");
		logDataJSON.setUseType("touch");
		logDataJSON.setRequestTime("201902321321321");
		logDataJSON.setResponseTime("201903123456");
		logDataJSON.setResult("sucess");
		logDataJSON.setResultCode("2S000000");
		logDataJSON.setCategory0("0");
		logDataJSON.setItem("1");
		logDataJSON.setType("NUMBER");
		logDataJSON.setValue("1");
		logDataJSON.setPosX("asdasd");
		logDataJSON.setPosY("234345345");
		
		requestDetailInfoJSON.setSvcCategory("5");
		requestDetailInfoJSON.setSvcItem("데이터상품권 50MB 구매");
		logDataJSON.setSvcDetailInfo(requestDetailInfoJSON);
		
		logRequestJSONList.add(logDataJSON);
		logRequestJSON.setLog(logRequestJSONList);
		
		try {
			System.out.println(JsonUtil.marshallingJson(logRequestJSON));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
