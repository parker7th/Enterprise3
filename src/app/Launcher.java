package app;
//CS 4743 Assignment 3 by Jesse Chavez.
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

//main caller. The 'Launcher'.
public class Launcher extends Application {
	//instantiate Logger.
	private static Logger logger = LogManager.getLogger();
	//set up a connection variable.
	private Connection conn;
	//create the init function.
	@Override
	public void init() throws Exception{
		super.init();
		 conn = null;
		
		try {
			//read database credentials from the properties file
			Properties props = new Properties();
			FileInputStream fis = null; //file pointer.
			fis = new FileInputStream("db.properties");//the parameterized queries.
			props.load(fis);//load the queries into props.
			fis.close();//close the file when done.
			
			//create the data source. LOAD prop variables into DataSource ds.
			MysqlDataSource ds = new MysqlDataSource();
			ds.setUrl(props.getProperty("MYSQL_DB_URL"));
			ds.setUser(props.getProperty("MYSQL_DB_USERNAME"));
			ds.setPassword(props.getProperty("MYSQL_DB_PASSWORD"));
			
			//create the connection
			conn = ds.getConnection();
			//pass connection to DataGateway.
			DataGateway.getInstance().setConnection(conn);
			logger.info("Connection has been made");
		}catch (SQLException e) {
			logger.fatal("Error: cannot connect to database");
			Platform.exit();
		}
		
	}
	
	//Oveerride the stop function(implement it).
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

	//main. Calls launch function.
	public static void main(String[] args) {
		launch(args);
	}
	
	// Override(implement) the start function.
	@Override
	public void start(Stage stage) throws Exception {
		//set up the viewURL. Link fxml file to code.
		URL viewURL = this.getClass().getResource("MainPage.fxml");
		FXMLLoader loader = new FXMLLoader(viewURL); //load fxml file.
		loader.setController(new MenuViewController());//set up it's controller.
		
		BorderPane mainPane = loader.load(); //load the fxml view/controller onto mainPane.
		changeScreen.getInstance().setMenuBorderPane(mainPane);//update GUI
		ExitScreen.getInstance();//exit screen?
		
		Scene scene = new Scene(mainPane); //create new scene with mainPane.
		stage.setScene(scene);//set scene to stage.
		stage.show(); //show the stage.
	}
}
