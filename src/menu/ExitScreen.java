package menu;


import javafx.application.Platform;

public class ExitScreen {
	private static ExitScreen instance = null;
		
		
		public void switchView(int viewType) {
			if(viewType == 1) {
				Platform.exit();
			}
		}
		public static ExitScreen getInstance() {
			if(instance == null) {
				instance = new ExitScreen();
			}
			return instance;
		}
}
