package com.lgu.ccss.mgr.auth.service.sms;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;

import com.lgu.ccss.common.constant.CCSSConst;
import com.lgu.ccss.common.dao.mgr.MgrAppSmsDao;
import com.lgu.ccss.common.enumtype.msg.MsgStatus;
import com.lgu.ccss.common.enumtype.msg.MsgTitle;
import com.lgu.ccss.common.enumtype.msg.MsgType;
import com.lgu.ccss.common.enumtype.msg.SendType;
import com.lgu.ccss.common.model.RequestJSON;
import com.lgu.ccss.common.model.ResponseJSON;
import com.lgu.ccss.common.model.mgr.MgrAppSmsCertInfoVO;
import com.lgu.ccss.common.model.mgr.MgrAppSmsVO;
import com.lgu.ccss.common.util.ResultCodeUtil;
import com.lgu.ccss.common.validation.CheckResultData;
import com.lgu.ccss.common.validation.ValidationChecker;
import com.lgu.common.model.ResultCode;
import com.lgu.common.util.ExceptionUtil;
import com.lgu.common.util.keygenerator.KeyGenerator;

@Service("mgrAuthSmsSendService")
public class MgrAuthSmsSendServiceImpl implements MgrAuthSmsSendService {

	private static final Logger logger = LoggerFactory.getLogger(MgrAuthSmsSendServiceImpl.class);
	private static final List<String> mandatoryList = new ArrayList<String>(
			Arrays.asList(RequestJSON.PARAM_MNGR_USER_CTN));
	
	
	@Autowired
	private MgrAppSmsDao mgrAppSmsDao;
	
	@Value("#{config['sms.orgNo']}")
	private String smsOrgNo;
	
	private static String SERVER_ID = System.getProperty("SERVER_ID");
	
	@Override
	public ResponseJSON doService(HttpHeaders headers, RequestJSON reqJson) {
		ResultCode resultCode=ResultCodeUtil.RC_2S000000;
		
		String api = reqJson.getCommon().getApiId();
		//String userCtn = CCSSUtil.getMgrUserCtn();
	

		ResponseJSON response = null;

		CheckResultData result = ValidationChecker.checkValidation(null,reqJson,CCSSConst.MANAGER_AUTH_SMS_SEND,mandatoryList);
		
		if (result.isStatus() == false) {
			resultCode = ResultCodeUtil.RC_3C004000;
			response = ResultCodeUtil.createResultMsg(resultCode,api,result.getReason());
			return response;
		}else{
			
			String userCtn = (String) reqJson.getParam().get(RequestJSON.PARAM_MNGR_USER_CTN);
			
			try {
				
				
				MgrAppSmsCertInfoVO certVO = null;
				
				/*
				 * 1. 해당 핸드폰 번호로 기 발송된 인증번호가 있는지 확인
				 * 1-1. 기 발송된 인증번호가 있는 경우 해당 인증번호 삭제
				 * 2. 인증번호 생성
				 * 3. 인증번호 저장 
				 * 4. 인증번호 SMS 전송 정보 저장
				 * */
				
				MgrAppSmsCertInfoVO mgrAppSmsCertInfoVO = new MgrAppSmsCertInfoVO();
				mgrAppSmsCertInfoVO.setMgrappCtn(userCtn);
				
				mgrAppSmsCertInfoVO = mgrAppSmsDao.getCertNo(mgrAppSmsCertInfoVO); //1
				
				
				if(mgrAppSmsCertInfoVO != null){ //1-1
					certVO = new MgrAppSmsCertInfoVO();
					//certVO.setMgrappCtn(userCtn);
					certVO.setSmsAuthSeq(mgrAppSmsCertInfoVO.getSmsAuthSeq());
					logger.info("Already exists CertNo. Delete Old CertNo. userCtn({})  certVO({})",userCtn,certVO);
					mgrAppSmsDao.setCertNoDel(certVO);
				}
				
				int cert = (int)(Math.random()*999999);
				String certNo =  String.format("%06d", cert); //2  숫자 6자리 나 오도록 패딩처리
				
				certVO = new MgrAppSmsCertInfoVO();
				certVO.setCertNo(certNo);			//인증번호 
				certVO.setMgrappCtn(userCtn);		//휴대폰 번호
				certVO.setExecScnCd(""); 			//실행화면 코드
				mgrAppSmsDao.setCertInfo(certVO); 	//3
				
				logger.info("#MANAGER_AUTH_SMS_SEND CERT NO :" + certNo + " USER CTN :" + userCtn);
				
				MgrAppSmsVO mgrAppSmsVO = makeMgrAppSmsInfo(certVO); 
				if(mgrAppSmsVO !=null){
					mgrAppSmsDao.setSmsInfoReg(mgrAppSmsVO); //4
				}else{
					resultCode = ResultCodeUtil.RC_4C005002;
				}
			} catch (Exception e) {
				resultCode = ResultCodeUtil.RC_4C005000;
				logger.error("userCtn({}) Exception({})", userCtn,ExceptionUtil.getPrintStackTrace(e));
			}
		}
		response = ResultCodeUtil.createResultMsg(resultCode, api);
		return response;
	}
	
	
	private MgrAppSmsVO makeMgrAppSmsInfo(MgrAppSmsCertInfoVO mgrAppSmsCertInfoVO) {
		MgrAppSmsVO resultVO = new  MgrAppSmsVO();
		
		String msgSeq;
		try {
			msgSeq = KeyGenerator.createCommonShardKey();
			//msgSeq = createSmsMagId(SERVER_ID,INSTANCE_ID,CCSSConst.SMS_KEY_MIN_VALUE ,CCSSConst.SMS_KEY_MAX_VALUE);
		} catch (Exception e) {
			logger.error("SMS Create Msg ID Fail userCtn({}) Exception({})", mgrAppSmsCertInfoVO.getMgrappCtn(),ExceptionUtil.getPrintStackTrace(e));
			return null;
		}
		
		//resultVO.setMsgId(Integer.parseInt(StringUtils.getPaddingResult("0", msgSeq, 20, "0")));
		resultVO.setMsgId(Long.parseLong(msgSeq));
		resultVO.setMsgStatus(MsgStatus.WAIT.getValue());
		resultVO.setCode("");
		resultVO.setMsgTitle(MsgTitle.SEND_CERT_NO.getDescription());
		
		String contentPreFix = "[인증번호]:";
		String contentPostFix = " 커넥티드카 사용자 본인 인증번호 입니다.";
		resultVO.setMsgCont(contentPreFix + mgrAppSmsCertInfoVO.getCertNo() + contentPostFix);
		
		resultVO.setMsgType(MsgType.SMS.getValue()); //SMS
		resultVO.setRecvPhoneNo(mgrAppSmsCertInfoVO.getMgrappCtn());
		resultVO.setSendType(SendType.SEND_INSTANTLY.getValue());
		resultVO.setSvrId(SERVER_ID);
		resultVO.setOrgNo(smsOrgNo);
		resultVO.setCallbackNo(smsOrgNo);
		
		return resultVO;
	}
	
	public static String createSmsMagId(String svrId,String instanceId ,int minValue,int maxValue) throws InterruptedException{
		String msgId;
		Random rn = new Random();
		//Thread.sleep(10);
		rn.setSeed(System.currentTimeMillis());
		int randomKey = Math.abs(rn.nextInt(maxValue)+minValue);
		msgId= svrId+instanceId + String.valueOf(randomKey);
		return msgId;
	}
}
