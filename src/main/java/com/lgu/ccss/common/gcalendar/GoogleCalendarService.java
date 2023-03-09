package com.lgu.ccss.common.gcalendar;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import org.apache.http.HttpStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.extensions.java6.auth.oauth2.AuthorizationCodeInstalledApp;
import com.google.api.client.extensions.jetty.auth.oauth2.LocalServerReceiver;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.calendar.Calendar;
import com.google.api.services.calendar.CalendarScopes;
import com.google.api.services.calendar.model.Events;

import com.lgu.ccss.common.gcalendar.CCSGCalendarDataStoreFactory;
import com.lgu.common.rest.RestAgent;
import com.lgu.common.rest.RestRequestData;
import com.lgu.common.rest.RestResultData;

@Component
public class GoogleCalendarService {
	
	private static Logger logger = LoggerFactory.getLogger(GoogleCalendarService.class);
	
	@Autowired
	private RestAgent restAgent;
	
	private static final String AUTH_HOST = "calendar.feelingk.com";
	private static final int AUTH_PORT = 8088;
	
	public static final String CALLBACK_PARAM_CODE = "code";
	public static final String CALLBACK_PARAM_ERROR = "error";
	public static final String CALLBACK_PARAM_STATE = "state";
	
	public static final String PARAM_MSGAPPID = "mgrappId";
	public static final String PARAM_MEMBID = "membId";
	public static final String PARAM_SESSIONID = "sessionId";
	
	public static final String TOKEN_NAME_ACCESSTOKEN = "accessToken";
	public static final String TOKEN_NAME_REFRESHTOKEN = "refreshToken";
	public static final String TOKEN_NAME_EXPDATE = "expDdate";
	
	private static final String APPLICATION_NAME = "Google Calendar API Java Quickstart";

	private static CCSGCalendarDataStoreFactory DATA_STORE_FACTORY;

	private static final JsonFactory JSON_FACTORY = JacksonFactory
			.getDefaultInstance();

	private static HttpTransport HTTP_TRANSPORT;

	private static final List<String> SCOPES = Arrays
			.asList(CalendarScopes.CALENDAR_READONLY);
	
	public static String proxyHost = null;
	public static int proxyPort = -1;
	
	static {
		try {
			
			logger.info("gcalendar.proxyHost:" + System.getProperty("gcalendar.proxyHost")  + ", gcalendar.proxyPort:" + System.getProperty("gcalendar.proxyPort"));
			
			proxyHost = System.getProperty("gcalendar.proxyHost");
			proxyPort = Integer.parseInt(System.getProperty("gcalendar.proxyPort"));
			
			if( proxyHost != null && proxyPort != -1 )
				HTTP_TRANSPORT = GoogleProxyNetHttpTransport.newTrustedTransport(proxyHost, proxyPort);
			else
				HTTP_TRANSPORT = GoogleNetHttpTransport.newTrustedTransport();
			//DATA_STORE_FACTORY = new FileDataStoreFactory(DATA_STORE_DIR);
			DATA_STORE_FACTORY = new CCSGCalendarDataStoreFactory();
			
		} catch (Throwable t) {
			logger.error("Exception", t);
			//System.exit(1);
		}
	}

	public Credential authorize(String userId) throws IOException {
		InputStream in = GoogleCalendarService.class
				.getResourceAsStream("/client_secret.json");
		GoogleClientSecrets clientSecrets = GoogleClientSecrets.load(
				JSON_FACTORY, new InputStreamReader(in));
		
		GoogleAuthorizationCodeFlow flow = new GoogleAuthorizationCodeFlow.Builder(
				HTTP_TRANSPORT, JSON_FACTORY, clientSecrets, SCOPES)
				.setDataStoreFactory(DATA_STORE_FACTORY)
				.setAccessType("offline").setApprovalPrompt("force").build();
		
		//LocalServerReceiver localReceiver = new LocalServerReceiver.Builder().setHost(AUTH_HOST).setPort(AUTH_PORT).build();
		LocalServerReceiver localReceiver = new LocalServerReceiver.Builder().setHost(AUTH_HOST).setPort(AUTH_PORT).build();
		
		Credential credential = new AuthorizationCodeInstalledApp(flow,localReceiver).authorizeForService(userId);
		
		logger.debug("credential:" + credential);
		
		if( credential != null )
		{
			logger.debug("credential.getAccessToken():" + credential.getAccessToken());
			logger.debug("credential.getRefreshToken():" + credential.getRefreshToken());
		}
		
		return credential;
	}
	
	public String authorizeForLogin(HashMap<String, String> returnParamMap, String redirectUri) throws IOException {
		InputStream in = GoogleCalendarService.class
				.getResourceAsStream("/client_secret.json");
		GoogleClientSecrets clientSecrets = GoogleClientSecrets.load(
				JSON_FACTORY, new InputStreamReader(in));
		
		GoogleAuthorizationCodeFlow flow = new GoogleAuthorizationCodeFlow.Builder(
				HTTP_TRANSPORT, JSON_FACTORY, clientSecrets, SCOPES)
				.setDataStoreFactory(DATA_STORE_FACTORY)
				.setAccessType("offline").setApprovalPrompt("force").build();
		
		//LocalServerReceiver localReceiver = new LocalServerReceiver.Builder().setHost(AUTH_HOST).setPort(AUTH_PORT).build();
		LocalServerReceiver localReceiver = new LocalServerReceiver.Builder().setHost(AUTH_HOST).setPort(AUTH_PORT).build();
		
		String authURL  = new AuthorizationCodeInstalledApp(flow,localReceiver).authorizeForLogin(returnParamMap, redirectUri);
		
		logger.debug("authURL:" + authURL);
				
		return authURL;
	}
	
	public Credential requestForToken(String userId, String code, String redirectUri) {
		Credential credential = null;
		try
		{
			InputStream in = GoogleCalendarService.class
					.getResourceAsStream("/client_secret.json");
			GoogleClientSecrets clientSecrets = GoogleClientSecrets.load(
					JSON_FACTORY, new InputStreamReader(in));
			
			GoogleAuthorizationCodeFlow flow = new GoogleAuthorizationCodeFlow.Builder(
					HTTP_TRANSPORT, JSON_FACTORY, clientSecrets, SCOPES)
					.setDataStoreFactory(DATA_STORE_FACTORY)
					.setAccessType("offline").setApprovalPrompt("force").build();
			
			//LocalServerReceiver localReceiver = new LocalServerReceiver.Builder().setHost(AUTH_HOST).setPort(AUTH_PORT).build();
			LocalServerReceiver localReceiver = new LocalServerReceiver.Builder().setHost(AUTH_HOST).setPort(AUTH_PORT).build();
			
			credential = new AuthorizationCodeInstalledApp(flow,localReceiver).tokenRequest(userId, code, redirectUri);
			
			logger.debug("credential:" + credential);
			
			if( credential != null )
			{
				logger.debug("credential.getAccessToken():" + credential.getAccessToken());
				logger.debug("credential.getRefreshToken():" + credential.getRefreshToken());
				logger.debug("credential.getExpirationTimeMilliseconds():" + credential.getExpirationTimeMilliseconds());
			}
		} catch (Exception ex)
		{
			logger.error("userId({}) code({}) Exception({})", userId, code, ex);
		}
		
		return credential;
	}

	public Calendar getCalendarService(Credential credential) throws IOException {
		try
		{
			return new Calendar.Builder(HTTP_TRANSPORT, JSON_FACTORY, credential)
					.setApplicationName(APPLICATION_NAME).build();
		}catch (Exception ex)
		{
			logger.error("Exception({})", ex);
		}
		return null;
	}
	
	/*public Calendar getCalendarService(String userId, String code, String redirectUri) throws IOException {
		Credential credential = authorizeForToken(userId, code, redirectUri);
		return new Calendar.Builder(HTTP_TRANSPORT, JSON_FACTORY, credential)
				.setApplicationName(APPLICATION_NAME).build();
	}*/
	
	
	public String getCalendarServiceForLogin(HashMap<String, String> returnParamMap, String redirectUri) throws IOException {
		String authURL = authorizeForLogin(returnParamMap, redirectUri);
		return authURL;
	}
	
	/*public static void getCalendarEvent(String userId) throws IOException {
		Calendar service = GoogleCalendarService.getCalendarService(userId);
		// Create a test event.
		Event event = new Event().setSummary("Google I/O 2015").setLocation("800 Howard St., San Francisco, CA 94103")
				.setDescription("A chance to hear more about Google's developer products.");

		System.out.println(String.format("Event created: %s", event.getHtmlLink()));

		// Pause while the user modifies the event in the Calendar UI.
		System.out.println("Modify the event's description and hit enter to continue.");
		System.in.read();

		// Fetch the event again if it's been modified.
		Calendar.Events.Get getRequest = service.events().get("primary", event.getId());
		getRequest.setRequestHeaders(new HttpHeaders().setIfNoneMatch(event.getEtag()));
		try {
			event = getRequest.execute();
			System.out.println("The event was modified, retrieved latest version.");
			
			logger.debug("event:\r\n" + event);
		} catch (GoogleJsonResponseException e) {
			if (e.getStatusCode() == 304) {
				// A 304 status code, "Not modified", indicates that the
				// etags match, and the event has
				// not been modified since we last retrieved it.
				System.out.println("The event was not modified, using local version.");
			} else {
				throw e;
			}
		} finally
		{
			
		}
	}*/

    /*public static void main(String[] args) throws IOException {
        com.google.api.services.calendar.Calendar service = getCalendarService(args[0]);
		// 캘린더 조회
		String pageToken = null;
		do {
		  CalendarList calendarList = service.calendarList().list().setPageToken(pageToken).execute();
		  List<CalendarListEntry> items1 = calendarList.getItems();

		  for (CalendarListEntry calendarListEntry : items1) {
		    System.out.println(calendarListEntry.getSummary());
		    System.out.println(calendarListEntry.getId());
		  }
		  pageToken = calendarList.getNextPageToken();
		} while (pageToken != null);
    }*/
	
	public static final String TRACE_WAS = "WAS";
	public static final String TRACE_GOOGLE_OAUTH = "GOOGLE_OAUTH";
	
	public static final String GOOGLE_OAUTH_REVOKE_URL = "https://accounts.google.com/o/oauth2/revoke?token=";
	
	public boolean requestRevokeAccessToken(String accessToken, String mobileCtn)
	{
		RestRequestData requestData = new RestRequestData(GOOGLE_OAUTH_REVOKE_URL+accessToken);
		requestData.setConnectionTimeout(5000);
		requestData.setTimeout(5000);

		requestData.setTraceId(mobileCtn);
		requestData.setSource(TRACE_WAS);
		requestData.setTarget(TRACE_GOOGLE_OAUTH);
		
		requestData.setHeader("Content-Type", "application/x-www-form-urlencoded");
		
		RestResultData restData = restAgent.requestGET(requestData, proxyHost, proxyPort );
		
		if( restData == null || restData.getStatusCode() != HttpStatus.SC_OK )
		{
			logger.error("AccessToken Revoke INTERWORKING ERROR " + restData);
			return false;
		}
		
		String resultString = restData.getJson(); 
		
		if( resultString != null && resultString.indexOf("error") > -1 )
		{
			logger.error("AccessToken Revoke Token ERROR " + resultString);
			return false;
		}
		
		return true;
	}
	
	public Events requestEventList(com.google.api.services.calendar.Calendar.Events.List requestEventList) throws IOException
	{
		return requestEventList.execute();
	}
}
