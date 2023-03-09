package com.lgu.ccss.api.conf.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;

import com.lgu.ccss.api.conf.model.ResultConfEncryptJSON;
import com.lgu.ccss.common.constant.CCSSConst;
import com.lgu.ccss.common.dao.MemberDao;
import com.lgu.ccss.common.model.MembVO;
import com.lgu.ccss.common.model.RequestJSON;
import com.lgu.ccss.common.model.ResponseJSON;
import com.lgu.ccss.common.util.CCSSUtil;
import com.lgu.ccss.common.util.ResultCodeUtil;
import com.lgu.ccss.common.validation.CheckResultData;
import com.lgu.ccss.common.validation.ValidationChecker;
import com.lgu.common.util.ExceptionUtil;

@Service("confEncryptService")
public class ConfEncryptServiceImpl implements ConfEncryptService {

	private static final Logger logger = LoggerFactory.getLogger(ConfEncryptServiceImpl.class);

	@Autowired
	private MemberDao memberDao;

	@Override
	public ResponseJSON doService(HttpHeaders headers, RequestJSON reqJson) {
		String api = reqJson.getCommon().getApiId();
		
		ResponseJSON response = null;
		
		CheckResultData result = ValidationChecker.checkValidation(headers,reqJson,CCSSConst.API_ID_CONF_ENCRYPTION,null);
		if (result.isStatus() == false) {
			response = ResultCodeUtil.createResultMsg(ResultCodeUtil.RC_3C004000, api, result.getReason());
			return response;
		}

		try {
			response = getSecurityKey(headers, reqJson);
			
		} catch (Exception e) {
			logger.error("deviceCtn({}) Exception({})", CCSSUtil.getCtn(), ExceptionUtil.getPrintStackTrace(e));
			response = ResultCodeUtil.createResultMsg(ResultCodeUtil.RC_4C005000, api);
		}
		
		return response;
	}
	
	private ResponseJSON getSecurityKey(HttpHeaders headers, RequestJSON reqJson) {
		String api = reqJson.getCommon().getApiId();
		String membId = CCSSUtil.getMembId();

		MembVO resMembVo = memberDao.selectMemberByID(membId);
		if (resMembVo == null) {
			return ResultCodeUtil.createResultMsg(ResultCodeUtil.RC_3C002004, api);
		}

		ResultConfEncryptJSON resultData = new ResultConfEncryptJSON();
		resultData.setSecurityKey(resMembVo.getTransToken());

		return ResultCodeUtil.createResultMsg(ResultCodeUtil.RC_2S000000, resultData, api);
		
	}
}
