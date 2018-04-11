package frontend.gamestate;

import engine.components.Component;
import frontend.components.Level;
<<<<<<< HEAD

import java.util.List;
import java.util.Map;

=======
>>>>>>> 585e88583efca66a75c4be1622035435b0d2e727
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
<<<<<<< HEAD
	public void addEntity(Level level, Integer entity);
=======
//	public void addEntity(int levelNumber, Integer entity);
>>>>>>> 585e88583efca66a75c4be1622035435b0d2e727
	
	/**
	 * Updates the current state by removing an entity from the environment.
	 * @param level the level  for the entity to be removed from
	 * @param entity the entity that the user would like to remove.
	 */
<<<<<<< HEAD
	public void removeEntity(Level level, Integer entity);
=======
//	public void removeEntity(int levelNumber, Integer entity);
>>>>>>> 585e88583efca66a75c4be1622035435b0d2e727
	
	/**
	 * Updates the current state by adding a new component tethered to an entity.
	 * @param level the level  to add the component to
	 * @param entity the entity to add the component to.
	 * @param component the component that should be added to the entity.
	 * 
	 */
<<<<<<< HEAD
	public void addComponent(Level level, Integer entity, Component component);
=======
//	public void addComponent(int levelNumber, Integer entity, Component component);
>>>>>>> 585e88583efca66a75c4be1622035435b0d2e727
	
	/**
	 * Updates the current state by adding a new component tethered to an entity.
	 * @param level the level  to remove the component from
	 * @param entity the entity to add the component to.
	 * @param component the component that should be added to the entity.
	 */
	
<<<<<<< HEAD
	public void removeComponent(Level level, Integer entity, Component component);
=======
//	public void removeComponent(int levelNumber, Integer entity, Component component);
>>>>>>> 585e88583efca66a75c4be1622035435b0d2e727
	
	/**
	 * Updates the current state by adding a new level to the list of levels.
	 * @param level the level  to add
	 */
	public void addLevel(Level level);
	
	/**
	 * Updates the current state my removing a level number.
	 * @param level the level  to remove
	 */
<<<<<<< HEAD
	public void remove(Level level);

	/*
		Returns a map that maps levels to entities to components
		which will be used to serialize and deserialize the game information
		TODO change the first part of the map to a Level object
	 */
	public Map<Level, Map<Integer, List<Component>>> getAuthorMap();
=======
	public void removeLevel(Level level);
>>>>>>> 585e88583efca66a75c4be1622035435b0d2e727
}

