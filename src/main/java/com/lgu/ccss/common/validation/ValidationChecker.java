package com.lgu.ccss.common.validation;


import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;

import com.lgu.ccss.common.constant.CCSSConst;
import com.lgu.ccss.common.constant.HeaderConst;
import com.lgu.ccss.common.model.RequestCommonDeviceJSON;
import com.lgu.ccss.common.model.RequestCommonJSON;
import com.lgu.ccss.common.model.RequestCommonLogDataJSON;
import com.lgu.ccss.common.model.RequestJSON;
import com.lgu.ccss.common.model.RequestParamReceiverJSON;
import com.lgu.ccss.common.model.RequestParamTargetJSON;
import com.lgu.ccss.common.model.setInfo.JsonSetInfo;
import com.lgu.ccss.common.model.setInfo.MsgSetInfo;
import com.lgu.ccss.common.model.setInfo.NotiSetInfo;
import com.lgu.common.util.JsonUtil;

public class ValidationChecker {
	
	private static final Logger logger = LoggerFactory.getLogger(ValidationChecker.class);
	
	public static CheckResultData checkCommon(RequestCommonJSON common) {
		CheckResultData result = null;
		
		RequestCommonDeviceJSON device = common.getDevice();
		result = checkValue(device.getDeviceType(), "common.device.deviceType");
		if (result.isStatus() == false) {
			return result;
		}
		
		result = checkValue(device.getAppType(), "common.device.appType");
		if (result.isStatus() == false) {
			return result;
		}	
		
		//device.getCarOem();		// optional
		
		RequestCommonLogDataJSON logData = common.getLogData();
		result = checkValue(logData.getClientIp(), "common.logData.clientIp");
		if (result.isStatus() == false) {
			return result;
		}
		
		result = checkValue(logData.getDevInfo(), "common.logData.devInfo");
		if (result.isStatus() == false) {
			return result;
		}
		
		result = checkValue(logData.getOsInfo(), "common.logData.osInfo");
		if (result.isStatus() == false) {
			return result;
		}
		
		result = checkValue(logData.getNwInfo(), "common.logData.nwInfo");
		if (result.isStatus() == false) {
			return result;
		}
		
		result = checkValue(logData.getSvcName(), "common.logData.svcName");
		if (result.isStatus() == false) {
			return result;
		}
		
		result = checkValue(logData.getDevModel(), "common.logData.devModel");
		if (result.isStatus() == false) {
			return result;
		}
		
		result = checkValue(logData.getCarrierType(), "common.logData.carrierType");
		if (result.isStatus() == false) {
			return result;
		}
		
		//logData.getSvcType();	// optional
		//logData.getDevType();	// optional
		
		return result;
	}
	
	public static CheckResultData checkValue(String value, String reason) {		
		CheckResultData result = new CheckResultData();
		if(reason.equals("param.target.roadjibun")) {
			result.setStatus(true);		
			return result;
		}
		if (value == null || value.length() == 0) {			
			result.setStatus(false);
			result.setReason(reason);
			return result;
		}
		result.setStatus(true);		
		return result;
	}
	
	public static CheckResultData checkParamValue(String value, String reason) {		
		List<String> ignoreString = new ArrayList<String>(Arrays.asList(
				RequestJSON.PARAM_ADDRESS,RequestJSON.PARAM_ARRIV_HOPE_TIME,RequestJSON.PARAM_AUTHORIZATION_CODE, 
				RequestJSON.PARAM_CCSS_TOKEN,RequestJSON.PARAM_CTN,RequestJSON.PARAM_DEVICE_NM, 
				RequestJSON.PARAM_DEVICE_TOKEN,RequestJSON.PARAM_END_LATY,RequestJSON.PARAM_END_LONX,
				RequestJSON.PARAM_END_NM,RequestJSON.PARAM_END_REAL_LATY,RequestJSON.PARAM_END_REAL_LONX,
				RequestJSON.PARAM_END_ROAD_JIBUN,RequestJSON.PARAM_END_ROAD_NAME,RequestJSON.PARAM_LATY, 
				RequestJSON.PARAM_LONX,RequestJSON.PARAM_MGR_APP_VER,RequestJSON.PARAM_NAVER_ACCESS_TOKEN,
				RequestJSON.PARAM_NAVER_LOGIN_ID,RequestJSON.PARAM_ONE_ID_KYE,RequestJSON.PARAM_POSX, 
				RequestJSON.PARAM_POSY,RequestJSON.PARAM_RANDOM_KEY,RequestJSON.PARAM_SEARCH_WORD,
				RequestJSON.PARAM_SERIAL,RequestJSON.PARAM_START_ADDRESS,RequestJSON.PARAM_START_LATY,
				RequestJSON.PARAM_START_LONX,RequestJSON.PARAM_START_NM,RequestJSON.PARAM_TARGET,
				RequestJSON.PARAM_TEMP_ONE_ID,RequestJSON.PARAM_UUID,RequestJSON.PARAM_VERSION,
				RequestJSON.PARAM_VIA_LOC_LATY,RequestJSON.PARAM_VIA_LOC_LONX,RequestJSON.PARAM_VIA_LOC_NM,
				RequestJSON.PARAM_X_CORRD,RequestJSON.PARAM_Y_CORRD, RequestJSON.PARAM_END_ADDRESS,
				"svcId", "cekDeviceId", "customId", "cekAccessToken", "sessionId", "ssoKey" , "roadjibun"
				));		
		
		CheckResultData result = new CheckResultData();
		if (value == null || value.length() == 0) {			
			result.setStatus(false);
			result.setReason(reason);
			return result;
		}				
		int forloop = 0 ;
		String subReason = reason.substring(reason.lastIndexOf(".")+1, reason.length());
		for(String key : ignoreString) {			
			if(subReason.equalsIgnoreCase(key) == false) {
				forloop += 1;
			}
		}
		if(forloop == ignoreString.size()) {
			Pattern WhiteSpace = Pattern.compile("[\\s]");
			Pattern specialChar = Pattern.compile("[!@#$%^&*]");
			if(WhiteSpace.matcher(value).find() == true || specialChar.matcher(value).find() == true) {				
				result.setStatus(false);
				result.setReason(reason);
				return result;
			}
			result.setStatus(true);
			return result;
		}		
		
		
		result.setStatus(true);		
		return result;
	}
		
	public static CheckResultData checkValidation(HttpHeaders headers,RequestJSON reqJson,String checkApiNm,List<String> mandatoryList) {
		CheckResultData result = ValidationChecker.checkCommon(reqJson.getCommon());
		
		
		System.out.println("#####################################99-0");
		if(headers != null){
			System.out.println("#####################################99-0-0");
			if(checkApiNm.indexOf("MANAGER") > -1	|| checkApiNm.indexOf("SENDTOCAR") > -1 
					|| checkApiNm.indexOf("SCHEDULE") > -1 ||  checkApiNm.indexOf("MANAGEMENT")  > -1 
					|| checkApiNm.indexOf("SEARCH") > -1  || checkApiNm.indexOf("ARRIVAL") > -1  
					|| checkApiNm.indexOf("SUBSCRIBER") > -1  || checkApiNm.indexOf("MYPAGE") > -1
					|| checkApiNm.indexOf("PUSHGW") > -1		|| checkApiNm.indexOf("AISV") > -1
					 ){
				
				System.out.println("#####################################99-0-1");
				
				if(checkApiNm.equals(CCSSConst.MANAGER_AUTH_LOGIN)){
					System.out.println("#####################################99-0-2");
					String randomKey = headers.getFirst(HeaderConst.HD_NAME_MGRAPP_RANDOM_KEY); 
					result = ValidationChecker.checkValue(randomKey, "randomKey header");
				}else if(checkApiNm.equals(CCSSConst.MANAGER_AUTH_LOGOUT)){
					System.out.println("#####################################99-0-3");
					String sessionId = headers.getFirst(HeaderConst.HD_NAME_MGRAPP_SESSION_ID); 
					result = ValidationChecker.checkValue(sessionId, "sessionId header");
				
					//지도 검색 HearValidatio 추가
				}else if(checkApiNm.equals(CCSSConst.SENDTOCAR_TARGET_SEARCH)  // 목적지 검색 AM
						|| checkApiNm.equals(CCSSConst.SENDTOCAR_TARGET_SEARCH) // 목적지 검색 BM
						|| checkApiNm.equals(CCSSConst.SENDTOCAR_TARGET_ROUTE_SEARCH)  // 경로 탐색 AM
						|| checkApiNm.equals(CCSSConst.SENDTOCAR_TARGET_ROUTE_SEARCH)){  // 경로 탐색 BM
					
					String sessionId = headers.getFirst(HeaderConst.HD_NAME_MGRAPP_SESSION_ID); 
					result = ValidationChecker.checkValue(sessionId, "sessionId header");
					
					if (result.isStatus() == false) {
						return result;
					}
					String randomKey = headers.getFirst(HeaderConst.HD_NAME_MGRAPP_RANDOM_KEY); 
					result = ValidationChecker.checkValue(randomKey, "randomKey header");
					
				}else{
					System.out.println("#####################################99-0-3");
					String sessionId = headers.getFirst(HeaderConst.HD_NAME_MGRAPP_SESSION_ID); 
					result = ValidationChecker.checkValue(sessionId, "sessionId header");
					
					if (result.isStatus() == false) {
						return result;
					}
					String randomKey = headers.getFirst(HeaderConst.HD_NAME_MGRAPP_RANDOM_KEY); 
					result = ValidationChecker.checkValue(randomKey, "randomKey header");
				}
				
				System.out.println("#####################################99-1");
			}else{
				String ccssToken = headers.getFirst(HeaderConst.HD_NAME_CCSS_TOKEN);
				result = ValidationChecker.checkValue(ccssToken, "ccssToken header");
				System.out.println("#####################################99-2");
			}
		}
		
		System.out.println("#####################################99-3");
		if (result.isStatus() == false) {
			return result;
		}
		/*System.out.println("#####################################99-4");
		result = ValidationChecker.checkValue(reqJson.getCommon().getApiId(), "common.apiId");
		
		if (result.isStatus() == false) {
			return result;
		}*/
		System.out.println("#####################################99-5");
		if(checkApiNm !=null && checkApiNm.length() > 0){
			System.out.println("$$$$$$$$$$$$$$$$$$$$$$$ : checkApiNm : "+ checkApiNm);
			System.out.println("$$$$$$$$$$$$$$$$$$$$$$$ : param ApiNm : "+ reqJson.getCommon().getApiId()  );
			if (!checkApiNm.equals(reqJson.getCommon().getApiId())) {
				result = new CheckResultData();
				result.setStatus(false);
				result.setReason("API_ID unmatched");
				return result;
			}
		}
		System.out.println("#####################################99-6");
		if(mandatoryList != null && mandatoryList.size() > 0){
			for (String key : mandatoryList) {
				System.out.println("#######KEY : "+key);
				result = ValidationChecker.checkParamValue(String.valueOf(reqJson.getParam().get(key)), "param." + key);
				if (result.isStatus() == false) {
					return result;
				}
			}
		}
		
		if (reqJson.getParam() == null) {
			result = new CheckResultData();
			result.setStatus(false);
			result.setReason("param is null");
			return result;
		}
		
		try{
			//Target Param Check
			if(reqJson.getParam().get(RequestJSON.PARAM_TARGET) != null){
				
				String reqParamJsonStr = JsonUtil.marshallingJson(reqJson.getParam().get(RequestJSON.PARAM_TARGET));
				RequestParamTargetJSON requestParamTargetJSON = JsonUtil.unmarshallingJson(reqParamJsonStr, RequestParamTargetJSON.class);
			 	Object obj=requestParamTargetJSON;
			 	for (Field field : obj.getClass().getDeclaredFields()){
			 		field.setAccessible(true);
			 		Object value;
					try {
						value = field.get(obj);
						
						result = ValidationChecker.checkValue(String.valueOf(value), "param.target." + field.getName());		
						
						if (result.isStatus() == false) {
							return result;
						}
					} catch (IllegalArgumentException | IllegalAccessException e) {
						logger.error("Target Param Exception !!" ,e);
					}
				}
			}
			//Reciver
			else if(reqJson.getParam().get(RequestJSON.PARAM_RECEIVER) != null){
				//String reqParamJsonStr = JsonUtil.marshallingJson(reqJson.getParam().get(RequestJSON.PARAM_RECEIVER));
				//RequestParamListReceiverJSON requestParamReceiverJSON =  JsonUtil.unmarshallingJson(reqParamJsonStr, RequestParamListReceiverJSON.class);
				@SuppressWarnings("unchecked")
				List<RequestParamReceiverJSON>  requestParamReceiverJSONList = (List<RequestParamReceiverJSON>) reqJson.getParam().get(RequestJSON.PARAM_RECEIVER);
				
				for(int idx=0; idx < requestParamReceiverJSONList.size(); idx++){
					String reqParamJsonStr = JsonUtil.marshallingJson(requestParamReceiverJSONList.get(idx));
					RequestParamReceiverJSON obj = JsonUtil.unmarshallingJson(reqParamJsonStr, RequestParamReceiverJSON.class);
					
					for (Field field : obj.getClass().getDeclaredFields()){
				 		field.setAccessible(true);
				 		Object value;
						try {
							value = field.get(obj);
							result = ValidationChecker.checkValue(String.valueOf(value), "param.receiver["+idx+"]." + field.getName());
							
							if (result.isStatus() == false) {
								return result;
							}
						} catch (IllegalArgumentException | IllegalAccessException e) {
							logger.error("Reciver Param Check Exception !!" ,e);
						}
					}
				}
				
			}else if(reqJson.getParam().get(RequestJSON.PARAM_MGR_JOIN_SET_INFO) != null){
				String reqParamJsonStr = JsonUtil.marshallingJson(reqJson.getParam().get(RequestJSON.PARAM_MGR_JOIN_SET_INFO));
				JsonSetInfo  reqParamJsonSetInfo= JsonUtil.unmarshallingJson(reqParamJsonStr, JsonSetInfo.class);
			 	Object obj=reqParamJsonSetInfo;
			 	for (Field field : obj.getClass().getDeclaredFields()){
			 		field.setAccessible(true);
			 		Object value;
					try {
						value = field.get(obj);
						for (Field subField : value.getClass().getDeclaredFields()){
							subField.setAccessible(true);
							if(value instanceof MsgSetInfo){
								result = ValidationChecker.checkValue(String.valueOf(value), "param.jsonSetInfo.message" + subField.getName());
							}else if(value instanceof NotiSetInfo){
								result = ValidationChecker.checkValue(String.valueOf(value), "param.jsonSetInfo.notifications." + subField.getName());
							}
						}
						if (result.isStatus() == false) {
							return result;
						}
					} catch (IllegalArgumentException | IllegalAccessException e) {
						logger.error("JsonSetInfo Param Check Exception !!" ,e);
					}
				}
			}else{
				//Nothing
			}
			
			System.out.println("#####################################99-7-0");
		}catch(Exception e){
			logger.error("Request Sub Param Check Exception !!" ,e);
		}
		
		System.out.println("#####################################99-7-1");
		System.out.println("#####################################99-8 Result :" +result);
		return result;
	} 

}
