package com.lgu.ccss.mgr.cekInfotainment.service.tempIdRegist;

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
import org.springframework.transaction.annotation.Transactional;

import com.lgu.ccss.common.constant.CCSSConst;
import com.lgu.ccss.common.model.RequestJSON;
import com.lgu.ccss.common.model.ResponseJSON;
import com.lgu.ccss.common.tlo.TloData;
import com.lgu.ccss.common.tlo.TloUtil;
import com.lgu.ccss.common.util.CCSSUtil;
import com.lgu.ccss.common.util.ResultCodeUtil;
import com.lgu.ccss.common.validation.CheckResultData;
import com.lgu.ccss.common.validation.ValidationChecker;
import com.lgu.ccss.mgr.cekInfotainment.model.getNidSearch.ResultNidInfoJSON;
import com.lgu.common.aisv.auth.AisvAuthAgent;
import com.lgu.common.aisv.auth.model.AisvAuthResponseJSON;
import com.lgu.common.model.ResultCode;

@Service("cekInfotainmentTempIdRegistService")
public class CekInfotainmentTempIdRegistServiceImpl implements CekInfotainmentTempIdRegistService {
	
	private static final Logger logger = LoggerFactory.getLogger(CekInfotainmentTempIdRegistService.class);
	private static final List<String> mandatoryList = new ArrayList<String>(
			Arrays.asList(RequestJSON.PARAM_NAVER_SVC_CODE,RequestJSON.PARAM_NAVER_AI_TEMP_ID_YN
					,RequestJSON.PARAM_NAVER_LOGIN_ID));
	
	@Autowired
	private AisvAuthAgent aisvAuthAgent;
	
	@Override
	@Transactional
	public ResponseJSON doService(HttpHeaders headers, RequestJSON reqJson) {
		ResultCode resultCode=ResultCodeUtil.RC_2S000000;
		String api = reqJson.getCommon().getApiId();
		String appType = reqJson.getCommon().getDevice().getAppType();
		String carOem = reqJson.getCommon().getDevice().getCarOem();
		String mgrappId = CCSSUtil.getMgrappId(); 
		
		setTloData(mgrappId);
		
		ResponseJSON response = null;
		
		ResultNidInfoJSON resultNidInfoJSON = null;
		AisvAuthResponseJSON aisvAuthResponseJSON = null;
		
		CheckResultData result = ValidationChecker.checkValidation(null,reqJson,CCSSConst.MANAGER_AISV_TEMP_ID_REGIST,mandatoryList);
		
		if (result.isStatus() == false) {
			resultCode = ResultCodeUtil.RC_3C004000;
			response = ResultCodeUtil.createResultMsg(resultCode,api,null);
			return response;
			
		}else{
			try {
				aisvAuthResponseJSON = aisvAuthAgent.tempIdRegist(reqJson,appType,carOem);	
				if(aisvAuthResponseJSON != null){
					resultNidInfoJSON = new ResultNidInfoJSON();
					resultNidInfoJSON.setOneId(aisvAuthResponseJSON.getData().getOneId());
					resultNidInfoJSON.setOneIdKey(aisvAuthResponseJSON.getData().getOneIdKey());
					resultNidInfoJSON.setAisvToken(aisvAuthResponseJSON.getData().getAisvToken());
				}else{
					//연동 실패
					resultCode = ResultCodeUtil.RC_5C200006;
				}
			} catch (Exception e) {
				resultCode = ResultCodeUtil.RC_4C005000;
				logger.error("sessionId({}) mgrappLoginId({}) Exception({})", null,null,e);
			} 
		}
		response = ResultCodeUtil.createResultMsg(resultCode, resultNidInfoJSON, api);
		return response;
	}

	private void setTloData(String mgrappId ) {
		if (mgrappId == null) {
			return;
		}
		Map<String, String> tlo = new HashMap<String, String>();
		tlo.put(TloData.NID, mgrappId);
		//tlo.put(TloData.MEMB_NO, memb.getMembNo());
		TloUtil.setTloData(tlo);
	}
}
