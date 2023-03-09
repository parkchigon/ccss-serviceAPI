package com.lgu.ccss.api.clova.service.deviceLogin;

import java.util.ArrayList;
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

import com.lgu.ccss.api.clova.model.ClovaLoginResultData;
import com.lgu.ccss.api.clova.model.ResultClovaLoginJSON;
import com.lgu.ccss.common.constant.CCSSConst;
import com.lgu.ccss.common.dao.DeviceDao;
import com.lgu.ccss.common.dao.ModelDao;
import com.lgu.ccss.common.dao.clova.ClovaAuthDao;
import com.lgu.ccss.common.model.ConnDeviceVO;
import com.lgu.ccss.common.model.DeviceModelVO;
import com.lgu.ccss.common.model.RequestJSON;
import com.lgu.ccss.common.model.ResponseJSON;
import com.lgu.ccss.common.model.clova.ClovaAuthInfoVO;
import com.lgu.ccss.common.tlo.TloData;
import com.lgu.ccss.common.tlo.TloUtil;
import com.lgu.ccss.common.util.CCSSUtil;
import com.lgu.ccss.common.util.ResultCodeUtil;
import com.lgu.ccss.common.validation.CheckResultData;
import com.lgu.ccss.common.validation.ValidationChecker;
import com.lgu.common.clova.auth.ClovaAuthAgent;
import com.lgu.common.clova.auth.ClovaAuthConst;
import com.lgu.common.clova.auth.model.ClovaAuthResponseBodyAccessTokenJSON;
import com.lgu.common.clova.auth.model.ClovaAuthResponseBodyAuthCodeJSON;
import com.lgu.common.clova.auth.model.ClovaAuthResponseBodyDeleteTokenJSON;
import com.lgu.common.util.DateUtils;

@Service("clovaDeviceLoginService")
public class ClovaDeviceLoginServiceImpl implements ClovaDeviceLoginService {

	private static final Logger logger = LoggerFactory.getLogger(ClovaDeviceLoginServiceImpl.class);
	private static final List<String> mandatoryList = new ArrayList<String>(
			/*Arrays.asList(RequestJSON.PARAM_CTN, RequestJSON.PARAM_SERIAL, RequestJSON.PARAM_AI_TOKEN)*/);


	@Value("#{config['clova.auth.url']}")
	private String clovaAuthUrl;
	
	@Autowired
	private DeviceDao deviceDao;

	@Autowired
	private ClovaAuthAgent clovaAuthAgent;
	
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

		ClovaLoginResultData result = null;
		
		try {
			
			result = doLogin(reqJson);

		} catch (Exception e) {
			logger.error("Clova Login Fail. CTN({}) Exception({})", deviceCtn, e);
			return ResultCodeUtil.createResultMsg(ResultCodeUtil.RC_4C005000, api);
		}

		ResponseJSON response = null;
		if (ResultCodeUtil.RC_2S000000.getCode().equals(result.getResultCode().getCode())) {
			response = ResultCodeUtil.createResultMsg(result.getResultCode(), result.getResultData(), api);
		} else {
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

		if (!CCSSConst.API_ID_CLOVA_LOGIN.equals(reqJson.getCommon().getApiId())) {
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

	private ClovaLoginResultData doLogin(RequestJSON reqJson) throws Exception {
		String membId = CCSSUtil.getMembId();
		String deviceCtn = CCSSUtil.getCtn();
		String deviceSerial = CCSSUtil.getSerial();
		String ccssToken = CCSSUtil.getCcssToken();
		String deviceModel = reqJson.getCommon().getLogData().getDevModel();
		//String naverAccessToken =  (String) reqJson.getParam().get(RequestJSON.PARAM_NAVER_ACCESS_TOKEN);
		
		String forcedYn =  (String) reqJson.getParam().get(RequestJSON.PARAM_FORCED_YN);
		
		setTloData(membId);
		
		ClovaLoginResultData loginResult = new ClovaLoginResultData();
		ResultClovaLoginJSON resultData = new ResultClovaLoginJSON();
		ClovaAuthInfoVO insertClovaAuthInfoVO = new ClovaAuthInfoVO();
		
		ConnDeviceVO connDevice = deviceDao.checkUICCID(membId, deviceCtn, deviceSerial);
		
		if (logger.isDebugEnabled()) {
			logger.debug("CONN_DEVICE({})", connDevice);
		}

		//Clova Client 정보조회
		
		DeviceModelVO oemVO = modelDao.selectClovaClientInfo(deviceModel); 
		
		//clova 토큰 조회 - memb_id
		ClovaAuthInfoVO  serlectClovaAuthInfoVO= new ClovaAuthInfoVO();
		serlectClovaAuthInfoVO = clovaAuthDao.selectClovaAuthInfo(membId);
		
	
		if(serlectClovaAuthInfoVO !=null){
			
			String clovaLoginType = serlectClovaAuthInfoVO.getLoginType();
			
			try{
				
				// REG_DT + EXPIRED_TIME 이 현재 시간 보다 작을 경우 토큰 리플레쉬
				boolean tokenExpired = checkTokenExpired(serlectClovaAuthInfoVO);
							
				if(tokenExpired || forcedYn.equals(ClovaAuthConst.DEF_Y)){ // 강제 갱신의 경우
						
					if(clovaLoginType.equals(ClovaAuthConst.DEF_GUEST)){
						
						//1.Clova AccessToken Delete
						ClovaAuthResponseBodyDeleteTokenJSON deleteTokenResp = clovaAuthAgent.deleteClovaAccessToken(oemVO.getClovaClientId(),oemVO.getClovaClientSecret(),deviceModel, ccssToken, deviceCtn, deviceSerial, serlectClovaAuthInfoVO.getClovaToken());
						
						if (deleteTokenResp == null) {
							logger.error("failed to delete Old Clova Access Token data. membId({}) deviceCtn({}) clovaLoginType({})", membId, deviceCtn,clovaLoginType);
							// 실패 했을 경우 ? 그냥 신규 생성 ?
						}
							
						//Clova Access Token 재발급
						//1.Clova Auth Code 요청
						ClovaAuthResponseBodyAuthCodeJSON authCodeResp = clovaAuthAgent.createAuthorizationCode(oemVO.getClovaClientId(),deviceModel,ccssToken, deviceCtn, deviceSerial, ClovaAuthConst.DEF_Y, null);
							
						if (authCodeResp == null) {
							logger.error("failed to get clova authorization Code data. membId({}) deviceCtn({})", membId,deviceCtn);
							loginResult.setResultCode(ResultCodeUtil.RC_5C100000);
							return loginResult;
						}
						
						String authorizationCode = authCodeResp.getCode();

						ClovaAuthResponseBodyAccessTokenJSON accessTokenResp = clovaAuthAgent.createClovaAccessToken(oemVO.getClovaClientId(),oemVO.getClovaClientSecret(),deviceModel, ccssToken, deviceCtn,  deviceSerial, authorizationCode);
								
						if (accessTokenResp == null) {
							logger.error("failed to get get Clova Access Token data. membId({}) deviceCtn({})", membId,deviceCtn);
							loginResult.setResultCode(ResultCodeUtil.RC_5C100001);
							return loginResult;
						}
						
						insertClovaAuthInfoVO = makeInsertClovaAuthVO(insertClovaAuthInfoVO,accessTokenResp,membId,ClovaAuthConst.DEF_GUEST);
							
					}else{ 
							
						//Naver Mode 일경우 
						//1)Clova AccessToken Delete
						ClovaAuthResponseBodyAccessTokenJSON refreshResp = null;
						
						refreshResp =clovaAuthAgent.refreshClovaAccessToken(oemVO.getClovaClientId(),oemVO.getClovaClientSecret(),deviceModel, ccssToken, deviceCtn,  deviceSerial, serlectClovaAuthInfoVO.getRefreshToken());

						if (refreshResp == null) {
							logger.error("failed to refresh Clova Access Token data. membId({}) deviceCtn({})", membId, deviceCtn);
							// guest 모드 삭제 후 업데이트
							
							//1.Clova AccessToken Delete
							ClovaAuthResponseBodyDeleteTokenJSON deleteTokenResp = clovaAuthAgent.deleteClovaAccessToken(oemVO.getClovaClientId(),oemVO.getClovaClientSecret(),deviceModel, ccssToken, deviceCtn, deviceSerial, serlectClovaAuthInfoVO.getClovaToken());
							
							if (deleteTokenResp == null) {
								logger.error("failed to delete Old Clova Access Token data. membId({}) deviceCtn({}) clovaLoginType({})", membId, deviceCtn,clovaLoginType);
								// 실패 했을 경우 ? 그냥 신규 생성 ?
							}
							
							//2)DB 정보 삭제
							if (clovaAuthDao.deleteClovaAuthInfo(serlectClovaAuthInfoVO) == 0) {
								logger.error("failed to delete Clova Auth Info data. membId({}) deviceCtn({})", membId,deviceCtn);
								loginResult.setResultCode(ResultCodeUtil.RC_4C005001);
								return loginResult;
							}else{
								//3)에러 응답 - 재로그인 
								loginResult.setResultCode(ResultCodeUtil.RC_5C100002);
								return loginResult;
							}
							
						}else{
							
							insertClovaAuthInfoVO = makeInsertClovaAuthVO(insertClovaAuthInfoVO,refreshResp,membId,ClovaAuthConst.DEF_NAVER);
							
							}
						}
						
					}else{
						//미만료
						resultData.setClovaAccessToken(serlectClovaAuthInfoVO.getClovaToken());
						resultData.setExpiredIn(serlectClovaAuthInfoVO.getExpiredTime());
						resultData.setRefreshToken(serlectClovaAuthInfoVO.getRefreshToken());
						resultData.setTokenType(serlectClovaAuthInfoVO.getTokenType());
						
						loginResult.setResultCode(ResultCodeUtil.RC_2S000000);
						loginResult.setResultData(resultData);
						return loginResult;
						
					}
				

			}catch(Exception e){
				logger.error("failed to Clova Access Token Expired Check. membId({}) deviceCtn({}) Exception({})", membId,deviceCtn,e);
				loginResult.setResultCode(ResultCodeUtil.RC_4C005000);
				return loginResult;
			}
			
		}else{
			// 없을 경우  Clova Access Token 요청 
			//1.Clova Auth Code 요청
			ClovaAuthResponseBodyAuthCodeJSON authCodeResp = clovaAuthAgent.createAuthorizationCode(oemVO.getClovaClientId(),deviceModel,ccssToken, deviceCtn, deviceSerial, ClovaAuthConst.DEF_Y, null);
				
			if (authCodeResp == null) {
				logger.error("failed to get clova authorization Code data. membId({}) deviceCtn({})", membId,deviceCtn);

				loginResult.setResultCode(ResultCodeUtil.RC_5C100000);
				return loginResult;
			}
			String authorizationCode = authCodeResp.getCode();
			
			//2.Clova Access Token 요청
			ClovaAuthResponseBodyAccessTokenJSON accessTokenResp = clovaAuthAgent.createClovaAccessToken(oemVO.getClovaClientId(),oemVO.getClovaClientSecret(),deviceModel, ccssToken, deviceCtn,  deviceSerial, authorizationCode);
					
			if (accessTokenResp == null) {
				logger.error("failed to get get Clova Access Token data. membId({}) deviceCtn({})",membId, deviceCtn);

				loginResult.setResultCode(ResultCodeUtil.RC_5C100001);
				return loginResult;
			}
			
			insertClovaAuthInfoVO = makeInsertClovaAuthVO(insertClovaAuthInfoVO,accessTokenResp,membId,ClovaAuthConst.DEF_GUEST);
			
		}
		
		//DB Insert 
		if (clovaAuthDao.insertClovaAuthInfo(insertClovaAuthInfoVO) == 0) {
			logger.error("failed to insert Clova Auth Info data. membId({}) deviceCtn({})", membId,deviceCtn);
			loginResult.setResultCode(ResultCodeUtil.RC_4C005001);
			return loginResult;
		}
		
		if(insertClovaAuthInfoVO != null){
			resultData.setClovaAccessToken(insertClovaAuthInfoVO.getClovaToken());
			resultData.setExpiredIn(insertClovaAuthInfoVO.getExpiredTime());
			resultData.setRefreshToken(insertClovaAuthInfoVO.getRefreshToken());
			resultData.setTokenType(insertClovaAuthInfoVO.getTokenType());
		}

		loginResult.setResultCode(ResultCodeUtil.RC_2S000000);
		loginResult.setResultData(resultData);
		
		return loginResult;
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
		//insertClovaAuthInfoVO.setMgrappId(mgrappId);
		return insertClovaAuthInfoVO;
	}
	
	
	//TLO 확인
	private void setTloData(String  membId /*,String joinStatus*/) {
		Map<String, String> tlo = new HashMap<String, String>();
		
		
		tlo.put(TloData.MEMB_ID, membId);
		//tlo.put(TloData.MEMB_NO, memb.getMembNo());
		//tlo.put(TloData.JOIN_STATUS, joinStatus);
		
		TloUtil.setTloData(tlo);
	}
	
	
}