package com.lgu.ccss.api.pushConfirm;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value = "/push")
public class PushServiceController {

	@Resource(name = "confirmPushService")
	private ConfirmPushService confirmPushService;

	@RequestMapping(value = "/confirm/{type}", method = RequestMethod.POST, consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
	@ResponseBody
	public String requestConfirm(HttpSession session, @RequestHeader HttpHeaders headers,
			@RequestBody MultiValueMap<String, String> requestBody, @PathVariable String type,
			HttpServletResponse response) {

		int status = confirmPushService.doService(headers, requestBody, type);
		response.setStatus(status);

		return null;
	}

}
