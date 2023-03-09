package com.lgu.ccss.mgr.user.service.delete;

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
import com.lgu.ccss.common.dao.mgr.MgrAppUserDao;
import com.lgu.ccss.common.model.RequestJSON;
import com.lgu.ccss.common.model.ResponseJSON;
import com.lgu.ccss.common.model.mgr.MgrAppUserVO;
import com.lgu.ccss.common.tlo.TloData;
import com.lgu.ccss.common.tlo.TloUtil;
import com.lgu.ccss.common.util.CCSSUtil;
import com.lgu.ccss.common.util.ResultCodeUtil;
import com.lgu.ccss.common.validation.CheckResultData;
import com.lgu.ccss.common.validation.ValidationChecker;
import com.lgu.ccss.mgr.user.model.ResultMgrUserJSON;
import com.lgu.common.model.ResultCode;
import com.lgu.common.util.ExceptionUtil;

@Service("mgrUserDeleteService")
public class MgrUserDeleteServiceImpl implements MgrUserDeleteService{

	private static final Logger logger = LoggerFactory.getLogger(MgrUserDeleteServiceImpl.class);
	private static final List<String> mandatoryList = new ArrayList<String>(
			Arrays.asList(RequestJSON.PARAM_MEMB_ID,RequestJSON.PARAM_MNGR_LOGIN_ID));

	@Autowired
	private MgrAppUserDao mgrAppUserDao;
	
	@Override
	@Transactional
	public ResponseJSON doService(HttpHeaders headers, RequestJSON reqJson) {
		ResultCode resultCode=ResultCodeUtil.RC_2S000000;
		String api = reqJson.getCommon().getApiId();
		String mgrappLoginId = CCSSUtil.getMgrUserLoginId();
		String sessionId = CCSSUtil.getMgrSessionId();
		String mgrappId = CCSSUtil.getMgrappId();
		
		ResponseJSON response = null;
		List<ResultMgrUserJSON> resultMgrUserJSONList = null;
		
		CheckResultData result = ValidationChecker.checkValidation(headers,reqJson,CCSSConst.MANAGER_USER_DEL,mandatoryList);
		
		if (result.isStatus() == false) {
			resultCode = ResultCodeUtil.RC_3C004000;
			response = ResultCodeUtil.createResultMsg(resultCode,api,result.getReason());
			return response;
		}else{
			
			String membId = (String) reqJson.getParam().get(RequestJSON.PARAM_MEMB_ID);
			String mgrAppLoginId = (String) reqJson.getParam().get(RequestJSON.PARAM_MNGR_LOGIN_ID);
			
			try {
				
				MgrAppUserVO mgrAppUserVO = new MgrAppUserVO();
				mgrAppUserVO.setMembId(membId);
				mgrAppUserVO.setMgrappLoginId(mgrAppLoginId);
				
				setTloData(mgrAppUserVO);
				
				if(mgrAppUserDao.deleteUser(mgrAppUserVO) > 0){
					logger.info("Success User Delete. sessionId({}) membId({}) mgrappLoginId({}) mgrappId({})", sessionId,membId,mgrappLoginId,mgrappId);
				}else{
					resultCode=ResultCodeUtil.RC_6C000002;
					logger.error("Not Exist Delete User Info. sessionId({}) membId({}) mgrappLoginId({}) mgrappId({})", sessionId,membId,mgrappLoginId,mgrappId);
				}
				
			} catch (Exception e) {
				resultCode = ResultCodeUtil.RC_4C005000;
				logger.error("sessionId({}) membId({}) mgrappLoginId({}) Exception({})", sessionId,membId,mgrappLoginId,ExceptionUtil.getPrintStackTrace(e));
			}
		}
		
		response = ResultCodeUtil.createResultMsg(resultCode,resultMgrUserJSONList, api);
		return response;
	}
	
	private void setTloData(MgrAppUserVO mgrAppUserVO) {
		if (mgrAppUserVO == null) {
			return;
		}
		Map<String, String> tlo = new HashMap<String, String>();
		tlo.put(TloData.MEMB_ID, mgrAppUserVO.getMembId());
		//tlo.put(TloData.MEMB_NO, mgrAppUserVO.getMembNo());
		TloUtil.setTloData(tlo);
	}
}
