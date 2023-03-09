package com.lgu.common.ncas;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.SocketTimeoutException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.CoreConnectionPNames;
import org.apache.http.params.HttpParams;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.lgtel.mmdb.CasCrypto;
import com.lgu.common.trace.TraceWriter;
import com.lgu.common.util.ExceptionUtil;

@Component
public class NCASQueryManager {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Value("#{config['ncas.url']}")
	private String URL;

	@Value("#{config['ncas.cp.type']}")
	private String CP_TYPE;

	@Value("#{config['ncas.cp.id']}")
	public String CP_ID;

	@Value("#{config['ncas.cp.pwd']}")
	public String CP_PWD;

	@Value("#{config['ncas.cp.casecode']}")
	public String CASE_CODE;

	@Value("#{config['ncas.connection.timeout']}")
	public String CAS_CONNECTION_TIMEOUT;

	@Value("#{config['ncas.timeout']}")
	public String CAS_TIMEOUT;

	@Value("#{config['ncas.sim.use']}")
	public String SIM_CAS_USE;

	@Value("#{config['ncas.sim.url']}")
	public String SIM_CAS_URL;

	@Autowired
	private TraceWriter traceWriter;

	public NCASResultData query(String ctn) {
		NCASResultData result = null;

		if (SIM_CAS_USE.equals("Y")) {
			result = query(URL, ctn);
			if (result == null || result.getSubsInfo() == null) {
				result = query(SIM_CAS_URL, ctn);
			}

		} else {
			result = query(URL, ctn);
		}

		return result;
	}

	@SuppressWarnings("deprecation")
	public NCASResultData query(String requestUrl, String ctn) {

		logger.debug("NCAS URL : " + requestUrl);
		logger.debug("NCAS CP_TYPE : " + CP_TYPE);
		logger.debug("NCAS CP_ID : " + CP_ID);
		logger.debug("NCAS CP_PWD : " + CP_PWD);
		logger.debug("NCAS CASE_CODE : " + CASE_CODE);
		logger.debug("NCAS CAS_CONNECTION_TIMEOUT : " + CAS_CONNECTION_TIMEOUT);
		logger.debug("NCAS CAS_TIMEOUT : " + CAS_TIMEOUT);

		StringBuilder sb = new StringBuilder();
		sb.append("?CPTYPE=" + CP_TYPE);
		sb.append("&CPID=" + CP_ID);
		sb.append("&CPPWD="
				+ URLEncoder.encode(CasCrypto.casCryptoEncode(
						"E645919BADBAD0D9", "D076AEABE5BC7585",
						"DF89BCE93B70CD13", "D91C3245767F1C0E", CP_PWD)));
		sb.append("&CASECODE=" + CASE_CODE);
		sb.append("&CTN=" + changeCTNFormat(ctn));

		HttpGet get = new HttpGet(requestUrl + sb.toString());
		HttpParams params = new BasicHttpParams();
		params.setParameter(CoreConnectionPNames.CONNECTION_TIMEOUT,
				Integer.parseInt(CAS_CONNECTION_TIMEOUT));
		params.setParameter(CoreConnectionPNames.SO_TIMEOUT,
				Integer.parseInt(CAS_TIMEOUT));
		get.setParams(params);
		get.setHeader("Connection", "close");

		traceWriter.trace(ctn, NCASConst.TRACE_SOURCE, NCASConst.TRACE_TARGET,
				get);

		HttpResponse response = null;
		try {
			response = requestHttp(get);
		} catch (IOException e) {
			logger.error("ctn({}) Exception({})", ctn,
					ExceptionUtil.getPrintStackTrace(e));
		}

		if (response == null) {
			return null;
		}

		traceWriter.trace(ctn, NCASConst.TRACE_TARGET, NCASConst.TRACE_SOURCE,
				response, null);

		if (response.getStatusLine().getStatusCode() != HttpStatus.SC_OK) {
			logger.error("ctn({}) statusCode({})", ctn, response
					.getStatusLine().getStatusCode());
			return null;
		}

		NCASResultData ncasData = null;
		for (Header header : response.getAllHeaders()) {
			if ("RESP".equals(header.getName())) {
				ncasData = getResultData(header);
			}
		}

		if (ncasData == null) {
			logger.error("NCAS ERROR. ctn({}) ncasCtn({}) headers({})", ctn,
					changeCTNFormat(ctn), response.getAllHeaders());
			return null;
		}

		if (!ncasData.getRespCode().equals(NCASErrorCode.ERRORCODE_SUCCESS)) {
			logger.error("NCAS ERROR. ctn({}) ncasCtn({}) respCode({})", ctn,
					changeCTNFormat(ctn), ncasData.getRespCode());
			return ncasData;
		}

		return ncasData;
	}

	public HttpResponse requestHttp(HttpGet get) throws IOException {
		HttpClient client = new DefaultHttpClient();
		HttpResponse response;

		try {
			response = client.execute(get);

		} catch (ClientProtocolException e) {
			logger.error("NCAS getSubsInfo Exception", e);
			throw new ClientProtocolException();
		} catch (SocketTimeoutException e) {
			logger.error("NCAS getSubsInfo Exception", e);
			throw new SocketTimeoutException();
		} catch (IOException e) {
			logger.error("NCAS getSubsInfo Exception", e);
			throw new IOException();
		}

		return response;
	}

	private NCASResultData getResultData(Header header) {
		boolean casResult = false;
		SubsInfo subsInfo = null;
		String respCode = null;

		String[] result = header.getValue().split("&");
		String key = null;
		String value = null;

		Map<String, String> resultBodyMap = new HashMap<String, String>();

		for (String data : result) {
			logger.debug("NCAS BODY DATA:" + data);

			if (data.split("=") == null) {
				continue;
			}

			key = data.split("=")[0];

			if (data.split("=").length == 2) {
				value = data.split("=")[1];
			} else {
				logger.debug("NCAS NO VALUE " + data);
				value = null;
			}
			
			if (key.equalsIgnoreCase("svc_auth_dt")) {
				String svcAuthDtStr = null;
				try {
					svcAuthDtStr = java.net.URLDecoder.decode(value, "UTF-8");
					logger.debug("svcAuthDt BODY DATA:" + svcAuthDtStr);
				} catch (UnsupportedEncodingException e) {
					e.printStackTrace();
				}
				String[] svcAuthDt = svcAuthDtStr.split("\\|");
				
				for(int i = 0 ; i < svcAuthDt.length ; i++){
					logger.debug("svcAuth.array_ : " + svcAuthDt[i]);
				}
				
				if (svcAuthDt != null && svcAuthDt.length ==14) {
					for(int i = 13; i>=11 ; i--){
						if (svcAuthDt[i]!=null&&!svcAuthDt[i].equals("0")) {
							String findRegDate = svcAuthDt[i].substring(0,4) + "-" + svcAuthDt[i].substring(4,6) + "-" + svcAuthDt[i].substring(6,8)+
									"+"+svcAuthDt[i].substring(8,10)+"%3A"+svcAuthDt[i].substring(10,12)+"%3A00.0";
							resultBodyMap.replace("REGDATE", findRegDate);
							break;
						}
					}
					for(int i = 0; i <= 4 ; i++){
						if (svcAuthDt[i]!=null&&!svcAuthDt[i].equals("0")) {
							String findRegDate = svcAuthDt[i].substring(0,4) + "-" + svcAuthDt[i].substring(4,6) + "-" + svcAuthDt[i].substring(6,8)+
									"+"+svcAuthDt[i].substring(8,10)+"%3A"+svcAuthDt[i].substring(10,12)+"%3A00.0";
							resultBodyMap.replace("REGDATE", findRegDate);
							break;
						}
					}
				}
			}

			if (NCASConst.ncas_field_respcode.equals(key)) {
				respCode = value;
				if (NCASErrorCode.ERRORCODE_SUCCESS.equals(respCode)) {
					casResult = true;
				} else {
					// logger.error("NCAS ERROR. CTN:" + ctn + ", NCAS_CTN:" +
					// changeCTNFormat(ctn)
					// + ", respCode:"
					// + respCode);
					break;
				}
			}

			resultBodyMap.put(key, value);
		}

		if (casResult && resultBodyMap != null) {
			subsInfo = new SubsInfo(resultBodyMap);
		}

		return new NCASResultData(subsInfo, respCode);
	}

	public String changeCTNFormat(String strMin) {
		if (strMin == null || strMin.length() == 0)
			return null;
		int len = strMin.length();
		String newMin = "", first = "", last = "";
		try {
			switch (len) {
			case 12: // 0190 1234 5678
				newMin = strMin;
				break;
			case 11: // 019 1234 5678
				first = strMin.substring(0, 3);
				last = strMin.substring(3, strMin.length());
				newMin = first + "0" + last;
				break;
			case 10: // 019 123 4567
				first = strMin.substring(0, 3);
				last = strMin.substring(3, strMin.length());
				newMin = first + "00" + last;
				break;
			default:
				return null;
			}
			if (newMin.length() != 12) {
				logger.error("changeCTNFormat Invalid MIN " + newMin.length());
				return null;
			}
			return newMin;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}
