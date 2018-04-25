package authoring.grid;

import java.lang.reflect.InvocationTargetException;

import authoring.entities.Entity;
import authoring.entities.data.EntityLoader;
import javafx.event.EventHandler;
import javafx.scene.image.ImageView;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseButton;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.Pane;

/**
 * Defines a cell in the grid. The cell will hold nothing when an entity has not been
 * placed inside of it, but will hold an entity when the user has placed an entity inside of it.
 * @author Dylan Powers
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
//		this.setOnDragEntered(e -> this.setStyle("-fx-background-color: #1CFEBA"));
//		this.setOnDragExited(e -> this.setStyle("-fx-border-color: black"));
//		this.setOnDragOver(e -> {
//			if (e.getGestureSource() != this && e.getDragboard().hasString()) {
//				e.acceptTransferModes(TransferMode.COPY_OR_MOVE);
//			}
//			e.consume();
//		});
//		this.setOnDragDropped(e -> {
//			Dragboard db = e.getDragboard();
//			EntityLoader el = new EntityLoader();
//			ImageView img = new ImageView(db.getImage());
//			img.fitWidthProperty().bind(this.widthProperty());
//			img.fitHeightProperty().bind(this.heightProperty());
//			img.setOnMouseClicked(e1->{
//				if(e1.getButton().equals(MouseButton.SECONDARY)) {
//					System.out.println("cocks!");
//					System.out.println(this.heightProperty());
//					this.setPrefHeight(Entity.ENTITY_HEIGHT*4);
//					img.fitHeightProperty().bind(this.heightProperty());
//					System.out.println(this.heightProperty());
////					img.setFitHeight(Entity.ENTITY_HEIGHT*4);
//				}
//			});
//			this.getChildren().add(img);
//			try {
//				el.buildEntity(0, db.getString());
//			} catch (InstantiationException | IllegalAccessException | IllegalArgumentException
//					| InvocationTargetException | SecurityException | ClassNotFoundException e1) {
//				// TODO Auto-generated catch block
//				e1.printStackTrace();
//			}
//			e.setDropCompleted(true);
//			e.consume();
//		});
	}

	public Entity getEntity() {
		return entity;
	}

	public void setEntity(Entity entity) {
		this.entity = entity;
	}
	
}
