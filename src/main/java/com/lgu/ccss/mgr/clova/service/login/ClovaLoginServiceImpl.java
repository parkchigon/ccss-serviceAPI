package com.lgu.ccss.mgr.clova.service.login;



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

import com.lgu.ccss.common.constant.CCSSConst;
import com.lgu.ccss.common.dao.DeviceDao;
import com.lgu.ccss.common.dao.MemberDao;
import com.lgu.ccss.common.dao.ModelDao;
import com.lgu.ccss.common.dao.clova.ClovaAuthDao;
import com.lgu.ccss.common.dao.mqtt.MqttMessageDao;
import com.lgu.ccss.common.model.ConnDeviceVO;
import com.lgu.ccss.common.model.DeviceModelVO;
import com.lgu.ccss.common.model.MembVO;
import com.lgu.ccss.common.model.RequestJSON;
import com.lgu.ccss.common.model.ResponseJSON;
import com.lgu.ccss.common.model.clova.ClovaAuthInfoVO;
import com.lgu.ccss.common.model.mqtt.MqttMessageContentVO;
import com.lgu.ccss.common.model.mqtt.MqttMessageVO;
import com.lgu.ccss.common.tlo.TloData;
import com.lgu.ccss.common.tlo.TloUtil;
import com.lgu.ccss.common.util.CCSSUtil;
import com.lgu.ccss.common.util.ResultCodeUtil;
import com.lgu.ccss.common.validation.CheckResultData;
import com.lgu.ccss.common.validation.ValidationChecker;
import com.lgu.ccss.mgr.clova.model.login.LoginResultData;
import com.lgu.common.clova.auth.ClovaAuthAgent;
import com.lgu.common.clova.auth.ClovaAuthConst;
import com.lgu.common.clova.auth.model.ClovaAuthResponseBodyAccessTokenJSON;
import com.lgu.common.model.ResultCode;


@Service("clovaLoginService")
public class ClovaLoginServiceImpl implements ClovaLoginService{

	private static final Logger logger = LoggerFactory.getLogger(ClovaLoginServiceImpl.class);
	private static final List<String> mandatoryList = new ArrayList<String>(
			Arrays.asList(RequestJSON.PARAM_MEMB_ID,RequestJSON.PARAM_NAVER_ACCESS_TOKEN , 
					RequestJSON.PARAM_NAVER_LOGIN_ID, RequestJSON.PARAM_AUTHORIZATION_CODE));

	@Value("#{config['mqtt.infortain.login.content.title']}")
	private String infotainLoginTitle;
	
	@Value("#{config['mqtt.infortain.login.content.msg']}")
	private String infotainLoginMsg;
	
	@Autowired
	private DeviceDao deviceDao;
	
	@Autowired
	private ClovaAuthAgent clovaAuthAgent;
	
	@Autowired
	private ClovaAuthDao clovaAuthDao;
	
	@Autowired
	private MqttMessageDao mqttMessageDao;
	
	@Autowired
	private MemberDao memberDao;
	
	@Autowired
	private ModelDao modelDao;
	
	//TO-DO
	@Override
	public ResponseJSON doService(HttpHeaders headers, RequestJSON reqJson) {
		
		ResultCode resultCode=ResultCodeUtil.RC_2S000000;
		String api = reqJson.getCommon().getApiId();
		String mgrappLoginId = CCSSUtil.getMgrUserLoginId();
		String sessionId = CCSSUtil.getMgrSessionId();
		String mgrappId = CCSSUtil.getMgrappId();
		String membId = (String) reqJson.getParam().get(RequestJSON.PARAM_MEMB_ID);
		String naverAccessToken =  (String) reqJson.getParam().get(RequestJSON.PARAM_NAVER_ACCESS_TOKEN);
		String authorizationCode  =  (String) reqJson.getParam().get(RequestJSON.PARAM_AUTHORIZATION_CODE);
		String nid =  (String) reqJson.getParam().get(RequestJSON.PARAM_NAVER_LOGIN_ID);
		setTloData(membId);
		
		ResponseJSON response = null;
		LoginResultData loginResultData =null;
		
		CheckResultData result = ValidationChecker.checkValidation(headers,reqJson,CCSSConst.MANAGER_CLOVA_LOGIN,mandatoryList);
		
		if (result.isStatus() == false) {
			resultCode = ResultCodeUtil.RC_3C004000;
			response = ResultCodeUtil.createResultMsg(resultCode,api,result.getReason());
			return response;
		
		}else{
			
			try{
				//Memb-Id 로 Device 정보 획득
				ConnDeviceVO connDeviceVO = new ConnDeviceVO();
				connDeviceVO.setMembId(membId);
				connDeviceVO = deviceDao.getDeviceInfo(connDeviceVO);
				
				if (connDeviceVO == null) {
					logger.error("failed to select connDevice data. mgrappLoginId({}) sessionId({}) membId({})", mgrappLoginId,sessionId,membId);
					return ResultCodeUtil.createResultMsg(ResultCodeUtil.RC_6C000003, api);
				}
				
				String deviceModel = connDeviceVO.getDeviceModelId();
				String deviceSerial = connDeviceVO.getUiccId();

				/*// 1.Clova Auth Code 요청
				ClovaAuthResponseBodyAuthCodeJSON authCodeResp = clovaAuthAgent.createAuthorizationCode(deviceModel, sessionId, mgrappLoginId,deviceSerial, ClovaAuthConst.DEF_N, naverAccessToken);

				if (authCodeResp == null) {
					logger.error("failed to get clova authorization Code data.  membId({})  mgrappLoginId({}) deviceSerial({}) naverAccessToken({}) naverId({})",membId, mgrappLoginId , deviceSerial,naverAccessToken,nid);
					return ResultCodeUtil.createResultMsg(ResultCodeUtil.RC_5C100000, api);
					String authorizationCode = authCodeResp.getCode();
				}*/
				DeviceModelVO oemVO = modelDao.selectClovaClientInfoByModelId(connDeviceVO.getDeviceModelId()); 
				
				if(oemVO == null){
					logger.error("Unsupported device information. mgrappLoginId({}) sessionId({}) membId({})", mgrappLoginId,sessionId,membId);
					return ResultCodeUtil.createResultMsg(ResultCodeUtil.RC_3C004002, api);
				}
				
				ClovaAuthResponseBodyAccessTokenJSON accessTokenResp = clovaAuthAgent.createClovaAccessToken(oemVO.getClovaClientId(),oemVO.getClovaClientSecret(),deviceModel, sessionId, mgrappLoginId,deviceSerial, authorizationCode);

				if (accessTokenResp == null || accessTokenResp.getAccess_token() == null ) {
					logger.error("failed to get get Clova Access Token data. membId({})  mgrappLoginId({}) deviceSerial({}) authorizationCode({}) naverAccessToken({}) naverId({})",membId, mgrappLoginId , deviceSerial,authorizationCode,naverAccessToken,nid);
					return ResultCodeUtil.createResultMsg(ResultCodeUtil.RC_5C100001, api);
				}
				
				//TB_CLOVA_INFO Insert
				ClovaAuthInfoVO insertClovaAuthInfoVO = new ClovaAuthInfoVO();
				insertClovaAuthInfoVO.setMembId(membId);
				insertClovaAuthInfoVO.setClovaToken(accessTokenResp.getAccess_token());
				insertClovaAuthInfoVO.setRefreshToken(accessTokenResp.getRefresh_token());
				insertClovaAuthInfoVO.setExpiredTime(accessTokenResp.getExpires_in());
				insertClovaAuthInfoVO.setLoginStatus(ClovaAuthConst.DEF_Y);
				insertClovaAuthInfoVO.setLoginType(ClovaAuthConst.DEF_NAVER);
				insertClovaAuthInfoVO.setTokenType(accessTokenResp.getToken_type());
				insertClovaAuthInfoVO.setMgrappId(mgrappId);
				insertClovaAuthInfoVO.setNid(nid);
				insertClovaAuthInfoVO.setnAccessToken(naverAccessToken);
				insertClovaAuthInfoVO.setClovaUpdate(ClovaAuthConst.DEF_Y);

				if (clovaAuthDao.insertClovaAuthInfo(insertClovaAuthInfoVO) == 0) {
					logger.error("failed to insert Clova Auth Info data. membId({})  mgrappLoginId({}) deviceSerial({}) authorizationCode({}) naverAccessToken({}) naverId({})",membId, mgrappLoginId , deviceSerial,authorizationCode,naverAccessToken,nid);
					return ResultCodeUtil.createResultMsg(ResultCodeUtil.RC_4C005001, api);
				}
				
				String guestmodeAgrYn = "Y";
				
				// update member info
				if (memberDao.updateMemberGuestmodeAgrYn(membId, guestmodeAgrYn) == 0) {
					logger.error("failed to update Member data. deviceCtn({})", membId);
					return ResultCodeUtil.createResultMsg(ResultCodeUtil.RC_3C002004, api);
				}
				
				//Result Data Set
				loginResultData = new LoginResultData();
				loginResultData.setClovaAccessToken(accessTokenResp.getAccess_token());
				loginResultData.setExpiresIn(Integer.parseInt(accessTokenResp.getExpires_in()));
				loginResultData.setRefreshToken(accessTokenResp.getRefresh_token());
				loginResultData.setNid(nid);
				
				
				//Send Car Push
				String msgTitle=ClovaAuthConst.SERVICE_CODE_CLOVA + infotainLoginTitle;
				String contentMsg=ClovaAuthConst.SERVICE_CODE_CLOVA + infotainLoginMsg;

				MembVO membVO  = memberDao.selectMemberByID(membId);
				
				MqttMessageContentVO mqttMessageContentVO = new MqttMessageContentVO();
				mqttMessageContentVO.getContent().put("code", MqttMessageVO.CLOVA_LOGIN_LOGOUT_PUSH_CODE);
				
				HashMap<String,Object> clova = new HashMap<String,Object>();
				clova.put("id", ClovaAuthConst.SERVICE_CODE_CLOVA);
				mqttMessageContentVO.getContent().put("clova", clova);
				
				MqttMessageVO mqttMessageVO = MqttMessageVO.makeMqttMessage(membVO.getMarketType(),connDeviceVO.getDeviceCtn(),MqttMessageVO.SEND_TYPE_EMERGENCY,MqttMessageVO.MASSAGE_TYPE_SINGLE,
						MqttMessageVO.CLOVA_LOGIN_LOGOUT_PUSH_CODE,msgTitle,contentMsg,null,null ,mqttMessageContentVO);
				
				boolean sendMqttResulFlag = mqttMessageDao.insertTbCarPushQueue(mqttMessageVO);
				
				if(sendMqttResulFlag){
					
					logger.info("Send Car Push Message Insert Success. sessionId({}) membId({}) mgrappId({})  mgrappLoginId({})",sessionId,membId,mgrappId,mgrappLoginId);
			
				}else{
					resultCode=ResultCodeUtil.RC_4C005001;
					logger.error("Send Car Push Message Insert. Fail. sessionId({}) membId({}) mgrappId({})  mgrappLoginId({}) mqttMessageVO({})",sessionId,membId,mgrappId,mgrappLoginId,mqttMessageVO.toString());
				}
				
				
			}catch(Exception e){
				resultCode = ResultCodeUtil.RC_4C005000;
				logger.error("Clova Login Fail. mgrappLoginId({}) ccssToken({}) membId({}) Exception({})", mgrappLoginId,mgrappLoginId,sessionId,membId,e);
			}

		}
		
		response = ResultCodeUtil.createResultMsg(resultCode,loginResultData,api);
		return response;
	}
	

	private void setTloData(String memId ) {
		if (memId == null) {
			return;
		}
		Map<String, String> tlo = new HashMap<String, String>();
		tlo.put(TloData.MEMB_ID, memId);
		//tlo.put(TloData.MEMB_NO, memb.getMembNo());
		TloUtil.setTloData(tlo);
	}
}
