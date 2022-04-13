package com.crm.genericUtility;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;


public class JavaUtility {
	public static int generateRandomNumber(int limit) {
		Random ran= new Random();
		int randomNumber=ran.nextInt(limit);
		return randomNumber;
	}
	public static String getCurrentTimeAndDate() {
		SimpleDateFormat sdf= new SimpleDateFormat("dd_MM_YYYY_HH_mm_ss");
		Date date= new Date();
		String requiredFormatDate=sdf.format(date);
		return requiredFormatDate;
	}
	
	public static long convertStringToLong(String timeout) {
		long timeouts=Long.parseLong(timeout);
		return timeouts;
	}
}
