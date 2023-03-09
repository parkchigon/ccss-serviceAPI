package com.lgu.ccss.api.pushConfirm;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import javax.annotation.PostConstruct;

import org.apache.http.HttpStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMap;

import com.lgu.ccss.common.dao.DevicePushSessDao;
import com.lgu.ccss.common.model.DevicePushSessVO;
import com.lgu.ccss.common.model.MembVO;

@Service("confirmPushService")
public class ConfirmPushServiceImpl implements ConfirmPushService {
	
	private static final String FORM_USERNAME = "username";
	private static final String FORM_PASSWORD = "password";
	private static final String FORM_TOPIC = "topic";
	private static final String FORM_ACC = "acc";
	
	private static final Logger logger = LoggerFactory.getLogger(ConfirmPushServiceImpl.class);
	
	private static List<String> publicTopics;
	
	@Value("#{config['mqtt.superUser.sendId']}")
	private String superUserSendId;
	
	@Value("#{config['mqtt.superUser.sendPw']}")
	private String superUserSendPw;
	
	@Value("#{config['mqtt.superUser.reportId']}")
	private String superUserReportId;
	
	@Value("#{config['mqtt.superUser.reportPw']}")
	private String superUserReportPw;
	
	@Value("#{config['mqtt.superUser.statusId']}")
	private String superUserStatusId;
	
	@Value("#{config['mqtt.superUser.statusPw']}")
	private String superUserStatusPw;
	
	@Value("#{config['push.topic.sub.message']}")
	private String pushTopicSubMessage;
	
	@Value("#{config['push.topic.sub.notice']}")
	private String pushTopicSubNotice;
	
	@Value("#{config['push.topic.pub.status']}")
	private String pushTopicPubStatus;
	
	@Value("#{config['push.topic.pub.report']}")
	private String pushTopicPubReport;
	
	@Autowired
	private DevicePushSessDao devicePushSessDao;
	
	@PostConstruct
	public void init() {
		setAllowTopic();
	}
	
	public int doService(HttpHeaders headers, MultiValueMap<String, String> requestBody, String type) {
		String username = requestBody.getFirst(FORM_USERNAME);
		String password = requestBody.getFirst(FORM_PASSWORD);
		String topic = requestBody.getFirst(FORM_TOPIC);
		String acc = requestBody.getFirst(FORM_ACC);
		
		boolean isValid = false;
		try {
			if ("user".equals(type)) {
				isValid = confirmUser(username, password);
			}
			else if ("super".equals(type)) {
				isValid = confirmSuper(username, password);
			}
			else if ("acl".equals(type)) {
				isValid = confirmACL(username, password, topic, acc);	
			}
		
			if (isValid == false && type.equals("super")==false) {
				logger.error("invalid push client. request({})", requestBody);
			}
			
		} catch (Exception e) {
			logger.error("username({}) Exception({})", username, e);
		}
		
		return isValid == true ? HttpStatus.SC_OK : HttpStatus.SC_FORBIDDEN;
	}
	
	public boolean confirmUser(String username, String password) {
		if (username == null || username.length() == 0) {
			return false;
		}
		
		if (password == null || password.length() == 0) {
			return false;
		}
				
		DevicePushSessVO devicePushSess = devicePushSessDao.selectDevicePushSess(password);
		if (devicePushSess == null) {			
			if (confirmSuper(username, password)) {
				return true;
			}
			
			return false;
		}
		
		if (username.equals(devicePushSess.getDeviceCtn())) {
			return true;
		}
				
		return false;
	}
	
	public boolean confirmSuper(String username, String password) {
		if (username == null || username.length() == 0) {
			return false;
		}
		
		if (password == null || password.length() == 0) {
			return false;
		}
		
		if (username.equals(superUserSendId) && password.equals(superUserSendPw)) {
			return true;
		}
		
		if (username.equals(superUserReportId) && password.equals(superUserReportPw)) {
			return true;
		}
		
		if (username.equals(superUserStatusId) && password.equals(superUserStatusPw)) {
			return true;
		}
		
		return false;
	}
	
	public boolean confirmSuper(String username) {
		if (username == null || username.length() == 0) {
			return false;
		}
		
		if (username.equals(superUserSendId)) {
			return true;
		}
		
		if (username.equals(superUserReportId)) {
			return true;
		}
		
		if (username.equals(superUserStatusId)) {
			return true;
		}
		
		return false;
	}
	
	public boolean confirmACL(String username, String password, String topic, String acc) {
		if (username == null || username.length() == 0) {
			return false;
		}
				
		if (topic == null || topic.length() == 0) {
			return false;
		}
		
		if (acc == null || acc.length() == 0) {
			return false;
		}
		
		if (confirmSuper(username)) {
			return true;
		}

		if (isAllowTopic(username, topic)) {
			return true;
		}

		return false;
	}
	
	public void setAllowTopic() {
		String amType = MembVO.MARKET_AM.toLowerCase();
		String bmType = MembVO.MARKET_BM.toLowerCase();
		
		List<String> list = new ArrayList<String>();
		list.add(pushTopicSubMessage);
		list.add(pushTopicSubNotice);
		list.add(pushTopicPubStatus);
		list.add(pushTopicPubReport);
		
		List<String> topicList = new ArrayList<String>();
		for (String str : list) {
			topicList.add(str.replace("{type}", amType));
			topicList.add(str.replace("{type}", bmType));
		}
		
		HashSet<String> distinctTopic = new HashSet<String>(topicList);
		publicTopics = new ArrayList<String>(distinctTopic);
		
		logger.debug("[ALLOW_TOPICS] " + publicTopics);
	}
	
	public boolean isAllowTopic(String username, String topic) {

		for (String str : publicTopics) {
			if (str.replace("{ctn}", username).equals(topic)) {
				return true;
			}
		}
		
		return false;
	}
}
