package tech.asmussen.dvi.core;

import tech.asmussen.dvi.ui.applications.TestApplication;

public class DVI {
	
	/**
	 * A snapshot of the time in milliseconds the program started running.
	 */
	public static final long START_TIME = System.currentTimeMillis();
	
	public static void main(String[] args) {
		
		Runnable applicationRunner = () -> TestApplication.main(args);
		
		Thread applicationThread = new Thread(applicationRunner);
		
		applicationThread.start();
	}
}
