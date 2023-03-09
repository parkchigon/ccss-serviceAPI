package com.lgu.ccss.api.ev.service.evCharginginfo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;

import com.lgu.ccss.common.constant.CCSSConst;
import com.lgu.ccss.common.constant.MDCConst;
import com.lgu.ccss.common.dao.ev.EvChargingDao;
import com.lgu.ccss.common.model.RequestJSON;
import com.lgu.ccss.common.model.ResponseJSON;
import com.lgu.ccss.common.model.ev.EvCharginginfoVO;
import com.lgu.ccss.common.util.CCSSUtil;
import com.lgu.ccss.common.util.ResultCodeUtil;
import com.lgu.ccss.common.validation.CheckResultData;
import com.lgu.ccss.common.validation.ValidationChecker;

@Service("evCharginginfoService")
public class EvCharginginfoServiceImpl implements EvCharginginfoService {

	private static final Logger logger = LoggerFactory.getLogger(EvCharginginfoServiceImpl.class);
	
	@Autowired
	private EvChargingDao evChargingDao;

	@Override
	public ResponseJSON doService(HttpHeaders headers, RequestJSON reqJson) {
		String api = reqJson.getCommon().getApiId();
		String deviceCtn = CCSSUtil.getCtn();
		
		ResponseJSON response = null;
		
		CheckResultData result = ValidationChecker.checkValidation(headers,reqJson,CCSSConst.API_ID_EV_CHARGINGINFO,null);
		if (result.isStatus() == false) {
			response = ResultCodeUtil.createResultMsg(ResultCodeUtil.RC_3C004000, api, result.getReason());
			return response;
		}

		try {
			response = updateEvCharginginfo(reqJson);
			
		} catch (Exception e) {
			logger.error("deviceCtn({}) Exception({})", deviceCtn, e);
			response = ResultCodeUtil.createResultMsg(ResultCodeUtil.RC_4C005000, api);
		}
		
		return response;
	}
	
	private ResponseJSON updateEvCharginginfo(RequestJSON reqJson) {
		String api = reqJson.getCommon().getApiId();
		String deviceCtn = CCSSUtil.getCtn();
		String ccssToken = CCSSUtil.getCcssToken();
		String membId = CCSSUtil.getMembId();
		
		//EV Parameter
		String battery_capa = (String) reqJson.getParam().get(RequestJSON.PARAM_BATTERY_CAPA);
		String drive_able_dist1 = (String) reqJson.getParam().get(RequestJSON.PARAM_DRIVE_ABLE_DIST1);
		String drive_able_dist2 = (String) reqJson.getParam().get(RequestJSON.PARAM_DRIVE_ABLE_DIST2);
		String expect_chrg_tm1 = (String) reqJson.getParam().get(RequestJSON.PARAM_EXPECT_CHRG_TM1);
		String expect_chrg_tm2 = (String) reqJson.getParam().get(RequestJSON.PARAM_EXPECT_CHRG_TM2);
		String expect_chrg_tm3 = (String) reqJson.getParam().get(RequestJSON.PARAM_EXPECT_CHRG_TM3);
		String car_use_dt = (String) reqJson.getParam().get(RequestJSON.PARAM_CAR_USE_DT);
		String total_drive_dist = (String) reqJson.getParam().get(RequestJSON.PARAM_TOTAL_DRIVE_DIST);
		String chrg_status = (String) reqJson.getParam().get(RequestJSON.PARAM_CHRG_STATUS);
		String full_chrg_alarm = (String) reqJson.getParam().get(RequestJSON.PARAM_FULL_CHRG_ALARM);
		
		if (battery_capa.equals("") || drive_able_dist1.equals("") || drive_able_dist2.equals("") || expect_chrg_tm1.equals("") || expect_chrg_tm2.equals("") || expect_chrg_tm3.equals("") || car_use_dt.equals("") || total_drive_dist.equals("") || chrg_status.equals("")
			|| battery_capa == null || drive_able_dist1 == null || drive_able_dist2 == null || expect_chrg_tm1 == null || expect_chrg_tm2 == null || expect_chrg_tm3 == null || car_use_dt == null || total_drive_dist == null || chrg_status == null){
			logger.error("EvCharginginfo parameter error. deviceCtn({}) ccssToken({})  membId({})", deviceCtn, ccssToken, membId);
			return ResultCodeUtil.createResultMsg(ResultCodeUtil.RC_3C004000, api, "parameter is null");
		}
		
		//EV validate Start!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
		if (-3>Integer.valueOf(battery_capa) || 100<Integer.valueOf(battery_capa)) {
			logger.error("EvCharginginfo battery_capa error. deviceCtn({}) ccssToken({})  membId({}) battery_capa({})", deviceCtn, ccssToken, membId, battery_capa);
			return ResultCodeUtil.createResultMsg(ResultCodeUtil.RC_3C004000, api, "battery_capa error");
		}
		
		if (!drive_able_dist1.replace(".", "").chars().allMatch(x -> Character.isDigit(x))) {
			logger.error("EvCharginginfo drive_able_dist1 error. deviceCtn({}) ccssToken({})  membId({}) drive_able_dist1({})", deviceCtn, ccssToken, membId, drive_able_dist1);
			return ResultCodeUtil.createResultMsg(ResultCodeUtil.RC_3C004000, api, "drive_able_dist1 error");
		}
		
		if (!drive_able_dist2.replace(".", "").chars().allMatch(x -> Character.isDigit(x))) {
			logger.error("EvCharginginfo drive_able_dist2 error. deviceCtn({}) ccssToken({})  membId({}) drive_able_dist2({})", deviceCtn, ccssToken, membId, drive_able_dist2);
			return ResultCodeUtil.createResultMsg(ResultCodeUtil.RC_3C004000, api, "drive_able_dist2 error");
		}
		
		if (expect_chrg_tm1.length() == 4) {
			if (!expect_chrg_tm1.chars().allMatch(x -> Character.isDigit(x))) {
				logger.error("EvCharginginfo expect_chrg_tm1 error. deviceCtn({}) ccssToken({})  membId({}) expect_chrg_tm1({})", deviceCtn, ccssToken, membId, expect_chrg_tm1);
				return ResultCodeUtil.createResultMsg(ResultCodeUtil.RC_3C004000, api, "expect_chrg_tm1 error");
			}
		} else {
			logger.error("EvCharginginfo expect_chrg_tm1 error. deviceCtn({}) ccssToken({})  membId({}) expect_chrg_tm1({})", deviceCtn, ccssToken, membId, expect_chrg_tm1);
			return ResultCodeUtil.createResultMsg(ResultCodeUtil.RC_3C004000, api, "expect_chrg_tm1 error");
		}
		
		if (expect_chrg_tm2.length() == 4) {
			if (!expect_chrg_tm2.chars().allMatch(x -> Character.isDigit(x))) {
				logger.error("EvCharginginfo expect_chrg_tm2 error. deviceCtn({}) ccssToken({})  membId({}) expect_chrg_tm2({})", deviceCtn, ccssToken, membId, expect_chrg_tm2);
				return ResultCodeUtil.createResultMsg(ResultCodeUtil.RC_3C004000, api, "expect_chrg_tm2 error");
			}
		} else {
			logger.error("EvCharginginfo expect_chrg_tm2 error. deviceCtn({}) ccssToken({})  membId({}) expect_chrg_tm2({})", deviceCtn, ccssToken, membId, expect_chrg_tm2);
			return ResultCodeUtil.createResultMsg(ResultCodeUtil.RC_3C004000, api, "expect_chrg_tm2 error");
		}		
		
		if (expect_chrg_tm3.length() == 4) {
			if (!expect_chrg_tm3.chars().allMatch(x -> Character.isDigit(x))) {
				logger.error("EvCharginginfo expect_chrg_tm3 error. deviceCtn({}) ccssToken({})  membId({}) expect_chrg_tm3({})", deviceCtn, ccssToken, membId, expect_chrg_tm3);
				return ResultCodeUtil.createResultMsg(ResultCodeUtil.RC_3C004000, api, "expect_chrg_tm3 error");
			}
		} else {
			logger.error("EvCharginginfo expect_chrg_tm3 error. deviceCtn({}) ccssToken({})  membId({}) expect_chrg_tm3({})", deviceCtn, ccssToken, membId, expect_chrg_tm3);
			return ResultCodeUtil.createResultMsg(ResultCodeUtil.RC_3C004000, api, "expect_chrg_tm3 error");
		}
		
		if (car_use_dt.length() == 8) {
			if (!car_use_dt.chars().allMatch(x -> Character.isDigit(x))) {
				logger.error("EvCharginginfo car_use_dt error. deviceCtn({}) ccssToken({})  membId({}) car_use_dt({})", deviceCtn, ccssToken, membId, car_use_dt);
				return ResultCodeUtil.createResultMsg(ResultCodeUtil.RC_3C004000, api, "car_use_dt error");
			}
		} else {
			logger.error("EvCharginginfo car_use_dt error. deviceCtn({}) ccssToken({})  membId({}) car_use_dt({})", deviceCtn, ccssToken, membId, car_use_dt);
			return ResultCodeUtil.createResultMsg(ResultCodeUtil.RC_3C004000, api, "car_use_dt error");
		}
		
		if (!total_drive_dist.replace(".", "").chars().allMatch(x -> Character.isDigit(x))) {
			logger.error("EvCharginginfo total_drive_dist error. deviceCtn({}) ccssToken({})  membId({}) total_drive_dist({})", deviceCtn, ccssToken, membId, total_drive_dist);
			return ResultCodeUtil.createResultMsg(ResultCodeUtil.RC_3C004000, api, "total_drive_dist error");
		}
		
		if(!(chrg_status.equals("0") || chrg_status.equals("1"))) {
			logger.error("EvCharginginfo chrg_status error. deviceCtn({}) ccssToken({})  membId({}) chrg_status({})", deviceCtn, ccssToken, membId, chrg_status);
			return ResultCodeUtil.createResultMsg(ResultCodeUtil.RC_3C004000, api, "chrg_status error");
		}
		
		if(!(full_chrg_alarm.equals("1") || full_chrg_alarm.equals("0"))) {
			logger.error("EvCharginginfo full_chrg_alarm error. deviceCtn({}) ccssToken({})  membId({}) full_chrg_alarm({})", deviceCtn, ccssToken, membId, full_chrg_alarm);
			return ResultCodeUtil.createResultMsg(ResultCodeUtil.RC_3C004000, api, "full_chrg_alarm error");
		}
		//EV validate End!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!

		EvCharginginfoVO evCharginginfoVO = new EvCharginginfoVO();
		
		evCharginginfoVO.setCtn(MDC.get(MDCConst.COMMON_CTN));
		evCharginginfoVO.setBattery_capa(battery_capa);
		evCharginginfoVO.setDrive_able_dist1(drive_able_dist1);
		evCharginginfoVO.setDrive_able_dist2(drive_able_dist2);
		evCharginginfoVO.setExpect_chrg_tm1(expect_chrg_tm1);
		evCharginginfoVO.setExpect_chrg_tm2(expect_chrg_tm2);
		evCharginginfoVO.setExpect_chrg_tm3(expect_chrg_tm3);
		evCharginginfoVO.setCar_use_dt(car_use_dt);
		evCharginginfoVO.setTotal_drive_dist(total_drive_dist);
		evCharginginfoVO.setChrg_status(chrg_status);
		evCharginginfoVO.setFull_chrg_alarm(full_chrg_alarm);
		
		if (evChargingDao.updateEvCharginginfo(evCharginginfoVO) > 0) {
			logger.debug("Success evCharginginfo. deviceCtn({}) ccssToken({})  membId({})", deviceCtn, ccssToken, membId);
		} else {
			logger.error("Not Exist Update EvCharging Info. deviceCtn({}) ccssToken({})  membId({})", deviceCtn, ccssToken, membId);
			return ResultCodeUtil.createResultMsg(ResultCodeUtil.RC_4C005001, api);
		}

		return ResultCodeUtil.createResultMsg(ResultCodeUtil.RC_2S000000, api);
	}
}
