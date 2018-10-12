package database;

import java.sql.Connection;

public class DataGateway {
	private static DataGateway instance = null;
	
	private Connection connection;
	
	private DataGateway() {
		
	}
	
	
	//Fetch list of authorized users
	
	public static DataGateway getInstance() {
		if(instance == null)
			instance = new DataGateway();
		return instance;
	}
	
	public Connection getConnection() {
		return connection;
	}
	
	public void setConnection(Connection connection){
		this.connection = connection;
	}

}
