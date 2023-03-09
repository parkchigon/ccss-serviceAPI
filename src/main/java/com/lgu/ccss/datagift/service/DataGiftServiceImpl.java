package com.lgu.ccss.datagift.service;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang.StringUtils;
import org.apache.http.HttpHeaders;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.lgu.ccss.common.dao.MemberDao;
import com.lgu.ccss.common.dao.datagift.DatagiftDao;
import com.lgu.ccss.common.dao.datagift.DatagiftPaymentDao;
import com.lgu.ccss.common.dao.datagift.DatagiftRegDao;
import com.lgu.ccss.common.dao.mgr.MgrAppDeviceDao;
import com.lgu.ccss.common.dao.mgr.MgrAppSessionDao;
import com.lgu.ccss.common.model.MembVO;
import com.lgu.ccss.common.model.datagift.DataGiftVO;
import com.lgu.ccss.common.model.datagift.PaymentVO;
import com.lgu.ccss.common.model.datagift.RegisterVO;
import com.lgu.ccss.common.model.mgr.MgrAppDeviceVO;
import com.lgu.ccss.common.model.mgr.MgrAppSessVO;
import com.lgu.ccss.common.util.CCSSUtil;
import com.lgu.common.datagift.DataGiftManager;
//import lgdacom.XPayClient.XPayClient;
import com.lgu.common.datagift.XPayClient;
import com.lgu.common.datagift.domain.Campaign;
import com.lgu.common.datagift.domain.CampaignInfo;
import com.lgu.common.datagift.domain.DataGift;
import com.lgu.common.datagift.domain.DataGiftCtnCheck;
import com.lgu.common.datagift.domain.DataGiftInfo;
import com.lgu.common.datagift.domain.DataGiftList;
import com.lgu.common.datagift.domain.DataGiftListInfo;
import com.lgu.common.datagift.domain.DataGiftOwnList;
import com.lgu.common.datagift.domain.DataGiftOwnListInfo;
import com.lgu.common.datagift.domain.DataGiftReg;
import com.lgu.common.datagift.domain.LogAgent;
import com.lgu.common.datagift.domain.LogResponseJSON;
import com.lgu.common.datagift.util.AES256DataGiftUtil;
import com.lgu.common.datagift.util.GiftApiAES256;
import com.lgu.common.ncas.NCASQueryManager;
import com.lgu.common.ncas.NCASResultData;

@Service("dataGiftService")
public class DataGiftServiceImpl implements DataGiftService {

	private static final Logger logger = LoggerFactory.getLogger(DataGiftServiceImpl.class);

	@Autowired
	private MgrAppSessionDao mgrAppSessionDao;

	@Autowired
	private MemberDao memberDao;

	@Autowired
	private MgrAppDeviceDao mgrAppDeviceDao;

	@Autowired
	private DatagiftDao datagiftDao;
	
	@Autowired
	private DatagiftPaymentDao datagiftPaymentDao;
	
	@Autowired
	private DatagiftRegDao datagiftRegDao;
	
	@Autowired
	private DataGiftManager dataGiftManager;
	
	@Autowired
	private NCASQueryManager nCASQueryManager;
	
	@Autowired
	private LogAgent logAgent;
	
	// LG유플러스 결제서비스 선택(test:테스트, service:서비스)
	@Value("#{config['data.gift.xpay.cst.platform']}")
	private String dataGiftXPayCstPlatform;
	
	// LG유플러스로 부터 발급받으신 상점아이디를 입력
	@Value("#{config['data.gift.xpay.cst.mid']}")
	private String dataGiftXPayCstMid;
	
	// 상점MertKey(mertkey는 상점관리자 -> 계약정보 -> 상점정보관리에서 확인하실수 있습니다)
	@Value("#{config['data.gift.xpay.lgd.mertkey']}")
	private String dataGiftXPayLgdMertkey;
	
	// 고객정보 암호화 KEY
	@Value("#{config['data.gift.encryption.key']}")
	private String dataGiftEncryptionKey;
	
	// LGD_RETURNURL 을 설정하여 주시기 바랍니다. 반드시 현재 페이지와 동일한 프로트콜 및 호스트이어야 합니다.
	@Value("#{config['data.gift.xpay.lgd.return.url']}")
	private String dataGiftXPayLgdReturnUrl;
	
	@Value("#{config['data.gift.fee_type.248']}")
	private String feeType248;
	
	@Value("#{config['data.gift.fee_type.288']}")
	private String feeType288;
	
	//데이터상품권 서버로그
	@Value("#{config['data.gift.service.log.url']}")
	private String logdataurl;

//	private Pattern VALID_DATE_YYYYMMDD_REGEX = Pattern.compile("^[0-9]{4}(1[0-2]|0[1-9])(3[01]|[12][0-9]|0[1-9])$", Pattern.CASE_INSENSITIVE);
//	private Pattern VALID_EMAIL_ADDRESS_REGEX = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
//	private Pattern VALID_NUMBER_REGEX = Pattern.compile("^[0-9]*$", Pattern.CASE_INSENSITIVE);

	private Pattern VALID_USER_AGENT_REGEX = Pattern
			.compile("\\[(.*)\\,(.*)\\]", Pattern.CASE_INSENSITIVE);

	
	private MembVO validationByAll(HttpServletRequest httpServletRequest) {
		String userAgent = httpServletRequest.getHeader(HttpHeaders.USER_AGENT);
		logger.info(String.format("userAgent(%s)", userAgent));
			 
		String memberId = "";
		String mgrAppId = "";
		String mgrAppCtn = "";
		String sessionId = "";
		String avnCtn = "";
		MgrAppSessVO mgrAppSess = null;
		
		Matcher matcher = VALID_USER_AGENT_REGEX.matcher(userAgent);
		if (matcher.find()) {

			mgrAppCtn = matcher.group(1);
			
			String[] arr = StringUtils.splitByWholeSeparator(mgrAppCtn, ",");
			
			if(arr.length == 2) {
				mgrAppCtn = arr[0];
				sessionId = arr[1];
			}else {
				mgrAppCtn = matcher.group(1);
				sessionId = matcher.group(2);
			}			
		}

		if (StringUtils.isEmpty(mgrAppCtn)) {
			logger.error(String.format("mgrAppCtn(%s) is empty", mgrAppCtn));
			return null;
		}

		if (StringUtils.isEmpty(sessionId)) {
			logger.error(String.format("sessionId(%s) is empty", sessionId));
			return null;
		}

		logger.info(String.format("mgrAppCtn(%s) sessionId(%s)", mgrAppCtn, sessionId));

		mgrAppSess = mgrAppSessionDao.selectMgrSess(sessionId);
		if (mgrAppSess == null) {
			logger.error(String.format("failed to select mgrAppSess data. mgrAppCtn(%s) sessionId(%s)",	mgrAppCtn, sessionId));
			return null;
		}

		MgrAppDeviceVO mgrAppDevice = new MgrAppDeviceVO();
		mgrAppDevice.setMgrappId(mgrAppSess.getMgrappId());
		mgrAppDevice = mgrAppDeviceDao.selectMgrMainDeviceInfo(mgrAppDevice);
		if (mgrAppDevice == null) {
			logger.error(String.format("failed to select mgrAppDevice data. mgrAppCtn(%s) sessionId(%s) mgrAppId(%s)"
							, mgrAppCtn, sessionId, mgrAppSess.getMgrappId()));
			return null;
		}
		
		mgrAppId = mgrAppSess.getMgrappId();
		memberId = mgrAppDevice.getMembId();

		MembVO memb = memberDao.selectMemberByID(mgrAppDevice.getMembId());
		
		if (memb == null) {
			logger.error(String.format("failed to select member data. mgrAppCtn(%s) sessionId(%s) deviceSess(%s)",
					mgrAppCtn, sessionId, mgrAppSess));
			return null;
		}
		
		avnCtn = memb.getMembCtn();
		HttpSession session = httpServletRequest.getSession();
		session.setAttribute("mgrAppCtn", mgrAppCtn);
		session.setAttribute("mgrAppId", mgrAppId);
		
		logger.info(String.format("[validation정보] mgrAppCtn=%s, sessionId=%s, mgrAppId=%s, memberId=%s, avnCtn=%s", mgrAppCtn, sessionId, mgrAppId, memberId, avnCtn));
		
		return memb;
	}
	
	
	@SuppressWarnings("null")
	private boolean getCanBuyGift(String ctn)
	{
		logger.debug("DataGiftServiceImpl_getCanBuyGift start");
		boolean canBuyGift = true;
		try{
			String fee_type = null;
			
			/*************************************************************** 
			 * NCAS 연동 수행: 차량의 ctn(avnCtn)과 연동된 요금제(부가서비스) 조회.
			 * 가입요금제(부가서비스)가 Premium 이상이 아닌 경우는 아래의 내용으로 팝업 메세지를  출력.
			
			 * 상품코드
				1) 요금제
				LPZ0000248, 개통용		=> canBuyGift = "false"
				LPZ0000288, Basic	=> canBuyGift = "false"
				2) 부가상품
				LRZ0001028, Premium 무료형
				LRZ0001029, Premium 유료형
				LRZ0001030, Payment
				LRZ0001814, Data Coupon 100MB
				LRZ0001815, Data Coupon 250MB
				LRZ0001816, Data Coupon 500MB
				LRZ0001817, Data Coupon 1GB
				LRZ0001818, Data Coupon 2GB
				LRZ0001819, Data Coupon 5GB
				3) 과금데이타서비스요소
				LBD0000928, 긴급구난(에어백 전개 자동통보/출동)
				LBD0000929, 차량진단
				LBD0000930, 텔레메틱스 (차량추적, 차량제어, 음성/고장전화)
				LBD0000931, 네비게이션
				LBD0000932, 서버기반 AI음성인식
				LBD0000933, 날씨
				LBD0000934, 메시지 보내주기
				LBD0000935, 홈 IOT
				4) 할인상품
				LDZ0002090, Basic 할인
			***************************************************************/
	
			NCASResultData ncasData = nCASQueryManager.query(ctn);
			if (ncasData == null && ncasData.getSubsInfo() == null) {
				logger.error(String.format("[데이타상품권 NCAS연동] failed to query NCAS data. ctn(%s)", ctn));
				canBuyGift = false;
				return canBuyGift;
			}
	
			fee_type = ncasData.getSubsInfo().getFee_type();
//			if(fee_type == null){
//				logger.error(String.format("[데이타상품권 NCAS연동] fee_type is null. ctn(%s)", ctn));
//				canBuyGift = false;
//				return canBuyGift;
//			}
			
			if(feeType248.contentEquals(fee_type) || feeType288.contentEquals(fee_type)){
				// 개통용 또는 Basic요금제의 경우: 데이타상품권 구매를 제한 한다.
				canBuyGift = true;
			}else{
				// 나머지 부가상품 가입자의 경우: 데이타상품권 구매를 허용한다.
				//canBuyGift = "false";
				if(ncasData.getSubsInfo().getSvc_auth_dt() == null) {
					logger.error(String.format("[데이타상품권 NCAS연동] svc_auth is null. ctn(%s)", ctn));
					canBuyGift = false;
					return canBuyGift;
				} else {
					String svcAuth1 = java.net.URLDecoder.decode(ncasData.getSubsInfo().getSvc_auth_dt(), "UTF-8");
					String[] svcAuth = svcAuth1.split("\\|");
					logger.debug(String.format("getCanBuyGift_svcAuth1(%s)", svcAuth1));
					if(svcAuth != null && svcAuth.length > 5){
						for(int i = 4 ; i <= 2 ;i--){
							if(svcAuth[i].equals("1")){
								canBuyGift = true;
							}
						}
					}
				}
			}
			
		}catch(Exception e){
			logger.error(String.format("[데이타상품권 NCAS연동] 요금제 정보 조회 오류발생, reason=%s", e.getMessage()), e);
			canBuyGift = false;
		}
		return canBuyGift;
	}

	/*******************************************************************************************************
	 * 데이타상품권 메인화면(main.jsp) 요청 처리
	 * 
	 * - request parameter : carNumber(차량번호 또는 차종)
	 * - model : carNumber, canBuyGift
	 *******************************************************************************************************/
	@Override
	public String getMain(HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse, Model model) {
		logger.debug("DataGiftServiceImpl_getMain start");
		try {
			HttpSession session = httpServletRequest.getSession();
			MembVO memb = validationByAll(httpServletRequest);
			Boolean canBuyGift = true;
			String carNumber = null;
			String devType = null;
			String ctn = null;
			
			ctn = (memb != null) ?  memb.getMembCtn() : null;
			if (memb == null || ctn == null) {
				logger.error("getMain error, memb == null or ctn == null");
				return "datagift/error";
			}
			String ccssToken = StringUtils.defaultIfEmpty(httpServletRequest.getParameter("ccssToken"),"");
			String appVersion = StringUtils.defaultIfEmpty(httpServletRequest.getParameter("appVersion"),"");
			String carOem = StringUtils.defaultIfEmpty(httpServletRequest.getParameter("carOem"), "");
			String devModel = StringUtils.defaultIfEmpty(httpServletRequest.getParameter("devModel"), "");
			String nwType = StringUtils.defaultIfEmpty(httpServletRequest.getParameter("nwType"), "");
			String posX = StringUtils.defaultIfEmpty(httpServletRequest.getParameter("posX"), "");
			String posY = StringUtils.defaultIfEmpty(httpServletRequest.getParameter("posY"), "");
			String userCtn = StringUtils.defaultIfEmpty(CCSSUtil.getMgrUserLoginId(), "");
			String uuid = StringUtils.defaultIfEmpty(CCSSUtil.getUuid(), "");
			logger.debug(String.format("userCtn=%s, uuid=%s, ccssToken=%s, appVersion=%s, carOem=%s, devModel=%s, posX=%s, posY=%s, nwType=%s",
					userCtn, uuid, ccssToken, appVersion, carOem, devModel, posX, posY, nwType));

			session.setAttribute("ccssToken", ccssToken);
			session.setAttribute("appVersion", appVersion);
			session.setAttribute("carOem", carOem);
			session.setAttribute("devModel", devModel);
			session.setAttribute("nwType", nwType);
			session.setAttribute("posX", posX);
			session.setAttribute("posY", posY);
			session.setAttribute("userCtn", userCtn);
			session.setAttribute("uuid", uuid);
			
			carNumber = StringUtils.defaultIfEmpty(httpServletRequest.getParameter("carNumber"), "[차량정보없음]");
			devType = StringUtils.defaultIfEmpty(httpServletRequest.getParameter("devType"), "android");
			session.setAttribute("carNumber", carNumber);
			session.setAttribute("devType", devType);
			canBuyGift = this.getCanBuyGift(ctn);
			
			logger.debug(String.format("[main.do parameter]: devType=%s, carNumber=%s, canBuyGift=%s", devType, carNumber, canBuyGift));
			
			model.addAttribute("carNumber", carNumber);
			model.addAttribute("canBuyGift", canBuyGift);
			model.addAttribute("devType", devType);
			
			model.addAttribute("ccssToken", ccssToken);
			model.addAttribute("appVersion", appVersion);
			model.addAttribute("carOem", carOem);
			model.addAttribute("devModel", devModel);
			model.addAttribute("nwType", nwType);
			model.addAttribute("posX", posX);
			model.addAttribute("posY", posY);
			model.addAttribute("userCtn", userCtn);
			model.addAttribute("uuid", uuid);
			
			// Log서버 Proxy 테스트 코드
			/*
			String dataSize = "0";
			String logTime = (new SimpleDateFormat("yyyyMMddHHmmss")).format(new Date());
			LogResponseJSON logResponseJSON = logAgent.sendLog(ctn, ccssToken, devType, uuid, appVersion, carOem, devModel, userCtn, "success", "200", dataSize, nwType, posX, posY, logTime);
			logger.debug(String.format("[STEP X] sendLog Result > ctn(%s) : resultMsg(fail) resultCode(100) logResponse(%s) : ", ctn, logResponseJSON.toString()));
			*/
			// Log서버 Proxy 테스트 코드
			
			return "datagift/main";
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}

		return "datagift/error";
	}

	/*******************************************************************************************************
	 * 데이타상품권 이력화면(history.jsp) 요청 처리
	 * - request parameter : carNumber(차량번호 또는 차종)
	 * - model : carNumber, list, ctn
	 *******************************************************************************************************/
	@Override
	public String getPurchaseHistory(HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse, Model model) {
		logger.debug("DataGiftServiceImpl_getPurchaseHistory start");
		try {
			MembVO memb = validationByAll(httpServletRequest);
			if (memb == null) {
				logger.error("(memb == null)");

				return "datagift/error";
			}
			
			String carNumber = StringUtils.defaultIfEmpty(httpServletRequest.getParameter("carNumber"), "[차량정보없음]");
			String devType = httpServletRequest.getParameter("devType");
			
			//20190408
			String ccssToken = httpServletRequest.getParameter("ccssToken");
			String appVersion = httpServletRequest.getParameter("appVersion");
			String carOem = httpServletRequest.getParameter("carOem");
			String devModel = httpServletRequest.getParameter("devModel");
			String nwType = httpServletRequest.getParameter("nwType");
			String posX = httpServletRequest.getParameter("posX");
			String posY = httpServletRequest.getParameter("posY");
			String userCtn = CCSSUtil.getMgrUserLoginId();
			String uuid = CCSSUtil.getUuid();
			
			logger.debug(String.format("userCtn=(%s), uuid=(%s), ccssToken=(%s), appVersion=(%s), carOem=(%s), devModel=(%s), posX=(%s), posY=(%s), nwType=(%s)",
					userCtn, uuid, ccssToken, appVersion, carOem, devModel, posX, posY, nwType));
			
			String ctn = null;
			ctn = memb.getMembCtn();
			
			String encrypt_ctn = GiftApiAES256.encrypt(ctn, this.dataGiftEncryptionKey);
			String encode_ctn = new String(Base64.encodeBase64(encrypt_ctn.getBytes()));
			logger.debug(String.format("getPurchaseHistory > ctn(%s) : encrypt_ctn(%s) encode_ctn(%s) : ", ctn, encrypt_ctn, encode_ctn));
			
			
			
			DataGiftCtnCheck dataGiftCtnCheck = dataGiftManager.getDataGiftCtnCheck(encode_ctn);
			if (!DataGiftManager.SUCCESS.equals(dataGiftCtnCheck.getResultCode())) {
				logger.error(String.format("failed to gataGiftCtnCheck - %s, %s",
						dataGiftCtnCheck.getResultCode(),
						dataGiftCtnCheck.getResultMessage()));

				return "datagift/error";
			}

			// 상품권리스트 타입
			// U : 나의 이용중 내역(미입력 시 기본값)
			// P : 충전하기 내역
			// G : 선물하기 내역
			// R : 상품권번호 등록 내역
			DataGiftOwnList dataGiftOwnList = dataGiftManager.getDataGiftOwnList(encode_ctn, "U", "19000101", "99991230"); /* startdate, endDate가 없으면, "Parameter 정보 오류" */
			if (!DataGiftManager.SUCCESS.equals(dataGiftOwnList.getResultCode())) {
				logger.error(String.format("failed to getDataGiftOwnList - %s, %s",
						dataGiftOwnList.getResultCode(),
						dataGiftOwnList.getResultMessage()));

				return "datagift/error";
			}

			DataGiftOwnListInfo dataGiftOwnListInfo = this.convertDataGiftList(dataGiftOwnList, memb);
			List<DataGiftInfo> list = dataGiftOwnListInfo.getDataGiftListInfo();
			
			/**************************
			 * 데이타상품권의 구매이력 정보는
			 * 구매가격이 없으므로, DB에서 조회 후, 추가한다.
			 * : List<DataGiftInfo> list 정보를
			 * DB조회 결과를 사용하여 업데이트 한다.
			 **************************/
			
			// DB 조회 > list 업데이트 .... 작업예정!

			model.addAttribute("list",list != null ? list : new ArrayList<DataGift>());
			logger.debug("list size : " + list.size());
			logger.debug("list : " + list.toString());
			
			model.addAttribute("ctn", ctn);
			model.addAttribute("carNumber", carNumber);
			model.addAttribute("dataGiftListCnt", dataGiftOwnList.getDataGiftListCnt());
			model.addAttribute("devType", devType);

			return "datagift/history";
		} catch (Exception e) {
			logger.error(String.format("getPurchaseHistory error, reason=%s", e.getMessage()), e);
		}

		return "datagift/error";
	}

	
	/*******************************************************************************************************
	 * 상품권구매 화면(list.jsp) 요청 처리
	 * - request parameter : carNumber(차량번호 또는 차종), canBuyGift(구매하기 가능여부: "true", "false")
	 * - model: list, ctn, carNumber, canBuyGift
	 *******************************************************************************************************/	
	@Override
	public String getDataGiftList(HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse, Model model) {
		logger.debug("DataGiftServiceImpl_getDataGiftList start");
		try {
			
			HttpSession session = httpServletRequest.getSession();
			
			String carNumber = (String)session.getAttribute("carNumber");
			String canBuyGift = httpServletRequest.getParameter("canBuyGift");
			String devType = (String)session.getAttribute("devType");
			
			//20190408 로그
			String ccssToken = (String) session.getAttribute("ccssToken");
			String appVersion  = (String) session.getAttribute("appVersion");
			String carOem  = (String) session.getAttribute("carOem");
			String devModel  = (String) session.getAttribute("devModel");
			String nwType  = (String) session.getAttribute("nwType");
			String posX  = (String) session.getAttribute("posX");
			String posY  = (String) session.getAttribute("posY");
			String userCtn  = (String) session.getAttribute("userCtn");
			String uuid  = (String) session.getAttribute("uuid");
			
			logger.debug(String.format("userCtn=%s, uuid=%s, ccssToken=%s, appVersion=%s, carOem=%s, devModel=%s, posX=%s, posY=%s, nwType=%s",
					userCtn, uuid, ccssToken, appVersion, carOem, devModel, posX, posY, nwType));
			
			MembVO memb = validationByAll(httpServletRequest);
			logger.debug("getDataGiftList memb" + memb);
			if (memb == null) {
				logger.error("(memb == null)");

				return "datagift/error";
			}
			
			String mgrAppCtn = (String)session.getAttribute("mgrAppCtn"); // validationByAll 호출 이후, session에서 얻어야 한다.
			logger.debug("getDataGiftList mgrAppCtn" +mgrAppCtn);
			
			String ctn = null;
			ctn = memb.getMembCtn();
			String encrypt_ctn = GiftApiAES256.encrypt(ctn, this.dataGiftEncryptionKey);
			String encode_ctn = new String(Base64.encodeBase64(encrypt_ctn.getBytes()));

			logger.debug(String.format("getDataGiftList > ctn(%s) : encrypt_ctn(%s) encode_ctn(%s) : ", ctn, encrypt_ctn, encode_ctn));
			
			DataGiftList dataGiftList = null;
			DataGiftCtnCheck dataGiftCtnCheck = dataGiftManager.getDataGiftCtnCheck(encode_ctn);
			if (!DataGiftManager.SUCCESS.contentEquals(dataGiftCtnCheck.getResultCode())) {
				logger.error(String.format("failed to gataGiftCtnCheck - %s, %s",
						dataGiftCtnCheck.getResultCode(),
						dataGiftCtnCheck.getResultMessage()));

				return "datagift/error";
			}

			dataGiftList = dataGiftManager.getDataGiftList();
			if (!DataGiftManager.SUCCESS.equals(dataGiftList.getResultCode())) {
				logger.error(String.format("failed to getDataGiftList - %s, %s",
						dataGiftList.getResultCode(),
						dataGiftList.getResultMessage()));

				return "datagift/error";
			}
			
			DataGiftListInfo dataGiftListInfo = this.convertCampaignList(dataGiftList); 
			List<CampaignInfo> list = dataGiftListInfo.getCampaignList();

			model.addAttribute("list", list != null ? list : new ArrayList<CampaignInfo>());
			model.addAttribute("ctn", ctn);
			model.addAttribute("carNumber", carNumber);
			model.addAttribute("canBuyGift", canBuyGift);
			model.addAttribute("devType", devType);
			model.addAttribute("mgrAppCtn", mgrAppCtn);
			
			model.addAttribute("ccssToken", ccssToken);
			model.addAttribute("appVersion", appVersion);
			model.addAttribute("carOem", carOem);
			model.addAttribute("devModel", devModel);
			model.addAttribute("nwType", nwType);
			model.addAttribute("posX", posX);
			model.addAttribute("posY", posY);
			model.addAttribute("userCtn", userCtn);
			model.addAttribute("uuid", uuid);
			
			return "datagift/list";
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}

		return "datagift/error";
	}

	/*******************************************************************************************************
	 * 구매하기 화면(payment.jsp) 요청 처리
	 * - request [get] parameter : carNumber(차량번호 또는 차종), canBuyGift(구매하기 가능여부: "true", "false")
	                              giftName(상품권 이름), dataSize(데이타용량), sellAmount(가격), issueNo(상품권 발행번호),
	                              giftKub(상품권구분-청소년/일반), issueName(캠페인명), campStartDt(캠페인 시작일자), campEndDt(캠페인 종료일자) // 2010-10-10, 추가!
	                              LGD_BUYER(구매자명), LGD_BUYER_PHONENUM(구매자 휴대폰번호), LGD_BUYEREMAIL(구매자이메일),
	                              LGD_CUSTOM_FIRSTPAY(초기결제수단)
	   - request [set] attribute : carNumber, canBuyGift, ctn, giftName, dataSize, sellAmount, issueNo, 
	   							  giftKub(상품권구분-청소년/일반), issueName(캠페인명), campStartDt(캠페인 시작일자), campEndDt(캠페인 종료일자) // 2010-10-10, 추가!
								  CST_PLATFORM, CST_MID, LGD_MERTKEY, LGD_RETURNURL, LGD_OID,
								  LGD_PRODUCTINFO, LGD_TIMESTAMP, LGD_BUYER, LGD_BUYER_PHONENUM,
								  LGD_BUYEREMAIL, LGD_CUSTOM_FIRSTPAY
	   - model: carNumber, canBuyGift, ctn, giftName, dataSize, sellAmount, issueNo, 
	   			giftKub(상품권구분-청소년/일반), issueName(캠페인명), campStartDt(캠페인 시작일자), campEndDt(캠페인 종료일자) // 2010-10-10, 추가!
	   			LGD_BUYER, LGD_BUYER_PHONENUM, LGD_BUYEREMAIL, LGD_CUSTOM_FIRSTPAY
	 *******************************************************************************************************/		
	@Override
	public String getPayment(HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse, Model model) {
		logger.debug("DataGiftServiceImpl_getPayment start");
		
		try {
			HttpSession session = httpServletRequest.getSession();
			MembVO memb = validationByAll(httpServletRequest);
			if (memb == null) {
				logger.error("(memb == null)");

				return "datagift/error";
			}

			String memberId = memb.getMembId();
			String ctn = memb.getMembCtn();

			String encrypt_ctn = GiftApiAES256.encrypt(ctn, this.dataGiftEncryptionKey);
			String encode_ctn = new String(Base64.encodeBase64(encrypt_ctn.getBytes()));
			
			logger.debug(String.format("getPayment > ctn(%s) : encrypt_ctn(%s) encode_ctn(%s) : ", ctn, encrypt_ctn, encode_ctn));
			
			/*  ----------------- 임시로 막아 둠, 2018-09-27 ----------------*/
			DataGiftCtnCheck dataGiftCtnCheck = dataGiftManager.getDataGiftCtnCheck(encode_ctn);
			if (!DataGiftManager.SUCCESS.equals(dataGiftCtnCheck.getResultCode())) {
				logger.error(String.format("failed to gataGiftCtnCheck - %s, %s",
						dataGiftCtnCheck.getResultCode(),
						dataGiftCtnCheck.getResultMessage()));

				return "datagift/error";
			}

			String carNumber = httpServletRequest.getParameter("carNumber");
			String canBuyGift = httpServletRequest.getParameter("canBuyGift");
			String giftName = StringUtils.defaultIfEmpty(httpServletRequest.getParameter("giftName"), "");
			String dataSize = StringUtils.defaultIfEmpty(httpServletRequest.getParameter("dataSize"), "");
			String sellAmount = StringUtils.defaultIfEmpty(httpServletRequest.getParameter("sellAmount"), "");
			String issueNo = StringUtils.defaultIfEmpty(httpServletRequest.getParameter("issueNo"), "");
			logger.debug(String.format("getPayment > issueNo(%s) ", issueNo));
			
			String giftKub = StringUtils.defaultIfEmpty(httpServletRequest.getParameter("giftKub"), "");
			String issueName = StringUtils.defaultIfEmpty(httpServletRequest.getParameter("issueName"), "");
			String campStartDt = StringUtils.defaultIfEmpty(httpServletRequest.getParameter("campStartDate"), "");
			String campEndDt = StringUtils.defaultIfEmpty(httpServletRequest.getParameter("campEndDate"), "");
			String mgrAppId = (String)session.getAttribute("mgrAppId"); // validationByAll 호출 이후, session에서 얻어야 한다.
			String mgrAppCtn = (String)session.getAttribute("mgrAppCtn"); // validationByAll 호출 이후, session에서 얻어야 한다.
			
			//20190408
			String ccssToken = httpServletRequest.getParameter("ccssToken");
			String appVersion = httpServletRequest.getParameter("appVersion");
			String carOem = httpServletRequest.getParameter("carOem");
			String devModel = httpServletRequest.getParameter("devModel");
			String nwType = httpServletRequest.getParameter("nwType");
			String posX = httpServletRequest.getParameter("posX");
			String posY = httpServletRequest.getParameter("posY");
			String userCtn = httpServletRequest.getParameter("userCtn");
			String uuid = httpServletRequest.getParameter("uuid");
			
			logger.debug(String.format("userCtn=%s, uuid=%s, ccssToken=%s, appVersion=%s, carOem=%s, devModel=%s, posX=%s, posY=%s, nwType=%s",
					userCtn, uuid, ccssToken, appVersion, carOem, devModel, posX, posY, nwType));
			
			String LGD_BUYER = httpServletRequest.getParameter("LGD_BUYER");
			String LGD_BUYER_PHONENUM = httpServletRequest.getParameter("LGD_BUYER_PHONENUM");
			String LGD_BUYEREMAIL = httpServletRequest.getParameter("LGD_BUYEREMAIL");
			String LGD_CUSTOM_FIRSTPAY = httpServletRequest.getParameter("LGD_CUSTOM_FIRSTPAY");
			
			
			SimpleDateFormat f = new SimpleDateFormat("yyyyMMddHHmmss");
		    String timeStamp = f.format(new Date());			
			
			httpServletRequest.setAttribute("CST_PLATFORM", this.dataGiftXPayCstPlatform);
			httpServletRequest.setAttribute("CST_MID", this.dataGiftXPayCstMid);
			httpServletRequest.setAttribute("LGD_MERTKEY", this.dataGiftXPayLgdMertkey);		// payment.jsp에서 LGD_HASHDATA 데이타 생성에만 사용됨.
			httpServletRequest.setAttribute("LGD_RETURNURL", this.dataGiftXPayLgdReturnUrl);
			httpServletRequest.setAttribute("LGD_OID", String.format("OID_%s_%s", timeStamp, UUID.randomUUID().toString()));
			httpServletRequest.setAttribute("LGD_PRODUCTINFO", giftName);
			httpServletRequest.setAttribute("LGD_TIMESTAMP", timeStamp);
			
			httpServletRequest.setAttribute("memberId", memberId);
			httpServletRequest.setAttribute("ctn", ctn);
			httpServletRequest.setAttribute("giftName", giftName);
			httpServletRequest.setAttribute("dataSize", dataSize);
			httpServletRequest.setAttribute("sellAmount", sellAmount);
			httpServletRequest.setAttribute("issueNo", issueNo);
			httpServletRequest.setAttribute("carNumber", carNumber);
			httpServletRequest.setAttribute("canBuyGift", canBuyGift);
			httpServletRequest.setAttribute("giftKub", giftKub);
			httpServletRequest.setAttribute("issueName", issueName);
			httpServletRequest.setAttribute("campStartDt", campStartDt);
			httpServletRequest.setAttribute("campEndDt", campEndDt);
			httpServletRequest.setAttribute("mgrAppId", mgrAppId);		// 20101010, 현재 적용안됨.
			httpServletRequest.setAttribute("mgrAppCtn", mgrAppCtn);
			
			//20190408
			httpServletRequest.setAttribute("ccssToken", ccssToken);
			httpServletRequest.setAttribute("appVersion", appVersion);
			httpServletRequest.setAttribute("carOem", carOem);
			httpServletRequest.setAttribute("devModel", devModel);
			httpServletRequest.setAttribute("nwType", nwType);
			httpServletRequest.setAttribute("posX", posX);
			httpServletRequest.setAttribute("posY", posY);
			httpServletRequest.setAttribute("userCtn", userCtn);
			httpServletRequest.setAttribute("uuid", uuid);
			
			httpServletRequest.setAttribute("LGD_BUYER", LGD_BUYER);
			httpServletRequest.setAttribute("LGD_BUYER_PHONENUM", LGD_BUYER_PHONENUM);
			httpServletRequest.setAttribute("LGD_BUYEREMAIL", LGD_BUYEREMAIL);
			httpServletRequest.setAttribute("LGD_CUSTOM_FIRSTPAY", LGD_CUSTOM_FIRSTPAY);
			   
			model.addAttribute("memberId", memberId);
			model.addAttribute("ctn", ctn);
			model.addAttribute("giftName", giftName);
			model.addAttribute("dataSize", dataSize);
			model.addAttribute("sellAmount", sellAmount);
			model.addAttribute("issueNo", issueNo);
			model.addAttribute("carNumber", carNumber);
			model.addAttribute("giftKub", giftKub);
			model.addAttribute("issueName", issueName);
			model.addAttribute("campStartDt", campStartDt);
			model.addAttribute("campEndDt", campEndDt);
			model.addAttribute("mgrAppId", mgrAppId);
			model.addAttribute("mgrAppCtn", mgrAppCtn);
			model.addAttribute("LGD_BUYER", LGD_BUYER);
			model.addAttribute("LGD_BUYER_PHONENUM", LGD_BUYER_PHONENUM);
			model.addAttribute("LGD_BUYEREMAIL", LGD_BUYEREMAIL);
			model.addAttribute("LGD_CUSTOM_FIRSTPAY", LGD_CUSTOM_FIRSTPAY);
			model.addAttribute("canBuyGift", canBuyGift);
			
			model.addAttribute("ccssToken", ccssToken);
			model.addAttribute("appVersion", appVersion);
			model.addAttribute("carOem", carOem);
			model.addAttribute("devModel", devModel);
			model.addAttribute("nwType", nwType);
			model.addAttribute("posX", posX);
			model.addAttribute("posY", posY);
			model.addAttribute("userCtn", userCtn);
			model.addAttribute("uuid", uuid);
			
			return "datagift/payment";
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}

		return "datagift/error";
	}

	@Override
	public String getCallback(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
			Model model) throws ServletException, IOException 
	{
		logger.debug(String.format("결재요청 결과 수심됨 - forward to returnurl.jsp"));

		
		return "datagift/returnurl";
	}
	

	
	/*******************************************************************************************************
	 * 결과 화면(result.jsp) 요청 처리
	   - request [get] parameter : 	carNumber, canBuyGift, ctn, giftName, dataSize, sellAmount, issueNo, 
	  								LGD_BUYER_PHONENUM, CST_PLATFORM, CST_MID, LGD_OID, LGD_BUYER, 
									LGD_PRODUCTINFO, LGD_AMOUNT, LGD_BUYEREMAIL, LGD_CUSTOM_SKIN, 
									LGD_CUSTOM_PROCESSTYPE, LGD_TIMESTAMP, LGD_HASHDATA, LGD_RETURNURL, 
									LGD_VERSION, LGD_CUSTOM_FIRSTPAY, LGD_PCVIEWYN, LGD_CUSTOM_SWITCHINGTYPE, 
									LGD_MPILOTTEAPPCARDWAPURL, LGD_KVPMISPWAPURL, LGD_KVPMISPCANCELURL, 
									LGD_MTRANSFERWAPURL, LGD_MTRANSFERCANCELURL, LGD_KVPMISPAUTOAPPYN, 
									LGD_MTRANSFERAUTOAPPYN, LGD_RESPCODE, LGD_PAYKEY, LGD_RESPMSG

	   - model: 1) 결재취소:   구매하기 화면 전환
	   						carNumber, giftName, dataSize, sellAmount, issueNo, 
	   						LGD_BUYER, LGD_BUYER_PHONENUM, LGD_BUYEREMAIL, LGD_CUSTOM_FIRSTPAY, canBuyGift
	   			2) 결재성공:	상품권 메인 화면 전환
	   						carNumber
	   			3) 장애 관리자 문의: 상품권 메인 화면 전환
	   						carNumber
	 *******************************************************************************************************/		
	@SuppressWarnings({ "unused", "null" })
	@Override
	public String processLastPayment(HttpServletRequest request, HttpServletResponse response,
			Model model) throws IOException {
		logger.debug("DataGiftServiceImpl_processLastPayment start");

		String resultMsg = null;
		String sessionCheck = request.getParameter("sessionCheck");		
		String devType = request.getParameter("devType");

		/* 구매하기 화면 설정 데이타 */
		String memberId = request.getParameter("memberId");
		String carNumber = request.getParameter("carNumber");
		String canBuyGift = (String)request.getParameter("canBuyGift");
		String ctn = (String)request.getParameter("ctn");
		String issueNo = (String)request.getParameter("issueNo");
		String giftName = request.getParameter("giftName");
		String dataSize = request.getParameter("dataSize");
		String sellAmount = request.getParameter("sellAmount");
		String giftKub = request.getParameter("giftKub");					// 현재 적용되지 않음! 상품권 구분 {N-일반, Y-청소년}, ex) "N", 
		String issueName = request.getParameter("issueName");			// 현재 적용되지 않음! 발행명, ex) "OOOO 이벤트"
		String campStartDt = request.getParameter("campStartDt");	// 현재 적용되지 않음! 캠페인 시작일자, ex) "2014-01-01"
		String campEndDt = request.getParameter("campEndDt");		// 현재 적용되지 않음! 켐페인 만료일자, ex) "2014-01-15"
		String mgrAppId = request.getParameter("mgrAppId");				// 현재 적용되지 않음! 매니저앱ID.
		String mgrAppCtn = request.getParameter("mgrAppCtn");
		
		//20190408
		String ccssToken = request.getParameter("ccssToken");
		String appVersion = request.getParameter("appVersion");
		String carOem = request.getParameter("carOem");
		String devModel = request.getParameter("devModel");
		String nwType = request.getParameter("nwType");
		String posX = request.getParameter("posX");
		String posY = request.getParameter("posY");
		String userCtn = request.getParameter("userCtn");
		String uuid = request.getParameter("uuid");
		String logTime = (new SimpleDateFormat("yyyyMMddHHmmss")).format(new Date());
		logger.debug(String.format("userCtn=%s, uuid=%s, ccssToken=%s, appVersion=%s, carOem=%s, devModel=%s, posX=%s, posY=%s, nwType=%s, dataSize=%s, logTime=%s",
				userCtn, uuid, ccssToken, appVersion, carOem, devModel, posX, posY, nwType, dataSize, logTime));
		
		String CST_PLATFORM = request.getParameter("CST_PLATFORM");				// test or service
		String CST_MID = request.getParameter("CST_MID");						// 상점ID
	    String LGD_MID = ("test".equals(CST_PLATFORM.trim())?"t":"")+CST_MID;	// 상점ID: test인 경우에 테스트용 상점ID를 만들기 위해서 사용함.
		String LGD_BUYER = request.getParameter("LGD_BUYER");					// 구매자 이름
		String LGD_BUYER_PHONENUM = request.getParameter("LGD_BUYER_PHONENUM");	// 구매자 휴대폰 번호
		String LGD_BUYEREMAIL = request.getParameter("LGD_BUYEREMAIL");			// 구매자 이메일
		String LGD_CUSTOM_FIRSTPAY = request.getParameter("LGD_CUSTOM_FIRSTPAY");	// 초기결재수단
		String LGD_PRODUCTINFO = request.getParameter("LGD_PRODUCTINFO");		// 상품권 이름
		String LGD_OID = request.getParameter("LGD_OID");						// CCSS 주문번호
		String LGD_TIMESTAMP = request.getParameter("LGD_TIMESTAMP");			// 요청시간, 숫자형식으로만 전달, 예) 20090226110637
		
		String LGD_AMOUNT = request.getParameter("LGD_AMOUNT");									// sellAmount 값.
		String LGD_CUSTOM_SKIN = request.getParameter("LGD_CUSTOM_SKIN");						//  "SMART_XPAY2" 상점정의 결제창 스킨
		String LGD_CUSTOM_PROCESSTYPE = request.getParameter("LGD_CUSTOM_PROCESSTYPE");			// "TWOTR" 고정값 사용.
		String LGD_HASHDATA = request.getParameter("LGD_HASHDATA");
		String LGD_RETURNURL = request.getParameter("LGD_RETURNURL");
		String LGD_VERSION = request.getParameter("LGD_VERSION");								// "JSP_Non-ActiveX_SmartXPay" 
		String LGD_PCVIEWYN = request.getParameter("LGD_PCVIEWYN");								// 페이나우 휴대폰번호 입력 화면 사용 여부(유심칩이 없는 단말기에서 입력-->유심칩이 있는 휴대폰에서 실제 결제)
		String LGD_CUSTOM_SWITCHINGTYPE = request.getParameter("LGD_CUSTOM_SWITCHINGTYPE");		// "SUBMIT", 신용카드 카드사 인증 페이지 연동 방식
		String LGD_MPILOTTEAPPCARDWAPURL = request.getParameter("LGD_MPILOTTEAPPCARDWAPURL");	// "", ISP 카드결제 연동을 위한 파라미터(필수), iOS 연동시 필수
		String LGD_KVPMISPWAPURL = request.getParameter("LGD_KVPMISPWAPURL");					// "", ISP 카드결제 연동을 위한 파라미터(필수)
		String LGD_KVPMISPCANCELURL = request.getParameter("LGD_KVPMISPCANCELURL");				// "", ISP 카드결제 연동을 위한 파라미터(필수)
		String LGD_MTRANSFERWAPURL = request.getParameter("LGD_MTRANSFERWAPURL");				// "", 계좌이체 연동을 위한 파라미터(필수)
		String LGD_MTRANSFERCANCELURL = request.getParameter("LGD_MTRANSFERCANCELURL");			// "", 계좌이체 연동을 위한 파라미터(필수)
		String LGD_KVPMISPAUTOAPPYN = request.getParameter("LGD_KVPMISPAUTOAPPYN");				// "A", 신용카드 결제 사용시 필수 - Web to Web: 안드로이드: A (디폴트), iOS: N
		String LGD_MTRANSFERAUTOAPPYN = request.getParameter("LGD_MTRANSFERAUTOAPPYN");			// "A", 계좌이체 결제 사용시 필수 - Web to Web: 안드로이드: A (디폴트), iOS: N
		
		
		/* 인증결제 - 응답 */
		String LGD_PAYKEY = request.getParameter("LGD_PAYKEY");		// 최종 결제요청에 대한 완료 후, rollback이 필요한 경우에 사용함.
		String LGD_RESPCODE = request.getParameter("LGD_RESPCODE");
		String LGD_RESPMSG = request.getParameter("LGD_RESPMSG");
		

		/* 최종결제요청 - 요청 */
		String LGD_TXNAME = PaymentVO.TX_NAME_REQUEST;
		
		/* 최종결제요청 - 응답 */
		String LGD_PAYTYPE = null;		// 결제수단코드: ex) 신용카드(SC0010)
		String LGD_PAYDATE = null;
		String LGD_TID = null;					// 거래번호
		String LGD_FINANCECODE = null;	// 결제기관코드
		String LGD_FINANCENAME = null;	// 결제기관코명
		String LGD_ESCROWYN = null;		// 최종 에스크로 적용 여부
		String LGD_TRANSAMOUNT = null; 	// 환율적용금액(USD결제시 환율적용 원화금액)
		String LGD_EXCHANGERATE = null;	// 적용환율
		String LGD_CARDNUM;				// 신용카드번호(일반 가맹점은 *처리됨)
		String LGD_CARDINSTALLMONTH;	// 신용카드할부개월
		String LGD_CARDNOINTYN;			// 신용카드무이자여부 1: 무이자,   0 : 일반
		String LGD_FINANCEAUTHNUM;		// 결제기관승인번호


		/* PaymentVO attributes */
		String lgdOid = LGD_OID;
		String payType = PaymentVO.PAY_TYPE_REQUEST;  // 결재요청 종류: "P"(결제요청), "C"(결제취소)
		String cstPlatform = CST_PLATFORM;
		String lgdMid = LGD_MID;
		String lgdAmount = LGD_AMOUNT;
		String lgdBuyer = LGD_BUYER;
		String lgdPrdtInfo = LGD_PRODUCTINFO;
		String lgdTimestamp = LGD_TIMESTAMP;
		String lgdReturnurl = LGD_RETURNURL;
		String buyerAddress = null;
		String buyerPhone = LGD_BUYER_PHONENUM;
		String buyerMail = LGD_BUYEREMAIL;
		String lgdCustomFirstpay = LGD_CUSTOM_FIRSTPAY;
		String lgdCustomProcessType = LGD_CUSTOM_PROCESSTYPE;			// "SMART_XPAY2" 상점정의 결제창 스킨
		String lgdLanguage = request.getParameter("LGD_LANGUAGE");		// 해외카드결재시 필요함. 국내카드는 불필요.
		String lgdKvpmIspAutoAppYn = LGD_KVPMISPAUTOAPPYN;
		String lgdKvpmIspWapUrl = LGD_KVPMISPWAPURL;
		String lgdKvpmIspCancelUrl = LGD_KVPMISPCANCELURL;
		String lgdMpiLotteAppCardWapUrl = LGD_MPILOTTEAPPCARDWAPURL;
		String lgdPayKey = LGD_PAYKEY;		// 인증요청 응답값으로, 최종결제요청에 사용함.
		String lgdTxName = LGD_TXNAME;		// 최종결제요청 요청  "PaymentByKey", 취소시, “Cancel” 값 고정
		String lgdTid = null;			// 최종결제요청 응답수신값, 거래번호
		String lgdPayType = null;	// 최종결제요청 응답수신값, 결제수단코드
		String lgdPayDate = null;	// 최종결제요청 응답수신값, 결제처리일시
		String lgdFinanceCode = null;		// 최종결제요청 응답수신값
		String lgdFinanceName = null;		// 최종결제요청 응답수신값
		String lgdCardNum = null;			// 최종결제요청 응답수신값, 신용카드번호(일반 가맹점은 *처리됨)
		String lgdCardInstallMonth = null;	// 최종결제요청 응답수신값, 신용카드할부개월
		String lgdCardNoIntYn = null;		// 최종결제요청 응답수신값, 신용카드무이자여부 1: 무이자,   0 : 일반
		String lgdFinanceAuthNum = null;	// 최종결제요청 응답수신값, 결제기관승인번호
		String lgdRespCode = null;			// 
		String lgdRespMsg = null;			// 
		
		/* DataGiftVO attributes */
		String giftNo = null;		// 상품권 고유번호, 발핻등록 후 발급됨.
		String issueRegKup = null;	// I-발행, R-발행등록, (추가) P-결제
		String giftRegDt = null;	// 상품권 발행/등록 일시
		
		/* RegisterVO attributes */
		String result = null;
		String resultCode = null;
		String resultMessage = null;
		
		
		/* DB작업: 결제정보와 상품권 정보를 초기화 함(여기서는 결재인증요청 결과를 기준으로 수행)
		 * 
		 * 1) 결제요청 후, 업데이트 항목: 
		 * 	  - PaymentVO[lgdTid, lgdPayType, lgdPayDate, lgdFinanceCode, lgdFinanceName, lgdCardNum, lgdCardInstallMonth
		 *                , lgdCardNoIntYn, lgdFinanceAuthNum, lgdRespCode, lgdRespMsg]
		 *    - DataGiftVO[lgdTid, lgdPayDate, issueRegKup["P"]]
		 *    - RegisterVO[lgdTid]
		 * 2) 발행등록 후, 업데이트 항목: 
		 * 	  - DataGiftVO[issueRegKup["R"], giftNo, giftRegDt]
		 * 	  - RegisterVO[giftNo, result, resultCode, resultMessage]
		 */
		
		
    	PaymentVO paymentVo = new PaymentVO(lgdOid, issueNo, payType, memberId, cstPlatform, lgdMid, lgdAmount, lgdBuyer, lgdPrdtInfo, lgdTimestamp, lgdReturnurl, buyerAddress, 
    			buyerPhone, buyerMail, lgdCustomFirstpay, lgdCustomProcessType, lgdLanguage, lgdKvpmIspAutoAppYn, lgdKvpmIspWapUrl, 
    			lgdKvpmIspCancelUrl, lgdMpiLotteAppCardWapUrl, lgdPayKey, lgdTxName, lgdTid, lgdPayType, lgdPayDate, lgdFinanceCode, 
    			lgdFinanceName, lgdCardNum, lgdCardInstallMonth, lgdCardNoIntYn, lgdFinanceAuthNum, lgdRespCode, lgdRespMsg, 
    			"Y", null, "SYSTEM", null, "SYSTEM");
    	
    	DataGiftVO datagiftVo = new DataGiftVO(lgdOid, lgdMid, lgdTid, lgdPayDate, issueNo, giftName, dataSize, giftKub, issueRegKup, sellAmount, issueName, 
		campStartDt, campEndDt, ctn, giftNo, giftRegDt, mgrAppId, mgrAppCtn, memberId, carNumber,  "Y", null, "SYSTEM", null, "SYSTEM");
    	
    	
    	RegisterVO registerVo = new RegisterVO(lgdOid, lgdTid, issueNo, ctn, result, resultCode, resultMessage, giftNo, "Y", null, "SYSTEM", null, "SYSTEM");
    	
		int respCnt = 0;
		boolean isDBOK = false;
	    
		
		/* 결제 취소를  TEST위해서 seesionCheck null 처리! ---------------- TEST start -------------	*/
		//sessionCheck = null;
		//pgs														
		/* 결제 취소를  TEST위해서 seesionCheck null 처리! ---------------- TEST end ---------------	*/
		
		// returnurl.jsp에서 session 체크 결과를 처리한다. 유효하지 않으면 여기서 에러 처리.		
		if(sessionCheck == null || !"valid".equals(sessionCheck.trim()))
		{
			/*********************************
			 * 인증단계에서 취소를 선택한 경우에 여기서 처리된다.
			 *********************************/
			
			logger.error(String.format("최종결제요청 처리: 인증결제 응답처리(returnurl.jsp)에서 session데이터가 없거나, 인증단계에서 취소 처리로  결제 취소 됨!"));
			resultMsg = new String("결재가 취소 되었습니다.");
			model.addAttribute("RESULT_TYPE", "100");
			model.addAttribute("RESULT_MESSAGE", resultMsg);
			this.setPaymentAgain(model, carNumber, giftName, dataSize, sellAmount, issueNo
					, giftKub, issueName, campStartDt, campEndDt
					, LGD_BUYER, LGD_BUYER_PHONENUM, LGD_BUYEREMAIL, LGD_CUSTOM_FIRSTPAY, canBuyGift
					, ccssToken, appVersion, carOem, devModel, nwType, posX, posY, userCtn, uuid);
			
			/////////////로그서버/////////////////
			LogResponseJSON logResponseJSON = logAgent.sendLog(ctn, ccssToken, devType, uuid, appVersion, carOem, devModel, userCtn, "fail", "100", dataSize, nwType, posX, posY, logTime);
			logger.debug(String.format("[STEP1] sendLog Result > ctn(%s) : resultMsg(fail) resultCode(100) logResponse(%s) : ", ctn, logResponseJSON.toString()));

			return  "datagift/result";
		}
		
		// 발행등록에 사용함.
		String encrypt_IssueNo = null;
		String encode_IssueNo = null;
		
		String encrypt_ctn = null;
		String encode_ctn = null;
		
		try {
			encrypt_ctn = GiftApiAES256.encrypt(ctn, this.dataGiftEncryptionKey);
			encode_ctn = new String(Base64.encodeBase64(encrypt_ctn.getBytes()));
			encrypt_IssueNo = AES256DataGiftUtil.AESEncode(this.dataGiftEncryptionKey, issueNo);
			encode_IssueNo = new String(Base64.encodeBase64(encrypt_IssueNo.getBytes()));
			logger.debug(String.format("processLastPayment > ctn(%s) : encrypt_ctn(%s) encode_ctn(%s) : ", ctn, encrypt_ctn, encode_ctn));
			
		} catch (Exception e){
			logger.error(String.format("[관리자 확인 필요] 최종결제요청 처리: ctn encription 에러, reason=%s", e.getMessage()), e);
			
			resultMsg = new String("데이타상품권 결제에 실패했습니다. 잠시 후 다시 시도해 주세요.");
			model.addAttribute("RESULT_TYPE", "150");
			model.addAttribute("RESULT_MESSAGE", resultMsg);
			this.setPaymentAgain(model, carNumber, giftName, dataSize, sellAmount, issueNo
					, giftKub, issueName, campStartDt, campEndDt
					, LGD_BUYER, LGD_BUYER_PHONENUM, LGD_BUYEREMAIL, LGD_CUSTOM_FIRSTPAY, canBuyGift
					, ccssToken, appVersion, carOem, devModel, nwType, posX, posY, userCtn, uuid);
			
			/////////////로그서버/////////////////
			LogResponseJSON logResponseJSON = logAgent.sendLog(ctn, ccssToken, devType, uuid, appVersion, carOem, devModel, userCtn, "fail", "150", dataSize, nwType, posX, posY, logTime);
			logger.debug(String.format("[STEP2] sendLog Result > ctn(%s) : resultMsg(fail) resultCode(150) logResponse(%s) : ", ctn, logResponseJSON.toString()));
			
			return  "datagift/result";		
		}
		
		
		try{
		    /*
		     * [최종결제요청 페이지(STEP2-2)]
			 *
			 * 매뉴얼 "5.1. Smart XPay 결제 요청 페이지 개발"의 "단계 5. 최종 결제 요청 및 요청 결과 처리" 참조
		     *
		     * LG유플러스으로 부터 내려받은 LGD_PAYKEY(인증Key)를 가지고 최종 결제요청.(파라미터 전달시 POST를 사용하세요)
		     */
	
			/* ※ 중요
			* 환경설정 파일의 경우 반드시 외부에서 접근이 가능한 경로에 두시면 안됩니다.
			* 해당 환경파일이 외부에 노출이 되는 경우 해킹의 위험이 존재하므로 반드시 외부에서 접근이 불가능한 경로에 두시기 바랍니다. 
			* 예) [Window 계열] C:\inetpub\wwwroot\lgdacom ==> 절대불가(웹 디렉토리)
			*/
			Resource resource = null;
			File file = null;
			String configPath = null;
			
			try{
			    resource = new ClassPathResource("/lgdacom");
			    file = resource.getFile();
			    configPath = file.getAbsolutePath();
			}catch(Exception e){
				logger.error(String.format("[관리자 확인 필요] XPay config loading error! reason=%s", e.getMessage()), e);
				
				lgdTid = null;
				lgdRespCode = null;
				lgdRespMsg = e.getMessage() == null ? null : e.getMessage().length() > 140 ? e.getMessage().substring(0, 140) :e.getMessage();
				
	        	paymentVo.setLgdTid(lgdTid);
	        	paymentVo.setLgdRespCode(lgdRespCode);
	        	paymentVo.setLgdRespMsg(lgdRespMsg);

	        	try {
	        		this.datagiftPaymentDao.insertPayment(paymentVo);
	        	}catch(Exception ee)
	        	{
	        		logger.error(String.format("insertPayment(xpay config fail! 저장) 오류, reason=%s", ee.getMessage()));
	        	}
		   		
				resultMsg = new String("데이타상품권 결제에 실패했습니다. 잠시 후 다시 시도해 주세요.");
				model.addAttribute("RESULT_TYPE", "150");
				model.addAttribute("RESULT_MESSAGE", resultMsg);
				this.setPaymentAgain(model, carNumber, giftName, dataSize, sellAmount, issueNo
						, giftKub, issueName, campStartDt, campEndDt
						, LGD_BUYER, LGD_BUYER_PHONENUM, LGD_BUYEREMAIL, LGD_CUSTOM_FIRSTPAY, canBuyGift
						, ccssToken, appVersion, carOem, devModel, nwType, posX, posY, userCtn, uuid);
				
				/////////////로그서버/////////////////
				LogResponseJSON logResponseJSON = logAgent.sendLog(ctn, ccssToken, devType, uuid, appVersion, carOem, devModel, userCtn, "fail", "150", dataSize, nwType, posX, posY, logTime);
				logger.debug(String.format("[STEP3] sendLog Result > ctn(%s) : resultMsg(fail) resultCode(150) logResponse(%s) : ", ctn, logResponseJSON.toString()));
				
				return  "datagift/result";
			}
		    
		    /*
		     *************************************************
		     * 1.최종결제 요청 - BEGIN
		     *  (단, 최종 금액체크를 원하시는 경우 금액체크 부분 주석을 제거 하시면 됩니다.)
		     *************************************************
		     */
		    
		    //해당 API를 사용하기 위해 WEB-INF/lib/XPayClient.jar 를 Classpath 로 등록하셔야 합니다.
			//new created because proxy does not work add park
			// (1) XpayClient의 사용을 위한 xpay 객체 생성
		    XPayClient xpay = new XPayClient();
	
			// (2) Init: XPayClient 초기화(환경설정 파일 로드) 
			// configPath: 설정파일
			// CST_PLATFORM: - test, service 값에 따라 lgdacom.conf의 test_url(test) 또는 url(srvice) 사용
			//				- test, service 값에 따라 테스트용 또는 서비스용 아이디 생성
		   	boolean isInitOK = xpay.Init(configPath, CST_PLATFORM);
		   	
		   	if( !isInitOK ) {
		    	//API 초기화 실패 처리! DB저장 후, 결과 페이지 호출.
		   		logger.error(String.format("[관리자 확인 필요] 최종결제요청 처리(초기화 실패) : %s%s%s%s", 
		   				"결제요청을 초기화 하는데 실패하였습니다.", 
		   				" LG유플러스에서 제공한 환경파일이 정상적으로 설치 되었는지 확인하시기 바랍니다. ", 
		   				"mall.conf에는 Mert ID = Mert Key 가 반드시 등록되어 있어야 합니다.",
		   				" 문의전화 LG유플러스 1544-7772"));
		        
		   		
		   		/*******************************************
		   		 * 결제수행 결과(초기화 실패)를 DB에 insert한다.
		   		 *******************************************/
		   		
				lgdTid = null;
				lgdRespCode = null;
				lgdRespMsg = "xpay.Init() fail!";
				
	        	paymentVo.setLgdTid(lgdTid);
	        	paymentVo.setLgdRespCode(lgdRespCode);
	        	paymentVo.setLgdRespMsg(lgdRespMsg);

	        	try {
	        		this.datagiftPaymentDao.insertPayment(paymentVo);
	        	}catch(Exception e)
	        	{
	        		logger.error(String.format("insertPayment(xpay.Init fail! 저장) 오류, reason=%s", e.getMessage()));
	        	}
		   		
				resultMsg = new String("데이타상품권 결제에 실패했습니다. 잠시 후 다시 시도해 주세요.");
				model.addAttribute("RESULT_TYPE", "150");
				model.addAttribute("RESULT_MESSAGE", resultMsg);
				this.setPaymentAgain(model, carNumber, giftName, dataSize, sellAmount, issueNo
						, giftKub, issueName, campStartDt, campEndDt
						, LGD_BUYER, LGD_BUYER_PHONENUM, LGD_BUYEREMAIL, LGD_CUSTOM_FIRSTPAY, canBuyGift
						, ccssToken, appVersion, carOem, devModel, nwType, posX, posY, userCtn, uuid);
				
				/////////////로그서버/////////////////
				LogResponseJSON logResponseJSON = logAgent.sendLog(ctn, ccssToken, devType, uuid, appVersion, carOem, devModel, userCtn, "fail", "150", dataSize, nwType, posX, posY, logTime);
				logger.debug(String.format("[STEP4] sendLog Result > ctn(%s) : resultMsg(fail) resultCode(150) logResponse(%s) : ", ctn, logResponseJSON.toString()));
				
				return  "datagift/result";		        
		   	}else{
		   		//API 초기화 성공!
				try{
					// (3) Init_TX: 메모리에 mall.conf, lgdacom.conf 할당 및 트랜잭션의 고유한 키 TXID 생성
					xpay.Init_TX(LGD_MID);
					xpay.Set("LGD_TXNAME", LGD_TXNAME);
					xpay.Set("LGD_PAYKEY", LGD_PAYKEY);
	
				}catch(Exception e) {
					// LG U+ API 사용 불가, 설정파일 확인 등 필요(예외처리)
					logger.error(String.format("[관리자 확인 필요] 최종결제요청 처리(초기화 실패): LG유플러스 제공 API를 사용할 수 없습니다. 환경파일 설정을 확인해 주시기 바랍니다. %s", e.getMessage()));
					
			   		
			   		/*******************************************
			   		 * 결제수행 결과(초기화 실패)를 DB에 insert한다.
			   		 *******************************************/
			   		
					lgdTid = null;
					lgdRespCode = null;
					lgdRespMsg = "xpay.Init_TX() fail!";
					
		        	paymentVo.setLgdTid(lgdTid);
		        	paymentVo.setLgdRespCode(lgdRespCode);
		        	paymentVo.setLgdRespMsg(lgdRespMsg);

		        	try {
		        		this.datagiftPaymentDao.insertPayment(paymentVo);
		        	}catch(Exception ee)
		        	{
		        		logger.error(String.format("insertPayment(xpay.Init_TX fail! 저장) 오류, reason=%s", ee.getMessage()));
		        	}
					
					resultMsg = new String("데이타상품권 결제에 실패했습니다. 잠시 후 다시 시도해 주세요.");
					model.addAttribute("RESULT_TYPE", "250");
					model.addAttribute("RESULT_MESSAGE", resultMsg);
					this.setPaymentAgain(model, carNumber, giftName, dataSize, sellAmount, issueNo
							, giftKub, issueName, campStartDt, campEndDt
							, LGD_BUYER, LGD_BUYER_PHONENUM, LGD_BUYEREMAIL, LGD_CUSTOM_FIRSTPAY, canBuyGift
							, ccssToken, appVersion, carOem, devModel, nwType, posX, posY, userCtn, uuid);

					/////////////로그서버/////////////////
					LogResponseJSON logResponseJSON = logAgent.sendLog(ctn, ccssToken, devType, uuid, appVersion, carOem, devModel, userCtn, "fail", "250", dataSize, nwType, posX, posY, logTime);
					logger.debug(String.format("[STEP5] sendLog Result > ctn(%s) : resultMsg(fail) resultCode(250) logResponse(%s) : ", ctn, logResponseJSON.toString()));

					return  "datagift/result";					
				}
		   	}
			/*
			 *************************************************
			 * 1.최종결제 요청(수정하지 마세요) - END
			 *************************************************
			 */
	
		    /*
		     * 2. 최종결제 요청 결과처리
		     *
		     * 최종 결제요청 결과 리턴 파라미터는 연동메뉴얼을 참고하시기 바랍니다.
		     */
			// (4) TX: lgdacom.conf에 설정된 URL로 소켓 통신하여 최종취소요청, 결과값으로 true, false 리턴
			if ( xpay.TX() ) 
			{  // 결제처리 완료 수행(성공, 실패 포함)
				//1)결제결과 처리(성공,실패 결과 처리를 하시기 바랍니다.)
				
				// ----------- 최종결제 요청 응답 데이타 로그 출력 -----------
				logger.info(String.format("최종결제요청 처리(Tx완료): 결제요청이 완료되었습니다. \n - TX 결제요청 통신 응답코드 = %s, \n -TX 결제요청 통신 응답메시지 = %s " + 
				                          "\n - 거래번호 : %s \n - 상점아이디 : %s \n - 상점주문번호 : %s " + 
				                          "\n - 결제금액 : %s \n - 결과코드 : %s \n - 결과메세지 : %s"
						                   , xpay.m_szResCode, xpay.m_szResMsg																	//통신 응답코드("0000" 일 때 통신 성공)
						                   , xpay.Response("LGD_TID",0), xpay.Response("LGD_MID",0), xpay.Response("LGD_OID",0)
						                   , xpay.Response("LGD_AMOUNT",0), xpay.Response("LGD_RESPCODE",0), xpay.Response("LGD_RESPMSG",0))); 	//LGD_RESPCODE 결제요청 응답코드
				
				LGD_TID = xpay.Response("LGD_TID",0);
				LGD_MID = xpay.Response("LGD_MID",0);
				LGD_OID = xpay.Response("LGD_OID",0);
				LGD_AMOUNT = xpay.Response("LGD_AMOUNT",0);
				LGD_RESPCODE = xpay.Response("LGD_RESPCODE",0);
				LGD_RESPMSG = xpay.Response("LGD_RESPMSG",0);
				
				lgdTid = LGD_TID;
				lgdRespCode = LGD_RESPCODE;
				lgdRespMsg = LGD_RESPMSG;
				
				Map<String, String> respParamMap = new HashMap<String, String>();
				for (int i = 0; i < xpay.ResponseNameCount(); i++)
				{
					logger.info(xpay.ResponseName(i) + " = ");
					
					for (int j = 0; j < xpay.ResponseCount(); j++)
					{
						String key = xpay.ResponseName(i);
						String value = xpay.Response(key, j);
						logger.info("\t" + value);
						respParamMap.put(key, value);
					}
		        }
				// ----------------------------------------------
				
				
		         
				// (5)DB에 요청 결과 처리 수행.
		        if( "0000".equals( xpay.m_szResCode )) 
		        {	// 최종결제 요청 성공.
					// 통신상의 문제가 없을시
					// 최종결제요청 결과 성공 DB처리(LGD_RESPCODE 값에 따라 결제가 성공인지, 실패인지 DB처리)
		        	logger.info("최종결제요청 처리(Tx완료 >> 결제성공 >> 결제DB처리 ): 최종결제요청 결과 성공 DB처리 수행.....");

		        	// 여기서, 결재수행결과(성공)에 대한 DB 작업을 수행한다.
		        	/************************************************************************************
					 1) (최종)결제요청 결과(성공)를 DB 저장(결제이력 테이블): LGD_MID, LGD_OID, LGD_AMOUNT, LGD_TID, ….  
				        - 결재정보: LGD_OID(주문번호), LGD_TID(거래번호), gift_pay_dt
					 2) 결재된 테이타상품권 정보를 DB저장(상품권 테이블):   // insert 
				        - 상품권 정보: issue_no, gift_name, data_size, issue_reg_kep[P], sell_amount, issue_name, camp_start_dt, camp_end_dt
		        	 ************************************************************************************/
		        	
		        	lgdPayType = respParamMap.get("LGD_PAYTYPE");	// 결재수단 코드: 신용카드
		        	lgdPayDate = respParamMap.get("LGD_PAYDATE");
		        	lgdFinanceCode = respParamMap.get("LGD_FINANCECODE");
		        	lgdFinanceName = respParamMap.get("LGD_FINANCENAME");
		        	lgdCardNum = respParamMap.get("LGD_CARDNUM");
		        	lgdCardInstallMonth = respParamMap.get("LGD_CARDINSTALLMONTH");
		        	lgdCardNoIntYn = respParamMap.get("LGD_CARDNOINTEREST_YN");
		        	lgdFinanceAuthNum = respParamMap.get("LGD_FINANCEAUTHNUM");

		        	paymentVo.setLgdTid(lgdTid);
		        	paymentVo.setLgdRespCode(lgdRespCode);
		        	paymentVo.setLgdRespMsg(lgdRespMsg);
		        		
		        	paymentVo.setLgdPayType(lgdPayType);
		        	paymentVo.setLgdPayDate(lgdPayDate);
		        	paymentVo.setLgdFinanceCode(lgdFinanceCode);
		        	paymentVo.setLgdFinanceName(lgdFinanceName);
		        	paymentVo.setLgdCardNum(lgdCardNum);
		        	paymentVo.setLgdCardInstallMonth(lgdCardInstallMonth);
		        	paymentVo.setLgdCardNoIntYn(lgdCardNoIntYn);
		        	paymentVo.setLgdFinanceAuthNum(lgdFinanceAuthNum);
		        	
	        		datagiftVo.setLgdTid(lgdTid);
	        		datagiftVo.setLgdPayDate(lgdPayDate);
	        		datagiftVo.setIssueRegKup(DataGiftVO.DATA_GIFT_STATE_PAYMENT);
		        	
	        		// 1) 결제정보 insert
		        	try {
			        	this.datagiftPaymentDao.insertPayment(paymentVo);
		        	}catch(Exception e) {
		        		logger.error(String.format("insertPayment(결제 저장) 에러, reason=%s", e.getMessage()), e);
		        	}

	        		// 2) 상품권 정보(상태: 결재완료) insert
	        		try {
	        			respCnt = this.datagiftDao.insertDataGift(datagiftVo);
		        		isDBOK = (respCnt > 0 ) ? true : false;
	        		}catch(Exception e) {
		        		logger.error(String.format("insertDataGift(데이타상품권  저장) 에러, reason=%s", e.getMessage()), e);
		        		isDBOK = false;	        		
		        	}
		        	
					/*
					* 최종결제요청 결과를 DB처리합니다. (결제성공 또는 실패 모두 DB처리 가능)
					* 상점내 DB에 어떠한 이유로 처리를 하지 못한경우 false로 변경해 주세요.
					* 만약  DB처리 실패시 Rollback 처리, isDBOK 파라미터를 false 로 변경
					*/
					if( !isDBOK ) { // 최종결제요청(성공)에 대한 DB처리 실패.
						logger.error("최종결제요청 처리(Tx완료 >> 결제성공 >> 결제DB처리 실패): 최종결제요청 결과 성공에 대한 DB처리 실패!");
						
						/////////////로그서버/////////////////
						// LG U+ 결정으로 시스템로그는 rollback 수행 후 성공/실패가 판가름 난 이후 저장하도록 함.
						// by Kenshin(2019.04.26)
						//LogResponseJSON logResponseJSON = logAgent.sendLog(ctn, ccssToken, devType, uuid, appVersion, carOem, devModel, userCtn, "fail", "100", dataSize, nwType, posX, posY, logTime);
						//logger.debug(String.format("[STEP6] sendLog Result > ctn(%s) : resultMsg(fail) resultCode(100) logResponse(%s) : ", ctn, logResponseJSON.toString()));
						
						// DB작업 실패로, 결제 취소 수행함.
						return this.rollBackPayment(xpay, model, carNumber, devType, giftName, dataSize, sellAmount, issueNo
											, giftKub, issueName, campStartDt, campEndDt
											, LGD_BUYER, LGD_BUYER_PHONENUM, LGD_BUYEREMAIL, LGD_CUSTOM_FIRSTPAY, canBuyGift, paymentVo
											, ccssToken, appVersion, carOem, devModel, nwType, posX, posY, userCtn, uuid, ctn, logTime);
					}else{	// 최종결제요청(성공)에 대한 DB처리 성공
						logger.info("최종결제요청 처리(Tx완료 >> 결제성공 >> 결제DB처리 성공): 최종결제요청 결과 성공에 대한 DB처리 성공!");
						/***************************************************
						 *					  상품권 발행등록 수행  				   *
						 ***************************************************/
						logger.info("최종결제요청 처리(Tx완료 >> 결제성공 >> 결제DB처리 성공 >> 발행등록처리): 최종결제요청 결과 성공에 발행등록 수행 .....");
						
						DataGiftCtnCheck dataGiftCtnCheck = null;
						DataGiftReg dataGiftReg = null;
						
						// 1. 발행등록 - ctn 체크. ---------------------------
						try{
							dataGiftCtnCheck = dataGiftManager.getDataGiftCtnCheck(encode_ctn);
							result = dataGiftCtnCheck.getResult();
							resultCode = dataGiftCtnCheck.getResultCode();
							resultMessage = dataGiftCtnCheck.getResultMessage();
						}catch(Exception e){
							logger.error(String.format("최종결제요청 처리(Tx완료 >> 결제성공 >> 결제DB처리 성공 >> 발행등록처리): failed to gataGiftCtnCheck, reason=%s"
									, e.getMessage()), e);
							result = "fail";
							resultCode = null;
							resultMessage = e.getMessage();
						}
						
						if (dataGiftCtnCheck== null || !DataGiftManager.SUCCESS.equals(dataGiftCtnCheck.getResultCode())) 
						{
							logger.error(String.format("최종결제요청 처리(Tx완료 >> 결제성공 >> 결제DB처리 성공 >> 발행등록처리 실패): failed to gataGiftCtnCheck - %s, %s",
									StringUtils.defaultIfEmpty(dataGiftCtnCheck.getResultCode(), ""),
									StringUtils.defaultIfEmpty(dataGiftCtnCheck.getResultMessage(), "")));
							
							logger.error(String.format("최종결제요청 처리(Tx완료 >> 결제성공 >> 결제DB처리 성공 >> 발행등록처리 실패 >> 발행등록실패 DB처리): 발행등록실패 DB처리 ....."));
							
							/*************************************
							 * 발행등록 에러 결과를 DB 저장(발행등록 이력테이블):  
							 *************************************/
							// RegisterVO: lgdTid, 결재 결과 업데이트!
							registerVo.setLgdTid(lgdTid);
					    	registerVo.setGiftNo(null);
					    	registerVo.setResult(result);
					    	registerVo.setResultCode(resultCode);
					    	registerVo.setResultMessage(resultMessage == null ? null : 
					    				resultMessage.length() > 200 ? resultMessage.substring(0, 200) : resultMessage);
					    	
					    	try {
					    		this.datagiftRegDao.insertRegister(registerVo);
					    	}catch(Exception e)
					    	{
					    		logger.error(String.format("insertRegister(발행등록 실패 저장) 에러, reason=%s", e.getMessage()));
					    	}
							
							logger.error(String.format("최종결제요청 처리(Tx완료 >> 결제성공 >> 결제DB처리 성공 >> 발행등록처리 실패 >> 발행등록실패 DB처리 >> 결재처리rollback수행): 발행등록실패에 대한 결재 rollback 수행 ....."));
							
							/////////////로그서버/////////////////
							// this.rollBackPayment에서 서버시로그 저장하도록 변경
							//LogResponseJSON logResponseJSON = logAgent.sendLog(ctn, ccssToken, devType, uuid, appVersion, carOem, devModel, userCtn, "fail", resultCode, dataSize, nwType, posX, posY, logTime);
							//logger.debug(String.format("[STEP7] sendLog Result > ctn(%s) : resultMsg(fail) resultCode(%s) logResponse(%s) : ", ctn, resultCode, logResponseJSON.toString()));
							
							// 발행등록 실패, 결제 취소 수행함.
							return this.rollBackPayment(xpay, model, carNumber, devType, giftName, dataSize, sellAmount, issueNo
									, giftKub, issueName, campStartDt, campEndDt
									, LGD_BUYER, LGD_BUYER_PHONENUM, LGD_BUYEREMAIL, LGD_CUSTOM_FIRSTPAY, canBuyGift, paymentVo
									, ccssToken, appVersion, carOem, devModel, nwType, posX, posY, userCtn, uuid, ctn, logTime);
						}
						
						// 2. 발행등록 - 발행등록요청. ---------------------------
						try{
							dataGiftReg = this.dataGiftManager.getDataGiftReg(encode_IssueNo, encode_ctn);
							result = dataGiftReg.getResult();
							resultCode = dataGiftReg.getResultCode();
							resultMessage = dataGiftReg.getResultMessage();
							
							String encode_giftNo = dataGiftReg.getGiftNo();
							String decode_giftNo = new String(Base64.decodeBase64(encode_giftNo));
							String decrypt_giftNo = AES256DataGiftUtil.AESDecode(this.dataGiftEncryptionKey, decode_giftNo);
							dataGiftReg.setGiftNo(decrypt_giftNo);
							giftNo = dataGiftReg.getGiftNo();
						}catch(Exception e)
						{
							logger.error(String.format("최종결제요청 처리(Tx완료 >> 결제성공 >> 결제DB처리 성공 >> 발행등록처리): failed to gataGiftCtnCheck, reason=%s" , e.getMessage()), e);
							result = "fail";
							resultCode = dataGiftReg.getResultCode();
							resultMessage = dataGiftReg.getResultMessage();
							
						}
						
						if (dataGiftReg == null || !DataGiftManager.SUCCESS.equals(dataGiftReg.getResultCode())) 
						{
							logger.error(String.format("최종결제요청 처리(Tx완료 >> 결제성공 >> 결제DB처리 성공 >> 발행등록처리 실패): failed to getDataGiftReg - %s, %s",
									StringUtils.defaultIfEmpty(dataGiftReg.getResultCode(), ""),
									StringUtils.defaultIfEmpty(dataGiftReg.getResultMessage(), "")));
							
							logger.error(String.format("최종결제요청 처리(Tx완료 >> 결제성공 >> 결제DB처리 성공 >> 발행등록처리 실패 >> 발행등록실패 DB처리): 발행등록실패 DB처리 ....."));
							
							/*************************************
							 * 발행등록 에러 결과를 DB 저장(발행등록 이력테이블):  
							 *************************************/
							// RegisterVO: lgdTid, 결재 결과 업데이트!
							registerVo.setLgdTid(lgdTid);
					    	registerVo.setGiftNo(null);
					    	registerVo.setResult(result);
					    	registerVo.setResultCode(resultCode);
					    	registerVo.setResultMessage(resultMessage == null ? null : 
			    							resultMessage.length() > 200 ? resultMessage.substring(0, 200) : resultMessage);
					    	
					    	try {
					    		this.datagiftRegDao.insertRegister(registerVo);
					    	}catch(Exception e) {
					    		logger.error(String.format("insertRegister(발행등록 실패 저장) 에러, reason=%s", e.getMessage()));
					    	}
							
							logger.error(String.format("최종결제요청 처리(Tx완료 >> 결제성공 >> 결제DB처리 성공 >> 발행등록처리 실패 >> 발행등록실패 DB처리 >> 결재처리rollback수행): 발행등록실패에 대한 결재 rollback 수행 ....."));
							
							/////////////로그서버/////////////////
							// this.rollBackPayment에서 서버시로그 저장하도록 변경
							//LogResponseJSON logResponseJSON = logAgent.sendLog(ctn, ccssToken, devType, uuid, appVersion, carOem, devModel, userCtn, "fail", resultCode, dataSize, nwType, posX, posY, logTime);
							//logger.debug(String.format("[STEP8] sendLog Result > ctn(%s) : resultMsg(fail) resultCode(%s) logResponse(%s) : ", ctn, resultCode, logResponseJSON.toString()));

							// 발행등록 실패, 결제 취소 수행함.
							return this.rollBackPayment(xpay, model, carNumber, devType, giftName, dataSize, sellAmount, issueNo
									, giftKub, issueName, campStartDt, campEndDt
									, LGD_BUYER, LGD_BUYER_PHONENUM, LGD_BUYEREMAIL, LGD_CUSTOM_FIRSTPAY, canBuyGift, paymentVo
									, ccssToken, appVersion, carOem, devModel, nwType, posX, posY, userCtn, uuid, ctn, logTime);
						}
						
						/* ---------------- 발행등록 성공 -------------- */
						logger.info("최종결제요청 처리(Tx완료 >> 결제성공 >> 결제DB처리 성공 >> 발행등록처리 성공): 최종결제요청 결과 성공에 발행등록 성공!");
						
						logger.info("최종결제요청 처리(Tx완료 >> 결제성공 >> 결제DB처리 성공 >> 발행등록처리 성공 >> 발행등록성공 DB처리): 발행등록 성공에 대한 DB처리 .....");
						
						/***********************************************************
						 * 발행등록 정보를 DB에 반영(발행등록이력 테이블): 
						 * 발행등록된 상품권 정보를 DB에 반영(상품권 테이블):  // update
						     - 발행등록 정보:  issue_no, avn_ctn, gift_no, gift_reg_dt  
						 ***********************************************************/
						registerVo.setLgdTid(lgdTid);
				    	registerVo.setGiftNo(giftNo);
				    	registerVo.setResult(result);
				    	registerVo.setResultCode(resultCode);
				    	registerVo.setResultMessage(resultMessage == null ? null : resultMessage.length() > 200 ? resultMessage.substring(0, 200) : resultMessage);
				    	
				    	datagiftVo.setIssueRegKup(DataGiftVO.DATA_GIFT_STATE_REGIST);
				    	datagiftVo.setGiftNo(giftNo);

				    	
				    	try {
				    		this.datagiftRegDao.insertRegister(registerVo);
				    	}catch(Exception e)
				    	{
				    		logger.error(String.format("insertRegister(발행등록  저장) 에러, reason=%s", e.getMessage()));
				    	}
				    	
				    	try {
				    		this.datagiftDao.updateDataGift(datagiftVo.getLgdOid(), datagiftVo.getGiftNo(), datagiftVo.getIssueRegKup());
				    	}catch(Exception e)
				    	{
				    		logger.error(String.format("updateDataGift(상품권 발행정보 업데이트: 발행등록 성공) 에러, reason=%s", e.getMessage()));
				    	}
						
						resultMsg = new String("데이터 상품권 구매가 완료 되었습니다. 구매한 내역은 [데이타상품권 > 구매내역]에서 확인하실 수 있습니다.");
						model.addAttribute("RESULT_TYPE", "200");
						model.addAttribute("RESULT_MESSAGE", resultMsg);
						this.setMainAgain(model, carNumber, devType, ccssToken, appVersion, carOem, devModel, nwType, posX, posY, userCtn, uuid);
						
						/////////////로그서버/////////////////
						LogResponseJSON logResponseJSON = logAgent.sendLog(ctn, ccssToken, devType, uuid, appVersion, carOem, devModel, userCtn, "success", "200", dataSize, nwType, posX, posY, logTime);
						logger.debug(String.format("[STEP8] sendLog Result > ctn(%s) : resultMsg(success) resultCode(200) logResponse(%s) : ", ctn, logResponseJSON.toString()));					

						/////////////로그서버/////////////////
						//LogResponseJSON logResponseJSON = logAgent.sendLog(ctn, ccssToken, devType, uuid, appVersion, carOem, devModel, userCtn, "success", "200", dataSize, nwType, posX, posY, logTime);
						
						
						/*String url = null;
						HttpRequestWrapper reqWrapper = (HttpRequestWrapper) request;
					    byte[] body = reqWrapper.getBodyData();
					    String content = new String(body, 0, body.length);
					    Gson gson = new Gson();
					    RequestJSON reqJson = gson.fromJson(content, RequestJSON.class);						
						RestRequestData requestData = new RestRequestData(url);
						
						HttpClient httpClient = new DefaultHttpClient();
						HttpPost httpPost = new HttpPost("logdataurl"); //http://192.168.32.93:8080
						// HttpPost httpPost = new HttpPost(requestData.getUrl());
						ArrayList<NameValuePair> params = new ArrayList<NameValuePair>();
						//header
						requestData.setHeader(HTTP.CONTENT_TYPE, HeaderConst.HD_VALUE_CONTENTTYPE_JSON_UTF8);
						try {
							RequestCommonJSON log = reqJson.getlog();
							params.add(new BasicNameValuePair("seq", "1"));
							// logTime
							String logTime = (new SimpleDateFormat("yyyyMMddHHmmss")).format(new Date());
							params.add(new BasicNameValuePair("logTime", logTime));
							
							////// device:{
							String deviceType= reqJson.getlog().getDevice().getDeviceType();
							String marketType = null;
							if (deviceType.equalsIgnoreCase(CCSSConst.DEF_AVN)) {
								marketType = CCSSConst.DEF_AVN;
							}
							params.add(new BasicNameValuePair("deviceType", marketType));
							params.add(new BasicNameValuePair("appType", "MANAGER_APP"));

							String carOem = (String) reqJson.getlog().getDevice().getCarOem();
							String pushCarOem;
							if (carOem.equals("")) {
								pushCarOem = PushGwMessageVO.THINKWARE;
							} else if (carOem.equals("NS")) {
								pushCarOem = PushGwMessageVO.NS;
							} else {
								pushCarOem = PushGwMessageVO.SY;
							}
							params.add(new BasicNameValuePair("carOem", pushCarOem)); // SY
							
							params.add(new BasicNameValuePair("ctn", ctn));
							params.add(new BasicNameValuePair("userCtn", LGD_BUYER_PHONENUM));
							// serial
							String serial = String.format(UUID.randomUUID().toString());
							params.add(new BasicNameValuePair("serial", serial)); //uiccid
							params.add(new BasicNameValuePair("useType", "touch"));
							// TIME
							params.add(new BasicNameValuePair("requestTime", LGD_TIMESTAMP));//요청 (RequestTime)
							params.add(new BasicNameValuePair("responseTime", lgdPayDate));// 최종결제요청 응답수신값 (ResponseTime)
							params.add(new BasicNameValuePair("Category0", "5"));

							// Item
							String item = null;
							if (datagiftVo.getDataSize().equals("100")) {
								item = "1";
							} else if (datagiftVo.getDataSize().equals("250")) {
								item = "2";
							} else if (datagiftVo.getDataSize().equals("500")) {
								item = "3";
							} else if (datagiftVo.getDataSize().equals("1024")) {
								item = "4";
							} else if (datagiftVo.getDataSize().equals("2048")) {
								item = "5";
							} else if (datagiftVo.getDataSize().equals("5120")) {
								item = "6";
							}
							params.add(new BasicNameValuePair("item", item));
							params.add(new BasicNameValuePair("type", "NUMBER"));
							params.add(new BasicNameValuePair("value", "1")); 
							
							//네트워크 타입
							String nwType = request.getParameter("nwType"); // LTE, WIFI , 3G, 4G, 5G
							params.add(new BasicNameValuePair("nwType", nwType));
							
							// 위도경도 암호화
							MembVO membVO  = new MembVO();
							membVO = mgrAppManagementDao.selectMembTransToken(membVO);
							// transTocekn 으로 App 에서 올라온 posX,posY 복호화
							String posX = AES256LogUtil.AESDecode(membVO.getTransToken(),(String)reqJson.getParam().get(RequestJSON.PARAM_POSX));
							String posY = AES256LogUtil.AESDecode(membVO.getTransToken(),(String)reqJson.getParam().get(RequestJSON.PARAM_POSY));
							params.add(new BasicNameValuePair("posX", AES256LogUtil.AESEncode(membVO.getTransToken(), posX)));
							params.add(new BasicNameValuePair("posY", AES256LogUtil.AESEncode(membVO.getTransToken(), posY)));
							
							// svcDetailInfo:{
							String svcDetailInfo = (String) reqJson.getlog().getDevice().getDetailInfo();
							params.add(new BasicNameValuePair("svcCategory", "데이터상품권"));
							params.add(new BasicNameValuePair("svcItem", giftName+ "구매")); // 상품권 이름  ex) 데이터상품권 100MB구매
							httpPost.setEntity(new UrlEncodedFormEntity(params, "UTF-8"));
						} catch (UnsupportedEncodingException e) {
							e.printStackTrace();
						}
						try {
							HttpResponse response1 = httpClient.execute(httpPost);
							HttpEntity respEntity = response1.getEntity();
							if (respEntity != null) {
								String content1 = EntityUtils.toString(respEntity);
							}
						} catch (ClientProtocolException e) {
							e.printStackTrace();
						} catch (IOException e) {
							e.printStackTrace();
						}catch(Exception e) {
							logger.error(e.getMessage(),e); 
						}*/
						///////////// 로그서버/////////////////
						
						return "datagift/result";
					}
				}else{		// 최종 결제 요청 실패.
		         	//최종결제요청 결과 실패 DB처리
					logger.error("최종결제요청 처리(Tx완료 >> 결제실패): 최종결제요청에 실패하였습니다.");      
		        	
					
					logger.error("최종결제요청 처리(Tx완료 >> 결제실패 >> 결재실패 DB처리): 결재실패에 대한 DB처리 .....");
					/***********************************************************************************
					 * 여기서, 결재수행결과(실패)에 대한 DB 작업을 수행한다.
					 *  (최종)결제요청 결과(에러)를  DB 저장(결제이력 테이블) : LGD_MID, LGD_OID, LGD_AMOUNT, LGD_TID, ….  
					 ***********************************************************************************/
					
					lgdRespMsg = String.format("xpay.TX() fail. %s", xpay.m_szResMsg == null ? null : 
						xpay.m_szResMsg.length() > 140 ? xpay.m_szResMsg.substring(0, 140) : xpay.m_szResMsg);
					
	        		// 결제정보 insert
		        	paymentVo.setLgdTid(lgdTid);
		        	paymentVo.setLgdRespCode(lgdRespCode);
		        	paymentVo.setLgdRespMsg(lgdRespMsg);

		        	try {
		        		this.datagiftPaymentDao.insertPayment(paymentVo);
		        	}catch(Exception e) {
		        		logger.error(String.format("insertPayment(결재실패 저장) 오류, reason=%s", e.getMessage()));
		        	}
					
					resultMsg = new String("데이타상품권 결제에 실패했습니다. 잠시 후 다시 시도해 주세요.");	//  "결재가 취소 되었습니다." => "데이타상품권 발급에 실패햇습니다. 잠시 후 다시 시도해 주세요."
					model.addAttribute("RESULT_TYPE", "250");
					model.addAttribute("RESULT_MESSAGE", resultMsg);
					this.setPaymentAgain(model, carNumber, giftName, dataSize, sellAmount, issueNo
							, giftKub, issueName, campStartDt, campEndDt
							, LGD_BUYER, LGD_BUYER_PHONENUM, LGD_BUYEREMAIL, LGD_CUSTOM_FIRSTPAY, canBuyGift
							, ccssToken, appVersion, carOem, devModel, nwType, posX, posY, userCtn, uuid);	
					
					/////////////로그서버/////////////////
					LogResponseJSON logResponseJSON = logAgent.sendLog(ctn, ccssToken, devType, uuid, appVersion, carOem, devModel, userCtn, "fail", "250", dataSize, nwType, posX, posY, logTime);
					logger.debug(String.format("[STEP9] sendLog Result > ctn(%s) : resultMsg(fail) resultCode(250) logResponse(%s) : ", ctn, logResponseJSON.toString()));
					
					/////////////로그서버/////////////////
					//LogResponseJSON logResponseJSON = logAgent.sendLog(ctn, ccssToken, devType, uuid, appVersion, carOem, devModel, userCtn, "fail", "150", dataSize, nwType, posX, posY, logTime);
					
					return "datagift/result";
		        }
			}else {	// 결제처리 완료 수행을 못한 경우:
				//2)API 요청실패 화면처리
				logger.error( String.format("최종결제요청 처리(Tx실패): 결제요청이 실패하였습니다. \n - TX 결제요청 Response_code = %s \n - TX 결제요청 Response_msg = $s"
						                    , xpay.m_szResCode, xpay.m_szResMsg));
	
				logger.error("최종결제요청 처리(Tx실패 >> 결재실패 DB처리): 결제요청 실패 대한 DB처리 .....");
				//최종결제요청 결과 실패 DB처리
				/***********************************************************************************
				 * 여기서, 결재수행결과(실패)에 대한 DB 작업을 수행한다.
				 *  (최종)결제요청 결과(에러)를  DB 저장(결제이력 테이블) : LGD_MID, LGD_OID, LGD_AMOUNT, LGD_TID, ….  
				 ***********************************************************************************/
				
				lgdTid = null;
				lgdRespCode = xpay.m_szResCode;
				lgdRespMsg = String.format("xpay.TX() fail. %s", xpay.m_szResMsg == null ? null : 
					xpay.m_szResMsg.length() > 140 ? xpay.m_szResMsg.substring(0, 140) : xpay.m_szResMsg);
				
	        	paymentVo.setLgdTid(lgdTid);
	        	paymentVo.setLgdRespCode(lgdRespCode);
	        	paymentVo.setLgdRespMsg(lgdRespMsg);

	        	try {
	        		this.datagiftPaymentDao.insertPayment(paymentVo);
	        	}catch(Exception e) {
	        		logger.error(String.format("insertPayment(결재실패 저장) 오류, reason=%s", e.getMessage()));
	        	}
				
				resultMsg = new String("데이타상품권 결제에 실패했습니다. 잠시 후 다시 시도해 주세요.");
				model.addAttribute("RESULT_TYPE", "250");
				model.addAttribute("RESULT_MESSAGE", resultMsg);
				this.setPaymentAgain(model, carNumber, giftName, dataSize, sellAmount, issueNo
						, giftKub, issueName, campStartDt, campEndDt
						, LGD_BUYER, LGD_BUYER_PHONENUM, LGD_BUYEREMAIL, LGD_CUSTOM_FIRSTPAY, canBuyGift
						, ccssToken, appVersion, carOem, devModel, nwType, posX, posY, userCtn, uuid);
				
				/////////////로그서버/////////////////
				LogResponseJSON logResponseJSON = logAgent.sendLog(ctn, ccssToken, devType, uuid, appVersion, carOem, devModel, userCtn, "fail", "250", dataSize, nwType, posX, posY, logTime);
				logger.debug(String.format("[STEP10] sendLog Result > ctn(%s) : resultMsg(fail) resultCode(250) logResponse(%s) : ", ctn, logResponseJSON.toString()));
				
				return "datagift/result";
			}		
		}catch(Exception e)
		{
			logger.error(String.format("[관리자 확인 필요] 최종결제요청 처리(시스템장애): 결재처리에 장애가 발행, reason=%s", e.getMessage()), e);
			return "datagift/error";
		}
	}
	
	
	/**
	 * 결재성공건에 대한 rollback 수행: DB작업이 실패하거나, 발행/등록에 실패한 경우에 호출된다. 
	 *
	 * 1) rollback 성공
	 * 		- 결재수행결과 DB저장.
	 * 		- 데이타상품권 DB삭제 > DB업데이트로 변경예정(B)
	 * 		- 결제 취소 결과창 생성.
	 * 2) rollback 실패
	 * 		- 결재수행결과 DB저장.
	 *      - 데이타상품권  DB업데이트로 예정(E)
	 * 		- 관리자문의 결과창 생성.
	 * 
	 * @param xpay
	 * @param model
	 * @param carNumber
	 * @param giftName
	 * @param dataSize
	 * @param sellAmount
	 * @param issueNo
	 * @param LGD_BUYER
	 * @param LGD_BUYER_PHONENUM
	 * @param LGD_BUYEREMAIL
	 * @param LGD_CUSTOM_FIRSTPAY
	 * @param canBuyGift
	 * @return
	 */
	private String rollBackPayment(XPayClient xpay, Model model, String carNumber, String devType, String giftName, String dataSize, String sellAmount
			, String issueNo, String giftKub, String issueName, String campStartDt, String campEndDt
			, String LGD_BUYER, String LGD_BUYER_PHONENUM, String LGD_BUYEREMAIL, String LGD_CUSTOM_FIRSTPAY, String canBuyGift, PaymentVO payment
			, String ccssToken, String appVersion, String carOem, String devModel, String nwType, String posX, String posY, String userCtn, String uuid
			, String ctn, String logTime)
	{ // 최종결제요청(성공)에 대한 DB처리 실패.
		logger.debug("DataGiftServiceImpl_rollBackPayment start");
		String LGD_RESPCODE = null;
		String LGD_RESPMSG = null;
		
		xpay.Rollback("Rollback 처리 [TID:" + xpay.Response("LGD_TID",0)
                + ",MID:" + xpay.Response("LGD_MID",0)
                + ",OID:"+ xpay.Response("LGD_OID",0) + "]");

		LGD_RESPCODE = xpay.Response("LGD_RESPCODE",0);
		LGD_RESPMSG = xpay.Response("LGD_RESPMSG",0);
		
		logger.info( "TX Rollback Response_code = " + LGD_RESPCODE);
		logger.info( "TX Rollback Response_msg = " + LGD_RESPMSG);
		
		payment.setPayType(PaymentVO.PAY_TYPE_ROLLBACK);
		payment.setRegDt(new Date());
		payment.setUpdDt(new Date());
		payment.setLgdRespCode(LGD_RESPCODE);
		payment.setLgdRespMsg(LGD_RESPMSG);
		
//		/* 2018-10-29, 테스트 목적으로 rollback 에러 발생시킴. - 차후 반드시 원복해야 한다. */
//		pgs
//		payment.setLgdRespCode("9999");
//		payment.setLgdRespMsg("실제XPay rollback은 성공이지만, 테스트목적으로 Rollback에러 발생!");
//		xpay.m_szResCode = "9999";
		
		try {
			this.datagiftPaymentDao.insertPayment(payment);		// [참고] DB에러로, 결재rollback 하는 상황이면, insert가 안된다.
		}catch(Exception e)
		{
			logger.error(String.format("insertPayment(결제rollback 결과[%s:%s]저장) 에러, reason=%s", LGD_RESPCODE, LGD_RESPMSG, e.getMessage()));
		}
		
		if( "0000".equals( xpay.m_szResCode ) ) {
			logger.info("결재처리 성공후, 자동취소가 정상적으로 완료 되었습니다.");
			
/* 2018-10-23, 변경요청사항 검토: 
 * 1. 결제Rollback이 성공한 건에 대해서 구매이력에서 조회.
 * 2. 결제Rollback이 실패한 건에 대해서 구매이력에서 조회.   
 */			
//			/************************************************************************** 
//			 * 결재 rollback 결과를  DB 저장(결제이력): LGD_MID, LGD_OID, LGD_AMOUNT, LGD_TID, …
//			 * 결재된 테이타상품권 정보를 DB 삭제(상품권 테이블):   // delete 
//			 **************************************************************************/
//			try {
//				this.datagiftDao.deleteDataGiftByOid(payment.getLgdOid());   
//				// [참고] DB에러로, 결재rollback 하는 상황이면, 결재성공에 대한 상품권 정보가 DB에 없을 것이고, delete 명령도 수행되지 않는다.
//			}catch(Exception e) {
//				logger.error(String.format("[관리자 확인 필요] deleteDataGiftByOid(결재rollback-상품권 삭제) 에러, reason=%s, payment=%s", e.getMessage(), payment.toString()));
//			}
			
			// 결제rollback 성공에 따른 상품권 정보 삭제 기능을  상태값 변경하는 것으로 기능 변경함: 2018-10-23, 이상열
	    	try {
	    		this.datagiftDao.updateDataGift(payment.getLgdOid(), null, DataGiftVO.DATA_GIFT_STATE_ROLLBACK_OK);
	    	}catch(Exception e)
	    	{
	    		logger.error(String.format("updateDataGift(상품권 발행정보 업데이트: 발품권 구매 rollback 성공) 에러, reason=%s", e.getMessage()));
	    	}			
			
			String resultMsg = new String("데이타상품권 발급에 실패했습니다. 잠시 후 다시 시도해 주세요.");  // "결재가 취소 되었습니다." => "데이타상품권 발급에 실패햇습니다. 잠시 후 다시 시도해 주세요."
			model.addAttribute("RESULT_TYPE", "400");								   // "RESULT_TYPE", "400"); 으로 변경예정.
			model.addAttribute("RESULT_MESSAGE", resultMsg);
			this.setPaymentAgain(model, carNumber, giftName, dataSize, sellAmount, issueNo
					, giftKub, issueName, campStartDt, campEndDt
					, LGD_BUYER, LGD_BUYER_PHONENUM, LGD_BUYEREMAIL, LGD_CUSTOM_FIRSTPAY, canBuyGift
					, ccssToken, appVersion, carOem, devModel, nwType, posX, posY, userCtn, uuid);
			
			// 시스템로그 추가
			LogResponseJSON logResponseJSON = logAgent.sendLog(ctn, ccssToken, devType, uuid, appVersion, carOem, devModel, userCtn, "fail", "450", dataSize, nwType, posX, posY, logTime);
			logger.debug(String.format("[STEP6] sendLog Result > ctn(%s) : resultMsg(fail) resultCode(450) logResponse(%s) : ", ctn, logResponseJSON.toString()));
			
			return  "datagift/result";							
		}else{
			logger.error(String.format("결재처리 성공후, Rollback을 수행하였으나 실패하였습니다. 아래 두가지 경우를 확인 바랍니다.\n" 
		                                         + "1) 결재 성공 후, DB저장이 안된 경우. => 결제는 되었으나 DB에 정보가 없음!"
		                                         + "2) 결재 성공 후, 발행등록이 실패한 경우. => 결제는 되었으나 발행등록이 안되어 결제한 데이타 상품권 사용 못함."
					                             + "payment=%s", payment.toString()));
			
			// 결제rollback 실페에 따른 관리자 확인 메세지 출력 기능을  상태값 변경하는 것으로 기능 변경함: 2018-10-23, 이상열
			// 
	    	try {
	    		this.datagiftDao.updateDataGift(payment.getLgdOid(), null, DataGiftVO.DATA_GIFT_STATE_ROLLBACK_ERROR);
	    	}catch(Exception e)
	    	{
	    		logger.error(String.format("updateDataGift(상품권 발행정보 업데이트: 발품권 구매 rollback 실패) 에러, reason=%s", e.getMessage()));
	    	}
	    	
			String resultMsg = new String("데이타상품권 발급에 실패했습니다. 구매내역에서 결재취소를 해 주세요.");  // "결재처리에 장애가 발행하였습니다. 관리자 문의를 바랍니다." => "데이타상품권 발급에 실패했습니다. 구매내역에서 결재취소를 해 주세요."
			model.addAttribute("RESULT_TYPE", "500");							// "RESULT_TYPE", "500"); 으로 변경예정.
			model.addAttribute("RESULT_MESSAGE", resultMsg);
			this.setMainAgain(model, carNumber, devType, ccssToken, appVersion, carOem, devModel, nwType, posX, posY, userCtn, uuid);
			
			// 시스템로그 추가
			LogResponseJSON logResponseJSON = logAgent.sendLog(ctn, ccssToken, devType, uuid, appVersion, carOem, devModel, userCtn, "fail", "500", dataSize, nwType, posX, posY, logTime);
			logger.debug(String.format("[STEP7] sendLog Result > ctn(%s) : resultMsg(fail) resultCode(500) logResponse(%s) : ", ctn, logResponseJSON.toString()));
			
			return "datagift/result";
		}
	}
	

		
	// if RESULT_TYPE이 100
	private void setPaymentAgain(Model model, String carNumber, 
			String giftName, String dataSize, String sellAmount, String issueNo,
			String giftKub, String issueName, String campStartDt, String campEndDt, 
			String LGD_BUYER, String LGD_BUYER_PHONENUM, String LGD_BUYEREMAIL, String LGD_CUSTOM_FIRSTPAY, String canBuyGift,
			String ccssToken, String appVersion, String carOem, String devModel, String nwType, String posX, String posY, String userCtn, String uuid)
	{
		logger.debug("DataGiftServiceImpl_DataGiftServiceImpl_setPaymentAgain start");
	    model.addAttribute("carNumber", carNumber); 
	    model.addAttribute("giftName", giftName);	// RESULT_TYPE이 100 일때 사용 
	    model.addAttribute("dataSize", dataSize);	// RESULT_TYPE이 100 일때 사용 
	    model.addAttribute("sellAmount", sellAmount);// RESULT_TYPE이 100 일때 사용 
	    model.addAttribute("issueNo", issueNo);			// RESULT_TYPE이 100 일때 사용 
	    
	    model.addAttribute("giftKub", giftKub);	
	    model.addAttribute("issueName", issueName);	
	    model.addAttribute("campStartDate", campStartDt);	
	    model.addAttribute("campEndDate", campEndDt);	
	    
	    model.addAttribute("LGD_BUYER", LGD_BUYER);		// RESULT_TYPE이 100 일때 사용 
	    model.addAttribute("LGD_BUYER_PHONENUM", LGD_BUYER_PHONENUM);	// RESULT_TYPE이 100 일때 사용 
	    model.addAttribute("LGD_BUYEREMAIL", LGD_BUYEREMAIL);	// RESULT_TYPE이 100 일때 사용 
	    model.addAttribute("LGD_CUSTOM_FIRSTPAY", LGD_CUSTOM_FIRSTPAY);	// RESULT_TYPE이 100 일때 사용 
	    
	    model.addAttribute("canBuyGift", canBuyGift);
	    
	    model.addAttribute("ccssToken", ccssToken);
	    model.addAttribute("appVersion", appVersion);
	    model.addAttribute("carOem", carOem);
	    model.addAttribute("devModel", devModel);
	    model.addAttribute("nwType", nwType);
	    model.addAttribute("posX", posX);
	    model.addAttribute("posY", posY);
	    model.addAttribute("userCtn", userCtn);
	    model.addAttribute("uuid", uuid);
	}
	
	// RESULT_TYPE이 200,300 일때 
	private void setMainAgain(Model model, String carNumber, String devType, String ccssToken, String appVersion, String carOem, String devModel
			, String nwType, String posX, String posY, String userCtn, String uuid)
	{
		model.addAttribute("carNumber", carNumber); 
		model.addAttribute("devType", devType);
		model.addAttribute("ccssToken", ccssToken);
		model.addAttribute("appVersion", appVersion);
		model.addAttribute("carOem", carOem);
		model.addAttribute("devModel", devModel);
		model.addAttribute("nwType", nwType);
		model.addAttribute("posX", posX);
		model.addAttribute("posY", posY);
		model.addAttribute("userCtn", userCtn);
		model.addAttribute("uuid", uuid);
		//model.addAttribute("canBuyGift", canBuyGift);
	}
	
	

	
		
	@Override
	public String getResult(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
			Model model) {
		// TODO Auto-generated method stub
		logger.debug("DataGiftServiceImpl_getResult start");
		return null;
	}
	
	
	/**
	 * API Json 응답(Datagift)을 JSP view에 사용할 bean로 변경한다.
	 * 
	 * @param _dataGiftList
	 * @return
	 */
	private DataGiftOwnListInfo convertDataGiftList(DataGiftOwnList _dataGiftOwnList, MembVO memb)
	{
		logger.debug("DataGiftServiceImpl_convertDataGiftList start");
		DataGiftOwnListInfo dataGiftOwnListInfo = new DataGiftOwnListInfo();
		List<DataGiftInfo> datagiftListInfo = new ArrayList<DataGiftInfo>();
		List<DataGift> _datagiftList = _dataGiftOwnList.getDataGiftList();
		int listCount = 0;
		
		/*****************************************
		 * 데이타상품권 API 조회정보를 기반으로 정상적으로 발행/등록된 
		 * 상품권 정보 list정보를 생성한다.
		 *****************************************/
		if(_datagiftList != null)
		{
			listCount += _datagiftList.size();
			
			for(DataGift datagift : _datagiftList)
			{
				DataGiftInfo datagiftInfo = new DataGiftInfo();
				
				// 데이타상품권 API서버의 이력조회 정보에서는 구매가격이 없으므로, 
				// CCSS DB에서 giftNo에 대한 구매가격을 조회한다.
				String sellAmount = null;
				String payDate = null;
				String payDateTime = null;
				String carNumber = null;
				String issueRegKup = null;
				String tid = null;
				String issueNo = null;
				String lgdOid = null;
				String memberId = null;
				
				DataGiftVO dataGiftVo = null;
				try {
					//sellAmount = this.datagiftDao.selectSellAmountByGiftNo(datagift.getGiftNo());
					dataGiftVo = this.datagiftDao.selectDataGiftByGiftNo(datagift.getGiftNo());
					
					if(dataGiftVo == null)
					{
						logger.error(String.format("[관리자 확인 필요] CCSS DB(TB_GIFT테이블)에 데이타상품권 정보가 없습니다. giftNo[%s] \n\t-발행/등록 후, 상품권 테이블에 giftNo가 업데이트 되지 않은 경우에 해당합니다.", datagift.getGiftNo()));
						continue;
					}else{
						sellAmount = dataGiftVo.getSellAmount();
						carNumber = dataGiftVo.getCarNum();
						issueRegKup = dataGiftVo.getIssueRegKup();
						tid = dataGiftVo.getLgdTid();
						issueNo = dataGiftVo.getIssueNo();
						lgdOid = dataGiftVo.getLgdOid();
						memberId = dataGiftVo.getMemberId();
						
						if(dataGiftVo.getLgdPayDate() != null && dataGiftVo.getLgdPayDate().length() == 14)
						{	// ex) 20181017103241 => 2018.10.17로 변경.
							payDate = String.format("%s.%s.%s", dataGiftVo.getLgdPayDate().substring(0, 4)
																, dataGiftVo.getLgdPayDate().substring(4, 6)
																, dataGiftVo.getLgdPayDate().substring(6, 8));
						}else{
							payDate = "";
						}
						payDateTime = dataGiftVo.getLgdPayDate();
						
						datagiftInfo.setPayDate(payDate);
						datagiftInfo.setPayDateTime(payDateTime);
						datagiftInfo.setSellAmount(sellAmount == null ? "" : sellAmount);
						datagiftInfo.setCarNumber(carNumber);
						if(DataGiftVO.DATA_GIFT_STATE_REGIST.contentEquals(issueRegKup.trim()))
						{
							datagiftInfo.setState("신용카드 결제완료");
						}else{
							logger.error(String.format("[관리자 확인 필요] TB_DATAGIFT 테이블에서 발행/등록된 데이타상품권 issueRegKup값(R) 오류, issueRegKup=%s", issueRegKup));
							datagiftInfo.setState("");
						}
						datagiftInfo.setIssueRegKup(issueRegKup);
						datagiftInfo.setTid(tid);
						datagiftInfo.setIssueNo(issueNo);
						datagiftInfo.setLgdOid(lgdOid);
						datagiftInfo.setMemberId(memberId);
					}
				}catch(Exception e)
				{
					logger.error(String.format("datagiftDao.selectSellAmountByGiftNo failed, reason=%s", e.getMessage()));
				}
				
				datagiftInfo.setDataSize(datagift.getDataSize());
				datagiftInfo.setGiftName(datagift.getGiftName());
	
				// 아래의 정보는 history.jsp에서 아직 사용하지 않음.
				datagiftInfo.setCodeDesc(datagift.getCodeDesc());			// "CodeDesc": "이용중"
				datagiftInfo.setCtn(datagift.getCtn());
				datagiftInfo.setEndDate(datagift.getEndDate());
				datagiftInfo.setGiftNo(datagift.getGiftNo());
				datagiftInfo.setPayTypeName(datagift.getPayTypeName());		//  "PayTypeName": "상품권번호",
				datagiftInfo.setStartDate(datagift.getStartDate());
				datagiftInfo.setUsedDataSize(datagift.getUsedDataSize());
	
				datagiftListInfo.add(datagiftInfo);
			}
		}
		
		/*****************************************
		 * TB_DATAGIFT 테이블 정보를 조회하여  결제 수행(성공) 후, 
		 * 발행/등록 처리에  실패한 상품권 정보 list정보를 생성한다.
		 *****************************************/
		try{
			List<DataGiftVO> dataGiftVoList = null;
			dataGiftVoList = this.datagiftDao.selectUnRegDataGift(memb.getMembId());
			
			if(dataGiftVoList != null)
			{
				listCount += dataGiftVoList.size();
						
				for(DataGiftVO dataGiftVo : dataGiftVoList)
				{
					DataGiftInfo datagiftInfo = new DataGiftInfo();
					String payDate = null;
					String payDateTime = null;
					
					if(dataGiftVo.getLgdPayDate() != null && dataGiftVo.getLgdPayDate().length() == 14)
					{	// ex) 20181017103241 => 2018.10.17로 변경.
						payDate = String.format("%s.%s.%s", dataGiftVo.getLgdPayDate().substring(0, 4)
															, dataGiftVo.getLgdPayDate().substring(4, 6)
															, dataGiftVo.getLgdPayDate().substring(6, 8));
					}else{
						payDate = "";
					}
					payDateTime = dataGiftVo.getLgdPayDate();
	
					datagiftInfo.setPayDate(payDate);
					datagiftInfo.setPayDateTime(payDateTime);
					datagiftInfo.setDataSize(dataGiftVo.getDataSize());
					datagiftInfo.setSellAmount(dataGiftVo.getSellAmount() == null ? "" : dataGiftVo.getSellAmount());
					datagiftInfo.setCarNumber(dataGiftVo.getCarNum());
					datagiftInfo.setGiftName(dataGiftVo.getGiftName());
					datagiftInfo.setState("데이타상품권 발급실패");
					datagiftInfo.setIssueRegKup(dataGiftVo.getIssueRegKup());
					datagiftInfo.setTid(dataGiftVo.getLgdTid());
					datagiftInfo.setIssueNo(dataGiftVo.getIssueNo());
					datagiftInfo.setLgdOid(dataGiftVo.getLgdOid());
					datagiftInfo.setMemberId(dataGiftVo.getMemberId());
					
					// 아래의 정보는 history.jsp에서 아직 사용하지 않음.
					datagiftInfo.setCtn(dataGiftVo.getCtn());
					datagiftInfo.setEndDate(dataGiftVo.getCampEndDt());
					datagiftInfo.setGiftNo(dataGiftVo.getGiftNo());
					datagiftInfo.setStartDate(dataGiftVo.getCampStartDt());
					datagiftInfo.setUsedDataSize("0");
					
					datagiftListInfo.add(datagiftInfo);
				}
			}
		}catch(Exception e){
			logger.error(String.format("datagiftDao.selectUnRegDataGift failed, reason=%s", e.getMessage()));
		}
		
		Collections.reverse(datagiftListInfo);
		
		dataGiftOwnListInfo.setDataGiftListCnt(String.valueOf(listCount));
		dataGiftOwnListInfo.setDataGiftListInfo(datagiftListInfo);
		
		return dataGiftOwnListInfo;
	}
	
	/**
	 * API Json 응답(Campaign)을 JSP view에 사용할 bean로 변경한다.
	 * 
	 * @param _dataGiftList
	 * @return
	 */
	private DataGiftListInfo convertCampaignList(DataGiftList _dataGiftList)
	{
		logger.debug("DataGiftServiceImpl_convertCampaignList start");
		DataGiftListInfo dataGiftListInfo = new DataGiftListInfo();
		List<CampaignInfo> campaignListInfo = new ArrayList<CampaignInfo>();
		List<Campaign> _campaignList = _dataGiftList.getCampaignList();
		
		for(Campaign _campaign : _campaignList)
		{
			CampaignInfo campaignInfo = new CampaignInfo();
			
			campaignInfo.setCampEndDate(_campaign.getCampEndDate());
			campaignInfo.setCampStartDate(_campaign.getCampStartDate());
			campaignInfo.setDataSize(_campaign.getDataSize());
			campaignInfo.setGiftKub(_campaign.getGiftKub());
			campaignInfo.setGiftName(_campaign.getGiftName());
			campaignInfo.setIssueCnt(_campaign.getIssueCnt());
			campaignInfo.setIssueName(_campaign.getIssueName());
			campaignInfo.setIssueNo(_campaign.getIssueNo());
			campaignInfo.setIssueRegKub(_campaign.getIssueRegKub());
			campaignInfo.setRemainCnt(_campaign.getRemainCnt());
			campaignInfo.setSellAmount(_campaign.getSellAmount());
			
			campaignListInfo.add(campaignInfo);
		}
		
		Collections.sort(campaignListInfo);
		
		dataGiftListInfo.setCampaignCnt(_dataGiftList.getCampaignCnt());
		dataGiftListInfo.setCampaignList(campaignListInfo);
		
		return dataGiftListInfo;
	}

	/*******************************************************************************************************
	 * 결과 화면(result.jsp) 요청 처리
	   - request [POST] parameter : carNumber, devType, lgdOid, issueNo, memberId
	   - model: 1) 결재취소(성공): RESULT_TYPE, RESULT_MESSAGE, carNumber
	            2) 결재취소(실패): RESULT_TYPE, RESULT_MESSAGE, carNumber
	 *******************************************************************************************************/	
	@Override
	public String processCancelPayment(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
			Model model) {
		logger.debug("DataGiftServiceImpl_processCancelPayment start");
		
		String resultType = null;
		String resultMsg = null;
		String carNumber = httpServletRequest.getParameter("carNumber");
		String devType = httpServletRequest.getParameter("devType");
		String lgdOid = httpServletRequest.getParameter("lgdOid");
		String issueNo = httpServletRequest.getParameter("issueNo");
		String memberId = httpServletRequest.getParameter("memberId");
		
    	model.addAttribute("carNumber", carNumber); 
		model.addAttribute("devType", devType);
		
	    /*
	     * [결제취소 요청 페이지]
	     *
		 * 매뉴얼 "6. 결제 취소를 위한 개발사항(API)"의 "단계 3. 결제 취소 요청 및 요청 결과 처리" 참고
	     * LG유플러스으로 부터 내려받은 거래번호(LGD_TID)를 가지고 취소 요청을 합니다.(파라미터 전달시 POST를 사용하세요)
	     * (승인시 LG유플러스으로 부터 내려받은 PAYKEY와 혼동하지 마세요.)
	     */
	    String CST_PLATFORM         = this.dataGiftXPayCstPlatform;                 					//LG유플러스 결제서비스 선택(test:테스트, service:서비스)
	    String CST_MID              = this.dataGiftXPayCstMid;                      					//LG유플러스으로 부터 발급받으신 상점아이디를 입력하세요.
	    String LGD_MID              = ("test".equals(CST_PLATFORM.trim())?"t":"")+CST_MID;  			//테스트 아이디는 't'를 제외하고 입력하세요.
	                                                                                        			//상점아이디(자동생성)
	    String LGD_TID              = httpServletRequest.getParameter("LGD_TID");                      	//LG유플러스으로 부터 내려받은 거래번호(LGD_TID)
	    
		String payType = PaymentVO.PAY_TYPE_CANCEL;  // 결재요청 종류: "P"(결제요청), "R"(RollBack), "C"(결제취소)
		String cstPlatform = CST_PLATFORM;
		String lgdMid = LGD_MID;
		String lgdTid = LGD_TID;
		String lgdRespCode = null;
		String lgdRespMsg = null;  
		String issueRegKup = null;
	    
//    	PaymentVO paymentVo = new PaymentVO(lgdOid, issueNo, payType, memberId, cstPlatform, lgdMid, lgdAmount, lgdBuyer, lgdPrdtInfo, lgdTimestamp, lgdReturnurl, buyerAddress, 
//    			buyerPhone, buyerMail, lgdCustomFirstpay, lgdCustomProcessType, lgdLanguage, lgdKvpmIspAutoAppYn, lgdKvpmIspWapUrl, 
//    			lgdKvpmIspCancelUrl, lgdMpiLotteAppCardWapUrl, lgdPayKey, "Cancel", lgdTid, lgdPayType, lgdPayDate, lgdFinanceCode, 
//    			lgdFinanceName, lgdCardNum, lgdCardInstallMonth, lgdCardNoIntYn, lgdFinanceAuthNum, lgdRespCode, lgdRespMsg, 
//    			"Y", null, "SYSTEM", null, "SYSTEM");
//    	
//    	DataGiftVO datagiftVo = new DataGiftVO(lgdOid, lgdMid, lgdTid, lgdPayDate, issueNo, giftName, dataSize, giftKub, issueRegKup, sellAmount, issueName, 
//		campStartDt, campEndDt, ctn, giftNo, giftRegDt, mgrAppId, memberId, carNumber, "",  "Y", null, "SYSTEM", null, "SYSTEM");	   
		
    	PaymentVO paymentVo = new PaymentVO(lgdOid, issueNo, payType, memberId, cstPlatform, lgdMid, null, null, null, null, null, null, 
		null, null, null, null, null, null, null, null, null, null, "Cancel", lgdTid, null, null, null, null, null, null, null, null, 
		lgdRespCode, lgdRespMsg, "Y", null, "SYSTEM", null, "SYSTEM");

		DataGiftVO datagiftVo = new DataGiftVO(lgdOid, lgdMid, lgdTid, null, issueNo, null, null, null, null, null, null, 
				null, null, null, null, null, null, null, memberId, carNumber, "Y", null, "SYSTEM", null, "SYSTEM");			
	    
	    
	    if(LGD_TID == null)
	    {
	    	logger.error(String.format("[관리자 확인 필요] 결제취소요청처리: request parameter에 거래번호(LGD_TID) 정보가 없습니다. lgdOid=%s, issueNo=%s, memberId=%s", lgdOid, issueNo, memberId));
			return "datagift/cancelResult";
	    }

	    try{
			/* ※ 중요
			* 환경설정 파일의 경우 반드시 외부에서 접근이 가능한 경로에 두시면 안됩니다.
			* 해당 환경파일이 외부에 노출이 되는 경우 해킹의 위험이 존재하므로 반드시 외부에서 접근이 불가능한 경로에 두시기 바랍니다. 
			* 예) [Window 계열] C:\inetpub\wwwroot\lgdacom ==> 절대불가(웹 디렉토리)
			*/
	    	Resource resource = null;
	    	File file = null;
	    	String configPath = null;
	    	try{
			    resource = new ClassPathResource("/lgdacom");
			    file = resource.getFile();
			    configPath = file.getAbsolutePath();	        
	    	}catch(Exception ee){
	    		logger.error(String.format("[관리자 확인 필요] XPay config loading error! reason=%s", ee.getMessage()), ee);
				return "datagift/cancelResult";
	    	}
		    
		    
			// (1) XpayClient의 사용을 위한 xpay 객체 생성
		    XPayClient xpay = new XPayClient();
	
			// (2) Init: XPayClient 초기화(환경설정 파일 로드) 
			// configPath: 설정파일
			// CST_PLATFORM: - test, service 값에 따라 lgdacom.conf의 test_url(test) 또는 url(srvice) 사용
			//				- test, service 값에 따라 테스트용 또는 서비스용 아이디 생성
		    boolean isInitOK = xpay.Init(configPath, CST_PLATFORM);
		    
		    if( !isInitOK ) {
		   		logger.error(String.format("[관리자 확인 필요] 결제취소요청처리(초기화 실패) : %s%s%s%s", 
		   				"결제요청을 초기화 하는데 실패하였습니다.", 
		   				" LG유플러스에서 제공한 환경파일이 정상적으로 설치 되었는지 확인하시기 바랍니다. ", 
		   				"mall.conf에는 Mert ID = Mert Key 가 반드시 등록되어 있어야 합니다.",
		   				" 문의전화 LG유플러스 1544-7772"));
				return "datagift/cancelResult";		    
			}
	
			// (3) Init_TX: 메모리에 mall.conf, lgdacom.conf 할당 및 트랜잭션의 고유한 키 TXID 생성
		    try{
			    xpay.Init_TX(LGD_MID);
			    xpay.Set("LGD_TXNAME", "Cancel");
			    xpay.Set("LGD_TID", LGD_TID);
		    }catch(Exception ee){
				// LG U+ API 사용 불가, 설정파일 확인 등 필요(예외처리)
				logger.error(String.format("[관리자 확인 필요] 결제취소요청처리(초기화 실패): LG유플러스 제공 API를 사용할 수 없습니다. 환경파일 설정을 확인해 주시기 바랍니다. %s", ee.getMessage()));
				return "datagift/cancelResult";		
		    }
		    
		    
		    /*
		     * 1. 결제취소 요청 결과처리
		     *
		     * 취소결과 리턴 파라미터는 연동메뉴얼을 참고하시기 바랍니다.
			 *
			 * [[[중요]]] 고객사에서 정상취소 처리해야할 응답코드
			 * 1. 신용카드 : 0000, AV11  
			 * 2. 계좌이체 : 0000, RF00, RF10, RF09, RF15, RF19, RF23, RF25 (환불진행중 응답건-> 환불결과코드.xls 참고)
			 * 3. 나머지 결제수단의 경우 0000(성공) 만 취소성공 처리
			 *
		     */
			// (4) TX: lgdacom.conf에 설정된 URL로 소켓 통신하여 최종 인증요청, 결과값으로 true, false 리턴
		    if (xpay.TX()) {
				// (5) 결제취소요청 결과 처리
		        //1)결제취소결과 화면처리(성공,실패 결과 처리를 하시기 바랍니다.)
		    	logger.info(String.format("결제 취소요청이 완료되었습니다. \n\t-TX Response_code = %s\n\t-TX Response_msg = %s", xpay.m_szResCode, xpay.m_szResMsg));
		    	resultMsg = new String("결제 취소요청이 완료되었습니다.");
		    	
		        if( "0000".equals( xpay.m_szResCode )) 	// 결제취소요청 성공.
		        {	
			    	resultType = "200";
		        	logger.info(String.format("결제 취소요청이 성공하였습니다. "));
		        	issueRegKup = "C";
				}else{									// 결제취소요청 실패.
			    	resultType = "100";
					logger.error(String.format("결제 취소요청이 실패하였습니다. "));
					issueRegKup = "M";
				}	    	
		    }else {
		        //2)API 요청 실패 화면처리
		    	logger.error(String.format("결제 취소요청이 실패하였습니다. \n\t-TX Response_code = %s\n\t-TX Response_msg = %s", xpay.m_szResCode, xpay.m_szResMsg));
		    	resultMsg = new String("결제 취소요청이 실패하였습니다.");
		    	resultType = "100";
		    	issueRegKup = "M";
		    }
		    
        	lgdRespCode = xpay.m_szResCode;	        	
			lgdRespMsg = xpay.m_szResMsg == null ? null : xpay.m_szResMsg.length() > 140 ? xpay.m_szResMsg.substring(0, 140) : xpay.m_szResMsg;
		    
		    // 결재 취소 이력을  TB_GIFT_PAYMENT 테이블에  insert.
			paymentVo.setLgdRespCode(lgdRespCode);
			paymentVo.setLgdRespMsg(lgdRespMsg);
			
        	try {
        		this.datagiftPaymentDao.insertPayment(paymentVo);
        	}catch(Exception ee)
        	{
        		logger.error(String.format("insertPayment(결재취소 요청 저장) 오류, reason=%s", ee.getMessage()));
        	}
		    
		    // 데이타상품권 테이블을 update.
        	datagiftVo.setIssueRegKup(issueRegKup);
	    	try {
	    		this.datagiftDao.updateDataGift(datagiftVo.getLgdOid(), datagiftVo.getGiftNo(), datagiftVo.getIssueRegKup());
	    	}catch(Exception e)
	    	{
	    		logger.error(String.format("updateDataGift(상품권 발행정보 업데이트: 결재취소 결과[C:결제취소완료 or M:결제취소에러]) 에러, reason=%s", e.getMessage()));
	    	}
		    
		    model.addAttribute("RESULT_TYPE", resultType);
			model.addAttribute("RESULT_MESSAGE", resultMsg);
		}catch(Exception e)
		{
			logger.error(String.format("[관리자 확인 필요] 결체취소요청 (시스템장애): 결재취소에 장애가 발행, reason=%s", e.getMessage()), e);
			
			resultMsg = new String("결제 취소요청이 실패하였습니다");
			model.addAttribute("RESULT_TYPE", "100");
			model.addAttribute("RESULT_MESSAGE", resultMsg);
		}finally{
			model.addAttribute("carNumber", carNumber); 
			model.addAttribute("devType", devType); 
		}
			
		
	    return "datagift/cancelResult";
	}



}
