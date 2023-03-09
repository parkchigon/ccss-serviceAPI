package com.lgu.ccss.api.gcalendar.service.eventlist;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.http.HttpStatus;
import org.apache.http.client.ClientProtocolException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;

import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.googleapis.json.GoogleJsonResponseException;
import com.google.api.client.util.DateTime;
import com.google.api.services.calendar.Calendar;
import com.google.api.services.calendar.model.Events;
import com.lgu.ccss.api.gcalendar.service.model.EventListParameter;
import com.lgu.ccss.common.constant.CCSSConst;
import com.lgu.ccss.common.dao.InfotainAuthInfoDao;
import com.lgu.ccss.common.gcalendar.GoogleCalendarService;
import com.lgu.ccss.common.model.InfotainAuthInfoVO;
import com.lgu.ccss.common.model.RequestJSON;
import com.lgu.ccss.common.model.ResponseJSON;
import com.lgu.ccss.common.util.CCSSUtil;
import com.lgu.ccss.common.util.ResultCodeUtil;
import com.lgu.ccss.common.validation.CheckResultData;
import com.lgu.ccss.common.validation.ValidationChecker;
import com.lgu.common.ai.auth.AuthConst;
import com.lgu.common.util.ExceptionUtil;


@Service("gCalendarEventListService")
public class GCalendarEventListServiceImpl implements GCalendarEventListService {
	private static final Logger logger = LoggerFactory.getLogger(GCalendarEventListServiceImpl.class);

	@Autowired
	private InfotainAuthInfoDao infotainAuthInfoDao;
	
	@Autowired
	private GoogleCalendarService googleCalendarService;
	
	@Override
	public ResponseJSON doService(HttpHeaders headers, RequestJSON reqJson) {
		String api = reqJson.getCommon().getApiId();
		String deviceCtn = CCSSUtil.getCtn();

		ResponseJSON response = null;
		
		CheckResultData result = checkValidation(reqJson);
		if (result.isStatus() == false) {
			response = ResultCodeUtil.createResultMsg(ResultCodeUtil.RC_3C004000, api, result.getReason());
			return response;
		}

		try {
			response = doGCalendarService(headers, reqJson);

		} catch (Exception e) {
			logger.error("deviceCtn({}) Exception({})", deviceCtn, ExceptionUtil.getPrintStackTrace(e));
			response = ResultCodeUtil.createResultMsg(ResultCodeUtil.RC_4C005000, api);
		}

		return response;

	}

	private CheckResultData checkValidation(RequestJSON request) {
		CheckResultData result = ValidationChecker.checkCommon(request.getCommon());
		if (result.isStatus() == false) {
			return result;
		}

		result = ValidationChecker.checkValue(request.getCommon().getApiId(), "common.apiId");
		if (result.isStatus() == false) {
			return result;
		}

		return result;
	}

	private ResponseJSON doGCalendarService(HttpHeaders headers, RequestJSON reqJson)
			throws ClientProtocolException, IOException {

		String api = reqJson.getCommon().getApiId();
		String ccssToken = headers.getFirst("ccssToken");
		String deviceCtn = CCSSUtil.getCtn();
		String membId = CCSSUtil.getMembId();
		
		Events events = null;
		
		try {
			
			List<InfotainAuthInfoVO> gcalendarSvcParam = infotainAuthInfoDao.selectInfotainAuthInfo(membId, AuthConst.SERVICE_CODE_GOOGLE);
			if( gcalendarSvcParam == null || gcalendarSvcParam.size() == 0 )
			{
				logger.error( "google calendar logout deviceCtn({}) ccssToken({})", deviceCtn, ccssToken);
				return ResultCodeUtil.createResultMsg(ResultCodeUtil.RC_3C005005, api, "google calendar logout");			
			}
			
			String primaryId = null;
			Iterator<InfotainAuthInfoVO> iter = gcalendarSvcParam.iterator();
			while( iter.hasNext() )
			{
				InfotainAuthInfoVO infoAuthVo = (InfotainAuthInfoVO)iter.next();
				
				if( infoAuthVo.getTokenNm().equals(CCSSConst.INFOTAINMENT_PARAM_FIELD_PREFIX+1))
				{
					primaryId = infoAuthVo.getTokenValue();
					break;
				}
			}
			
			if( primaryId == null )
			{
				logger.error( "google calendar logout deviceCtn({}) ccssToken({})", deviceCtn, ccssToken);
				return ResultCodeUtil.createResultMsg(ResultCodeUtil.RC_3C005005, api, "google calendar primaryId invalid");
			}
			
			Credential credential = googleCalendarService.authorize(membId);
			if( credential == null )
			{
				logger.error( "google calendar logout credential is null deviceCtn({}) ccssToken({})", deviceCtn, ccssToken);
				return ResultCodeUtil.createResultMsg(ResultCodeUtil.RC_3C005005, api, "google calendar primaryId invalid");
			}
			
		/*	//refresh access token
			if( credential.getExpiresInSeconds() < 0 )
			{
				
			}*/
			
			Calendar service = googleCalendarService.getCalendarService(credential);
			if( service == null )
			{
				logger.error( "google calendar get calendar fail deviceCtn({}) ccssToken({})", deviceCtn, ccssToken);
				return ResultCodeUtil.createResultMsg(ResultCodeUtil.RC_5G000001, api, "google calendar primaryId invalid");
			}
			
			com.google.api.services.calendar.Calendar.Events.List requestEventList = service.events().list(primaryId);
			CheckResultData resultData = setGoogleParameter(requestEventList, reqJson);
			
			if( !resultData.isStatus() )
			{
				return ResultCodeUtil.createResultMsg(ResultCodeUtil.RC_3C004000, api, resultData.getReason());
			}
			
			events = googleCalendarService.requestEventList(requestEventList);

			logger.debug("calendar id:" + primaryId + ", reqJson:" + reqJson);
			
		} catch (GoogleJsonResponseException e) {
			logger.error("Invalid GCalendar Response. deviceCtn({}) ccssToken({})  exception({})",	deviceCtn, ccssToken, e);
			
			if( e.getStatusCode() == HttpStatus.SC_UNAUTHORIZED )
			{
				int resultCnt = infotainAuthInfoDao.deleteInfotainAuthInfo(membId, AuthConst.SERVICE_CODE_GOOGLE);
				logger.info("Invalid access token!, delete token info:" + resultCnt);
			}
					
			return ResultCodeUtil.createResultMsg(ResultCodeUtil.RC_5G000004, api, e.getStatusMessage());
		} catch (IOException e) {
			logger.error("failed to send google event list api. deviceCtn({}) ccssToken({})  exception({})",
					deviceCtn, ccssToken, e);
			return ResultCodeUtil.createResultMsg(ResultCodeUtil.RC_5G000001, api);
		} catch (Exception e) {
			logger.error("failed to send google event list api. deviceCtn({}) ccssToken({})  exception({})",
					deviceCtn, ccssToken, e);
			return ResultCodeUtil.createResultMsg(ResultCodeUtil.RC_4C005000, api);
		}
		
		return ResultCodeUtil.createResultMsg(ResultCodeUtil.RC_2S000000, events, api);
	}

	public CheckResultData setGoogleParameter(com.google.api.services.calendar.Calendar.Events.List requestEventList, RequestJSON reqJson)
	{
		String curParameter = EventListParameter.PARAM_ALWAYSINCLUDEEMAIL;
	
		try
		{
			if( reqJson.getParam().get(curParameter) != null )
				requestEventList.setAlwaysIncludeEmail((boolean)reqJson.getParam().get(curParameter));
			
			curParameter = EventListParameter.PARAM_ICALUID;		
			if( reqJson.getParam().get(curParameter) != null )
				requestEventList.setICalUID((String)reqJson.getParam().get(curParameter));
			
			curParameter = EventListParameter.PARAM_MAXATTENDEES;	
			if( reqJson.getParam().get(curParameter) != null )
				requestEventList.setMaxAttendees((int) reqJson.getParam().get(curParameter));
			
			curParameter = EventListParameter.PARAM_MAXRESULTS;	
			if( reqJson.getParam().get(curParameter) != null )
				requestEventList.setMaxResults((int) reqJson.getParam().get(curParameter));
			
			curParameter = EventListParameter.PARAM_ORDERBY;	
			if( reqJson.getParam().get(curParameter) != null )
				requestEventList.setOrderBy((String) reqJson.getParam().get(curParameter));
			
			curParameter = EventListParameter.PARAM_PAGETOKEN;	
			if( reqJson.getParam().get(curParameter) != null )
				requestEventList.setPageToken((String) reqJson.getParam().get(curParameter));
			
			curParameter = EventListParameter.PARAM_PRIVATEEXTENDEDPROPERTY;	
			if( reqJson.getParam().get(curParameter) != null )
			{
				List<String> param = new ArrayList<String>();
				param.add((String) reqJson.getParam().get(curParameter));
				requestEventList.setPrivateExtendedProperty(param);
			}
			
			curParameter = EventListParameter.PARAM_Q;	
			if( reqJson.getParam().get(curParameter) != null )
				requestEventList.setQ((String) reqJson.getParam().get(curParameter));
			
			curParameter = EventListParameter.PARAM_SHAREDEXTENDEDPROPERTY;	
			if( reqJson.getParam().get(curParameter) != null )
			{
				List<String> param = new ArrayList<String>();
				param.add((String) reqJson.getParam().get(curParameter));
				requestEventList.setSharedExtendedProperty(param);
			}
			
			curParameter = EventListParameter.PARAM_SHOWDELETED;	
			if( reqJson.getParam().get(curParameter) != null )
				requestEventList.setShowDeleted((boolean) reqJson.getParam().get(curParameter));
			
			curParameter = EventListParameter.PARAM_SHOWHIDDENINVITATIONS;	
			if( reqJson.getParam().get(curParameter) != null )
				requestEventList.setShowHiddenInvitations((boolean) reqJson.getParam().get(curParameter));
			
			curParameter = EventListParameter.PARAM_SINGLEEVENTS;	
			if( reqJson.getParam().get(curParameter) != null )
				requestEventList.setSingleEvents((boolean) reqJson.getParam().get(curParameter));
			
			curParameter = EventListParameter.PARAM_SYNCTOKEN;	
			if( reqJson.getParam().get(curParameter) != null )
				requestEventList.setSyncToken((String) reqJson.getParam().get(curParameter));
			
			curParameter = EventListParameter.PARAM_TIMEMIN;	
			if( reqJson.getParam().get(curParameter) != null )
				requestEventList.setTimeMin(new DateTime((String) reqJson.getParam().get(curParameter)));
			
			curParameter = EventListParameter.PARAM_TIMEMAX;	
			if( reqJson.getParam().get(curParameter) != null )
				requestEventList.setTimeMax(new DateTime((String) reqJson.getParam().get(curParameter)));
			
			curParameter = EventListParameter.PARAM_TIMEZONE;	
			if( reqJson.getParam().get(curParameter) != null )
				requestEventList.setTimeZone((String) reqJson.getParam().get(curParameter));
			
			curParameter = EventListParameter.PARAM_UPDATEDMIN;	
			if( reqJson.getParam().get(curParameter) != null )
				requestEventList.setUpdatedMin(new DateTime((String) reqJson.getParam().get(curParameter)));
			
			CheckResultData result = new CheckResultData();
			result.setStatus(true);
			return result;
		}catch (Exception e) {
			
			logger.error("failed to set google eventlis api. curParameter({}) exception({})",curParameter, e);
			CheckResultData result = new CheckResultData();
			result.setStatus(false);
			result.setReason(curParameter + " invalid value");
			return result;
		}
	}
	/*private ResponseJSON doGCalendarService(HttpHeaders headers, RequestJSON reqJson)
			throws ClientProtocolException, IOException {

		String api = reqJson.getCommon().getApiId();
		String ccssToken = headers.getFirst("ccssToken");
		String deviceCtn = CCSSUtil.getCtn();
		String membId = CCSSUtil.getMembId();
		
		Events events = null;

		try {
			String mgrappId = (String)reqJson.getParam().get(RequestJSON.PARAM_GCALENDAR_MGRAPPID);
			String timeMinStr = (String) reqJson.getParam().get(RequestJSON.PARAM_GCALENDAR_TIMEMIN);
			String timeMaxStr = (String) reqJson.getParam().get(RequestJSON.PARAM_GCALENDAR_TIMEMAX);
			String countStr = (String) reqJson.getParam().get(RequestJSON.PARAM_GCALENDAR_COUNT);
			
			if( mgrappId == null || mgrappId.trim().length() == 0 )
			{
				logger.error(RequestJSON.PARAM_GCALENDAR_MGRAPPID + " invalid param value deviceCtn({}) ccssToken({})",
						deviceCtn, ccssToken);
				return ResultCodeUtil.createResultMsg(ResultCodeUtil.RC_3C004000, api, "invalid mgrappiD format ");
			}
			
			long timeMinLong = GoogleCalendarUtil.convertStringToDate(RequestJSON.GCALENDAR_DATE_FORMAT, timeMinStr);
			if( timeMinLong == -1 )
			{
				logger.error(RequestJSON.PARAM_GCALENDAR_TIMEMIN + "invalid param value deviceCtn({}) ccssToken({})",
						deviceCtn, ccssToken);
				return ResultCodeUtil.createResultMsg(ResultCodeUtil.RC_3C004000, api, "invalid timemin format " + RequestJSON.GCALENDAR_DATE_FORMAT);
			}
			long timeMaxLong = GoogleCalendarUtil.convertStringToDate(RequestJSON.GCALENDAR_DATE_FORMAT, timeMaxStr);
			if( timeMaxLong == -1 )
			{
				logger.error(RequestJSON.PARAM_GCALENDAR_TIMEMAX + "invalid param value deviceCtn({}) ccssToken({})",
						deviceCtn, ccssToken);
				return ResultCodeUtil.createResultMsg(ResultCodeUtil.RC_3C004000, api, "invalid timemax format " + RequestJSON.GCALENDAR_DATE_FORMAT);
			}
			
			int count = convertStringToInt(countStr);
			if( count == -1 )
			{
				logger.error(RequestJSON.PARAM_GCALENDAR_COUNT + "invalid param value deviceCtn({}) ccssToken({})",
						deviceCtn, ccssToken);
				return ResultCodeUtil.createResultMsg(ResultCodeUtil.RC_3C004000, api, "invalid count format ");
			}
			
			List<MgrappInfotainAuthInfoVO> gcalendarSvcParam = mgrappInfotainAuthInfoDao.selectMgrappInfotainAuthInfo(mgrappId, CCSSConst.INFOTAINMENT_SVCCODE_GCALENDAR);
			if( gcalendarSvcParam == null || gcalendarSvcParam.size() == 0 )
			{
				logger.error( "google calendar logout deviceCtn({}) ccssToken({})", deviceCtn, ccssToken);
				return ResultCodeUtil.createResultMsg(ResultCodeUtil.RC_3C005005, api, "google calendar logout");			
			}
			
			//
			String primaryId = null;
			Iterator iter = gcalendarSvcParam.iterator();
			while( iter.hasNext() )
			{
				MgrappInfotainAuthInfoVO infoAuthVo = (MgrappInfotainAuthInfoVO)iter.next();
				
				if( infoAuthVo.getTokenNm().equals(CCSSConst.INFOTAINMENT_PARAM_FIELD_PREFIX+1))
				{
					primaryId = infoAuthVo.getTokenValue();
					break;
				}
			}
			
			if( primaryId == null )
			{
				logger.error( "google calendar logout deviceCtn({}) ccssToken({})", deviceCtn, ccssToken);
				return ResultCodeUtil.createResultMsg(ResultCodeUtil.RC_3C005005, api, "google calendar primaryId invalid");
			}
			
			Credential credential = googleCalendarService.authorize(mgrappId);
			if( credential == null )
			{
				logger.error( "google calendar logout credential is null deviceCtn({}) ccssToken({})", deviceCtn, ccssToken);
				return ResultCodeUtil.createResultMsg(ResultCodeUtil.RC_3C005005, api, "google calendar primaryId invalid");
			}
			
			Calendar service = googleCalendarService.getCalendarService(credential);
			if( service == null )
			{
				logger.error( "google calendar get calendar fail deviceCtn({}) ccssToken({})", deviceCtn, ccssToken);
				return ResultCodeUtil.createResultMsg(ResultCodeUtil.RC_5G000001, api, "google calendar primaryId invalid");
			}
			
			
			 * {"accessRole":"owner","defaultReminders":[{"method":"popup","minutes":10}],"etag":"\"p32gefokfmq2dg0g\"","items":[{"created":"2017-12-12T11:59:27.000Z","creator":{"displayName":"이재영","email":"ytmljy@gmail.com","self":true},"end":{"dateTime":"2017-12-15T12:30:00.000+09:00"},"etag":"\"3026159934332000\"","htmlLink":"https://www.google.com/calendar/event?eid=NWs4ZWgxbWc3b2ZmaXJvcGFjdDAxOGRxM2EgeXRtbGp5QG0","iCalUID":"5k8eh1mg7offiropact018dq3a@google.com","id":"5k8eh1mg7offiropact018dq3a","kind":"calendar#event","organizer":{"displayName":"이재영","email":"ytmljy@gmail.com","self":true},"reminders":{"useDefault":true},"sequence":0,"start":{"dateTime":"2017-12-15T11:30:00.000+09:00"},"status":"confirmed","summary":"test","updated":"2017-12-12T11:59:27.166Z"}],"kind":"calendar#events","summary":"재영 이","timeZone":"Asia/Seoul","updated":"2017-12-12T11:59:27.364Z"}
			 
			com.google.api.services.calendar.Calendar.Events.List requestEventList = service.events().list(primaryId);
			
			
			events = service.events().list(primaryId)
					.setMaxResults(count)
					.setTimeMin(new DateTime(timeMinLong))
					.setTimeMax(new DateTime(timeMaxLong))
					.setOrderBy("startTime")
                    .setSingleEvents(true)
                    .execute();

			logger.debug("calendar id:" + primaryId + ", timeMin:" + timeMinStr +", timeMax:" + timeMaxStr
					+ ", count:" + count + ", events \r\n" + events);
			
		} catch (Exception e) {
			logger.error("failed to decrypt Lat/Lon data. deviceCtn({}) ccssToken({})  exception({})",
					deviceCtn, ccssToken, ExceptionUtil.getPrintStackTrace(e));
			return ResultCodeUtil.createResultMsg(ResultCodeUtil.RC_4C005000, api);
		}
		
		return ResultCodeUtil.createResultMsg(ResultCodeUtil.RC_2S000000, events, api);
	}*/
	/*private int convertStringToInt(String count)
	{
		int returnValue = -1;
		
		try
		{
			returnValue = Integer.parseInt(count);
		} catch( Exception ex )
		{
			logger.error("failed to convertStringToInt count({}) exception({})", count, ex);
		}
		
		return returnValue;
	}*/
}
