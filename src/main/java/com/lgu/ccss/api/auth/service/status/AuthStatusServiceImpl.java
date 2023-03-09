package com.lgu.ccss.api.auth.service.status;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;

import com.lgu.ccss.api.auth.model.AuthStatusResultData;
import com.lgu.ccss.api.auth.model.ResultAuthStatusJSON;
import com.lgu.ccss.api.auth.service.AuthStatusChecker;
import com.lgu.ccss.common.constant.CCSSConst;
import com.lgu.ccss.common.dao.MemberDao;
import com.lgu.ccss.common.model.MembVO;
import com.lgu.ccss.common.model.RequestJSON;
import com.lgu.ccss.common.model.ResponseJSON;
import com.lgu.ccss.common.tlo.TloData;
import com.lgu.ccss.common.tlo.TloUtil;
import com.lgu.ccss.common.util.CCSSUtil;
import com.lgu.ccss.common.util.ResultCodeUtil;
import com.lgu.ccss.common.validation.CheckResultData;
import com.lgu.ccss.common.validation.ValidationChecker;
import com.lgu.common.ncas.NCASQueryManager;
import com.lgu.common.ncas.NCASResultData;
import com.lgu.common.ncas.SubsInfo;
import com.lgu.common.util.ExceptionUtil;

@Service("authStatusService")
public class AuthStatusServiceImpl implements AuthStatusService {

	private static final Logger logger = LoggerFactory.getLogger(AuthStatusServiceImpl.class);
	private static final List<String> mandatoryList = new ArrayList<String>(
			Arrays.asList(RequestJSON.PARAM_CTN, RequestJSON.PARAM_SERIAL));
	
	@Autowired
	private MemberDao memberDao;

	@Autowired
	private NCASQueryManager nCASQueryManager;
	
	@Autowired
	private AuthStatusChecker authStatusChecker;
	
	@Override
	public ResponseJSON doService(HttpHeaders headers, RequestJSON reqJson) {
		String api = reqJson.getCommon().getApiId();
		String deviceCtn = CCSSUtil.getCtn();
		
		ResponseJSON response = null;

		CheckResultData result = ValidationChecker.checkValidation(null,reqJson,CCSSConst.API_ID_AUTH_STATUS,mandatoryList);
		if (result.isStatus() == false) {
			response = ResultCodeUtil.createResultMsg(ResultCodeUtil.RC_3C004000, api, result.getReason());
			return response;
		}

		try {
			response = getStatus(reqJson);

		} catch (Exception e) {
			logger.error("deviceCtn({}) Exception({})", deviceCtn, ExceptionUtil.getPrintStackTrace(e));
			response = ResultCodeUtil.createResultMsg(ResultCodeUtil.RC_4C005000, api);
		}

		return response;

	}

	/*private CheckResultData checkValidation(RequestJSON reqJson) {
		CheckResultData result = ValidationChecker.checkCommon(reqJson.getCommon());
		if (result.isStatus() == false) {
			return result;
		}

		result = ValidationChecker.checkValue(reqJson.getCommon().getApiId(), "common.apiId");
		if (result.isStatus() == false) {
			return result;
		}

		if (!CCSSConst.API_ID_AUTH_STATUS.equals(reqJson.getCommon().getApiId())) {
			result = new CheckResultData();
			result.setStatus(false);
			result.setReason("API_ID unmatched");
			return result;
		}

		for (String key : mandatoryList) {
			result = ValidationChecker.checkValue((String) reqJson.getParam().get(key), "param." + key);
			if (result.isStatus() == false) {
				return result;
			}
		}

		return result;
	}*/

	private ResponseJSON getStatus(RequestJSON reqJson) throws Exception {
		String api = reqJson.getCommon().getApiId();
		String deviceCtn = CCSSUtil.getCtn();
		String deviceSerial = CCSSUtil.getSerial();

		// query NCAS
		NCASResultData ncasData = nCASQueryManager.query(deviceCtn);
		if (ncasData == null) {
			logger.error("failed to query NCAS data. deviceCtn({})", deviceCtn);
			return ResultCodeUtil.createResultMsg(ResultCodeUtil.RC_5N000001, api);
		}

		SubsInfo subsInfo = ncasData.getSubsInfo();
		if (subsInfo == null) {
			logger.error("failed to get SubsInfo data. deviceCtn({})", deviceCtn);
		}
		
		if (logger.isDebugEnabled()) {
			logger.debug("SUBS_INFO({})", subsInfo);
		}

		// get Member data
		MembVO memb = memberDao.selectMemberForActiveStatus(deviceCtn);
		
		if (logger.isDebugEnabled()) {
			logger.debug("MEMBER({})", memb);
		}
		
		AuthStatusResultData status = authStatusChecker.getStatus(subsInfo, memb, deviceCtn, deviceSerial);
		if (status.getJoinStatus() == null) {
			return ResultCodeUtil.createResultMsg(ResultCodeUtil.RC_3C002004, api);
		}
		
		setTloData(memb, status.getJoinStatus());
		
		ResultAuthStatusJSON result = new ResultAuthStatusJSON();
		result.setJoinStatus(status.getJoinStatus());
		result.setFirstJoinStatus(status.getFirstJoinStatus());
		result.setJoinDate(status.getJoinDate());
		result.setRatePayment(status.getRatePayment());
		//if (deviceCtn.equals("01222990424") || deviceCtn.equals("01222992224")) {
			//result.setRatePaymentStatus("01");
		//} else {
			result.setRatePaymentStatus(status.getRatePaymentStatus());
		//}
		result.setRatePaymentChangeStatus(status.getRatePaymentChangeStatus());
		result.setRatePaymentResvStatus(status.getRatePaymentResvStatus());
		result.setDataStatus(status.getDataStatus());
		
		return ResultCodeUtil.createResultMsg(ResultCodeUtil.RC_2S000000, result, api);
	}
	
	private void setTloData(MembVO memb, String joinStatus) {
		Map<String, String> tlo = new HashMap<String, String>();
		
		if (memb != null) {
			tlo.put(TloData.MEMB_ID, memb.getMembId());
			tlo.put(TloData.MEMB_NO, memb.getMembNo());
		}
		
		tlo.put(TloData.JOIN_STATUS, joinStatus);
		
		TloUtil.setTloData(tlo);
	}
}
