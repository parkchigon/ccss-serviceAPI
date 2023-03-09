package com.lgu.ccss.common.log;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface LogService {
	public void setRequestLog(HttpServletRequest request);
	public void setResponseLog(HttpServletRequest request, HttpServletResponse response);
}
