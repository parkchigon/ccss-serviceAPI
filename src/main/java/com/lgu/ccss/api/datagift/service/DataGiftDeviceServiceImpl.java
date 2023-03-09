package com.lgu.ccss.api.datagift.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;

import com.lgu.ccss.api.datagift.model.ResultData;
import com.lgu.ccss.common.constant.CCSSConst;
import com.lgu.ccss.common.dao.DeviceDao;
import com.lgu.ccss.common.dao.DeviceSessDao;
import com.lgu.ccss.common.dao.MemberDao;
import com.lgu.ccss.common.model.ConnDeviceVO;
import com.lgu.ccss.common.model.DeviceSessVO;
import com.lgu.ccss.common.model.MembVO;
import com.lgu.ccss.common.model.RequestJSON;
import com.lgu.ccss.common.model.ResponseJSON;
import com.lgu.ccss.common.util.ResultCodeUtil;
import com.lgu.ccss.common.validation.CheckResultData;
import com.lgu.ccss.common.validation.ValidationChecker;
import com.lgu.common.datagift.DataGiftManager;
import com.lgu.common.datagift.domain.DataGiftCtnCheck;
import com.lgu.common.datagift.domain.DataGiftOwnList;
import com.lgu.common.datagift.util.AES256DataGiftUtil;

@Service("dataGiftDeviceService")
public class DataGiftDeviceServiceImpl implements DataGiftDeviceService {

	private static final Logger logger = LoggerFactory
			.getLogger(DataGiftDeviceServiceImpl.class);
	
	@Autowired
	private DeviceSessDao deviceSessDao;
	
	@Autowired
	private DeviceDao deviceDao;
	
	@Autowired
	private MemberDao memberDao;
	
	@Autowired
	private DataGiftManager dataGiftManager;
	
	// 고객정보 암호화 KEY
	@Value("#{config['data.gift.encryption.key']}")
	private String dataGiftEncryptionKey;
	

	private static final List<String> mandatoryList = new ArrayList<String>(
			//Arrays.asList(RequestJSON.PARAM_CTN, RequestJSON.PARAM_REG_TYPE, RequestJSON.PARAM_START_DATE, RequestJSON.PARAM_END_DATE));
			Arrays.asList(RequestJSON.PARAM_CTN, RequestJSON.PARAM_REG_TYPE));  // 2018-10-16, start_date, end_date를 필수파라메터에서 제거.
	
	/**
	 * validate the request.
	 * 
	 * @param reqJson
	 * @return
	 */
	private CheckResultData validateRequestMessage(HttpHeaders headers, RequestJSON reqJson) {
		
		// check header.ccssToken.
		CheckResultData result = ValidationChecker.checkValue(headers.getFirst("ccssToken"), "header.ccssToken");
		if (result.isStatus() == false) {
			return result;
		}
		
		// check common.device & common.logDate.
		result = ValidationChecker.checkCommon(reqJson.getCommon());
		if (result.isStatus() == false) {
			return result;
		}

		// check common.apiId.
		result = ValidationChecker.checkValue(reqJson.getCommon().getApiId(), "common.apiId");
		if (result.isStatus() == false) {
			return result;
		}

		// check common.apiId value.
		if (!CCSSConst.API_ID_DATAGIFTOWN_LIST.equals(reqJson.getCommon().getApiId())) {
			result = new CheckResultData();
			result.setStatus(false);
			result.setReason("API_ID unmatched");
			return result;
		}

		// check 
		for (String key : mandatoryList) {
			result = ValidationChecker.checkParamValue((String) reqJson.getParam().get(key), "param." + key);
			if (result.isStatus() == false) {
				return result;
			}
		}

		return result;
	}
	
	/**
	 * ccssToken 정보를 사용하여 device session정보 
	 * > conn device 정보 
	 * > memb 정보를 
	 * 순차적으로 조회
	 * 
	 * @param headers
	 * @param reqJson
	 * @return
	 */
	private MembVO checkAvnSession(HttpHeaders headers, RequestJSON reqJson) {

		String ccssToken = headers.getFirst("ccssToken");
		String avnCtn = (String) reqJson.getParam().get(RequestJSON.PARAM_CTN);

		logger.info("avnCtn({}) ccssToken({})", avnCtn, ccssToken);

		DeviceSessVO deviceSess = this.deviceSessDao.selectDeviceSess(ccssToken);
		if (deviceSess == null) {
			logger.error(
					"failed to select deviceSess data. ccssToken({})", ccssToken);
			return null;
		}
		
		ConnDeviceVO connDeviceReq = new ConnDeviceVO();
		ConnDeviceVO connDeviceResp = null;
		connDeviceReq.setConnDeviceId(deviceSess.getConnDeviceId());
		connDeviceReq.setMembId(deviceSess.getMembId());
		
		connDeviceResp = this.deviceDao.getDeviceInfo(connDeviceReq);
		if (connDeviceResp == null) {
			logger.error(
					"failed to select connDevice data. ccssToken({}) deviceId({}) membId({})",
						ccssToken, connDeviceReq.getConnDeviceId(), connDeviceReq.getMembId());
			return null;
		}

		MembVO memb = memberDao.selectMemberByID(connDeviceReq.getMembId());
		if (memb == null) {
			logger.error(
					"failed to select member data. ccssToken({}) deviceId({}) membId({})",
						ccssToken, connDeviceReq.getConnDeviceId(), connDeviceReq.getMembId());
			return null;
		}

		return memb;
	}
	

	@Override
	public ResponseJSON getDatagiftOwnList(HttpHeaders headers, RequestJSON reqJson) {
		
		String api = null;		// (REST) API구분자
		String avnCtn = null;
		String regType = null;
		String startDate = null;
		String endDate = null;
		
		try {
			api = reqJson.getCommon().getApiId();
			
			// 1. check the request parameter.
			CheckResultData check = validateRequestMessage(headers, reqJson);
			
			if (check.isStatus() == false) {
				return ResultCodeUtil.createResultMsg(ResultCodeUtil.RC_3C004000, api, check.getReason());	// 파라미터 오류
			}
			
			// 2. check the session, device, member info!
			MembVO memb = checkAvnSession(headers, reqJson);  // Session check 방식을 AVN에 맞게 변경함!
			if (memb == null) {
				logger.error("{}", "(memb == null)");
	
				return ResultCodeUtil.createResultMsg(ResultCodeUtil.RC_3C003004, api, check.getReason());	// 유효하지 않은 CCSSToken
			}
			
			avnCtn = (String) reqJson.getParam().get(RequestJSON.PARAM_CTN);
			regType = (String) reqJson.getParam().get(RequestJSON.PARAM_REG_TYPE);
			startDate = (String) reqJson.getParam().get(RequestJSON.PARAM_START_DATE);
			endDate = (String) reqJson.getParam().get(RequestJSON.PARAM_END_DATE);
			
			//String encrypt_avnCtn = GiftApiAES256.encrypt(avnCtn, dataGiftEncryptionKey);
			String encrypt_avnCtn = AES256DataGiftUtil.AESEncode(this.dataGiftEncryptionKey, avnCtn);
			String encode_avnCtn = new String(Base64.encodeBase64(encrypt_avnCtn.getBytes()));

			// 3. 고객 CTN 인증.
			DataGiftCtnCheck dataGiftCtnCheck = dataGiftManager.getDataGiftCtnCheck(encode_avnCtn);
			if (!DataGiftManager.SUCCESS.equals(dataGiftCtnCheck.getResultCode())) {
				logger.debug("failed to gataGiftCtnCheck - {}, {}",
						dataGiftCtnCheck.getResultCode(),
						dataGiftCtnCheck.getResultMessage());
				return ResultCodeUtil.createResultMsg(ResultCodeUtil.RC_5R000001, api, dataGiftCtnCheck.getResultMessage());  // "데이타상품권 CTN인증 오류"
			}
			
			// 4. 상품권 리스트(이력) 조회.
			DataGiftOwnList dataGiftOwnList = dataGiftManager.getDataGiftOwnList(encode_avnCtn, regType, startDate, endDate);
			if (!DataGiftManager.SUCCESS.equals(dataGiftOwnList.getResultCode())) {
				logger.error("failed to getDataGiftOwnList - {}, {}",
						dataGiftOwnList.getResultCode(),
						dataGiftOwnList.getResultMessage());
	
				return ResultCodeUtil.createResultMsg(ResultCodeUtil.RC_5R000002, api, dataGiftOwnList.getResultMessage());	// 데이타상품권 리스트 조회(이력) 실패
			}

			ResultData resultData = new ResultData(dataGiftOwnList);
			ResponseJSON response = ResultCodeUtil.createResultMsg(ResultCodeUtil.RC_2S000000, resultData, api);
			return response;
			
		} catch (Exception e) {
			logger.error("failed to getDataGiftOwnList - {}", e.getMessage());
			return ResultCodeUtil.createResultMsg(ResultCodeUtil.RC_5R000001, api, e.getMessage());	// 데이타상품권 리스트 조회(이력) 실패
		}
	}

}
