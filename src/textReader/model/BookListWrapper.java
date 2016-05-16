package textReader.model;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "books")
public class BookListWrapper {

    private List<BookItem> books ;

    @XmlElement(name = "book")
    public List<BookItem> getBooks() {
        return this.books;
    }

    public void setBooks(List<BookItem> books) {
        this.books = books;
    }
}
