package textReader.model;

import java.util.List;
import javafx.collections.ObservableList;

public class BookList implements BookListFactory{
	
	//ObservableList<BookItem> books;
	
//	public BookList(){
//		 this.books = FXCollections.observableArrayList();	 
//	}
	
	@Override
	public int totalNumberOfBooks(ObservableList<BookItem> books){
		return books.size();
	}
	
	@Override
	public void add(BookItem newBookComponent,ObservableList<BookItem> books){
		books.add(newBookComponent);
	}
	
	@Override
	public void remove(int componentIndex,ObservableList<BookItem> books){
		for (BookItem bookItem : books) {
			if(bookItem.getBookIndex() == componentIndex){
				books.remove(bookItem);
				break;
			}
		}
	}
	
	@Override 
	public BookItem getComponent(int componentIndex,ObservableList<BookItem> books) {
		return books.get(componentIndex);
	}

	public void addAll(List<BookItem> list,ObservableList<BookItem> books) {
		// TODO Auto-generated method stub
		books.addAll(list);
	}
	
}
