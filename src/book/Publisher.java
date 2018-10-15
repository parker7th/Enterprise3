package book;

import javafx.beans.property.Property;
import javafx.beans.property.SimpleStringProperty;

//Publisher class 
// will have to store database info later.
public class Publisher {
	private int id;
	private SimpleStringProperty publisherName;
	public Publisher(int id, String publisherName) {
		this.id = id;
		this.publisherName = new SimpleStringProperty();
		this.publisherName.setValue(publisherName);
	}
	//getters and setters
	public int getPublisherId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getPublisherName() {
		return this.publisherName.get();
	}
	public void setPublisherName(String PublisherName) {
		this.publisherName.set(PublisherName);
	}
	
	@Override
	public String toString(){
		return this.getPublisherName();
	}
	public SimpleStringProperty getPublisherNameProberty() {
		return publisherName;
	}
	
}
