package com.lgu.common.rest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.conn.params.ConnRoutePNames;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.CoreConnectionPNames;
import org.apache.http.params.HttpParams;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.lgu.common.trace.TraceWriter;
import com.lgu.common.util.ExceptionUtil;

@Component
public class RestAgent {

	private static final Logger logger = LoggerFactory.getLogger(RestAgent.class);
	
	private static final int DEFAULT_CONNECTION_TIMEOUT = 5000;
	private static final int DEFAULT_TIMEOUT = 5000;

	@Autowired
	private TraceWriter traceWriter;

	
	public RestResultData request(RestRequestData requestData) {
		
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
	
	public RestResultData requestProxy(RestRequestData requestData,String proxyHost,int proxyPort) {
		
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
		
		HttpHost proxy = null;
		
		if( proxyHost != null && !System.getProperty("spring.profiles.active").equals("local"))
		{
			proxy = new HttpHost(proxyHost, proxyPort, HttpHost.DEFAULT_SCHEME_NAME);
			post.getParams().setParameter(ConnRoutePNames.DEFAULT_PROXY,proxy);
		}
		
		
		try {
			if(requestData.getJson() !=null)
			post.setEntity(new StringEntity(requestData.getJson(), StandardCharsets.UTF_8.toString()));
		} catch (UnsupportedEncodingException e) {
			logger.error("Exception({})", ExceptionUtil.getPrintStackTrace(e));
			return null;
		}
		
		//traceWriter.trace(requestData.getTraceId(), requestData.getSource(), requestData.getTarget(), post);
		
		HttpClient client = null;
		HttpResponse response = null;
		try {
			//client = getHttpClientProxy(requestData.getUrl(),null,0);
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
	
	public RestResultData requestProxy(RestRequestData requestData,String proxyHost, String proxySubHost,int proxyPort) {
		
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
		
		HttpHost proxy = null;
		
		if( proxyHost != null && !System.getProperty("spring.profiles.active").equals("local"))
		{
			proxy = new HttpHost(proxyHost, proxyPort, HttpHost.DEFAULT_SCHEME_NAME);
			post.getParams().setParameter(ConnRoutePNames.DEFAULT_PROXY,proxy);
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
			try {
				response = client.execute(post);
			} catch(IOException ioe) {
				logger.error("Proxy Master Server die.");
				proxy = new HttpHost(proxySubHost, proxyPort, HttpHost.DEFAULT_SCHEME_NAME);
				post.getParams().setParameter(ConnRoutePNames.DEFAULT_PROXY,proxy);
				response = client.execute(post);
			}
			
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
	
	public RestResultData requestDelete(RestRequestData requestData) {
		
		if (logger.isDebugEnabled()) {
			logger.debug("REQUEST({})", requestData);
		}
		
		HttpDeleteWithBody delete = new HttpDeleteWithBody(requestData.getUrl());
		
		HttpParams params = new BasicHttpParams();
		setBasicParams(requestData, params);
		delete.setParams(params);
		
		for (String name : requestData.getHeaders()) {
			delete.setHeader(name, requestData.getHeader(name));
		}
		try {
			delete.setEntity(new StringEntity(requestData.getJson(), StandardCharsets.UTF_8.toString()));
		} catch (UnsupportedEncodingException e) {
			logger.error("Exception({})", ExceptionUtil.getPrintStackTrace(e));
			return null;
		}
		
		traceWriter.trace(requestData.getTraceId(), requestData.getSource(), requestData.getTarget(), delete);
		
		HttpClient client = null;
		HttpResponse response = null;
		try {
			client = getHttpClient(requestData.getUrl());
			response = client.execute(delete);
			
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
	
	public RestResultData requestDeleteProxy(RestRequestData requestData, String proxyHost, String proxySubHost,int proxyPort) {
		
		if (logger.isDebugEnabled()) {
			logger.debug("REQUEST({})", requestData);
		}
		
		HttpDeleteWithBody delete = new HttpDeleteWithBody(requestData.getUrl());
		
		HttpParams params = new BasicHttpParams();
		setBasicParams(requestData, params);
		delete.setParams(params);
		
		for (String name : requestData.getHeaders()) {
			delete.setHeader(name, requestData.getHeader(name));
		}
		
		HttpHost proxy = null;
		
		if( proxyHost != null && !System.getProperty("spring.profiles.active").equals("local"))
		{
			proxy = new HttpHost(proxyHost, proxyPort, HttpHost.DEFAULT_SCHEME_NAME);
			delete.getParams().setParameter(ConnRoutePNames.DEFAULT_PROXY,proxy);
		}
		
		try {
			delete.setEntity(new StringEntity(requestData.getJson(), StandardCharsets.UTF_8.toString()));
		} catch (UnsupportedEncodingException e) {
			logger.error("Exception({})", ExceptionUtil.getPrintStackTrace(e));
			return null;
		}
		
		traceWriter.trace(requestData.getTraceId(), requestData.getSource(), requestData.getTarget(), delete);
		
		HttpClient client = null;
		HttpResponse response = null;
		try {
			client = getHttpClient(requestData.getUrl());
			try {
				response = client.execute(delete);
			} catch(IOException ioe) {
				logger.debug("Proxy Master Server die.");
				proxy = new HttpHost(proxySubHost, proxyPort, HttpHost.DEFAULT_SCHEME_NAME);
				delete.getParams().setParameter(ConnRoutePNames.DEFAULT_PROXY,proxy);
				response = client.execute(delete);
			}
			
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

	public RestResultData requestGET(RestRequestData requestData, String proxyHost, int proxyPort) {
		
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
		
		HttpHost proxy = null;
		if( proxyHost != null )
		{
			proxy = new HttpHost(proxyHost, proxyPort, HttpHost.DEFAULT_SCHEME_NAME);
			getHttp.getParams().setParameter(ConnRoutePNames.DEFAULT_PROXY,proxy);
		}
		
		traceWriter.trace(requestData.getTraceId(), requestData.getSource(), requestData.getTarget(), getHttp);
		
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
		
		traceWriter.trace(requestData.getTraceId(), requestData.getTarget(), requestData.getSource(), response, body);
		
		if (client != null) {
			client.getConnectionManager().shutdown();
		}
		
		return resultData;
	}
	
	public RestResultData requestGET(RestRequestData requestData,String clovaToken, String proxyHost, String proxySubHost ,int proxyPort) {
		
		if (logger.isDebugEnabled()) {
			logger.debug("REQUEST({})", requestData);
		}
		
		HttpGet getHttp = new HttpGet(requestData.getUrl());
		
		for (String name : requestData.getHeaders()) {
			getHttp.addHeader(name, requestData.getHeader(name));
		}
		
		if (logger.isDebugEnabled()) {
			logger.debug("REQUEST({})", getHttp.toString());
		}
		
		HttpHost proxy = null;
		if( proxyHost != null )
		{
			proxy = new HttpHost(proxyHost, proxyPort, HttpHost.DEFAULT_SCHEME_NAME);
			getHttp.getParams().setParameter(ConnRoutePNames.DEFAULT_PROXY,proxy);
		}
		
		traceWriter.trace(requestData.getTraceId(), requestData.getSource(), requestData.getTarget(), getHttp);
		
		HttpClient client = null;
		HttpResponse response = null;
		try {
			
			client = getHttpClient(requestData.getUrl());
			
			try {
				response = client.execute(getHttp);
			} catch(IOException ioe) {
				logger.debug("Proxy Master Server die.");
				proxy = new HttpHost(proxySubHost, proxyPort, HttpHost.DEFAULT_SCHEME_NAME);
				getHttp.getParams().setParameter(ConnRoutePNames.DEFAULT_PROXY,proxy);
				response = client.execute(getHttp);
			}
			
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
	
	public RestResultData requestGET(RestRequestData requestData, String proxyHost, String proxySubHost ,int proxyPort) {
		
		if (logger.isDebugEnabled()) {
			logger.debug("REQUEST({})", requestData);
		}
		
		HttpGet getHttp = new HttpGet(requestData.getUrl());
		
		for (String name : requestData.getHeaders()) {
			getHttp.addHeader(name, requestData.getHeader(name));
		}
		
		if (logger.isDebugEnabled()) {
			logger.debug("REQUEST({})", getHttp.toString());
		}
		
		HttpHost proxy = null;
		if( proxyHost != null )
		{
			proxy = new HttpHost(proxyHost, proxyPort, HttpHost.DEFAULT_SCHEME_NAME);
			getHttp.getParams().setParameter(ConnRoutePNames.DEFAULT_PROXY,proxy);
		}
		
		traceWriter.trace(requestData.getTraceId(), requestData.getSource(), requestData.getTarget(), getHttp);
		
		HttpClient client = null;
		HttpResponse response = null;
		try {
			
			client = getHttpClient(requestData.getUrl());
			
			try {
				response = client.execute(getHttp);
			} catch(IOException ioe) {
				logger.debug("Proxy Master Server die.");
				proxy = new HttpHost(proxySubHost, proxyPort, HttpHost.DEFAULT_SCHEME_NAME);
				getHttp.getParams().setParameter(ConnRoutePNames.DEFAULT_PROXY,proxy);
				response = client.execute(getHttp);
			}
			
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
	
	public RestResultData requestGET(RestRequestData requestData) {
		
		if (logger.isDebugEnabled()) {
			logger.debug("REQUEST({})", requestData);
		}
		
		HttpGet getHttp = new HttpGet(requestData.getUrl());
		
		for (String name : requestData.getHeaders()) {
			getHttp.addHeader(name, requestData.getHeader(name));
		}
		
		if (logger.isDebugEnabled()) {
			logger.debug("REQUEST({})", getHttp.toString());
		}
		
		traceWriter.trace(requestData.getTraceId(), requestData.getSource(), requestData.getTarget(), getHttp);
		
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
		
		traceWriter.trace(requestData.getTraceId(), requestData.getTarget(), requestData.getSource(), response, body);
		
		if (client != null) {
			client.getConnectionManager().shutdown();
		}
		
		return resultData;
	}
	
	public RestResultData requestPUT(RestRequestData requestData, String proxyHost, String proxySubHost ,int proxyPort) {
		
		if (logger.isDebugEnabled()) {
			logger.debug("REQUEST({})", requestData);
		}
		
		HttpPut putHttp = new HttpPut(requestData.getUrl());
		
		HttpParams params = new BasicHttpParams();
		setBasicParams(requestData, params);
		putHttp.setParams(params);
		
		if (logger.isDebugEnabled()) {
			logger.debug("PARAMS({})", params.toString());
		}
		
		for (String name : requestData.getHeaders()) {
			putHttp.addHeader(name, requestData.getHeader(name));
		}
		
		HttpHost proxy = null;
		if( proxyHost != null )
		{
			proxy = new HttpHost(proxyHost, proxyPort, HttpHost.DEFAULT_SCHEME_NAME);
			putHttp.getParams().setParameter(ConnRoutePNames.DEFAULT_PROXY,proxy);
		}
		try {
			if(requestData.getJson() !=null)
				putHttp.setEntity(new StringEntity(requestData.getJson(), StandardCharsets.UTF_8.toString()));
		} catch (UnsupportedEncodingException e) {
			logger.error("Exception({})", ExceptionUtil.getPrintStackTrace(e));
			return null;
		}
		
		
		traceWriter.trace(requestData.getTraceId(), requestData.getSource(), requestData.getTarget(), putHttp);
		
		HttpClient client = null;
		HttpResponse response = null;
		try {
			
			client = getHttpClient(requestData.getUrl());
			
			try {
				response = client.execute(putHttp);
			} catch(IOException ioe) {
				logger.debug("Proxy Master Server die.");
				proxy = new HttpHost(proxySubHost, proxyPort, HttpHost.DEFAULT_SCHEME_NAME);
				putHttp.getParams().setParameter(ConnRoutePNames.DEFAULT_PROXY,proxy);
				response = client.execute(putHttp);
			}
			
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
	
	public static void main(String[] args) throws Exception {
		//String url = "https://msp.f-secure.com/web-test/common/test.html";
		//String url = "https://auth.aisvc.uplus.co.kr/auth";
		//String url = "http://www.google.com";
		String url = "https://106.103.227.86:18000";
		
		
		HttpClient httpclient = getHttpClient(url);
        HttpPost httppost = new HttpPost(url);

        System.out.println("executing request " + httppost.getRequestLine());

        HttpResponse response = httpclient.execute(httppost);
        HttpEntity entity = response.getEntity();
        String responseBody = EntityUtils.toString(response.getEntity(),"UTF-8");

        System.out.println(responseBody);

        System.out.println("--------------------");
        System.out.println(response.getStatusLine());
        if (entity != null) {
            System.out.println("Response content length: "
                    + entity.getContentLength());
        }
        EntityUtils.consume(entity);
	}
}

