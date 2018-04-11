package frontend.gamestate;

import java.util.List;

import data.DataWrite;

import java.util.ArrayList;
import frontend.components.Level;

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

	public GameState() {
		state = new ArrayList<>();
	}
	
	@Override
	public void save() {
		try {
			DataWrite.saveFile(this,"Test");
			System.out.println("saved!");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			System.out.print("didnt work");
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
