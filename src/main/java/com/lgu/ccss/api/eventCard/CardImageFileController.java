package com.lgu.ccss.api.eventCard;

import java.io.File;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value = "/card")
public class CardImageFileController {
	
	private static final Logger logger = LoggerFactory.getLogger(CardImageFileController.class);
	@Value("#{config['upload.eventCardImg.path']}")
	private String uploadEventCardImgPath;
	
	@Value("#{config['upload.noticeCardImg.path']}")
	private String uploadNoticeCardImgPath;
	

	/**
	    * 이벤트 카드 이미지 조회
	    * @param request
	    * @param response
	    * @param bannerImgFileName
	    * @param file
	    * @throws IOException
	    */
	@RequestMapping(value = "/eventCardFileDown/{cardImgFileName:.+}*", method = RequestMethod.GET)
	public void eventCardFileDown(@PathVariable String cardImgFileName,HttpServletRequest request, HttpServletResponse response) throws IOException {
		
		String fileExtension = cardImgFileName.substring(cardImgFileName.lastIndexOf(".")+1,cardImgFileName.length());
		try{
			File file = new File(uploadEventCardImgPath + cardImgFileName);
			
			response.setContentType("image/"+fileExtension);
			FileUtils.copyFile(file, response.getOutputStream());
			
		}catch(Exception e){
			logger.error("eventCardFileDown Fail :" ,e );
		}
	}
	
	
	/**
	    *  카드 공지사항  이미지 조회
	    * @param request
	    * @param response
	    * @param bannerImgFileName
	    * @param file
	    * @throws IOException
	    */
	@RequestMapping(value = "/cardNoticeFileDown/{cardImgFileName:.+}*", method = RequestMethod.GET)
	public void cardNoticeFileDown(@PathVariable String cardImgFileName,HttpServletRequest request, HttpServletResponse response) throws IOException {
		
		String fileExtension = cardImgFileName.substring(cardImgFileName.lastIndexOf(".")+1,cardImgFileName.length());
		try{
			File file = new File(uploadNoticeCardImgPath + cardImgFileName);
			
			response.setContentType("image/"+fileExtension);
			FileUtils.copyFile(file, response.getOutputStream());
			
		}catch(Exception e){
			logger.error("eventCardFileDown Fail :" ,e );
		}
	}
}
