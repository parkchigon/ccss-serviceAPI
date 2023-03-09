package com.lgu.ccss.mgr.user.service.search;

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

@Service("mgrUserListService")
public class MgrUserListServiceImpl implements MgrUserListService{

	private static final Logger logger = LoggerFactory.getLogger(MgrUserListServiceImpl.class);
	private static final List<String> mandatoryList = new ArrayList<String>(
			Arrays.asList(RequestJSON.PARAM_MEMB_ID));

	@Autowired
	private MgrAppUserDao mgrAppUserDao;
		
	@Override
	@Transactional
	public ResponseJSON doService(HttpHeaders headers, RequestJSON reqJson) {
		ResultCode resultCode=ResultCodeUtil.RC_2S000000;
		String api = reqJson.getCommon().getApiId();
		String mgrappLoginId = CCSSUtil.getMgrUserLoginId();
		String sessionId = CCSSUtil.getMgrSessionId();
		
		ResponseJSON response = null;
		List<ResultMgrUserJSON> resultMgrUserJSONList = null;
		
		CheckResultData result = ValidationChecker.checkValidation(headers,reqJson,CCSSConst.MANAGER_USER_LIST,mandatoryList);
		
		if (result.isStatus() == false) {
			resultCode = ResultCodeUtil.RC_3C004000;
			response = ResultCodeUtil.createResultMsg(resultCode,api,result.getReason());
			return response;
		}else{
			
			String membId = (String) reqJson.getParam().get(RequestJSON.PARAM_MEMB_ID);
			
			try {
				
				MgrAppUserVO mgrAppUserVO = new MgrAppUserVO();
				//mgrAppUserVO.setMgrappCtn(mgrappLoginId);
				mgrAppUserVO.setMembId(membId);
				
				setTloData(mgrAppUserVO);
				
				List<MgrAppUserVO> userList = mgrAppUserDao.selectMgrAppUserInfoList(mgrAppUserVO);
				
				if(userList !=null && userList.size() > 0){
					resultMgrUserJSONList = new ArrayList<ResultMgrUserJSON>();
					
					int idx=1;
					for(MgrAppUserVO tempVO : userList){
						ResultMgrUserJSON resultMgrUserJSON = new ResultMgrUserJSON();
						resultMgrUserJSON.setUserIndex(idx);
						resultMgrUserJSON.setMgrappLoginId(tempVO.getMgrappLoginId());
						resultMgrUserJSON.setUserNm(tempVO.getUserNm());
						resultMgrUserJSON.setMgrConStatus(tempVO.getMgrConStatus());
						resultMgrUserJSONList.add(resultMgrUserJSON);
						
						idx++;
					}
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
