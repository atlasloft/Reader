package tools;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;

public class FileUtils {
	
	/**
	 * users select a file from a file chooser
	 * return the chosen file
	 * @param Stage
	 * @return
	 */
	public static File getFile(Stage Stage) {
		FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle("Open text file");
		 fileChooser.getExtensionFilters().addAll(
		         new ExtensionFilter("Text Files", "*.txt"));
		 
		return fileChooser.showOpenDialog(Stage);
	}
	
	/**
	 * get file path
	 * @param file
	 * @return
	 */
	public static String getFilePath(File file){
		return file.getAbsolutePath();
	}
	
	/**
	 * get the text file content
	 * @param file
	 * @return
	 * @throws IOException
	 */
	public static String printFile(File file) throws IOException{
		StringBuffer stringBuffer = new StringBuffer();		
	    BufferedReader bufferedReader;
	    
		try {
			bufferedReader = new BufferedReader(new FileReader(file));
			for (String line; (line = bufferedReader.readLine()) != null;) {
				stringBuffer.append(line);
				stringBuffer.append("\n");
			}
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		return stringBuffer.toString();
	}
	
	/**
	 * get the text file content in double space
	 * @param file
	 * @return
	 * @throws IOException
	 */
	public static String printDoubleSpaceFile(File file) throws IOException{
		StringBuffer stringBuffer = new StringBuffer();		
	    BufferedReader bufferedReader;
	    
		try {
			bufferedReader = new BufferedReader(new FileReader(file));
			for (String line; (line = bufferedReader.readLine()) != null;) {
				stringBuffer.append(line);
				stringBuffer.append("\n\n");
			}
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		return stringBuffer.toString();
	}
	
	/**
	 * operate textArea CSS
	 * to change the background of textArea
	 * @param color
	 */
	public static void operateTextAreaCSSwithBackground(String color){
		File file = new File("src/textReader/view/textArea.css");
		List<String> lines;
		try {
			lines = org.apache.commons.io.FileUtils.readLines(file);
			lines.set(2, "	-fx-control-inner-background: "+color+ ";");
			org.apache.commons.io.FileUtils.writeLines(file, lines);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}
	
	/**
	 * operate textArea CSS
	 * to change the font color of textArea
	 * @param color
	 */
	public static void operateTextAreaCSSwithFontColor(String color){
		File file = new File("src/textReader/view/textArea.css");
		List<String> lines;
		try {
			lines = org.apache.commons.io.FileUtils.readLines(file);
			lines.set(1, "	-fx-text-fill: "+color+ ";");
			org.apache.commons.io.FileUtils.writeLines(file, lines);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}
	
	
}
