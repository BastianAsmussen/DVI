package tech.asmussen.dvi.core;

import tech.asmussen.dvi.api.News;
import tech.asmussen.dvi.ui.applications.GUIApplication;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

/**
 * The core class of Dansk Vin Import (DVI).
 *
 * @author Bastian A. W. Asmussen (BastianA)
 * @version 1.0.0
 * @see #START_TIME
 * @see #NEWS_HANDLE
 * @see #getTime(String)
 */
public class DVI {
	
	/**
	 * A snapshot of the time in milliseconds the program started running.
	 */
	public static final long START_TIME = System.currentTimeMillis();
	
	/**
	 * The handle for an instance of the news class.
	 */
	public static final News NEWS_HANDLE = new News();
	
	/**
	 * Returns the current time in the format HH:mm:ss dd-MM-yyyy.
	 *
	 * @param timezone The timezone to use ("DK", "GB" or "SG"). Defaults to "UTC".
	 * @return The current time in the format HH:mm dd-MM-yyyy.
	 */
	public static String getTime(String timezone) {
		
		DateFormat dateFormat = new SimpleDateFormat("HH:mm dd/MM/yyyy"); // HH for hour, mm for minute, ss for second, dd for day, MM for month, yyyy for year.
		
		switch (timezone == null ? "UTC" : timezone.toUpperCase()) {
			
			case "DK" -> dateFormat.setTimeZone(TimeZone.getTimeZone("Europe/Copenhagen")); // Timezone for Denmark.
			case "GB" -> dateFormat.setTimeZone(TimeZone.getTimeZone("Europe/London")); // Timezone for Great Britain.
			case "SG" -> dateFormat.setTimeZone(TimeZone.getTimeZone("Asia/Singapore")); // Timezone for Singapore.
			
			default -> dateFormat.setTimeZone(TimeZone.getTimeZone("UTC")); // Default timezone is UTC.
		}
		
		return dateFormat.format(new Date()); // Returns the current time in the format HH:mm:ss dd-MM-yyyy.
	}
	
	/**
	 * Starts the application.
	 *
	 * @param args The arguments of the program.
	 */
	public static void main(String[] args) {
		
		GUIApplication.main(args); // Starts the GUI and parses the arguments given to the program.
	}
}
