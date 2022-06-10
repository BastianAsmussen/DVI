package tech.asmussen.dvi.ui.controllers;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;

import tech.asmussen.dvi.api.API;
import tech.asmussen.dvi.core.DVI;
import tech.asmussen.dvi.api.News;
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
	private ListView<String> underMinimumList;
	
	@FXML
	private ListView<String> overMaximumList;
	
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
	
	private static final int DELAY = 300; // 300 seconds = 5 minutes.
	
	private static int iteration = 0;
	
	private void updateUI() {
		
		uptimeLabel.setText(Uptime.format(DVI.START_TIME));
		
		dkTimeLabel.setText(DVI.getTime("DK"));
		gbTimeLabel.setText(DVI.getTime("GB"));
		sgTimeLabel.setText(DVI.getTime("SG"));
		
		if (iteration == 0) {
			
			if (API.isConnected()) {
				
				storageTemperatureLabel.setText(String.format("Temp: %.2f°C", Storage.getTemperature()));
				storageHumidityLabel.setText(String.format("Fugt: %.2f%%", Storage.getHumidity()));
				
				underMinimumList.getItems().clear();
				overMaximumList.getItems().clear();
				mostSoldList.getItems().clear();
				
				underMinimumList.getItems().addAll(Storage.getItemsUnderMinimum());
				overMaximumList.getItems().addAll(Storage.getItemsOverMaximum());
				mostSoldList.getItems().addAll(Storage.getMostSoldItems());
				
				outsideTemperatureLabel.setText(String.format("Temp: %.2f°C", Outside.getTemperature()));
				outsideHumidityLabel.setText(String.format("Fugt: %.2f%%", Outside.getHumidity()));
				
				newsLabel.setText(new News().getNews());
				
				System.out.printf("[%s] Opdaterede GUI!\n", DVI.getTime("DK"));
				
			} else
				
				System.err.printf("[%s] Kunne ikke opdatere GUI, ingen internet forbindelse!\n", DVI.getTime("DK"));
			
			iteration = DELAY;
			
		} else
			
			iteration--;
	}
	
	@Override
	public void initialize(URL url, ResourceBundle resourceBundle) {
		
		final ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor();
		
		executorService.scheduleAtFixedRate(() -> Platform.runLater(this::updateUI), 0, 1, TimeUnit.SECONDS);
	}
}
