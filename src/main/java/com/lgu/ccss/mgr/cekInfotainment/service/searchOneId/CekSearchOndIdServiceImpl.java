package com.lgu.ccss.mgr.cekInfotainment.service.searchOneId;



import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;

import com.lgu.ccss.common.constant.CCSSConst;

import com.lgu.ccss.common.dao.mgr.MgrAppOneIdAuthInfo;
import com.lgu.ccss.common.model.OneIdAuthInfoVO;
import com.lgu.ccss.common.model.RequestJSON;
import com.lgu.ccss.common.model.ResponseJSON;
import com.lgu.ccss.common.tlo.TloData;
import com.lgu.ccss.common.tlo.TloUtil;
import com.lgu.ccss.common.util.CCSSUtil;
import com.lgu.ccss.common.util.ResultCodeUtil;
import com.lgu.ccss.common.validation.CheckResultData;
import com.lgu.ccss.common.validation.ValidationChecker;
import com.lgu.ccss.mgr.cekInfotainment.model.searchOneId.ResultSearchOneIdJSON;
import com.lgu.common.aisv.auth.AisvAuthConst;
import com.lgu.common.cekAi.auth.CekAuthAgent;
import com.lgu.common.cekAi.auth.CekAuthConst;
import com.lgu.common.cekAi.auth.model.CekAuthResponseJSON;
import com.lgu.common.model.ResultCode;


@Service("cekSearchOndIdService")
public class CekSearchOndIdServiceImpl implements CekSearchOndIdService{

	private static final Logger logger = LoggerFactory.getLogger(CekSearchOndIdServiceImpl.class);
	private static final List<String> mandatoryList = new ArrayList<String>(
			Arrays.asList(RequestJSON.PARAM_MEMB_ID,RequestJSON.PARAM_NAVER_LOGIN_ID));

	@Autowired
	private MgrAppOneIdAuthInfo mgrAppOneIdAuthInfo;
		
	@Autowired
	private CekAuthAgent cekAuthAgent;
	
	//TO-DO
	@Override
	public ResponseJSON doService(HttpHeaders headers, RequestJSON reqJson) {
		ResultCode resultCode=ResultCodeUtil.RC_2S000000;
		String api = reqJson.getCommon().getApiId();
		String mgrappLoginId = CCSSUtil.getMgrUserLoginId();
		String sessionId = CCSSUtil.getMgrSessionId();
		String mgrappId = CCSSUtil.getMgrappId();
		String membId = (String) reqJson.getParam().get(RequestJSON.PARAM_MEMB_ID);
		String nid = (String) reqJson.getParam().get(RequestJSON.PARAM_NAVER_LOGIN_ID);

		// BM(BM_MGR_APP)에서 AI연동을 제거하기위한 앱타입정보 (2019/11/11)
		String appType = reqJson.getCommon().getDevice().getAppType();
		
		setTloData(membId);
		
		ResponseJSON response = null;
		ResultSearchOneIdJSON resultSearchOneIdJSON =null;
		
		CheckResultData result = ValidationChecker.checkValidation(headers,reqJson,CCSSConst.MANAGER_CEK_SEARCH_ONE_ID,mandatoryList);
		
		if (result.isStatus() == false) {
			resultCode = ResultCodeUtil.RC_3C004000;
			response = ResultCodeUtil.createResultMsg(resultCode,api,result.getReason());
			return response;
			
		}else{
			
			/*
			 * Date : 2019/11/11 (멀티홈코드 개발건 연계)
			 * Author : JYJ
			 * Description :
			 * 		BM에서 AI 연동을 제거하기 위해 AM으로 기본 프로세스는 유지한채  BM 별도 분기처리 (음성처리 AI 연동제거)
			 * 		BM에서는 매니저앱에서 전달받은 CEK정보를 U+우리집AI와 연동처리를 안하고 DB에만 저장처리
			 */
			// BM(BM_MGR_APP)의 경우 (신규)
			if (appType.equals(AisvAuthConst.DEVICE_BM_APP_TYPE)) {
			
				try {
					
					/*
					 * 1. membId로 TB_ ONEID_AUTHINFO 조회 
					 * */
					OneIdAuthInfoVO oneIdAuthInfoVO  = new OneIdAuthInfoVO();
					oneIdAuthInfoVO.setMembId(membId);
					oneIdAuthInfoVO = mgrAppOneIdAuthInfo.selectCekOneIdAuthInfo(oneIdAuthInfoVO);
					
					String oneId = "";
					String ssoKey = "";
					String oneIdMembNo = "";
					String loginStatus = "N";
					String iotSessionKey = "";
					String homeCode = "";
					String ctn = "";
					
					if(oneIdAuthInfoVO == null){
						resultSearchOneIdJSON = new ResultSearchOneIdJSON();
						resultSearchOneIdJSON.setOneId(oneId);
						resultSearchOneIdJSON.setSsoKey(ssoKey);
						resultSearchOneIdJSON.setOneIdMembNo(oneIdMembNo);
						resultSearchOneIdJSON.setLoginStatus(loginStatus);
						resultSearchOneIdJSON.setIotSessionKey(iotSessionKey);
						resultSearchOneIdJSON.setHomeCode(homeCode);
						resultSearchOneIdJSON.setCtn(ctn);
					}else{
						
						if(oneIdAuthInfoVO.getOneId() == null || oneIdAuthInfoVO.getOneId().isEmpty()){
							resultCode = ResultCodeUtil.RC_5C200010;
							return ResultCodeUtil.createResultMsg(resultCode, api);
						}else{
							
							oneId = oneIdAuthInfoVO.getOneId()==null?"":oneIdAuthInfoVO.getOneId();
							ssoKey = oneIdAuthInfoVO.getSsoKey()==null?"":oneIdAuthInfoVO.getSsoKey();
							oneIdMembNo = oneIdAuthInfoVO.getOneIdMembNo()==null?"":oneIdAuthInfoVO.getOneIdMembNo();
							loginStatus = oneIdAuthInfoVO.getLoginStatus()==null?"":oneIdAuthInfoVO.getLoginStatus();
							iotSessionKey = oneIdAuthInfoVO.getIotSessionKey()==null?"":oneIdAuthInfoVO.getIotSessionKey();
							homeCode = oneIdAuthInfoVO.getHomeCode()==null?"":oneIdAuthInfoVO.getHomeCode();
							ctn = oneIdAuthInfoVO.getCtn()==null?"":oneIdAuthInfoVO.getCtn();
							
							resultSearchOneIdJSON = new ResultSearchOneIdJSON();
							resultSearchOneIdJSON.setOneId(oneIdAuthInfoVO.getOneId());
							resultSearchOneIdJSON.setSsoKey(oneIdAuthInfoVO.getSsoKey());
							resultSearchOneIdJSON.setOneIdMembNo(oneIdAuthInfoVO.getOneIdMembNo());
							resultSearchOneIdJSON.setLoginStatus(oneIdAuthInfoVO.getLoginStatus());
							resultSearchOneIdJSON.setIotSessionKey(oneIdAuthInfoVO.getIotSessionKey());
							resultSearchOneIdJSON.setHomeCode(oneIdAuthInfoVO.getHomeCode());
							resultSearchOneIdJSON.setCtn(oneIdAuthInfoVO.getCtn());
						}
					}
					
					logger.info("BM:: membId({}) oneId({}) ssoKey({}) oneIdMembNo({}) iotSessionKey({}) homeCode({}) ctn({}) logoutYn({})",membId, oneId, ssoKey, oneIdMembNo,iotSessionKey, homeCode, ctn, loginStatus);
					
				} catch (Exception e) {
					resultCode = ResultCodeUtil.RC_4C005000;
					logger.error("BM:: sessionId({}) mgrappLoginId({}) Exception({})", sessionId,mgrappLoginId,e);
				} 
				
			} else {
				
				try {
					
					/*
					 * 1. membId로 TB_ ONEID_AUTHINFO 조회 
					 * */
					OneIdAuthInfoVO oneIdAuthInfoVO  = new OneIdAuthInfoVO();
					oneIdAuthInfoVO.setMembId(membId);
					oneIdAuthInfoVO = mgrAppOneIdAuthInfo.selectCekOneIdAuthInfo(oneIdAuthInfoVO);
					if(oneIdAuthInfoVO == null){
						resultSearchOneIdJSON = new ResultSearchOneIdJSON();
						resultSearchOneIdJSON.setOneId("");
						resultSearchOneIdJSON.setSsoKey("");
						resultSearchOneIdJSON.setOneIdMembNo("");
						resultSearchOneIdJSON.setLoginStatus("N");
						resultSearchOneIdJSON.setIotSessionKey("");
						resultSearchOneIdJSON.setHomeCode("");
						resultSearchOneIdJSON.setCtn("");
					}else{
						
						String custmId;
						if(oneIdAuthInfoVO.getOneId() == null || oneIdAuthInfoVO.getOneId().isEmpty()){
							resultCode = ResultCodeUtil.RC_5C200010;
							return ResultCodeUtil.createResultMsg(resultCode, api);
						}else{
							custmId = oneIdAuthInfoVO.getOneId();						
						}
						
						List<Map<String, Object>> cekSearchResultList = new LinkedList<Map<String, Object>>();
						
						CekAuthResponseJSON cekAuthResponseJSON = cekAuthAgent.getSvcAuthInfo2(reqJson, mgrappLoginId,nid,custmId);
						if(cekAuthResponseJSON !=null){
							cekSearchResultList = (List<Map<String, Object>>) cekAuthResponseJSON.getBody().getAuthInfos();
						}
	
						String tempOneId = null;
						String tempSsoKey =null;
						String tempOneIdMembNo =null;
						String tempIotSessionKey =null;
						String tempHomeCode =null;
						String tempCtn =null;
						String tempLoginStatus = null;
						for(int i = 0 ; i < cekSearchResultList.size() ; i++){
							if(cekSearchResultList.get(i).get("contSvcCd").equals(CekAuthConst.SERVICE_CODE_BIOT)) {
								tempOneId = (String) cekSearchResultList.get(i).get("authParameter1");
								tempSsoKey = (String) cekSearchResultList.get(i).get("authParameter3");
								tempOneIdMembNo = (String) cekSearchResultList.get(i).get("authParameter2");
								tempIotSessionKey = (String) cekSearchResultList.get(i).get(CekAuthConst.PARAM_NAME_IOT_SESSION_KEY);
								tempHomeCode = (String) cekSearchResultList.get(i).get("authParameter5");
								tempCtn = (String) cekSearchResultList.get(i).get("authParameter8");
								tempLoginStatus = (String) cekSearchResultList.get(i).get("logoutYn");
							}
						}
						
						// TB_ONEID_AUTHINFO One ID 정보 저장
						String oneId = tempOneId;
						String ssoKey = tempSsoKey;
						String oneIdMembNo = tempOneIdMembNo;
						String iotSessionKey = tempIotSessionKey;
						String homeCode = tempHomeCode;
						String ctn = tempCtn;
						String loginStatus;
						if(tempLoginStatus.equals("Y")){
							loginStatus = "N";
						}else{
							loginStatus = "Y";
						}
						
						logger.info("AM:: membId({}) oneId({}) ssoKey({}) oneIdMembNo({}) iotSessionKey({}) homeCode({}) ctn({}) logoutYn({})",membId, oneId, ssoKey, oneIdMembNo,iotSessionKey, homeCode, ctn, loginStatus);
						
						int oneIdAuthInfoCnt = mgrAppOneIdAuthInfo.insertCekOneIdAuthInfo(membId, oneId, ssoKey, oneIdMembNo,
								iotSessionKey, homeCode, ctn, nid, loginStatus);
	
						if (oneIdAuthInfoCnt > 0) {
							logger.info(
									"AM:: Insert to TB_ONEID_AUTHINFO. mgrappId({}) sessionId({}) mgrappLoginId({}) membId({}) ondId({}) ssoKey({}) oneIdMembNo({})",
									mgrappId, sessionId, mgrappLoginId, membId, oneId, ssoKey, oneIdMembNo);
	
						} else {
							resultCode = ResultCodeUtil.RC_5C200004;
							logger.info(
									"AM:: Insert to TB_ONEID_AUTHINFO. mgrappId({}) sessionId({}) mgrappLoginId({}) membId({}) ondId({}) ssoKey({}) oneIdMembNo({})",
									mgrappId, sessionId, mgrappLoginId, membId, oneId, ssoKey, oneIdMembNo);
							return ResultCodeUtil.createResultMsg(resultCode, api);
						}
						
						if(tempLoginStatus.equals("Y")){
							resultSearchOneIdJSON = new ResultSearchOneIdJSON();
							resultSearchOneIdJSON.setOneId(tempOneId);
							resultSearchOneIdJSON.setSsoKey(tempSsoKey);
							resultSearchOneIdJSON.setOneIdMembNo(tempOneIdMembNo);
							resultSearchOneIdJSON.setLoginStatus("N");
							resultSearchOneIdJSON.setIotSessionKey(tempIotSessionKey);
							resultSearchOneIdJSON.setHomeCode(tempHomeCode);
							resultSearchOneIdJSON.setCtn(tempCtn);
							
						}else if(tempLoginStatus.equals("N")){
							resultSearchOneIdJSON = new ResultSearchOneIdJSON();
							resultSearchOneIdJSON.setOneId(tempOneId);
							resultSearchOneIdJSON.setSsoKey(tempSsoKey);
							resultSearchOneIdJSON.setOneIdMembNo(tempOneIdMembNo);
							resultSearchOneIdJSON.setLoginStatus("Y");
							resultSearchOneIdJSON.setIotSessionKey(tempIotSessionKey);
							resultSearchOneIdJSON.setHomeCode(tempHomeCode);
							resultSearchOneIdJSON.setCtn(tempCtn);
						}
					}
				} catch (Exception e) {
					resultCode = ResultCodeUtil.RC_4C005000;
					logger.error("AM:: sessionId({}) mgrappLoginId({}) Exception({})", sessionId,mgrappLoginId,e);
				} 
			}
		}
		
		response = ResultCodeUtil.createResultMsg(resultCode,resultSearchOneIdJSON, api);
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
