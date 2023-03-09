package com.lgu.ccss.mgr.auth.service.sms;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;

import com.lgu.ccss.common.constant.CCSSConst;
import com.lgu.ccss.common.dao.mgr.MgrAppSmsDao;
import com.lgu.ccss.common.model.RequestJSON;
import com.lgu.ccss.common.model.ResponseJSON;
import com.lgu.ccss.common.model.mgr.MgrAppSmsCertInfoVO;
import com.lgu.ccss.common.util.CCSSUtil;
import com.lgu.ccss.common.util.ResultCodeUtil;
import com.lgu.ccss.common.validation.CheckResultData;
import com.lgu.ccss.common.validation.ValidationChecker;
import com.lgu.ccss.mgr.auth.model.ResultMgrAuthSmsCertJSON;
import com.lgu.common.model.ResultCode;
import com.lgu.common.util.ExceptionUtil;

@Service("mgrAuthSmsCertService")
public class MgrAuthSmsCertServiceImpl implements MgrAuthSmsCertService {

	private static final Logger logger = LoggerFactory.getLogger(MgrAuthSmsCertServiceImpl.class);
	private static final List<String> mandatoryList = new ArrayList<String>(
			Arrays.asList(RequestJSON.PARAM_MNGR_USER_CTN,RequestJSON.PARAM_CERT_NO /*,RequestJSON.PARAM_CTN*/));
	
	@Autowired
	private MgrAppSmsDao mgrAppSmsDao;
	
	@Override
	public ResponseJSON doService(HttpHeaders headers, RequestJSON reqJson) {
		ResultCode resultCode=ResultCodeUtil.RC_2S000000;
		String api = reqJson.getCommon().getApiId();
		String userCtn = (String) reqJson.getParam().get(RequestJSON.PARAM_MNGR_USER_CTN);
		String deviceSerial = CCSSUtil.getSerial();
		
		ResponseJSON response = null;
		ResultMgrAuthSmsCertJSON resultMgrAuthSmsCertJSON = null;
		
		CheckResultData result = ValidationChecker.checkValidation(null,reqJson,CCSSConst.MANAGER_AUTH_SMS_CERT,mandatoryList);
		
		if (result.isStatus() == false) {
			resultCode = ResultCodeUtil.RC_3C004000;
			response = ResultCodeUtil.createResultMsg(resultCode,api,result.getReason());
			return response;
		}else{
			try {
				
				/*
				 * 1. 핸드폰, 인증번호 있는지 확인
				 * 1-1.인증번호 있으나 만료된 인증번호 일 경우 해당 인증번호 삭제 후 실패
				 * 2. 인증 데이터 확인 및 성공 후 휴대폰 번호 리턴
				 * */
				String certNo = (String) reqJson.getParam().get(RequestJSON.PARAM_CERT_NO);
				
				MgrAppSmsCertInfoVO certInfo = new MgrAppSmsCertInfoVO();
				
				certInfo.setMgrappCtn(userCtn);
				certInfo.setCertNo(certNo);

				certInfo = mgrAppSmsDao.getCertInfoConfirm(certInfo); //1
				if(certInfo != null){ //1-1
					
					if(certNo.equals(certInfo.getCertNo()) && userCtn.equals(certInfo.getMgrappCtn())){
						
						logger.debug("userCtn({}) certNo({})  certVO({})",userCtn,certNo,certInfo);
						if(!certInfo.getExpiredStatus().equals("S")){ //인증 번호 만료
							
							logger.error("Expired certNo. userCtn({}) certNo({}) )",userCtn,certNo);
							resultCode =ResultCodeUtil.RC_4C005003;
							
						}else{
							
							// Sms 인증 성공 후 - UUID update (TB_MGRAPP_USER)  // 일괄 업데이트
							String uuid = (String) reqJson.getParam().get(RequestJSON.PARAM_UUID);
							System.out.println("##################");
							System.out.println("uuid:" + uuid);
							System.out.println("##################");
							System.out.println("##################");
							
							if(uuid != null && uuid.length() > 0){
								
								//MgrAppUserVO mgrAppUserVO = new MgrAppUserVO();
								//mgrAppUserVO.setMgrappCtn(userCtn);
								//mgrAppUserVO.setUuid(uuid);
								
								//UUID update
								//if (mgrAppUserDao.updateMgrAppUuid(mgrAppUserVO)) {
								//	logger.debug("Update Success UUID. userCtn({})  uuid({}))", userCtn,uuid);
								//} else {
								//	logger.error("Not Exist User. userCtn({})  uuid({})", userCtn,uuid);
								//}
							}
							
							mgrAppSmsDao.setCertNoDel(certInfo); //사용된 인증번호 삭제
							// 성공 CASE
							resultMgrAuthSmsCertJSON = new ResultMgrAuthSmsCertJSON();
							resultMgrAuthSmsCertJSON.setUserCtn(userCtn);
						}
					}else{
						
						logger.error("Mismatch certNo. userCtn({}) certNo({})  )",userCtn,certNo);
						resultCode =ResultCodeUtil.RC_4C005005;
						
					}
				}else{
					logger.error("Not exist authentication request history. UICCID({} userCtn({}) certNo({}) )",deviceSerial,userCtn,certNo);
					resultCode =ResultCodeUtil.RC_4C005006;
				}
			} catch (Exception e) {
				resultCode = ResultCodeUtil.RC_4C005000;
				logger.error("userCtn({}) Exception({})", userCtn,ExceptionUtil.getPrintStackTrace(e));
			}
		}
		
		response = ResultCodeUtil.createResultMsg(resultCode,resultMgrAuthSmsCertJSON, api);
		return response;
	}
}
