package com.lgu.ccss.mgr.send2car.service.geocodingSerch;

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
import com.lgu.common.map.MapAgent;
import com.lgu.common.map.MapConst;
import com.lgu.common.map.model.ResRevgeocodingSearchJSON;
import com.lgu.common.model.ResultCode;
import com.lgu.common.util.AES256Util;

@Service("revGeocodingSearchService")
public class RevGeocodingSearchServiceImpl implements RevGeocodingSearchService{

	private static final Logger logger = LoggerFactory.getLogger(RevGeocodingSearchService.class);
	private static final List<String> mandatoryList = new ArrayList<String>(
			Arrays.asList(RequestJSON.PARAM_MEMB_ID,RequestJSON.PARAM_POSX,RequestJSON.PARAM_POSY));
	

	@Autowired
	private MapAgent mapAgent;
	
	@Autowired
	private MgrAppManagementDao mgrAppManagementDao;
	
	@Override
	public ResponseJSON doService(HttpHeaders headers, RequestJSON reqJson) {
		ResultCode resultCode=ResultCodeUtil.RC_2S000000;
		String api = reqJson.getCommon().getApiId();
		String mgrappLoginId = CCSSUtil.getMgrUserLoginId();
		String sessionId = CCSSUtil.getMgrSessionId();
		String membId = (String) reqJson.getParam().get(RequestJSON.PARAM_MEMB_ID);
		String mgrappId = CCSSUtil.getMgrappId(); 
		setTloData(membId);
		
		ResponseJSON response = null;
		
		ResRevgeocodingSearchJSON resultMapInfoData = null;
		
		CheckResultData result = ValidationChecker.checkValidation(headers,reqJson,CCSSConst.SENDTOCAR_REV_GEOCODING_SEARCH,mandatoryList);
		
		if (result.isStatus() == false) {
			resultCode = ResultCodeUtil.RC_3C004000;
			response = ResultCodeUtil.createResultMsg(resultCode,api,result.getReason());
			return response;
		}else{
			
			
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
					
					String encPosx = (String) reqJson.getParam().get(RequestJSON.PARAM_POSX);
					String encPosy = (String) reqJson.getParam().get(RequestJSON.PARAM_POSY);	
					
					reqJson.getParam().put(RequestJSON.PARAM_POSX,AES256Util.AESDecode(membVO.getTransToken() ,encPosx));
					reqJson.getParam().put(RequestJSON.PARAM_POSY,AES256Util.AESDecode(membVO.getTransToken() ,encPosy));
					
					resultMapInfoData = mapAgent.revgeocodingSearch(reqJson, MapConst.URL_REV_GEOCODING_SEARCH, MapConst.SVC_POI, deviceType, mgrappLoginId);				
					if(resultMapInfoData !=null && resultMapInfoData.getResult_code().equals("0")){ // 조회 결과 없음.
						
						resultMapInfoData.getAdm().setPosx(AES256Util.AESEncode(membVO.getTransToken() ,resultMapInfoData.getAdm().getPosx()));
						resultMapInfoData.getAdm().setPosy(AES256Util.AESEncode(membVO.getTransToken() ,resultMapInfoData.getAdm().getPosy()));
					}else{
						resultCode=ResultCodeUtil.RC_7C000000;
						logger.error("Not Exist Search Result sessionId({}) membId({}) mgrappId({})  mgrappLoginId({})  ",sessionId,membId,mgrappId,mgrappLoginId);
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
		
		response = ResultCodeUtil.createResultMsg(resultCode,resultMapInfoData, api);
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
