package com.lgu.ccss.mgr.send2car.service.routeSearch;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;

import com.lgu.ccss.common.constant.CCSSConst;
import com.lgu.ccss.common.dao.mgr.MgrAppManagementDao;
import com.lgu.ccss.common.model.MembVO;
import com.lgu.ccss.common.model.RequestJSON;
import com.lgu.ccss.common.model.ResponseJSON;
import com.lgu.ccss.common.tlo.TloData;
import com.lgu.ccss.common.tlo.TloUtil;
import com.lgu.ccss.common.util.CCSSUtil;
import com.lgu.ccss.common.util.ResultCodeUtil;
import com.lgu.ccss.common.validation.CheckResultData;
import com.lgu.ccss.common.validation.ValidationChecker;
import com.lgu.ccss.mgr.send2car.model.targetSearch.ResultRouteSearchJSON;
import com.lgu.common.map.MapAgent;
import com.lgu.common.map.MapConst;
import com.lgu.common.map.model.ResRouthSearchJSON;
import com.lgu.common.map.model.findStatRoute.ResFindStatRouthSearchJSON;
import com.lgu.common.model.ResultCode;
import com.lgu.common.util.AES256Util;


@Service("routeSearchService")
public class RouteSearchServiceImpl implements RouteSearchService{

	private static final Logger logger = LoggerFactory.getLogger(RouteSearchServiceImpl.class);
	private static final List<String> mandatoryList = new ArrayList<String>(
			Arrays.asList(RequestJSON.PARAM_MEMB_ID,RequestJSON.PARAM_START_LONX,RequestJSON.PARAM_START_LATY,RequestJSON.PARAM_START_NM,	
					RequestJSON.PARAM_END_LONX, RequestJSON.PARAM_END_LATY,	RequestJSON.PARAM_END_NM,
					RequestJSON.PARAM_SEARCH_OPTION
					/*,RequestJSON.PARAM_X_CORRD,RequestJSON.PARAM_Y_CORRD*/));

	@Autowired
	private MgrAppManagementDao mgrAppManagementDao;
	
	@Autowired
	private MapAgent mapAgent;
	
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
		ResultRouteSearchJSON resultRouteSearchJSON = null; 
		
		CheckResultData result = ValidationChecker.checkValidation(headers,reqJson,CCSSConst.SENDTOCAR_TARGET_ROUTE_SEARCH,mandatoryList);
		
		if (result.isStatus() == false) {
			resultCode = ResultCodeUtil.RC_3C004000;
			response = ResultCodeUtil.createResultMsg(resultCode,api,result.getReason());
			return response;
		}else{
			ResRouthSearchJSON resultMapInfoData = null;
			
			try {
				

				
				//1.현재 MembId 로 transTocken 조회 
				MembVO membVO = new MembVO();
				membVO.setMembId(membId);
				membVO.setMgrappId(mgrappId);
				membVO = mgrAppManagementDao.selectMembTransToken(membVO);
				
				if(membVO !=null){
					
					String deviceType="";
					
					if(membVO.getMarketType().equals(CCSSConst.DEF_AM)){
						deviceType = MapConst.DEVICE_TYPE_AM;
					}else{
						deviceType = MapConst.DEVICE_TYPE_BM;
					}
					
					//출발지 도착지 위경도 암복호화
					String encStartLonx = (String) reqJson.getParam().get(RequestJSON.PARAM_START_LONX);
					String encStartLaty = (String) reqJson.getParam().get(RequestJSON.PARAM_START_LATY);
					String encTargetLonx = (String) reqJson.getParam().get(RequestJSON.PARAM_END_LONX);
					String encTargetLaty = (String) reqJson.getParam().get(RequestJSON.PARAM_END_LATY);
					String encViaLonx = (String) reqJson.getParam().get(RequestJSON.PARAM_VIA_LOC_LONX);
					String encViaLaty = (String) reqJson.getParam().get(RequestJSON.PARAM_VIA_LOC_LATY);	
					
					
					reqJson.getParam().put(RequestJSON.PARAM_START_LONX,AES256Util.AESDecode(membVO.getTransToken() ,encStartLonx));
					reqJson.getParam().put(RequestJSON.PARAM_START_LATY,AES256Util.AESDecode(membVO.getTransToken() ,encStartLaty));
					reqJson.getParam().put(RequestJSON.PARAM_END_LONX,AES256Util.AESDecode(membVO.getTransToken() ,encTargetLonx));
					reqJson.getParam().put(RequestJSON.PARAM_END_LATY,AES256Util.AESDecode(membVO.getTransToken() ,encTargetLaty));
					if(encViaLonx !=null && encViaLaty !=null){
						reqJson.getParam().put(RequestJSON.PARAM_VIA_LOC_LONX,AES256Util.AESDecode(membVO.getTransToken() ,encViaLonx));
						reqJson.getParam().put(RequestJSON.PARAM_VIA_LOC_LATY,AES256Util.AESDecode(membVO.getTransToken() ,encViaLaty));
					}

					resultMapInfoData = mapAgent.routeSearch(reqJson, MapConst.URL_ROUTH_SEARCH, MapConst.SVC_ROUTH, deviceType, mgrappLoginId);				
					
					
					if(resultMapInfoData != null){
						if (resultMapInfoData.getResult_code().equals(MapConst.RESULT_SUCCESS)){ //정상
							resultRouteSearchJSON = new ResultRouteSearchJSON();
							
							//2018.02.26 암호화 삭제
							/*
							List<LinkDataJSON> tempLinkList =resultMapInfoData.getResult().getLink();
							for(LinkDataJSON linkDataJSON : tempLinkList){
								List<VertexDataJSON> tempVertextList = linkDataJSON.getVertext();
								for(VertexDataJSON vertexDataJSON : tempVertextList  ){
									String lonx = vertexDataJSON.getLonx();
									String laty = vertexDataJSON.getLaty();
									vertexDataJSON.setLonx(AES256Util.AESEncode(membVO.getTransToken() ,lonx));
									vertexDataJSON.setLaty(AES256Util.AESEncode(membVO.getTransToken() ,laty));
								}
							}*/
							
							ResFindStatRouthSearchJSON resFindStatRouthSearchResult =  mapAgent.findStatRouthSearch(reqJson, MapConst.URL_FIND_STAT_ROUTH, MapConst.SVC_ROUTH, deviceType, mgrappLoginId);
							
							if(resFindStatRouthSearchResult.getTlo_code().equals(ResFindStatRouthSearchJSON.SUCCESS_TLO_CODE)){
								
								long startTime = resFindStatRouthSearchResult.getTime_list().get(0).getStart_time();
								long endTime = resFindStatRouthSearchResult.getTime_list().get(0).getEnd_time();
								long diffTime =  endTime - startTime;
								String estTime= String.valueOf(diffTime); // 초로 변환
								
								/*System.out.println("--------------------------------------------------------");
								System.out.println("oldTime:"+ resultMapInfoData.getResult().getToTalTime());
								System.out.println("--------------------------------------------------------");
								System.out.println("startTime:"+ startTime);
								System.out.println("endTime:"+ endTime);
								System.out.println("estTime(초):"+ estTime);
								System.out.println("--------------------------------------------------------");*/
								
								resultMapInfoData.getResult().setToTalTime(estTime);
							}
							
							resultRouteSearchJSON.setSearchResult(resultMapInfoData.getResult());
							
						}else{
							//조회실패
							logger.error("failed to request Map Infra data. sessionId({}) mgrappLoginId({}) resRouteSearchJSON({})",sessionId,mgrappLoginId, resultMapInfoData);
							resultCode = ResultCodeUtil.RC_7C000000;
						}
					}else{
						//연동 실패
						resultCode = ResultCodeUtil.RC_7C000001;
					}
					
				}else{
					resultCode=ResultCodeUtil.RC_3C002004;
					logger.error("Not Exist Memb Information sessionId({}) membId({}) mgrappId({})  mgrappLoginId({})  ",sessionId,membId,mgrappId,mgrappLoginId);
				}
				
			} catch(BadPaddingException be){
				resultCode = ResultCodeUtil.RC_3C004005;
				logger.error("sessionId({}) mgrappLoginId({}) Exception({})", sessionId,mgrappLoginId,be);
			} catch(IllegalBlockSizeException ibse){
				resultCode = ResultCodeUtil.RC_3C004005;
				logger.error("sessionId({}) mgrappLoginId({}) Exception({})", sessionId,mgrappLoginId,ibse);
			} catch (Exception e) {
				resultCode = ResultCodeUtil.RC_4C005000;
				logger.error("sessionId({}) mgrappLoginId({}) Exception({})", sessionId,mgrappLoginId,e);
			}
		}
		
		response = ResultCodeUtil.createResultMsg(resultCode,resultRouteSearchJSON, api);
		return response;
	}
	
	private void setTloData(String membId) {
		if (membId == null ) {
			return;
		}
		Map<String, String> tlo = new HashMap<String, String>();
		tlo.put(TloData.MEMB_ID, membId);
		//tlo.put(TloData.MEMB_NO, memb.getMembNo());
		TloUtil.setTloData(tlo);
	}
}
