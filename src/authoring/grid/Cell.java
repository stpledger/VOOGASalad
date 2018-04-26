package authoring.grid;

import authoring.entities.Entity;
import javafx.scene.layout.Pane;

/**
 * Defines a cell in the grid. The cell will hold nothing when an entity has not been
 * placed inside of it, but will hold an entity when the user has placed an entity inside of it.
 * @author Dylan Powers
 * @author Hemanth Yakkali
 *
 */
public class Cell extends Pane {

	private Entity entity;
	private boolean isOccupied;

	/**
	 * To initialize a blank cell
	 */
	public Cell() {
		this.setEntity(null);
		this.setOccupied(false);
		this.setPrefWidth(Entity.ENTITY_WIDTH);
		this.setPrefHeight(Entity.ENTITY_HEIGHT);
		this.setStyle("-fx-border-color: black");
	}
	
	/**
	 * @return the entity that is within this cell, if it has one
	 */
	public Entity getEntity() {
		return entity;
	}

	/**
	 * Set the entity for this cell
	 * @param entity the entity to be placed in this cell
	 */
	public void setEntity(Entity entity) {
		this.entity = entity;
	}

	/**
	 * 
	 * @return Whether or not cell is occupied by entity sprite
	 */
	public boolean isOccupied() {
		return isOccupied;
	}

	/**
	 * 
	 * @param isOccupied {@code Boolean} cell occupied by entity sprite
	 */
	public void setOccupied(boolean isOccupied) {
		this.isOccupied = isOccupied;
	}

}
