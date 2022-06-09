package tech.asmussen.dvi.api;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class Outside {
	
	public static double getTemperature() {
		
		final String operator = "OutdoorTemp";
		
		double temperature = 0.0;
		
		try {
			
			URL url = API.generateCompleteURL(operator);
			
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			
			connection.setRequestMethod("POST");
			connection.setRequestProperty("Content-Type", "application/soap+xml; charset=utf-8");
			
			final String xml = "<?xml version=\"1.0\" encoding=\"utf-8\"?> <soap12:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soap12=\"http://www.w3.org/2003/05/soap-envelope\"> <soap12:Body> <OutdoorTemp xmlns=\"http://dvimonitor.pilotdrift.dk/\" /> </soap12:Body> </soap12:Envelope>";
			
			connection.setDoOutput(true);
			
			DataOutputStream writer = new DataOutputStream(connection.getOutputStream());
			
			writer.writeBytes(xml);
			writer.flush();
			writer.close();
			
			BufferedReader input = new BufferedReader(
					new InputStreamReader(connection.getInputStream()));
			
			String line;
			
			StringBuilder response = new StringBuilder();
			
			while ((line = input.readLine()) != null)
				
				response.append(line);
			
			input.close();
			
			if (!response.toString().contains("<OutdoorTempResult>"))
				
				return temperature;
			
			temperature = Double.parseDouble(response.toString().split("<OutdoorTempResult>")[1].split("</OutdoorTempResult>")[0]);
			
		} catch (IOException | NumberFormatException e) {
			
			e.printStackTrace();
		}
		
		return temperature;
	}
	
	public static double getHumidity() {
		
		final String operator = "OutdoorHumidity";
		
		double humidityPercentage = 0.0;
		
		try {
			
			URL url = API.generateCompleteURL(operator);
			
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			
			connection.setRequestMethod("POST");
			connection.setRequestProperty("Content-Type", "application/soap+xml; charset=utf-8");
			
			final String xml = "<?xml version=\"1.0\" encoding=\"utf-8\"?> <soap12:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soap12=\"http://www.w3.org/2003/05/soap-envelope\"> <soap12:Body> <OutdoorHumidity xmlns=\"http://dvimonitor.pilotdrift.dk/\" /> </soap12:Body> </soap12:Envelope>";
			
			connection.setDoOutput(true);
			
			DataOutputStream writer = new DataOutputStream(connection.getOutputStream());
			
			writer.writeBytes(xml);
			writer.flush();
			writer.close();
			
			BufferedReader input = new BufferedReader(
					new InputStreamReader(connection.getInputStream()));
			
			String line;
			
			StringBuilder response = new StringBuilder();
			
			while ((line = input.readLine()) != null)
				
				response.append(line);
			
			input.close();
			
			if (!response.toString().contains("<OutdoorHumidityResult>"))
				
				return humidityPercentage;
			
			humidityPercentage = Double.parseDouble(response.toString().split("<OutdoorHumidityResult>")[1].split("</OutdoorHumidityResult>")[0]);
			
		} catch (IOException | NumberFormatException e) {
			
			e.printStackTrace();
		}
		
		return humidityPercentage;
	}
}
