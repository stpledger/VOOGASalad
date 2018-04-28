package GamePlayer;

import java.util.List;
import java.util.Map;
import HUD.SampleToolBar;
import Menu.MenuGameBar;
import Menu.PauseMenu;
import buttons.FileUploadButton;
import buttons.GameSelectButton;
import buttons.SwitchGameButton;
import data.DataGameState;
import engine.components.Component;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.value.ObservableValue;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.util.Duration;

public class GamePlayerController {
	private final int WIDTH_SIZE = 800;
	private final int HEIGHT_SIZE = 500;
	private final int LEVEL_ONE = 1;
	public final int FRAMES_PER_SECOND = 60;
	public final int MILLISECOND_DELAY = 1000 / FRAMES_PER_SECOND;
	public final double SECOND_DELAY = 1.0 / FRAMES_PER_SECOND;
	private double renderTime;
	private Stage myStage;
	private Scene myScene;
	private Pane gameRoot;
	private SplashScreenView gamePlayerSplash;
	private Scene mySplashScene;
	private BorderPane myPane = new BorderPane();
	private PauseMenu pauseMenu = new PauseMenu(this);
	private GamePlayerEntityView gameView;
	private FileUploadButton fileBtn;
	private SwitchGameButton switchBtn;
	private Map<Integer, Pane> levelEntityGroupMap; //map that is used to store the initial group for each level.
	private DataGameState currentGameState;
	public List<GameSelectButton> gameSelectButtonList;
	private Timeline myTimeline;
	private Map<Integer, Map<String, Component>> PlayerKeys;
	private SampleToolBar sampleBar;
	private Map<Integer, Map<String, Boolean>> HUDPropMap;

	private Timeline animation;

	public GamePlayerController(Stage stage) {
		myStage = stage;
		myStage.setResizable(false);
	}

	public Scene initializeStartScene() {
		//Testing HighScore Screen
		HighScoreView highScoreScreen = new HighScoreView(myStage);
		Scene highScore = highScoreScreen.getHighScoreScene();
		
		gamePlayerSplash = new SplashScreenView(myStage);
		mySplashScene = gamePlayerSplash.getSplashScene();
		connectButtonsToController();
		myScene = new Scene(myPane,WIDTH_SIZE,HEIGHT_SIZE);
		assignKeyInputs();
		return mySplashScene;
		//return highScore;
		
	}

	/**
	 * Helper Method to establish button listener connection to the controller
	 */
	private void connectButtonsToController() {
		gameSelectButtonList = gamePlayerSplash.getSplashScreenButtons();
		for (GameSelectButton b : gameSelectButtonList) {
			b.getGameSelectBooleanProperty().addListener((ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) -> {
				currentGameState = b.getGameState();
				setGameView(currentGameState);
				myStage.setScene(myScene);
			});
		}
		fileBtn = gamePlayerSplash.fileBtn;  //public variable need to encapsulate later
		fileBtn.getFileBooleanProperty().addListener((ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) -> {
			currentGameState = fileBtn.getGameState();
			setGameView(currentGameState);
			myStage.setScene(myScene);
		});

		switchBtn = pauseMenu.switchBtn;
		switchBtn.getSwitchBooleanProperty().addListener((ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) -> {
			pauseMenu.hide();
			myStage.setScene(mySplashScene);
			//switchBtn.setSwitchBoolean(); //changes boolean value back to false
		});
	}
	
	/**
	 * Method that sets the current scene of the game
	 */
	public void setGameView(DataGameState currentGame) {
		gameView = new GamePlayerEntityView(currentGame);
		HUDPropMap = gameView.getHudPropMap();
		PlayerKeys = gameView.getPlayerKeys();
		levelEntityGroupMap = gameView.getlevelEntityMap();
		gameRoot = levelEntityGroupMap.get(LEVEL_ONE);  //level 1
		myPane.setCenter(gameRoot); //adds starting game Root to the file and placing it in the Center Pane
		MenuGameBar menuBar = new MenuGameBar(this);
		myPane.setBottom(menuBar);
		sampleBar = new SampleToolBar(LEVEL_ONE, PlayerKeys, HUDPropMap);
		myPane.setTop(sampleBar);
		initializeGameAnimation(); //begins the animation cycle
		//set level change listener
		/*gameView.getLevelStatus().getUpdate().addListener((o, oldVal, newVal) -> {
			changeGameLevel(newVal.intValue());
		});*/
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
		gameRoot = levelEntityGroupMap.get(level);
		myPane.setCenter(gameRoot);
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
				renderTime = 0;
			}
			gameView.updateScroll(gameRoot);
			//update PlayerKey Values;
			PlayerKeys = gameView.getPlayerKeys();
			sampleBar.updateGameStatusValues(PlayerKeys);
			sampleBar.updateGameStatusLabels();
		}
	}

	
	public void restartGame() {
		setGameView(currentGameState);
	}

	
	private void assignKeyInputs() {
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
	}

	public void saveGame(){
		gameView.saveGame();
	}
}
