package com.lgu.ccss.mgr.device.service.nickname;



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
import com.lgu.ccss.common.dao.mgr.MgrAppDeviceDao;
import com.lgu.ccss.common.model.RequestJSON;
import com.lgu.ccss.common.model.ResponseJSON;
import com.lgu.ccss.common.model.mgr.MgrAppDeviceVO;
import com.lgu.ccss.common.tlo.TloData;
import com.lgu.ccss.common.tlo.TloUtil;
import com.lgu.ccss.common.util.CCSSUtil;
import com.lgu.ccss.common.util.ResultCodeUtil;
import com.lgu.ccss.common.validation.CheckResultData;
import com.lgu.ccss.common.validation.ValidationChecker;
import com.lgu.common.model.ResultCode;
import com.lgu.common.util.ExceptionUtil;

@Service("mgrDeviceNickNameService")
public class MgrDeviceNickNameServiceImpl implements MgrDeviceNickNameService{

	private static final Logger logger = LoggerFactory.getLogger(MgrDeviceNickNameServiceImpl.class);
	private static final List<String> mandatoryList = new ArrayList<String>(
			Arrays.asList(RequestJSON.PARAM_MEMB_ID,RequestJSON.PARAM_DEVICE_NM));

	@Autowired
	private MgrAppDeviceDao mgrAppDeviceDao;
	
		
	@Override
	@Transactional
	public ResponseJSON doService(HttpHeaders headers, RequestJSON reqJson) {
		ResultCode resultCode=ResultCodeUtil.RC_2S000000;
		String api = reqJson.getCommon().getApiId();
		String mgrappLoginId = CCSSUtil.getMgrUserLoginId();
		String sessionId = CCSSUtil.getMgrSessionId();
		String mgrappId = CCSSUtil.getMgrappId();
		
		ResponseJSON response = null;
		
		CheckResultData result = ValidationChecker.checkValidation(headers,reqJson,CCSSConst.MANAGER_DEVICE_NICKNAME_MODI,mandatoryList);
		
		if (result.isStatus() == false) {
			resultCode = ResultCodeUtil.RC_3C004000;
			response = ResultCodeUtil.createResultMsg(resultCode,api,result.getReason());
			return response;
		}else{
			
			String membId = (String) reqJson.getParam().get(RequestJSON.PARAM_MEMB_ID);
			String deviceNm = (String) reqJson.getParam().get(RequestJSON.PARAM_DEVICE_NM);
			try {
				
				//1
				MgrAppDeviceVO mgrAppDeviceVO = new MgrAppDeviceVO();
				mgrAppDeviceVO.setMgrappId(mgrappId);
				mgrAppDeviceVO.setMembId(membId);
				mgrAppDeviceVO.setDeviceNm(deviceNm);
				
				setTloData(mgrAppDeviceVO);
				
				if(mgrAppDeviceDao.updateDeviceNm(mgrAppDeviceVO) <= 0){
					resultCode = ResultCodeUtil.RC_4C005001;
					logger.error("Update Device Name Fail.  sessionId({}) membId({}) mgrappLoginId({}) ", sessionId,membId,mgrappLoginId);
				}else{
					logger.debug("Update Device Name Success. sessionId({}) membId({}) mgrappLoginId({}) ", sessionId,membId,mgrappLoginId);					
				}
			} catch (Exception e) {
				resultCode = ResultCodeUtil.RC_4C005000;
				logger.error("sessionId({}) membId({}) mgrappLoginId({}) Exception({})", sessionId,membId,mgrappLoginId,ExceptionUtil.getPrintStackTrace(e));
			}
		}
		
		response = ResultCodeUtil.createResultMsg(resultCode, api);
		return response;
	}
	
	private void setTloData(MgrAppDeviceVO mgrAppDeviceVO) {
		if (mgrAppDeviceVO == null) {
			return;
		}
		Map<String, String> tlo = new HashMap<String, String>();
		tlo.put(TloData.MEMB_ID, mgrAppDeviceVO.getMembId());
		//tlo.put(TloData.MEMB_NO, mgrAppUserVO.getMembNo());
		TloUtil.setTloData(tlo);
	}
}
