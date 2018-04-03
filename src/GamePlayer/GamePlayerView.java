import javafx.scene.Scene;
import javafx.scene.paint.Color;

public class GamePlayerView {
	
	private Scene myScene;
	private Group group;

	public GamePlayerView() {
		
	}
	
	public Scene intializeStartScene() {
		group = new Group();
		myScene = setupScene();
		return myScene;
	}
	
	/**
	 * 
	 * @return New scene with the grid, buttons, and a background color
	 */
	public static Scene setupScene() {
		return new Scene(group,WIDTH_SIZE,HEIGHT_SIZE,Color.WHEAT);
	}
}
