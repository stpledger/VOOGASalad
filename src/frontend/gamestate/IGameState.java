package frontend.gamestate;

import frontend.components.Level;

/**
 * Public-facing API to load and save games from the authoring environment. 
 * @author dylanpowers
 *
 */
public interface IGameState {
	
	/**
	 * Saves the current state of the game.
	 */
	public void save();
	
	/**
	 * Updates the current state by adding a new level to the list of levels.
	 * @param level the level  to add
	 */
	public void addLevel(Level level);
	
	/**
	 * Updates the current state my removing a level number.
	 * @param level the level  to remove
	 */
	public void removeLevel(Level level);

}

