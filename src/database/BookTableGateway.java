package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

//import book.BookListViewController;
import book.Book;

//import java.util.Properties;
//import java.sql.PreparedStatement;

//DateGateway specifically for Books and the DB BookList Table.
public class BookTableGateway {
	private Connection conn;
	private static Logger logger = LogManager.getLogger();
	
	public BookTableGateway(Connection conn) {
		this.conn = conn;
	}
	//insertBooks - inserts a book object and it's data
	// into the DB BookList table. 
	public void insertBook(Book book) throws SQLException {
		//prepare statement for SQL command.
		PreparedStatement st = conn.prepareStatement("insert into BookList "
				+" (title, summary, year_published, ISBN, date_added) "
				+ " values (?, ?, ?, ?, ?) ", PreparedStatement.RETURN_GENERATED_KEYS);
		st.setString(1, book.getTitle());
		st.setString(2, book.getSummary());
		st.setString(3, book.getYearPublished());
		st.setString(4, book.getIsbn());
		st.setString(5, book.getDateAdded().toString());
		st.executeUpdate();//execute the prepared statement(SQL).
		
		ResultSet rs = st.getGeneratedKeys();//store the result from SQL commands.
		rs.first();//get 'first row' of the result set (stuff fromDB)
		book.setId(rs.getInt(1));//get book id value(id) and store in book object
		
		logger.info("The new id is " + book.getId());
		rs.close();
		try {
			st.close();
			}catch(SQLException e) {
				logger.error(e);
			}
	}
	
	public void updateBook(Book book) throws SQLException{
		System.out.println("The updateBook function has been called");
		try {
			PreparedStatement st = conn.prepareStatement("update BookList "
					+ "set title = ?, summary = ?, year_published = ?, ISBN = ?, date_added = ? "
					+ "where id = ? ");
			st.setString(1, book.getTitle());
			st.setString(2, book.getSummary());
			st.setString(3, book.getYearPublished());
			st.setString(4, book.getIsbn());
			st.setString(5, book.getDateAdded().toString());
			st.setInt(6, book.getId());
			
			st.executeUpdate();
			st.close();
			
		}catch(SQLException e) {
			logger.error(e);
		}
	}
	
	public void deleteBook(Book book) throws SQLException{
		PreparedStatement st = conn.prepareStatement("delete from BookList where id = ? ");
		st.setInt(1, book.getId());
		st.executeUpdate();
		try {
		st.close();
		}catch(SQLException e) {
			logger.error(e);
		}
	}
	
	public List<Book> fetchBooks() throws SQLException {
		List<Book> books = new ArrayList<Book>();
		Statement st = conn.createStatement();
		ResultSet rs = st.executeQuery("select * from BookList order by id");
		while(rs.next()) {
			Book book = new Book(rs.getString("title"));
			book.setId(rs.getInt("id"));
			book.setTitle(rs.getString("title"));
			book.setSummary(rs.getString("summary"));
			book.setYearPublished(rs.getString("year_published"));
			book.setpublisher_id(rs.getInt("publisher_id"));
			book.setIsbn(rs.getString("ISBN"));
			book.setDateAdded(rs.getDate("date_added").toLocalDate());
			book.setGateway(this);
			
			System.out.println(book.getTitle());
			books.add(book);
		}
		try {
		rs.close();
		st.close();
		}catch(SQLException e) {
			logger.error(e);
		}
		return books;
	}
	
	
	
}
