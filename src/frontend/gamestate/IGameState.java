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
	 * @param entity the entity that the user would like to add.
	 */
	public void addEntity(Integer entity);
	
	/**
	 * Updates the current state by removing an entity from the environment.
	 * @param entity the entity that the user would like to remove.
	 */
	public void removeEntity(Integer entity);
	
	/**
	 * Updates the current state by adding a new component tethered to an entity.
	 * @param entity the entity to add the component to.
	 * @param component the component that should be added to the entity.
	 */
	public void addComponent(Integer entity, Component component);
	
	/**
	 * Updates the current state by adding a new component tethered to an entity.
	 * @param entity the entity to add the component to.
	 * @param component the component that should be added to the entity.
	 */
	public void removeComponent(Integer entity, Component component);
	
	public void addLevel();
	
	public void removeLevel();
	
}
