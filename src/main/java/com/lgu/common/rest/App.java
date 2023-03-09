/*package com.lgu.common.rest;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.NTCredentials;
import org.apache.http.client.AuthCache;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.impl.client.BasicAuthCache;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.DefaultProxyRoutePlanner;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;



public class App {
	final static String PROXY_ADDRESS = "192.168.32.91"; // proxy (IP) address

	final static String USERNAME = ""; // username for proxy authentication

	final static String PASSWORD = ""; // password for proxy authentication

	final static String PROXY_DOMAIN = ""; // proxy domain

	@Autowired
	public static void main(String[] args) throws UnsupportedEncodingException {

		String url = "https://auth.clova.ai/authorize"; // url we want to make
														// request to

		// the rest is combination of documentation for apache http library and
		// Stackoverflow Q&A :)
		HttpHost proxy = new HttpHost(PROXY_ADDRESS, 9080);
		DefaultProxyRoutePlanner routePlanner = new DefaultProxyRoutePlanner(
				proxy);
		// HttpGet get = new HttpGet(url);
		HttpPost post = new HttpPost(url);

		List<NameValuePair> urlParameters = new ArrayList<NameValuePair>();
		urlParameters.add(new BasicNameValuePair("client_id","36b0087a7029752f7e416a35a0fdb220"));
		urlParameters.add(new BasicNameValuePair("device_id","8888888888822228888F"));
		urlParameters.add(new BasicNameValuePair("model_id", "ccs_test"));
		urlParameters.add(new BasicNameValuePair("response_type", "code"));
		urlParameters.add(new BasicNameValuePair("state", "1234567890"));
		urlParameters.add(new BasicNameValuePair("request_vu", "Y"));

		post.setEntity(new UrlEncodedFormEntity(urlParameters));

		AuthCache authCache = new BasicAuthCache();
		CredentialsProvider credsProvider = new BasicCredentialsProvider();
		credsProvider.setCredentials(new AuthScope(PROXY_ADDRESS, 9080,
				AuthScope.ANY_HOST, "ntlm"), new NTCredentials(USERNAME,
				PASSWORD, "", PROXY_DOMAIN));
		HttpClientContext context = HttpClientContext.create();
		context.setCredentialsProvider(credsProvider);
		context.setAuthCache(authCache);

		CloseableHttpClient httpclient = HttpClients.custom()
				.setDefaultCredentialsProvider(credsProvider)
				.setRoutePlanner(routePlanner).build();

		try {
			HttpResponse resp = httpclient.execute(post, context);
			int httpCode = resp.getStatusLine().getStatusCode();
			HttpEntity entity = resp.getEntity();
			String responseString = EntityUtils.toString(entity, "UTF-8");
			System.out.println(httpCode);
			System.out.println(responseString);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}
*/