package com.lgu.ccss.api.location.service;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;

import com.lgu.ccss.api.location.model.LocationData;
import com.lgu.ccss.common.constant.CCSSConst;
import com.lgu.ccss.common.dao.ArrivalNotiDao;
import com.lgu.ccss.common.dao.DeviceDao;
import com.lgu.ccss.common.dao.MemberDao;
import com.lgu.ccss.common.dao.ParkingLocationDao;
import com.lgu.ccss.common.dao.mgr.MgrAppSmsDao;
import com.lgu.ccss.common.enumtype.msg.MsgStatus;
import com.lgu.ccss.common.enumtype.msg.MsgType;
import com.lgu.ccss.common.enumtype.msg.SendType;
import com.lgu.ccss.common.model.ArrivalAlarmSetVO;
import com.lgu.ccss.common.model.ConnDeviceVO;
import com.lgu.ccss.common.model.DeviceParkLoctVO;
import com.lgu.ccss.common.model.MembVO;
import com.lgu.ccss.common.model.RequestJSON;
import com.lgu.ccss.common.model.ResponseJSON;
import com.lgu.ccss.common.model.mgr.MgrAppSmsVO;
import com.lgu.ccss.common.util.CCSSUtil;
import com.lgu.ccss.common.util.ResultCodeUtil;
import com.lgu.ccss.common.validation.CheckResultData;
import com.lgu.ccss.common.validation.ValidationChecker;
import com.lgu.ccss.mgr.arrival.model.rcpt.search.ResultArrivalSearchVO;
import com.lgu.common.map.MapAgent;
import com.lgu.common.map.MapConst;
import com.lgu.common.map.model.ResRevgeocodingSearchJSON;
import com.lgu.common.model.ResultCode;
import com.lgu.common.util.AES256Util;
import com.lgu.common.util.LocationDistance;
import com.lgu.common.util.keygenerator.KeyGenerator;

@Service("locationService")
public class LocationServiceImpl implements LocationService {

	private static final Logger logger = LoggerFactory.getLogger(LocationServiceImpl.class);
	
	private static String SERVER_ID = System.getProperty("SERVER_ID");

	@Autowired
	MemberDao memberDao;
	
	@Autowired
	DeviceDao deviceDao;

	@Autowired
	ParkingLocationDao parkingLocationDao;

	@Autowired
	private MapAgent mapAgent;
	
	@Autowired
	private ArrivalNotiDao arrivalNotiDao;
	
	@Autowired
	private MgrAppSmsDao mgrAppSmsDao;
	
	@Value("#{config['sms.callback']}")
	private String smsCallback;

	@Value("#{config['sms.arrivalNotiContent']}")
	private String arrivalNoticeSmsContent;
	
	@Override
	public ResponseJSON doService(HttpHeaders headers, RequestJSON reqJson) {
		String api = reqJson.getCommon().getApiId();
		String deviceCtn = CCSSUtil.getCtn();

		ResponseJSON response = null;

		CheckResultData result = ValidationChecker.checkValidation(null, reqJson, CCSSConst.API_ID_LOCATION_PARKING,
				null);
		if (result.isStatus() == false) {
			response = ResultCodeUtil.createResultMsg(ResultCodeUtil.RC_3C004000, api, result.getReason());
			return response;
		}

		try {
			response = setLocation(reqJson);

		} catch (Exception e) {
			logger.error("deviceCtn({}) Exception({})", deviceCtn, e);
			response = ResultCodeUtil.createResultMsg(ResultCodeUtil.RC_4C005000, api);
		}

		return response;
	}

	@SuppressWarnings("unchecked")
	ResponseJSON setLocation(RequestJSON reqJson) {
		String api = reqJson.getCommon().getApiId();
		String deviceCtn = CCSSUtil.getCtn();
		String ccssToken = CCSSUtil.getCcssToken();
		String membId = CCSSUtil.getMembId();

		MembVO memb = memberDao.selectMemberByID(membId);
		if (memb == null) {
			logger.error("failed to select member data. deviceCtn({}) ccssToken({}) membId({})", deviceCtn, ccssToken,
					membId);
			return ResultCodeUtil.createResultMsg(ResultCodeUtil.RC_3C002004, api);
		}
		
		ConnDeviceVO connDevice = new ConnDeviceVO();
		connDevice.setMembId(membId);
		connDevice = deviceDao.getDeviceInfo(connDevice);
		if (connDevice == null) {
			logger.error("failed to select connDevice data. deviceCtn({}) ccssToken({}) membId({})", deviceCtn, ccssToken,
					membId);
			return ResultCodeUtil.createResultMsg(ResultCodeUtil.RC_4C005001, api);
		}

		LinkedHashMap<String, String> reqMap = null;
		try {
			reqMap = (LinkedHashMap<String, String>) reqJson.getParam().get(RequestJSON.PARAM_LOCATION);

		} catch (Exception e) {
			logger.error("deviceCtn({}) Exception({})", deviceCtn, e);
			return ResultCodeUtil.createResultMsg(ResultCodeUtil.RC_3C004000, api, "param.location");
		}

		String laty = (String) reqMap.get(LocationData.FIELD_LATITUDE);
		String lonx = (String) reqMap.get(LocationData.FIELD_LONGTITUDE);

		Position pos = new Position(lonx, laty);
		Position decPos = pos.decode(memb.getTransToken());
		if (decPos == null) {
			return ResultCodeUtil.createResultMsg(ResultCodeUtil.RC_3C004005, api);
		}
		
		sendArrivalNotice(memb, decPos);
		
		ResultCode result = saveParkingLocation(membId, connDevice.getConnDeviceId(), decPos);		
		
		return ResultCodeUtil.createResultMsg(result, api);
	}
	
	private void sendArrivalNotice(MembVO memb, Position newPos) {
		
		ArrivalAlarmSetVO arrivalAlarmSetVO = new ArrivalAlarmSetVO();
		arrivalAlarmSetVO.setMembId(memb.getMembId());
		
		List<ArrivalAlarmSetVO> arrivalAlarmList = arrivalNotiDao.selectUsedArrivalAlarmInfo(arrivalAlarmSetVO);
		if (arrivalAlarmList == null || arrivalAlarmList.size() == 0) {
			return;
		}
		
		for (ArrivalAlarmSetVO arrivalAlarm : arrivalAlarmList) {
			Position pos = new Position(arrivalAlarm.getTargetLonx(), arrivalAlarm.getTargetLaty());
			Position decPos = pos.decode(CCSSConst.LOCATION_ENC_KEY);

			double locationDistance  = LocationDistance.distance
					(Double.parseDouble(decPos.getLaty()) , Double.parseDouble(decPos.getLonx()), 
							Double.parseDouble(newPos.getLaty()),Double.parseDouble(newPos.getLonx()), "meter");
			if (locationDistance < CCSSConst.MIN_LOCATION_DISTANCE_ARRIVAL_NOTICE) {
				sendArrivalNoticeSms(memb.getMembId(), arrivalAlarm.getArrivalAlarmId());
			}
			
			logger.debug("[LOCATION_DISTANCE] start({} end({}) result({})", decPos, newPos, locationDistance);
		}
	}
	
	private void sendArrivalNoticeSms(String membId, String arrivalAlarmId) {
		ArrivalAlarmSetVO arrivalAlarmVO = new ArrivalAlarmSetVO();
		arrivalAlarmVO.setMembId(membId);
		arrivalAlarmVO.setArrivalAlarmId(arrivalAlarmId);
		
		List<ResultArrivalSearchVO> arrivalRcptList = arrivalNotiDao.selectArrivalRcptInfo(arrivalAlarmVO);
		for (ResultArrivalSearchVO arrivalRcpt : arrivalRcptList) {
			mgrAppSmsDao.setSmsInfoReg(makeArrivalNoticeSms(arrivalRcpt));
		}
	}
	
	private MgrAppSmsVO makeArrivalNoticeSms(ResultArrivalSearchVO arrivalRcpt) {
		MgrAppSmsVO resultVO = new  MgrAppSmsVO();
		
		String msgSeq;
		try {
			msgSeq = KeyGenerator.createCommonShardKey();
		} catch (Exception e) {
			logger.error("[EXCEPTION] {}", e);
			return null;
		}
		
		resultVO.setMsgId(Long.parseLong(msgSeq));
		resultVO.setMsgStatus(MsgStatus.WAIT.getValue());
		resultVO.setCode("");
		resultVO.setMsgTitle("");
		resultVO.setMsgCont(arrivalNoticeSmsContent);
		resultVO.setMsgType(MsgType.SMS.getValue()); //SMS
		resultVO.setRecvPhoneNo(arrivalRcpt.getRcptCtn());
		resultVO.setSendType(SendType.SEND_INSTANTLY.getValue());
		resultVO.setSvrId(SERVER_ID);
		resultVO.setOrgNo(smsCallback);
		resultVO.setCallbackNo(smsCallback);
		
		return resultVO;
	}
	
	private ResultCode saveParkingLocation(String membId, String connDeviceId, Position newPos) {
		String deviceCtn = CCSSUtil.getCtn();
		
		Map<String, Object> geoMap = new LinkedHashMap<String, Object>();
		geoMap.put(RequestJSON.PARAM_POSX, newPos.getLonx());
		geoMap.put(RequestJSON.PARAM_POSY, newPos.getLaty());
		RequestJSON reqJson = new RequestJSON();
		reqJson.setParam(geoMap);
		ResRevgeocodingSearchJSON resGeo = mapAgent.revgeocodingSearch(reqJson, MapConst.URL_REV_GEOCODING_SEARCH,
				MapConst.SVC_POI, MapConst.DEVICE_TYPE_AM, deviceCtn);
		if (resGeo == null || resGeo.getAdm() == null) {
			return ResultCodeUtil.RC_7C000000;
		}

		// String date = (String) map.get(LocationData.FIELD_DATE);

		DeviceParkLoctVO location = new DeviceParkLoctVO();
		location.setConnDeviceId(connDeviceId);
		location.setMembId(membId);
		location.setAddress(resGeo.getAdm().getAddress());
		
		Position encPos = newPos.encode(CCSSConst.LOCATION_ENC_KEY);
		location.setLonx(encPos.getLonx());
		location.setLaty(encPos.getLaty());

		if (logger.isDebugEnabled()) {
			logger.debug("DEVICE_CTN({}) LOCATION({})", deviceCtn, location);
		}
		
		if (parkingLocationDao.insertParkingLocation(location) == 0) {
			logger.error("failed to insert parking location data. deviceCtn({})", deviceCtn);

			return ResultCodeUtil.RC_4C005001;
		}
		
		return ResultCodeUtil.RC_2S000000;
	}
}

class Position {
	
	private static final Logger logger = LoggerFactory.getLogger(Position.class);
	
	private String lonx;
	private String laty;
	
	public Position(String lonx, String laty) {
		this.lonx = lonx;
		this.laty = laty;
	}
	
	public String getLonx() {
		return lonx;
	}
	public void setLonx(String lonx) {
		this.lonx = lonx;
	}
	public String getLaty() {
		return laty;
	}
	public void setLaty(String laty) {
		this.laty = laty;
	}
	
	public Position encode(String key) {
		Position newPos = null;
		
		try {
			if (this.lonx != null && this.laty != null) {
				String lonx = AES256Util.AESEncode(key, this.lonx);
				String laty = AES256Util.AESEncode(key, this.laty);
				newPos = new Position(lonx, laty);
			}
		} catch (Exception e) {
			logger.error("key({}) pos({}) Exception({})", key, this, e);
		}

		return newPos;
	}
	
	public Position decode(String key) {
		Position newPos = null;
		
		try {
			if (this.lonx != null && this.laty != null) {
				String lonx = AES256Util.AESDecode(key, this.lonx);
				String laty = AES256Util.AESDecode(key, this.laty);
				newPos = new Position(lonx, laty);
			}
		} catch (Exception e) {
			logger.error("key({}) pos({}) Exception({})", key, this, e);
		}

		return newPos;
	}
	
	@Override
	public String toString() {
		return "Position [lonx=" + lonx + ", laty=" + laty + "]";
	}
}
