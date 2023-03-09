package com.lgu.ccss.api.service.service.logout;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;

import com.lgu.ccss.common.constant.CCSSConst;
import com.lgu.ccss.common.dao.mgr.MgrAppOneIdAuthInfo;
import com.lgu.ccss.common.model.RequestJSON;
import com.lgu.ccss.common.model.ResponseJSON;
import com.lgu.ccss.common.util.CCSSUtil;
import com.lgu.ccss.common.util.ResultCodeUtil;
import com.lgu.ccss.common.validation.CheckResultData;
import com.lgu.ccss.common.validation.ValidationChecker;
import com.lgu.common.util.ExceptionUtil;

@Service("logoutCekInfotainmentService")
public class LogoutCekInfotainmentServiceImpl implements LogoutCekInfotainmentService {
	private static final Logger logger = LoggerFactory.getLogger(LogoutInfotainmentServiceImpl.class);
	private static final List<String> mandatoryList = new ArrayList<String>(
			Arrays.asList(RequestJSON.PARAM_SERVICE_CODE));

	@Autowired
	private MgrAppOneIdAuthInfo mgrAppOneIdAuthInfo;
	
	@Override
	public ResponseJSON doService(HttpHeaders headers, RequestJSON reqJson) {
		String api = reqJson.getCommon().getApiId();
		String deviceCtn = CCSSUtil.getCtn();

		ResponseJSON response = null;

		CheckResultData result = ValidationChecker.checkValidation(null,reqJson,CCSSConst.API_ID_SERVICE_CEK_LOGOUT_INFOTAINMENT,mandatoryList);
		if (result.isStatus() == false) {
			response = ResultCodeUtil.createResultMsg(ResultCodeUtil.RC_3C004000, api, result.getReason());
			return response;
		}
		
		try {
			response = logoutCekInfotainment(headers, reqJson);

		} catch (Exception e) {
			logger.error("deviceCtn({}) Exception({})", deviceCtn, ExceptionUtil.getPrintStackTrace(e));
			response = ResultCodeUtil.createResultMsg(ResultCodeUtil.RC_4C005000, api);
		}

		return response;
	}

	private ResponseJSON logoutCekInfotainment(HttpHeaders headers, RequestJSON reqJson) {
		String api = reqJson.getCommon().getApiId();
		String deviceCtn = CCSSUtil.getCtn();
		String membId = CCSSUtil.getMembId();
		
		if (mgrAppOneIdAuthInfo.updateCekOneIdAuthInfo(membId) == 0) {
			//resultCode = ResultCodeUtil.RC_4C005001;
			logger.error("failed to update OneIdAuthInfo data. deviceCtn({})", deviceCtn);
			return ResultCodeUtil.createResultMsg(ResultCodeUtil.RC_5A000004, api);
		}
		
		/*AIAuthInfoVO aiAuthInfo = authInfoDao.selectAIAuthInfo(CCSSUtil.getMembId());
		if (aiAuthInfo == null) {
			logger.error("failed to select InfotainAuthInfo data. deviceCtn({})", deviceCtn);
			return ResultCodeUtil.createResultMsg(ResultCodeUtil.RC_4C005001, api);
		}

		if (logger.isDebugEnabled()) {
			logger.debug("AI_AUTHINFO({})", aiAuthInfo);
		}
		
		String serviceCode = (String) reqJson.getParam().get(RequestJSON.PARAM_SERVICE_CODE);

		Map<String, String> authCekInfo = new LinkedHashMap<String, String>();
		authCekInfo.put(AuthRequestBodyJSON.AUTH_INFO_CONT_SVC_CD, serviceCode);
		
		AuthResponseJSON response = authAgent.logoutSvc(deviceCtn, aiAuthInfo.getDeviceToken(),
				reqJson.getCommon().getAuthRequestCommon(), authCekInfo);
		if (response == null) {
			logger.error("failed to logout Infotainment. deviceCtn({})", deviceCtn);
			return ResultCodeUtil.createResultMsg(ResultCodeUtil.RC_5A000004, api);
		}
		if (cekInfotainAuthInfoDao.deleteCekInfotainAuthInfo(membId, serviceCode,null) == 0) {
			logger.error("failed to delete InfotainAuthInfo data. deviceCtn({}) serviceCode({})", deviceCtn,
					serviceCode);
		}*/

		return ResultCodeUtil.createResultMsg(ResultCodeUtil.RC_2S000000, api);
	}

}
