package com.lgu.ccss.mgr.cekInfotainment.service.save;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.lgu.ccss.common.constant.CCSSConst;
import com.lgu.ccss.common.dao.CekInfotainAuthInfoDao;
import com.lgu.ccss.common.dao.DeviceDao;
import com.lgu.ccss.common.dao.MemberDao;
import com.lgu.ccss.common.dao.mgr.MgrAppOneIdAuthInfo;
import com.lgu.ccss.common.dao.mqtt.MqttMessageDao;
import com.lgu.ccss.common.model.ConnDeviceVO;
import com.lgu.ccss.common.model.MembVO;
import com.lgu.ccss.common.model.RequestJSON;
import com.lgu.ccss.common.model.ResponseJSON;
import com.lgu.ccss.common.model.mqtt.MqttMessageContentVO;
import com.lgu.ccss.common.model.mqtt.MqttMessageVO;
import com.lgu.ccss.common.tlo.TloData;
import com.lgu.ccss.common.tlo.TloUtil;
import com.lgu.ccss.common.util.CCSSUtil;
import com.lgu.ccss.common.util.ResultCodeUtil;
import com.lgu.ccss.common.validation.CheckResultData;
import com.lgu.ccss.common.validation.ValidationChecker;
import com.lgu.ccss.mgr.cekInfotainment.model.getNidSearch.CekInfoJSON;
import com.lgu.ccss.mgr.cekInfotainment.model.getNidSearch.ResultNidInfoJSON;
import com.lgu.common.aisv.auth.AisvAuthAgent;
import com.lgu.common.aisv.auth.AisvAuthConst;
import com.lgu.common.aisv.auth.model.AisvAuthResponseJSON;
import com.lgu.common.cekAi.auth.CekAuthAgent;
import com.lgu.common.cekAi.auth.CekAuthConst;
import com.lgu.common.cekAi.auth.model.CekAuthRequestBodyJSON;
import com.lgu.common.cekAi.auth.model.CekAuthResponseJSON;
import com.lgu.common.model.ResultCode;

@Service("cekInfotainmentSaveService")
public class CekInfotainmentSaveServiceImpl implements CekInfotainmentSaveService {

	private static final Logger logger = LoggerFactory.getLogger(CekInfotainmentSaveServiceImpl.class);
	private static final List<String> mandatoryList = new ArrayList<String>(
			Arrays.asList(RequestJSON.PARAM_MEMB_ID, RequestJSON.PARAM_SERVICE_CODE, RequestJSON.PARAM_NAVER_LOGIN_ID));

	@SuppressWarnings("serial")
	private static final HashMap<String, String> mqttLoginCodeMap = new HashMap<String, String>() {
		{
			put(CekAuthConst.SERVICE_CODE_IOT, MqttMessageVO.CEK_IOT_LOGIN_LOGOUT_PUSH_CODE);
			put(CekAuthConst.SERVICE_CODE_GENIE, MqttMessageVO.MUSIC_LOGIN_LOGOUT_PUSH_CODE);
		}
	};
	
	@Value("#{config['mqtt.infortain.login.content.title']}")
	private String infotainLoginTitle;

	@Value("#{config['mqtt.infortain.login.content.msg']}")
	private String infotainLoginMsg;

	@Value("#{config['cek.ai.getAuth.code.reDirectUrl']}")
	private String cekGetAuthorizationRedirectUrl;

	@Autowired
	private CekInfotainAuthInfoDao cekInfotainAuthInfoDao;

	@Autowired
	private CekAuthAgent cekAuthAgent;

	@Autowired
	private DeviceDao deviceDao;

	@Autowired
	private MemberDao memberDao;	
	
	@Autowired
	private MqttMessageDao mqttMessageDao;
		
	
	@Autowired
	private MgrAppOneIdAuthInfo mgrAppOneIdAuthInfo;

	@Autowired
	private AisvAuthAgent aisvAuthAgent;

	@Override
	@Transactional
	public ResponseJSON doService(HttpHeaders headers, RequestJSON reqJson) {
		ResultCode resultCode = ResultCodeUtil.RC_2S000000;
		String api = reqJson.getCommon().getApiId();
		String mgrappLoginId = CCSSUtil.getMgrUserLoginId();
		String sessionId = CCSSUtil.getMgrSessionId();
		String mgrappId = CCSSUtil.getMgrappId();
		String serviceCode = (String) reqJson.getParam().get(RequestJSON.PARAM_SERVICE_CODE);
		String membId = (String) reqJson.getParam().get(RequestJSON.PARAM_MEMB_ID);

		// BM(BM_MGR_APP)?????? AI????????? ?????????????????? ??????????????? (2019/11/11)
		String appType = reqJson.getCommon().getDevice().getAppType();
		
		ResponseJSON response = null;

		CheckResultData result = ValidationChecker.checkValidation(headers, reqJson,
				CCSSConst.MANAGER_CEK_INFOTAINMENT_SAVE, mandatoryList);

		if (result.isStatus() == false) {

			resultCode = ResultCodeUtil.RC_3C004000;
			response = ResultCodeUtil.createResultMsg(resultCode, api, result.getReason());
			return response;

		} else {
			
			/*
			 * Date : 2019/11/11 (??????????????? ????????? ??????)
			 * Author : JYJ
			 * Description :
			 * 		BM?????? AI ????????? ???????????? ?????? AM?????? ?????? ??????????????? ????????????  BM ?????? ???????????? (???????????? AI ????????????)
			 * 		BM????????? ?????????????????? ???????????? CEK????????? U+?????????AI??? ??????????????? ????????? DB?????? ????????????
			 */
			// BM(BM_MGR_APP)??? ?????? (??????)
			if (appType.equals(AisvAuthConst.DEVICE_BM_APP_TYPE)) {
				
				//////////////////////////////////////////////////////////////////////////////////
				setTloData(membId);
				
				try {

					// ?????? ?????? ?????? ??? ??????CTN, ?????? UICCID
					ConnDeviceVO connDeviceVO = new ConnDeviceVO();
					connDeviceVO.setMembId(membId);
					connDeviceVO = deviceDao.getDeviceInfo(connDeviceVO);
				
					if (connDeviceVO != null) {
						
						Map<String, String> authParameter = new LinkedHashMap<String, String>();

						for (String key : reqJson.getParam().keySet()) {
							if (key.startsWith("authParameter")) {
								String value = (String) reqJson.getParam().get(key);
								if (value != null) {
									authParameter.put(key, value);
								}
							}
						}
						
						String nid = (String) reqJson.getParam().get(RequestJSON.PARAM_NAVER_LOGIN_ID);
						String deviceCtn = connDeviceVO.getDeviceCtn();
						String iotCode = CekAuthConst.SERVICE_CODE_IOT;
						
						// TB_CEK_INFOTAIN_AUTHINFO ??????
						for (String key : authParameter.keySet()) {
							if (insertCekInfotainAuthInfo(membId, mgrappId, iotCode, key, authParameter.get(key),
									nid) == false) {
								logger.error(
										"failed to insert Cek InfotainAuthInfo data (BM). mgrappId({}) sessionId({}) mgrappLoginId({}) deviceCtn({}) parameter({}:{})",
										mgrappId, sessionId, mgrappLoginId, deviceCtn, key, authParameter.get(key));
								return ResultCodeUtil.createResultMsg(ResultCodeUtil.RC_4C005001, api);
							}
						}
						
						// TB_ONEID_AUTHINFO One ID ?????? ??????
						String oneId = (String) reqJson.getParam().get(CekAuthConst.PARAM_NAME_ONE_ID_KEY_VALUE);	
						String ssoKey = (String) reqJson.getParam().get(CekAuthConst.PARAM_NAME_SSO_KEY_VALUE);	
						String oneIdMembNo = (String) reqJson.getParam().get(CekAuthConst.PARAM_NAME_CUSTOM_ID_KEY_VALUE);	
						String iotSessionKey = (String) reqJson.getParam().get(CekAuthConst.PARAM_NAME_IOT_SESSION_KEY);	
						String homeCode = (String) reqJson.getParam().get(CekAuthConst.PARAM_NAME_HOME_CODE);	
						String ctn = (String) reqJson.getParam().get(CekAuthConst.PARAM_NAME_CTN);	
						int oneIdAuthInfoCnt = mgrAppOneIdAuthInfo.insertCekOneIdAuthInfo(membId, oneId, ssoKey, oneIdMembNo,
								iotSessionKey, homeCode, ctn, nid, null);
						
						if (oneIdAuthInfoCnt > 0) {
							logger.info(
									"Insert to TB_ONEID_AUTHINFO (BM). mgrappId({}) sessionId({}) mgrappLoginId({}) membId({}) ondId({}) ssoKey({}) oneIdMembNo({}) iotSessionKey({})",
									mgrappId, sessionId, mgrappLoginId, membId, oneId, ssoKey, oneIdMembNo, iotSessionKey);
						} else {
							logger.info(
									"failed to insert TB_ONEID_AUTHINFO data (BM). mgrappId({}) sessionId({}) mgrappLoginId({}) membId({}) ondId({}) ssoKey({}) oneIdMembNo({})",
									mgrappId, sessionId, mgrappLoginId, membId, oneId, ssoKey, oneIdMembNo);
							return ResultCodeUtil.createResultMsg(ResultCodeUtil.RC_5C200004, api);
						}
						
						// ?????? Discovery??? Car Push??? ???????????? clova????????? ?????? ????????????, ???IoT???????????? ?????? ??? ????????? ??????
						String code = "";
						if (serviceCode.equals(CekAuthConst.SERVICE_CODE_IOT) || serviceCode.equals(CekAuthConst.SERVICE_CODE_BIOT) 
								|| serviceCode.equals(CekAuthConst.SERVICE_CODE_CUSTIOT)) {
							code = mqttLoginCodeMap.get(CekAuthConst.SERVICE_CODE_IOT);
						} else if (serviceCode.equals(CekAuthConst.SERVICE_CODE_GENIE)) {
							code = mqttLoginCodeMap.get(CekAuthConst.SERVICE_CODE_GENIE);
						} else {
							logger.error(
									"Not Invalid CEK AI ServiceCode  sessionId({}) membId({}) mgrappId({})  mgrappLoginId({}) , serviceCode({})",
									sessionId, membId, mgrappId, mgrappLoginId, serviceCode);
						}
						
						MembVO membVO = memberDao.selectMemberByID(membId);
						MqttMessageContentVO mqttMessageContentVO = new MqttMessageContentVO();
						mqttMessageContentVO.getContent().put("code", code);

						HashMap<String, Object> infoapp = new HashMap<String, Object>();
						infoapp.put("id", CekAuthConst.SERVICE_CODE_IOT);
						mqttMessageContentVO.getContent().put("infoapp", infoapp);

						
						String msgTitle = CekAuthConst.SERVICE_CODE_IOT + infotainLoginTitle;
						String contentMsg = CekAuthConst.SERVICE_CODE_IOT + infotainLoginMsg;

						MqttMessageVO mqttMessageVO = MqttMessageVO.makeMqttMessage(membVO.getMarketType(),
								connDeviceVO.getDeviceCtn(), MqttMessageVO.SEND_TYPE_EMERGENCY,
								MqttMessageVO.MASSAGE_TYPE_SINGLE, code, msgTitle, contentMsg, null, null,
								mqttMessageContentVO);

						boolean sendMqttResulFlag = mqttMessageDao.insertTbCarPushQueue(mqttMessageVO);

						if (sendMqttResulFlag) {
							logger.info(
									"Send Car Push Message Insert Success. sessionId({}) membId({}) mgrappId({})  mgrappLoginId({})",
									sessionId, membId, mgrappId, mgrappLoginId);
						} else {
							resultCode = ResultCodeUtil.RC_4C005001;
							logger.error(
									"Send Car Push Message Insert. Fail. sessionId({}) membId({}) mgrappId({})  mgrappLoginId({}) mqttMessageVO({})",
									sessionId, membId, mgrappId, mgrappLoginId, mqttMessageVO.toString());
						}
						
						response = ResultCodeUtil.createResultMsg(resultCode, api);

					} else {
						resultCode = ResultCodeUtil.RC_3C002006;
						logger.error("Not Exist Device Info. mgrappId({}) sessionId({}) membId({}) mgrappLoginId({})",
						mgrappId, sessionId, membId, mgrappLoginId);
						response = ResultCodeUtil.createResultMsg(resultCode, api);
					}
				} catch (Exception e) {
					resultCode = ResultCodeUtil.RC_4C005000;
					logger.error("mgrappId({}) sessionId({}) membId({}) mgrappLoginId({}) Exception({})", mgrappId,
					sessionId, membId, mgrappLoginId, e);
					response = ResultCodeUtil.createResultMsg(resultCode, api);
				}
				
				
			// AM??? ?????? (?????? ???????????? ??????)
			} else {
				
				String SUCCESS = ResultCodeUtil.RC_2S000000.getCode(); // ??????
				String NEED = ResultCodeUtil.RC_5C200007.getCode(); // ????????? ????????????
				String TEMP = ResultCodeUtil.RC_5C200008.getCode(); // ?????? ????????? ?????? ??????
	
				ResponseJSON responseGetNidInfo = null;
				ResponseJSON responseTempIdRegist = null;
	
				String codeGetNidInfo = "";
				String codeTempIdRegist = "";
	
				RequestJSON reqJsonVirtual = new RequestJSON();
				reqJsonVirtual.setCommon(reqJson.getCommon());
				reqJsonVirtual.setParam(reqJson.getParam());
				//////////////////////////////////////////////////////////////////////////////////
				// CekInfotainmentGetNidSearchServiceImpl
				//////////////////////////////////////////////////////////////////////////////////
				
				responseGetNidInfo = getNidInfo(headers, reqJson);
				codeGetNidInfo = responseGetNidInfo.getResultCode();
				if (TEMP.equals(codeGetNidInfo)) {
					// ?????????????????? ????????? ?????? ?????? ???????????? ???????????? ????????????.
					ResultNidInfoJSON resultNidInfoJSON = (ResultNidInfoJSON) responseGetNidInfo.getResultData();
	
					// ?????? OneId, ?????? OneIdKey ?????? ??????
					Map<String, Object> vParam = new LinkedHashMap<String, Object>();
	
					for (String key : reqJson.getParam().keySet()) {
						vParam.put(key, reqJson.getParam().get(key));
					}
	
					vParam.put("authParameter1", resultNidInfoJSON.getOneId());
					vParam.put("authParameter2", resultNidInfoJSON.getOneIdKey());
					
					reqJsonVirtual.setParam(vParam);
	
				} else if (NEED.equals(codeGetNidInfo)) {
					//////////////////////////////////////////////////////////////////////////////////
					// CekInfotainmentTempIdRegistServiceImpl
					//////////////////////////////////////////////////////////////////////////////////
	
					responseTempIdRegist = tempIdRegist(headers, reqJson);
					codeTempIdRegist = responseTempIdRegist.getResultCode();
					if (!SUCCESS.equals(codeTempIdRegist)) {
						return response;
					}
	
					// ????????? ???????????? ?????? ????????? ???????????? ????????? ???????????? ???????????? ????????????.
					ResultNidInfoJSON resultNidInfoJSON = (ResultNidInfoJSON) responseTempIdRegist.getResultData();
	
					// ?????? OneId, ?????? OneIdKey ?????? ??????
					Map<String, Object> vParam = new LinkedHashMap<String, Object>();
					for (String key : reqJson.getParam().keySet()) {
						vParam.put(key, reqJson.getParam().get(key));
					}
					
					vParam.put("authParameter1", resultNidInfoJSON.getOneId());
					vParam.put("authParameter2", resultNidInfoJSON.getOneIdKey());
					
					reqJsonVirtual.setParam(vParam);
	
				} else if (!SUCCESS.equals(codeGetNidInfo)) {
					resultCode = ResultCodeUtil.RC_5C200006;
					response = ResultCodeUtil.createResultMsg(resultCode, api);
					return response;
				}
	
				//////////////////////////////////////////////////////////////////////////////////
				setTloData(membId);
				
				try {
					// ?????? ?????? ?????? ??? ??????CTN, ?????? UICCID
					ConnDeviceVO connDeviceVO = new ConnDeviceVO();
					connDeviceVO.setMembId(membId);
					connDeviceVO = deviceDao.getDeviceInfo(connDeviceVO);
					
					if (connDeviceVO != null) {
						
						response = saveCekInfotainment(headers, reqJsonVirtual, reqJson, connDeviceVO);
	
					} else {
						resultCode = ResultCodeUtil.RC_3C002006;
						logger.error("Not Exist Device Info. mgrappId({}) sessionId({}) membId({}) mgrappLoginId({})",
								mgrappId, sessionId, membId, mgrappLoginId);
						response = ResultCodeUtil.createResultMsg(resultCode, api);
					}
				} catch (Exception e) {
					resultCode = ResultCodeUtil.RC_4C005000;
					logger.error("mgrappId({}) sessionId({}) membId({}) mgrappLoginId({}) Exception({})", mgrappId,
							sessionId, membId, mgrappLoginId, e);
					response = ResultCodeUtil.createResultMsg(resultCode, api);
				}
			}
		}
		return response;
	}

	private ResponseJSON saveCekInfotainment(HttpHeaders headers, RequestJSON reqJsonVirtual, RequestJSON reqJson,
			ConnDeviceVO connDeviceVO) throws JsonGenerationException, JsonMappingException, IOException {
		ResultCode resultCode = ResultCodeUtil.RC_2S000000;
		String api = reqJson.getCommon().getApiId();
		String mgrappLoginId = CCSSUtil.getMgrUserLoginId();
		String sessionId = CCSSUtil.getMgrSessionId();
		String membId = (String) reqJson.getParam().get(RequestJSON.PARAM_MEMB_ID);
		String mgrappId = CCSSUtil.getMgrappId();
		String serviceCode = (String) reqJson.getParam().get(RequestJSON.PARAM_SERVICE_CODE);
		String nid = (String) reqJson.getParam().get(RequestJSON.PARAM_NAVER_LOGIN_ID);
		String deviceCtn = connDeviceVO.getDeviceCtn();

		// ?????? ?????? ???????????? ?????? ??????
		// CekAIAuthInfoVO cekAiAuthInfo =
		// cekAuthInfoDao.selectCekAIAuthInfo(membId,nid);

		CekAuthResponseJSON cekAuthResponseJSON = null;

		Map<String, String> authParameter = new LinkedHashMap<String, String>();

		// [2018.08.29 jyj] AI ????????????OneId ?????? ??????
		/*
		 * if (tempOneId != null && !tempOneId.isEmpty()) {
		 * authParameter.put(RequestJSON.PARAM_TEMP_ONE_ID, tempOneId); }
		 */

		for (String key : reqJson.getParam().keySet()) {
			if (key.startsWith("authParameter")) {
				String value = (String) reqJson.getParam().get(key);
				if (value != null) {
					authParameter.put(key, value);
				}
			} else {
				logger.error("Ignore Key name is Invalied. mgrappId({}) sessionId({}) mgrappLoginId({}) key({}) ",
						mgrappId, sessionId, mgrappLoginId, key);
			}
		}

		Map<String, String> cekAuthInfo = new LinkedHashMap<String, String>();
		cekAuthInfo.put(CekAuthRequestBodyJSON.CEK_AUTH_INFO_CONT_SVC_CD, serviceCode);
		cekAuthInfo.putAll(authParameter);

		// 2018-09-28 JYJ (?????????AI???????????? ?????????, ??????ID??? ????????? ???????????? ????????? ????????????ID??? ??????????????? ??????)
		//cekAuthResponseJSON = cekAuthAgent.saveSvcAuthInfo(reqJsonVirtual, nid, cekAuthInfo, mgrappLoginId);
		cekAuthResponseJSON = cekAuthAgent.saveSvcAuthInfo(reqJson, nid, cekAuthInfo, mgrappLoginId);

		if (cekAuthResponseJSON == null) {

			resultCode = ResultCodeUtil.RC_5A000002;
			logger.error("failed to save Cek Infotainment data. mgrappId({}) sessionId({}) mgrappLoginId({})", mgrappId,
					sessionId, mgrappLoginId);

		} else {
			String iotCode = CekAuthConst.SERVICE_CODE_IOT;
			// AI?????????????????? ???????????? ????????? ???????????? ???????????? ?????? (2018.08.29 ?????????)
			// logger.error("AI???????????? ????????????:" +
			// cekAuthResponseJSON.getBody().toString());
			for (String key : authParameter.keySet()) {
				if (insertCekInfotainAuthInfo(membId, mgrappId, iotCode, key, authParameter.get(key),
						nid) == false) {
					logger.error(
							"failed to insert Cek InfotainAuthInfo data. mgrappId({}) sessionId({}) mgrappLoginId({}) deviceCtn({}) parameter({}:{})",
							mgrappId, sessionId, mgrappLoginId, deviceCtn, key, authParameter.get(key));
					return ResultCodeUtil.createResultMsg(ResultCodeUtil.RC_4C005001, api);
				}
			}

			// TB_ONEID_AUTHINFO One ID ?????? ??????
			String oneId = (String) reqJson.getParam().get(CekAuthConst.PARAM_NAME_ONE_ID_KEY_VALUE);
			String ssoKey = (String) reqJson.getParam().get(CekAuthConst.PARAM_NAME_SSO_KEY_VALUE);
			String oneIdMembNo = (String) reqJson.getParam().get(CekAuthConst.PARAM_NAME_CUSTOM_ID_KEY_VALUE);
			String iotSessionKey = (String) reqJson.getParam().get(CekAuthConst.PARAM_NAME_IOT_SESSION_KEY);
			String homeCode = (String) reqJson.getParam().get(CekAuthConst.PARAM_NAME_HOME_CODE);
			String ctn = (String) reqJson.getParam().get(CekAuthConst.PARAM_NAME_CTN);
			int oneIdAuthInfoCnt = mgrAppOneIdAuthInfo.insertCekOneIdAuthInfo(membId, oneId, ssoKey, oneIdMembNo,
					iotSessionKey, homeCode, ctn, nid, null);

			if (oneIdAuthInfoCnt > 0) {
				logger.info(
						"Insert to TB_ONEID_AUTHINFO. mgrappId({}) sessionId({}) mgrappLoginId({}) membId({}) ondId({}) ssoKey({}) oneIdMembNo({})",
						mgrappId, sessionId, mgrappLoginId, membId, oneId, ssoKey, oneIdMembNo);

			} else {
				resultCode = ResultCodeUtil.RC_5C200004;
				logger.info(
						"Insert to TB_ONEID_AUTHINFO. mgrappId({}) sessionId({}) mgrappLoginId({}) membId({}) ondId({}) ssoKey({}) oneIdMembNo({})",
						mgrappId, sessionId, mgrappLoginId, membId, oneId, ssoKey, oneIdMembNo);
				return ResultCodeUtil.createResultMsg(resultCode, api);
			}
		}
		return ResultCodeUtil.createResultMsg(resultCode, api);
	}

	private boolean insertCekInfotainAuthInfo(String membId, String mgrappId, String serviceId, String tokenName,
			String tokenValue, String nid) {
		if (tokenValue == null || tokenValue.length() == 0) {
			return true;
		}
		if (cekInfotainAuthInfoDao.insertCekInfotainAuthInfo(membId, mgrappId, serviceId, tokenName, tokenValue,
				nid) == 0) {
			return false;
		}
		return true;
	}

	private void setTloData(String membId) {
		if (membId == null || membId.length() <= 0) {
			return;
		}
		Map<String, String> tlo = new HashMap<String, String>();
		tlo.put(TloData.MEMB_ID, membId);
		TloUtil.setTloData(tlo);
	}

	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	//
	////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	public ResponseJSON getNidInfo(HttpHeaders headers, RequestJSON reqJson) {
		ResultCode resultCode = ResultCodeUtil.RC_2S000000;
		String api = reqJson.getCommon().getApiId();
		String appType = reqJson.getCommon().getDevice().getAppType();
		String carOem = reqJson.getCommon().getDevice().getCarOem();
		String nid = (String) reqJson.getParam().get(RequestJSON.PARAM_NAVER_LOGIN_ID);

		setTloData(nid);

		ResponseJSON response = null;
		ResultNidInfoJSON resultNidInfoJSON = null;
		CekInfoJSON cekInfo = null;

		AisvAuthResponseJSON aisvAuthResponseJSON = null;
		try {
			aisvAuthResponseJSON = aisvAuthAgent.getNidInfo2(reqJson, appType, carOem);

			if (aisvAuthResponseJSON != null) {
				resultNidInfoJSON = new ResultNidInfoJSON();
				if (aisvAuthResponseJSON.getData() != null) {
					resultNidInfoJSON.setOneIdKey(aisvAuthResponseJSON.getData().getOneIdKey());
					resultNidInfoJSON.setOneId(aisvAuthResponseJSON.getData().getOneId());
					resultNidInfoJSON.setSsoKey(aisvAuthResponseJSON.getData().getSsoKey());
					resultNidInfoJSON.setTempIdYn(aisvAuthResponseJSON.getData().getTempIdYn());
					resultNidInfoJSON.setCtn(aisvAuthResponseJSON.getData().getCtn());
					resultNidInfoJSON.setAiTempIdYn(aisvAuthResponseJSON.getData().getAiTempIdYn());
					resultNidInfoJSON.setAisvToken(aisvAuthResponseJSON.getData().getAisvToken());

					cekInfo = new CekInfoJSON();
					cekInfo.setCekUserId(aisvAuthResponseJSON.getData().getCekInfo().getCekUserId());
					cekInfo.setOneIdKey(aisvAuthResponseJSON.getData().getCekInfo().getCekOneIdKey());
					cekInfo.setCekSsoKey(aisvAuthResponseJSON.getData().getCekInfo().getCekSsoKey());
					cekInfo.setCekTempIdYn(aisvAuthResponseJSON.getData().getCekInfo().getCekTempIdYn());
					cekInfo.setCekCtn(aisvAuthResponseJSON.getData().getCekInfo().getCekCtn());

					resultNidInfoJSON.setCekInfo(cekInfo);
					if (resultNidInfoJSON.getOneId() != null && resultNidInfoJSON.getOneIdKey() != null
							&& "Y".equalsIgnoreCase(aisvAuthResponseJSON.getData().getAiTempIdYn())) {
						// ?????? ???????????? ????????? ?????? ??????.
						resultCode = ResultCodeUtil.RC_5C200008;
					}
				} else {
					resultNidInfoJSON = null;
					// response Data ??????
					resultCode = ResultCodeUtil.RC_5C200007;
				}
			} else {
				// ?????? ??????
				resultCode = ResultCodeUtil.RC_5C200006;
				//response = ResultCodeUtil.createResultMsg(resultCode, api);
				//return response;
			}

		} catch (Exception e) {
			resultCode = ResultCodeUtil.RC_4C005000;
			logger.error("sessionId({}) mgrappLoginId({}) Exception({})", null, null, e);
		}

		response = ResultCodeUtil.createResultMsg(resultCode, resultNidInfoJSON, api);
		System.out.println("response####" + response.toString());
		return response;
	}

	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	//
	////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	public ResponseJSON tempIdRegist(HttpHeaders headers, RequestJSON reqJson) {
		ResultCode resultCode = ResultCodeUtil.RC_2S000000;
		String api = reqJson.getCommon().getApiId();
		String appType = reqJson.getCommon().getDevice().getAppType();
		String carOem = reqJson.getCommon().getDevice().getCarOem();
		String mgrappId = CCSSUtil.getMgrappId();

		setTloData(mgrappId);

		ResponseJSON response = null;

		ResultNidInfoJSON resultNidInfoJSON = null;
		AisvAuthResponseJSON aisvAuthResponseJSON = null;

		try {
			aisvAuthResponseJSON = aisvAuthAgent.tempIdRegist2(reqJson, appType, carOem);
			if (aisvAuthResponseJSON != null) {
				resultNidInfoJSON = new ResultNidInfoJSON();
				resultNidInfoJSON.setOneId(aisvAuthResponseJSON.getData().getOneId());
				resultNidInfoJSON.setOneIdKey(aisvAuthResponseJSON.getData().getOneIdKey());
				resultNidInfoJSON.setAisvToken(aisvAuthResponseJSON.getData().getAisvToken());
			} else {
				// ?????? ??????
				resultCode = ResultCodeUtil.RC_5C200006;
			}
		} catch (Exception e) {
			resultCode = ResultCodeUtil.RC_4C005000;
			logger.error("sessionId({}) mgrappLoginId({}) Exception({})", null, null, e);
		}

		response = ResultCodeUtil.createResultMsg(resultCode, resultNidInfoJSON, api);
		return response;
	}
}
