package frontend.gamestate;

import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.ArrayList;
import engine.components.Component;
/**
 * Keeps track of the current state of the authoring environment, so that the author can save/load games dynamically. 
 *
 * @author dylanpowers
 *
 */
public class GameState implements IGameState {

	private Map<Integer, List<Component>> state;
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
	 * @param entity the entity that the user would like to add
	 */
	public void addEntity(Integer entity) {
		if (!state.containsKey(entity)) 
			state.put(entity, new ArrayList<Component>());
	}
	
	/**
	 * Remove an entity from the state. This would happen if the user deletes an object from the {@code GameEnvironmentView}.
	 * @param entity the entity that the user would like to remove
	 */
	public void removeEntity(Integer entity) {
		if (state.containsKey(entity)) 
			state.remove(entity);
	}
	
	/**
	 * Add a new component to the state. The user would edit this using the {@code LocalPropertiesView}.
	 * @param entity the entity that the component should be added to
	 * @param component the component that should be added
	 */
	public void addComponent(Integer entity, Component component) {
		if (!state.containsKey(entity)) {
			// TODO write exception for what happens if the entity does not exist
		} else {
			if (!state.get(entity).contains(component)) 
				state.get(entity).add(component);
		}
	}
	
	/**
	 * Remove a component from the state. The user would edit this using the {@code LocalPropertiesView}.
	 * @param entity the entity that the component should be removed from
	 * @param component the component that should be removed
	 */
	public void removeComponent(Integer entity, Component component) {
		if (!state.containsKey(entity)) {
			// TODO write exception for what happens if the entity does not exist
		} else {
			if (state.get(entity).contains(component))
				state.get(entity).remove(component);
		}
	}
}
