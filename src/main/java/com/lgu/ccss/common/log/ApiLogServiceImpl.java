package com.lgu.ccss.common.log;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.google.gson.Gson;
import com.lgu.ccss.common.constant.MDCConst;
import com.lgu.ccss.common.model.RequestCommonJSON;
import com.lgu.ccss.common.model.RequestJSON;
import com.lgu.ccss.common.model.ResponseJSON;
import com.lgu.ccss.common.tlo.TloData;
import com.lgu.ccss.common.tlo.TloUtil;
import com.lgu.ccss.common.util.CCSSUtil;
import com.lgu.common.http.HttpRequestWrapper;
import com.lgu.common.http.HttpResponseWrapper;
import com.lgu.common.tlo.TloWriter;
import com.lgu.common.trace.TraceConst;
import com.lgu.common.trace.TraceWriter;
import com.lgu.common.util.StringUtils;

@Component
public class ApiLogServiceImpl implements LogService {

	@Value("#{systemProperties.SERVER_ID}")
	private String serverId;
	
	@Autowired
	private TloWriter tloWriter;

	@Autowired
	private TraceWriter traceWriter;

	public void setRequestLog(HttpServletRequest request) {
		HttpRequestWrapper reqWrapper = (HttpRequestWrapper) request;

		byte[] body = reqWrapper.getBodyData();
		String content = new String(body, 0, body.length);

		Gson gson = new Gson();
		RequestJSON apiReq = gson.fromJson(content, RequestJSON.class);

		MDC.put(MDCConst.API_ID, apiReq.getCommon().getApiId());

		String requestUrl = StringUtils.nvl(request.getRequestURI());
		CCSSUtil.setCommonDataAPI(request, requestUrl, reqWrapper.getHeader("ccssToken"), apiReq);
		setRequestTrace(reqWrapper, apiReq);
		setRequestTloData(reqWrapper, apiReq);
	}
	
	public void setResponseLog(HttpServletRequest request, HttpServletResponse response) {
		HttpResponseWrapper resWrapper = (HttpResponseWrapper) response;

		String content = resWrapper.getResponseText();

		Gson gson = new Gson();
		ResponseJSON apiRes = gson.fromJson(content, ResponseJSON.class);

		setResponseTrace(resWrapper);
		setResponseTloData(apiRes);

		tloWriter.write(TloUtil.getTloData());
	}
	
	private void setRequestTloData(HttpRequestWrapper reqWrapper, RequestJSON apiReq) {
		RequestCommonJSON common = apiReq.getCommon();

		Map<String, String> tlo = new HashMap<String, String>();
		tlo.put(TloData.LOG_TYPE, "SVC");
		tlo.put(TloData.SID, CCSSUtil.getCtn());

		tlo.put(TloData.REQ_TIME, TloData.getNowDate());

		tlo.put(TloData.CLIENT_IP, reqWrapper.getRemoteAddr());
		tlo.put(TloData.DEV_INFO, common.getLogData().getDevInfo());
		tlo.put(TloData.OS_INFO, common.getLogData().getOsInfo());
		tlo.put(TloData.NW_INFO, common.getLogData().getNwInfo());
		tlo.put(TloData.SVC_NAME, common.getLogData().getSvcName());
		tlo.put(TloData.DEV_MODEL, common.getLogData().getDevModel());
		tlo.put(TloData.CARRIER_TYPE, common.getLogData().getCarrierType());
		tlo.put(TloData.SESSION_ID, CCSSUtil.getCcssToken());
		tlo.put(TloData.SERVER_ID, serverId);
		tlo.put(TloData.MEMB_ID, CCSSUtil.getMembId());
		tlo.put(TloData.MEMB_NO, CCSSUtil.getMembNo());
		tlo.put(TloData.DEVICE_TYPE, common.getDevice().getDeviceType());
		tlo.put(TloData.APP_TYPE, common.getDevice().getAppType());
		tlo.put(TloData.CAR_OEM, common.getDevice().getCarOem());
		tlo.put(TloData.CLIENT_ID, CCSSUtil.getSerial());

		tlo.put(TloData.SVC_CLASS, TloUtil.getSvcClass(common.getApiId()));

		TloUtil.setTloData(tlo);
	}
	
	private void setResponseTloData(ResponseJSON apiRes) {
		Map<String, String> tlo = new HashMap<String, String>();
		tlo.put(TloData.RSP_TIME, TloData.getNowDate());
		tlo.put(TloData.RESULT_CODE, apiRes.getResultCode());

		TloUtil.setTloData(tlo);
	}
	
	private void setRequestTrace(HttpRequestWrapper reqWrapper, RequestJSON apiReq) {

		String source = apiReq.getCommon().getDevice().getDeviceType() + ":"
				+ apiReq.getCommon().getDevice().getAppType();

		traceWriter.trace(CCSSUtil.getCtn(), source, TraceConst.NODE_ID_WAS, reqWrapper);

		MDC.put(MDCConst.TRACE_SOURCE, source);
	}
	
	private void setResponseTrace(HttpResponseWrapper resWrapper) {

		traceWriter.trace(CCSSUtil.getCtn(), TraceConst.NODE_ID_WAS, MDC.get(MDCConst.TRACE_SOURCE), resWrapper);
	}
}
