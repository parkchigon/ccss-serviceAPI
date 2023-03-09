package com.lgu.ccss.api.auth.service.nLogin;

import java.text.ParseException;
import java.text.SimpleDateFormat;
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
import org.springframework.transaction.annotation.Transactional;

import com.lgu.ccss.api.auth.model.AuthStatusResultData;
import com.lgu.ccss.api.auth.model.LoginResultData;
import com.lgu.ccss.api.auth.model.ResultAuthNguestLoginJSON;
import com.lgu.ccss.api.auth.service.AuthStatusChecker;
import com.lgu.ccss.common.constant.CCSSConst;
import com.lgu.ccss.common.dao.DeviceDao;
import com.lgu.ccss.common.dao.DeviceSessDao;
import com.lgu.ccss.common.dao.MemberDao;
import com.lgu.ccss.common.dao.ModelDao;
import com.lgu.ccss.common.dao.clova.ClovaAuthDao;
import com.lgu.ccss.common.model.ConnDeviceVO;
import com.lgu.ccss.common.model.DeviceModelVO;
import com.lgu.ccss.common.model.DeviceSessVO;
import com.lgu.ccss.common.model.MembData;
import com.lgu.ccss.common.model.MembVO;
import com.lgu.ccss.common.model.OemVO;
import com.lgu.ccss.common.model.RequestJSON;
import com.lgu.ccss.common.model.ResponseJSON;
import com.lgu.ccss.common.model.clova.ClovaAuthInfoVO;
import com.lgu.ccss.common.tlo.TloData;
import com.lgu.ccss.common.tlo.TloUtil;
import com.lgu.ccss.common.util.CCSSUtil;
import com.lgu.ccss.common.util.MembManager;
import com.lgu.ccss.common.util.ResultCodeUtil;
import com.lgu.ccss.common.validation.CheckResultData;
import com.lgu.ccss.common.validation.ValidationChecker;
import com.lgu.common.clova.auth.ClovaAuthAgent;
import com.lgu.common.clova.auth.ClovaAuthConst;
import com.lgu.common.clova.auth.model.ClovaAuthResponseBodyAccessTokenJSON;
import com.lgu.common.clova.auth.model.ClovaAuthResponseBodyAuthCodeJSON;
import com.lgu.common.clova.auth.model.ClovaAuthResponseBodyDeleteTokenJSON;
import com.lgu.common.model.ResultCode;
import com.lgu.common.ncas.SubsInfo;
import com.lgu.common.util.DateUtils;
import com.lgu.common.util.ExceptionUtil;
import com.lgu.common.util.RandomKeyMakerUtil;
import com.lgu.common.util.keygenerator.KeyGenerator;

@Service("bmAuthNLoginService")
public class BMAuthNLoginServiceImpl implements AuthNLoginService {

	private static final Logger logger = LoggerFactory.getLogger(BMAuthNLoginServiceImpl.class);
	private static final List<String> mandatoryList = new ArrayList<String>(Arrays.asList(RequestJSON.PARAM_CTN,
			RequestJSON.PARAM_SERIAL, RequestJSON.PARAM_AI_TOKEN, RequestJSON.PARAM_CAR_NUM));

	private static final int TOKEN_TIMEOUT_DIFF_MIN = 2;

	@Value("#{systemProperties.SERVER_ID}")
	private String serverId;
	
	@Value("#{config['ai.publicUrl']}")
	private String aiUrl;

	@Value("#{config['session.timeoutMin']}")
	private String sessionTimeoutMin;

	@Value("#{config['service.member.loginRetryCnt']}")
	private String memberLoginRetryCnt;

	@Value("#{config['service.member.loginLockMin']}")
	private String memberLoginLockMin;
	
	@Value("#{config['clova.auth.url']}")
	private String clovaAuthUrl;
	
	@Value("#{config['clova.link.url']}")
	private String clovaLinkUrl;

	@Autowired
	private DeviceDao deviceDao;

	@Autowired
	private DeviceSessDao deviceSessDao;

	@Autowired
	private MemberDao memberDao;

	@Autowired
	private AuthStatusChecker authStatusChecker;
	
	@Autowired
	private MembManager membManager;
	
	@Autowired
	private ModelDao modelDao;
	
	@Autowired
	private ClovaAuthDao clovaAuthDao;
	
	@Autowired
	private ClovaAuthAgent clovaAuthAgent;
	
	@Override
	@Transactional
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

			// 가입자일시정지 시, resultData도 null이 아닌 값으로  리턴해 달라는 오비고 요청에 의해 빈 resultData리턴
			//response = ResultCodeUtil.createResultMsg(result.getResultCode(), api);
			ResultAuthNguestLoginJSON resultData = new ResultAuthNguestLoginJSON();
			response = ResultCodeUtil.createResultMsg(result.getResultCode(), resultData, api);
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

		if (!CCSSConst.API_IDAUTH_NLOGIN.equals(reqJson.getCommon().getApiId())) {
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
		ResultAuthNguestLoginJSON resultData = new ResultAuthNguestLoginJSON();

		DeviceModelVO model = modelDao.selectDeviceModelByName(reqJson.getCommon().getLogData().getDevModel(),
				reqJson.getCommon().getDevice().getDeviceType());
		if (model == null) {
			loginResult.setResultCode(ResultCodeUtil.RC_3C004002);
			return loginResult;
		}
		
		// get member data
		MembData membData = new MembData();
		ResultCode resultCode = membManager.getSubsInfo(deviceCtn, membData);
		if (ResultCodeUtil.RC_3C002004.equals(resultCode)) {
			if (membData.getSubsInfo() == null) {
				loginResult.setResultCode(resultCode);
				return loginResult;
			}
			else {
				// create member info
				MembVO memb = makeMemberInfo(reqJson, deviceCtn, membData.getSubsInfo());
				if (memb == null) {
					logger.error("failed to make Member data. deviceCtn({}) UICCID({})", deviceCtn, deviceSerial);
					loginResult.setResultCode(ResultCodeUtil.RC_4C005000);
					return loginResult;
				}

				if (logger.isDebugEnabled()) {
					logger.debug("MEMBER({})", memb.toString());
				}

				if (memberDao.insertMember(memb) == 0) {
					logger.error("failed to insert Member data. deviceCtn({}) UICCID({})", deviceCtn, deviceSerial);
					loginResult.setResultCode(ResultCodeUtil.RC_4C005001);
					return loginResult;
				}	
				membData.setMemb(memb);
				
				// create device info
				ConnDeviceVO connDevice = makeDeviceInfo(reqJson, memb.getMembId());

				if (logger.isDebugEnabled()) {
					logger.debug("CONN_DEVICE({})", connDevice.toString());
				}

				if (deviceDao.insertConnDevice(connDevice) == 0) {
					logger.error("failed to insert ConnDevice data. deviceCtn({}) UICCID({})", deviceCtn, deviceSerial);
					loginResult.setResultCode(ResultCodeUtil.RC_4C005001);
					return loginResult;
				}
			}
		}
		else if (!ResultCodeUtil.RC_2S000000.equals(resultCode)) {
			loginResult.setResultCode(resultCode);
			return loginResult;
		}

		SubsInfo subsInfo = membData.getSubsInfo();
		MembVO memb = membData.getMemb();

		// select member table
		MembVO guestmodeAgrYn = memberDao.selectMemberGuestmodeAgrYn(memb.getMembId());
		resultData.setGuestmodeAgrYn(guestmodeAgrYn.getGuestmodeAgrYn());
				
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
			logger.error("mismatch UICCID. deviceCtn({}) UICCID({}) NCAS_UICCID({})", deviceCtn, deviceSerial,subsInfo.getUsim_iccid_no());
			
			loginResult.setResultCode(ResultCodeUtil.RC_3C003002);
			return loginResult;
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
		//clova 토큰 조회 - memb_id
		ClovaAuthInfoVO  serlectClovaAuthInfoVO= new ClovaAuthInfoVO();
		serlectClovaAuthInfoVO = 	clovaAuthDao.selectClovaAuthInfo(memb.getMembId());
		if (serlectClovaAuthInfoVO != null) {
			
			// REG_DT + EXPIRED_TIME 이 현재 시간 보다 작을 경우 토큰 리플레쉬
			boolean tokenExpired = checkTokenExpired(serlectClovaAuthInfoVO);
			if(tokenExpired) {
				ClovaAuthInfoVO insertClovaAuthInfoVO = new ClovaAuthInfoVO();
				String clovaLoginType = serlectClovaAuthInfoVO.getLoginType();
				String deviceModel = reqJson.getCommon().getLogData().getDevModel();
				DeviceModelVO oemVO = modelDao.selectClovaClientInfo(deviceModel);
				if(clovaLoginType.equals(ClovaAuthConst.DEF_GUEST)) {
					
					//1.Clova AccessToken Delete
					ClovaAuthResponseBodyDeleteTokenJSON deleteTokenResp = clovaAuthAgent.deleteClovaAccessToken(oemVO.getClovaClientId(),oemVO.getClovaClientSecret(),deviceModel, ccssToken, deviceCtn, deviceSerial, serlectClovaAuthInfoVO.getClovaToken());
					
					if (deleteTokenResp == null) {
						logger.error("failed to delete Old Clova Access Token data. membId({}) deviceCtn({}) clovaLoginType({})", memb.getMembId(), deviceCtn,clovaLoginType);
						// 실패 했을 경우 ? 그냥 신규 생성 ?
					}
						
					//Clova Access Token 재발급
					//1.Clova Auth Code 요청
					ClovaAuthResponseBodyAuthCodeJSON authCodeResp = clovaAuthAgent.createAuthorizationCode(oemVO.getClovaClientId(),deviceModel,ccssToken, deviceCtn, deviceSerial, ClovaAuthConst.DEF_Y, null);
						
					if (authCodeResp == null) {
						logger.error("failed to get clova authorization Code data. membId({}) deviceCtn({})", memb.getMembId(),deviceCtn);
						loginResult.setResultCode(ResultCodeUtil.RC_5C100000);
						return loginResult;
					}
					
					String authorizationCode = authCodeResp.getCode();

					ClovaAuthResponseBodyAccessTokenJSON accessTokenResp = clovaAuthAgent.createClovaAccessToken(oemVO.getClovaClientId(),oemVO.getClovaClientSecret(),deviceModel, ccssToken, deviceCtn,  deviceSerial, authorizationCode);
							
					if (accessTokenResp == null) {
						logger.error("failed to get get Clova Access Token data. membId({}) deviceCtn({})", memb.getMembId(),deviceCtn);
						loginResult.setResultCode(ResultCodeUtil.RC_5C100001);
						return loginResult;
					}
					insertClovaAuthInfoVO = makeInsertClovaAuthVO(insertClovaAuthInfoVO,accessTokenResp,memb.getMembId(),ClovaAuthConst.DEF_GUEST);
				} else {
					//Naver Mode 일경우 
					//1)Clova AccessToken Delete
					ClovaAuthResponseBodyAccessTokenJSON refreshResp = null;
					
					refreshResp =clovaAuthAgent.refreshClovaAccessToken(oemVO.getClovaClientId(),oemVO.getClovaClientSecret(),deviceModel, ccssToken, deviceCtn,  deviceSerial, serlectClovaAuthInfoVO.getRefreshToken());
	
					if (refreshResp == null) {
						logger.error("failed to refresh Clova Access Token data. membId({}) deviceCtn({})", memb.getMembId(), deviceCtn);
						// guest 모드 삭제 후 업데이트
						
						//1.Clova AccessToken Delete
						ClovaAuthResponseBodyDeleteTokenJSON deleteTokenResp = clovaAuthAgent.deleteClovaAccessToken(oemVO.getClovaClientId(),oemVO.getClovaClientSecret(),deviceModel, ccssToken, deviceCtn, deviceSerial, serlectClovaAuthInfoVO.getClovaToken());
						
						if (deleteTokenResp == null) {
							logger.error("failed to delete Old Clova Access Token data. membId({}) deviceCtn({}) clovaLoginType({})", memb.getMembId(), deviceCtn,clovaLoginType);
							// 실패 했을 경우 ? 그냥 신규 생성 ?
						}
						
						//2)DB 정보 삭제
						if (clovaAuthDao.deleteClovaAuthInfo(serlectClovaAuthInfoVO) == 0) {
							logger.error("failed to delete Clova Auth Info data. membId({}) deviceCtn({})", memb.getMembId(),deviceCtn);
							loginResult.setResultCode(ResultCodeUtil.RC_4C005001);
							return loginResult;
						}else{
							//3)에러 응답 - 재로그인 
							loginResult.setResultCode(ResultCodeUtil.RC_5C100002);
							return loginResult;
						}
						
					}else{
						
						insertClovaAuthInfoVO = makeInsertClovaAuthVO(insertClovaAuthInfoVO,refreshResp,memb.getMembId(),ClovaAuthConst.DEF_NAVER);
						
					}
				}
				if (clovaAuthDao.insertClovaAuthInfo(insertClovaAuthInfoVO) == 0) {
					logger.error("failed to insert Clova Auth Info data. membId({}) deviceCtn({})", memb.getMembId(),deviceCtn);
					loginResult.setResultCode(ResultCodeUtil.RC_4C005001);
					return loginResult;
				}
				
				if(insertClovaAuthInfoVO != null){
					resultData.setClovaAccessToken(insertClovaAuthInfoVO.getClovaToken());
					resultData.setExpiredIn(insertClovaAuthInfoVO.getExpiredTime());
					resultData.setExpiresIn(Integer.parseInt(insertClovaAuthInfoVO.getExpiredTime()));
					resultData.setRefreshToken(insertClovaAuthInfoVO.getRefreshToken());
					resultData.setTokenType(insertClovaAuthInfoVO.getTokenType());
					resultData.setClovaBasicUrl(clovaLinkUrl);
				}
			} else {
				resultData.setClovaAccessToken(serlectClovaAuthInfoVO.getClovaToken());
				resultData.setExpiredIn(serlectClovaAuthInfoVO.getExpiredTime());
				resultData.setExpiresIn(Integer.parseInt(serlectClovaAuthInfoVO.getExpiredTime()));
				resultData.setRefreshToken(serlectClovaAuthInfoVO.getRefreshToken());
				resultData.setTokenType(serlectClovaAuthInfoVO.getTokenType());
				resultData.setClovaBasicUrl(clovaLinkUrl);
			}
		} else {
			// 없을 경우
			// Clova Access Token 요청
			// 1.Clova Auth Code 요청

			//CAR OEM Clova Client ID & SECRET Key 획득
			String deviceModel = reqJson.getCommon().getLogData().getDevModel();
			DeviceModelVO oemVO = modelDao.selectClovaClientInfo(deviceModel); 
			
			ClovaAuthResponseBodyAuthCodeJSON authCodeResp = clovaAuthAgent.createAuthorizationCode(oemVO.getClovaClientId(),deviceModel, ccssToken, deviceCtn,deviceSerial, ClovaAuthConst.DEF_Y, null);
			//ClovaAuthResponseJSON authCodeResp = clovaAuthAgent.createAuthorizationCode(deviceModel, ccssToken, deviceCtn,deviceSerial, ClovaAuthConst.DEF_Y, null);

			if (authCodeResp == null || authCodeResp.getCode() == null) {
				logger.error("failed to get clova authorization Code data. deviceCtn({})",deviceCtn);
				loginResult.setResultCode(ResultCodeUtil.RC_5C100000);
				return loginResult;
			}
			
			String authorizationCode = authCodeResp.getCode();
			//String authorizationCode = authCodeResp.getBody().getAuthCode().getCode();
			
			ClovaAuthResponseBodyAccessTokenJSON accessTokenResp = clovaAuthAgent.createClovaAccessToken(oemVO.getClovaClientId(),oemVO.getClovaClientSecret(),deviceModel, ccssToken, deviceCtn,deviceSerial, authorizationCode);

			if (accessTokenResp == null || accessTokenResp.getAccess_token() == null ) {
				logger.error("failed to get get Clova Access Token data. deviceCtn({})",deviceCtn);

				loginResult.setResultCode(ResultCodeUtil.RC_5C100001);
				return loginResult;
			}

			ClovaAuthInfoVO insertClovaAuthInfoVO = new ClovaAuthInfoVO();
			insertClovaAuthInfoVO.setMembId(memb.getMembId());
			insertClovaAuthInfoVO.setClovaToken(accessTokenResp.getAccess_token());
			insertClovaAuthInfoVO.setRefreshToken(accessTokenResp.getRefresh_token());
			insertClovaAuthInfoVO.setExpiredTime(accessTokenResp.getExpires_in());
			insertClovaAuthInfoVO.setLoginStatus(ClovaAuthConst.DEF_Y);
			insertClovaAuthInfoVO.setLoginType(ClovaAuthConst.DEF_GUEST);
			insertClovaAuthInfoVO.setTokenType(accessTokenResp.getToken_type());
			// insertClovaAuthInfoVO.setMgrappId(mgrappId);

			if (clovaAuthDao.insertClovaAuthInfo(insertClovaAuthInfoVO) == 0) {
				logger.error("failed to insert Clova Auth Info data. deviceCtn({})",deviceCtn);
				loginResult.setResultCode(ResultCodeUtil.RC_4C005001);
				return loginResult;
			}
			/*
			 * long platformTokenTimeout = 720; // default 12 hour try { Date
			 * nowDate = new Date(); Date expireDate =
			 * DateUtils.stringToDate(platformTokenResp
			 * .getBody().getPlatformToken().getExpireTime(),
			 * "yyyy-MM-dd HH:mm:ss");
			 * 
			 * platformTokenTimeout = (expireDate.getTime() - nowDate.getTime())
			 * / 1000 / 60; platformTokenTimeout -= TOKEN_TIMEOUT_DIFF_MIN;
			 * 
			 * } catch (Exception e) { logger.error("CTN({}) Exception({})",
			 * deviceCtn, ExceptionUtil.getPrintStackTrace(e)); }
			 */

			resultData.setClovaAccessToken(insertClovaAuthInfoVO.getClovaToken());
			resultData.setExpiredIn(insertClovaAuthInfoVO.getExpiredTime());
			resultData.setExpiresIn(Integer.parseInt(insertClovaAuthInfoVO.getExpiredTime()));
			resultData.setRefreshToken(insertClovaAuthInfoVO.getRefreshToken());
			resultData.setTokenType(insertClovaAuthInfoVO.getTokenType());
			
			resultData.setClovaBasicUrl(clovaLinkUrl);
		}
		// get ai device/platform token
		/*		String isAIToken = (String) reqJson.getParam().get(RequestJSON.PARAM_AI_TOKEN);
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
		}*/

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
		AuthStatusResultData status = authStatusChecker.getBMStatus(subsInfo, memb, deviceCtn, deviceSerial);
		if(status == null){
			logger.error("NCAS AUTH FAIL!!!");

			loginResult.setResultCode(ResultCodeUtil.RC_5N000000);
			return loginResult;
		}
		setTloData(memb, status.getJoinStatus());
		
		// update member info
		if (memberDao.updateMemberInfo(memb.getMembId(), securityKey, 0, status.getRatePayment()) == 0) {
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
		deviceSess.setDeviceCtn(CCSSUtil.getCtn());
		deviceSess.setUsimSn("NULL");
		
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

	private MembVO makeMemberInfo(RequestJSON reqJson, String deviceCtn, SubsInfo subsInfo) throws ParseException {
		MembVO memb = new MembVO();

		String membId = null;
		try {
			membId = KeyGenerator.createCommonShardKey(Integer.parseInt(serverId));
		} catch (Exception e) {
			logger.error("deviceCtn({}) Exception({})", deviceCtn, ExceptionUtil.getPrintStackTrace(e));
		}

		if (membId == null) {
			return null;
		}

		memb.setMembId(membId);
		memb.setMembNo(subsInfo.getSub_no());
		memb.setMembCtn(deviceCtn);
		memb.setUseStatus(MembVO.USE_STATUS_JOIN);
		memb.setUseYn(MembVO.USE_Y);
		memb.setNewRejoinYn(MembVO.NEW_REJOIN_N);
		memb.setProductNm(subsInfo.getFee_type());

		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Date joinDate = format.parse(subsInfo.getRegDate());
		memb.setJoinDt(joinDate);

		memb.setServerId(serverId);
		memb.setPayResvYn(MembVO.PAY_RESV_N);
		memb.setMarketType(MembVO.MARKET_BM);
		memb.setExpSmsSendYn(MembVO.EXP_SMS_SEND_N);
		memb.setLastExpSmsSendYn(MembVO.LAST_EXP_SMS_SEND_N);

		setMembPrivateInfo(subsInfo, memb);
		
		return memb;
	}
	
	private void setMembPrivateInfo(SubsInfo subsInfo, MembVO memb) {		
		String birthYear = null;
		String sex = null;
		
		// set birthYear
		birthYear = subsInfo.getReal_birth_pers_id();
		if (birthYear == null || birthYear.length() == 0) {
			birthYear = subsInfo.getSub_birth_pers_id();
		}
		
		if (birthYear == null || birthYear.length() == 0) {
			birthYear = "N";
		
		} else {
			birthYear = birthYear.substring(0, 2);
		}
		
		// set sex
		sex = subsInfo.getReal_sex_pers_id();
		if (sex == null || sex.length() == 0) {
			sex = subsInfo.getSub_sex_pers_id();
		}
		
		if (sex == null || sex.length() == 0) {
			sex = "N";
		} 
		
		memb.setBirthYear(birthYear);
		
		memb.setGender(sex);
	}
	
	public boolean checkTokenExpired(ClovaAuthInfoVO serlectClovaAuthInfoVO){		
		boolean expired = false;		
		long expiredIn = Long.parseLong(serlectClovaAuthInfoVO.getExpiredTime()); 		
		String regDt = DateUtils.changeDateFormat(DateUtils.DATE_FORMAT_YMD_HMS,DateUtils.DATE_FORMAT_YMDHMS,serlectClovaAuthInfoVO.getRegDt());
		String tokenExpiredTime="";
		try {
			String nowtime = DateUtils.getCurrentTime(DateUtils.DATE_FORMAT_YMDHMS);	
			tokenExpiredTime = DateUtils.getAddDate( regDt,Calendar.SECOND, (int) expiredIn,DateUtils.DATE_FORMAT_YMDHMS);
			long timeGap = Long.parseLong(nowtime) - Long.parseLong(tokenExpiredTime);
			logger.info("nowtime({}) tokenExpiredTime({}) expiredIn({}) timeGap({})", nowtime,tokenExpiredTime,expiredIn,timeGap);
			if(timeGap > 0 ){ 
				expired = true;
			}
		} catch (Exception e) {
			logger.error("failed to Check Clova Token Expired   membId({}) lastTokenRegDt({}) expiredIn({}) Exception({})", serlectClovaAuthInfoVO.getMembId(),
					serlectClovaAuthInfoVO.getRegDt() ,serlectClovaAuthInfoVO.getExpiredTime(),e);
		}
		return expired;
	}
	
	public ClovaAuthInfoVO makeInsertClovaAuthVO(ClovaAuthInfoVO insertClovaAuthInfoVO,ClovaAuthResponseBodyAccessTokenJSON resTokenInfo,String membId,String loginType){
		insertClovaAuthInfoVO.setMembId(membId);
		insertClovaAuthInfoVO.setClovaToken(resTokenInfo.getAccess_token());
		insertClovaAuthInfoVO.setRefreshToken(resTokenInfo.getRefresh_token());
		insertClovaAuthInfoVO.setExpiredTime(resTokenInfo.getExpires_in());
		insertClovaAuthInfoVO.setLoginStatus(ClovaAuthConst.DEF_Y);
		insertClovaAuthInfoVO.setLoginType(loginType);
		insertClovaAuthInfoVO.setTokenType(resTokenInfo.getToken_type());
		insertClovaAuthInfoVO.setClovaUpdate(ClovaAuthConst.DEF_Y);
		//insertClovaAuthInfoVO.setMgrappId(mgrappId);
		return insertClovaAuthInfoVO;
	}

	private ConnDeviceVO makeDeviceInfo(RequestJSON reqJson, String membId) {
		ConnDeviceVO connDevice = new ConnDeviceVO();

		connDevice.setConnDeviceId(ConnDeviceVO.makeConnDeviceId(membId));
		connDevice.setMembId(membId);

		connDevice.setVehicleModelId(ConnDeviceVO.VEHICLE_MODEL_BM);
		connDevice.setDeviceModelId(reqJson.getCommon().getLogData().getDevModel());
		connDevice.setDeviceType(reqJson.getCommon().getDevice().getDeviceType());
		connDevice.setUseYn(ConnDeviceVO.USE_Y);
		connDevice.setDeviceCtn(CCSSUtil.getCtn());
		
		connDevice.setDeviceEsn(getOptionalParam(reqJson, RequestJSON.PARAM_ESN));
		connDevice.setUsimModel(getOptionalParam(reqJson, RequestJSON.PARAM_USIM_MODEL));
		connDevice.setUsimSn(getOptionalParam(reqJson, RequestJSON.PARAM_USIM_SN));
		
		connDevice.setDeviceLoginDt(DateUtils.getBasicCurrentTime());

		connDevice.setDevicePushClientId("");
		connDevice.setDevicePushConnStatus("");
		connDevice.setDevicePushConnDt(DateUtils.getBasicCurrentTime());

		connDevice.setFirmwareInfo(getOptionalParam(reqJson, RequestJSON.PARAM_FIRMWARE_INFO));
		
		connDevice.setJsonSetInfo("");
		connDevice.setUiccId(CCSSUtil.getSerial());
		
		connDevice.setCarNum((String) reqJson.getParam().get(RequestJSON.PARAM_CAR_NUM));

		return connDevice;
	}
	
	private String getOptionalParam(RequestJSON reqJson, String param) {
		String str = (String) reqJson.getParam().get(param);
		if (str == null || str.length() == 0) {
			return "NULL";
		}
		
		return str;
	}
}