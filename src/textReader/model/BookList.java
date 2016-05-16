package textReader.model;

import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class BookList implements BookListFactory{
	
	ObservableList<BookItem> books;
	
	public BookList(){
		 this.books = FXCollections.observableArrayList();	 
	}
	
	@Override
	public void add(BookItem newBookComponent){
		this.books.add(newBookComponent);
	}
	
	@Override
	public void remove(int componentIndex){
		this.books.remove(componentIndex);
	}
	
	@Override 
	public BookItem getComponent(int componentIndex) {
		return this.books.get(componentIndex);
	}
	
	@Override
	public ObservableList<BookItem> getBookList(){
		return this.books;
	}

	public void addAll(List<BookItem> list) {
		// TODO Auto-generated method stub
		this.books = (ObservableList<BookItem>) list;
	}
	
}
