package frontend.gamestate;

import engine.components.Component;
import frontend.components.Level;

import java.util.List;
import java.util.Map;

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
	 * Updates the current state by adding a new entity to the environment.
	 * @param level the level for the entity to be added to
	 * @param entity the entity that the user would like to add.
	 */
	public void addEntity(Level level, Integer entity);
	
	/**
	 * Updates the current state by removing an entity from the environment.
	 * @param level the level  for the entity to be removed from
	 * @param entity the entity that the user would like to remove.
	 */
	public void removeEntity(Level level, Integer entity);
	
	/**
	 * Updates the current state by adding a new component tethered to an entity.
	 * @param level the level  to add the component to
	 * @param entity the entity to add the component to.
	 * @param component the component that should be added to the entity.
	 * 
	 */
	public void addComponent(Level level, Integer entity, Component component);
	
	/**
	 * Updates the current state by adding a new component tethered to an entity.
	 * @param level the level  to remove the component from
	 * @param entity the entity to add the component to.
	 * @param component the component that should be added to the entity.
	 */
	
	public void removeComponent(Level level, Integer entity, Component component);
	
	/**
	 * Updates the current state by adding a new level to the list of levels.
	 * @param level the level  to add
	 */
	public void addLevel(Level level);
	
	/**
	 * Updates the current state my removing a level number.
	 * @param level the level  to remove
	 */
	public void remove(Level level);

	/*
		Returns a map that maps levels to entities to components
		which will be used to serialize and deserialize the game information
		TODO change the first part of the map to a Level object
	 */
	public Map<Level, Map<Integer, List<Component>>> getAuthorMap();
}

