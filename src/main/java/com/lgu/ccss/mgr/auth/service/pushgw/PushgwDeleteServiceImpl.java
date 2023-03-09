package com.lgu.ccss.mgr.auth.service.pushgw;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.httpclient.HttpStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;

import com.lgu.ccss.common.constant.CCSSConst;
import com.lgu.ccss.common.dao.ModelDao;
import com.lgu.ccss.common.dao.mgr.MgrAppUserDao;
import com.lgu.ccss.common.model.OemVO;
import com.lgu.ccss.common.model.RequestCommonLogDataJSON;
import com.lgu.ccss.common.model.RequestJSON;
import com.lgu.ccss.common.model.ResponseJSON;
import com.lgu.ccss.common.model.mgr.MgrAppUserVO;
import com.lgu.ccss.common.util.CCSSUtil;
import com.lgu.ccss.common.util.ResultCodeUtil;
import com.lgu.ccss.common.validation.CheckResultData;
import com.lgu.ccss.common.validation.ValidationChecker;
import com.lgu.common.model.ResultCode;
import com.lgu.common.pushgw.PushConst;
import com.lgu.common.pushgw.model.PushVO;
import com.lgu.common.pushgw.model.ResPushJSON;
import com.lgu.common.pushgw.service.PushServiceAgent;

@Service("pushgwDeleteService")
public class PushgwDeleteServiceImpl implements PushgwDeleteService{

	private static final Logger logger = LoggerFactory.getLogger(PushgwDeleteServiceImpl.class);
	private static final List<String> mandatoryList = new ArrayList<String>(
			Arrays.asList(RequestJSON.PARAM_APP_PUSH_TYPE,RequestJSON.PARAM_DEVICE_TOKEN));

	
	@Autowired
	private PushServiceAgent pushServiceAgent;
	
	@Autowired
	private MgrAppUserDao mgrAppUserDao;
	
	@Autowired
	private ModelDao modelDao;
	
	//TO-DO
	@Override
	public ResponseJSON doService(HttpHeaders headers, RequestJSON reqJson) {
		ResultCode resultCode=ResultCodeUtil.RC_2S000000;
		String api = reqJson.getCommon().getApiId();
		String mgrappLoginId = CCSSUtil.getMgrUserLoginId();
		String sessionId = CCSSUtil.getMgrSessionId();
		String mgrappId = CCSSUtil.getMgrappId();
		String deviceToken = (String) reqJson.getParam().get(RequestJSON.PARAM_DEVICE_TOKEN);
		String osType =  (String) reqJson.getCommon().getLogData().getOsInfo();
		//20181005 박치곤 carOem으로 SY,HK,AM인지 구분
		String carOem =  (String) reqJson.getCommon().getDevice().getCarOem();
		
		String deviceModelId = mgrAppUserDao.selectDeviceModelId(mgrappLoginId);
		if ("EL1".equals(deviceModelId)) {
        	carOem = "NS";
        }
		if(carOem == null || carOem.length() == 0){
			carOem = "AM";
		}
		
		ResponseJSON response = null;
		
		CheckResultData result = ValidationChecker.checkValidation(headers,reqJson,CCSSConst.PUSHGW_SUBSCRIBER_DEREGISTER,mandatoryList);
		
		if (result.isStatus() == false) {
			resultCode = ResultCodeUtil.RC_3C004000;
			response = ResultCodeUtil.createResultMsg(resultCode,api,result.getReason());
			return response;
		}else{
			
			try {
				
				ResPushJSON resPushJSON = null;
				
				MgrAppUserVO mgrAppUserVO = new MgrAppUserVO();
				mgrAppUserVO.setMgrappLoginId(mgrappLoginId);
				mgrAppUserVO.setMgrappId(mgrappId);
				mgrAppUserVO = mgrAppUserDao.selectMgrUserInfo(mgrAppUserVO);
				
				
				if(mgrAppUserVO != null ){
					
					if(mgrAppUserVO.getPushKeyRegYn().equals(CCSSConst.DEF_YES)){
						
						String appPushType = (String) reqJson.getParam().get(RequestJSON.PARAM_APP_PUSH_TYPE);
						
						PushVO pushVO = new PushVO();
						pushVO.setDeviceToken(deviceToken);
						pushVO.setPushId(PushServiceAgent.createPushId());
						pushVO.setDeviceId(mgrAppUserVO.getUuid());
						pushVO.setAppPushType(appPushType);
						pushVO.setServiceKey(mgrAppUserVO.getMgrappLoginId());
						
						//20181005 박치곤 ostype으로 안드로이드 iso인지 구분
						if(osType.indexOf(RequestCommonLogDataJSON.OS_INFO_ANDROID) > -1){
							osType = RequestCommonLogDataJSON.OS_INFO_ANDROID;
						}else if(osType.indexOf(RequestCommonLogDataJSON.OS_INFO_IOS) > -1){
							osType = RequestCommonLogDataJSON.OS_INFO_IOS;
						}
						//20181005 박치곤 디비에서 조회할 내용 조합
						String OemId = carOem +"_"+ osType;
						//20181005 박치곤 디비 조회
						OemVO oemVo = modelDao.selectPushId(OemId);
						
						String pushServiceId = oemVo.getPushId(); 
						
						if(appPushType.equals(PushConst.GCM)){
							resPushJSON = (ResPushJSON) pushServiceAgent.subscriberDeregister(pushVO,mgrappLoginId, OemId, pushServiceId);
						}else {
							resPushJSON = (ResPushJSON) pushServiceAgent.subscriberDeregister(pushVO,mgrappLoginId, OemId, pushServiceId);
						}
						
						
						if(resPushJSON !=null){
							
							if(resPushJSON.getRETURN_CODE().equals(String.valueOf(HttpStatus.SC_OK))){
								
								//해당 Magrapp Id 찾아서 Push ID null /  PUSH_KEY_REG_YN ='N' Update 
								mgrAppUserVO.setPushId("");
								mgrAppUserVO.setPushKeyRegYn(CCSSConst.DEF_NO);
								
								boolean updateFlag = mgrAppUserDao.updateMgrUserPushId(mgrAppUserVO);
								
								if(updateFlag){
									
									logger.error("Pushgw Device DeReg DB Update Seccess. sessionId({}) mgrappId({}) mgrappLoginId({}) deviceToken({})", sessionId,mgrappId,mgrappLoginId ,deviceToken);
								
								}else{
									
									resultCode = ResultCodeUtil.RC_5P000003;
									logger.error("Pushgw Device DeReg Update Fail. sessionId({}) mgrappId({}) mgrappLoginId({}) deviceToken({})", sessionId,mgrappId,mgrappLoginId,deviceToken);
								}
								
							}else{
								resultCode = ResultCodeUtil.RC_5P000001;
								logger.error("Pushgw Device DeReg Fail. sessionId({}) mgrappId({}) mgrappLoginId({}) deviceToken({})", sessionId,mgrappId,mgrappLoginId,deviceToken);
							}
							
						}else{
							resultCode = ResultCodeUtil.RC_5P000000;
							logger.error("Pushgw Connecting Fail. sessionId({}) mgrappId({}) mgrappLoginId({}) deviceToken({})", sessionId,mgrappId,mgrappLoginId,deviceToken);
						}
						
					}else{
						resultCode = ResultCodeUtil.RC_5P000004;
						logger.error("Pushgw Service Unregistered state. sessionId({}) mgrappId({}) mgrappLoginId({}) deviceToken({})", sessionId,mgrappId,mgrappLoginId,deviceToken);
					}
					
				}else{
					resultCode = ResultCodeUtil.RC_6C000002;
					logger.error("Not Exist Mgrapp User Info. sessionId({}) mgrappId({}) mgrappLoginId({})  deviceToken({})", sessionId,mgrappId,mgrappLoginId,deviceToken);
				}
				
			}catch (Exception e) {
				resultCode = ResultCodeUtil.RC_4C005000;
				logger.error("sessionId({}) mgrappLoginId({}) Exception({})", sessionId,mgrappLoginId,e);
			}
		}
		
		response = ResultCodeUtil.createResultMsg(resultCode,api);
		return response;
	}
}
