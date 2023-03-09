package com.lgu.ccss.mgr.management.service.notice;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;

import com.lgu.ccss.common.constant.CCSSConst;
import com.lgu.ccss.common.dao.NoticeDao;
import com.lgu.ccss.common.dao.code.CodeServiceDao;
import com.lgu.ccss.common.model.NoticeVO;
import com.lgu.ccss.common.model.RequestJSON;
import com.lgu.ccss.common.model.ResponseJSON;
import com.lgu.ccss.common.model.code.CodeVO;
import com.lgu.ccss.common.util.CCSSUtil;
import com.lgu.ccss.common.util.ResultCodeUtil;
import com.lgu.ccss.common.validation.CheckResultData;
import com.lgu.ccss.common.validation.ValidationChecker;
import com.lgu.ccss.mgr.management.model.notice.ResultNoticeSearchJSON;
import com.lgu.ccss.mgr.management.model.notice.ResultNoticeSearchVO;
import com.lgu.common.model.ResultCode;

@Service("noticeSearchService")
public class NoticeSearchServiceImpl implements NoticeSearchService{

	private static final Logger logger = LoggerFactory.getLogger(NoticeSearchServiceImpl.class);
	private static final List<String> mandatoryList = new ArrayList<String>(
			Arrays.asList(RequestJSON.PARAM_START_POSITION,RequestJSON.PARAM_REQ_COUNT  
					,RequestJSON.PARAM_SERVICE_CATEGORY,RequestJSON.PARAM_SERVICE_EXPOSURE));

	@Autowired
	private NoticeDao noticeDao;
	
	@Autowired
	private CodeServiceDao codeServiceDao;
	//TO-DO
	@Override
	public ResponseJSON doService(HttpHeaders headers, RequestJSON reqJson) {
		ResultCode resultCode=ResultCodeUtil.RC_2S000000;
		String api = reqJson.getCommon().getApiId();
		String mgrappLoginId = CCSSUtil.getMgrUserLoginId();
		String sessionId = CCSSUtil.getMgrSessionId();
		String mgrappId = CCSSUtil.getMgrappId();
		
		ResponseJSON response = null;
		ResultNoticeSearchJSON resultNoticeSearchJSON =null;
		
		CheckResultData result = ValidationChecker.checkValidation(headers,reqJson,CCSSConst.MANAGEMENT_NOTICE_LIST,mandatoryList);
		
		if (result.isStatus() == false) {
			resultCode = ResultCodeUtil.RC_3C004000;
			response = ResultCodeUtil.createResultMsg(resultCode,api,result.getReason());
			return response;
		}else{
			
			try {
				String serviceExposure = "";
						
				//그룹 코드 SERVICE_EXPOSURE 상체 코드  조회
				CodeVO codeVO = new CodeVO();
				codeVO.setGrpCdNm("SERVICE_EXPOSURE");
				List<CodeVO> serviceExpDtlCodeList = codeServiceDao.selectDtlCodeListByGrpCodeNm(codeVO);
				
				for(CodeVO tempVO : serviceExpDtlCodeList){
					String dtlCodeNm = tempVO.getDtlCdNm();
					String cdVal = tempVO.getCdVal();
					
					if(dtlCodeNm.equals("APP")){
						serviceExposure = cdVal;
						break;
					}
				}
				
				NoticeVO noticeVO  = new NoticeVO();
				//String serviceCategory = (String) reqJson.getParam().get(RequestJSON.PARAM_SEVVICE_CATEGORY);
				/*if(!serviceCategory.equals(CCSSConst.DEF_ALL)){
					noticeVO.setServiceCategory(serviceCategory);
				}*/
				noticeVO.setServiceCategory((String) reqJson.getParam().get(RequestJSON.PARAM_SERVICE_CATEGORY));
				noticeVO.setStartPosition((String) reqJson.getParam().get(RequestJSON.PARAM_START_POSITION));
				noticeVO.setReqCount((String) reqJson.getParam().get(RequestJSON.PARAM_REQ_COUNT));
				noticeVO.setServiceExposure(serviceExposure);
				List<NoticeVO>  noticeList = noticeDao.selectNoticeList(noticeVO);
				
				if(noticeList !=null && noticeList.size() > 0){
					
					resultNoticeSearchJSON = new ResultNoticeSearchJSON();
					List<ResultNoticeSearchVO> resultNoticeList = new LinkedList<ResultNoticeSearchVO>();
					for(NoticeVO tempVO : noticeList ){
						ResultNoticeSearchVO resultNoticeSearchVO = new ResultNoticeSearchVO();
						resultNoticeSearchVO.setIndex(tempVO.getIndex());
						resultNoticeSearchVO.setNotiId(tempVO.getNotiId());
						resultNoticeSearchVO.setNotiTitle(tempVO.getNotiTitle());
						resultNoticeSearchVO.setNotiCont(tempVO.getNotiCont());
						resultNoticeSearchVO.setRegDt(tempVO.getRegDt());
						resultNoticeSearchVO.setNotiImportance(tempVO.getNotiImportance());
						resultNoticeSearchVO.setServiceCategory(tempVO.getServiceCategory());
						resultNoticeList.add(resultNoticeSearchVO);
					}
					
					resultNoticeSearchJSON.setNoticeList(resultNoticeList);
					resultNoticeSearchJSON.setTotalCount(noticeList.get(0).getTotalCount());
					
				}else{
					resultCode=ResultCodeUtil.RC_9C300000;
					logger.debug("Not Exist Notice List sessionId({}) mgrappId({})  mgrappLoginId({})",sessionId,mgrappId,mgrappLoginId);
				}
				
			}catch (Exception e) {
				resultCode = ResultCodeUtil.RC_4C005000;
				logger.error("sessionId({}) mgrappLoginId({}) Exception({})", sessionId,mgrappLoginId,e);
			}
		}
		
		response = ResultCodeUtil.createResultMsg(resultCode,resultNoticeSearchJSON,api);
		return response;
	}
}
