package frontend.gamestate;

import engine.components.Component;
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
	 * @param levelNumber the level number for the entity to be added to
	 * @param entity the entity that the user would like to add.
	 */
	public void addEntity(int levelNumber, Integer entity);
	
	/**
	 * Updates the current state by removing an entity from the environment.
	 * @param levelNumber the level number for the entity to be removed from
	 * @param entity the entity that the user would like to remove.
	 */
	public void removeEntity(int levelNumber, Integer entity);
	
	/**
	 * Updates the current state by adding a new component tethered to an entity.
	 * @param levelNumber the level number to add the component to
	 * @param entity the entity to add the component to.
	 * @param component the component that should be added to the entity.
	 * 
	 */
	public void addComponent(int levelNumber, Integer entity, Component component);
	
	/**
	 * Updates the current state by adding a new component tethered to an entity.
	 * @param levelNumber the level number to remove the component from
	 * @param entity the entity to add the component to.
	 * @param component the component that should be added to the entity.
	 */
	
	public void removeComponent(int levelNumber, Integer entity, Component component);
	
	/**
	 * Updates the current state by adding a new level to the list of levels.
	 */
	public void addLevel();
	
	/**
	 * Updates the current state my removing a level number.
	 * @param levelNumber the level number to remove
	 */
	public void remove(int levelNumber);
}
