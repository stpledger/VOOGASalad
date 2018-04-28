package GamePlayer;

import java.util.List;
import java.util.ResourceBundle;

import buttons.IGamePlayerButton;
import javafx.scene.Scene;
import javafx.stage.Stage;

public abstract class BranchScreenView {
	
	private Stage myStage;
//	private ResourceBundle myResources;
	private Scene myScene;
	protected final int WIDTH_SIZE = 800;
	protected final int HEIGHT_SIZE = 500;
	private List<IGamePlayerButton> buttonList;

	public BranchScreenView() {
		//this.myResources = ResourceBundle.getBundle(resource);
	}
	
	/**
	 * Returns the Scene of the Branch Screen
	 * @return
	 */
	public abstract Scene getScene();
	
	/**
	 * Obtains buttons from the screen to be connected to the controller
	 * @return
	 */
	public abstract List<IGamePlayerButton> getButtons();

	
	/**
	 * Abstract method that initializes all components on an extended Screen
	 * @return
	 */
	public abstract Scene initializeScreen();
	

}
