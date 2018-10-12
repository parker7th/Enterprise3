package book;
import java.sql.SQLException;
import java.time.LocalDate;

import database.BookTableGateway;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;

public class Book {
	//private SimpleStringProperty firstNm;
	private int id;
	private SimpleIntegerProperty publisher_id;
	private SimpleStringProperty title, summary, isbn, yearPublished;
	private SimpleObjectProperty <LocalDate> dateAdded;
	
	private BookTableGateway gateway;
	
	//public Book(BookTableGateway gateway) {
	public Book() {
		//this.gateway = gateway;
		this.id = 0;
		this.title = new SimpleStringProperty();
		this.summary = new SimpleStringProperty();
		this.yearPublished = new SimpleStringProperty();
		this.dateAdded = new SimpleObjectProperty <LocalDate>();
		this.publisher_id = new SimpleIntegerProperty();
		this.isbn = new SimpleStringProperty();
	}
	

	public Book(String string) {
		this();
		setTitle(string);
	}


	public void save() throws SQLException {
		if(getId() == 0) {
			gateway.insertBook(this);
		}else {
		gateway.updateBook(this);
		}
	}
	
	@Override
	public String toString() {
		return title.get();
	}
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title.get();
	}
	public SimpleStringProperty getTitleProperty() {
		return title;
	}

	public void setTitle(String title) {
		this.title.setValue(title);
	}

	public String getSummary() {
		return summary.get();
	}
	public SimpleStringProperty getSummaryProperty() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary.setValue(summary);
	}

	public String getIsbn() {
		return isbn.get();
	}
	public SimpleStringProperty getIsbnProperty() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn.setValue(isbn);
	}

	public String getYearPublished() {
		return yearPublished.get();
	}
	public SimpleStringProperty getYearPublishedProperty() {
		return yearPublished;
	}

	public void setYearPublished(String yearPublished) {
		this.yearPublished.set(yearPublished);
	}

	public LocalDate getDateAdded() {
		return dateAdded.get();
	}
	public SimpleObjectProperty <LocalDate> getDateAddedProperty() {
		return dateAdded;
	}

	public void setDateAdded(LocalDate localDate) {
		this.dateAdded.setValue(localDate);
	}
	
	public void setpublisher_id(int publisher) {
		this.publisher_id.setValue(publisher);
	}
	
	public int getpublisher_id() {
		return publisher_id.get();
	}
	
	public SimpleIntegerProperty getpublisher_idProperty() {
		return publisher_id;
	}


	public void setGateway(BookTableGateway bookTableGateway) {
		this.gateway = bookTableGateway;
		
	}
	
	
}
