package com.lgu.ccss.mypage.phone.service;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.http.HttpHeaders;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lgu.ccss.common.dao.DeviceDao;
import com.lgu.ccss.common.dao.MemberDao;
import com.lgu.ccss.common.dao.mgr.MgrAppDeviceDao;
import com.lgu.ccss.common.dao.mgr.MgrAppSessionDao;
import com.lgu.ccss.common.model.MembVO;
import com.lgu.ccss.common.model.mgr.MgrAppDeviceVO;
import com.lgu.ccss.common.model.mgr.MgrAppSessVO;
import com.lgu.ccss.mypage.pnd.dao.PNDDao;
import com.lgu.ccss.mypage.pnd.model.TermsVO;
import com.lgu.common.esb.ESBManager;
import com.lgu.common.esb.vo.AmountDataVO;
import com.lgu.common.esb.vo.BillReturnInfoVO;
import com.lgu.common.esb.vo.BillTypeResultVO;
import com.lgu.common.esb.vo.BillTypeVO;
import com.lgu.common.esb.vo.DiscountInfoResultVO;
import com.lgu.common.esb.vo.DiscountInfoVO;
import com.lgu.common.esb.vo.PauseHistoryCollectionVO;
import com.lgu.common.esb.vo.PauseHistoryVO;
import com.lgu.common.esb.vo.PauseResultVO;
import com.lgu.common.esb.vo.PayDetailVO;
import com.lgu.common.esb.vo.PayInfoVO;
import com.lgu.common.esb.vo.PayMethodAuthVO;
import com.lgu.common.esb.vo.PayMethodResultVO;
import com.lgu.common.esb.vo.PayMethodVO;
import com.lgu.common.esb.vo.SubsInfoVO;
import com.lgu.common.esb.vo.UnpauseResultVO;
import com.lgu.common.ncas.NCASQueryManager;
import com.lgu.common.ncas.NCASResultData;
import com.lgu.common.ncas.SubsInfo;
import com.lgu.common.util.DateUtils;

import lguplus.u3.webservice.cm016.RetrieveCustInfoSvcServiceStub;
import lguplus.u3.webservice.sb915.RetrieveCustSvcEntrInfoBDServiceStub;
import lguplus.u3.webservice.sm546.RetrieveDscntMgmtServiceStub;

@Service("phoneService")
public class PhoneServiceImpl implements PhoneService {

	private static final Logger logger = LoggerFactory.getLogger(PhoneServiceImpl.class);


	@Autowired
	private NCASQueryManager nCASQueryManager;

	@Autowired
	private ESBManager eSBManager;

	@Autowired
	private MgrAppSessionDao mgrAppSessionDao;

	@Autowired
	private MemberDao memberDao;

	@Autowired
	private PNDDao pNDDao;

	@Autowired
	private MgrAppDeviceDao mgrAppDeviceDao;
	
	@Autowired
	private DeviceDao deviceDao;

	@Value("#{config['service.ratePayment']}")
	private String ratePayment;
	@Value("#{config['service.ratePayment.period']}")
	private String ratePaymentPeriod;
	@Value("#{config['service.ratePayment.expirationNotiDay']}")
	private String ratePaymentExpirationNotiDay;
	@Value("#{config['el1.service.ratePayment.period']}")
	private String el1RatePaymentPeriod;
	@Value("#{config['service.joiningDay']}")
	private String joiningDay;
	private String strRatePaymentPeriod;

	@Value("#{config['esb.real']}")
	private String real; // 상용인지 여부 - ESB 상용과 개발이 너무 다름
	@Value("#{config['esb.enable']}")
	private String esbEnable;

	@Value("#{config['kmc.phone.returnUrl']}")
	private String kmcReturnUrl;
	@Value("#{config['kmc.phone.cpId']}")
	private String kmcCpId;
	@Value("#{config['kmc.phone.urlCode']}")
	private String kmcUrlCode;

	@Value("#{config['ipin.phone.returnUrl']}")
	private String ipinReturnUrl;
	@Value("#{config['ipin.phone.siteCode']}")
	private String ipinSiteCode;
	@Value("#{config['ipin.phone.sitePw']}")
	private String ipinSitePw;
	
	@Value("#{rate['ratePayMobileDtl']}")
	private String ratePayDtl;
	
	//AM 약관동의 설정 값
	@Value("#{config['am.agr.groupcd']}")
	private String agrGroupCd;
	@Value("#{config['am.agr.terms.agrservice']}")
	private String agrAgrService;	
	@Value("#{config['am.agr.terms.information']}")
	private String agrInformation;
	@Value("#{config['am.agr.terms.third']}")
	private String agrThird;
	@Value("#{config['am.agr.terms.location']}")
	private String agrLocation;

	private Pattern VALID_DATE_YYYYMMDD_REGEX = Pattern.compile("^[0-9]{4}(1[0-2]|0[1-9])(3[01]|[12][0-9]|0[1-9])$",
			Pattern.CASE_INSENSITIVE);

	private Pattern VALID_EMAIL_ADDRESS_REGEX = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$",
			Pattern.CASE_INSENSITIVE);

	private Pattern VALID_NUMBER_REGEX = Pattern.compile("^[0-9]*$", Pattern.CASE_INSENSITIVE);

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
			logger.info("mgrAppCtn({}) is empty", mgrAppCtn);
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
/*
		MgrAppSessVO mgrAppSess = mgrAppSessionDao.selectMgrSess(sessionId);
		if (mgrAppSess == null) {
			logger.error("failed to select mgrAppSess data. mgrAppCtn({}) sessionId({})", mgrAppCtn, sessionId);
			return null;
		}

		MgrAppDeviceVO mgrAppDevice = new MgrAppDeviceVO();
		mgrAppDevice.setMgrappId(mgrAppSess.getMgrappId());
		mgrAppDevice = mgrAppDeviceDao.selectMgrMainDeviceInfo(mgrAppDevice);
		if (mgrAppDevice == null) {
			logger.error("failed to select mgrAppDevice data. mgrAppCtn({}) sessionId({}) mgrAppId({})", mgrAppCtn,
					sessionId, mgrAppSess.getMgrappId());
			return null;
		}
*/
		//20190308 kangjin
		MgrAppDeviceVO mgrAppDevice = new MgrAppDeviceVO();
		//mgrAppDevice.setMgrappId(mgrAppSess.getMgrappId());
		mgrAppDevice.setMgrappId(mgrAppCtn);
		mgrAppDevice = mgrAppDeviceDao.selectMgrMainDeviceInfo(mgrAppDevice);
		if (mgrAppDevice == null) {
			logger.error("failed to select mgrAppDevice data. mgrAppCtn({}) sessionId({}) mgrAppId({})", mgrAppCtn,
					sessionId);
			return null;
		}		
		
		MembVO memb = memberDao.selectMemberByID(mgrAppDevice.getMembId());
		if (memb == null) {
			logger.error("failed to select member data. mgrAppCtn({}) sessionId({}) deviceSess({})", mgrAppCtn,
					sessionId);
			return null;
		}

		return memb;
	}
	
	private SubsInfo checkByCtn(HttpServletRequest httpServletRequest) {
		String userAgent = httpServletRequest.getHeader(HttpHeaders.USER_AGENT);
		logger.info("userAgent({})", userAgent);

		String mgrAppCtn = "";
		String sessionId = "";
		Matcher matcher = VALID_USER_AGENT_REGEX.matcher(userAgent);
		if (matcher.find()) {
			mgrAppCtn = matcher.group(1);
			sessionId = matcher.group(2);
		}

		if (StringUtils.isEmpty(mgrAppCtn)) {
			logger.info("mgrAppCtn({}) is empty", mgrAppCtn);
			return null;
		}

		if (StringUtils.isEmpty(sessionId)) {
			logger.info("sessionId({}) is empty", sessionId);
			return null;
		}

		logger.info("mgrAppCtn({}) sessionId({})", mgrAppCtn, sessionId);

		MgrAppSessVO mgrAppSess = mgrAppSessionDao.selectMgrSess(sessionId);
		if (mgrAppSess == null) {
			logger.error("failed to select mgrAppSess data. mgrAppCtn({}) sessionId({})", mgrAppCtn, sessionId);
			return null;
		}

		MgrAppDeviceVO mgrAppDevice = new MgrAppDeviceVO();
		mgrAppDevice.setMgrappId(mgrAppSess.getMgrappId());
		mgrAppDevice = mgrAppDeviceDao.selectMgrMainDeviceInfo(mgrAppDevice);
		if (mgrAppDevice == null) {
			logger.error("failed to select mgrAppDevice data. mgrAppCtn({}) sessionId({}) mgrAppId({})", mgrAppCtn,
					sessionId, mgrAppSess.getMgrappId());
			return null;
		}

		MembVO memb = memberDao.selectMemberByID(mgrAppDevice.getMembId());
		if (memb == null) {
			logger.error("failed to select member data. mgrAppCtn({}) sessionId({}) deviceSess({})", mgrAppCtn,
					sessionId, mgrAppSess);
			return null;
		}

		
		String deviceCtn = memb.getMembCtn();


		if (StringUtils.isEmpty(deviceCtn)) {
			logger.info("deviceCtn({}) is empty", deviceCtn);
			return null;
		}

		// query NCAS
		NCASResultData ncasData = nCASQueryManager.query(deviceCtn);
		if (ncasData == null) {
			logger.error("failed to query NCAS data. deviceCtn({})", deviceCtn);
			return null;
		}

		SubsInfo subsInfo = ncasData.getSubsInfo();
		if (subsInfo == null) {
			logger.error("failed to get SubsInfo data. deviceCtn({})", deviceCtn);
			return null;
		}

		if (logger.isDebugEnabled()) {
			logger.debug("SUBS_INFO({})", subsInfo);
		}

		return subsInfo;
	}

	/**
	 * 요금제에 따라 페이지 분기
	 */
	@Override
	public String getMain(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Model model) {
		try {
			if (StringUtils.equalsIgnoreCase("Y", esbEnable)) {
				MembVO memb = validationByAll(httpServletRequest);
				if (memb == null) {
					logger.debug("{}", "(memb == null)");

					return "phone/errorPage";
				}
				
				//접속 디바이스 모델 찾기 20190128 kangjin
				String connDeviceModelId = getConnDeviceModel(memb);
				
				// 닛산 LEAF 20190128 kangjin
				if("EL1".equals(connDeviceModelId)) {
					strRatePaymentPeriod = el1RatePaymentPeriod;
				}else {
					strRatePaymentPeriod = ratePaymentPeriod;
				}				

				String returnValue = "";
				if (DateUtils.isExpireTime(memb.getJoinDt(), strRatePaymentPeriod)
						&& MembVO.PAY_RESV_Y.equals(memb.getPayResvYn())) {
					// returnValue =
					// "redirect:/mypage/phone/main/phone/unLimitMenu";
					returnValue = "redirect:unlimitMenu.do";
				} else {
					returnValue = "redirect:limitMenu.do";
				}

				return returnValue;
			} else {
				String returnValue = "redirect:limitMenu.do";

				return returnValue;
			}
		} catch (Exception e) {
			logger.debug("{}", e.getMessage());

			e.printStackTrace();
		}

		return "phone/errorPage";
	}

	/**
	 * 인증선택
	 */
	@Override
	public String getAuthChoice(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
			Model model) {
		try {
			if (StringUtils.equalsIgnoreCase("Y", esbEnable)) {
				// TODO
			} else {
				// TODO
			}

			return "phone/authChoice";
		} catch (Exception e) {
			logger.debug("{}", e.getMessage());

			e.printStackTrace();
		}

		return "phone/errorPage";
	}
	
	/*인증선택 unlimitBillType*/
	@Override
	public String getAuthChoiceKmc(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
			Model model) {
		try {
			if (StringUtils.equalsIgnoreCase("Y", esbEnable)) {
				// TODO
			} else {
				// TODO
			}

			return "phone/authChoiceKmc";
		} catch (Exception e) {
			logger.debug("{}", e.getMessage());

			e.printStackTrace();
		}

		return "phone/errorPage";
	}
	

	/**
	 * 본인인증 > 나이스아이핀
	 */
	@Override
	public String getAuthIpinMain(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
			Model model) {
		try {
			if (StringUtils.equalsIgnoreCase("Y", esbEnable)) {
				// TODO
			} else {
				// TODO
			}

			model.addAttribute("returnUrl", ipinReturnUrl);
			model.addAttribute("siteCode", ipinSiteCode);
			model.addAttribute("sitePw", ipinSitePw);

			return "phone/authIpinMain";
		} catch (Exception e) {
			logger.debug("{}", e.getMessage());

			e.printStackTrace();
		}

		return "phone/errorPage";
	}

	/**
	 * 본인인증 > 나이스아이핀 > 결과
	 */
	@Override
	public String getAuthIpinResult(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
			Model model) {
		try {
			if (StringUtils.equalsIgnoreCase("Y", esbEnable)) {
				// TODO
			} else {
				// TODO
			}

			model.addAttribute("siteCode", ipinSiteCode);
			model.addAttribute("sitePw", ipinSitePw);

			return "phone/authIpinResult";
		} catch (Exception e) {
			logger.debug("{}", e.getMessage());

			e.printStackTrace();
		}

		return "phone/errorPage";
	}

	/**
	 * 본인인증 > 휴대폰본인인증
	 */
	@Override
	public String getAuthKmcMain(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
			Model model) {
		try {
			if (StringUtils.equalsIgnoreCase("Y", esbEnable)) {
				// TODO
			} else {
				// TODO
			}

			model.addAttribute("returnUrl", kmcReturnUrl);
			model.addAttribute("cpId", kmcCpId);
			model.addAttribute("urlCode", kmcUrlCode);

			return "phone/authKmcMain";
		} catch (Exception e) {
			logger.debug("{}", e.getMessage());

			e.printStackTrace();
		}

		return "phone/errorPage";
	}

	/**
	 * 본인인증 > 휴대폰본인인증 > 결과
	 */
	@Override
	public String getAuthKmcResult(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
			Model model) {
		try {
			if (StringUtils.equalsIgnoreCase("Y", esbEnable)) {
				// TODO
			} else {
				// TODO
			}

			return "phone/authKmcResult";
		} catch (Exception e) {
			logger.debug("{}", e.getMessage());

			e.printStackTrace();
		}

		return "phone/errorPage";
	}

	// ######################################################################
	// 월정액요금제1
	// ######################################################################

	/**
	 * 월정액요금제1 > 메뉴
	 */
	@Override
	public String getLimitMenu(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
			Model model) {
		try {
			if (StringUtils.equalsIgnoreCase("Y", esbEnable)) {
				MembVO memb = validationByAll(httpServletRequest);
				if (memb == null) {
					logger.debug("{}", "(memb == null)");

					return "phone/errorPage";
				}

				String payResvYn = StringUtils.defaultIfEmpty(StringUtils.equals("Y", memb.getPayResvYn()) ? "Y" : "N",
						"N");

				model.addAttribute("payResvYn", payResvYn);// 요금제예약신청
			} else {

				model.addAttribute("payResvYn", "N");// 요금제예약신청
			}

			return "phone/limitMenu";
		} catch (Exception e) {
			logger.debug("{}", e.getMessage());

			e.printStackTrace();
		}

		return "phone/errorPage";
	}

	/**
	 * 월정액요금제1 > 가입정보
	 */
	@Override
	public String getLimitSubsInfo(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
			Model model) {
		try {
			if (StringUtils.equalsIgnoreCase("Y", esbEnable)) {
				MembVO memb = validationByAll(httpServletRequest);
				if (memb == null) {
					logger.debug("{}", "(memb == null)");

					return "phone/errorPage";
				}

				// 2018-12-27
				// query NCAS
				NCASResultData ncasData = nCASQueryManager.query(memb.getMembCtn());
				if (ncasData == null) {
					logger.error("failed to query NCAS data. deviceCtn({})", memb.getMembCtn());
					return null;
				}
				SubsInfo subsInfoNcas = ncasData.getSubsInfo();
				if (subsInfoNcas == null) {
					logger.error("failed to get SubsInfo data. deviceCtn({})", memb.getMembCtn());
					return null;
				}				
				
				SubsInfoVO subsInfo = eSBManager.getSubsInfo(subsInfoNcas.getSub_no(), memb.getProductNm());
				if (subsInfo == null) {
					logger.debug("{}", "(subsInfo == null)");

					return "phone/errorPage";
				}

				String callYm = new SimpleDateFormat("yyyyMMdd").format(new Date());
				String ip = getIp();

				AmountDataVO amountData = eSBManager.getAmountData(subsInfoNcas.getSub_no(), memb.getProductNm(), callYm, ip);
				if (amountData == null) {
					logger.debug("{}", "(amountData == null)");

					return "phone/errorPage";
				}

				String alloValue = "0";
				if (VALID_NUMBER_REGEX.matcher(amountData.getAlloValue()).find()) {
					alloValue = String.valueOf((int) (Float.parseFloat(amountData.getAlloValue()) / 1024.0f));
				}
				String fxedFctr1 = subsInfo.getFxedFctr1();
				// String svcEndDttm = "";
				// if (StringUtils.isNotEmpty(subsInfo.getSvcEndDttm())
				// &&
				// VALID_DATE_YYYYMMDD_REGEX.matcher(subsInfo.getSvcEndDttm()).find()
				// && !subsInfo.getSvcEndDttm().startsWith("9999")) {
				// Date date = new
				// SimpleDateFormat("yyyyMMdd").parse(subsInfo.getSvcEndDttm());
				//
				// svcEndDttm = new SimpleDateFormat("yyyyMMdd").format(date);
				// }
				// String svcFrstStrtDttm = "";
				// if (StringUtils.isNotEmpty(subsInfo.getSvcFrstStrtDttm())
				// &&
				// VALID_DATE_YYYYMMDD_REGEX.matcher(subsInfo.getSvcFrstStrtDttm()).find()
				// && !subsInfo.getSvcFrstStrtDttm().startsWith("9999")) {
				// Date date = new
				// SimpleDateFormat("yyyyMMdd").parse(subsInfo.getSvcFrstStrtDttm());
				//
				// svcFrstStrtDttm = new
				// SimpleDateFormat("yyyy.MM.dd").format(date);
				// }

				
				String svcFrstStrtDttm1 = "";
				String svcFrstStrtDttm2 = "";
				if (StringUtils.isNotEmpty(subsInfo.getSvcFrstStrtDttm())
						&& VALID_DATE_YYYYMMDD_REGEX.matcher(subsInfo.getSvcFrstStrtDttm()).find()
						&& !subsInfo.getSvcFrstStrtDttm().startsWith("9999")) {
					//Date date = memb.getJoinDt();
					Date date = new SimpleDateFormat("yyyyMMdd").parse(subsInfo.getSvcFrstStrtDttm());

					svcFrstStrtDttm1 = new SimpleDateFormat("yyyy년 MM월 dd일(E)").format(date);
					svcFrstStrtDttm2 = new SimpleDateFormat("yyyy년 MM월 dd일").format(date);
				}
				
				
				/*String svcFrstStrtDttm1 = "";
				String svcFrstStrtDttm2 = "";
				if (StringUtils.isNotEmpty(subsInfo.getSvcFrstStrtDttm())
						&& VALID_DATE_YYYYMMDD_REGEX.matcher(subsInfo.getSvcFrstStrtDttm()).find()
						&& !subsInfo.getSvcFrstStrtDttm().startsWith("9999")) {
					Date date = new SimpleDateFormat("yyyyMMdd").parse(subsInfo.getSvcFrstStrtDttm());

					svcFrstStrtDttm1 = new SimpleDateFormat("yyyy년 MM월 dd일(E)").format(date);
					svcFrstStrtDttm2 = new SimpleDateFormat("yyyy년 MM월 dd일").format(date);

					// Calendar calendar = Calendar.getInstance();
					// calendar.setTime(date);
					//
					// String[] dayOfTheWeeks = { "일", "월", "화", "수", "목", "금",
					// "토" };
					// svcFrstStrtDttm = String.format("%s(%s)",
					// svcFrstStrtDttm,
					// dayOfTheWeeks[calendar.get(Calendar.DAY_OF_WEEK) - 1]);
				}*/

				String svcStrtDttm = "";
				String svcEndDttm = "";
				if (StringUtils.isNotEmpty(subsInfo.getSvcFrstStrtDttm())
						&& VALID_DATE_YYYYMMDD_REGEX.matcher(subsInfo.getSvcFrstStrtDttm()).find()
						&& !subsInfo.getSvcFrstStrtDttm().startsWith("9999")) {
					Date startDate = new SimpleDateFormat("yyyyMMdd").parse(subsInfo.getSvcFrstStrtDttm());

					Calendar calendar = Calendar.getInstance();
					calendar.setTime(startDate);

					//접속 디바이스 모델 찾기 20190128 kangjin
					String connDeviceModelId = getConnDeviceModel(memb);
					
					if("EL1".equals(connDeviceModelId)) {
						calendar.add(Calendar.YEAR, 2);
					}else {
						calendar.add(Calendar.YEAR, 1);
					}
					
					calendar.add(Calendar.DATE, -1);
					
					svcStrtDttm = new SimpleDateFormat("yyyy년 MM월 dd일").format(startDate);
					svcEndDttm = new SimpleDateFormat("yyyy년 MM월 dd일").format(calendar.getTime());
				}

				String svcNm = subsInfo.getSvcNm();
				String useValue = "0";
				if (VALID_NUMBER_REGEX.matcher(amountData.getUseValue()).find()) {
					useValue = String.valueOf((int) (Float.parseFloat(amountData.getUseValue()) / 1024.0f));
				}

				String remainingValue = "0";
				if (VALID_NUMBER_REGEX.matcher(alloValue).find() && VALID_NUMBER_REGEX.matcher(useValue).find()) {
					remainingValue = String.valueOf(Long.parseLong(alloValue) - Long.parseLong(useValue));
				}

				model.addAttribute("alloValue", StringUtils.defaultIfEmpty(alloValue, ""));// 제공량
				model.addAttribute("fxedFctr1", StringUtils.defaultIfEmpty(fxedFctr1, ""));// 약정정보
				model.addAttribute("svcEndDttm", StringUtils.defaultIfEmpty(svcEndDttm, ""));// 해지일자
				model.addAttribute("svcStrtDttm", StringUtils.defaultIfEmpty(svcStrtDttm, ""));// 개통일자
				model.addAttribute("svcFrstStrtDttm1", StringUtils.defaultIfEmpty(svcFrstStrtDttm1, ""));// 개통일자 TEST용으로 잠시 주석처리
				model.addAttribute("svcFrstStrtDttm2", StringUtils.defaultIfEmpty(svcFrstStrtDttm2, ""));// 개통일자 TEST용으로 잠시 주석처리
				model.addAttribute("svcNm", StringUtils.defaultIfEmpty(svcNm, ""));// 상품(요금제)명
				model.addAttribute("useValue", StringUtils.defaultIfEmpty(useValue, ""));// 사용량
				model.addAttribute("remainingValue", StringUtils.defaultIfEmpty(remainingValue, ""));// 남은
																										// 데이터
			} else {
				Calendar calendar = Calendar.getInstance();
				calendar.add(Calendar.YEAR, 1);

				String alloValue = "10000.00";
				String fxedFctr1 = "";
				String svcEndDttm = new SimpleDateFormat("MM월 dd일").format(calendar.getTime());
				String svcFrstStrtDttm1 = new SimpleDateFormat("yyyy년 MM월 dd일(E)").format(new Date());
				String svcFrstStrtDttm2 = new SimpleDateFormat("yyyy년 MM월 dd일").format(new Date());
				String svcNm = "LTE) 태블릿/스마트기기 데이터 10GB";
				String useValue = "0.00";
				String remainingValue = "10000.00";

				model.addAttribute("alloValue", alloValue);// 제공량
				model.addAttribute("fxedFctr1", fxedFctr1);// 약정기간
				model.addAttribute("svcEndDttm", svcEndDttm);// 해지일자
				model.addAttribute("svcFrstStrtDttm1", svcFrstStrtDttm1);// 개통일자
				model.addAttribute("svcFrstStrtDttm2", svcFrstStrtDttm2);// 개통일자
				model.addAttribute("svcNm", svcNm);// 상품(요금제)명
				model.addAttribute("useValue", useValue);// 사용량
				model.addAttribute("remainingValue", StringUtils.defaultIfEmpty(remainingValue, ""));// 남은
																										// 데이터
			}

			return "phone/limitSubsInfo";
		} catch (Exception e) {
			logger.debug("{}", e);

			e.printStackTrace();
		}

		return "phone/errorPage";
	}

	/**
	 * 월정액요금제1 > 요금제신청
	 */
	@Override
	public String getLimitApplyPlan(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
			Model model) {
		try {
			if (StringUtils.equalsIgnoreCase("Y", esbEnable)) {
				MembVO memb = validationByAll(httpServletRequest);
				if (memb == null) {
					logger.debug("{}", "(memb == null)");

					return "phone/errorPage";
				}

				// 2018-12-27
				// query NCAS
				NCASResultData ncasData = nCASQueryManager.query(memb.getMembCtn());
				if (ncasData == null) {
					logger.error("failed to query NCAS data. deviceCtn({})", memb.getMembCtn());
					return null;
				}
				SubsInfo subsInfoNcas = ncasData.getSubsInfo();
				if (subsInfoNcas == null) {
					logger.error("failed to get SubsInfo data. deviceCtn({})", memb.getMembCtn());
					return null;
				}				
				
				SubsInfoVO subsInfo = eSBManager.getSubsInfo(subsInfoNcas.getSub_no(), memb.getProductNm());

				if (subsInfo == null) {
					logger.debug("{}", "(subsInfo == null)");

					return "phone/errorPage";
				}

				Date svcFrstStrtDttm = new Date();
				if (StringUtils.isNotEmpty(subsInfo.getSvcFrstStrtDttm())
						&& VALID_DATE_YYYYMMDD_REGEX.matcher(subsInfo.getSvcFrstStrtDttm()).find()
						&& !subsInfo.getSvcFrstStrtDttm().startsWith("9999")) {
					svcFrstStrtDttm = new SimpleDateFormat("yyyyMMdd").parse(subsInfo.getSvcFrstStrtDttm());
				}

				Calendar calendar = Calendar.getInstance();
				// calendar.setTime(memb.getJoinDt());
				calendar.setTime(svcFrstStrtDttm);
				
				//접속 디바이스 모델 찾기 20190128 kangjin
				String connDeviceModelId = getConnDeviceModel(memb);
				
				if("EL1".equals(connDeviceModelId)) {
					calendar.add(Calendar.YEAR, 2);
				}else {
					calendar.add(Calendar.YEAR, 1);
				}
				
				calendar.add(Calendar.DATE, -1);

				String svcNm = subsInfo.getSvcNm();
				String svcEndDttm1 = new SimpleDateFormat("yyyy년 MM월 dd일").format(calendar.getTime());
				String svcEndDttm2 = new SimpleDateFormat("yyyy.MM.dd").format(calendar.getTime());
				String payResvYn = StringUtils.defaultIfEmpty(StringUtils.equals("Y", memb.getPayResvYn()) ? "Y" : "N",
						"N");

				model.addAttribute("svcNm", svcNm);// 상품(요금제)명
				model.addAttribute("svcEndDttm1", svcEndDttm1);// 종료예정일(2018년 1월
																// 1일)
				model.addAttribute("svcEndDttm2", svcEndDttm2);// 종료예정일(2018.01.01)
				model.addAttribute("payResvYn", payResvYn);// 요금제예약신청
				
				
			} else {
				Calendar calendar = Calendar.getInstance();
				calendar.getTime();
				calendar.add(Calendar.YEAR, 1);

				String svcNm = "LTE) 태블릿/스마트기기 데이터 10GB";
				String svcEndDttm1 = new SimpleDateFormat("MM월 dd일").format(calendar.getTime());
				String svcEndDttm2 = new SimpleDateFormat("yyyy-MM-dd").format(calendar.getTime());

				model.addAttribute("svcNm", svcNm);// 상품(요금제)명
				model.addAttribute("svcEndDttm1", svcEndDttm1);// 종료예정일(2018년 1월
																// 1일)
				model.addAttribute("svcEndDttm2", svcEndDttm2);// 종료예정일(2018.1.1)
				model.addAttribute("payResvYn", "N");// 요금제예약신청
				
			}

			return "phone/limitApplyPlan";
		} catch (Exception e) {
			logger.debug("{}", e.getMessage());

			e.printStackTrace();
		}

		return "phone/errorPage";
	}

	/**
	 * 월정액요금제1 > 요금제신청 > 요금 납부 정보
	 */
	@Override
	public String getLimitEnterBillInfo(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
			Model model) {
		try {
			if (StringUtils.equalsIgnoreCase("Y", esbEnable)) {
				MembVO memb = validationByAll(httpServletRequest);
				if (memb == null) {
					logger.debug("{}", "(memb == null)");

					return "phone/errorPage";
				}
				
				// 2018-12-27
				// query NCAS
				NCASResultData ncasData = nCASQueryManager.query(memb.getMembCtn());
				if (ncasData == null) {
					logger.error("failed to query NCAS data. deviceCtn({})", memb.getMembCtn());
					return null;
				}
				SubsInfo subsInfoNcas = ncasData.getSubsInfo();
				if (subsInfoNcas == null) {
					logger.error("failed to get SubsInfo data. deviceCtn({})", memb.getMembCtn());
					return null;
				}				
				
				SubsInfoVO subsInfo = eSBManager.getSubsInfo(subsInfoNcas.getSub_no(), memb.getProductNm());
				if (subsInfo == null) {
					logger.debug("{}", "(subsInfo == null)");

					return "phone/errorPage";
				}

				String payResvYn = StringUtils.defaultIfEmpty(StringUtils.equals("Y", memb.getPayResvYn()) ? "Y" : "N",
						"N");
				
				model.addAttribute("pymMthdCd", StringUtils.defaultIfEmpty(subsInfo.getPymMthdCd(), ""));// 납부방법구분
				model.addAttribute("payResvYn", payResvYn);// 요금제예약신청
				
			} else {
				model.addAttribute("pymMthdCd", "CC");// 납부방법구분
				model.addAttribute("payResvYn", "N");// 요금제예약신청
			}

			return "phone/limitEnterBillInfo";
		} catch (Exception e) {
			logger.debug("{}", e.getMessage());

			e.printStackTrace();
		}

		return "phone/errorPage";
	}

	/**
	 * 월정액요금제1 > 이용약관
	 */
	@Override
	public String getLimitTerms(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
			Model model) {
		try {
			if (StringUtils.equalsIgnoreCase("Y", esbEnable)) {
				MembVO memb = validationByAll(httpServletRequest);
				if (memb == null) {
					logger.debug("{}", "(memb == null)");

					return "phone/errorPage";
				}

				TermsVO terms = pNDDao.selectOneCurrentServiceTerms();
				if (terms != null) {
					String termsTitle = terms.getTermsTitle();
					String termsCont = terms.getTermsCont();

					model.addAttribute("termsTitle", StringUtils.defaultIfEmpty(termsTitle, ""));
					model.addAttribute("termsCont", StringUtils.defaultIfEmpty(termsCont, ""));
				} else {
					model.addAttribute("termsTitle", "");
					model.addAttribute("termsCont", "");
				}
			} else {
				TermsVO terms = pNDDao.selectOneCurrentServiceTerms();
				if (terms != null) {
					String termsTitle = terms.getTermsTitle();
					String termsCont = terms.getTermsCont();

					model.addAttribute("termsTitle", StringUtils.defaultIfEmpty(termsTitle, ""));
					model.addAttribute("termsCont", StringUtils.defaultIfEmpty(termsCont, ""));
				} else {
					model.addAttribute("termsTitle", "");
					model.addAttribute("termsCont", "");
				}
			}

			return "phone/limitTerms";
		} catch (Exception e) {
			logger.debug("{}", e.getMessage());

			e.printStackTrace();
		}

		return "phone/errorPage";
	}

	// ######################################################################
	// 월정액요금제2
	// ######################################################################

	/**
	 * 월정액요금제2 > 메뉴
	 */
	@Override
	public String getUnlimitedMenu(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
			Model model) {
		try {
			if (StringUtils.equalsIgnoreCase("Y", esbEnable)) {
				MembVO memb = validationByAll(httpServletRequest);
				if (memb == null) {
					logger.debug("{}", "(memb == null)");

					return "phone/errorPage";
				}
			} else {
				// TODO
			}

			return "phone/unlimitMenu";
		} catch (Exception e) {
			logger.debug("{}", e.getMessage());

			e.printStackTrace();
		}

		return "phone/errorPage";
	}

	/**
	 * 월정액요금제2 > 가입정보
	 */
	@Override
	public String getUnlimitSubsInfo(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
			Model model) {
		try {
			if (StringUtils.equalsIgnoreCase("Y", esbEnable)) {
				MembVO memb = validationByAll(httpServletRequest);
				if (memb == null) {
					logger.debug("{}", "(memb == null)");

					return "phone/errorPage";
				}

				// 2018-12-27
				// query NCAS
				NCASResultData ncasData = nCASQueryManager.query(memb.getMembCtn());
				if (ncasData == null) {
					logger.error("failed to query NCAS data. deviceCtn({})", memb.getMembCtn());
					return null;
				}
				SubsInfo subsInfoNcas = ncasData.getSubsInfo();
				if (subsInfoNcas == null) {
					logger.error("failed to get SubsInfo data. deviceCtn({})", memb.getMembCtn());
					return null;
				}
				
				SubsInfoVO subsInfo = eSBManager.getSubsInfo(subsInfoNcas.getSub_no(), memb.getProductNm());
				if (subsInfo == null) {
					logger.debug("{}", "(subsInfo == null)");

					return "phone/errorPage";
				}

				String callYm = new SimpleDateFormat("yyyyMMdd").format(new Date());
				String ip = getIp();

				AmountDataVO amountData = eSBManager.getAmountData(subsInfoNcas.getSub_no(), memb.getProductNm(), callYm, ip);
				if (amountData == null) {
					logger.debug("{}", "(amountData == null)");

					return "phone/errorPage";
				}

				String alloValue = "0";
				if (VALID_NUMBER_REGEX.matcher(amountData.getAlloValue()).find()) {
					alloValue = String.valueOf((int) (Float.parseFloat(amountData.getAlloValue()) / 1024.0f));
				}
				String fxedFctr1 = subsInfo.getFxedFctr1();
				// String svcEndDttm = "";
				// if (StringUtils.isNotEmpty(subsInfo.getSvcEndDttm())
				// &&
				// VALID_DATE_YYYYMMDD_REGEX.matcher(subsInfo.getSvcEndDttm()).find()
				// && !subsInfo.getSvcEndDttm().startsWith("9999")) {
				// Date date = new
				// SimpleDateFormat("yyyyMMdd").parse(subsInfo.getSvcEndDttm());
				//
				// svcEndDttm = new SimpleDateFormat("yyyyMMdd").format(date);
				// }
				// String svcFrstStrtDttm = "";
				// if (StringUtils.isNotEmpty(subsInfo.getSvcFrstStrtDttm())
				// &&
				// VALID_DATE_YYYYMMDD_REGEX.matcher(subsInfo.getSvcFrstStrtDttm()).find()
				// && !subsInfo.getSvcFrstStrtDttm().startsWith("9999")) {
				// Date date = new
				// SimpleDateFormat("yyyyMMdd").parse(subsInfo.getSvcFrstStrtDttm());
				//
				// svcFrstStrtDttm = new
				// SimpleDateFormat("yyyy.MM.dd").format(date);
				// }

				String svcFrstStrtDttm1 = "";
				String svcFrstStrtDttm2 = "";
				if (StringUtils.isNotEmpty(subsInfo.getSvcFrstStrtDttm())
						&& VALID_DATE_YYYYMMDD_REGEX.matcher(subsInfo.getSvcFrstStrtDttm()).find()
						&& !subsInfo.getSvcFrstStrtDttm().startsWith("9999")) {
					Date date = new SimpleDateFormat("yyyyMMdd").parse(subsInfo.getSvcFrstStrtDttm());

					svcFrstStrtDttm1 = new SimpleDateFormat("yyyy년 MM월 dd일(E)").format(date);
					svcFrstStrtDttm2 = new SimpleDateFormat("yyyy년 MM월 dd일").format(date);

					// Calendar calendar = Calendar.getInstance();
					// calendar.setTime(date);
					//
					// String[] dayOfTheWeeks = { "일", "월", "화", "수", "목", "금",
					// "토" };
					// svcFrstStrtDttm = String.format("%s(%s)",
					// svcFrstStrtDttm,
					// dayOfTheWeeks[calendar.get(Calendar.DAY_OF_WEEK) - 1]);
				}

				String svcStrtDttm = "";
				String svcEndDttm = "";

				Date today = new Date();

				Calendar calendar = Calendar.getInstance();
				calendar.setTime(today);

				int min = calendar.getActualMinimum(Calendar.DAY_OF_MONTH);
				int max = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);

				svcStrtDttm = new SimpleDateFormat("yyyy년 MM월 ").format(today) + String.format("%02d일", min);
				svcEndDttm = new SimpleDateFormat("yyyy년 MM월 ").format(today) + String.format("%02d일", max);

				String svcNm = subsInfo.getSvcNm();
				String useValue = "0";
				if (VALID_NUMBER_REGEX.matcher(amountData.getUseValue()).find()) {
					useValue = String.valueOf((int) (Float.parseFloat(amountData.getUseValue()) / 1024.0f));
				}

				String remainingValue = "0";
				if (VALID_NUMBER_REGEX.matcher(alloValue).find() && VALID_NUMBER_REGEX.matcher(useValue).find()) {
					remainingValue = String.valueOf(Long.parseLong(alloValue) - Long.parseLong(useValue));
				}

				model.addAttribute("alloValue", StringUtils.defaultIfEmpty(alloValue, ""));// 제공량
				model.addAttribute("fxedFctr1", StringUtils.defaultIfEmpty(fxedFctr1, ""));// 약정기간
				model.addAttribute("svcEndDttm", StringUtils.defaultIfEmpty(svcEndDttm, ""));// 해지일자
				model.addAttribute("svcStrtDttm", StringUtils.defaultIfEmpty(svcStrtDttm, ""));// 개통일자
				model.addAttribute("svcFrstStrtDttm1", StringUtils.defaultIfEmpty(svcFrstStrtDttm1, ""));// 개통일자
				model.addAttribute("svcFrstStrtDttm2", StringUtils.defaultIfEmpty(svcFrstStrtDttm2, ""));// 개통일자
				model.addAttribute("svcNm", StringUtils.defaultIfEmpty(svcNm, ""));// 상품(요금제)명
				model.addAttribute("useValue", StringUtils.defaultIfEmpty(useValue, ""));// 사용량
				model.addAttribute("remainingValue", StringUtils.defaultIfEmpty(remainingValue, ""));// 남은
																										// 데이터
			} else {
				Calendar calendar = Calendar.getInstance();
				calendar.add(Calendar.YEAR, 1);

				String alloValue = "10000.00";
				String fxedFctr1 = "";
				String svcEndDttm = new SimpleDateFormat("MM월 dd일").format(calendar.getTime());
				String svcFrstStrtDttm1 = new SimpleDateFormat("yyyy년 MM월 dd일(E)").format(new Date());
				String svcFrstStrtDttm2 = new SimpleDateFormat("yyyy년 MM월 dd일").format(new Date());
				String svcNm = "LTE) 태블릿/스마트기기 데이터 10GB";
				String useValue = "0.00";
				String remainingValue = "10000.00";

				model.addAttribute("alloValue", alloValue);// 제공량
				model.addAttribute("fxedFctr1", fxedFctr1);// 약정기간
				model.addAttribute("svcEndDttm", svcEndDttm);// 해지일자
				model.addAttribute("svcFrstStrtDttm1", svcFrstStrtDttm1);// 개통일자
				model.addAttribute("svcFrstStrtDttm2", svcFrstStrtDttm2);// 개통일자
				model.addAttribute("svcNm", svcNm);// 상품(요금제)명
				model.addAttribute("useValue", useValue);// 사용량
				model.addAttribute("remainingValue", StringUtils.defaultIfEmpty(remainingValue, ""));// 남은
																										// 데이터
			}

			return "phone/unlimitSubsInfo";
		} catch (Exception e) {
			logger.debug("{}", e.getMessage());

			e.printStackTrace();
		}

		return "phone/errorPage";
	}

	/**
	 * 월정액요금제2 > 청구서조회 > 요금납부정보
	 */
	@Override
	public String getUnlimitPayInfo(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
			Model model) {
		try {
			if (StringUtils.equalsIgnoreCase("Y", esbEnable)) {
				MembVO memb = validationByAll(httpServletRequest);
				if (memb == null) {
					logger.debug("{}", "(memb == null)");

					return "phone/errorPage";
				}

				Calendar calendar = Calendar.getInstance();
				calendar.add(Calendar.MONTH, -1);

				String billTrgtYymm = new SimpleDateFormat("yyyyMM").format(calendar.getTime());

				// 2018-12-27
				// query NCAS
				NCASResultData ncasData = nCASQueryManager.query(memb.getMembCtn());
				if (ncasData == null) {
					logger.error("failed to query NCAS data. deviceCtn({})", memb.getMembCtn());
					return null;
				}
				SubsInfo subsInfoNcas = ncasData.getSubsInfo();
				if (subsInfoNcas == null) {
					logger.error("failed to get SubsInfo data. deviceCtn({})", memb.getMembCtn());
					return null;
				}	
				
				PayInfoVO payInfo = eSBManager.getPayInfo(subsInfoNcas.getSub_no(), memb.getProductNm(), billTrgtYymm);
				if (payInfo == null) {
					logger.debug("{}", "(payInfo == null)");

					return "phone/errorPage";
				}

				String addWdrwTrgtCntnt = "";
				if (StringUtils.isNotEmpty(payInfo.getAddWdrwTrgtCntnt())
						&& VALID_DATE_YYYYMMDD_REGEX.matcher(payInfo.getAddWdrwTrgtCntnt()).find()) {
					Date date = new SimpleDateFormat("yyyyMMdd").parse(payInfo.getAddWdrwTrgtCntnt());

					addWdrwTrgtCntnt = new SimpleDateFormat("yyyy년 MM월 dd일").format(date);
				}
				String bankNm = payInfo.getBankNm();
				String billAcntNo = payInfo.getBillAcntNo();
				String bltxtKdCd = payInfo.getBltxtKdCd();
				String bltxtKdNm = payInfo.getBltxtKdNm();
				String cardDepoNm = "";
				if (StringUtils.isNotEmpty(payInfo.getCardDepoNm())) {
					cardDepoNm = payInfo.getCardDepoNm().replaceAll("(?<=.{1}).", "*");
				}
				String duedDt = "";
				if (StringUtils.isNotEmpty(payInfo.getDuedDt())
						&& VALID_DATE_YYYYMMDD_REGEX.matcher(payInfo.getDuedDt()).find()) {
					Date date = new SimpleDateFormat("yyyyMMdd").parse(payInfo.getDuedDt());

					duedDt = new SimpleDateFormat("매 월 dd일").format(date);
				}
				String cardNm = payInfo.getCardNm();
				String pymMthdCd = payInfo.getPymMthdCd();
				String pymMthdNm = payInfo.getPymMthdNm();
				String svcNm = payInfo.getSvcNm();

				model.addAttribute("addWdrwTrgtCntnt", StringUtils.defaultIfEmpty(addWdrwTrgtCntnt, ""));// 출금일
				model.addAttribute("bankNm", StringUtils.defaultIfEmpty(bankNm, ""));// 은행명
				model.addAttribute("billAcntNo", StringUtils.defaultIfEmpty(billAcntNo, ""));// 청구계정번호
				model.addAttribute("bltxtKdCd", StringUtils.defaultIfEmpty(bltxtKdCd, ""));// 요금청구구분(ex)N)
				model.addAttribute("bltxtKdNm", StringUtils.defaultIfEmpty(bltxtKdNm, ""));// 요금청구구분명(ex)우편(엽서요약)
																							// 청구서)
				model.addAttribute("cardDepoNm", StringUtils.defaultIfEmpty(cardDepoNm, ""));// 예금(카드)주명
				model.addAttribute("cardNm", StringUtils.defaultIfEmpty(cardNm, ""));// 카드회사명
				model.addAttribute("duedDt", StringUtils.defaultIfEmpty(duedDt, ""));// 납기일
				model.addAttribute("pymMthdCd", StringUtils.defaultIfEmpty(pymMthdCd, ""));// 납부방법구분(ex)GR)
				model.addAttribute("pymMthdNm", StringUtils.defaultIfEmpty(pymMthdNm, ""));// 납부방법구분명(ex)지로)
				model.addAttribute("svcNm", StringUtils.defaultIfEmpty(svcNm, ""));// 상품(요금제)명
			} else {

				String addWdrwTrgtCntnt = new SimpleDateFormat("yyyy년 MM월 dd일").format(new Date());
				String bankNm = "";
				String billAcntNo = "";
				String bltxtKdCd = "";
				String bltxtKdNm = "휴대폰(간편 청구서)";
				String cardDepoNm = "노연대".replaceAll("(?<=.{1}).", "*");
				String duedDt = new SimpleDateFormat("yyyy년 MM월 dd일").format(new Date());
				String cardNm = "국민카드";
				String pymMthdCd = "";
				String pymMthdNm = "신용카드 자동이체";
				String svcNm = "LTE) 태블릿/스마트기기 데이터 10GB";

				model.addAttribute("addWdrwTrgtCntnt", addWdrwTrgtCntnt);// 출금일
				model.addAttribute("bankNm", bankNm);// 은행명
				model.addAttribute("billAcntNo", billAcntNo);// 청구계정번호
				model.addAttribute("bltxtKdCd", bltxtKdCd);// 요금청구구분(ex)N)
				model.addAttribute("bltxtKdNm", bltxtKdNm);// 요금청구구분명(ex)우편(엽서요약)
															// 청구서)
				model.addAttribute("cardDepoNm", cardDepoNm);// 예금(카드)주명
				model.addAttribute("cardNm", cardNm);// 카드회사명
				model.addAttribute("duedDt", duedDt);// 납기일
				model.addAttribute("pymMthdCd", pymMthdCd);// 납부방법구분(ex)GR)
				model.addAttribute("pymMthdNm", pymMthdNm);// 납부방법구분명(ex)지로)
				model.addAttribute("svcNm", svcNm);// 상품(요금제)명
			}

			return "phone/unlimitPayInfo";
		} catch (Exception e) {
			logger.debug("{}", e.getMessage());

			e.printStackTrace();
		}

		return "phone/errorPage";
	}
	
	/**
	 * 월정액요금제2 > 청구서조회 > 요금납부정보 > 납부방법변경
	 */
	@Override
	public String getUnlimitPayMethod(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
			Model model) {
		try {
			if (StringUtils.equalsIgnoreCase("Y", esbEnable)) {
				MembVO memb = validationByAll(httpServletRequest);
				if (memb == null) {
					logger.debug("{}", "(memb == null)");

					return "phone/errorPage";
				}

				Calendar calendar = Calendar.getInstance();
				//calendar.add(Calendar.MONTH, -1);

				String billTrgtYymm = new SimpleDateFormat("yyyyMM").format(calendar.getTime());

				// 2018-12-27
				// query NCAS
				NCASResultData ncasData = nCASQueryManager.query(memb.getMembCtn());
				if (ncasData == null) {
					logger.error("failed to query NCAS data. deviceCtn({})", memb.getMembCtn());
					return null;
				}
				SubsInfo subsInfoNcas = ncasData.getSubsInfo();
				if (subsInfoNcas == null) {
					logger.error("failed to get SubsInfo data. deviceCtn({})", memb.getMembCtn());
					return null;
				}
				String ip = getIp();
				
				PayMethodVO payMethod = eSBManager.getPayMethod(subsInfoNcas.getSub_no(), memb.getProductNm(), billTrgtYymm, ip);
				if (payMethod == null) {
					logger.debug("{}", "(payMethod == null)");

					return "phone/errorPage";
				}

				String addWdrwTrgtCntnt = "";
				if (StringUtils.isNotEmpty(payMethod.getAddWdrwTrgtCntnt())
						&& VALID_DATE_YYYYMMDD_REGEX.matcher(payMethod.getAddWdrwTrgtCntnt()).find()) {
					Date date = new SimpleDateFormat("yyyyMMdd").parse(payMethod.getAddWdrwTrgtCntnt());

					addWdrwTrgtCntnt = new SimpleDateFormat("yyyy년 MM월 dd일").format(date);
				}
				String bankNm = payMethod.getBankNm();
				String billAcntNo = payMethod.getBillAcntNo();
				String bltxtKdCd = payMethod.getBltxtKdCd();
				String bltxtKdNm = payMethod.getBltxtKdNm();
				String cardDepoNm = "";
				if (StringUtils.isNotEmpty(payMethod.getCardDepoNm())) {
					cardDepoNm = payMethod.getCardDepoNm().replaceAll("(?<=.{1}).", "*");
				}
				String duedDt = "";
				if (StringUtils.isNotEmpty(payMethod.getDuedDt())
						&& VALID_DATE_YYYYMMDD_REGEX.matcher(payMethod.getDuedDt()).find()) {
					Date date = new SimpleDateFormat("yyyyMMdd").parse(payMethod.getDuedDt());

					duedDt = new SimpleDateFormat("yyyy.MM.dd").format(date);
				}
				String custNm = payMethod.getCustNm();
				String cardNm = payMethod.getCardNm();
				String pymMthdCd = payMethod.getPymMthdCd();
				String pymMthdNm = payMethod.getPymMthdNm();
				String svcNm = payMethod.getSvcNm();

				// 청구서 수령형태
				// 청구서유형코드 : Y,T(e-mail청구서),L(e-mail문자청구서),M(문자청구서),기타(우편 청구서)
				// Y : e-mail청구서 - email
				// L : e-mail문자청구서 - email,ctn
				// M : 문자청구서 - ctn
				// W : 모바일고객센터 청구서 - ctn
				// B : 모바일 청구서 - ctn
				// A : E-mail모바일 청구서 - email
				// P : receive from cellphone
				// 기타 : 우편 청구서 - addr

				model.addAttribute("addWdrwTrgtCntnt", StringUtils.defaultIfEmpty(addWdrwTrgtCntnt, ""));// 출금일
				model.addAttribute("bankNm", StringUtils.defaultIfEmpty(bankNm, ""));// 은행명
				model.addAttribute("billAcntNo", StringUtils.defaultIfEmpty(billAcntNo, ""));// 청구계정번호
				model.addAttribute("bltxtKdCd", StringUtils.defaultIfEmpty(bltxtKdCd, ""));// 요금청구구분(ex)N)
				model.addAttribute("bltxtKdNm", StringUtils.defaultIfEmpty(bltxtKdNm, ""));// 요금청구구분명(ex)우편(엽서요약)
																							// 청구서)
				model.addAttribute("cardDepoNm", StringUtils.defaultIfEmpty(cardDepoNm, ""));// 예금(카드)주명
				model.addAttribute("cardNm", StringUtils.defaultIfEmpty(cardNm, ""));// 카드회사명
				model.addAttribute("custNm", StringUtils.defaultIfEmpty(custNm, ""));// 고객명
				model.addAttribute("duedDt", StringUtils.defaultIfEmpty(duedDt, ""));// 납기일
				model.addAttribute("pymMthdCd", StringUtils.defaultIfEmpty(pymMthdCd, ""));// 납부방법구분(ex)GR)
				model.addAttribute("pymMthdNm", StringUtils.defaultIfEmpty(pymMthdNm, ""));// 납부방법구분명(ex)지로)
				model.addAttribute("svcNm", StringUtils.defaultIfEmpty(svcNm, ""));// 상품(요금제)명
			} else {
				String addWdrwTrgtCntnt = new SimpleDateFormat("yyyy년 MM월 dd일").format(new Date());
				String bankNm = "";
				String billAcntNo = "";
				String bltxtKdCd = "";
				String bltxtKdNm = "휴대폰(간편 청구서)";
				String cardDepoNm = "노연대".replaceAll("(?<=.{1}).", "*");
				String duedDt = new SimpleDateFormat("yyyy년 MM월 dd일").format(new Date());
				String cardNm = "국민카드";
				String custNm = "노연대".replaceAll("(?<=.{1}).", "*");
				String pymMthdCd = "";
				String pymMthdNm = "신용카드 자동이체";
				String svcNm = "LTE) 태블릿/스마트기기 데이터 10GB";

				model.addAttribute("addWdrwTrgtCntnt", addWdrwTrgtCntnt);// 출금일
				model.addAttribute("bankNm", bankNm);// 은행명
				model.addAttribute("billAcntNo", billAcntNo);// 청구계정번호
				model.addAttribute("bltxtKdCd", bltxtKdCd);// 요금청구구분(ex)N)
				model.addAttribute("bltxtKdNm", bltxtKdNm);// 요금청구구분명(ex)우편(엽서요약)
															// 청구서)
				model.addAttribute("cardDepoNm", cardDepoNm);// 예금(카드)주명
				model.addAttribute("cardNm", cardNm);// 카드회사명
				model.addAttribute("custNm", custNm);// 고객명
				model.addAttribute("duedDt", duedDt);// 납기일
				model.addAttribute("pymMthdCd", pymMthdCd);// 납부방법구분(ex)GR)
				model.addAttribute("pymMthdNm", pymMthdNm);// 납부방법구분명(ex)지로)
				model.addAttribute("svcNm", svcNm);// 상품(요금제)명
			}

			return "phone/unlimitPayMethod";
		} catch (Exception e) {
			logger.debug("{}", e.getMessage());

			e.printStackTrace();
		}

		return "phone/errorPage";
	}

	private String getIp() {
		String hostAddr = "";

		try {

			Enumeration<NetworkInterface> nienum = NetworkInterface.getNetworkInterfaces();

			while (nienum.hasMoreElements()) {

				NetworkInterface ni = nienum.nextElement();

				Enumeration<InetAddress> kk= ni.getInetAddresses();

				while (kk.hasMoreElements()) {

					InetAddress inetAddress = kk.nextElement();

					if (!inetAddress.isLoopbackAddress() && 

					!inetAddress.isLinkLocalAddress() && 

					inetAddress.isSiteLocalAddress()) {

						 hostAddr = inetAddress.getHostAddress().toString();

					}

				}

			}

		} catch (SocketException e) {

			e.printStackTrace();

		} 
		return hostAddr;
	}

	/**
	 * 월정액요금제2 > 청구서조회 > 요금납부정보 > 청구유형변경
	 */
	@Override
	public String getUnlimitBillType(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
			Model model) {
		try {
			if (StringUtils.equalsIgnoreCase("Y", esbEnable)) {
				MembVO memb = validationByAll(httpServletRequest);
				if (memb == null) {
					logger.debug("{}", "(memb == null)");

					return "phone/errorPage";
				}

				// 2018-12-27
				// query NCAS
				NCASResultData ncasData = nCASQueryManager.query(memb.getMembCtn());
				if (ncasData == null) {
					logger.error("failed to query NCAS data. deviceCtn({})", memb.getMembCtn());
					return null;
				}
				SubsInfo subsInfoNcas = ncasData.getSubsInfo();
				if (subsInfoNcas == null) {
					logger.error("failed to get SubsInfo data. deviceCtn({})", memb.getMembCtn());
					return null;
				}
				
				BillTypeVO billType = eSBManager.getBillType(subsInfoNcas.getSub_no(), memb.getProductNm());
				if (billType == null) {
					logger.debug("{}", "(billType == null)");

					return "phone/errorPage";
				}

				String billAcntNo = billType.getBillAcntNo();
				String billEmailAddr1 = "";
				String billEmailAddr2 = "";
				if (StringUtils.isNotEmpty(billType.getBillEmailAddr())
						&& VALID_EMAIL_ADDRESS_REGEX.matcher(billType.getBillEmailAddr()).find()) {
					String[] temp = billType.getBillEmailAddr().split("@");

					billEmailAddr1 = temp[0];
					billEmailAddr2 = temp[1];
				}
				String bltxtKdCd = billType.getBltxtKdCd();
				String bltxtKdNm = billType.getBltxtKdNm();
				String bltxtKdValdStrtDt = "";
				if (StringUtils.isNotEmpty(billType.getBltxtKdValdStrtDt())
						&& VALID_DATE_YYYYMMDD_REGEX.matcher(billType.getBltxtKdValdStrtDt()).find()) {
					Date date = new SimpleDateFormat("yyyyMMdd").parse(billType.getBltxtKdValdStrtDt());

					bltxtKdValdStrtDt = new SimpleDateFormat("yyyy.MM.dd").format(date);
				}
				String scurMailRcpYn = billType.getScurMailRcpYn();
				List<BillReturnInfoVO> list = billType.getList();
				if (list != null && !list.isEmpty()) {
					List<BillReturnInfoVO> tmp = new ArrayList<BillReturnInfoVO>();
					for (int i = 0; i < list.size(); i++) {
						BillReturnInfoVO billReturnInfo = list.get(i);

						BillReturnInfoVO filterdBillReturnInfo = new BillReturnInfoVO();
						if (StringUtils.isNotEmpty(billReturnInfo.getBillAcntNo())) {
							filterdBillReturnInfo.setBillAcntNo(billReturnInfo.getBillAcntNo());// 청구계정번호
						}
						if (StringUtils.isNotEmpty(billReturnInfo.getBillTrgtYymm())) {
							Date date = new SimpleDateFormat("yyyyMM").parse(billReturnInfo.getBillTrgtYymm());

							filterdBillReturnInfo.setBillTrgtYymm(new SimpleDateFormat("yyyy.MM").format(date));// 청구년월
						}
						if (StringUtils.isNotEmpty(billReturnInfo.getDlvDt())
								&& VALID_DATE_YYYYMMDD_REGEX.matcher(billReturnInfo.getDlvDt()).find()) {
							Date date = new SimpleDateFormat("yyyyMMdd").parse(billReturnInfo.getDlvDt());

							filterdBillReturnInfo.setDlvDt(new SimpleDateFormat("yyyy.MM.dd").format(date));// 반송일
						}
						if (StringUtils.isNotEmpty(billReturnInfo.getRetnDvNm())) {
							filterdBillReturnInfo.setRetnDvNm(billReturnInfo.getRetnDvNm());// 청구서종류
						}
						if (StringUtils.isNotEmpty(billReturnInfo.getRetnRsnNm())) {
							filterdBillReturnInfo.setRetnRsnNm(billReturnInfo.getRetnRsnNm());// 반송사유
						}
						tmp.add(filterdBillReturnInfo);
					}
					list = tmp;
				}

				model.addAttribute("billAcntNo", StringUtils.defaultIfEmpty(billAcntNo, ""));// 청구계정번호
				model.addAttribute("billEmailAddr1", StringUtils.defaultIfEmpty(billEmailAddr1, ""));// 청구서이메일주소(아이디)
				model.addAttribute("billEmailAddr2", StringUtils.defaultIfEmpty(billEmailAddr2, ""));// 청구서이메일주소(도메인)
				model.addAttribute("bltxtKdCd", StringUtils.defaultIfEmpty(bltxtKdCd, ""));// 청구서유형코드
				model.addAttribute("bltxtKdNm", StringUtils.defaultIfEmpty(bltxtKdNm, ""));// 청구서유형명
				model.addAttribute("bltxtKdValdStrtDt", StringUtils.defaultIfEmpty(bltxtKdValdStrtDt, ""));// 청구서유형유효시작일자
				model.addAttribute("scurMailRcpYn", StringUtils.defaultIfEmpty(scurMailRcpYn, ""));// 보안메일수신여부
				model.addAttribute("list", list != null ? list : new ArrayList<BillReturnInfoVO>());// 청구서반송내역
			} else {
				List<BillReturnInfoVO> list = new ArrayList<BillReturnInfoVO>();
				{
					String billAcntNo = "";
					String billTrgtYymm = new SimpleDateFormat("yyyy.MM").format(new Date());
					String dlvDt = new SimpleDateFormat("yyyy.MM.dd").format(new Date());
					String retnDvNm = "휴대폰(간편 청구서)";
					String retnRsnNm = "반송사유";

					BillReturnInfoVO billReturnInfoVO = new BillReturnInfoVO();
					billReturnInfoVO.setBillAcntNo(billAcntNo);// 청구계정번호
					billReturnInfoVO.setBillTrgtYymm(billTrgtYymm);// 청구년월
					billReturnInfoVO.setDlvDt(dlvDt);// 청구서종류
					billReturnInfoVO.setRetnDvNm(retnDvNm);// 반송일
					billReturnInfoVO.setRetnRsnNm(retnRsnNm);// 반송사유

					list.add(billReturnInfoVO);
				}

				// bltxtKdCdNm

				String billAcntNo = "";
				String billEmailAddr1 = "notsurely";
				String billEmailAddr2 = "empal.com";
				String bltxtKdCd = "";
				String bltxtKdNm = "휴대폰(간편 청구서)";
				String bltxtKdValdStrtDt = new SimpleDateFormat("yyyy.MM.dd").format(new Date());
				String scurMailRcpYn = "Y";

				model.addAttribute("billAcntNo", billAcntNo);// 청구계정번호
				model.addAttribute("billEmailAddr1", billEmailAddr1);// 청구서이메일주소(아이디)
				model.addAttribute("billEmailAddr2", billEmailAddr2);// 청구서이메일주소(도메인)
				model.addAttribute("bltxtKdCd", bltxtKdCd);// 청구서유형코드
				model.addAttribute("bltxtKdNm", bltxtKdNm);// 청구서유형명
				model.addAttribute("bltxtKdValdStrtDt", bltxtKdValdStrtDt);// 청구서유형유효시작일자
				model.addAttribute("scurMailRcpYn", scurMailRcpYn);// 보안메일수신여부
				model.addAttribute("list", list);// 청구서반송내역
			}

			return "phone/unlimitBillType";
		} catch (Exception e) {
			logger.debug("{}", e.getMessage());

			e.printStackTrace();
		}

		return "phone/errorPage";
	}
	
	/**
	 * 월정액요금제2 > 청구서조회 > 상세내역조회 (청구서확인)
	 */
	@Override
	public String getUnlimitPayDetail(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
			Model model) {
		try {
			if (StringUtils.equalsIgnoreCase("Y", esbEnable)) {
				MembVO memb = validationByAll(httpServletRequest);
				if (memb == null) {
					logger.debug("{}", "(memb == null)");

					return "phone/errorPage";
				}

				Calendar calendar = Calendar.getInstance();
				calendar.add(Calendar.MONTH, -1);

				String billTrgtYymm = new SimpleDateFormat("yyyyMM").format(calendar.getTime());

				// 2018-12-27
				// query NCAS
				NCASResultData ncasData = nCASQueryManager.query(memb.getMembCtn());
				if (ncasData == null) {
					logger.error("failed to query NCAS data. deviceCtn({})", memb.getMembCtn());
					return null;
				}
				SubsInfo subsInfoNcas = ncasData.getSubsInfo();
				if (subsInfoNcas == null) {
					logger.error("failed to get SubsInfo data. deviceCtn({})", memb.getMembCtn());
					return null;
				}
				
				String ip = getIp();
				
				PayDetailVO payDetail = eSBManager.getPayDetail(subsInfoNcas.getSub_no(), memb.getProductNm(), billTrgtYymm, ip);
				if (payDetail == null) {
					logger.debug("{}", "(payDetail == null)");

					return "phone/errorPage";
				}

				String billAcntNo = payDetail.getBillAcntNo();
				String billAmt = "";
				if (StringUtils.isNotEmpty(payDetail.getBillAmt())
						&& VALID_NUMBER_REGEX.matcher(payDetail.getBillAmt()).find()) {
					billAmt = new DecimalFormat("###,###,###").format(Double.parseDouble(payDetail.getBillAmt()));
				}
				String spramt = "";
				if (StringUtils.isNotEmpty(payDetail.getSpramt())
						&& VALID_NUMBER_REGEX.matcher(payDetail.getSpramt()).find()) {
					spramt = new DecimalFormat("###,###,###").format(Double.parseDouble(payDetail.getSpramt()));
				}
				String totPymScdlAmt = "";
				if (StringUtils.isNotEmpty(payDetail.getTotPymScdlAmt())
						&& VALID_NUMBER_REGEX.matcher(payDetail.getTotPymScdlAmt()).find()) {
					totPymScdlAmt = new DecimalFormat("###,###,###")
							.format(Double.parseDouble(payDetail.getTotPymScdlAmt()));
				}
				String txamt = "";
				if (StringUtils.isNotEmpty(payDetail.getTxamt())
						&& VALID_NUMBER_REGEX.matcher(payDetail.getTxamt()).find()) {
					txamt = new DecimalFormat("###,###,###").format(Double.parseDouble(payDetail.getTxamt()));
				}
				String upaidChrg = "";
				if (StringUtils.isNotEmpty(payDetail.getUpaidChrg())
						&& VALID_NUMBER_REGEX.matcher(payDetail.getUpaidChrg()).find()) {
					upaidChrg = new DecimalFormat("###,###,###").format(Double.parseDouble(payDetail.getUpaidChrg()));
				}
				List<DiscountInfoVO> list = payDetail.getList();
				
				//접속 디바이스 모델 찾기 20190128 kangjin
				String connDeviceModelId = getConnDeviceModel(memb);
				
				// 닛산 LEAF 20190128 kangjin
				if("EL1".equals(connDeviceModelId)) {
					strRatePaymentPeriod = el1RatePaymentPeriod;
				}else {
					strRatePaymentPeriod = ratePaymentPeriod;
				}

				String dscntAmt = "0";
				{
					if (DateUtils.isExpireTime(memb.getJoinDt(), strRatePaymentPeriod)) {
						if (MembVO.PAY_RESV_Y.equals(memb.getPayResvYn())) {
							// LDZ0002087 (LTE) 태블릿/스마트기기 데이터 10GB 요금제 기본료 3000원
							// 할인
							dscntAmt = "3000";
						}
					} else {
						// LDZ0002086 (LTE) 태블릿/스마트기기 데이터 10GB 요금제 기본료 15000원 할인
						dscntAmt = "15000";
					}
					if (StringUtils.isNotEmpty(dscntAmt) && VALID_NUMBER_REGEX.matcher(dscntAmt).find()) {
						dscntAmt = new DecimalFormat("###,###,###").format(Double.parseDouble(dscntAmt));
					}
				}

				String dscntDtl = "";
				if (!StringUtils.equals(dscntAmt, "0")) {
					dscntDtl = "(LTE) 태블릿/스마트기기 데이터 10GB 요금제 기본료 할인";
				}

				model.addAttribute("billAcntNo", StringUtils.defaultIfEmpty(billAcntNo, ""));// 청구계정번호
				model.addAttribute("billAmt", StringUtils.defaultIfEmpty(billAmt, ""));// 이번달요금(1)
				model.addAttribute("spramt", StringUtils.defaultIfEmpty(spramt, ""));// 공급가액
				model.addAttribute("totPymScdlAmt", StringUtils.defaultIfEmpty(totPymScdlAmt, ""));// 이번달납부금액(1+2)
				model.addAttribute("txamt", StringUtils.defaultIfEmpty(txamt, ""));// 세액
				model.addAttribute("upaidChrg", StringUtils.defaultIfEmpty(upaidChrg, ""));// 미납요금(2)
				model.addAttribute("list", list != null ? list : new ArrayList<DiscountInfoVO>());// 할인내역
				model.addAttribute("dscntAmt", StringUtils.defaultIfEmpty(dscntAmt, ""));// 요금할인
				model.addAttribute("dscntDtl", StringUtils.defaultIfEmpty(dscntDtl, ""));// 할인내용
			} else {
				List<DiscountInfoVO> list = new ArrayList<DiscountInfoVO>();
				{
					String dscntAmt = "";// 할인금액
					String dscntDtl = "";// 할인내역

					DiscountInfoVO discountInfoVO = new DiscountInfoVO();
					discountInfoVO.setDscntAmt(dscntAmt);
					discountInfoVO.setDscntDtl(dscntDtl);

					list.add(discountInfoVO);
				}

				String billAcntNo = "";
				String billAmt = new DecimalFormat("###,###,###").format(Double.parseDouble("22000"));
				String spramt = new DecimalFormat("###,###,###").format(Double.parseDouble("22000"));
				String totPymScdlAmt = new DecimalFormat("###,###,###").format(Double.parseDouble("22000"));
				String txamt = new DecimalFormat("###,###,###").format(Double.parseDouble("0"));
				String upaidChrg = new DecimalFormat("###,###,###").format(Double.parseDouble("0"));
				String dscntAmt = new DecimalFormat("###,###,###").format(Double.parseDouble("0"));
				String dscntDtl = "기본료 할인";

				model.addAttribute("billAcntNo", billAcntNo);// 청구계정번호
				model.addAttribute("billAmt", billAmt);// 이번달요금(1)
				model.addAttribute("spramt", spramt);// 공급가액
				model.addAttribute("totPymScdlAmt", totPymScdlAmt);// 이번달납부금액(1+2)
				model.addAttribute("txamt", txamt);// 세액
				model.addAttribute("upaidChrg", upaidChrg);// 미납요금(2)
				model.addAttribute("list", list);// 할인내역
				model.addAttribute("dscntAmt", dscntAmt);// 요금할인
				model.addAttribute("dscntDtl", dscntDtl);// 할인내용
			}

			return "phone/unlimitPayDetail";
		} catch (Exception e) {
			logger.debug("{}", e.getMessage());

			e.printStackTrace();
		}

		return "phone/errorPage";
	}

	/**
	 * 월정액요금제2 > 서비스변경내역 > 서비스일시정지내역
	 */
	@Override
	public String getUnlimitPauseHistory(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
			Model model) {
		try {
			if (StringUtils.equalsIgnoreCase("Y", esbEnable)) {
				MembVO memb = validationByAll(httpServletRequest);
				if (memb == null) {
					logger.debug("{}", "(memb == null)");

					return "phone/errorPage";
				}
				
				SubsInfo subsInfo = checkByCtn(httpServletRequest);
				
				if (subsInfo == null) {
					logger.debug("{}", "(subsInfo == null)");

					return "errorPage";
				}
				
				boolean isPause = false;
				if (subsInfo.getCtn_stus_code().equals("S")) {
				
					isPause = true;
				}

				model.addAttribute("isPause", isPause);// 일시정지여부
				
				PauseHistoryCollectionVO pauseHistoryCollection = eSBManager.getPauseHistory(subsInfo.getSub_no(),
						memb.getProductNm());
				if (pauseHistoryCollection == null) {
					logger.debug("{}", "(pauseHistoryCollection == null)");

					return "phone/errorPage";
				} else {
					List<PauseHistoryVO> list = pauseHistoryCollection.getList();
					if (list != null && !list.isEmpty()) {
						List<PauseHistoryVO> tmp = new ArrayList<PauseHistoryVO>();
						for (int i = 0; i < list.size(); i++) {
							PauseHistoryVO pauseHistory = list.get(i);

							PauseHistoryVO filterdPauseHistory = new PauseHistoryVO();

							String susDate = "";
							if (StringUtils.isNotEmpty(pauseHistory.getSusDate())
									&& VALID_DATE_YYYYMMDD_REGEX.matcher(pauseHistory.getSusDate()).find()) {
								Date date = new SimpleDateFormat("yyyyMMdd").parse(pauseHistory.getSusDate());

								susDate = new SimpleDateFormat("yyyy.MM.dd").format(date);
							}
							String rspDate = "";
							if (StringUtils.isNotEmpty(pauseHistory.getRspDate())
									&& VALID_DATE_YYYYMMDD_REGEX.matcher(pauseHistory.getRspDate()).find()) {
								Date date = new SimpleDateFormat("yyyyMMdd").parse(pauseHistory.getRspDate());

								rspDate = new SimpleDateFormat("yyyy.MM.dd").format(date);
							}
							String rsnNm = StringUtils.defaultIfEmpty(pauseHistory.getRsnNm(), "");
							String prodNm = StringUtils.defaultIfEmpty(pauseHistory.getProdNm(), "");
							String susRsnNm = StringUtils.defaultIfEmpty(pauseHistory.getSusRsnNm(), "");
							String susDays = StringUtils.defaultIfEmpty(pauseHistory.getSusDays(), "");
							String term = "";
							if (StringUtils.isNotEmpty(susDate)) {
								term = susDate + " ~ ";
							}
							if (StringUtils.isNotEmpty(rspDate)) {
								term += rspDate;
							}

							filterdPauseHistory.setSusDate(susDate);
							filterdPauseHistory.setRspDate(rspDate);
							filterdPauseHistory.setRsnNm(rsnNm);
							filterdPauseHistory.setProdNm(prodNm);
							filterdPauseHistory.setSusRsnNm(susRsnNm);
							filterdPauseHistory.setSusDays(susDays);
							filterdPauseHistory.setTerm(term);

							tmp.add(filterdPauseHistory);
						}
						list = tmp;

						pauseHistoryCollection.setList(list);
					}
				}

				List<PauseHistoryVO> list = pauseHistoryCollection.getList();

				model.addAttribute("list", list != null ? list : new ArrayList<PauseHistoryVO>());// 일시정지내역
			} else {
				List<PauseHistoryVO> list = new ArrayList<PauseHistoryVO>();
				{
					String susDate = new SimpleDateFormat("yyyy.MM.dd").format(new Date());// 일시정지일자
					String rspDate = new SimpleDateFormat("yyyy.MM.dd").format(new Date());// 정지해제일자
																							// 혹은
																							// 정지해제예약일자
					String rsnNm = "";// 요청구분명
					String prodNm = "LTE) 태블릿/스마트기기 데이터 10GB";// 서비스명
					String susRsnNm = "일시정지상세사유명";// 일시정지상세사유명
					String susDays = "1";// 일시정지일수
					String term = susDate + " ~ " + rspDate;// 기간

					PauseHistoryVO pauseHistoryVO = new PauseHistoryVO();
					pauseHistoryVO.setSusDate(susDate);
					pauseHistoryVO.setRspDate(rspDate);
					pauseHistoryVO.setRsnNm(rsnNm);
					pauseHistoryVO.setProdNm(prodNm);
					pauseHistoryVO.setSusRsnNm(susRsnNm);
					pauseHistoryVO.setTerm(term);
					pauseHistoryVO.setSusDays(susDays);

					list.add(pauseHistoryVO);
				}

				model.addAttribute("list", list);// 일시정지내역
			}

			return "phone/unlimitPauseHistory";
		} catch (Exception e) {
			logger.debug("{}", e.getMessage());

			e.printStackTrace();
		}

		return "phone/errorPage";
	}
	
	/**
	 * 월정액요금제2 > 이용약관
	 */
	@Override
	public String getUnlimitTerms(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
			Model model) {
		try {
			if (StringUtils.equalsIgnoreCase("Y", esbEnable)) {
				MembVO memb = validationByAll(httpServletRequest);
				if (memb == null) {
					logger.debug("{}", "(memb == null)");

					return "phone/errorPage";
				}

				TermsVO terms = pNDDao.selectOneCurrentServiceTerms();
				if (terms != null) {
					String termsTitle = terms.getTermsTitle();
					String termsCont = terms.getTermsCont();

					model.addAttribute("termsTitle", StringUtils.defaultIfEmpty(termsTitle, ""));
					model.addAttribute("termsCont", StringUtils.defaultIfEmpty(termsCont, ""));
				} else {
					model.addAttribute("termsTitle", "");
					model.addAttribute("termsCont", "");
				}
			} else {
				TermsVO terms = pNDDao.selectOneCurrentServiceTerms();
				if (terms != null) {
					String termsTitle = terms.getTermsTitle();
					String termsCont = terms.getTermsCont();

					model.addAttribute("termsTitle", StringUtils.defaultIfEmpty(termsTitle, ""));
					model.addAttribute("termsCont", StringUtils.defaultIfEmpty(termsCont, ""));
				} else {
					model.addAttribute("termsTitle", "");
					model.addAttribute("termsCont", "");
				}
			}

			return "phone/unlimitTerms";
		} catch (Exception e) {
			logger.debug("{}", e.getMessage());

			e.printStackTrace();
		}

		return "phone/errorPage";
	}

	/**
	 * 월정액요금제2 > 서비스변경내역 > 서비스일시정지신청
	 */
	@Override
	public String getUnlimitRequestPause(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
			Model model) {
		try {
			if (StringUtils.equalsIgnoreCase("Y", esbEnable)) {
				MembVO memb = validationByAll(httpServletRequest);
				if (memb == null) {
					logger.debug("{}", "(memb == null)");

					return "phone/errorPage";
				}
			} else {

			}

			return "phone/unlimitRequestPause";
		} catch (Exception e) {
			logger.debug("{}", e.getMessage());

			e.printStackTrace();
		}

		return "phone/errorPage";
	}

	// ######################################################################
	// ######################################################################

	private static String createResultJson(String resultCode, String resultMsg) {
		try {
			HashMap<String, Object> result = new HashMap<String, Object>();
			result.put("resultCode", resultCode);
			result.put("resultMsg", resultMsg);

			return new ObjectMapper().writeValueAsString(result);
		} catch (Exception e) {
			logger.debug("{}", e.getMessage());

			e.printStackTrace();
		}
		return "{ \"resultCode\":\"" + resultCode + "\", \"resultMsg\":\"" + resultMsg + "\" }";
	}

	// 납부방법변경
	@SuppressWarnings("unused")
	@Override
	public String getChangeMethod(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
			String body) {
		HashMap<String, Object> result = new HashMap<String, Object>();
		try {
			if (StringUtils.equalsIgnoreCase("Y", esbEnable)) {
				// 기존에 신용카드가 아닌 상태에서 신용카드로 변경하는 경우에 한함
				HashMap<String, Object> parameter = new ObjectMapper().readValue(body,
						new TypeReference<HashMap<String, Object>>() {
						});

				MembVO memb = validationByAll(httpServletRequest);
				if (memb == null) {
					logger.debug("{}", "(memb == null)");

					return createResultJson("9999", "데이터 처리 중 오류가 발생하였습니다.");
				}

				// 2018-12-27
				// query NCAS
				NCASResultData ncasData = nCASQueryManager.query(memb.getMembCtn());
				if (ncasData == null) {
					logger.error("failed to query NCAS data. deviceCtn({})", memb.getMembCtn());
					return null;
				}
				SubsInfo subsInfoNcas = ncasData.getSubsInfo();
				if (subsInfoNcas == null) {
					logger.error("failed to get SubsInfo data. deviceCtn({})", memb.getMembCtn());
					return null;
				}	
				
				RetrieveCustSvcEntrInfoBDServiceStub.DsOutputOutVO dsOutputOutVO = eSBManager
						.getCustomerInfo(subsInfoNcas.getSub_no(), memb.getProductNm());
				if (dsOutputOutVO == null) {
					logger.debug("{}", "(dsOutputOutVO == null)");

					return createResultJson("9999", "데이터 처리 중 오류가 발생하였습니다.");
				}

				// aceno 가입계약번호 VARCHAR2 12 Y 단수
				// acntOwnrNo 소유자번호 VARCHAR2 13 N 단수 주민번호,사업자번호,외국인번호,외국인거소번호등
				// actInd actInd VARCHAR2 1 Y 단수 null(후순위만 Y이나 후순위 삭제함)
				// bankAcntNo 은행계좌번호 VARCHAR2 16 Y 단수 자동이체인 경우 필수
				// bankCd 은행코드 VARCHAR2 3 Y 단수 자동이체인 경우 필수
				// bankNm 은행명 VARCHAR2 60 Y 단수
				// billAcntNo 청구계정번호 VARCHAR2 12 N 단수 납부계정을 변경하려는 청구계정번호
				// bltxtKdCd 청구유형 VARCHAR2 1 N 단수 고객유형이 GG(LG그룹사_관계사)가 아닌 경우
				// 청구서유형이
				// Shot-mail(S)/SMS(M)/모바일(W) 면 지로로 변경 불가
				// cardNo 카드번호 VARCHAR2 16 Y 단수 신용카드인 경우 필수
				// cardValdEndYymm 유효일자 VARCHAR2 6 Y 단수 "YYYYMM신용카드인 경우 필수"
				// cdcmpCd 카드사코드 VARCHAR2 2 Y 단수 신용카드인 경우 필수
				// cdcmpNm 카드사 VARCHAR2 360 Y 단수
				// custDvCd 납부자고객구분 VARCHAR2 240 N 단수 개인:I,법인:G
				// custKdCd 납부자고객유형 VARCHAR2 240 N 단수 VW_CS_HB_CUST_KD_01 참조
				// entrNo 가입번호 VARCHAR2 12 N 단수 가족사랑,해피투게더의 경우 대표가입자만이
				// 납부계정을변경할수있어필수항목임
				// newInd newInd VARCHAR2 1 Y 단수 null(향후 사용위해 남겨둔 필드)
				// persIdind 외국인 여부 VARCHAR2 1 Y 단수 외국인:Y
				// ppayAcntYn 선불계정 구분 VARCHAR2 1 Y 단수 선불:Y
				// pymAcntNo 납부계정번호 VARCHAR2 12 Y 단수 없으면 NULL
				// pymCustNm 납부자 고객 명 VARCHAR2 360 N 단수 외국인 영어명 가능
				// pymManCustNo 납부자 고객 번호 VARCHAR2 15 Y 단수 사용안함
				// pymMthdCd 납부방법 VARCHAR2 2 N 단수
				// "자동이체:CM,신용카드:CC,지로:GR,납부정보변경(CM->CM,CC->CC):즉시변경
				// 납부방법변경(그외):예약변경"
				// pymMthdNm 납부방법명 VARCHAR2 30 Y 단수 자동이체,신용카드,지로

				String aceno = null;
				String acntOwnrNo = (String) parameter.get("acntOwnrNo");// 필수
				String actInd = null;
				String bankAcntNo = null;
				String bankCd = null;
				String bankNm = null;
				String billAcntNo = dsOutputOutVO.getBillAcntNo();// 필수
				String bltxtKdCd = dsOutputOutVO.getBltxtKdCd();// 필수
				String cardNo = (String) parameter.get("cardNo");
				String cardValdEndYymm = (String) parameter.get("cardValdEndYymm");
				String cdcmpCd = (String) parameter.get("cdcmpCd");
				String cdcmpNm = null;
				String custDvCd = (String) parameter.get("custDvCd");// 필수
				String custKdCd = "I".equals(custDvCd) ? "II" : "GE";// 필수
				String entrNo = memb.getMembNo();// 필수
				String newInd = null;
				String persIdind = (String) parameter.get("persIdind");
				String ppayAcntYn = null;
				String pymAcntNo = null;
				String pymCustNm = (String) parameter.get("pymCustNm");// 필수
				String pymManCustNo = null;
				String pymMthdCd = "CC";// 필수
				String pymMthdNm = null;

				PayMethodResultVO payMethodResultVO = eSBManager.updatePayMethod(aceno, acntOwnrNo, actInd, bankAcntNo,
						bankCd, bankNm, billAcntNo, bltxtKdCd, cardNo, cardValdEndYymm, cdcmpCd, cdcmpNm, custDvCd,
						custKdCd, entrNo, newInd, persIdind, ppayAcntYn, pymAcntNo, pymCustNm, pymManCustNo, pymMthdCd,
						pymMthdNm);
				if (payMethodResultVO == null) {
					logger.debug("{}", "(payMethodResultVO == null)");

					return createResultJson("9999", "데이터 처리 중 오류가 발생하였습니다.");
				}

				if (!"Y".equals(payMethodResultVO.getMsgCode())) {
					logger.debug("failed to updatePayMethod()");

					return createResultJson("9999", payMethodResultVO.getMsgText());
				}

				result.put("resultCode", "0000");
				result.put("resultMsg", "성공");

			} else {
				HashMap<String, Object> parameter = new ObjectMapper().readValue(body,
						new TypeReference<HashMap<String, Object>>() {
						});

				String acntOwnrNo = (String) parameter.get("acntOwnrNo");// 필수
				String cardNo = (String) parameter.get("cardNo");
				String cardValdEndYymm = (String) parameter.get("cardValdEndYymm");
				String cdcmpCd = (String) parameter.get("cdcmpCd");
				String custDvCd = (String) parameter.get("custDvCd");// 필수
				String custKdCd = "I".equals(custDvCd) ? "II" : "GE";// 필수
				String persIdind = (String) parameter.get("persIdind");
				String pymCustNm = (String) parameter.get("pymCustNm");// 필수
				String pymMthdCd = "CC";// 필수

				result.put("resultCode", "0000");
				result.put("resultMsg", "성공");
			}

			return new ObjectMapper().writeValueAsString(result);
		} catch (Exception e) {
			logger.debug("{}", e.getMessage());

			e.printStackTrace();
		}

		return createResultJson("9999", "데이터 처리 중 오류가 발생하였습니다.");
	}

	// 청구유형변경
	@SuppressWarnings("unused")
	@Override
	public String getChangeType(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
			String body) {
		HashMap<String, Object> result = new HashMap<String, Object>();
		try {
			if (StringUtils.equalsIgnoreCase("Y", esbEnable)) {
				HashMap<String, Object> parameter = new ObjectMapper().readValue(body,
						new TypeReference<HashMap<String, Object>>() {
						});

				MembVO memb = validationByAll(httpServletRequest);
				if (memb == null) {
					logger.debug("{}", "(memb == null)");

					return createResultJson("9999", "데이터 처리 중 오류가 발생하였습니다.");
				}

				// 2018-12-27
				// query NCAS
				NCASResultData ncasData = nCASQueryManager.query(memb.getMembCtn());
				if (ncasData == null) {
					logger.error("failed to query NCAS data. deviceCtn({})", memb.getMembCtn());
					return null;
				}
				SubsInfo subsInfoNcas = ncasData.getSubsInfo();
				if (subsInfoNcas == null) {
					logger.error("failed to get SubsInfo data. deviceCtn({})", memb.getMembCtn());
					return null;
				}	
				
				RetrieveCustSvcEntrInfoBDServiceStub.DsOutputOutVO dsOutputOutVO = eSBManager
						.getCustomerInfo(subsInfoNcas.getSub_no(), memb.getProductNm());
				if (dsOutputOutVO == null) {
					logger.debug("{}", "(dsOutputOutVO == null)");

					return createResultJson("9999", "데이터 처리 중 오류가 발생하였습니다.");
				}

				RetrieveCustInfoSvcServiceStub.DsConfldsOutVO dsConfldsOut = eSBManager.getBillInfo(subsInfoNcas.getSub_no());
				if (dsConfldsOut == null) {
					logger.debug("{}", "(dsConfldsOut == null)");

					return createResultJson("9999", "데이터 처리 중 오류가 발생하였습니다.");
				}

				// dsConfldsIn
				// userId 사용자ID VARCHAR2 100 Y 단수
				// userNm 사용자명 VARCHAR2 500 Y 단수
				// mrktCd 마켓코드 VARCHAR2 10 Y 단수
				// userOrgCd 사용자기관코드 VARCHAR2 10 Y 단수
				// userDlrCd 사용자대리점코드 VARCHAR2 10 Y 단수
				// userWorkDlrCd 작업대리점코드 VARCHAR2 10 Y 단수
				// userDlrNm 사용자대리점명 VARCHAR2 100 Y 단수
				// lockMode lockMode VARCHAR2 10 N 단수 B - 고정
				// cnId cnId VARCHAR2 10 Y 단수
				// directive directive VARCHAR2 10 Y 단수
				// runDate runDate VARCHAR2 8 Y 단수
				// runDateDtm runDateDtm VARCHAR2 16 Y 단수
				// transactionMode transactionMode VARCHAR2 10 Y 단수
				// userWorkDlrNm 작업대리점명 VARCHAR2 100 Y 단수
				// entrNo 가입번호 VARCHAR2 15 Y 단수
				// entrSysUpdateDate entrSysUpdateDate VARCHAR2 16 Y 단수
				// entrDlUpdateStamp entrDlUpdateStamp VARCHAR2 16 Y 단수
				// aceno 가입계약번호 VARCHAR2 15 Y 단수
				// cntcSysUpdateDate cntcSysUpdateDate VARCHAR2 16 Y 단수
				// cntcDlUpdateStamp cntcDlUpdateStamp VARCHAR2 16 Y 단수
				// billAcntNo 청구계정번호 VARCHAR2 15 N 단수
				// billSysUpdateDate billSysUpdateDate VARCHAR2 16 N 단수
				// billDlUpdateStamp billDlUpdateStamp VARCHAR2 16 N 단수

				String userId = null;
				String userNm = null;
				String mrktCd = null;
				String userOrgCd = null;
				String userDlrCd = null;
				String userWorkDlrCd = null;
				String userDlrNm = null;
				String lockMode = "B";// 필수
				String cnId = null;
				String directive = null;
				String runDate = null;
				String runDateDtm = null;
				String transactionMode = null;
				String userWorkDlrNm = null;
				String entrNo = null;
				String entrSysUpdateDate = null;
				String entrDlUpdateStamp = null;
				String aceno = null;
				String cntcSysUpdateDate = null;
				String cntcDlUpdateStamp = null;
				String billAcntNo = dsOutputOutVO.getBillAcntNo();// 필수
				String billSysUpdateDate = dsConfldsOut.getBillSysUpdateDate();// 필수
				String billDlUpdateStamp = dsConfldsOut.getBillDlUpdateStamp();// 필수

				// dsReqIn
				// billAcntNo 청구계정번호 VARCHAR2 9 N 단수
				// bltxtKdCdNm 청구서유형코드명 VARCHAR2 80 N 단수
				// dscntSvcCd 할인서비스코드 VARCHAR2 10 Y 단수
				// dscntSvcNm 할인서비스명 VARCHAR2 240 Y 단수
				// bltxtKdValdStrtDt 청구서유형유효시작일자 VARCHAR2 14 Y 단수
				// bltxtKdValdEndDt 청구서유형유효종료일자 VARCHAR2 14 Y 단수
				// scurMailRcpYn 보안메일수신여부 VARCHAR2 1 Y 단수
				// bltxtRcpProdNo 청구서수신상품번호 VARCHAR2 20 Y 단수
				// billEmailAddr 청구이메일주소 VARCHAR2 50 Y 단수
				// copyReal copyReal VARCHAR2 1 Y 단수
				// prodNo 상품번호 VARCHAR2 20 Y 단수
				// emailCopyCustYn emailCopyCustYn VARCHAR2 1 Y 단수
				// chgBlAddrYn chgBlAddrYn VARCHAR2 1 Y 단수
				// billAddrSeqno 청구주소누적번호 VARCHAR2 15 Y 단수
				// custAddrZip 고객주소우편번호 VARCHAR2 7 Y 단수
				// custVilgAbvAddr 고객동이상주소 VARCHAR2 500 Y 단수
				// custVilgBlwAddr 고객동이하주소 VARCHAR2 500 Y 단수
				// imsi01 imsi01 VARCHAR2 500 Y 단수
				// imsi02 imsi02 VARCHAR2 500 Y 단수
				// imsi03 imsi03 VARCHAR2 500 Y 단수
				// bltxtChrgrId am직배 담당자 ID VARCHAR2 100 Y 단수 청구서 유형이 am
				// 직배(G)인경우 set

				// Y - e-mail(상세) 청구서
				// P - 휴대폰(간편청구서)
				String billAcntNo_ = dsOutputOutVO.getBillAcntNo();// 필수
				String bltxtKdCdNm = (String) parameter.get("bltxtKdCdNm");// 필수
				String dscntSvcCd = null;
				String dscntSvcNm = null;
				String bltxtKdValdStrtDt = new SimpleDateFormat("yyyyMMdd").format(new Date());
				String bltxtKdValdEndDt = null;
				String scurMailRcpYn = (String) parameter.get("scurMailRcpYn");
				String pn = (String) parameter.get("pn");
				String first = "";
				String last = "";
				String fullPn;
				if (pn.length() > 3) {
					first = pn.substring(0,3);
					last = pn.substring(3,pn.length());
				}
				fullPn = first+"0"+last;
				String bltxtRcpProdNo;
				String billEmailAddr;
				if(bltxtKdCdNm.equals("Y")){
					bltxtRcpProdNo = null;
					billEmailAddr = (String) parameter.get("billEmailAddr");
				}else {
					bltxtRcpProdNo = fullPn;
					billEmailAddr = null;
				}
				String copyReal = null;
				String prodNo = dsOutputOutVO.getProdNo();
				String emailCopyCustYn = (StringUtils.equals((String) parameter.get("emailCopyCustYn"), "Y")
						? billEmailAddr : null); // 단체는 변경불가 with prodNo
				String chgBlAddrYn = null;
				String billAddrSeqno = null;
				String custAddrZip = null;
				String custVilgAbvAddr = null;
				String custVilgBlwAddr = null;
				String imsi01 = null;
				String imsi02 = null;
				String imsi03 = null;
				String bltxtChrgrId = null;

				BillTypeResultVO billTypeResultVO = eSBManager.updateBillType(userId, userNm, mrktCd, userOrgCd,
						userDlrCd, userWorkDlrCd, userDlrNm, lockMode, cnId, directive, runDate, runDateDtm,
						transactionMode, userWorkDlrNm, entrNo, entrSysUpdateDate, entrDlUpdateStamp, aceno,
						cntcSysUpdateDate, cntcDlUpdateStamp, billAcntNo, billSysUpdateDate, billDlUpdateStamp,
						//
						billAcntNo_, bltxtKdCdNm, dscntSvcCd, dscntSvcNm, bltxtKdValdStrtDt, bltxtKdValdEndDt,
						scurMailRcpYn, bltxtRcpProdNo, billEmailAddr, copyReal, prodNo, emailCopyCustYn, chgBlAddrYn,
						billAddrSeqno, custAddrZip, custVilgAbvAddr, custVilgBlwAddr, imsi01, imsi02, imsi03,
						bltxtChrgrId);
				if (billTypeResultVO == null) {
					logger.debug("{}", "(billTypeResultVO == null)");

					return createResultJson("9999", "데이터 처리 중 오류가 발생하였습니다.");
				}

				if (!"Y".equals(billTypeResultVO.getMsgCode())) {
					logger.debug("failed to updateBillType");

					return createResultJson("9999", billTypeResultVO.getMsgText());
				}

				String custNm = StringUtils.defaultIfEmpty(dsOutputOutVO.getCustNm(), "");
				String bltxtKdNm = "";
				if ("Y".equalsIgnoreCase(bltxtKdNm)) {
					bltxtKdNm = "e-mail(상세) 청구서";
				} else if ("P".equalsIgnoreCase(bltxtKdNm)) {
					bltxtKdNm = "휴대폰(간편청구서)";
				}

				result.put("resultCode", "0000");
				result.put("resultMsg", "성공");
				result.put("custNm", custNm);
				result.put("bltxtKdNm", bltxtKdNm);

			} else {
				HashMap<String, Object> parameter = new ObjectMapper().readValue(body,
						new TypeReference<HashMap<String, Object>>() {
						});

				String bltxtKdCdNm = (String) parameter.get("bltxtKdCdNm");// 필수(SMS:M,
																			// 모바일:W,
																			// 메일:S)
				String scurMailRcpYn = (String) parameter.get("scurMailRcpYn");
				String billEmailAddr = (String) parameter.get("billEmailAddr");
				String emailCopyCustYn = (String) parameter.get("emailCopyCustYn");

				String custNm = "이제이";
				String bltxtKdNm = "휴대폰(간편청구서)";

				result.put("resultCode", "0000");
				result.put("resultMsg", "성공");
				result.put("custNm", custNm);
				result.put("bltxtKdNm", bltxtKdNm);
			}

			return new ObjectMapper().writeValueAsString(result);
		} catch (Exception e) {
			logger.debug("{}", e.getMessage());

			e.printStackTrace();
		}

		return createResultJson("9999", "데이터 처리 중 오류가 발생하였습니다.");
	}

	// 일시정지
	@SuppressWarnings("unused")
	@Override
	public String getStopService(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
			String body) {
		HashMap<String, Object> result = new HashMap<String, Object>();
		try {
			if (StringUtils.equalsIgnoreCase("Y", esbEnable)) {
				HashMap<String, Object> parameter = new ObjectMapper().readValue(body,
						new TypeReference<HashMap<String, Object>>() {
						});

				MembVO memb = validationByAll(httpServletRequest);
				if (memb == null) {
					logger.debug("{}", "(memb == null)");

					return createResultJson("9999", "데이터 처리 중 오류가 발생하였습니다.");
				}

				// 2018-12-27
				// query NCAS
				NCASResultData ncasData = nCASQueryManager.query(memb.getMembCtn());
				if (ncasData == null) {
					logger.error("failed to query NCAS data. deviceCtn({})", memb.getMembCtn());
					return null;
				}
				SubsInfo subsInfoNcas = ncasData.getSubsInfo();
				if (subsInfoNcas == null) {
					logger.error("failed to get SubsInfo data. deviceCtn({})", memb.getMembCtn());
					return null;
				}	
				
				RetrieveCustSvcEntrInfoBDServiceStub.DsOutputOutVO dsOutputOutVO = eSBManager
						.getCustomerInfo(subsInfoNcas.getSub_no(), memb.getProductNm());
				if (dsOutputOutVO == null) {
					logger.debug("{}", "(dsOutputOutVO == null)");

					return createResultJson("9999", "데이터 처리 중 오류가 발생하였습니다.");
				}

				// 한해 2회제한
				PauseHistoryCollectionVO pauseHistoryCollection = eSBManager.getPauseHistory(dsOutputOutVO.getEntrNo(),
						dsOutputOutVO.getSvcCd());
				if (pauseHistoryCollection == null) {
					logger.debug("{}", "(pauseHistoryCollection == null)");

					return createResultJson("9999", "데이터 처리 중 오류가 발생하였습니다.");
				}

				List<PauseHistoryVO> list = pauseHistoryCollection.getList();
				if (list != null && !list.isEmpty()) {
					// 한해 2회제한
					int flag2 = 0;

					String year = new SimpleDateFormat("yyyy").format(new Date());

					for (int i = 0; i < list.size(); i++) {
						PauseHistoryVO pauseHistory = list.get(i);
						String susDate = pauseHistory.getSusDate();
						// String rspDate = pauseHistory.getRspDate();
						if (StringUtils.startsWith(susDate, year)) {
							flag2++;
						}
					}

					if (flag2 >= 2) {
						logger.debug("{}", "(pauseHistoryCollection == null)");

						return createResultJson("1000", "일시정지는 연2회까지 가능합니다.");
					}
				}

				// entrNo 가입번호 NUMBER 12 N 복수
				// entrSttsChngSeqno 가입변경일련번호 VARCHAR2 10 복수 변경일경우 존재
				// custNo 고객번호 VARCHAR2 15 N 복수 실명고객번호
				// prodNo 상품번호 VARCHAR2 20 N 복수
				// billAcntNo 청구계정번호 VARCHAR2 10 N 복수
				// entrSttsValdStrtDt 처리일자 VARCHAR2 8 N 복수
				// entrSttsValdEndDt 일시정지희망일자 VARCHAR2 8 N 복수
				// rspEntrSttsValdEndDt 정지해제희망일자 VARCHAR2 8 복수 HS서비스는 필수
				// rspEntrSttsChngRsnDtlCd 정지해제상세사유코드 VARCHAR2 10 복수 정지예약과 함께
				// 해제예약할경우
				// 사유를 지정하여 넘김PR(정상적절차)
				// rgstDt 처리일자 VARCHAR2 8 N 복수 오늘일자
				// entrSttsChngRsnCd 가입상태변경사유코드 VARCHAR2 10 N 복수
				// entrSttsChngRsnDtlCd 가입상태변경상세사유코드 VARCHAR2 10 N 복수
				// prssYn 처리여부 VARCHAR2 1 N 복수 Y
				// prssDlrCd 처리대리점코드 VARCHAR2 8 N 복수 100000
				// aplyLvlCd 적용레벨 VARCHAR2 1 N 복수 C'-가입번호별 서비스 적용 레벨
				// msgParmCnt 파라미터갯수 VARCHAR2 1 N 복수 신규:3
				// memoCrteDlrCd 대리점코드 VARCHAR2 8 N 복수
				// rsnCd 사유코드 VARCHAR2 3 N 복수 신규:E19
				// rsnDtlCd 사유상세코드 VARCHAR2 4 N 복수 신규:4039
				// sendPhbYn 발신금지 여부 VARCHAR2 1 N 복수 Y/N
				// icallPhbYn 착신금지여부 VARCHAR2 1 N 복수 Y/N
				// msgParm1 파라미터1 VARCHAR2 30 N 복수 "일시정지/정지해제"
				// msgParm2 파라미터2 VARCHAR2 30 N 복수 일시정지예약일/일시정지해제예약일
				// msgParm3 파라미터3 VARCHAR2 30 N 복수 일시정지사유코드명/정지해제사유코드명
				// userMemo 사용자메모 VARCHAR2 800 복수

				// 경제적 사정 - EX
				// 장비도난(모델/PC/AP) - 12
				// 장애발생/품질불만 - 13
				// 부모요청 - 14
				// 인터넷 미사용 - 15
				// 장기부재 - ZA
				// 폰 파손 - UP

				String entrNo = dsOutputOutVO.getEntrNo();// 필수
				String entrSttsChngSeqno = null;
				String custNo = dsOutputOutVO.getCustNo();// 필수
				String prodNo = dsOutputOutVO.getProdNo();// 필수
				String billAcntNo = dsOutputOutVO.getBillAcntNo();// 필수
				String entrSttsValdStrtDt = (String) parameter.get("entrSttsValdStrtDt");// 필수
				String entrSttsValdEndDt = (String) parameter.get("entrSttsValdEndDt");// 필수
				String rspEntrSttsValdEndDt = null;
				String rspEntrSttsChngRsnDtlCd = null;
				String rgstDt = new SimpleDateFormat("yyyyMMdd").format(new Date());// 필수
				String entrSttsChngRsnCd = "SUS";// 필수(SUS/RSP)
				String entrSttsChngRsnDtlCd = "STP";// 필수(일시정지)
				String prssYn = "Y";// 필수
				String prssDlrCd = "100000";// 필수
				String aplyLvlCd = "C";// 필수
				String msgParmCnt = "3";// 필수
				String memoCrteDlrCd = dsOutputOutVO.getEntrDlrCd();// 필수
				String rsnCd = "E19";// 필수
				String rsnDtlCd = "4039";// 필수
				String sendPhbYn = "N";// 필수
				String icallPhbYn = "N";// 필수
				String msgParm1 = "일시정지";// 필수
				String msgParm2 = (String) parameter.get("entrSttsValdStrtDt");// 필수
				String msgParm3 = (String) parameter.get("selRsnCd");// 필수
				String userMemo = (String) parameter.get("selRsnNm");// ?

				PauseResultVO pauseResultVO = eSBManager.updatePause(entrNo, entrSttsChngSeqno, custNo, prodNo,
						billAcntNo, entrSttsValdStrtDt, entrSttsValdEndDt, rspEntrSttsValdEndDt,
						rspEntrSttsChngRsnDtlCd, rgstDt, entrSttsChngRsnCd, entrSttsChngRsnDtlCd, prssYn, prssDlrCd,
						aplyLvlCd, msgParmCnt, memoCrteDlrCd, rsnCd, rsnDtlCd, sendPhbYn, icallPhbYn, msgParm1,
						msgParm2, msgParm3, userMemo);
				if (pauseResultVO == null) {
					logger.debug("{}", "(pauseResultVO == null)");

					return createResultJson("9999", "데이터 처리 중 오류가 발생하였습니다.");
				}

				if (!"SUCCESS".equals(pauseResultVO.getResult())) {
					logger.debug("failed to updatePause");

					return createResultJson("9999", pauseResultVO.getResult());
				}

				result.put("resultCode", "0000");
				result.put("resultMsg", "성공");

			} else {
				HashMap<String, Object> parameter = new ObjectMapper().readValue(body,
						new TypeReference<HashMap<String, Object>>() {
						});

				String entrSttsValdStrtDt = (String) parameter.get("entrSttsValdStrtDt");// 필수
				String entrSttsValdEndDt = (String) parameter.get("entrSttsValdEndDt");// 필수
				String msgParm3 = (String) parameter.get("selRsnCd");// 필수

				result.put("resultCode", "0000");
				result.put("resultMsg", "성공");
			}

			return new ObjectMapper().writeValueAsString(result);
		} catch (Exception e) {
			logger.debug("{}", e.getMessage());

			e.printStackTrace();
		}

		return createResultJson("9999", "데이터 처리 중 오류가 발생하였습니다.");
	}

	// 일시정지해제
	@SuppressWarnings("unused")
	@Override
	public String getUnpauseService(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
			String body) {
		HashMap<String, Object> result = new HashMap<String, Object>();
		try {
			if (StringUtils.equalsIgnoreCase("Y", esbEnable)) {
				HashMap<String, Object> parameter = new ObjectMapper().readValue(body,
						new TypeReference<HashMap<String, Object>>() {
						});

				String tp = (String) parameter.get("tp");
				String ci = (String) parameter.get("ci");
				String di = (String) parameter.get("di");
				String pn = (String) parameter.get("pn");
				String nm = (String) parameter.get("nm");
				String bd = (String) parameter.get("bd");

				//////////////////////////////////////////////////////////////////////
				// 본인인증
				//
				// TP - IPIN
				// CI -
				// T/VrVoqut4xtte9Flzlxix9Vfdwv4KePmfDgmV8p1l/o/hu52sjqNJou/q1IWX3ojAgDTg8xU3xrBlshDSKDLQ==
				// DI -
				////////////////////////////////////////////////////////////////////// MC0GCCqGSIb3DQIJAyEAwbAxA/yFJ+TI0E5HI4Qp9C96gKPNtUUk6bfvz960Quw=
				// PN -
				// NM - 노연대
				// BD - 19780206
				//
				// TP - KMC
				// CI -
				// T/VrVoqut4xtte9Flzlxix9Vfdwv4KePmfDgmV8p1l/o/hu52sjqNJou/q1IWX3ojAgDTg8xU3xrBlshDSKDLQ==
				// DI -
				////////////////////////////////////////////////////////////////////// MC0GCCqGSIb3DQIJAyEAeISx8l2Ix2yR4y1QJg+CvTxWYlC6699c6W69kMmVe7A=
				// PN - 01055997694
				// NM - 노연대
				// BD - 19780206
				////////////////////////////////////////////////////////////////////////

				MembVO memb = validationByAll(httpServletRequest);
				if (memb == null) {
					logger.debug("{}", "(memb == null)");

					return createResultJson("9999", "데이터 처리 중 오류가 발생하였습니다.");
				}

				// 2018-12-27
				// query NCAS
				NCASResultData ncasData = nCASQueryManager.query(memb.getMembCtn());
				if (ncasData == null) {
					logger.error("failed to query NCAS data. deviceCtn({})", memb.getMembCtn());
					return null;
				}
				SubsInfo subsInfoNcas = ncasData.getSubsInfo();
				if (subsInfoNcas == null) {
					logger.error("failed to get SubsInfo data. deviceCtn({})", memb.getMembCtn());
					return null;
				}	
				
				RetrieveCustSvcEntrInfoBDServiceStub.DsOutputOutVO dsOutputOutVO = eSBManager
						.getCustomerInfo(subsInfoNcas.getSub_no(), memb.getProductNm());
				if (dsOutputOutVO == null) {
					logger.debug("{}", "(dsOutputOutVO == null)");

					return createResultJson("9999", "데이터 처리 중 오류가 발생하였습니다.");
				}

				PauseHistoryCollectionVO pauseHistoryCollection = eSBManager.getPauseHistory(dsOutputOutVO.getEntrNo(),
						dsOutputOutVO.getSvcCd());
				if (pauseHistoryCollection == null) {
					logger.debug("{}", "(pauseHistoryCollection == null)");

					return createResultJson("9999", "데이터 처리 중 오류가 발생하였습니다.");
				}

				List<PauseHistoryVO> list = pauseHistoryCollection.getList();
				if (list == null || list.isEmpty()) {
					logger.debug("{}", "(list == null)");

					return createResultJson("9999", "데이터 처리 중 오류가 발생하였습니다.");
				}

				RetrieveCustInfoSvcServiceStub.DsCustInfoOutVO dsCustInfoOut = eSBManager
						.getStateInfo(subsInfoNcas.getSub_no());
				if (dsCustInfoOut == null) {
					logger.debug("{}", "(dsCustInfoOut == null)");

					return createResultJson("9999", "데이터 처리 중 오류가 발생하였습니다.");
				}

				String entrNo = dsOutputOutVO.getEntrNo();// 필수
				String entrSttsChngRsnCd = "RSP";// 필수(SUS/RSP)
				String entrSttsChngRsnDtlCd = dsCustInfoOut.getEntrSttsChngRsnDtlCd();// 필수
				String entrSttsChngRsnDtlNm = dsCustInfoOut.getEntrSttsChngRsnDtlNm();// 필수
				String sendPhbYn = "N";// 필수
				String icallPhbYn = "N";// 필수
				String entrSttsValdStrtDt = new SimpleDateFormat("yyyyMMdd").format(new Date());// 필수
				String userMemo = null;
				String userDlrCd = dsOutputOutVO.getEntrDlrCd();// 필수

				UnpauseResultVO unpauseResultVO = eSBManager.updateUnpause(entrNo, entrSttsChngRsnCd,
						entrSttsChngRsnDtlCd, entrSttsChngRsnDtlNm, sendPhbYn, icallPhbYn, entrSttsValdStrtDt, userMemo,
						userDlrCd);
				if (unpauseResultVO == null) {
					logger.debug("{}", "(unpauseResultVO == null)");

					return createResultJson("9999", "데이터 처리 중 오류가 발생하였습니다.");
				}

				if (!"SUCCESS".equals(unpauseResultVO.getResult())) {
					logger.debug("failed to updatePause");

					return createResultJson("9999", unpauseResultVO.getResult());
				}

				result.put("resultCode", "0000");
				result.put("resultMsg", "성공");

			} else {
				HashMap<String, Object> parameter = new ObjectMapper().readValue(body,
						new TypeReference<HashMap<String, Object>>() {
						});

				result.put("resultCode", "0000");
				result.put("resultMsg", "성공");
			}

			return new ObjectMapper().writeValueAsString(result);
		} catch (Exception e) {
			logger.debug("{}", e.getMessage());

			e.printStackTrace();
		}

		return createResultJson("9999", "데이터 처리 중 오류가 발생하였습니다.");
	}

	// 요금제신청
	@SuppressWarnings("unused")
	@Override
	public String getApplyPlan(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
			String body) {
		HashMap<String, Object> result = new HashMap<String, Object>();
		try {
			if (StringUtils.equalsIgnoreCase("Y", esbEnable)) {
				HashMap<String, Object> parameter = new ObjectMapper().readValue(body,
						new TypeReference<HashMap<String, Object>>() {
						});

				String tp = (String) parameter.get("tp");
				String ci = (String) parameter.get("ci");
				String di = (String) parameter.get("di");
				String pn = (String) parameter.get("pn");
				String nm = (String) parameter.get("nm");
				String bd = (String) parameter.get("bd");

				//////////////////////////////////////////////////////////////////////
				// 본인인증
				//
				// TP - IPIN
				// CI -
				// T/VrVoqut4xtte9Flzlxix9Vfdwv4KePmfDgmV8p1l/o/hu52sjqNJou/q1IWX3ojAgDTg8xU3xrBlshDSKDLQ==
				// DI -
				////////////////////////////////////////////////////////////////////// MC0GCCqGSIb3DQIJAyEAwbAxA/yFJ+TI0E5HI4Qp9C96gKPNtUUk6bfvz960Quw=
				// PN -
				// NM - 노연대
				// BD - 19780206
				//
				// TP - KMC
				// CI -
				// T/VrVoqut4xtte9Flzlxix9Vfdwv4KePmfDgmV8p1l/o/hu52sjqNJou/q1IWX3ojAgDTg8xU3xrBlshDSKDLQ==
				// DI -
				////////////////////////////////////////////////////////////////////// MC0GCCqGSIb3DQIJAyEAeISx8l2Ix2yR4y1QJg+CvTxWYlC6699c6W69kMmVe7A=
				// PN - 01055997694
				// NM - 노연대
				// BD - 19780206
				////////////////////////////////////////////////////////////////////////

				MembVO memb = validationByAll(httpServletRequest);
				if (memb == null) {
					logger.debug("{}", "(memb == null)");

					return createResultJson("9999", "데이터 처리 중 오류가 발생하였습니다.");
				}

				if (StringUtils.equals("Y", memb.getPayResvYn())) {
					logger.debug("{}", "PayResvYn is Y");
					// 이미 예약신청되어 있음
					return createResultJson("1000", "요청하신 요금제로 변경했습니다.\\n서비스를 이용해 주셔서 감사합니다.");
				}

				// 2018-12-27
				// query NCAS
				NCASResultData ncasData = nCASQueryManager.query(memb.getMembCtn());
				if (ncasData == null) {
					logger.error("failed to query NCAS data. deviceCtn({})", memb.getMembCtn());
					return null;
				}
				SubsInfo subsInfoNcas = ncasData.getSubsInfo();
				if (subsInfoNcas == null) {
					logger.error("failed to get SubsInfo data. deviceCtn({})", memb.getMembCtn());
					return null;
				}	
				
				RetrieveCustSvcEntrInfoBDServiceStub.DsOutputOutVO dsOutputOutVO = eSBManager
						.getCustomerInfo(subsInfoNcas.getSub_no(), memb.getProductNm());
				if (dsOutputOutVO == null) {
					logger.debug("{}", "(dsOutputOutVO == null)");

					return createResultJson("9999", "데이터 처리 중 오류가 발생하였습니다.");
				}

				RetrieveDscntMgmtServiceStub.DsDscntListOutVO[] dsDscntListOut = eSBManager
						.getDiscountInfo(subsInfoNcas.getSub_no());
				if (dsDscntListOut == null) {
					logger.debug("{}", "(dsDscntListOut == null)");

					return createResultJson("9999", "데이터 처리 중 오류가 발생하였습니다.");
				}

				RetrieveDscntMgmtServiceStub.DsDscntListOutVO found = null;
				for (int i = 0; i < dsDscntListOut.length; i++) {
					RetrieveDscntMgmtServiceStub.DsDscntListOutVO dsDscntListOut_ = dsDscntListOut[i];
					if (StringUtils.equals("Y", real)) {
						// LDZ0002086 (LTE) 태블릿/스마트기기 데이터 10GB 요금제 기본료 15000원
						// 할인(만기할것)
						if (StringUtils.equals("LDZ0002087", dsDscntListOut_.getDscntSvcCd())) {
							if (!StringUtils.equals("ACT", dsDscntListOut_.getDscntSttsKdCd())) {
								// 할인코드가 활성화되어 있지 않으면 에러
								return createResultJson("9999", "데이터 처리 중 오류가 발생하였습니다.");
							}

							found = dsDscntListOut_;
							break;
						}
					} else {
						// LDZ0000770 프로모션 할인(만기할것)
						if (StringUtils.equals("LDZ0000770", dsDscntListOut_.getDscntSvcCd())) {
							found = dsDscntListOut_;
							break;
						}
					}
				}

				if (found == null) {
					logger.debug("{}", "(found == null)");

					return createResultJson("9999", "데이터 처리 중 오류가 발생하였습니다.");
				}

				Date svcFrstStrtDttm = new Date();
				if (StringUtils.isNotEmpty(dsOutputOutVO.getSvcFrstStrtDttm())
						&& VALID_DATE_YYYYMMDD_REGEX.matcher(dsOutputOutVO.getSvcFrstStrtDttm()).find()
						&& !dsOutputOutVO.getSvcFrstStrtDttm().startsWith("9999")) {
					svcFrstStrtDttm = new SimpleDateFormat("yyyyMMdd").parse(dsOutputOutVO.getSvcFrstStrtDttm());
				}

				Calendar calendar = Calendar.getInstance();
				// calendar.setTime(memb.getJoinDt());
				calendar.setTime(svcFrstStrtDttm);
				
				//접속 디바이스 모델 찾기 20190128 kangjin
				String connDeviceModelId = getConnDeviceModel(memb);
				
				if("EL1".equals(connDeviceModelId)) {
					calendar.add(Calendar.YEAR, 2);
				}else {
					calendar.add(Calendar.YEAR, 1);
				}
				
				calendar.add(Calendar.DATE, -1);

				// dsDscntListOut
				// mode 작업구분 VARCHAR2 14 N 복수
				// (LIST:조회,SAVE_ACT:할인등록,SAVE_EXP:할인만기)
				// entrNo 가입번호 VARCHAR2 14 Y 복수 상품번호 존재시 NULL가능
				// prodNo 상품번호 VARCHAR2 14 Y 복수 가입번호 존재시 NULL가능
				// entrByDscntSeqno entrByDscntSeqno VARCHAR2 14 Y 복수
				// SAVE_EXP:할인만기시 필수
				// svcCd 상품코드 VARCHAR2 14 Y 복수 종속할인적용시 필수
				// svcDpndRelsCd 상품종속관계코드 VARCHAR2 14 Y 복수 미존재시 SMD/PMD
				// dscntSvcCd 할인코드 VARCHAR2 14 Y 복수 할인코드 존재시 상품코드 무시
				// entrDscntKdCd 할인유형코드 VARCHAR2 14 Y 복수 미존재시 GEN
				// dscntSttsKdCd 할인상태코드 VARCHAR2 14 Y 복수 (미입력시,등록-ACT,만기-EXP)
				// dscntStrtDttm 할인시작일자 VARCHAR2 14 Y 복수 dscntStrtKdCd 미입력시당일시작
				// dscntEndDttm 할인종료일자 VARCHAR2 14 Y 복수 (SAVE_ACT:dscntEndKdCd
				// 미입력시99991231,SAVE_EXP:할인만기시 필수)
				// dscntStrtKdCd 할인시작구분 VARCHAR2 14 Y 복수
				// (A:즉시(당일),B:익월1일,M1:1개월후부터
				// 시작,M3:3개월후부터 시작)
				// dscntEndKdCd 할인종료구분 VARCHAR2 14 Y 복수 (A:무제한,M3:3개월간
				// 적용,D30:30일간 적용)
				// agmtStrtDttm 약정시작일자 VARCHAR2 14 Y 복수
				// agmtEndDttm 약정종료일자 VARCHAR2 14 Y 복수
				// agmtMnbr 할인종료구분 VARCHAR2 14 Y 복수

				// 할인을 만기시켜서 금액이 발생하게 요금제를 변경한다.
				String mode = "SAVE_EXP";// 필수
				String entrNo = dsOutputOutVO.getEntrNo();
				String prodNo = dsOutputOutVO.getProdNo();
				String entrByDscntSeqno = found.getEntrByDscntSeqno();
				String svcCd = null;// dsOutputOutVO.getSvcCd();
				String svcDpndRelsCd = null;// ??
				String dscntSvcCd = found.getDscntSvcCd();
				String entrDscntKdCd = null;// dsDscntListOut.getEntrDscntKdCd();
				String dscntSttsKdCd = null;// dsDscntListOut.getDscntSttsKdCd();
				String dscntStrtDttm = null;// dsDscntListOut.getDscntStrtDttm();
				String dscntEndDttm = new SimpleDateFormat("yyyyMMdd").format(calendar.getTime());// 가입일기준
																									// +
																									// 1년
				String dscntStrtKdCd = null;// dsDscntListOut.getDscntSttsKdCd();
				String dscntEndKdCd = null;
				String agmtStrtDttm = null;
				String agmtEndDttm = null;
				String agmtMnbr = null;

				DiscountInfoResultVO discountInfoResultVO = eSBManager.updateDiscountInfo(mode, entrNo, prodNo,
						entrByDscntSeqno, svcCd, svcDpndRelsCd, dscntSvcCd, entrDscntKdCd, dscntSttsKdCd, dscntStrtDttm,
						dscntEndDttm, dscntStrtKdCd, dscntEndKdCd, agmtStrtDttm, agmtEndDttm, agmtMnbr);
				if (discountInfoResultVO == null) {
					logger.debug("{}", "(discountInfoResultVO == null)");

					return createResultJson("9999", "데이터 처리 중 오류가 발생하였습니다.");
				}

				if (!"N0001".equals(discountInfoResultVO.getErrCode())) {
					logger.debug("failed to updateDiscountInfo");

					return createResultJson("9999", discountInfoResultVO.getErrMsg());
				}

				/* LDZ0002087 (LTE) 태블릿/스마트기기 데이터 10GB 요금제 기본료 3000원 할인(등록할것)
				if (StringUtils.equals("Y", real)) {
					// dsDscntListOut
					// mode 작업구분 VARCHAR2 14 N 복수
					// (LIST:조회,SAVE_ACT:할인등록,SAVE_EXP:할인만기)
					// entrNo 가입번호 VARCHAR2 14 Y 복수 상품번호 존재시 NULL가능
					// prodNo 상품번호 VARCHAR2 14 Y 복수 가입번호 존재시 NULL가능
					// entrByDscntSeqno entrByDscntSeqno VARCHAR2 14 Y 복수
					// SAVE_EXP:할인만기시 필수
					// svcCd 상품코드 VARCHAR2 14 Y 복수 종속할인적용시 필수
					// svcDpndRelsCd 상품종속관계코드 VARCHAR2 14 Y 복수 미존재시 SMD/PMD
					// dscntSvcCd 할인코드 VARCHAR2 14 Y 복수 할인코드 존재시 상품코드 무시
					// entrDscntKdCd 할인유형코드 VARCHAR2 14 Y 복수 미존재시 GEN
					// dscntSttsKdCd 할인상태코드 VARCHAR2 14 Y 복수
					// (미입력시,등록-ACT,만기-EXP)
					// dscntStrtDttm 할인시작일자 VARCHAR2 14 Y 복수 dscntStrtKdCd
					// 미입력시당일시작
					// dscntEndDttm 할인종료일자 VARCHAR2 14 Y 복수
					// (SAVE_ACT:dscntEndKdCd
					// 미입력시99991231,SAVE_EXP:할인만기시 필수)
					// dscntStrtKdCd 할인시작구분 VARCHAR2 14 Y 복수
					// (A:즉시(당일),B:익월1일,M1:1개월후부터
					// 시작,M3:3개월후부터 시작)
					// dscntEndKdCd 할인종료구분 VARCHAR2 14 Y 복수 (A:무제한,M3:3개월간
					// 적용,D30:30일간 적용)
					// agmtStrtDttm 약정시작일자 VARCHAR2 14 Y 복수
					// agmtEndDttm 약정종료일자 VARCHAR2 14 Y 복수
					// agmtMnbr 할인종료구분 VARCHAR2 14 Y 복수

					// 기본료 할인코드 추가
					mode = "SAVE_ACT";// 필수
					entrNo = dsOutputOutVO.getEntrNo();
					prodNo = dsOutputOutVO.getProdNo();
					entrByDscntSeqno = null;
					svcCd = null;// dsOutputOutVO.getSvcCd();
					svcDpndRelsCd = null;// ??
					dscntSvcCd = "LDZ0002087";
					entrDscntKdCd = null;// dsDscntListOut.getEntrDscntKdCd();
					dscntSttsKdCd = "ACT";// dsDscntListOut.getDscntSttsKdCd();
					dscntStrtDttm = new SimpleDateFormat("yyyyMMdd").format(calendar.getTime());// 가입일기준
																								// +
																								// 1년
					dscntEndDttm = null;
					dscntStrtKdCd = null;// dsDscntListOut.getDscntSttsKdCd();
					dscntEndKdCd = "A";
					agmtStrtDttm = null;
					agmtEndDttm = null;
					agmtMnbr = null;

					discountInfoResultVO = eSBManager.updateDiscountInfo(mode, entrNo, prodNo, entrByDscntSeqno, svcCd,
							svcDpndRelsCd, dscntSvcCd, entrDscntKdCd, dscntSttsKdCd, dscntStrtDttm, dscntEndDttm,
							dscntStrtKdCd, dscntEndKdCd, agmtStrtDttm, agmtEndDttm, agmtMnbr);
					if (discountInfoResultVO == null) {
						logger.debug("{}", "(discountInfoResultVO == null)");

						return createResultJson("9999", "데이터 처리 중 오류가 발생하였습니다.");
					}

					if (!"N0001".equals(discountInfoResultVO.getErrCode())) {
						logger.debug("failed to updateDiscountInfo");

						return createResultJson("9999", discountInfoResultVO.getErrMsg());
					}
				}*/

				int value = memberDao.updateMemberPayResv(memb.getMembId(), "Y");
				if (value != 1) {
					logger.debug("failed to updateMemberPayResv");

					return createResultJson("9999", "데이터 처리 중 오류가 발생하였습니다.");
				}

				result.put("name", dsOutputOutVO.getSvcNm());
				result.put("pay", ratePayDtl);
				result.put("date", new SimpleDateFormat("yyyy.MM.dd").format(new Date()));
				
				result.put("resultCode", "0000");
				result.put("resultMsg", "성공");

			} else {
				HashMap<String, Object> parameter = new ObjectMapper().readValue(body,
						new TypeReference<HashMap<String, Object>>() {
						});

				result.put("resultCode", "0000");
				result.put("resultMsg", "성공");
			}
			return new ObjectMapper().writeValueAsString(result);
		} catch (Exception e) {
			logger.debug("{}", e.getMessage());

			e.printStackTrace();
		}

		return createResultJson("9999", "데이터 처리 중 오류가 발생하였습니다.");
	}

	// 납부방법검증
	@SuppressWarnings("unused")
	@Override
	public String getVerifyPayment(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
			String body) {
		HashMap<String, Object> result = new HashMap<String, Object>();
		try {
			if (StringUtils.equalsIgnoreCase("Y", esbEnable)) {
				HashMap<String, Object> parameter = new ObjectMapper().readValue(body,
						new TypeReference<HashMap<String, Object>>() {
						});

				MembVO memb = validationByAll(httpServletRequest);
				if (memb == null) {
					logger.debug("{}", "(memb == null)");

					return createResultJson("9999", "데이터 처리 중 오류가 발생하였습니다.");
				}

				// 2018-12-27
				// query NCAS
				NCASResultData ncasData = nCASQueryManager.query(memb.getMembCtn());
				if (ncasData == null) {
					logger.error("failed to query NCAS data. deviceCtn({})", memb.getMembCtn());
					return null;
				}
				SubsInfo subsInfoNcas = ncasData.getSubsInfo();
				if (subsInfoNcas == null) {
					logger.error("failed to get SubsInfo data. deviceCtn({})", memb.getMembCtn());
					return null;
				}	
				
				RetrieveCustSvcEntrInfoBDServiceStub.DsOutputOutVO dsOutputOutVO = eSBManager
						.getCustomerInfo(subsInfoNcas.getSub_no(), memb.getProductNm());
				if (dsOutputOutVO == null) {
					logger.debug("{}", "(dsOutputOutVO == null)");

					return createResultJson("9999", "데이터 처리 중 오류가 발생하였습니다.");
				}

				// acntOwnrNo 소유자번호 VARCHAR2 13 N 단수 주민번호,사업자번호,외국인번호,외국인거소번호등
				// custDvCd 고객구분 VARCHAR2 10 N 단수 "개인:I,법인:G"
				// custKdCd 고객유형 VARCHAR2 2 N 단수 VW_CS_HB_CUST_KD_01 참조
				// persIdind 외국인여부 VARCHAR2 10 Y 단수 외국인:Y
				// pymMthdCd 납부방법 VARCHAR2 10 Y 단수 "자동이체:CM,신용카드:CC,지로:GR"
				// bankCd 은행코드 VARCHAR2 10 Y 단수 자동이체시 필수
				// bankAcntNo 은행계좌번호 VARCHAR2 21 Y 단수 자동이체시 필수
				// cdcmpCd 카드타입 VARCHAR2 10 Y 단수 신용카드시 필수
				// cardNo 카드번호 VARCHAR2 44 Y 단수 신용카드시 필수
				// cardValdEndYymm 카드유효기간 VARCHAR2 10 Y 단수 "신용카드시 필수YYYYMM 형식"
				// acntOwnrNm 소유자명 VARCHAR2 250 N 단수 소유주명
				// mode 소유자명 VARCHAR2 60 N 단수 "N : 인증시 계좌/카드가 납부자 소유인지 체크한다.
				// (환불계좌
				// 인증시사용), P : 인증시 계좌/카드와 납부자 소유를 체크하지 않는다. (납부계정 등록시 사용)"

				String acntOwnrNo = (String) parameter.get("acntOwnrNo");// 필수
				String custDvCd = (String) parameter.get("custDvCd");// 필수
				String custKdCd = "I".equals(custDvCd) ? "II" : "GE";// 필수
				String persIdind = (String) parameter.get("persIdind");
				String pymMthdCd = null;
				String bankCd = null;
				String bankAcntNo = null;
				String cdcmpCd = (String) parameter.get("cdcmpCd");
				String cardNo = (String) parameter.get("cardNo");
				String cardValdEndYymm = (String) parameter.get("cardValdEndYymm");
				String acntOwnrNm = (String) parameter.get("acntOwnrNm");// 필수
				String mode = "P";// 필수

				PayMethodAuthVO payMethodAuthVO = eSBManager.getPayMethod(acntOwnrNo, custDvCd, custKdCd, persIdind,
						pymMthdCd, bankCd, bankAcntNo, cdcmpCd, cardNo, cardValdEndYymm, acntOwnrNm, mode);
				if (payMethodAuthVO == null) {
					logger.debug("{}", "(payMethodAuthVO == null)");

					return createResultJson("9999", "데이터 처리 중 오류가 발생하였습니다.");
				}

				if (!"0000".equals(payMethodAuthVO.getMsgCode())) {
					logger.debug("failed to getPayMethod");

					return createResultJson("9999", payMethodAuthVO.getMsgText());
				}

				result.put("resultCode", "0000");
				result.put("resultMsg", "성공");

			} else {
				String userAgent = httpServletRequest.getHeader(HttpHeaders.USER_AGENT);

				HashMap<String, Object> parameter = new ObjectMapper().readValue(body,
						new TypeReference<HashMap<String, Object>>() {
						});

				String acntOwnrNo = (String) parameter.get("acntOwnrNo");// 필수
				String custDvCd = (String) parameter.get("custDvCd");// 필수
				String custKdCd = "I".equals(custDvCd) ? "II" : "GE";// 필수
				String persIdind = (String) parameter.get("persIdind");
				String cdcmpCd = (String) parameter.get("cdcmpCd");
				String cardNo = (String) parameter.get("cardNo");
				String cardValdEndYymm = (String) parameter.get("cardValdEndYymm");
				String acntOwnrNm = (String) parameter.get("acntOwnrNm");// 필수

				result.put("resultCode", "0000");
				result.put("resultMsg", "성공");
			}

			return new ObjectMapper().writeValueAsString(result);
		} catch (Exception e) {
			logger.debug("{}", e.getMessage());

			e.printStackTrace();
		}

		return createResultJson("9999", "데이터 처리 중 오류가 발생하였습니다.");
	}
	
	/////////////////////////////////////////////
	//   서비스 이용약관 동의 2018-11-27    //
	/////////////////////////////////////////////
	@Override
	public String getAgreement(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
			Model model) {
		try {
				List<TermsVO> list = pNDDao.selectCurrentTerms();

				model.addAttribute("list", list != null ? list : new ArrayList<TermsVO>());

			return "phone/agreement";
		} catch (Exception e) {
			logger.debug("{}",e.getMessage());
			
			e.printStackTrace();
		}
		
		return "phone/errorPage";
	}

	
	/*	 이용약관 리스트 
	 *  2018-12-3
	*/
	@Override
	public String getAgreementCustom(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
			Model model,@PathVariable String flag) {
		logger.debug("getAgreementCustom >>>> " + flag);
		try {
			
			TermsVO termsVo = new TermsVO();
			String termsDiv = "";

			if (flag.equals("agrservice")) {
				
				termsVo.setTermsDiv("20");
				
			} else if (flag.equals("information")) {
				//termsDiv = agrInformation;
				termsVo.setTermsDiv("21");
				
			} else if (flag.equals("third")) {
				//termsDiv = agrThird;
				termsVo.setTermsDiv("22");
				
			} else if (flag.equals("location")) {
				//termsDiv = agrLocation;
				termsVo.setTermsDiv("23");
			} else {
				
				return "phone/errorPage";
			}
			
			logger.debug("getAgreementCustom >>" + termsVo.getTermsDiv() +">> " + termsDiv);
				//termsVo.setTermsDiv(String.valueOf(agrAgrService));
				logger.debug("termsVo>>>>>>>>>>>>>>>>"+termsVo);
				
				TermsVO terms = pNDDao.selectOneMobileServieTerms(termsVo);
				/*logger.debug(">>>>>>>>>>>>>>>>"+terms.getTermsTitle());
				logger.debug(">>>>>>>>>>>>>>>>"+terms.getTermsCont());*/
				
				model.addAttribute("terms",terms);
				//model.addAttribute("list", list != null ? list : new ArrayList<TermsVO>());
				
		} catch (Exception e) {
			logger.debug("{}",e.getMessage());
			
			e.printStackTrace();
		}
		return "phone/agreementCustom";
	}
	
	/**
	 * 접속모델 조회 20190128 kangjin
	 */	
	private String getConnDeviceModel(MembVO memb) {

		String connDeviceModelId = pNDDao.getConnDeviceModel(memb.getMembId());
		if (connDeviceModelId == null) {
			logger.error("failed to select connDeviceModel data. connDeviceId", connDeviceModelId);
			return null;
		}		

		return connDeviceModelId;
	}		
	
	
		
}
