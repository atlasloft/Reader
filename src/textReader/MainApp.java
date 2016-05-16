package textReader;

import java.io.File;
import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

/**
 * main entrance of this application
 * @author Grace
 *
 */
public class MainApp extends Application {
	
    private Stage primaryStage;
    private BorderPane rootLayout;
 //   private BookList bookList = new BookList();
    private ReaderOverviewController controllerReader;
    
    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("Reader");

       // loadBookDataFromFile(new File("model/books.xml")); 
        
        initRootLayout();

        showReaderOverview();
        
    }
    
    
    /**
     * Initializes the root layout.
     */
    public void initRootLayout() {
        try {
            // Load root layout from fxml file.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("view/RootLayout.fxml"));
            
            // set the controller of rootLayout    
            loader.setController(new RootLayoutController(this));
            
            rootLayout = (BorderPane) loader.load();
            
            // Show the scene containing the root layout.
            Scene scene = new Scene(rootLayout);
            primaryStage.setScene(scene);
                 
            primaryStage.show();
            
        } catch (IOException e) {
            e.getCause() ;
        }
    }
    
    /**
     * Shows the reader overview inside the root layout.
     */
    public void showReaderOverview() {
        try {
            // Load person overview.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("view/ReaderOverview.fxml"));
            
            controllerReader = new ReaderOverviewController();
            
            // set the controller of rootLayout      
            loader.setController(controllerReader);
            
            AnchorPane readerOverview = (AnchorPane) loader.load();

            // Set person overview into the center of root layout.
            rootLayout.setCenter(readerOverview);;
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    /**
     * Returns the main stage.
     * @return
     */
    public Stage getPrimaryStage() {
        return primaryStage;
    }
    

	public static void main(String[] args) {
		launch(args);
	}

	/**
	 * add the new book into book list
	 * print the content of new book in the text area 
	 * @param textFile
	 */
	public void loadFiles(File textFile) {
		this.controllerReader.printTextInTextArea(textFile);
		this.controllerReader.addListViewItem(textFile);
		
	}
	
	public void deleteFile(){
		this.controllerReader.deleteFile();
	}
	
	
}
