package tech.asmussen.dvi.ui.controllers;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;

import tech.asmussen.dvi.core.DVI;
import tech.asmussen.dvi.core.News;
import tech.asmussen.dvi.core.Outside;
import tech.asmussen.dvi.core.Storage;
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
	private Label outsideTemperatureLabel;
	
	@FXML
	private Label outsideHumidityLabel;
	
	// Denmark time label.
	@FXML
	private Label dkTimeLabel;
	
	// London time label.
	@FXML
	private Label gbTimeLabel;
	
	// Singapore time label.
	@FXML
	private Label sgTimeLabel;
	
	@FXML
	private Label newsLabel;
	
	@FXML
	private Label uptimeLabel;
	
	@FXML
	private ListView<String> underMinimumList;
	
	@FXML
	private ListView<String> overMaximumList;
	
	private static final int WAIT_TIME = 300; // Run every 5 minutes.
	
	private static int iteration = 0;
	
	private void updateUI() {
		
		uptimeLabel.setText(Uptime.format(DVI.START_TIME));
		
		dkTimeLabel.setText(DVI.getTime("DK"));
		gbTimeLabel.setText(DVI.getTime("GB"));
		sgTimeLabel.setText(DVI.getTime("SG"));
		
		if (iteration == 0) {
			
			System.out.println("Updating asynchronous items...");
			
			storageTemperatureLabel.setText(String.format("Temp: %.2f°C", Storage.getTemperature()));
			storageHumidityLabel.setText(String.format("Fugt: %.2f%%", Storage.getHumidity()));
			
			outsideTemperatureLabel.setText(String.format("Temp: %.2f°C", Outside.getTemperature()));
			outsideHumidityLabel.setText(String.format("Fugt: %.2f%%", Outside.getHumidity()));
			
			newsLabel.setText(new News().get());
			
			iteration = WAIT_TIME;
			
		} else
			
			iteration--;
	}
	
	@Override
	public void initialize(URL url, ResourceBundle resourceBundle) {
		
		final ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor();
		
		executorService.scheduleAtFixedRate(() -> Platform.runLater(this::updateUI), 0, 1, TimeUnit.SECONDS);
	}
}
