package com.lgu.ccss.mgr.infotainment.service.callback;



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
import com.lgu.ccss.common.dao.mgr.MgrAppCallbackDao;
import com.lgu.ccss.common.model.CallBackInfoVO;
import com.lgu.ccss.common.model.RequestJSON;
import com.lgu.ccss.common.model.ResponseJSON;
import com.lgu.ccss.common.tlo.TloData;
import com.lgu.ccss.common.tlo.TloUtil;
import com.lgu.ccss.common.util.CCSSUtil;
import com.lgu.ccss.common.util.ResultCodeUtil;
import com.lgu.ccss.common.validation.CheckResultData;
import com.lgu.ccss.common.validation.ValidationChecker;
import com.lgu.ccss.mgr.infotainment.model.LoginCallBackJSON;
import com.lgu.common.model.ResultCode;
import com.lgu.common.util.JsonUtil;

@Service("mgrInfotainmentLoginCallBackService")
public class MgrInfotainmentLoginCallBackServiceImpl implements MgrInfotainmentLoginCallBackService{

	private static final Logger logger = LoggerFactory.getLogger(MgrInfotainmentLoginCallBackServiceImpl.class);
	private static final List<String> mandatoryList = new ArrayList<String>(
			Arrays.asList(RequestJSON.PARAM_MEMB_ID,RequestJSON.PARAM_SERVICE_CODE));

	@Autowired
	private MgrAppCallbackDao mgrAppCallbackDao;
	
	
	@Override
	@Transactional
	public ResponseJSON doService(HttpHeaders headers, RequestJSON reqJson) {
		ResultCode resultCode=ResultCodeUtil.RC_2S000000;
		String api = reqJson.getCommon().getApiId();
		String mgrappLoginId = CCSSUtil.getMgrUserLoginId();
		String sessionId = CCSSUtil.getMgrSessionId();
		String mgrappId = CCSSUtil.getMgrappId();
		String membId = (String) reqJson.getParam().get(RequestJSON.PARAM_MEMB_ID);
		
		setTloData(membId);
		
		ResponseJSON response = null;
		LoginCallBackJSON loginCallBackJSON = null;
		
		CheckResultData result = ValidationChecker.checkValidation(headers,reqJson,CCSSConst.MANAGER_INFOTAINMENT_LOGIN_CALL_BACK,mandatoryList);
		
		if (result.isStatus() == false) {
			
			resultCode = ResultCodeUtil.RC_3C004000;
			response = ResultCodeUtil.createResultMsg(resultCode,api,result.getReason());
			return response;
		
		}else{
			
			try {
				
				CallBackInfoVO callBackInfoVO = new CallBackInfoVO();
				callBackInfoVO.setMgrappId(mgrappId);
				
				callBackInfoVO = mgrAppCallbackDao.selectTbCallBackInfo(callBackInfoVO);
				
				if(callBackInfoVO !=null){
					
					loginCallBackJSON = new LoginCallBackJSON();
					
					Object obj = new Object();
					obj = JsonUtil.unmarshallingJson(callBackInfoVO.getReturnData(), Object.class);
					loginCallBackJSON.setCallBackData(obj);
					
				}else{
					resultCode = ResultCodeUtil.RC_5A000005;
					logger.error("Not Exist Infotain Login Callback Info. mgrappId({}) sessionId({}) mgrappLoginId({})", mgrappId,sessionId,mgrappLoginId);
				}
				
			} catch (Exception e) {
				resultCode = ResultCodeUtil.RC_4C005000;
				logger.error("mgrappId({}) sessionId({}) mgrappLoginId({}) Exception({})", mgrappId,sessionId,mgrappLoginId,e);
				
			}
		}
		response = ResultCodeUtil.createResultMsg(resultCode,loginCallBackJSON,api);
		return response;
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
