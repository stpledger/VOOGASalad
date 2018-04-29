package authoring.gamestate;

import java.util.List;

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
	 * Updates the current state.
	 * @param o the object to add
	 */
	public void addLevel(Level level);

	/**
	 * Updates the current state.
	 * @param o the object to remove
	 */
	public void removeLevel(Level level);

	public List<Level> getLevels();
}

