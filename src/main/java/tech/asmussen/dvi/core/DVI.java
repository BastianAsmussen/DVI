package tech.asmussen.dvi.core;

import tech.asmussen.dvi.ui.applications.TestApplication;

import java.util.ArrayList;

public class DVI {
	
	/**
	 * A snapshot of the time in milliseconds the program started running.
	 */
	public static final long START_TIME = System.currentTimeMillis();
	
	public static ArrayList<Thread> threadPool = new ArrayList<>();
	
	public static void startThreads() {
		
		for (Thread thread : threadPool)
			
			thread.start();
	}
	
	public static Thread createThread(Runnable runnable) {
		
		return new Thread(runnable);
	}
	
	public static void main(String[] args) {
		
		Runnable applicationRunner = () -> TestApplication.main(args);
		
		threadPool.add(createThread(applicationRunner));
		
		startThreads();
	}
}
