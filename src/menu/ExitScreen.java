package menu;


import javafx.application.Platform;

//The ExitScreen class. Singleton.
public class ExitScreen {
	private static ExitScreen instance = null;
		
		//switcherView function.
		//takes in viewType code
		public void switchView(int viewType) {
			if(viewType == 1) {
				Platform.exit();
			}
		}
		//ExitScreen getInstance. 
		public static ExitScreen getInstance() {
			if(instance == null) {
				instance = new ExitScreen();
			}
			return instance;
		}
}
