package gameplayer;

import java.util.List;
import buttons.IGamePlayerButton;
import javafx.scene.Scene;

public abstract class BranchScreenView {
	
	protected final int WIDTH_SIZE = 800;
	protected final int HEIGHT_SIZE = 500;

	public BranchScreenView() {}
	
	/**
	 * Returns the Scene of the Branched Screen
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
