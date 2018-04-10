package frontend.gamestate;

import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.ArrayList;

import data.Level;
import engine.components.Component;

/**
 * Keeps track of the current state of the authoring environment, so that the author can save/load games dynamically. 
 *
 * @author Dylan Powers
 *
 */
public class GameState implements IGameState {

	private Map<Level, Map<Integer, List<Component>>> state;
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
	 * @param level the level for the entity to be added to
	 * @param entity the entity that the user would like to add
	 */
	public void addEntity(Level level, Integer entity) {
		// TODO check that level exists in the first place
		if (!state.get(level).containsKey(entity))
			state.get(level).put(entity, new ArrayList<Component>());
	}
	
	/**
	 * Remove an entity from the state. This would happen if the user deletes an object from the {@code GameEnvironmentView}.
	 * @param level the level  for the entity to be removed from
	 * @param entity the entity that the user would like to remove
	 */
	public void removeEntity(Level level, Integer entity) {
		// TODO check that level exists in the first place
		if (state.get(level).containsKey(entity))
			state.remove(entity);
	}
	
	/**
	 * Add a new component to the state. The user would edit this using the {@code LocalPropertiesView}.
	 * @param level the level to add the component to
	 * @param entity the entity that the component should be added to
	 * @param component the component that should be added
	 */
	public void addComponent(Level level, Integer entity, Component component) {
		// TODO check that level exists in the first place
		if (!state.get(level).containsKey(entity)) {
			// TODO write exception for what happens if the entity does not exist
		} else {
			if (!state.get(level).get(entity).contains(component))
				state.get(level).get(entity).add(component);
		}
	}
	
	/**
	 * Remove a component from the state. The user would edit this using the {@code LocalPropertiesView}.
	 * @param level  level to remove the component from
	 * @param entity the entity that the component should be removed from
	 * @param component the component that should be removed
	 */
	public void removeComponent(Level level, Integer entity, Component component) {
		// TODO check that level exists in the first place
		if (!state.get(level).containsKey(entity)) {
			// TODO write exception for what happens if the entity does not exist
		} else {
			if (state.get(level).get(entity).contains(component))
				state.get(level).get(entity).remove(component);
		}
	}
	
	/**
	 * Updates the current state by adding a new level to the list of levels.
	 * @param level the level remove
	 */
	public void addLevel(Level level){
		state.put(level, new HashMap<>());
	}
	
	/**
	 * Updates the current state my removing a level number.
	 * @param level the level  to remove
	 */
	public void remove(Level level) {
		state.remove(level);
	}

	/*
		Returns a map that maps levels to entities to components
		which will be used to serialize and deserialize the game information
		TODO change the first part of the map to a Level object
	 */
	public Map<Level, Map<Integer, List<Component>>> getAuthorMap()
	{
		return state;
	}

}
