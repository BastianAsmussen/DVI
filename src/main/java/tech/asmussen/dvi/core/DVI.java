package tech.asmussen.dvi.core;

import tech.asmussen.dvi.ui.applications.GUIApplication;
import tech.asmussen.util.Threading;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public class DVI {
	
	/**
	 * A snapshot of the time in milliseconds the program started running.
	 */
	public static final long START_TIME = System.currentTimeMillis();
	
	public static String getTime(String timezone) {
		
		Date date = new Date();
		
		DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
		
		switch (timezone) {
			
			case "DK" -> dateFormat.setTimeZone(TimeZone.getTimeZone("Europe/Copenhagen"));
			case "GB" -> dateFormat.setTimeZone(TimeZone.getTimeZone("Europe/London"));
			case "SG" -> dateFormat.setTimeZone(TimeZone.getTimeZone("Asia/Singapore"));
			
			default -> dateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
		}
		
		return dateFormat.format(date);
	}
	
	public static void main(String[] args) {
		
		//Threading.createThread(() -> GUIApplication.main(args));
		//Threading.startThreads();
		
		System.out.println(Storage.getTemperature());
	}
}
