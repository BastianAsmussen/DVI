package tech.asmussen.dvi.ui.controllers;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

import tech.asmussen.dvi.core.DVI;
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
	private Label uptimeLabel;
	
	private void updateUI() {
		
		/*
		storageTemperatureLabel.setText(String.format("%.2f°C", DVI.getStorageTemperature()));
		storageHumidityLabel.setText(String.format("%.2f%%", DVI.getStorageHumidity()));
		
		outsideTemperatureLabel.setText(String.format("%.2f°C", DVI.getOutsideTemperature()));
		outsideHumidityLabel.setText(String.format("%.2f%%", DVI.getOutsideHumidity()));
		
		dkTimeLabel.setText(DVI.getTime("DK"));
		gbTimeLabel.setText(DVI.getGBTime("GB"));
		sgTimeLabel.setText(DVI.getSGTime("SG"));
		
		 */
		uptimeLabel.setText(Uptime.format(DVI.START_TIME));
	}
	
	@Override
	public void initialize(URL url, ResourceBundle resourceBundle) {
		
		final ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor();
		
		executorService.scheduleAtFixedRate(() -> Platform.runLater(this::updateUI), 0, 1, TimeUnit.SECONDS);
	}
}
