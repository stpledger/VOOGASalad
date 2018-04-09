package frontend.gamestate;

import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.ArrayList;
import engine.components.Component;

/**
 * Keeps track of the current state of the authoring environment, so that the author can save/load games dynamically. 
 *
 * @author Dylan Powers
 *
 */
public class GameState implements IGameState {

	private Map<Integer, Map<Integer, List<Component>>> state;
	/**
	 * This object should only be constructed once, upon initialization of the authoring environment.
	 * It will then continue to keep track of the current state of the game by using the update method below.
	 */
	public GameState() {
		state = new HashMap<>();
	}
	
	public void save() {
		
	}

	/**
	 * Add an entity to the state. This would happen if a user places a new object into the {@code GameEnvironmentView}.
	 * @param levelNumber the level number for the entity to be added to
	 * @param entity the entity that the user would like to add
	 */
	public void addEntity(int levelNumber, Integer entity) {
		// TODO check that level exists in the first place
		if (!state.get(levelNumber).containsKey(entity)) 
			state.get(levelNumber).put(entity, new ArrayList<Component>());
	}
	
	/**
	 * Remove an entity from the state. This would happen if the user deletes an object from the {@code GameEnvironmentView}.
	 * @param levelNumber the level number for the entity to be removed from
	 * @param entity the entity that the user would like to remove
	 */
	public void removeEntity(int levelNumber, Integer entity) {
		// TODO check that level exists in the first place
		if (state.get(levelNumber).containsKey(entity)) 
			state.remove(entity);
	}
	
	/**
	 * Add a new component to the state. The user would edit this using the {@code LocalPropertiesView}.
	 * @param levelNumber the level number to add the component to
	 * @param entity the entity that the component should be added to
	 * @param component the component that should be added
	 */
	public void addComponent(int levelNumber, Integer entity, Component component) {
		// TODO check that level exists in the first place
		if (!state.get(levelNumber).containsKey(entity)) {
			// TODO write exception for what happens if the entity does not exist
		} else {
			if (!state.get(levelNumber).get(entity).contains(component)) 
				state.get(levelNumber).get(entity).add(component);
		}
	}
	
	/**
	 * Remove a component from the state. The user would edit this using the {@code LocalPropertiesView}.
	 * @param levelNumber the level number to remove the component from
	 * @param entity the entity that the component should be removed from
	 * @param component the component that should be removed
	 */
	public void removeComponent(int levelNumber, Integer entity, Component component) {
		// TODO check that level exists in the first place
		if (!state.get(levelNumber).containsKey(entity)) {
			// TODO write exception for what happens if the entity does not exist
		} else {
			if (state.get(levelNumber).get(entity).contains(component))
				state.get(levelNumber).get(entity).remove(component);
		}
	}
	
	/**
	 * Updates the current state by adding a new level to the list of levels.
	 * @param levelNumber the level number to remove
	 */
	public void addLevel(int levelNumber) {
		state.put(levelNumber, new HashMap<>());
	}
	
	/**
	 * Updates the current state my removing a level number.
	 * @param levelNumber the level number to remove
	 */
	public void remove(int levelNumber) {
		state.remove(levelNumber);
	}
}
