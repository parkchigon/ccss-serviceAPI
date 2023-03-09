package com.lgu.ccss.api.gcalendar.service.model;

import java.util.Date;

public class EventListParameter {
	
	//Google Calendar
	public static final String PARAM_MGRAPPID = "mgrAppId";
	public static final String PARAM_ALWAYSINCLUDEEMAIL = "alwaysIncludeEmail";
	public static final String PARAM_ICALUID = "iCalUID";
	public static final String PARAM_MAXATTENDEES = "maxAttendees";
	public static final String PARAM_MAXRESULTS = "maxResults";
	public static final String PARAM_ORDERBY = "orderBy";
	public static final String PARAM_PAGETOKEN = "pageToken";
	public static final String PARAM_PRIVATEEXTENDEDPROPERTY = "privateExtendedProperty";
	public static final String PARAM_Q = "q";
	public static final String PARAM_SHAREDEXTENDEDPROPERTY = "sharedExtendedProperty";
	public static final String PARAM_SHOWDELETED = "showDeleted";
	public static final String PARAM_SHOWHIDDENINVITATIONS = "showHiddenInvitations";
	public static final String PARAM_SINGLEEVENTS = "singleEvents";
	public static final String PARAM_SYNCTOKEN = "syncToken";
	public static final String PARAM_TIMEMIN = "timeMin";
	public static final String PARAM_TIMEMAX = "timeMax";
	public static final String PARAM_TIMEZONE = "timeZone";
	public static final String PARAM_UPDATEDMIN = "updatedMin";
	
	private String	  mgrAppId = null;
	private boolean   alwaysIncludeEmail = false;     
	private String    iCalUID = null;                
	private int		  maxAttendees = -1;           
	private int   	  maxResults = 250;            
	private String    orderBy = null;                
	private String    pageToken = null;             
	private String    privateExtendedProperty = null;
	private String    q = null;                 
	private String    sharedExtendedProperty = null; 
	private boolean   showDeleted = false;  
	private boolean   showHiddenInvitations = false;  
	private boolean   singleEvents = false;
	private String    syncToken = null;             
	private Date	  timeMax = null;               
	private Date	  timeMin = null;               
	private String    timeZone = null;              
	private Date	  updatedMin = null;
	
	
	public String getMgrAppId() {
		return mgrAppId;
	}
	public void setMgrAppId(String mgrAppId) {
		this.mgrAppId = mgrAppId;
	}


	public boolean isAlwaysIncludeEmail() {
		return alwaysIncludeEmail;
	}
	public void setAlwaysIncludeEmail(boolean alwaysIncludeEmail) {
		this.alwaysIncludeEmail = alwaysIncludeEmail;
	}

	public String getiCalUID() {
		return iCalUID;
	}
	public void setiCalUID(String iCalUID) {
		this.iCalUID = iCalUID;
	}


	public int getMaxAttendees() {
		return maxAttendees;
	}


	public void setMaxAttendees(int maxAttendees) {
		this.maxAttendees = maxAttendees;
	}


	public int getMaxResults() {
		return maxResults;
	}


	public void setMaxResults(int maxResults) {
		this.maxResults = maxResults;
	}


	public String getOrderBy() {
		return orderBy;
	}


	public void setOrderBy(String orderBy) {
		this.orderBy = orderBy;
	}


	public String getPageToken() {
		return pageToken;
	}


	public void setPageToken(String pageToken) {
		this.pageToken = pageToken;
	}


	public String getPrivateExtendedProperty() {
		return privateExtendedProperty;
	}


	public void setPrivateExtendedProperty(String privateExtendedProperty) {
		this.privateExtendedProperty = privateExtendedProperty;
	}


	public String getQ() {
		return q;
	}


	public void setQ(String q) {
		this.q = q;
	}


	public String getSharedExtendedProperty() {
		return sharedExtendedProperty;
	}


	public void setSharedExtendedProperty(String sharedExtendedProperty) {
		this.sharedExtendedProperty = sharedExtendedProperty;
	}


	public boolean isShowDeleted() {
		return showDeleted;
	}


	public void setShowDeleted(boolean showDeleted) {
		this.showDeleted = showDeleted;
	}


	public boolean isShowHiddenInvitations() {
		return showHiddenInvitations;
	}


	public void setShowHiddenInvitations(boolean showHiddenInvitations) {
		this.showHiddenInvitations = showHiddenInvitations;
	}


	public boolean isSingleEvents() {
		return singleEvents;
	}


	public void setSingleEvents(boolean singleEvents) {
		this.singleEvents = singleEvents;
	}


	public String getSyncToken() {
		return syncToken;
	}


	public void setSyncToken(String syncToken) {
		this.syncToken = syncToken;
	}


	public Date getTimeMax() {
		return timeMax;
	}


	public void setTimeMax(Date timeMax) {
		this.timeMax = timeMax;
	}


	public Date getTimeMin() {
		return timeMin;
	}


	public void setTimeMin(Date timeMin) {
		this.timeMin = timeMin;
	}


	public String getTimeZone() {
		return timeZone;
	}


	public void setTimeZone(String timeZone) {
		this.timeZone = timeZone;
	}


	public Date getUpdatedMin() {
		return updatedMin;
	}


	public void setUpdatedMin(Date updatedMin) {
		this.updatedMin = updatedMin;
	}


	@Override
	public String toString() {
		return this.getClass().getName() 
				+ " [alwaysIncludeEmail=" + alwaysIncludeEmail + ", iCalUID=" + iCalUID + ", maxAttendees=" + maxAttendees
				+ " [maxResults=" + maxResults + ", orderBy=" + orderBy + ", pageToken=" + pageToken
				+ " [privateExtendedProperty=" + privateExtendedProperty + ", q=" + q + ", sharedExtendedProperty=" + sharedExtendedProperty
				+ " [showDeleted=" + showDeleted + ", showHiddenInvitations=" + showHiddenInvitations + ", singleEvents=" + singleEvents
				+ " [timeMax=" + timeMax + ", timeMin=" + timeMin + ", timeZone=" + timeZone + ", updatedMin=" + updatedMin
				+ "]";
	}
}
