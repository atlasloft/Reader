package textReader;

import java.awt.ScrollPane;
import java.awt.TextArea;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextAreaBuilder;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import textReader.MainApp;
import tools.FileUtils;

public class RootLayoutController {
    
	@FXML
	private MenuItem openMenu;
	
	@FXML
	private MenuItem deleteMenu;
	
	private Stage stage;
	private MainApp mainApp;

	public RootLayoutController(MainApp mainApp) {
		this.mainApp = mainApp;
	}

	public void initialize() {
		
		this.stage = mainApp.getPrimaryStage();
		
		openMenuAction(openMenu);
		
		deleteMenuAction(deleteMenu);
		
	}
	
	private void deleteMenuAction(MenuItem deleteMenu){
		deleteMenu.setOnAction(new EventHandler<ActionEvent>() {
			
			// select a text file and print it
		    @Override 
		    public void handle(ActionEvent e) {
				mainApp.deleteFile();
		    }
		});
	}
	
	/**
	 * add an action of openMenu
	 * click to select a text file
	 * @param openMenu
	 */
	private void openMenuAction(MenuItem openMenu){
		openMenu.setOnAction(new EventHandler<ActionEvent>() {
			
			// select a text file and print it
		    @Override 
		    public void handle(ActionEvent e) {
		    	File textFile = FileUtils.getFile(stage);
				if(textFile != null){
					mainApp.loadFiles(textFile);
				}	
		    }
		});
	}


}
