package frontend.gamestate;

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
	 * Updates the current state when a change is made in the authoring environment.
	 */
	public void update();
}
