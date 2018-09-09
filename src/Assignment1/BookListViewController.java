package Assignment1;

import java.net.URL;
import java.util.ResourceBundle;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;

public class BookListViewController implements Initializable{
	
	private static Logger logger = LogManager.getLogger();
	
	@FXML
	 	private Label pageIdentifier;
		private ListView<String> BookListView;
		private ObservableList<String> listData;

	@FXML
    void buttonExitAction(ActionEvent event) {
    	ExitScreen.getInstance().switchView(1);
    }
 
	void changeScreen(ActionEvent event) {
		//changeScreen.getInstance().switchView(1);
		System.out.println("BookListView: changeScreen");
		//logger.error("The change screen instance is being called");
	}

	@FXML
	void bookViewClicked(MouseEvent event) {
		String book = BookListView.getSelectionModel().getSelectedItem();
		if (book != null) {
			System.out.println(book);
			logger.info(book.toString() + " was clicked");
		}
		if( event.getClickCount() == 2) {
			System.out.println("The book Detail screen 1 was selected");
			changeScreen.getInstance().switchView(2);
			logger.info("double clicked " + book.toString());
		}
	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		//create data
		listData = FXCollections.observableArrayList();
		listData.add("Fake Book");
		listData.add("Fake Book 2: Return of the fake book");

		// bind the GUI list view to the list data
		BookListView.setItems(listData);
	}
}
