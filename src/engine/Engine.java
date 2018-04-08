package engine;

/**
 * Interface to be used by Player to run the engine. Player should run game loop, 
 * and call engine to update with amount of time passed as argument.
 * @author fitzj
 */
public interface Engine {
	
	/**
	 * Update method, for engine to run systems and update components
	 * @param time	Time passed since last update
	 */
	public void update(double time);
}
