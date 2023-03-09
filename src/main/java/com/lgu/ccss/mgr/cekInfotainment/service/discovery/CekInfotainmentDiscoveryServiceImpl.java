package com.lgu.ccss.mgr.cekInfotainment.service.discovery;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lgu.ccss.common.constant.CCSSConst;
import com.lgu.ccss.common.dao.DeviceDao;
import com.lgu.ccss.common.dao.MemberDao;
import com.lgu.ccss.common.dao.clova.ClovaAuthDao;
import com.lgu.ccss.common.dao.mqtt.MqttMessageDao;
import com.lgu.ccss.common.model.ConnDeviceVO;
import com.lgu.ccss.common.model.MembVO;
import com.lgu.ccss.common.model.RequestJSON;
import com.lgu.ccss.common.model.ResponseJSON;
import com.lgu.ccss.common.model.clova.ClovaAuthInfoVO;
import com.lgu.ccss.common.model.mqtt.MqttMessageContentVO;
import com.lgu.ccss.common.model.mqtt.MqttMessageVO;
import com.lgu.ccss.common.util.CCSSUtil;
import com.lgu.ccss.common.util.ResultCodeUtil;
import com.lgu.ccss.common.validation.CheckResultData;
import com.lgu.ccss.common.validation.ValidationChecker;
import com.lgu.ccss.mgr.cekInfotainment.service.save.CekInfotainmentSaveServiceImpl;
import com.lgu.common.cekAi.auth.CekAuthConst;
import com.lgu.common.clova.auth.ClovaAuthAgent;
import com.lgu.common.clova.auth.model.ClovaAuthResponseDiscoveryJSON;
import com.lgu.common.model.ResultCode;

@Service("cekInfotainmentDiscoveryService")
public class CekInfotainmentDiscoveryServiceImpl implements CekInfotainmentDiscoveryService {
	private static final Logger logger = LoggerFactory.getLogger(CekInfotainmentSaveServiceImpl.class);
	private static final List<String> mandatoryList = new ArrayList<String>(
			Arrays.asList(RequestJSON.PARAM_MEMB_ID, RequestJSON.PARAM_SERVICE_CODE));

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

	@Value("#{config['mqtt.infortain.logout.content.title']}")
	private String infotainLogoutTitle;

	@Value("#{config['mqtt.infortain.logout.content.msg']}")
	private String infotainLogoutMsg;
	
	@Value("#{config['cek.ai.getAuth.code.reDirectUrl']}")
	private String cekGetAuthorizationRedirectUrl;
	
	@Autowired
	private DeviceDao deviceDao;
	
	@Autowired
	private MemberDao memberDao;
	
	@Autowired
	private MqttMessageDao mqttMessageDao;
	
	@Autowired
	private ClovaAuthDao clovaAuthDao;
	
	@Autowired
	private ClovaAuthAgent clovaAuthAgent;
	
	@Override
	@Transactional
	public ResponseJSON doService(HttpHeaders headers, RequestJSON reqJson) {
		ResultCode resultCode = ResultCodeUtil.RC_2S000000;
		String api = reqJson.getCommon().getApiId();
		String mgrappLoginId = CCSSUtil.getMgrUserLoginId();
		String sessionId = CCSSUtil.getMgrSessionId();
		String mgrappId = CCSSUtil.getMgrappId();
		String membId = (String) reqJson.getParam().get(RequestJSON.PARAM_MEMB_ID);
		String serviceCode = (String) reqJson.getParam().get(RequestJSON.PARAM_SERVICE_CODE);

		ResponseJSON response = null;

		CheckResultData result = ValidationChecker.checkValidation(headers, reqJson,CCSSConst.MANAGER_CEK_INFOTAINMENT_DISCOVERY, mandatoryList);

		if (result.isStatus() == false) {

			resultCode = ResultCodeUtil.RC_3C004000;
			response = ResultCodeUtil.createResultMsg(resultCode, api, result.getReason());
			return response;

		}
		try {
			// 차량 정보 조회 – 차량CTN, 차량 UICCID
			ConnDeviceVO connDeviceVO = new ConnDeviceVO();
			connDeviceVO.setMembId(membId);
			connDeviceVO = deviceDao.getDeviceInfo(connDeviceVO);
			String loginout = (String) reqJson.getParam().get(RequestJSON.PARAM_LOGINOUT);
			if (connDeviceVO != null) {


				response = linkDevie(reqJson);
				
				// Mqtt Push Message Insert
				if (response.getResultCode().equals(ResultCodeUtil.RC_2S000000.getCode())) {

					String code;
					if (serviceCode.equals(CekAuthConst.SERVICE_CODE_IOT) || serviceCode.equals(CekAuthConst.SERVICE_CODE_BIOT) 
							|| serviceCode.equals(CekAuthConst.SERVICE_CODE_CUSTIOT)) {
						code = mqttLoginCodeMap.get(CekAuthConst.SERVICE_CODE_IOT);
					} else if (serviceCode.equals(CekAuthConst.SERVICE_CODE_GENIE)) {
						code = mqttLoginCodeMap.get(CekAuthConst.SERVICE_CODE_GENIE);
					} else {
						code = "";
					}
					
					
					
					if (code != null && code.length() > 0) {

						MembVO membVO = memberDao.selectMemberByID(membId);
						MqttMessageContentVO mqttMessageContentVO = new MqttMessageContentVO();
						mqttMessageContentVO.getContent().put("code", code);

						HashMap<String, Object> infoapp = new HashMap<String, Object>();
						infoapp.put("id", CekAuthConst.SERVICE_CODE_IOT);
						mqttMessageContentVO.getContent().put("infoapp", infoapp);

						
						
						String msgTitle = "";
						String contentMsg = "";

						if(loginout.equals("login")) {
							msgTitle = CekAuthConst.SERVICE_CODE_IOT + infotainLoginTitle;
							contentMsg = CekAuthConst.SERVICE_CODE_IOT + infotainLoginMsg;
						}else {
							msgTitle =CekAuthConst.SERVICE_CODE_IOT + infotainLogoutTitle;
							contentMsg = CekAuthConst.SERVICE_CODE_IOT + infotainLogoutMsg;
						}
						
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
					} else {
						logger.error(
								"Not Invalid CEK AI ServiceCode  sessionId({}) membId({}) mgrappId({})  mgrappLoginId({}) , serviceCode({})",
								sessionId, membId, mgrappId, mgrappLoginId, serviceCode);
					}
				}
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
		
		return response;
	}

	private ResponseJSON linkDevie(RequestJSON reqJson) {
		ResultCode resultCode = ResultCodeUtil.RC_2S000000;
		String api = reqJson.getCommon().getApiId();
		String membId = (String) reqJson.getParam().get(RequestJSON.PARAM_MEMB_ID);
		String loginout = (String) reqJson.getParam().get(RequestJSON.PARAM_LOGINOUT);
		try {
			ClovaAuthInfoVO clovaAuthInfo = clovaAuthDao.selectClovaAuthInfo(membId);
			if(clovaAuthInfo == null){
				return ResultCodeUtil.createResultMsg(resultCode, api);
			}
			ClovaAuthResponseDiscoveryJSON discoveryResp = clovaAuthAgent.discoveryClova(clovaAuthInfo.getClovaToken());
			if(discoveryResp == null) {
				resultCode = ResultCodeUtil.RC_2S000000;
				return ResultCodeUtil.createResultMsg(resultCode, api);
			}
			else if(discoveryResp.getResult() == null) {
				resultCode = ResultCodeUtil.RC_2S000000;
				return ResultCodeUtil.createResultMsg(resultCode, api);
			}
			else if(discoveryResp.getResult().getDeviceInfoList() == null) {
				resultCode = ResultCodeUtil.RC_2S000000;
				return ResultCodeUtil.createResultMsg(resultCode, api);
			}
			else if(discoveryResp.getResult().getDeviceInfoList().size() == 0) {
				resultCode = ResultCodeUtil.RC_2S000000;
				return ResultCodeUtil.createResultMsg(resultCode, api);
			}
			ClovaAuthResponseDiscoveryJSON connectResp = null;
			for(int i =0; i < discoveryResp.getResult().getDeviceInfoList().size(); i++) {
				if(loginout.equals("login")) {
					connectResp = clovaAuthAgent.connectClova(discoveryResp.getResult().getDeviceInfoList().get(i).getDeviceId(), clovaAuthInfo.getClovaToken());					
				}else {
					connectResp = clovaAuthAgent.disConnectClova(discoveryResp.getResult().getDeviceInfoList().get(i).getDeviceId(), clovaAuthInfo.getClovaToken());
				}
				if(connectResp == null) {
					resultCode = ResultCodeUtil.RC_2S000000;
					return ResultCodeUtil.createResultMsg(resultCode, api);
				}
			}
			return ResultCodeUtil.createResultMsg(ResultCodeUtil.RC_2S000000, api);
		} catch (NumberFormatException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			return ResultCodeUtil.createResultMsg(resultCode, api);
		} catch (UnsupportedEncodingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			return ResultCodeUtil.createResultMsg(resultCode, api);
		}
	}
	
	
}
