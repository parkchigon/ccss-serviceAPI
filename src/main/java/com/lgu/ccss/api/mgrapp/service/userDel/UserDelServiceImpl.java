package com.lgu.ccss.api.mgrapp.service.userDel;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;

import com.lgu.ccss.common.constant.CCSSConst;
import com.lgu.ccss.common.dao.mgr.MgrAppUserDao;
import com.lgu.ccss.common.model.RequestJSON;
import com.lgu.ccss.common.model.ResponseJSON;
import com.lgu.ccss.common.model.mgr.MgrAppUserVO;
import com.lgu.ccss.common.tlo.TloData;
import com.lgu.ccss.common.tlo.TloUtil;
import com.lgu.ccss.common.util.CCSSUtil;
import com.lgu.ccss.common.util.ResultCodeUtil;
import com.lgu.ccss.common.validation.CheckResultData;
import com.lgu.ccss.common.validation.ValidationChecker;

@Service("userDelService")
public class UserDelServiceImpl implements UserDelService {

	private static final Logger logger = LoggerFactory.getLogger(UserDelServiceImpl.class);

	@Autowired
	private MgrAppUserDao mgrAppUserDao;

	@Override
	public ResponseJSON doService(HttpHeaders headers, RequestJSON reqJson) {
		String api = reqJson.getCommon().getApiId();
		String deviceCtn = CCSSUtil.getCtn();

		ResponseJSON response = null;

		CheckResultData result = ValidationChecker.checkValidation(null, reqJson, CCSSConst.API_ID_MGRAPP_USER_DEL,
				null);
		if (result.isStatus() == false) {
			response = ResultCodeUtil.createResultMsg(ResultCodeUtil.RC_3C004000, api, result.getReason());
			return response;
		}

		try {
			response = delUser(reqJson);

		} catch (Exception e) {
			logger.error("deviceCtn({}) Exception({})", deviceCtn, e);
			response = ResultCodeUtil.createResultMsg(ResultCodeUtil.RC_4C005000, api);
		}

		return response;
	}

	private ResponseJSON delUser(RequestJSON reqJson) {
		String api = reqJson.getCommon().getApiId();
		String deviceCtn = CCSSUtil.getCtn();
		String ccssToken = CCSSUtil.getCcssToken();
		String membId = CCSSUtil.getMembId();

		MgrAppUserVO mgrAppUserVO = new MgrAppUserVO();
		mgrAppUserVO.setMgrappId((String) reqJson.getParam().get(RequestJSON.PARAM_MNGR_APP_ID));
		mgrAppUserVO.setMembId(membId);

		setTloData(mgrAppUserVO);

		if (mgrAppUserDao.deleteUserByMgrAppId(mgrAppUserVO) > 0) {
			logger.debug("Success User Delete. deviceCtn({}) ccssToken({})  membId({})", deviceCtn,
					ccssToken, membId);

		} else {

			logger.error("Not Exist Delete User Info. deviceCtn({}) ccssToken({}) membId({})", deviceCtn,
					ccssToken, membId);
			return ResultCodeUtil.createResultMsg(ResultCodeUtil.RC_6C000002, api);
		}

		return ResultCodeUtil.createResultMsg(ResultCodeUtil.RC_2S000000, api);
	}

	private void setTloData(MgrAppUserVO mgrAppUserVO) {
		if (mgrAppUserVO == null) {
			return;
		}
		Map<String, String> tlo = new HashMap<String, String>();
		tlo.put(TloData.MEMB_ID, mgrAppUserVO.getMembId());
		// tlo.put(TloData.MEMB_NO, mgrAppUserVO.getMembNo());
		TloUtil.setTloData(tlo);
	}
}
