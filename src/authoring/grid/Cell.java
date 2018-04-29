package authoring.grid;

import authoring.entities.Entity;
import engine.components.StringComponent;
import javafx.scene.image.Image;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.Pane;

/**
 * Defines a cell in the grid. The cell will hold nothing when an entity has not been
 * placed inside of it, but will hold an entity when the user has placed an entity inside of it.
 * @author Dylan Powers
 * @author Hemanth Yakkali
 *
 */
public class Cell extends Pane {

	private final String DEFAULT_STYLE = "-fx-background-color: rgba(0, 0, 0, 0); -fx-border-color: black";
	private Entity entity;
	private Image image;

	/**
	 * To initialize a blank cell
	 */
	public Cell() {
		this.setEntity(null);
		this.setPrefWidth(Entity.ENTITY_WIDTH);
		this.setPrefHeight(Entity.ENTITY_HEIGHT);
		this.setStyle(DEFAULT_STYLE);
		this.setUpDrag();
	}
	
	private void setUpDrag() {
		this.setOnDragDetected(e -> {
			Dragboard db = this.startDragAndDrop(TransferMode.COPY);
			ClipboardContent cc = new ClipboardContent();
			cc.putImage(image);
			cc.putString(((StringComponent)entity.get("Name")).getData());
			db.setContent(cc);
			e.consume();
		});
		this.setOnDragDone(e ->{
			this.setEntity(null);
			this.getChildren().clear();
		});
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
	}

	/**
	 * Check if this cell contains an entity.
	 * @return true iff the cell contains an entity
	 */
	public boolean containsEntity() {
		return this.entity != null;
	}

	public void setImage(Image i) {
		image = i;
	}

}
