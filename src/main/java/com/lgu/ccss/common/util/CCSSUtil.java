package com.lgu.ccss.common.util;

import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.annotation.PostConstruct;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lgu.ccss.common.constant.MDCConst;
import com.lgu.ccss.common.model.DeviceSessVO;
import com.lgu.ccss.common.model.RequestJSON;
import com.lgu.ccss.common.model.mgr.MgrAppSessVO;
import com.lgu.common.util.StringUtils;

import devonframe.dataaccess.CommonDao;

@Component
public class CCSSUtil {
	private static Logger logger = LoggerFactory.getLogger(CCSSUtil.class);

	@Autowired
	private CommonDao commonDao_altibase;
	private static CommonDao _altibaseDao;

	@PostConstruct
	public void init() {
		CCSSUtil._altibaseDao = commonDao_altibase;
	}

	public static void setCommonDataAPI(HttpServletRequest request, String requestUrl, String ccssToken,
			RequestJSON apiReq) {

		//bmapi/authentication/save/guestmode/agr추가 이유 : MDC에 데이터 저장시키기 위해서
		if ((!requestUrl.startsWith("/bmapi/authentication/save/guestmode/agr") && !requestUrl.startsWith("/api/authentication/save/guestmode/agr"))  &&((requestUrl.startsWith("/api/authentication") || requestUrl.startsWith("/bmapi/authentication") || ccssToken == null || ccssToken.length() <= 0))) {
			if (apiReq != null) {
				MDC.put(MDCConst.COMMON_CTN, (String) apiReq.getParam().get(RequestJSON.PARAM_CTN));
				MDC.put(MDCConst.COMMON_SERIAL, (String) apiReq.getParam().get(RequestJSON.PARAM_SERIAL));
				MDC.put(MDCConst.COMMON_MGR_USER_ID, (String) apiReq.getParam().get(RequestJSON.PARAM_MNGR_LOGIN_ID));
			}

			logger.debug("CTN({}) SERIAL({}) CCSS_TOKEN({}) MEMB_ID({}) MEMB_NO({}) SESS_EXP_DT({})",
					MDC.get(MDCConst.COMMON_CTN), MDC.get(MDCConst.COMMON_SERIAL), MDC.get(MDCConst.COMMON_CCSS_TOKEN),
					MDC.get(MDCConst.COMMON_MEMB_ID), MDC.get(MDCConst.COMMON_MEMB_NO),
					MDC.get(MDCConst.COMMON_SESS_EXP_DT));

			return;
		}

		// PND ccssToken
		MDC.put(MDCConst.COMMON_CCSS_TOKEN, ccssToken);

		DeviceSessVO deviceSess = new DeviceSessVO();
		deviceSess.setDeviceSessId(ccssToken);
		deviceSess = _altibaseDao.select("DeviceSession.selectDeviceSession", deviceSess);

		if (deviceSess != null) {
			MDC.put(MDCConst.COMMON_CTN, deviceSess.getDeviceCtn());
			MDC.put(MDCConst.COMMON_SERIAL, deviceSess.getDeviceSerial());
			MDC.put(MDCConst.COMMON_MEMB_ID, deviceSess.getMembId());
			MDC.put(MDCConst.COMMON_MEMB_NO, deviceSess.getMembNo());
			MDC.put(MDCConst.COMMON_SESS_EXP_DT, deviceSess.getDeviceSessExpDt());
		}

		logger.debug("CTN({}) SERIAL({}) CCSS_TOKEN({}) MEMB_ID({}) MEMB_NO({}) SESS_EXP_DT({})",
				MDC.get(MDCConst.COMMON_CTN), MDC.get(MDCConst.COMMON_SERIAL), MDC.get(MDCConst.COMMON_CCSS_TOKEN),
				MDC.get(MDCConst.COMMON_MEMB_ID), MDC.get(MDCConst.COMMON_MEMB_NO),
				MDC.get(MDCConst.COMMON_SESS_EXP_DT));
	}

	public static void setCommonDataMgrApp(HttpServletRequest request, String requestUrl, RequestJSON apiReq,
			String sessionId) {
		
		String deviceType = null;	
		if(requestUrl.indexOf("/mgrapi/authentication/register")  > -1 ) {
			MDC.put(MDCConst.COMMON_MGR_SESS_RANDOM_KEY, StringUtils.nvl(request.getHeader("randomKey")));
			MDC.put(MDCConst.COMMON_MGR_IP, StringUtils.nvl(request.getRemoteAddr()));
			deviceType = (String) apiReq.getCommon().getDevice().getDeviceType();
			MDC.put(MDCConst.COMMON_DEVICE_TYPE, deviceType);
		}
		
		if ( (requestUrl.indexOf("/mgrapi/authentication/subscriber")  < 0 
				&& requestUrl.startsWith("/mgrapi/authentication") && requestUrl.indexOf("/mgrapi/authentication/logout") < 0) 
				|| sessionId == null || sessionId.length() <= 0) {
			if (apiReq != null) {
				String mgrappLoginId = (String) apiReq.getParam().get(RequestJSON.PARAM_MNGR_LOGIN_ID);
				deviceType = (String) apiReq.getCommon().getDevice().getDeviceType();
				MDC.put(MDCConst.COMMON_DEVICE_TYPE, deviceType);
				// ctn이 없으면, Remote IP로 대체
				if (mgrappLoginId != null && mgrappLoginId.length() > 0) {
					MDC.put(MDCConst.COMMON_MGR_USER_ID, mgrappLoginId);

				} else {
					mgrappLoginId = request.getRemoteAddr();
				}
			}

			logger.debug("MGR_USER_ID({}) MGR_APP_SESSION({}) MGR_APP_SESS_EXP_DT({}), DEVICETYPE({})",
					MDC.get(MDCConst.COMMON_MGR_USER_ID), MDC.get(MDCConst.COMMON_MGR_SESSION_ID),
					MDC.get(MDCConst.COMMON_MGR_SESS_EXP_DT), MDC.get(MDCConst.COMMON_DEVICE_TYPE));

			return;
		}
		
		
		
		// Mngr SessionId
		MDC.put(MDCConst.COMMON_MGR_SESSION_ID, sessionId);

		// MgrApp Sessioon 조회
		MgrAppSessVO mgrAppSess = new MgrAppSessVO();
		mgrAppSess.setMgrappSessionId(sessionId);
		mgrAppSess = _altibaseDao.select("MgrAppSession.selectMgrSession", mgrAppSess);

		if (mgrAppSess != null) {
			MDC.put(MDCConst.COMMON_MGR_SESSION_ID, mgrAppSess.getMgrappSessionId());
			MDC.put(MDCConst.COMMON_MGR_SESS_EXP_DT, mgrAppSess.getSessionExpDt());
			MDC.put(MDCConst.COMMON_MGR_SESS_RANDOM_KEY, mgrAppSess.getRandomKey());
			MDC.put(MDCConst.COMMON_MGRAPP_ID, mgrAppSess.getMgrappId());
			MDC.put(MDCConst.COMMON_MGR_USER_ID, mgrAppSess.getMgrappLoginId());
		
			MDC.put(MDCConst.COMMON_OS_TYPE, mgrAppSess.getOsType());
			MDC.put(MDCConst.COMMON_UUID, mgrAppSess.getUuid());
			
			MDC.put(MDCConst.COMMON_DEVICE_TYPE, mgrAppSess.getDeviceType());
			/*MgrAppUserVO mgrAppUserVO = new MgrAppUserVO();
			mgrAppUserVO.setMgrappId(mgrAppSess.getMgrappId());
			mgrAppUserVO = _oracleDao.select("MgrAppUser.selectMgrUserInfo", mgrAppUserVO);

			if (mgrAppUserVO != null) {
				MDC.put(MDCConst.COMMON_MGR_USER_ID, mgrAppUserVO.getMgrUserLoginId());
			}*/
		}
		logger.debug(
				"MGR_USER_ID({}) MGR_APP_SESSION({}) MGR_APP_SESS_EXP_DT({}) MGR_APP_RANDOM_KEY({}) MGR_APP_ID({}) OS_TYPE({}) UUID({}) DEVICETYPE({})",
				MDC.get(MDCConst.COMMON_MGR_USER_ID), MDC.get(MDCConst.COMMON_MGR_SESSION_ID),
				MDC.get(MDCConst.COMMON_MGR_SESS_EXP_DT), MDC.get(MDCConst.COMMON_MGR_SESS_RANDOM_KEY),
				MDC.get(MDCConst.COMMON_MGRAPP_ID),	MDC.get(MDCConst.COMMON_OS_TYPE),	MDC.get(MDCConst.COMMON_UUID) ,   MDC.get(MDCConst.COMMON_DEVICE_TYPE));
	}
	
	
	/*public static void setWebApplicationContext(HttpServletRequest request)
	{
		try
		{
			// HttpSession 객체 가져오기
			HttpSession session = request.getSession();
	
			// ServletContext 객체 가져오기
			ServletContext conext = session.getServletContext();
	
			// Spring Context 가져오기
			WebApplicationContext wContext = WebApplicationContextUtils.getWebApplicationContext(conext);
			
			MDC.put(MDCConst.COMMON_WEB_CONTEXT, wContext);
		} catch (Exception ex )
		{
			logger.error("requestURL({}) Exception({})",request.getRequestURI(), ex);
		}
			
	}*/
	
	public static String getCtn() {
		return MDC.get(MDCConst.COMMON_CTN);
	}

	public static String getSerial() {
		return MDC.get(MDCConst.COMMON_SERIAL);
	}

	public static String getCcssToken() {
		return MDC.get(MDCConst.COMMON_CCSS_TOKEN);
	}

	public static String getMembId() {
		return MDC.get(MDCConst.COMMON_MEMB_ID);
	}

	public static String getMembNo() {
		return MDC.get(MDCConst.COMMON_MEMB_NO);
	}

	public static String getSessExpDt() {
		return MDC.get(MDCConst.COMMON_SESS_EXP_DT);
	}

	public static String getMgrSessionId() {
		return MDC.get(MDCConst.COMMON_MGR_SESSION_ID);
	}

	/*public static String getMgrUserCtn() {
		return MDC.get(MDCConst.COMMON_MGR_USER_ID);
	}*/
	
	public static String getMgrUserLoginId() {
		return MDC.get(MDCConst.COMMON_MGR_USER_ID);
	}

	public static String getMgrSessExpDt() {
		return MDC.get(MDCConst.COMMON_MGR_SESS_EXP_DT);
	}

	public static String getMgrRandomKey() {
		return MDC.get(MDCConst.COMMON_MGR_SESS_RANDOM_KEY);
	}

	public static String getMgrappId() {
		return MDC.get(MDCConst.COMMON_MGRAPP_ID);
	}
	
	public static String getOsType() {
		return MDC.get(MDCConst.COMMON_OS_TYPE);
	}
	
	public static String getUuid() {
		return MDC.get(MDCConst.COMMON_UUID);
	}
	
	public static String getMgrIp() {
		return MDC.get(MDCConst.COMMON_MGR_IP);
	}
	public static String getDeviceType() {
		return MDC.get(MDCConst.COMMON_DEVICE_TYPE);
	}
	
	public static Object getBean(String beanName)
	{
		// 현재 요청중인 thread local의 HttpServletRequest 객체 가져오기
		HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();

		// HttpSession 객체 가져오기
		HttpSession session = request.getSession();

		// ServletContext 객체 가져오기
		ServletContext conext = session.getServletContext();

		// Spring Context 가져오기
		WebApplicationContext wContext = WebApplicationContextUtils.getWebApplicationContext(conext);

		// 스프링 빈 가져오기 & casting
		return wContext.getBean(beanName);
	}
}
