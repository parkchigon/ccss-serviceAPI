package com.lgu.common.hang.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
@RequestMapping(value = "/api/internal/hang")
public class HangController {
	@RequestMapping(value = "/check", method = RequestMethod.POST)
	@ResponseBody
	public String checkHang() {
		return "success";
	}
	
}
