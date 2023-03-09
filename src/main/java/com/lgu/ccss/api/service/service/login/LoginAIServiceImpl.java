package com.lgu.ccss.api.service.service.login;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;

import com.lgu.ccss.api.service.model.ResultLoginAIJSON;
import com.lgu.ccss.common.constant.CCSSConst;
import com.lgu.ccss.common.dao.AuthInfoDao;
import com.lgu.ccss.common.dao.DeviceDao;
import com.lgu.ccss.common.model.ConnDeviceVO;
import com.lgu.ccss.common.model.MembData;
import com.lgu.ccss.common.model.MembVO;
import com.lgu.ccss.common.model.RequestJSON;
import com.lgu.ccss.common.model.ResponseJSON;
import com.lgu.ccss.common.util.CCSSUtil;
import com.lgu.ccss.common.util.MembManager;
import com.lgu.ccss.common.util.ResultCodeUtil;
import com.lgu.ccss.common.validation.CheckResultData;
import com.lgu.ccss.common.validation.ValidationChecker;
import com.lgu.common.ai.auth.AuthAgent;
import com.lgu.common.ai.auth.model.AuthResponseJSON;
import com.lgu.common.model.ResultCode;
import com.lgu.common.util.ExceptionUtil;

@Service("loginAIService")
public class LoginAIServiceImpl implements LoginAIService {
	private static final Logger logger = LoggerFactory.getLogger(LoginAIService.class);

	@Value("#{config['ai.publicUrl']}")
	private String aiUrl;

	@Autowired
	private DeviceDao deviceDao;

	@Autowired
	private AuthInfoDao authInfoDao;

	@Autowired
	private AuthAgent authAgent;
	
	@Autowired
	private MembManager membManager;

	@Override
	public ResponseJSON doService(HttpHeaders headers, RequestJSON reqJson) {
		String api = reqJson.getCommon().getApiId();
		String deviceCtn = CCSSUtil.getCtn();

		ResponseJSON response = null;
		
		CheckResultData result = ValidationChecker.checkValidation(null, reqJson, CCSSConst.API_ID_SERVICE_LOGIN_AI,
				null);
		if (result.isStatus() == false) {
			response = ResultCodeUtil.createResultMsg(ResultCodeUtil.RC_3C004000, api, result.getReason());
			return response;
		}
		
		try {
			response = doLoginAI(headers, reqJson);

		} catch (Exception e) {
			logger.error("deviceCtn({}) Exception({})", deviceCtn, ExceptionUtil.getPrintStackTrace(e));
			response = ResultCodeUtil.createResultMsg(ResultCodeUtil.RC_4C005000, api);
		}

		return response;
	}

	private ResponseJSON doLoginAI(HttpHeaders headers, RequestJSON reqJson) {

		String api = reqJson.getCommon().getApiId();
		String deviceCtn = CCSSUtil.getCtn();
		String deviceSerial = CCSSUtil.getSerial();

		// get member data
		MembData membData = new MembData();
		ResultCode resultCode = membManager.getSubsInfo(deviceCtn, membData);
		if (!ResultCodeUtil.RC_2S000000.equals(resultCode)) {
			return ResultCodeUtil.createResultMsg(resultCode, api);
		}
		
		MembVO memb = membData.getMemb();
		
		ConnDeviceVO connDeviceVo = deviceDao.checkUICCID(memb.getMembId(), deviceCtn, deviceSerial);
		if (connDeviceVo == null) {
			logger.error("failed to check UICCID. deviceCtn({}) UICCID({})", deviceCtn, deviceSerial);
			return ResultCodeUtil.createResultMsg(ResultCodeUtil.RC_3C003002, api);
		}

		AuthResponseJSON deviceTokenResp = authAgent.createDeviceToken(deviceCtn, deviceSerial,
				reqJson.getCommon().getAuthRequestCommon());
		if (deviceTokenResp == null) {
			logger.error("failed to get deviceToken data. deviceCtn({})", deviceCtn);
			return ResultCodeUtil.createResultMsg(ResultCodeUtil.RC_5A000000, api);
		}
		String deviceToken = deviceTokenResp.getBody().getDeviceToken().getToken();

		AuthResponseJSON platformTokenResp = authAgent.createPlatformToken(deviceCtn, deviceSerial, deviceToken,
				reqJson.getCommon().getAuthRequestCommon());
		if (platformTokenResp == null) {
			logger.error("failed to get platformToken data. deviceCtn({})", deviceCtn);
			return ResultCodeUtil.createResultMsg(ResultCodeUtil.RC_5A000000, api);
		}
		String platformToken = platformTokenResp.getBody().getPlatformToken().getToken();
		
		if (authInfoDao.insertAIAuthInfo(deviceToken, platformToken, memb.getMembId()) == 0) {
			logger.error("failed to insert AIAuthInfo data. deviceCtn({})", deviceCtn);
			return ResultCodeUtil.createResultMsg(ResultCodeUtil.RC_4C005001, api);
		}

		ResultLoginAIJSON resultData = new ResultLoginAIJSON();
		resultData.setAiDeviceToken(deviceToken);
		resultData.setAiPlatformToken(platformToken);
		resultData.setAiUrl(aiUrl);
		
		return ResultCodeUtil.createResultMsg(ResultCodeUtil.RC_2S000000, resultData, api);
	}
}