package tech.asmussen.dvi.ui.applications;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class TestApplication extends Application {
	
	@Override
	public void start(Stage stage) throws Exception {
		
		FXMLLoader loader = new FXMLLoader(getClass().getResource("test-view.fxml"));
		
		Scene scene = new Scene(loader.load(), 600, 400);
		
		stage.setTitle("DVI - Statistik");
		stage.setScene(scene);
		stage.setResizable(false);
		stage.setOnCloseRequest(event -> System.exit(0));
		stage.show();
	}
	
	public static void main(String[] args) {
		
		launch();
	}
}
