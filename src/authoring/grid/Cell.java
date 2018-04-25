package authoring.grid;

import authoring.entities.Entity;
<<<<<<< HEAD
import authoring.entities.data.EntityLoader;
import javafx.event.EventHandler;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;
=======
>>>>>>> 50a491971bca15c750a9dabaafd9be2b9e1ae435
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
<<<<<<< HEAD
	private Image image;
=======
>>>>>>> 50a491971bca15c750a9dabaafd9be2b9e1ae435
	
	/**
	 * To initialize a blank cell
	 */
	public Cell() {
		this.setEntity(null);
		this.setPrefWidth(Entity.ENTITY_WIDTH);
		this.setPrefHeight(Entity.ENTITY_HEIGHT);
		this.setStyle("-fx-border-color: black");
<<<<<<< HEAD
		setupDragActions();
	}

	/**
	 * Sets up the drag actions for any cell. 
	 */
	private void setupDragActions() {
		this.setOnDragEntered(e -> this.setStyle("-fx-background-color: #1CFEBA"));
		this.setOnDragExited(e -> this.setStyle("-fx-border-color: black"));
		this.setOnDragDetected(e -> {
			if (this.image != null) {
				Dragboard db = this.startDragAndDrop(TransferMode.MOVE);
				ClipboardContent cc = new ClipboardContent();
				cc.putImage(this.image);
				db.setContent(cc);
				e.consume();
			}
		});
		this.setOnDragOver(e -> {
			if (e.getGestureSource() != this && (e.getDragboard().hasImage() || e.getDragboard().hasString())) {
				e.acceptTransferModes(TransferMode.COPY_OR_MOVE);
			}
			e.consume();
		});
		this.setOnDragDropped(e -> {
			Dragboard db = e.getDragboard();
			EntityLoader el = new EntityLoader();
			this.image = db.getImage();
			ImageView img = new ImageView(db.getImage());
			img.fitWidthProperty().bind(this.widthProperty());
			img.fitHeightProperty().bind(this.heightProperty());
			this.getChildren().add(img);
			try {
				el.buildEntity(0, db.getString());
			} catch (InstantiationException | IllegalAccessException | IllegalArgumentException
					| InvocationTargetException | SecurityException | ClassNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			e.setDropCompleted(true);
			e.consume();
		});
=======
>>>>>>> 50a491971bca15c750a9dabaafd9be2b9e1ae435
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
	
}
