package com.lgu.ccss.common.interceptor;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.http.protocol.HTTP;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.context.MessageSourceAware;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.lgu.ccss.common.constant.CCSSConst;
import com.lgu.ccss.common.constant.HeaderConst;
import com.lgu.ccss.common.constant.MDCConst;
import com.lgu.ccss.common.dao.DeviceSessDao;
import com.lgu.ccss.common.dao.MemberDao;
import com.lgu.ccss.common.dao.mgr.MgrAppDeviceDao;
import com.lgu.ccss.common.dao.mgr.MgrAppSessionDao;
import com.lgu.ccss.common.log.LogManager;
import com.lgu.ccss.common.log.LogService;
import com.lgu.ccss.common.model.MembData;
import com.lgu.ccss.common.model.MembVO;
import com.lgu.ccss.common.model.mgr.MgrAppDeviceVO;
import com.lgu.ccss.common.tlo.TloData;
import com.lgu.ccss.common.tlo.TloUtil;
import com.lgu.ccss.common.util.CCSSUtil;
import com.lgu.ccss.common.util.ResultCodeUtil;
import com.lgu.common.http.HttpResponseWrapper;
import com.lgu.common.model.ResultCode;
import com.lgu.common.ncas.NCASConst;
import com.lgu.common.ncas.NCASErrorCode;
import com.lgu.common.ncas.NCASQueryManager;
import com.lgu.common.ncas.NCASResultData;
import com.lgu.common.ncas.SubsInfo;
import com.lgu.common.tlo.TloWriter;
import com.lgu.common.trace.TraceConst;
import com.lgu.common.trace.TraceWriter;
import com.lgu.common.util.DateUtils;
import com.lgu.common.util.HttpHeaderUtils;
import com.lgu.common.util.StringUtils;

import devonframe.util.RandomUtil;

/**
 * 컨트롤러의 전반적인 전후 처리를 위한 인터셉터 클래스. <br/>
 * 시스템 점검, 요청 정보 등을 다룬다.
 * 
 */
public class RequestInterceptor extends HandlerInterceptorAdapter implements MessageSourceAware {

	private static final Logger logger = LoggerFactory.getLogger(RequestInterceptor.class);

	@Value("#{systemProperties.SERVER_ID}")
	private String serverId;

	@Value("#{config['session.timeoutMin']}")
	private String sessionTimeoutMin;

	@Value("#{config['mgrapp.session.timeoutMin']}")
	private String mgrappSessionTimeoutMin;

	@Autowired
	private DeviceSessDao deviceSessDao;
	
	@Autowired
	private MgrAppDeviceDao mgrAppDeviceDao;
	
	@Autowired
	private MemberDao	memberDao;
	
	@Autowired
	private NCASQueryManager nCASQueryManager;

	@Autowired
	private MgrAppSessionDao mgrAppSessionDao;

	@Autowired
	private TloWriter tloWriter;

	@Autowired
	private TraceWriter traceWriter;
	
	@Autowired
	private LogManager logManager;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		logger.debug("requestURL:" + request.getRequestURI());
		
		try {
			String requestUrl = StringUtils.nvl(request.getRequestURI());
			
			if (requestUrl.startsWith("/api/internal")) {
				return true;
			}
			
			String prefix = urlToPrefix(requestUrl);
			
			LogService logService = (LogService) logManager.getLogService(prefix);
			if (logService == null) {
				logger.error("logService is null. prefix({}) url({})", prefix, requestUrl);
			} else {
				logService.setRequestLog(request);
			}
			String deviceType = CCSSUtil.getDeviceType();
			if (prefix.equals(CCSSConst.PREFIX_API) || prefix.equals(CCSSConst.PREFIX_BMAPI)) {
				boolean sessionStatus = checkSessionAPI(request, response);
				if (sessionStatus == false) {
					return false;
				}

			} else if (prefix.equals(CCSSConst.PREFIX_MGRAPI) ) {
				if(deviceType.equalsIgnoreCase("PND")) {					
					boolean deviceStatus = checkDeviceMgrApp(request, response);
					if (deviceStatus == false) {
						return false;
				}
				}
				if (request.getMethod().equals("POST")){
					boolean sessionStatus = checkSessionMgrApp(request, response);
					if (sessionStatus == false) {
						return false;
					}
				} else if (request.getMethod().equals("GET")) {	
					boolean sessionStatus = checkSessionMgrAppGetMethod(request, response);
					if (sessionStatus == false) {
						return false;
					}
				}
			} 
		}
		catch (Exception e) {
			logger.error("Exception({})", e);
		}

		return super.preHandle(request, response, handler);
	}

	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView mav)
			throws Exception {

		logger.debug("requestURL:" + request.getRequestURI());
		
		try {
			String requestUrl = StringUtils.nvl(request.getRequestURI());
			String prefix = urlToPrefix(requestUrl);
			
			if (requestUrl.startsWith("/api/internal")) {
				return;
			}
			
			/*if (requestUrl.equals("/mgrapi/gcalendar/login"))
			{
				return;
			}*/
	
			if (prefix.equals(CCSSConst.PREFIX_API) || prefix.equals(CCSSConst.PREFIX_MGRAPI) || prefix.equals(CCSSConst.PREFIX_BMAPI)) {
				String transactionId = request.getHeader(HeaderConst.HD_NAME_TRANSACTION_ID);
				if (transactionId != null && transactionId.length() > 0) {
					response.setHeader(HeaderConst.HD_NAME_TRANSACTION_ID, transactionId);
				}
			} 
			
			// response.setHeader("Connection", "close");
			
			LogService logService = (LogService) logManager.getLogService(prefix);
			if (logService == null) {
				logger.error("logService is null. prefix({}) url({})", prefix, requestUrl);
			} else {
				logService.setResponseLog(request, response);
			}
		}
		catch (Exception e) {
			logger.error("Exception({})", e);
			
		} finally {
			MDC.clear();
		}
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {

	}

	public String getTrId(HttpServletRequest httpServletRequest) {

		HttpSession session = httpServletRequest.getSession(true);
		String trId = "TR_" + DateUtils.getMillisecondsTime() + RandomUtil.getRandomStr('A', 'Z')
				+ RandomUtil.getRandomNum(1, 10000);
		session.setAttribute("TID", trId);
		return trId;
	}

	private boolean checkSessionAPI(HttpServletRequest request, HttpServletResponse response) {
		String ccssToken = StringUtils.nvl(request.getHeader("ccssToken"));
		String requestUrl = StringUtils.nvl(request.getRequestURI());

		if (logger.isDebugEnabled()) {
			logger.debug("checkSession IN. ccssToken({}) requestUrl({})", ccssToken, requestUrl);
		}

		try {
			//게스트모드일 경우 MDC에 데이터 적재 시키기 위해 적용
			if ( (!requestUrl.startsWith("/bmapi/authentication/save/guestmode/agr")&&!requestUrl.startsWith("/api/authentication/save/guestmode/agr")) &&
					( requestUrl.startsWith("/api/authentication") || requestUrl.startsWith("/bmapi/authentication"))) {
				return true;
			}

			if (ccssToken == null || ccssToken.length() == 0) {
				logger.error("abnormal ccssToken value. ccssToken({})", ccssToken);
				sessionMgrFailMsg(request, response, ResultCodeUtil.RC_3C003004, CCSSUtil.getCtn());
				return false;
			}
			String sessExpDt = CCSSUtil.getSessExpDt();
			if (sessExpDt == null || sessExpDt.length() == 0) {
				logger.error("failed to select Session Info. ccssToken({})", ccssToken);
				sessionMgrFailMsg(request, response, ResultCodeUtil.RC_3C003004, CCSSUtil.getCtn());
				return false;
			}
			if (comparedSessionExpire(CCSSUtil.getSessExpDt())) {
				logger.info("Timeout Session. ccssToken({}) deviceCtn({}) sessExpDt({})", ccssToken, CCSSUtil.getCtn(),
						CCSSUtil.getSessExpDt());
				deviceSessDao.deleteDeviceSessByID(ccssToken);
				sessionMgrFailMsg(request, response, ResultCodeUtil.RC_3C003000, CCSSUtil.getCtn());
				return false;
			} else {
				String connIp = HttpHeaderUtils.getClientIPAddr(request);
				String expDate = DateUtils.getCurrentDate(Calendar.MINUTE, Integer.parseInt(sessionTimeoutMin),
						DateUtils.DATE_FORMAT_YMDHMS);
				
				if (expDate.compareTo(CCSSUtil.getSessExpDt()) < 0) {
					expDate = CCSSUtil.getSessExpDt();
				}
				
				deviceSessDao.updateDeviceSessTime(ccssToken, expDate, connIp);
			}

		} catch (Exception e) {
			logger.error("ccssToken({}) Exception({})", ccssToken, e);
			return false;
		}

		if (logger.isDebugEnabled()) {
			logger.debug("checkSession OUT. ccssToken({})", ccssToken);
		}

		return true;
	}

	private boolean checkSessionMgrApp(HttpServletRequest request, HttpServletResponse response) {
		String requestUrl = StringUtils.nvl(request.getRequestURI());
		String sessionId = StringUtils.nvl(request.getHeader("sessionId"));
		String randomKey = StringUtils.nvl(request.getHeader("randomKey"));

		if (logger.isDebugEnabled()) {
			logger.debug("checkSessionMgrApp IN. sessionId({}) requestUrl({})", sessionId, requestUrl);
		}

		try {
			// ignore URL Area
			if (requestUrl.equals("/mgrapi/authentication/register")
					|| requestUrl.equals("/mgrapi/authentication/version")
					|| requestUrl.equals("/mgrapi/authentication/sms/send")
					|| requestUrl.equals("/mgrapi/authentication/sms/cert")
					|| requestUrl.equals("/mgrapi/authentication/login")
					|| requestUrl.equals("/mgrapi/cek/infotainment/getNidInfo")
					|| requestUrl.equals("/mgrapi/cek/infotainment/tempIdRegist")
					) {

				return true;
			}
			
			if (sessionId == null || sessionId.length() == 0) {
				logger.error("abnormal sessionId value. sessionId({})", sessionId);
				sessionMgrFailMsg(request, response, ResultCodeUtil.RC_3C004000, CCSSUtil.getMgrUserLoginId());
				return false;
			}

			String mgrSessExpDt = CCSSUtil.getMgrSessExpDt();
			if (mgrSessExpDt == null || mgrSessExpDt.length() == 0) {
				logger.error("failed to select Mgrapp Session Info. sessionId({})", sessionId);
				sessionMgrFailMsg(request, response, ResultCodeUtil.RC_3C003007, CCSSUtil.getMgrUserLoginId());
				return false;
			}

			if (comparedSessionExpire(CCSSUtil.getMgrSessExpDt())) {
				logger.info("Mgr App Timeout Session. sessionId({}) userCtn({}) mgrSessExpDt({})", sessionId,
						CCSSUtil.getMgrUserLoginId(), CCSSUtil.getMgrSessExpDt());
				mgrAppSessionDao.deleteMgrSessByID(sessionId);
				sessionMgrFailMsg(request, response, ResultCodeUtil.RC_3C003008, CCSSUtil.getMgrUserLoginId());
				return false;

			} else {
				String connIp = HttpHeaderUtils.getClientIPAddr(request);

				String expDate = DateUtils.getCurrentDate(Calendar.MINUTE, Integer.parseInt(mgrappSessionTimeoutMin),
						DateUtils.DATE_FORMAT_YMDHMS);

				if (randomKey != null && randomKey.length() > 0) {
					if (CCSSUtil.getMgrRandomKey().equals(randomKey)) {
						mgrAppSessionDao.updateMgrSessTime(sessionId, expDate, connIp, randomKey);
					} else {
						
						sessionMgrFailMsg(request, response, ResultCodeUtil.RC_6C000006, CCSSUtil.getMgrUserLoginId());
						return false;
					}
				}
			}
			
		} catch (Exception e) {
			logger.error("sessionId({}) Exception({})", sessionId, e);
			return false;
		}

		if (logger.isDebugEnabled()) {
			logger.debug("checkSessionMgrApp OUT. sessionId({})", sessionId);
		}

		return true;
	}
	
	
	private boolean checkDeviceMgrApp(HttpServletRequest request, HttpServletResponse response) {
		String requestUrl = StringUtils.nvl(request.getRequestURI());
		String sessionId = StringUtils.nvl(request.getHeader("sessionId"));

		if (logger.isDebugEnabled()) {
			logger.debug("checkDeviceMgrApp IN. sessionId({}) requestUrl({})", sessionId, requestUrl);
		}

		try {
			// ignore URL Area
			if (requestUrl.equals("/mgrapi/authentication/logout")
					|| requestUrl.equals("/mgrapi/authentication/register")
					|| requestUrl.equals("/mgrapi/authentication/version")
					|| requestUrl.equals("/mgrapi/authentication/sms/send")
					|| requestUrl.equals("/mgrapi/authentication/sms/cert")
					|| requestUrl.equals("/mgrapi/authentication/login")
					|| requestUrl.equals("/mgrapi/cek/infotainment/getNidInfo")
					|| requestUrl.equals("/mgrapi/cek/infotainment/tempIdRegist")
					|| requestUrl.equals("/mgrapi/device/list")
					|| requestUrl.equals("/mgrapi/device/choice")
					|| requestUrl.equals("/mgrapi/device/delete"))
			 {
				return true;
			}

			String mgrappId = CCSSUtil.getMgrappId();
			String membId = CCSSUtil.getMembId();
			logger.debug("checkSessionMgrApp IN. mgrappId({})", mgrappId);
			logger.debug("checkSessionMgrApp IN. membId({})", membId);
			
			//20190111 서비스해지 추가
			MgrAppDeviceVO mgrAppDeviceVO= new MgrAppDeviceVO();
			mgrAppDeviceVO.setMgrappId(mgrappId);
			//mgrAppDeviceVO.setMembId(membId);
			
			List<MgrAppDeviceVO> resultList = mgrAppDeviceDao.selectMgrDeviceInfoList(mgrAppDeviceVO);
			//MgrAppDeviceVO list = mgrAppDeviceDao.selectMgrMainDeviceInfo(mgrAppDeviceVO);
		
			if(resultList !=null && resultList.size() > 0){
				//String membId="";
				boolean isMainUse	=false;
				for(int i =0; i<resultList.size();i++) {
					mgrAppDeviceVO = resultList.get(i);
					if(mgrAppDeviceVO.getMainUseYn() != null) {			
						if (mgrAppDeviceVO.getMainUseYn().equalsIgnoreCase("Y")) {
							isMainUse = true;
							membId = mgrAppDeviceVO.getMembId();
							break;
						}
					}else {
						logger.error("Main_Use_Yn is null.");
						sessionMgrFailMsg(request, response, ResultCodeUtil.RC_3C002010, CCSSUtil.getMgrUserLoginId());
						return false;
					}
				}
				if (isMainUse == false) {
					//주기기 없음
					logger.error("No Service Suspence.");
					sessionMgrFailMsg(request, response, ResultCodeUtil.RC_3C002010, CCSSUtil.getMgrUserLoginId());
					return false;
				}

				if (!membId.isEmpty()) {
					MembVO membVO = memberDao.selectMemberByID(membId);
					if(membVO == null) {
						//디비에 가입자 정보가 없는경우.
						logger.error("No Main Device.");
						sessionMgrFailMsg(request, response, ResultCodeUtil.RC_3C002004, CCSSUtil.getMgrUserLoginId());                        
						return false;
					} 

					String deviceCtn = membVO.getMembCtn();
					MembData membData = new MembData();
					ResultCode resultCode = getNcasSubsInfo(deviceCtn, membData);

					if (!ResultCodeUtil.RC_2S000000.equals(resultCode)) {
						if (ResultCodeUtil.RC_3C002005.equals(resultCode)) {
							// 사용자 일시정지
							logger.error("Subscriber is suspended");
							sessionMgrFailMsg(request, response, ResultCodeUtil.RC_3C002005, CCSSUtil.getMgrUserLoginId());
							return false;
						} else {
							if (resultList.size() >= 2) {
								// 주기기 서비스 해지
								logger.error("No service Suspence.");
								sessionMgrFailMsg(request, response, ResultCodeUtil.RC_3C002011, CCSSUtil.getMgrUserLoginId());
								return false;
							}else {
								// 서비스 해지
								logger.error("No Service Suspence");
								sessionMgrFailMsg(request, response, ResultCodeUtil.RC_3C002009, CCSSUtil.getMgrUserLoginId());
								return false;
							}
						}
					}
				}
			}else{
				//사용자 Device 정보 없음
				logger.error("Not Exist User DeviceInfo.");
				sessionMgrFailMsg(request, response, ResultCodeUtil.RC_6C000003, CCSSUtil.getMgrUserLoginId());
				return false;
			}
		} catch (Exception e) {
			logger.error("sessionId({}) Exception({})", sessionId, e);
			return false;
		}

		if (logger.isDebugEnabled()) {
			logger.debug("checkDeviceMgrApp OUT. sessionId({})", sessionId);
		}

		return true;
	}
	
	
	private boolean checkSessionMgrAppGetMethod(HttpServletRequest request, HttpServletResponse response) {
		String requestUrl = StringUtils.nvl(request.getRequestURI());
		String sessionId = StringUtils.nvl(request.getParameter("sessionId"));
		String randomKey = StringUtils.nvl(request.getParameter("randomKey"));

		if (logger.isDebugEnabled()) {
			logger.debug("checkSessionMgrApp IN. sessionId({}) requestUrl({})", sessionId, requestUrl);
		}

		try {
			// ignore URL Area
			if ( requestUrl.equals("/mgrapi/gcalendar/login") ||
					requestUrl.equals("/mgrapi/gcalendar/login/callback")
					) {
				return true;
			}


			if (sessionId == null || sessionId.length() == 0) {
				logger.error("abnormal sessionId value. sessionId({})", sessionId);
				sessionMgrFailMsg(request, response, ResultCodeUtil.RC_3C004000, CCSSUtil.getMgrUserLoginId());
				return false;
			}

			String mgrSessExpDt = CCSSUtil.getMgrSessExpDt();
			if (mgrSessExpDt == null || mgrSessExpDt.length() == 0) {
				logger.error("failed to select Mgrapp Session Info. sessionId({})", sessionId);
				sessionMgrFailMsg(request, response, ResultCodeUtil.RC_3C003007, CCSSUtil.getMgrUserLoginId());
				return false;
			}

			if (comparedSessionExpire(CCSSUtil.getMgrSessExpDt())) {
				logger.info("Mgr App Timeout Session. sessionId({}) userCtn({}) mgrSessExpDt({})", sessionId,
						CCSSUtil.getMgrUserLoginId(), CCSSUtil.getMgrSessExpDt());
				mgrAppSessionDao.deleteMgrSessByID(sessionId);
				sessionMgrFailMsg(request, response, ResultCodeUtil.RC_3C003008, CCSSUtil.getMgrUserLoginId());
				return false;

			} else {
				String connIp = HttpHeaderUtils.getClientIPAddr(request);

				String expDate = DateUtils.getCurrentDate(Calendar.MINUTE, Integer.parseInt(mgrappSessionTimeoutMin),
						DateUtils.DATE_FORMAT_YMDHMS);

				if (randomKey != null && randomKey.length() > 0) {
					if (CCSSUtil.getMgrRandomKey().equals(randomKey)) {
						mgrAppSessionDao.updateMgrSessTime(sessionId, expDate, connIp, randomKey);
					} else {

						sessionMgrFailMsg(request, response, ResultCodeUtil.RC_6C000006, CCSSUtil.getMgrUserLoginId());
					}
				}
			}

		} catch (Exception e) {
			logger.error("sessionId({}) Exception({})", sessionId, e);
			return false;
		}

		if (logger.isDebugEnabled()) {
			logger.debug("checkSessionMgrApp OUT. sessionId({})", sessionId);
		}

		return true;
	}
	


	public boolean comparedSessionExpire(String sessExpDt) {

		Date nowDt = new Date();
		Date expDt = DateUtils.stringToDate(sessExpDt);

		if (DateUtils.comparedExpDate(nowDt, expDt) < 0) {
			return true;
		}
		return false;
	}

	
	private ResultCode getNcasSubsInfo(String deviceCtn, MembData membData) {
		
		NCASResultData ncasData = nCASQueryManager.query(deviceCtn);
		if(ncasData ==null) {
			logger.error("failed to query NCAS data. deviceCtn({})", deviceCtn);
			return ResultCodeUtil.RC_5N000001;
		}
		
		if(NCASErrorCode.ERRORCODE_NO_LGT.equals(ncasData.getRespCode())) {
			logger.error("No subscriber. deviceCtn({})",deviceCtn, ncasData.getRespCode());
			return ResultCodeUtil.RC_3C002004;
		}
		
		if(!NCASErrorCode.ERRORCODE_SUCCESS.equals(ncasData.getRespCode())) {
			logger.error("Abnormal subscriber. deviceCtn({}) respCode({})",deviceCtn,ncasData.getRespCode());
			return ResultCodeUtil.RC_5N000000;
		}
		
		SubsInfo subsInfo = ncasData.getSubsInfo();
		if(subsInfo == null) {
			logger.error("failed to get SubsInfo data. deviceCtn({})",deviceCtn);
			return ResultCodeUtil.RC_5N000000;
		}
		
		if (subsInfo.getCtn_stus_code().equals(NCASConst.CTN_STUS_CODE_SUSPEND)) {
			logger.error("Subscriber is suspended. deviceCtn({})", deviceCtn);
			return ResultCodeUtil.RC_3C002005;
		}		
		
		if(logger.isDebugEnabled()) {
			logger.debug("divecCtn({}) SUBS_INFO({})",deviceCtn,subsInfo);
		}
		
		return ResultCodeUtil.RC_2S000000;
	}
	

	public void sessionMgrFailMsg(HttpServletRequest req, HttpServletResponse res, ResultCode resultCode, String mgrUserLoginId)
			throws IOException {
		res.setCharacterEncoding(StandardCharsets.UTF_8.toString());

		res.setHeader(HeaderConst.HD_NAME_CACHE_CONTROL, HeaderConst.HD_VALUE_NO_CACHE);
		res.setHeader(HTTP.CONTENT_TYPE, HeaderConst.HD_VALUE_CONTENTTYPE_JSON_UTF8);

		String transactionId = req.getHeader(HeaderConst.HD_NAME_TRANSACTION_ID);
		if (transactionId != null && transactionId.length() > 0) {
			res.setHeader(HeaderConst.HD_NAME_TRANSACTION_ID, transactionId);
		}

		res.getWriter().write(ResultCodeUtil.createResultMsgToJsonString(resultCode, MDC.get(MDCConst.API_ID)));

		Map<String, String> tlo = new HashMap<String, String>();
		tlo.put(TloData.RSP_TIME, TloData.getNowDate());
		tlo.put(TloData.RESULT_CODE, resultCode.getCode());

		TloUtil.setTloData(tlo);
		tloWriter.write(TloUtil.getTloData());

		traceWriter.trace(mgrUserLoginId, TraceConst.NODE_ID_WAS, MDC.get(MDCConst.TRACE_SOURCE), (HttpResponseWrapper) res);
	}
	
	@Override
	public void setMessageSource(MessageSource messageSource) {
		// TODO Auto-generated method stub

	}
	
	private String urlToPrefix(String url) {		
		if (url.startsWith(CCSSConst.PREFIX_API)) {
			return CCSSConst.PREFIX_API;
		}
		else if (url.startsWith(CCSSConst.PREFIX_BMAPI)) {
			return CCSSConst.PREFIX_BMAPI;
		}
		else if (url.startsWith(CCSSConst.PREFIX_MGRAPI)) {
			return CCSSConst.PREFIX_MGRAPI;
		}
		else if (url.startsWith(CCSSConst.PREFIX_MYPAGE_PND)) {
			return CCSSConst.PREFIX_MYPAGE_PND;
		}
		else if (url.startsWith(CCSSConst.PREFIX_MYPAGE_EV)) {
			return CCSSConst.PREFIX_MYPAGE_EV;
		}
		else if (url.startsWith(CCSSConst.PREFIX_MYPAGE_PHONE)) {
			return CCSSConst.PREFIX_MYPAGE_PHONE;
		}
		else if (url.startsWith(CCSSConst.PREFIX_PUSH)) {
			return CCSSConst.PREFIX_PUSH;
		}
		else if (url.startsWith(CCSSConst.PREFIX_DATAGIFT)) {
			return CCSSConst.PREFIX_DATAGIFT;
		}
		
		return null;
	}
}