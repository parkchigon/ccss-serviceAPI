package com.lgu.ccss.mypage.pnd.service;

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
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.annotation.PostConstruct;
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

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lgu.ccss.common.dao.DeviceSessDao;
import com.lgu.ccss.common.dao.MemberDao;
import com.lgu.ccss.common.dao.TermsDao;
import com.lgu.ccss.common.model.DeviceSessVO;
import com.lgu.ccss.common.model.MembCommAgrVO;
import com.lgu.ccss.common.model.MembVO;
import com.lgu.ccss.mypage.pnd.dao.PNDDao;
import com.lgu.ccss.mypage.pnd.model.TermsVO;
import com.lgu.common.esb.ESBManager;
import com.lgu.common.esb.vo.AmountDataVO;
import com.lgu.common.esb.vo.BillReturnInfoVO;
import com.lgu.common.esb.vo.BillTypeResultVO;
import com.lgu.common.esb.vo.BillTypeVO;
import com.lgu.common.esb.vo.DiscountInfoResultVO;
import com.lgu.common.esb.vo.DiscountInfoVO;
import com.lgu.common.esb.vo.PauseHistory;
import com.lgu.common.esb.vo.PauseHistoryCollection;
import com.lgu.common.esb.vo.PauseHistoryCollectionVO;
import com.lgu.common.esb.vo.PauseHistoryVO;
import com.lgu.common.esb.vo.PauseLiftHistory;
import com.lgu.common.esb.vo.PauseLiftHistoryCollection;
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

@Service("pNDService")
public class PNDServiceImpl implements PNDService {

	private static final Logger logger = LoggerFactory.getLogger(PNDServiceImpl.class);

	@Autowired
	private NCASQueryManager nCASQueryManager;

	@Autowired
	private ESBManager eSBManager;

	@Autowired
	private DeviceSessDao deviceSessDao;

	@Autowired
	private MemberDao memberDao;

	@Autowired
	private PNDDao pNDDao;

	@Autowired
	private TermsDao termsDao;

	@Value("#{config['service.ratePayment']}")
	private String ratePayment;
	@Value("#{config['service.ratePayment.period']}")
	private String ratePaymentPeriod;
	@Value("#{config['service.ratePayment.expirationNotiDay']}")
	private String ratePaymentExpirationNotiDay;
	@Value("#{config['service.joiningDay']}")
	private String joiningDay;

	@Value("#{config['esb.real']}")
	private String real; // ???????????? ?????? - ESB ????????? ????????? ?????? ??????
	@Value("#{config['esb.enable']}")
	private String esbEnable;

	@Value("#{config['kmc.pnd.returnUrl']}")
	private String kmcReturnUrl;
	@Value("#{config['kmc.pnd.cpId']}")
	private String kmcCpId;
	@Value("#{config['kmc.pnd.urlCode']}")
	private String kmcUrlCode;

	@Value("#{config['ipin.pnd.returnUrl']}")
	private String ipinReturnUrl;
	@Value("#{config['ipin.pnd.siteCode']}")
	private String ipinSiteCode;
	@Value("#{config['ipin.pnd.sitePw']}")
	private String ipinSitePw;

	@Value("#{rate['ratePayDtl']}")
	private String ratePayDtl;
	
	@Value("#{rate['limitApplyPlanHtml']}")
	private String limitApplyPlanHtml;
	
	@Value("#{rate['limitExitImminentHtml']}")
	private String limitExitImminentHtml;

	@Value("#{rate['unlimitPayDetailHtml']}")
	private String unlimitPayDetailHtml;
	
	@Value("#{rate['defaultAmountList']}")
	private String defaultAmountList;
	
	private Map<String, String> defaultAmountMap = new HashMap<String, String>();
	
	@PostConstruct
	public void postConstruct() {
		for (String keyvalue : defaultAmountList.split(",")) {
			String[] list = keyvalue.split(":");
			defaultAmountMap.put(list[0], list[1]);
		}
	}
	
	private Pattern VALID_DATE_YYYYMMDD_REGEX = Pattern.compile("^[0-9]{4}(1[0-2]|0[1-9])(3[01]|[12][0-9]|0[1-9])$",
			Pattern.CASE_INSENSITIVE);

	private Pattern VALID_EMAIL_ADDRESS_REGEX = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$",
			Pattern.CASE_INSENSITIVE);

	private Pattern VALID_NUMBER_REGEX = Pattern.compile("^[0-9]*$", Pattern.CASE_INSENSITIVE);

	private Pattern VALID_USER_AGENT_REGEX = Pattern.compile("\\[(.*)\\,(.*)\\]", Pattern.CASE_INSENSITIVE);

	private SubsInfo checkByCtn(HttpServletRequest httpServletRequest) {
		String userAgent = httpServletRequest.getHeader(HttpHeaders.USER_AGENT);
		logger.info("userAgent({})", userAgent);

		String deviceCtn = "";

		Matcher matcher = VALID_USER_AGENT_REGEX.matcher(userAgent);
		if (matcher.find()) {
			deviceCtn = matcher.group(1);
			
			String[] arr = StringUtils.splitByWholeSeparator(deviceCtn, ",");
			
			if(arr.length == 2) {
				deviceCtn = arr[0];
			}else {
				deviceCtn = matcher.group(1);
			}			
		}

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

	private MembVO validationByAll(HttpServletRequest httpServletRequest) {
		String userAgent = httpServletRequest.getHeader(HttpHeaders.USER_AGENT);
		logger.info("userAgent({})", userAgent);

		String deviceCtn = "";
		String ccssToken = "";

		Matcher matcher = VALID_USER_AGENT_REGEX.matcher(userAgent);
		if (matcher.find()) {
			deviceCtn = matcher.group(1);
			
			String[] arr = StringUtils.splitByWholeSeparator(deviceCtn, ",");
			
			if(arr.length == 2) {
				deviceCtn = arr[0];
				ccssToken = arr[1];
			}else {
				deviceCtn = matcher.group(1);
				ccssToken = matcher.group(2);
			}			
		}

		if (StringUtils.isEmpty(deviceCtn)) {
			logger.info("deviceCtn({}) is empty", deviceCtn);
			return null;
		}

		if (StringUtils.isEmpty(ccssToken)) {
			logger.info("ccssToken({}) is empty", ccssToken);
			return null;
		}

		logger.info("deviceCtn({}) ccssToken({})", deviceCtn, ccssToken);

		DeviceSessVO deviceSess = deviceSessDao.selectDeviceSess(ccssToken);
		if (deviceSess == null) {
			logger.error("failed to select deviceSession data. deviceCtn({}) ccssToken({})", deviceCtn, ccssToken);
			return null;
		}

		MembVO memb = memberDao.selectMemberByID(deviceSess.getMembId());
		if (memb == null) {
			logger.error("failed to select member data. deviceCtn({}) ccssToken({}) deviceSess({})", deviceCtn,
					ccssToken, deviceSess);
			return null;
		}
		

		return memb;
	}

	private MembVO validationByCtn(HttpServletRequest httpServletRequest) {
		String userAgent = httpServletRequest.getHeader(HttpHeaders.USER_AGENT);
		logger.info("userAgent({})", userAgent);

		String deviceCtn = "";

		Matcher matcher = VALID_USER_AGENT_REGEX.matcher(userAgent);
		if (matcher.find()) {
			deviceCtn = matcher.group(1);
			
			String[] arr = StringUtils.splitByWholeSeparator(deviceCtn, ",");
			
			if(arr.length == 2) {
				deviceCtn = arr[0];
			}else {
				deviceCtn = matcher.group(1);
			}
		}

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

		//MembVO memb = memberDao.selectMemberByID(subsInfo.getSub_no());
		MembVO memb = memberDao.selectMemberByNO(subsInfo.getSub_no());

		if (memb == null) {
			logger.error("failed to select member data. deviceCtn({})", deviceCtn);
			return null;
		}

		return memb;
	}

	/**
	 * ???????????? > ??????
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
			
			if("EL1".equals(model.asMap().get("modelNmGbn"))) {
				model.addAttribute("modelNmGbn", "EL1");
			}else {
				model.addAttribute("modelNmGbn", "");
			}

			return "authChoice";
		} catch (Exception e) {
			logger.debug("{}", e.getMessage());

			e.printStackTrace();
		}

		return "errorPage";
	}
	
	/**
	 * ???????????? > ??????
	 */
	@Override
	public String getAuthChoiceKmc(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
			Model model) {
		try {
			if (StringUtils.equalsIgnoreCase("Y", esbEnable)) {
				// TODO
			} else {
				// TODO
			}

			return "authChoiceKmc";
		} catch (Exception e) {
			logger.debug("{}", e.getMessage());

			e.printStackTrace();
		}

		return "errorPage";
	}

	/**
	 * ???????????? > ??????????????????
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

			return "authIpinMain";
		} catch (Exception e) {
			logger.debug("{}", e.getMessage());

			e.printStackTrace();
		}

		return "errorPage";
	}

	/**
	 * ???????????? > ?????????????????? > ??????
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

			return "authIpinResult";
		} catch (Exception e) {
			logger.debug("{}", e.getMessage());

			e.printStackTrace();
		}

		return "errorPage";
	}

	/**
	 * ???????????? > ?????????????????????
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

			return "authKmcMain";
		} catch (Exception e) {
			logger.debug("{}", e.getMessage());

			e.printStackTrace();
		}

		return "errorPage";
	}

	/**
	 * ???????????? > ????????????????????? > ??????
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

			return "authKmcResult";
		} catch (Exception e) {
			logger.debug("{}", e.getMessage());

			e.printStackTrace();
		}

		return "errorPage";
	}

	/**
	 * ??????????????????1 > ????????????
	 */
	@Override
	public String getLimitExitGuide(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
			Model model) {
		try {
			if (StringUtils.equalsIgnoreCase("Y", esbEnable)) {
				MembVO memb = validationByAll(httpServletRequest);
				if (memb == null) {
					logger.debug("{}", "(memb == null)");

					return "errorFullPage";
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
				//SubsInfoVO subsInfo = eSBManager.getSubsInfo(memb.getMembNo(), memb.getProductNm());
				if (subsInfo == null) {
					logger.debug("{}", "(subsInfo == null)");

					return "errorPage";
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

				//?????? ???????????? ?????? ?????? 20190128 kangjin
				String connDeviceModelId = getConnDeviceModel(memb);
				
				if("EL1".equals(connDeviceModelId)) {
					calendar.add(Calendar.YEAR, 2);
					model.addAttribute("svcYear", "2");
					model.addAttribute("strJini", "(???????????? ????????? ?????? ??????)");
				}else {
					calendar.add(Calendar.YEAR, 1);
					model.addAttribute("svcYear", "1");
					model.addAttribute("strJini", "");
				}
				
				calendar.add(Calendar.DATE, -1);
				
				String svcEndDttm1 = new SimpleDateFormat("yyyy??? MM??? dd???").format(calendar.getTime());
				String svcEndDttm2 = new SimpleDateFormat("yyyy-MM-dd").format(calendar.getTime());

				model.addAttribute("svcEndDttm1", svcEndDttm1);// ???????????????(2018??? 1???
																// 1???)
				model.addAttribute("svcEndDttm2", svcEndDttm2);// ???????????????(2018-1-1)
			} else {
				Calendar calendar = Calendar.getInstance();
				calendar.add(Calendar.YEAR, 1);

				String svcEndDttm1 = new SimpleDateFormat("yyyy??? MM??? dd???").format(calendar.getTime());
				String svcEndDttm2 = new SimpleDateFormat("yyyy-MM-dd").format(calendar.getTime());

				model.addAttribute("svcEndDttm1", svcEndDttm1);// ???????????????(2018??? 1???
																// 1???)
				model.addAttribute("svcEndDttm2", svcEndDttm2);// ???????????????(2018-1-1)
			}

			return "limitExitGuide";
		} catch (Exception e) {
			logger.debug("{}", e.getMessage());

			e.printStackTrace();
		}

		return "errorFullPage";
	}

	/**
	 * ??????????????????1 > ??????????????????
	 */
	@Override
	public String getLimitExitImminent(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
			Model model) {
		try {
			if (StringUtils.equalsIgnoreCase("Y", esbEnable)) {
				
				logger.debug("getLimitExitImminent() esbEnable(Y) 1.######################");
				
				MembVO memb = validationByAll(httpServletRequest);
				if (memb == null) {
					logger.debug("{}", "(memb == null)");

					return "errorFullPage";
				}

				logger.debug("getLimitExitImminent() esbEnable(Y) 2.######################");
				logger.debug("getLimitExitImminent() esbEnable(Y) 3.######################{} {}",memb.getMembNo(), memb.getProductNm());

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
				//SubsInfoVO subsInfo = eSBManager.getSubsInfo(memb.getMembNo(), memb.getProductNm());
				if (subsInfo == null) {
					logger.debug("{}", "(subsInfo == null)");

					return "errorPage";
				}
				
				logger.debug("getLimitExitImminent() esbEnable(Y) 4.######################");

				Date svcFrstStrtDttm = new Date();
				if (StringUtils.isNotEmpty(subsInfo.getSvcFrstStrtDttm())
						&& VALID_DATE_YYYYMMDD_REGEX.matcher(subsInfo.getSvcFrstStrtDttm()).find()
						&& !subsInfo.getSvcFrstStrtDttm().startsWith("9999")) {
					svcFrstStrtDttm = new SimpleDateFormat("yyyyMMdd").parse(subsInfo.getSvcFrstStrtDttm());
				}

				Calendar calendar = Calendar.getInstance();
				// calendar.setTime(memb.getJoinDt());
				calendar.setTime(svcFrstStrtDttm);
				calendar.add(Calendar.YEAR, 1);

				String svcEndDttm1 = new SimpleDateFormat("yyyy??? MM??? dd???").format(calendar.getTime());
				String svcEndDttm2 = new SimpleDateFormat("yyyy-MM-dd").format(calendar.getTime());
				
				logger.debug("getLimitExitImminent() esbEnable(Y) 5.######################");


				//?????? ???????????? ?????? ?????? 20190128 kangjin
				String connDeviceModelId = getConnDeviceModel(memb);
				String strJini = "";
				String svcYear = "";
				
				if("EL1".equals(connDeviceModelId)) {
					strJini = "";
					svcYear = "2";
				}else {
					strJini = "(???????????? ????????? ?????? ??????)";
					svcYear = "1";
				}				
				//model.addAttribute("svcEndDttm1", svcEndDttm1);// ???????????????(2018??? 1??? 1???)
				//model.addAttribute("svcEndDttm2", svcEndDttm2);// ???????????????(2018-1-1)
 				String limitExitImminent = new String(limitExitImminentHtml);
 				limitExitImminent = limitExitImminent.replace("#svcEndDttm1", svcEndDttm1);
 				limitExitImminent = limitExitImminent.replace("#svcEndDttm2", svcEndDttm2);
 				limitExitImminent = limitExitImminent.replace("#strJini", strJini);
 				limitExitImminent = limitExitImminent.replace("#svcYear", svcYear);
				model.addAttribute("limitExitImminentHtml", limitExitImminent);	
				
				logger.debug("getLimitExitImminent() esbEnable(Y) 6.######################");
				
			} else {
				Calendar calendar = Calendar.getInstance();
				calendar.add(Calendar.YEAR, 1);
				
				logger.debug("getLimitExitImminent() esbEnable(N) 1.######################");

				String svcEndDttm1 = new SimpleDateFormat("yyyy??? MM??? dd???").format(calendar.getTime());
				String svcEndDttm2 = new SimpleDateFormat("yyyy-MM-dd").format(calendar.getTime());
				
				logger.debug("getLimitExitImminent() esbEnable(N) 2######################");

				//model.addAttribute("svcEndDttm1", svcEndDttm1);// ???????????????(2018??? 1??? 1???)
				//model.addAttribute("svcEndDttm2", svcEndDttm2);// ???????????????(2018-1-1)
 				String limitExitImminent = new String(limitExitImminentHtml);
 				limitExitImminent = limitExitImminent.replace("#svcEndDttm1", svcEndDttm1);
 				limitExitImminent = limitExitImminent.replace("#svcEndDttm2", svcEndDttm2);
				model.addAttribute("limitExitImminentHtml", limitExitImminent);	
				
				logger.debug("getLimitExitImminent() esbEnable(N) 3######################");
			}

			return "limitExitImminent";
		} catch (Exception e) {
			logger.debug("{}", e.getMessage());

			e.printStackTrace();
		}

		return "errorFullPage";
	}

	/**
	 * ??????????????????1 > ????????????
	 */
	@Override
	public String getLimitIntroduction(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
			Model model) {
		try {
			if (StringUtils.equalsIgnoreCase("Y", esbEnable)) {
				// TODO
			} else {
				// TODO
			}

			return "limitIntroduction";
		} catch (Exception e) {
			logger.debug("{}", e.getMessage());

			e.printStackTrace();
		}

		return "errorFullPage";
	}

	/**
	 * ??????????????????1 > ?????????????????????
	 */
	@Override
	public String getLimitAcceptTerms(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
			Model model) {
		try {
			if (StringUtils.equalsIgnoreCase("Y", esbEnable)) {
				SubsInfo subsInfo = checkByCtn(httpServletRequest);
				if (subsInfo == null) {
					logger.debug("{}", "(subsInfo == null)");

					return "errorFullPage";
				}

				List<TermsVO> list = pNDDao.selectCurrentTerms();

				model.addAttribute("list", list != null ? list : new ArrayList<TermsVO>());
			} else {
				List<TermsVO> list = pNDDao.selectCurrentTerms();

				model.addAttribute("list", list != null ? list : new ArrayList<TermsVO>());
			}

			return "limitAcceptTerms";
		} catch (Exception e) {
			logger.debug("{}", e.getMessage());

			e.printStackTrace();
		}

		return "errorFullPage";
	}

	/**
	 * ??????????????????1 > ????????????
	 */
	@Override
	public String getLimitCompleted(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
			Model model) {
		try {
			if (StringUtils.equalsIgnoreCase("Y", esbEnable)) {
				String itemSn = StringUtils.defaultIfEmpty(httpServletRequest.getParameter("itemSn"), "");
				String usimMdlNm = StringUtils.defaultIfEmpty(httpServletRequest.getParameter("usimMdlNm"), "");
				String usimSn = StringUtils.defaultIfEmpty(httpServletRequest.getParameter("usimSn"), "");
				String naviSn = StringUtils.defaultIfEmpty(httpServletRequest.getParameter("naviSn"), "");
				String termsYn = StringUtils.defaultIfEmpty(httpServletRequest.getParameter("termsYn"), "");
				String registerYn = StringUtils.defaultIfEmpty(httpServletRequest.getParameter("registerYn"), "");
				String oldYn = StringUtils.defaultIfEmpty(httpServletRequest.getParameter("oldYn"), "");
				String uiccId = StringUtils.defaultIfEmpty(httpServletRequest.getParameter("uiccId"), "");

				if (StringUtils.isEmpty(itemSn) || StringUtils.isEmpty(usimMdlNm) || StringUtils.isEmpty(usimSn)
						|| StringUtils.isEmpty(naviSn) || StringUtils.isEmpty(termsYn)
						|| StringUtils.isEmpty(registerYn) || StringUtils.isEmpty(oldYn)
						|| StringUtils.isEmpty(uiccId)) {
					logger.debug("parameter is empty");

					return "errorFullPage";
				}

				String startDt = DateUtils.getCurrentDate(Calendar.DATE, 0, DateUtils.DATE_FORMAT_YMDHMS);
				String endDt = DateUtils.getCurrentDate(Calendar.DATE, 90, DateUtils.DATE_FORMAT_YMDHMS);

				java.util.Date validStartDt = new SimpleDateFormat(DateUtils.DATE_FORMAT_YMDHMS).parse(startDt);
				java.util.Date validEndDt = new SimpleDateFormat(DateUtils.DATE_FORMAT_YMDHMS).parse(endDt);

				MembCommAgrVO membCommAgr = new MembCommAgrVO();
				membCommAgr.setItemSn(itemSn);
				membCommAgr.setUsimModelNm(usimMdlNm);
				membCommAgr.setUiccId(uiccId);
				membCommAgr.setNaviSn(naviSn);
				membCommAgr.setValidStartDt(validStartDt);
				membCommAgr.setValidEndDt(validEndDt);

				int ret = termsDao.insertMembCommAgr(membCommAgr);
				if (ret != 1) {
					logger.debug("failed to insertMembCommAgr");

					return "errorFullPage";
				}

			} else {
				String itemSn = StringUtils.defaultIfEmpty(httpServletRequest.getParameter("itemSn"), "");
				String usimMdlNm = StringUtils.defaultIfEmpty(httpServletRequest.getParameter("usimMdlNm"), "");
				String usimSn = StringUtils.defaultIfEmpty(httpServletRequest.getParameter("usimSn"), "");
				String naviSn = StringUtils.defaultIfEmpty(httpServletRequest.getParameter("naviSn"), "");
				String termsYn = StringUtils.defaultIfEmpty(httpServletRequest.getParameter("termsYn"), "");
				String registerYn = StringUtils.defaultIfEmpty(httpServletRequest.getParameter("registerYn"), "");
				String oldYn = StringUtils.defaultIfEmpty(httpServletRequest.getParameter("oldYn"), "");
				String uiccId = StringUtils.defaultIfEmpty(httpServletRequest.getParameter("uiccId"), "");

				if (StringUtils.isEmpty(itemSn) || StringUtils.isEmpty(usimMdlNm) || StringUtils.isEmpty(usimSn)
						|| StringUtils.isEmpty(naviSn) || StringUtils.isEmpty(termsYn)
						|| StringUtils.isEmpty(registerYn) || StringUtils.isEmpty(oldYn)
						|| StringUtils.isEmpty(uiccId)) {
					logger.debug("parameter is empty");

					return "errorFullPage";
				}

				String startDt = DateUtils.getCurrentDate(Calendar.DATE, 0, DateUtils.DATE_FORMAT_YMDHMS);
				String endDt = DateUtils.getCurrentDate(Calendar.DATE, 1, DateUtils.DATE_FORMAT_YMDHMS);

				java.util.Date validStartDt = new SimpleDateFormat(DateUtils.DATE_FORMAT_YMDHMS).parse(startDt);
				java.util.Date validEndDt = new SimpleDateFormat(DateUtils.DATE_FORMAT_YMDHMS).parse(endDt);

				MembCommAgrVO membCommAgr = new MembCommAgrVO();
				membCommAgr.setItemSn(itemSn);
				membCommAgr.setUsimModelNm(usimMdlNm);
				membCommAgr.setUiccId(uiccId);
				membCommAgr.setNaviSn(naviSn);
				membCommAgr.setValidStartDt(validStartDt);
				membCommAgr.setValidEndDt(validEndDt);

				int ret = termsDao.insertMembCommAgr(membCommAgr);
				if (ret != 1) {
					logger.debug("failed to insertMembCommAgr");

					return "errorFullPage";
				}
			}

			return "limitCompleted";
		} catch (Exception e) {
			logger.debug("{}", e.getMessage());

			e.printStackTrace();
		}

		return "errorFullPage";
	}

	/**
	 * ??????????????????1 > ????????????
	 */
	@Override
	public String getLimitSubsInfo(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
			Model model) {
		try {
			if (StringUtils.equalsIgnoreCase("Y", esbEnable)) {
				MembVO memb = validationByAll(httpServletRequest);
				
				
				if (memb == null) {
					logger.debug("{}", "(memb == null)");

					return "errorPage";
				}
				logger.debug("PNDServiceImpl_getLimitSubsInfo_getJoinDt", memb.getJoinDt());

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
				//SubsInfoVO subsInfo = eSBManager.getSubsInfo(memb.getMembNo(), memb.getProductNm());
				if (subsInfo == null) {
					logger.debug("{}", "(subsInfo == null)");

					return "errorPage";
				}

				String callYm = new SimpleDateFormat("yyyyMMdd").format(new Date());
				
				String ip = getIp();
			

				AmountDataVO amountData = eSBManager.getAmountData(subsInfoNcas.getSub_no(), memb.getProductNm(), callYm, ip);
				//AmountDataVO amountData = eSBManager.getAmountData(memb.getMembNo(), memb.getProductNm(), callYm);
				if (amountData == null) {
					logger.debug("{}", "(amountData == null)");
					
					String defaultAlloValue = defaultAmountMap.get(subsInfo.getSvcCd());
					if (defaultAlloValue == null) {
						defaultAlloValue = "0";
					}
					String defaultUseValue = "0";
					
					amountData = new AmountDataVO();
					amountData.setAlloValue(defaultAlloValue);
					amountData.setUseValue(defaultUseValue);
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

				String svcFrstStrtDttm = "";
				if (StringUtils.isNotEmpty(subsInfo.getSvcFrstStrtDttm())
						&& VALID_DATE_YYYYMMDD_REGEX.matcher(subsInfo.getSvcFrstStrtDttm()).find()
						&& !subsInfo.getSvcFrstStrtDttm().startsWith("9999")) {
					//Date date = memb.getJoinDt();
					Date date = new SimpleDateFormat("yyyyMMdd").parse(subsInfo.getSvcFrstStrtDttm());

					svcFrstStrtDttm = new SimpleDateFormat("yyyy.MM.dd(E)").format(date);

				}

				/*if (StringUtils.isNotEmpty(subsInfo.getSvcFrstStrtDttm())
						&& VALID_DATE_YYYYMMDD_REGEX.matcher(subsInfo.getSvcFrstStrtDttm()).find()
						&& !subsInfo.getSvcFrstStrtDttm().startsWith("9999")) {
					Date date = new SimpleDateFormat("yyyyMMdd").parse(subsInfo.getSvcFrstStrtDttm());

					svcFrstStrtDttm = new SimpleDateFormat("yyyy.MM.dd(E)").format(date);

					// Calendar calendar = Calendar.getInstance();
					// calendar.setTime(date);
					//
					// String[] dayOfTheWeeks = { "???", "???", "???", "???", "???", "???",
					// "???" };
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
					
					//?????? ???????????? ?????? ?????? 20190128 kangjin
					String connDeviceModelId = getConnDeviceModel(memb);
					
					if("EL1".equals(connDeviceModelId)) {
						calendar.add(Calendar.YEAR, 2);
					}else {
						calendar.add(Calendar.YEAR, 1);
					}
					
					calendar.add(Calendar.DATE, -1);

					svcStrtDttm = new SimpleDateFormat("yyyy.MM.dd").format(startDate);
					svcEndDttm = new SimpleDateFormat("yyyy.MM.dd").format(calendar.getTime());
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

				String minNo = subsInfo.getMinNo();
				String first="";
				String middle ="";
				String last="";
				String newMin="";
				
				//first = minNo.substring(0,3);
				//last = minNo.substring(4, 12);
				
				//newMin = first + last;
				
				first = minNo.substring(0,3);
				middle = minNo.substring(4,8);
				last = minNo.substring(8,12);
				
				newMin = first +"-"+middle+"-"+ last;
				
				model.addAttribute("alloValue", StringUtils.defaultIfEmpty(alloValue, ""));// ?????????
				model.addAttribute("fxedFctr1", StringUtils.defaultIfEmpty(fxedFctr1, ""));// ????????????
				model.addAttribute("svcEndDttm", StringUtils.defaultIfEmpty(svcEndDttm, ""));// ????????????
				model.addAttribute("svcStrtDttm", StringUtils.defaultIfEmpty(svcStrtDttm, ""));// ????????????
				model.addAttribute("svcFrstStrtDttm", StringUtils.defaultIfEmpty(svcFrstStrtDttm, ""));// ????????????
				model.addAttribute("svcNm", StringUtils.defaultIfEmpty(svcNm, ""));// ??????(?????????)???
				model.addAttribute("useValue", StringUtils.defaultIfEmpty(useValue, ""));// ?????????
				model.addAttribute("remainingValue", StringUtils.defaultIfEmpty(remainingValue, ""));// ??????
																										// ?????????
				model.addAttribute("ctn", StringUtils.defaultIfEmpty(newMin, ""));// ????????????
			} else {
				Calendar calendar = Calendar.getInstance();
				calendar.add(Calendar.YEAR, 1);

				String fxedFctr1 = "";
				String svcEndDttm = new SimpleDateFormat("MM.dd").format(calendar.getTime());
				String svcFrstStrtDttm = new SimpleDateFormat("yyyy.MM.dd").format(new Date());
				String svcNm = "LTE) ?????????/??????????????? ????????? 10GB";
				String ctn = "01212345678";
				
				System.out.println("############ map : " + defaultAmountMap);
				
				String alloValue = defaultAmountMap.get("LPZ0010077");
				if (alloValue == null) {
					alloValue = "0";
				}
				if (VALID_NUMBER_REGEX.matcher(alloValue).find()) {
					alloValue = String.valueOf((int) (Float.parseFloat(alloValue) / 1024.0f));
				}
				
				String useValue = "0";
				if (VALID_NUMBER_REGEX.matcher(useValue).find()) {
					useValue = String.valueOf((int) (Float.parseFloat(useValue) / 1024.0f));
				}

				String remainingValue = "0";
				if (VALID_NUMBER_REGEX.matcher(alloValue).find() && VALID_NUMBER_REGEX.matcher(useValue).find()) {
					remainingValue = String.valueOf(Long.parseLong(alloValue) - Long.parseLong(useValue));
				}
				
				model.addAttribute("alloValue", alloValue);// ?????????
				model.addAttribute("fxedFctr1", fxedFctr1);// ????????????
				model.addAttribute("svcEndDttm", svcEndDttm);// ????????????
				model.addAttribute("svcFrstStrtDttm", svcFrstStrtDttm);// ????????????
				model.addAttribute("svcNm", svcNm);// ??????(?????????)???
				model.addAttribute("useValue", useValue);// ?????????
				model.addAttribute("remainingValue", StringUtils.defaultIfEmpty(remainingValue, ""));// ??????
																										// ?????????
				model.addAttribute("ctn", ctn);// ?????????
			}

			return "limitSubsInfo";
		} catch (Exception e) {
			logger.debug("{}", e.getMessage());

			e.printStackTrace();
		}

		return "errorPage";
	}

	/**
	 * ??????????????????1 > ???????????????
	 */
	@Override
	public String getLimitApplyPlan(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
			Model model) {
		try {
			if (StringUtils.equalsIgnoreCase("Y", esbEnable)) {
				MembVO memb = validationByAll(httpServletRequest);
				if (memb == null) {
					logger.debug("{}", "(memb == null)");

					return "errorPage";
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
				//SubsInfoVO subsInfo = eSBManager.getSubsInfo(memb.getMembNo(), memb.getProductNm());
				if (subsInfo == null) {
					logger.debug("{}", "(subsInfo == null)");

					return "errorPage";
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
				
				//?????? ???????????? ?????? ?????? 20190128 kangjin
				String connDeviceModelId = getConnDeviceModel(memb);
				
				if("EL1".equals(connDeviceModelId)) {
					calendar.add(Calendar.YEAR, 2);
				}else {
					calendar.add(Calendar.YEAR, 1);
				}
				
				calendar.add(Calendar.DATE, -1);
				
				String svcEndDttm1 = new SimpleDateFormat("yyyy??? MM??? dd???").format(calendar.getTime());
				String svcEndDttm2 = new SimpleDateFormat("yyyy-MM-dd").format(calendar.getTime());
				String payResvYn = StringUtils.defaultIfEmpty(StringUtils.equals("Y", memb.getPayResvYn()) ? "Y" : "N",
						"N");

				//kangjin
				String strJini = "";
				
				if("EL1".equals(connDeviceModelId)) {
					strJini = "";
				}else {
					strJini = "(???????????? ????????? ?????? ??????)";
				}				
				//model.addAttribute("svcEndDttm1", svcEndDttm1);// ???????????????(2018??? 1??? 1???)
				//model.addAttribute("svcEndDttm2", svcEndDttm2);// ???????????????(2018-01-01)
				String limitApplyPlan = new String(limitApplyPlanHtml);
				limitApplyPlan = limitApplyPlan.replace("#svcEndDttm1", svcEndDttm1);
				limitApplyPlan = limitApplyPlan.replace("#svcEndDttm2", svcEndDttm2);
				limitApplyPlan = limitApplyPlan.replace("#strJini", strJini);
				model.addAttribute("limitApplyPlanHtml", limitApplyPlan);
				
				model.addAttribute("payResvYn", payResvYn);// ?????????????????????
			} else {
				Calendar calendar = Calendar.getInstance();
				calendar.getTime();
				calendar.add(Calendar.YEAR, 1);

				String svcEndDttm1 = new SimpleDateFormat("MM??? dd???").format(calendar.getTime());
				String svcEndDttm2 = new SimpleDateFormat("yyyy-MM-dd").format(calendar.getTime());

				//model.addAttribute("svcEndDttm1", svcEndDttm1);// ???????????????(2018??? 1??? 1???)
				//model.addAttribute("svcEndDttm2", svcEndDttm2);// ???????????????(2018-1-1)
				String limitApplyPlan = new String(limitApplyPlanHtml);
				limitApplyPlan = limitApplyPlan.replace("#svcEndDttm1", svcEndDttm1);
				limitApplyPlan = limitApplyPlan.replace("#svcEndDttm2", svcEndDttm2);
				model.addAttribute("limitApplyPlanHtml", limitApplyPlan);
				
				model.addAttribute("payResvYn", "N");// ?????????????????????	
			}

			return "limitApplyPlan";
		} catch (Exception e) {
			logger.debug("{}", e.getMessage());

			e.printStackTrace();
		}

		return "errorPage";
	}

	/**
	 * ??????????????????1 > ??????????????? > ??????????????????
	 */
	@Override
	public String getLimitEnterBillInfo(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
			Model model) {
		try {
			if (StringUtils.equalsIgnoreCase("Y", esbEnable)) {
				MembVO memb = validationByAll(httpServletRequest);
				if (memb == null) {
					logger.debug("{}", "(memb == null)");

					return "errorPage";
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
				//SubsInfoVO subsInfo = eSBManager.getSubsInfo(memb.getMembNo(), memb.getProductNm());
				if (subsInfo == null) {
					logger.debug("{}", "(subsInfo == null)");

					return "errorPage";
				}

				String payResvYn = StringUtils.defaultIfEmpty(StringUtils.equals("Y", memb.getPayResvYn()) ? "Y" : "N",
						"N");

				model.addAttribute("pymMthdCd", StringUtils.defaultIfEmpty(subsInfo.getPymMthdCd(), ""));// ??????????????????
				model.addAttribute("payResvYn", payResvYn);// ?????????????????????

			} else {
				model.addAttribute("pymMthdCd", "CC");// ??????????????????
				model.addAttribute("payResvYn", "N");// ?????????????????????
			}

			return "limitEnterBillInfo";
		} catch (Exception e) {
			logger.debug("{}", e.getMessage());

			e.printStackTrace();
		}

		return "errorPage";
	}

	/**
	 * ??????????????????1 > ????????????
	 */
	@Override
	public String getLimitTerms(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
			Model model) {
		try {
			if (StringUtils.equalsIgnoreCase("Y", esbEnable)) {
				MembVO memb = validationByAll(httpServletRequest);
				if (memb == null) {
					logger.debug("{}", "(memb == null)");

					return "errorPage";
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

			return "limitTerms";
		} catch (Exception e) {
			logger.debug("{}", e.getMessage());

			e.printStackTrace();
		}

		return "errorPage";
	}

	/**
	 * ??????????????????2 > ????????????
	 */
	@Override
	public String getUnlimitSubsInfo(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
			Model model) {
		try {
			if (StringUtils.equalsIgnoreCase("Y", esbEnable)) {
				MembVO memb = validationByAll(httpServletRequest);
				if (memb == null) {
					logger.debug("{}", "(memb == null)");

					return "errorPage";
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
				//SubsInfoVO subsInfo = eSBManager.getSubsInfo(memb.getMembNo(), memb.getProductNm());
				
				if (subsInfo == null) {
					logger.debug("{}", "(subsInfo == null)");

					return "errorPage";
				}

				String callYm = new SimpleDateFormat("yyyyMMdd").format(new Date());
				String ip = getIp();
				
				AmountDataVO amountData = eSBManager.getAmountData(subsInfoNcas.getSub_no(), memb.getProductNm(), callYm, ip);
				//AmountDataVO amountData = eSBManager.getAmountData(memb.getMembNo(), memb.getProductNm(), callYm);
				if (amountData == null) {
					logger.debug("{}", "(amountData == null)");

					String defaultAlloValue = defaultAmountMap.get(subsInfo.getSvcCd());
					if (defaultAlloValue == null) {
						defaultAlloValue = "0";
					}
					String defaultUseValue = "0";
					
					amountData = new AmountDataVO();
					amountData.setAlloValue(defaultAlloValue);
					amountData.setUseValue(defaultUseValue);
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

				String svcFrstStrtDttm = "";
				if (StringUtils.isNotEmpty(subsInfo.getSvcFrstStrtDttm())
						&& VALID_DATE_YYYYMMDD_REGEX.matcher(subsInfo.getSvcFrstStrtDttm()).find()
						&& !subsInfo.getSvcFrstStrtDttm().startsWith("9999")) {
					Date date = new SimpleDateFormat("yyyyMMdd").parse(subsInfo.getSvcFrstStrtDttm());

					svcFrstStrtDttm = new SimpleDateFormat("yyyy??? MM??? dd??? (E)").format(date);

					// Calendar calendar = Calendar.getInstance();
					// calendar.setTime(date);
					//
					// String[] dayOfTheWeeks = { "???", "???", "???", "???", "???", "???",
					// "???" };
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

				svcStrtDttm = new SimpleDateFormat("yyyy.MM.").format(today) + String.format("%02d", min);
				svcEndDttm = new SimpleDateFormat("MM.").format(today) + String.format("%02d", max);

				String svcNm = subsInfo.getSvcNm();
				String useValue = "0";
				if (VALID_NUMBER_REGEX.matcher(amountData.getUseValue()).find()) {
					useValue = String.valueOf((int) (Float.parseFloat(amountData.getUseValue()) / 1024.0f));
				}

				String remainingValue = "0";
				if (VALID_NUMBER_REGEX.matcher(alloValue).find() && VALID_NUMBER_REGEX.matcher(useValue).find()) {
					remainingValue = String.valueOf(Long.parseLong(alloValue) - Long.parseLong(useValue));
				}
				
				String minNo = subsInfo.getMinNo();
				String first="";
				String middle ="";
				String last="";
				String newMin="";
				
				//first = minNo.substring(0,3);
				//last = minNo.substring(4, 12);
				
				//newMin = first + last;
				
				first = minNo.substring(0,3);
				middle = minNo.substring(4,8);
				last = minNo.substring(8,12);
				
				newMin = first +"-"+middle+"-"+ last;
				
				model.addAttribute("alloValue", StringUtils.defaultIfEmpty(alloValue, ""));// ?????????
				model.addAttribute("fxedFctr1", StringUtils.defaultIfEmpty(fxedFctr1, ""));// ????????????
				model.addAttribute("svcEndDttm", StringUtils.defaultIfEmpty(svcEndDttm, ""));// ????????????
				model.addAttribute("svcStrtDttm", StringUtils.defaultIfEmpty(svcStrtDttm, ""));// ????????????
				model.addAttribute("svcFrstStrtDttm", StringUtils.defaultIfEmpty(svcFrstStrtDttm, ""));// ????????????
				model.addAttribute("svcNm", StringUtils.defaultIfEmpty(svcNm, ""));// ??????(?????????)???
				model.addAttribute("useValue", StringUtils.defaultIfEmpty(useValue, ""));// ?????????
				model.addAttribute("remainingValue", StringUtils.defaultIfEmpty(remainingValue, ""));// ?????? ?????????
				model.addAttribute("ctn" ,StringUtils.defaultIfEmpty(newMin,""));// ????????????
			} else {
				Calendar calendar = Calendar.getInstance();
				calendar.add(Calendar.YEAR, 1);

				String fxedFctr1 = "";
				String svcEndDttm = new SimpleDateFormat("MM??? dd???").format(calendar.getTime());
				String svcFrstStrtDttm = new SimpleDateFormat("yyyy???MM???dd???").format(new Date());
				String svcNm = "LTE) ?????????/??????????????? ????????? 10GB";
				String ctn = "012012345678";
				
				System.out.println("############ map : " + defaultAmountMap);
				
				String alloValue = defaultAmountMap.get("LPZ0010077");
				if (alloValue == null) {
					alloValue = "0";
				}
				if (VALID_NUMBER_REGEX.matcher(alloValue).find()) {
					alloValue = String.valueOf((int) (Float.parseFloat(alloValue) / 1024.0f));
				}
				
				String useValue = "0";
				if (VALID_NUMBER_REGEX.matcher(useValue).find()) {
					useValue = String.valueOf((int) (Float.parseFloat(useValue) / 1024.0f));
				}

				String remainingValue = "0";
				if (VALID_NUMBER_REGEX.matcher(alloValue).find() && VALID_NUMBER_REGEX.matcher(useValue).find()) {
					remainingValue = String.valueOf(Long.parseLong(alloValue) - Long.parseLong(useValue));
				}

				model.addAttribute("alloValue", alloValue);// ?????????
				model.addAttribute("fxedFctr1", fxedFctr1);// ????????????
				model.addAttribute("svcEndDttm", svcEndDttm);// ????????????
				model.addAttribute("svcFrstStrtDttm", svcFrstStrtDttm);// ????????????
				model.addAttribute("svcNm", svcNm);// ??????(?????????)???
				model.addAttribute("useValue", useValue);// ?????????

				String minNo = ctn;
				String first="";
				String middle ="";
				String last="";
				String newMin="";
				
				//first = minNo.substring(0,3);
				//last = minNo.substring(4, 12);
				
				//newMin = first + last;
				
				first = minNo.substring(0,3);
				middle = minNo.substring(4,8);
				last = minNo.substring(8,12);
				
				newMin = first +"-"+middle+"-"+ last;
				
				model.addAttribute("remainingValue", StringUtils.defaultIfEmpty(remainingValue, ""));// ??????  ?????????				
				model.addAttribute("ctn", newMin);// ????????????

			}

			return "unlimitSubsInfo";
		} catch (Exception e) {
			logger.debug("{}", e.getMessage());

			e.printStackTrace();
		}

		return "errorPage";
	}

	/**
	 * ??????????????????2 > ??????????????? > ??????????????????
	 */
	@Override
	public String getUnlimitPayInfo(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
			Model model) {
		try {
			if (StringUtils.equalsIgnoreCase("Y", esbEnable)) {
				MembVO memb = validationByAll(httpServletRequest);
				if (memb == null) {
					logger.debug("{}", "(memb == null)");

					return "errorPage";
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
				//PayInfoVO payInfo = eSBManager.getPayInfo(memb.getMembNo(), memb.getProductNm(), billTrgtYymm);
				if (payInfo == null) {
					logger.debug("{}", "(payInfo == null)");

					return "errorPage";
				}

				String addWdrwTrgtCntnt = "";
				if (StringUtils.isNotEmpty(payInfo.getAddWdrwTrgtCntnt())
						&& VALID_DATE_YYYYMMDD_REGEX.matcher(payInfo.getAddWdrwTrgtCntnt()).find()) {
					Date date = new SimpleDateFormat("yyyyMMdd").parse(payInfo.getAddWdrwTrgtCntnt());

					addWdrwTrgtCntnt = new SimpleDateFormat("yyyy??? MM??? dd???").format(date);
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

					duedDt = new SimpleDateFormat("??? ??? dd???").format(date);
				}
				String cardNm = payInfo.getCardNm();
				String pymMthdCd = payInfo.getPymMthdCd();
				String pymMthdNm = payInfo.getPymMthdNm();
				String svcNm = payInfo.getSvcNm();

				model.addAttribute("addWdrwTrgtCntnt", StringUtils.defaultIfEmpty(addWdrwTrgtCntnt, ""));// ?????????
				model.addAttribute("bankNm", StringUtils.defaultIfEmpty(bankNm, ""));// ?????????
				model.addAttribute("billAcntNo", StringUtils.defaultIfEmpty(billAcntNo, ""));// ??????????????????
				model.addAttribute("bltxtKdCd", StringUtils.defaultIfEmpty(bltxtKdCd, ""));// ??????????????????(ex)N)
				model.addAttribute("bltxtKdNm", StringUtils.defaultIfEmpty(bltxtKdNm, ""));// ?????????????????????(ex)??????(????????????)
																							// ?????????)
				model.addAttribute("cardDepoNm", StringUtils.defaultIfEmpty(cardDepoNm, ""));// ??????(??????)??????
				model.addAttribute("cardNm", StringUtils.defaultIfEmpty(cardNm, ""));// ???????????????
				model.addAttribute("duedDt", StringUtils.defaultIfEmpty(duedDt, ""));// ?????????
				model.addAttribute("pymMthdCd", StringUtils.defaultIfEmpty(pymMthdCd, ""));// ??????????????????(ex)GR)
				model.addAttribute("pymMthdNm", StringUtils.defaultIfEmpty(pymMthdNm, ""));// ?????????????????????(ex)??????)
				model.addAttribute("svcNm", StringUtils.defaultIfEmpty(svcNm, ""));// ??????(?????????)???
			} else {

				String addWdrwTrgtCntnt = new SimpleDateFormat("yyyy??? MM??? dd???").format(new Date());
				String bankNm = "";
				String billAcntNo = "";
				String bltxtKdCd = "";
				String bltxtKdNm = "?????????(?????? ?????????)";
				String cardDepoNm = "?????????".replaceAll("(?<=.{1}).", "*");
				String duedDt = new SimpleDateFormat("yyyy??? MM??? dd???").format(new Date());
				String cardNm = "????????????";
				String pymMthdCd = "";
				String pymMthdNm = "???????????? ????????????";
				String svcNm = "LTE) ?????????/??????????????? ????????? 10GB";

				model.addAttribute("addWdrwTrgtCntnt", addWdrwTrgtCntnt);// ?????????
				model.addAttribute("bankNm", bankNm);// ?????????
				model.addAttribute("billAcntNo", billAcntNo);// ??????????????????
				model.addAttribute("bltxtKdCd", bltxtKdCd);// ??????????????????(ex)N)
				model.addAttribute("bltxtKdNm", bltxtKdNm);// ?????????????????????(ex)??????(????????????)
															// ?????????)
				model.addAttribute("cardDepoNm", cardDepoNm);// ??????(??????)??????
				model.addAttribute("cardNm", cardNm);// ???????????????
				model.addAttribute("duedDt", duedDt);// ?????????
				model.addAttribute("pymMthdCd", pymMthdCd);// ??????????????????(ex)GR)
				model.addAttribute("pymMthdNm", pymMthdNm);// ?????????????????????(ex)??????)
				model.addAttribute("svcNm", svcNm);// ??????(?????????)???
			}

			return "unlimitPayInfo";
		} catch (Exception e) {
			logger.debug("{}", e.getMessage());

			e.printStackTrace();
		}

		return "errorPage";
	}

	/**
	 * ??????????????????2 > ??????????????? > ?????????????????? > ??????????????????
	 */
	@Override
	public String getUnlimitPayMethod(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
			Model model) {
		try {
			if (StringUtils.equalsIgnoreCase("Y", esbEnable)) {
				MembVO memb = validationByAll(httpServletRequest);
				if (memb == null) {
					logger.debug("{}", "(memb == null)");

					return "errorPage";
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
				//PayMethodVO payMethod = eSBManager.getPayMethod(memb.getMembNo(), memb.getProductNm(), billTrgtYymm);
				if (payMethod == null) {
					logger.debug("{}", "(payMethod == null)");

					return "errorPage";
				}

				String addWdrwTrgtCntnt = "";
				if (StringUtils.isNotEmpty(payMethod.getAddWdrwTrgtCntnt())
						&& VALID_DATE_YYYYMMDD_REGEX.matcher(payMethod.getAddWdrwTrgtCntnt()).find()) {
					Date date = new SimpleDateFormat("yyyyMMdd").parse(payMethod.getAddWdrwTrgtCntnt());

					addWdrwTrgtCntnt = new SimpleDateFormat("yyyy??? MM??? dd???").format(date);
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
				if (StringUtils.isNotEmpty(payMethod.getCustNm())) {
					custNm = payMethod.getCustNm().replaceAll("(?<=.{1}).", "*");
				}
				String cardNm = payMethod.getCardNm();				
				String pymMthdCd = payMethod.getPymMthdCd();
				String pymMthdNm = payMethod.getPymMthdNm();
				String svcNm = payMethod.getSvcNm();

				// ????????? ????????????
				// ????????????????????? : Y,T(e-mail?????????),L(e-mail???????????????),M(???????????????),??????(?????? ?????????)
				// Y : e-mail????????? - email
				// L : e-mail??????????????? - email,ctn
				// M : ??????????????? - ctn
				// W : ????????????????????? ????????? - ctn
				// B : ????????? ????????? - ctn
				// A : E-mail????????? ????????? - email
				// P : receive from cellphone
				// ?????? : ?????? ????????? - addr

				model.addAttribute("addWdrwTrgtCntnt", StringUtils.defaultIfEmpty(addWdrwTrgtCntnt, ""));// ?????????
				model.addAttribute("bankNm", StringUtils.defaultIfEmpty(bankNm, ""));// ?????????
				model.addAttribute("billAcntNo", StringUtils.defaultIfEmpty(billAcntNo, ""));// ??????????????????
				model.addAttribute("bltxtKdCd", StringUtils.defaultIfEmpty(bltxtKdCd, ""));// ??????????????????(ex)N)
				model.addAttribute("bltxtKdNm", StringUtils.defaultIfEmpty(bltxtKdNm, ""));// ?????????????????????(ex)??????(????????????)
																							// ?????????)
				model.addAttribute("cardDepoNm", StringUtils.defaultIfEmpty(cardDepoNm, ""));// ??????(??????)??????
				model.addAttribute("cardNm", StringUtils.defaultIfEmpty(cardNm, ""));// ???????????????
				model.addAttribute("custNm", StringUtils.defaultIfEmpty(custNm, ""));// ?????????
				model.addAttribute("duedDt", StringUtils.defaultIfEmpty(duedDt, ""));// ?????????
				model.addAttribute("pymMthdCd", StringUtils.defaultIfEmpty(pymMthdCd, ""));// ??????????????????(ex)GR)
				model.addAttribute("pymMthdNm", StringUtils.defaultIfEmpty(pymMthdNm, ""));// ?????????????????????(ex)??????)
				model.addAttribute("svcNm", StringUtils.defaultIfEmpty(svcNm, ""));// ??????(?????????)???
			} else {
				String addWdrwTrgtCntnt = new SimpleDateFormat("yyyy??? MM??? dd???").format(new Date());
				String bankNm = "";
				String billAcntNo = "";
				String bltxtKdCd = "";
				String bltxtKdNm = "?????????(?????? ?????????)";
				String cardDepoNm = "?????????".replaceAll("(?<=.{1}).", "*");
				String duedDt = new SimpleDateFormat("yyyy??? MM??? dd???").format(new Date());
				String cardNm = "????????????";
				String custNm = "?????????".replaceAll("(?<=.{1}).", "*");
				String pymMthdCd = "";
				String pymMthdNm = "???????????? ????????????";
				String svcNm = "LTE) ?????????/??????????????? ????????? 10GB";

				model.addAttribute("addWdrwTrgtCntnt", addWdrwTrgtCntnt);// ?????????
				model.addAttribute("bankNm", bankNm);// ?????????
				model.addAttribute("billAcntNo", billAcntNo);// ??????????????????
				model.addAttribute("bltxtKdCd", bltxtKdCd);// ??????????????????(ex)N)
				model.addAttribute("bltxtKdNm", bltxtKdNm);// ?????????????????????(ex)??????(????????????)
															// ?????????)
				model.addAttribute("cardDepoNm", cardDepoNm);// ??????(??????)??????
				model.addAttribute("cardNm", cardNm);// ???????????????
				model.addAttribute("custNm", custNm);// ?????????
				model.addAttribute("duedDt", duedDt);// ?????????
				model.addAttribute("pymMthdCd", pymMthdCd);// ??????????????????(ex)GR)
				model.addAttribute("pymMthdNm", pymMthdNm);// ?????????????????????(ex)??????)
				model.addAttribute("svcNm", svcNm);// ??????(?????????)???
			}

			return "unlimitPayMethod";
		} catch (Exception e) {
			logger.debug("{}", e.getMessage());

			e.printStackTrace();
		}

		return "errorPage";
	}

	/**
	 * ??????????????????2 > ??????????????? > ?????????????????? > ??????????????????
	 */
	@Override
	public String getUnlimitBillType(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
			Model model) {
		try {
			if (StringUtils.equalsIgnoreCase("Y", esbEnable)) {
				MembVO memb = validationByAll(httpServletRequest);
				if (memb == null) {
					logger.debug("{}", "(memb == null)");

					return "errorPage";
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
				//BillTypeVO billType = eSBManager.getBillType(memb.getMembNo(), memb.getProductNm());
				if (billType == null) {
					logger.debug("{}", "(billType == null)");

					return "errorPage";
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
							filterdBillReturnInfo.setBillAcntNo(billReturnInfo.getBillAcntNo());// ??????????????????
						}
						if (StringUtils.isNotEmpty(billReturnInfo.getBillTrgtYymm())) {
							Date date = new SimpleDateFormat("yyyyMM").parse(billReturnInfo.getBillTrgtYymm());

							filterdBillReturnInfo.setBillTrgtYymm(new SimpleDateFormat("yyyy.MM").format(date));// ????????????
						}
						if (StringUtils.isNotEmpty(billReturnInfo.getDlvDt())
								&& VALID_DATE_YYYYMMDD_REGEX.matcher(billReturnInfo.getDlvDt()).find()) {
							Date date = new SimpleDateFormat("yyyyMMdd").parse(billReturnInfo.getDlvDt());

							filterdBillReturnInfo.setDlvDt(new SimpleDateFormat("yyyy.MM.dd").format(date));// ?????????
						}
						if (StringUtils.isNotEmpty(billReturnInfo.getRetnDvNm())) {
							filterdBillReturnInfo.setRetnDvNm(billReturnInfo.getRetnDvNm());// ???????????????
						}
						if (StringUtils.isNotEmpty(billReturnInfo.getRetnRsnNm())) {
							filterdBillReturnInfo.setRetnRsnNm(billReturnInfo.getRetnRsnNm());// ????????????
						}
						tmp.add(filterdBillReturnInfo);
					}
					list = tmp;
				}

				model.addAttribute("billAcntNo", StringUtils.defaultIfEmpty(billAcntNo, ""));// ??????????????????
				model.addAttribute("billEmailAddr1", StringUtils.defaultIfEmpty(billEmailAddr1, ""));// ????????????????????????(?????????)
				model.addAttribute("billEmailAddr2", StringUtils.defaultIfEmpty(billEmailAddr2, ""));// ????????????????????????(?????????)
				model.addAttribute("bltxtKdCd", StringUtils.defaultIfEmpty(bltxtKdCd, ""));// ?????????????????????
				model.addAttribute("bltxtKdNm", StringUtils.defaultIfEmpty(bltxtKdNm, ""));// ??????????????????
				model.addAttribute("bltxtKdValdStrtDt", StringUtils.defaultIfEmpty(bltxtKdValdStrtDt, ""));// ?????????????????????????????????
				model.addAttribute("scurMailRcpYn", StringUtils.defaultIfEmpty(scurMailRcpYn, ""));// ????????????????????????
				model.addAttribute("list", list != null ? list : new ArrayList<BillReturnInfoVO>());// ?????????????????????
			} else {
				List<BillReturnInfoVO> list = new ArrayList<BillReturnInfoVO>();
				{
					String billAcntNo = "";
					String billTrgtYymm = new SimpleDateFormat("yyyy.MM").format(new Date());
					String dlvDt = new SimpleDateFormat("yyyy.MM.dd").format(new Date());
					String retnDvNm = "?????????(?????? ?????????)";
					String retnRsnNm = "????????????";

					BillReturnInfoVO billReturnInfoVO = new BillReturnInfoVO();
					billReturnInfoVO.setBillAcntNo(billAcntNo);// ??????????????????
					billReturnInfoVO.setBillTrgtYymm(billTrgtYymm);// ????????????
					billReturnInfoVO.setDlvDt(dlvDt);// ???????????????
					billReturnInfoVO.setRetnDvNm(retnDvNm);// ?????????
					billReturnInfoVO.setRetnRsnNm(retnRsnNm);// ????????????

					list.add(billReturnInfoVO);
				}

				// bltxtKdCdNm

				String billAcntNo = "";
				String billEmailAddr1 = "notsurely";
				String billEmailAddr2 = "empal.com";
				String bltxtKdCd = "";
				String bltxtKdNm = "?????????(?????? ?????????)";
				String bltxtKdValdStrtDt = new SimpleDateFormat("yyyy.MM.dd").format(new Date());
				String scurMailRcpYn = "Y";

				model.addAttribute("billAcntNo", billAcntNo);// ??????????????????
				model.addAttribute("billEmailAddr1", billEmailAddr1);// ????????????????????????(?????????)
				model.addAttribute("billEmailAddr2", billEmailAddr2);// ????????????????????????(?????????)
				model.addAttribute("bltxtKdCd", bltxtKdCd);// ?????????????????????
				model.addAttribute("bltxtKdNm", bltxtKdNm);// ??????????????????
				model.addAttribute("bltxtKdValdStrtDt", bltxtKdValdStrtDt);// ?????????????????????????????????
				model.addAttribute("scurMailRcpYn", scurMailRcpYn);// ????????????????????????
				model.addAttribute("list", list);// ?????????????????????
			}

			return "unlimitBillType";
		} catch (Exception e) {
			logger.debug("{}", e.getMessage());

			e.printStackTrace();
		}

		return "errorPage";
	}

	/**
	 * ????????? 2 > ??????????????? > ??????????????????
	 */
	@Override
	public String getUnlimitPayDetail(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
			Model model) {
		try {
			if (StringUtils.equalsIgnoreCase("Y", esbEnable)) {
				MembVO memb = validationByAll(httpServletRequest);
				if (memb == null) {
					logger.debug("{}", "(memb == null)");

					return "errorPage";
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
				//PayDetailVO payDetail = eSBManager.getPayDetail(memb.getMembNo(), memb.getProductNm(), billTrgtYymm);
				if (payDetail == null) {
					logger.debug("{}", "(payDetail == null)");

					return "errorPage";
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

				String dscntAmt = "0";
				/* 20180504 ??????????????? ????????? 10GB ????????? ????????? ?????? ??????
				{
					if (DateUtils.isExpireTime(memb.getJoinDt(), ratePaymentPeriod)) {
						if (MembVO.PAY_RESV_Y.equals(memb.getPayResvYn())) {
							// LDZ0002087 (LTE) ?????????/??????????????? ????????? 10GB ????????? ????????? 3000???
							// ??????
							dscntAmt = "3000";
						}
					} else {
						// LDZ0002086 (LTE) ?????????/??????????????? ????????? 10GB ????????? ????????? 15000??? ??????
						dscntAmt = "15000";
					}
					if (StringUtils.isNotEmpty(dscntAmt) && VALID_NUMBER_REGEX.matcher(dscntAmt).find()) {
						dscntAmt = new DecimalFormat("###,###,###").format(Double.parseDouble(dscntAmt));
					}
				}
				*/

				String dscntDtl = "";
				/* 20180504 ??????????????? ????????? 10GB ????????? ????????? ?????? ??????
				if (!StringUtils.equals(dscntAmt, "0")) {
					dscntDtl = "(LTE) ?????????/??????????????? ????????? 10GB ????????? ????????? ??????";
				}
				*/
				
				if (list.size() > 0) {
					dscntAmt = list.get(0).getDscntAmt();
					dscntDtl = list.get(0).getDscntDtl();
				}

				//model.addAttribute("billAcntNo", StringUtils.defaultIfEmpty(billAcntNo, ""));// ??????????????????
				//model.addAttribute("billAmt", StringUtils.defaultIfEmpty(billAmt, ""));// ???????????????(1)
				//model.addAttribute("spramt", StringUtils.defaultIfEmpty(spramt, ""));// ????????????
				//model.addAttribute("totPymScdlAmt", StringUtils.defaultIfEmpty(totPymScdlAmt, ""));// ?????????????????????(1+2)
				//model.addAttribute("txamt", StringUtils.defaultIfEmpty(txamt, ""));// ??????
				//model.addAttribute("upaidChrg", StringUtils.defaultIfEmpty(upaidChrg, ""));// ????????????(2)
				//model.addAttribute("list", list != null ? list : new ArrayList<DiscountInfoVO>());// ????????????
				//model.addAttribute("dscntAmt", StringUtils.defaultIfEmpty(dscntAmt, ""));// ????????????
				//model.addAttribute("dscntDtl", StringUtils.defaultIfEmpty(dscntDtl, ""));// ????????????
				
 				String unlimitPayDetail = new String(unlimitPayDetailHtml);
 				unlimitPayDetail = unlimitPayDetail.replace("#billAcntNo", StringUtils.defaultIfEmpty(billAcntNo, ""));
 				unlimitPayDetail = unlimitPayDetail.replace("#billAmt", StringUtils.defaultIfEmpty(billAmt, ""));
 				unlimitPayDetail = unlimitPayDetail.replace("#spramt", StringUtils.defaultIfEmpty(spramt, ""));
 				unlimitPayDetail = unlimitPayDetail.replace("#totPymScdlAmt", StringUtils.defaultIfEmpty(totPymScdlAmt, ""));
 				unlimitPayDetail = unlimitPayDetail.replace("#txamt", StringUtils.defaultIfEmpty(txamt, ""));
 				unlimitPayDetail = unlimitPayDetail.replace("#upaidChrg", StringUtils.defaultIfEmpty(upaidChrg, ""));
 				unlimitPayDetail = unlimitPayDetail.replace("#dscntAmt", StringUtils.defaultIfEmpty(dscntAmt, ""));
 				unlimitPayDetail = unlimitPayDetail.replace("#dscntDtl",StringUtils.defaultIfEmpty(dscntDtl, "") );

				model.addAttribute("unlimitPayDetailHtml", unlimitPayDetail);
			} else {
				List<DiscountInfoVO> list = new ArrayList<DiscountInfoVO>();
				{
					String dscntAmt = "";// ????????????
					String dscntDtl = "";// ????????????

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
				String dscntDtl = "????????? ??????";

				//model.addAttribute("billAcntNo", billAcntNo);// ??????????????????
				//model.addAttribute("billAmt", billAmt);// ???????????????(1)
				//model.addAttribute("spramt", spramt);// ????????????
				//model.addAttribute("totPymScdlAmt", totPymScdlAmt);// ?????????????????????(1+2)
				//model.addAttribute("txamt", txamt);// ??????
				//model.addAttribute("upaidChrg", upaidChrg);// ????????????(2)
				//model.addAttribute("list", list);// ????????????
				//model.addAttribute("dscntAmt", dscntAmt);// ????????????
				//model.addAttribute("dscntDtl", dscntDtl);// ????????????
				
 				String unlimitPayDetail = new String(unlimitPayDetailHtml);
 				unlimitPayDetail = unlimitPayDetail.replace("#billAcntNo", StringUtils.defaultIfEmpty(billAcntNo, ""));
 				unlimitPayDetail = unlimitPayDetail.replace("#billAmt", StringUtils.defaultIfEmpty(billAmt, ""));
 				unlimitPayDetail = unlimitPayDetail.replace("#spramt", StringUtils.defaultIfEmpty(spramt, ""));
 				unlimitPayDetail = unlimitPayDetail.replace("#totPymScdlAmt", StringUtils.defaultIfEmpty(totPymScdlAmt, ""));
 				unlimitPayDetail = unlimitPayDetail.replace("#txamt", StringUtils.defaultIfEmpty(txamt, ""));
 				unlimitPayDetail = unlimitPayDetail.replace("#upaidChrg", StringUtils.defaultIfEmpty(upaidChrg, ""));
 				unlimitPayDetail = unlimitPayDetail.replace("#dscntAmt", StringUtils.defaultIfEmpty(dscntAmt, ""));
 				unlimitPayDetail = unlimitPayDetail.replace("#dscntDtl",StringUtils.defaultIfEmpty(dscntDtl, "") );

				model.addAttribute("unlimitPayDetailHtml", unlimitPayDetail);
			}

			return "unlimitPayDetail";
		} catch (Exception e) {
			logger.debug("{}", e.getMessage());

			e.printStackTrace();
		}

		return "errorPage";
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
	 * ??????????????????2 > ????????????????????? > ???????????????????????????
	 */
	@SuppressWarnings("unused")
	@Override
	public String getUnlimitPauseHistory(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
			Model model) {
		try {
			if (StringUtils.equalsIgnoreCase("Y", esbEnable)) {
				MembVO memb = validationByCtn(httpServletRequest);
				if (memb == null) {
					logger.debug("{}", "(memb == null)");

					return "errorPage";
				}

				/*SubsInfo subsInfo = checkByCtn(httpServletRequest);
				if (subsInfo == null) {
					logger.debug("{}", "(subsInfo == null)");

					return "errorPage";
				}

				boolean isPause = false;
				if (subsInfo.getCtn_stus_code().equals("S")) {
					isPause = true;
				}

				model.addAttribute("isPause", isPause);// ??????????????????
*/
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
				//SubsInfoVO subsInfo = eSBManager.getSubsInfo(memb.getMembNo(), memb.getProductNm());
				if (subsInfo == null) {
					logger.debug("{}", "(subsInfo == null)");

					return "errorPage";
				}
				if(subsInfo.getEntrSttsCd().equals("A")){
					PauseHistoryCollection pauseHistory = eSBManager.getPauseHistorys(subsInfoNcas.getSub_no(), memb.getProductNm());
					if (pauseHistory == null) {
						logger.debug("{}", "(pauseHistoryCollection == null)");

						return "errorPage";
					}else {
						List<PauseHistory> list = pauseHistory.getList();
						if (list != null && !list.isEmpty()) {
							List<PauseHistory> tmp = new ArrayList<PauseHistory>();
							for (int i = 0; i < list.size(); i++) {
								PauseHistory pauseHistorys = list.get(i);
								PauseHistory filterdPauseHistory = new PauseHistory();
									if("????????????".equals(pauseHistorys.getEntrSttsNm())) {
										String entrSttsValdStrtDt ="";
										if(StringUtils.isNotEmpty(pauseHistorys.getEntrSttsValdStrtDt()) ) {
											Date date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.S").parse(pauseHistorys.getEntrSttsValdStrtDt());
											entrSttsValdStrtDt = new SimpleDateFormat("yyyy.MM.dd").format(date);
										}
										
										String entrSttsValdEndDt = "";
										if(StringUtils.isNotEmpty(pauseHistorys.getEntrSttsValdEndDt()) ) {
											Date date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.S").parse(pauseHistorys.getEntrSttsValdEndDt());
											entrSttsValdEndDt = new SimpleDateFormat("yyyy.MM.dd").format(date);
										}
										Date date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.S").parse(pauseHistorys.getEntrSttsValdStrtDt());
										Date date1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.S").parse(pauseHistorys.getEntrSttsValdEndDt());
										long diffDate = date.getTime() - date1.getTime();
										long diffDate1 = diffDate / (24*60*60*1000);
										diffDate1 = Math.abs(diffDate1);
										String susDate = String.valueOf(diffDate1);
										String entrSttsChngRsnDtlNm = StringUtils.defaultIfEmpty(pauseHistorys.getEntrSttsChngRsnDtlNm(), "");
										String entrSttsNm = StringUtils.defaultIfEmpty(pauseHistorys.getEntrSttsNm(), "");
										String prodNm = StringUtils.defaultIfEmpty(pauseHistorys.getProdNm(), "");
										String prodNo = StringUtils.defaultIfEmpty(pauseHistorys.getProdNo(), "");
										
										filterdPauseHistory.setEntrSttsValdStrtDt(entrSttsValdStrtDt);
										filterdPauseHistory.setEntrSttsValdEndDt(entrSttsValdEndDt);
										filterdPauseHistory.setEntrSttsChngRsnDtlNm(entrSttsChngRsnDtlNm);
										filterdPauseHistory.setEntrSttsNm(entrSttsNm);
										filterdPauseHistory.setProdNm(subsInfo.getSvcNm());
										filterdPauseHistory.setProdNo(susDate);
										
										tmp.add(filterdPauseHistory);
									}else {
										String entrSttsValdStrtDt ="";
										if(StringUtils.isNotEmpty(pauseHistorys.getEntrSttsValdStrtDt()) ) {
											Date date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.S").parse(pauseHistorys.getEntrSttsValdStrtDt());
											entrSttsValdStrtDt = new SimpleDateFormat("yyyy.MM.dd").format(date);
										}
										
										String entrSttsValdEndDt = "";
										if(StringUtils.isNotEmpty(pauseHistorys.getEntrSttsValdEndDt()) ) {
											Date date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.S").parse(pauseHistorys.getEntrSttsValdEndDt());
											entrSttsValdEndDt = new SimpleDateFormat("yyyy.MM.dd").format(date);
										}
										String entrSttsChngRsnDtlNm = StringUtils.defaultIfEmpty(pauseHistorys.getEntrSttsChngRsnDtlNm(), "");
										String entrSttsNm = StringUtils.defaultIfEmpty(pauseHistorys.getEntrSttsNm(), "");
										String prodNm = StringUtils.defaultIfEmpty(pauseHistorys.getProdNm(), "");
										String prodNo = StringUtils.defaultIfEmpty(pauseHistorys.getProdNo(), "");
										
/*										Date date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.S").parse(pauseHistorys.getEntrSttsValdStrtDt());
										Date date1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.S").parse(pauseHistorys.getEntrSttsValdEndDt());
										long diffDate = date.getTime() - date1.getTime();
										
										long diffDate1 = diffDate / (24*60*60*1000);
										diffDate1 = Math.abs(diffDate1);
										String susDate = StringUtils.defaultIfEmpty(String.valueOf(diffDate1), "");*/
										
										filterdPauseHistory.setEntrSttsValdStrtDt(entrSttsValdStrtDt);
										filterdPauseHistory.setEntrSttsValdEndDt(entrSttsValdEndDt);
										filterdPauseHistory.setEntrSttsChngRsnDtlNm(entrSttsChngRsnDtlNm);
										filterdPauseHistory.setEntrSttsNm(entrSttsNm);
										filterdPauseHistory.setProdNm(subsInfo.getSvcNm());
										filterdPauseHistory.setProdNo(prodNo);
										
										tmp.add(filterdPauseHistory);
									}
							}
							list = tmp;

							pauseHistory.setList(list);
						}
					}
					List<PauseHistory> list = pauseHistory.getList();
					if(list.get(0).getEntrSttsNm().equals("????????????")) {
						model.addAttribute("isPause", "????????????");// ??????????????????
					}else if(list.get(0).getEntrSttsNm().equals("??????")){
						model.addAttribute("isPause", "??????");// ??????????????????
					}else if(list.get(0).getEntrSttsNm().equals("??????")){
						model.addAttribute("isPause", "?????????");// ??????????????????
					}
					model.addAttribute("list", list != null ? list : new ArrayList<PauseHistory>());
				}else {
					PauseLiftHistoryCollection pauseLiftHistory = eSBManager.getPauseLiftHistory(subsInfoNcas.getSub_no(), memb.getProductNm());
					if(pauseLiftHistory == null) {
						logger.debug("{}", "(pauseLiftHistory == null)");

						return "errorPage";
					}else {
						List<PauseLiftHistory> list = pauseLiftHistory.getList();
						if (list != null && !list.isEmpty()) {
							List<PauseLiftHistory> tmp = new ArrayList<PauseLiftHistory>();
							for (int i = 0; i < list.size(); i++) {
								PauseLiftHistory pauseLiftHistorys = list.get(i);
								PauseLiftHistory filterdPauseLiftHistory = new PauseLiftHistory();
									
										String entrSttsValdStrtDt ="";
										if(StringUtils.isNotEmpty(pauseLiftHistorys.getEntrSttsValdStrtDt()) ) {
											Date date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.S").parse(pauseLiftHistorys.getEntrSttsValdStrtDt());
											entrSttsValdStrtDt = new SimpleDateFormat("yyyy.MM.dd").format(date);
										}
										
										String entrSttsValdEndDt = "";
										if(StringUtils.isNotEmpty(pauseLiftHistorys.getEntrSttsValdEndDt()) ) {
											Date date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.S").parse(pauseLiftHistorys.getEntrSttsValdEndDt());
											entrSttsValdEndDt = new SimpleDateFormat("yyyy.MM.dd").format(date);
										}
										String entrSttsChngRsnDtlNm = StringUtils.defaultIfEmpty(pauseLiftHistorys.getEntrSttsChngRsnDtlNm(), "");
										String entrSttsNm = StringUtils.defaultIfEmpty(pauseLiftHistorys.getEntrSttsNm(), "");
										String prodNm = StringUtils.defaultIfEmpty(pauseLiftHistorys.getProdNm(), "");
										String prodNo = StringUtils.defaultIfEmpty(pauseLiftHistorys.getProdNo(), "");
										
										Date date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.S").parse(pauseLiftHistorys.getEntrSttsValdStrtDt());
										Date date1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.S").parse(pauseLiftHistorys.getEntrSttsValdEndDt());
										long diffDate = date.getTime() - date1.getTime();
										long diffDate1 = diffDate / (24*60*60*1000);
										diffDate1 = Math.abs(diffDate1);
										String susDate = String.valueOf(diffDate1);
										if(susDate.equals("0")) {
											susDate = "1";
										}
										
										filterdPauseLiftHistory.setEntrSttsValdStrtDt(entrSttsValdStrtDt);
										filterdPauseLiftHistory.setEntrSttsValdEndDt(entrSttsValdEndDt);
										filterdPauseLiftHistory.setEntrSttsChngRsnDtlNm(entrSttsChngRsnDtlNm);
										filterdPauseLiftHistory.setEntrSttsNm(entrSttsNm);
										filterdPauseLiftHistory.setProdNm(subsInfo.getSvcNm());
										filterdPauseLiftHistory.setProdNo(susDate);
										
										tmp.add(filterdPauseLiftHistory);
									
							}
							list = tmp;

						}
						pauseLiftHistory.setList(list);
					}
					List<PauseLiftHistory> list = pauseLiftHistory.getList();
					model.addAttribute("isPause", "??????");// ??????????????????
					model.addAttribute("list", list != null ? list : new ArrayList<PauseLiftHistory>());

				}
				/*
				PauseHistoryCollectionVO pauseHistoryCollection = eSBManager.getPauseHistory(subsInfoNcas.getSub_no(),
						memb.getProductNm());
				PauseHistoryCollection pauseHistory = eSBManager.getPauseHistorys(subsInfoNcas.getSub_no(), memb.getProductNm());
				//PauseHistoryCollectionVO pauseHistoryCollection = eSBManager.getPauseHistory(memb.getMembNo(),
				//		memb.getProductNm());
				if (pauseHistory == null) {
					logger.debug("{}", "(pauseHistoryCollection == null)");

					return "errorPage";
				} else {
					List<PauseHistory> list = pauseHistory.getList();
					if (list != null && !list.isEmpty()) {
						List<PauseHistory> tmp = new ArrayList<PauseHistory>();
						for (int i = 0; i < list.size(); i++) {
							PauseHistory pauseHistorys = list.get(i);
							PauseHistory filterdPauseHistory = new PauseHistory();
								if("".equals(pauseHistorys.getEntrSttsNm()) && "".equals(pauseHistorys.getProdNo())) {
									String entrSttsValdStrDt ="";
									if(StringUtils.isNotEmpty(pauseHistorys.getEntrSttsValdStrDt()) 
											&& VALID_DATE_YYYYMMDD_REGEX.matcher(pauseHistorys.getEntrSttsValdStrDt()).find()) {
										Date date = new SimpleDateFormat("yyyyMMdd").parse(pauseHistorys.getEntrSttsValdStrDt());
										entrSttsValdStrDt = new SimpleDateFormat("yyyy.MM.dd").format(date);
									}
									
									String entrSttsValdEndDt = "";
									if(StringUtils.isNotEmpty(pauseHistorys.getEntrSttsValdEndDt()) 
											&& VALID_DATE_YYYYMMDD_REGEX.matcher(pauseHistorys.getEntrSttsValdEndDt()).find()) {
										Date date = new SimpleDateFormat("yyyyMMdd").parse(pauseHistorys.getEntrSttsValdEndDt());
										entrSttsValdEndDt = new SimpleDateFormat("yyyy.MM.dd").format(date);
									}
									String entrSttsChngRsnDtlNm = StringUtils.defaultIfEmpty(pauseHistorys.getEntrSttsChngRsnDtlNm(), "");
									String entrSttsNm = StringUtils.defaultIfEmpty(pauseHistorys.getEntrSttsNm(), "");
									String prodNm = StringUtils.defaultIfEmpty(pauseHistorys.getProdNm(), "");
									String prodNo = StringUtils.defaultIfEmpty(pauseHistorys.getProdNo(), "");
									
									filterdPauseHistory.setEntrSttsValdStrDt(entrSttsValdStrDt);
									filterdPauseHistory.setEntrSttsValdEndDt(entrSttsValdEndDt);
									filterdPauseHistory.setEntrSttsChngRsnDtlNm(entrSttsChngRsnDtlNm);
									filterdPauseHistory.setEntrSttsNm(entrSttsNm);
									filterdPauseHistory.setProdNm(prodNm);
									filterdPauseHistory.setProdNo(prodNo);
									
									tmp.add(filterdPauseHistory);
								}
							PauseHistory filterdPauseHistory = new PauseHistory();
							
							String susDate = "";
							if (StringUtils.isNotEmpty(pauseHistorys.getSusDate())
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

						pauseHistory.setList(list);
					}
				}

				List<PauseHistory> list = pauseHistory.getList();
				model.addAttribute("list", list != null ? list : new ArrayList<PauseHistoryVO>());// ??????????????????
*/
			} else {
				List<PauseHistory> list = new ArrayList<PauseHistory>();
				{
					String entrSttsValdStrDt = "2019-01-16 10:14:55.0";// ??????????????????
					String entrSttsValdEndDt = "2019-01-17 00:00:00.0";// ??????????????????
																							// ??????
																							// ????????????????????????
					
					Date date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.S").parse(entrSttsValdStrDt);
					Date date1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.S").parse(entrSttsValdEndDt);
					
					long diffDate = date.getTime() - date1.getTime();
					
					long diffDate1 = diffDate / (24*60*60*1000);
					diffDate1 = Math.abs(diffDate1);
					String susDate = String.valueOf(diffDate1);
					String entrSttsChngRsnDtlNm = "????????????";// ???????????????
					String prodNm = "LTE) ?????????/??????????????? ????????? 10GB";// ????????????
					String entrSttsNm = "?????????";// ???????????????????????????
					String prodNo = susDate;// ??????????????????
					//String term = susDate + " ~ " + rspDate;// ??????

					PauseHistory pauseHistory = new PauseHistory();
					pauseHistory.setEntrSttsValdStrtDt("");
					pauseHistory.setEntrSttsValdEndDt(entrSttsValdEndDt);
					pauseHistory.setEntrSttsChngRsnDtlNm(entrSttsChngRsnDtlNm);
					pauseHistory.setProdNm(prodNm);
					pauseHistory.setEntrSttsNm(entrSttsNm);
					pauseHistory.setProdNo(prodNo);

					list.add(pauseHistory);
				}

				model.addAttribute("list", list);// ??????????????????
			}

			return "unlimitPauseHistory";
		} catch (Exception e) {
			logger.debug("{}", e.getMessage());

			e.printStackTrace();
		}

		return "errorPage";
	}

	/**
	 * ??????????????????2 > ????????????
	 */
	@Override
	public String getUnlimitTerms(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
			Model model) {
		try {
			if (StringUtils.equalsIgnoreCase("Y", esbEnable)) {
				MembVO memb = validationByAll(httpServletRequest);
				if (memb == null) {
					logger.debug("{}", "(memb == null)");

					return "errorPage";
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

			return "unlimitTerms";
		} catch (Exception e) {
			logger.debug("{}", e.getMessage());

			e.printStackTrace();
		}

		return "errorPage";
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

	// ??????????????????
	@SuppressWarnings("unused")
	@Override
	public String getChangeMethod(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
			String body) {
		HashMap<String, Object> result = new HashMap<String, Object>();
		try {
			if (StringUtils.equalsIgnoreCase("Y", esbEnable)) {
				// ????????? ??????????????? ?????? ???????????? ??????????????? ???????????? ????????? ??????
				HashMap<String, Object> parameter = new ObjectMapper().readValue(body,
						new TypeReference<HashMap<String, Object>>() {
						});

				MembVO memb = validationByAll(httpServletRequest);
				if (memb == null) {
					logger.debug("{}", "(memb == null)");

					return createResultJson("9999", "????????? ?????? ??? ????????? ?????????????????????.");
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
				//RetrieveCustSvcEntrInfoBDServiceStub.DsOutputOutVO dsOutputOutVO = eSBManager
				//		.getCustomerInfo(memb.getMembNo(), memb.getProductNm());
				if (dsOutputOutVO == null) {
					logger.debug("{}", "(dsOutputOutVO == null)");

					return createResultJson("9999", "????????? ?????? ??? ????????? ?????????????????????.");
				}

				// aceno ?????????????????? VARCHAR2 12 Y ??????
				// acntOwnrNo ??????????????? VARCHAR2 13 N ?????? ????????????,???????????????,???????????????,????????????????????????
				// actInd actInd VARCHAR2 1 Y ?????? null(???????????? Y?????? ????????? ?????????)
				// bankAcntNo ?????????????????? VARCHAR2 16 Y ?????? ??????????????? ?????? ??????
				// bankCd ???????????? VARCHAR2 3 Y ?????? ??????????????? ?????? ??????
				// bankNm ????????? VARCHAR2 60 Y ??????
				// billAcntNo ?????????????????? VARCHAR2 12 N ?????? ??????????????? ??????????????? ??????????????????
				// bltxtKdCd ???????????? VARCHAR2 1 N ?????? ??????????????? GG(LG?????????_?????????)??? ?????? ??????
				// ??????????????????
				// Shot-mail(S)/SMS(M)/?????????(W) ??? ????????? ?????? ??????
				// cardNo ???????????? VARCHAR2 16 Y ?????? ??????????????? ?????? ??????
				// cardValdEndYymm ???????????? VARCHAR2 6 Y ?????? "YYYYMM??????????????? ?????? ??????"
				// cdcmpCd ??????????????? VARCHAR2 2 Y ?????? ??????????????? ?????? ??????
				// cdcmpNm ????????? VARCHAR2 360 Y ??????
				// custDvCd ????????????????????? VARCHAR2 240 N ?????? ??????:I,??????:G
				// custKdCd ????????????????????? VARCHAR2 240 N ?????? VW_CS_HB_CUST_KD_01 ??????
				// entrNo ???????????? VARCHAR2 12 N ?????? ????????????,?????????????????? ?????? ?????????????????????
				// ????????????????????????????????????????????????
				// newInd newInd VARCHAR2 1 Y ?????? null(?????? ???????????? ????????? ??????)
				// persIdind ????????? ?????? VARCHAR2 1 Y ?????? ?????????:Y
				// ppayAcntYn ???????????? ?????? VARCHAR2 1 Y ?????? ??????:Y
				// pymAcntNo ?????????????????? VARCHAR2 12 Y ?????? ????????? NULL
				// pymCustNm ????????? ?????? ??? VARCHAR2 360 N ?????? ????????? ????????? ??????
				// pymManCustNo ????????? ?????? ?????? VARCHAR2 15 Y ?????? ????????????
				// pymMthdCd ???????????? VARCHAR2 2 N ??????
				// "????????????:CM,????????????:CC,??????:GR,??????????????????(CM->CM,CC->CC):????????????
				// ??????????????????(??????):????????????"
				// pymMthdNm ??????????????? VARCHAR2 30 Y ?????? ????????????,????????????,??????

				String aceno = null;
				String acntOwnrNo = (String) parameter.get("acntOwnrNo");// ??????
				String actInd = null;
				String bankAcntNo = null;
				String bankCd = null;
				String bankNm = null;
				String billAcntNo = dsOutputOutVO.getBillAcntNo();// ??????
				String bltxtKdCd = dsOutputOutVO.getBltxtKdCd();// ??????
				String cardNo = (String) parameter.get("cardNo");
				String cardValdEndYymm = (String) parameter.get("cardValdEndYymm");
				String cdcmpCd = (String) parameter.get("cdcmpCd");
				String cdcmpNm = null;
				String custDvCd = (String) parameter.get("custDvCd");// ??????
				String custKdCd = "I".equals(custDvCd) ? "II" : "GE";// ??????
				String entrNo = memb.getMembNo();// ??????
				String newInd = null;
				String persIdind = (String) parameter.get("persIdind");
				String ppayAcntYn = null;
				String pymAcntNo = null;
				String pymCustNm = (String) parameter.get("pymCustNm");// ??????
				String pymManCustNo = null;
				String pymMthdCd = "CC";// ??????
				String pymMthdNm = null;

				PayMethodResultVO payMethodResultVO = eSBManager.updatePayMethod(aceno, acntOwnrNo, actInd, bankAcntNo,
						bankCd, bankNm, billAcntNo, bltxtKdCd, cardNo, cardValdEndYymm, cdcmpCd, cdcmpNm, custDvCd,
						custKdCd, entrNo, newInd, persIdind, ppayAcntYn, pymAcntNo, pymCustNm, pymManCustNo, pymMthdCd,
						pymMthdNm);
				if (payMethodResultVO == null) {
					logger.debug("{}", "(payMethodResultVO == null)");

					return createResultJson("9999", "????????? ?????? ??? ????????? ?????????????????????.");
				}

				if (!"Y".equals(payMethodResultVO.getMsgCode())) {
					logger.debug("failed to updatePayMethod()");

					return createResultJson("9999", payMethodResultVO.getMsgText());
				}

				result.put("resultCode", "0000");
				result.put("resultMsg", "??????");

			} else {
				HashMap<String, Object> parameter = new ObjectMapper().readValue(body,
						new TypeReference<HashMap<String, Object>>() {
						});

				String acntOwnrNo = (String) parameter.get("acntOwnrNo");// ??????
				String cardNo = (String) parameter.get("cardNo");
				String cardValdEndYymm = (String) parameter.get("cardValdEndYymm");
				String cdcmpCd = (String) parameter.get("cdcmpCd");
				String custDvCd = (String) parameter.get("custDvCd");// ??????
				String custKdCd = "I".equals(custDvCd) ? "II" : "GE";// ??????
				String persIdind = (String) parameter.get("persIdind");
				String pymCustNm = (String) parameter.get("pymCustNm");// ??????
				String pymMthdCd = "CC";// ??????

				result.put("resultCode", "0000");
				result.put("resultMsg", "??????");
			}

			return new ObjectMapper().writeValueAsString(result);
		} catch (Exception e) {
			logger.debug("{}", e.getMessage());

			e.printStackTrace();
		}

		return createResultJson("9999", "????????? ?????? ??? ????????? ?????????????????????.");
	}

	// ??????????????????
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

					return createResultJson("9999", "????????? ?????? ??? ????????? ?????????????????????.");
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
				//RetrieveCustSvcEntrInfoBDServiceStub.DsOutputOutVO dsOutputOutVO = eSBManager
				//		.getCustomerInfo(memb.getMembNo(), memb.getProductNm());
				if (dsOutputOutVO == null) {
					logger.debug("{}", "(dsOutputOutVO == null)");

					return createResultJson("9999", "????????? ?????? ??? ????????? ?????????????????????.");
				}

				RetrieveCustInfoSvcServiceStub.DsConfldsOutVO dsConfldsOut = eSBManager.getBillInfo(subsInfoNcas.getSub_no());
				//RetrieveCustInfoSvcServiceStub.DsConfldsOutVO dsConfldsOut = eSBManager.getBillInfo(memb.getMembNo());
				if (dsConfldsOut == null) {
					logger.debug("{}", "(dsConfldsOut == null)");

					return createResultJson("9999", "????????? ?????? ??? ????????? ?????????????????????.");
				}

				// dsConfldsIn
				// userId ?????????ID VARCHAR2 100 Y ??????
				// userNm ???????????? VARCHAR2 500 Y ??????
				// mrktCd ???????????? VARCHAR2 10 Y ??????
				// userOrgCd ????????????????????? VARCHAR2 10 Y ??????
				// userDlrCd ???????????????????????? VARCHAR2 10 Y ??????
				// userWorkDlrCd ????????????????????? VARCHAR2 10 Y ??????
				// userDlrNm ????????????????????? VARCHAR2 100 Y ??????
				// lockMode lockMode VARCHAR2 10 N ?????? B - ??????
				// cnId cnId VARCHAR2 10 Y ??????
				// directive directive VARCHAR2 10 Y ??????
				// runDate runDate VARCHAR2 8 Y ??????
				// runDateDtm runDateDtm VARCHAR2 16 Y ??????
				// transactionMode transactionMode VARCHAR2 10 Y ??????
				// userWorkDlrNm ?????????????????? VARCHAR2 100 Y ??????
				// entrNo ???????????? VARCHAR2 15 Y ??????
				// entrSysUpdateDate entrSysUpdateDate VARCHAR2 16 Y ??????
				// entrDlUpdateStamp entrDlUpdateStamp VARCHAR2 16 Y ??????
				// aceno ?????????????????? VARCHAR2 15 Y ??????
				// cntcSysUpdateDate cntcSysUpdateDate VARCHAR2 16 Y ??????
				// cntcDlUpdateStamp cntcDlUpdateStamp VARCHAR2 16 Y ??????
				// billAcntNo ?????????????????? VARCHAR2 15 N ??????
				// billSysUpdateDate billSysUpdateDate VARCHAR2 16 N ??????
				// billDlUpdateStamp billDlUpdateStamp VARCHAR2 16 N ??????

				String userId = null;
				String userNm = null;
				String mrktCd = null;
				String userOrgCd = null;
				String userDlrCd = null;
				String userWorkDlrCd = null;
				String userDlrNm = null;
				String lockMode = "B";// ??????
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
				String billAcntNo = dsOutputOutVO.getBillAcntNo();// ??????
				String billSysUpdateDate = dsConfldsOut.getBillSysUpdateDate();// ??????
				String billDlUpdateStamp = dsConfldsOut.getBillDlUpdateStamp();// ??????

				// dsReqIn
				// billAcntNo ?????????????????? VARCHAR2 9 N ??????
				// bltxtKdCdNm ???????????????????????? VARCHAR2 80 N ??????
				// dscntSvcCd ????????????????????? VARCHAR2 10 Y ??????
				// dscntSvcNm ?????????????????? VARCHAR2 240 Y ??????
				// bltxtKdValdStrtDt ????????????????????????????????? VARCHAR2 14 Y ??????
				// bltxtKdValdEndDt ????????????????????????????????? VARCHAR2 14 Y ??????
				// scurMailRcpYn ???????????????????????? VARCHAR2 1 Y ??????
				// bltxtRcpProdNo ??????????????????????????? VARCHAR2 20 Y ??????
				// billEmailAddr ????????????????????? VARCHAR2 50 Y ??????
				// copyReal copyReal VARCHAR2 1 Y ??????
				// prodNo ???????????? VARCHAR2 20 Y ??????
				// emailCopyCustYn emailCopyCustYn VARCHAR2 1 Y ??????
				// chgBlAddrYn chgBlAddrYn VARCHAR2 1 Y ??????
				// billAddrSeqno ???????????????????????? VARCHAR2 15 Y ??????
				// custAddrZip ???????????????????????? VARCHAR2 7 Y ??????
				// custVilgAbvAddr ????????????????????? VARCHAR2 500 Y ??????
				// custVilgBlwAddr ????????????????????? VARCHAR2 500 Y ??????
				// imsi01 imsi01 VARCHAR2 500 Y ??????
				// imsi02 imsi02 VARCHAR2 500 Y ??????
				// imsi03 imsi03 VARCHAR2 500 Y ??????
				// bltxtChrgrId am?????? ????????? ID VARCHAR2 100 Y ?????? ????????? ????????? am
				// ??????(G)????????? set

				// Y - e-mail(??????) ?????????
				// P - ?????????(???????????????)
				String billAcntNo_ = dsOutputOutVO.getBillAcntNo();// ??????
				String bltxtKdCdNm = (String) parameter.get("bltxtKdCdNm");// ??????
				String dscntSvcCd = (String) parameter.get("dscntSvcCd");
				String dscntSvcNm = (String) parameter.get("dscntSvcNm");
				String bltxtKdValdStrtDt = new SimpleDateFormat("yyyyMMdd").format(new Date());
				String bltxtKdValdEndDt = null;
				String scurMailRcpYn = (String) parameter.get("scurMailRcpYn");
				String pn = (String) parameter.get("pn");
				String first = "";
				String last = "";
				String fullPn;
				if (pn.length() > 3) {
					first = pn.substring(0,3);
					last = pn.substring(3);
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
						? billEmailAddr : null); // ????????? ???????????? with prodNo
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

					return createResultJson("9999", "????????? ?????? ??? ????????? ?????????????????????.");
				}

				if (!"Y".equals(billTypeResultVO.getMsgCode())) {
					logger.debug("failed to updateBillType");

					return createResultJson("9999", billTypeResultVO.getMsgText());
				}

				String custNm = StringUtils.defaultIfEmpty(dsOutputOutVO.getCustNm(), "");
				String bltxtKdNm = "";
				if ("Y".equalsIgnoreCase(bltxtKdNm)) {
					bltxtKdNm = "e-mail(??????) ?????????";
				} else if ("P".equalsIgnoreCase(bltxtKdNm)) {
					bltxtKdNm = "?????????(???????????????)";
				}

				result.put("resultCode", "0000");
				result.put("resultMsg", "??????");
				result.put("custNm", custNm);
				result.put("bltxtKdNm", bltxtKdNm);

			} else {
				HashMap<String, Object> parameter = new ObjectMapper().readValue(body,
						new TypeReference<HashMap<String, Object>>() {
						});

				String bltxtKdCdNm = (String) parameter.get("bltxtKdCdNm");// ??????(SMS:M,
																			// ?????????:W,
																			// ??????:S)
				String scurMailRcpYn = (String) parameter.get("scurMailRcpYn");
				String billEmailAddr = (String) parameter.get("billEmailAddr");
				String emailCopyCustYn = (String) parameter.get("emailCopyCustYn");

				String custNm = "?????????";
				String bltxtKdNm = "?????????(???????????????)";

				result.put("resultCode", "0000");
				result.put("resultMsg", "??????");
				result.put("custNm", custNm);
				result.put("bltxtKdNm", bltxtKdNm);
			}

			return new ObjectMapper().writeValueAsString(result);
		} catch (Exception e) {
			logger.debug("{}", e.getMessage());

			e.printStackTrace();
		}

		return createResultJson("9999", "????????? ?????? ??? ????????? ?????????????????????.");
	}

	// ????????????
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

					return createResultJson("9999", "????????? ?????? ??? ????????? ?????????????????????.");
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
				//RetrieveCustSvcEntrInfoBDServiceStub.DsOutputOutVO dsOutputOutVO = eSBManager
				//		.getCustomerInfo(memb.getMembNo(), memb.getProductNm());
				if (dsOutputOutVO == null) {
					logger.debug("{}", "(dsOutputOutVO == null)");

					return createResultJson("9999", "????????? ?????? ??? ????????? ?????????????????????.");
				}

				// ?????? 2?????????
				PauseHistoryCollectionVO pauseHistoryCollection = eSBManager.getPauseHistory(dsOutputOutVO.getEntrNo(),
						dsOutputOutVO.getSvcCd());
				if (pauseHistoryCollection == null) {
					logger.debug("{}", "(pauseHistoryCollection == null)");

					return createResultJson("9999", "????????? ?????? ??? ????????? ?????????????????????.");
				}

				List<PauseHistoryVO> list = pauseHistoryCollection.getList();
				if (list != null && !list.isEmpty()) {
					// ?????? 2?????????
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

						return createResultJson("1000", "??????????????? ???2????????? ???????????????.");
					}
				}

				// entrNo ???????????? NUMBER 12 N ??????
				// entrSttsChngSeqno ???????????????????????? VARCHAR2 10 ?????? ??????????????? ??????
				// custNo ???????????? VARCHAR2 15 N ?????? ??????????????????
				// prodNo ???????????? VARCHAR2 20 N ??????
				// billAcntNo ?????????????????? VARCHAR2 10 N ??????
				// entrSttsValdStrtDt ???????????? VARCHAR2 8 N ??????
				// entrSttsValdEndDt ???????????????????????? VARCHAR2 8 N ??????
				// rspEntrSttsValdEndDt ???????????????????????? VARCHAR2 8 ?????? HS???????????? ??????
				// rspEntrSttsChngRsnDtlCd ?????????????????????????????? VARCHAR2 10 ?????? ??????????????? ??????
				// ?????????????????????
				// ????????? ???????????? ??????PR(???????????????)
				// rgstDt ???????????? VARCHAR2 8 N ?????? ????????????
				// entrSttsChngRsnCd ?????????????????????????????? VARCHAR2 10 N ??????
				// entrSttsChngRsnDtlCd ???????????????????????????????????? VARCHAR2 10 N ??????
				// prssYn ???????????? VARCHAR2 1 N ?????? Y
				// prssDlrCd ????????????????????? VARCHAR2 8 N ?????? 100000
				// aplyLvlCd ???????????? VARCHAR2 1 N ?????? C'-??????????????? ????????? ?????? ??????
				// msgParmCnt ?????????????????? VARCHAR2 1 N ?????? ??????:3
				// memoCrteDlrCd ??????????????? VARCHAR2 8 N ??????
				// rsnCd ???????????? VARCHAR2 3 N ?????? ??????:E19
				// rsnDtlCd ?????????????????? VARCHAR2 4 N ?????? ??????:4039
				// sendPhbYn ???????????? ?????? VARCHAR2 1 N ?????? Y/N
				// icallPhbYn ?????????????????? VARCHAR2 1 N ?????? Y/N
				// msgParm1 ????????????1 VARCHAR2 30 N ?????? "????????????/????????????"
				// msgParm2 ????????????2 VARCHAR2 30 N ?????? ?????????????????????/???????????????????????????
				// msgParm3 ????????????3 VARCHAR2 30 N ?????? ???????????????????????????/???????????????????????????
				// userMemo ??????????????? VARCHAR2 800 ??????

				// ????????? ?????? - EX
				// ????????????(??????/PC/AP) - 12
				// ????????????/???????????? - 13
				// ???????????? - 14
				// ????????? ????????? - 15
				// ???????????? - ZA
				// ??? ?????? - UP
				
				String chekEntrSttsValdStrtDt = new SimpleDateFormat("yyyyMMdd").format(new Date());
				
				
				String entrNo = dsOutputOutVO.getEntrNo();// ??????
				String entrSttsChngSeqno = null;
				String custNo = dsOutputOutVO.getCustNo();// ??????
				String prodNo = dsOutputOutVO.getProdNo();// ??????
				String billAcntNo = dsOutputOutVO.getBillAcntNo();// ??????
				String entrSttsValdStrtDt = (String) parameter.get("entrSttsValdStrtDt");// ??????
				String entrSttsValdEndDt = (String) parameter.get("entrSttsValdEndDt");// ??????
				String rspEntrSttsValdEndDt = null;
				String rspEntrSttsChngRsnDtlCd = "PR";
				String rgstDt = new SimpleDateFormat("yyyyMMdd").format(new Date());// ??????
				String entrSttsChngRsnCd = "SUS";// ??????(SUS/RSP)
				String entrSttsChngRsnDtlCd = (String) parameter.get("selRsnCd");// ??????(????????????)
				String prssYn = "Y";// ??????
				String prssDlrCd = "100000";// ??????
				String aplyLvlCd = "C";// ??????
				String msgParmCnt = "3";// ??????
				String memoCrteDlrCd = dsOutputOutVO.getEntrDlrCd();// ??????
				String rsnCd = "E19";// ??????
				String rsnDtlCd = "4039";// ??????
				String sendPhbYn = "N";// ??????
				String icallPhbYn = "N";// ??????
				String msgParm1 = "????????????";// ??????
				String msgParm2 = (String) parameter.get("entrSttsValdStrtDt");// ??????
				String msgParm3 = (String) parameter.get("selRsnCd");// ??????
				String userMemo = (String) parameter.get("selRsnNm");// ?

				if(chekEntrSttsValdStrtDt.equals(entrSttsValdStrtDt)) {
					RetrieveCustInfoSvcServiceStub.DsCustInfoOutVO dsCustInfoOut = eSBManager
							.getStateInfo(subsInfoNcas.getSub_no());
					//RetrieveCustInfoSvcServiceStub.DsCustInfoOutVO dsCustInfoOut = eSBManager
					//		.getStateInfo(memb.getMembNo());
					if (dsCustInfoOut == null) {
						logger.debug("{}", "(dsCustInfoOut == null)");

						return createResultJson("9999", "????????? ?????? ??? ????????? ?????????????????????.");
					}
					String entrSttsChngRsnDtlNm = (String) parameter.get("selRsnNm");// ??????
					String userDlrCd = dsOutputOutVO.getEntrDlrCd();// ??????
					//SB695
					UnpauseResultVO unpauseResultVO = eSBManager.updatePauseSus(entrNo,
							entrSttsChngRsnCd,
							entrSttsChngRsnDtlCd,
							entrSttsChngRsnDtlNm,
							sendPhbYn,
							icallPhbYn,
							entrSttsValdStrtDt,
							userMemo,
							userDlrCd);
					if (unpauseResultVO == null) {
						logger.debug("{}", "(unpauseResultVO == null)");

						return createResultJson("9999", "????????? ?????? ??? ????????? ?????????????????????.");
					}

					if (!"SUCCESS".equals(unpauseResultVO.getResult())) {
						logger.debug("failed to updatePause");

						return createResultJson("9999", unpauseResultVO.getResult());
					}
					//SB 639
					PauseResultVO pauseResultVORsp = eSBManager.updatePauseRsp(entrNo, entrSttsChngSeqno, custNo, prodNo,
							billAcntNo, entrSttsValdStrtDt, entrSttsValdEndDt, rspEntrSttsValdEndDt,
							rspEntrSttsChngRsnDtlCd, rgstDt, entrSttsChngRsnCd, entrSttsChngRsnDtlCd, prssYn, prssDlrCd,
							aplyLvlCd, msgParmCnt, memoCrteDlrCd, rsnCd, rsnDtlCd, sendPhbYn, icallPhbYn, msgParm1,
							msgParm2, msgParm3, userMemo);
					if (pauseResultVORsp == null) {
						logger.debug("{}", "(pauseResultVO == null)");

						return createResultJson("9999", "????????? ?????? ??? ????????? ?????????????????????.");
					}

					if (!"SUCCESS".equals(pauseResultVORsp.getResult())) {
						logger.debug("failed to updatePause");

						return createResultJson("9999", pauseResultVORsp.getResult());
					}
					
					
					return createResultJson("0001", "????????? ?????? ????????? ?????? ???????????? \n * ??????????????? ??? 2????????? ???????????????");
				}else {
					//SB639
					PauseResultVO pauseResultVO = eSBManager.updatePause(entrNo, entrSttsChngSeqno, custNo, prodNo,
							billAcntNo, entrSttsValdStrtDt, entrSttsValdEndDt, rspEntrSttsValdEndDt,
							rspEntrSttsChngRsnDtlCd, rgstDt, entrSttsChngRsnCd, entrSttsChngRsnDtlCd, prssYn, prssDlrCd,
							aplyLvlCd, msgParmCnt, memoCrteDlrCd, rsnCd, rsnDtlCd, sendPhbYn, icallPhbYn, msgParm1,
							msgParm2, msgParm3, userMemo);
					if (pauseResultVO == null) {
						logger.debug("{}", "(pauseResultVO == null)");

						return createResultJson("9999", "????????? ?????? ??? ????????? ?????????????????????.");
					}

					if (!"SUCCESS".equals(pauseResultVO.getResult())) {
						logger.debug("failed to updatePause");

						return createResultJson("9999", pauseResultVO.getResult());
					}
					//SB639
					PauseResultVO pauseResultVORsp = eSBManager.updatePauseRsp(entrNo, entrSttsChngSeqno, custNo, prodNo,
							billAcntNo, entrSttsValdStrtDt, entrSttsValdEndDt, rspEntrSttsValdEndDt,
							rspEntrSttsChngRsnDtlCd, rgstDt, entrSttsChngRsnCd, entrSttsChngRsnDtlCd, prssYn, prssDlrCd,
							aplyLvlCd, msgParmCnt, memoCrteDlrCd, rsnCd, rsnDtlCd, sendPhbYn, icallPhbYn, msgParm1,
							msgParm2, msgParm3, userMemo);
					if (pauseResultVORsp == null) {
						logger.debug("{}", "(pauseResultVO == null)");

						return createResultJson("9999", "????????? ?????? ??? ????????? ?????????????????????.");
					}

					if (!"SUCCESS".equals(pauseResultVORsp.getResult())) {
						logger.debug("failed to updatePause");

						return createResultJson("9999", pauseResultVORsp.getResult());
					}
					
					return createResultJson("0002", "????????? ?????? ????????? ?????????????????? \n * ??????????????? ??? 2????????? ???????????????");
				}
				
				/*PauseResultVO pauseResultVO = eSBManager.updatePause(entrNo, entrSttsChngSeqno, custNo, prodNo,
						billAcntNo, entrSttsValdStrtDt, entrSttsValdEndDt, rspEntrSttsValdEndDt,
						rspEntrSttsChngRsnDtlCd, rgstDt, entrSttsChngRsnCd, entrSttsChngRsnDtlCd, prssYn, prssDlrCd,
						aplyLvlCd, msgParmCnt, memoCrteDlrCd, rsnCd, rsnDtlCd, sendPhbYn, icallPhbYn, msgParm1,
						msgParm2, msgParm3, userMemo);
				if (pauseResultVO == null) {
					logger.debug("{}", "(pauseResultVO == null)");

					return createResultJson("9999", "????????? ?????? ??? ????????? ?????????????????????.");
				}

				if (!"SUCCESS".equals(pauseResultVO.getResult())) {
					logger.debug("failed to updatePause");

					return createResultJson("9999", pauseResultVO.getResult());
				}*/

				/*result.put("resultCode", "0000");
				result.put("resultMsg", "??????");*/

			} else {
				HashMap<String, Object> parameter = new ObjectMapper().readValue(body,
						new TypeReference<HashMap<String, Object>>() {
						});

				String entrSttsValdStrtDt = (String) parameter.get("entrSttsValdStrtDt");// ??????
				String entrSttsValdEndDt = (String) parameter.get("entrSttsValdEndDt");// ??????
				String msgParm3 = (String) parameter.get("selRsnCd");// ??????

				result.put("resultCode", "0000");
				result.put("resultMsg", "??????");
			}

			return new ObjectMapper().writeValueAsString(result);
		} catch (Exception e) {
			logger.debug("{}", e.getMessage());

			e.printStackTrace();
		}

		return createResultJson("9999", "????????? ?????? ??? ????????? ?????????????????????.");
	}

	// ??????????????????
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
				// ????????????
				//
				// TP - IPIN
				// CI -
				// T/VrVoqut4xtte9Flzlxix9Vfdwv4KePmfDgmV8p1l/o/hu52sjqNJou/q1IWX3ojAgDTg8xU3xrBlshDSKDLQ==
				// DI -
				////////////////////////////////////////////////////////////////////// MC0GCCqGSIb3DQIJAyEAwbAxA/yFJ+TI0E5HI4Qp9C96gKPNtUUk6bfvz960Quw=
				// PN -
				// NM - ?????????
				// BD - 19780206
				//
				// TP - KMC
				// CI -
				// T/VrVoqut4xtte9Flzlxix9Vfdwv4KePmfDgmV8p1l/o/hu52sjqNJou/q1IWX3ojAgDTg8xU3xrBlshDSKDLQ==
				// DI -
				////////////////////////////////////////////////////////////////////// MC0GCCqGSIb3DQIJAyEAeISx8l2Ix2yR4y1QJg+CvTxWYlC6699c6W69kMmVe7A=
				// PN - 01055997694
				// NM - ?????????
				// BD - 19780206
				////////////////////////////////////////////////////////////////////////

				MembVO memb = validationByCtn(httpServletRequest);
				if (memb == null) {
					logger.debug("{}", "(memb == null)");

					return createResultJson("9999", "????????? ?????? ??? ????????? ?????????????????????.");
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
				//RetrieveCustSvcEntrInfoBDServiceStub.DsOutputOutVO dsOutputOutVO = eSBManager
				//		.getCustomerInfo(memb.getMembNo(), memb.getProductNm());
				if (dsOutputOutVO == null) {
					logger.debug("{}", "(dsOutputOutVO == null)");

					return createResultJson("9999", "????????? ?????? ??? ????????? ?????????????????????.");
				}

				PauseHistoryCollectionVO pauseHistoryCollection = eSBManager.getPauseHistory(dsOutputOutVO.getEntrNo(),
						dsOutputOutVO.getSvcCd());
				if (pauseHistoryCollection == null) {
					logger.debug("{}", "(pauseHistoryCollection == null)");

					return createResultJson("9999", "????????? ?????? ??? ????????? ?????????????????????.");
				}

				List<PauseHistoryVO> list = pauseHistoryCollection.getList();
				if (list == null || list.isEmpty()) {
					logger.debug("{}", "(list == null)");

					return createResultJson("9999", "????????? ?????? ??? ????????? ?????????????????????.");
				}
				
				RetrieveCustInfoSvcServiceStub.DsCustInfoOutVO dsCustInfoOut = eSBManager
						.getStateInfo(subsInfoNcas.getSub_no());
				//RetrieveCustInfoSvcServiceStub.DsCustInfoOutVO dsCustInfoOut = eSBManager
				//		.getStateInfo(memb.getMembNo());
				if (dsCustInfoOut == null) {
					logger.debug("{}", "(dsCustInfoOut == null)");

					return createResultJson("9999", "????????? ?????? ??? ????????? ?????????????????????.");
				}
				
				String entrNo = dsOutputOutVO.getEntrNo();// ??????
				String entrSttsChngRsnCd = "RSP";// ??????(SUS/RSP)
				String entrSttsChngRsnDtlCd = "CR";// ??????
				String entrSttsChngRsnDtlNm = "????????????";// ??????
				String sendPhbYn = "N";// ??????
				String icallPhbYn = "N";// ??????
				String entrSttsValdStrtDt = new SimpleDateFormat("yyyyMMdd").format(new Date());// ??????
				String userMemo = null;
				String userDlrCd = dsOutputOutVO.getEntrDlrCd();// ??????
				
				String custNo = dsOutputOutVO.getCustNo();
				String prodNo = dsOutputOutVO.getProdNo();
				String aplyLvIcd = "C";
				String msgParamCnt = "3";
				String memoCrteDlrCd = "100000";
				String rsnCd ="E20";
				String rsnDtlCd = "4040";
				String msgParam1 = "????????????";
				String entrSttsValdEndDt = new SimpleDateFormat("yyyyMMdd").format(new Date());// ??????
				String msgParam2 = new SimpleDateFormat("yyyy-MM-dd").format(new Date());// ??????
				
				UnpauseResultVO unpauseResultVO = eSBManager.updateUnpause(entrNo,
						entrSttsChngRsnCd,
						entrSttsChngRsnDtlCd,
						entrSttsChngRsnDtlNm,
						sendPhbYn,
						icallPhbYn,
						entrSttsValdStrtDt,
						userMemo,
						userDlrCd);
				if (unpauseResultVO == null) {
					logger.debug("{}", "(unpauseResultVO == null)");

					return createResultJson("9999", "????????? ?????? ??? ????????? ?????????????????????.");
				}

				if (!"SUCCESS".equals(unpauseResultVO.getResult())) {
					logger.debug("failed to updatePause");

					return createResultJson("9999", unpauseResultVO.getResult());
				}

	
				PauseLiftHistoryCollection pauseLiftHistory = eSBManager.getPauseLiftHistory(subsInfoNcas.getSub_no(), memb.getProductNm());
				if(pauseLiftHistory == null) {
					logger.debug("{}", "(pauseLiftHistory == null)");

					return "errorPage";
				}
				List<PauseLiftHistory> pauseList = pauseLiftHistory.getList();
				if (pauseList == null || pauseList.isEmpty()) {
					logger.debug("{}", "(list == null)");

					return createResultJson("9999", "????????? ?????? ??? ????????? ?????????????????????.");
				}
				
				String entrSttsChngSeqno = pauseList.get(0).getEntrSttsChngSeqno();
				
				UnpauseResultVO unpauseRsvResult = eSBManager.updateUnpauseRsv(entrNo,
						entrSttsValdEndDt, 
						entrSttsChngSeqno,
						custNo,
						prodNo,
						aplyLvIcd,
						msgParamCnt,
						memoCrteDlrCd,
						rsnCd,
						rsnDtlCd,
						msgParam1,
						msgParam2);
				
				if (unpauseRsvResult == null) {
					result.put("resultCode", "0000");
					result.put("resultMsg", "??????");	
				}
				
				result.put("resultCode", "0000");
				result.put("resultMsg", "??????");

			} else {
				HashMap<String, Object> parameter = new ObjectMapper().readValue(body,
						new TypeReference<HashMap<String, Object>>() {
						});

				result.put("resultCode", "0000");
				result.put("resultMsg", "??????");
			}
			
			return new ObjectMapper().writeValueAsString(result);
		} catch (Exception e) {
			logger.debug("{}", e.getMessage());

			e.printStackTrace();
		}

		return createResultJson("9999", "????????? ?????? ??? ????????? ?????????????????????.");
	}

	// ????????????????????????
	@SuppressWarnings("unused")
	@Override
	public String getUnpauseRsvService(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
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
				// ????????????
				//
				// TP - IPIN
				// CI -
				// T/VrVoqut4xtte9Flzlxix9Vfdwv4KePmfDgmV8p1l/o/hu52sjqNJou/q1IWX3ojAgDTg8xU3xrBlshDSKDLQ==
				// DI -
				////////////////////////////////////////////////////////////////////// MC0GCCqGSIb3DQIJAyEAwbAxA/yFJ+TI0E5HI4Qp9C96gKPNtUUk6bfvz960Quw=
				// PN -
				// NM - ?????????
				// BD - 19780206
				//
				// TP - KMC
				// CI -
				// T/VrVoqut4xtte9Flzlxix9Vfdwv4KePmfDgmV8p1l/o/hu52sjqNJou/q1IWX3ojAgDTg8xU3xrBlshDSKDLQ==
				// DI -
				////////////////////////////////////////////////////////////////////// MC0GCCqGSIb3DQIJAyEAeISx8l2Ix2yR4y1QJg+CvTxWYlC6699c6W69kMmVe7A=
				// PN - 01055997694
				// NM - ?????????
				// BD - 19780206
				////////////////////////////////////////////////////////////////////////

				MembVO memb = validationByCtn(httpServletRequest);
				if (memb == null) {
					logger.debug("{}", "(memb == null)");

					return createResultJson("9999", "????????? ?????? ??? ????????? ?????????????????????.");
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
				//RetrieveCustSvcEntrInfoBDServiceStub.DsOutputOutVO dsOutputOutVO = eSBManager
				//		.getCustomerInfo(memb.getMembNo(), memb.getProductNm());
				if (dsOutputOutVO == null) {
					logger.debug("{}", "(dsOutputOutVO == null)");

					return createResultJson("9999", "????????? ?????? ??? ????????? ?????????????????????.");
				}

				PauseHistoryCollectionVO pauseHistoryCollection = eSBManager.getPauseHistory(dsOutputOutVO.getEntrNo(),
						dsOutputOutVO.getSvcCd());
				if (pauseHistoryCollection == null) {
					logger.debug("{}", "(pauseHistoryCollection == null)");

					return createResultJson("9999", "????????? ?????? ??? ????????? ?????????????????????.");
				}

				List<PauseHistoryVO> list = pauseHistoryCollection.getList();
				if (list == null || list.isEmpty()) {
					logger.debug("{}", "(list == null)");

					return createResultJson("9999", "????????? ?????? ??? ????????? ?????????????????????.");
				}
				
				RetrieveCustInfoSvcServiceStub.DsCustInfoOutVO dsCustInfoOut = eSBManager
						.getStateInfo(subsInfoNcas.getSub_no());
				//RetrieveCustInfoSvcServiceStub.DsCustInfoOutVO dsCustInfoOut = eSBManager
				//		.getStateInfo(memb.getMembNo());
				if (dsCustInfoOut == null) {
					logger.debug("{}", "(dsCustInfoOut == null)");

					return createResultJson("9999", "????????? ?????? ??? ????????? ?????????????????????.");
				}

				PauseHistoryCollection pauseHistory = eSBManager.getPauseHistorys(subsInfoNcas.getSub_no(), memb.getProductNm());
				if (pauseHistory == null) {
					logger.debug("{}", "(pauseHistoryCollection == null)");

					return "errorPage";
				}
				List<PauseHistory> pauseList = pauseHistory.getList();
				if (pauseList == null || pauseList.isEmpty()) {
					logger.debug("{}", "(list == null)");

					return createResultJson("9999", "????????? ?????? ??? ????????? ?????????????????????.");
				}
				
				String entrNo = dsOutputOutVO.getEntrNo();// ??????
				String entrSttsChngRsnCd = "RSP";// ??????(SUS/RSP)
				String entrSttsChngRsnDtlCd = dsCustInfoOut.getEntrSttsChngRsnDtlCd();// ??????
				String entrSttsChngRsnDtlNm = dsCustInfoOut.getEntrSttsChngRsnDtlNm();// ??????
				String sendPhbYn = "N";// ??????
				String icallPhbYn = "N";// ??????
				String entrSttsValdStrtDt = new SimpleDateFormat("yyyyMMdd").format(new Date());// ??????
				String userMemo = null;
				String userDlrCd = dsOutputOutVO.getEntrDlrCd();// ??????
				String entrSttsChngSeqno = pauseList.get(0).getEntrSttsChngSeqno();
				String custNo = dsOutputOutVO.getCustNo();
				String prodNo = dsOutputOutVO.getProdNo();
				String aplyLvIcd = "C";
				String msgParamCnt = "3";
				String memoCrteDlrCd = "100000";
				String rsnCd ="E20";
				String rsnDtlCd = "4040";
				String msgParam1 = "????????????";
				String msgParam2 = new SimpleDateFormat("yyyy-MM-dd").format(new Date());// ??????

				UnpauseResultVO unpauseRsvResult = eSBManager.updateUnpauseRsv(entrNo,
						entrSttsValdStrtDt, 
						entrSttsChngSeqno,
						custNo,
						prodNo,
						aplyLvIcd,
						msgParamCnt,
						memoCrteDlrCd,
						rsnCd,
						rsnDtlCd,
						msgParam1,
						msgParam2);
				
				if (unpauseRsvResult == null) {
					result.put("resultCode", "0000");
					result.put("resultMsg", "??????");
				}

				result.put("resultCode", "0000");
				result.put("resultMsg", "??????");

			} else {
				HashMap<String, Object> parameter = new ObjectMapper().readValue(body,
						new TypeReference<HashMap<String, Object>>() {
						});

				result.put("resultCode", "0000");
				result.put("resultMsg", "??????");
			}
			
			return new ObjectMapper().writeValueAsString(result);
		} catch (Exception e) {
			logger.debug("{}", e.getMessage());

			e.printStackTrace();
		}

		return createResultJson("9999", "????????? ?????? ??? ????????? ?????????????????????.");
	}
	
	// ???????????????
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
				// ????????????
				//
				// TP - IPIN
				// CI -
				// T/VrVoqut4xtte9Flzlxix9Vfdwv4KePmfDgmV8p1l/o/hu52sjqNJou/q1IWX3ojAgDTg8xU3xrBlshDSKDLQ==
				// DI -
				////////////////////////////////////////////////////////////////////// MC0GCCqGSIb3DQIJAyEAwbAxA/yFJ+TI0E5HI4Qp9C96gKPNtUUk6bfvz960Quw=
				// PN -
				// NM - ?????????
				// BD - 19780206
				//
				// TP - KMC
				// CI -
				// T/VrVoqut4xtte9Flzlxix9Vfdwv4KePmfDgmV8p1l/o/hu52sjqNJou/q1IWX3ojAgDTg8xU3xrBlshDSKDLQ==
				// DI -
				////////////////////////////////////////////////////////////////////// MC0GCCqGSIb3DQIJAyEAeISx8l2Ix2yR4y1QJg+CvTxWYlC6699c6W69kMmVe7A=
				// PN - 01055997694
				// NM - ?????????
				// BD - 19780206
				////////////////////////////////////////////////////////////////////////

				MembVO memb = validationByAll(httpServletRequest);
				if (memb == null) {
					logger.debug("{}", "(memb == null)");

					return createResultJson("9999", "????????? ?????? ??? ????????? ?????????????????????.");
				}

				if (StringUtils.equals("Y", memb.getPayResvYn())) {
					logger.debug("{}", "PayResvYn is Y");
					// ?????? ?????????????????? ??????
					return createResultJson("1000", "???????????? ???????????? ??????????????????.\\n???????????? ????????? ????????? ???????????????.");
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
				//RetrieveCustSvcEntrInfoBDServiceStub.DsOutputOutVO dsOutputOutVO = eSBManager
				//		.getCustomerInfo(memb.getMembNo(), memb.getProductNm());
				if (dsOutputOutVO == null) {
					logger.debug("{}", "(dsOutputOutVO == null)");

					return createResultJson("9999", "????????? ?????? ??? ????????? ?????????????????????.");
				}

				/* 20180504 ??????????????? ????????? 10GB ????????? ????????? ?????? ?????? ??????
				 * 
				 */
				RetrieveDscntMgmtServiceStub.DsDscntListOutVO[] dsDscntListOut = eSBManager
						.getDiscountInfo(memb.getMembNo());
				if (dsDscntListOut == null) {
					logger.debug("{}", "(dsDscntListOut == null)");

					return createResultJson("9999", "????????? ?????? ??? ????????? ?????????????????????.");
				}

				RetrieveDscntMgmtServiceStub.DsDscntListOutVO found = null;
				for (int i = 0; i < dsDscntListOut.length; i++) {
					RetrieveDscntMgmtServiceStub.DsDscntListOutVO dsDscntListOut_ = dsDscntListOut[i];
					if (StringUtils.equals("Y", real)) {
						// LDZ0002086 (LTE) ?????????/??????????????? ????????? 10GB ????????? ????????? 15000???
						// LDZ0002087 ??????????????? ????????? 10GB ????????? ????????? 12,000??? ?????? (??????)
						// ??????(????????????)
						if (StringUtils.equals("LDZ0002087", dsDscntListOut_.getDscntSvcCd())) {
							if (!StringUtils.equals("ACT", dsDscntListOut_.getDscntSttsKdCd())) {
								// ??????????????? ??????????????? ?????? ????????? ??????
								return createResultJson("9999", "????????? ?????? ??? ????????? ?????????????????????.");
							}

							found = dsDscntListOut_;
							break;
						}
					} else {
						// LDZ0000770 ???????????? ??????(????????????)
						if (StringUtils.equals("LDZ0000770", dsDscntListOut_.getDscntSvcCd())) {
							found = dsDscntListOut_;
							break;
						}
					}
				}

				if (found == null) {
					logger.debug("{}", "(found == null)");

					return createResultJson("9999", "????????? ?????? ??? ????????? ?????????????????????.");
				}
				

				/* 20180504 ??????????????? ????????? 10GB ????????? ????????? ?????? ?????? ??????*/
				
				Date svcFrstStrtDttm = new Date();
				if (StringUtils.isNotEmpty(dsOutputOutVO.getSvcFrstStrtDttm())
						&& VALID_DATE_YYYYMMDD_REGEX.matcher(dsOutputOutVO.getSvcFrstStrtDttm()).find()
						&& !dsOutputOutVO.getSvcFrstStrtDttm().startsWith("9999")) {
					svcFrstStrtDttm = new SimpleDateFormat("yyyyMMdd").parse(dsOutputOutVO.getSvcFrstStrtDttm());
				}

				Calendar calendar = Calendar.getInstance();
				// calendar.setTime(memb.getJoinDt());
				calendar.setTime(svcFrstStrtDttm);
				
				//?????? ???????????? ?????? ?????? 20190128 kangjin
				String connDeviceModelId = getConnDeviceModel(memb);
				
				if("EL1".equals(connDeviceModelId)) {
					calendar.add(Calendar.YEAR, 2);
				}else {
					calendar.add(Calendar.YEAR, 1);
				}
				
				calendar.add(Calendar.DATE, -1);

				// dsDscntListOut
				// mode ???????????? VARCHAR2 14 N ??????
				// (LIST:??????,SAVE_ACT:????????????,SAVE_EXP:????????????)
				// entrNo ???????????? VARCHAR2 14 Y ?????? ???????????? ????????? NULL??????
				// prodNo ???????????? VARCHAR2 14 Y ?????? ???????????? ????????? NULL??????
				// entrByDscntSeqno entrByDscntSeqno VARCHAR2 14 Y ??????
				// SAVE_EXP:??????????????? ??????
				// svcCd ???????????? VARCHAR2 14 Y ?????? ????????????????????? ??????
				// svcDpndRelsCd ???????????????????????? VARCHAR2 14 Y ?????? ???????????? SMD/PMD
				// dscntSvcCd ???????????? VARCHAR2 14 Y ?????? ???????????? ????????? ???????????? ??????
				// entrDscntKdCd ?????????????????? VARCHAR2 14 Y ?????? ???????????? GEN
				// dscntSttsKdCd ?????????????????? VARCHAR2 14 Y ?????? (????????????,??????-ACT,??????-EXP)
				// dscntStrtDttm ?????????????????? VARCHAR2 14 Y ?????? dscntStrtKdCd ????????????????????????
				// dscntEndDttm ?????????????????? VARCHAR2 14 Y ?????? (SAVE_ACT:dscntEndKdCd
				// ????????????99991231,SAVE_EXP:??????????????? ??????)
				// dscntStrtKdCd ?????????????????? VARCHAR2 14 Y ??????
				// (A:??????(??????),B:??????1???,M1:1???????????????
				// ??????,M3:3??????????????? ??????)
				// dscntEndKdCd ?????????????????? VARCHAR2 14 Y ?????? (A:?????????,M3:3?????????
				// ??????,D30:30?????? ??????)
				// agmtStrtDttm ?????????????????? VARCHAR2 14 Y ??????
				// agmtEndDttm ?????????????????? VARCHAR2 14 Y ??????
				// agmtMnbr ?????????????????? VARCHAR2 14 Y ??????

				// ????????? ??????????????? ????????? ???????????? ???????????? ????????????.
				String mode = "SAVE_EXP";// ??????
				String entrNo = dsOutputOutVO.getEntrNo();
				String prodNo = dsOutputOutVO.getProdNo();
				String entrByDscntSeqno = found.getEntrByDscntSeqno();
				String svcCd = null;// dsOutputOutVO.getSvcCd();
				String svcDpndRelsCd = null;// ??
				String dscntSvcCd = found.getDscntSvcCd();
				String entrDscntKdCd = null;// dsDscntListOut.getEntrDscntKdCd();
				String dscntSttsKdCd = null;// dsDscntListOut.getDscntSttsKdCd();
				String dscntStrtDttm = null;// dsDscntListOut.getDscntStrtDttm();
				String dscntEndDttm = new SimpleDateFormat("yyyyMMdd").format(calendar.getTime());// ???????????????
																									// +
																									// 1???
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

					return createResultJson("9999", "????????? ?????? ??? ????????? ?????????????????????.");
				}

				if (!"N0001".equals(discountInfoResultVO.getErrCode())) {
					logger.debug("failed to updateDiscountInfo");

					return createResultJson("9999", discountInfoResultVO.getErrMsg());
				}
				

				/* 20180504 ??????????????? ????????? 10GB ????????? ????????? ?????? ?????? ??????
				// LDZ0002087 (LTE) ?????????/??????????????? ????????? 10GB ????????? ????????? 3000??? ??????(????????????)
				if (StringUtils.equals("Y", real)) {
					// dsDscntListOut
					// mode ???????????? VARCHAR2 14 N ??????
					// (LIST:??????,SAVE_ACT:????????????,SAVE_EXP:????????????)
					// entrNo ???????????? VARCHAR2 14 Y ?????? ???????????? ????????? NULL??????
					// prodNo ???????????? VARCHAR2 14 Y ?????? ???????????? ????????? NULL??????
					// entrByDscntSeqno entrByDscntSeqno VARCHAR2 14 Y ??????
					// SAVE_EXP:??????????????? ??????
					// svcCd ???????????? VARCHAR2 14 Y ?????? ????????????????????? ??????
					// svcDpndRelsCd ???????????????????????? VARCHAR2 14 Y ?????? ???????????? SMD/PMD
					// dscntSvcCd ???????????? VARCHAR2 14 Y ?????? ???????????? ????????? ???????????? ??????
					// entrDscntKdCd ?????????????????? VARCHAR2 14 Y ?????? ???????????? GEN
					// dscntSttsKdCd ?????????????????? VARCHAR2 14 Y ??????
					// (????????????,??????-ACT,??????-EXP)
					// dscntStrtDttm ?????????????????? VARCHAR2 14 Y ?????? dscntStrtKdCd
					// ????????????????????????
					// dscntEndDttm ?????????????????? VARCHAR2 14 Y ??????
					// (SAVE_ACT:dscntEndKdCd
					// ????????????99991231,SAVE_EXP:??????????????? ??????)
					// dscntStrtKdCd ?????????????????? VARCHAR2 14 Y ??????
					// (A:??????(??????),B:??????1???,M1:1???????????????
					// ??????,M3:3??????????????? ??????)
					// dscntEndKdCd ?????????????????? VARCHAR2 14 Y ?????? (A:?????????,M3:3?????????
					// ??????,D30:30?????? ??????)
					// agmtStrtDttm ?????????????????? VARCHAR2 14 Y ??????
					// agmtEndDttm ?????????????????? VARCHAR2 14 Y ??????
					// agmtMnbr ?????????????????? VARCHAR2 14 Y ??????

					// ????????? ???????????? ??????
					mode = "SAVE_ACT";// ??????
					entrNo = dsOutputOutVO.getEntrNo();
					prodNo = dsOutputOutVO.getProdNo();
					entrByDscntSeqno = null;
					svcCd = null;// dsOutputOutVO.getSvcCd();
					svcDpndRelsCd = null;// ??
					dscntSvcCd = "LDZ0002087";
					entrDscntKdCd = null;// dsDscntListOut.getEntrDscntKdCd();
					dscntSttsKdCd = "ACT";// dsDscntListOut.getDscntSttsKdCd();
					dscntStrtDttm = new SimpleDateFormat("yyyyMMdd").format(calendar.getTime());// ???????????????
																								// +
																								// 1???
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

						return createResultJson("9999", "????????? ?????? ??? ????????? ?????????????????????.");
					}

					if (!"N0001".equals(discountInfoResultVO.getErrCode())) {
						logger.debug("failed to updateDiscountInfo");

						return createResultJson("9999", discountInfoResultVO.getErrMsg());
					}
				}*/
				

				int value = memberDao.updateMemberPayResv(memb.getMembId(), "Y");
				if (value != 1) {
					logger.debug("failed to updateMemberPayResv");

					return createResultJson("9999", "????????? ?????? ??? ????????? ?????????????????????.");
				}

				result.put("name", dsOutputOutVO.getSvcNm());
				result.put("pay", ratePayDtl);
				result.put("date", new SimpleDateFormat("yyyy.MM.dd").format(new Date()));
				
				result.put("resultCode", "0000");
				result.put("resultMsg", "??????");

			} else {
				HashMap<String, Object> parameter = new ObjectMapper().readValue(body,
						new TypeReference<HashMap<String, Object>>() {
						});

				result.put("resultCode", "0000");
				result.put("resultMsg", "??????");
			}

			return new ObjectMapper().writeValueAsString(result);
		} catch (Exception e) {
			logger.debug("{}", e.getMessage());

			e.printStackTrace();
		}

		return createResultJson("9999", "????????? ?????? ??? ????????? ?????????????????????.");
	}

	// ??????????????????
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

					return createResultJson("9999", "????????? ?????? ??? ????????? ?????????????????????.");
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
				//RetrieveCustSvcEntrInfoBDServiceStub.DsOutputOutVO dsOutputOutVO = eSBManager
				//		.getCustomerInfo(memb.getMembNo(), memb.getProductNm());
				if (dsOutputOutVO == null) {
					logger.debug("{}", "(dsOutputOutVO == null)");

					return createResultJson("9999", "????????? ?????? ??? ????????? ?????????????????????.");
				}

				// acntOwnrNo ??????????????? VARCHAR2 13 N ?????? ????????????,???????????????,???????????????,????????????????????????
				// custDvCd ???????????? VARCHAR2 10 N ?????? "??????:I,??????:G"
				// custKdCd ???????????? VARCHAR2 2 N ?????? VW_CS_HB_CUST_KD_01 ??????
				// persIdind ??????????????? VARCHAR2 10 Y ?????? ?????????:Y
				// pymMthdCd ???????????? VARCHAR2 10 Y ?????? "????????????:CM,????????????:CC,??????:GR"
				// bankCd ???????????? VARCHAR2 10 Y ?????? ??????????????? ??????
				// bankAcntNo ?????????????????? VARCHAR2 21 Y ?????? ??????????????? ??????
				// cdcmpCd ???????????? VARCHAR2 10 Y ?????? ??????????????? ??????
				// cardNo ???????????? VARCHAR2 44 Y ?????? ??????????????? ??????
				// cardValdEndYymm ?????????????????? VARCHAR2 10 Y ?????? "??????????????? ??????YYYYMM ??????"
				// acntOwnrNm ???????????? VARCHAR2 250 N ?????? ????????????
				// mode ???????????? VARCHAR2 60 N ?????? "N : ????????? ??????/????????? ????????? ???????????? ????????????.
				// (????????????
				// ???????????????), P : ????????? ??????/????????? ????????? ????????? ???????????? ?????????. (???????????? ????????? ??????)"

				String acntOwnrNo = (String) parameter.get("acntOwnrNo");// ??????
				String custDvCd = (String) parameter.get("custDvCd");// ??????
				String custKdCd = "I".equals(custDvCd) ? "II" : "GE";// ??????
				String persIdind = (String) parameter.get("persIdind");
				String pymMthdCd = null;
				String bankCd = null;
				String bankAcntNo = null;
				String cdcmpCd = (String) parameter.get("cdcmpCd");
				String cardNo = (String) parameter.get("cardNo");
				String cardValdEndYymm = (String) parameter.get("cardValdEndYymm");
				String acntOwnrNm = (String) parameter.get("acntOwnrNm");// ??????
				String mode = "P";// ??????

				PayMethodAuthVO payMethodAuthVO = eSBManager.getPayMethod(acntOwnrNo, custDvCd, custKdCd, persIdind,
						pymMthdCd, bankCd, bankAcntNo, cdcmpCd, cardNo, cardValdEndYymm, acntOwnrNm, mode);
				if (payMethodAuthVO == null) {
					logger.debug("{}", "(payMethodAuthVO == null)");

					return createResultJson("9999", "????????? ?????? ??? ????????? ?????????????????????.");
				}

				if (!"0000".equals(payMethodAuthVO.getMsgCode())) {
					logger.debug("failed to getPayMethod");

					return createResultJson("9999", payMethodAuthVO.getMsgText());
				}

				result.put("resultCode", "0000");
				result.put("resultMsg", "??????");

			} else {
				String userAgent = httpServletRequest.getHeader(HttpHeaders.USER_AGENT);

				HashMap<String, Object> parameter = new ObjectMapper().readValue(body,
						new TypeReference<HashMap<String, Object>>() {
						});

				String acntOwnrNo = (String) parameter.get("acntOwnrNo");// ??????
				String custDvCd = (String) parameter.get("custDvCd");// ??????
				String custKdCd = "I".equals(custDvCd) ? "II" : "GE";// ??????
				String persIdind = (String) parameter.get("persIdind");
				String cdcmpCd = (String) parameter.get("cdcmpCd");
				String cardNo = (String) parameter.get("cardNo");
				String cardValdEndYymm = (String) parameter.get("cardValdEndYymm");
				String acntOwnrNm = (String) parameter.get("acntOwnrNm");// ??????

				result.put("resultCode", "0000");
				result.put("resultMsg", "??????");
			}

			return new ObjectMapper().writeValueAsString(result);
		} catch (Exception e) {
			logger.debug("{}", e.getMessage());

			e.printStackTrace();
		}

		return createResultJson("9999", "????????? ?????? ??? ????????? ?????????????????????.");
	}
	
	/**
	 * ???????????? > ??????
	 */
	@Override
	public String getUnpauseResult(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
			Model model) {
		try {
			if (StringUtils.equalsIgnoreCase("Y", esbEnable)) {
				// TODO
			} else {
				// TODO
			}

			return "unpauseResult";
		} catch (Exception e) {
			logger.debug("{}", e.getMessage());

			e.printStackTrace();
		}

		return "errorPage";
	}
	
	/**
	 * ????????????????????????
	 */
	@Override
	public String getUnpauseApply(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
			Model model) {
		try {
			if (StringUtils.equalsIgnoreCase("Y", esbEnable)) {
				// TODO
			} else {
				// TODO
			}

			return "unpauseApply";
		} catch (Exception e) {
			logger.debug("{}", e.getMessage());

			e.printStackTrace();
		}

		return "errorPage";
	}
	
	/**
	 * ??????????????????????????????
	 */
	@Override
	public String getUnpauseRsvApply(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
			Model model) {
		try {
			if (StringUtils.equalsIgnoreCase("Y", esbEnable)) {
				// TODO
			} else {
				// TODO
			}

			return "unpauseRsv";
		} catch (Exception e) {
			logger.debug("{}", e.getMessage());

			e.printStackTrace();
		}

		return "errorPage";
	}
	
	/**
	 * ???????????? ?????? 20190128 kangjin
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
