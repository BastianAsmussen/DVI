package tech.asmussen.dvi.core;

import tech.asmussen.dvi.ui.applications.GUIApplication;
import tech.asmussen.util.Threading;

public class DVI {
	
	/**
	 * A snapshot of the time in milliseconds the program started running.
	 */
	public static final long START_TIME = System.currentTimeMillis();
	
	public static void main(String[] args) {
		
		Threading.createThread(() -> GUIApplication.main(args));
		Threading.startThreads();
		
		News news = new News();
		
		System.out.println(news.get());
	}
}
