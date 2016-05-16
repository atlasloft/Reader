package textReader.model;

import javafx.collections.ObservableList;

/**
 * maintain a book list
 * @author Grace
 *
 */
public interface BookListFactory {

	/**
	 * add a new book into book list
	 * @param newBookComponent
	 */
	default public void add(BookItem newBookComponent,ObservableList<BookItem> books) {	
		
		throw new UnsupportedOperationException();
		
	}
	
	// remove a book in book list
	default	public void remove(int componentIndex,ObservableList<BookItem> books) {
			
			throw new UnsupportedOperationException();
			
	}
	
	// get components
	default public BookItem getComponent(int componentIndex,ObservableList<BookItem> books) {
			
		throw new UnsupportedOperationException();
			
	}

	int totalNumberOfBooks(ObservableList<BookItem> books);

	
	
	
}
