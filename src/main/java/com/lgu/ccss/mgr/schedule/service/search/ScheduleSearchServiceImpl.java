package com.lgu.ccss.mgr.schedule.service.search;

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
import com.lgu.ccss.common.dao.ScheduleDao;
import com.lgu.ccss.common.dao.mgr.MgrAppManagementDao;
import com.lgu.ccss.common.model.MembVO;
import com.lgu.ccss.common.model.RequestJSON;
import com.lgu.ccss.common.model.ResponseJSON;
import com.lgu.ccss.common.model.ScheduleSetVO;
import com.lgu.ccss.common.tlo.TloData;
import com.lgu.ccss.common.tlo.TloUtil;
import com.lgu.ccss.common.util.CCSSUtil;
import com.lgu.ccss.common.util.ResultCodeUtil;
import com.lgu.ccss.common.validation.CheckResultData;
import com.lgu.ccss.common.validation.ValidationChecker;
import com.lgu.ccss.mgr.schedule.model.ResultScheduleSearchJSON;
import com.lgu.ccss.mgr.schedule.model.ResultScheduleSetVO;
import com.lgu.common.model.ResultCode;
import com.lgu.common.util.AES256Util;

@Service("scheduleSearchService")
public class ScheduleSearchServiceImpl implements ScheduleSearchService{

	private static final Logger logger = LoggerFactory.getLogger(ScheduleSearchServiceImpl.class);
	private static final List<String> mandatoryList = new ArrayList<String>(
			Arrays.asList(RequestJSON.PARAM_MEMB_ID,RequestJSON.PARAM_START_POSITION,RequestJSON.PARAM_REQ_COUNT));

	@Autowired
	private MgrAppManagementDao mgrAppManagementDao;
	
	@Autowired
	private ScheduleDao scheduleDao;
	
	
	//TO-DO
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
		ResultScheduleSearchJSON resultScheduleSearchJSON =null;
		
		CheckResultData result = ValidationChecker.checkValidation(headers,reqJson,CCSSConst.SCHEDULE_SEARCH_LIST,mandatoryList);
		
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
					ScheduleSetVO scheduleSetVO  = new ScheduleSetVO();
					scheduleSetVO.setMembId(membId);
					scheduleSetVO.setMgrappId(mgrappId);
					scheduleSetVO.setStartPosition((String) reqJson.getParam().get(RequestJSON.PARAM_START_POSITION));
					scheduleSetVO.setReqCount((String) reqJson.getParam().get(RequestJSON.PARAM_REQ_COUNT));
					
					List<ScheduleSetVO>  scheduleSetList = scheduleDao.selectSchedulList(scheduleSetVO);
					
					if(scheduleSetList !=null && scheduleSetList.size() > 0){
						resultScheduleSearchJSON = new ResultScheduleSearchJSON();
						List<ResultScheduleSetVO> resultScheduleSetList = new LinkedList<ResultScheduleSetVO>();
						for(ScheduleSetVO tempVO : scheduleSetList ){
							ResultScheduleSetVO resultScheduleSetVO = new ResultScheduleSetVO();
							//DB 복호화
							String desStartLonx = AES256Util.AESDecode(CCSSConst.LOCATION_ENC_KEY ,tempVO.getStartLonx());
							String desStartLaty = AES256Util.AESDecode(CCSSConst.LOCATION_ENC_KEY , tempVO.getStartLaty());	
							String desTargetLonx = AES256Util.AESDecode(CCSSConst.LOCATION_ENC_KEY , tempVO.getTargetLonx());
							String desTargetLaty = AES256Util.AESDecode(CCSSConst.LOCATION_ENC_KEY , tempVO.getTargetLaty());
							
							if(tempVO.getTargetRealLonx() !=null && tempVO.getTargetRealLonx().length() > 0 && tempVO.getTargetRealLaty() !=null && tempVO.getTargetRealLaty().length() > 0){
								String desTargetRealLonx = AES256Util.AESDecode(CCSSConst.LOCATION_ENC_KEY , tempVO.getTargetRealLonx());
								resultScheduleSetVO.setTargetRealLonx(AES256Util.AESEncode(membVO.getTransToken()  ,desTargetRealLonx));
								
								String desTargetRealLaty = AES256Util.AESDecode(CCSSConst.LOCATION_ENC_KEY , tempVO.getTargetRealLaty());
								resultScheduleSetVO.setTargetRealLaty(AES256Util.AESEncode(membVO.getTransToken()  ,desTargetRealLaty));
							}
							

							resultScheduleSetVO.setIndex(tempVO.getIndex());
							resultScheduleSetVO.setScheduleId(tempVO.getScheduleId());
							resultScheduleSetVO.setStartNm(tempVO.getStartNm());
							resultScheduleSetVO.setStartAddress(tempVO.getStartAddress());
							resultScheduleSetVO.setStartJibun(tempVO.getStartJibun());
							resultScheduleSetVO.setStartLonx(AES256Util.AESEncode(membVO.getTransToken()  ,desStartLonx));
							resultScheduleSetVO.setStartLaty(AES256Util.AESEncode(membVO.getTransToken()  ,desStartLaty));
							resultScheduleSetVO.setTargetNm(tempVO.getTargetNm());
							resultScheduleSetVO.setTargetAddress(tempVO.getTargetAddress());
							resultScheduleSetVO.setTargetLonx(AES256Util.AESEncode(membVO.getTransToken() ,desTargetLonx));
							resultScheduleSetVO.setTargetLaty(AES256Util.AESEncode(membVO.getTransToken() ,desTargetLaty));
							resultScheduleSetVO.setArrivHopeTime(tempVO.getArrivHopeTime());
							resultScheduleSetVO.setRepeatAlarmDay(tempVO.getRepeatAlarmDay());
							
							resultScheduleSetVO.setTargetPoiid(tempVO.getPoiId());
							resultScheduleSetVO.setTargetRoadName(tempVO.getRoadName());
							resultScheduleSetVO.setTargetRoadJibun(tempVO.getRoadJibun());
							resultScheduleSetVO.setSearchOption(tempVO.getSearchOption());
							
							resultScheduleSetList.add(resultScheduleSetVO);
							
							/*//TransToken 암호화
							tempVO.setStartLonx(AES256Util.AESEncode(membVO.getTransToken()  ,desStartLonx));
							tempVO.setStartLaty(AES256Util.AESEncode(membVO.getTransToken()  ,desStartLaty));
							tempVO.setTargetLonx(AES256Util.AESEncode(membVO.getTransToken() ,desTargetLonx));
							tempVO.setTargetLaty(AES256Util.AESEncode(membVO.getTransToken() ,desTargetLonx));*/
						}
						//resultScheduleSearchJSON.setScheduleList(scheduleSetList);
						resultScheduleSearchJSON.setScheduleList(resultScheduleSetList);
						resultScheduleSearchJSON.setTotalCount(scheduleSetList.get(0).getTotalCount());
						
					}else{
						resultCode=ResultCodeUtil.RC_9C100000;
						logger.debug("Not Exist Schedul List sessionId({}) membId({}) mgrappId({})  mgrappLoginId({})",sessionId,membId,mgrappId,mgrappLoginId);
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
		
		response = ResultCodeUtil.createResultMsg(resultCode,resultScheduleSearchJSON,api);
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
