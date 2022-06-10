package tech.asmussen.dvi.api;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class API {
	
	public static final String URL = "http://dvimonitor.pilotdrift.dk/monitor.asmx";
	
	public static URL generateCompleteURL(String operator) throws MalformedURLException {
		
		return new URL(URL + "?op=" + operator);
	}
	
	public static boolean isConnected() {
		
		try {
			
			new URL("https://google.com").openConnection().connect();
			
			return true;
			
		} catch (IOException e) {
			
			return false;
		}
	}
	
	public static String generateConnection(HttpURLConnection connection, String xml) throws IOException {
		
		connection.setDoOutput(true);
		
		DataOutputStream writer = new DataOutputStream(connection.getOutputStream());
		
		writer.writeBytes(xml);
		writer.flush();
		writer.close();
		
		BufferedReader input = new BufferedReader(new InputStreamReader(connection.getInputStream()));
		
		String line;
		
		StringBuilder response = new StringBuilder();
		
		while ((line = input.readLine()) != null)
			
			response.append(line);
		
		input.close();
		
		return response.toString();
	}
}
