package GamePlayer;

import java.io.File;
<<<<<<< HEAD

=======
>>>>>>> dbe4ae09bbd68f8c94bb0b5e3180cfbf7b0010c7
import java.util.Set;
import HUD.SampleToolBar;
import Menu.PauseMenu;
import buttons.FileUploadButton;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.property.SetProperty;
import javafx.beans.property.SimpleSetProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.util.Duration;

public class GamePlayerController {
	private final int WIDTH_SIZE = 800;
	private final int HEIGHT_SIZE = 400;
	public final int FRAMES_PER_SECOND = 60;
	public final int MILLISECOND_DELAY = 1000 / FRAMES_PER_SECOND;
	public final double SECOND_DELAY = 1.0 / FRAMES_PER_SECOND;
	private Scene myScene;
	private Group gameRoot;
	private BorderPane pane = new BorderPane();
	private PauseMenu pauseMenu = new PauseMenu();
	private GamePlayerEntityView gameView;
	private File currentFile;
	private FileUploadButton fileBtn;
	
	// SORRY FOR CHANGING YOUR CODE PLAYER	-ENGINE Team
	private SetProperty<KeyCode> activeKeys;
	
	public GamePlayerController() {
		activeKeys = new SimpleSetProperty<>();
	}
	
	
	public Scene intializeStartScene() {
		SampleToolBar sampleBar = new SampleToolBar();
//		fileBtn = sample
		fileBtn = new FileUploadButton();
		fileBtn.getFileBooleanProperty().addListener((ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) -> initializeGameStart());
			/*{@Override
			public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
				System.out.println("blah");
				initializeGameStart(); //begin the game
			}
		});*/
		
//		group = new Group();
//		group.getChildren().add(fileBtn);
		pane.setTop(sampleBar);
		myScene = new Scene(pane,WIDTH_SIZE,HEIGHT_SIZE);
		myScene.setOnKeyPressed(e -> {
			handleKeyInput(e.getCode());
			// SORRY
			//activeKeys.add(e.getCode());
			
		});
//		
//		myScene.setOnKeyReleased(e -> {
//			activeKeys.remove(e.getCode());
//		});
		return myScene;
	}
	
	/**
	 * Method that begins displaying the game
	 */
	public void initializeGameStart() {
		currentFile = fileBtn.getFile();
		gameView = new GamePlayerEntityView(currentFile);
		gameRoot = gameView.createEntityGroup();
		pane.setCenter(gameRoot); //adds starting game Root to the file and placing it in the Center Pane
		initializeGameAnimation(); //begins the animation cycle
	}

	/**
	 * Begins the animation cycle count of the animation after game has started
	 */
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
	private void step (double elapsedTime, Group root) {
		gameView.getSystemManager().execute(elapsedTime);
		gameView.getRenderManager().garbageCollect();
		gameView.getRenderManager().renderObjects();
		
	}
	

	/**
	 * Listener for the file button.
	 * @param code
	 */
	
	
	private void handleKeyInput (KeyCode code) {
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
		
		
		
		
		
	}
}
