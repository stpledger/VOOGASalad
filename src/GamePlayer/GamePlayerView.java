package GamePlayer;

import buttons.FileUploadButton;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;

public class GamePlayerView {
	private final int WIDTH_SIZE = 824;
	private final int HEIGHT_SIZE = 864;
	private Scene myScene;
	private Group group;

	public GamePlayerView() {
		
	}
	
	public Scene intializeStartScene() {
		FileUploadButton fileBtn = new FileUploadButton();
		group = new Group();
		group.getChildren().add(fileBtn);
		myScene = setupScene();
		return myScene;
	}
	
	/**
	 * 
	 * @return New scene with the grid, buttons, and a background color
	 */
	public Scene setupScene() {
		return new Scene(group,WIDTH_SIZE,HEIGHT_SIZE,Color.WHEAT);
	}
}
