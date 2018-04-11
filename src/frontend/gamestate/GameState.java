package frontend.gamestate;

import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.ArrayList;

import engine.components.Component;
import frontend.components.Level;

/**
 * Keeps track of the current state of the authoring environment, so that the author can save/load games dynamically. 
 *
 * @author Dylan Powers
 *
 */
public class GameState implements IGameState {

<<<<<<< HEAD
	private Map<Level, Map<Integer, List<Component>>> state;
=======
>>>>>>> 585e88583efca66a75c4be1622035435b0d2e727
	/**
	 * This object should only be constructed once, upon initialization of the authoring environment.
	 * It will then continue to keep track of the current state of the game by using the update method below.
	 */
	private List<Level> state;

	public GameState() {
		state = new ArrayList<>();
	}
	
<<<<<<< HEAD
	/*allows the creation of a gamestate from an existing 
	 * map that represents a game @param state
	 */
	public GameState(Map<Level, Map<Integer, List<Component>>> state)
	{
		this.state = state;
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
=======
	@Override
	public void save() {
		
	}
	
	/**
	 * Updates the current state by adding a new level object to the list of levels.
	 * @param level The level object to add
	 */
	@Override
	public void addLevel(Level level) {
		if(!state.contains(level)) {
			state.add(level);
>>>>>>> 585e88583efca66a75c4be1622035435b0d2e727
		}
	}
	
	/**
<<<<<<< HEAD
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

=======
	 * Updates the current state my removing a level object.
	 * @param level The level object to remove
	 */
	@Override
	public void removeLevel(Level level) {
		if(state.contains(level)) {
			state.remove(level);
		}
	}
>>>>>>> 585e88583efca66a75c4be1622035435b0d2e727
}
