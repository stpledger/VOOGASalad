package gameplayer.controller;

import java.util.Map;

import authoring.gamestate.Level;
import data.DataGameState;
import data.DataUtils;
import engine.components.Component;
import gameplayer.hud.SampleToolBar;
import gameplayer.menu.MenuGameBar;
import gameplayer.menu.PauseMenu;
import gameplayer.view.GameView;
import gameplayer.view.HighScoreView;
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
	private DataGameState initialGameState;

	public Controller(Stage stage, DataGameState currentGame) {
		initialGameState = new DataGameState(currentGame.getGameState(), currentGame.getGameName());

		myStage = stage;
		pauseMenu = new PauseMenu(myStage, this);
		gameState = currentGame;
		currentGameName = gameState.getGameName();
		DataUtils.setGame(currentGameName);
		myStage.setResizable(false);
		this.gameManager = new GameManager(gameState);
		myPane = new BorderPane();
		myScene = new Scene(myPane,WIDTH_SIZE,HEIGHT_SIZE);
		
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
	 * Restarts the current game
	 */
	public void restartGame() {
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
			gameManager.setActiveLevel(level);
			sampleBar.setActiveLevel(level);
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
	 * Returns the Current Data GameState from the Controller
	 */
	public DataGameState getInitialGameState() {
		System.out.println(initialGameState ==gameState);
		return initialGameState;
	}
	
	/**
	 * Returns the gameManager to access information about the game.
	 * @return
	 */
	public GameManager getGameManager() {
		return gameManager;
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
		
		sampleBar = new SampleToolBar(gameManager, hudPropMap);
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
			sampleBar.updateGameStatusLabels(gameManager);
		}
	}


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
		myScene.setOnKeyPressed(e->{
			if(e.getCode() == KeyCode.H) {
				gameOver();
			}
		});
	}

	/**
	 * Shows the high score screen
	 */
	private void gameOver(){
		HighScoreView highScoreScreen = new HighScoreView();
		highScoreScreen.setScore(100.0);
		highScoreScreen.setGameName(currentGameName);
		myStage.setScene(highScoreScreen.getScene());
	}



}
