package com.lgu.ccss.mgr.cekInfotainment.service.search;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.lgu.ccss.common.constant.CCSSConst;
import com.lgu.ccss.common.dao.CekInfotainAuthInfoDao;
import com.lgu.ccss.common.model.CekInfotainAuthInfoVO;
import com.lgu.ccss.common.model.RequestJSON;
import com.lgu.ccss.common.model.ResponseJSON;
import com.lgu.ccss.common.tlo.TloData;
import com.lgu.ccss.common.tlo.TloUtil;
import com.lgu.ccss.common.util.CCSSUtil;
import com.lgu.ccss.common.util.ResultCodeUtil;
import com.lgu.ccss.common.validation.CheckResultData;
import com.lgu.ccss.common.validation.ValidationChecker;
import com.lgu.common.cekAi.auth.CekAuthAgent;
import com.lgu.common.cekAi.auth.model.CekAuthResponseJSON;
import com.lgu.common.model.ResultCode;
import com.lgu.common.util.ExceptionUtil;

@Service("cekInfotainmentSearchService")
public class CekInfotainmentSearchServiceImpl implements CekInfotainmentSearchService{

	private static final Logger logger = LoggerFactory.getLogger(CekInfotainmentSearchServiceImpl.class);
	private static final List<String> mandatoryList = new ArrayList<String>(
			Arrays.asList(RequestJSON.PARAM_MEMB_ID,RequestJSON.PARAM_SERVICE_CODE,
					RequestJSON.PARAM_NAVER_LOGIN_ID,  RequestJSON.PARAM_CUSTOM_ID));

	@Autowired
	private CekInfotainAuthInfoDao cekInfotainAuthInfoDao;
	
	@Autowired
	private CekAuthAgent cekAuthAgent;
	
	
	@Value("#{config['cek.infotainment.search.yn']}")
	private String cekInfotainmentSearchYn;
	
	@Override
	@Transactional
	public ResponseJSON doService(HttpHeaders headers, RequestJSON reqJson) {
		ResultCode resultCode=ResultCodeUtil.RC_2S000000;
		String api = reqJson.getCommon().getApiId();
		String mgrappLoginId = CCSSUtil.getMgrUserLoginId();
		String sessionId = CCSSUtil.getMgrSessionId();
		String mgrappId = CCSSUtil.getMgrappId();
		
		ResponseJSON response = null;
		
		CheckResultData result = ValidationChecker.checkValidation(headers,reqJson,CCSSConst.MANAGER_CEK_INFOTAINMENT_SEARCH,mandatoryList);
		
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
		
		List<Map<String, Object>> cekSearchResultList = new LinkedList<Map<String, Object>>();
		Map<String, Object> authParameter = new LinkedHashMap<String, Object>();
		
		if(!cekInfotainmentSearchYn.equals(CCSSConst.DEF_YES)){
			
			List<CekInfotainAuthInfoVO> infotainAuthInfoList = cekInfotainAuthInfoDao.selectInfotainAuthInfo(membId,serviceCode);
			
			if (infotainAuthInfoList.isEmpty()) {
				logger.error("failed to select Cek InfotainAuthInfo data. mgrappId({}) sessionId({}) mgrappLoginId({}) membId({}) serviceCode({})"
						,mgrappId,sessionId,mgrappLoginId, membId, serviceCode);
				return ResultCodeUtil.createResultMsg(ResultCodeUtil.RC_2I000000, api);
			
			}else{
				
				boolean logoutYnflag =true;
				
				if(infotainAuthInfoList.get(0).getMgrappId().equals(mgrappId)){
					
					for (CekInfotainAuthInfoVO vo : infotainAuthInfoList) {
						if( vo.getTokenNm().indexOf(CCSSConst.INFOTAINMENT_PARAM_FIELD_PREFIX) > -1 ){
							authParameter.put(vo.getTokenNm(), vo.getTokenValue());
							if(vo.getUseYn().equals(CekInfotainAuthInfoVO.USE_Y)){
								logoutYnflag = false;								
							}
						}else{
							//Notting
						}
					}
					authParameter.put(CekInfotainAuthInfoVO.LOGIN_STATUS, CekInfotainAuthInfoVO.SELF_LOGIN_STATUS);
				}else{
					authParameter.put(CekInfotainAuthInfoVO.LOGIN_STATUS, CekInfotainAuthInfoVO.OTHERS_LOGIN_STATUS);
				}
				
				if(logoutYnflag){
					authParameter.put(CekInfotainAuthInfoVO.LOGOUT_YN, CekInfotainAuthInfoVO.USE_N);
				}else{
					authParameter.put(CekInfotainAuthInfoVO.LOGOUT_YN, CekInfotainAuthInfoVO.USE_Y);
				}
				cekSearchResultList.add(authParameter);
				
				return ResultCodeUtil.createResultMsg(ResultCodeUtil.RC_2S000000, cekSearchResultList, api);
			}
		}else{
			
			CekAuthResponseJSON cekAuthResponseJSON = cekAuthAgent.getSvcAuthInfo(reqJson, mgrappLoginId);
			
			if(cekAuthResponseJSON !=null){
				cekSearchResultList = (List<Map<String, Object>>) cekAuthResponseJSON.getBody().getAuthInfos();
			}
			
			return ResultCodeUtil.createResultMsg(ResultCodeUtil.RC_2S000000, cekSearchResultList, api);
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
