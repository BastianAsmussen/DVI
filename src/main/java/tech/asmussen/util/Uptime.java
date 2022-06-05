package tech.asmussen.util;

/**
 * A utility class for getting the current time.
 */
public class Uptime {
	
	/**
	 * Format the given time in milliseconds to a human-readable format.
	 *
	 * @param startTime The time in milliseconds the program started running.
	 * @return The formatted time.
	 */
	public static String format(long startTime) {
		
		final long milliseconds = System.currentTimeMillis() - startTime;
		
		final long days = milliseconds / (1_000 * 60 * 60 * 24);
		final long hours = milliseconds / (1_000 * 60 * 60) % 24;
		final long minutes = milliseconds / (1_000 * 60) % 60;
		final long seconds = milliseconds / 1_000 % 60;
		
		return String.format("Oppetid: %d %s, %d %s, %d %s, %d og %s.",
				days, days == 1 ? "dag" : "dage",
				hours, hours == 1 ? "time" : "timer",
				minutes, minutes == 1 ? "minut" : "minutter",
				seconds, seconds == 1 ? "sekund" : "sekunder"
		);
	}
	
	/**
	 * Get the current time in milliseconds.
	 *
	 * @return The current time in milliseconds.
	 */
	public static long getCurrentTime() {
		
		return System.currentTimeMillis();
	}
}
