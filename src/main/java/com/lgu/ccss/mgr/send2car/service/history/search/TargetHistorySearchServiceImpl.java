package com.lgu.ccss.mgr.send2car.service.history.search;

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
import com.lgu.ccss.common.dao.Send2CarDao;
import com.lgu.ccss.common.dao.mgr.MgrAppManagementDao;
import com.lgu.ccss.common.model.MembVO;
import com.lgu.ccss.common.model.RequestJSON;
import com.lgu.ccss.common.model.ResponseJSON;
import com.lgu.ccss.common.model.Send2CarVO;
import com.lgu.ccss.common.tlo.TloData;
import com.lgu.ccss.common.tlo.TloUtil;
import com.lgu.ccss.common.util.CCSSUtil;
import com.lgu.ccss.common.util.ResultCodeUtil;
import com.lgu.ccss.common.validation.CheckResultData;
import com.lgu.ccss.common.validation.ValidationChecker;
import com.lgu.ccss.mgr.send2car.model.history.DestListJSON;
import com.lgu.ccss.mgr.send2car.model.history.ResultHistorySearchJSON;
import com.lgu.ccss.mgr.send2car.model.history.Send2CarLocationJSON;
import com.lgu.common.model.ResultCode;
import com.lgu.common.util.AES256Util;

@Service("targetHistorySearchService")
public class TargetHistorySearchServiceImpl implements TargetHistorySearchService{

	private static final Logger logger = LoggerFactory.getLogger(TargetHistorySearchServiceImpl.class);
	private static final List<String> mandatoryList = new ArrayList<String>(
			Arrays.asList(RequestJSON.PARAM_MEMB_ID ,RequestJSON.PARAM_START_POSITION ,RequestJSON.PARAM_REQ_COUNT ));

	
	@Autowired
	private Send2CarDao send2CarDao;
	
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
		
		ResponseJSON response = null;
		ResultHistorySearchJSON resultHistorySearchJSON =null;
		
		
		CheckResultData result = ValidationChecker.checkValidation(headers,reqJson,CCSSConst.SENDTOCAR_HISTORY_LIST,mandatoryList);
		
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
					
					//2.memb_id & mgrapp_id TB_SEND2CAR ,USE_YN='Y' 조회
					Send2CarVO send2CarVO = new Send2CarVO();
					send2CarVO.setUseYn(CCSSConst.DEF_YES);
					send2CarVO.setMembId(membId);
					send2CarVO.setMgrappId(mgrappId);
					send2CarVO.setStartPosition((String) reqJson.getParam().get(RequestJSON.PARAM_START_POSITION));
					send2CarVO.setReqCount( (String) reqJson.getParam().get(RequestJSON.PARAM_REQ_COUNT));
					
					//MGR APP에서 호출 할때 부를수 있는 LIST의 최대 RNUM 값. 
					int maxCount = 99;
					//호출시 한번에 불러오는 LIST의 숫자 또는 호출시 불러오는 LIST의 마지막 LIST의 RNUM.
					int reqCount = Integer.parseInt(reqJson.getParam().get(RequestJSON.PARAM_REQ_COUNT).toString());
					//호출시 불러오는 LIST에서 첫번째 RNUM.
					int startPosition = Integer.parseInt(reqJson.getParam().get(RequestJSON.PARAM_START_POSITION).toString());
					
					// startPosition의 범위는 0~98, 0미만 98초과의 값을 PARAMETER 오류로 분류.
					if(startPosition > maxCount-1) {
						resultCode = ResultCodeUtil.RC_3C004000;
						response = ResultCodeUtil.createResultMsg(resultCode,api,result.getReason());
						return response;
					}
					
					//reqCount의 범위는 1~99, 1미만 또는 99초과의 값은 PARAMETER 오류로 분류.
					if(reqCount > maxCount || reqCount <= 0) {
						resultCode = ResultCodeUtil.RC_3C004000;
						response = ResultCodeUtil.createResultMsg(resultCode,api,result.getReason());
						return response;
					}
					
					//startPosition+reqCount+1값이 maxCount의 값보다 크거나, 불러들일 리스트의 갯수가 reqCount 값 미만일때 reqCount의 값을 제한해둠.
					if((startPosition+1) >= ((maxCount-1)-reqCount) && (maxCount-startPosition) <= (reqCount+1)) {
						send2CarVO.setReqCount(Integer.toString(maxCount-startPosition));
					}					
					List<Send2CarVO> send2CarList = send2CarDao.selectMgrAppTargetHis(send2CarVO);					
					
					setTloData(membId);
					
					if(send2CarList !=null && send2CarList.size() > 0){
						
						resultHistorySearchJSON = new ResultHistorySearchJSON();
						List<DestListJSON> destList = new LinkedList<DestListJSON>();
						if (send2CarList.get(0).getTotalCount() > 99) {
							resultHistorySearchJSON.setTotalCount(99);
						} else {
							resultHistorySearchJSON.setTotalCount(send2CarList.get(0).getTotalCount());
						}
						
						for(Send2CarVO tempVO : send2CarList){
							DestListJSON destListJSON =new DestListJSON();
							destListJSON.setTargetIndex(tempVO.getRnum());
							destListJSON.setSendToCarId(tempVO.getSendToCarId());
							destListJSON.setArrivHopeTime(tempVO.getArrivHopeTime());
							
							Send2CarLocationJSON locationJSON = new Send2CarLocationJSON();
							locationJSON.setName(tempVO.getTargetNm());
							locationJSON.setAddress(tempVO.getTargetAddress());
							
							
							//DB 위경도 복호화
							String decLonx = AES256Util.AESDecode(CCSSConst.LOCATION_ENC_KEY ,tempVO.getTargetLonx());
							String decLaty = AES256Util.AESDecode(CCSSConst.LOCATION_ENC_KEY , tempVO.getTargetLaty());	
							
							//현재 TransToken으로 암호화
							locationJSON.setLonx(AES256Util.AESEncode(membVO.getTransToken(),decLonx));
							locationJSON.setLaty(AES256Util.AESEncode(membVO.getTransToken(),decLaty));
							
							if(tempVO.getTargetRealLonx() !=null && tempVO.getTargetRealLonx().length() > 0){
								String decRealLonx = AES256Util.AESDecode(CCSSConst.LOCATION_ENC_KEY ,tempVO.getTargetRealLonx());
								locationJSON.setRlonx(AES256Util.AESEncode(membVO.getTransToken(),decRealLonx));
							}
							if(tempVO.getTargetRealLaty() !=null && tempVO.getTargetRealLaty().length() > 0){
								String decRealLaty = AES256Util.AESDecode(CCSSConst.LOCATION_ENC_KEY , tempVO.getTargetRealLaty());	
								locationJSON.setRlaty(AES256Util.AESEncode(membVO.getTransToken(),decRealLaty));
							}
							
							
							locationJSON.setPoiid(tempVO.getPoiId());
							locationJSON.setRoadname(tempVO.getRoadName());
							locationJSON.setRoadjibun(tempVO.getRoadJibun());
							locationJSON.setSearchOption(tempVO.getSearchOption());
							
							destListJSON.setLocation(locationJSON);
							destList.add(destListJSON);
						}
						
						resultHistorySearchJSON.setDestList(destList);
						
					}else{
						resultCode = ResultCodeUtil.RC_9C000000;
						logger.error("Not Exist Target History sessionId({}) membId({}) mgrappId({})  mgrappLoginId({}) ",sessionId,membId,mgrappId,mgrappLoginId);
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
				logger.error("sessionId({}) membId({}) mgrappId({})  mgrappLoginId({}) Exception({})", sessionId,membId,mgrappId,mgrappLoginId,e);
			}
		}
		
		response = ResultCodeUtil.createResultMsg(resultCode,resultHistorySearchJSON, api);
		return response;
	}
	
	private void setTloData(String membId) {
		if (membId == null) {
			return;
		}
		Map<String, String> tlo = new HashMap<String, String>();
		tlo.put(TloData.MEMB_ID, membId);
		//tlo.put(TloData.MEMB_NO, memb.getMembNo());
		TloUtil.setTloData(tlo);
	}
}
