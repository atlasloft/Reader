package textReader.model;

import java.io.File;
import org.apache.commons.io.FilenameUtils;

/**
 * one book in the bookList
 * @author Grace
 *
 */
public class BookItem {
	private File file;
	private int bookIndex;
	
	public void setBookIndex(int bookIndex){
		this.bookIndex = bookIndex;
	}
	
	public int getBookIndex(){
		return this.bookIndex;
	}
	
	// get the book
	public File getFile(){
		return this.file;
	}
	
	public void setFile(File file){
		this.file = file;
	}
	
	// return file path
    public String getFilePath() {
        return this.file.getAbsolutePath();
    }
    
    // return file name with extension
    public String getFileName(){
    	return this.file.getName();
    }

    // return file name without extension
    public String getFileNameStringWithoutEx(){
    	return FilenameUtils.getBaseName(this.file.getName());
    }


}
