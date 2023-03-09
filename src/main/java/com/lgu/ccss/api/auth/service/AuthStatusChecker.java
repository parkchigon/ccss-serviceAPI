package com.lgu.ccss.api.auth.service;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.lgu.ccss.api.auth.model.AuthStatusResultData;
import com.lgu.ccss.common.dao.DeviceDao;
import com.lgu.ccss.common.dao.MemberDao;
import com.lgu.ccss.common.dao.TermsDao;
import com.lgu.ccss.common.model.ConnDeviceVO;
import com.lgu.ccss.common.model.MembCommAgrVO;
import com.lgu.ccss.common.model.MembVO;
import com.lgu.common.esb.ESBManager;
import com.lgu.common.esb.vo.AmountDataVO;
import com.lgu.common.ncas.NCASConst;
import com.lgu.common.ncas.SubsInfo;
import com.lgu.common.util.DateUtils;
import com.lgu.common.util.ExceptionUtil;

@Component
public class AuthStatusChecker {

	private static final Logger logger = LoggerFactory.getLogger(AuthStatusChecker.class);

	@Value("#{config['service.ratePayment']}")
	private String ratePayment;

	@Value("#{config['service.ratePayment.period']}")
	private String ratePaymentPeriod;

	@Value("#{config['service.ratePayment.expirationNotiDay']}")
	private String ratePaymentExpirationNotiDay;
	
	@Value("#{config['el1.service.ratePayment.period']}")
	private String el1RatePaymentPeriod;

	@Value("#{config['el1.service.ratePayment.expirationNotiDay']}")
	private String el1RatePaymentExpirationNotiDay;	
	
	private String strRatePaymentPeriod;
	private String strRatePaymentExpirationNotiDay;

	@Value("#{config['service.joiningDay']}")
	private String joiningDay;
	
	@Value("#{config['service.basic.payment']}")
	private String basicPayment;
	
	@Autowired
	private MemberDao memberDao;

	@Autowired
	private TermsDao termsDao;

	@Autowired
	private ESBManager esbManager;
	
	@Autowired
	private DeviceDao deviceDao;

	public AuthStatusResultData getStatus(SubsInfo subsInfo, MembVO memb, String deviceCtn, String deviceSerial)
			throws Exception {

		AuthStatusResultData result = new AuthStatusResultData();
		
		// check UICCID
		if (subsInfo != null && !deviceSerial.equals(subsInfo.getUsim_iccid_no())) {
			return result;
		}

		// set JoinStatus
		String joinStatus = AuthStatusResultData.JOIN_STATUS_NONE;
		if (subsInfo == null) {
			List<MembCommAgrVO> membCommAgrs = termsDao.selectMembCommAgr(deviceSerial);
			if (membCommAgrs.size() > 0) {
				MembCommAgrVO membCommAgr = membCommAgrs.get(membCommAgrs.size() - 1);
				if (isJoiningDate(membCommAgr.getValidStartDt()) == true) {
					joinStatus = AuthStatusResultData.JOIN_STATUS_ING;
				}
			}
		} else {
			if (subsInfo.getCtn_stus_code().equals(NCASConst.CTN_STUS_CODE_SUSPEND)) {
				joinStatus = AuthStatusResultData.JOIN_STATUS_PAUSE;
			} else if (subsInfo.getCtn_stus_code().equals(NCASConst.CTN_STUS_CODE_ACTIVE)) {
				joinStatus = AuthStatusResultData.JOIN_STATUS_IN;
			}
		}
		result.setJoinStatus(joinStatus);

		// get Member data by CTN
		List<MembVO> membList = memberDao.selectMemberByCTN(deviceCtn);
		if (membList.size() == 0) {
			logger.error("failed to get Member data by CTN. deviceCtn({})", deviceCtn);
		}

		// set FirstJoinStatus
		String firstJoinStatus = AuthStatusResultData.FIRST_JOIN_STATUS_N;
		if (membList.size() == 0) {
			firstJoinStatus = AuthStatusResultData.FIRST_JOIN_STATUS_Y;
		}
		result.setFirstJoinStatus(firstJoinStatus);

		if (subsInfo == null) {
			return result;
		}

		// set JoinDate, ratePayment
		result.setJoinDate(subsInfo.getRegDate());
		result.setRatePayment(subsInfo.getFee_type());

		// set DataStatus
		result.setDataStatus(getDataStatus(subsInfo, deviceCtn));

		String payResvYn = MembVO.PAY_RESV_N;
		if (memb != null) {
			payResvYn = memb.getPayResvYn();
			
			Date joinDate = DateUtils.getDate(subsInfo.getRegDate(), "yyyy-MM-dd");

			// 닛산 LEAF 20190128 kangjin
			ConnDeviceVO connDeviceVO = new ConnDeviceVO();
			connDeviceVO.setMembId(memb.getMembId());
			connDeviceVO = deviceDao.getDeviceInfo(connDeviceVO);
			
			// 닛산 LEAF 20190128 kangjin
			if("EL1".equals(connDeviceVO.getDeviceModelId())) {
				strRatePaymentPeriod = el1RatePaymentPeriod;
				strRatePaymentExpirationNotiDay = el1RatePaymentExpirationNotiDay;		
			}else {
				strRatePaymentPeriod = ratePaymentPeriod;
				strRatePaymentExpirationNotiDay = ratePaymentExpirationNotiDay;
			}
			
			if (logger.isDebugEnabled()) {
				logger.debug(
						"deviceCtn({}) joinDate({}) payResvYn({}) strRatePaymentPeriod({}) strRatePaymentExpirationNotiDay({}) ",
						deviceCtn, joinDate, payResvYn, strRatePaymentPeriod, strRatePaymentExpirationNotiDay);
			}
			
			// set RatePaymentStatus
			String ratePaymentStatus = AuthStatusResultData.RATE_PAYMENT_STATUS_NORMAL;
			if (DateUtils.isExpireTime(joinDate, strRatePaymentPeriod) == true) {
				if (payResvYn.equals(MembVO.PAY_RESV_N)) {
					ratePaymentStatus = AuthStatusResultData.RATE_PAYMENT_STATUS_EXPIRE;
				}
			} else if (DateUtils.isExpireTime(joinDate, strRatePaymentExpirationNotiDay) == true) {
				if (payResvYn.equals(MembVO.PAY_RESV_N)) {
					ratePaymentStatus = AuthStatusResultData.RATE_PAYMENT_STATUS_EXPIRING;
				}
			}
			result.setRatePaymentStatus(ratePaymentStatus);

			// set RatePaymentChangeStatus
			String ratePaymentChangeStatus = AuthStatusResultData.RATE_PAYMENT_CHAGE_STATUS_N;
			if (DateUtils.isExpireTime(joinDate, strRatePaymentPeriod) == true) {
				if (payResvYn.equals(MembVO.PAY_RESV_Y)) {
					ratePaymentChangeStatus = AuthStatusResultData.RATE_PAYMENT_CHAGE_STATUS_Y;
				}
			}
			result.setRatePaymentChangeStatus(ratePaymentChangeStatus);
			
			// set RatePaymentResvStatus
			if (payResvYn.equals(MembVO.PAY_RESV_Y)) {
				result.setRatePaymentResvStatus(AuthStatusResultData.RATE_PAYMENT_RESV_STATUS_Y);
			} else {
				result.setRatePaymentResvStatus(AuthStatusResultData.RATE_PAYMENT_RESV_STATUS_N);
			}			
			
		}else {
			// 닛산 LEAF 20190307 kangjin
			String ratePaymentStatus = AuthStatusResultData.RATE_PAYMENT_STATUS_NORMAL;
			result.setRatePaymentStatus(ratePaymentStatus);
			String ratePaymentChangeStatus = AuthStatusResultData.RATE_PAYMENT_CHAGE_STATUS_N;
			result.setRatePaymentChangeStatus(ratePaymentChangeStatus);
			result.setRatePaymentResvStatus(AuthStatusResultData.RATE_PAYMENT_RESV_STATUS_N);			
		}

		return result;
	}

	public AuthStatusResultData getBMStatus(SubsInfo subsInfo, MembVO memb, String deviceCtn, String deviceSerial)
			throws Exception {

		AuthStatusResultData result = new AuthStatusResultData();
		
		// check UICCID
		if (subsInfo != null && !deviceSerial.equals(subsInfo.getUsim_iccid_no())) {
			return result;
		}

		// set JoinStatus
		String joinStatus = AuthStatusResultData.JOIN_STATUS_NONE;
		if (subsInfo == null) {
			List<MembCommAgrVO> membCommAgrs = termsDao.selectMembCommAgr(deviceSerial);
			if (membCommAgrs.size() > 0) {
				MembCommAgrVO membCommAgr = membCommAgrs.get(membCommAgrs.size() - 1);
				if (isJoiningDate(membCommAgr.getValidStartDt()) == true) {
					joinStatus = AuthStatusResultData.JOIN_STATUS_ING;
				}
			}
		} else {
			if (subsInfo.getCtn_stus_code().equals(NCASConst.CTN_STUS_CODE_SUSPEND)) {
				joinStatus = AuthStatusResultData.JOIN_STATUS_PAUSE;
			} else if (subsInfo.getCtn_stus_code().equals(NCASConst.CTN_STUS_CODE_ACTIVE)) {
				joinStatus = AuthStatusResultData.JOIN_STATUS_IN;
			}
		}
		result.setJoinStatus(joinStatus);

		// get Member data by CTN
		List<MembVO> membList = memberDao.selectMemberByCTN(deviceCtn);
		if (membList.size() == 0) {
			logger.error("failed to get Member data by CTN. deviceCtn({})", deviceCtn);
		}

		// set FirstJoinStatus
		String firstJoinStatus = AuthStatusResultData.FIRST_JOIN_STATUS_N;
		if (membList.size() == 0) {
			firstJoinStatus = AuthStatusResultData.FIRST_JOIN_STATUS_Y;
		}
		result.setFirstJoinStatus(firstJoinStatus);

		if (subsInfo == null) {
			return result;
		}

		// set JoinDate, ratePayment
		result.setJoinDate(subsInfo.getRegDate());
		String payment[] ={"LPZ0000248","LPZ0000288","LRZ0001028","LRZ0001029","LRZ0001030","LRZ0001814","LRZ0001815","LRZ0001816","LRZ0001817","LRZ0001818","LRZ0001819"};
		if(subsInfo.getSvc_auth_dt() != null) {
			//String[] svcAuth = subsInfo.getSvc_auth().split("%7C");
			//String aaa = "0%7C0%7C0%7C0%7C0%7C0%7C0%7C0%7C0%7C0%7C0%7C0";
			String svcAuthDt = java.net.URLDecoder.decode(subsInfo.getSvc_auth_dt(), "UTF-8");
			String[] _svcAuthDt = svcAuthDt.split("\\|");
			//logger.debug("svcAuthDt BODY DATA:" + _svcAuthDt);
			if(_svcAuthDt != null && _svcAuthDt.length > 5){
				for(int i = 4 ; i  >= 0 ;i--){
					if(_svcAuthDt[i]!=null && !_svcAuthDt[i].equals("0") && _svcAuthDt[i].length()==12){
						result.setRatePayment(payment[i]);
						break;
					}
				}
			}
		}
		if(result.getRatePayment()==null)
			result.setRatePayment(basicPayment);
		
		//result.setRatePayment(subsInfo.getFee_type());

		// set DataStatus
		//result.setDataStatus(getDataStatus(subsInfo, deviceCtn));

		String payResvYn = MembVO.PAY_RESV_N;
		if (memb != null) {
			payResvYn = memb.getPayResvYn();
		}

		Date joinDate = DateUtils.getDate(subsInfo.getRegDate(), "yyyy-MM-dd");

		if (logger.isDebugEnabled()) {
			logger.debug(
					"deviceCtn({}) joinDate({}) payResvYn({}) ratePaymentPeriod({}) ratePaymentExpirationNotiDay({}) ",
					deviceCtn, joinDate, payResvYn, ratePaymentPeriod, ratePaymentExpirationNotiDay);
		}

		// set RatePaymentStatus
		String ratePaymentStatus = AuthStatusResultData.RATE_PAYMENT_STATUS_NORMAL;
		if (DateUtils.isExpireTime(joinDate, ratePaymentPeriod) == true) {
			if (payResvYn.equals(MembVO.PAY_RESV_N)) {
				ratePaymentStatus = AuthStatusResultData.RATE_PAYMENT_STATUS_EXPIRE;
			}
		} else if (DateUtils.isExpireTime(joinDate, ratePaymentExpirationNotiDay) == true) {
			if (payResvYn.equals(MembVO.PAY_RESV_N)) {
				ratePaymentStatus = AuthStatusResultData.RATE_PAYMENT_STATUS_EXPIRING;
			}
		}
		result.setRatePaymentStatus(ratePaymentStatus);

		// set RatePaymentChangeStatus
		String ratePaymentChangeStatus = AuthStatusResultData.RATE_PAYMENT_CHAGE_STATUS_N;
		if (DateUtils.isExpireTime(joinDate, ratePaymentPeriod) == true) {
			if (payResvYn.equals(MembVO.PAY_RESV_Y)) {
				ratePaymentChangeStatus = AuthStatusResultData.RATE_PAYMENT_CHAGE_STATUS_Y;
			}
		}
		result.setRatePaymentChangeStatus(ratePaymentChangeStatus);
		
		// set RatePaymentResvStatus
		if (payResvYn.equals(MembVO.PAY_RESV_Y)) {
			result.setRatePaymentResvStatus(AuthStatusResultData.RATE_PAYMENT_RESV_STATUS_Y);
		} else {
			result.setRatePaymentResvStatus(AuthStatusResultData.RATE_PAYMENT_RESV_STATUS_N);
		}

		return result;
	}
	
	private boolean isJoiningDate(Date beginDate) {
		long diffDays = DateUtils.getDiffDay(beginDate, new Date());

		if (diffDays >= Long.parseLong(joiningDay)) {
			return false;
		}

		return true;
	}

	private String getDataStatus(SubsInfo subsInfo, String deviceCtn) throws UnknownHostException {
		String esbEnabled = System.getProperty("esb.enabled");

		String dataStatus = AuthStatusResultData.DATA_STATUS_REMAIN;
		
		if (esbEnabled != null && esbEnabled.equals("N")) {
			return dataStatus;
		}
		InetAddress inet= InetAddress.getLocalHost();
		String ip = inet.getHostAddress();
		AmountDataVO amountData = null;

		try {
			amountData = esbManager.getAmountData(subsInfo.getSub_no(), subsInfo.getFee_type(),
					DateUtils.getString(new Date(), "yyyyMMdd"), ip);
			if (amountData == null) {
				logger.error("failed to get ESB data. deviceCtn({})", deviceCtn);

			} else {
				long allowData = Long.parseLong(amountData.getAlloValue());
				long useData = Long.parseLong(amountData.getUseValue());
				if (useData >= allowData) {
					dataStatus = AuthStatusResultData.DATA_STATUS_EMPTY;
				}

				logger.debug("ALLOW_DATA({}) USE_DATA({}) deviceCtn({}))", allowData, useData, deviceCtn);
			}
		} catch (Exception e) {
			logger.error(ExceptionUtil.getPrintStackTrace(e));

		}

		return dataStatus;
	}

	public static void main(String[] args) {
		try {
			// System.out.println("####### : " + DateUtils.getDiffDay("2017-12-10",
			// "2017-12-09"));

			for (int i = 0; i < 365; i++) {
				Date joinDate = DateUtils.getDate("2017-09-26", "yyyy-MM-dd");
				Calendar cal = Calendar.getInstance(Locale.KOREA);
				cal.setTime(joinDate);
				cal.add(Calendar.DATE, i);
				joinDate = cal.getTime();

				System.out.println("isExpireTime : " + DateUtils.getString(joinDate, "yyyy-MM-dd") + " "
						+ DateUtils.isExpireTime(joinDate, "30"));
			}

		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
