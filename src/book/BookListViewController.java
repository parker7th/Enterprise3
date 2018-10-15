package book;

import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import database.BookTableGateway;
import database.publisherTableGateway;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import menu.ExitScreen;
import menu.changeScreen;

public class BookListViewController implements Initializable{
	
	//implement a SingleTon pattern.
	private static BookListViewController instance = null;
	public static BookListViewController getInstance(){
		return instance;
	}
	
	private static Logger logger = LogManager.getLogger();
	private BookTableGateway gateway;
	private publisherTableGateway publisherTableGateway;
	
	 @FXML
	 private ListView<Book> BookListView;
	 //private List<String> bookListData;
	 private List<Book> bookListData;
	 @FXML
	 	private Label pageIdentifier;
	 
	 @FXML
	 public List<Publisher> publisherList;
	 
	 public BookListViewController(BookTableGateway gateway, publisherTableGateway publisherGateway) throws SQLException {
		 this.gateway = gateway;
		 this.publisherTableGateway = publisherGateway;
		 bookListData = this.gateway.fetchBooks();
		 publisherList = this.publisherTableGateway.fetchPublishers();
		 instance = this; //instance of the singleton. 
		 
	 }
	 
	 public BookListViewController(List<Book> books) {
		// TODO Auto-generated constructor stub
		this.bookListData = books;
		instance = this;
		
	}

	@FXML
	void ExitScreen(ActionEvent event) {
		ExitScreen.getInstance().switchView(1);
	}
	 
	 @FXML
		void listViewClicked(MouseEvent event) throws SQLException {
			Book book = BookListView.getSelectionModel().getSelectedItem();
			if (book != null) {
				logger.info(book.toString() + " was clicked");
			}
			if( event.getClickCount() == 2) {
				changeScreen.getInstance().switchView(2, book);
				logger.info("double clicked ");
			}
		}
	 @FXML
	 void deleteBookClicked(ActionEvent event) throws SQLException {
		 logger.info("The delete button was pressed");
		 logger.info("The gateway is: " + gateway);
		 gateway.deleteBook(BookListView.getSelectionModel().getSelectedItem());
		}
	

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		//create data
		ObservableList<Book> tempBooks = FXCollections.observableArrayList(bookListData);
		logger.info("The value of tempbooks is: " + tempBooks);
		
		BookListView.setItems(tempBooks);
		
	}

}
