package gameplayer.controller;

import java.util.Map;

import data.DataGameState;
import gameplayer.hud.SampleToolBar;
import gameplayer.menu.MenuGameBar;
import gameplayer.menu.PauseMenu;
import gameplayer.view.GameView;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Controller implements IController {
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
	private BorderPane myPane;
	private PauseMenu pauseMenu;
	private GameView gameView;

	private Map<Integer, Pane> gameLevelDisplays;
	private DataGameState currentGameState;
	private SampleToolBar sampleBar;
	private Map<Integer, Map<String, Boolean>> hudPropMap;
	private Timeline animation;
	private String currentGameName;
	private DataGameState gameState;
	private GameManager gameManager;

	public Controller(Stage stage, DataGameState currentGame) {
		gameState = currentGame;
		myStage = stage;
		myStage.setResizable(false);
		this.gameManager = new GameManager(gameState);
		myPane = new BorderPane();
		myScene = new Scene(myPane,WIDTH_SIZE,HEIGHT_SIZE);
		pauseMenu = new PauseMenu(myStage);
		assignKeyInputs();
		setGameView();
	}
	
	/**
	 * Initializes controller scene
	 * @return
	 */
	public Scene getControllerScene() {
		return myScene;
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
	 * Changes the display of the gave.
	 * @param level to be loaded
	 */
	public void changeGameLevel(int level) {
		if(level > gameManager.getNumOfLevels()){
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
	 * Method that sets the current scene of the game
	 */
	private void setGameView() {
		gameView = new GameView(gameState, gameManager);
		hudPropMap = gameView.getHudPropMap();
		gameLevelDisplays = gameView.getGameLevelDisplays();
		
		gameRoot = gameLevelDisplays.get(LEVEL_ONE);
		myPane.setCenter(gameRoot);
		
		MenuGameBar menuBar = new MenuGameBar(this);
		myPane.setBottom(menuBar);
		
		sampleBar = new SampleToolBar(LEVEL_ONE, gameManager.getPlayerKeys(), hudPropMap);
		myPane.setTop(sampleBar);
		
		initializeGameAnimation();
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
			sampleBar.updateGameStatusValues(gameManager.getPlayerKeys());
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
