package com.lgu.ccss.api.mgrapp.service.userList;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;

import com.lgu.ccss.api.mgrapp.model.ResultMgrUserJSON;
import com.lgu.ccss.api.mgrapp.model.ResultMgrUserListJSON;
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

@Service("userListService")
public class UserListServiceImpl implements UserListService {

	private static final Logger logger = LoggerFactory.getLogger(UserListServiceImpl.class);
	
	@Autowired
	private MgrAppUserDao mgrAppUserDao;
	
	@Override
	public ResponseJSON doService(HttpHeaders headers, RequestJSON reqJson) {
		String api = reqJson.getCommon().getApiId();
		String deviceCtn = CCSSUtil.getCtn();

		ResponseJSON response = null;

		CheckResultData result = ValidationChecker.checkValidation(null, reqJson, CCSSConst.API_ID_MGRAPP_USER_LIST,
				null);
		if (result.isStatus() == false) {
			response = ResultCodeUtil.createResultMsg(ResultCodeUtil.RC_3C004000, api, result.getReason());
			return response;
		}

		try {
			response = getUserList(reqJson);

		} catch (Exception e) {
			logger.error("deviceCtn({}) Exception({})", deviceCtn, e);
			response = ResultCodeUtil.createResultMsg(ResultCodeUtil.RC_4C005000, api);
		}

		return response;
	}
	
	ResponseJSON getUserList(RequestJSON reqJson) {
		String api = reqJson.getCommon().getApiId();
		String membId = CCSSUtil.getMembId();
		
		MgrAppUserVO mgrAppUserVO = new MgrAppUserVO();
		List<ResultMgrUserJSON> resultMgrUserJSONList = new ArrayList<ResultMgrUserJSON>();
		
		mgrAppUserVO.setMembId(membId);
		
		setTloData(mgrAppUserVO);
		
		List<MgrAppUserVO> userList = mgrAppUserDao.selectMgrAppUserInfoList(mgrAppUserVO);
		if(userList !=null && userList.size() > 0){
			int seq=1;
			for(MgrAppUserVO tempVO : userList){
				ResultMgrUserJSON resultMgrUserJSON = new ResultMgrUserJSON();
				resultMgrUserJSON.setSeq(Integer.toString(seq));
				resultMgrUserJSON.setCtn(tempVO.getMgrappLoginId());
				resultMgrUserJSON.setName(tempVO.getUserNm());
				resultMgrUserJSON.setMgrAppId(tempVO.getMgrappId());
				resultMgrUserJSON.setConStatus(tempVO.getMgrConStatus());
				resultMgrUserJSONList.add(resultMgrUserJSON);
				
				seq++;
			}
		}
		
		ResultMgrUserListJSON result = new ResultMgrUserListJSON();
		result.setUserList(resultMgrUserJSONList);
		return ResultCodeUtil.createResultMsg(ResultCodeUtil.RC_2S000000, result, api);
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
