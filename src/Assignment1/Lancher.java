package Assignment1;

import java.net.URL;

import javafx.application.Application;
//import javafx.event.ActionEvent;
//import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
//import javafx.scene.control.Menu;
//import javafx.scene.control.MenuBar;
//import javafx.scene.control.MenuItem;
import javafx.scene.layout.BorderPane;
//import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Lancher extends Application {



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
