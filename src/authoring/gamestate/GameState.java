package authoring.gamestate;

import java.util.List;
import java.util.logging.Logger;

import data.DataWrite;

import java.util.ArrayList;

/**
 * Keeps track of the current state of the authoring environment, so that the author can save/load games dynamically. 
 *
 * @author Dylan Powers
 * @author Hemanth Yakkali(hy115)
 *
 */
public class GameState implements IGameState {

	/**
	 * This object should only be constructed once, upon initialization of the authoring environment.
	 * It will then continue to keep track of the current state of the game by using the update method below.
	 */
	private List<Level> state;
	
	private final static Logger LOGGER = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

	public GameState() {
		state = new ArrayList<>();
	}

	@Override
	public void save() {
		try {
			DataWrite.saveFile(this,"Test");
			System.out.println("saved!");
		} catch (Exception e) {
			LOGGER.log(java.util.logging.Level.SEVERE, e.toString(), e);
		}
	}

	/**
	 * Updates the current state by adding a new level object to the list of levels.
	 * @param level The level object to add
	 */
	@Override
	public void addLevel(Level level) {
		if(!state.contains(level)) {
			state.add(level);
		}
	}

	/**
	 * Updates the current state my removing a level object.
	 * param level The level object to remove
	 */
	@Override
	public void removeLevel(Level level) {
		if(state.contains(level)) {
			state.remove(level);
		}
	}

	public List<Level> getLevels() {
		return state;
	}
}
