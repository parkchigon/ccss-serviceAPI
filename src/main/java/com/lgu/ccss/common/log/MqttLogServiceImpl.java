package com.lgu.ccss.common.log;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.lgu.ccss.common.constant.CCSSConst;
import com.lgu.ccss.common.tlo.TloData;
import com.lgu.ccss.common.tlo.TloUtil;
import com.lgu.ccss.common.util.ResultCodeUtil;
import com.lgu.common.tlo.TloWriter;

@Component
public class MqttLogServiceImpl implements LogService {

	@Value("#{systemProperties.SERVER_ID}")
	private String serverId;
	
	@Autowired
	private TloWriter tloWriter;

	public void setRequestLog(HttpServletRequest request) {
		setRequestTloData(request);
	}
	
	public void setResponseLog(HttpServletRequest request, HttpServletResponse response) {
		setResponseTloData(request, response);

		tloWriter.write(TloUtil.getTloData());
	}

	private void setRequestTloData(HttpServletRequest request) {
		Map<String, String> tlo = new HashMap<String, String>();
		tlo.put(TloData.LOG_TYPE, "SVC");
		tlo.put(TloData.SID, request.getParameter("username"));
		tlo.put(TloData.REQ_TIME, TloData.getNowDate());

		tlo.put(TloData.CLIENT_IP, request.getRemoteAddr());
		tlo.put(TloData.DEV_INFO, CCSSConst.DEF_NONE);
		tlo.put(TloData.OS_INFO, CCSSConst.DEF_NONE);
		tlo.put(TloData.NW_INFO, CCSSConst.DEF_NONE);
		tlo.put(TloData.SVC_NAME, CCSSConst.DEF_SVC_NAME);
		tlo.put(TloData.DEV_MODEL, CCSSConst.DEF_NONE);
		tlo.put(TloData.CARRIER_TYPE, CCSSConst.DEF_NONE);
		tlo.put(TloData.SESSION_ID, CCSSConst.DEF_NONE);
		tlo.put(TloData.SERVER_ID, serverId);
		tlo.put(TloData.MEMB_ID, CCSSConst.DEF_NONE);
		tlo.put(TloData.MEMB_NO, CCSSConst.DEF_NONE);
		tlo.put(TloData.DEVICE_TYPE, CCSSConst.DEF_NONE);
		tlo.put(TloData.APP_TYPE, CCSSConst.DEF_NONE);
		tlo.put(TloData.CAR_OEM, CCSSConst.DEF_NONE);
		tlo.put(TloData.CLIENT_ID, CCSSConst.DEF_NONE);

		tlo.put(TloData.SVC_CLASS, TloUtil.getSvcClass(request.getRequestURI()));

		TloUtil.setTloData(tlo);
	}
	
	private void setResponseTloData(HttpServletRequest request, HttpServletResponse response) {
		Map<String, String> tlo = new HashMap<String, String>();
		tlo.put(TloData.RSP_TIME, TloData.getNowDate());
		
		if (response.getStatus() == HttpStatus.SC_OK) {
			tlo.put(TloData.RESULT_CODE, ResultCodeUtil.RC_2S000000.getCode());
		} else {
			tlo.put(TloData.RESULT_CODE, ResultCodeUtil.RC_4C005007.getCode());
		}

		TloUtil.setTloData(tlo);
	}
}
