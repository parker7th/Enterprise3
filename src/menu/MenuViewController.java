package menu;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import book.Book;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

public class MenuViewController implements Initializable {
	
	@FXML
    private Label PageIdentifier;

    @FXML
    void ExitScreen(ActionEvent event) {
    	ExitScreen.getInstance().switchView(1);
    }
    
	@FXML
	void changeScreen(ActionEvent event) throws SQLException {
		changeScreen.getInstance().switchView(1, null);
		
	}
	@FXML
	void changeScreenCreate(ActionEvent event) throws SQLException{
		Book newBook = new Book();
		changeScreen.getInstance().switchView(2, newBook);
	}
	
	public void initialize(URL location, ResourceBundle resources) {
		
	}
}
