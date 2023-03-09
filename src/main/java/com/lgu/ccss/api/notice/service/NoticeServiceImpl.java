package com.lgu.ccss.api.notice.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;

import com.lgu.ccss.api.notice.model.NoticeListData;
import com.lgu.ccss.api.notice.model.ResultNoticeListJSON;
import com.lgu.ccss.common.constant.CCSSConst;
import com.lgu.ccss.common.dao.MemberDao;
import com.lgu.ccss.common.dao.NoticeDao;
import com.lgu.ccss.common.model.MembVO;
import com.lgu.ccss.common.model.NoticeVO;
import com.lgu.ccss.common.model.RequestJSON;
import com.lgu.ccss.common.model.ResponseJSON;
import com.lgu.ccss.common.util.CCSSUtil;
import com.lgu.ccss.common.util.ResultCodeUtil;
import com.lgu.ccss.common.validation.CheckResultData;
import com.lgu.ccss.common.validation.ValidationChecker;

@Service("noticeService")
public class NoticeServiceImpl implements NoticeService {
	private static final Logger logger = LoggerFactory.getLogger(NoticeServiceImpl.class);

	@Autowired
	MemberDao memberDao;
	
	@Autowired
	NoticeDao noticeDao;

	@Override
	public ResponseJSON doService(HttpHeaders headers, RequestJSON reqJson) {
		String api = reqJson.getCommon().getApiId();
		String deviceCtn = CCSSUtil.getCtn();

		ResponseJSON response = null;

		CheckResultData result = ValidationChecker.checkValidation(null, reqJson, CCSSConst.API_ID_NOTICE_LIST,
				null);
		if (result.isStatus() == false) {
			response = ResultCodeUtil.createResultMsg(ResultCodeUtil.RC_3C004000, api, result.getReason());
			return response;
		}

		try {
			response = getNoticeList(reqJson);

		} catch (Exception e) {
			logger.error("deviceCtn({}) Exception({})", deviceCtn, e);
			response = ResultCodeUtil.createResultMsg(ResultCodeUtil.RC_4C005000, api);
		}

		return response;
	}
	
	ResponseJSON getNoticeList(RequestJSON reqJson) {
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
		
		NoticeVO noticeVO = new NoticeVO();
		noticeVO.setServiceCategory(memb.getMarketType());
		noticeVO.setServiceExposure(NoticeVO.SVC_EXPO_DEVICE);
		noticeVO.setStartPosition("0");
		noticeVO.setReqCount("20");
		noticeVO.setNotiType(CCSSConst.DEF_NOTICE_NT01);
		List<NoticeVO> noticeList = noticeDao.selectNoticeList(noticeVO);
		if (noticeList.size() == 0) {
			return ResultCodeUtil.createResultMsg(ResultCodeUtil.RC_9C300000, api);
		}
		
		List<NoticeListData> resultList = new ArrayList<NoticeListData>();
		int index = 1;
		for (NoticeVO notice : noticeList) {
			NoticeListData data = new NoticeListData();
			data.setIndex(String.valueOf(index));
			data.setImportance(notice.getNotiImportance());
			data.setTitle(notice.getNotiTitle());
			data.setCategory(notice.getServiceCategory());
			data.setContent(notice.getNotiCont());
			data.setRegDate(notice.getRegDt());
			resultList.add(data);
			
			index++;
		}
		
		ResultNoticeListJSON result = new ResultNoticeListJSON();
		result.setNotice(resultList);
		
		return ResultCodeUtil.createResultMsg(ResultCodeUtil.RC_2S000000, result, api);
	}
}
