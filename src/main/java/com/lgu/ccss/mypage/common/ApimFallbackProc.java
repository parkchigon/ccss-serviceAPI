package com.lgu.ccss.mypage.common;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class ApimFallbackProc implements InitializingBean {
	
	private static final Logger logger = LoggerFactory.getLogger(ApimFallbackProc.class);
	
	private static String apimUrl;
	private static long subUrlConnTime = -1;
	private static int mainUrlFailCnt = 0;

	
	@Value("#{config['apimgw.public.url']}")
	private String apimPublicUrl;
	
	@Value("#{config['apimgw.private.url']}")
	private String apimPrivateUrl;

	@Value("#{config['apimgw.threshold.fail.count']}")
	private String failCnt;				
	
	@Value("#{config['apimgw.persist.minute']}")
	private String persistTime;			
	
	@Override
	public void afterPropertiesSet() throws Exception {
		
		if (apimPublicUrl == null) {
			throw new Exception("No ['apimgw.public.url'] info in config.properties");
		}
		if (apimPrivateUrl == null) {
			throw new Exception("No ['apimgw.private.url'] info in config.properties");
		}
		if (failCnt == null) {
			throw new Exception("No ['apimgw.threshold.fail.count'] info in config.properties");
		}
		if (persistTime == null) {
			throw new Exception("No ['apimgw.persist.minute'] info in config.properties");
		}
		
		if (!apimPrivateUrl.trim().equals("")) {
			apimUrl = apimPrivateUrl;
		} else {
			apimUrl = apimPublicUrl;
		}
		
		logger.info("[apimPublicUrl: " + apimPublicUrl + "] [apimPrivateUrl: " + apimPrivateUrl + "] "
				+ "[failCnt: " + failCnt + "] [persistTime: " + persistTime + "]");
		
	}

	/*
	 * APIM G/W도메인 Get()
	 */
	public String getApimgwUrl() {
		
		logger.info("getApimgwUrl() -START- APIM Gateway URL 호출");
		
		/*
		System.out.println("##apimPublicUrl:" + apimPublicUrl);
		System.out.println("##apimPrivateUrl:" + apimPrivateUrl);
		System.out.println("##failCnt:" + failCnt);
		System.out.println("##persistTime:" + persistTime);
		System.out.println("@@mainUrlFailCnt:" + mainUrlFailCnt);
		
		if (subUrlConnTime != -1) {
			Date subConnDate = new Date(subUrlConnTime*1000L); 
		    SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 
		    //sdf.setTimeZone(java.util.TimeZone.getTimeZone("GMT+9")); 
		    String formattedDate = sdf.format(subConnDate);
		    System.out.println("@@subUrlConnTime:" + formattedDate);
		}
		*/
		
		// 현재 클라이언트에 전달하는 APIM G/W의 URL이 Public인지 Private인지에 따라
		/*if (apimUrl.equals(apimPublicUrl.trim())) 
		{
			// Public G/W도메인의 연결 실패가 임계치를 넘었을 경우 Private G/W도메인으로 재설정
			if (mainUrlFailCnt > Integer.parseInt(failCnt))
			{
				apimUrl = apimPrivateUrl;
				subUrlConnTime = System.currentTimeMillis() / 1000;
				mainUrlFailCnt = 0;
				
				logger.info("Public G/W 실패카운트 임계치초과 -> Private G/W URL로 변경 ({})", apimUrl);
			}
		}*/
		if (apimUrl.equals(apimPrivateUrl.trim())) 
		{
			// Private G/W도메인의 연결 실패가 임계치를 넘었을 경우 Public G/W도메인으로 재설정
			if (mainUrlFailCnt >= Integer.parseInt(failCnt))
			{
				apimUrl = apimPublicUrl;
				subUrlConnTime = System.currentTimeMillis() / 1000;
				mainUrlFailCnt = 0;
				
				logger.info("Private G/W 실패카운트 임계치초과 -> Public G/W URL로 변경 ({})", apimUrl);
			}
		}
		else
		{
			
			Date subDate = new Date(subUrlConnTime*1000L); 
			Date now = new Date();
			SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 
			
			/*
			System.out.println("subDate.getTime():"+sdf.format(subDate));
			System.out.println("now.getTime():"+sdf.format(now));
			System.out.println("diff:"+ ((now.getTime()-subDate.getTime()) / 60000));
			*/

			// Public G/W도메인 연결한 시간이 임계치를 넘었을 경우, 다시 Private G/W도메인으로 재연결시도
			if (((now.getTime()-subDate.getTime()) / 60000) >= Integer.parseInt(persistTime)) 
			{
				apimUrl = apimPrivateUrl;
				mainUrlFailCnt = 0;
				subUrlConnTime = -1;
				
				logger.info("Public 최초연결시간 : {}", sdf.format(subDate));
				logger.info("Public G/W 가용시간초과 -> Private G/W URL로 변경 ({})", apimUrl);
			}
		}
		
		logger.info("연결 APIM URL : {}", apimUrl);
		logger.info("getApimgwUrl() -END- APIM Gateway URL 호출");
		
		return apimUrl;
	}
	
	/*
	 * APIM G/W Private도메인 호출실패 카운트 추가
	 */
	public void setFailCount() {
		
		logger.info("setFailCount() -START- APIM URL timeout 실패카운트추가");
		
		// 현 도메인이 Private URL이면 실패카운트 추가
		if (apimUrl.equals(apimPrivateUrl.trim()))
		{
			mainUrlFailCnt++;
			logger.info("실패카운트 개수 : {}", mainUrlFailCnt);
		}
		else
		{
			mainUrlFailCnt = 0;
		}
		
		logger.info("setFailCount() -END- APIM URL timeout 실패카운트추가");
	}
	
	/*
	 * APIM G/W 연결시 성공한 URL 전달
	 */
	public void setSuccessUrl(String apimurl) {
		
		logger.info("setSuccessUrl() -START- APIM URL 성공호출");
		
		if (apimurl.trim().equals(apimPrivateUrl.trim())) {
			// APIM Private URL이 성공했을 경우 실패카운트를 초기화한다.
			mainUrlFailCnt = 0;
		}
		
		logger.info("setSuccessUrl() -END- APIM URL 성공호출");
	}
	
	/*
	 * 클라이언트에서 APIM호출시, Timeout이 발생하면 대체 도메인을 호출
	 */
	public String getApimgwSubUrl() {
		
		logger.info("getApimgwSubUrl() -START- APIM URL timeout시 재연결 URL 호출");
		logger.info("변경전 APIM URL : {}", apimUrl);
		
		// 현재 연결된 도메인이 Private일 경우에는 Public도메인 전달
		if (apimUrl.equals(apimPrivateUrl.trim()))
		{
			logger.info("getApimgwSubUrl() -END- APIM URL timeout시 재연결 URL 호출");
			return apimPublicUrl;
		}
		// Public도메인이 실패한 경우에는 무조건 Private으로 전환
		else
		{
			apimUrl = apimPrivateUrl;
			mainUrlFailCnt = 0;
		}
		
		logger.info("변경후 APIM URL : {}", apimUrl);
		logger.info("getApimgwSubUrl() -END- APIM URL timeout시 재연결 URL 호출");
		
		return apimUrl;
	}
}