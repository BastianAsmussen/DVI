package tech.asmussen.dvi.core;

import tech.asmussen.dvi.api.News;
import tech.asmussen.dvi.ui.applications.GUIApplication;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public class DVI {
	
	/**
	 * A snapshot of the time in milliseconds the program started running.
	 */
	public static final long START_TIME = System.currentTimeMillis();
	
	public static final News NEWS_HANDLE = new News();
	
	public static String getTime(String timezone) {
		
		DateFormat dateFormat = new SimpleDateFormat("HH:mm:ss dd-MM-yyyy");
		
		switch (timezone.toUpperCase()) {
			
			case "DK" -> dateFormat.setTimeZone(TimeZone.getTimeZone("Europe/Copenhagen"));
			case "GB" -> dateFormat.setTimeZone(TimeZone.getTimeZone("Europe/London"));
			case "SG" -> dateFormat.setTimeZone(TimeZone.getTimeZone("Asia/Singapore"));
			
			default -> dateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
		}
		
		return dateFormat.format(new Date());
	}
	
	public static void main(String[] args) {
		
		GUIApplication.main(args);
	}
}
