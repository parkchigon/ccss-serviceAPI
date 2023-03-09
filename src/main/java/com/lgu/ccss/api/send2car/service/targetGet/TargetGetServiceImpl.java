package com.lgu.ccss.api.send2car.service.targetGet;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;

import com.lgu.ccss.api.send2car.model.ResultTargetJson;
import com.lgu.ccss.api.send2car.model.TargetData;
import com.lgu.ccss.api.send2car.service.targetSet.TargetSetServiceImpl;
import com.lgu.ccss.common.constant.CCSSConst;
import com.lgu.ccss.common.dao.MemberDao;
import com.lgu.ccss.common.dao.Send2CarDao;
import com.lgu.ccss.common.model.MembVO;
import com.lgu.ccss.common.model.RequestJSON;
import com.lgu.ccss.common.model.ResponseJSON;
import com.lgu.ccss.common.model.Send2CarVO;
import com.lgu.ccss.common.util.CCSSUtil;
import com.lgu.ccss.common.util.ResultCodeUtil;
import com.lgu.ccss.common.validation.CheckResultData;
import com.lgu.ccss.common.validation.ValidationChecker;
import com.lgu.common.util.AES256Util;

@Service("targetGetService")
public class TargetGetServiceImpl implements TargetGetService {
	private static final Logger logger = LoggerFactory.getLogger(TargetSetServiceImpl.class);
	
	private static final List<String> mandatoryList = new ArrayList<String>(Arrays.asList(RequestJSON.PARAM_SEND2CAR_ID));
	
	@Autowired
	Send2CarDao send2CarDao;
	
	@Autowired
	MemberDao memberDao;
	
	@Override
	public ResponseJSON doService(HttpHeaders headers, RequestJSON reqJson) {
		String api = reqJson.getCommon().getApiId();
		String deviceCtn = CCSSUtil.getCtn();

		ResponseJSON response = null;

		CheckResultData result = ValidationChecker.checkValidation(null, reqJson, CCSSConst.API_ID_SEND2CAR_TARGET_GET, mandatoryList);
		if (result.isStatus() == false) {
			response = ResultCodeUtil.createResultMsg(ResultCodeUtil.RC_3C004000, api, result.getReason());
			return response;
		}

		try {
			response = getTarget(reqJson);

		} catch (Exception e) {
			logger.error("deviceCtn({}) Exception({})", deviceCtn, e);
			response = ResultCodeUtil.createResultMsg(ResultCodeUtil.RC_4C005000, api);
		}

		return response;
	}
	
	ResponseJSON getTarget(RequestJSON reqJson) {
		String api = reqJson.getCommon().getApiId();
		String deviceCtn = CCSSUtil.getCtn();
		String ccssToken = CCSSUtil.getCcssToken();
		String membId = CCSSUtil.getMembId();
		
		MembVO memb = memberDao.selectMemberByID(membId);
		if (memb == null) {
			logger.error("failed to select member data. deviceCtn({}) ccssToken({}) membId({})", deviceCtn,
					ccssToken, membId);
			return ResultCodeUtil.createResultMsg(ResultCodeUtil.RC_3C002004, api);
		}
		
		Send2CarVO send2CarVO = new Send2CarVO();
		send2CarVO.setMembId(membId);
		send2CarVO.setSendToCarId((String) reqJson.getParam().get(RequestJSON.PARAM_SEND2CAR_ID));
		
		List<Send2CarVO> send2CarList = send2CarDao.selectTargetList(send2CarVO);
		
		TargetData targetData = new TargetData();
		if (send2CarList.size() > 0) {
			targetData.setSeq("0");
			targetData.setSend2CarId(send2CarList.get(0).getSendToCarId());
			targetData.setName(send2CarList.get(0).getTargetNm());
			targetData.setAddress(send2CarList.get(0).getTargetAddress());
			
			targetData.setPoiid(send2CarList.get(0).getPoiId());
			targetData.setRoadname(send2CarList.get(0).getRoadName());
			targetData.setRoadjibun(send2CarList.get(0).getRoadJibun());
			
			String laty = send2CarList.get(0).getTargetLaty();
			String lonx = send2CarList.get(0).getTargetLonx();
			String transToken = memb.getTransToken();
			
			//real laty && real lonx add
			String rlaty = send2CarList.get(0).getTargetRealLaty();
			String rlonx = send2CarList.get(0).getTargetRealLonx();
			
			try {
				if (laty != null && laty.length() > 0) {
					String realLaty = AES256Util.AESDecode(CCSSConst.LOCATION_ENC_KEY, laty);
					targetData.setLatitude(AES256Util.AESEncode(transToken, realLaty));
				}
				
				if (lonx != null && lonx.length() > 0) {
					String realLonx = AES256Util.AESDecode(CCSSConst.LOCATION_ENC_KEY, lonx);
					targetData.setLongtitude(AES256Util.AESEncode(transToken, realLonx));
				}
				
				if (rlaty != null && rlaty.length() > 0) {
					String decRealLaty = AES256Util.AESDecode(CCSSConst.LOCATION_ENC_KEY, rlaty);
					targetData.setRlatitude(AES256Util.AESEncode(transToken, decRealLaty));
				}
				
				if (rlonx != null && rlonx.length() > 0) {
					String decRealLonx = AES256Util.AESDecode(CCSSConst.LOCATION_ENC_KEY, rlonx);
					targetData.setRlongtitude(AES256Util.AESEncode(transToken, decRealLonx));
				}
			
			} catch (Exception e) {
				logger.error("deviceCtn({}) laty({}) lonx({}) transToken({}) Exception({})", deviceCtn, laty, lonx, transToken, e);
				return ResultCodeUtil.createResultMsg(ResultCodeUtil.RC_3C004005, api);
			}
		}
		
		ResultTargetJson result = new ResultTargetJson();
		result.setTarget(targetData);
		
		return ResultCodeUtil.createResultMsg(ResultCodeUtil.RC_2S000000, result, api);
	}
}
