package tech.asmussen.dvi.api;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;

public class Storage {
	
	public static double getTemperature() {
		
		final String operator = "StockTemp";
		
		double temperature = 0.0;
		
		try {
			
			URL url = API.generateCompleteURL(operator);
			
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			
			connection.setRequestMethod("POST");
			connection.setRequestProperty("Content-Type", "application/soap+xml; charset=utf-8");
			
			final String xml = "<?xml version=\"1.0\" encoding=\"utf-8\"?> <soap12:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soap12=\"http://www.w3.org/2003/05/soap-envelope\"> <soap12:Body> <StockTemp xmlns=\"http://dvimonitor.pilotdrift.dk/\" /> </soap12:Body> </soap12:Envelope>";
			
			String response = API.generateConnection(connection, xml);
			
			if (!response.contains("<StockTempResult>"))
				
				return temperature;
			
			temperature = Double.parseDouble(response.split("<StockTempResult>")[1].split("</StockTempResult>")[0]);
			
		} catch (IOException | NumberFormatException e) {
			
			e.printStackTrace();
		}
		
		return temperature;
	}
	
	public static double getHumidity() {
		
		final String operator = "StockHumidity";
		
		double humidityPercentage = 0.0;
		
		try {
			
			URL url = API.generateCompleteURL(operator);
			
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			
			connection.setRequestMethod("POST");
			connection.setRequestProperty("Content-Type", "application/soap+xml; charset=utf-8");
			
			final String xml = "<?xml version=\"1.0\" encoding=\"utf-8\"?> <soap12:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soap12=\"http://www.w3.org/2003/05/soap-envelope\"> <soap12:Body> <StockHumidity xmlns=\"http://dvimonitor.pilotdrift.dk/\" /> </soap12:Body> </soap12:Envelope>";
			
			String response = API.generateConnection(connection, xml);
			
			if (!response.contains("<StockHumidityResult>"))
				
				return humidityPercentage;
			
			humidityPercentage = Double.parseDouble(response.split("<StockHumidityResult>")[1].split("</StockHumidityResult>")[0]);
			
		} catch (IOException | NumberFormatException e) {
			
			e.printStackTrace();
		}
		
		return humidityPercentage;
	}
	
	public static ArrayList<String> getItemsUnderMinimum() {
		
		final String operator = "StockItemsUnderMin";
		
		ArrayList<String> items = new ArrayList<>();
		
		try {
			
			URL url = API.generateCompleteURL(operator);
			
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			
			connection.setRequestMethod("POST");
			connection.setRequestProperty("Content-Type", "application/soap+xml; charset=utf-8");
			
			final String xml = "<?xml version=\"1.0\" encoding=\"utf-8\"?> <soap12:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soap12=\"http://www.w3.org/2003/05/soap-envelope\"> <soap12:Body> <StockItemsUnderMin xmlns=\"http://dvimonitor.pilotdrift.dk/\" /> </soap12:Body> </soap12:Envelope>";
			
			String response = API.generateConnection(connection, xml);
			
			if (!response.contains("<StockItemsUnderMinResult>"))
				
				System.out.println(items);
			
			Collections.addAll(items, response.split("<StockItemsUnderMinResult>")[1].split("</StockItemsUnderMinResult>")[0].split("<string>"));
			
			items.replaceAll(s -> s.replace("</string>", ""));
			items.remove(0);
			
			return items;
			
		} catch (IOException | NumberFormatException e) {
			
			e.printStackTrace();
		}
		
		return items;
	}
	
	public static ArrayList<String> getItemsOverMaximum() {
		
		final String operator = "StockItemsOverMax";
		
		ArrayList<String> items = new ArrayList<>();
		
		try {
			
			URL url = API.generateCompleteURL(operator);
			
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			
			connection.setRequestMethod("POST");
			connection.setRequestProperty("Content-Type", "application/soap+xml; charset=utf-8");
			
			final String xml = "<?xml version=\"1.0\" encoding=\"utf-8\"?> <soap12:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soap12=\"http://www.w3.org/2003/05/soap-envelope\"> <soap12:Body> <StockItemsOverMax xmlns=\"http://dvimonitor.pilotdrift.dk/\" /> </soap12:Body> </soap12:Envelope>";
			
			String response = API.generateConnection(connection, xml);
			
			if (!response.contains("<StockItemsOverMaxResult>"))
				
				System.out.println(items);
			
			Collections.addAll(items, response.split("<StockItemsOverMaxResult>")[1].split("</StockItemsOverMaxResult>")[0].split("<string>"));
			
			items.replaceAll(s -> s.replace("</string>", ""));
			items.remove(0);
			
			return items;
			
		} catch (IOException | NumberFormatException e) {
			
			e.printStackTrace();
		}
		
		return items;
	}
	
	public static ArrayList<String> getMostSoldItems() {
		
		final String operator = "StockItemsMostSold";
		
		ArrayList<String> items = new ArrayList<>();
		
		try {
			
			URL url = API.generateCompleteURL(operator);
			
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			
			connection.setRequestMethod("POST");
			connection.setRequestProperty("Content-Type", "application/soap+xml; charset=utf-8");
			
			final String xml = "<?xml version=\"1.0\" encoding=\"utf-8\"?> <soap12:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soap12=\"http://www.w3.org/2003/05/soap-envelope\"> <soap12:Body> <StockItemsMostSold xmlns=\"http://dvimonitor.pilotdrift.dk/\" /> </soap12:Body> </soap12:Envelope>";
			
			String response = API.generateConnection(connection, xml);
			
			if (!response.contains("<StockItemsMostSoldResult>"))
				
				System.out.println(items);
			
			Collections.addAll(items, response.split("<StockItemsMostSoldResult>")[1].split("</StockItemsMostSoldResult>")[0].split("<string>"));
			
			items.replaceAll(s -> s.replace("</string>", ""));
			items.remove(0);
			
			return items;
			
		} catch (IOException | NumberFormatException e) {
			
			e.printStackTrace();
		}
		
		return items;
	}
}
