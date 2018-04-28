package GamePlayer;

import java.util.ResourceBundle;

import javafx.scene.Scene;
import javafx.stage.Stage;

public abstract class BranchScreenView {
	
	private Stage myStage;
	private ResourceBundle myResources;
	private Scene myScene;
	private final int WIDTH_SIZE = 800;
	private final int HEIGHT_SIZE = 500;

	public BranchScreenView(Scene scene, String resource) {
		this.myScene = scene;
		this.myResources = ResourceBundle.getBundle(resource);
	}
	
	protected Scene getScene() {
		return this.myScene;
	}
	
	

}
