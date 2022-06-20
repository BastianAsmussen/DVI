package tech.asmussen.util;

/**
 * A utility class for getting the current time.
 *
 * @author Bastian A. W. Asmussen (BastianA)
 * @version 1.0.0
 * @see #formatTime(long)
 */
public class Uptime {
	
	/**
	 * Format the given time in milliseconds to a human-readable format.
	 *
	 * @param startTime The time in milliseconds the program started running.
	 * @return The formatted time.
	 */
	public static String formatTime(long startTime) {
		
		final long milliseconds = System.currentTimeMillis() - startTime; // Get the time in milliseconds since the program started running.
		
		final long days = milliseconds / (1_000 * 60 * 60 * 24); // Get the number of days since the program started running.
		final long hours = milliseconds / (1_000 * 60 * 60) % 24; // Get the number of hours since the program started running.
		final long minutes = milliseconds / (1_000 * 60) % 60; // Get the number of minutes since the program started running.
		final long seconds = milliseconds / 1_000 % 60; // Get the number of seconds since the program started running.
		
		return String.format("Oppetid: %d %s, %d %s, %d %s og %d %s.",
				days, days == 1 ? "dag" : "dage",
				hours, hours == 1 ? "time" : "timer",
				minutes, minutes == 1 ? "minut" : "minutter",
				seconds, seconds == 1 ? "sekund" : "sekunder"
		);
	}
}
