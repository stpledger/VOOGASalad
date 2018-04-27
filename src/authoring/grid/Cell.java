package authoring.grid;

import authoring.entities.Entity;
import authoring.entities.data.EntityLoader;
import authoring.views.properties.LocalPropertiesView;
import authoring.views.properties.PropertiesView;
import engine.components.Component;
import javafx.scene.image.ImageView;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseButton;
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

	private Entity entity;
	private int number;
	private final String DEFAULT_STYLE = "-fx-background-color: rgba(0, 0, 0, 0); -fx-border-color: black";
	private final String DRAGGED_OVER_STYLE = "-fx-background-color: #1CFEBA";
	/**
	 * To initialize a blank cell
	 * @param number the (distinct) number of the cell
	 */
	public Cell(int number) {
		this.setEntity(null);
		this.setPrefWidth(Entity.ENTITY_WIDTH);
		this.setPrefHeight(Entity.ENTITY_HEIGHT);
		this.setStyle(DEFAULT_STYLE);
		initMouseOps();
		this.number = number;
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
	 * Check if this cell contains an entity.
	 * @return true iff the cell contains an entity
	 */
	public boolean containsEntity() {
		return this.entity != null;
	}
	
	/**
	 * Initializes the mouse operations for a given cell.
	 */
	private void initMouseOps() {
		this.setOnDragEntered(e -> this.setStyle(DRAGGED_OVER_STYLE));
		this.setOnDragExited(e -> this.setStyle(DEFAULT_STYLE));
		this.setOnDragOver(e -> {
			if (!e.getGestureSource().equals(this) && e.getDragboard().hasString()) {
				e.acceptTransferModes(TransferMode.COPY_OR_MOVE);
			}
			e.consume();
		});
		this.setOnDragDropped(e -> {
			Dragboard db = e.getDragboard();
			EntityLoader el = new EntityLoader();
			ImageView img = new ImageView(db.getImage());
			img.setFitWidth(Entity.ENTITY_WIDTH);
			img.setFitHeight(Entity.ENTITY_HEIGHT);
			this.getChildren().add(img);
			try {
				Entity en = el.buildEntity(number, db.getString());
				this.setEntity(en);
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			e.setDropCompleted(true);
			e.consume();
		});
		this.setOnMouseClicked(e1 -> {
			if (e1.getButton().equals(MouseButton.SECONDARY)) {
				if (this.containsEntity()) {
					PropertiesView pv = new LocalPropertiesView(this.getEntity(), componentList ->  {
						for (Component c : componentList) {
							this.getEntity().add(c);
						}
					});
					pv.open();
				}
			}
		});
	}
}
