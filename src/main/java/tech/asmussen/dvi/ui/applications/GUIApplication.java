package tech.asmussen.dvi.ui.applications;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class GUIApplication extends Application {
	
	@Override
	public void start(Stage stage) throws Exception {
		
		FXMLLoader loader = new FXMLLoader(getClass().getResource("gui-view.fxml")); // Load the FXML file.
		
		Scene scene = new Scene(loader.load(), 800, 400); // Create the scene.
		
		stage.setTitle("DVI - Statistik"); // Set the title.
		stage.setScene(scene); // Set the scene.
		stage.setResizable(false); // Make the stage non-resizable.
		stage.setOnCloseRequest(event -> System.exit(0)); // Make the program stop when the user clicks the close button.
		stage.show(); // Show the stage.
	}
	
	public static void main(String[] args) {
		
		launch(); // Launch the application.
	}
}
