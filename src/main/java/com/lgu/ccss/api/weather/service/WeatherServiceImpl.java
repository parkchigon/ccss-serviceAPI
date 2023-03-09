package com.lgu.ccss.api.weather.service;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

import org.apache.http.client.ClientProtocolException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;

import com.lgu.ccss.api.weather.model.RequestWeatherJSON;
import com.lgu.ccss.common.dao.AuthInfoDao;
import com.lgu.ccss.common.dao.MemberDao;
import com.lgu.ccss.common.model.AIAuthInfoVO;
import com.lgu.ccss.common.model.MembVO;
import com.lgu.ccss.common.model.RequestCommonLogDataJSON;
import com.lgu.ccss.common.model.ResponseJSON;
import com.lgu.ccss.common.util.CCSSUtil;
import com.lgu.ccss.common.util.ResultCodeUtil;
import com.lgu.ccss.common.validation.CheckResultData;
import com.lgu.ccss.common.validation.ValidationChecker;
import com.lgu.common.ai.coif.COIFAgent;
import com.lgu.common.ai.coif.COIFErrorCode;
import com.lgu.common.ai.coif.model.COIFRequestCommonLogDataJSON;
import com.lgu.common.ai.coif.model.COIFResponseJSON;
import com.lgu.common.util.AES256Util;
import com.lgu.common.util.ExceptionUtil;

@Service("weatherService")
public class WeatherServiceImpl implements WeatherService {
	private static final Logger logger = LoggerFactory.getLogger(WeatherServiceImpl.class);

	@Autowired
	private MemberDao memberDao;

	@Autowired
	private AuthInfoDao authInfoDao;

	@Autowired
	private COIFAgent coifAgent;

	@Override
	public ResponseJSON doService(HttpHeaders headers, RequestWeatherJSON reqJson) {
		String api = reqJson.getCommon().getApiId();
		String deviceCtn = CCSSUtil.getCtn();

		ResponseJSON response = null;

		CheckResultData result = checkValidation(reqJson);
		if (result.isStatus() == false) {
			response = ResultCodeUtil.createResultMsg(ResultCodeUtil.RC_3C004000, api, result.getReason());
			return response;
		}

		try {
			response = doWeatherService(headers, reqJson);

		} catch (Exception e) {
			logger.error("deviceCtn({}) Exception({})", deviceCtn, ExceptionUtil.getPrintStackTrace(e));
			response = ResultCodeUtil.createResultMsg(ResultCodeUtil.RC_4C005000, api);
		}

		return response;

	}

	private CheckResultData checkValidation(RequestWeatherJSON request) {
		CheckResultData result = ValidationChecker.checkCommon(request.getCommon());
		if (result.isStatus() == false) {
			return result;
		}

		result = ValidationChecker.checkValue(request.getCommon().getApiId(), "common.apiId");
		if (result.isStatus() == false) {
			return result;
		}

		return result;
	}

	private ResponseJSON doWeatherService(HttpHeaders headers, RequestWeatherJSON reqJson)
			throws ClientProtocolException, IOException {

		String api = reqJson.getCommon().getApiId();
		String ccssToken = headers.getFirst("ccssToken");
		String deviceCtn = CCSSUtil.getCtn();
		String membId = CCSSUtil.getMembId();

		MembVO memb = memberDao.selectMemberByID(membId);
		if (memb == null) {
			logger.error("failed to select member data. deviceCtn({}) ccssToken({}) membId({})", deviceCtn,
					ccssToken, membId);
			return ResultCodeUtil.createResultMsg(ResultCodeUtil.RC_3C002004, api);
		}

		AIAuthInfoVO aiAuthInfo = authInfoDao.selectAIAuthInfo(membId);
		if (aiAuthInfo == null) {
			logger.error("failed to select AIAuthInfo data. deviceCtn({}) ccssToken({}) membId({})", deviceCtn,
					ccssToken, membId);
			return ResultCodeUtil.createResultMsg(ResultCodeUtil.RC_4C005001, api);
		}

		try {
			decryptLocationInfo(memb.getTransToken(), reqJson);

		} catch (Exception e) {
			logger.error("failed to decrypt Lat/Lon data. deviceCtn({}) ccssToken({}) memb({}) exception({})",
					deviceCtn, ccssToken, memb, ExceptionUtil.getPrintStackTrace(e));
			return ResultCodeUtil.createResultMsg(ResultCodeUtil.RC_3C004005, api);
		}

		COIFResponseJSON coifRes = coifAgent.requestWeather(aiAuthInfo.getDeviceToken(), deviceCtn,
				makeLogData(reqJson.getCommon().getLogData()), reqJson.getParam().getBody());
		if (coifRes == null) {
			logger.error("failed to request Weather Server. deviceCtn({}) ccssToken({}) aiAuthInfo({})", deviceCtn,
					ccssToken, aiAuthInfo);
			return ResultCodeUtil.createResultMsg(ResultCodeUtil.RC_5C000001, api);
		}

		String resultCode = coifRes.getCommon().getResultCode();
		if (resultCode == null || !resultCode.equals(COIFErrorCode.RC_20000000.getCode())) {
			logger.error("failed to request Weather Server. deviceCtn({}) ccssToken({}) coifRes({})", deviceCtn,
					ccssToken, coifRes);
			String coifMsg = resultCode + " " + coifRes.getCommon().getResultMessage();
			return ResultCodeUtil.createResultMsg(ResultCodeUtil.RC_5C000000, api, coifMsg);
		}

		return ResultCodeUtil.createResultMsg(ResultCodeUtil.RC_2S000000, coifRes.getBody(), api);
	}

	private void decryptLocationInfo(String secureKey, RequestWeatherJSON weather) throws InvalidKeyException,
			NoSuchAlgorithmException, NoSuchPaddingException, InvalidAlgorithmParameterException,
			UnsupportedEncodingException, IllegalBlockSizeException, BadPaddingException {

		String encLat = weather.getParam().getBody().getSlot().getLatitude();
		String encLon = weather.getParam().getBody().getSlot().getLongtitude();
		
		if (encLat == null || encLon == null) {
			return;
		}

		String decLat = AES256Util.AESDecode(secureKey, encLat);
		String decLon = AES256Util.AESDecode(secureKey, encLon);

		weather.getParam().getBody().getSlot().setLatitude(decLat);
		weather.getParam().getBody().getSlot().setLongtitude(decLon);
	}

	private COIFRequestCommonLogDataJSON makeLogData(RequestCommonLogDataJSON logData) {
		COIFRequestCommonLogDataJSON coifLogData = new COIFRequestCommonLogDataJSON();

		coifLogData.setClientIp(logData.getClientIp());
		coifLogData.setDevInfo(logData.getDevInfo());
		coifLogData.setOsInfo(logData.getOsInfo());
		coifLogData.setNwInfo(logData.getNwInfo());
		coifLogData.setSvcName(logData.getSvcName());
		coifLogData.setDevModel(logData.getDevModel());
		coifLogData.setCarrierType(logData.getCarrierType());
		coifLogData.setSvcType(logData.getSvcType());
		coifLogData.setDevType(logData.getDevType());

		return coifLogData;
	}

	public static void main(String[] args) throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException,
			InvalidAlgorithmParameterException, UnsupportedEncodingException, IllegalBlockSizeException,
			BadPaddingException {
		String secureKey = "Tk1MDA0MDI1Ng==5";

		String encLat = AES256Util.AESEncode(secureKey, "37.5");
		String encLon = AES256Util.AESEncode(secureKey, "126.9");

		System.out.println("encLat : " + encLat);
		System.out.println("encLon : " + encLon);
	}
}
