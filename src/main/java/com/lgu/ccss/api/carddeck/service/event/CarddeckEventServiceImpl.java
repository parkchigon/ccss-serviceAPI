package com.lgu.ccss.api.carddeck.service.event;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;

import com.lgu.ccss.api.carddeck.model.CarddeckEventData;
import com.lgu.ccss.api.carddeck.model.ResultCarddeckEventJSON;
import com.lgu.ccss.common.constant.CCSSConst;
import com.lgu.ccss.common.dao.CarddeckDao;
import com.lgu.ccss.common.dao.MemberDao;
import com.lgu.ccss.common.model.EventCardVO;
import com.lgu.ccss.common.model.MembVO;
import com.lgu.ccss.common.model.RequestJSON;
import com.lgu.ccss.common.model.ResponseJSON;
import com.lgu.ccss.common.util.CCSSUtil;
import com.lgu.ccss.common.util.ResultCodeUtil;
import com.lgu.ccss.common.validation.CheckResultData;
import com.lgu.ccss.common.validation.ValidationChecker;

@Service("carddeckEventService")
public class CarddeckEventServiceImpl implements CarddeckEventService {

	private static final Logger logger = LoggerFactory.getLogger(CarddeckEventServiceImpl.class);

	@Autowired
	MemberDao memberDao;
	
	@Autowired
	CarddeckDao carddeckDao;

	@Override
	public ResponseJSON doService(HttpHeaders headers, RequestJSON reqJson) {
		String api = reqJson.getCommon().getApiId();
		String deviceCtn = CCSSUtil.getCtn();

		ResponseJSON response = null;

		CheckResultData result = ValidationChecker.checkValidation(null, reqJson, CCSSConst.API_ID_CARDDECK_EVENT,
				null);
		if (result.isStatus() == false) {
			response = ResultCodeUtil.createResultMsg(ResultCodeUtil.RC_3C004000, api, result.getReason());
			return response;
		}

		try {
			response = getCarddeckNotice(reqJson);

		} catch (Exception e) {
			logger.error("deviceCtn({}) Exception({})", deviceCtn, e);
			response = ResultCodeUtil.createResultMsg(ResultCodeUtil.RC_4C005000, api);
		}

		return response;
	}

	private ResponseJSON getCarddeckNotice(RequestJSON reqJson) {
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
		
		List<EventCardVO> eventCardList = carddeckDao.selectCarddeckEvent("AM");
		List<CarddeckEventData> carddeckEvent = new ArrayList<CarddeckEventData>();
		int index = 1;
		for (EventCardVO eventCardVO: eventCardList) {
			CarddeckEventData data = new CarddeckEventData();
			data.setIndex(String.valueOf(index));
			data.setIndex(eventCardVO.getEventCardId());
			data.setContentType(CarddeckEventData.CONTENT_TYPE_URL);
			data.setContent(eventCardVO.getEventCardUrl());
			data.setStartDt(eventCardVO.getExposureStartDt());
			data.setEndDt(eventCardVO.getExposureEndDt());
			data.setAppId(eventCardVO.getAppId());
			data.setCardType(eventCardVO.getCardType());
			carddeckEvent.add(data);
			index++;
		}
		
		ResultCarddeckEventJSON result = new ResultCarddeckEventJSON();
		result.setCarddeckEvent(carddeckEvent);
		
		return ResultCodeUtil.createResultMsg(ResultCodeUtil.RC_2S000000, result, api);
	}
}