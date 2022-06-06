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

public class TestController implements Initializable {
	
	@FXML
	private Label uptimeLabel;
	
	private void updateUI() {
		
		uptimeLabel.setText(Uptime.format(DVI.START_TIME));
	}
	
	@Override
	public void initialize(URL url, ResourceBundle resourceBundle) {
		
		final ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor();
		
		executorService.scheduleAtFixedRate(() -> Platform.runLater(this::updateUI), 0, 1, TimeUnit.SECONDS);
	}
}
