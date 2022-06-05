module tech.asmussen.dvi.ui {
	
	requires javafx.controls;
	requires javafx.fxml;
	
	opens tech.asmussen.dvi.ui.applications to javafx.fxml;
	exports tech.asmussen.dvi.ui.applications;
	
	opens tech.asmussen.dvi.ui.controllers to javafx.fxml;
	exports tech.asmussen.dvi.ui.controllers;
}