package menu;

import java.io.IOException;
import java.net.URL;

import java.sql.SQLException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import book.BookListViewController;
import book.bookDetailController;
import book.Book;
import database.BookTableGateway;
import database.DataGateway;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.layout.BorderPane;

public class changeScreen {
	
	private static Logger logger = LogManager.getLogger();
	private static changeScreen instance = null;
	
	private BorderPane menuBorderPane;
	private changeScreen() {
		menuBorderPane = null;
	}
	
	public void switchView(int viewType , Object arg) throws SQLException {
		if(viewType == 1) {
			try {
				URL scene = getClass().getResource("/book/BookPage.fxml/");
				FXMLLoader loader = new FXMLLoader(scene);
				
				BookTableGateway gateway = new BookTableGateway(DataGateway.getInstance().getConnection());
				//List<Book> books = gateway.fetchBooks();
				
				loader.setController(new BookListViewController(gateway));
				Node bookPane = loader.load();
				menuBorderPane.setCenter(bookPane);
			} catch(IOException e) {
				logger.error("An error has occured in the Book Page screen.");
				System.out.println(e);
			}
		} else if(viewType == 2) {
			try {
				URL detailScene = getClass().getResource("/book/FakeBookDetail.fxml/");
				FXMLLoader loader = new FXMLLoader(detailScene);
				System.out.println("Test1 with argument: " + arg);
				loader.setController(new bookDetailController((Book) arg));
				System.out.println("TEst 2");
				Node bookPane = loader.load();
				System.out.println("Test 3");
				menuBorderPane.setCenter(bookPane);

			} catch(IOException e){
				logger.error("An error has occured when chaging to the book Detail screen.");
				System.out.println(e);
			}
		}
	}
	
	public void setMenuBorderPane(BorderPane menuBorderPane) {
		this.menuBorderPane = menuBorderPane;
	}
	

	public static changeScreen getInstance() {
		if(instance == null) {
			instance = new changeScreen();
		}
		return instance;
	}
}
