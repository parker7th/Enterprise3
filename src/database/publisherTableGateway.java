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

import book.Publisher;

public class publisherTableGateway {
	//will create connection to our publisher table
	// in our database.
	
	private Connection conn;
	private static Logger logger = LogManager.getLogger();
	
	public publisherTableGateway(Connection conn) {
		this.conn = conn;
	}
	
	public List<Publisher> fetchPublishers() throws SQLException {
		logger.info("Did it get inside the fetchPublisher\n");
		List<Publisher> publishers = new ArrayList<Publisher>();

		Statement st = conn.createStatement();
		ResultSet rs = st.executeQuery("select * from publisher order by publisher_name");
		while(rs.next()) {
			Publisher publisher = new Publisher(rs.getInt("publisher_id"), rs.getString("publisher_name"));
			publishers.add(publisher);
		}
		rs.close();
		st.close();
		return publishers;
	}
}
