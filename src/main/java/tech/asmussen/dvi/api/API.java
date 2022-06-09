package tech.asmussen.dvi.api;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

public class API {
	
	public static final String URL = "http://dvimonitor.pilotdrift.dk/monitor.asmx";
	
	public static URL generateCompleteURL(String operator) throws MalformedURLException {
		
		return new URL(URL + "?op=" + operator);
	}
	
	public static boolean isConnected() {
		
		try {
			
			new URL("https://google.com")
					.openConnection()
					.connect();
			
			return true;
			
		} catch (IOException e) {
			
			return false;
		}
	}
}
