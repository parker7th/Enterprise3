package app;
//CS 4743 Assignment 2 by Jesse Chavez.
//Enterprise project.

import java.io.FileInputStream;
import java.net.URL;
import java.sql.SQLException;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;

import database.DataGateway;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;

import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import menu.ExitScreen;
import menu.MenuViewController;
import menu.changeScreen;

public class Launcher extends Application {

	private static Logger logger = LogManager.getLogger();
	private Connection conn;
	@Override
	public void init() throws Exception{
		super.init();
		 conn = null;
		//create a connection
		try {
			//read database credentials from the properties file
			Properties props = new Properties();
			FileInputStream fis = null;
			fis = new FileInputStream("db.properties");
			props.load(fis);
			fis.close();
			
			//create the datasource
			MysqlDataSource ds = new MysqlDataSource();
			ds.setUrl(props.getProperty("MYSQL_DB_URL"));
			ds.setUser(props.getProperty("MYSQL_DB_USERNAME"));
			ds.setPassword(props.getProperty("MYSQL_DB_PASSWORD"));
			
			//create the connection
			conn = ds.getConnection();
			DataGateway.getInstance().setConnection(conn);
			logger.info("Connection has been made");
			
		}catch (SQLException e) {
			logger.fatal("Error: cannot connect to database");
			Platform.exit();
		}
		
	}
	
	@Override
	public void stop() throws Exception{
		super.stop();
		
		//close the connection
		try {
			conn.close();
			logger.info("Connection has been closed");
		}catch(SQLException e){
			logger.fatal("Error: cannot close connection to database");
			
		}
	}

	public static void main(String[] args) {
		launch(args);

	}

	@Override
	public void start(Stage stage) throws Exception {
		URL viewURL = this.getClass().getResource("MainPage.fxml");
		FXMLLoader loader = new FXMLLoader(viewURL);
		loader.setController(new MenuViewController());
		
		BorderPane mainPane = loader.load();
		changeScreen.getInstance().setMenuBorderPane(mainPane);
		ExitScreen.getInstance();
		
		Scene scene = new Scene(mainPane);
		stage.setScene(scene);
		stage.show();
	}
}
