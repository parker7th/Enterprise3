package book;
//Publisher class 
// will have to store database info later.
public class Publisher {
	private int id;
	private String publisherName;
	public Publisher(int id, String publisherName) {
		this.id = id;
		this.publisherName = publisherName;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getPublisherName() {
		return this.publisherName;
	}
	public void setPublisherName(String PublisherName) {
		this.publisherName = PublisherName;
	}
	
	@Override
	public String toString(){
		return this.getPublisherName();
	}
	
}
