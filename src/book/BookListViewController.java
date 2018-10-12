package book;

import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import database.BookTableGateway;
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
	
	private static Logger logger = LogManager.getLogger();
	private BookTableGateway gateway;
	
	 @FXML
	 private ListView<Book> BookListView;
	 //private List<String> listData;
	 private List<Book> listData;
	 @FXML
	 	private Label pageIdentifier;
	 
	 public BookListViewController(BookTableGateway gateway) throws SQLException {
		 this.gateway = gateway;
		 listData = this.gateway.fetchBooks();
	 }
	 
	 public BookListViewController(List<Book> books) {
		// TODO Auto-generated constructor stub
		this.listData = books;
		
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
		ObservableList<Book> tempBooks = FXCollections.observableArrayList(listData);
		logger.info("The value of tempbooks is: " + tempBooks);
		
		BookListView.setItems(tempBooks);
		
	}

}
