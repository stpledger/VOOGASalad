package GamePlayer;

import java.io.File;
import java.io.FileNotFoundException;

import java.util.Map;

import HUD.SampleToolBar;
import Menu.MenuGameBar;
import Menu.PauseMenu;
import buttons.FileUploadButton;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.value.ObservableValue;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.BorderPane;
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
	private Group originGameRoot;
	private Timeline myTimeline;
	private ScrollPane myScroll;

	private BorderPane myPane = new BorderPane();
	private PauseMenu pauseMenu = new PauseMenu(this);
	private GamePlayerEntityView gameView;

	private File currentFile;
	private FileUploadButton fileBtn;
	private Map<Integer, Group> levelEntityGroupMap; //map that is used to store the initial group for each level.

	private Timeline animation;
	
	public GamePlayerController(Stage stage) {
		myStage = stage;
		myStage.setResizable(false);
	}
	
	
	public Scene intializeStartScene() {
		fileBtn = pauseMenu.fileBtn;  //public variable need to encapsulate later
		fileBtn.getFileBooleanProperty().addListener((ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) -> {
			try{
				initializeGameStart();
				//initializeLevelFile();
			}
			catch(FileNotFoundException e){
				e.printStackTrace(); //fix this or you will fail
			}
		});
		//myPane.setTop(sampleBar);
		myScene = new Scene(myPane,WIDTH_SIZE,HEIGHT_SIZE);
		myScene.setOnKeyPressed(e -> {
			if(e.getCode() == KeyCode.ESCAPE) {
				pauseMenu.show(myStage);
				//myTimeline.pause();
			// SORRY

			} else {
				gameView.setInput(e.getCode());
			}
		});

		myScene.setOnKeyReleased(e -> {
			if(e.getCode() != KeyCode.ESCAPE) {
				gameView.removeInput(e.getCode());
			}
		});
		return myScene;
	}
	
	/**
	 * Method that begins displaying the game
	 * @throws FileNotFoundException 
	 */
	public void initializeGameStart() throws FileNotFoundException {
		SampleToolBar sampleBar = new SampleToolBar(this);
		myPane.setTop(sampleBar);
		currentFile = fileBtn.getFile();
		gameView = new GamePlayerEntityView(currentFile);
		levelEntityGroupMap = gameView.getlevelEntityMap();
		MenuGameBar menuBar = new MenuGameBar(this);
		myPane.setBottom(menuBar);
		gameRoot = levelEntityGroupMap.get(1);  //level 1
		originGameRoot = new Group();
		originGameRoot.getChildren().add(gameRoot);
		//initializeScroll();
		myPane.setCenter(originGameRoot); //adds starting game Root to the file and placing it in the Center Pane
		initializeGameAnimation(); //begins the animation cycle
	}
	
	/**
	 * Takes in an organized map of Levels and stores the file in the current
	 * Game File for the controller to operate. From here, future level changes
	 * can be accessed and changed from this file.
	 */
	public void initializeLevelFile() {
		
		
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
		originGameRoot.getChildren().clear();
		originGameRoot.getChildren().add(gameRoot);


		//myScroll.setContent(levelEntityGroupMap.get(level));
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
		gameView.updateScroll(gameRoot);
	}

	public void saveGame(){
		gameView.saveGame();
	}

	private void initializeScroll(){
		myScroll = new ScrollPane(gameRoot);
		myScroll.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
		myScroll.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
	}
}
