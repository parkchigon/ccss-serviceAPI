package com.lgu.ccss.mgr.management.service.config.modify;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;

import com.lgu.ccss.common.constant.CCSSConst;
import com.lgu.ccss.common.dao.mgr.MgrAppManagementDao;
import com.lgu.ccss.common.model.RequestJSON;
import com.lgu.ccss.common.model.ResponseJSON;
import com.lgu.ccss.common.model.mgr.MgrAppUserVO;
import com.lgu.ccss.common.model.setInfo.JsonSetInfo;
import com.lgu.ccss.common.tlo.TloData;
import com.lgu.ccss.common.tlo.TloUtil;
import com.lgu.ccss.common.util.CCSSUtil;
import com.lgu.ccss.common.util.ResultCodeUtil;
import com.lgu.ccss.common.validation.CheckResultData;
import com.lgu.ccss.common.validation.ValidationChecker;
import com.lgu.ccss.mgr.management.model.encInfo.ResultSearchConfigJSON;
import com.lgu.common.model.ResultCode;
import com.lgu.common.util.JsonUtil;

@Service("modifyConfigService")
public class ModifyConfigServiceImpl implements ModifyConfigService{

	private static final Logger logger = LoggerFactory.getLogger(ModifyConfigServiceImpl.class);
	private static final List<String> mandatoryList = new ArrayList<String>();

	@Autowired
	private MgrAppManagementDao mgrAppManagementDao;
	
	@Override
	public ResponseJSON doService(HttpHeaders headers, RequestJSON reqJson) {
		ResultCode resultCode=ResultCodeUtil.RC_2S000000;
		String api = reqJson.getCommon().getApiId();
		String mgrappLoginId = CCSSUtil.getMgrUserLoginId();
		String sessionId = CCSSUtil.getMgrSessionId();
		String mgrappId = CCSSUtil.getMgrappId();
		
		String membId = (String) reqJson.getParam().get(RequestJSON.PARAM_MEMB_ID);
		
		setTloData(membId);
		
		ResponseJSON response = null;
		ResultSearchConfigJSON resultSearchConfigJSON = null;
		
		CheckResultData result = ValidationChecker.checkValidation(headers,reqJson,CCSSConst.MANAGEMENT_MODIFY_CONFIG,mandatoryList);
		
		if (result.isStatus() == false) {
			resultCode = ResultCodeUtil.RC_3C004000;
			response = ResultCodeUtil.createResultMsg(resultCode,api,result.getReason());
			return response;
			
		}else{
			
			try {
				
				
				MgrAppUserVO mgrAppUserVO  = new MgrAppUserVO();
				mgrAppUserVO.setMgrappId(mgrappId);
				
				String reqParamJsonStr = JsonUtil.marshallingJson(reqJson.getParam().get(RequestJSON.PARAM_MGR_JOIN_SET_INFO));
				JsonSetInfo jsonSetInfo = 	JsonUtil.unmarshallingJson(reqParamJsonStr, JsonSetInfo.class);
				
				mgrAppUserVO.setJsonSetInfo(JsonUtil.marshallingJson(jsonSetInfo));
				
				
				boolean updateFlag = mgrAppManagementDao.updateMgrappConfig(mgrAppUserVO);

				if(updateFlag){
					logger.info("Magrapp Config Modify Success. sessionId({}) membId({}) mgrappId({})  mgrappLoginId({})",sessionId,membId,mgrappId,mgrappLoginId);
				}else{
					resultCode=ResultCodeUtil.RC_4C005001;
					logger.error("Magrapp Config Modify Fail.   sessionId({}) membId({}) mgrappId({})  mgrappLoginId({}) mgrAppUserVO({})",sessionId,membId,mgrappId,mgrappLoginId,mgrAppUserVO.toString());
				}
				
			} catch (Exception e) {
				resultCode = ResultCodeUtil.RC_4C005000;
				logger.error("mgrappLoginId({}) sessionId({}) mgrappId({}) Exception({})", mgrappLoginId,sessionId,mgrappId,e);
			}
		}
		
		response = ResultCodeUtil.createResultMsg(resultCode,resultSearchConfigJSON, api);
		return response;
	}
	
	private void setTloData(String memId ) {
		if (memId == null) {
			return;
		}
		Map<String, String> tlo = new HashMap<String, String>();
		tlo.put(TloData.MEMB_ID, memId);
		//tlo.put(TloData.MEMB_NO, memb.getMembNo());
		TloUtil.setTloData(tlo);
	}
}
