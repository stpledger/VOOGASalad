
/**
 * Interface to be used by the game player when interacting with the Engine
 * @author Jack Fitzpatrick
 */
public interface EngineToPlayerInterface {

	/**
	 * Starts game loop using GameState used with Engine constructor
	 */
	public void play();
	
	/**
	 * Pauses game loop, so GameState is no longer being modified
	 */
	public void pause();
	
}
