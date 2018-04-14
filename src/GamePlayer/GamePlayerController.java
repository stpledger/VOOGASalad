package GamePlayer;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;

import java.util.Map;

import java.util.HashSet;
import java.util.Set;
import java.util.function.Consumer;

import HUD.SampleToolBar;
import Menu.MenuGameBar;
import Menu.PauseMenu;
import buttons.FileUploadButton;
import engine.systems.InputHandler;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.property.SetProperty;
import javafx.beans.property.SimpleSetProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;
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
	private Group gameRoot;

	private BorderPane pane = new BorderPane();
	private PauseMenu pauseMenu = new PauseMenu();
	private GamePlayerEntityView gameView;

	private File currentFile;
	private FileUploadButton fileBtn;
	private Map<Integer, Group> levelEntityGroupMap; //map that is used to store the initial group for each level.

	
	public GamePlayerController(Stage stage) {
		myStage = stage;
		myStage.setResizable(false);
	}
	
	
	public Scene intializeStartScene() {
		SampleToolBar sampleBar = new SampleToolBar();
//		MenuGameBar menuBar = new MenuGameBar(this);
//		pane.setBottom(menuBar);
		fileBtn = pauseMenu.fileBtn;  //public variable need to encapsulate later
		fileBtn.getFileBooleanProperty().addListener((ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) -> {
			try{
				initializeGameStart();
			}
			catch(FileNotFoundException e){
				e.printStackTrace(); //fix this or you will fail
			}
		});
		pane.setTop(sampleBar);
		myScene = new Scene(pane,WIDTH_SIZE,HEIGHT_SIZE);
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
		return myScene;
	}
	
	/**
	 * Method that begins displaying the game
	 * @throws FileNotFoundException 
	 */
	public void initializeGameStart() throws FileNotFoundException {
		MenuGameBar menuBar = new MenuGameBar(this);
		pane.setBottom(menuBar);

		currentFile = fileBtn.getFile();
		gameView = new GamePlayerEntityView(currentFile);


		levelEntityGroupMap = gameView.getlevelEntityMap();
		gameRoot = levelEntityGroupMap.get(1);

		pane.getChildren().addAll(gameRoot); //adds starting game Root to the file and placing it in the Center Pane

		initializeGameAnimation(); //begins the animation cycle

	}

	/**
	 * Begins the animation cycle count of the animation after game has started
	 */
	public void initializeGameAnimation() {
		KeyFrame frame = new KeyFrame(Duration.millis(MILLISECOND_DELAY),
				e -> step(SECOND_DELAY));

		Timeline animation = new Timeline();
		animation.setCycleCount(Timeline.INDEFINITE);
		animation.getKeyFrames().add(frame);
		animation.play();
	}
	/**
	 * Changes the display of the gave.
	 * @param level to be loaded
	 */
	public void changeGameLevel(int level) {
		gameRoot = levelEntityGroupMap.get(level);
		//gameView.initializeGamePlayerEntityView(level);

		pane.getChildren().clear();
		pane.getChildren().addAll(gameRoot);
	}
	
	public Map<Integer, Group> getGameLevelRoot(){
		return levelEntityGroupMap;
	}
	
	/**
	 * Step method that repeats the animation by checking entities using render and system Manager
	 * @param elapsedTime
	 */
	private void step (double elapsedTime) {
	    renderTime+=elapsedTime;
		gameView.execute(elapsedTime);
		if (renderTime>15) {
			gameView.render();
			renderTime = 0;
		}
	}
}
