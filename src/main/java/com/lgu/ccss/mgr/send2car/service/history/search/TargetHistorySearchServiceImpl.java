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
			
				//1.?????? MembId ??? transTocken ?????? 
				MembVO membVO = new MembVO();
				membVO.setMembId(membId);
				membVO.setMgrappId(mgrappId);
				membVO = mgrAppManagementDao.selectMembTransToken(membVO);
				
				if(membVO !=null){
					
					//2.memb_id & mgrapp_id TB_SEND2CAR ,USE_YN='Y' ??????
					Send2CarVO send2CarVO = new Send2CarVO();
					send2CarVO.setUseYn(CCSSConst.DEF_YES);
					send2CarVO.setMembId(membId);
					send2CarVO.setMgrappId(mgrappId);
					send2CarVO.setStartPosition((String) reqJson.getParam().get(RequestJSON.PARAM_START_POSITION));
					send2CarVO.setReqCount( (String) reqJson.getParam().get(RequestJSON.PARAM_REQ_COUNT));
					
					//MGR APP?????? ?????? ?????? ????????? ?????? LIST??? ?????? RNUM ???. 
					int maxCount = 99;
					//????????? ????????? ???????????? LIST??? ?????? ?????? ????????? ???????????? LIST??? ????????? LIST??? RNUM.
					int reqCount = Integer.parseInt(reqJson.getParam().get(RequestJSON.PARAM_REQ_COUNT).toString());
					//????????? ???????????? LIST?????? ????????? RNUM.
					int startPosition = Integer.parseInt(reqJson.getParam().get(RequestJSON.PARAM_START_POSITION).toString());
					
					// startPosition??? ????????? 0~98, 0?????? 98????????? ?????? PARAMETER ????????? ??????.
					if(startPosition > maxCount-1) {
						resultCode = ResultCodeUtil.RC_3C004000;
						response = ResultCodeUtil.createResultMsg(resultCode,api,result.getReason());
						return response;
					}
					
					//reqCount??? ????????? 1~99, 1?????? ?????? 99????????? ?????? PARAMETER ????????? ??????.
					if(reqCount > maxCount || reqCount <= 0) {
						resultCode = ResultCodeUtil.RC_3C004000;
						response = ResultCodeUtil.createResultMsg(resultCode,api,result.getReason());
						return response;
					}
					
					//startPosition+reqCount+1?????? maxCount??? ????????? ?????????, ???????????? ???????????? ????????? reqCount ??? ???????????? reqCount??? ?????? ????????????.
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
							
							
							//DB ????????? ?????????
							String decLonx = AES256Util.AESDecode(CCSSConst.LOCATION_ENC_KEY ,tempVO.getTargetLonx());
							String decLaty = AES256Util.AESDecode(CCSSConst.LOCATION_ENC_KEY , tempVO.getTargetLaty());	
							
							//?????? TransToken?????? ?????????
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
