<<<<<<< HEAD
package GamePlayer;
=======
package gameplayer;
>>>>>>> 26e8f6b0b1bed98bfb35b300f0b49fd1d18e6429

import java.util.Map;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;

public interface IController {
	
	/**
	 * Initializes controller scene
	 * @return
	 */
	public Scene initializeStartScene();
	
	/**
	 * Changes the display of the gave.
	 * @param level to be loaded
	 */
	public void changeGameLevel(int level);
	
	/**
	 * Returns the level game display
	 * @return
	 */
	public Map<Integer, Pane> getGameLevelRoot();
	
	/**
	 * Restarts the current level
	 */
	public void restartGame();
	
	/**
	 * Saves game to a file
	 */
	public void saveGame();
	
	
	
	
	
	
}
