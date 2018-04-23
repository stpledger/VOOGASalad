package GamePlayer;

import buttons.FileUploadButton;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.stage.Stage;
/**
 * Splash Screen for Selecting Games and Uploading Games
 * @author Ryan
 */

public class GamePlayerSplashScreen extends Application{
	private Stage splashStage;
	private GridPane myGridPane;
	private final int WIDTH_SIZE = 800;
	private final int HEIGHT_SIZE = 500;
	private FileUploadButton fileBtn;
//	
//	public GamePlayerSplashScreen(Stage stage) {
//		splashStage = stage;
//		splashStage.setResizable(false);
//		myGridPane = new GridPane();
//	}

	public static void main(String[] args) {
		launch(args);
	}
	
	@Override
	public void start(Stage primaryStage) throws Exception{
		primaryStage.setScene(initializeSplashScreen());
		primaryStage.show();
	}
	/**
	 * Initializes the Splash Screen with Components to select File
	 * @return
	 */
	
	public Scene initializeSplashScreen() {
		myGridPane = new GridPane();
		myGridPane = setupGridSpacing(myGridPane);
		myGridPane.setGridLinesVisible(true);
		fileBtn = new FileUploadButton();
		fileBtn.setMaxSize(WIDTH_SIZE/3, HEIGHT_SIZE/2);
		myGridPane.add(fileBtn, 2, 1);
		Scene currentStage= new Scene(myGridPane,WIDTH_SIZE,HEIGHT_SIZE);		
		return currentStage;
	}
	
	private GridPane setupGridSpacing(GridPane gridPane) {
		for (int i = 0; i<3; i++) {
			gridPane.getColumnConstraints().add(new ColumnConstraints(WIDTH_SIZE/3));
		}
		for (int i = 0; i<2; i++) {
			gridPane.getRowConstraints().add(new RowConstraints(HEIGHT_SIZE/2));
		}
		
		return gridPane;
	}
	

	
	
}
