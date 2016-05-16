package textReader;

import java.io.File;
import java.io.IOException;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.paint.Color;
import textReader.model.BookItem;
import textReader.model.BookList;
import textReader.model.BookListWrapper;
import tools.FileUtils;

public class ReaderOverviewController {
	
	@FXML
	private TextArea textArea;
	
	@FXML
	private ListView<BookItem> listView;
	
	@FXML
	private ColorPicker fontColor;
	
	@FXML
	private ColorPicker background;
		
	private BookList bookList = new BookList();
	
	//这两个的默认值问题需要修改！！！
	private String fontColorString;
	private String backgroundString;
	
	private String css = this.getClass().getResource("view/textArea.css").toExternalForm();
	
	public void addListViewItem(File file){
		BookItem newBookItem = new BookItem(file);
		bookList.add(newBookItem);
		listView.setItems(bookList.getBookList());
		
		saveBookDataToFile(new File("model/books.xml"));
		
	}
	
	/**
	 * print the content of text file in text area
	 * @param file
	 */
	public void printTextInTextArea(File file){
		try {
			
			this.textArea.setText(FileUtils.printFile(file));
			
		} catch (IOException e1) {
			
			e1.printStackTrace();
		}
	}
		
	public void initialize() {
		
		listView.setItems(bookList.getBookList());
		listView.setCellFactory((ListView<BookItem> bookItem) -> new BookCell());
		
		textArea.getStylesheets().add(css);
		
		loadBookDataFromFile(new File("model/books.xml"));
		
		fontColorPickerAction(fontColor);
		backgroundColorPickerAction(background);
		
	}
	
	private void backgroundColorPickerAction(ColorPicker background){
		background.setOnAction(new EventHandler<ActionEvent>(){

			@Override
			public void handle(ActionEvent event) {
				Color color = background.getValue();
				
				backgroundString = toRgbString(color);
				
				//save the change in CSS
				FileUtils.operateTextAreaCSSwithBackground(backgroundString);
				
				textArea.setStyle("-fx-control-inner-background: " +backgroundString  + ";"+
						"-fx-text-fill: " + fontColorString + ";" );
	
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
	 * Loads book data from the specified file. The current person data will
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
	        
	        this.bookList.addAll(wrapper.getBooks());

	        // Save the file path to the registry.
	        //setPersonFilePath(file);

	    } catch (Exception e) { // catches ANY exception
//	        Dialogs.create()
//	                .title("Error")
//	                .masthead("Could not load data from file:\n" + file.getPath())
//	                .showException(e);
	    	
	    }
	}
	
	/**
	 * Saves the current book data to the specified file.
	 * 
	 * @param file
	 */
	public void saveBookDataToFile(File file) {
	    try {
	        JAXBContext context = JAXBContext
	                .newInstance(BookListWrapper.class);
	        Marshaller m = context.createMarshaller();
	        m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

	        //test......
	        //System.out.println(this.bookList.getComponent(0).getFileName());
	            
	        // Wrapping our book data.
	        BookListWrapper wrapper = new BookListWrapper();
	        wrapper.setBooks(this.bookList.getBookList());

	        System.out.println(wrapper.getBooks().get(0).getFileName());
	        
	        // Marshalling and saving XML to the file.
	        m.marshal(wrapper, file);
	        
	        //test output
	    	m.marshal(wrapper, System.out);

	        // Save the file path to the registry.
	        //setPersonFilePath(file);
	    } catch (Exception e) { // catches ANY exception
//	        Dialogs.create().title("Error")
//	                .masthead("Could not save data to file:\n" + file.getPath())
//	                .showException(e);
	    }
	}
	
}
