/**package GamePlayer;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.function.Consumer;

import HUD.SampleToolBar;

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
	private final int HEIGHT_SIZE = 400;
	public final int FRAMES_PER_SECOND = 60;
	public final int MILLISECOND_DELAY = 1000 / FRAMES_PER_SECOND;
	public final double SECOND_DELAY = 1.0 / FRAMES_PER_SECOND;
	private Stage myStage;
	private Scene myScene;
	private Group gameRoot;
	private BorderPane pane = new BorderPane();
	private PauseMenu pauseMenu = new PauseMenu();
	private GamePlayerEntityView gameView;
	private File currentFile;
	private FileUploadButton fileBtn;
	//Consumer that changes the view of the pane.
//	Consumer newLevel = (e) -> {
//		entityTypes.addAll(Arrays.asList(getEntitiesInEntitiesPackage()));
//		pane.setCenter((Node) e);
//	};

	
	public GamePlayerController(Stage stage) {
		myStage = stage;
		
	}
	
	
	public Scene intializeStartScene() {
		SampleToolBar sampleBar = new SampleToolBar();
		//MenuGameBar menuBar = new MenuGameBar();
		//pane.setBottom(menuBar);
		fileBtn = pauseMenu.fileBtn;  //public variable need to encapsulate later
		fileBtn.getFileBooleanProperty().addListener((ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) -> {
			try{
				initializeGameStart();
			}
			catch(FileNotFoundException e){
				e.printStackTrace();
			}
		});
		pane.setTop(sampleBar);
		myScene = new Scene(pane,WIDTH_SIZE,HEIGHT_SIZE);
		myScene.setOnKeyPressed(e -> {
			if(e.getCode() == KeyCode.ESCAPE) {
				pauseMenu.show(myStage);
			// SORRY
			} else {
				gameView.setInput(e.getCode());
			}
		});
//		
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
<<<<<<< HEAD
	/**public void initializeGameStart() {
=======
	public void initializeGameStart() throws FileNotFoundException {
		/**
		 * When the Game Starts create the Level Map;
		 */
>>>>>>> 5a8cb25e4995b2deaf23b47c9c4cc00e0f9bc0a4
		currentFile = fileBtn.getFile();
		gameView = new GamePlayerEntityView(currentFile);
		
		
		gameRoot = gameView.createEntityGroup();
		//gameRoot.getChildren().add(new Rectangle(200,200));
<<<<<<< HEAD
		myScene.setOnKeyPressed(e -> gameView.setInput(e.getCode()));
=======
		//myScene.setOnKeyPressed(e -> gameView.setInput(e.getCode()));
>>>>>>> 5ffe0488334c8d3bdd3e798934c76a03d31a2a22
		pane.setCenter(gameRoot); //adds starting game Root to the file and placing it in the Center Pane
		
		initializeGameAnimation(); //begins the animation cycle

	}

	/**
	 * Begins the animation cycle count of the animation after game has started
	 *
	public void initializeGameAnimation() {
		
		KeyFrame frame = new KeyFrame(Duration.millis(MILLISECOND_DELAY),
				e -> step(SECOND_DELAY, gameRoot));
		Timeline animation = new Timeline();
		animation.setCycleCount(Timeline.INDEFINITE);
		animation.getKeyFrames().add(frame);
		animation.play();
	}

	
	/**
	 * Step method that repeats the animation by checking entities using render and system Manager
	 * @param elapsedTime
	 * @param root
	 */
<<<<<<< HEAD
	/**private void step (double elapsedTime, Group root) {
		gameView.systemManager.execute(elapsedTime);
		gameView.renderManager.garbageCollect();
		gameView.renderManager.renderObjects();
		
	}
	

	/**
	 * Listener for the file button.
	 * @param code
	 */
	
	
	/**private void handleKeyInput (KeyCode code) {
		if (code == KeyCode.ESCAPE) {
			Stage mainStage = (Stage) pane.getScene().getWindow();
			//instantiate the Pause menu popup class 
//			Popup popup = new Popup();
//	        popup.setX(500);
//	        popup.setY(200);
//	        popup.getContent().addAll(new Circle(25, 25, 50, Color.AQUAMARINE));
			pauseMenu.show(mainStage);
			//pane.getChildren().get(0).setVisible(false);
			//mainStage.setScene(new Scene(new Button("asdkl;f")));
		}
		
		
		
		
		
=======
	private void step (double elapsedTime, Group root) {
		gameView.getSystemManager().execute(elapsedTime);
		gameView.getRenderManager().garbageCollect();
		gameView.getRenderManager().renderObjects();
>>>>>>> 5a8cb25e4995b2deaf23b47c9c4cc00e0f9bc0a4
	}
}
**/