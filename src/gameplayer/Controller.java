package GamePlayer;

import java.util.Map;
import HUD.SampleToolBar;
import Menu.MenuGameBar;
import Menu.PauseMenu;
import data.DataGameState;
import engine.components.Component;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Controller implements IController{
	private static final int WIDTH_SIZE = 800;
	private static final int HEIGHT_SIZE = 500;
	private static final int LEVEL_ONE = 1;
	public static final int FRAMES_PER_SECOND = 60;
	public static final int MILLISECOND_DELAY = 1000 / FRAMES_PER_SECOND;
	public static final double SECOND_DELAY = 1.0 / FRAMES_PER_SECOND;

	private double renderTime;
	private Stage myStage;
	private Scene myScene;
	private Pane gameRoot;
	private BorderPane myPane = new BorderPane();
	private PauseMenu pauseMenu = new PauseMenu(this);
	private GameView gameView;

	private Map<Integer, Pane> gameLevelDisplays;
	private DataGameState currentGameState;
	private Map<Integer, Map<String, Component>> playerKeys;
	private SampleToolBar sampleBar;
	private Map<Integer, Map<String, Boolean>> hudPropMap;
	private Timeline animation;
	private String currentGameName;
	private DataGameState gameState;

	public Controller(Stage stage, DataGameState currentGame) {
		gameState = currentGame;
		myStage = stage;
		myStage.setResizable(false);
	}

	/**
	 * Initializes controller scene
	 * @return
	 */
	public Scene initializeStartScene() {
		myScene = new Scene(myPane,WIDTH_SIZE,HEIGHT_SIZE);
		assignKeyInputs();
		setGameView();
		return myScene;
	}

	/**
	 * Changes the display of the gave.
	 * @param level to be loaded
	 */
	public void changeGameLevel(int level) {
		if(level > gameView.getNumOfLevels()){
			gameOver();
		}
		else {
			gameRoot = gameLevelDisplays.get(level);
			myPane.setCenter(gameRoot);
			gameView.setActiveLevel(level);
		}
	}

	/**
	 * Returns the level game display
	 * @return
	 */
	public Map<Integer, Pane> getGameLevelRoot(){
		return gameLevelDisplays;
	}
	
	/**
	 * Restarts the current level
	 */
	public void restartGame() {
		setGameView();
	}

	/**
	 * Saves game to a file
	 */
	public void saveGame(){
		gameView.saveGame();
	}

	/**
	 * Method that sets the current scene of the game
	 */
	private void setGameView() {
		gameView = new GameView(gameState);
		hudPropMap = gameView.getHudPropMap();
		playerKeys = gameView.getPlayerKeys();
		gameLevelDisplays = gameView.getGameLevelDisplays();

		gameRoot = gameLevelDisplays.get(LEVEL_ONE);
		myPane.setCenter(gameRoot);

		MenuGameBar menuBar = new MenuGameBar(this);
		myPane.setBottom(menuBar);

		sampleBar = new SampleToolBar(LEVEL_ONE, playerKeys, hudPropMap);
		myPane.setTop(sampleBar);

		//TODO: IS THIS STUFF NECESSARY?
		/*for(Win w : gameView.getWinComponents()){
			w.getWinStatus().addListener((o, oldVal, newVal) -> {
				changeGameLevel(gameView.getActiveLevel() + 1);
			});
		}*/
		initializeGameAnimation();
		//set level change listener
		/*gameView.getLevelStatus().getUpdate().addListener((o, oldVal, newVal) -> {
			changeGameLevel(newVal.intValue());
		});*/
	}
	
	/**
	 * Begins the animation cycle count of the animation after game has started
	 */
	private void initializeGameAnimation() {
		KeyFrame frame = new KeyFrame(Duration.millis(MILLISECOND_DELAY),
				e -> step(SECOND_DELAY));
		animation = new Timeline();
		animation.setCycleCount(Timeline.INDEFINITE);
		animation.getKeyFrames().add(frame);
		animation.play();
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
			playerKeys = gameView.getPlayerKeys();
			sampleBar.updateGameStatusValues(playerKeys);
			sampleBar.updateGameStatusLabels();
		}
	}

	//	public void setHighScoreView() {
	//		HighScoreView highScoreScreen = new HighScoreView();
	//		Scene highScore = highScoreScreen.getScene();
	//		myStage.setScene(highScore);
	//	}

	/**
	 * Passes keys to engine and assigns escape key to pause menu
	 */
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

	/**
	 * Shows the high score screen
	 */
	private void gameOver(){
		//TODO add game over functionality like the high score screen
	}

}
