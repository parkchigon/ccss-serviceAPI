package com.lgu.ccss.mgr.send2car.service.totalSearch;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
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
import com.lgu.ccss.mgr.send2car.model.totalSearch.AdmJSON;
import com.lgu.ccss.mgr.send2car.model.totalSearch.AdmListJSON;
import com.lgu.ccss.mgr.send2car.model.totalSearch.AdmLocationJSON;
import com.lgu.ccss.mgr.send2car.model.totalSearch.PoiListJSON;
import com.lgu.ccss.mgr.send2car.model.totalSearch.PoiLocationJSON;
import com.lgu.ccss.mgr.send2car.model.totalSearch.ResultTotalSearchJSON;
import com.lgu.common.map.MapAgent;
import com.lgu.common.map.MapConst;
import com.lgu.common.map.model.ResTotalSearchJSON;
import com.lgu.common.map.model.poi.POIDataJSON;
import com.lgu.common.model.ResultCode;
import com.lgu.common.util.AES256Util;

@Service("totalSearchService")
public class TotalSearchServiceImpl implements TotalSearchService{

	private static final Logger logger = LoggerFactory.getLogger(TotalSearchServiceImpl.class);
	private static final List<String> mandatoryList = new ArrayList<String>(
			Arrays.asList(RequestJSON.PARAM_MEMB_ID,RequestJSON.PARAM_START_POSITION,RequestJSON.PARAM_REQ_COUNT
					,RequestJSON.PARAM_SEARCH_WORD,RequestJSON.PARAM_SORTOPT));
	
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
		ResultTotalSearchJSON resultTotalSearchJSON = null;
		
		CheckResultData result = ValidationChecker.checkValidation(headers,reqJson,CCSSConst.SENDTOCAR_TOTAL_SEARCH,mandatoryList);
		
		
		if (result.isStatus() == false) {
			resultCode = ResultCodeUtil.RC_3C004000;
			response = ResultCodeUtil.createResultMsg(resultCode,api,result.getReason());
			return response;
		}else{
			ResTotalSearchJSON resultMapInfoData = null;
			
			try {
				
				//1.?????? MembId ??? transTocken ?????? 
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
					
					resultMapInfoData = mapAgent.totalSearch(reqJson, MapConst.URL_TOTAL_SEARCH, MapConst.SVC_POI, deviceType, mgrappLoginId, membVO.getTransToken());				
					
					
					if(resultMapInfoData != null){
						if (resultMapInfoData.getResult_code().equals(MapConst.RESULT_SUCCESS)){ //??????
							
							resultTotalSearchJSON = new ResultTotalSearchJSON();
							
							//Total Count
							resultTotalSearchJSON.setTotalCount(resultMapInfoData.getTotalcount());
							
							//Type Total Count
							resultTotalSearchJSON.setAdmtotalcount(resultMapInfoData.getAdmtotalcount());
							//resultTotalSearchJSON.setReftotalcount(resultMapInfoData.getReftotalcount());
							//resultTotalSearchJSON.setUcp_poitotalcount(resultMapInfoData.getUcp_poitotalcount());
							//resultTotalSearchJSON.setTel_poitotalcount(resultMapInfoData.getTel_poitotalcount());
							resultTotalSearchJSON.setPoitotalcount(resultMapInfoData.getPoitotalcount());
							
							//Type Count
							resultTotalSearchJSON.setAdmcount(resultMapInfoData.getAdmcount());
							//resultTotalSearchJSON.setTel_poicount(resultMapInfoData.getTel_poicount());
							//resultTotalSearchJSON.setRefcount(resultMapInfoData.getRefcount());
							resultTotalSearchJSON.setPoicount(resultMapInfoData.getPoicount());
							//resultTotalSearchJSON.setUcp_poicount(resultMapInfoData.getUcp_poicount());
							
							List<AdmJSON> admJsonArray = new LinkedList<AdmJSON>();
							admJsonArray = resultMapInfoData.getAdm();
							
							List<AdmListJSON> resultAdmListJSON = new LinkedList<AdmListJSON>();
							
							
							if(admJsonArray != null && admJsonArray.size() > 0){
								
							
								for(int idx=0; idx < admJsonArray.size(); idx++){
									
									AdmListJSON  admListJSON = new AdmListJSON();
									admListJSON.setTargetIndex(idx);
									
									AdmLocationJSON tempAdmLocationJSON = new AdmLocationJSON();
									String posx = admJsonArray.get(idx).getPosx();
									String posy = admJsonArray.get(idx).getPosy();
									tempAdmLocationJSON.setLonx(AES256Util.AESEncode(membVO.getTransToken() ,posx));
									tempAdmLocationJSON.setLaty(AES256Util.AESEncode(membVO.getTransToken() ,posy));
									tempAdmLocationJSON.setAddress(admJsonArray.get(idx).getAddress());
									tempAdmLocationJSON.setRoadname(admJsonArray.get(idx).getRoadname());
									tempAdmLocationJSON.setRoadjibun(admJsonArray.get(idx).getRoadjibun());
									admListJSON.setLocation(tempAdmLocationJSON);

									resultAdmListJSON.add(admListJSON);
								}
								resultTotalSearchJSON.setAdmList(resultAdmListJSON);
							}

							
							List<POIDataJSON> poiJsonArray = new LinkedList<POIDataJSON>();
							poiJsonArray = resultMapInfoData.getPoi();
							
							
							List<PoiListJSON> resultPoiListJSON = new LinkedList<PoiListJSON>();
							
							if(poiJsonArray != null && poiJsonArray.size() > 0){
								for(int idx=0; idx < poiJsonArray.size(); idx++){
									PoiListJSON  poiListJSON = new PoiListJSON();
									poiListJSON.setTargetIndex(idx);
									
									PoiLocationJSON tempPoiLocationJSON = new PoiLocationJSON();
									
									String dpx = poiJsonArray.get(idx).getDpx();
									String dpy = poiJsonArray.get(idx).getDpy();
									String rpx = poiJsonArray.get(idx).getRpx();
									String rpy = poiJsonArray.get(idx).getRpy();
									
									tempPoiLocationJSON.setDpx(AES256Util.AESEncode(membVO.getTransToken() ,dpx));
									tempPoiLocationJSON.setDpy(AES256Util.AESEncode(membVO.getTransToken() ,dpy));
									tempPoiLocationJSON.setRpx(AES256Util.AESEncode(membVO.getTransToken() ,rpx));
									tempPoiLocationJSON.setRpy(AES256Util.AESEncode(membVO.getTransToken() ,rpy));
									
									tempPoiLocationJSON.setPoiid(poiJsonArray.get(idx).getPoiid());
									tempPoiLocationJSON.setName(poiJsonArray.get(idx).getName1());
									tempPoiLocationJSON.setAddress(poiJsonArray.get(idx).getAddress());
									tempPoiLocationJSON.setRoadname(poiJsonArray.get(idx).getRoadname());
									tempPoiLocationJSON.setRoadjibun(poiJsonArray.get(idx).getRoadjibun());
									
									poiListJSON.setLocation(tempPoiLocationJSON);
									
									resultPoiListJSON.add(poiListJSON);
								}
								resultTotalSearchJSON.setPoiList(resultPoiListJSON);
							}	
						}else{
							
							//????????????
							logger.error("failed to request Map Infra data. sessionId({}) mgrappLoginId({}) resTotalSearchJSON({})",sessionId,mgrappLoginId, resultMapInfoData);
							resultCode = ResultCodeUtil.RC_7C000000;
						}
					}else{
						//?????? ??????
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
		
		response = ResultCodeUtil.createResultMsg(resultCode,resultTotalSearchJSON, api);
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
