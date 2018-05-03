package gameplayer.controller;

import java.util.Map;

import data.DataGameState;
import gameplayer.hud.SampleToolBar;
import gameplayer.levelunlock.SelectLevel;
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

public class Controller implements IController, LevelController, WinController {
	private static final int WIDTH_SIZE = 800;
	private static final int HEIGHT_SIZE = 500;
	private static final int LEVEL_ONE = 1;
	public static final int FRAMES_PER_SECOND = 60;
	public static final int MILLISECOND_DELAY = 1000 / FRAMES_PER_SECOND;
	public static final double SECOND_DELAY = 1.0 / FRAMES_PER_SECOND;

	private double renderTime;
	private Stage myStage;
	private Scene gameScene;
	private Scene levelScene;
	private Pane gameRoot;
	private BorderPane myPane;
	private PauseMenu pauseMenu;
	private GameView gameView;
	private SelectLevel levelSelector;

	private Map<Integer, Pane> gameLevelDisplays;
	private DataGameState currentGameState;
	private SampleToolBar sampleBar;
	private Map<Integer, Map<String, Boolean>> hudPropMap;
	private Timeline animation;
	private String currentGameName;
	private DataGameState gameState;
	private GameManager gameManager;

	public Controller(Stage stage, DataGameState currentGame) {
		this.gameState = currentGame;
		this.myStage = stage;
		this.myStage.setWidth(WIDTH_SIZE);
		this.myStage.setHeight(HEIGHT_SIZE);
		this.myStage.setResizable(false);
		this.gameManager = new GameManager(gameState);
		this.myPane = new BorderPane();
		this.pauseMenu = new PauseMenu(myStage);
		this.levelSelector = new SelectLevel((int) gameState.getLevelProgress(), gameManager.getNumOfLevels(), myStage, this);
		setGameView();
		openLevelSelector();
		//changeGameLevel(1);
	}

	/**
	 * Opens the level selector
	 */
	private void openLevelSelector(){
		myStage.setScene(levelSelector.getMyScene());
	}

	/**
	 * Initializes controller scene
	 * @return
	 */
	public Scene getControllerScene() {
		return this.gameScene;
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
		this.gameView.saveGame();
	}

	/**
	 * Method to call when a level has been won and the level selector needs to appear again
	 * @param level - level won
	 */
	public void levelWon(int level){
		if(level > this.gameManager.getNumOfLevels()){
			gameOver();
		}
		else{
			levelSelector.updateLevelProgress(level + 1);
			openLevelSelector();
		}
	}

	/**
	 * Changes the display of the gave.
	 * @param level to be loaded
	 */
	public void changeGameLevel(int level) {
		this.gameRoot = this.gameLevelDisplays.get(level);
		this.myPane.setCenter(gameRoot);
		this.gameView.setActiveLevel(level);
		this.gameManager.setActiveLevel(level);
		this.sampleBar.setActiveLevel(level);
		this.gameScene = new Scene(myPane, WIDTH_SIZE, HEIGHT_SIZE);
		initializeGameAnimation();
		assignKeyInputs();
		myStage.setScene(gameScene);
	}

	/**
	 * Returns the level game display
	 * @return
	 */
	public Map<Integer, Pane> getGameLevelRoot(){
		return this.gameLevelDisplays;
	}



	/**
	 * Method that sets the current scene of the game
	 */
	private void setGameView() {
		this.gameView = new GameView(this.gameState, this.gameManager);
		this.hudPropMap = this.gameView.getHudPropMap();
		this.gameLevelDisplays = this.gameView.getGameLevelDisplays();

		this.gameRoot = this.gameLevelDisplays.get(LEVEL_ONE);
		this.myPane.setCenter(this.gameRoot);

		MenuGameBar menuBar = new MenuGameBar(this);
		this.myPane.setBottom(menuBar);

		this.sampleBar = new SampleToolBar(this.gameManager, this.hudPropMap);
		this.myPane.setTop(this.sampleBar);
	}

	/**
	 * Begins the animation cycle count of the animation after game has started
	 */
	private void initializeGameAnimation() {
		KeyFrame frame = new KeyFrame(Duration.millis(MILLISECOND_DELAY),
				e -> step(SECOND_DELAY));
		this.animation = new Timeline();
		this.animation.setCycleCount(Timeline.INDEFINITE);
		this.animation.getKeyFrames().add(frame);
		this.animation.play();
	}

	/**
	 * Step method that repeats the animation by checking entities using render and system Manager
	 * @param elapsedTime
	 */
	private void step (double elapsedTime) {
		if (!this.pauseMenu.isShowing()) {
			this.renderTime+=elapsedTime;
			this.gameView.execute(elapsedTime);
			if (this.renderTime>6) {
				this.gameView.render();
				this.renderTime = 0;
			}
			this.gameView.updateScroll(this.gameRoot);
			this.sampleBar.updateGameStatusLabels(this.gameManager);
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
		this.gameScene.setOnKeyPressed(e -> {
			if(e.getCode() == KeyCode.ESCAPE) {
				this.pauseMenu.show(this.myStage);
			} else {
				if(this.gameView != null) {
					this.gameView.setInput(e.getCode());
				}
			}
		});
		this.gameScene.setOnKeyReleased(e -> {
			if(e.getCode() != KeyCode.ESCAPE) {
				if(this.gameView != null) {
					this.gameView.removeInput(e.getCode());
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
