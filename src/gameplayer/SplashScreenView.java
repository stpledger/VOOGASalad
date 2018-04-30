package gameplayer;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import buttons.FileUploadButton;
import buttons.GameSelectButton;
import buttons.IGamePlayerButton;
import data.DataGameState;
import data.DataRead;
import data.DataWrite;
import javafx.beans.property.BooleanProperty;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.stage.Stage;

/**
 * Splash Screen for Selecting Games and Uploading Games
 * @author Ryan Fu
 */
public class SplashScreenView extends BranchScreenView{
	private static final int ROW_NUM = 2;
	private static final int COL_NUM = 3;
	private Scene splashScene;
	private GridPane myGridPane;
	private BorderPane myBorderPane;
	private ScrollPane myScrollPane;
	private Stage myStage;
	private Map<Image, DataGameState> imageGameStateMap;
	public FileUploadButton fileBtn;
	public DataGameState currentGame;

	public SplashScreenView(Stage stage) {
		myStage = stage;
		imageGameStateMap = DataRead.getAllGames();
		splashScene = initializeScreen();
	}

	/**
	 * Returns the current Scene of the SplashScreenView
	 */
	@Override
	public Scene getScene() {
		return splashScene;
	}

	/**
	 * Initializes the Splash Screen with GameSelectButtons to select File
	 * @return
	 */
	@Override
	public Scene initializeScreen() {
		myGridPane = new GridPane();
		myGridPane = setupGridSpacing(myGridPane);
		myGridPane.setGridLinesVisible(true);
		fileBtn = new FileUploadButton(myStage);

		assignGameSelectButtons();
		myScrollPane = new ScrollPane(myGridPane);
		myScrollPane.setVbarPolicy(ScrollBarPolicy.NEVER);
		myBorderPane = new BorderPane(myScrollPane);
		myBorderPane.setBottom(fileBtn);
		Scene currentScene= new Scene(myBorderPane,WIDTH_SIZE,HEIGHT_SIZE);		
		return currentScene;
	}
		/**
		 * Method to dynamically create Game Select Buttons, Use when DataRead.getAllGames is working.
		 */
	private void assignGameSelectButtons() {
		int row = 0;
		int col = 0;
		for (Image image: imageGameStateMap.keySet()) {  //attains each image and the corresponding game file
			DataGameState currentDataGameState = imageGameStateMap.get(image);
			String nameOfGame = currentDataGameState.getGameName();
			GameSelectButton currentButton = new GameSelectButton(myStage, nameOfGame, currentDataGameState, image);
			currentButton.setMaxSize(WIDTH_SIZE/COL_NUM, HEIGHT_SIZE/ROW_NUM);
			myGridPane.add(currentButton, col, row);
			if (col == (COL_NUM-1)) { //if maximum number of columns is reached
				col=0; 		//reset number of columns
				row++;		//increment the number of rows
			}else {
				col++; 		//if not, increment the number of columns
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