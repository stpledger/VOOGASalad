package GamePlayer;

import HUD.SampleToolBar;
import Menu.PauseMenu;
import buttons.FileUploadButton;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ToolBar;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Popup;
import javafx.stage.Stage;

public class GamePlayerController {
	private final int WIDTH_SIZE = 800;
	private final int HEIGHT_SIZE = 400;
	private Scene myScene;
	private Group group;
	private BorderPane pane = new BorderPane();
	private PauseMenu pauseMenu = new PauseMenu();

	public GamePlayerController() {

	}
	
	public Scene intializeStartScene() {
	//FileUploadButton fileBtn = new FileUploadButton();
		SampleToolBar sampleBar = new SampleToolBar();
		group = new Group();
	//	group.getChildren().add(fileBtn);
		pane.setTop(sampleBar);
		myScene = setupScene();
		myScene.setOnKeyPressed(e -> handleKeyInput(e.getCode()));
		return myScene;
	}
	

	/**
	 * 
	 * @return New scene with the grid, buttons, and a background color
	 */
	public Scene setupScene() {
		return new Scene(pane,WIDTH_SIZE,HEIGHT_SIZE);
	}
	
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
