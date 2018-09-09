package Assignment1;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.layout.BorderPane;

public class changeScreen implements Initializable{
	private static changeScreen instance = null;
	
	private BorderPane menuBorderPane = null;

	public changeScreen()
	{
		menuBorderPane = null;
	}
	
	public void switchView(int viewType) {
		if(viewType == 1) {
			try {
				Parent newScreen;
				System.out.println("Test for BookPage");
				URL scene = this.getClass().getResource("BookPage.fxml/");
				System.out.println("Test 2 for BookPage");
				FXMLLoader loader = new FXMLLoader(scene);
				System.out.println("Test 3 for BookPage");
				loader.setController(new BookListViewController());
				System.out.println("Test 4 for BookPage");
				newScreen = FXMLLoader.load(scene);
				System.out.println("Test 5 for BookPage");
				menuBorderPane.setCenter(newScreen);
				System.out.println("Test 6 for BookPage");
			} catch(IOException e) {
				System.out.println("Test 7 for BookPage");
				System.out.println("An error has occured in the changeScreen.java");
				System.out.println(e.getMessage());
			}
		}
		if(viewType == 2) {
			try {
				URL scene = this.getClass().getResource("FakeBookDetail1.fxml");
				FXMLLoader loader = new FXMLLoader(scene);
				loader.setController(new BookListViewController());
				Node bookPane = loader.load();
				menuBorderPane.setCenter(bookPane);
			} catch(IOException e) {
				System.out.println("An error has occured in the changeScreen.java");
				System.out.println(e.getMessage());
			}
		}
	}
	
	public void setMenuBorderPane(BorderPane menuBorderPane) {
		this.menuBorderPane = menuBorderPane;
	}

	//this function returns the instance of this class
	public static changeScreen getInstance() {
		if(instance == null) {
			instance = new changeScreen();
		}
		return instance;
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
	}
}
