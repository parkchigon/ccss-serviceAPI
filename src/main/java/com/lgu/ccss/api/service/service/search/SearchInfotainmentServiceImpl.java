package com.lgu.ccss.api.service.service.search;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;

import com.lgu.ccss.common.constant.CCSSConst;
import com.lgu.ccss.common.dao.InfotainAuthInfoDao;
import com.lgu.ccss.common.model.InfotainAuthInfoVO;
import com.lgu.ccss.common.model.RequestJSON;
import com.lgu.ccss.common.model.ResponseJSON;
import com.lgu.ccss.common.util.CCSSUtil;
import com.lgu.ccss.common.util.ResultCodeUtil;
import com.lgu.ccss.common.validation.CheckResultData;
import com.lgu.ccss.common.validation.ValidationChecker;
import com.lgu.common.util.ExceptionUtil;

@Service("searchInfotainmentService")
public class SearchInfotainmentServiceImpl implements SearchInfotainmentService {
	private static final Logger logger = LoggerFactory.getLogger(SearchInfotainmentServiceImpl.class);
	private static final List<String> mandatoryList = new ArrayList<String>(
			Arrays.asList(RequestJSON.PARAM_SERVICE_CODE));
	
	@Autowired
	private InfotainAuthInfoDao infotainAuthInfoDao;

	@Override
	public ResponseJSON doService(HttpHeaders headers, RequestJSON reqJson) {
		String api = reqJson.getCommon().getApiId();
		String deviceCtn = CCSSUtil.getCtn();

		ResponseJSON response = null;

		CheckResultData result = ValidationChecker.checkValidation(null,reqJson,CCSSConst.API_ID_SERVICE_SEARCH_INFOTAINMENT,mandatoryList);
		if (result.isStatus() == false) {
			response = ResultCodeUtil.createResultMsg(ResultCodeUtil.RC_3C004000, api, result.getReason());
			return response;
		}

		try {
			response = searchInfotainment(headers, reqJson);

		} catch (Exception e) {
			logger.error("deviceCtn({}) Exception({})", deviceCtn, ExceptionUtil.getPrintStackTrace(e));
			response = ResultCodeUtil.createResultMsg(ResultCodeUtil.RC_4C005000, api);
		}

		return response;

	}

	private ResponseJSON searchInfotainment(HttpHeaders headers, RequestJSON reqJson) {

		String api = reqJson.getCommon().getApiId();
		String deviceCtn = CCSSUtil.getCtn();
		String membId = CCSSUtil.getMembId();
		
		String serviceCode = (String) reqJson.getParam().get(RequestJSON.PARAM_SERVICE_CODE);
		
		List<InfotainAuthInfoVO> infotainAuthInfoList = infotainAuthInfoDao.selectInfotainAuthInfo(membId,
				serviceCode);
		if (infotainAuthInfoList.isEmpty()) {
			logger.error("failed to select InfotainAuthInfo data. deviceCtn({}) membId({}) serviceCode({})",
					deviceCtn, membId, serviceCode);
			return ResultCodeUtil.createResultMsg(ResultCodeUtil.RC_2I000000, api);
		}
		
		Map<String, String> authParameter = new LinkedHashMap<String, String>();
		for (InfotainAuthInfoVO vo : infotainAuthInfoList) {
			if (vo.getTokenNm().startsWith("authParameter")) {
				authParameter.put(vo.getTokenNm(), vo.getTokenValue());
			}
		}

		return ResultCodeUtil.createResultMsg(ResultCodeUtil.RC_2S000000, authParameter, api);
	}
}
