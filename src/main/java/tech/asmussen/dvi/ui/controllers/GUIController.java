package tech.asmussen.dvi.ui.controllers;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;

import tech.asmussen.dvi.api.API;
import tech.asmussen.dvi.core.DVI;
import tech.asmussen.dvi.api.Outside;
import tech.asmussen.dvi.api.Storage;
import tech.asmussen.util.Uptime;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class GUIController implements Initializable {
	
	@FXML
	private Label storageTemperatureLabel;
	@FXML
	private Label storageHumidityLabel;
	@FXML
	private ListView<String> overMaximumList;
	@FXML
	private ListView<String> underMinimumList;
	@FXML
	private ListView<String> mostSoldList;
	@FXML
	private Label outsideTemperatureLabel;
	@FXML
	private Label outsideHumidityLabel;
	@FXML
	private Label dkTimeLabel;
	@FXML
	private Label gbTimeLabel;
	@FXML
	private Label sgTimeLabel;
	@FXML
	private Label newsLabel;
	@FXML
	private Label uptimeLabel;
	
	/**
	 * How long should the delay be (in seconds) between GUI updates.
	 */
	private static final int DELAY = 300; // 300 seconds = 5 minutes.
	
	/**
	 * Used to determine if the GUI should update.
	 */
	private static int iteration = 0;
	
	/**
	 * Updates all elements of the GUI and checks if it should update API data.
	 */
	private void updateGUI() {
		
		uptimeLabel.setText(Uptime.format(DVI.START_TIME)); // Update the uptime label.
		
		dkTimeLabel.setText(DVI.getTime("DK")); // Update the DK time label.
		gbTimeLabel.setText(DVI.getTime("GB")); // Update the GB time label.
		sgTimeLabel.setText(DVI.getTime("SG")); // Update the SG time label.
		
		if (iteration == 0) {
			
			newsLabel.setText(DVI.NEWS_HANDLE.getNews()); // Update the news label.
			
			if (API.isConnected()) { // If the API is connected, update the API data.
				
				storageTemperatureLabel.setText(String.format("Temp: %.2f°C", Storage.getTemperature())); // Update the storage temperature label.
				storageHumidityLabel.setText(String.format("Fugt: %.2f%%", Storage.getHumidity())); // Update the storage humidity label.
				
				underMinimumList.getItems().clear(); // Clear the under minimum list.
				overMaximumList.getItems().clear(); // Clear the over maximum list.
				mostSoldList.getItems().clear(); // Clear the most sold list.
				
				underMinimumList.getItems().addAll(Storage.getItemsUnderMinimum()); // Add the items under minimum to the list.
				overMaximumList.getItems().addAll(Storage.getItemsOverMaximum()); // Add the items over maximum to the list.
				mostSoldList.getItems().addAll(Storage.getMostSoldItems()); // Add the most sold items to the list.
				
				outsideTemperatureLabel.setText(String.format("Temp: %.2f°C", Outside.getTemperature())); // Update the outside temperature label.
				outsideHumidityLabel.setText(String.format("Fugt: %.2f%%", Outside.getHumidity())); // Update the outside humidity label.
				
				System.out.printf("[%s] Opdaterede GUI!\n", DVI.getTime("DK")); // Print to the console.
				
			} else
				
				System.err.printf("[%s] Kunne ikke opdatere GUI, ingen internet forbindelse!\n", DVI.getTime("DK")); // Print to the console.
			
			iteration = DELAY; // Reset the timer.
			
		} else
			
			iteration--; // Decrement the timer.
	}
	
	@Override
	public void initialize(URL url, ResourceBundle resourceBundle) {
		
		final ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor(); // Creates a new thread.
		
		executorService.scheduleAtFixedRate(() -> Platform.runLater(this::updateGUI), 0, 1, TimeUnit.SECONDS); // Runs the updateUI method every second.
	}
}
