package com.lgu.ccss.common.gcalendar;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lgu.common.util.DateUtils;

public class GoogleCalendarUtil {
	
	private static final Logger logger = LoggerFactory.getLogger(GoogleCalendarUtil.class);
	
	public static final String EXP_DATE_FORMAT = "yyyy-MM-dd HH:mm";
	
	
	public static String convertDateToString(String format, long curTime)
	{
		String returnValue = null;
		try
		{
			returnValue = DateUtils.getFormattedTime(new Date(curTime), format);
		} catch( Exception ex )
		{	
			logger.error("curTime({}) Exception({})", curTime, ex);
		}
		
		return returnValue;
	}

	public static long convertStringToDate(String format, String curTime)
	{
		long returnValue = -1;
		try
		{
			returnValue = DateUtils.stringToDate(curTime, format).getTime();
		} catch(Exception ex)
		{
			logger.error("curTime({}) Exception({})", curTime, ex);
		}
		return returnValue;
	}
}
