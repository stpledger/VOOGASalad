package frontend.gamestate;

import frontend.components.Level;
<<<<<<< HEAD

=======
>>>>>>> cfc057d2d57201c0659b86078c34e768f921d3ac
import java.util.List;
import java.util.Map;

/**
 * Public-facing API to load and save games from the authoring environment. 
 * @author dylanpowers
 *
 */
public interface IGameState {
	
	/**
	 * Saves the current state of the game. Should only be called when the user wishes to save the game by pressing the button.
	 */
	public void save();
	
	/**
	 * Updates the current state by adding a new level to the list of levels.
	 * @param levelNumber the level number to add
	 */
	public void addLevel(Level level);
	
	/**
	 * Updates the current state my removing a level number.
	 * @param level the level  to remove
	 */
	public void removeLevel(Level level);

	public List<Level> getLevels();
}

