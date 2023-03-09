package com.lgu.ccss.mgr.mypage.service.datausage;



import java.net.InetAddress;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lgu.ccss.common.constant.CCSSConst;
import com.lgu.ccss.common.dao.MemberDao;
import com.lgu.ccss.common.model.MembVO;
import com.lgu.ccss.common.model.RequestJSON;
import com.lgu.ccss.common.model.ResponseJSON;
import com.lgu.ccss.common.tlo.TloData;
import com.lgu.ccss.common.tlo.TloUtil;
import com.lgu.ccss.common.util.CCSSUtil;
import com.lgu.ccss.common.util.ResultCodeUtil;
import com.lgu.ccss.common.validation.CheckResultData;
import com.lgu.ccss.common.validation.ValidationChecker;
import com.lgu.ccss.mgr.mypage.model.ResultDataUsageJSON;
import com.lgu.common.esb.ESBManager;
import com.lgu.common.esb.vo.AmountDataVO;
import com.lgu.common.esb.vo.SubsInfoVO;
import com.lgu.common.model.ResultCode;

@Service("DataUsageService")
public class DataUsageServiceImpl implements DataUsageService{

	private static final Logger logger = LoggerFactory.getLogger(DataUsageServiceImpl.class);
	private static final List<String> mandatoryList = new ArrayList<String>(
			Arrays.asList(RequestJSON.PARAM_MEMB_ID));
	
	@Autowired
	private MemberDao memberDao;
	
	@Autowired
	private ESBManager eSBManager;
	
	private Pattern VALID_NUMBER_REGEX = Pattern.compile("^[0-9]*$", Pattern.CASE_INSENSITIVE);
	
	@Override
	@Transactional
	public ResponseJSON doService(HttpHeaders headers, RequestJSON reqJson) {
		ResultCode resultCode=ResultCodeUtil.RC_2S000000;
		String api = reqJson.getCommon().getApiId();
		String mgrappLoginId = CCSSUtil.getMgrUserLoginId();
		String sessionId = CCSSUtil.getMgrSessionId();
		String mgrappId = CCSSUtil.getMgrappId();
		String membId = (String) reqJson.getParam().get(RequestJSON.PARAM_MEMB_ID);
		
		ResponseJSON response = null;
		
		CheckResultData result = ValidationChecker.checkValidation(headers,reqJson,CCSSConst.MYPAGE_DATA_USAGE,mandatoryList);
		
		if (result.isStatus() == false) {
			
			resultCode = ResultCodeUtil.RC_3C004000;
			response = ResultCodeUtil.createResultMsg(resultCode,api,result.getReason());
			return response;
		
		}else{
			
			setTloData(membId);
			
			try {
				
				MembVO memb = memberDao.selectMemberByID(membId);
				if (memb == null) {
					logger.error("failed to select member data. mgrappId({}) membId({}) sessionId({}) mgrappLoginId({})", mgrappId, membId,
							sessionId, mgrappLoginId);
					resultCode = ResultCodeUtil.RC_3C002004;
					return response = ResultCodeUtil.createResultMsg(resultCode, api, "no member");
				}
				
				SubsInfoVO subsInfo = null;
				try {
					subsInfo = eSBManager.getSubsInfo(memb.getMembNo(), memb.getProductNm());
					if (subsInfo == null) {
						logger.error("failed to query esb CustSvcEntrInfo. mgrappId({}) membId({}) sessionId({}) mgrappLoginId({})", mgrappId, membId,
								sessionId, mgrappLoginId);
						resultCode = ResultCodeUtil.RC_3C002004;
						return response = ResultCodeUtil.createResultMsg(resultCode, api, "no data esb CustSvcEntrInfo");
					}
				} catch (Exception e) {
					logger.error("readTime out esb CustSvcEntrInfo. mgrappId({}) membId({}) sessionId({}) mgrappLoginId({})", mgrappId, membId,
							sessionId, mgrappLoginId);
					resultCode = ResultCodeUtil.RC_3C002008;
					return response = ResultCodeUtil.createResultMsg(resultCode, api, "no data esb");
				}
				InetAddress inet= InetAddress.getLocalHost();
				String ip = inet.getHostAddress();
				String callYm = new SimpleDateFormat("yyyyMMdd").format(new Date());
				AmountDataVO amountData = eSBManager.getAmountData(memb.getMembNo(), memb.getProductNm(), callYm, ip);
				if (amountData == null) {
					logger.error("failed to query esb CallAppSmry. mgrappId({}) membId({}) sessionId({}) mgrappLoginId({})", mgrappId, membId,
							sessionId, mgrappLoginId);
					resultCode = ResultCodeUtil.RC_3C002004;
					return response = ResultCodeUtil.createResultMsg(resultCode, api, "no data esb CallAppSmry");
				}

				String alloValue = "0";
				if (VALID_NUMBER_REGEX.matcher(amountData.getAlloValue()).find()) {
					alloValue = String.valueOf((int) (Float.parseFloat(amountData.getAlloValue()) / 1024.0f));
				}
				
				String useValue = "0";
				if (VALID_NUMBER_REGEX.matcher(amountData.getUseValue()).find()) {
					useValue = String.valueOf((int) (Float.parseFloat(amountData.getUseValue()) / 1024.0f));
				}
				ResultDataUsageJSON resultData = new ResultDataUsageJSON();
				
				resultData.setAlloValue(alloValue);
				resultData.setUseValue(useValue);
				
				response = ResultCodeUtil.createResultMsg(resultCode, resultData, api);
			} catch (Exception e) {
				resultCode = ResultCodeUtil.RC_4C005000;
				logger.error("mgrappId({}) sessionId({}) membId({}) mgrappLoginId({}) Exception({})", mgrappId,sessionId,membId,mgrappLoginId,e);
				response = ResultCodeUtil.createResultMsg(resultCode, api);
			}
			
		}
		
		return response;
	}
	
	private void setTloData(String membId) {
		if (membId == null || membId.length() <=0) {
			return;
		}
		Map<String, String> tlo = new HashMap<String, String>();
		tlo.put(TloData.MEMB_ID, membId);
		TloUtil.setTloData(tlo);
	}

}
