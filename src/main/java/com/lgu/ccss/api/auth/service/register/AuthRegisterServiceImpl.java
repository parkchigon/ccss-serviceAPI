package com.lgu.ccss.api.auth.service.register;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lgu.ccss.api.auth.model.ResultAuthRegisterJSON;
import com.lgu.ccss.common.constant.CCSSConst;
import com.lgu.ccss.common.dao.DeviceDao;
import com.lgu.ccss.common.dao.MemberDao;
import com.lgu.ccss.common.dao.ModelDao;
import com.lgu.ccss.common.dao.TermsDao;
import com.lgu.ccss.common.model.ConnDeviceVO;
import com.lgu.ccss.common.model.DeviceModelVO;
import com.lgu.ccss.common.model.MembAgrVO;
import com.lgu.ccss.common.model.MembCommAgrVO;
import com.lgu.ccss.common.model.MembData;
import com.lgu.ccss.common.model.MembVO;
import com.lgu.ccss.common.model.RequestJSON;
import com.lgu.ccss.common.model.ResponseJSON;
import com.lgu.ccss.common.model.TermsVO;
import com.lgu.ccss.common.tlo.TloData;
import com.lgu.ccss.common.tlo.TloUtil;
import com.lgu.ccss.common.util.CCSSUtil;
import com.lgu.ccss.common.util.MembManager;
import com.lgu.ccss.common.util.ResultCodeUtil;
import com.lgu.ccss.common.validation.CheckResultData;
import com.lgu.ccss.common.validation.ValidationChecker;
import com.lgu.common.model.ResultCode;
import com.lgu.common.ncas.SubsInfo;
import com.lgu.common.util.DateUtils;
import com.lgu.common.util.ExceptionUtil;
import com.lgu.common.util.keygenerator.KeyGenerator;

@Service("authRegisterService")
public class AuthRegisterServiceImpl implements AuthRegisterService {

	private static final Logger logger = LoggerFactory.getLogger(AuthRegisterServiceImpl.class);
	private static final List<String> mandatoryList = new ArrayList<String>(Arrays.asList(RequestJSON.PARAM_CTN,
			RequestJSON.PARAM_SERIAL, RequestJSON.PARAM_ESN, RequestJSON.PARAM_USIM_MODEL, RequestJSON.PARAM_USIM_SN,
			RequestJSON.PARAM_FIRMWARE_INFO, RequestJSON.PARAM_TERMS_NO, RequestJSON.PARAM_IS_AGREE));

	@Value("#{systemProperties.SERVER_ID}")
	private String serverId;

	@Value("#{config['member.agree.validMonth']}")
	private String memberAgreeValidMonth;

	@Autowired
	private DeviceDao deviceDao;

	@Autowired
	private MemberDao memberDao;

	@Autowired
	private TermsDao termsDao;

	@Autowired
	private ModelDao modelDao;
	
	@Autowired
	private MembManager membManager;

	@Override
	@Transactional
	public ResponseJSON doService(HttpHeaders headers, RequestJSON reqJson) {
		String api = reqJson.getCommon().getApiId();
		String deviceCtn = CCSSUtil.getCtn();

		ResponseJSON response = null;

		CheckResultData result = checkValidation(reqJson);
		if (result.isStatus() == false) {
			response = ResultCodeUtil.createResultMsg(ResultCodeUtil.RC_3C004000, api, result.getReason());
			return response;
		}

		try {
			response = registService(reqJson);

		} catch (Exception e) {
			logger.error("deviceCtn({}) Exception({})", deviceCtn, ExceptionUtil.getPrintStackTrace(e));
			response = ResultCodeUtil.createResultMsg(ResultCodeUtil.RC_4C005000, api);
		}

		return response;
	}

	private CheckResultData checkValidation(RequestJSON reqJson) {
		CheckResultData result = ValidationChecker.checkCommon(reqJson.getCommon());
		if (result.isStatus() == false) {
			return result;
		}

		result = ValidationChecker.checkValue(reqJson.getCommon().getApiId(), "common.apiId");
		if (result.isStatus() == false) {
			return result;
		}

		if (!CCSSConst.API_ID_AUTH_REGISTER.equals(reqJson.getCommon().getApiId())) {
			result = new CheckResultData();
			result.setStatus(false);
			result.setReason("API_ID unmatched");
			return result;
		}
		
		for (String key : mandatoryList) {
			result = ValidationChecker.checkParamValue((String) reqJson.getParam().get(key), "param." + key);
			if (result.isStatus() == false) {
				return result;
			}
		}
		
		return result;
	}

	private ResponseJSON registService(RequestJSON reqJson) throws IOException, ParseException {
		String api = reqJson.getCommon().getApiId();
		String deviceCtn = CCSSUtil.getCtn();
		String deviceSerial = CCSSUtil.getSerial();

		DeviceModelVO model = modelDao.selectDeviceModelByName(reqJson.getCommon().getLogData().getDevModel(),
				reqJson.getCommon().getDevice().getDeviceType());
		if (model == null) {
			return ResultCodeUtil.createResultMsg(ResultCodeUtil.RC_3C004002, api);
		}
		
		// check agree status
		String isAgree = (String) reqJson.getParam().get(RequestJSON.PARAM_IS_AGREE);
		if (!CCSSConst.DEF_YES.equals(isAgree)) {
			return ResultCodeUtil.createResultMsg(ResultCodeUtil.RC_3C002001, api);
		}

		// get member data
		MembData membData = new MembData();
		ResultCode resultCode = membManager.getNcasSubsInfo(deviceCtn, membData);
		if (!ResultCodeUtil.RC_2S000000.equals(resultCode)) {
			return ResultCodeUtil.createResultMsg(resultCode, api);
		}
		
		SubsInfo subsInfo = membData.getSubsInfo();
		
		// select member table
		MembVO alreadyMemb = memberDao.selectMemberForActiveStatus(deviceCtn);
		if (alreadyMemb != null) {
			logger.error("already exist Subscriber. deviceCtn({}) UICCID({} | {})", deviceCtn, deviceSerial,
					subsInfo.getUsim_iccid_no());
			return ResultCodeUtil.createResultMsg(ResultCodeUtil.RC_3C002003, api);
		}

		// check UICCID
		if (!deviceSerial.equals(subsInfo.getUsim_iccid_no())) {
			logger.error("failed to check UICCID. deviceCtn({}) UICCID({} | {})", deviceCtn, deviceSerial,
					subsInfo.getUsim_iccid_no());
			return ResultCodeUtil.createResultMsg(ResultCodeUtil.RC_3C003002, api);
		}

		// check data terms status
		List<MembCommAgrVO> membCommAgrs = termsDao.selectMembCommAgr(deviceSerial);
		if (membCommAgrs.size() == 0) {
			logger.error("failed to get MemberCommAgr data. deviceCtn({}) UICCID({})", deviceCtn, deviceSerial);
			return ResultCodeUtil.createResultMsg(ResultCodeUtil.RC_3C002002, api);
		}

		MembCommAgrVO membCommAgr = membCommAgrs.get(membCommAgrs.size() - 1);
		
		if (logger.isDebugEnabled()) {
			logger.debug("MEMBER_COMM_AGR({})", membCommAgr);
		}

		// check terms
		String termsNo = (String) reqJson.getParam().get(RequestJSON.PARAM_TERMS_NO);
		List<String> termsNoList = Arrays.asList(termsNo.split(","));
		for (String no : termsNoList) {
			TermsVO terms = termsDao.selectTerms(no);
			if (terms == null) {
				logger.error("failed to get Terms data. deviceCtn({}) termsNo({})", deviceCtn, no);
				return ResultCodeUtil.createResultMsg(ResultCodeUtil.RC_3C002000, api);
			}
		}

		// create member info
		MembVO memb = makeMemberInfo(reqJson, deviceCtn, subsInfo);
		if (memb == null) {
			logger.error("failed to make Member data. deviceCtn({}) UICCID({})", deviceCtn, deviceSerial);
			return ResultCodeUtil.createResultMsg(ResultCodeUtil.RC_4C005000, api);
		}

		if (logger.isDebugEnabled()) {
			logger.debug("MEMBER({})", memb.toString());
		}

		if (memberDao.insertMember(memb) == 0) {
			logger.error("failed to insert Member data. deviceCtn({}) UICCID({})", deviceCtn, deviceSerial);
			return ResultCodeUtil.createResultMsg(ResultCodeUtil.RC_4C005001, api);
		}

		setTloData(memb);

		// create device info
		ConnDeviceVO connDevice = makeDeviceInfo(reqJson, memb.getMembId());

		if (logger.isDebugEnabled()) {
			logger.debug("CONN_DEVICE({})", connDevice.toString());
		}

		if (deviceDao.insertConnDevice(connDevice) == 0) {
			logger.error("failed to insert ConnDevice data. deviceCtn({}) UICCID({})", deviceCtn, deviceSerial);
			return ResultCodeUtil.createResultMsg(ResultCodeUtil.RC_4C005001, api);
		}

		// create agree info
		MembAgrVO membAgr = makeMembAgrInfo(reqJson, memb, membCommAgr.getAgrYn());

		if (logger.isDebugEnabled()) {
			logger.debug("MEMBER_AGR({})", membAgr);
		}

		if (termsDao.insertMembAgr(membAgr) == 0) {
			logger.error("failed to insert MemberAgr data. deviceCtn({}) UICCID({})", deviceCtn, deviceSerial);
			return ResultCodeUtil.createResultMsg(ResultCodeUtil.RC_4C005001, api);
		}

		ResultAuthRegisterJSON resultData = new ResultAuthRegisterJSON();
		resultData.setOpenStatus(ResultAuthRegisterJSON.OPEN_STATUS_NORMAL);

		return ResultCodeUtil.createResultMsg(ResultCodeUtil.RC_2S000000, resultData, api);
	}

	private MembVO makeMemberInfo(RequestJSON reqJson, String deviceCtn, SubsInfo subsInfo) throws ParseException {
		MembVO memb = new MembVO();

		String membId = null;
		try {
			membId = KeyGenerator.createCommonShardKey(Integer.parseInt(serverId));
		} catch (Exception e) {
			logger.error("deviceCtn({}) Exception({})", deviceCtn, ExceptionUtil.getPrintStackTrace(e));
		}

		if (membId == null) {
			return null;
		}

		memb.setMembId(membId);
		memb.setMembNo(subsInfo.getSub_no());
		memb.setMembCtn(deviceCtn);
		memb.setUseStatus(MembVO.USE_STATUS_JOIN);
		memb.setUseYn(MembVO.USE_Y);
		memb.setNewRejoinYn(MembVO.NEW_REJOIN_N);
		memb.setProductNm(subsInfo.getFee_type());

		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Date joinDate = format.parse(subsInfo.getRegDate());
		memb.setJoinDt(joinDate);

		memb.setServerId(serverId);
		memb.setPayResvYn(MembVO.PAY_RESV_N);
		memb.setMarketType(MembVO.MARKET_AM);
		memb.setExpSmsSendYn(MembVO.EXP_SMS_SEND_N);
		memb.setLastExpSmsSendYn(MembVO.LAST_EXP_SMS_SEND_N);

		setMembPrivateInfo(subsInfo, memb);
		
		return memb;
	}
	
	private void setMembPrivateInfo(SubsInfo subsInfo, MembVO memb) {		
		String birthYear = null;
		String sex = null;
		
		// set birthYear
		birthYear = subsInfo.getReal_birth_pers_id();
		if (birthYear == null || birthYear.length() == 0) {
			birthYear = subsInfo.getSub_birth_pers_id();
		}
		
		if (birthYear == null || birthYear.length() == 0) {
			birthYear = "N";
		
		} else {
			birthYear = birthYear.substring(0, 2);
		}
		
		// set sex
		sex = subsInfo.getReal_sex_pers_id();
		if (sex == null || sex.length() == 0) {
			sex = subsInfo.getSub_sex_pers_id();
		}
		
		if (sex == null || sex.length() == 0) {
			sex = "N";
		} 
		
		memb.setBirthYear(birthYear);
		memb.setGender(sex);
	}

	private ConnDeviceVO makeDeviceInfo(RequestJSON reqJson, String membId) {
		ConnDeviceVO connDevice = new ConnDeviceVO();

		connDevice.setConnDeviceId(ConnDeviceVO.makeConnDeviceId(membId));
		connDevice.setMembId(membId);

		connDevice.setVehicleModelId(ConnDeviceVO.VEHICLE_MODEL_AM);
		connDevice.setDeviceModelId(reqJson.getCommon().getLogData().getDevModel());
		connDevice.setDeviceType(reqJson.getCommon().getDevice().getDeviceType());
		connDevice.setUseYn(ConnDeviceVO.USE_Y);
		connDevice.setDeviceCtn(CCSSUtil.getCtn());
		connDevice.setDeviceEsn((String) reqJson.getParam().get(RequestJSON.PARAM_ESN));
		connDevice.setUsimModel((String) reqJson.getParam().get(RequestJSON.PARAM_USIM_MODEL));
		connDevice.setUsimSn((String) reqJson.getParam().get(RequestJSON.PARAM_USIM_SN));
		connDevice.setDeviceLoginDt(DateUtils.getBasicCurrentTime());

		connDevice.setDevicePushClientId("");
		connDevice.setDevicePushConnStatus("");
		connDevice.setDevicePushConnDt(DateUtils.getBasicCurrentTime());

		connDevice.setFirmwareInfo((String) reqJson.getParam().get(RequestJSON.PARAM_FIRMWARE_INFO));
		connDevice.setJsonSetInfo("");
		connDevice.setUiccId(CCSSUtil.getSerial());
		
		connDevice.setCarNum(getOptionalParam(reqJson, RequestJSON.PARAM_CAR_NUM));

		return connDevice;
	}

	private MembAgrVO makeMembAgrInfo(RequestJSON reqJson, MembVO memb, String commAgrYn) {

		MembAgrVO membAgr = new MembAgrVO();

		membAgr.setMembId(memb.getMembId());
		membAgr.setAgrYn((String) reqJson.getParam().get(RequestJSON.PARAM_IS_AGREE));
		membAgr.setCommAgrYn(commAgrYn);
		membAgr.setTermsNo((String) reqJson.getParam().get(RequestJSON.PARAM_TERMS_NO));
		membAgr.setTermsAuthNo("");
		membAgr.setReAgrYn(MembAgrVO.AGR_N);
		membAgr.setValidStartDt(DateUtils.getBasicCurrentTime());
		membAgr.setValidEndDt(DateUtils.getCurrentDate(Calendar.MONTH, Integer.parseInt(memberAgreeValidMonth),
				DateUtils.DATE_FORMAT_YMDHMS));

		return membAgr;
	}

	private void setTloData(MembVO memb) {
		if (memb == null) {
			return;
		}

		Map<String, String> tlo = new HashMap<String, String>();
		tlo.put(TloData.MEMB_ID, memb.getMembId());
		tlo.put(TloData.MEMB_NO, memb.getMembNo());
		TloUtil.setTloData(tlo);
	}
	
	private String getOptionalParam(RequestJSON reqJson, String param) {
		String str = (String) reqJson.getParam().get(param);
		if (str == null || str.length() == 0) {
			return "NULL";
		}
		
		return str;
	}
}