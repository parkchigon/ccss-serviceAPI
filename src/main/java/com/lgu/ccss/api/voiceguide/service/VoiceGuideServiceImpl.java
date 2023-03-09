package com.lgu.ccss.api.voiceguide.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;

import com.lgu.ccss.api.voiceguide.model.ResultVoiceGuideJSON;
import com.lgu.ccss.api.voiceguide.model.VoiceDomainListData;
import com.lgu.ccss.api.voiceguide.model.VoiceGuideListData;
import com.lgu.ccss.common.dao.VoiceDao;
import com.lgu.ccss.common.model.VoiceVerVO;
import com.lgu.ccss.common.model.VoiceDomainVO;
import com.lgu.ccss.common.model.VoiceGuideVO;
import com.lgu.ccss.common.constant.CCSSConst;
import com.lgu.ccss.common.model.RequestJSON;
import com.lgu.ccss.common.model.ResponseJSON;
import com.lgu.ccss.common.util.ResultCodeUtil;
import com.lgu.ccss.common.validation.CheckResultData;
import com.lgu.ccss.common.validation.ValidationChecker;


@Service("voiceGuideService")
public class VoiceGuideServiceImpl implements VoiceGuideService {

	private static final Logger logger = LoggerFactory.getLogger(VoiceGuideServiceImpl.class);
	private static final List<String> mandatoryList = new ArrayList<String>(
			Arrays.asList(RequestJSON.PARAM_VERSION));

	@Autowired
	private VoiceDao voiceDao;

	@Override
	public ResponseJSON doService(HttpHeaders headers, RequestJSON reqJson) {
		String api = reqJson.getCommon().getApiId();
		
		ResponseJSON response = null;
		
		CheckResultData result = ValidationChecker.checkValidation(null,reqJson,CCSSConst.API_ID_VOICEGUIDE,mandatoryList);
		if (result.isStatus() == false) {
			response = ResultCodeUtil.createResultMsg(ResultCodeUtil.RC_3C004000, api, result.getReason());
			return response;
		}

		String deviceVer = (String) reqJson.getParam().get(RequestJSON.PARAM_VERSION);
		
		// "common":{"device":{"deviceType":""}} (PND일 경우 'AM', AVN일 경우 'BM')
		String deviceType = (String) reqJson.getCommon().getDevice().getDeviceType();
		String marketType = null;
		if (deviceType.equalsIgnoreCase(CCSSConst.DEF_PND)) {
			marketType = CCSSConst.DEF_AM;
		} else if (deviceType.equalsIgnoreCase(CCSSConst.DEF_AVN)) {
			marketType = CCSSConst.DEF_BM;
		}
		
		// 요청 market_type이 없을 경우 
		if (marketType == null) {
			return ResultCodeUtil.createResultMsg(ResultCodeUtil.RC_3C004002, api);
		}

		VoiceVerVO voiceVerVo = voiceDao.selectVoiceVersion(marketType);
		
		// 해당 TB_VOICE_VER DB 데이타가 없을 경우 오류 return 
		if (voiceVerVo == null) {
			logger.error("failed to select VoiceVersion data. deviceVer({})", deviceVer);
			return ResultCodeUtil.createResultMsg(ResultCodeUtil.RC_4C005001, api);
		}

		//if (deviceType.equalsIgnoreCase(CCSSConst.DEF_PND)) {
			// 버전이 같을 경우 데이터없이 Response(디바이스앱에 해당 버전의 데이터가 존재하는 가정)
			if (deviceVer.equals(voiceVerVo.getVersion())) {
				logger.info("Requested version is the same as Setup version. Requested version({}) Setup version({})", deviceVer,voiceVerVo.getVersion());
				ResultVoiceGuideJSON guideData = new ResultVoiceGuideJSON();
				return ResultCodeUtil.createResultMsg(ResultCodeUtil.RC_2S000000, guideData, api);
			}
		//}

		List<VoiceDomainVO> voiceDomainList = voiceDao.selectVoiceDomainList(marketType,voiceVerVo.getVoiceVerNo());

		// 해당 TB_VOICE_DOMAIN DB 데이터가 없을 경우 오류 return
		if (voiceDomainList == null) {
			return ResultCodeUtil.createResultMsg(ResultCodeUtil.RC_4C005001, api);
		}

		// JSON "domain" 리스트 생성 
		List<VoiceDomainListData> resultVoiceDomainList = new ArrayList<VoiceDomainListData>();
		if(voiceDomainList !=null && voiceDomainList.size() > 0){
			for(VoiceDomainVO tempVO : voiceDomainList){
				VoiceDomainListData voiceDomainListData = new VoiceDomainListData();
				
				voiceDomainListData.setName(tempVO.getName());
				voiceDomainListData.setExposureRatio(tempVO.getExposureRatio());
				resultVoiceDomainList.add(voiceDomainListData);
			}
		}
		
		List<VoiceGuideVO> voiceGuideList = voiceDao.selectVoiceGuideList(voiceVerVo);
		
		// 해당 TB_VOICE_GUIDE DB 데이터가 없을 경우 오류 return
		if (voiceGuideList == null) {
			return ResultCodeUtil.createResultMsg(ResultCodeUtil.RC_4C005001, api);
		}
		List<VoiceGuideListData> resultVoiceGuideList = new ArrayList<VoiceGuideListData>();
		if(voiceGuideList !=null && voiceGuideList.size() > 0){
			for(VoiceGuideVO tempVO : voiceGuideList){
				VoiceGuideListData voiceGuideListData = new VoiceGuideListData();
				
				voiceGuideListData.setDomain(tempVO.getDomain());
				voiceGuideListData.setCommand(tempVO.getCommand());
				voiceGuideListData.setPriority(tempVO.getPriority());
				voiceGuideListData.setLevel(tempVO.getLevel());
				resultVoiceGuideList.add(voiceGuideListData);
			}
		}
		
		
		ResultVoiceGuideJSON guideData = new ResultVoiceGuideJSON();
		guideData.setVersion(voiceVerVo.getVersion());
		guideData.setDomain(resultVoiceDomainList);
		guideData.setGuide(resultVoiceGuideList);

		return ResultCodeUtil.createResultMsg(ResultCodeUtil.RC_2S000000, guideData, api);
	}

}