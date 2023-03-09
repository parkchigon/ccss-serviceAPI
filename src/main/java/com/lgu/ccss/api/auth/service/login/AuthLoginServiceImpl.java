package com.lgu.ccss.api.auth.service.login;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;

import com.lgu.ccss.api.auth.model.AuthStatusResultData;
import com.lgu.ccss.api.auth.model.LoginResultData;
import com.lgu.ccss.api.auth.model.ResultAuthLoginJSON;
import com.lgu.ccss.api.auth.service.AuthStatusChecker;
import com.lgu.ccss.common.constant.CCSSConst;
import com.lgu.ccss.common.dao.AuthInfoDao;
import com.lgu.ccss.common.dao.DeviceDao;
import com.lgu.ccss.common.dao.DeviceSessDao;
import com.lgu.ccss.common.dao.MemberDao;
import com.lgu.ccss.common.dao.ModelDao;
import com.lgu.ccss.common.model.ConnDeviceVO;
import com.lgu.ccss.common.model.DeviceModelVO;
import com.lgu.ccss.common.model.DeviceSessVO;
import com.lgu.ccss.common.model.MembData;
import com.lgu.ccss.common.model.MembVO;
import com.lgu.ccss.common.model.RequestJSON;
import com.lgu.ccss.common.model.ResponseJSON;
import com.lgu.ccss.common.tlo.TloData;
import com.lgu.ccss.common.tlo.TloUtil;
import com.lgu.ccss.common.util.CCSSUtil;
import com.lgu.ccss.common.util.MembManager;
import com.lgu.ccss.common.util.ResultCodeUtil;
import com.lgu.ccss.common.validation.CheckResultData;
import com.lgu.ccss.common.validation.ValidationChecker;
import com.lgu.common.ai.auth.AuthAgent;
import com.lgu.common.ai.auth.model.AuthResponseJSON;
import com.lgu.common.model.ResultCode;
import com.lgu.common.ncas.SubsInfo;
import com.lgu.common.util.DateUtils;
import com.lgu.common.util.ExceptionUtil;
import com.lgu.common.util.RandomKeyMakerUtil;

@Service("authLoginService")
public class AuthLoginServiceImpl implements AuthLoginService {

	private static final Logger logger = LoggerFactory.getLogger(AuthLoginServiceImpl.class);
	private static final List<String> mandatoryList = new ArrayList<String>(
			Arrays.asList(RequestJSON.PARAM_CTN, RequestJSON.PARAM_SERIAL, RequestJSON.PARAM_AI_TOKEN));

	private static final int TOKEN_TIMEOUT_DIFF_MIN = 2;

	@Value("#{config['ai.publicUrl']}")
	private String aiUrl;

	@Value("#{config['session.timeoutMin']}")
	private String sessionTimeoutMin;

	@Value("#{config['service.member.loginRetryCnt']}")
	private String memberLoginRetryCnt;

	@Value("#{config['service.member.loginLockMin']}")
	private String memberLoginLockMin;

	@Autowired
	private DeviceDao deviceDao;

	@Autowired
	private DeviceSessDao deviceSessDao;

	@Autowired
	private AuthInfoDao authInfoDao;

	@Autowired
	private MemberDao memberDao;

	@Autowired
	private AuthAgent authAgent;

	@Autowired
	private AuthStatusChecker authStatusChecker;
	
	@Autowired
	private MembManager membManager;
	
	@Autowired
	private ModelDao modelDao;

	@Override
	public ResponseJSON doService(HttpHeaders headers, RequestJSON reqJson) {
		String api = reqJson.getCommon().getApiId();
		String deviceCtn = CCSSUtil.getCtn();

		CheckResultData check = checkValidation(reqJson);
		if (check.isStatus() == false) {
			return ResultCodeUtil.createResultMsg(ResultCodeUtil.RC_3C004000, api, check.getReason());
		}

		LoginResultData result = null;
		try {
			result = doLogin(reqJson);

		} catch (Exception e) {
			logger.error("CTN({}) Exception({})", deviceCtn, e);
			return ResultCodeUtil.createResultMsg(ResultCodeUtil.RC_4C005000, api);
		}

		ResponseJSON response = null;
		if (ResultCodeUtil.RC_2S000000.getCode().equals(result.getResultCode().getCode())) {
			response = ResultCodeUtil.createResultMsg(result.getResultCode(), result.getResultData(), api);

		} else {
			if (result.getMembId() != null && result.getMembId().length() > 0) {
				memberDao.increaseLoginFailCnt(result.getMembId());
			}

			response = ResultCodeUtil.createResultMsg(result.getResultCode(), api);
		}

		return response;
	}

	private CheckResultData checkValidation(RequestJSON reqJson) {
		CheckResultData result = ValidationChecker.checkCommon(reqJson.getCommon());
		if (result.isStatus() == false) {
			return result;
		}

		result = ValidationChecker.checkValue(reqJson.getCommon().getApiId(), "common.apiId");
		if (result.isStatus() == false) {
			return result;
		}

		if (!CCSSConst.API_ID_AUTH_LOGIN.equals(reqJson.getCommon().getApiId())) {
			result = new CheckResultData();
			result.setStatus(false);
			result.setReason("API_ID unmatched");
			return result;
		}

		for (String key : mandatoryList) {
			result = ValidationChecker.checkParamValue((String) reqJson.getParam().get(key), "param." + key);
			if (result.isStatus() == false) {
				return result;
			}
		}

		return result;
	}

	private LoginResultData doLogin(RequestJSON reqJson) throws Exception {

		String deviceCtn = CCSSUtil.getCtn();
		String deviceSerial = CCSSUtil.getSerial();

		LoginResultData loginResult = new LoginResultData();
		ResultAuthLoginJSON resultData = new ResultAuthLoginJSON();
		
		DeviceModelVO model = modelDao.selectDeviceModelByName(reqJson.getCommon().getLogData().getDevModel(),
				reqJson.getCommon().getDevice().getDeviceType());
		if (model == null) {
			loginResult.setResultCode(ResultCodeUtil.RC_3C004002);
			return loginResult;
		}

		// get member data
		MembData membData = new MembData();
		ResultCode resultCode = membManager.getSubsInfo(deviceCtn, membData);
		if (!ResultCodeUtil.RC_2S000000.equals(resultCode)) {
			loginResult.setResultCode(resultCode);
			return loginResult;
		}

		SubsInfo subsInfo = membData.getSubsInfo();
		MembVO memb = membData.getMemb();

		loginResult.setMembId(memb.getMembId());

		/*
		 * if (isValidLoginCnt(memb) == false) { logger.
		 * error("login count is over!!. deviceCtn({}) loginFailCnt({}) LatestLoginDt({}) "
		 * , deviceCtn, memb.getLoginFailCnt(), memb.getLatestLoginDt());
		 * 
		 * loginResult.setResultCode(ResultCodeUtil.RC_3C003006); return loginResult; }
		 */

		// check UICC Auth
		if (!deviceSerial.equals(subsInfo.getUsim_iccid_no())) {
			logger.error("failed to check NCAS UICCID. deviceCtn({}) UICCID({})", deviceCtn, deviceSerial);
			
			loginResult.setResultCode(ResultCodeUtil.RC_3C003002);
			return loginResult;
		}
		
		ConnDeviceVO connDevice = deviceDao.checkUICCID(memb.getMembId(), deviceCtn, deviceSerial);
		if (connDevice == null) {
			logger.error("mismatch UICCID. deviceCtn({}) UICCID({}) NCAS_UICCID", deviceCtn, deviceSerial,
					subsInfo.getUsim_iccid_no());
		}

		if (logger.isDebugEnabled()) {
			logger.debug("CONN_DEVICE({})", connDevice);
		}

		// create ccss token
		int sessionTimeout = Integer.parseInt(sessionTimeoutMin);
		String ccssToken = RandomKeyMakerUtil.sessionIdGen(deviceCtn, memb.getMembId());
		String ccssTokenExpiration = DateUtils.getCurrentDate(Calendar.MINUTE, sessionTimeout,
				DateUtils.DATE_FORMAT_YMDHMS);

		resultData.setCcssToken(ccssToken);
		resultData.setCcssTokenExpiration(Integer.toString(sessionTimeout - TOKEN_TIMEOUT_DIFF_MIN));

		// delete/insert session info

		// get ai device/platform token
		String isAIToken = (String) reqJson.getParam().get(RequestJSON.PARAM_AI_TOKEN);
		if (RequestJSON.USE_Y.equals(isAIToken)) {
			AuthResponseJSON deviceTokenResp = authAgent.createDeviceToken(deviceCtn, deviceSerial,
					reqJson.getCommon().getAuthRequestCommon());
			if (deviceTokenResp == null) {
				logger.error("failed to get deviceToken data. deviceCtn({})", deviceCtn);

				loginResult.setResultCode(ResultCodeUtil.RC_5A000000);
				return loginResult;
			}
			String deviceToken = deviceTokenResp.getBody().getDeviceToken().getToken();

			AuthResponseJSON platformTokenResp = authAgent.createPlatformToken(deviceCtn, deviceSerial, deviceToken,
					reqJson.getCommon().getAuthRequestCommon());
			if (platformTokenResp == null) {
				logger.error("failed to get platformToken data. deviceCtn({})", deviceCtn);

				loginResult.setResultCode(ResultCodeUtil.RC_5A000000);
				return loginResult;
			}
			String platformToken = platformTokenResp.getBody().getPlatformToken().getToken();

			if (authInfoDao.insertAIAuthInfo(deviceToken, platformToken, memb.getMembId()) == 0) {
				logger.error("failed to insert AIAuthInfo data. deviceCtn({})", deviceCtn);

				loginResult.setResultCode(ResultCodeUtil.RC_4C005001);
				return loginResult;
			}

			long platformTokenTimeout = 720;		// default 12 hour
			try {
				Date nowDate = new Date(); 
				Date expireDate = DateUtils.stringToDate(platformTokenResp.getBody().getPlatformToken().getExpireTime(),
						"yyyy-MM-dd HH:mm:ss");
				
				platformTokenTimeout = (expireDate.getTime() - nowDate.getTime()) / 1000 / 60;
				platformTokenTimeout -= TOKEN_TIMEOUT_DIFF_MIN;
				
			} catch (Exception e) {
				logger.error("CTN({}) Exception({})", deviceCtn, ExceptionUtil.getPrintStackTrace(e));
			}

			resultData.setAiDeviceToken(deviceToken);
			resultData.setAiPlatformToken(platformToken);
			resultData.setAiPlatformTokenExpiration(Long.toString(platformTokenTimeout));
			resultData.setAiUrl(aiUrl);
		}

		// create security key
		String securityKey = RandomKeyMakerUtil.securityKeyGen(deviceCtn, memb.getMembId());
		resultData.setSecurityKey(securityKey);

		// delete latest device session
		deviceSessDao.deleteDeviceSessByCTN(deviceCtn, deviceSerial);

		// create new device session
		DeviceSessVO deviceSess = makeDeviceSessInfo(reqJson, memb, connDevice, ccssToken, ccssTokenExpiration);
		if (deviceSessDao.insertDeviceSess(deviceSess) == 0) {
			logger.error("failed to insert DeviceSession data. deviceCtn({})", deviceCtn);

			loginResult.setResultCode(ResultCodeUtil.RC_4C005001);
			return loginResult;
		}

		// set auth status
		AuthStatusResultData status = authStatusChecker.getStatus(subsInfo, memb, deviceCtn, deviceSerial);

		setTloData(memb, status.getJoinStatus());
		
		// update member info
		if (memberDao.updateMemberInfo(memb.getMembId(), securityKey, 0, null) == 0) {
			logger.error("failed to update Member data. deviceCtn({}) securityKey({})", deviceCtn, securityKey);

			loginResult.setResultCode(ResultCodeUtil.RC_4C005001);
			return loginResult;
		}

		resultData.setJoinStatus(status.getJoinStatus());
		resultData.setFirstJoinStatus(status.getFirstJoinStatus());
		resultData.setJoinDate(status.getJoinDate());
		resultData.setRatePayment(status.getRatePayment());
		resultData.setRatePaymentStatus(status.getRatePaymentStatus());
		resultData.setRatePaymentChangeStatus(status.getRatePaymentChangeStatus());
		resultData.setRatePaymentResvStatus(status.getRatePaymentResvStatus());
		resultData.setDataStatus(status.getDataStatus());

		loginResult.setResultCode(ResultCodeUtil.RC_2S000000);
		loginResult.setResultData(resultData);
		return loginResult;
	}

	private DeviceSessVO makeDeviceSessInfo(RequestJSON authVo, MembVO memb, ConnDeviceVO connDeviceVo,
			String ccssToken, String ccssTokenExpiration) {

		DeviceSessVO deviceSess = new DeviceSessVO();

		deviceSess.setDeviceSessId(ccssToken);
		deviceSess.setConnDeviceId(connDeviceVo.getConnDeviceId());
		deviceSess.setMembId(connDeviceVo.getMembId());
		deviceSess.setDeviceCtn(connDeviceVo.getDeviceCtn());
		deviceSess.setUsimSn(connDeviceVo.getUsimSn());
		deviceSess.setDeviceConnIp(authVo.getCommon().getLogData().getClientIp());
		deviceSess.setDeviceLoginDt(DateUtils.getBasicCurrentTime());
		deviceSess.setDeviceSessExpDt(ccssTokenExpiration);
		deviceSess.setDeviceSerial(CCSSUtil.getSerial());
		deviceSess.setMembNo(memb.getMembNo());

		return deviceSess;
	}

	private void setTloData(MembVO memb, String joinStatus) {
		Map<String, String> tlo = new HashMap<String, String>();
		
		if (memb != null) {
			tlo.put(TloData.MEMB_ID, memb.getMembId());
			tlo.put(TloData.MEMB_NO, memb.getMembNo());
		}
		
		tlo.put(TloData.JOIN_STATUS, joinStatus);
		
		TloUtil.setTloData(tlo);
	}
}