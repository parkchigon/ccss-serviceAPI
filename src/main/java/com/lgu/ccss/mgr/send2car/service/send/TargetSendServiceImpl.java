package com.lgu.ccss.mgr.send2car.service.send;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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
import com.lgu.common.map.MapAgent;
import com.lgu.common.map.MapConst;
import com.lgu.common.map.model.ResRevgeocodingSearchJSON;
import com.lgu.common.map.model.findStatRoute.ResFindStatRouthSearchJSON;
import com.lgu.common.model.ResultCode;
import com.lgu.common.util.AES256Util;
import com.lgu.common.util.keygenerator.KeyGenerator;

@Service("targetSendService")
public class TargetSendServiceImpl implements TargetSendService{

	private static final Logger logger = LoggerFactory.getLogger(TargetSendServiceImpl.class);
	private static final List<String> mandatoryList = new ArrayList<String>(
			Arrays.asList(RequestJSON.PARAM_MEMB_ID ,RequestJSON.PARAM_END_LONX ,RequestJSON.PARAM_END_LATY
					,RequestJSON.PARAM_END_NM,RequestJSON.PARAM_SEARCH_OPTION));
	
	@Autowired
	private Send2CarDao send2CarDao;
	
	@Autowired
	private MgrAppManagementDao mgrAppManagementDao;
	
	@Autowired
	private MapAgent mapAgent;
	
	@Value("#{systemProperties.SERVER_ID}")
	private String svrId;
	
	@Value("#{config['arriv.hope.time.gap']}")
	private int arrivHopeTimeGap;

	
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

		CheckResultData result = ValidationChecker.checkValidation(headers,reqJson,CCSSConst.SENDTOCAR_TARGET_SEND,mandatoryList);
		
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
					
					Send2CarVO send2CarVO = new Send2CarVO();
					send2CarVO.setSendToCarId(KeyGenerator.createCommonShardKey());
					send2CarVO.setUseYn(CCSSConst.DEF_YES);
					send2CarVO.setMembId(membId);
					send2CarVO.setMgrappId(mgrappId);
					send2CarVO.setSendStatus(Send2CarVO.SEND_STATUS_READY);
					send2CarVO.setArrivHopeTime((String) reqJson.getParam().get(RequestJSON.PARAM_ARRIV_HOPE_TIME));
					send2CarVO.setSvrId(svrId);
					send2CarVO.setServiceType(CCSSConst.SENDTOCAR_SVC_TYPE_NOMAL);
					send2CarVO.setRsvType(CCSSConst.SENDTOCAR_RSV_TYPE_IMMEDIATELY);
					
					
					//도착지 위경도 암복호화
					String descTargetLonx = AES256Util.AESDecode(membVO.getTransToken(),  (String) reqJson.getParam().get(RequestJSON.PARAM_END_LONX));
					String descTargetLaty = AES256Util.AESDecode(membVO.getTransToken(), (String) reqJson.getParam().get(RequestJSON.PARAM_END_LATY));
					
					String descTargetRealLonx = AES256Util.AESDecode(membVO.getTransToken(),  (String) reqJson.getParam().get(RequestJSON.PARAM_END_REAL_LONX));
					String descTargetRealLaty = AES256Util.AESDecode(membVO.getTransToken(), (String) reqJson.getParam().get(RequestJSON.PARAM_END_REAL_LATY));
					
					send2CarVO.setTargetLonx( AES256Util.AESEncode(CCSSConst.LOCATION_ENC_KEY ,descTargetLonx));
					send2CarVO.setTargetLaty( AES256Util.AESEncode(CCSSConst.LOCATION_ENC_KEY ,descTargetLaty));
					send2CarVO.setTargetRealLonx(AES256Util.AESEncode(CCSSConst.LOCATION_ENC_KEY ,descTargetRealLonx));
					send2CarVO.setTargetRealLaty(AES256Util.AESEncode(CCSSConst.LOCATION_ENC_KEY ,descTargetRealLaty));
					send2CarVO.setPoiId((int) reqJson.getParam().get(RequestJSON.PARAM_END_POI_ID));
					
					send2CarVO.setRoadName((String) reqJson.getParam().get(RequestJSON.PARAM_END_ROAD_NAME));
					send2CarVO.setRoadJibun((String) reqJson.getParam().get(RequestJSON.PARAM_END_ROAD_JIBUN));
					send2CarVO.setTargetNm((String) reqJson.getParam().get(RequestJSON.PARAM_END_NM));
					send2CarVO.setSearchOption((String) reqJson.getParam().get(RequestJSON.PARAM_SEARCH_OPTION));
					
					String deviceType;
					if(MembVO.MARKET_AM.equals(membVO.getMarketType())){
						deviceType = MapConst.DEVICE_TYPE_AM;
					}else{
						deviceType = MapConst.DEVICE_TYPE_BM;
					}
					
					
					//목적 설정 시간이 있는 경우
					if(send2CarVO.getArrivHopeTime() !=null && send2CarVO.getArrivHopeTime().length() > 0){
						//목적 설정 시간이 있는 경우
						
						send2CarVO.setServiceType(CCSSConst.SENDTOCAR_SVC_TYPE_RESERVATION);
						
						//reqJson.getParam().put(RequestJSON.PARAM_END_LONX,descTargetLonx);
						//reqJson.getParam().put(RequestJSON.PARAM_END_LATY,descTargetLaty);
						
						reqJson.getParam().put(RequestJSON.PARAM_END_REAL_LONX,descTargetRealLonx);
						reqJson.getParam().put(RequestJSON.PARAM_END_REAL_LATY,descTargetRealLaty);
						
						// 목적 설정시간 내에 도착 할수 없으면 . 실패 처리.
						String startLonx = (String) reqJson.getParam().get(RequestJSON.PARAM_START_LONX);
						String startLaty =	(String) reqJson.getParam().get(RequestJSON.PARAM_START_LATY);
						if(startLonx !=null && startLaty !=null){
							
							String arrivHopeTime = (String) reqJson.getParam().get(RequestJSON.PARAM_ARRIV_HOPE_TIME);
							
							String descStartLonx = AES256Util.AESDecode(membVO.getTransToken(),  (String) reqJson.getParam().get(RequestJSON.PARAM_START_LONX));
							String descStartLaty = AES256Util.AESDecode(membVO.getTransToken(), (String) reqJson.getParam().get(RequestJSON.PARAM_START_LATY));
							
							reqJson.getParam().put(RequestJSON.PARAM_START_LONX,descStartLonx);
							reqJson.getParam().put(RequestJSON.PARAM_START_LATY,descStartLaty);
							
							ResFindStatRouthSearchJSON resFindStatRouthSearchResult =  mapAgent.findStatRouthSearch(reqJson, MapConst.URL_FIND_STAT_ROUTH, MapConst.SVC_ROUTH, deviceType, mgrappLoginId);
							
							if(resFindStatRouthSearchResult.getTlo_code().equals(ResFindStatRouthSearchJSON.SUCCESS_TLO_CODE)){
								
								long startTime = resFindStatRouthSearchResult.getTime_list().get(0).getStart_time();
								long endTime = resFindStatRouthSearchResult.getTime_list().get(0).getEnd_time();
								
								long diffTime =  endTime - startTime;

								String estTime= String.valueOf(diffTime); // 초로 변환
								
								//System.out.println("--------------------------------------------------------");
								//System.out.println("startTime:"+ startTime);
								//System.out.println("endTime:"+ endTime);
								//System.out.println("estTime(초):"+ estTime);
								//System.out.println("--------------------------------------------------------");
								
								//도착 희망 시간 <  현재 시간  + 예상 소요시간
								if(possibilityCheckArrivHopeTime(arrivHopeTime , estTime)){
									
									send2CarVO.setEstTime(estTime);
									
								}else{
									
									// 만약 갈수 없는 시간대 일경우... 실패 처리.
									resultCode=ResultCodeUtil.RC_9C000003;
									logger.error("Target Send Fail - Expected  Time is longer than arrivHopeTime . sessionId({}) membId({}) mgrappId({})  mgrappLoginId({}) send2CarVO({})",sessionId,membId,mgrappId,mgrappLoginId,send2CarVO.toString());
									response = ResultCodeUtil.createResultMsg(resultCode,api,"Expected  Time is longer than arrivHopeTime");
									return response;
								}
								
							}else{
								// 타임머신 조회 결과 없음.
								resultCode=ResultCodeUtil.RC_9C000004;
								logger.error("Target Send  Fail - Not Exist time-machine Search result . sessionId({}) membId({}) mgrappId({})  mgrappLoginId({}) send2CarVO({})",sessionId,membId,mgrappId,mgrappLoginId,send2CarVO.toString());
								response = ResultCodeUtil.createResultMsg(resultCode,api,"Not Exist Time-Machine Search result");
								return response;
							}	
						}
					}
					//좌표로 지도 인프라 조회 - Address 조회를 위해
					reqJson.getParam().put(RequestJSON.PARAM_POSX,descTargetRealLonx);
					reqJson.getParam().put(RequestJSON.PARAM_POSY,descTargetRealLaty);
					ResRevgeocodingSearchJSON  searchReult = mapAgent.revgeocodingSearch(reqJson, MapConst.URL_REV_GEOCODING_SEARCH, MapConst.SVC_POI, deviceType, mgrappLoginId);
					if(searchReult.getAdm().getRoadname() != null && !searchReult.getAdm().getRoadname().equals("")) {
						send2CarVO.setTargetAddress(searchReult.getAdm().getRoadname());						
					}else {
						send2CarVO.setTargetAddress(searchReult.getAdm().getAddress());
					}
					
					
					//목적지 중복 체크
					if(!send2CarDao.checkDupleTargetSendList(send2CarVO)) {
						
						
						int deleteCount = send2CarDao.updateOneHundred(send2CarVO);
						
						if(deleteCount > 0){
							logger.info("Success Delete Target History sessionId({}) membId({}) mgrappId({})  mgrappLoginId({}) ",sessionId,membId,mgrappId,mgrappLoginId);
						}else{
							logger.error("No Target History. sessionId({}) membId({}) mgrappId({})  mgrappLoginId({}) ",sessionId,membId,mgrappId,mgrappLoginId);
						}
						logger.error("send2CarVO({})",send2CarVO.toString());
						// DB 등록
						boolean regFlag = send2CarDao.insertTargetSend(send2CarVO);
						
						if(regFlag){
							logger.info("Target Send Info Reg Success. sessionId({}) membId({}) mgrappId({})  mgrappLoginId({})",sessionId,membId,mgrappId,mgrappLoginId);

						}else{
							resultCode=ResultCodeUtil.RC_4C005001;
							logger.error("Target Send Info Reg Fail. sessionId({}) membId({}) mgrappId({})  mgrappLoginId({}) send2CarVO({})",sessionId,membId,mgrappId,mgrappLoginId,send2CarVO.toString());
						}
					}else{
						
						resultCode=ResultCodeUtil.RC_9C000002;
						logger.error("Duplicated Send2Car Target List  sessionId({}) membId({}) mgrappId({})  mgrappLoginId({})  ",sessionId,membId,mgrappId,mgrappLoginId);
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
		
		response = ResultCodeUtil.createResultMsg(resultCode,response, api);
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
	
	private boolean possibilityCheckArrivHopeTime(String arrivHopeTime, String estTime) {
		boolean flag = false;
		
		try{
			
			SimpleDateFormat  formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm");
			Date hopeTime = formatter.parse(arrivHopeTime);
			Calendar hopeTimeCal = Calendar.getInstance();
			hopeTimeCal.setTime(hopeTime);
			hopeTimeCal.add(Calendar.MINUTE, arrivHopeTimeGap);
			Date allowHopeTime;
			allowHopeTime = formatter.parse(formatter.format(hopeTimeCal.getTime()));
			
			//현재 시간 예상 소요시간 ADD
			Date nowTime = new Date();
			Calendar cal = Calendar.getInstance();
			cal.setTime(nowTime);
			cal.add(Calendar.SECOND, Integer.parseInt(estTime));
			
			String strAddTime = formatter.format(cal.getTime());
			Date addDate;
			addDate = formatter.parse(strAddTime);
			
			//도착 희망 시간이 현재 시간 + 예상 시간 보다 높을 경우.
			if(allowHopeTime.getTime() > addDate.getTime()){
				flag = true;
			}
			
		}catch (Exception e){
			logger.error("possibilityCheckArrivHopeTime Fail. arrivHopeTime({}) estTime({})", arrivHopeTime,estTime,e);
		}
	
		
		return flag;
		
	}
}
