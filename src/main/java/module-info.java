module tech.asmussen.dvi.ui {
	
	requires javafx.controls;
	requires javafx.fxml;
	
	requires com.rometools.rome;
	requires java.desktop;
	
	opens tech.asmussen.dvi.ui.applications to javafx.fxml;
	exports tech.asmussen.dvi.ui.applications;
	
	opens tech.asmussen.dvi.ui.controllers to javafx.fxml;
	exports tech.asmussen.dvi.ui.controllers;
}
