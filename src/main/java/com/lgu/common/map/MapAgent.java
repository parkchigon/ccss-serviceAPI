package com.lgu.common.map;

import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.http.protocol.HTTP;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.lgu.ccss.common.model.RequestJSON;
import com.lgu.common.map.model.ReqRevgeocodingSearchJSON;
import com.lgu.common.map.model.ReqRouthSearchJSON;
import com.lgu.common.map.model.ReqTargetSearchJSON;
import com.lgu.common.map.model.ReqTotalSearchJSON;
import com.lgu.common.map.model.ResRevgeocodingSearchJSON;
import com.lgu.common.map.model.ResRouthSearchJSON;
import com.lgu.common.map.model.ResTargetSearchJSON;
import com.lgu.common.map.model.ResTotalSearchJSON;
import com.lgu.common.map.model.findStatRoute.ExtensionDataJSON;
import com.lgu.common.map.model.findStatRoute.LocDataAmJSON;
import com.lgu.common.map.model.findStatRoute.ReqFindStatRouthSearchAmJSON;
import com.lgu.common.map.model.findStatRoute.ReqFindStatRouthSearchJSON;
import com.lgu.common.map.model.findStatRoute.ResFindStatRouthSearchJSON;
import com.lgu.common.map.model.findStatRoute.ResMapDownloadInfoJSON;
import com.lgu.common.map.model.findStatRoute.StatRouteJSON;
import com.lgu.common.map.model.route.LocDataJSON;
import com.lgu.common.rest.RestAgent;
import com.lgu.common.rest.RestRequestData;
import com.lgu.common.rest.RestResultData;
import com.lgu.common.util.AES256Util;
import com.lgu.common.util.DateUtils;
import com.lgu.common.util.ExceptionUtil;

@Component
public class MapAgent {
	private static final Logger logger = LoggerFactory.getLogger(MapAgent.class);
	private static final Gson gson = new Gson();

	@Value("#{config['map.infra.encrypt.key']}")
	private String mapEncryptKey;
	
	@Value("#{config['am.map.infra.url']}")
	private String amMapInfraUrl;
	
	@Value("#{config['bm.map.infra.url']}")
	private String bmMapInfraUrl;
	
	@Value("#{config['am.map.infra.svc.id']}")
	private String amMapInfraSvcId;
	
	@Value("#{config['am.mapapi.auth.key']}")
	private String amMapapiAuthKey;
	@Value("#{config['am.routh.auth.key']}") 
	private String amRouthAuthKey;
	@Value("#{config['am.poi.auth.key']}") 
	private String amPoiAuthKey;
	@Value("#{config['am.location.auth.key']}")
	private String amLocationAuthKey;

	@Value("#{config['bm.map.infra.svc.id']}")
	private String bmMapInfraSvcId;
	
	@Value("#{config['bm.mapapi.auth.key']}")
	private String bmMapapiAuthKey;
	@Value("#{config['bm.routh.auth.key']}") 
	private String bmRouthAuthKey;
	@Value("#{config['bm.poi.auth.key']}") 
	private String bmPoiAuthKey;
	@Value("#{config['bm.location.auth.key']}")
	private String bmLocationAuthKey;
	
	@Value("#{config['timeMachine.map.infra.svc.id']}")
	private String timeMachineMapInfraSvcId;
	@Value("#{config['timeMachine.mapapi.auth.key']}")
	private String timeMachineMapapiAuthKey;
	@Value("#{config['timeMachine.routh.auth.key']}") 
	private String timeMachineRouthAuthKey;
	@Value("#{config['timeMachine.poi.auth.key']}") 
	private String timeMachinePoiAuthKey;
	@Value("#{config['timeMachine.location.auth.key']}")
	private String timeMachineLocationAuthKey;
	
	
	@Value("#{config['map.infra.connection.timeout']}")
	private String mapInfraConnectionTimeout;

	@Value("#{config['map.infra.timeout']}")
	private String mapInfraTimeout;
	
	
	@Value("#{config['map.infra.mrVersion']}")
	private String mapInfraMrverSion;
	
	@Value("#{config['map.proxy.http.proxyHost']}")
	private String mapHttpProxyHost;
	
	@Value("#{config['map.proxy.http.proxySubHost']}")
	private String mapHttpProxySubHost;
	
	@Value("#{config['map.proxy.http.proxyPort']}")
	private int mapHttpProxyPort;
	
	@Autowired
	private RestAgent restAgent;
	
	public RestRequestData makeHeaderData(RequestJSON reqJson,String url,Map<String,String> svcIdAuthKeyMap){
		// set headers 
		RestRequestData reqData = new RestRequestData(url);
		reqData.setHeader(MapConst.HD_NAME_API_VERSION, MapConst.API_VERSION); //M
		reqData.setHeader(MapConst.HD_NAME_API_TYPE, MapConst.API_TYPE_OPEN_API);  //M
		reqData.setHeader(MapConst.HD_NAME_CLIENT_IP, "");
		reqData.setHeader(MapConst.HD_NAME_DEV_INFO, MapConst.DEV_INFO_PHONE);	//M
		reqData.setHeader(MapConst.HD_NAME_OS_INFO, "");
		reqData.setHeader(MapConst.HD_NAME_NW_INFO, "");
		reqData.setHeader(MapConst.HD_NAME_DEV_MODEL, "");
		reqData.setHeader(MapConst.HD_NAME_CARRIER_TYPE, "");
		reqData.setHeader(MapConst.HD_NAME_TEL_NO, "");	 	//C:???????????? ?????? ??????(CTN) 
		reqData.setHeader(MapConst.HD_NAME_AUTH_KEY, svcIdAuthKeyMap.get(MapConst.HD_NAME_AUTH_KEY));	//M
		reqData.setHeader(MapConst.HD_NAME_SVC_ID, svcIdAuthKeyMap.get(MapConst.HD_NAME_SVC_ID));	//M
		//String xCoord = (String) reqJson.getParam().get(RequestJSON.PARAM_X_CORRD);
		/*if(xCoord !=null && xCoord.length() > 0){
			reqData.setHeader(MapConst.HD_NAME_X_COORD, xCoord);	//O:???????????? ?????? ??????(CTN) 
		}else{*/
			reqData.setHeader(MapConst.HD_NAME_X_COORD, "");	
		//}
		/*String yCoord = (String) reqJson.getParam().get(RequestJSON.PARAM_Y_CORRD);
		if(yCoord !=null && yCoord.length() > 0){
			reqData.setHeader(MapConst.HD_NAME_Y_COORD, yCoord);	//O:???????????? ?????? ??????(CTN) 
		}else{*/
			reqData.setHeader(MapConst.HD_NAME_Y_COORD, "");	
		//}
	
		reqData.setHeader(MapConst.HD_NAME_SPEED, "");
		reqData.setHeader(MapConst.HD_NAME_GPS_TIME, "");
		reqData.setHeader(MapConst.HD_NAME_VALID_YN, "");
		
		reqData.setHeader(HTTP.CONTENT_TYPE,MapConst.HD_VALUE_CONTENTTYPE_JSON_UTF8);
		return reqData;
	}
	
	public ResTargetSearchJSON targetSearch(RequestJSON reqJson,String subUrl,String svcType,String deviceType, String mgrappLoginId) {
		//get Terget Service Key Map
		Map<String,String> svcIdAuthKeyMap = getMapSvcIdAndAuthKey(deviceType,svcType);
		
		// set request url
		String url = svcIdAuthKeyMap.get(MapConst.DEF_URL) +  subUrl;
		
		// set headers 
		RestRequestData reqData = makeHeaderData(reqJson,url,svcIdAuthKeyMap);
		
		// set body
		ReqTargetSearchJSON reqTargetSearchJSON = new ReqTargetSearchJSON();
		reqTargetSearchJSON.setCutflag("0"); // 0: ?????? ????????? ?????? 1: ?????? ????????? ??????
		reqTargetSearchJSON.setCoordtype("1"); // 0: ???????????? ?????? 1: WGS84 2. TM(????????????)
		reqTargetSearchJSON.setStartposition((String) reqJson.getParam().get(RequestJSON.PARAM_START_POSITION));
		reqTargetSearchJSON.setReqcount((String) reqJson.getParam().get(RequestJSON.PARAM_REQ_COUNT));
		reqTargetSearchJSON.setDepth("0"); //0 : depth ?????? ??????(???????????? ?????? ??????)(default)  1 : 1 depth ??? ??????(????????? depth) 2 : 2 depth ?????? ?????? 3 : 3 depth ?????? ??????
		reqTargetSearchJSON.setQuery((String) reqJson.getParam().get(RequestJSON.PARAM_SEARCH_WORD));
		reqTargetSearchJSON.setOption("8");    // 1: ADM  4: categorycode 	8: Name	   ???????????? ??????	ex)ADM+NAME = 9
		reqTargetSearchJSON.setNameopt("7"); // ???????????? option (option??? Name??? ????????? ?????? ?????? ???.)
		//1 : Prefix Match 		2 : Postfix Match		4 : Innerfix Match		7 : All (Prefix, Postfix, Innerfix Match)(default)
		reqTargetSearchJSON.setAdmopt("0");  // ????????????????????????		(option??? ADM??? ????????? ?????? ?????????)		0 :?????????????????? ?????? ??????(default)		1 : ???????????? ??????		2 :????????? ??????(???????????? ??????)		3 :????????? ??????(?????????/????????? ??????)
		reqTargetSearchJSON.setAdm("");
		reqTargetSearchJSON.setCatecode("");
		reqTargetSearchJSON.setSpopt("0");
		reqTargetSearchJSON.setRadius("");
		reqTargetSearchJSON.setMid("");
		reqTargetSearchJSON.setX1("");
		reqTargetSearchJSON.setY1("");
		reqTargetSearchJSON.setX2("");
		reqTargetSearchJSON.setY2("");
		reqTargetSearchJSON.setSortopt((String) reqJson.getParam().get(RequestJSON.PARAM_SORTOPT));
		
		reqData.setJson(gson.toJson(reqTargetSearchJSON));

		// set trace info
		setTraceInfo(reqData, mgrappLoginId);

		// set params
		reqData.setConnectionTimeout(Integer.parseInt(mapInfraConnectionTimeout));
		reqData.setTimeout(Integer.parseInt(mapInfraTimeout));

		return targetSearchMapApiRequest(reqData, mgrappLoginId);
	}

	
	private ResTargetSearchJSON targetSearchMapApiRequest(RestRequestData reqData, String mgrappLoginId) {
			
		RestResultData resultData = restAgent.requestProxy(reqData, mapHttpProxyHost, mapHttpProxySubHost, mapHttpProxyPort);
		if (resultData == null) {
			logger.error("failed to request Map Infra Server. mgrappLoginId({})", mgrappLoginId);
			return null;
		}

		if (resultData.getStatusCode() != HttpStatus.SC_OK) {
			logger.error("failed to request Map Infra Server. mgrappLoginId({}) statusCode({})", mgrappLoginId, resultData.getStatusCode());
			return null;
		}

		ResTargetSearchJSON resTargetSearchJSON = new ResTargetSearchJSON();
		try {
			resTargetSearchJSON = gson.fromJson(resultData.getJson(), ResTargetSearchJSON.class);

		} catch (JsonSyntaxException e) {
			logger.error("JSON({}) Exception({})", resultData.getJson(), ExceptionUtil.getPrintStackTrace(e));
			return null;
		}

		/*if (!resTargetSearchJSON.getResult_code().equals(MapConst.RESULT_SUCCESS)){ //??????
			logger.error("failed to request Map Infra data. resTargetSearchJSON({})", resTargetSearchJSON);
			return null;
		}*/
		
		return resTargetSearchJSON;
	}
	
	
	public ResRouthSearchJSON routeSearch(RequestJSON reqJson,String subUrl,String svcType,String deviceType, String mgrappLoginId) {
		//get Terget Service Key Map
		Map<String,String> svcIdAuthKeyMap = getMapSvcIdAndAuthKey(deviceType,svcType);
		
		// set request url
		String url = svcIdAuthKeyMap.get(MapConst.DEF_URL) +  subUrl;
		
		// set headers 
		RestRequestData reqData = makeHeaderData(reqJson,url,svcIdAuthKeyMap);
		
		// set body
		ReqRouthSearchJSON reqRouthSearchJSON = new ReqRouthSearchJSON();
		
		//?????? VER ?????? smr_ver
		
		ResMapDownloadInfoJSON resMapDownloadInfoJSON = new ResMapDownloadInfoJSON();
		resMapDownloadInfoJSON = mapDownloadInfo(reqJson,MapConst.SVC_MAP,deviceType); 
		if(resMapDownloadInfoJSON !=null){
			reqRouthSearchJSON.setMrVersion(resMapDownloadInfoJSON.getSmr_ver());
			//reqRouthSearchJSON.setMrVersion(mapInfraMrverSion);
			reqRouthSearchJSON.setCallBack("func_cb");
			//reqRouthSearchJSON.setSearchOption("real_traffic"); //real_traffic : ????????? real_traffic2 : ????????? real_traffic_freeroad :???????????? short_distance_priority :???????????? highway_priority :??????????????????  motorcycle :????????? ????????????
			reqRouthSearchJSON.setSearchOption((String) reqJson.getParam().get(RequestJSON.PARAM_SEARCH_OPTION)); //real_traffic : ????????? real_traffic2 : ????????? real_traffic_freeroad :???????????? short_distance_priority :???????????? highway_priority :??????????????????  motorcycle :????????? ????????????
			
			reqRouthSearchJSON.setCarType("");
			reqRouthSearchJSON.setCarHeight("");
			reqRouthSearchJSON.setCarWeight("");
			reqRouthSearchJSON.setCarWaterProtect("");
			
			LocDataJSON startLocData = new LocDataJSON(); //PARAM_SEARCH_OPTION
			startLocData.setLonx((String) reqJson.getParam().get(RequestJSON.PARAM_START_LONX));
			startLocData.setLaty((String) reqJson.getParam().get(RequestJSON.PARAM_START_LATY));
			startLocData.setName((String) reqJson.getParam().get(RequestJSON.PARAM_START_NM));
			
			reqRouthSearchJSON.setNewStartloc(startLocData);
			
			LocDataJSON endLocData = new LocDataJSON();
			endLocData.setLonx((String) reqJson.getParam().get(RequestJSON.PARAM_END_LONX));
			endLocData.setLaty((String) reqJson.getParam().get(RequestJSON.PARAM_END_LATY));
			endLocData.setName((String) reqJson.getParam().get(RequestJSON.PARAM_END_NM));
			reqRouthSearchJSON.setNewEndloc(endLocData);
			
			String viaNm = (String) reqJson.getParam().get(RequestJSON.PARAM_VIA_LOC_NM);
			
			if(viaNm !=null && viaNm.length() > 0){  //?????? ????????? 1??????...
				LocDataJSON viaLocData = new LocDataJSON();
				viaLocData.setLonx((String) reqJson.getParam().get(RequestJSON.PARAM_VIA_LOC_LONX));
				viaLocData.setLaty((String) reqJson.getParam().get(RequestJSON.PARAM_VIA_LOC_LATY));
				viaLocData.setName((String) reqJson.getParam().get(RequestJSON.PARAM_VIA_LOC_NM));
				
				List<LocDataJSON> addViaLocList = new LinkedList<LocDataJSON>();
				addViaLocList.add(viaLocData);
				reqRouthSearchJSON.setNewVialocList(addViaLocList);
			}
			reqData.setJson(gson.toJson(reqRouthSearchJSON));
	
			// set trace info
			setTraceInfo(reqData, mgrappLoginId);
	
			// set params
			reqData.setConnectionTimeout(Integer.parseInt(mapInfraConnectionTimeout));
			reqData.setTimeout(Integer.parseInt(mapInfraTimeout));
	
			return routeSearchMapApiRequest(reqData, mgrappLoginId,deviceType);
			}else{
				logger.error("failed to request Map Infra Server - mapDownloadInfo. mgrappLoginId({})", mgrappLoginId);
				return null;
			}
		
		
	}
	
	private ResRouthSearchJSON routeSearchMapApiRequest(RestRequestData reqData, String mgrappLoginId, String deviceType) {
		
		RestResultData resultData = null;
		
		try {
		resultData = restAgent.requestProxy(reqData, mapHttpProxyHost, mapHttpProxySubHost, mapHttpProxyPort);
		} catch (Exception e) {
			logger.error("RESULTDATA({}) Exception({})", resultData.getJson(), e);
			return null;
		}
		if (resultData == null) {
			logger.error("failed to request Map Infra Server. mgrappLoginId({})", mgrappLoginId);
			return null;
		}

		if (resultData.getStatusCode() != HttpStatus.SC_OK) {
			logger.error("failed to request Map Infra Server. mgrappLoginId({}) statusCode({})", mgrappLoginId, resultData.getStatusCode());
			return null;
		}

		ResRouthSearchJSON resRouthSearchJSON = new ResRouthSearchJSON();
		try {
			resRouthSearchJSON = gson.fromJson(resultData.getJson(), ResRouthSearchJSON.class);
			resRouthSearchJSON.setDeviceType(deviceType);

		} catch (JsonSyntaxException e) {
			logger.error("JSON({}) Exception({})", resultData.getJson(), e);
			return null;
		}
		
		return resRouthSearchJSON;
	}
	
	//????????? ?????? ??????
	public ResRevgeocodingSearchJSON revgeocodingSearch(RequestJSON reqJson,String subUrl,String svcType,String deviceType, String mgrappLoginId) {
		//get Terget Service Key Map
		Map<String,String> svcIdAuthKeyMap = getMapSvcIdAndAuthKey(deviceType,svcType);
		
		// set request url
		String url = svcIdAuthKeyMap.get(MapConst.DEF_URL) +  subUrl;
		
		// set headers 
		RestRequestData reqData = makeHeaderData(reqJson,url,svcIdAuthKeyMap);
		
		// set body
		ReqRevgeocodingSearchJSON reqRevgeocodingSearchJSON = new ReqRevgeocodingSearchJSON();
		
		reqRevgeocodingSearchJSON.setCutflag("0"); // ????????? 0 ??????
		reqRevgeocodingSearchJSON.setCoordtype("1"); // 0: ???????????? ?????? 1: WGS84 2. TM(????????????)
		reqRevgeocodingSearchJSON.setStartposition("0"); //????????? 0 ??????
		reqRevgeocodingSearchJSON.setReqcount("0"); //????????? 0 ??????
		reqRevgeocodingSearchJSON.setPosx((String) reqJson.getParam().get(RequestJSON.PARAM_POSX));
		reqRevgeocodingSearchJSON.setPosy((String) reqJson.getParam().get(RequestJSON.PARAM_POSY));
		
		reqData.setJson(gson.toJson(reqRevgeocodingSearchJSON));

		// set trace info
		setTraceInfo(reqData, mgrappLoginId);

		// set params
		reqData.setConnectionTimeout(Integer.parseInt(mapInfraConnectionTimeout));
		reqData.setTimeout(Integer.parseInt(mapInfraTimeout));

		return revgeocodingSearchMapApiRequest(reqData, mgrappLoginId, deviceType);
	}

	
	private ResRevgeocodingSearchJSON revgeocodingSearchMapApiRequest(RestRequestData reqData, String mgrappLoginId, String deviceType) {
			
		RestResultData resultData = null;
		try {
		resultData = restAgent.requestProxy(reqData, mapHttpProxyHost, mapHttpProxySubHost, mapHttpProxyPort);
		} catch (Exception e) {
			logger.error("RESULTDATA({}) Exception({})", resultData.getJson(), e);
			return null;
		}
		if (resultData == null) {
			logger.error("failed to request Map Infra Server. mgrappLoginId({})", mgrappLoginId);
			return null;
		}

		if (resultData.getStatusCode() != HttpStatus.SC_OK) {
			logger.error("failed to request Map Infra Server. mgrappLoginId({}) statusCode({})", mgrappLoginId, resultData.getStatusCode());
			return null;
		}

		ResRevgeocodingSearchJSON resRevgeocodingSearchJSON = new ResRevgeocodingSearchJSON();
		try {
			resRevgeocodingSearchJSON = gson.fromJson(resultData.getJson(), ResRevgeocodingSearchJSON.class);
			resRevgeocodingSearchJSON.setDeviceType(deviceType);
		} catch (JsonSyntaxException e) {
			logger.error("JSON({}) Exception({})", resultData.getJson(), ExceptionUtil.getPrintStackTrace(e));
			return null;
		}

		/*if (!resTargetSearchJSON.getResult_code().equals(MapConst.RESULT_SUCCESS)){ //??????
			logger.error("failed to request Map Infra data. resTargetSearchJSON({})", resTargetSearchJSON);
			return null;
		}*/
		
		return resRevgeocodingSearchJSON;
	}
	
	public Map<String,String>  getMapSvcIdAndAuthKey(String deviceType,String svcType){
		
		Map<String,String> result = new HashMap<String,String>();
		
		String svcId;
		String authKey;
		String reqUrl;
		//set svc_id Value
		if(deviceType.equals(MapConst.DEVICE_TYPE_AM)){
			svcId = amMapInfraSvcId;
			//set authKey
			if(svcType.equals(MapConst.SVC_MAP)){
				authKey = amMapapiAuthKey;
			}else if(svcType.equals(MapConst.SVC_POI)){
				authKey = amPoiAuthKey;
			}else if(svcType.equals(MapConst.SVC_ROUTH)){
				authKey = amRouthAuthKey;
			}else{ // LOCATION
				authKey = amLocationAuthKey;
			}
			
			reqUrl = amMapInfraUrl;
		}else{
			svcId = bmMapInfraSvcId;
			//set authKey
			if(svcType.equals(MapConst.SVC_MAP)){
				authKey = bmMapapiAuthKey;
			}else if(svcType.equals(MapConst.SVC_POI)){
				authKey = bmPoiAuthKey;
			}else if(svcType.equals(MapConst.SVC_ROUTH)){
				authKey = bmRouthAuthKey;
			}else{ // LOCATION
				authKey = bmLocationAuthKey;
			}
			
			reqUrl = bmMapInfraUrl;
		}
		result.put(MapConst.HD_NAME_SVC_ID, svcId);
		result.put(MapConst.HD_NAME_AUTH_KEY, authKey);
		result.put(MapConst.DEF_URL, reqUrl);
		return result;
		
	}
	
	public ResTotalSearchJSON totalSearch(RequestJSON reqJson,String subUrl,String svcType,String deviceType, String mgrappLoginId, String transToken) throws InvalidKeyException, InvalidAlgorithmParameterException, NoSuchAlgorithmException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException, UnsupportedEncodingException {
		//get Terget Service Key Map
		Map<String,String> svcIdAuthKeyMap = getMapSvcIdAndAuthKey(deviceType,svcType);
		
		// set request url
		String url = svcIdAuthKeyMap.get(MapConst.DEF_URL) +  subUrl;
		
		// set headers 
		RestRequestData reqData = makeHeaderData(reqJson,url,svcIdAuthKeyMap);
		
		// set body
		ReqTotalSearchJSON reqTotalSearchJSON = new ReqTotalSearchJSON();
		reqTotalSearchJSON.setCutflag("0"); // 0: ?????? ????????? ?????? 1: ?????? ????????? ??????
		reqTotalSearchJSON.setCoordtype("1"); // 0: ???????????? ?????? 1: WGS84 2. TM(????????????)
		reqTotalSearchJSON.setStartposition((String) reqJson.getParam().get(RequestJSON.PARAM_START_POSITION));
		reqTotalSearchJSON.setReqcount((String) reqJson.getParam().get(RequestJSON.PARAM_REQ_COUNT));
		reqTotalSearchJSON.setDepth("2"); //0 : depth ?????? ??????(???????????? ?????? ??????)(default)  1 : 1 depth ??? ??????(????????? depth) 2 : 2 depth ?????? ?????? 3 : 3 depth ?????? ??????
		//reqTotalSearchJSON.setMode("3");   //?????? ?????? 1 : poi ?????? 2 :adm ?????? 3 :poi+adm ?????? (?????????) 4 : poi + adm + tel + ucp ?????? 5 :tel db?????? 6 :ucp??????
		//20180905 ??????
		reqTotalSearchJSON.setMode("4");   //?????? ?????? 1 : poi ?????? 2 :adm ?????? 3 :poi+adm ?????? (?????????) 4 : poi + adm + tel + ucp ?????? 5 :tel db?????? 6 :ucp??????
		reqTotalSearchJSON.setQuery((String) reqJson.getParam().get(RequestJSON.PARAM_SEARCH_WORD));
		
		reqTotalSearchJSON.setSpopt("0"); //???????????? ?????? 0 : ???????????? ?????? ??????(?????????) 1 : Extent ??????  2 : Range ??????
		reqTotalSearchJSON.setRadius("");
		reqTotalSearchJSON.setAdmcode("");
		String xCoord = (String)reqJson.getParam().get(RequestJSON.PARAM_X_CORRD);
		String yCoord = (String)reqJson.getParam().get(RequestJSON.PARAM_Y_CORRD);
		if(xCoord == null || yCoord == null) {
			reqTotalSearchJSON.setX1(xCoord);
			reqTotalSearchJSON.setY1(yCoord);
		}else {
			reqTotalSearchJSON.setX1(AES256Util.AESDecode(transToken, xCoord));
			reqTotalSearchJSON.setY1(AES256Util.AESDecode(transToken, yCoord));			
		}
		reqTotalSearchJSON.setX2("");
		reqTotalSearchJSON.setY2("");
		reqTotalSearchJSON.setSortopt((String) reqJson.getParam().get(RequestJSON.PARAM_SORTOPT));
		
		reqData.setJson(gson.toJson(reqTotalSearchJSON));

		// set trace info
		setTraceInfo(reqData, mgrappLoginId);

		// set params
		reqData.setConnectionTimeout(Integer.parseInt(mapInfraConnectionTimeout));
		reqData.setTimeout(Integer.parseInt(mapInfraTimeout));

		return totalSearchMapApiRequest(reqData, mgrappLoginId, deviceType);
	}
	
	private ResTotalSearchJSON totalSearchMapApiRequest(RestRequestData reqData, String mgrappLoginId, String deviceType) {
		
		RestResultData resultData = null;
		try {
		resultData = restAgent.requestProxy(reqData, mapHttpProxyHost, mapHttpProxySubHost, mapHttpProxyPort);
		
		}catch (Exception e) {
			logger.error("RESULTDATA({}) Exception({})", resultData, e);
			return null;
		}
		
		if (resultData == null) {
			logger.error("failed to request Map Infra Server. mgrappLoginId({})", mgrappLoginId);
			return null;
		}

		if (resultData.getStatusCode() != HttpStatus.SC_OK) {
			logger.error("failed to request Map Infra Server. mgrappLoginId({}) statusCode({})", mgrappLoginId, resultData.getStatusCode());
			return null;
		}

		ResTotalSearchJSON resTotalSearchJSON = new ResTotalSearchJSON();
		try {
			resTotalSearchJSON = gson.fromJson(resultData.getJson(), ResTotalSearchJSON.class);
			resTotalSearchJSON.setDeviceType(deviceType);

		} catch (JsonSyntaxException e) {
			logger.error("JSON({}) Exception({})", resultData.getJson(), ExceptionUtil.getPrintStackTrace(e));
			return null;
		}

		
		
		return resTotalSearchJSON;
	}
	
	public ResFindStatRouthSearchJSON findStatRouthSearch(RequestJSON reqJson,String subUrl,String svcType,String deviceType, String mgrappLoginId) {
		//get Terget Service Key Map
		Map<String,String> svcIdAuthKeyMap = getMapSvcIdAndAuthKey(deviceType,svcType);
		
		// set request url
		String url = svcIdAuthKeyMap.get(MapConst.DEF_URL) +  subUrl;
		
		// set headers 
		RestRequestData reqData = makeHeaderData(reqJson,url,svcIdAuthKeyMap);
		
		// set body
		ReqFindStatRouthSearchJSON reqFindStatRouthSearchJSON = new ReqFindStatRouthSearchJSON();
		
		//???????????? VER ????????? smr_ver
		ResMapDownloadInfoJSON resMapDownloadInfoJSON = new ResMapDownloadInfoJSON();

		
		resMapDownloadInfoJSON =	mapDownloadInfo(reqJson,MapConst.SVC_MAP,deviceType); 

		
		if(resMapDownloadInfoJSON !=null){  
		reqFindStatRouthSearchJSON.setMrVersion(resMapDownloadInfoJSON.getSmr_ver());
		//reqFindStatRouthSearchJSON.setSearchOption("real_traffic"); //real_traffic : ??????????????? real_traffic2 : ?????????????????? real_traffic_freeroad :????????????????????? short_distance_priority :?????????????????? highway_priority :??????????????????????????????  motorcycle :??????????????? ????????????????????????
		reqFindStatRouthSearchJSON.setSearchOption((String) reqJson.getParam().get(RequestJSON.PARAM_SEARCH_OPTION));
		reqFindStatRouthSearchJSON.setSearchType("7"); // 7 : ???????????? ????????????
		
		
	
		
		//Start Set
		com.lgu.common.map.model.findStatRoute.LocDataJSON startLocData = new com.lgu.common.map.model.findStatRoute.LocDataJSON();
		startLocData.setLonx(String.format("%.1f",Double.parseDouble((String.format("%.4f",Double.parseDouble((String) reqJson.getParam().get(RequestJSON.PARAM_START_LONX)) * 36000)))).replace(".",""));
		startLocData.setLaty(String.format("%.1f",Double.parseDouble((String.format("%.5f",Double.parseDouble((String) reqJson.getParam().get(RequestJSON.PARAM_START_LATY)) * 36000)))).replace(".",""));
		startLocData.setName((String) reqJson.getParam().get(RequestJSON.PARAM_START_NM));
		reqFindStatRouthSearchJSON.setNewStartloc(startLocData);
		
		//Target Set
		com.lgu.common.map.model.findStatRoute.LocDataJSON endLocData = new com.lgu.common.map.model.findStatRoute.LocDataJSON();
		

		//endLocData.setLonx(String.format("%.1f",Double.parseDouble((String.format("%.4f",Double.parseDouble((String) reqJson.getParam().get(RequestJSON.PARAM_END_LONX)) * 36000)))).replace(".",""));
		//endLocData.setLaty(String.format("%.1f",Double.parseDouble((String.format("%.5f",Double.parseDouble((String) reqJson.getParam().get(RequestJSON.PARAM_END_LATY)) * 36000)))).replace(".",""));
		
		if( reqJson.getParam().get(RequestJSON.PARAM_END_REAL_LONX) !=null && reqJson.getParam().get(RequestJSON.PARAM_END_REAL_LATY) !=null ){
			endLocData.setLonx(String.format("%.1f",Double.parseDouble((String.format("%.4f",Double.parseDouble((String) reqJson.getParam().get(RequestJSON.PARAM_END_REAL_LONX)) * 36000)))).replace(".",""));
			endLocData.setLaty(String.format("%.1f",Double.parseDouble((String.format("%.5f",Double.parseDouble((String) reqJson.getParam().get(RequestJSON.PARAM_END_REAL_LATY)) * 36000)))).replace(".",""));
		}else{
			endLocData.setLonx(String.format("%.1f",Double.parseDouble((String.format("%.4f",Double.parseDouble((String) reqJson.getParam().get(RequestJSON.PARAM_END_LONX)) * 36000)))).replace(".",""));
			endLocData.setLaty(String.format("%.1f",Double.parseDouble((String.format("%.5f",Double.parseDouble((String) reqJson.getParam().get(RequestJSON.PARAM_END_LATY)) * 36000)))).replace(".",""));
			
		}
		
		endLocData.setName((String) reqJson.getParam().get(RequestJSON.PARAM_END_NM));
		reqFindStatRouthSearchJSON.setNewEndloc(endLocData);
		
		
		//ExtensionData Set : arrivHomeTime set
				List<ExtensionDataJSON> extensionDataJSONList = new ArrayList<ExtensionDataJSON>();
				ExtensionDataJSON extensionDataJSON = new ExtensionDataJSON();

				//????????? ?????????????????? - ???????????? ????????? ???????????? ??????????????? ????????????.
				//???????????? ???????????? ??????????????? Madantory ?????????????????? ???????????? ???????????????  ?????????????????? +1
				String arrivHopeTime = (String) reqJson.getParam().get(RequestJSON.PARAM_ARRIV_HOPE_TIME);
				if(arrivHopeTime == null){
					arrivHopeTime = DateUtils.getCurrentDate(Calendar.DATE, +1 , "yyyy-MM-dd HH:mm");
				}
				
				String targetTime = String.valueOf(convertDateformatToLong("yyyy-MM-dd HH:mm" ,arrivHopeTime));
		
		StatRouteJSON statRouteJSON = new StatRouteJSON();
		statRouteJSON.setTargetTime(targetTime);
		extensionDataJSON.setStatRoute(statRouteJSON);
		extensionDataJSONList.add(extensionDataJSON);
		reqFindStatRouthSearchJSON.setNewExtensionList(extensionDataJSONList);
		
		reqData.setJson(gson.toJson(reqFindStatRouthSearchJSON));

		// set trace info
		setTraceInfo(reqData, mgrappLoginId);

		// set params
		reqData.setConnectionTimeout(Integer.parseInt(mapInfraConnectionTimeout));
		reqData.setTimeout(Integer.parseInt(mapInfraTimeout));

		return findStatRouthSearchMapApiRequest(reqData, mgrappLoginId,deviceType);
	
		}else{
			logger.error("failed to request Map Infra Server - mapDownloadInfo. mgrappLoginId({})", mgrappLoginId);
			return null;
		}
		
		
		}
	
	public ResFindStatRouthSearchJSON findStatRouthSearchDiff(RequestJSON reqJson,String subUrl,String svcType,String deviceType, String mgrappLoginId) {
		System.out.println("deviceType::: " + deviceType);
		if(deviceType.equals(MapConst.DEVICE_TYPE_BM)) {
			//get Terget Service Key Map
			Map<String,String> svcIdAuthKeyMap = getMapSvcIdAndAuthKey(deviceType,svcType);
			
			// set request url
			String url = svcIdAuthKeyMap.get(MapConst.DEF_URL) +  subUrl;
			
			// set headers 
			RestRequestData reqData = makeHeaderData(reqJson,url,svcIdAuthKeyMap);
			
			// set body
			ReqFindStatRouthSearchJSON reqFindStatRouthSearchJSON = new ReqFindStatRouthSearchJSON();
			
			//?????? VER ?????? smr_ver
			ResMapDownloadInfoJSON resMapDownloadInfoJSON = new ResMapDownloadInfoJSON();

			
			resMapDownloadInfoJSON =	mapDownloadInfo(reqJson,MapConst.SVC_MAP,deviceType); 

			
			if(resMapDownloadInfoJSON !=null){  
			reqFindStatRouthSearchJSON.setMrVersion(resMapDownloadInfoJSON.getSmr_ver());
			//reqFindStatRouthSearchJSON.setSearchOption("real_traffic"); //real_traffic : ????????? real_traffic2 : ????????? real_traffic_freeroad :???????????? short_distance_priority :???????????? highway_priority :??????????????????  motorcycle :????????? ????????????
			reqFindStatRouthSearchJSON.setSearchOption((String) reqJson.getParam().get(RequestJSON.PARAM_SEARCH_OPTION));
			reqFindStatRouthSearchJSON.setSearchType("7"); // 7 : ?????? ??????
			
			
		
			
			//Start Set
			com.lgu.common.map.model.findStatRoute.LocDataJSON startLocData = new com.lgu.common.map.model.findStatRoute.LocDataJSON();
			startLocData.setLonx(String.format("%.1f",Double.parseDouble((String.format("%.4f",Double.parseDouble((String) reqJson.getParam().get(RequestJSON.PARAM_START_LONX)) * 36000)))).replace(".",""));
			startLocData.setLaty(String.format("%.1f",Double.parseDouble((String.format("%.5f",Double.parseDouble((String) reqJson.getParam().get(RequestJSON.PARAM_START_LATY)) * 36000)))).replace(".",""));
			startLocData.setName((String) reqJson.getParam().get(RequestJSON.PARAM_START_NM));
			reqFindStatRouthSearchJSON.setNewStartloc(startLocData);
			
			//Target Set
			com.lgu.common.map.model.findStatRoute.LocDataJSON endLocData = new com.lgu.common.map.model.findStatRoute.LocDataJSON();
			

			//endLocData.setLonx(String.format("%.1f",Double.parseDouble((String.format("%.4f",Double.parseDouble((String) reqJson.getParam().get(RequestJSON.PARAM_END_LONX)) * 36000)))).replace(".",""));
			//endLocData.setLaty(String.format("%.1f",Double.parseDouble((String.format("%.5f",Double.parseDouble((String) reqJson.getParam().get(RequestJSON.PARAM_END_LATY)) * 36000)))).replace(".",""));
			
			if( reqJson.getParam().get(RequestJSON.PARAM_END_REAL_LONX) !=null && reqJson.getParam().get(RequestJSON.PARAM_END_REAL_LATY) !=null ){
				endLocData.setLonx(String.format("%.1f",Double.parseDouble((String.format("%.4f",Double.parseDouble((String) reqJson.getParam().get(RequestJSON.PARAM_END_REAL_LONX)) * 36000)))).replace(".",""));
				endLocData.setLaty(String.format("%.1f",Double.parseDouble((String.format("%.5f",Double.parseDouble((String) reqJson.getParam().get(RequestJSON.PARAM_END_REAL_LATY)) * 36000)))).replace(".",""));
			}else{
				endLocData.setLonx(String.format("%.1f",Double.parseDouble((String.format("%.4f",Double.parseDouble((String) reqJson.getParam().get(RequestJSON.PARAM_END_LONX)) * 36000)))).replace(".",""));
				endLocData.setLaty(String.format("%.1f",Double.parseDouble((String.format("%.5f",Double.parseDouble((String) reqJson.getParam().get(RequestJSON.PARAM_END_LATY)) * 36000)))).replace(".",""));
				
			}
			
			endLocData.setName((String) reqJson.getParam().get(RequestJSON.PARAM_END_NM));
			reqFindStatRouthSearchJSON.setNewEndloc(endLocData);
			
			
			//ExtensionData Set : arrivHomeTime set
					List<ExtensionDataJSON> extensionDataJSONList = new ArrayList<ExtensionDataJSON>();
					ExtensionDataJSON extensionDataJSON = new ExtensionDataJSON();

					//?????? ????????? - ?????? ?????? ?????? ????????? ??????.
					//?????? ?????? ????????? Madantory ????????? ?????? ?????????  ????????? +1
					String arrivHopeTime = (String) reqJson.getParam().get(RequestJSON.PARAM_ARRIV_HOPE_TIME);
					if(arrivHopeTime == null){
						arrivHopeTime = DateUtils.getCurrentDate(Calendar.DATE, +1 , "yyyy-MM-dd HH:mm");
					}
					
					String targetTime = String.valueOf(convertDateformatToLong("yyyy-MM-dd HH:mm" ,arrivHopeTime));
			
			StatRouteJSON statRouteJSON = new StatRouteJSON();
			statRouteJSON.setTargetTime(targetTime);
			extensionDataJSON.setStatRoute(statRouteJSON);
			extensionDataJSONList.add(extensionDataJSON);
			reqFindStatRouthSearchJSON.setNewExtensionList(extensionDataJSONList);
			
			reqData.setJson(gson.toJson(reqFindStatRouthSearchJSON));

			// set trace info
			setTraceInfo(reqData, mgrappLoginId);

			// set params
			reqData.setConnectionTimeout(Integer.parseInt(mapInfraConnectionTimeout));
			reqData.setTimeout(Integer.parseInt(mapInfraTimeout));

			return findStatRouthSearchMapApiRequest(reqData, mgrappLoginId,deviceType);
		
			}else{
				logger.error("failed to request Map Infra Server - mapDownloadInfo. mgrappLoginId({})", mgrappLoginId);
				return null;
			}
		} else {
			//get Terget Service Key Map
			Map<String,String> svcIdAuthKeyMap = getMapSvcIdAndAuthKey(deviceType,svcType);
			
			// set request url
			String url = svcIdAuthKeyMap.get(MapConst.DEF_URL) +  subUrl;
			
			// set headers 
			RestRequestData reqData = makeHeaderData(reqJson,url,svcIdAuthKeyMap);
			
			// set body
			ReqFindStatRouthSearchAmJSON reqFindStatRouthSearchAmJSON = new ReqFindStatRouthSearchAmJSON();
			
			//?????? VER ?????? smr_ver
			ResMapDownloadInfoJSON resMapDownloadInfoJSON = new ResMapDownloadInfoJSON();

			
			resMapDownloadInfoJSON =	mapDownloadInfo(reqJson,MapConst.SVC_MAP,deviceType); 

			
			if(resMapDownloadInfoJSON !=null){  
				reqFindStatRouthSearchAmJSON.setMrVersion(resMapDownloadInfoJSON.getSmr_ver());
				reqFindStatRouthSearchAmJSON.setSearchType("1"); // 1 : ??????????????????
				reqFindStatRouthSearchAmJSON.setEndLocCount(1);
				
				String arrivHopeTime = (String) reqJson.getParam().get(RequestJSON.PARAM_ARRIV_HOPE_TIME);
				if(arrivHopeTime == null){
					arrivHopeTime = DateUtils.getCurrentDate(Calendar.DATE, +1 , "yyyy-MM-dd HH:mm");
				}
				
				String timeStamp = String.valueOf(convertDateformatToLong("yyyy-MM-dd HH:mm" ,arrivHopeTime));
				reqFindStatRouthSearchAmJSON.setTimeStamp(timeStamp);

				//Start Set
				com.lgu.common.map.model.findStatRoute.LocDataAmJSON statisticStartLoc = new com.lgu.common.map.model.findStatRoute.LocDataAmJSON();
				statisticStartLoc.setLonx(String.format("%.1f",Double.parseDouble((String.format("%.4f",Double.parseDouble((String) reqJson.getParam().get(RequestJSON.PARAM_START_LONX)) * 36000)))).replace(".",""));
				statisticStartLoc.setLaty(String.format("%.1f",Double.parseDouble((String.format("%.5f",Double.parseDouble((String) reqJson.getParam().get(RequestJSON.PARAM_START_LATY)) * 36000)))).replace(".",""));
				reqFindStatRouthSearchAmJSON.setStatisticStartLoc(statisticStartLoc);
			
				//Target Set
				com.lgu.common.map.model.findStatRoute.LocDataAmJSON statisticEndtLocList = new com.lgu.common.map.model.findStatRoute.LocDataAmJSON();
				//endLocData.setLonx(String.format("%.1f",Double.parseDouble((String.format("%.4f",Double.parseDouble((String) reqJson.getParam().get(RequestJSON.PARAM_END_LONX)) * 36000)))).replace(".",""));
				//endLocData.setLaty(String.format("%.1f",Double.parseDouble((String.format("%.5f",Double.parseDouble((String) reqJson.getParam().get(RequestJSON.PARAM_END_LATY)) * 36000)))).replace(".",""));
				
				if( reqJson.getParam().get(RequestJSON.PARAM_END_REAL_LONX) !=null && reqJson.getParam().get(RequestJSON.PARAM_END_REAL_LATY) !=null ){
					(statisticEndtLocList).setLonx(String.format("%.1f",Double.parseDouble((String.format("%.4f",Double.parseDouble((String) reqJson.getParam().get(RequestJSON.PARAM_END_REAL_LONX)) * 36000)))).replace(".",""));
					( statisticEndtLocList).setLaty(String.format("%.1f",Double.parseDouble((String.format("%.5f",Double.parseDouble((String) reqJson.getParam().get(RequestJSON.PARAM_END_REAL_LATY)) * 36000)))).replace(".",""));
				}else{
					(statisticEndtLocList).setLonx(String.format("%.1f",Double.parseDouble((String.format("%.4f",Double.parseDouble((String) reqJson.getParam().get(RequestJSON.PARAM_END_LONX)) * 36000)))).replace(".",""));
					(statisticEndtLocList).setLaty(String.format("%.1f",Double.parseDouble((String.format("%.5f",Double.parseDouble((String) reqJson.getParam().get(RequestJSON.PARAM_END_LATY)) * 36000)))).replace(".",""));
					
				}
				List<LocDataAmJSON> addStatistEndLocList = new LinkedList<LocDataAmJSON>();
				addStatistEndLocList.add(statisticEndtLocList);
				reqFindStatRouthSearchAmJSON.setStatisticEndLocList(addStatistEndLocList);
				
				
				
				reqData.setJson(gson.toJson(reqFindStatRouthSearchAmJSON));
	
				// set trace info
				setTraceInfo(reqData, mgrappLoginId);
	
				// set params
				reqData.setConnectionTimeout(Integer.parseInt(mapInfraConnectionTimeout));
				reqData.setTimeout(Integer.parseInt(mapInfraTimeout));
	
				return findStatRouthSearchMapApiRequest(reqData, mgrappLoginId,deviceType);
		
			}else{
				logger.error("failed to request Map Infra Server - mapDownloadInfo. mgrappLoginId({})", mgrappLoginId);
				return null;
			}
		}

		}
	
	private ResFindStatRouthSearchJSON findStatRouthSearchMapApiRequest(RestRequestData reqData, String mgrappLoginId, String deviceType) {
		
		RestResultData resultData = null;
		
		try {
		resultData = restAgent.requestProxy(reqData, mapHttpProxyHost, mapHttpProxySubHost, mapHttpProxyPort);
		
		}catch (Exception e) {
			logger.error("RESULTDATA({}) Exception({})", resultData, e);
			return null;
		}
		if (resultData == null) {
			logger.error("failed to request Map Infra Server. mgrappLoginId({})", mgrappLoginId);
			return null;
		}

		if (resultData.getStatusCode() != HttpStatus.SC_OK) {
			logger.error("failed to request Map Infra Server. mgrappLoginId({}) statusCode({})", mgrappLoginId, resultData.getStatusCode());
			return null;
		}

		ResFindStatRouthSearchJSON resFindStatRouthSearchJSON = new ResFindStatRouthSearchJSON();
		try {
			resFindStatRouthSearchJSON = gson.fromJson(resultData.getJson(), ResFindStatRouthSearchJSON.class);
			resFindStatRouthSearchJSON.setDeviceType(deviceType);

		} catch (JsonSyntaxException e) {
			logger.error("JSON({}) Exception({})", resultData.getJson(), e);
			return null;
		}
		
		return resFindStatRouthSearchJSON;
	}
	
	private void setTraceInfo(RestRequestData reqData, String mgrappLoginId) {
		reqData.setTraceId(mgrappLoginId);
		reqData.setSource(MapConst.TRACE_SOURCE);
		reqData.setTarget(MapConst.TRACE_TARGET);
	}
	
	public ResMapDownloadInfoJSON mapDownloadInfo(RequestJSON reqJson,String svcType,String deviceType) {
		
		//get Terget Service Key Map
		Map<String,String> svcIdAuthKeyMap = getMapSvcIdAndAuthKey(deviceType,svcType);
		
		// set request url
		String url = svcIdAuthKeyMap.get(MapConst.DEF_URL) +  MapConst.URL_DOWNLOAD_MAP_INFO;
		
		// set headers 
		RestRequestData reqData = makeHeaderData(reqJson,url,svcIdAuthKeyMap);

		// set params
		reqData.setConnectionTimeout(Integer.parseInt(mapInfraConnectionTimeout));
		reqData.setTimeout(Integer.parseInt(mapInfraTimeout));
		
		
		return mapDownLoadInfoRequestApi(reqData);
	}
	
	private ResMapDownloadInfoJSON mapDownLoadInfoRequestApi(RestRequestData reqData) {
		
		RestResultData resultData = restAgent.requestProxy(reqData, mapHttpProxyHost, mapHttpProxySubHost, mapHttpProxyPort);
		
		if (resultData == null) {
			logger.error("failed to request Map Infra Server. ");
			return null;
		}

		if (resultData.getStatusCode() != HttpStatus.SC_OK) {
			logger.error("failed to request Map Infra Server.  statusCode({})", resultData.getStatusCode());
			return null;
		}

		ResMapDownloadInfoJSON resMapDownloadInfoJSON = new ResMapDownloadInfoJSON();
		try {
			resMapDownloadInfoJSON = gson.fromJson(resultData.getJson(), ResMapDownloadInfoJSON.class);

		} catch (JsonSyntaxException e) {
			logger.error("JSON({}) Exception({})", resultData.getJson(), e);
			return null;
		}
		
		return resMapDownloadInfoJSON;
	}

	public long convertDateformatToLong(String dateFormat,String arrivHopeTime){
		
		SimpleDateFormat df = new SimpleDateFormat(dateFormat); //yyyy-MM-dd HH:mm
		
		long time=0;
		
		try {
			Date date;
			date = df.parse(arrivHopeTime);
			time = date.getTime() / 1000;
				
		} catch (ParseException e) {
			
			logger.error("arrivHopeTime convertDateformatToLong Fail",e);
		}
		System.out.println("Time:" + time);
		return time;
	}
}
