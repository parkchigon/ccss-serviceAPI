package com.lgu.ccss.api.service.service.login;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;

import com.lgu.ccss.api.service.model.PushTopicData;
import com.lgu.ccss.api.service.model.ResultLoginPushJson;
import com.lgu.ccss.common.constant.CCSSConst;
import com.lgu.ccss.common.dao.DeviceDao;
import com.lgu.ccss.common.dao.DevicePushSessDao;
import com.lgu.ccss.common.model.ConnDeviceVO;
import com.lgu.ccss.common.model.DevicePushSessVO;
import com.lgu.ccss.common.model.MembData;
import com.lgu.ccss.common.model.MembVO;
import com.lgu.ccss.common.model.RequestJSON;
import com.lgu.ccss.common.model.ResponseJSON;
import com.lgu.ccss.common.util.CCSSUtil;
import com.lgu.ccss.common.util.MembManager;
import com.lgu.ccss.common.util.ResultCodeUtil;
import com.lgu.ccss.common.validation.CheckResultData;
import com.lgu.ccss.common.validation.ValidationChecker;
import com.lgu.common.model.ResultCode;
import com.lgu.common.util.DateUtils;
import com.lgu.common.util.RandomKeyMakerUtil;

@Service("loginPushService")
public class LoginPushServiceImpl implements LoginPushService {
	private static final Logger logger = LoggerFactory.getLogger(LoginPushService.class);
	
	@Value("#{config['push.server']}")
	private String pushServer;
	
	@Value("#{config['push.protocol']}")
	private String pushProtocol;
	
	@Value("#{config['push.locInterval']}")
	private String pushLocInterval;
	
	@Value("#{config['push.topic.sub.message']}")
	private String pushTopicSubMessage;
	
	@Value("#{config['push.topic.sub.notice']}")
	private String pushTopicSubNotice;
	
	@Value("#{config['push.topic.pub.status']}")
	private String pushTopicPubStatus;
	
	@Value("#{config['push.topic.pub.report']}")
	private String pushTopicPubReport;
	
	@Autowired
	private MembManager membManager;
	
	@Autowired
	DeviceDao deviceDao;
	
	@Autowired
	private DevicePushSessDao devicePushSessDao;
	
	@Override
	public ResponseJSON doService(HttpHeaders headers, RequestJSON reqJson) {
		String api = reqJson.getCommon().getApiId();
		String deviceCtn = CCSSUtil.getCtn();

		ResponseJSON response = null;

		CheckResultData result = ValidationChecker.checkValidation(null, reqJson, CCSSConst.API_ID_SERVICE_LOGIN_PUSH,
				null);
		if (result.isStatus() == false) {
			response = ResultCodeUtil.createResultMsg(ResultCodeUtil.RC_3C004000, api, result.getReason());
			return response;
		}
		
		try {
			response = doLoginPush(headers, reqJson);

		} catch (Exception e) {
			logger.error("deviceCtn({}) Exception({})", deviceCtn, e);
			response = ResultCodeUtil.createResultMsg(ResultCodeUtil.RC_4C005000, api);
		}

		return response;
	}

	private ResponseJSON doLoginPush(HttpHeaders headers, RequestJSON reqJson) {

		String api = reqJson.getCommon().getApiId();
		String deviceCtn = CCSSUtil.getCtn();
		String deviceSerial = CCSSUtil.getSerial();
		String ccssToken = CCSSUtil.getCcssToken();
		String membId = CCSSUtil.getMembId();

		// get member data
		MembData membData = new MembData();
		ResultCode resultCode = membManager.getSubsInfo(deviceCtn, membData);
		if (!ResultCodeUtil.RC_2S000000.equals(resultCode)) {
			return ResultCodeUtil.createResultMsg(resultCode, api);
		}
		
		MembVO memb = membData.getMemb();
		
		ConnDeviceVO connDevice = new ConnDeviceVO();
		connDevice.setMembId(membId);
		connDevice = deviceDao.getDeviceInfo(connDevice);
		if (connDevice == null) {
			logger.error("failed to select connDevice data. deviceCtn({}) ccssToken({}) membId({})", deviceCtn, ccssToken,
					membId);
			return ResultCodeUtil.createResultMsg(ResultCodeUtil.RC_4C005001, api);
		}

		// create key
		String password = RandomKeyMakerUtil.sessionIdGen(deviceCtn, memb.getMembNo());
		
		// insert altibase devicePushSess
		devicePushSessDao.deleteDevicePushSessByCTN(deviceCtn);
		
		DevicePushSessVO devicePushSess = new DevicePushSessVO();
		devicePushSess.setDevicePushSessionId(password);
		devicePushSess.setDeviceCtn(deviceCtn);
		devicePushSess.setUsimSn(deviceSerial);
		devicePushSess.setDevicePushConnDt(DateUtils.getBasicCurrentTime());
		devicePushSess.setConnDeviceId(connDevice.getConnDeviceId());
		devicePushSess.setMembId(membId);
		
		if (devicePushSessDao.insertDevicePushSess(devicePushSess) == 0) {
			logger.error("failed to insert device push session. deviceCtn({})", deviceCtn);
			
			return ResultCodeUtil.createResultMsg(ResultCodeUtil.RC_4C005001, api);
		}
		
		String type = memb.getMarketType().toLowerCase();
		String subMessage = pushTopicSubMessage.replace("{type}", type).replace("{ctn}", deviceCtn);
		String subNotice = pushTopicSubNotice.replace("{type}", type);
		String pubStatus = pushTopicPubStatus.replace("{ctn}", deviceCtn);
		String pubReport = pushTopicPubReport;
		PushTopicData topic = new PushTopicData(subMessage, subNotice, pubStatus, pubReport);
		
		ResultLoginPushJson resultData = new ResultLoginPushJson();
		resultData.setPassword(password);
		resultData.setSecurityKey(memb.getTransToken());
		resultData.setPushServer(pushServer);
		resultData.setProtocol(pushProtocol);
		resultData.setLocInterval(pushLocInterval);
		resultData.setTopic(topic);
		
		return ResultCodeUtil.createResultMsg(ResultCodeUtil.RC_2S000000, resultData, api);
	}
}
