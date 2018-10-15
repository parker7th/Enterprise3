package book;

import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import database.BookTableGateway;
import database.DataGateway;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

public class bookDetailController implements Initializable{
	
	private static Logger logger = LogManager.getLogger();
	
	@FXML private Button bookSaveButton;
	
	@FXML private Button bookAuditBook;

    @FXML private TextField bookTitle;

    @FXML private TextField bookSummary;

    @FXML private DatePicker bookDateAdded;

    @FXML private TextField bookISBN;

    @FXML private ChoiceBox<String> bookYP;
    
    @FXML private ChoiceBox<String> publisherChoiceBox;
    
    private Book books;
    private BookTableGateway gateway;

	private Publisher publisher; 
    
    //The constructor for bookDetailController
    public bookDetailController() {
    	
    }
    
    public bookDetailController(Book books) {
		// TODO Auto-generated constructor stub
    	this();
    	
    	this.books = books;
    	this.gateway = new BookTableGateway(DataGateway.getInstance().getConnection());
	}

	@FXML 
    void onSaveClick(ActionEvent event) throws SQLException{
		logger.info("Save clicked");
		
		books.setGateway(gateway);
		books.save();
	}
	
	 @FXML
	 void onAuditClick(ActionEvent event){
		 logger.info("The audit button was pressed");
	 }

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		/*bindBidirectional ties GUI(ie the ids from the FXML file) property values to class property values
		 * Where it is then wrapped in a string property and then referenced by the GUI,
		 * likewise since it's a reference updating the GUI would biDirectionally update the object
		 */
		bookTitle.textProperty().bindBidirectional(books.getTitleProperty());
		for(int i = 1900; i < 2019; i++){
			bookYP.getItems().addAll(Integer.toString(i));
		}

		bookYP.valueProperty().bindBidirectional(books.getYearPublishedProperty());
		//Access the global instance bookListViewController for the list of publisher info.
		  //publistList is the DB data from publisher table.
		List<Publisher> publisherList = BookListViewController.getInstance().publisherList;
		for(int i = 0; i< publisherList.size(); i++){
			//get the publisher name from DB and store it in publisherChoiceBox.
			publisherChoiceBox.getItems().addAll(publisherList.get(i).getPublisherName());
			//if book id matches that publisherList DB id, display the publisher
			//assigned to that book.
			if(books.getpublisher_id() == publisherList.get(i).getPublisherId()){
				publisher = publisherList.get(i);
			}
		}
		//display the publisher assigned to the book.
		publisherChoiceBox.valueProperty().bindBidirectional(publisher.getPublisherNameProberty());
		
		bookSummary.textProperty().bindBidirectional(books.getSummaryProperty());
		bookDateAdded.valueProperty().bindBidirectional(books.getDateAddedProperty());
		bookISBN.textProperty().bindBidirectional(books.getIsbnProperty());
	}

}
