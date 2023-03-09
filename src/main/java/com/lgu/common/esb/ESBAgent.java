package com.lgu.common.esb;

import java.io.UnsupportedEncodingException;
import java.util.Base64;
import java.util.Base64.Encoder;

import org.apache.commons.httpclient.HttpStatus;
import org.apache.http.protocol.HTTP;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.lgu.common.esb.model.DcntDlstBltxResponseJSON;
import com.lgu.common.esb.model.MmlyRmndUsagOverResponseJSON;
import com.lgu.common.esb.model.PayDlstResponseJSON;
import com.lgu.common.esb.oAuth.oAuthAgent;
import com.lgu.common.esb.oAuth.oAuthConst;
import com.lgu.common.rest.RestAgent;
import com.lgu.common.rest.RestRequestData;
import com.lgu.common.rest.RestResultData;
import com.lgu.common.util.ExceptionUtil;

@Component
public class ESBAgent {

	private static final Logger logger = LoggerFactory.getLogger(oAuthAgent.class);
	private static final Gson gson = new Gson();

	@Value("#{config['esb.oauth.baseurl']}")
	private String esbOAuthUrl;

	@Value("#{config['auth.http.proxyHost']}")
	private String authHttpProxyHost;
	
	@Value("#{config['auth.http.proxySubHost']}")
	private String authHttpProxySubHost;
	
	@Value("#{config['auth.http.proxyPort']}")
	private int authHttpProxyPort;
	
	@Value("#{config['auth.client.id']}")
	private String authClientId;

	@Value("#{config['auth.client.secret']}")
	private String authClientSecret;
	
	@Autowired
	private RestAgent restAgent;
	
	public PayDlstResponseJSON requestPaymDlst(String urls, String billAcntId, String billTrgtYymm, String auth, String ip) throws UnsupportedEncodingException {
		//3차 APIM 변경시 삭제 예정
		//String noEncoder = billAcntId; 
		/*byte[] targetBytes = billAcntId.getBytes();
		Encoder encoder = Base64.getEncoder(); 
		byte[] encodedBytes = encoder.encode(targetBytes);
		String encoderBillAcntId= new String(encodedBytes);*/
		String encoderBillAcntId = Base64.getEncoder().encodeToString(billAcntId.getBytes());
		//logger.info("encoderBillAcntId ({})",encoderBillAcntId);
		//logger.info("encoderBillAcntId111 ({})",encoderBillAcntId);

		// set request url
		String url = urls+"/pv/bl/bl/bt/billDlst/v1/paymDlst?billTrgtYymm=" + billTrgtYymm;
		//String url = urls+"/pv/bl/bl/bt/billDlst/v1/paymDlst?billTrgtYymm=202107";
		RestRequestData reqData = new RestRequestData(url);
		reqData.setHeader(HTTP.CONTENT_TYPE, oAuthConst.HD_VALUE_CONTENTTYPE_JSON_UTF8);
		reqData.setHeader(oAuthConst.AUTHORIZATION, auth);
		reqData.setHeader(oAuthConst.BILLACNTID, encoderBillAcntId);
		reqData.setHeader(oAuthConst.X_IBM_CLIENT_ID, authClientId);
		reqData.setHeader(oAuthConst.X_IBM_CLIENT_SECRET, authClientSecret);
		reqData.setHeader(oAuthConst.X_FORWARDED_FOR, ip);
		reqData.setHeader(oAuthConst.X_FORWARDED_APPNAME, "Nissan_telematics");

		// set params
		reqData.setConnectionTimeout(5000);
		reqData.setTimeout(5000);

		return request(reqData);
	}
	
	public DcntDlstBltxResponseJSON requestDcntDlstBltx(String urls, String billAcntId, String billTrgtYymm, String auth, String ip) throws UnsupportedEncodingException {
		//3차 APIM 변경시 삭제 예정
/*		String noEncoder = billAcntId; 
		byte[] targetBytes = noEncoder.getBytes();
		Encoder encoder = Base64.getEncoder(); 
		byte[] encodedBytes = encoder.encode(targetBytes);
		String encoderBillAcntId= new String(encodedBytes);*/
		String encoderBillAcntId = Base64.getEncoder().encodeToString(billAcntId.getBytes());
		//logger.info("encoderBillAcntId ({})",encoderBillAcntId);

		// set request url
		String url = urls+"/pv/bl/bl/bt/billDlst/v1/dcntDlstBltx?billTrgtYymm=" + billTrgtYymm + "&fromPage=1&toPage=9999";
		//String url = urls+"/pv/bl/bl/bt/billDlst/v1/dcntDlstBltx?billTrgtYymm=202106&fromPage=1&toPage=9999";
		RestRequestData reqData = new RestRequestData(url);
		reqData.setHeader(HTTP.CONTENT_TYPE, oAuthConst.HD_VALUE_CONTENTTYPE_JSON_UTF8);
		reqData.setHeader(oAuthConst.AUTHORIZATION, auth);
		reqData.setHeader(oAuthConst.BILLACNTID, encoderBillAcntId);
		reqData.setHeader(oAuthConst.X_IBM_CLIENT_ID, authClientId);
		reqData.setHeader(oAuthConst.X_IBM_CLIENT_SECRET, authClientSecret);
		reqData.setHeader(oAuthConst.X_FORWARDED_FOR, ip);
		reqData.setHeader(oAuthConst.X_FORWARDED_APPNAME, "Nissan_telematics");

		// set params
		reqData.setConnectionTimeout(5000);
		reqData.setTimeout(5000);

		return requestDcntDlst(reqData);
	}

	private  PayDlstResponseJSON request(RestRequestData reqData) {
	
		RestResultData resultData = restAgent.requestGET(reqData);
	
		PayDlstResponseJSON paymDlstResp = new PayDlstResponseJSON();
		if (resultData == null) {
			logger.error("failed to request APIM Server)");
			return null;
		}
	
		if (resultData.getStatusCode() != HttpStatus.SC_OK) {
			logger.error("failed to request paymDlstResp Server.statusCode({})",
					resultData.getStatusCode());
			paymDlstResp.setHttpCode(resultData.getStatusCode());
			return paymDlstResp;
		}
	
		try {
			paymDlstResp.setHttpCode(resultData.getStatusCode());
			paymDlstResp = gson.fromJson(resultData.getJson(), PayDlstResponseJSON.class);
	
		} catch (JsonSyntaxException e) {
			logger.error("JSON({}) Exception({})", resultData.getJson(), ExceptionUtil.getPrintStackTrace(e));
			return null;
		}
	
		if (paymDlstResp == null ) {
			logger.error("failed to request PaymDlst data. paymDlstResp({})", paymDlstResp);
			return null;
		}
		
		return paymDlstResp;
	}
	
	private DcntDlstBltxResponseJSON requestDcntDlst(RestRequestData reqData) {
		
		RestResultData resultData = restAgent.requestGET(reqData);
	
		DcntDlstBltxResponseJSON dcntDlstBltxResp = new DcntDlstBltxResponseJSON();
		if (resultData == null) {
			logger.error("failed to request APIM Server)");
			return null;
		}
	
		if (resultData.getStatusCode() != HttpStatus.SC_OK) {
			logger.error("failed to request paymDlstResp Server.statusCode({})",
					resultData.getStatusCode());
			dcntDlstBltxResp.setHttpCode(resultData.getStatusCode());
			return dcntDlstBltxResp;
		}
	
		try {
			dcntDlstBltxResp.setHttpCode(resultData.getStatusCode());
			dcntDlstBltxResp = gson.fromJson(resultData.getJson(), DcntDlstBltxResponseJSON.class);
	
		} catch (JsonSyntaxException e) {
			logger.error("JSON({}) Exception({})", resultData.getJson(), ExceptionUtil.getPrintStackTrace(e));
			return null;
		}
	
		if (dcntDlstBltxResp == null ) {
			logger.error("failed to request DcntDlstBltxResp data. paymDlstResp({})", dcntDlstBltxResp);
			return null;
		}
		
		return dcntDlstBltxResp;
	}
	
	public MmlyRmndUsagOverResponseJSON requestMmlyRmndUsagOver(String urls, String callYm, String prodNo,String aceno, String entrNo, String billAcntNo, String oAuthAccessToken, String ip) throws UnsupportedEncodingException {
		//3차 APIM 변경시 삭제 예정
		//prodNo 인코더
		
		String encoderProdNo = Base64.getEncoder().encodeToString(prodNo.getBytes());
		//entrNo 인코더
	
		String encoderEntrNo = Base64.getEncoder().encodeToString(entrNo.getBytes());
	
		//entrNo 인코더
		String encoderBillAcntNo = Base64.getEncoder().encodeToString(billAcntNo.getBytes());
		
		//aceno 인코더
		String encoderAceno = Base64.getEncoder().encodeToString(aceno.getBytes());
		
		// set request url
		String url = urls+"/pv/bl/cp/rm/mmlyRtngMgmt/v1/mmlyRmndUsagOver?wrkTypCd=1&callYyyymm=" + callYm;
		RestRequestData reqData = new RestRequestData(url);
		reqData.setHeader(HTTP.CONTENT_TYPE, oAuthConst.HD_VALUE_CONTENTTYPE_JSON_UTF8);
		reqData.setHeader(oAuthConst.AUTHORIZATION, oAuthAccessToken);
		reqData.setHeader("prodNo", encoderProdNo);
		reqData.setHeader("entrCntcId", encoderAceno);
		reqData.setHeader("entrId", encoderEntrNo);
		reqData.setHeader(oAuthConst.BILLACNTID, encoderBillAcntNo);
		reqData.setHeader("X-USER-ID", "");
		reqData.setHeader(oAuthConst.X_IBM_CLIENT_ID, authClientId);
		reqData.setHeader(oAuthConst.X_IBM_CLIENT_SECRET, authClientSecret);
		reqData.setHeader(oAuthConst.X_FORWARDED_FOR, ip);
		reqData.setHeader(oAuthConst.X_FORWARDED_APPNAME, "Nissan_telematics");

		// set params
		reqData.setConnectionTimeout(5000);
		reqData.setTimeout(5000);

		return requestMmlyRmndUsagOver(reqData);
	}
	
	private MmlyRmndUsagOverResponseJSON requestMmlyRmndUsagOver(RestRequestData reqData) {
			
		RestResultData resultData = restAgent.requestGET(reqData);
	
		MmlyRmndUsagOverResponseJSON mmlyRmndUsagOverResp = new MmlyRmndUsagOverResponseJSON();
		if (resultData == null) {
			logger.error("failed to request APIM Server)");
			return null;
		}
	
		if (resultData.getStatusCode() != HttpStatus.SC_OK) {
			logger.error("failed to request paymDlstResp Server.statusCode({})",
					resultData.getStatusCode());
			mmlyRmndUsagOverResp.setHttpCode(resultData.getStatusCode());
			return mmlyRmndUsagOverResp;
		}
	
		mmlyRmndUsagOverResp.setHttpCode(resultData.getStatusCode());
		try {
			mmlyRmndUsagOverResp = gson.fromJson(resultData.getJson(), MmlyRmndUsagOverResponseJSON.class);
	
		} catch (JsonSyntaxException e) {
			logger.error("JSON({}) Exception({})", resultData.getJson(), ExceptionUtil.getPrintStackTrace(e));
			return null;
		}
	
		if (mmlyRmndUsagOverResp == null ) {
			logger.error("failed to request DcntDlstBltxResp data. paymDlstResp({})", mmlyRmndUsagOverResp);
			return null;
		}
		return mmlyRmndUsagOverResp;
	}
}
