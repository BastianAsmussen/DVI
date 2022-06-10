package tech.asmussen.dvi.api;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;

public class Storage {
	
	public static double getTemperature() {
		
		final String operator = "StockTemp"; // The name of the operator.
		
		double temperature = 0.0; // The temperature variable.
		
		try {
			
			URL url = API.generateCompleteURL(operator); // The URL to the web service.
			
			HttpURLConnection connection = (HttpURLConnection) url.openConnection(); // The connection to the web service.
			
			connection.setRequestMethod("POST"); // The request method to use.
			connection.setRequestProperty("Content-Type", "application/soap+xml; charset=utf-8"); // The type of data that is sent to the web service.
			
			final String xml = "<?xml version=\"1.0\" encoding=\"utf-8\"?> <soap12:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soap12=\"http://www.w3.org/2003/05/soap-envelope\"> <soap12:Body> <StockTemp xmlns=\"http://dvimonitor.pilotdrift.dk/\" /> </soap12:Body> </soap12:Envelope>"; // The XML to send to the web service.
			
			String response = API.generateConnection(connection, xml); // The response from the web service.
			
			if (!response.contains("<StockTempResult>")) // If the response does not contain the result.
				
				return temperature; // Return the temperature.
			
			temperature = Double.parseDouble(response.split("<StockTempResult>")[1].split("</StockTempResult>")[0]); // Get the temperature from the response.
			
		} catch (IOException | NumberFormatException e) {
			
			e.printStackTrace(); // Print the stack trace.
		}
		
		return temperature; // Return the temperature.
	}
	
	public static double getHumidity() {
		
		final String operator = "StockHumidity"; // The name of the operator.
		
		double humidityPercentage = 0.0; // The humidity percentage variable.
		
		try {
			
			URL url = API.generateCompleteURL(operator); // The URL to the web service.
			
			HttpURLConnection connection = (HttpURLConnection) url.openConnection(); // The connection to the web service.
			
			connection.setRequestMethod("POST"); // The request method to use.
			connection.setRequestProperty("Content-Type", "application/soap+xml; charset=utf-8"); // The type of data that is sent to the web service.
			
			final String xml = "<?xml version=\"1.0\" encoding=\"utf-8\"?> <soap12:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soap12=\"http://www.w3.org/2003/05/soap-envelope\"> <soap12:Body> <StockHumidity xmlns=\"http://dvimonitor.pilotdrift.dk/\" /> </soap12:Body> </soap12:Envelope>"; // The XML to send to the web service.
			
			String response = API.generateConnection(connection, xml); // The response from the web service.
			
			if (!response.contains("<StockHumidityResult>")) // If the response does not contain the result.
				
				return humidityPercentage; // Return the humidity percentage.
			
			humidityPercentage = Double.parseDouble(response.split("<StockHumidityResult>")[1].split("</StockHumidityResult>")[0]); // Get the humidity percentage from the response.
			
		} catch (IOException | NumberFormatException e) {
			
			e.printStackTrace(); // Print the stack trace.
		}
		
		return humidityPercentage; // Return the humidity percentage.
	}
	
	public static ArrayList<String> getItemsUnderMinimum() {
		
		final String operator = "StockItemsUnderMin"; // The name of the operator.
		
		ArrayList<String> items = new ArrayList<>(); // The items variable.
		
		try {
			
			URL url = API.generateCompleteURL(operator); // The URL to the web service.
			
			HttpURLConnection connection = (HttpURLConnection) url.openConnection(); // The connection to the web service.
			
			connection.setRequestMethod("POST"); // The request method to use.
			connection.setRequestProperty("Content-Type", "application/soap+xml; charset=utf-8"); // The type of data that is sent to the web service.
			
			final String xml = "<?xml version=\"1.0\" encoding=\"utf-8\"?> <soap12:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soap12=\"http://www.w3.org/2003/05/soap-envelope\"> <soap12:Body> <StockItemsUnderMin xmlns=\"http://dvimonitor.pilotdrift.dk/\" /> </soap12:Body> </soap12:Envelope>"; // The XML to send to the web service.
			
			String response = API.generateConnection(connection, xml); // The response from the web service.
			
			if (!response.contains("<StockItemsUnderMinResult>")) // If the response does not contain the result.
				
				return items; // Return the items.
			
			Collections.addAll(items, response.split("<StockItemsUnderMinResult>")[1].split("</StockItemsUnderMinResult>")[0].split("<string>")); // Get the items from the response.
			
			items.replaceAll(s -> s.replace("</string>", "")); // Remove the XML tags from the items.
			items.remove(0); // Remove the first item because it is empty.
			
			return items; // Return the items.
			
		} catch (IOException | NumberFormatException e) {
			
			e.printStackTrace(); // Print the stack trace if an error occurs.
		}
		
		return items; // Return the items.
	}
	
	public static ArrayList<String> getItemsOverMaximum() {
		
		final String operator = "StockItemsOverMax"; // The name of the operator.
		
		ArrayList<String> items = new ArrayList<>(); // The items variable.
		
		try {
			
			URL url = API.generateCompleteURL(operator); // The URL to the web service.
			
			HttpURLConnection connection = (HttpURLConnection) url.openConnection(); // The connection to the web service.
			
			connection.setRequestMethod("POST"); // The request method to use.
			connection.setRequestProperty("Content-Type", "application/soap+xml; charset=utf-8"); // The type of data that is sent to the web service.
			
			final String xml = "<?xml version=\"1.0\" encoding=\"utf-8\"?> <soap12:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soap12=\"http://www.w3.org/2003/05/soap-envelope\"> <soap12:Body> <StockItemsOverMax xmlns=\"http://dvimonitor.pilotdrift.dk/\" /> </soap12:Body> </soap12:Envelope>"; // The XML to send to the web service.
			
			String response = API.generateConnection(connection, xml); // The response from the web service.
			
			if (!response.contains("<StockItemsOverMaxResult>")) // If the response does not contain the result.
				
				return items; // Return the items.
			
			Collections.addAll(items, response.split("<StockItemsOverMaxResult>")[1].split("</StockItemsOverMaxResult>")[0].split("<string>")); // Get the items from the response (The items are split by the XML tags).
			
			items.replaceAll(s -> s.replace("</string>", "")); // Remove the XML tags from the items.
			items.remove(0); // Remove the first item because it is empty.
			
			return items; // Return the items.
			
		} catch (IOException | NumberFormatException e) {
			
			e.printStackTrace(); // Print the stack trace if an error occurs.
		}
		
		return items; // Return the items.
	}
	
	public static ArrayList<String> getMostSoldItems() {
		
		final String operator = "StockItemsMostSold"; // The name of the operator.
		
		ArrayList<String> items = new ArrayList<>(); // The items variable.
		
		try {
			
			URL url = API.generateCompleteURL(operator); // The URL to the web service.
			
			HttpURLConnection connection = (HttpURLConnection) url.openConnection(); // The connection to the web service.
			
			connection.setRequestMethod("POST"); // The request method to use.
			connection.setRequestProperty("Content-Type", "application/soap+xml; charset=utf-8"); // The type of data that is sent to the web service.
			
			final String xml = "<?xml version=\"1.0\" encoding=\"utf-8\"?> <soap12:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soap12=\"http://www.w3.org/2003/05/soap-envelope\"> <soap12:Body> <StockItemsMostSold xmlns=\"http://dvimonitor.pilotdrift.dk/\" /> </soap12:Body> </soap12:Envelope>"; // The XML to send to the web service.
			
			String response = API.generateConnection(connection, xml); // The response from the web service.
			
			if (!response.contains("<StockItemsMostSoldResult>")) // If the response does not contain the result.
				
				return items; // Return the items.
			
			Collections.addAll(items, response.split("<StockItemsMostSoldResult>")[1].split("</StockItemsMostSoldResult>")[0].split("<string>")); // Get the items from the response (The items are split by the XML tags).
			
			items.replaceAll(s -> s.replace("</string>", "")); // Remove the XML tags from the items.
			items.remove(0); // Remove the first item because it is empty.
			
			return items; // Return the items.
			
		} catch (IOException | NumberFormatException e) {
			
			e.printStackTrace(); // Print the stack trace if an error occurs.
		}
		
		return items; // Return the items.
	}
}
