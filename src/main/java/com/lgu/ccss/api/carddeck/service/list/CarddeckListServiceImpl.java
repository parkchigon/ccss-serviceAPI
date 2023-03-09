package com.lgu.ccss.api.carddeck.service.list;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;

import com.lgu.ccss.api.carddeck.model.CarddeckListData;
import com.lgu.ccss.api.carddeck.model.ResultCarddeckListJSON;
import com.lgu.ccss.common.constant.CCSSConst;
import com.lgu.ccss.common.dao.CarddeckDao;
import com.lgu.ccss.common.dao.MemberDao;
import com.lgu.ccss.common.model.CardVO;
import com.lgu.ccss.common.model.MembVO;
import com.lgu.ccss.common.model.RequestJSON;
import com.lgu.ccss.common.model.ResponseJSON;
import com.lgu.ccss.common.util.CCSSUtil;
import com.lgu.ccss.common.util.ResultCodeUtil;
import com.lgu.ccss.common.validation.CheckResultData;
import com.lgu.ccss.common.validation.ValidationChecker;

@Service("carddeckListService")
public class CarddeckListServiceImpl implements CarddeckListService {

	private static final Logger logger = LoggerFactory.getLogger(CarddeckListServiceImpl.class);

	@Autowired
	MemberDao memberDao;
	
	@Autowired
	CarddeckDao carddeckDao;

	@Override
	public ResponseJSON doService(HttpHeaders headers, RequestJSON reqJson) {
		String api = reqJson.getCommon().getApiId();
		String deviceCtn = CCSSUtil.getCtn();

		ResponseJSON response = null;

		CheckResultData result = ValidationChecker.checkValidation(null, reqJson, CCSSConst.API_ID_CARDDECK_LIST,
				null);
		if (result.isStatus() == false) {
			response = ResultCodeUtil.createResultMsg(ResultCodeUtil.RC_3C004000, api, result.getReason());
			return response;
		}

		try {
			response = getCarddeckList(reqJson);

		} catch (Exception e) {
			logger.error("deviceCtn({}) Exception({})", deviceCtn, e);
			response = ResultCodeUtil.createResultMsg(ResultCodeUtil.RC_4C005000, api);
		}

		return response;
	}
	
	private ResponseJSON getCarddeckList(RequestJSON reqJson) {
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
		
		List<CardVO> cardList = carddeckDao.selectCarddeckList(memb.getMarketType());
		List<CarddeckListData> carddeckList = new ArrayList<CarddeckListData>();
		int index = 1;
		for (CardVO cardVO: cardList) {
			CarddeckListData data = new CarddeckListData();
			data.setIndex(String.valueOf(index));
			data.setName(cardVO.getCardNm());
			data.setAppId(cardVO.getAppId());;
			carddeckList.add(data);
			index++;
		}
		
		ResultCarddeckListJSON result = new ResultCarddeckListJSON();
		result.setCarddeckList(carddeckList);
		
		return ResultCodeUtil.createResultMsg(ResultCodeUtil.RC_2S000000, result, api);
	}
}