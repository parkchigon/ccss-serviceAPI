package com.lgu.ccss.api.send2car.service.targetList;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;

import com.lgu.ccss.api.send2car.model.ResultTargetListJson;
import com.lgu.ccss.api.send2car.model.TargetData;
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
import com.lgu.common.util.DateUtils;

@Service("targetListService")
public class TargetListServiceImpl implements TargetListService {

	private static final Logger logger = LoggerFactory.getLogger(TargetListServiceImpl.class);

	@Autowired
	Send2CarDao send2CarDao;

	@Autowired
	MemberDao memberDao;

	@Override
	public ResponseJSON doService(HttpHeaders headers, RequestJSON reqJson) {
		String api = reqJson.getCommon().getApiId();
		String deviceCtn = CCSSUtil.getCtn();

		ResponseJSON response = null;

		CheckResultData result = ValidationChecker.checkValidation(null, reqJson, CCSSConst.API_ID_SEND2CAR_TARGET_LIST,
				null);
		if (result.isStatus() == false) {
			response = ResultCodeUtil.createResultMsg(ResultCodeUtil.RC_3C004000, api, result.getReason());
			return response;
		}

		try {
			response = getTargetList(reqJson);

		} catch (Exception e) {
			logger.error("deviceCtn({}) Exception({})", deviceCtn, e);
			response = ResultCodeUtil.createResultMsg(ResultCodeUtil.RC_4C005000, api);
		}

		return response;
	}

	ResponseJSON getTargetList(RequestJSON reqJson) {
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
		
		System.out.println("########################################");
		System.out.println("membId:"+membId);
		System.out.println("########################################");
		List<Send2CarVO> send2CarList = send2CarDao.selectTargetList(send2CarVO);
		
		System.out.println("########################################");
		System.out.println("send2CarList Size:"+send2CarList.size());
		System.out.println("########################################");
		
		List<TargetData> targetList = new ArrayList<TargetData>();
		
		int seq = 0;
		for (Send2CarVO vo : send2CarList) {
			System.out.println("vo:"+vo.toString());
			TargetData targetData = new TargetData();
			targetData.setSeq(String.valueOf(seq));
			targetData.setSend2CarId(vo.getSendToCarId());
			targetData.setName(vo.getTargetNm());
			targetData.setAddress(vo.getTargetAddress());
			
			targetData.setPoiid(vo.getPoiId());
			targetData.setRoadname(vo.getRoadName());
			targetData.setRoadjibun(vo.getRoadJibun());
			
			String laty = vo.getTargetLaty();
			String lonx = vo.getTargetLonx();
			String transToken = memb.getTransToken();
			
			//real laty && real lonx add
			String rlaty = vo.getTargetRealLaty();
			String rlonx = vo.getTargetRealLonx();
			
			
			try {
				
				if (laty != null && laty.length() > 0) {
					String decLaty = AES256Util.AESDecode(CCSSConst.LOCATION_ENC_KEY, laty);
					targetData.setLatitude(AES256Util.AESEncode(transToken, decLaty));
				}
				
				if (lonx != null && lonx.length() > 0) {
					String decLonx = AES256Util.AESDecode(CCSSConst.LOCATION_ENC_KEY, lonx);
					targetData.setLongtitude(AES256Util.AESEncode(transToken, decLonx));
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
			
			String today = DateUtils.getCurrentTime("yyyy-MM-dd");
			String hopeTime = vo.getArrivHopeTime();
			if (hopeTime != null && hopeTime.length() > 0) {
				if (hopeTime.startsWith(today)) {
					targetData.setDate(hopeTime);
					targetList.add(targetData);
					seq++;
				}
			} else {
				targetList.add(targetData);
				seq++;
			}
		}

		ResultTargetListJson result = new ResultTargetListJson();
		result.setTargetList(targetList);
		
		return ResultCodeUtil.createResultMsg(ResultCodeUtil.RC_2S000000, result, api);
	}
}
