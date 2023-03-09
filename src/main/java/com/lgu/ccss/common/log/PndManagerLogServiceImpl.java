package com.lgu.ccss.common.log;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.http.HttpHeaders;
import org.apache.http.HttpStatus;
import org.apache.commons.lang.StringUtils;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.lgu.ccss.common.constant.CCSSConst;
import com.lgu.ccss.common.constant.MDCConst;
import com.lgu.ccss.common.tlo.TloData;
import com.lgu.ccss.common.tlo.TloUtil;
import com.lgu.ccss.common.util.CCSSUtil;
import com.lgu.ccss.common.util.ResultCodeUtil;
import com.lgu.common.tlo.TloWriter;
import com.lgu.common.trace.TraceConst;
import com.lgu.common.trace.TraceWriter;

@Component
public class PndManagerLogServiceImpl implements LogService {
	
	private Pattern VALID_USER_AGENT_REGEX = Pattern.compile("\\[(.*)\\,(.*)\\]", Pattern.CASE_INSENSITIVE);
	
	@Value("#{systemProperties.SERVER_ID}")
	private String serverId;
	
	@Autowired
	private TloWriter tloWriter;

	@Autowired
	private TraceWriter traceWriter;
	
	public void setRequestLog(HttpServletRequest request) {
		String userAgent = request.getHeader(HttpHeaders.USER_AGENT);

		String ccssToken = "";

		Matcher matcher = VALID_USER_AGENT_REGEX.matcher(userAgent);
		if (matcher.find()) {
			
			String[] arr = StringUtils.splitByWholeSeparator(matcher.group(1), ",");
			
			if(arr.length == 2) {
				ccssToken = arr[1];
			}else {
				ccssToken = matcher.group(2);
			}			
		}		
		

		//String requestUrl = StringUtils.nvl(request.getRequestURI());
		// kangjin 20190213
		String requestUrl = com.lgu.common.util.StringUtils.nvl(request.getRequestURI());
		CCSSUtil.setCommonDataAPI(request, requestUrl, ccssToken, null);
		setRequestTloData(request);
		setRequestTrace(request);
	}
	
	public void setResponseLog(HttpServletRequest request, HttpServletResponse response) {
		setResponseTloData(request, response);
		setResponseTrace(request, response);

		tloWriter.write(TloUtil.getTloData());
	}
	
	private void setRequestTloData(HttpServletRequest req) {
		Map<String, String> tlo = new HashMap<String, String>();
		tlo.put(TloData.LOG_TYPE, "SVC");
		tlo.put(TloData.SID, CCSSUtil.getCtn());

		tlo.put(TloData.REQ_TIME, TloData.getNowDate());

		tlo.put(TloData.CLIENT_IP, req.getRemoteAddr());
		tlo.put(TloData.DEV_INFO, CCSSConst.DEF_NONE);
		tlo.put(TloData.OS_INFO, CCSSConst.DEF_NONE);
		tlo.put(TloData.NW_INFO, CCSSConst.DEF_NONE);
		tlo.put(TloData.SVC_NAME, CCSSConst.DEF_SVC_NAME);
		tlo.put(TloData.DEV_MODEL, CCSSConst.DEF_NONE);
		tlo.put(TloData.CARRIER_TYPE, CCSSConst.DEF_NONE);
		tlo.put(TloData.SESSION_ID, CCSSUtil.getCcssToken());
		tlo.put(TloData.SERVER_ID, serverId);
		tlo.put(TloData.MEMB_ID, CCSSUtil.getMembId());
		tlo.put(TloData.MEMB_NO, CCSSUtil.getMembNo());
		tlo.put(TloData.DEVICE_TYPE, CCSSConst.DEF_PND);
		tlo.put(TloData.APP_TYPE, CCSSConst.DEF_MANAGER_APP);
		tlo.put(TloData.CAR_OEM, CCSSConst.DEF_NONE);
		tlo.put(TloData.CLIENT_ID, CCSSUtil.getSerial());

		//tlo.put(TloData.SVC_CLASS, TloUtil.getSvcClass(StringUtils.nvl(req.getRequestURI())));
		// kangjin 20190213
		tlo.put(TloData.SVC_CLASS, TloUtil.getSvcClass(com.lgu.common.util.StringUtils.nvl(req.getRequestURI())));

		TloUtil.setTloData(tlo);
	}
	
	private void setResponseTloData(HttpServletRequest request, HttpServletResponse response) {
		Map<String, String> tlo = new HashMap<String, String>();
		tlo.put(TloData.RSP_TIME, TloData.getNowDate());

		if (response.getStatus() == HttpStatus.SC_OK) {
			tlo.put(TloData.RESULT_CODE, ResultCodeUtil.RC_2S000000.getCode());
		} else {
			tlo.put(TloData.RESULT_CODE, ResultCodeUtil.RC_4C005000.getCode());
		}

		TloUtil.setTloData(tlo);
	}
	
	private void setRequestTrace(HttpServletRequest request) {

		String source = "PND:MANAGER_APP";

		traceWriter.trace(CCSSUtil.getCtn(), source, TraceConst.NODE_ID_WAS, request);

		MDC.put(MDCConst.TRACE_SOURCE, source);
	}
	
	private void setResponseTrace(HttpServletRequest request, HttpServletResponse response) {
		traceWriter.trace(CCSSUtil.getCtn(), TraceConst.NODE_ID_WAS, MDC.get(MDCConst.TRACE_SOURCE), response);
	}
}
