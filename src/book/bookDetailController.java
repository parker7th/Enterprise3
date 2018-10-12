package book;

import java.net.URL;
import java.sql.SQLException;
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
    
    private Book books;
    private BookTableGateway gateway; 
    
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
		bookYP.getItems().addAll("1900", "1901", "1902", "1903", "1904", "1905" , "1906", "1907", "1908", "1909", "1910",
				"1911", "1912", "1913", "1914", "1915", "1916", "1917", "1918", "1919", "1920", "1921", "1922", "1923", "1924",
				"1925", "1926", "1927", "1928", "1929", "1930", "1931", "1932", "1933", "1934", "1935", "1936", "1937", "1938", 
				"1939", "1940", "1941", "1942", "1943", "1944", "1945", "1946", "1947", "1948", "1949", "1950", "1951", "1952",
				"1953", "1954", "1955", "1956", "1957", "1958", "1959", "1960", "1961", "1962", "1963", "1964", "1965", "1966",
				"1967", "1968", "1969", "1970", "1971", "1972", "1973", "1974", "1975", "1976", "1977", "1978", "1979", "1980",
				"1981", "1982", "1983", "1984", "1985", "1986", "1987", "1988", "1989", "1990", "1991", "1992", "1993", "1994",
				"1995", "1996", "1997", "1998", "1999", "2000", "2001", "2002", "2003", "2004", "2005", "2006", "2007", "2008",
				"2009", "2010", "2011", "2012", "2013", "2014", "2015", "2016", "2017", "2018", "2019", "2020");
		bookYP.valueProperty().bindBidirectional(books.getYearPublishedProperty());
		bookSummary.textProperty().bindBidirectional(books.getSummaryProperty());
		bookDateAdded.valueProperty().bindBidirectional(books.getDateAddedProperty());
		bookISBN.textProperty().bindBidirectional(books.getIsbnProperty());
	}

}
