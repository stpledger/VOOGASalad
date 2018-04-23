package GamePlayer;

import java.util.Map;

import buttons.FileUploadButton;
import data.DataGameState;
import data.DataRead;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.image.Image;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.stage.Stage;
/**
 * Splash Screen for Selecting Games and Uploading Games
 * @author Ryan
 */

public class GamePlayerSplashScreen{
	private Scene splashScene;
	private Stage mySplashStage;
	private GridPane myGridPane;
	private ScrollPane myScrollPane;
	private Map<Image, DataGameState> imageGameStateMap;
	private final int WIDTH_SIZE = 800;
	private final int HEIGHT_SIZE = 500;
	private final int ROW_NUM = 2;
	private final int COL_NUM = 3;
	public FileUploadButton fileBtn;
	
	public GamePlayerSplashScreen(Stage stage) {
		//imageGameStateMap = DataRead.getAllGames();
		mySplashStage = stage;
		splashScene = initializeSplashScreen();
	}

	public Scene getSplashScene() {
		return splashScene;
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
		assignGameSelectButtons();
		myScrollPane = new ScrollPane(myGridPane);
		myScrollPane.setHbarPolicy(ScrollBarPolicy.NEVER);
		Scene currentScene= new Scene(myScrollPane,WIDTH_SIZE,HEIGHT_SIZE);		
		return currentScene;
	}
	
	/**
	 * Adds button to each of the 5 grid panes in order for them to be selected, each button should take
	 * in a parameter for the file and the name of the game.
	 * Button Parameters: File, Name, Image
	 */
	private void assignGameSelectButtons() {
		int count = 1;
		for (int i = 0; i<ROW_NUM; i++) {
			for (int k = 0; k<COL_NUM; k++) {
				Button currentButton = new Button("Game "+ count);
				currentButton.setMaxSize(WIDTH_SIZE/3, HEIGHT_SIZE/2);
				myGridPane.add(currentButton, k, i);
				count++;
				if (count==6) {
					break;
				}
			}
		}
		
	}
	
	private GridPane setupGridSpacing(GridPane gridPane) {
		for (int i = 0; i<COL_NUM; i++) {
			gridPane.getColumnConstraints().add(new ColumnConstraints(WIDTH_SIZE/COL_NUM));
		}
		for (int i = 0; i<ROW_NUM; i++) {
			gridPane.getRowConstraints().add(new RowConstraints(HEIGHT_SIZE/ROW_NUM));
		}
		
		return gridPane;
	}
	

	
	
}
