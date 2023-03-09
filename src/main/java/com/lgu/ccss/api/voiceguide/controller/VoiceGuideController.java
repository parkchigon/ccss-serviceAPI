package com.lgu.ccss.api.voiceguide.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lgu.ccss.api.voiceguide.service.VoiceGuideService;
import com.lgu.ccss.common.model.RequestJSON;
import com.lgu.ccss.common.model.ResponseJSON;

@Controller
public class VoiceGuideController {
	
	
	@Resource(name = "voiceGuideService") 
	private VoiceGuideService voiceGuideService;
	
	@RequestMapping(value= {"/api/voiceguide", "/bmapi/voiceguide"}, method = RequestMethod.POST)
	@ResponseBody
	public ResponseJSON reqVoiceGuide(HttpSession session, @RequestHeader HttpHeaders headers,
			@RequestBody RequestJSON reqJson) throws Exception {
		ResponseJSON response = null;
		
		response = voiceGuideService.doService(headers, reqJson);

		return response;
	}
	
}
