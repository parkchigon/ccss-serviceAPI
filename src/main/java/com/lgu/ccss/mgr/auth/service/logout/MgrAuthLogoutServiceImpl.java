package com.lgu.ccss.mgr.auth.service.logout;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;

import com.lgu.ccss.common.constant.CCSSConst;
import com.lgu.ccss.common.constant.HeaderConst;
import com.lgu.ccss.common.dao.mgr.MgrAppSessionDao;
import com.lgu.ccss.common.dao.mgr.MgrAppUserDao;
import com.lgu.ccss.common.model.RequestJSON;
import com.lgu.ccss.common.model.ResponseJSON;
import com.lgu.ccss.common.model.mgr.MgrAppSessVO;
import com.lgu.ccss.common.model.mgr.MgrAppUserVO;
import com.lgu.ccss.common.util.ResultCodeUtil;
import com.lgu.ccss.common.validation.CheckResultData;
import com.lgu.ccss.common.validation.ValidationChecker;
import com.lgu.common.model.ResultCode;
import com.lgu.common.util.ExceptionUtil;
import com.lgu.common.util.StringUtils;

@Service("mgrAuthLogoutService")
public class MgrAuthLogoutServiceImpl implements MgrAuthLogoutService {

	private static final Logger logger = LoggerFactory.getLogger(MgrAuthLogoutServiceImpl.class);
	private static final List<String> mandatoryList = new ArrayList<String>();
	
	
	@Autowired
	private MgrAppUserDao mgrAppUserDao;
	@Autowired
	private MgrAppSessionDao mgrAppSessionDao;
	
	@Override
	public ResponseJSON doService(HttpHeaders headers, RequestJSON reqJson, HttpServletRequest request) {
		ResultCode resultCode=ResultCodeUtil.RC_2S000000;
		
		String api = reqJson.getCommon().getApiId();
		String sessionId = StringUtils.nvl(request.getHeader(HeaderConst.HD_NAME_MGRAPP_SESSION_ID));
		
		ResponseJSON response = null;
		
		CheckResultData result = ValidationChecker.checkValidation(headers,reqJson,CCSSConst.MANAGER_AUTH_LOGOUT,mandatoryList);
		
		if (result.isStatus() == false || (sessionId == null || sessionId.length() <=0 )) {
			resultCode = ResultCodeUtil.RC_3C004000;
			response = ResultCodeUtil.createResultMsg(resultCode,api,result.getReason());
			return response;
		}else{
			
			try {
				
				//Select session Info
				MgrAppSessVO mgrAppSessVO = mgrAppSessionDao.selectMgrSess(sessionId);
				

				if(mgrAppSessVO !=null){
					
					//delete Session Info
					String mgrappId = mgrAppSessVO.getMgrappId();					
					mgrAppSessionDao.deleteMgrSessByMgrappId(mgrappId);
					
					
					//update mgrConStatus
					MgrAppUserVO updateUserVO = new MgrAppUserVO();
					updateUserVO.setMgrappId(mgrappId);
					updateUserVO.setMgrConStatus("N");
					
					mgrAppUserDao.updateMgrConStatus(updateUserVO);
					
					logger.info("Logout Success.  sessionId({}),mgrappId({})", sessionId,mgrappId);
					
				}
			} catch (Exception e) {
				resultCode = ResultCodeUtil.RC_4C005000;
				logger.error("Lougout Fail sessionId({}) Exception({})",sessionId,ExceptionUtil.getPrintStackTrace(e));
			}
		}
		response = ResultCodeUtil.createResultMsg(resultCode, api);
		//logger.info("response({})", response);
		return response;
	}
}
