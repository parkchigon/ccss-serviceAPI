package com.lgu.ccss.api.auth.service.nLogin;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
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
import com.lgu.common.util.RandomKeyMakerUtil;

@Service("authNLoginService")
public class AuthNLoginServiceImpl implements AuthNLoginService {

	private static final Logger logger = LoggerFactory.getLogger(AuthNLoginServiceImpl.class);
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
	private ClovaAuthAgent clovaAuthAgent;
	
	@Autowired
	private AuthStatusChecker authStatusChecker;
	
	@Autowired
	private MembManager membManager;
	
	@Autowired
	private ModelDao modelDao;
	
	@Autowired
	private ClovaAuthDao clovaAuthDao;

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
		if (!ResultCodeUtil.RC_2S000000.equals(resultCode))
		{			
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

		//clova 토큰 조회 - memb_id
		ClovaAuthInfoVO  serlectClovaAuthInfoVO= new ClovaAuthInfoVO();
		serlectClovaAuthInfoVO = clovaAuthDao.selectClovaAuthInfo(memb.getMembId());
		if(serlectClovaAuthInfoVO !=null){
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
		}else{
			// 없을 경우
			// Clova Access Token 요청 
			
			//CAR OEM Clova Client ID & SECRET Key 획득
			String deviceModel = reqJson.getCommon().getLogData().getDevModel();
			DeviceModelVO oemVO = modelDao.selectClovaClientInfo(deviceModel); 
			
			//1.Clova Auth Code 요청
			
			ClovaAuthResponseBodyAuthCodeJSON authCodeResp = clovaAuthAgent.createAuthorizationCode(oemVO.getClovaClientId(),deviceModel,ccssToken, deviceCtn, deviceSerial, ClovaAuthConst.DEF_Y, null);
			//ClovaAuthResponseJSON authCodeResp = clovaAuthAgent.createAuthorizationCode(deviceModel,ccssToken, deviceCtn, deviceSerial, ClovaAuthConst.DEF_Y, null);
				
			if (authCodeResp == null || authCodeResp.getCode() == null ) {
				logger.error("failed to get clova authorization Code data. deviceCtn({})", deviceCtn);

				loginResult.setResultCode(ResultCodeUtil.RC_5C100000);
				return loginResult;
			}
			String authorizationCode = authCodeResp.getCode();
			//String authorizationCode = authCodeResp.getBody().getAuthCode().getCode();

			ClovaAuthResponseBodyAccessTokenJSON accessTokenResp = clovaAuthAgent.createClovaAccessToken(oemVO.getClovaClientId(),oemVO.getClovaClientSecret(),deviceModel, ccssToken, deviceCtn,  deviceSerial, authorizationCode);
					
			if (accessTokenResp == null || accessTokenResp.getAccess_token() == null ) {
				logger.error("failed to get get Clova Access Token data. deviceCtn({})", deviceCtn);

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
			//insertClovaAuthInfoVO.setMgrappId(mgrappId);
			
			if (clovaAuthDao.insertClovaAuthInfo(insertClovaAuthInfoVO) == 0) {
				logger.error("failed to insert Clova Auth Info data. deviceCtn({})", deviceCtn);
				loginResult.setResultCode(ResultCodeUtil.RC_4C005001);
				return loginResult;
			}
			
			resultData.setClovaAccessToken(insertClovaAuthInfoVO.getClovaToken());
			resultData.setExpiredIn(insertClovaAuthInfoVO.getExpiredTime());
			resultData.setExpiresIn(Integer.parseInt(insertClovaAuthInfoVO.getExpiredTime()));
			resultData.setRefreshToken(insertClovaAuthInfoVO.getRefreshToken());
			resultData.setTokenType(insertClovaAuthInfoVO.getTokenType());
			
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
		if (memberDao.updateMemberInfo(memb.getMembId(), securityKey, 0,null) == 0) {
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
		
		resultData.setClovaBasicUrl(clovaLinkUrl);
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
}
	