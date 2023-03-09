package com.lgu.ccss.api.carddeck.service.notice;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;

import com.lgu.ccss.api.carddeck.model.CarddeckNoticeData;
import com.lgu.ccss.api.carddeck.model.ResultCarddeckNoticeJSON;
import com.lgu.ccss.common.constant.CCSSConst;
import com.lgu.ccss.common.dao.MemberDao;
import com.lgu.ccss.common.dao.NoticeDao;
import com.lgu.ccss.common.dao.code.CodeServiceDao;
import com.lgu.ccss.common.model.MembVO;
import com.lgu.ccss.common.model.NoticeVO;
import com.lgu.ccss.common.model.RequestJSON;
import com.lgu.ccss.common.model.ResponseJSON;
import com.lgu.ccss.common.model.code.CodeVO;
import com.lgu.ccss.common.util.CCSSUtil;
import com.lgu.ccss.common.util.ResultCodeUtil;
import com.lgu.ccss.common.validation.CheckResultData;
import com.lgu.ccss.common.validation.ValidationChecker;

@Service("carddeckNoticeService")
public class CarddeckNoticeServiceImpl implements CarddeckNoticeService {

	private static final Logger logger = LoggerFactory.getLogger(CarddeckNoticeServiceImpl.class);
	// FW_VER 파라미터는 'mandatory'에서 'optional'로 변경
	//private static final List<String> mandatoryList = new ArrayList<String>(
	//		Arrays.asList(RequestJSON.PARAM_FW_VER));
	
	@Autowired
	MemberDao memberDao;
	
	@Autowired
	NoticeDao noticeDao;
	
	@Autowired
	private CodeServiceDao codeServiceDao;	
	
	@Override
	public ResponseJSON doService(HttpHeaders headers, RequestJSON reqJson) {
		String api = reqJson.getCommon().getApiId();
		String deviceCtn = CCSSUtil.getCtn();

		ResponseJSON response = null;

		CheckResultData result = ValidationChecker.checkValidation(null, reqJson, CCSSConst.API_ID_CARDDECK_NOTICE,
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

		String serviceExposure = "";
		
		try {
			//그룹 코드 SERVICE_EXPOSURE 상체 코드  조회
			CodeVO codeVO = new CodeVO();
			codeVO.setGrpCdNm("SERVICE_EXPOSURE");
			List<CodeVO> serviceExpDtlCodeList = codeServiceDao.selectDtlCodeListByGrpCodeNm(codeVO);
			
			for(CodeVO tempVO : serviceExpDtlCodeList){
				String dtlCodeNm = tempVO.getDtlCdNm();
				String cdVal = tempVO.getCdVal();
				
				if(dtlCodeNm.equals("DEVICE")){
					serviceExposure = cdVal;
					break;
				}
			}		
			
			MembVO memb = memberDao.selectMemberByID(membId);
			if (memb == null) {
				logger.error("failed to select member data. deviceCtn({}) ccssToken({}) membId({})", deviceCtn, ccssToken,
						membId);
				return ResultCodeUtil.createResultMsg(ResultCodeUtil.RC_3C002004, api);
			}
			
			String firmVer = (String) reqJson.getParam().get(RequestJSON.PARAM_FW_VER);
			
			// "ALL"일 경우 버전 조건없이 전체 공지를 전달한다.
			if (firmVer!=null && firmVer.equalsIgnoreCase("ALL")) {
				firmVer = null;
			}
	
			List<NoticeVO> noticeList = noticeDao.selectCarddeckNotice(memb.getMarketType(), firmVer, serviceExposure);
	
			if (noticeList.size() > 0) {
				CarddeckNoticeData data = new CarddeckNoticeData();
				data.setFwVer(noticeList.get(0).getFirmVer());
				data.setTitle(noticeList.get(0).getNotiTitle());
				data.setContentType(noticeList.get(0).getContType());
				data.setContent(noticeList.get(0).getNotiCont());
	
				ResultCarddeckNoticeJSON result = new ResultCarddeckNoticeJSON();
				result.setCarddeckNotice(data);
				
				return ResultCodeUtil.createResultMsg(ResultCodeUtil.RC_2S000000, result, api);
				
			} else {
				return ResultCodeUtil.createResultMsg(ResultCodeUtil.RC_9C300000, api);
			}
		}catch (Exception e) {
			return ResultCodeUtil.createResultMsg(ResultCodeUtil.RC_4C005000, api);
		}
	}
}