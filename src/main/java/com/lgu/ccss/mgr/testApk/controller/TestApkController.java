package com.lgu.ccss.mgr.testApk.controller;


import java.io.File;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value = "/download")
public class TestApkController {
	private static final Logger logger = LoggerFactory.getLogger(TestApkController.class);
	
	
	//@Value("#{systemProperties.spring.profiles.active}")
	//private String springProfilesActive;
	
	private String apkFilePath = "/testApp/";
	
	@RequestMapping(value = "/testApp/{fileName:.+}*", method = RequestMethod.GET)
	public void downLoadFile(@PathVariable String fileName,HttpServletRequest request, HttpServletResponse response) throws IOException {
		logger.info("Test App file Download Path : "+ apkFilePath + " File Name : " + fileName );
		//String fileExtension = fileName.substring(fileName.lastIndexOf(".")+1,fileName.length());
		try{
			File file = new File(apkFilePath + fileName);
			//response.setContentType("image/"+fileExtension);
			FileUtils.copyFile(file, response.getOutputStream());
		}catch(Exception e){
			logger.error("Test App File Download Fail : "+ apkFilePath + " File Name : " + fileName  , e);
		}
	}
}
