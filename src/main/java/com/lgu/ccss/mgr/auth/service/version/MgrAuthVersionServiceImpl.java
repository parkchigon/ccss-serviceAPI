package com.lgu.ccss.mgr.auth.service.version;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;

import com.lgu.ccss.common.constant.CCSSConst;
import com.lgu.ccss.common.dao.mgr.MgrAppDao;
import com.lgu.ccss.common.model.RequestCommonLogDataJSON;
import com.lgu.ccss.common.model.RequestJSON;
import com.lgu.ccss.common.model.ResponseJSON;
import com.lgu.ccss.common.model.mgr.MgrAppVO;
import com.lgu.ccss.common.util.ResultCodeUtil;
import com.lgu.ccss.common.validation.CheckResultData;
import com.lgu.ccss.common.validation.ValidationChecker;
import com.lgu.ccss.mgr.auth.model.ResultMgrAuthVersionJSON;
import com.lgu.common.util.ExceptionUtil;

@Service("mgrAuthVersionService")
public class MgrAuthVersionServiceImpl implements MgrAuthVersionService {

	private static final Logger logger = LoggerFactory.getLogger(MgrAuthVersionServiceImpl.class);
	private static final List<String> mandatoryList = new ArrayList<String>(
			Arrays.asList(RequestJSON.PARAM_APP_TYPE,RequestJSON.PARAM_VERSION,RequestJSON.PARAM_MNGR_LOGIN_ID));
	
	@Autowired
	private MgrAppDao mgrAppDao;

	@Override
	public ResponseJSON doService(HttpHeaders headers, RequestJSON reqJson) {
		String api = reqJson.getCommon().getApiId();
		//String deviceCtn = CCSSUtil.getCtn();
		
		ResponseJSON response = null;

		CheckResultData result = ValidationChecker.checkValidation(null,reqJson,CCSSConst.MANAGER_AUTH_VERSION,mandatoryList);
		
		if (result.isStatus() == false) {
			response = ResultCodeUtil.createResultMsg(ResultCodeUtil.RC_3C004000, api, result.getReason());
			return response;
		}

		try {
			response = getCurrentMagrAppVersion(reqJson);

		} catch (Exception e) {
			
			logger.error("Exception({})", ExceptionUtil.getPrintStackTrace(e));
			response = ResultCodeUtil.createResultMsg(ResultCodeUtil.RC_4C005000, api);
		}

		return response;

	}

	private ResponseJSON getCurrentMagrAppVersion(RequestJSON reqJson) throws Exception {
		ResponseJSON response = null;
		
		String api = reqJson.getCommon().getApiId();
		String appVer =  (String) reqJson.getParam().get(RequestJSON.PARAM_VERSION);
		//String appType =  (String) reqJson.getParam().get(RequestJSON.PARAM_APP_TYPE);
		String osType =  (String) reqJson.getCommon().getLogData().getOsInfo();
		String appType =  (String) reqJson.getCommon().getDevice().getAppType();
		String mgrappLoginId =  (String) reqJson.getParam().get(RequestJSON.PARAM_MNGR_LOGIN_ID);
		
		
		MgrAppVO mgrAppVO = new MgrAppVO();
		mgrAppVO.setAppVer(appVer);
		mgrAppVO.setAppType(appType);
		
		if(osType.indexOf(RequestCommonLogDataJSON.OS_INFO_ANDROID) > -1){
			mgrAppVO.setOsType(RequestCommonLogDataJSON.OS_INFO_ANDROID);
		}else if(osType.indexOf(RequestCommonLogDataJSON.OS_INFO_IOS) > -1){
			mgrAppVO.setOsType(RequestCommonLogDataJSON.OS_INFO_IOS);
		}else{
			//
		}
		//mgrAppVO.setOsType(osType);
		// Select MngrApp Data
		mgrAppVO = mgrAppDao.getCurrentMagrAppVersion(mgrAppVO);
		
		

		//setTloData(mgrAppVO);
		
		if (logger.isDebugEnabled()) {
			logger.debug("Mngr Current App Info ({}) mgrappLoginId({})", mgrAppVO,mgrappLoginId);
		}
		
		MgrAppVO mgrApp = new MgrAppVO();
		mgrApp.setAppVer(appVer);
		mgrApp.setAppType(appType);
		
		if(osType.indexOf(RequestCommonLogDataJSON.OS_INFO_ANDROID) > -1){
			mgrApp.setOsType(RequestCommonLogDataJSON.OS_INFO_ANDROID);
		}else if(osType.indexOf(RequestCommonLogDataJSON.OS_INFO_IOS) > -1){
			mgrApp.setOsType(RequestCommonLogDataJSON.OS_INFO_IOS);
		}else{
			//
		}
		mgrApp = mgrAppDao.getNewestMagrAppVersion(mgrApp);
		
		ResultMgrAuthVersionJSON result = null;
		
		if(mgrAppVO !=null){
			result = new ResultMgrAuthVersionJSON();
			result.setCurVersion(mgrAppVO.getAppVer());
			
			//if(Double.parseDouble(appVer) < Double.parseDouble(mgrAppVO.getAppVer())){
				result.setForcedUpdYn(mgrAppVO.getForcedUpdYn());
			//}
			result.setMarketUrl(mgrAppVO.getAppMarketUrl());
			result.setDescription(mgrAppVO.getAppCont());
			response = ResultCodeUtil.createResultMsg(ResultCodeUtil.RC_2S000000, result, api);
		}else{
			result = new ResultMgrAuthVersionJSON();
			result.setCurVersion(mgrApp.getAppVer());
			result.setForcedUpdYn("E");
			result.setMarketUrl("");
			result.setDescription("");
			//response = ResultCodeUtil.createResultMsg(ResultCodeUtil.RC_2A000000, result, api);
			response = ResultCodeUtil.createResultMsg(ResultCodeUtil.RC_2S000000, result, api);
		}
		
		return response;
	}
}
