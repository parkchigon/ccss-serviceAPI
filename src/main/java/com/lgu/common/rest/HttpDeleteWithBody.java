package com.lgu.common.rest;

import java.net.URI;

import org.apache.http.client.methods.HttpEntityEnclosingRequestBase;

public class HttpDeleteWithBody  extends HttpEntityEnclosingRequestBase {

	public static final String METHOD_NAME = "DELETE";
			
	@Override
	public String getMethod() {
		return METHOD_NAME;
	}
	
	public HttpDeleteWithBody(final String url) {
		super();
		setURI(URI.create(url));
	}
	public HttpDeleteWithBody(final URI uri){
		super();
		setURI(uri);
	}
	public HttpDeleteWithBody(){
		super();
	}
}