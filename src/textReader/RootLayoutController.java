package textReader;

import java.io.File;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.AnchorPane;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Pair;
import textReader.MainApp;
import tools.FileUtils;


public class RootLayoutController {
    
	@FXML
	private MenuItem openMenu;
	
	@FXML
	private MenuItem deleteMenu;
	
	@FXML
	private MenuItem author;
	
	@FXML
	private MenuItem aboutReader;
	
	@FXML
	private MenuItem readMode;
	
	@FXML
	private MenuItem normalMode;
	
	private Stage stage;
	private MainApp mainApp;

	public RootLayoutController(MainApp mainApp) {
		this.mainApp = mainApp;
	}

	public void initialize() {
		
		this.stage = mainApp.getPrimaryStage();
		
		openMenuAction(openMenu);
		
		deleteMenuAction(deleteMenu);
		
		aboutAction(author, aboutReader);
		
		modeAction(readMode, normalMode);
		
	}
	
	private void modeAction(MenuItem readMode, MenuItem normalMode){
		readModeAction(readMode);
		normalModeAction(normalMode);
	}
	
	private void normalModeAction(MenuItem normalMode){
		normalMode.setOnAction(new EventHandler<ActionEvent>() {

		    @Override 
		    public void handle(ActionEvent e) {
		    	mainApp.setNormalMode();
		    }
		});
	}
	
	private void readModeAction(MenuItem readMode){
		readMode.setOnAction(new EventHandler<ActionEvent>() {

		    @Override 
		    public void handle(ActionEvent e) {
		    	mainApp.setReadMode();
		    }
		});
	}
	
	/**
	 * add action listener of about Reader & about author
	 * @param author
	 * @param aboutReader
	 */
	private void aboutAction(MenuItem author, MenuItem aboutReader){
		authorAction(author);
		aboutReaderAction(aboutReader);
	}
	
	private void aboutReaderAction(MenuItem aboutReader){
		aboutReader.setOnAction(new EventHandler<ActionEvent>() {

		    @Override 
		    public void handle(ActionEvent e) {
		    	Dialog<String> dialog = new Dialog<>();
		    	dialog.getDialogPane().getButtonTypes().add(ButtonType.CLOSE);
		    	dialog.setTitle("About Reader");
		    	
		    	//FIXME: Remove after release 8u40
		    	dialog.setResizable(true);
		    	dialog.getDialogPane().setPrefSize(480, 500);
		    	
		    	dialog.setContentText("Version: Reader 1.0 \n\n"
		    			+ "Build date: 2016-05-20 \n\n"
		    			+ "Operations: \n"
		    			+ "1. Open a text file: \n"
		    			+ "	click the Open in the File menu, and select a text file in the file chooser.\n\n"
		    			+ "2. Delete a text file: \n"
		    			+ "	select a file in the book list and click the Delete in the File menu.\n\n"
		    			+ "3. Change the background: \n"
		    			+ "	select any color in the colorpicker of background.\n\n"
		    			+ "4. Change the font color:\n"
		    			+ "	select any color in the colorpicker of font color.\n\n"
		    			+ "5. Change the font space:\n"
		    			+ "	click the drop-down menu of space and select any space.\n\n"
		    			+ "6. Change the font size\n"
		    			+ "	click the drop-down menu of size and select any size.\n\n"
		    			+ "7. Line wrap: \n"
		    			+ "	click the button of wrap.\n");
		    	dialog.show();

		    }
		});
	}
	
	/**
	 * create a dialog about author
	 * @param author
	 */
	private void authorAction(MenuItem author){
		author.setOnAction(new EventHandler<ActionEvent>() {

		    @Override 
		    public void handle(ActionEvent e) {
		    	Dialog<Pair<String, String>> dialog = new Dialog<>();
		    	dialog.getDialogPane().getButtonTypes().add(ButtonType.CLOSE);
		    	dialog.setTitle("About author");
		    	dialog.setHeaderText("Hi, I am Grace Tang.");
		    	dialog.setContentText("I am a Chinese programmer :) \n"
		    			+"I am studying in the University of Manchester now.\n"
		    			+"If you have any advice about this reader,\n"
		    			+ "please sent an e-mail to saledouble@qq.com :)\n");
		    	dialog.show();

		    }
		});
	}
	
	/**
	 * add action listener of deleteMenu
	 * to delete a book in a booklist
	 * @param deleteMenu
	 */
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
