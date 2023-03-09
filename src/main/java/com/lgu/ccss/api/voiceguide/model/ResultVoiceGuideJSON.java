package com.lgu.ccss.api.voiceguide.model;

import java.util.List;
import com.lgu.ccss.api.voiceguide.model.VoiceDomainListData;
import com.lgu.ccss.api.voiceguide.model.VoiceGuideListData;

public class ResultVoiceGuideJSON {
	private String version;
	private List<VoiceDomainListData> domain;
	private List<VoiceGuideListData> guide;
	
	public String getVersion() {
		return version;
	}
	public void setVersion(String version) {
		this.version = version;
	}
	public List<VoiceDomainListData> getDomain() {
		return domain;
	}
	public void setDomain(List<VoiceDomainListData> domain) {
		this.domain = domain;
	}
	public List<VoiceGuideListData> getGuide() {
		return guide;
	}
	public void setGuide(List<VoiceGuideListData> guide) {
		this.guide = guide;
	}
	
	@Override
	public String toString() {
		return "ResultVoiceGuideJSON [version=" + version + ", domain=" + domain + ", guide=" + guide + "]";
	}
}
