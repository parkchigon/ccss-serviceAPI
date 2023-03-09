package com.lgu.ccss.api.auth.service.saveGuestAgr;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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


@Service("saveGuestmodeAgrService")
public class SaveGuestmodeAgrServiceImpl implements SaveGuestmodeAgrService{
	private static final Logger logger = LoggerFactory.getLogger(SaveGuestmodeAgrServiceImpl.class);
	private static final List<String> mandatoryList = new ArrayList<String>(Arrays.asList());
	
	@Autowired
	private MemberDao memberDao;
	
	@Override
	@Transactional
	public ResponseJSON doService(HttpHeaders headers, RequestJSON reqJson) {
		String api = reqJson.getCommon().getApiId();
		String membId = CCSSUtil.getMembId();
		
		ResponseJSON response = null;

		CheckResultData result = ValidationChecker.checkValidation(null,reqJson,CCSSConst.API_AUTH_SAVE_GUESTMODE_AGR,mandatoryList);
		if (result.isStatus() == false) {
			response = ResultCodeUtil.createResultMsg(ResultCodeUtil.RC_3C004000, api, result.getReason());
			return response;
		}

		try {
			response = guestmodeAgr(reqJson);

		} catch (Exception e) {
			logger.error("membId({}) Exception({})", membId, ExceptionUtil.getPrintStackTrace(e));
			response = ResultCodeUtil.createResultMsg(ResultCodeUtil.RC_4C005000, api);
		}

		return response;
	}

	private ResponseJSON guestmodeAgr(RequestJSON reqJson) {
		String api = reqJson.getCommon().getApiId();
		String membId = CCSSUtil.getMembId();
		
		MembVO memb = memberDao.selectMemberByID(membId);
		if (memb == null) {
			logger.error("failed to select member data. membId({})", membId);
			return ResultCodeUtil.createResultMsg(ResultCodeUtil.RC_3C002004, api);
		}
		
		String guestmodeAgrYn = "Y";
		
		// update member info
		if (memberDao.updateMemberGuestmodeAgrYn(memb.getMembId(), guestmodeAgrYn) == 0) {
			logger.error("failed to update Member data. membId({})", memb.getMembId());
			return ResultCodeUtil.createResultMsg(ResultCodeUtil.RC_3C002004, api);
		}
		
		
		return ResultCodeUtil.createResultMsg(ResultCodeUtil.RC_2S000000, api);
	}

}
