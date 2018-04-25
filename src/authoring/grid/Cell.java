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

	/**
	 * To initialize a blank cell
	 */
	public Cell() {
		this.setEntity(null);
		this.setPrefWidth(Entity.ENTITY_WIDTH);
		this.setPrefHeight(Entity.ENTITY_HEIGHT);
		this.setStyle("-fx-border-color: black");
	}

	public Entity getEntity() {
		return entity;
	}

	public void setEntity(Entity entity) {
		this.entity = entity;
	}

}
