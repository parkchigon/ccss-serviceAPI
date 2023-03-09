package com.lgu.ccss.mgr.parking.service.location;

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
import com.lgu.ccss.common.dao.mgr.MgrAppParkLocDao;
import com.lgu.ccss.common.model.DeviceParkLoctVO;
import com.lgu.ccss.common.model.MembVO;
import com.lgu.ccss.common.model.RequestJSON;
import com.lgu.ccss.common.model.ResponseJSON;
import com.lgu.ccss.common.tlo.TloData;
import com.lgu.ccss.common.tlo.TloUtil;
import com.lgu.ccss.common.util.CCSSUtil;
import com.lgu.ccss.common.util.ResultCodeUtil;
import com.lgu.ccss.common.validation.CheckResultData;
import com.lgu.ccss.common.validation.ValidationChecker;
import com.lgu.ccss.mgr.parking.model.ResultParkingLocationJSON;
import com.lgu.common.model.ResultCode;
import com.lgu.common.util.AES256Util;
import com.lgu.common.util.LocationDistance;

@Service("parkingLocationService")
public class ParkingLocationServiceImpl implements ParkingLocationService{

	private static final Logger logger = LoggerFactory.getLogger(ParkingLocationServiceImpl.class);
	private static final List<String> mandatoryList = new ArrayList<String>(
			Arrays.asList(RequestJSON.PARAM_MEMB_ID,RequestJSON.PARAM_LONX,RequestJSON.PARAM_LATY));

	@Autowired
	private MgrAppParkLocDao mgrAppParkLocDao;
	
	@Autowired
	private MgrAppManagementDao mgrAppManagementDao;
	
	
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
		ResultParkingLocationJSON resultParkingLocationJSON = null;
		
		CheckResultData result = ValidationChecker.checkValidation(headers,reqJson,CCSSConst.SEARCH_PARKING_LOCATION,mandatoryList);
		
		if (result.isStatus() == false) {
			resultCode = ResultCodeUtil.RC_3C004000;
			response = ResultCodeUtil.createResultMsg(resultCode,api,result.getReason());
			return response;
			
		}else{
			
			
			try {
				/*
				 * 1. membId로 TB_MEMB trancToken 조회 
				 * */
				MembVO membVO  = new MembVO();
				membVO.setMembId(membId);
				membVO.setMgrappId(mgrappId);
				membVO = mgrAppManagementDao.selectMembTransToken(membVO);
				
				if(membVO !=null){
					
					/*
					 * 2. membId로 TB_DEVICE_PARK_LOCT 조회 (조건 : 삭제 정책이 현재 없기 때문에 regDt 정렬 후 rownum= 1 기준으로 개발..
					 * */
					DeviceParkLoctVO deviceParkLoctVO  = new DeviceParkLoctVO();
					deviceParkLoctVO.setMembId(membId);
					deviceParkLoctVO = mgrAppParkLocDao.selectLatestParkLocation(deviceParkLoctVO);
					
					if(deviceParkLoctVO !=null){
						
						//3. transTocekn으로 App 에서 올라온 logx,laty 복호화
						String desLonx = AES256Util.AESDecode(membVO.getTransToken(), (String) reqJson.getParam().get(RequestJSON.PARAM_LONX));
						String desLaty = AES256Util.AESDecode(membVO.getTransToken(), (String) reqJson.getParam().get(RequestJSON.PARAM_LATY));
						
						//DB 위경도 복호화
						String parkingLonx = AES256Util.AESDecode(CCSSConst.LOCATION_ENC_KEY ,deviceParkLoctVO.getLonx());
						String parkingLaty = AES256Util.AESDecode(CCSSConst.LOCATION_ENC_KEY , deviceParkLoctVO.getLaty());		
						
						//4. 반경 1km 계산.
						double locationDistance  = LocationDistance.distance
								(Double.parseDouble(desLaty) , Double.parseDouble(desLonx), 
										Double.parseDouble(parkingLaty),Double.parseDouble(parkingLonx), "meter");
						
						if(locationDistance < CCSSConst.MIN_LOCATION_DISTANCE ){
							resultParkingLocationJSON = new ResultParkingLocationJSON();
							resultParkingLocationJSON.setLaty( AES256Util.AESEncode(membVO.getTransToken(), parkingLaty));  // 암호화
							resultParkingLocationJSON.setLonx( AES256Util.AESEncode(membVO.getTransToken(), parkingLonx));
							resultParkingLocationJSON.setParkingDateTime(deviceParkLoctVO.getRegDt());
							resultParkingLocationJSON.setAddress(deviceParkLoctVO.getAddress());
							resultParkingLocationJSON.setDistance(String.valueOf((int)locationDistance));
						}else{
							resultCode=ResultCodeUtil.RC_8C000001;
							logger.error("Distance exceeded 1km sessionId({}) mgrappLoginId({}) ",sessionId,mgrappLoginId);
						}
						
					}else{
						resultCode = ResultCodeUtil.RC_8C000000;
						logger.error("Not Exist Last Parking Information sessionId({}) mgrappLoginId({}) ",sessionId,mgrappLoginId);
					}
				}else{
					resultCode = ResultCodeUtil.RC_3C002004;
					logger.error("Not Exist Member Info mgrappLoginId({}) sessionId({}) membId({})",mgrappLoginId,sessionId, membId);
				}
				
			} catch(BadPaddingException be){
				resultCode = ResultCodeUtil.RC_3C004005;
				logger.error("sessionId({}) mgrappLoginId({}) Exception({})", sessionId,mgrappLoginId,be);
			}catch(IllegalBlockSizeException ibse){
				resultCode = ResultCodeUtil.RC_3C004005;
				logger.error("sessionId({}) mgrappLoginId({}) Exception({})", sessionId,mgrappLoginId,ibse);
			} catch (Exception e) {
				resultCode = ResultCodeUtil.RC_4C005000;
				logger.error("sessionId({}) mgrappLoginId({}) Exception({})", sessionId,mgrappLoginId,e);
			} 
		}
		
		response = ResultCodeUtil.createResultMsg(resultCode,resultParkingLocationJSON, api);
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
