package com.lgu.ccss.mgr.device.service.search;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;

import com.lgu.ccss.common.constant.CCSSConst;
import com.lgu.ccss.common.constant.HeaderConst;
import com.lgu.ccss.common.dao.DeviceDao;
import com.lgu.ccss.common.dao.MemberDao;
import com.lgu.ccss.common.dao.mgr.MgrAppDeviceDao;
import com.lgu.ccss.common.model.ConnDeviceVO;
import com.lgu.ccss.common.model.MembData;
import com.lgu.ccss.common.model.MembVO;
import com.lgu.ccss.common.model.RequestJSON;
import com.lgu.ccss.common.model.ResponseJSON;
import com.lgu.ccss.common.tlo.TloData;
import com.lgu.ccss.common.tlo.TloUtil;
import com.lgu.ccss.common.util.CCSSUtil;
import com.lgu.ccss.common.util.ResultCodeUtil;
import com.lgu.ccss.common.validation.CheckResultData;
import com.lgu.ccss.common.validation.ValidationChecker;
import com.lgu.ccss.mgr.device.model.MgrDeviceInfo;
import com.lgu.ccss.mgr.device.model.ResultMgrDeviceJSON;
import com.lgu.common.model.ResultCode;
import com.lgu.common.ncas.NCASConst;
import com.lgu.common.ncas.NCASErrorCode;
import com.lgu.common.ncas.NCASQueryManager;
import com.lgu.common.ncas.NCASResultData;
import com.lgu.common.ncas.SubsInfo;
import com.lgu.common.util.AES256Util;
import com.lgu.common.util.ExceptionUtil;

@Service("mgrDeviceListService")
public class MgrDeviceListServiceImpl implements MgrDeviceListService {

	private static final Logger logger = LoggerFactory.getLogger(MgrDeviceListServiceImpl.class);
	private static final List<String> mandatoryList = new ArrayList<String>();

	@Autowired
	private DeviceDao deviceDao;
	
	@Autowired
	private MemberDao memberDao;
	
	@Autowired
	private NCASQueryManager nCASQueryManager;
	// @Autowired
	// private MgrAuthLoginServiceImpl mgrAuthLoginServiceImpl;
	
	@Autowired
	private MgrAppDeviceDao mgrAppDeviceDao;
	
	@Override
	public ResponseJSON doService(HttpHeaders headers, RequestJSON reqJson) {
		ResultCode resultCode = ResultCodeUtil.RC_2S000000;
		String api = reqJson.getCommon().getApiId();
		String mgrappLoginId = CCSSUtil.getMgrUserLoginId();
		String sessionId = CCSSUtil.getMgrSessionId();
		String mgrappId = CCSSUtil.getMgrappId();
		String randomKey = headers.getFirst(HeaderConst.HD_NAME_MGRAPP_RANDOM_KEY);
		String membId = CCSSUtil.getMembId();
		String deviceType = reqJson.getCommon().getDevice().getDeviceType();
		
		ResponseJSON response = null;
		List<MgrDeviceInfo> resultMgrDeviceJSONList = null;

		CheckResultData result = ValidationChecker.checkValidation(headers, reqJson, CCSSConst.MANAGER_DEVICE_LIST,
				mandatoryList);

		if (result.isStatus() == false) {
			resultCode = ResultCodeUtil.RC_3C004000;
			response = ResultCodeUtil.createResultMsg(resultCode, api, result.getReason());
			return response;

		} else {

			try {

				/*
				 * 1. mgrappId으로 Device List 조회
				 */
				//List<ConnDeviceVO> deviceList = deviceDao.getDeviceInfoListByUserCtn(mgrappLoginId);
				if(deviceType.equals("PND")) {
					List<ConnDeviceVO> deviceList = deviceDao.getDeviceInfoListByMgrappId(mgrappId);
					
					if (deviceList != null && deviceList.size() > 0) {
						resultMgrDeviceJSONList = new ArrayList<MgrDeviceInfo>();

						int idx = 1;
						for (ConnDeviceVO tempVO : deviceList) {
							MgrDeviceInfo resultMgrDeviceJSON = new MgrDeviceInfo();
							resultMgrDeviceJSON.setDeviceIndex(idx);
							resultMgrDeviceJSON.setConnDeviceId(tempVO.getConnDeviceId());
							resultMgrDeviceJSON.setMembId(tempVO.getMembId());
							resultMgrDeviceJSON.setDeviceCtn(AES256Util.AESEncode(randomKey ,tempVO.getDeviceCtn()));
							resultMgrDeviceJSON.setDeviceNm(tempVO.getDeviceNm());
							resultMgrDeviceJSON.setDeviceModel(tempVO.getDeviceModelId());
							resultMgrDeviceJSON.setSerial(tempVO.getUiccId());
							resultMgrDeviceJSON.setJoinDt(tempVO.getJoinDt());
							resultMgrDeviceJSON.setUseStatus(tempVO.getUseStatus());
							resultMgrDeviceJSON.setClovaAccessToken(tempVO.getClovaAccessToken());
							resultMgrDeviceJSON.setNid(tempVO.getNid());
							/*if(deviceList.size() == 1 ){
								int count = mgrAppDeviceDao.updateMainUseYn(tempVO.getMembId(), mgrappId,CCSSConst.DEF_YES);
								
								if(count > 0){
									logger.info("Device List Size is Zero Default Main Use Device Set. sessionId({}) mgrappLoginId({}) membId({}) ", sessionId, mgrappLoginId, tempVO.getMembId());
								}
								
								resultMgrDeviceJSON.setMainUseYn(CCSSConst.DEF_YES);
								
							}else{
								resultMgrDeviceJSON.setMainUseYn(tempVO.getMainUseYn());
							}*/
							resultMgrDeviceJSON.setMainUseYn(tempVO.getMainUseYn());
							resultMgrDeviceJSON.setTransToken(tempVO.getTransToken());
							resultMgrDeviceJSONList.add(resultMgrDeviceJSON);

							idx++;
						}
						
						
						setTloData(deviceList.get(0));
						ConnDeviceVO mgrAppDeviceVO= new ConnDeviceVO();
	                     boolean isMainUse = false;
	                     for (int i = 0; i < deviceList.size(); i++) {   
	                        mgrAppDeviceVO = deviceList.get(i);
	                        if (mgrAppDeviceVO.getMainUseYn().equalsIgnoreCase("Y")) {
	                           isMainUse = true;
	                           membId = mgrAppDeviceVO.getMembId();
	                           break;
	                        }
	                     }
	                     if (!isMainUse) {
	                        //주기기 없음
	                        logger.error("No Service Suspence.  mgrappLoginId({})",mgrappLoginId);
	                          resultCode = ResultCodeUtil.RC_3C002010;
	                          ResultMgrDeviceJSON resultMgrDevice = new ResultMgrDeviceJSON();
	      					resultMgrDevice.setDevice(resultMgrDeviceJSONList);
	      					
	      					response = ResultCodeUtil.createResultMsg(resultCode, resultMgrDevice, api);
	      					return response;
	                     }
	                     
	                     if (!membId.isEmpty()) {
	                         MembVO membVO = memberDao.selectMemberByID(membId);
	                         if(membVO == null) {
	                               //디비에 가입자 정보가 없는경우.
	                               logger.error("No Main Device. mgrappLoginId({})",mgrappLoginId);
	                               resultCode = ResultCodeUtil.RC_3C002004;
	                               ResultMgrDeviceJSON resultMgrDevice = new ResultMgrDeviceJSON();
	           					resultMgrDevice.setDevice(resultMgrDeviceJSONList);
	           					
	           					response = ResultCodeUtil.createResultMsg(resultCode, resultMgrDevice, api);
	           					return response;
	                         }
	                         
	                        String deviceCtn = membVO.getMembCtn();
	                        MembData membData = new MembData();
	                        resultCode = getNcasSubsInfo(deviceCtn, membData);
	                        if(!ResultCodeUtil.RC_2S000000.equals(resultCode)) {
	                           if (deviceList.size() >= 2) {
	                                 //주기기 서비스 해지
	                                 logger.error("No Service Suspence. UUID({}) mgrappLoginId({})",mgrappLoginId);
	                                 resultCode = ResultCodeUtil.RC_3C002009;
	                                ResultMgrDeviceJSON resultMgrDevice = new ResultMgrDeviceJSON();
	             					resultMgrDevice.setDevice(resultMgrDeviceJSONList);
	             					
	             					response = ResultCodeUtil.createResultMsg(resultCode, resultMgrDevice, api);
	             					return response;
	                            } else {
	                               //서비스 해지 
	                               logger.error("No Service Suspence. UUID({}) mgrappLoginId({})",mgrappLoginId);
	                               resultCode = ResultCodeUtil.RC_3C002009;
	                               ResultMgrDeviceJSON resultMgrDevice = new ResultMgrDeviceJSON();
	           					resultMgrDevice.setDevice(resultMgrDeviceJSONList);
	           					
	           					response = ResultCodeUtil.createResultMsg(resultCode, resultMgrDevice, api);
	           					return response;
	                            }
	                         }
	                         
	                         //  서비스해지 끝
	                     }
					} else {
						resultCode = ResultCodeUtil.RC_6C000003;
						logger.error("Not Exist Device Information mgrappLoginId({}) ", mgrappLoginId);
					}

				}else {
					List<ConnDeviceVO> deviceList = deviceDao.getDeviceInfoListByMgrappId(mgrappId);
					
					if (deviceList != null && deviceList.size() > 0) {
						resultMgrDeviceJSONList = new ArrayList<MgrDeviceInfo>();

						int idx = 1;
						for (ConnDeviceVO tempVO : deviceList) {
							MgrDeviceInfo resultMgrDeviceJSON = new MgrDeviceInfo();
							resultMgrDeviceJSON.setDeviceIndex(idx);
							resultMgrDeviceJSON.setConnDeviceId(tempVO.getConnDeviceId());
							resultMgrDeviceJSON.setMembId(tempVO.getMembId());
							resultMgrDeviceJSON.setDeviceCtn(AES256Util.AESEncode(randomKey ,tempVO.getDeviceCtn()));
							resultMgrDeviceJSON.setDeviceNm(tempVO.getDeviceNm());
							resultMgrDeviceJSON.setDeviceModel(tempVO.getDeviceModelId());
							resultMgrDeviceJSON.setSerial(tempVO.getUiccId());
							resultMgrDeviceJSON.setJoinDt(tempVO.getJoinDt());
							resultMgrDeviceJSON.setUseStatus(tempVO.getUseStatus());
							resultMgrDeviceJSON.setClovaAccessToken(tempVO.getClovaAccessToken());
							resultMgrDeviceJSON.setNid(tempVO.getNid());
							if(deviceList.size() == 1 ){
								int count = mgrAppDeviceDao.updateMainUseYn(tempVO.getMembId(), mgrappId,CCSSConst.DEF_YES);
								
								if(count > 0){
									logger.info("Device List Size is Zero Default Main Use Device Set. sessionId({}) mgrappLoginId({}) membId({}) ", sessionId, mgrappLoginId, tempVO.getMembId());
								}
								
								resultMgrDeviceJSON.setMainUseYn(CCSSConst.DEF_YES);
								
							}else{
								resultMgrDeviceJSON.setMainUseYn(tempVO.getMainUseYn());
							}
							//resultMgrDeviceJSON.setMainUseYn(tempVO.getMainUseYn());
							resultMgrDeviceJSON.setTransToken(tempVO.getTransToken());
							resultMgrDeviceJSON.setClovaClientId(tempVO.getClovaClientId());
							resultMgrDeviceJSON.setClovaClientSecret(tempVO.getClovaClientSecret());
							resultMgrDeviceJSONList.add(resultMgrDeviceJSON);

							idx++;
						}
						
						
						setTloData(deviceList.get(0));
						
					} else {
						resultCode = ResultCodeUtil.RC_6C000003;
						logger.error("Not Exist Device Information mgrappLoginId({}) ", mgrappLoginId);
					}
				}
				
			} catch (Exception e) {
				resultCode = ResultCodeUtil.RC_4C005000;
				logger.error("sessionId({}) mgrappLoginId({}) Exception({})", sessionId, mgrappLoginId, ExceptionUtil.getPrintStackTrace(e));
			}
		}

		ResultMgrDeviceJSON resultMgrDevice = new ResultMgrDeviceJSON();
		resultMgrDevice.setDevice(resultMgrDeviceJSONList);
		
		response = ResultCodeUtil.createResultMsg(resultCode, resultMgrDevice, api);
		return response;
	}

	private void setTloData(ConnDeviceVO connDeviceVO) {
		if (connDeviceVO == null) {
			return;
		}
		Map<String, String> tlo = new HashMap<String, String>();
		tlo.put(TloData.MEMB_ID, connDeviceVO.getMembId());
		// tlo.put(TloData.MEMB_NO, memb.getMembNo());
		TloUtil.setTloData(tlo);
	}
	
	private ResultCode getNcasSubsInfo(String deviceCtn, MembData membData) {
	      
	      NCASResultData ncasData = nCASQueryManager.query(deviceCtn);
	      if(ncasData == null) {
	         logger.error("failed to query NCAS data.deviceCtn({})",deviceCtn);
	         return ResultCodeUtil.RC_5N000001;
	      }
	      
	      if (NCASErrorCode.ERRORCODE_NO_LGT.equals(ncasData.getRespCode())) {
	         logger.error("No subscriber. deviceCtn({}) respCode({})", deviceCtn, ncasData.getRespCode());
	         return ResultCodeUtil.RC_3C002004;
	      }
	      
	      if(!NCASErrorCode.ERRORCODE_SUCCESS.equals(ncasData.getRespCode())) {
	         logger.error("Abnormal suvscriber. deviceCtn({}) respCode({})" , deviceCtn ,ncasData.getRespCode());
	         return ResultCodeUtil.RC_5N000000;
	      }
	      
	      SubsInfo subsInfo = ncasData.getSubsInfo();
	      if(subsInfo == null) {
	         logger.error("failed to get SuvsInfo data.deviceCtn({})",deviceCtn);
	         return ResultCodeUtil.RC_5N000000;
	      }
	      
	      if(subsInfo.getCtn_stus_code().equals(NCASConst.CTN_STUS_CODE_SUSPEND)) {
	         logger.error("Subscriber is suspended. deviceCtn({})", deviceCtn);
	         return ResultCodeUtil.RC_3C002005;
	      }

	      if (logger.isDebugEnabled()) {
	         logger.debug("deviceCtn({}) SUBS_INFO({})", deviceCtn, subsInfo);
	      }
	      return ResultCodeUtil.RC_2S000000;
	   }
}
