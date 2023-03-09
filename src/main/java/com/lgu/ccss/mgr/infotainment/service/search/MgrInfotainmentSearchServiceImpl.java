package com.lgu.ccss.mgr.infotainment.service.search;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.lgu.ccss.common.constant.CCSSConst;
import com.lgu.ccss.common.dao.InfotainAuthInfoDao;
import com.lgu.ccss.common.model.InfotainAuthInfoVO;
import com.lgu.ccss.common.model.RequestJSON;
import com.lgu.ccss.common.model.ResponseJSON;
import com.lgu.ccss.common.tlo.TloData;
import com.lgu.ccss.common.tlo.TloUtil;
import com.lgu.ccss.common.util.CCSSUtil;
import com.lgu.ccss.common.util.ResultCodeUtil;
import com.lgu.ccss.common.validation.CheckResultData;
import com.lgu.ccss.common.validation.ValidationChecker;
import com.lgu.common.model.ResultCode;
import com.lgu.common.util.ExceptionUtil;

@Service("mgrInfotainmentSearchService")
public class MgrInfotainmentSearchServiceImpl implements MgrInfotainmentSearchService{

	private static final Logger logger = LoggerFactory.getLogger(MgrInfotainmentSearchServiceImpl.class);
	private static final List<String> mandatoryList = new ArrayList<String>(
			Arrays.asList(RequestJSON.PARAM_MEMB_ID,RequestJSON.PARAM_SERVICE_CODE));

	@Autowired
	private InfotainAuthInfoDao infotainAuthInfoDao;
	
	@Override
	@Transactional
	public ResponseJSON doService(HttpHeaders headers, RequestJSON reqJson) {
		ResultCode resultCode=ResultCodeUtil.RC_2S000000;
		String api = reqJson.getCommon().getApiId();
		String mgrappLoginId = CCSSUtil.getMgrUserLoginId();
		String sessionId = CCSSUtil.getMgrSessionId();
		String mgrappId = CCSSUtil.getMgrappId();
		
		ResponseJSON response = null;
		
		CheckResultData result = ValidationChecker.checkValidation(headers,reqJson,CCSSConst.MANAGER_INFOTAINMENT_SEARCH,mandatoryList);
		
		if (result.isStatus() == false) {
			resultCode = ResultCodeUtil.RC_3C004000;
			response = ResultCodeUtil.createResultMsg(resultCode,api,result.getReason());
			return response;
		}else{
			
			String membId = (String) reqJson.getParam().get(RequestJSON.PARAM_MEMB_ID);
			setTloData(membId);
			
			try {
				response = searchInfotainment(headers, reqJson);
				
			} catch (Exception e) {
				resultCode = ResultCodeUtil.RC_4C005000;
				logger.error("mgrappId({}) sessionId({}) membId({}) mgrappLoginId({}) Exception({})", mgrappId,sessionId,membId,mgrappLoginId,ExceptionUtil.getPrintStackTrace(e));
				response = ResultCodeUtil.createResultMsg(resultCode, api);
			}
			
		}
		return response;
	}
	
	private ResponseJSON searchInfotainment(HttpHeaders headers, RequestJSON reqJson) throws JsonParseException, JsonMappingException, IOException {

		String api = reqJson.getCommon().getApiId();
		String mgrappLoginId = CCSSUtil.getMgrUserLoginId();
		String sessionId = CCSSUtil.getMgrSessionId();
		String mgrappId = CCSSUtil.getMgrappId();

		
		String membId = (String) reqJson.getParam().get(RequestJSON.PARAM_MEMB_ID);
		String serviceCode = (String) reqJson.getParam().get(RequestJSON.PARAM_SERVICE_CODE);
		
		List<InfotainAuthInfoVO> infotainAuthInfoList = infotainAuthInfoDao.selectInfotainAuthInfo(membId,serviceCode);
		
		Map<String, Object> authParameter = new LinkedHashMap<String, Object>();
		
		
		if (infotainAuthInfoList.isEmpty()) {
			logger.error("failed to select InfotainAuthInfo data. mgrappId({}) sessionId({}) mgrappLoginId({}) membId({}) serviceCode({})"
					,mgrappId,sessionId,mgrappLoginId, membId, serviceCode);
			return ResultCodeUtil.createResultMsg(ResultCodeUtil.RC_2I000000, api);
		
		}else{

			if(infotainAuthInfoList.get(0).getMgrappId().equals(mgrappId)){
				
				for (InfotainAuthInfoVO vo : infotainAuthInfoList) {
					if( vo.getTokenNm().indexOf(CCSSConst.INFOTAINMENT_PARAM_FIELD_PREFIX) > -1 ){
						authParameter.put(vo.getTokenNm(), vo.getTokenValue());
					}/*else if(serviceCode.equals(AuthConst.SERVICE_CODE_IOT) &&  vo.getTokenNm().equals(RequestJSON.PARAM_ONE_ID_KYE) ){
						authParameter.put(RequestJSON.PARAM_ONE_ID_KYE, vo.getTokenValue());
					}*/else{
						//Notting
					}
				}
				authParameter.put(InfotainAuthInfoVO.LOGIN_STATUS, InfotainAuthInfoVO.SELF_LOGIN_STATUS);
			}else{
				authParameter.put(InfotainAuthInfoVO.LOGIN_STATUS, InfotainAuthInfoVO.OTHERS_LOGIN_STATUS);
			}
			return ResultCodeUtil.createResultMsg(ResultCodeUtil.RC_2S000000, authParameter, api);
		}
	}
	
	private void setTloData(String membId) {
		if (membId == null || membId.length() <=0) {
			return;
		}
		Map<String, String> tlo = new HashMap<String, String>();
		tlo.put(TloData.MEMB_ID, membId);
		TloUtil.setTloData(tlo);
	}
}
