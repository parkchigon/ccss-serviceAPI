package com.lgu.ccss.api.service.service.save;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;

import com.lgu.ccss.common.constant.CCSSConst;
import com.lgu.ccss.common.dao.AuthInfoDao;
import com.lgu.ccss.common.dao.InfotainAuthInfoDao;
import com.lgu.ccss.common.model.AIAuthInfoVO;
import com.lgu.ccss.common.model.RequestJSON;
import com.lgu.ccss.common.model.ResponseJSON;
import com.lgu.ccss.common.util.CCSSUtil;
import com.lgu.ccss.common.util.ResultCodeUtil;
import com.lgu.ccss.common.validation.CheckResultData;
import com.lgu.ccss.common.validation.ValidationChecker;
import com.lgu.common.ai.auth.AuthAgent;
import com.lgu.common.ai.auth.model.AuthRequestBodyJSON;
import com.lgu.common.ai.auth.model.AuthResponseJSON;
import com.lgu.common.util.ExceptionUtil;

@Service("saveInfotainmentService")
public class SaveInfotainmentServiceImpl implements SaveInfotainmentService {
	private static final Logger logger = LoggerFactory.getLogger(SaveInfotainmentServiceImpl.class);
	private static final List<String> mandatoryList = new ArrayList<String>(
			Arrays.asList(RequestJSON.PARAM_SERVICE_CODE));

	@Autowired
	private AuthInfoDao authInfoDao;

	@Autowired
	private InfotainAuthInfoDao infotainAuthInfoDao;

	@Autowired
	private AuthAgent authAgent;

	@Override
	public ResponseJSON doService(HttpHeaders headers, RequestJSON reqJson) {
		String api = reqJson.getCommon().getApiId();
		String deviceCtn = CCSSUtil.getCtn();

		ResponseJSON response = null;

		CheckResultData result = ValidationChecker.checkValidation(null,reqJson,CCSSConst.API_ID_SERVICE_SAVE_INFOTAINMENT,mandatoryList);
		if (result.isStatus() == false) {
			response = ResultCodeUtil.createResultMsg(ResultCodeUtil.RC_3C004000, api, result.getReason());
			return response;
		}

		try {
			response = saveInfotainment(headers, reqJson);

		} catch (Exception e) {
			logger.error("deviceCtn({}) Exception({})", deviceCtn, ExceptionUtil.getPrintStackTrace(e));
			response = ResultCodeUtil.createResultMsg(ResultCodeUtil.RC_4C005000, api);
		}

		return response;

	}

	/*private CheckResultData checkValidation(RequestJSON reqJson) {
		CheckResultData result = ValidationChecker.checkCommon(reqJson.getCommon());
		if (result.isStatus() == false) {
			return result;
		}

		result = ValidationChecker.checkValue(reqJson.getCommon().getApiId(), "common.apiId");
		if (result.isStatus() == false) {
			return result;
		}

		if (!CCSSConst.API_ID_SERVICE_SAVE_INFOTAINMENT.equals(reqJson.getCommon().getApiId())) {
			result = new CheckResultData();
			result.setStatus(false);
			result.setReason("API_ID unmatched");
			return result;
		}

		for (String key : mandatoryList) {
			result = ValidationChecker.checkValue((String) reqJson.getParam().get(key), "param." + key);
			if (result.isStatus() == false) {
				return result;
			}
		}

		return result;
	}*/

	private ResponseJSON saveInfotainment(HttpHeaders headers, RequestJSON reqJson) {

		String api = reqJson.getCommon().getApiId();
		String deviceCtn = CCSSUtil.getCtn();
		String membId = CCSSUtil.getMembId();

		AIAuthInfoVO aiAuthInfo = authInfoDao.selectAIAuthInfo(membId);
		if (aiAuthInfo == null) {
			logger.error("failed to select AI AuthInfo data. deviceCtn({})", deviceCtn);
			return ResultCodeUtil.createResultMsg(ResultCodeUtil.RC_4C005001, api);
		}

		Map<String, String> authParameter = new LinkedHashMap<String, String>();
		for (String key : reqJson.getParam().keySet()) {
			String value = (String) reqJson.getParam().get(key);
			if (value != null && key.startsWith("authParameter")) {
				authParameter.put(key, value);
			}
		}

		String serviceCode = (String) reqJson.getParam().get(RequestJSON.PARAM_SERVICE_CODE);

		Map<String, String> authInfo = new LinkedHashMap<String, String>();
		authInfo.put(AuthRequestBodyJSON.AUTH_INFO_CONT_SVC_CD, serviceCode);
		authInfo.putAll(authParameter);

		AuthResponseJSON response = authAgent.saveSvcAuthInfo(deviceCtn, aiAuthInfo.getDeviceToken(),
				reqJson.getCommon().getAuthRequestCommon(), authInfo);
		if (response == null) {
			logger.error("failed to save Infotainment data. deviceCtn({})", deviceCtn);
			return ResultCodeUtil.createResultMsg(ResultCodeUtil.RC_5A000002, api);
		}

		for (String key : authParameter.keySet()) {
			if (insertInfotainAuthInfo(membId, serviceCode, key, authParameter.get(key)) == false) {
				logger.error("failed to insert InfotainAuthInfo data. deviceCtn({}) parameter({}:{})", deviceCtn, key,
						authParameter.get(key));
				return ResultCodeUtil.createResultMsg(ResultCodeUtil.RC_4C005001, api);
			}
		}

		return ResultCodeUtil.createResultMsg(ResultCodeUtil.RC_2S000000, api);
	}

	private boolean insertInfotainAuthInfo(String membId, String serviceId, String tokenName, String tokenValue) {
		if (tokenValue == null || tokenValue.length() == 0) {
			return true;
		}
		if (infotainAuthInfoDao.insertInfotainAuthInfo(membId, serviceId, tokenName, tokenValue) == 0) {
			return false;
		}

		return true;
	}
}
