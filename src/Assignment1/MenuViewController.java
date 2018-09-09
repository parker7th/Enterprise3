package Assignment1;

import java.net.URL;
import java.util.ResourceBundle;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

public class MenuViewController implements Initializable {
	private static MenuViewController instance = null;
	private static Logger logger = LogManager.getLogger();
	
	@FXML
    private Label PageIdentifier;
	
	@FXML
	private void buttonExitAction(ActionEvent event) {
		Platform.exit();
	}

	@FXML
	private void buttonBookListAction(ActionEvent event) {

		System.out.println("The book Detail screen 1 was selected: MenuViewController");
		//System.out.println(event.getSource().toString());
		changeScreen.getInstance().switchView(1);
		logger.info("The change screen instance is being called");
	}
	
	
	public void initialize(URL location, ResourceBundle resources) {
		
	}
	
	//this function returns the instance of this class
	public static MenuViewController getInstance() {
		if(instance == null) {
			instance = new MenuViewController();
		}
		return instance;
	}
}
