package tech.asmussen.dvi.api;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * The API class is used to communicate with the server.
 *
 * @author Bastian A. W. Asmussen (BastianA)
 * @version 1.0.0
 * @see #URL
 * @see #generateCompleteURL(String)
 * @see #generateConnection(HttpURLConnection, String)
 * @see #isConnected()
 */
public class API {
	
	/**
	 * The URL for the web server to pull data from.
	 */
	public static final String URL = "http://dvimonitor.pilotdrift.dk/monitor.asmx";
	
	/**
	 * Generates a complete URL based on the {@link #URL}.
	 *
	 * @param operator The operator to use in the URL.
	 * @return The complete URL.
	 * @throws MalformedURLException If the URL is malformed or the operator is not valid throw this exception.
	 */
	public static URL generateCompleteURL(String operator) throws MalformedURLException {
		
		return new URL(URL + "?op=" + operator); // Returns the complete URL.
	}
	
	/**
	 * Generates a connection to the web server.
	 *
	 * @param connection The connection to the web server.
	 * @param xml        The XML to send to the web server.
	 * @return The response from the web server.
	 * @throws IOException If the connection to the web server fails throw this exception.
	 */
	public static String generateConnection(HttpURLConnection connection, String xml) throws IOException {
		
		connection.setDoOutput(true); // Set the connection to output data.
		
		DataOutputStream writer = new DataOutputStream(connection.getOutputStream()); // Create a writer to write the XML to the web server.
		
		writer.writeBytes(xml); // Write the XML to the web server.
		writer.flush(); // Flush the writer.
		writer.close(); // Close the writer.
		
		BufferedReader input = new BufferedReader(new InputStreamReader(connection.getInputStream())); // Create a reader to read the response from the web server.
		
		String line; // The line variable will be used to read the response from the web server.
		
		StringBuilder response = new StringBuilder(); // The response variable will be used to store the response from the web server.
		
		while ((line = input.readLine()) != null) // While there is a line to read from the web server run this loop.
			
			response.append(line); // Read the response from the web server.
		
		input.close(); // Close the reader.
		
		return response.toString(); // Return the response from the web server.
	}
	
	/**
	 * Checks if the client (this program) has a connection to the internet.
	 *
	 * @return True if the client has a connection to the internet, false otherwise.
	 */
	public static boolean isConnected() {
		
		try {
			
			new URL("https://google.com")
					.openConnection()
					.connect(); // Try to connect to google.com
			
			return true; // If we can connect to google.com, we are connected.
			
		} catch (IOException e) {
			
			return false; // If we can't connect to google.com, we are not connected.
		}
	}
}
