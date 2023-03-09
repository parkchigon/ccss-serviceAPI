package com.lgu.ccss.common.dao;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.lgu.ccss.common.model.VoiceVerVO;
import com.lgu.ccss.common.model.VoiceDomainVO;
import com.lgu.ccss.common.model.VoiceGuideVO;

import devonframe.dataaccess.CommonDao;

@Component
public class VoiceDao {

	@Value("#{config['common.systemId']}")
	private String systemId;	
	
	@Resource(name = "commonDao_oracle")
	private CommonDao commonDao_oracle;
	
	public VoiceVerVO selectVoiceVersion(String marketType) {

		if (marketType == null) {
			throw new IllegalArgumentException();
		}
		VoiceVerVO voiceVer = new VoiceVerVO();
		voiceVer.setMarketType(marketType);

		return commonDao_oracle.select("Voice.selectVoiceVersion", voiceVer);
	}

	public List<VoiceDomainVO> selectVoiceDomainList(String marketType,String voiceVerNo) {

		if (marketType == null || voiceVerNo == null) {
			throw new IllegalArgumentException();
		}
		
		VoiceDomainVO voiceDomain = new VoiceDomainVO();
		voiceDomain.setMarketType(marketType);
		voiceDomain.setVoiceVerNo(voiceVerNo);
		
		return commonDao_oracle.selectList("Voice.selectVoiceDomainList", voiceDomain);
	}
	
	public List<VoiceGuideVO> selectVoiceGuideList(VoiceVerVO voiceVerVo) {
		if (voiceVerVo == null) {
			throw new IllegalArgumentException();
		}

		return commonDao_oracle.selectList("Voice.selectVoiceGuideList", voiceVerVo);
	}
}
