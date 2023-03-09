package com.lgu.ccss.common.filter;

import java.io.IOException;

import javax.servlet.*;
import javax.servlet.http.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lgu.common.http.HttpRequestWrapper;
import com.lgu.common.http.HttpResponseWrapper;


public class HttpRequestWrapperFilter  implements Filter {
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest httpServletRequest = (HttpServletRequest) request;
		HttpRequestWrapper requestWrapper = new HttpRequestWrapper(httpServletRequest);
		HttpResponseWrapper responseWrapper = new HttpResponseWrapper((HttpServletResponse)response);
		chain.doFilter((ServletRequest)requestWrapper, responseWrapper);
		//chain.doFilter((ServletRequest)requestWrapper, response);
		
		if (!response.isCommitted()) {
            try {
            	response.getOutputStream().write(responseWrapper.getResponseBinary());
            } catch(IllegalStateException e) {
            	logger.error("#" + httpServletRequest.getRequestURL()  + " is already commited?");
                logger.error("Exception", e);
            }
        } else {
        	logger.error("#" + httpServletRequest.getRequestURL() + " is already commited.");
        }
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void init(FilterConfig paramFilterConfig) throws ServletException {
		// TODO Auto-generated method stub
		
	}

}
