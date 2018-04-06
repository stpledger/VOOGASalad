package engine;

import java.util.ArrayList;
import java.util.List;

import engine.systems.ISystem;
import engine.test.TestGameState;

/**
 * Actual instantiation of engine. Runs game initializer and calls systems to update.
 * @author fitzj
 */
public class InternalEngine implements Engine {

	private List<ISystem> systems;
	
	public InternalEngine(List<ISystem> systems) {
		this.systems = systems;
	}

	/**
	 * Method to update systems
	 * @param time 	Time since last update
	 */
	public void update(double time) {
		systems.forEach(sys -> sys.execute(time));
	}	
}
