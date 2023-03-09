package com.lgu.ccss.mgr.cekInfotainment.service.logout;

import java.util.ArrayList;
import java.util.Arrays;
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

import com.lgu.ccss.common.constant.CCSSConst;
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
import com.lgu.ccss.mgr.infotainment.service.logout.MgrInfotainmentLogoutServiceImpl;
import com.lgu.common.aisv.auth.AisvAuthConst;
import com.lgu.common.cekAi.auth.CekAuthConst;
import com.lgu.common.model.ResultCode;

@Service("cekInfotainmentLogoutService")
public class CekInfotainmentLogoutServiceImpl implements CekInfotainmentLogoutService{
	private static final Logger logger = LoggerFactory.getLogger(MgrInfotainmentLogoutServiceImpl.class);
	private static final List<String> mandatoryList = new ArrayList<String>(
			Arrays.asList(RequestJSON.PARAM_MEMB_ID,RequestJSON.PARAM_SERVICE_CODE,RequestJSON.PARAM_NAVER_LOGIN_ID));
	
	@SuppressWarnings("serial")
	private static final HashMap<String, String> mqttLoginCodeMap = new HashMap<String, String>() {
		{
			put(CekAuthConst.SERVICE_CODE_IOT, MqttMessageVO.CEK_IOT_LOGIN_LOGOUT_PUSH_CODE);
			put(CekAuthConst.SERVICE_CODE_GENIE, MqttMessageVO.MUSIC_LOGIN_LOGOUT_PUSH_CODE);
		}
	};	
	
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
	private MgrAppOneIdAuthInfo mgrAppOneIdAuthInfo;
	
	@Override
	@Transactional
	public ResponseJSON doService(HttpHeaders headers, RequestJSON reqJson) {
		ResultCode resultCode=ResultCodeUtil.RC_2S000000;
		String api = reqJson.getCommon().getApiId();
		
		ResponseJSON response = null;
		
		CheckResultData result = ValidationChecker.checkValidation(headers,reqJson,CCSSConst.MANAGER_CEK_INFOTAINMENT_LOGOUT,mandatoryList);
		
		if (result.isStatus() == false) {
			
			resultCode = ResultCodeUtil.RC_3C004000;
			response = ResultCodeUtil.createResultMsg(resultCode,api,result.getReason());
			return response;
		
		}else{
			
			String membId = (String) reqJson.getParam().get(RequestJSON.PARAM_MEMB_ID);
			setTloData(membId);
			response = logoutCekInfotainment(headers, reqJson);			
		}
		return response;
	}

	
	private ResponseJSON logoutCekInfotainment(HttpHeaders headers, RequestJSON reqJson) {
		ResultCode resultCode=ResultCodeUtil.RC_2S000000;
		
		String api = reqJson.getCommon().getApiId();
		String mgrappLoginId = CCSSUtil.getMgrUserLoginId();
		String sessionId = CCSSUtil.getMgrSessionId();
		String membId = (String) reqJson.getParam().get(RequestJSON.PARAM_MEMB_ID);
		String mgrappId = CCSSUtil.getMgrappId();
		
		// BM(BM_MGR_APP)에서 AI연동을 제거하기위한 앱타입정보 (2019/11/22)
		String appType = reqJson.getCommon().getDevice().getAppType();
		
		String serviceCode = (String) reqJson.getParam().get(RequestJSON.PARAM_SERVICE_CODE);
		
		if (mgrAppOneIdAuthInfo.updateCekOneIdAuthInfo(membId) == 0) {
			//resultCode = ResultCodeUtil.RC_4C005001;
			logger.error("failed to update OneIdAuthInfo data. mgrappId({}) sessionId({}) mgrappLoginId({})  deviceCtn({}) serviceCode({})", mgrappId,sessionId,mgrappLoginId,serviceCode);
			resultCode = ResultCodeUtil.RC_4C005001;
			return ResultCodeUtil.createResultMsg(resultCode, api);
		}
		
		/*
		 * Date : 2019/11/22 (멀티홈코드 개발건 연계)
		 * Author : JYJ
		 * Description :
		 * 		BM에서 AI 연동을 제거하기 위해 AM으로 기본 프로세스는 유지한채  BM 별도 분기처리 (음성처리 AI 연동제거)
		 * 		BM에서는 매니저앱에서 전달받은 CEK정보를 U+우리집AI와 연동처리를 안하고 DB에만 저장처리
		 *      => 기존 Discovery후 Car Push를 보냈으나 BM에서는 clova연동을 하지 않으므로, 홈IoT인증정보 로그아웃 후 곧바로 발송
		 */
		// BM(BM_MGR_APP)의 경우 (신규)
		if (appType.equals(AisvAuthConst.DEVICE_BM_APP_TYPE)) {

			try {
			
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
	
				String msgTitle = CekAuthConst.SERVICE_CODE_IOT + infotainLogoutTitle;
				String contentMsg = CekAuthConst.SERVICE_CODE_IOT + infotainLogoutMsg;
	
				// 차량 정보 조회 – 차량CTN, 차량 UICCID
				ConnDeviceVO connDeviceVO = new ConnDeviceVO();
				connDeviceVO.setMembId(membId);
				connDeviceVO = deviceDao.getDeviceInfo(connDeviceVO);
				
				if (connDeviceVO != null) {
				
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
					resultCode = ResultCodeUtil.RC_3C002006;
					logger.error("Not Exist Device Info. mgrappId({}) sessionId({}) membId({}) mgrappLoginId({})",
					mgrappId, sessionId, membId, mgrappLoginId);
					return ResultCodeUtil.createResultMsg(resultCode, api);
				}
			
			} catch (Exception e) {
				resultCode = ResultCodeUtil.RC_4C005000;
				logger.error("mgrappId({}) sessionId({}) membId({}) mgrappLoginId({}) Exception({})", mgrappId,
				sessionId, membId, mgrappLoginId, e);
				return ResultCodeUtil.createResultMsg(resultCode, api);
			}
			
		}
		
		return ResultCodeUtil.createResultMsg(resultCode, api);
	}


	private void setTloData(String membId) {
		if (membId == null || membId.length() <=0) {
			return;
		}
		Map<String, String> tlo = new HashMap<String, String>();
		tlo.put(TloData.MEMB_ID, membId);
		TloUtil.setTloData(tlo);
	}
}
