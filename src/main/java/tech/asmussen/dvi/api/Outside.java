package tech.asmussen.dvi.api;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * A class that is used to fetch information about outside the building.
 *
 * @author Bastian A. W. Asmussen (BastianA)
 * @version 1.0.0
 * @see #getTemperature()
 * @see #getHumidity()
 */
public class Outside {
	
	/**
	 * Get the temperature from outside the location.
	 *
	 * @return The temperature of outside the location in celsius.
	 */
	public static double getTemperature() {
		
		final String operator = "OutdoorTemp"; // This is the name of the method in the web service.
		
		double temperature = 0.0; // This is the variable that will hold the temperature.
		
		try {
			
			URL url = API.generateCompleteURL(operator); // This is the URL to the web service.
			
			HttpURLConnection connection = (HttpURLConnection) url.openConnection(); // This is the connection to the web service.
			
			connection.setRequestMethod("POST"); // This is the request method to use.
			connection.setRequestProperty("Content-Type", "application/soap+xml; charset=utf-8"); // This is the type of data that is sent to the web service.
			
			final String xml = "<?xml version=\"1.0\" encoding=\"utf-8\"?> <soap12:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soap12=\"http://www.w3.org/2003/05/soap-envelope\"> <soap12:Body> <OutdoorTemp xmlns=\"http://dvimonitor.pilotdrift.dk/\" /> </soap12:Body> </soap12:Envelope>"; // This is the XML that is sent to the web service.
			
			String response = API.generateConnection(connection, xml); // This is the response from the web service.
			
			if (!response.contains("<OutdoorTempResult>")) // This is the check to see if the web service returned a valid value.
				
				return temperature; // This returns the temperature.
			
			temperature = Double.parseDouble(response.split("<OutdoorTempResult>")[1].split("</OutdoorTempResult>")[0]); // This is the temperature that is returned from the web service.
			
		} catch (IOException | NumberFormatException e) {
			
			e.printStackTrace(); // This prints the stack trace if an error occurs.
		}
		
		return temperature; // This returns the temperature.
	}
	
	/**
	 * Get the humidity from outside the building.
	 *
	 * @return The humidity percentage from outside the building.
	 */
	public static double getHumidity() {
		
		final String operator = "OutdoorHumidity"; // This is the name of the method in the web service.
		
		double humidityPercentage = 0.0; // This is the variable that will hold the humidity percentage.
		
		try {
			
			URL url = API.generateCompleteURL(operator); // This is the URL to the web service.
			
			HttpURLConnection connection = (HttpURLConnection) url.openConnection(); // This is the connection to the web service.
			
			connection.setRequestMethod("POST"); // This is the request method to use.
			connection.setRequestProperty("Content-Type", "application/soap+xml; charset=utf-8"); // This is the type of data that is sent to the web service.
			
			final String xml = "<?xml version=\"1.0\" encoding=\"utf-8\"?> <soap12:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soap12=\"http://www.w3.org/2003/05/soap-envelope\"> <soap12:Body> <OutdoorHumidity xmlns=\"http://dvimonitor.pilotdrift.dk/\" /> </soap12:Body> </soap12:Envelope>"; // This is the XML that is sent to the web service.
			
			String response = API.generateConnection(connection, xml); // This is the response from the web service.
			
			if (!response.contains("<OutdoorHumidityResult>")) // This is the check to see if the web service returned a valid value.
				
				return humidityPercentage; // This returns the humidity percentage.
			
			humidityPercentage = Double.parseDouble(response.split("<OutdoorHumidityResult>")[1].split("</OutdoorHumidityResult>")[0]); // This is the humidity percentage that is returned from the web service.
			
		} catch (IOException | NumberFormatException e) {
			
			e.printStackTrace(); // This prints the stack trace if an error occurs.
		}
		
		return humidityPercentage; // This returns the humidity percentage.
	}
}
