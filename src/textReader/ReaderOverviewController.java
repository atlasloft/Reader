package textReader;

import java.io.File;
import java.io.IOException;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import textReader.model.BookItem;
import textReader.model.BookList;
import textReader.model.BookListWrapper;
import tools.FileUtils;

public class ReaderOverviewController {
	
	@FXML
	private TextArea textArea;
	
	@FXML
	private ColorPicker fontColor;
	
	@FXML
	private ColorPicker background;
		
	@FXML
	private ListView<BookItem> listView;
	
	@FXML
	private MenuItem smallFont;
	
	@FXML
	private MenuItem mediumFont;
	
	@FXML
	private MenuItem largeFont;
	
	@FXML
	private MenuItem oneSpace;
	
	@FXML
	private MenuItem twoSpace;
	
	@FXML
	private Button wrap;
	
	@FXML
	private MenuItem fontTimes;
	
	@FXML
	private MenuItem fontArial;
		
	ObservableList<BookItem> books = FXCollections.observableArrayList();;
	
	private BookList bookList = new BookList();
	
	private boolean wrapFlag = true;
	
	private File file ;
	
	//这四个的默认值问题需要修改！！！
	//删除文件有问题
	private String fontColorString = "white";
	private String backgroundString = "black";
	private int fontSize = 12;
	//private int fontSpace = 1;
	
	private String css = this.getClass().getResource("view/textArea.css").toExternalForm();
	
	public void initialize() {
		
		textArea.getStylesheets().add(css);
		
		loadBookDataFromFile(new File("src/textReader/model/books.xml"));
		
		Actions();	
		
		listView.setItems(books);
		listView.setCellFactory((ListView<BookItem> bookItem) -> new BookCell());
		
	}
	
	/**
	 * the actions of all components
	 */
	public void Actions(){
		
		// add action listener of color picker
		fontColorPickerAction(fontColor);
		backgroundColorPickerAction(background);
		
		// add action listener of font size
		fontSizeAction(smallFont, mediumFont, largeFont);
		
		fontSpace(oneSpace, twoSpace);
		
		fontAction(fontTimes, fontArial);
		
		// add action listener of listView
		listViewAction(listView);
		
		wrapAction(wrap);
	}
	
	/**
	 * change the font when the menu is clicked
	 * @param fontTimes
	 * @param fontArial
	 */
	private void fontAction(MenuItem fontTimes, MenuItem fontArial){
		fontTimesAction(fontTimes);
		fontArialAction(fontArial);
	}
	
	private void fontArialAction(MenuItem fontArial){
		fontArial.setOnAction(new EventHandler<ActionEvent>(){

			@Override
			public void handle(ActionEvent event) {
				textArea.setFont(Font.font("Arial"));
			}
	    });
	}
	
	private void fontTimesAction(MenuItem fontTimes){
		fontTimes.setOnAction(new EventHandler<ActionEvent>(){

			@Override
			public void handle(ActionEvent event) {
				textArea.setFont(Font.font("Times New Roman"));
			}
	    });
	}
	
	/**
	 * line wrap
	 * @param wrap
	 */
	private void wrapAction(Button wrap){
		wrap.setOnAction(new EventHandler<ActionEvent>(){

			@Override
			public void handle(ActionEvent event) {
				textArea.setWrapText(wrapFlag);
				wrapFlag = !wrapFlag;
			}
	    });
	}
	
	private void fontSpace(MenuItem oneSpace, MenuItem twoSpace){
		oneSpaceAction(oneSpace);
		twoSpaceAction(twoSpace);
	}
	
	private void twoSpaceAction(MenuItem twoSpace){
		twoSpace.setOnAction(new EventHandler<ActionEvent>(){

			@Override
			public void handle(ActionEvent event) {
				try {
					
					textArea.setText(FileUtils.printDoubleSpaceFile(file));
					
				} catch (IOException e1) {
					
					e1.printStackTrace();
				}
	
			}
	    });
	}
	
	private void oneSpaceAction(MenuItem oneSpace){
		oneSpace.setOnAction(new EventHandler<ActionEvent>(){

			@Override
			public void handle(ActionEvent event) {
				try {
					
					textArea.setText(FileUtils.printDoubleSpaceFile(file));
					
				} catch (IOException e1) {
					
					e1.printStackTrace();
				}
	
			}
	    });
	}
	
	/**
	 * add an action listener of listView
	 * @param listView
	 */
	public void listViewAction(ListView<BookItem> listView){
	    listView.setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				BookItem bookItem = listView.getSelectionModel().getSelectedItem();
				printTextInTextArea(bookItem.getFile());
			}
	    });
	}
	
	/**
	 * add an action listener of font size
	 * @param smallFont
	 * @param mediumFont
	 * @param largeItem
	 */
	public void fontSizeAction(MenuItem smallFont, MenuItem mediumFont, MenuItem largeItem){
		smallFontAction(smallFont);
		mediumFontAction(mediumFont);
		largeFontAction(largeFont);
	}
	
	/**
	 * set large size of font in text Area
	 * @param largeFont
	 */
	public void largeFontAction(MenuItem smallFont){
		smallFont.setOnAction(new EventHandler<ActionEvent>(){

			@Override
			public void handle(ActionEvent event) {
				fontSize = 16;
				textArea.setStyle("-fx-control-inner-background: " +backgroundString  + ";"+
						"-fx-text-fill: " + fontColorString + ";" +
						"-fx-font-size: 16pt;");
	
			}
	    });
	}
	
	/**
	 * set medium size of font in text Area
	 * @param mediumFont
	 */
	public void mediumFontAction(MenuItem smallFont){
		smallFont.setOnAction(new EventHandler<ActionEvent>(){

			@Override
			public void handle(ActionEvent event) {
				fontSize = 14;
				textArea.setStyle("-fx-control-inner-background: " +backgroundString  + ";"+
						"-fx-text-fill: " + fontColorString + ";" +
						"-fx-font-size: 14pt;");
	
			}
	    });
	}
	
	/**
	 * set small size of font in text Area
	 * @param smallFont
	 */
	public void smallFontAction(MenuItem smallFont){
		smallFont.setOnAction(new EventHandler<ActionEvent>(){

			@Override
			public void handle(ActionEvent event) {
				fontSize  = 12;
				textArea.setStyle("-fx-control-inner-background: " +backgroundString  + ";"+
						"-fx-text-fill: " + fontColorString + ";" +
						"-fx-font-size: 12pt;");
	
			}
	    });
	}
	
	
	/**
	 * delete a chosen book in the book list
	 */
	public void deleteFile(){
		BookItem bookItem = listView.getSelectionModel().getSelectedItem();
		if(bookItem!= null){			
			
			listView.getSelectionModel().clearSelection(); 
			bookList.remove(bookItem.getBookIndex(), books);
			
			saveBookDataToFile(new File("src/textReader/model/books.xml"));
			
		}
	}
	
	/**
	 * add a new book into the book list
	 * @param file
	 */
	// 需要不能打开相同的file, 以后添加实现
	public void addListViewItem(File file){
		BookItem newBookItem = new BookItem();
		newBookItem.setFile(file);
		newBookItem.setBookIndex(bookList.totalNumberOfBooks(books));
		
		bookList.add(newBookItem, books);
		//listView.setItems(books);
		
		saveBookDataToFile(new File("src/textReader/model/books.xml"));
		
	}
	
	/**
	 * print the content of text file in text area
	 * @param file
	 */
	public void printTextInTextArea(File file){
		try {
			
			this.textArea.setText(FileUtils.printFile(file));
			this.file = file;
			
		} catch (IOException e1) {
			
			e1.printStackTrace();
		}
	}
	
	/**
	 * change the background of text area
	 * @param background
	 */
	private void backgroundColorPickerAction(ColorPicker background){
		background.setOnAction(new EventHandler<ActionEvent>(){

			@Override
			public void handle(ActionEvent event) {
				Color color = background.getValue();
				
				backgroundString = toRgbString(color);
				
				//save the change in CSS
				FileUtils.operateTextAreaCSSwithBackground(backgroundString);
				
				textArea.setStyle("-fx-control-inner-background: " +backgroundString  + ";"+
						"-fx-text-fill: " + fontColorString + ";" +
						"-fx-font-size: " + fontSize + ";");
	
			}
	    });
	}
	
	/**
	 * change the font color when the users select color in color picker
	 * @param fontColor
	 */
	private void fontColorPickerAction(ColorPicker fontColor){
	    fontColor.setOnAction(new EventHandler<ActionEvent>(){

			@Override
			public void handle(ActionEvent event) {
				Color color = fontColor.getValue();
				
				fontColorString = toRgbString(color);
				
				// save the change in CSS
				FileUtils.operateTextAreaCSSwithFontColor(fontColorString);
				
		        textArea.setStyle("-fx-control-inner-background: " +backgroundString  + ";"+
						"-fx-text-fill: " + fontColorString + ";");
			}
	    });

	}
	
	/**
	 * convert color into the string of RED, GREEN and BLUE from 0-255
	 * @param c
	 * @return
	 */
	private String toRgbString(Color c) {
		return "rgb("
					+ to255Int(c.getRed())
	                + "," + to255Int(c.getGreen())
	                + "," + to255Int(c.getBlue())
	           + ")";
	    }
	
	private int to255Int(double d) {
		return (int) (d * 255);
	}
	

	/**
	 * Loads book data from the "src/textReader/model/books.xml". The current book data will
	 * be replaced.
	 * 
	 * @param file
	 */
	public void loadBookDataFromFile(File file) {
	    try {
	        JAXBContext context = JAXBContext
	                .newInstance(BookListWrapper.class);
	        Unmarshaller um = context.createUnmarshaller();

	        // Reading XML from the file and unmarshalling.
	        BookListWrapper wrapper = (BookListWrapper) um.unmarshal(file);
	        
	        bookList.addAll(wrapper.getBooks(), books);

	    } catch (Exception e) { // catches ANY exception
	    	
	    }
	}
	
	/**
	 * Saves the current book data to the specified file: src/textReader/model/books.xml.
	 * 
	 * @param file
	 */
	public void saveBookDataToFile(File file) {
	    try {
	        JAXBContext context = JAXBContext
	                .newInstance(BookListWrapper.class);
	        Marshaller m = context.createMarshaller();
	        m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
	            
	        // Wrapping our book data.
	        BookListWrapper wrapper = new BookListWrapper();
	        wrapper.setBooks(books);
	        
	        //test output
	    	m.marshal(wrapper, System.out);
	    	
	    	// Marshalling and saving XML to the file.
	        m.marshal(wrapper, file);
	        

	    } catch (Exception e) {
	    	e.printStackTrace();
	    }
	}

	/**
	 * set read mode: 
	 * full screen of TextArea
	 * @param rootLayout
	 */
	public void setReadMode(BorderPane rootLayout) {
		rootLayout.setCenter(textArea);
		
	}
	
	
}
