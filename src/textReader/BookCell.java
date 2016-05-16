package textReader;

import javafx.scene.control.ListCell;
import textReader.model.BookItem;

/**
 * Customize the item in the view list
 * @author Grace
 *
 */
public class BookCell extends ListCell<BookItem> {
	@Override
	protected void updateItem(BookItem item, boolean empty) {
		super.updateItem(item, empty);
		
	        if (item != null) {
	        	setText(item.getFileNameStringWithoutEx());
	        } 
     }
}
