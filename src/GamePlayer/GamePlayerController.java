package GamePlayer;

import java.io.File;
import java.io.FileNotFoundException;

import java.util.Map;

import HUD.SampleToolBar;
import Menu.MenuGameBar;
import Menu.PauseMenu;
import buttons.FileUploadButton;
import buttons.SwitchGameButton;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.value.ObservableValue;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.util.Duration;

public class GamePlayerController {
	private final int WIDTH_SIZE = 800;
	private final int HEIGHT_SIZE = 500;

	public final int FRAMES_PER_SECOND = 60;
	public final int MILLISECOND_DELAY = 1000 / FRAMES_PER_SECOND;
	public final double SECOND_DELAY = 1.0 / FRAMES_PER_SECOND;
	private double renderTime;
	private Stage myStage;
	private Scene myScene;
	private Pane gameRoot;
	private Timeline myTimeline;
	private GamePlayerSplashScreen gamePlayerSplash;
	private Scene mySplashScene;

	private BorderPane myPane = new BorderPane();
	private PauseMenu pauseMenu = new PauseMenu(this);
	private GamePlayerEntityView gameView;

	private File currentFile;
	private FileUploadButton fileBtn;
	private SwitchGameButton switchBtn;
	private Map<Integer, Pane> levelEntityGroupMap; //map that is used to store the initial group for each level.

	private Timeline animation;

	public GamePlayerController(Stage stage) {
		myStage = stage;
		myStage.setResizable(false);
	}


	public Scene intializeStartScene() {
		gamePlayerSplash = new GamePlayerSplashScreen(myStage);
		mySplashScene = gamePlayerSplash.getSplashScene();
		connectButtonsToController();
		myScene = new Scene(myPane,WIDTH_SIZE,HEIGHT_SIZE);
		myScene.setOnKeyPressed(e -> {
			if(e.getCode() == KeyCode.ESCAPE) {
				pauseMenu.show(myStage);
				//myTimeline.pause();
			} else {
				if(gameView != null) {
					gameView.setInput(e.getCode());
				}
			}
		});

		myScene.setOnKeyReleased(e -> {
			if(e.getCode() != KeyCode.ESCAPE) {
				if(gameView != null) {
					gameView.removeInput(e.getCode());
				}
			}
		});
		//return myScene;
		return mySplashScene;
	}


	/**
	 * Helper Method to establish button listener connection to the controller
	 */
	private void connectButtonsToController() {
		fileBtn = gamePlayerSplash.fileBtn;  //public variable need to encapsulate later
		fileBtn.getFileBooleanProperty().addListener((ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) -> {
			try{
				myStage.setScene(myScene);
				initializeGameStart();
			}
			catch(FileNotFoundException e){
				System.out.println("File Does Not Exist");
			}
		});

		switchBtn = pauseMenu.switchBtn;
		switchBtn.getSwitchBooleanProperty().addListener((ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) -> {
			pauseMenu.hide();
			myStage.setScene(mySplashScene);
			//switchBtn.setSwitchBoolean(); //changes boolean value back to false
		});
	}

	/**
	 * Method that begins displaying the game
	 * @throws FileNotFoundException 
	 */
	public void initializeGameStart() throws FileNotFoundException {
		currentFile = fileBtn.getFile();
		gameView = new GamePlayerEntityView(currentFile);
		levelEntityGroupMap = gameView.getlevelEntityMap();
		gameRoot = levelEntityGroupMap.get(1);  //level 1
		myPane.setCenter(gameRoot); //adds starting game Root to the file and placing it in the Center Pane
		MenuGameBar menuBar = new MenuGameBar(this);
		myPane.setBottom(menuBar);
		SampleToolBar sampleBar = new SampleToolBar(this);
		myPane.setTop(sampleBar);
		initializeGameAnimation(); //begins the animation cycle
	}


	/**
	 * Begins the animation cycle count of the animation after game has started
	 */
	public void initializeGameAnimation() {
		KeyFrame frame = new KeyFrame(Duration.millis(MILLISECOND_DELAY),
				e -> step(SECOND_DELAY));
		animation = new Timeline();
		animation.setCycleCount(Timeline.INDEFINITE);
		animation.getKeyFrames().add(frame);
		animation.play();
		myTimeline = animation;
	}
	/**
	 * Changes the display of the gave.
	 * @param level to be loaded
	 */
	public void changeGameLevel(int level) {
		/*int lastIndex = myPane.getChildren().size()-1;
		myPane.getChildren().remove(lastIndex);
		System.out.println(level);
		myPane.getChildren().addAll(levelEntityGroupMap.get(level));*/
		gameRoot = levelEntityGroupMap.get(level);
		gameView.setActiveLevel(level);
	}

	public Map<Integer, Pane> getGameLevelRoot(){
		return levelEntityGroupMap;
	}

	/**
	 * Step method that repeats the animation by checking entities using render and system Manager
	 * @param elapsedTime
	 */
	private void step (double elapsedTime) {
		if (!pauseMenu.isShowing()) {
			renderTime+=elapsedTime;
			gameView.execute(elapsedTime);
			if (renderTime>6) {
				gameView.render();
				System.out.println("rendering");
				renderTime = 0;
			}
			gameView.updateScroll(gameRoot);
		}
	}


	public void restartGame() {
		try{
			initializeGameStart();
			//initializeLevelFile();
		}
		catch(FileNotFoundException e){
			System.out.println("File Does Not Exist");
		}
	}



	public void saveGame(){
		gameView.saveGame();
	}
}
