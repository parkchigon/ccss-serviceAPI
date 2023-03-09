package com.lgu.ccss.mgr.cekInfotainment.service.getNidSearch;

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
import com.lgu.ccss.common.util.ResultCodeUtil;
import com.lgu.ccss.common.validation.CheckResultData;
import com.lgu.ccss.common.validation.ValidationChecker;
import com.lgu.ccss.mgr.cekInfotainment.model.getNidSearch.CekInfoJSON;
import com.lgu.ccss.mgr.cekInfotainment.model.getNidSearch.ResultNidInfoJSON;
import com.lgu.common.aisv.auth.AisvAuthAgent;
import com.lgu.common.aisv.auth.model.AisvAuthResponseJSON;
import com.lgu.common.model.ResultCode;

@Service("cekInfotainmentGetNidSearchService")
public class CekInfotainmentGetNidSearchServiceImpl implements CekInfotainmentGetNidSearchService{

	private static final Logger logger = LoggerFactory.getLogger(CekInfotainmentGetNidSearchServiceImpl.class);
	private static final List<String> mandatoryList = new ArrayList<String>(
			Arrays.asList(RequestJSON.PARAM_NAVER_LOGIN_ID,RequestJSON.PARAM_NAVER_TYPE));
	
	
	@Autowired
	private AisvAuthAgent aisvAuthAgent;
	
	@Override
	@Transactional
	public ResponseJSON doService(HttpHeaders headers, RequestJSON reqJson) {
		ResultCode resultCode=ResultCodeUtil.RC_2S000000;
		String api = reqJson.getCommon().getApiId();
		String appType = reqJson.getCommon().getDevice().getAppType();
		String carOem = reqJson.getCommon().getDevice().getCarOem();
		String nid = (String) reqJson.getParam().get(RequestJSON.PARAM_NAVER_LOGIN_ID);
		
		setTloData(nid);
		
		ResponseJSON response = null;
		ResultNidInfoJSON resultNidInfoJSON = null;
		CekInfoJSON cekInfo =null;
		
		CheckResultData result = ValidationChecker.checkValidation(null,reqJson,CCSSConst.MANAGER_AISV_GET_NID_INFO,mandatoryList);
		
		if (result.isStatus() == false) {
			resultCode = ResultCodeUtil.RC_3C004000;
			response = ResultCodeUtil.createResultMsg(resultCode,api,null);
			return response;
			
		}else{
			AisvAuthResponseJSON aisvAuthResponseJSON = null;
			try {
				aisvAuthResponseJSON = aisvAuthAgent.getNidInfo(reqJson, appType, carOem);

				if(aisvAuthResponseJSON != null){
					resultNidInfoJSON = new ResultNidInfoJSON();				
					if (aisvAuthResponseJSON.getData() != null) {
						resultNidInfoJSON.setOneIdKey(aisvAuthResponseJSON.getData().getOneIdKey());
						resultNidInfoJSON.setOneId(aisvAuthResponseJSON.getData().getOneId());
						resultNidInfoJSON.setSsoKey(aisvAuthResponseJSON.getData().getSsoKey());
						resultNidInfoJSON.setTempIdYn(aisvAuthResponseJSON.getData().getTempIdYn());
						resultNidInfoJSON.setCtn(aisvAuthResponseJSON.getData().getCtn());
						resultNidInfoJSON.setAiTempIdYn(aisvAuthResponseJSON.getData().getAiTempIdYn());
						resultNidInfoJSON.setAisvToken(aisvAuthResponseJSON.getData().getAisvToken());
						
						cekInfo = new CekInfoJSON();
						cekInfo.setCekUserId(aisvAuthResponseJSON.getData().getCekInfo().getCekUserId());
						cekInfo.setOneIdKey(aisvAuthResponseJSON.getData().getCekInfo().getCekOneIdKey());
						cekInfo.setCekSsoKey(aisvAuthResponseJSON.getData().getCekInfo().getCekSsoKey());
						cekInfo.setCekTempIdYn(aisvAuthResponseJSON.getData().getCekInfo().getCekTempIdYn());
						cekInfo.setCekCtn(aisvAuthResponseJSON.getData().getCekInfo().getCekCtn());
						
						resultNidInfoJSON.setCekInfo(cekInfo);
					}else{
						resultNidInfoJSON = null;
						//response Data 없음
						resultCode = ResultCodeUtil.RC_5C200007;
					}
				}else{
					//연동 실패
					resultCode = ResultCodeUtil.RC_5C200006;
				}
				
			} catch (Exception e) {
				resultCode = ResultCodeUtil.RC_4C005000;
				logger.error("sessionId({}) mgrappLoginId({}) Exception({})", null,null,e);
			} 
		}
		response = ResultCodeUtil.createResultMsg(resultCode,resultNidInfoJSON, api);
		return response;
	}
	

	private void setTloData(String nid ) {
		if (nid == null) {
			return;
		}
		Map<String, String> tlo = new HashMap<String, String>();
		tlo.put(TloData.NID, nid);
		//tlo.put(TloData.MEMB_NO, memb.getMembNo());
		TloUtil.setTloData(tlo);
	}

}
