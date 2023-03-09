package com.lgu.ccss.api.service.service.search;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;

import com.lgu.ccss.common.constant.CCSSConst;
import com.lgu.ccss.common.dao.CekInfotainAuthInfoDao;
import com.lgu.ccss.common.dao.mgr.MgrAppOneIdAuthInfo;
import com.lgu.ccss.common.model.CekInfotainAuthInfoVO;
import com.lgu.ccss.common.model.OneIdAuthInfoVO;
import com.lgu.ccss.common.model.RequestJSON;
import com.lgu.ccss.common.model.ResponseJSON;
import com.lgu.ccss.common.util.CCSSUtil;
import com.lgu.ccss.common.util.ResultCodeUtil;
import com.lgu.ccss.common.validation.CheckResultData;
import com.lgu.ccss.common.validation.ValidationChecker;
import com.lgu.common.aisv.auth.AisvAuthConst;
import com.lgu.common.cekAi.auth.CekAuthAgent;
import com.lgu.common.cekAi.auth.CekAuthConst;
import com.lgu.common.cekAi.auth.model.CekAuthResponseJSON;
import com.lgu.common.util.ExceptionUtil;

@Service("searchCekInfotainmentService")
public class SearchCekInfotainmentServiceImpl implements SearchCekInfotainmentService{
	private static final Logger logger = LoggerFactory.getLogger(SearchCekInfotainmentServiceImpl.class);
	private static final List<String> mandatoryList = new ArrayList<String>(
			Arrays.asList(RequestJSON.PARAM_SERVICE_CODE));

	@Autowired
	private CekInfotainAuthInfoDao cekInfotainAuthInfoDao;	
	
	@Autowired
	private MgrAppOneIdAuthInfo mgrAppOneIdAuthInfo;
	
	@Autowired
	private CekAuthAgent cekAuthAgent;
	
	@Override
	public ResponseJSON doService(HttpHeaders headers, RequestJSON reqJson) {
		String api = reqJson.getCommon().getApiId();
		String deviceCtn = CCSSUtil.getCtn();
		
		ResponseJSON response = null;

		CheckResultData result = ValidationChecker.checkValidation(null,reqJson,CCSSConst.API_ID_SERVICE_CEK_SEARCH_INFOTAINMENT,mandatoryList);
		if (result.isStatus() == false) {
			response = ResultCodeUtil.createResultMsg(ResultCodeUtil.RC_3C004000, api, result.getReason());
			return response;
		}
		
		try {
			response = searchCekInfotainment(headers, reqJson);

		} catch (Exception e) {
			logger.error("deviceCtn({}) Exception({})", deviceCtn, ExceptionUtil.getPrintStackTrace(e));
			response = ResultCodeUtil.createResultMsg(ResultCodeUtil.RC_4C005000, api);
		}

		return response;
	}


	private ResponseJSON searchCekInfotainment(HttpHeaders headers, RequestJSON reqJson) throws Exception {
		String api = reqJson.getCommon().getApiId();
		String membId = CCSSUtil.getMembId();
		String serviceCode = (String) reqJson.getParam().get(RequestJSON.PARAM_SERVICE_CODE);
		
		// BM(AVN)에서 AI연동을 제거하기위한 앱타입정보 (2019/11/11)
		String deviceType = reqJson.getCommon().getDevice().getDeviceType();
		
		OneIdAuthInfoVO oneIdAuthInfoVO  = new OneIdAuthInfoVO();
		oneIdAuthInfoVO.setMembId(membId);
		oneIdAuthInfoVO = mgrAppOneIdAuthInfo.selectCekOneIdAuthInfo(oneIdAuthInfoVO);
		
		Map<String, String> authParameter = new LinkedHashMap<String, String>();
		
		String nid = null;
		String custmId = null;
		if(oneIdAuthInfoVO == null || oneIdAuthInfoVO.getOneId() ==null
				|| oneIdAuthInfoVO.getLoginStatus() ==null || oneIdAuthInfoVO.getLoginStatus().equals("N")){
			return ResultCodeUtil.createResultMsg(ResultCodeUtil.RC_2I000000, api);
		}else{
			custmId = oneIdAuthInfoVO.getOneId();
			nid = oneIdAuthInfoVO.getNid();
		}
		
		/*
		 * Date : 2019/11/11 (멀티홈코드 개발건 연계)
		 * Author : JYJ
		 * Description :
		 * 		BM에서 AI 연동을 제거하기 위해 AM으로 기본 프로세스는 유지한채  BM 별도 분기처리 (음성처리 AI 연동제거)
		 * 		BM에서는 매니저앱에서 전달받은 CEK정보를 U+우리집AI와 연동처리를 안하고 DB에만 저장처리
		 */
		// BM(AVN)의 경우 (신규)
		if (deviceType.equalsIgnoreCase(CCSSConst.DEF_AVN)) {
			
			// TB_ONEID_AUTHINFO정보에는 '홈IoT 단말ID' 정보가 없어 TB_CEK_INFOTAIN_AUTHINFO에서 authParameter7 컬럼에서 데이터 가져옴
			List<CekInfotainAuthInfoVO> infotainAuthInfoList = cekInfotainAuthInfoDao.selectInfotainAuthInfo(membId,serviceCode);
			
			if (infotainAuthInfoList.isEmpty()) {
				logger.error("failed to select Cek InfotainAuthInfo data. membId({}) serviceCode({})" ,membId, serviceCode);
				return ResultCodeUtil.createResultMsg(ResultCodeUtil.RC_2I000000, api);
			}else{

				String authParameter7 = "";
				for (CekInfotainAuthInfoVO vo : infotainAuthInfoList) {
					if( vo.getTokenNm().equals("authParameter7")) {
						authParameter7 = vo.getTokenValue();
						break;
					}
				}
				
				authParameter.put("authParameter1", oneIdAuthInfoVO.getOneId());
				authParameter.put("authParameter2", oneIdAuthInfoVO.getSsoKey());
				authParameter.put("authParameter3", oneIdAuthInfoVO.getIotSessionKey());
				authParameter.put("authParameter4", oneIdAuthInfoVO.getOneIdMembNo());
				authParameter.put("authParameter5", authParameter7);
				authParameter.put("authParameter6", oneIdAuthInfoVO.getHomeCode());
			}
			
		// AM의 경우 (기존 프로세스 유지)
		} else {
		
			List<Map<String, Object>> cekSearchResultList = new LinkedList<Map<String, Object>>();
			
			CekAuthResponseJSON cekAuthResponseJSON = cekAuthAgent.getSvcAuthInfo2(reqJson, membId, nid, custmId);
			
			if(cekAuthResponseJSON !=null){
				cekSearchResultList = (List<Map<String, Object>>) cekAuthResponseJSON.getBody().getAuthInfos();
			} else{
				return ResultCodeUtil.createResultMsg(ResultCodeUtil.RC_2I000000, api);
			}
			
			String authParameter1 = null;
			String authParameter2 = null;
			String authParameter3 = null;
			String authParameter4 = null;
			String authParameter5 = null;
			String authParameter7 = null;			
			
			for(int i = 0 ; i < cekSearchResultList.size() ; i++) {
				if(cekSearchResultList.get(i).get("contSvcCd").equals(CekAuthConst.SERVICE_CODE_BIOT)) {
					authParameter1 = (String) cekSearchResultList.get(i).get("authParameter1");
					authParameter2 = (String) cekSearchResultList.get(i).get("authParameter2");
					authParameter3 = (String) cekSearchResultList.get(i).get("authParameter3");
					authParameter4 = (String) cekSearchResultList.get(i).get("authParameter4");
					authParameter5 = (String) cekSearchResultList.get(i).get("authParameter5");
					authParameter7 = (String) cekSearchResultList.get(i).get("authParameter7");
				}
			}
			
			authParameter.put("authParameter1", authParameter1);
			authParameter.put("authParameter2", authParameter3);
			authParameter.put("authParameter3", authParameter4);
			authParameter.put("authParameter4", authParameter2);
			authParameter.put("authParameter5", authParameter7);
			authParameter.put("authParameter6", authParameter5);
		}
		
		return ResultCodeUtil.createResultMsg(ResultCodeUtil.RC_2S000000, authParameter, api);
	}
}
