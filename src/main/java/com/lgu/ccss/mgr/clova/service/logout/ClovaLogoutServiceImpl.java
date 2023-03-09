package com.lgu.ccss.mgr.clova.service.logout;

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
import com.lgu.ccss.mgr.clova.service.login.ClovaLoginServiceImpl;
import com.lgu.common.clova.auth.ClovaAuthConst;
import com.lgu.common.model.ResultCode;

@Service("clovaLogoutService")
public class ClovaLogoutServiceImpl implements ClovaLogoutService{
	private static final Logger logger = LoggerFactory.getLogger(ClovaLoginServiceImpl.class);
	private static final List<String> mandatoryList = new ArrayList<String>(
			Arrays.asList(RequestJSON.PARAM_MEMB_ID));
	
	@Value("#{config['mqtt.infortain.logout.content.title']}")
	private String infotainLogoutTitle;
	
	@Value("#{config['mqtt.infortain.logout.content.msg']}")
	private String infotainLogoutMsg;
	
	@Autowired
	private DeviceDao deviceDao;
	
	@Autowired
	private MqttMessageDao mqttMessageDao;
	
	@Autowired
	private MemberDao memberDao;
	
	@Autowired
	private ClovaAuthDao clovaAuthDao;
	
	@Override
	public ResponseJSON doService(HttpHeaders headers, RequestJSON reqJson) {
		ResultCode resultCode=ResultCodeUtil.RC_2S000000;
		String api = reqJson.getCommon().getApiId();
		String mgrappLoginId = CCSSUtil.getMgrUserLoginId();
		String sessionId = CCSSUtil.getMgrSessionId();
		String mgrappId = CCSSUtil.getMgrappId();
		String membId = (String) reqJson.getParam().get(RequestJSON.PARAM_MEMB_ID);
		
		ResponseJSON response = null;
		
		CheckResultData result = ValidationChecker.checkValidation(headers,reqJson,CCSSConst.MANAGER_CLOVA_LOGOUT,mandatoryList);
		
		if (result.isStatus() == false) {
			resultCode = ResultCodeUtil.RC_3C004000;
			response = ResultCodeUtil.createResultMsg(resultCode,api,result.getReason());
			return response;
		
		}else{
			try {
				//Memb-Id 로 Device 정보 획득
				ConnDeviceVO connDeviceVO = new ConnDeviceVO();
				connDeviceVO.setMembId(membId);
				connDeviceVO = deviceDao.getDeviceInfo(connDeviceVO);
				
				if (connDeviceVO == null) {
					logger.error("failed to select connDevice data. mgrappLoginId({}) sessionId({}) membId({})", mgrappLoginId,sessionId,membId);
					return ResultCodeUtil.createResultMsg(ResultCodeUtil.RC_6C000003, api);
				}
				
				ClovaAuthInfoVO clovaAuthInfo = new ClovaAuthInfoVO();
				clovaAuthInfo.setMembId(membId);
				
				if (clovaAuthDao.deleteClovaAuthInfo(clovaAuthInfo) == 0) {
					logger.error("failed to delete Clova Auth Info data. membId({}))",membId);
					return ResultCodeUtil.createResultMsg(ResultCodeUtil.RC_4C005001, api);
				}
				
				//Send Car Push
				String msgTitle=ClovaAuthConst.SERVICE_CODE_CLOVA + infotainLogoutTitle;
				String contentMsg=ClovaAuthConst.SERVICE_CODE_CLOVA + infotainLogoutMsg;

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
			} catch (Exception e) {
				resultCode = ResultCodeUtil.RC_4C005000;
				logger.error("Clova Login Fail. mgrappLoginId({}) ccssToken({}) membId({}) Exception({})", mgrappLoginId,mgrappLoginId,sessionId,membId,e);
			}
		}
		response = ResultCodeUtil.createResultMsg(resultCode, api);
		return response;
	}

}
