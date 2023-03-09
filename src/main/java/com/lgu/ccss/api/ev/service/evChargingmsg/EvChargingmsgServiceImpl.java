package com.lgu.ccss.api.ev.service.evChargingmsg;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;

import com.lgu.ccss.common.constant.CCSSConst;
import com.lgu.ccss.common.dao.mgr.MgrAppUserDao;
import com.lgu.ccss.common.dao.pushGw.PushGwMessageDao;
import com.lgu.ccss.common.model.RequestJSON;
import com.lgu.ccss.common.model.ResponseJSON;
import com.lgu.ccss.common.model.ev.EvChargingmsgVO;
import com.lgu.ccss.common.model.mgr.MgrAppUserVO;
import com.lgu.ccss.common.model.pushgw.PushGwMessageVO;
import com.lgu.ccss.common.util.CCSSUtil;
import com.lgu.ccss.common.util.ResultCodeUtil;
import com.lgu.ccss.common.validation.CheckResultData;
import com.lgu.ccss.common.validation.ValidationChecker;
import com.lgu.common.model.ResultCode;

@Service("evChargingmsgService")
public class EvChargingmsgServiceImpl implements EvChargingmsgService {

	private static final Logger logger = LoggerFactory.getLogger(EvChargingmsgServiceImpl.class);

	@Value("#{config['ev.push.title']}")
	private String evPushTitle;

	@Value("#{config['ev.push.content1']}")
	private String evPushContent1;

	@Value("#{config['ev.push.content2']}")
	private String evPushContent2;

	@Value("#{config['ev.push.succContent']}")
	private String evPushSuccContent;

	@Autowired
	private MgrAppUserDao mgrAppUserDao;

	@Autowired
	private PushGwMessageDao pushGwMessageDao;

	@Override
	public ResponseJSON doService(HttpHeaders headers, RequestJSON reqJson) {
		String api = reqJson.getCommon().getApiId();
		String deviceCtn = CCSSUtil.getCtn();

		ResponseJSON response = null;

		CheckResultData result = ValidationChecker.checkValidation(headers, reqJson, CCSSConst.API_ID_EV_CHARGINGMSG,
				null);
		if (result.isStatus() == false) {
			response = ResultCodeUtil.createResultMsg(ResultCodeUtil.RC_3C004000, api, result.getReason());
			return response;
		}

		try {
			response = updateEvChargingmsg(reqJson);

		} catch (Exception e) {
			logger.error("deviceCtn({}) Exception({})", deviceCtn, e);
			response = ResultCodeUtil.createResultMsg(ResultCodeUtil.RC_4C005000, api);
		}

		return response;
	}

	private ResponseJSON updateEvChargingmsg(RequestJSON reqJson) {
		String api = reqJson.getCommon().getApiId();
		String deviceCtn = CCSSUtil.getCtn();
		String ccssToken = CCSSUtil.getCcssToken();
		String membId = CCSSUtil.getMembId();
		String mgrappLoginId = CCSSUtil.getMgrUserLoginId();
		String sessionId = CCSSUtil.getMgrSessionId();
		String mgrappId = CCSSUtil.getMgrappId();
		ResultCode resultCode = ResultCodeUtil.RC_2S000000;
		//RC_4C005001
		String chrgMsg = "";

		EvChargingmsgVO evChargingmsgVO = new EvChargingmsgVO();

		evChargingmsgVO.setChrg_msg((String) reqJson.getParam().get(RequestJSON.PARAM_CHRG_MSG));
		chrgMsg = evChargingmsgVO.getChrg_msg();
		
		if(!(chrgMsg.equals("60") || chrgMsg.equals("70") || chrgMsg.equals("80") || chrgMsg.equals("90") || chrgMsg.equals("100"))) {
			logger.error("EvChargingmsg chrgMsg error(chrgMsg value : 60, 70, 80, 90 ,100). deviceCtn({}) ccssToken({})  membId({}) chrgMsg({})", deviceCtn, ccssToken, membId, chrgMsg);
			return ResultCodeUtil.createResultMsg(ResultCodeUtil.RC_3C004000, api, "chrgMsg error(chrgMsg value : 60, 70, 80 ,90, 100)");
		}

		String carOem = (String) reqJson.getCommon().getDevice().getCarOem();
		String pushCarOem;
		if (carOem.equals("")) {
			pushCarOem = PushGwMessageVO.THINKWARE;
		} else if (carOem.equals("NS")) {
			pushCarOem = PushGwMessageVO.NS;
		} else {
			pushCarOem = PushGwMessageVO.SY;
		}
		// App Push 전송
		MgrAppUserVO mgrAppUserVO = new MgrAppUserVO();
		mgrAppUserVO.setMembId(membId);
		mgrAppUserVO.setMgrappLoginId(mgrappLoginId);

		List<MgrAppUserVO> mgrappIdList = mgrAppUserDao.selectMgrappDevice(membId);
		
		if (mgrappIdList.size() == 0) {
			resultCode = ResultCodeUtil.RC_4C005001;
			return ResultCodeUtil.createResultMsg(resultCode, api, "mgrappIdList is null.");
		}

		String[] arrMgrappId = new String[mgrappIdList.size()];


		for (int j = 0; j < mgrappIdList.size(); j++) {
			arrMgrappId[j] = mgrappIdList.get(j).getMgrappId();
		}
		
		mgrAppUserVO.setArrMgrappId(arrMgrappId);

		List<MgrAppUserVO> mgrappLoginIdList = mgrAppUserDao.selectMgrappUser(mgrAppUserVO);
		
		if (mgrappLoginIdList.size() == 0) {
			resultCode = ResultCodeUtil.RC_4C005001;
			return ResultCodeUtil.createResultMsg(resultCode, api, "mgrappLoginIdList is null.");
		}

		String[] arrMgrappLoginId = new String[mgrappLoginIdList.size()];

		for (int j = 0; j < mgrappLoginIdList.size(); j++) {
			arrMgrappLoginId[j] = mgrappLoginIdList.get(j).getMgrappLoginId();
		}

		mgrAppUserVO.setArrMgrappLoginId(arrMgrappLoginId);

		List<MgrAppUserVO> userList = mgrAppUserDao.selectEvMgrAppPusthTargetUserList(mgrAppUserVO);
		
		if (userList.size() == 0) {
			resultCode = ResultCodeUtil.RC_4C005001;
			return ResultCodeUtil.createResultMsg(resultCode, api, "userList is null.");
		}
		
		String title = evPushTitle;
		String content = "";
		if (chrgMsg.equals("100")) {
			content = evPushSuccContent;
		} else {
			content = evPushContent1 + chrgMsg + evPushContent2;
		}

		for (MgrAppUserVO tmpVO : userList) {
			try {

				PushGwMessageVO pushGwMessageVO = PushGwMessageVO.makePushMessage(tmpVO, null,
						PushGwMessageVO.MASSAGE_TYPE_SINGLE, PushGwMessageVO.EV_CHARGINGMSG_PUSH_CODE, title, content,
						null, null, pushCarOem);

				boolean sendMqttResulFlag = pushGwMessageDao.insertTbAppPushQueue(pushGwMessageVO);

				if (sendMqttResulFlag) {

					logger.info(
							"Send App Push Message Insert Success. sessionId({}) membId({}) mgrappId({})  mgrappLoginId({})",
							sessionId, membId, mgrappId, mgrappLoginId);

				} else {
					resultCode = ResultCodeUtil.RC_4C005001;
					logger.error(
							"Send App Push Message Insert. Fail. sessionId({}) membId({}) mgrappId({})  mgrappLoginId({}) pushGwMessageVO({})",
							sessionId, membId, mgrappId, mgrappLoginId, pushGwMessageVO.toString());
				}

			} catch (Exception e) {
				logger.error(
						"Push Message Insert fail. sessionId({}) membId({}) mgrappId({})  mgrappLoginId({}) targetCtn({}) exception({})",
						sessionId, membId, mgrappId, tmpVO.getMgrappLoginId(), e);
				continue;
			}
		}
		return ResultCodeUtil.createResultMsg(resultCode, api);
	}
}
