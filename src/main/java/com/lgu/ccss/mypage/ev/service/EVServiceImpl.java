package com.lgu.ccss.mypage.ev.service;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.http.HttpHeaders;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.lgu.ccss.common.dao.MemberDao;
import com.lgu.ccss.common.dao.mgr.MgrAppDeviceDao;
import com.lgu.ccss.common.dao.mgr.MgrAppSessionDao;
import com.lgu.ccss.common.model.MembVO;
import com.lgu.ccss.common.model.mgr.MgrAppDeviceVO;
import com.lgu.ccss.common.model.mgr.MgrAppSessVO;
import com.lgu.ccss.mypage.ev.dao.EVDao;
import com.lgu.ccss.mypage.ev.model.EVVO;

@Service("eVService")
public class EVServiceImpl implements EVService {

	private static final Logger logger = LoggerFactory.getLogger(EVServiceImpl.class);


	@Autowired
	private EVDao eVDao;
	
	@Autowired
	private MgrAppSessionDao mgrAppSessionDao;

	@Autowired
	private MemberDao memberDao;
	
	@Autowired
	private MgrAppDeviceDao mgrAppDeviceDao;	

	private Pattern VALID_USER_AGENT_REGEX = Pattern.compile("\\[(.*)\\,(.*)\\]", Pattern.CASE_INSENSITIVE);
	
	private MembVO validationByAll(HttpServletRequest httpServletRequest) {
		String userAgent = httpServletRequest.getHeader(HttpHeaders.USER_AGENT);
		logger.info("userAgent({})", userAgent);

		String mgrAppCtn = "";
		String sessionId = "";
		//String uuid = "";
		Matcher matcher = VALID_USER_AGENT_REGEX.matcher(userAgent);
		if (matcher.find()) {
			
			String[] arr = StringUtils.splitByWholeSeparator(matcher.group(1), ",");
			
			if(arr.length == 2) {
				mgrAppCtn = arr[0];
				sessionId = arr[1];
			}else {
				mgrAppCtn = matcher.group(1);
				sessionId = matcher.group(2);
				//uuid = matcher.group(3);
			}				
			
		}		
		
		if (StringUtils.isEmpty(mgrAppCtn)) { 
			logger.info("mgrAppCtn({}) is empty",mgrAppCtn);
			return null; 
		}
		 
		/*if (StringUtils.isEmpty(sessionId)) {
			logger.info("sessionId({}) is empty", sessionId);
			return null;
		}
		
		if(StringUtils.isEmpty(uuid)) {
			logger.info("uuid({}) is empty", uuid);
			return null;
		}*/
		
		logger.info("mgrAppCtn({}) sessionId({})", mgrAppCtn, sessionId);
		// 아이폰의 경우 백그라운드에서 포그라운로 변경시 신규로그인으로 sessionId가 없을때가 있어 생략
		/*
		MgrAppSessVO mgrAppSess = mgrAppSessionDao.selectMgrSess(sessionId);
		if (mgrAppSess == null) {
			logger.error("failed to select mgrAppSess data. mgrAppCtn({}) sessionId({})", mgrAppCtn, sessionId);
			return null;
		}
		*/
		
		MgrAppDeviceVO mgrAppDevice = new MgrAppDeviceVO();
		//mgrAppDevice.setMgrappId(mgrAppSess.getMgrappId());
		// mgrAppCtn, mgrAppId 동일하여 mgrAppCtn 으로 대치 
		mgrAppDevice.setMgrappId(mgrAppCtn);
		mgrAppDevice = mgrAppDeviceDao.selectMgrMainDeviceInfo(mgrAppDevice);
		if (mgrAppDevice == null) {
			logger.error("failed to select mgrAppDevice data. mgrAppCtn({}) sessionId({})", mgrAppCtn,
					sessionId);
			return null;
		}

		MembVO memb = memberDao.selectMemberByID(mgrAppDevice.getMembId());
		if (memb == null) {
			logger.error("failed to select member data. mgrAppCtn({}) sessionId({})", mgrAppCtn,
					sessionId);
			return null;
		}

		return memb;
	}	


	/**
	 * 전기차 충전정보
	 */
	@Override
	public String selectEVChrgInfo(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
			Model model) {
		try {
			MembVO memb = validationByAll(httpServletRequest);

			if (memb == null) {
				logger.debug("{}", "(memb == null)");

				return "phone/errorPage";
			}
			
			EVVO evVO = new EVVO();
			evVO.setDeviceCtn(memb.getMembCtn());
									
			EVVO ev = eVDao.selectEVChrgInfo(evVO);
			
			model.addAttribute("ev",ev);
			
			// 차량 전원이 OFF일 경우
			if("-1".equals(ev.getBatteryCapa()) || "-2".equals(ev.getBatteryCapa()) || "-3".equals(ev.getBatteryCapa()) ) {
				return "phone/evOffInfo";	
			}else {
				return "phone/evChrgInfo";	
			}
			
		} catch (Exception e) {
			logger.error("{}", e.getMessage());

			e.printStackTrace();
		}
		
		return "phone/errorPage";
	}
	
	
}
