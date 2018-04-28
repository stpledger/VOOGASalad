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
	private final String DEFAULT_STYLE = "-fx-background-color: rgba(0, 0, 0, 0); -fx-border-color: black";
	private int number;
	
	/**
	 * {@code Boolean} describes if cell is occupied by entity sprite
	 */
	private boolean isOccupied;

	/**
	 * To initialize a blank cell
	 * @param number the (distinct) number of the cell
	 */
	public Cell(int number) {
		this.setEntity(null);
//		this.setOccupied(false);
		this.setPrefWidth(Entity.ENTITY_WIDTH);
		this.setPrefHeight(Entity.ENTITY_HEIGHT);
		this.setStyle(DEFAULT_STYLE);
		this.number = number;
	}
	
	/**
	 * @return the entity that is within this cell, if it has one
	 */
	public Entity getEntity() {
		return entity;
	}

	/**
	 * Set the entity for this cell and marks cell as occupied
	 * @param entity the entity to be placed in this cell
	 */
	public void setEntity(Entity entity) {
		this.entity = entity;
//		this.setOccupied(true);
	}

//	/**
//	 * 
//	 * @return Whether or not cell is occupied by entity sprite
//	 */
//	public boolean isOccupied() {
//		return isOccupied;
//	}

	/**
	 * @return the number (ID) of this cell
	 */
	public int getNumber() {
		return this.number;
	}
//	/**
//	 * 
//	 * @param isOccupied {@code Boolean} cell occupied by entity sprite
//	 */
//	public void setOccupied(boolean isOccupied) {
//		this.isOccupied = isOccupied;
//	}
	
	/**
	 * Check if this cell contains an entity.
	 * @return true iff the cell contains an entity
	 */
	public boolean containsEntity() {
		return this.entity != null;
	}
	
}
