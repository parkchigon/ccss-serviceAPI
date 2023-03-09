package com.lgu.ccss.mgr.schedule.service.register;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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
import com.lgu.common.model.ResultCode;
import com.lgu.common.util.AES256Util;
import com.lgu.common.util.DateUtils;
import com.lgu.common.util.keygenerator.KeyGenerator;

@Service("scheduleRegService")
public class ScheduleRegServiceImpl implements ScheduleRegService{

	private static final Logger logger = LoggerFactory.getLogger(ScheduleRegServiceImpl.class);
	private static final List<String> mandatoryList = new ArrayList<String>(
			Arrays.asList(RequestJSON.PARAM_MEMB_ID,RequestJSON.PARAM_START_LONX,RequestJSON.PARAM_START_LATY,
					RequestJSON.PARAM_START_NM,	RequestJSON.PARAM_END_LONX,RequestJSON.PARAM_END_LATY,
					RequestJSON.PARAM_END_NM,/*RequestJSON.PARAM_ARRIV_HOPE_TIME,*/RequestJSON.PARAM_REPEAT_ALARM_DAY,
					RequestJSON.PARAM_START_ADDRESS,RequestJSON.PARAM_END_ADDRESS,
					RequestJSON.PARAM_END_REAL_LONX,RequestJSON.PARAM_END_REAL_LATY
					,RequestJSON.PARAM_END_POI_ID,RequestJSON.PARAM_SEARCH_OPTION
					));

	
	@Autowired
	private MgrAppManagementDao mgrAppManagementDao;
	
	@Autowired
	private ScheduleDao scheduleDao;
	
	@Value("#{systemProperties.SERVER_ID}")
	private String svrId;
	
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
		
		CheckResultData result = ValidationChecker.checkValidation(headers,reqJson,CCSSConst.SCHEDULE_REG,mandatoryList);
		
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
					String arrivHopeTime = (String) reqJson.getParam().get(RequestJSON.PARAM_ARRIV_HOPE_TIME);
					
					String repeatAlarmDay = (String) reqJson.getParam().get(RequestJSON.PARAM_REPEAT_ALARM_DAY);
					//패턴 체크
					boolean flag = checkScheduleParam(arrivHopeTime,repeatAlarmDay);
					
					if(flag){
						
						//2. transTocekn으로 App 에서 올라온 logx,laty 복호화
						String descStartLonx = AES256Util.AESDecode(membVO.getTransToken(), (String) reqJson.getParam().get(RequestJSON.PARAM_START_LONX));
						String descStartLaty = AES256Util.AESDecode(membVO.getTransToken(), (String) reqJson.getParam().get(RequestJSON.PARAM_START_LATY));
						
						String descTargetLonx = AES256Util.AESDecode(membVO.getTransToken(), (String) reqJson.getParam().get(RequestJSON.PARAM_END_LONX));
						String descTargetLaty = AES256Util.AESDecode(membVO.getTransToken(), (String) reqJson.getParam().get(RequestJSON.PARAM_END_LATY));
						
						String descTargetRealLonx = AES256Util.AESDecode(membVO.getTransToken(), (String) reqJson.getParam().get(RequestJSON.PARAM_END_REAL_LONX));
						String descTargetRealLaty = AES256Util.AESDecode(membVO.getTransToken(), (String) reqJson.getParam().get(RequestJSON.PARAM_END_REAL_LATY));
						
						
						//3. DB 암호화
						String encStartLonx = AES256Util.AESEncode(CCSSConst.LOCATION_ENC_KEY ,descStartLonx);
						String encStLaty = AES256Util.AESEncode(CCSSConst.LOCATION_ENC_KEY , descStartLaty);		
						
						String encTargetLonx = AES256Util.AESEncode(CCSSConst.LOCATION_ENC_KEY ,descTargetLonx);
						String encTargettLaty = AES256Util.AESEncode(CCSSConst.LOCATION_ENC_KEY , descTargetLaty);	
						
						String encTargetRealLonx = AES256Util.AESEncode(CCSSConst.LOCATION_ENC_KEY ,descTargetRealLonx);
						String encTargettRealLaty = AES256Util.AESEncode(CCSSConst.LOCATION_ENC_KEY , descTargetRealLaty);	
					
						// DB 데이터 생성
						ScheduleSetVO scheduleSetVO  = new ScheduleSetVO();
						scheduleSetVO.setScheduleId(KeyGenerator.createCommonShardKey());
						
						scheduleSetVO.setMembId(membId);
						scheduleSetVO.setMgrappId(mgrappId);
						
						scheduleSetVO.setStartAddress((String) reqJson.getParam().get(RequestJSON.PARAM_START_ADDRESS));
						scheduleSetVO.setStartJibun((String) reqJson.getParam().get(RequestJSON.PARAM_START_JIBUN));
						scheduleSetVO.setStartLonx(encStartLonx);//
						scheduleSetVO.setStartLaty(encStLaty);//
						scheduleSetVO.setStartNm((String) reqJson.getParam().get(RequestJSON.PARAM_START_NM));
						
						scheduleSetVO.setTargetAddress((String) reqJson.getParam().get(RequestJSON.PARAM_END_ADDRESS));
						scheduleSetVO.setTargetNm((String) reqJson.getParam().get(RequestJSON.PARAM_END_NM));
						scheduleSetVO.setTargetLonx(encTargetLonx);// encTargetLonxencTargetLonx
						scheduleSetVO.setTargetLaty(encTargettLaty);  
						
						scheduleSetVO.setTargetRealLonx(encTargetRealLonx);
						scheduleSetVO.setTargetRealLaty(encTargettRealLaty);
						
						scheduleSetVO.setPoiId((int) reqJson.getParam().get(RequestJSON.PARAM_END_POI_ID));
						scheduleSetVO.setRoadName((String) reqJson.getParam().get(RequestJSON.PARAM_END_ROAD_NAME));
						scheduleSetVO.setRoadJibun((String) reqJson.getParam().get(RequestJSON.PARAM_END_ROAD_JIBUN));
						
						scheduleSetVO.setSearchOption((String) reqJson.getParam().get(RequestJSON.PARAM_SEARCH_OPTION));
						
						scheduleSetVO.setArrivHopeTime(arrivHopeTime);
						scheduleSetVO.setRepeatAlarmDay(repeatAlarmDay);
						
						scheduleSetVO.setSvrId(svrId);
						
						//기존 : 도착 희망 시간 현재일 -2일 + 희망 시간
						/*if(arrivHopeTime != null  && arrivHopeTime.length() > 0){
							String sendDt = DateUtils.getCurrentDate(Calendar.DATE, -2 , "yyyy-MM-dd") + " " + arrivHopeTime;
							scheduleSetVO.setSendDt(sendDt);
						}*/
						//변경 : SendToCar데몬의 Config의 scheduler.db.select.prevhour값 (48->24) 변경으로 인한
						//     도착 희망 시간 현재일 -1일 + 희망 시간
						//일자 : 2019-11-20 JYJ
						if(arrivHopeTime != null  && arrivHopeTime.length() > 0){
							String sendDt = DateUtils.getCurrentDate(Calendar.DATE, -1 , "yyyy-MM-dd") + " " + arrivHopeTime;
							scheduleSetVO.setSendDt(sendDt);
						}
						
						
						boolean regFlag = scheduleDao.insertSecheuleInfo(scheduleSetVO);
						
						if(regFlag){
							logger.info("Schedul Info Reg Success. sessionId({}) membId({}) mgrappId({})  mgrappLoginId({})",sessionId,membId,mgrappId,mgrappLoginId);

						}else{
							resultCode=ResultCodeUtil.RC_4C005001;
							logger.error("Schedul Info Reg Fail. sessionId({}) membId({}) mgrappId({})  mgrappLoginId({}) scheduleSetVO({})",sessionId,membId,mgrappId,mgrappLoginId,scheduleSetVO.toString());

						}
					}else{
						
						resultCode=ResultCodeUtil.RC_3C004000;
						logger.error("Invalid Schduler Param arrivHopeTime({}) repeatAlarmDay({}) sessionId({}) membId({}) mgrappId({})  mgrappLoginId({})",arrivHopeTime,repeatAlarmDay,sessionId,membId,mgrappId,mgrappLoginId);
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
		
		response = ResultCodeUtil.createResultMsg(resultCode,api);
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
	
	private boolean checkScheduleParam(String arrivHopeTime,String repeatAlarmDay){
		boolean checkFlag = true;
		
		if(arrivHopeTime !=null && arrivHopeTime.length() > 0){
			String  timeRegExp = "^(2[0-3]|[01][0-9]):?([0-5][0-9])$";
			Pattern p = Pattern.compile(timeRegExp);
			Matcher m = p.matcher(arrivHopeTime);
			 
			 if(m.find()){
				 String repeatDayRegExp = "^[0-1]{7}$";
				 p = Pattern.compile(repeatDayRegExp);
				 m = p.matcher(repeatAlarmDay);
				 
				 if(!m.find()){
					 checkFlag = false;
				 }
				 
			 }else{
				 checkFlag = false;
			 }
			return checkFlag;
		
		}else{
			 String repeatDayRegExp = "^[0-1]{7}$";
			Pattern p = Pattern.compile(repeatDayRegExp);
			Matcher m = p.matcher(repeatAlarmDay);
			 if(!m.find()){
				 checkFlag = false;
			 }
		}
		return checkFlag;
	}
	
}
