package com.lgu.common.esb;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TestDate {
	public static void main(String[] args) throws IOException, ParseException {
		Date now = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		String formatedNow = dateFormat.format(now);
		System.out.println("11111"+formatedNow);
		Date systemNow = dateFormat.parse(formatedNow);
		Date configNow = dateFormat.parse("2022-01-28 04:20");
		System.out.println("111112"+systemNow);
		System.out.println("1111121"+configNow);
		boolean beforeDate = systemNow.after(configNow);
		System.out.println("111113"+beforeDate);
	/*	try { 
			InetAddress ip = InetAddress.getLocalHost(); 
			System.out.println("Host Name = [" + ip.getHostName() + "]"); 
			System.out.println("Host Address = [" + ip.getHostAddress() + "]"); 
			System.out.println("Host Name = [" + ip +"]"); 
			} catch (Exception e) { 
				System.out.println(e); 
				}*/


	}
}
